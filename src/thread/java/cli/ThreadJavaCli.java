package thread.java.cli;

import java.io.File;
import thread.java.cli.io.SuperBufferedWriter;
import thread.java.cli.io.SuperBufferedWriter.SuperBufferedWriterBuilder;

/**
 *
 * @author AlexanDev_CWA
 */
public class ThreadJavaCli {

    public static void main(String[] args) throws InterruptedException {

        SuperBufferedWriterBuilder superWriter = SuperBufferedWriter.builder();

        for (int i = 0; i < 50; i++) {
            File file = new File(System.getProperty("user.dir") + "\\repo\\" + "file_" + i + ".log");
            superWriter.appendFile(file);
        }
        superWriter.build().join();
        superWriter.build().start();
    }

}
