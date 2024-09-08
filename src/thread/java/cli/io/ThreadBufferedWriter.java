package thread.java.cli.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AlexanDev_CWA
 */
public class ThreadBufferedWriter extends Thread {

    private static final Logger LOGGER = Logger.getLogger(ThreadBufferedWriter.class.getName());
    public final File file;
    private final int milis;
    private final int iterations;

    public ThreadBufferedWriter(File file, int milis, int interations) {
        this.file = file;
        this.milis = milis;
        this.iterations = interations;
    }

    @Override
    public void run() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file,true))) {
            for (int i = 0; i < iterations; i++) {
                writer.write(String.format("Iteración #%d | Thread: %s", i, this.getName()));
                writer.newLine();
                Thread.sleep(milis);
                LOGGER.info(String.format("Thread %s | Iteration %d", this.getName(), i));
            }
        } catch (IOException | InterruptedException ex) {
            Logger.getLogger(ThreadBufferedWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
