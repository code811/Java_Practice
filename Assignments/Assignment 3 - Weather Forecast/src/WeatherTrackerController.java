import java.util.ArrayList;

public class WeatherTrackerController {

    private final WeatherTrackerUI UI;

    private final ArrayList<WeatherEvent> WEATHER_EVENTS;

    public WeatherTrackerController(WeatherTrackerUI UI, ArrayList<WeatherEvent> WEATHER_EVENTS) {
        this.UI = UI;
        this.WEATHER_EVENTS = WEATHER_EVENTS;
    }

    public void run() {
        UI.printHeader();
        int option = 0;
        do {
            option = UI.promptMenu();
            doOption(option);
        } while (option != 5);
    }

    public void doOption(int option) {
        switch(option) {
            case 1 -> addWeatherEvent();
            case 2 -> UI.promptUpdateLocation();
            case 3 ->UI.promptUpdateActive();
            case 4 -> UI.printViewAllEvents();
            case 5 -> UI.printQuit();
            default -> UI.printInvalid();
        }
    }

    // Case 1
    public void addWeatherEvent() {
        WEATHER_EVENTS.add(UI.promptAddWeatherEvent());
    }
}
