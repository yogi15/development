package enginePS;

import java.io.Serializable;

import eventPS.EventPS;

public interface EnginePS extends Serializable {
	
	 public boolean process(EventPS event); 
	 public String engineName(String engineName); 
	 
 
}
