import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MovieDBTest {
    private MovieDB movieDB;

    @Before
    public void setUp() throws Exception {
        movieDB = new MovieDB();
    }

    @Test
    public void processMovieDataDoesNotProcessANullFileName() throws Exception {
        assertFalse(movieDB.processMovieData(null));
    }

    @Test
    public void processMovieDataDoesNotProcessAnEmptyFileName() throws Exception {
        assertFalse(movieDB.processMovieData(""));
    }

    @Test
    public void processMovieDataDoesNotProcessANonExistantFile() throws Exception {
        assertFalse(movieDB.processMovieData("invalidFile.txt"));
    }

    @Test
    public void processMovieDataProcessesAValidFile() throws Exception {
        assertTrue(movieDB.processMovieData("input/input.txt"));
    }
}