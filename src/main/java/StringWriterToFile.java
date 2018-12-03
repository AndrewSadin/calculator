import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringWriterToFile {

    private static Logger log = LoggerFactory.getLogger(StringWriterToFile.class);

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