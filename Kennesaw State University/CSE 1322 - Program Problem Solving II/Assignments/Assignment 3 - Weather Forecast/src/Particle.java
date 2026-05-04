public class Particle extends Obscuration {

    private String particleType;

    public Particle(String location, boolean active, int visibility, String particleType) {
        super(location, active, visibility);
        setParticleType(particleType);
    }

    public String getParticleType() {
        return particleType;
    }

    public void setParticleType(String particleType) {
        switch(particleType) {
            case "Dust":
            case "Sand":
            case "Ash": {
                this.particleType = particleType;
                break;
            }
            default: this.particleType = "Other";
        }
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nParticle type: " + particleType;
    }
}
