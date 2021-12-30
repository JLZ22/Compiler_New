package codeGen; 
import java.io.*;
import java.util.List;

import ast.*;

public class Emitter
{
	private PrintWriter out;
	private int labelId; 
	private List<String> procContext; 
	private int excessStackHeight = 0; 
	private ProcedureDeclaration proc; 

	//creates an emitter for writing to a new file with given name
	public Emitter(String outputFileName)
	{
		labelId = 0; 
		try
		{
			out = new PrintWriter(new FileWriter(outputFileName), true);
		}
		catch(IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	/**
	 * Each method call will increment the instance 
	 * variable "labelId" and return it
	 * 
	 * @return Return a value one greater than labelId
	 */
	public int nextLabelId(){
		labelId += 1; 
		return labelId; 
	}

	//prints one line of code to file (with non-labels indented)
	public void emit(String code)
	{
		if (!code.endsWith(":"))
			code = "\t" + code;
		out.println(code);
	}

	//closes the file.  should be called after all calls to emit.
	public void close()
	{
		out.close();
	}

	/**
	 * Pushes the value of the register onto the stack. 
	 * 
	 * @param reg The name of the register.
	 */
	public void emitPush(String reg){
		emit("subu $sp $sp 4"); 
		emit("sw " + reg + " ($sp)	# push val of " + reg + " into the stack");
		excessStackHeight += 4; 
	}

	/**
	 * Pops the value of the first item on the stack 
	 * and stores it in the given register. 
	 * 
	 * @param reg The name of the register. 
	 */
	public void emitPop(String reg){
		emit("lw " + reg + " ($sp)	# pop value of stack into " + reg);
		emit("addu $sp $sp 4"); 
		excessStackHeight -= 4; 
	}

	/**
	 * Remember proc as current procedure context. 
	 * 
	 * @param proc The procedure for which we are setting the context. 
	 */
	public void setProcedureContext(ProcedureDeclaration proc){
		this.proc = proc; 
		procContext = proc.getParams();
		List<String> localVars = proc.getLocalVariables(); 
		if(localVars != null)
			procContext.addAll(localVars); 
		excessStackHeight = 0; 
	}

	/**
	 * Clears the cucrrent procedure context by remembering null. 
	 */
	public void clearProcedureContext(){
		this.proc = null; 
	}

	/**
	 * If the given variable name is within the 
	 * current procedure context, return true. Otherwise, 
	 * return false. 
	 * 
	 * @param varName The name of the variable to be compared 
	 * 				  to the procedure context. 
	 * @return true If variable name is found within the procedure context; otherwise
	 * 		   false
	 */
	public boolean isLocalVariable(String varName){
		if(procContext==null)
			return false; 
		if(proc.getName().equals(varName))
			return true; 
		for(int i = 0 ; i < procContext.size() ; i++){
				if(procContext.get(i).equals(varName))
					return true; 
		}
		return false; 
	}

	/**
	 * Gets the offset of the varname on the stack. 
	 * 
	 * @precondition: localVarName is the name of a local
	 * 				  variable for the procedure currnetly 
	 * 				  being compiled
	 * 
	 * @param localVarName The name of the variable whose 
	 * 					   offsetis being calculated
	 * @return The offset of the localVarName
	 */
	public int getOffset(String localVarName){
		if(proc.getName().equals(localVarName))
			return 0; 
		return excessStackHeight + (procContext.size() - procContext.indexOf(localVarName))*4; 
	}
}