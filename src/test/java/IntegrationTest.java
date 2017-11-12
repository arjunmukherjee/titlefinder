import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;

public class IntegrationTest {
    @Test
    public void testTitleFinder() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        TitleFinder app = new TitleFinder(true);
        app.start();

        String expectedResult = "\nEnter a directive:  process-file | query | quit\n" +
                "invalidCommand\n" +
                "Invalid directive.. Please try again\n"+
                "\nEnter a directive:  process-file | query | quit\n" +
                "process-file \n" +
                "Invalid directive.. Please try again\n" +
                "\nEnter a directive:  process-file | query | quit\n" +
                "process-file input/input.txt\n" +
                "\n" +
                "Enter a directive:  process-file | query | quit\n" +
                "query st\n" +
                "2009\tDE\taudi Star Talk\n" +
                "2009\tDE\tBudi Star Talk\n" +
                "2009\tDE\tCudi Star Talk\n" +
                "2009\tDE\tDudi Star Talk\n" +
                "2009\tDE\tEudi Star Talk\n" +
                "2009\tDE\tFudi Star Talk\n" +
                "2009\tDE\tGudi Star Talk\n" +
                "2009\tDE\tHudi Star Talk\n" +
                "2009\tDE\tIudi Star Talk\n" +
                "2009\tDE\tJudi Star Talk\n" +
                "\n" +
                "Enter a directive:  process-file | query | quit\n" +
                "quit\n";

        System.out.flush();
        assertEquals(expectedResult, baos.toString());
    }
}
