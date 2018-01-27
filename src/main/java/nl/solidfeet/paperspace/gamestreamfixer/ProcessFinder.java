package nl.solidfeet.paperspace.gamestreamfixer;

import com.opencsv.CSVParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;

public class ProcessFinder {

    private final String filter = "/FO CSV /FI \"IMAGENAME eq %s\"";

    public Optional<String> findProcess(String processName) {
        Optional<String> pid = Optional.empty();

        Process p = null;
        try {
            p = Runtime.getRuntime().exec(System.getenv("windir") + "\\system32\\" + "tasklist.exe" + " " + String.format(filter, processName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        CSVParser csvParser = new CSVParser();

        try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            // Always skip the first line (header or message saying there is no process with that name
            input.readLine();
            String line = input.readLine();

            if (line != null) {
                // parse the CSV
                String[] csvParsedLine = csvParser.parseLine(line);
                pid = Optional.of(csvParsedLine[1]);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pid;
    }
}
