package ast; 
import environment.*; 
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
}
