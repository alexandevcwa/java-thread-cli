package thread.java.cli.io;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author AlexanDev_CWA
 */
public class SuperBufferedWriter extends Thread {

    private final List<File> fileList;

    private SuperBufferedWriter() {
        this.fileList = new ArrayList<>();
    }

    @Override
    public void run() {
        Random random = new Random();
        fileList.forEach((file) -> {
            ThreadBufferedWriter thread = new ThreadBufferedWriter(file,
                    (300 + random.nextInt(700)),
                    100
            );
            try {
                thread.setPriority(MAX_PRIORITY);
                thread.join();
                thread.start();
            } catch (InterruptedException ex) {
                Logger.getLogger(SuperBufferedWriter.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public List<File> getFileList() {
        return fileList;
    }

    public static SuperBufferedWriterBuilder builder() {
        return new SuperBufferedWriterBuilder();
    }

    public static class SuperBufferedWriterBuilder {

        private SuperBufferedWriter superBufferedWriter;

        public SuperBufferedWriterBuilder() {
            this.superBufferedWriter = new SuperBufferedWriter();
        }

        public SuperBufferedWriterBuilder appendFile(File file) {
            this.superBufferedWriter.getFileList().add(file);
            return this;
        }

        public SuperBufferedWriter build() {
            return superBufferedWriter;
        }
    }
}
