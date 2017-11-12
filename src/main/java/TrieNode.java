import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class TrieNode {
    private final ConcurrentHashMap<Character, TrieNode> children = new ConcurrentHashMap<>();
    private final ConcurrentSkipListSet<Movie> movies = new ConcurrentSkipListSet<>();
    static final int CAPACITY = 10;

    public TrieNode() {}

    public ConcurrentHashMap<Character, TrieNode> getChildren() {
        return children;
    }

    public Set<Movie> getMovies() {
        return movies;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        if (movies.size() >= (CAPACITY + 1) ) {
            movies.pollLast();
        }
    }
}
