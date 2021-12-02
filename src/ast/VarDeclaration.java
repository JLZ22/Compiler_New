package ast;
import environment.*; 
import codeGen.*; 
/**
 * This class represents a variable declaration. 
 */
public class VarDeclaration extends Statement{
    private String var; 
    
    public VarDeclaration(String var){
        this.var = var; 
    }

    /**
     * Adds a key, value pair to the hashmap within 
     * the given environment. 
     * 
     * @param env The environment with the hashmap.
     * @throws Exception 
     */
    public void exec(Environment env) throws Exception{
        env.setVariable(var, 0);
    }

    /**
     * Takes in an emitter and uses it to 
     * write MIPS code representation of a 
     * variable declaration into a file. 
     * 
     * @param e The emitter used to write code. 
     */
    public void compile(Emitter e){
        e.emit("var" + var + ": .word 0");
    }
}
