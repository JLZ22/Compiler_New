package codeGen; 
import java.io.*;
import java.util.List;

import ast.*;

public class Emitter
{
	private PrintWriter out;
	private int labelId; 
	private List<String> procContext; 

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
		emit("sw " + reg + " ($sp)	# push val of stack into " + reg);
	}

	/**
	 * Pops the value of the first item on the stack 
	 * and stores it in the given register. 
	 * 
	 * @param reg The name of the register. 
	 */
	public void emitPop(String reg){
		emit("lw " + reg + " ($sp)	# pop " + reg);
		emit("addu $sp $sp 4"); 
	}

	/**
	 * Remember proc as current procedure context. 
	 * 
	 * @param proc The procedure for which we are setting the context. 
	 */
	public void setProcedureContext(ProcedureDeclaration proc){
		procContext = proc.getParams(); 
	}

	/**
	 * Clears the cucrrent procedure context by remembering null. 
	 */
	public void clearProcedureContext(){
		procContext = null; 
	}
}