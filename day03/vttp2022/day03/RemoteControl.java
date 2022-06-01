package vttp2022.day03;

public class RemoteControl {

    private Controllable control;

    public RemoteControl(Controllable c) {
        this.control = c;
    }

    public void start() {
        this.control.start();
    }

    public void stop() {
        this.control.stop();
    }

}
