package ast; 
import environment.*; 
/**
 * This class represents an IF statement. 
 */
public class If extends Statement{
    private Condition condition; 
    private Statement stmt; 

    public If(Condition condition, Statement stmt){
        this.condition = condition; 
        this.stmt = stmt; 
    }

    /**
     * Executes the statement stored in the stmt 
     * instance variable if the condition is 
     * equal to 1. 
     * 
     * @param env The environment with the hashmap that 
     *            stores all variables and their values. 
     * @throws Exception
     */
    public void exec(Environment env) throws Exception{
        if(condition.eval(env) == 1){
            stmt.exec(env);
        }
    }
}
