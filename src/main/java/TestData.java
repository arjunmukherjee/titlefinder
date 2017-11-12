import java.util.Stack;

public abstract class TestData {
    public static Stack<String> data = new Stack<>();
    static {
        data.push("quit");
        data.push("query st");
        data.push("process-file input/input.txt");
        data.push("process-file ");
        data.push("invalidCommand");
    }
}








