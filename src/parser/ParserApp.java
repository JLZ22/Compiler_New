package parser; 
import java.io.*;
import scanner.*; 
import environment.*;  

public class ParserApp {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(new FileInputStream(
            "C:/Users/johnz/OneDrive/Documents/.HighSchool/Senior/Honors ATCS Compilers/Compiler/Compiler/testOrInstructionFiles/parserFiles/parserTesterRandom.txt")); 
        Parser p = new Parser(scanner); 
        Environment env = new Environment(null); 
        p.parseProgram().exec(env);
    }
}