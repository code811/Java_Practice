import java.util.ArrayList;
import java.util.Scanner;

public class WeatherTrackerUI {

    private final Scanner SC = new Scanner(System.in);

    private final ArrayList<WeatherEvent> WEATHER_EVENTS;

    private WeatherEvent weatherEvent;

    public WeatherTrackerUI(ArrayList<WeatherEvent> WEATHER_EVENTS) {
        this.WEATHER_EVENTS = WEATHER_EVENTS;
    }

    public void printHeader() {
        System.out.println("[Weather Tracking System]");
    }

    public int promptMenu() {
        System.out.println("1. Add weather event");
        System.out.println("2. Update location");
        System.out.println("3. Update active");
        System.out.println("4. View all events");
        System.out.println("5. Quit");
        System.out.print("Enter your option: ");
        int option = Integer.parseInt(SC.nextLine());
        System.out.println();
        return option;
    }

    // Case 1. Add weather event
    public WeatherEvent promptAddWeatherEvent() {
        System.out.println("1. Rain");
        System.out.println("2. Snow");
        System.out.println("3. Fog");
        System.out.println("4. Particle");
        System.out.print("What type of weather event is being added? ");
        int option = Integer.parseInt(SC.nextLine());

        switch(option) {
            case 1 -> promptRain();
            case 2 -> promptSnow();
            case 3 -> promptFog();
            case 4 -> promptParticle();
            default -> printInvalid();
        }
        System.out.println();
        return weatherEvent;
    }

    private String promptLocation() {
        System.out.print("Where is the event happening? ");
        return SC.nextLine();
    }

    private double promptPrecipitation() {
        System.out.print("What is the rate of fall? (" + Precipitation.MEASUREMENT + ") ");
        return Double.parseDouble(SC.nextLine());
    }

    private int promptObscuration() {
        System.out.print("What is the visibility? (1" + Obscuration.MEASUREMENT + ") ");
        return Integer.parseInt(SC.nextLine());
    }

    // Case 1. Rain
    private void promptRain() {
        String location = promptLocation();
        boolean active = true;
        double rateOfFall = promptPrecipitation();
        System.out.print("What is the diameter of the drops? (" + Rain.MEASUREMENT + ") ");
        double dropSize = Double.parseDouble(SC.nextLine());

        weatherEvent = new Rain(location, active, rateOfFall, dropSize);
        System.out.println("Rain event added");
    }

    // Case 2. Snow
    private void promptSnow() {
        String location = promptLocation();
        boolean active = true;
        double rateOfFall = promptPrecipitation();
        System.out.print("What is the temperature? (" + Snow.MEASUREMENT + ") ");
        double temperature = Double.parseDouble(SC.nextLine());

        weatherEvent = new Snow(location, active, rateOfFall, temperature);
        System.out.println("Snow event added");
    }

    // Case 3. Fog
    private void promptFog() {
        String location = promptLocation();
        boolean active = true;
        int visibility = promptObscuration();
        boolean freezingFog = false;
        System.out.print("Is the fog freezing? (y/n) ");
        if ((SC.nextLine().equalsIgnoreCase("y"))) {
            freezingFog = true;
        }

        weatherEvent = new Fog(location, active, visibility, freezingFog);
        System.out.println("Fog event added");
    }

    // Case 4. Particle
    private void promptParticle() {
        String location = promptLocation();
        boolean active = true;
        int visibility = promptObscuration();
        System.out.print("What is the obscuration made of? (Sand/Dust/Ash/Other) ");
        String particleType = SC.nextLine();

        weatherEvent = new Particle(location, active, visibility, particleType);
        System.out.println("Particle event added");
    }

    // Case 2. Update location
    public void promptUpdateLocation() {
        int id = promptIdToUpdate();
        if(id != -1) {
            System.out.print("Enter the new location of the weather event (currently '" + WEATHER_EVENTS.get(id).getLocation() + "'): ");
            WEATHER_EVENTS.get(id).setLocation(SC.nextLine());
            System.out.println("Location updated");
        }
        System.out.println();
    }

    // Case 3. Update active
    public void promptUpdateActive() {
        int index = promptIdToUpdate();
        if(index != -1) {
            WEATHER_EVENTS.get(index).setActive(!WEATHER_EVENTS.get(index).isActive());
            System.out.println("Event set to '" + getActive(index) + "'");
        }
        System.out.println();
    }

    private int promptIdToUpdate() {
        System.out.print("Enter id of weather event: ");
        int id = Integer.parseInt(SC.nextLine());

        int index = searchWeatherId(id);
        if(index != -1) {
            return index;
        }
        System.out.println("No event with that id.");
        return -1;
    }

    private int searchWeatherId(int id) {
        for(int i = 0; i < WEATHER_EVENTS.size(); i++) {
            if(WEATHER_EVENTS.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    private String getActive(int id) {
        if(WEATHER_EVENTS.get(id).isActive()) {
            return "active";
        }
        return "inactive";
    }

    // Case 4. View all events
    public void printViewAllEvents() {
        for(WeatherEvent weatherEvent : WEATHER_EVENTS) {
            System.out.println(weatherEvent);
            System.out.println();
        }
    }

    // Case 5. Quit
    public void printQuit() {
        System.out.println("Shutting off...");
    }

    // Default
    public void printInvalid() {
        System.out.println("Invalid option!");
        System.out.println();
    }
}
