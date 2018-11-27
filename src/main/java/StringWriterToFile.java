import java.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringWriterToFile {

    private static final Logger log = LogManager.getRootLogger();

    public void writeString(String stringWrite) {

        try(FileWriter writer = new FileWriter("src/main/resources/result.txt", false))
        {
            writer.write(stringWrite);
            writer.flush();

            log.info("Result is write in file");
        }
        catch(IOException ex){

            log.error(ex.getMessage());
        }
    }
}