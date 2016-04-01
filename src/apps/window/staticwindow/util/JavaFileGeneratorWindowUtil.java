package apps.window.staticwindow.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JScrollPane;

import util.commonUTIL;
import util.cacheUtil.ReferenceDataCache;
import apps.window.staticwindow.BasePanel;
import apps.window.staticwindow.JavaFileGeneratorWindow;
import beans.JavaFileGenerator;
import beans.WindowSheet;
import beans.WindowTableModelMapping;
import constants.CommonConstants;
import constants.JavaFileGeneratorConstants;

public class JavaFileGeneratorWindowUtil extends BaseWindowUtil {
	JavaFileGeneratorWindow JavaFileGeneratorWindow= null;
	   JavaFileGenerator javaFileGenerator = null;
    String windowName = JavaFileGeneratorConstants.WINDOW_NAME;
	/**
	 * @return the windowName
	 */
	public String getWindowName() {
		return JavaFileGeneratorConstants.WINDOW_NAME;
	}

	/**
	 * @param windowName the windowName to set
	 */
	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}

	/**
	 * @return the JavaFileGenerator
	 */
	public JavaFileGenerator getJavaFileGenerator() {
		return javaFileGenerator;
	}

	/**
	 * @param JavaFileGenerator the JavaFileGenerator to set
	 */
	public void setJavaFileGenerator(JavaFileGenerator windowS) {
		this.javaFileGenerator = windowS;
	}

	@Override
	public boolean validate( ) {
		// TODO Auto-generated method stub
		boolean flag = false;
		 
		 return validate(javaFileGenerator, windowName);
		 
		     
		
		 
	}

	@Override
	public Vector<String> fillData(String action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionMapper(String action) {
		// TODO Auto-generated method stub
		 	if(action.equalsIgnoreCase(CommonConstants.SAVEASNEWBUTTON)) {
			saveAsNewButtonAction();
		}
		if(action.equalsIgnoreCase(CommonConstants.NEWBUTTON)) {
			newButtonAction();
		}
		if(action.equalsIgnoreCase(CommonConstants.LOADBUTTON)) {
			 loadButtonAction();
		 
		}
		if(action.equalsIgnoreCase(JavaFileGeneratorConstants.SEARCHTEXTBOX)) {
			searchTextAction();
		}
		
		if(action.equalsIgnoreCase(CommonConstants.RIGHTSIDECENTERTABLE)) {
			rightSideCenterTableAction();
		}
		if(action.equalsIgnoreCase(CommonConstants.DELETEBUTTON)) {
			deleteButtonAction();
		}
		if(action.equalsIgnoreCase(JavaFileGeneratorConstants.LOADSCRIPT)) {
			loadScript();
		}
	}

	private void loadScript() {
		// TODO Auto-generated method stub
		
		Vector<JavaFileGenerator> data = JavaFileGeneratorWindow.model.getData();
		String script = "";
		JavaFileGeneratorWindow.textArea.setText(  script);
		  script = createJavaScriptOnGetMethod(data);
		JavaFileGeneratorWindow.textAreaScrollPane.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		JavaFileGeneratorWindow.textArea.append(script);
	}

	@Override
	public void setWindow(BasePanel windowName) {
		// TODO Auto-generated method stub
		JavaFileGeneratorWindow = (JavaFileGeneratorWindow)windowName;
		setJavaFileGenerator(JavaFileGeneratorWindow.getJavaFileGenerator()); 
		
		
	}
	
	
	private void saveAsNewButtonAction() { 
		JavaFileGeneratorWindow.propertyTable.setfillValues();
		 setJavaFileGenerator(JavaFileGeneratorWindow.propertyTable.getJavaFileGenerator());
		 JavaFileGeneratorWindow.model.addRow(JavaFileGeneratorWindow.propertyTable.getJavaFileGenerator());
		//if(validate( )) {
			
	//	}
		   
	}
	private void newButtonAction() {
	 JavaFileGeneratorWindow.propertyTable.clearPropertyValues();
	  JavaFileGeneratorWindow.model.clear();
	  setJavaFileGenerator(null);
	}
	
	
	private void loadButtonAction() {
		newButtonAction();
		String searchText =   JavaFileGeneratorWindow.JavaFileGeneratorSearchTextField.getText();
		 if(!commonUTIL.isEmpty(searchText)) {
			/*Vector<WindowSheet> data = ReferenceDataCache.selectWindowSheets(searchText);
			JavaFileGeneratorWindow.model.clear();
			if(!commonUTIL.isEmpty(data)) {
				WindowSheet firstRecord = data.get(0);
				for(int i=0;i<data.size();i++) {
					JavaFileGeneratorWindow.model.addRow((WindowSheet)data.get(i));
				}
				JavaFileGeneratorWindow.propertyTable.setPropertiesValues(firstRecord);
				// setJavaFileGenerator(firstRecord);*/
			//}
			
		 }
	}
	
	
	private void rightSideCenterTableAction() {
		if(JavaFileGeneratorWindow.rightSideCenterTable.getSelectedRow() != -1) 
			 JavaFileGeneratorWindow.propertyTable.setPropertiesValues( JavaFileGeneratorWindow.model.getRow(JavaFileGeneratorWindow.rightSideCenterTable.getSelectedRow()));
		JavaFileGeneratorWindow.setJavaFileGenerator( JavaFileGeneratorWindow.propertyTable.getJavaFileGenerator());
		setJavaFileGenerator(JavaFileGeneratorWindow.propertyTable.getJavaFileGenerator());
	}
	
	private void searchTextAction() {
		loadButtonAction();
	}
	
	private void deleteButtonAction() {
		/*if(ReferenceDataCache.deleteJavaFileGenerator(JavaFileGenerator)) {
			if( JavaFileGeneratorWindow.rightSideCenterTable.getSelectedRow() != -1) {
				JavaFileGeneratorWindow.model.delRow(JavaFileGeneratorWindow.rightSideCenterTable.getSelectedRow()); 
			}
			setJavaFileGenerator(null);
			 JavaFileGeneratorWindow.propertyTable.clearPropertyValues();
			}*/
		 

	}
	@Override
	public void windowstartUpData() {
		// TODO Auto-generated method stub
		 
	}
	
	// Getting data on Starup of this window.
	public String [] getWindowNameOnStartUP() {
		String curr [] = {""};
	  Vector<String> windowS=	ReferenceDataCache.getStarupData(JavaFileGeneratorConstants.WINDOWNAME);
	  curr = commonUTIL.convertStartupVectortoStringArray(windowS);
	  return curr;
		
	}
	// custom code 
	public String createJavaScriptOnGetMethod(Vector<JavaFileGenerator> dataForScript) {
		String script = "public Object getPropertyValue(String propertyPaneColumnName) {\n";
		script = script + "Object obj =null;\n";
		for(int i=0;i<dataForScript.size();i++) {
			JavaFileGenerator beanData = dataForScript.get(i);
		script = script + " if(propertyPaneColumnName.equalsIgnoreCase("+beanData.getWindowName()+"Constants."+beanData.getMethodName().toUpperCase()+")) { \n";
		script = script + " obj = get"+ beanData.getMethodName() + "() ; }  ";
		}
		script = script + "  \n return obj;} \n\n\n public void setPropertyValue(String propertyPaneColumnName, Object object) { \n" ;
		for(int i=0;i<dataForScript.size();i++) {
			JavaFileGenerator beanData = dataForScript.get(i);
		script = script + " if(propertyPaneColumnName.equalsIgnoreCase("+beanData.getWindowName()+"Constants."+beanData.getMethodName().toUpperCase()+")) { \n";
		script = script + "   set"+ beanData.getMethodName() + "((String)object) ; }  \n";
		}
		script = script + " }";
		return script;
	 }
	
	 
	

	


	@Override
	public void clearALL() {
		// TODO Auto-generated method stub
		JavaFileGeneratorWindow.propertyTable = null;
		
	}
	
	
	
}
