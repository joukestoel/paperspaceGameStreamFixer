package nl.solidfeet.paperspace.gamestreamfixer;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

public class ProcessFinderTest {

    @Test
    public void testExistingProcess() {
        ProcessFinder finder = new ProcessFinder();
        Optional<String> pid = finder.getProcessPID("tasklist.exe");

        assertTrue(pid.isPresent());
    }

    @Test
    public void testNonExistingProcess() {
        ProcessFinder finder = new ProcessFinder();
        Optional<String> pid = finder.getProcessPID("nonexistingprocess.exe");

        assertFalse(pid.isPresent());
    }
}