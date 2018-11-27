import java.io.*;

public class StringWriterToFile {

    public void writeString(String stringWrite) {

        try(FileWriter writer = new FileWriter("src/main/resources/result.txt", false))
        {
            writer.write(stringWrite);
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}