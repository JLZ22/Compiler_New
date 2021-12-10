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

    public ProcedureDeclaration(String name, List<String> params, Statement stmt){
        this.name = name; 
        parameters = params; 
        this.stmt = stmt; 
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
        stmt.compile(e);
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
     * Returns the parameters of this procedure declaration. 
     * 
     * @return The value inside parameters
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
