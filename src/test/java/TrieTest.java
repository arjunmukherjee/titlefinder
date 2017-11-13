import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TrieTest {
    private Trie trie;

    @Before
    public void setUp() throws Exception {
        trie = new Trie();
        Movie movie = new Movie("2009","DE","budi Star Talk");
        trie.insert("budi", movie);
        trie.insert("star", movie);
        trie.insert("Talk", movie);

        Movie anotherMovie = new Movie("2009","DE","Audi Star Talk");
        trie.insert("Audi", anotherMovie);
        trie.insert("star", anotherMovie);
        trie.insert("Talk", anotherMovie);
    }

    @Test
    public void findByValidPrefix() throws Exception {
        List<Movie> resultList = trie.findByPrefix("st");
        List<Movie> expectedList = new LinkedList<>();
        expectedList.add(new Movie("2009","DE","Audi Star Talk"));
        expectedList.add(new Movie("2009","DE","budi Star Talk"));

        assertEquals(expectedList, resultList);
    }

    @Test
    public void findByInValidPrefix() throws Exception {
        List<Movie> resultList = trie.findByPrefix("invalid");
        List<Movie> expectedList = new LinkedList<>();
        assertEquals(expectedList, resultList);
    }

    @Test
    public void findByNullPrefix() throws Exception {
        List<Movie> resultList = trie.findByPrefix(null);
        List<Movie> expectedList = new LinkedList<>();
        assertEquals(expectedList, resultList);
    }

    @Test
    public void findByEmptyPrefix() throws Exception {
        List<Movie> resultList = trie.findByPrefix("");
        List<Movie> expectedList = new LinkedList<>();
        assertEquals(expectedList, resultList);
    }

}