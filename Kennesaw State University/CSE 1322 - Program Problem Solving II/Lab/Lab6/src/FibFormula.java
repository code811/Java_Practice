public class FibFormula implements FindFib {

    // Set public since values are static and immutable, are math constants
    public static final double GoldenRatio = (1 + Math.sqrt(5)) / 2;
    public static final double GoldenRatioConjugate = (1 - Math.sqrt(5)) / 2;

    @Override
    public int calculateFib(int position) {
        return (int)((Math.pow(GoldenRatio, position) - Math.pow(GoldenRatioConjugate, position)) / Math.sqrt(5));
    }

    @Override
    public String toString() {
        return "Binet's Formula";
    }
}
