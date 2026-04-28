public class Snow extends Precipitation {

    public static final String MEASUREMENT = "F";

    private double temperature;

    public Snow(String location, boolean active, double rateOfFall, double temperature) {
        super(location, active, rateOfFall);
        setTemperature(temperature);
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        if(temperature < -459.67) {
            temperature = -459.67;
        }
        if(temperature > 32) {
            temperature = 32;
        }
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTemperature: " + temperature + " " + MEASUREMENT;
    }
}
