package ast; 
import environment.*; 
import codeGen.*; 
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

    /**
     * Takes in an emitter and uses it to 
     * write MIPS code representation of a 
     * conditional into a file. 
     * 
     * @param e The emitter used to write code. 
     * @param endLabel The label that will be jumped to after the 
     *                 conditional. 
     */
    public void compile(Emitter e, String endLabel){
        expr1.compile(e);
        e.emit("move $t1, $v0"); 
        expr2.compile(e); 
        String instruction = ""; 
        switch(relop){
            case "<=": 
                instruction = "bgt";   
                break; 
            case ">=":
                instruction = "blt";  
                break; 
            case "<": 
                instruction = "bge"; 
                break; 
            case ">": 
                instruction = "ble"; 
                break; 
            case "=": 
                instruction = "bne"; 
                break; 
            case "<>": 
                instruction = "beq"; 
                break; 
            default: 
                break; 
        }
        e.emit(instruction + " $t1, $v0, " + endLabel); 
    }
}
