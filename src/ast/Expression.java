package ast; 
import environment.*; 
import codeGen.*; 
/**
 * This class is an abstract representation of 
 * an expression which a combination of 
 * expressions, terms, and binary operators. 
 */
public abstract class Expression {
    /**
     * Evaluate whatever expression the class represents. 
     * 
     * @param env The environment with the hashmap that 
     *            stores all variables and their values. 
     * @return Whatever the expression the class represents 
     *         evaluates to. 
     * @throws Exception
     */
    public abstract int eval(Environment env) throws Exception; 

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
