package vttp2022.day06;

public class Utils {

    public static void printBox(Box b) {
        // what is this content
        Object c = b.getContent();

        if (c instanceof String) {
            System.out.println("This is a string");
        } else if (c instanceof Integer) {
            System.out.println("This is an integer");
        }
    }

    
}
