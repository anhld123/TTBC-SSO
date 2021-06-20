package ims.test.all;


import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class SMSProcessService
{
	public SMSProcessService()
	{
		
	}
	
	public void CallMethodsProcess(Object callmyclass,String strNameMethos, int a, int b)
	{		
		//callmyclass = new Object();
        // create a script engine manager
        ScriptEngineManager factory = new ScriptEngineManager();
        // create a JavaScript engine
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        // evaluate JavaScript code from String
        try 
        {
			//engine.eval("println('Welcome to Java world')");
			//goi lop es vao bien es
	        engine.put("callmyclass",callmyclass);
	        ScriptEngineFactory sef = engine.getFactory();
	        String s = sef.getMethodCallSyntax("callmyclass", strNameMethos, new String[] { "\""+a+"\"","\""+b+"\"","\""+b+"\""});
	        engine.eval(s);
                int giatri = (int)engine.eval(s);
                
                System.err.println(giatri);
		} 
        catch (ScriptException e) 
        {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//Global.WriteLogFile(e.getMessage());
		}
        // add the Java object into the engine.
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
	//SMSGWConnection DBConnection= new SMSGWConnection();
	
		ServicesProcessed myclass = new ServicesProcessed();
		SMSProcessService process = new SMSProcessService();
		process.CallMethodsProcess(myclass, "add", 1,2);
		//process.CallMethodsProcess(myclass, "xuprocess", 1, 2);
		//myclass.CallMethodsProcess(myclass, strNameMethos, iAutoId, strServiceCode, strServiceNumber, strContentIn);
	       // System.out.println( getc() );
	       // System.loadLibrary(arg0)
     // ExecuteScript es = new ExecuteScript();

      // create a script engine manager
//      ScriptEngineManager factory = new ScriptEngineManager();
//      // create a JavaScript engine
//      ScriptEngine engine = factory.getEngineByName("JavaScript");
//      // evaluate JavaScript code from String
//      try {
//		engine.eval("println('Welcome to Java world')");
//		//engine.put("LQProcessed myclass","new LQProcessed()");
//		engine.eval("LQProcessed myclass =new LQProcessed();");
//		//engine.
//		System.out.println(engine.eval("a"));
//	} catch (ScriptException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

      // add the Java object into the engine.
      
		// TODO Auto-generated method stub

	}

}
