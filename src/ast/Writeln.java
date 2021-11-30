package ast; 
import environment.*; 
import codeGen.*;
/**
 * This class represents a WRITELN statement. 
 */
public class Writeln extends Statement{
    private Expression expr; 

    public Writeln(Expression expr){
        this.expr = expr; 
    }

    /**
     * Prints out the expression stored in 
     * the instance variable "expr"
     * 
     * @param env The environment with the hashmap that 
     *            stores all variables and their values. 
     * @throws Exception
     */
    public void exec(Environment env) throws Exception{
        System.out.println(expr.eval(env)); 
    }

    /**
     * Writes assembly code which represents this class into a 
     * new file. 
     * 
     * @param e The emitter used to output the code to output the
     *          number. 
     */
    public void compile(Emitter e){
        expr.compile(e);
        e.emit("move $a0 $v0"); 
        e.emit("li $v0 1"); 
        e.emit("syscall"); 
        e.emit("li $v0 4"); 
        e.emit("la $a0 newLine"); 
        e.emit("syscall"); 
    }
}
