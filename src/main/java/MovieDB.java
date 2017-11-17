import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class MovieDB {
    private final Trie movieDB = new Trie();
    private static final int NUMBER_OF_COMPONENTS = 3;
    private static String MOVIE_DELIMITER = "\t";
    private static String TITLE_DELIMITER = " ";

    public boolean processMovieData(String fileName) {
        boolean result = false;
        if (fileName == null || fileName.isEmpty()) {
            return result;
        }

        Path path = Paths.get(fileName);
        try {
            if(!Files.exists(path) || Files.isDirectory(path)) {
                System.out.println("Invalid file [" + fileName + "]");
            } else {
                Files.lines(path).forEach(this::processMovieInfo);
                result = true;
            }
        } catch (IOException ex) {
            System.out.println("Issue processing file [" + fileName + "]");
        }

        return result;
    }

    public void searchMovies(String prefix) {
        printMatches(movieDB.findByPrefix(prefix));
    }

    private void printMatches(List<Movie> movies) {
        movies.forEach(System.out::println);
    }

    private boolean processMovieInfo(String movieInfo) {
        String[] infoArr = movieInfo.split(MOVIE_DELIMITER);
        if ( infoArr.length != NUMBER_OF_COMPONENTS ) {
            System.out.println("Invalid movie information [" + movieInfo + "]");
            return false;
        } else {
            Movie movie = new Movie(infoArr[0],infoArr[1],infoArr[2]);
            updateMovieDB(movie);
        }
        return true;
    }

    private void updateMovieDB(Movie movie) {
        String[] wordsInTitle = movie.getTitle().split(TITLE_DELIMITER);
        for(String words: wordsInTitle) {
            this.movieDB.insert(words, movie);
        }
    }
}

class DBUpdater implements Runnable {
    private final MovieDB movieDB;
    private String fileName;

    public DBUpdater(MovieDB movieDB, String fileName) {
        this.movieDB = movieDB;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        movieDB.processMovieData(this.fileName);
    }
}