package ast; 
import environment.*; 
import java.util.List;
import codeGen.*; 
/**
 * This class represents a statement beginning with 
 * "BEGIN" and ending with "END" .
 */
public class Block extends Statement{
    private List<Statement> stmts; 

    public Block(List<Statement> stmts){
        this.stmts = stmts; 
    }

    /**
     * Executes all the statements in the list of 
     * statements this class contains. 
     * 
     * @param env The environment with the hashmap that 
     *            stores all variables and their values. 
     * @throws Exception 
     */
    public void exec(Environment env) throws Exception{
        for(int i = 0 ; i < stmts.size() ; i++){
            stmts.get(i).exec(env); 
        }
    }

    /**
     * Writes assembly code which represents this class into a 
     * new file. 
     * 
     * @param e The emitter used to output the code to output the
     *          number. 
     */
    public void compile(Emitter e){
        for(int i = 0 ; i < stmts.size() ; i++){
            stmts.get(i).compile(e);
        }
    }
}