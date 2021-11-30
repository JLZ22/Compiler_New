package ast; 
import environment.*; 
/**
 * This class represents a variable. 
 */
public class Variable extends Expression{
    private String name; 

    public Variable(String name){
        this.name = name; 
    }

    /**
     * Returns the value of the variable with the
     * name stored in the instance variable, name. 
     * 
     * @param env The environment with the hashmap that 
     *            stores all variables and their values. 
     * @return The value of the variable with the name stored 
     *         in the instance variable. 
     * @throws Exception 
     */
    public int eval(Environment env) throws Exception{
        return env.getVariable(name); 
    }
}
