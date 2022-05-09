package edu.brown.cs.student.main.spotify;

import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.AudioFeatures;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.browse.GetListOfNewReleasesRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.requests.data.tracks.GetAudioFeaturesForTrackRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
*
* A class responsible for searching for a track info based on a given basic query
*
*/
public class SpotifyTrack {
    private SpotifyClient client = new SpotifyClient();
    private GetListOfNewReleasesRequest getListOfNewReleasesRequest;

    //global variable used to determine the maximum number of entries to be returned from the search
    private final int limit = 10;


    /*
     *
     * A class constructor
     *  To change the search limit, change the parameter above
     *
     */
    public SpotifyTrack(String query){
        client.clientCredentials();
        this.getListOfNewReleasesRequest = client.spotifyApi.getListOfNewReleases()
                //.market(CountryCode.SE)
                .limit(limit)
                //.offset(0)
                //.includeExternal("audio")
                .build();
    }

    /*
     *
     * A method responsible for looking up  song features including audio features
     * @return List<TrackInfo>
     *
     *
     */
    public List<TrackInfo> getTracks() {
        List<TrackInfo> resultFinal = new ArrayList<TrackInfo>();
        /**
        try {
            final Paging<Track> trackPaging = searchTracksRequest.execute();
            Track[] result = trackPaging.getItems();
            for (Track track: result) {
                String trackID = track.getId();
                String trackURI = track.getUri();
                ArtistSimplified[] trackArtist = track.getArtists();
                List<String> artists = new ArrayList<>();
                for(ArtistSimplified art: trackArtist) {
                    artists.add(art.getName());
                }
                GetAudioFeaturesForTrackRequest getAudioFeaturesForTrackRequest = client.spotifyApi
                        .getAudioFeaturesForTrack(trackID)
                        .build();
                try {
                    final AudioFeatures audioFeatures = getAudioFeaturesForTrackRequest.execute();
                    //System.out.println("ID: " + audioFeatures.getId());
                    float acousticness = audioFeatures.getAcousticness();
                    temp.setAcoustic(acousticness);
                    float danceability = audioFeatures.getDanceability();
                    temp.setDanceability(danceability);
                    float energy = audioFeatures.getEnergy();
                    temp.setEnergy(energy);
                    float liveness = audioFeatures.getLiveness();
                    temp.setLiveness(liveness);
                    float loudness = audioFeatures.getLoudness();
                    temp.setLoudness(loudness);
                    float speechness = audioFeatures.getSpeechiness();
                    temp.setSpeechness(speechness);
                    float tempo  = audioFeatures.getTempo();
                    temp.setTempo(tempo);
                    float valance  = audioFeatures.getValence();
                    temp.setValance(valance);
                    //System.out.println(audioFeatures);
                } catch (IOException | SpotifyWebApiException | ParseException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                //System.out.println(explicit);
                //System.out.println(track.toString());
                //System.out.println("------------------------------------");
                resultFinal.add(temp);
            }
            System.out.println("Total: " + trackPaging.getTotal());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
         */
        return resultFinal;
    }

}
