public class Rain extends Precipitation {

    public static final String MEASUREMENT = "in";

    private double dropSize;

    public Rain(String location, boolean active, double rateOfFall, double dropSize) {
        super(location, active, rateOfFall);
        setDropSize(dropSize);
    }

    public String classifyDropSize() {
        if(dropSize < 0.066) {
            return "Small";
        }
        else if(dropSize <= 0.112) {
            return "Medium";
        }
        else {
            return "Large";
        }
    }

    public double getDropSize() {
        return dropSize;
    }

    public void setDropSize(double dropSize) {
        if(dropSize < 0.02) {
            dropSize = 0;
        }
        this.dropSize = dropSize;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nDrop size: " + dropSize + "(" + classifyDropSize() + ")";
    }
}
