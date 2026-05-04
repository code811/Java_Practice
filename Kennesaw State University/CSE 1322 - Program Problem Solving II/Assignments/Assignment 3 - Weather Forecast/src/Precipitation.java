public class Precipitation extends WeatherEvent {

    public static final String MEASUREMENT = "in/h";

    private double rateOfFall;

    public Precipitation(String location, boolean active, double rateOfFall) {
        super(location, active);
        setRateOfFall(rateOfFall);
    }

    public double getRateOfFall() {
        return rateOfFall;
    }

    public void setRateOfFall(double rateOfFall) {
        if(rateOfFall < 0) {
            rateOfFall = 0;
        }

        this.rateOfFall = rateOfFall;
    }

    public String getIntensity() {
        if(rateOfFall < 0.5) {
            return "Light";
        }
        else if(rateOfFall <= 1.5) {
            return "Medium";
        }
        else {
            return "Heavy";
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "Rate of Fall: " + rateOfFall + MEASUREMENT + " (" + getIntensity() + ")";
    }
}
