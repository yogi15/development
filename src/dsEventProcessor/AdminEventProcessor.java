package dsEventProcessor;

public class AdminEventProcessor extends EventProcessor {
	
	boolean isErrorLogPanel = false;
	public String engineStartedSignal = "";
	boolean isSignalByFrontEnd = false;
	/**
	 * @return the engineStartedSignal
	 */
	public String getEngineStartedSignal() {
		return engineStartedSignal;
	}

	public boolean isSignalByFrontEnd() {
		return isSignalByFrontEnd;
	}
	public void setisSignalByFrontEnd(boolean flag) {
		this.isSignalByFrontEnd =flag ;
	}

	/**
	 * @param engineStartedSignal the engineStartedSignal to set
	 */
	public void setEngineStartedSignal(String engineStartedSignal) {
		this.engineStartedSignal = engineStartedSignal;
	}



	/**
	 * @return the errorType
	 */
	public String getErrorType() {
		return errorType;
	}



	/**
	 * @param errorType the errorType to set
	 */
	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}



	/**
	 * @return the errorDesc
	 */
	public String getErrorDesc() {
		return errorDesc;
	}



	/**
	 * @param errorDesc the errorDesc to set
	 */
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}



	/**
	 * @return the errorClassNameMethod
	 */
	public String getErrorClassNameMethod() {
		return errorClassNameMethod;
	}



	/**
	 * @param errorClassNameMethod the errorClassNameMethod to set
	 */
	public void setErrorClassNameMethod(String errorClassNameMethod) {
		this.errorClassNameMethod = errorClassNameMethod;
	}



	public String errorType = "";
	public String errorDesc = "";
	public String errorClassNameMethod ="";
	/**
	 * @return the isErrorLogPanel
	 */
	public boolean isErrorLogPanel() {
		return isErrorLogPanel;
	}



	/**
	 * @param isErrorLogPanel the isErrorLogPanel to set
	 */
	public void setErrorLogPanel(boolean isErrorLogPanel) {
		this.isErrorLogPanel = isErrorLogPanel;
	}



	/**
	 * @return the isEngineViewerPanel
	 */
	public boolean isEngineViewerPanel() {
		return isEngineViewerPanel;
	}



	/**
	 * @param isEngineViewerPanel the isEngineViewerPanel to set
	 */
	public void setEngineViewerPanel(boolean isEngineViewerPanel) {
		this.isEngineViewerPanel = isEngineViewerPanel;
	}



	boolean isEngineViewerPanel = false;
	
	
	
   @Override
public boolean isAdminEvent() {
	// TODO Auto-generated method stub
	return true;
}  
   
   

}
