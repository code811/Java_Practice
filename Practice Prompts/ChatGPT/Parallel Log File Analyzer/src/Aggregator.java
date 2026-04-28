import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Aggregator {

    private List<LogEntry> entries;

    public Aggregator(List<LogEntry> entries) {
        this.entries = entries;
    }

    public HashMap<String, Integer> getLogLevelOccurrences() throws ExecutionException, InterruptedException {

        int numOfThreads = 4;
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
        List<Future< HashMap<String, Integer> >> partialLists = new ArrayList<>();

        for(int i = 0; i < numOfThreads; i++) {
            int startChunk = (entries.size() * i) / numOfThreads;
            int endChunk = (entries.size() * (i + 1) / numOfThreads);

            partialLists.add(
                    executor.submit(() -> {
                        HashMap<String, Integer> partialEntries = new HashMap<>();

                        for(int j = startChunk; j < endChunk; j++) {
                            String level = entries.get(j).getLevel();
                            partialEntries.merge(level, 1, Integer::sum);
                        }
                        return partialEntries;
                    })
            );
        }

        HashMap<String, Integer> logLevelOccurrences = new HashMap<>();
        for(int i = 0; i < numOfThreads; i++) {
            HashMap<String, Integer> partialList = partialLists.get(i).get();

            for(String level : partialList.keySet()) {
                logLevelOccurrences.merge(level, partialList.get(level), Integer::sum);
            }
        }

        executor.shutdown();
        return logLevelOccurrences;
    }

    /*
    public HashMap<String, Integer> getLogLevelOccurrences() throws ExecutionException, InterruptedException {
        HashMap<String, Integer> logLevelOccurrence = new HashMap<>();

        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<HashMap<String, Integer>> partialList = new ArrayList<>();
        Future<List<HashMap<String, Integer>>> futureList = (executor.submit(() -> {
            String logLevel = entries.removeFirst().getLevel();
            partialList.add(new HashMap<>());
            partialList.getLast().put(logLevel, 1);

//            int instances = logLevelOccurrence.getOrDefault(logLevel, 0);
//            logLevelOccurrence.put(logLevel, instances + 1);

            return partialList;
        }));

        for(HashMap<String, Integer> partial : futureList.get()) {
            for(var entry : partial.entrySet()) {
                logLevelOccurrence.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }

        return logLevelOccurrence;
    }
     */


    public HashMap<String, Integer> getMostFrequentMessages(int topAmount) {
        HashMap<String, Integer> messages = new HashMap<>();

        for(String[] entry: parsedEntries) {
            String message = entry[3];

            if(messages.containsKey(message)) {
                messages.put(message, messages.get(message) + 1);
            }
            else {
                messages.put(message, 1);
            }
        }

        HashMap<String, Integer> mostFrequentMessages = new HashMap<>();

        for(int i = 0; i < topAmount; i++) {
            int greatest = 0;
            String greatestKey = "";
            for(String key : messages.keySet()) {
                if(messages.get(key) > greatest) {
                    greatest = messages.get(key);
                    greatestKey = key;
                }
            }

            mostFrequentMessages.put(greatestKey, greatest);
            messages.remove(greatestKey);
        }

        return mostFrequentMessages;
    }


    public HashMap<String, Integer> calculateLogsPerService() {
        HashMap<String, Integer> logsPerService = new HashMap<>();

        for(String[] entry : parsedEntries) {
            String service = entry[2];
            if(logsPerService.containsKey(service)) {
                logsPerService.put(service, logsPerService.get(service) + 1);
            }
            else {
                logsPerService.put(service, 1);
            }
        }

        return logsPerService;
    }

    public List< List<LogEntry> > detectErrorSpikes(int errorThreshold, int timeFrame) throws ExecutionException, InterruptedException {
        HashMap<String, List<LogEntry> > listOfDates = new HashMap<>();

        for(LogEntry entry : entries) {
            String date = entry.getDate();

            if(listOfDates.containsKey(date)) {
                listOfDates.get(date).add(entry);
            }
            else {
                listOfDates.put(date, new ArrayList<>());
                listOfDates.get(date).add(entry);
            }
        }

        int numOfThreads = 4;
        ExecutorService executor = Executors.newFixedThreadPool(numOfThreads);
        List< List<LogEntry> > listOfErrorSpikes = new ArrayList<>();
        Future<List< List<LogEntry> >> future = null;

        for(String date : listOfDates.keySet()) {

            future = executor.submit(() -> {
                List< List<LogEntry> > partialListOfErrorSpikes = new ArrayList<>();
                List<LogEntry> entriesThatDate = listOfDates.get(date);
                List<LogEntry> errorSpikesQueue = new ArrayList<>();

                entriesThatDate.sort(Comparator.comparing(LogEntry::getTimestamp));

                // Starts the sliding window
                int i = 0;
                int timeElapsed = 0;
                int numOfErrors = 0;
                for(; i < timeFrame; i++) {
                    LogEntry entry = entriesThatDate.get(i);
                    
                    timeElapsed = (errorSpikesQueue.getFirst().getSeconds() - errorSpikesQueue.getLast().getSeconds());
                    if(timeElapsed > timeFrame) {
                        break;
                    }

                    errorSpikesQueue.add(entry);
                    if(entry.getLevel().equals("ERROR")) {
                        numOfErrors++;
                    }
                }

                // Window begins to slide
                for(; i < entriesThatDate.size(); i++) {
                    LogEntry entry = entriesThatDate.get(i);

                    errorSpikesQueue.addFirst(entry);

                    timeElapsed = (errorSpikesQueue.getFirst().getSeconds() - errorSpikesQueue.getLast().getSeconds());
                    while(timeElapsed > timeFrame) {
                        timeElapsed -= errorSpikesQueue.getLast().getSeconds();

                        if(errorSpikesQueue.getLast().getLevel().equals("ERROR")) {
                            numOfErrors -= (numOfErrors != 0) ? 1 : 0; // Prevents numOfErrors from going negative
                        }
                        errorSpikesQueue.removeLast();
                    }

                    if(entry.getLevel().equals("ERROR")) {
                        numOfErrors++;
                    }

                    if (numOfErrors >= errorThreshold) {
                        partialListOfErrorSpikes.add(new ArrayList<>(errorSpikesQueue));
                    }
                }

                return partialListOfErrorSpikes;
            });
        }

        for(String date : listOfDates.keySet()) {
            listOfErrorSpikes.addAll(future.get());
        }

        executor.shutdown();
        return listOfErrorSpikes;
    }

























    /*
    public HashMap<String, Integer> detectErrorSpikes(int errorThreshold, int seconds) {
        HashMap<String, Integer> errorSpikes = new HashMap<>();

        for(String[] entry : parsedEntries) {
            String date = entry[0].split("T")[0];
            String time = entry[0].split("T")[1];
            int numberOfErrors = 0;

            for(String[] eachEntry : parsedEntries) {
                String level = eachEntry[1];

                if(!level.equals("ERROR")) {
                    continue;
                }

                String entryDate = eachEntry[0].split("T")[0];
                String entryTime = eachEntry[0].split("T")[1];

                if((!date.equals(entryDate)) ||
                        (timeToSecondsParser(time) + seconds) <= timeToSecondsParser(entryTime)) {

                    if(numberOfErrors >= errorThreshold) {
                        errorSpikes.put((date + "T" + time), numberOfErrors);
                    }
                    break;
                }

                numberOfErrors++;
            }
        }

        return errorSpikes;
    }

     */
}
