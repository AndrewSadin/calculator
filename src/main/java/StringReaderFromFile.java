import java.io.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringReaderFromFile {

    private static Logger log = LoggerFactory.getLogger(StringReaderFromFile.class);

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
