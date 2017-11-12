import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TitleFinder {
    private final boolean testMode;
    private static final String INVALID_DIRECTIVE_MESSAGE = "Invalid directive.. Please try again";
    private static final String PROCESS_FILE_DIRECTIVE = "process-file";
    private static final String QUERY_DIRECTIVE = "query";
    private static final String QUIT_DIRECTIVE = "quit";
    private static final String DIRECTIVE_DELIMITER = " ";
    private static final int COMMAND_INDEX = 0;
    private static final int DIRECTIVE_PARAMETER_INDEX = 1;

    public TitleFinder(boolean testMode) {
        this.testMode = testMode;
    }

    public static void main(String[] args) {
        TitleFinder app = new TitleFinder(false);
        app.start();
    }

    public void start() {
        MovieDB movieDB = new MovieDB();
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        boolean finished = false;

        try(Scanner reader = new Scanner(System.in)) {
            while ( !finished ) {
                System.out.println("\nEnter a directive:  "+ PROCESS_FILE_DIRECTIVE + " | " + QUERY_DIRECTIVE + " | " + QUIT_DIRECTIVE);
                String directive = "";
                if (testMode) {
                    directive = TestData.data.pop();
                    System.out.println(directive);
                } else {
                    directive = reader.nextLine();
                }
                finished = actionDirective(movieDB, pool, directive);
            }
        }
    }

    private boolean actionDirective(MovieDB movieDB, ExecutorService pool, String directive) {
        boolean finished = false;

        if (directive == null || directive.isEmpty()) {
            System.out.println(INVALID_DIRECTIVE_MESSAGE);
        } else {
            String[] directiveArr = directive.split(DIRECTIVE_DELIMITER);
            String command  = directiveArr[COMMAND_INDEX].trim().toLowerCase();
            switch(command) {
                case PROCESS_FILE_DIRECTIVE:
                    processFile(movieDB, pool, directiveArr);
                    break;
                case QUERY_DIRECTIVE:
                    queryByPrefix(movieDB, directiveArr);
                    break;
                case QUIT_DIRECTIVE:
                    finished = quit(pool);
                    break;
                default:
                    System.out.println(INVALID_DIRECTIVE_MESSAGE);
                    break;
            }
        }

        return finished;
    }

    private boolean quit(ExecutorService pool) {
        pool.shutdown();
        return true;
    }

    private void queryByPrefix(MovieDB movieDB, String[] directiveArr) {
        if(isValidDirective(directiveArr)) {
            String prefix = directiveArr[DIRECTIVE_PARAMETER_INDEX].trim().toLowerCase();
            movieDB.searchMovies(prefix);
        }
    }

    private void processFile(MovieDB movieDB, ExecutorService pool, String[] directiveArr) {
        if(isValidDirective(directiveArr)) {
            String fileName = directiveArr[DIRECTIVE_PARAMETER_INDEX].trim();
            DBUpdater dbUpdaterTask = new DBUpdater(movieDB, fileName);
            if (testMode) {
                dbUpdaterTask.run();
            } else {
                pool.execute(dbUpdaterTask);
            }
        }
    }

    private boolean isValidDirective(String[] directives) {
        final int NUMBER_OF_DIRECTIVE_COMPONENTS = 2;
        if ( directives.length != NUMBER_OF_DIRECTIVE_COMPONENTS ) {
            System.out.println(INVALID_DIRECTIVE_MESSAGE);
            return false;
        }
        return true;
    }
}
