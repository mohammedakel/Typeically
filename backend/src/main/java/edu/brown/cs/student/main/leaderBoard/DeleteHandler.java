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
import java.util.List;
import java.util.Map;

/**
 * Helper class that handles the /delete endpoint.
 */
public class DeleteHandler implements Route {
  private DatabaseLoader databaseLoader;

  /**
   * It takes in a database loader, which it uses to get the stored database shared
   * between the loader and all the handlers.
   * @param databaseLoader has the storedDatabase
   */
  public DeleteHandler(DatabaseLoader databaseLoader) {
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
      } catch (JSONException e) {
        System.out.println("ERROR: Invalid parameters passed in.");
      }

      String tableName = "";
      String columnName = "";
      String value = "";

      try {
        tableName = requestJSON.getString("tableName");
        columnName = requestJSON.getString("columnName");
        value = requestJSON.getString("value");

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
          this.deleteRow(tableName, columnName, value);
          result = ImmutableMap.of("result", "Successfully deleted the row(s) from the table, " + tableName + ".");

        } catch (JSONException | SQLException e) {
          result = ImmutableMap.of("result", "Failed to delete the row(s) from the table, " + tableName + ".");
        }
        return GSON.toJson(result);
      }

    } else {
      Map error2 = ImmutableMap.of("error", "Database is not properly loaded.");
      return GSON.toJson(error2);
    }
  }

  /**
   * It uses a SQL command to delete a row from the database and also the stored information.
   * @param tableName string
   * @param columnName string
   * @param value string
   * @throws JSONException json error
   * @throws SQLException sql error
   */
  public void deleteRow(String tableName, String columnName, String value)
      throws JSONException, SQLException {
    String sql = "DELETE FROM " + tableName + " WHERE " + columnName + " = ?";

    PreparedStatement deleteStatement = databaseLoader.getConn().prepareStatement(sql);

    deleteStatement.setString(1, value);
    deleteStatement.executeUpdate();
    deleteStatement.close();

    // update data in StoredData
    databaseLoader.setTableData();
  }
}