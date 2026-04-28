public class LogEntry {

    private String timestamp;
    private String level;
    private String service;
    private String message;

    public LogEntry(String timestamp, String level, String service, String message) {
        this.timestamp = timestamp;
        this.level = level;
        this.service = service;
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDate() {
        return timestamp.split("T")[0];
    }

    public String getTime() {
        return timestamp.split("T")[1];
    }

    public int getSeconds() {
        String time = getTime();
        String[] parsedTime = time.split(":");

        int seconds = 0;
        for(int i = 0; i < parsedTime.length; i++) {
            switch(i) {
                case 0 -> seconds += (Integer.parseInt(parsedTime[0]) * 60 * 60);
                case 1 -> seconds += (Integer.parseInt(parsedTime[1]) * 60);
                case 2 -> seconds += (Integer.parseInt(parsedTime[2]));
            }
        }

        return seconds;
    }

    public String getLevel() {
        return level;
    }

    public String getService() {
        return service;
    }

    public String getMessage() {
        return message;
    }
}
