package ast; 
import environment.*; 
import codeGen.*; 
/**
 * This class represents a WHILE statement. 
 */
public class While extends Statement{
    private Condition condition; 
    private Statement stmt; 

    public While(Condition condition, Statement stmt){
        this.condition = condition; 
        this.stmt = stmt; 
    }

    /**
     * While the condition stored in the instance 
     * variable "condition" is equal to 1, execute 
     * the stmt stored in the instance variable "statement"
     * 
     * @param env The environment with the hashmap that 
     *            stores all variables and their values. 
     * @throws Exception
     */
    public void exec(Environment env) throws Exception{
        while(condition.eval(env) == 1){
            stmt.exec(env); 
        }
    }

    /**
     * Takes in an emitter and uses it to 
     * write MIPS code representation of an
     * if statement into a file. 
     * 
     * @param e The emitter used to write code. 
=    */
    public void compile(Emitter e){
        int id = e.nextLabelId(); 
        String endLabel = "endWhile" + id; 
        String loopLabel = "loop" + id; 
        e.emit(loopLabel + ":");
        condition.compile(e, endLabel); 
        stmt.compile(e); 
        e.emit("j " + loopLabel); 
        e.emit(endLabel + ":"); 
    }
}
