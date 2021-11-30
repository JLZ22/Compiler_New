package ast; 
import environment.*; 
import codeGen.*;
/**
 * This class represents a statement which can 
 * be executed to produce a certain outcome. 
 */
public abstract class Statement {
    /**
     * Executes the current statement to produce 
     * an outcome. 
     * 
     * @param env The environment with the hashmap that 
     *            stores all variables and their values. 
     * @throws Exception
     */
    public abstract void exec(Environment env) throws Exception; 

    /**
     * Takes simple pascal code and converts it into 
     * assembly. 
     * 
     * @param e The emitter used to write the code into a new file. 
     */
    public void compile(Emitter e){
        throw new RuntimeException("Implement Me!!!!"); 
    }
}
