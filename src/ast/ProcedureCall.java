package ast; 
import environment.*; 
import java.util.List;
import codeGen.*; 
/**
 * This class represents a procedure call.
 */
public class ProcedureCall extends Expression{
    private String name; 
    private List<Expression> arguments; 

    public ProcedureCall(String name, List<Expression> arguments){
        this.name = name; 
        this.arguments = arguments;
    }

    /**
     * Evaluates the statements associated with the 
     * given procedure name. 
     * 
     * @param env The environment which stores the procedure name and 
     *            the statement associated with it. 
     */
    public int eval(Environment env) throws Exception{
        ProcedureDeclaration procedure = env.getProcedure(name);
        List<String> params = procedure.getParams(); 
        Environment subEnv = new Environment(env); 
        String name = procedure.getName(); 
        if(arguments != null)  
            for(int i = 0 ; i < arguments.size() ; i++)
                subEnv.declareVariable(params.get(i), arguments.get(i).eval(env));
        subEnv.declareVariable(name, 0); 
        procedure.getStatement().exec(subEnv); 
        return subEnv.getVariable(name); 
    }

    /**
     * Takes in an emitter and uses it to 
     * write MIPS code representation of a 
     * procedure call. 
     * 
     * @param e The emitter used to write code. 
     */
    public void compile(Emitter e){
        e.emitPush("$ra");
        for(int i = 0 ; i < arguments.size() ; i++){
            arguments.get(i).compile(e); 
            e.emitPush("$v0"); 
        }
        e.emit("jal PROC" + name);
        for(int i = 0 ; i < arguments.size() ; i++){
            arguments.get(i).compile(e); 
            e.emitPop("$t0"); 
        } 
        e.emitPop("$ra"); 
    }

}
