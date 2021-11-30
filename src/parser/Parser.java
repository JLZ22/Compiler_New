package parser; 
import java.util.ArrayList;
import java.util.List;
import scanner.*; 
import ast.*; 
import ast.Number;

public class Parser {
    private Scanner scanner; 
    private String currToken; 

    /**
     * Constructor for class Parser.
     * 
     * @param scanner The scanner for this class.
     * @throws ScanErrorException
     */
    public Parser(Scanner scanner) throws ScanErrorException{
        this.scanner = scanner; 
        currToken = this.scanner.nextToken(); 
    }

    /**
     * Tests currToken with an instance variable, and if they are the 
     * same, set currToken to nextToken. 
     * 
     * @param expectedToken The token to be tested against currToken
     * @throws Exception
     */
    private void eat(String expectedToken) throws Exception{
        // System.out.println(expectedToken); 
        if(!expectedToken.equals(currToken))
            throw new IllegalArgumentException("Expected \"" + expectedToken + "\" but found \"" + currToken + "\"");  
        currToken = scanner.nextToken(); 
    }

    /**
     * Parses through the parameters of a procedure declaration
     * and returns a list of the names of the parameters. 
     * 
     * @return A list of Strings that represent the names of the 
     *         parameters
     * @throws Exception
     */
    private List<String> parseParms() throws Exception{
        List<String> parms = new ArrayList<String>(); 
        while(!currToken.equals(")"))
        {
               parms.add(currToken);
               eat(currToken); 
               if(currToken.equals(","))
                eat(currToken); 
        }
        return parms; 
    }

    /**
     * Parses through a pascal program and returns an object
     * representation of all the statements. 
     * 
     * @return A program object that represents the simple 
     *         pascal program
     * @throws Exception
     */
    public Program parseProgram() throws Exception{
        ArrayList<ProcedureDeclaration> procedures = 
            new ArrayList<ProcedureDeclaration>();  
        while(currToken.equals("PROCEDURE")){
            eat(currToken); 
            String name = currToken; 
            eat(currToken); 
            eat("("); 
            List<String> parms = parseParms(); 
            eat(")"); 
            eat(";"); 
            procedures.add(new ProcedureDeclaration(name, parms, parseStatement()));
        }
        ArrayList<Statement> stmts = new ArrayList<Statement>(); 
        while(scanner.hasNext()){
            stmts.add(parseStatement()); 
        }
        return new Program(procedures, stmts); 
    }
    
    /**
     * Begins with "IF" and eats every token in the if statement.
     * It performs action in the if statement only if the 
     * conditional inside the if statement is true.  
     * @throws Exception
     */
    private Statement parseIf() throws Exception{
        eat("IF"); 
        Expression expr1 = parseExpression(); 
        String relop = currToken; 
        eat(currToken); 
        Expression expr2 = parseExpression(); 
        Condition condition = new Condition(expr1, expr2, relop);
        eat("THEN");
        Statement stmt = parseStatement(); 
        return new If(condition, stmt);  
    }

    /**
     * Begins with "WHILE" and eats every token in the while statement.
     * it performs action inside the while loop until the condition is
     * false;  
     * @throws Exception
     */
    private Statement parseWhile() throws Exception{
        eat("WHILE"); 
        Expression expr1 = parseExpression(); 
        String relop = currToken; 
        eat(currToken); 
        Expression expr2 = parseExpression(); 
        Condition condition = new Condition(expr1, expr2, relop);
        eat("DO");
        Statement stmt = parseStatement(); 
        return new While(condition, stmt);  
    }

    /**
     * Throws an error if currToken is not an integer. Otherwise, 
     * it eats the current token and returns the number parsed. 
     * 
     * @return The number stored in currToken. 
     * @throws Exception
     */
    private Number parseNumber() throws Exception{
        Number num = new Number(Integer.parseInt(currToken)); 
        eat(currToken); 
        return num; 
    }

    /**
     * Parses through a variable assignment 
     * and adds a corresponding key value pair
     * to the vars hashmap. 
     * 
     * @throws Exception
     */
    private Statement parseVarAssignment() throws Exception{
        String key = currToken; 
        eat(currToken); 
        eat(":=");
        Expression val = parseExpression(); 
        eat(";"); 
        return new Assignment(key, val); 
    }
    
