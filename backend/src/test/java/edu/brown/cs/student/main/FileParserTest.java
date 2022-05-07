package edu.brown.cs.student.main;

import edu.brown.cs.student.main.spotify.FileParser;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * a class that tests generic File parser class and its read line method
 *
 */

public class FileParserTest {

    @Test
    public void testFileParserLine(){
        String  filePath = "/Users/mohammedakel/Desktop/CS032-Spring2022/term-project-gletourn-janagony-jboustan-makel-mstephe7/config/secret/sampleOneLineTest.txt";
        FileParser parser = new FileParser(filePath);
        String firstLine = "11111";
        String secondLine = "22";
        String firstLineExpected = parser.readNewLine();
        String secondLineExpected = parser.readNewLine();
        assertEquals(firstLine, firstLineExpected);
        assertEquals(secondLine, secondLineExpected);
    }
}
