package nl.solidfeet.paperspace.gamestreamfixer;

import java.io.File;
import java.util.TimerTask;

public class FileRenamer extends TimerTask {
    private File orig,renamed;

    public FileRenamer() {
        orig = new File("C:\\Program Files\\Paperspace\\Agent\\ps_agent.exe");
        renamed = new File("C:\\Program Files\\Paperspace\\Agent\\ps_agentt.exe");
    }

    public void rename() {
        orig.renameTo(renamed);
    }

    public void restore() {
        renamed.renameTo(orig);
    }

    public void run() {
        restore();
    }
}
