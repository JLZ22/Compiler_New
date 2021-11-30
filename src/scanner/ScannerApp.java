package scanner; 
import java.io.*; 

public class ScannerApp {
    public static void main(String[] args) throws Exception{
        Scanner scanner = new Scanner(new FileInputStream(
            "Compiler/testOrInstructionFiles/scannerFiles/scannerTestRandom.txt")); 
        while(scanner.hasNext()){
            String token = scanner.nextToken(); 
            if(!token.equals(""))
                System.out.println(token); 
        }
    }
}