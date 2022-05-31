package vttp2022.day02;

public class Main {
    public static void main(String[] args) {
        System.out.println("good morning");

        // Instantiate the array
        String[] todos = new String[4];

        // Assign some values to the array
        todos[0] = "learn Java";
        todos[1] = "go jogging";
        todos[2] = "watch a movie";

        System.out.printf("Index: %d: value = %s\n", 0, todos[0]);
        System.out.printf("Index: %d: value = %s\n", 0, todos[1]);
        System.out.printf("Index: %d: value = %s\n", 0, todos[2]);
        System.out.printf("Index: %d: value = %s\n", 0, todos[3]);
        
        // Every array has a property called length
        System.out.printf("Size of todos: %d\n", todos.length);

        // Size of args
        System.out.printf("Size of args: %d\n", args.length);
        System.out.printf("Index: %d: value = %s\n", 0, args[0]);
        System.out.printf("Index: %d: value = %s\n", 0, args[1]);
    }
}