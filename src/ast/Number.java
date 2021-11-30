package ast; 
import environment.*; 
import codeGen.*; 
/**
 * This class represents a number. 
 */
public class Number extends Expression{
    private int num; 

    public Number(int num){
        this.num = num; 
    }

    /**
     * Returns the number stored in this class. 
     * 
     * @param env The environment with the hashmap that 
     *            stores all variables and their values. 
     * @return The nubmer stored in this class. 
     */
    public int eval(Environment env){
        return num; 
    }

    /**
     * Writes code into a file which outputs the number 
     * stored in this class. 
     * 
     * @param e The emitter used to output the code to output the
     *          number. 
     */
    public void compile(Emitter e){
        e.emit("li $v0 " + num);
    }
}
