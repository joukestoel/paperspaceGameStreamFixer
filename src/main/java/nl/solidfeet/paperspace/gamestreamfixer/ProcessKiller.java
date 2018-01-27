package nl.solidfeet.paperspace.gamestreamfixer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ProcessKiller {
    final String options = "/PID %s /F";

    public boolean kill(String pid) {
        Process p = null;
        try {
            p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "taskkill.exe" + " " + String.format(options, pid));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            // Always skip the first line (header or message saying there is no process with that name
            String line = input.readLine();
            if (line.startsWith("SUCCESS")) {
                System.out.println(String.format("Successfully kill process: %s", pid));
                return true;
            } else {
                System.err.println(String.format("Could not kill process: %s ", line));
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
