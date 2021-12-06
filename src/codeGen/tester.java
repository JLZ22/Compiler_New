package codeGen;
import scanner.Scanner; 
import parser.*;
import java.io.FileInputStream;
/**
 * This class is used purely to conduct small scale 
 * tests during the codeGen project. 
 */
public class tester {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(new FileInputStream(
            "src/testOrInstructionFiles/parserFiles/parserTesterRandom.txt")); 
        Parser p = new Parser(scanner); 
        p.parseProgram().compile("src/codeGen/compiledCode.asm");
    }
}
