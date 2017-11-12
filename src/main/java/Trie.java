import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Trie {
    private final TrieNode root;

    public Trie() {
        this.root = new TrieNode();
    }

    public void insert(String word, Movie movie) {
        word = word.toLowerCase();
        Map<Character,TrieNode> children = root.getChildren();
        TrieNode currentNode = root;
        for(char c: word.toCharArray()) {
            TrieNode newNode;
            if (children.containsKey(c)) {
                newNode = children.get(c);
            } else {
                newNode = new TrieNode();
                children.put(c, newNode);
            }
            newNode.addMovie(movie);
            currentNode = newNode;
            children = currentNode.getChildren();
        }
    }

    public List<Movie> findByPrefix(String prefix) {
        if (prefix == null || prefix.isEmpty()) {
            return Collections.emptyList();
        }

        List<Movie> movieMatches = new LinkedList<>();
        Map<Character,TrieNode> children = root.getChildren();
        TrieNode currentNode = root;
        for (char c: prefix.toCharArray()) {
            if (children.containsKey(c)) {
                currentNode = children.get(c);
                children = currentNode.getChildren();
                movieMatches.clear();
                movieMatches.addAll(currentNode.getMovies());
            } else {
                System.out.println("No results found for " + prefix);
                movieMatches.clear();
                break;
            }
        }

        return movieMatches;
    }
}
