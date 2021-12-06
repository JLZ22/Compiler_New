package ast; 
import environment.*; 
import codeGen.*;
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

    /**
     * Takes in an emitter and uses it to 
     * write MIPS code representation of a 
     * variable into a file. 
     * 
     * @param e The emitter used to write code. 
     */
    public void compile(Emitter e){
        e.emit("la $t0 var" + name); 
        e.emit("lw $v0 ($t0)"); 
    }
}
