import java.io.*;

public class StringReaderFromFile {

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
        }
        catch(IOException ex){

            System.out.println(ex);
        }
        return stringFromFile;
    }
}
