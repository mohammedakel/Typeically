package edu.brown.cs.student.main;

// look into using these imports for your REPL!
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import edu.brown.cs.student.main.DatabaseIntegration.DatabaseHandler;
import edu.brown.cs.student.main.DatabaseIntegration.DeleteHandler;
import edu.brown.cs.student.main.DatabaseIntegration.InsertHandler;
import edu.brown.cs.student.main.DatabaseIntegration.StoredDatabase;
import edu.brown.cs.student.main.DatabaseIntegration.VerifyAPI;
import edu.brown.cs.student.main.DatabaseIntegration.TableHandler;
import edu.brown.cs.student.main.DatabaseIntegration.UpdateHandler;
import edu.brown.cs.student.main.REPL.GenericREPL;
import edu.brown.cs.student.main.TableLoadViz.DatabaseLoader;
import joptsimple.OptionParser;
import joptsimple.OptionSet;
import spark.ExceptionHandler;
import spark.Request;
import spark.Response;
import spark.Spark;

/**
 * The Main class of our project. This is where execution begins.
 */
public final class Main {

  // use port 4567 by default when running server
  private static final int DEFAULT_PORT = 4567;
  private static DatabaseLoader databaseLoader;

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private String[] args;

  private Main(String[] args) {
    this.args = args;
  }

  private void run() {

    // instantiate database loader
    databaseLoader = new DatabaseLoader();
    databaseLoader.setStoredDatabase(new StoredDatabase());

    // instantiate api demo class
    VerifyAPI verifyAPI = new VerifyAPI();

    // set up parsing of command line flags
    OptionParser parser = new OptionParser();

    // "./run --gui" will start a web server
    parser.accepts("gui");

    // use "--port <n>" to specify what port on which the server runs
    parser.accepts("port").withRequiredArg().ofType(Integer.class)
            .defaultsTo(DEFAULT_PORT);

    parser.accepts("test");

    OptionSet options = parser.parse(args);
    if (options.has("gui")) {
      runSparkServer((int) options.valueOf("port"));
    }

    // -------------------- DECLARATIONS -------------------------


    // ------------------ REPL INSTANTIATION -------------------
    GenericREPL repl = new GenericREPL();

    try {
      // database integration
      repl.addCommand("load_database", databaseLoader);
      repl.addCommand("verify_api", verifyAPI);
    } catch (IllegalArgumentException e) {
      System.out.println("ERROR: duplicate command was added.");
    }

    // run the REPL until EOF, or until we have an IOException
    try {
      repl.runREPL();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Runs server.
   * @param port port
   */
  public static void runSparkServer(int port) {

    // set port to run the server on
    Spark.port(port);

    // specify location of static resources (HTML, CSS, JS, images, etc.)
    Spark.externalStaticFileLocation("src/main/resources/static");

    Spark.options("/*", (request, response) -> {
      String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
      if (accessControlRequestHeaders != null) {
        response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
      }

      String accessControlRequestMethod = request.headers("Access-Control-Request-Method");

      if (accessControlRequestMethod != null) {
        response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
      }
      return "OK";
    });

    // Allows requests from any domain (i.e., any URL). This makes development
    // easier, but it’s not a good idea for deployment.
    Spark.before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

    // Put Routes Here

    // create a call to Spark.get to make a GET request to a URL
    Spark.exception(Exception.class, new ExceptionPrinter());
    Spark.get("/table", new TableHandler(databaseLoader));
    Spark.get("/database", new DatabaseHandler(databaseLoader));
    Spark.post("/insert", new InsertHandler(databaseLoader));
    Spark.post("/update", new UpdateHandler(databaseLoader));
    Spark.post("/delete", new DeleteHandler(databaseLoader));
    Spark.init();
  }

  /**
   * Display an error page when an exception occurs in the server.
   */
  private static class ExceptionPrinter implements ExceptionHandler {
    @Override
    public void handle(Exception e, Request req, Response res) {
      res.status(500);
      StringWriter stacktrace = new StringWriter();
      try (PrintWriter pw = new PrintWriter(stacktrace)) {
        pw.println("<pre>");
        e.printStackTrace(pw);
        pw.println("</pre>");
      }
      res.body(stacktrace.toString());
    }
  }

  public static DatabaseLoader getDatabaseLoader() {
    return databaseLoader;
  }

  public static void setDatabaseLoader(DatabaseLoader loader) {
     databaseLoader = loader;
  }
}
