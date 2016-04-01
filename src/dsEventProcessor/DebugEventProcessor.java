package dsEventProcessor;

public class DebugEventProcessor extends EventProcessor {
	
	String debugType;
	/**
	 * @return the debugType
	 */
	public String getDebugType() {
		return debugType;
	}
	/**
	 * @param debugType the debugType to set
	 */
	public void setDebugType(String debugType) {
		this.debugType = debugType;
	}
	/**
	 * @return the debugInfo
	 */
	public String getDebugInfo() {
		return debugInfo;
	}
	/**
	 * @param debugInfo the debugInfo to set
	 */
	public void setDebugInfo(String debugInfo) {
		this.debugInfo = debugInfo;
	}
	/**
	 * @return the debugClassName
	 */
	public String getDebugClassName() {
		return debugClassName;
	}
	/**
	 * @param debugClassName the debugClassName to set
	 */
	public void setDebugClassName(String debugClassName) {
		this.debugClassName = debugClassName;
	}
	/**
	 * @return the debugMethod
	 */
	public String getDebugMethod() {
		return debugMethod;
	}
	/**
	 * @param debugMethod the debugMethod to set
	 */
	public void setDebugMethod(String debugMethod) {
		this.debugMethod = debugMethod;
	}
	/**
	 * @return the writeTofile
	 */
	public String getWriteTofile() {
		return writeTofile;
	}
	/**
	 * @param writeTofile the writeTofile to set
	 */
	public void setWriteTofile(String writeTofile) {
		this.writeTofile = writeTofile;
	}
	String debugInfo;
	String debugClassName;
	String debugMethod;
	String writeTofile;
	
	

}
