import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TrieNodeTest {

    private TrieNode node;

    @Before
    public void setUp() throws Exception {
        node = new TrieNode();
        node.addMovie(new Movie("2009","DE","nudi Star Talk"));
        node.addMovie(new Movie("2009","DE","budi Star Talk"));
        node.addMovie(new Movie("2009","DE","dudi Star Talk"));
        node.addMovie(new Movie("2009","DE","judi Star Talk"));
        node.addMovie(new Movie("2009","DE","kudi Star Talk"));
        node.addMovie(new Movie("2009","DE","cudi Star Talk"));
        node.addMovie(new Movie("2009","DE","eudi Star Talk"));
        node.addMovie(new Movie("2009","DE","fudi Star Talk"));
        node.addMovie(new Movie("2009","DE","hudi Star Talk"));
        node.addMovie(new Movie("2009","DE","iudi Star Talk"));
        node.addMovie(new Movie("2009","DE","ludi Star Talk"));
        node.addMovie(new Movie("2009","DE","mudi Star Talk"));
        node.addMovie(new Movie("2009","DE","gudi Star Talk"));
    }

    @Test
    public void addMovieOnlyAddsUptoCapacity() {
        node.addMovie(new Movie("2009","DE","zudi Star Talk"));
        assertEquals(node.getMovies().size(), TrieNode.CAPACITY);
    }

    @Test
    public void addMovieKeepsMoviesSorted() {
        List<Movie> movieMatches = new LinkedList<>();
        movieMatches.addAll(node.getMovies());

        List<Movie> expectedMatches = new LinkedList<>();
        expectedMatches.add(new Movie("2009","DE","budi Star Talk"));
        expectedMatches.add(new Movie("2009","DE","cudi Star Talk"));
        expectedMatches.add(new Movie("2009","DE","dudi Star Talk"));
        expectedMatches.add(new Movie("2009","DE","eudi Star Talk"));
        expectedMatches.add(new Movie("2009","DE","fudi Star Talk"));
        expectedMatches.add(new Movie("2009","DE","gudi Star Talk"));
        expectedMatches.add(new Movie("2009","DE","hudi Star Talk"));
        expectedMatches.add(new Movie("2009","DE","iudi Star Talk"));
        expectedMatches.add(new Movie("2009","DE","judi Star Talk"));
        expectedMatches.add(new Movie("2009","DE","kudi Star Talk"));

        assertEquals(movieMatches, expectedMatches);
    }
}
