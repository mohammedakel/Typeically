package edu.brown.cs.student.main.spotify;

/**
 * class responsible for reading the secret spotify IDs
 */

public class SpotifyAuth {

    /**
     * Reads the Spotify public ID from the secret text file where it has been stored.
     *
     * @return a String of the public developer spotify ID.
     */
    public static String getPublicId() {
        String filePath = "/Users/mohammedakel/Desktop/CS032-Spring2022/term-project-gletourn-janagony-jboustan-makel-mstephe7/config/secret/spotifyPublicID.txt";
        FileParser parser = new FileParser(filePath);
        return parser.readNewLine();
    }

    /**
     * Reads the Spotify public ID from the secret text file where it has been stored.
     *
     * @return a String of the public developer spotify ID.
     */
    public static String getPrivateId() {
        String filePath = "/Users/mohammedakel/Desktop/CS032-Spring2022/term-project-gletourn-janagony-jboustan-makel-mstephe7/config/secret/spotifyPrivateID.txt";
        FileParser parser = new FileParser(filePath);
        return parser.readNewLine();
    }

    /**
     * Reads the Spotify Redirect URI from the secret text file where it has been stored.
     *
     * @return a String of the Redirect UR.
     */
    public static String getRedirectURI() {
        String filePath = "/Users/mohammedakel/Desktop/CS032-Spring2022/term-project-gletourn-janagony-jboustan-makel-mstephe7/config/secret/RedirectURI.txt";
        FileParser parser = new FileParser(filePath);
        return parser.readNewLine();
    }
}

