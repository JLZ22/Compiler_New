package ast; 
import environment.*; 
/**
 * This class represents a variable assignment. 
 */
public class Assignment extends Statement{
    private String var; 
    private Expression expr; 

    public Assignment(String var, Expression expr){
        this.var = var; 
        this.expr = expr; 
    }

    /**
     * Adds a key, value pair to the hashmap within 
     * the given environment. 
     * 
     * @param env The environment with the hashmap.
     * @throws Exception 
     */
    public void exec(Environment env) throws Exception{
        env.setVariable(var, expr.eval(env));
    }
}
