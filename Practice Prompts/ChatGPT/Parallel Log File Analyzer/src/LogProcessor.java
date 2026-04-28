import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class LogProcessor {

    public LogEntry parse(String line) {
        String[] parsedEntry = line.split(" ", 4);

        return new LogEntry(
                parsedEntry[0],
                parsedEntry[1],
                parsedEntry[2],
                parsedEntry[3]
        );
    }
}
