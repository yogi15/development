package dsEventProcessor;

public class EngineEventMonitorProcessor extends EventProcessor {
	
	String engineName;
	
	/**
	 * @return the engineName
	 */
	public String getEngineName() {
		return engineName;
	}
	/**
	 * @param engineName the engineName to set
	 */
	public void setEngineName(String engineName) {
		this.engineName = engineName;
	}
	/**
	 * @return the isAlive
	 */
	public boolean isAlive() {
		return isAlive;
	}
	/**
	 * @param isAlive the isAlive to set
	 */
	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
	/**
	 * @return the isAliveAtdateTime
	 */
	public String getIsAliveAtdateTime() {
		return isAliveAtdateTime;
	}
	/**
	 * @param isAliveAtdateTime the isAliveAtdateTime to set
	 */
	public void setIsAliveAtdateTime(String isAliveAtdateTime) {
		this.isAliveAtdateTime = isAliveAtdateTime;
	}
	boolean isAlive = true;
	String isAliveAtdateTime = "";

	

}
