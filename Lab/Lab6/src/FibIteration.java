public class FibIteration implements FindFib {

    @Override
    public int calculateFib(int position) {
        if(position == 0) return 1;
        if(position == 1) return 1;

        int num1 = 1, num2 = 1;
        for(int i = 2; i < position; i++) {
            int temp = num1 + num2;
            num1 = num2;
            num2 = temp;
        }
        return num2;
    }

    @Override
    public String toString() {
        return "iteration";
    }
}
