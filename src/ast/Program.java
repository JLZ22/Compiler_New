package ast; 
import codeGen.*; 
import environment.*; 
import java.util.List;

/**
 * This class represents the root of a pascal 
 * program. 
 */
public class Program {
    private List<ProcedureDeclaration> procedures; 
    private List<Statement> stmts; 

    public Program(List<ProcedureDeclaration> procedures, List<Statement> stmts){
        this.procedures = procedures; 
        this.stmts = stmts; 
    }

    /**
     * Takes in an output file name and uses an Emitter 
     * to write MIPS code into it. 
     * 
     * @param fileName The name of the output file. 
     */
    public void compile(String fileName){
        Emitter e = new Emitter(fileName); 
        e.emit(".data"); 
        e.emit("newLine: .asciiz \"\\n\""); 
        e.emit(".text"); 
        e.emit(".globl main"); 
        e.emit("main:"); 
        for(int i = 0 ; i < stmts.size() ; i++){
            stmts.get(i).compile(e);
        }
        e.emit("li $v0, 10"); 
        e.emit("syscall"); 
        e.close(); 

    }

    /**
     * Adds all procedure declarations to the environment and 
     * executes all statements follwoing. 
     * 
     * @param env The environment which contains all variable and procedure 
     *            names and their respective values/statements. 
     * @throws Exception
     */
    public void exec(Environment env) throws Exception{
        for(int i = 0 ; i < procedures.size() ; i++){
            procedures.get(i).exec(env);
        }
        for(int i = 0 ; i < stmts.size() ; i++)
            stmts.get(i).exec(env); 
    }
}