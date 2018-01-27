package nl.solidfeet.paperspace.gamestreamfixer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TimerTask;

public class StreamerProcessChecker extends TimerTask {

    public void check() {
        String line;
        String pidInfo = "";

        Process p = null;
        try {
            p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            while ((line = input.readLine()) != null) {
                pidInfo += line;
            }

            input.close();

            if (pidInfo.contains("your process name")) {
                // do what you want
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        check();
    }
}