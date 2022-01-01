package ast; 
import environment.*; 
import java.util.List;
import codeGen.*; 
/**
 * This class represents a procedure declaration. 
 */
public class ProcedureDeclaration {
    private String name; 
    private List<String> parameters; 
    private Statement stmt; 
    private List<String> localVars; 

    public ProcedureDeclaration(List<String> localVars, String name, List<String> params, Statement stmt){
        this.name = name; 
        parameters = params; 
        this.stmt = stmt; 
        this.localVars = localVars; 
    }

    /**
     * Adds a key, value pair of name and stmt to the given environment. 
     * 
     * @param env The environment which stores the procedure name and 
     *            the statement associated with it. 
     */
    public void exec(Environment env){
        env.setProcedure(name, this);
    }

    /**
     * Takes in an emitter and uses it to 
     * write MIPS code representation of a 
     * procedure declaration. 
     * 
     * @param e The emitter used to write code. 
     */
    public void compile(Emitter e){
        e.emit("PROC" + name + ":"); 
        e.emit("li $t0 0"); 
        e.emitPush("$t0");
        if(localVars != null){
            for(int i = 0 ; i < localVars.size() ; i++){
                e.emit("li $v0 0"); 
                e.emitPush("$v0"); 
            }
        }
        e.setProcedureContext(this); 
        stmt.compile(e);
        if(localVars != null){
            for(int i = 0 ; i < localVars.size() ; i++){
                    e.emitPop("$t0"); 
            }
        }
        e.emitPop("$v0"); 
        e.clearProcedureContext();
        e.emit("jr $ra");
    }

    /**
     * Return the name of the procedure. 
     * 
     * @return The value stored in the name instance variable. 
     */
    public String getName(){
        return name; 
    }

     /**
     * Returns the local variables of this procedure declaration. 
     * 
     * @return The names of the local variables
     */                                 
    public List<String> getLocalVariables(){
        return localVars; 
    }

    /**
     * Returns the parameters of this procedure declaration. 
     * 
     * @return The name of the parameters
     */                                 
    public List<String> getParams(){
        return parameters; 
    }

    /**
     * Returns the statement associated with this 
     * procedure declaration. 
     * 
     * @return Returns the value inside stmt. 
     */
    public Statement getStatement(){
        return stmt; 
    }
}
