package vttp2022.day06;

import java.util.List;

public class App {
    public static void main( String[] args ) {
        BoxString boxOfStrings = new BoxString();
        boxOfStrings.setContent("This is a string");

        Box boxOfInts = new Box();
        boxOfInts.setContent(3);

        // No type checking for box, because the
        // content member is Object 
        boxOfInts.setContent(boxOfStrings);

        boxOfStrings.setContent("false");

        BoxG<Integer> bInt = new BoxG<>();
        BoxG<String> bStr = new BoxG<String>();
        BoxG<List<String>> bListStr = new BoxG<>();
        BoxG<BoxG<Float>> bBoxFloat;

        bBoxFloat = new BoxG<>();

        // This is incorrect
        //bInt.setContent("contenabct");
    }
}
