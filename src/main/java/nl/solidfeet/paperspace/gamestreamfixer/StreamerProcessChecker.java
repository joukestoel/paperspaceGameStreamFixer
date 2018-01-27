package nl.solidfeet.paperspace.gamestreamfixer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.Timer;
import java.util.TimerTask;

public class StreamerProcessChecker extends TimerTask {
    enum State {
        INACTIVE,STREAMING;
    }

    private final String streamingProcessName = "nvstreamer.exe";

    private State state;
    private ProcessFinder processFinder;
    private ProcessKiller processKiller;
    private FileRenamer fileRenamer;
    private Timer timer;

    public StreamerProcessChecker() {
        state = State.INACTIVE;

        processFinder = new ProcessFinder();
        processKiller = new ProcessKiller();
        fileRenamer = new FileRenamer();

        timer = new Timer();
    }

    public void check() {
        Optional<String> streamingProcessPid = processFinder.findProcess(streamingProcessName);

        if (state == State.INACTIVE) {
            // check whether we start streaming, if so, rename ps_agent.exe, stop ps_agent.exe process, wait 30 seconds and rename ps_agent.exe again
            if (streamingProcessPid.isPresent()) {
                System.out.println("Streaming started");

                fileRenamer.rename();
                timer.schedule(new FileRenamer(), 10000);

                Optional<String> psAgentPID = processFinder.findProcess("ps_agent.exe");
                if (psAgentPID.isPresent()) {
                    processKiller.kill(psAgentPID.get());
                }
                state = State.STREAMING;
            } else {
                System.out.println("Not streaming");
            }

        } else {
            // state is Streaming
            if (!streamingProcessPid.isPresent()) {
                System.out.println("Streaming stopped");
                state = State.INACTIVE;
            } else {
                System.out.println("Still streaming");
            }
        }
    }

    @Override
    public void run() {
        check();
    }
}