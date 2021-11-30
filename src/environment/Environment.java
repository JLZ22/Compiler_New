package environment; 
import java.util.HashMap;
import ast.*; 

/**
 * This class stores all the variables 
 * and their assignments in the form of 
 * a HashMap. 
 */
public class Environment {
    private HashMap<String, Integer> vars; 
    private HashMap<String, ProcedureDeclaration> procedures; 
    private Environment parent; 

    public Environment(Environment parent) {
        this.parent = parent; 
        vars = new HashMap<String, Integer>(); 
        procedures = new HashMap<String, ProcedureDeclaration>(); 
    }
    
    /**
     * Tests whether or not the vars HashMap contains 
     * the given variable 
     * 
     * @param variable The name of the variable
     * @return true if the variable is in the HashMap; otherwise, 
     *         false
     */
    public boolean containsVariable(String variable){
        if(vars.containsKey(variable))
            return true; 
        return false; 
    }

    /**
     * Associates the given variable name 
     * with the given variable
     * 
     * @param variable The name of the variable
     * @param value The value of the variable
     */
    public void declareVariable(String variable, int value){
        if(containsVariable(variable))
            vars.replace(variable, value); 
        else 
            vars.put(variable, value); 
    }

    /**
     * If the variable exists in the current environment, 
     * then it sets it to the new value. However, if the 
     * variable exists in the global environment, then it 
     * sets that variable to the new value. 
     * 
     * @param variable The name of the variable
     * @param value The value of the variable
     */
    public void setVariable(String variable, int value){
        if(parent != null && parent.containsVariable(variable))
            parent.declareVariable(variable, value); 
        else
            declareVariable(variable, value); 
    }

    /**
     * Returns the value associated with the given variable
     * 
     * @param variable The name of the variable
     * @return The value assigned to the given variable
     * @throws Exception when there is no variable in the the environments
     */
    public int getVariable(String variable) throws Exception{
        Integer var = vars.get(variable); 
        if(var == null){
            try{
                var = parent.getVariable(variable); 
            } catch (Exception e){
                throw new Exception("This variable has not been initialized.");
            }
        }
        return var.intValue(); 
    }

    /**
     * Associates the given procedure name with the 
     * given statement. 
     * 
     * @param proc The name of the procedure
     * @param stmt The procedure declaration assigned to the procedure. 
     */
    public void setProcedure(String proc, ProcedureDeclaration stmt){
        if(procedures.containsKey(proc))
            procedures.replace(proc, stmt);
        else
            procedures.put(proc, stmt); 
    }

    /**
     * Returns the statement associated with the given 
     * procedure name. 
     * 
     * @param proc The name of the procedure
     * @return The procedure declaration assigned to the given variable
     */
    public ProcedureDeclaration getProcedure(String proc){
        return procedures.get(proc); 
    }
}
