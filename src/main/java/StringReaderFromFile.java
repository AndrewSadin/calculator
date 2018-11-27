import java.io.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringReaderFromFile {

    private static final Logger log = LogManager.getRootLogger();

    String getString(){
        String stringFromFile = "";
        try{
            File file = new File("src/main/resources/calculate.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s;
            while((s=br.readLine())!=null){
                stringFromFile += s;
            }
            br.close();
            log.info("File is read");
        }
        catch(IOException ex){

            log.error(ex.getMessage());
        }
        return stringFromFile;
    }
}
