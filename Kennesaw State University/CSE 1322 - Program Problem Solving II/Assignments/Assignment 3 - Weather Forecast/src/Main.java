import java.util.ArrayList;

public class Main {
  public static void main(String[] args) {
    final ArrayList<WeatherEvent> WEATHER_EVENTS = new ArrayList<>();
    final WeatherTrackerUI UI = new WeatherTrackerUI(WEATHER_EVENTS);
    final WeatherTrackerController CONTROLLER = new WeatherTrackerController(UI, WEATHER_EVENTS);

    CONTROLLER.run();

  }
}