package vttp2022.day03;

public class Honda extends Car {

    public Honda() {
        this.setMake("honda");
    }

    public void accelerate() {
        System.out.println("Accelerating...");
    }

    public void stop(Integer count) {
        System.out.printf("Stopping in %d seconds\n");
    }
}
