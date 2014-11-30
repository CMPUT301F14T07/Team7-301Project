package ca.ualberta.cs.intent_singletons;

import android.content.Context;

/**
 * This class can be used to pass around a Context for the sake of saving and loading data locally. 
 * @author bbruner
 *
 */
public class ContextSingleton
{
	private static ContextSingleton contextSingleton;
	Context ctx;
	
	private ContextSingleton()
	{
		super();
		this.contextSingleton = null;
		this.ctx = null;
	}
	
	/**
	 * Get the instance of the ContextSingleton
	 * @return ContextSingleton
	 */
	public static ContextSingleton getInstance()
	{
		if(contextSingleton == null)
		{
			contextSingleton = new ContextSingleton();
		}
		return contextSingleton;
	}
	
	/**
	 * Set the Context in the singleton. Do not set with a context that is going to be
	 * destroyed soon or that might be picked up by the garbage collector.
	 * @param ctx The context to set.
	 */
	public void setContext(Context ctx)
	{
		this.ctx = ctx;
	}
	
	/**
	 * Get the Context in the singleton
	 * @return The context. Returns null if a call to setContext has not been made yet.
	 */
	public Context getContext()
	{
		return this.ctx;
	}
}
