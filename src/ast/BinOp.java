package ast; 
import environment.*; 
import codeGen.*;
/**
 * This class represents a binary operation between
 * two expressions.
 */
public class BinOp extends Expression{
    private String op; 
    private Expression exp1; 
    private Expression exp2; 

    public BinOp(String op, Expression exp1, Expression exp2){
        this.op = op; 
        this.exp1 = exp1; 
        this.exp2 = exp2; 
    }

    /**
     * Uses binary operators to evaluate expr1 and expr2 
     * based on what op is. 
     * 
     * @param env The environment with a hashmap that stores 
     *            all variables and their assignments. 
     * @throws Exception 
     */
    public int eval(Environment env) throws Exception{
        if(op.equals("*")){
            return exp1.eval(env) * exp2.eval(env); 
        }
        if(op.equals("/")){
            return exp1.eval(env) / exp2.eval(env); 
        }
        if(op.equals("+")){
            return exp1.eval(env) + exp2.eval(env); 
        }
        if(op.equals("-")){
            return exp1.eval(env) - exp2.eval(env); 
        }
        throw new Exception("Invalid Binary Operator");
    }

    /**
     * Writes assembly code which represents this class into a 
     * new file. 
     * 
     * @param e The emitter used to output the code to output the
     *          number. 
     */
    public void compile(Emitter e){
        exp1.compile(e); 
        e.emitPush("$v0");
        exp2.compile(e); 
        e.emitPop("$t2"); 
        switch(op){
            case "*": 
                e.emit("mult $t2, $v0"); 
                e.emit("mflo $v0");
                break; 
            case "/":
                e.emit("div $t2, $v0"); 
                e.emit("mflo $v0"); 
                break; 
            case "+": 
                e.emit("add $v0, $t2, $v0"); 
                break; 
            case "-": 
                e.emit("sub $v0, $t2, $v0"); 
                break; 
            default: 
                System.out.println("Invalid binary operator"); 
        }
    }
}
