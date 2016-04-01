package FutureContract;

import com.jidesoft.grid.EditorContext;

public enum  ContractPropertyEditorContext {
	 DateRule("DateRule");
	 
	 private EditorContext _editorContext;
	    
	ContractPropertyEditorContext(String contextName){
	        _editorContext = new EditorContext(contextName);
	    }
	    
	    public EditorContext getEditorContext() { return _editorContext; }
}
