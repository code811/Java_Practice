public class Fog extends Obscuration {

    private boolean freezingFog;

    public Fog(String location, boolean active, int visibility, boolean freezingFog) {
        super(location, active, visibility);
        setVisibility(visibility);
        setFreezingFog(freezingFog);
    }

    @Override
    public void setVisibility(int visibility) {
        if(visibility < 0) {
            visibility = 1;
        }
        if(visibility > 5) {
            visibility = 4;
        }
        super.setVisibility(visibility);
    }

    public boolean isFreezingFog() {
        return freezingFog;
    }

    public void setFreezingFog(boolean freezingFog) {
        this.freezingFog = freezingFog;
    }

    public String getFreezingFogAlert() {
        return ((freezingFog) ? "\nALERT! FREEZING FOG!" : "");
    }

    @Override
    public String toString() {
        return super.toString() + getFreezingFogAlert();
    }
}
