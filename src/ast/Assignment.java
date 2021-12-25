package ast; 
import environment.*; 
import codeGen.*; 
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

    /**
     * Takes in an emitter and uses it to 
     * write MIPS code representation of a 
     * variable assignment into a file. 
     * 
     * @param e The emitter used to write code. 
     */
    public void compile(Emitter e){
        expr.compile(e); 
        if(e.isLocalVariable(var)){
            e.emit("addu $t0 $sp " + e.getOffset(var)); 
            e.emit("sw $v0 ($t0)");
        }
        else{
            e.emit("la $t0 var" + var);
            e.emit("sw $v0 ($t0)"); 
        }
    }
}
