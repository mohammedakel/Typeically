package edu.brown.cs.student.main;

import edu.brown.cs.student.main.spotify.FileParser;
import edu.brown.cs.student.main.spotify.SpotifyAuth;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 *
 * a class that tests spotify keys authenticator class
 *
 */

public class SpotifyAuthTest {

    @Test
    public void publicIdTest(){
        String publicID = SpotifyAuth.getPublicId();
        System.out.println(publicID);


    }

    @Test
    public void privateIdTest(){
        String privateID = SpotifyAuth.getPrivateId();
        System.out.println(privateID);
    }

    @Test
    public void RedirectURITest(){
        String RedirectURI = SpotifyAuth.getRedirectURI();
        System.out.println(RedirectURI);
    }

}
