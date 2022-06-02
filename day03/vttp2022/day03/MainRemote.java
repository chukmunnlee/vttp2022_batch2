package vttp2022.day03;

import java.rmi.Remote;

public class MainRemote {

    public static void main(String[] args) {
        Honda h = new Honda();
        Computer myNotebook = new Computer();

        Controllable ctrl = new Honda();
		  Controllable ctrlb = new String();

        RemoteControl rm = new RemoteControl(h);
        RemoteControl rmNotebook = new RemoteControl(myNotebook);

        rm.start();
        rmNotebook.start();

    }
}
