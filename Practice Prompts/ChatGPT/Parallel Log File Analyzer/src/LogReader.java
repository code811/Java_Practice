import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LogReader {

    public List<String> read(List<File> files) {
        ExecutorService executor = Executors.newFixedThreadPool(4);

        List< Future<List<String>> > futures = new ArrayList<>();
        for(File file : files) {
            futures.add(executor.submit(() -> {
                try {
                    return Files.readAllLines(file.toPath());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }));
        }

        List<String> entries = new ArrayList<>();
        for(Future<List<String>> future : futures) {
            try {
                entries.addAll(future.get());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        executor.shutdown();
        return entries;
    }
}
