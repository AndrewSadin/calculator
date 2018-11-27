import java.util.regex.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Calculate {

    private static final Logger log = LogManager.getRootLogger();

    public static void main(String[] args){
        String calcString = getStringFromFile();
        if(!calcString.equals("")){
            log.info("Start string for calculate " + calcString);
            calcString.replace( " ", "" );
            if(checkString(calcString)) {
                calculate(calcString);
            }
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
                log.warn("First or last symbol is not number");
                return false;
            }
        } else {
            log.warn("No illegal symbol");
            return false;
        }
    }

    static void calculate(String string){
        String[] numbers = string.split("[/*+-]+");
        String[] symbols = string.split("[0-9.,]+");
        int numbersLength = numbers.length;
        int symbolsLength = symbols.length;
        if(symbols[0].equals("-")){
            numbers[1] = "-"+ numbers[1];
            for (int k = 0; k < numbersLength - 1; k++)
                numbers[k] = numbers[k + 1];
            numbersLength--;
        }
        for (int k = 0; k < symbols.length -1; k++)
            symbols[k] = symbols[k+1];
        symbolsLength--;
        for (int x = 0; x < symbolsLength; x++){
            symbolsLength = priorityCalculations(numbers, symbols, numbersLength, symbolsLength);
        }
        simpleCalculations(numbers, symbols, symbolsLength);
    }

    static int priorityCalculations(String[] numbers, String[] symbols, int numbersLength, int symbolsLength){
        double d;
        for(int x = 0; x < symbolsLength; x++){
            switch(symbols[x]){
                case "/" : d = Double.parseDouble(numbers[x]) / Double.parseDouble(numbers[x+1]);
                    for (int k = x; k < symbolsLength - 1; k++)
                        symbols[k] = symbols[k + 1];
                    numbers[x] = String.valueOf(d);
                    for (int k = x+1; k < numbersLength - 2; k++)
                        numbers[k] = numbers[k + 2];
                    symbolsLength--;
                    x--;
                    break;
                case "*" : d = Double.parseDouble(numbers[x]) * Double.parseDouble(numbers[x+1]);
                    for (int k = x; k < symbolsLength - 1; k++)
                        symbols[k] = symbols[k + 1];
                    numbers[x] = String.valueOf(d);
                    for (int k = x+1; k < numbersLength - 2; k++)
                        numbers[k] = numbers[k + 2];
                    symbolsLength--;
                    x--;
                    break;
                case "/-" : d = Double.parseDouble(numbers[x]) /- Double.parseDouble(numbers[x+1]);
                    for (int k = x; k < symbolsLength - 1; k++)
                        symbols[k] = symbols[k + 1];
                    numbers[x] = String.valueOf(d);
                    for (int k = x+1; k < numbersLength - 2; k++)
                        numbers[k] = numbers[k + 2];
                    symbolsLength--;
                    x--;
                    break;
                case "*-" : d = Double.parseDouble(numbers[x]) *- Double.parseDouble(numbers[x+1]);
                    for (int k = x; k < symbolsLength - 1; k++)
                        symbols[k] = symbols[k + 1];
                    numbers[x] = String.valueOf(d);
                    for (int k = x+1; k < numbersLength - 2; k++)
                        numbers[k] = numbers[k + 2];
                    symbolsLength--;
                    x--;
                    break;
            }
        }
        return symbolsLength;
    }

    static void simpleCalculations(String[] numbers, String[] symbols, int symbolsLength){
        try {
            for (int x = 0; x < symbolsLength; x++) {
                switch (symbols[x]) {
                    case "+":
                        numbers[x + 1] = String.valueOf( Double.parseDouble( numbers[x] ) + Double.parseDouble( numbers[x + 1] ) );
                        break;
                    case "-":
                        numbers[x + 1] = String.valueOf( Double.parseDouble( numbers[x] ) - Double.parseDouble( numbers[x + 1] ) );
                        break;
                    case "--":
                        numbers[x + 1] = String.valueOf( Double.parseDouble( numbers[x] ) + Double.parseDouble( numbers[x + 1] ) );
                        break;
                    case "+-":
                        numbers[x + 1] = String.valueOf( Double.parseDouble( numbers[x] ) - Double.parseDouble( numbers[x + 1] ) );
                        break;
                    default: throw new Exception("No illegal symbol");
                }
            }
        StringWriterToFile fileWriter = new StringWriterToFile();
        fileWriter.writeString(numbers[symbolsLength]);
        } catch (Exception  ex){
            log.warn(ex.getMessage());
        }
    }

}
