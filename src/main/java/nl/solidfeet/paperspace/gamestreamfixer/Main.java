package nl.solidfeet.paperspace.gamestreamfixer;

import java.util.Timer;

public class Main {
    public static void main(String... args) {
        Timer timer = new Timer();
        timer.schedule(new StreamerProcessChecker(), 2000, 2000);
    }
}
