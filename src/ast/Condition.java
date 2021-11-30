package ast; 
import environment.*; 
/**
 * This class represents a conditional expression which 
 * compares two expressions with a relative operator. 
 */
public class Condition extends Expression{
    private Expression expr1; 
    private Expression expr2; 
    private String relop; 

    public Condition(Expression expr1, Expression expr2, String relop){
        this.expr1 = expr1;
        this.expr2 = expr2; 
        this.relop = relop; 
    }

    /**
     * Evaluates the expressions relative to eachother 
     * using the relative operator stored in the relop 
     * instance variable. 
     * 
     * @param env The environment with the hashmap that 
     *            stores all variables and their values. 
     * @return 1 if the conditional is true; otherwise, 
     *         0
     * @throws Exception 
     */
    public int eval(Environment env) throws Exception{
        int bool = -1; 
        if(relop.equals("=")){
            if(expr1.eval(env) == expr2.eval(env))
                bool = 1; 
            else bool = 0;  
        }
        if(relop.equals("<>")){
            if(expr1.eval(env) != expr2.eval(env))
                bool = 1; 
            else bool = 0;  
        }
        if(relop.equals("<")){
            if(expr1.eval(env) < expr2.eval(env))
                bool = 1; 
            else bool = 0;  
        }
        if(relop.equals(">")){
            if(expr1.eval(env) > expr2.eval(env))
                bool = 1; 
            else bool = 0;  
        }
        if(relop.equals("<=")){
            if(expr1.eval(env) <= expr2.eval(env))
                bool = 1; 
            else bool = 0;  
        }
        if(relop.equals(">=")){
            if(expr1.eval(env) >= expr2.eval(env))
                bool = 1; 
            else bool = 0;  
        }
        if(bool == -1)
            throw new Exception("Invalid relative operator");
        return bool; 
    }
}
