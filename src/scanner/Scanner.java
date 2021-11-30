package scanner; 
import java.io.*;

/**
 * The scanner scans through an input file and outputs individual tokens.
 *
 * @author John Zeng
 *  
 * Usage:
 * Takes in a string and converts it into tokens based on certain rules
 *
 */
public class Scanner
{
    private BufferedReader in;
    private char currentChar;
    private boolean eof;
    
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(new FileInputStream("scannerTestTwo.txt")); 
        while(scanner.hasNext()){
            String token = scanner.nextToken(); 
            if(!token.equals(""))
                System.out.println(token); 
        }
    }
    /**
     * Scanner constructor for construction of a scanner that 
     * uses an InputStream object for input.  
     * Usage: 
     * FileInputStream inStream = new FileInputStream(new File(<file name>);
     * Scanner lex = new Scanner(inStream);
     * @param inStream the input stream to use
     */
    public Scanner(InputStream inStream)
    {
        in = new BufferedReader(new InputStreamReader(inStream));
        eof = false;
        getNextChar();
    }
    /**
     * Scanner constructor for constructing a scanner that 
     * scans a given input string.  It sets the end-of-file flag an then reads
     * the first character of the input string into the instance field currentChar.
     * Usage: Scanner lex = new Scanner(input_string);
     * @param inString the string to scan
     */
    public Scanner(String inString)
    {
        in = new BufferedReader(new StringReader(inString));
        eof = false;
        getNextChar();
    }
    /**
     * Sets currentChar to the next character in the input string. If the character is -1, 
     * it will set eof (end of file) to true, but if it is an integer from 0 to 65535 (0x00-0xffff), 
     * it will set currentChar to the character type casted version of that integer. In the case that 
     * there is an io error, it will print the exception. 
     */
    private void getNextChar()
    {
        try{
            int tempChar = in.read();
            if(tempChar == -1)
                eof = true; 
            else{
                currentChar = (char)tempChar; 
                if(currentChar == '.')
                    eof = true; 
            }
        }
        catch (IOException io){
            System.out.println(io);
        }        
    }
    /**
     * Compares the expected character to currentChar and throws an error if
     * they are different. If they are the same, it calls getNextChar. 
     * @param expected The character being compared to currentChar
     * @throws ScanErrorException Illegal character - expected <currentChar> but found <expected>
     */
    private void eat(char expected) throws ScanErrorException
    {
        if(expected != currentChar)
            throw new ScanErrorException("Illegal character - expected " + currentChar + " but found " + expected);
        else{
            getNextChar(); 
        }
    }
    /**
     * Return true or false based on whether or not the input string 
     * has another character. 
     * 
     * @return true if there is another character in the input string, otherwise
     *         false
     */
    public boolean hasNext()
    {
        return !eof; 
    }
    /**
     * Analyze a single character to determine if it is a digit or not. 
     * 
     * @param charInQuestion The character to be analyzed to see whether or not 
     * it is a digit
     * @return true if the character is a digit, otherwise 
     *         false
     */
    public static boolean isDigit(char charInQuestion){
        boolean isDig = false; 
        for(int i = 0 ; i < 10 ; i++){
            if(charInQuestion == (char)(48+i))
                isDig = true; 
        }
        return isDig; 
    }
    /**
     * Analyze a single character to determine if it is a letter or not. 
     * 
     * @param charInQuestion The character to be analyzed to see whether or not 
     *                       it is a letter
     * @return true if the character is a letter, otherwise 
     *         false
     */
    public static boolean isLetter(char charInQuestion){
        boolean isALetter = false; 
        for(int i = 0 ; i < 26; i++){
            if(charInQuestion == (char)(97+i))
                isALetter = true; 
        }
        for(int i = 0 ; i < 26; i++){
            if(charInQuestion == (char)(65+i))
                isALetter = true; 
        }
        return isALetter; 
    }
    /**
     * Analyze a single character to determine if it is a whitespace or not. 
     * 
     * @param charInQuestion The character to be analyzed to see whether or not 
     *                       it is a whitespace
     * @return true if the character is a whitespace, otherwise 
     *         false
     */
    public static boolean isWhiteSpace(char charInQuestion){
        boolean isAWhiteSpace = false; 
        char[] whiteSpace = {' ', '\t', '\r', '\n'};
        for(int i = 0 ; i < whiteSpace.length; i++){
            if(charInQuestion == whiteSpace[i])
                isAWhiteSpace = true; 
        }
        return isAWhiteSpace; 
    }
    /**
     * Analyze a single character to determine if it is an operand or not. 
     * 
     * @param charInQuestion The character to be analyzed to see whether or not 
     *                       it is a letter
     * @return true if the character is a letter, otherwise 
     *         false
     */
    public static boolean isOperand(char charInQuestion){
        boolean isAnOperand = false; 
        char[] operand = {'=', '+', '-', '*', '/', '%', '(', ')' , ';', ':', '<', '>', ','};
        for(int i = 0 ; i < operand.length; i++){
            if(charInQuestion == operand[i])
                isAnOperand = true; 
        }
        return isAnOperand; 
    }
    /**
     * Analyze the character to determine what type it is. 
     * 
     * @param charInQuestion the character to be analyzed
     * @return a String indicating the type of token the charInQuestion
     *         is
     */
    public String getTokenType(char charInQuestion){
        if(isDigit(charInQuestion))
            return "digit"; 
        if(isLetter(charInQuestion))
            return "letter"; 
        if(isWhiteSpace(charInQuestion))
            return "whitespace"; 
        if(isOperand(charInQuestion))
            return "operand"; 
        if(!hasNext())
            return "eof"; 
        return "unkown"; 
    }
    /**
     * Scans the input string and outputs a lexeme found in the input stream. 
     * 
     * @return A String representing the lexeme in the input stream 
     * @throws ScanErrorException Expected digit but found <currentChar label>
     */
    private String scanNumber() throws ScanErrorException{
        String lexeme = ""; 
        if(!isDigit(currentChar))
            throw new ScanErrorException("Expected digit but found " + getTokenType(currentChar));
        while(isDigit(currentChar) && !isOperand(currentChar)){
            lexeme += currentChar; 
            eat(currentChar); 
        }
        return lexeme; 
    }
    /**
     * Scans the input string and outputs a lexeme found in the input stream. 
     * 
     * @return A String representing the lexeme in the input stream 
     * @throws ScanErrorException Expected identifier but found <currentChar label>
     */
    private String scanIdentifier() throws ScanErrorException{
        String lexeme = ""; 
        if(!isLetter(currentChar))
            throw new ScanErrorException("Expected digit but found " + getTokenType(currentChar));
        while(!isWhiteSpace(currentChar) && !isOperand(currentChar)){
            lexeme += currentChar; 
            eat(currentChar); 
        }
        return lexeme; 
    }
    /**
     * Scans the input string and outputs a lexeme found in the input stream. 
     * 
     * @return A String representing the operand in the input stream 
     * @throws ScanErrorException Expected operand but found <currentChar label>
     */
    private String scanOperand() throws ScanErrorException{
        String lexeme = ""; 
        if(!isOperand(currentChar))
            throw new ScanErrorException("Expected digit but found " + getTokenType(currentChar));
        char firstChar = currentChar; 
        lexeme += currentChar; 
        eat(currentChar);
        if(firstChar == ':' && currentChar == '='){
            lexeme += currentChar; 
            eat(currentChar); 
        }
        else if(((firstChar == ':' || 
                  firstChar == '>' || 
                  firstChar == '<') && 
                  (currentChar == '=')) || 
                  (firstChar == '<' && currentChar == '>')){
            lexeme += currentChar; 
            eat(currentChar); 
        }
        return lexeme; 
    }
    /**
     * Skip all white spaces in the input string and output the next token. 
     * If it has reached the end of the file, return END. 
     * 
     * @throws ScanErrorException Unkown character currentChar
     * @return A String representing the next token in the input stream
     */
    public String nextToken() throws ScanErrorException
    {
        if(!hasNext())
            return "END"; 
        while(isWhiteSpace(currentChar))
            eat(currentChar); 
        String currCharType = getTokenType(currentChar); 
        String token = ""; 
        switch (currCharType){
            case "digit": 
                token = scanNumber(); 
                break; 
            case "letter": 
                token = scanIdentifier();
                break; 
            case "operand": 
                token = scanOperand();
                break;
            case "eof":
                token = "END"; 
                break; 
            default: 
                throw new ScanErrorException("Unknown character: " + currentChar);  
        }
        return token;
    }    
}
