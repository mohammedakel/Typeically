
package edu.brown.cs.student.main.spotify;

// imports

import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import edu.brown.cs.student.main.leaderBoard.DatabaseLoader;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Helper class that handles the /spotift endpoint.
 */

public class SpotifyHandler implements Route {

/**
   * Inherited method, returns Json object.
   * @param request req
   * @param response res
   * @return Json Object
   */

  @Override
  public Object handle(Request request, Response response) {
    TopTracks getTopTracks = new TopTracks();
    List<HashMap<String, String>> topTracks = getTopTracks.getListOfNewReleases();

    Gson GSON = new Gson();

    if (topTracks != null) {
      // create an immutable map
      Map topTracksMap = ImmutableMap.of("topTracks", topTracks);

      // return a json of the data
      return GSON.toJson(topTracksMap);
    } else {
      Map error = ImmutableMap.of("error", "Top tracks is null.");
      return GSON.toJson(error);
    }
  }
}
