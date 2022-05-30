// This is my first app
// Change is the only constant
package myapp.core;

import java.io.Console;

public class Calculator {

    public static void main(String[] args) {
        // get the console
        Console cons = System.console();

        // Read the inputs from the users
        // opnd0
        String opnd0 = cons.readLine("Operand 0: ").trim();

        // opnd1
        String opnd1 = cons.readLine("Operand 1: ").trim();

        // oper
        String operator = cons.readLine("Operator; one of add, sub, div, mul: ").trim();

        // Convert String to Integer
        Integer val0 = Integer.parseInt(opnd0);
        Integer val1 = Integer.parseInt(opnd1);
        Float result = 0f; // float

        // Perform the calculation
        switch (operator) {
            case "add":
                // Cast result to float
                result = (float)(val0 + val1);
                break;

            case "sub":
                result = (float)(val0 - val1);
                break;

            case "mul":
                result = (float)(val0 * val1);
                break;

            case "div":
                result = (float)val0 / val1;
                break;

            default:
        }

        System.out.printf("Result: %d %s %d = %.3f\n", val0, operator, val1, result);
    }
    
}
