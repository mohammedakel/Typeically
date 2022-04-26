package edu.brown.cs.student.main.DatabaseIntegration;

// imports
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import edu.brown.cs.student.main.TableLoadViz.DatabaseLoader;
import org.json.JSONException;
import org.json.JSONObject;
import spark.Request;
import spark.Response;
import spark.Route;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class that handles the /insert endpoint.
 */
public class InsertHandler implements Route {
  private DatabaseLoader databaseLoader;

  /**
   * It takes in a database loader, which it uses to get the stored database shared
   * between the loader and all the handlers.
   * @param databaseLoader has the storedDatabase
   */
  public InsertHandler(DatabaseLoader databaseLoader) {
    this.databaseLoader = databaseLoader;
  }

  /**
   * Inherited method, returns Json object depending on
   * the information in the url and state of the database connection.
   *
   * @param request req
   * @param response res
   * @return Json Object
   */
  @Override
  public Object handle(Request request, Response response) {
    Gson GSON = new Gson();

    if (databaseLoader.getConn() != null) {
      JSONObject requestJSON = null;
      try {
        requestJSON = new JSONObject(request.body());
        System.out.println(requestJSON);
      } catch (JSONException e) {
        System.out.println("ERROR: Invalid parameters passed in.");
      }
      String tableName = "";

      try {
        tableName = requestJSON.getString("tableName");
        System.out.println(tableName);

      } catch (JSONException e) {
        System.out.println("ERROR: Invalid parameters passed in.");
      }

      List<String> tableNames = databaseLoader.getStoredDatabase().getTableNames();

      if (!tableNames.contains(tableName)) {
        Map error1 = ImmutableMap.of("error", "Table does not exist.");
        return GSON.toJson(error1);

      } else {
        Map result;
        try {

          List<String> columnNames =  databaseLoader.getStoredDatabase().getColumnNames(tableName);
          Map<String, String> rowToInsert = new HashMap<>();

          for (int col = 0; col < columnNames.size(); col++) {
            String colName = columnNames.get(col);
            String value = requestJSON.getString(colName);
            rowToInsert.put(colName,value);
          }

          this.insertRow(tableName, rowToInsert);
          result = ImmutableMap.of("result", "Successfully inserted the row into the table, " + tableName + ".");

        } catch (JSONException | SQLException e) {
          result = ImmutableMap.of("result", "Failed to insert the row into the table, " + tableName + ".");
        }
        return GSON.toJson(result);
      }

    } else {
      Map error2 = ImmutableMap.of("error", "Database is not properly loaded.");
      return GSON.toJson(error2);
    }
  }

  /**
   * It uses a SQL command to insert a row into the database and also the stored information.
   * @param tableName string
   * @param rowToInsert map (row to insert)
   * @throws JSONException json error
   * @throws SQLException sql error
   */
  public void insertRow(String tableName, Map<String, String> rowToInsert) throws SQLException {
    List<String> columnNames = databaseLoader.getStoredDatabase().getColumnNames(tableName);
    StringBuilder placeholders = new StringBuilder("(");
    if (columnNames.size() == 1) {
      placeholders = new StringBuilder("?");
    } else {
      for (int i = 0; i < columnNames.size() - 1; i++) {
        placeholders.append("?, ");
      }
      placeholders.append("?)");
    }

    String sql = "INSERT INTO " + tableName + " VALUES " + placeholders;
    PreparedStatement insertStatement = databaseLoader.getConn().prepareStatement(sql);
    for (int col = 0; col < columnNames.size(); col++) {
      String colName = columnNames.get(col);
      String value = rowToInsert.get(colName);
      insertStatement.setString(col+1, value);
    }
    insertStatement.executeUpdate();
    insertStatement.close();

    // update data in StoredData
    databaseLoader.setTableData();
  }
}