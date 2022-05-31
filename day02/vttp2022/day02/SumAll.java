package vttp2022.day02;

public class SumAll {

    public static void main(String[] args) {

        int operand = 0;
        int result = 0;

        // Get access to all the numbers
        for (int i = 0; i < args.length; i++) {
            System.out.printf("%s\n", args[i]);
            operand = Integer.parseInt(args[i]);
            // result = result + operand;
            result += operand;
        }

        System.out.printf("The result is %d\n", result);
    }
}
