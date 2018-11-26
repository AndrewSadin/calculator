import java.util.regex.*;
import javax.script.*;

public class Calculate {
    public static void main(String[] args){
        String calcString = getStringFromFile();
        System.out.println("Start string for calculate " + calcString);
        calcString.replace( " ", "" );
        if(checkString(calcString)) {
            System.out.println(1);
            calculate(calcString);
        }
    }

    static String getStringFromFile(){
        StringReaderFromFile fileReader = new StringReaderFromFile();
        return fileReader.getString();
    }

    static Boolean checkString(String string) {
        Pattern p = Pattern.compile("^[0-9*/.,+-]+");
        Matcher m = p.matcher(string);
        if (m.matches()) {
            p = Pattern.compile("^[*/.,]|[+.*/,-]$");
            if (m.matches()) {
                return true;
            } else {
                System.out.println("First or last symbol is not number");
                return false;
            }
        } else {
            System.out.println("No illegal symbol");
            return false;
        }
    }

    static void calculate(String string){
//        String[] numbers = string.split("[/*+.,-]");
////        for(String number: numbers){
////            System.out.println(number);
////        }
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        Object result = engine.eval("5*4/2");
        System.out.println(result);
    }

}
