package nl.solidfeet.paperspace.gamestreamfixer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class ProcessFinder {

    final String filter = "/FO CSV /FI \"IMAGENAME eq %s\"";

    public Optional<String> getProcessPID(String processName) {
        Optional<String> pid;

        Process p = null;
        try {
            p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe" + " " + String.format(filter, processName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String line;
        try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            // Always skip the first line (header or message saying there is no process with that name
            input.readLine();
            line = input.readLine();

            if (line != null) {
                // parse the CSV

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
