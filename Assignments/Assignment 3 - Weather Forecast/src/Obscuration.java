public class Obscuration extends WeatherEvent {

    public static final String MEASUREMENT = "/8 mi";

    private int visibility;

    public Obscuration(String location, boolean active, int visibility) {
        super(location, active);
        setVisibility(visibility);
    }

    public int getVisibility() {
        return visibility;
    }

    public void setVisibility(int visibility) {
        if(visibility < 0) {
            visibility = 0;
        }
        this.visibility = visibility;
    }

    public String getVisibilityCondition() {
        return (visibility >= 56) ? "Normal" : visibility + MEASUREMENT;
    }

    @Override
    public String toString() {
        return super.toString() +
                "Visibility: " + getVisibilityCondition();
    }
}
