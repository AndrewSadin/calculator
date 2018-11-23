import java.util.regex.*;

public class Calculate {
    public static void main(String[] args){
        String calcString = getStringFromFile();
        System.out.println("Start string for calculate " + calcString);
        calcString.replace( " ", "" );
        if(checkString(calcString)){

        } else {

        };
    }

    static String getStringFromFile(){
        StringReaderFromFile fileReader = new StringReaderFromFile();
        return fileReader.getString();
    }

    static Boolean checkString(String string){
        Pattern p = Pattern.compile( "^[0-9*/.,+-]+" );
        Matcher m = p.matcher( string );
        if (m.matches()) {
            System.out.println( "No illegal symbol" );
            p = Pattern.compile( "^[*/.,]|[+.*/,-]$" );
            m = p.matcher( string );
            if(m.matches()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    static String calculate(String string){
        return string;
    }

}