    /**
     * Parses through a WRITELN statement and outputs the number 
     * within the parenthases of it. 
     * 
     * @throws Exception
     */
    private Expression parseWriteln() throws Exception{
        eat("WRITELN"); 
        eat("("); 
        Expression exp = parseExpression(); 
        eat(")"); 
        eat(";"); 
        return exp; 
    }

    /**
     * Parses through a statement and conducts action
     * based on whether or not it is a WRITELN, BEGIN, 
     * IF, WHILE, or variable assignment. 
     * 
     * @throws Exception
     */
    public Statement parseStatement() throws Exception{
        if(currToken.equals("WRITELN"))
            return new Writeln(parseWriteln()); 
        if(currToken.equals("BEGIN"))
            return parseBlock(); 
        if(currToken.equals("IF"))
            return parseIf(); 
        if(currToken.equals("WHILE"))
            return parseWhile();  
        return parseVarAssignment();
    }

    /**
     * Parses through the arugments of a procedure call and r
     * eturns a list of Expressions which represents those 
     * arguments
     * 
     * @return A list of expressions representing the arguments of 
     *         a procedure call
     * @throws Exception
     */
    public List<Expression> parseArgs() throws Exception {
        List<Expression> args = new ArrayList<Expression>(); 
        while(!currToken.equals(")")){
            args.add(parseExpression()); 
            if(currToken.equals(","))
                eat(currToken); 
        }
        return args; 
    }

    /**
     * Parses through a WRITELN and determines the factor of 
     * the number within the parenthasese. This number can be 
     * encased in parenthasese and follow negative signs. This 
     * method will simplify the parenthasese and negative signs 
     * until it is left with a positive or negative integer.  
     * 
     * @return The integer after conducting all negative sign calculations. 
     * @throws Exception
     */
    public Expression parseFactor() throws Exception{
        if(currToken.equals("(")){
            eat("("); 
            Expression temp = parseExpression(); 
            eat(")"); 
            return temp; 
        }
        if(currToken.equals("-")){
            eat("-"); 
            Expression temp = new BinOp("-", new Number(0), parseFactor());
            return temp; 
        }
        Expression num; 
        try{
            num = parseNumber();
            return num; 
        } catch(Exception e){
            String firstToken = currToken; 
            eat(currToken); 
            if(currToken.equals("(")){ 
                eat("(");
                List<Expression> args = parseArgs(); 
                num = new ProcedureCall(firstToken, args); 
                eat(")"); 
            }
            else
                num = new Variable(firstToken); 
            return num; 
        }
    }

    /**
     * Parses through a statement and conducts all multiplication
     * and division to return an integer that accurately 
     * represents the terms and symbols given. 
     * 
     * @return The term after all division/multiplication 
     *         arithmetic is complete. 
     * @throws Exception
     */
    private Expression parseTerm() throws Exception{
        Expression num = parseFactor(); 
        while(currToken.equals("*") || currToken.equals("/")){
            if(currToken.equals("*")){
                eat(currToken); 
                num = new BinOp("*", num, parseFactor()); 
            }
            else if(currToken.equals("/")){
                eat(currToken); 
                num = new BinOp("/", num, parseFactor());  
            }
        }
        return num; 
    }

    /**
     * Parses through a statement and conducts 
     * all arithmetic to return an integer that 
     * accurately represents the terms and symbols 
     * given. 
     * 
     * @return The term after all arithmetic is complete. 
     * @throws Exception
     */
    private Expression parseExpression() throws Exception{
        Expression num = parseTerm(); 
        while(currToken.equals("+") || currToken.equals("-")){
            if(currToken.equals("+")){
                eat(currToken); 
                num = new BinOp("+", num, parseTerm());
                return num; 
            }
            else if(currToken.equals("-")){
                eat(currToken); 
                num = new BinOp("-", num, parseTerm());
                return num; 
            }
            else 
                break;
        }
        return num;
    }


    /**
     * Parses through a block of code and 
     * outputs the result of the expressions 
     * within each WRITELN statement. 
     * 
     * @throws Exception
     */
    public Statement parseBlock() throws Exception{
        List<Statement> block = new ArrayList<Statement>();  
        eat("BEGIN"); 
        while(!currToken.equals("END")){
            Statement stmt = parseStatement();
            block.add(stmt); 
        }
        eat("END"); 
        eat(";"); 
        return new Block(block); 
    }
}