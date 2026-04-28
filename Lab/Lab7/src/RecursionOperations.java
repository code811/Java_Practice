public class RecursionOperations {

    public static int recursiveMultiply(int factor1, int factor2) {
        if(factor2 == 1) return factor1;
        return factor1 + recursiveMultiply(factor1, (factor2 - 1));
    }

    public static int recursiveDivision(int dividend, int divisor) {
        if(dividend - divisor < 0) return 0;
        return 1 + recursiveDivision((dividend - divisor) , divisor);
    }

    public static int recursiveRemainder(int dividend, int divisor) {
        if(dividend - divisor < 0) return dividend;
        return recursiveRemainder((dividend - divisor) , divisor);
    }

    public static String recursiveEcho(String echo, int numOfReverberations) {
        if(numOfReverberations == 1) return echo;
        return echo + recursiveEcho(echo, (numOfReverberations - 1));
    }

    public static boolean recursiveReverse(String forwards, String backwards) {
        if(forwards.length() != backwards.length()) return false;
        if(forwards.toLowerCase().charAt(0) != backwards.toLowerCase().charAt(backwards.length() - 1)) return false;
        if(forwards.length() == 1) return true;
        return recursiveReverse(forwards.substring(1), backwards.substring(0, backwards.length() - 1));
    }
}
