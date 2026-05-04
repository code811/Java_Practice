public class WeatherEvent {

    private String location;

    private static int nextId = 0;

    private int id;

    private boolean active;

    public WeatherEvent(String location, boolean active) {
        id = nextId++;
        this.location = location;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Weather Event Location: " + location + '\n' +
                "id: " + id + '\n' +
                "active: " + active + '\n';
    }
}
