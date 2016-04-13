package apps.window.staticwindow.util;

import java.util.Vector;
import util.commonUTIL;
import util.cacheUtil.ReferenceDataCache;
import apps.window.staticwindow.BasePanel;
import apps.window.staticwindow.TemplateWindow;
import apps.window.util.tableModelUtil.TableUtils;
import beans.Template;
import beans.WindowSheet;
import beans.WindowTableModelMapping;

import com.jidesoft.grid.Property;
import constants.CommonConstants;
import constants.TemplateConstants;

public class TemplateWindowUtil extends BaseWindowUtil {
	TemplateWindow templateWindow = null;
	Template template = null;
	String templateName;

	/**
	 * @return the windowName
	 */
	public String getWindowName() {
		return TemplateConstants.WINDOW_NAME;
	}

	/**
	 * @param windowName
	 *            the windowName to set
	 */
	public void setWindowName(String templateName) {
		this.templateName = templateName;
	}

	/**
	 * @return the template
	 */
	public Template getTemplate() {
		return template;
	}

	/**
	 * @param template
	 *            the template to set
	 */
	public void setTemplate(Template template) {
		template = template;
	}

	@Override
	public boolean validate() {
		// TODO Auto-generated method stub
		boolean flag = false;
		return validate(getTemplate(), TemplateConstants.WINDOW_NAME);
	}

	@Override
	public Vector<String> fillData(String action) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actionMapper(String action) {
		// TODO Auto-generated method stub
		Property prop = templateWindow.propertyTable.getPropertyTable()
				.getSelectedProperty();
		if (action.equalsIgnoreCase(CommonConstants.SAVEASNEWBUTTON)) {
			saveAsNewButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.NEWBUTTON)) {
			newButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.LOADBUTTON)) {
			loadButtonAction();
		}
		if (action.equalsIgnoreCase(TemplateConstants.SEARCHTEXTBOX)) {
			searchTextAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.RIGHTSIDECENTERTABLE)) {
			rightSideCenterTableAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.DELETEBUTTON)) {
			deleteButtonAction();
		}
		if (action.equalsIgnoreCase(CommonConstants.SAVEBUTTON)) {
			saveButtonAction();
		}
	}

	
	private void saveButtonAction() {
		// TODO Auto-generated method stub
		templateWindow.propertyTable
		.setfillValues(template);
		setTemplate((Template) templateWindow.propertyTable.getBean());
		//if(validate( )) 
			 
		  /*if(ReferenceDataCache.updateTemplate(getTemplate())) {
			  if(templateWindow.rightSideCenterTable.getSelectedRow() != -1) {
				  int i=  TableUtils.getSelectedRowIndex( templateWindow.rightSideCenterTable);
				  templateWindow.model.udpateValueAt(getTemplate(), i, 0);
			  }
			
			 
		  }*/
		
	}
	@Override
	public void setWindow(BasePanel windowName) {
		// TODO Auto-generated method stub
		templateWindow = (TemplateWindow) windowName;
		setTemplate(templateWindow.getTemplate());
	}

	private void saveAsNewButtonAction() {
		Template templeate = new Template();
		templateWindow.propertyTable.setfillValues(templateWindow.propertyTable
				.getTemplate());
		setTemplate(templateWindow.propertyTable.getTemplate());
		template = templateWindow.propertyTable.getTemplate();
		if (validate())
			template = ReferenceDataCache.saveTemplate(template);
			  
		templateWindow.model.addRow(template);
		  setTemplate(template);
	}

	private void newButtonAction() {
		templateWindow.propertyTable.clearPropertyValues();
		templateWindow.model.clear();
		setTemplate(null);
	}

	private void loadButtonAction() {
		newButtonAction();
		String searchText = templateWindow.TemplateSearchTextField.getText();
		if (!commonUTIL.isEmpty(searchText)) {
			Vector<Template> data = ReferenceDataCache
					.selectTemplates(searchText);
			templateWindow.model.clear();
			if (!commonUTIL.isEmpty(data)) {
				Template firstRecord = data.get(0);
				for (int i = 0; i < data.size(); i++) {
					templateWindow.model.addRow((Template) data.get(i));
				}
				templateWindow.propertyTable.setPropertiesValues(firstRecord);
				setTemplate(firstRecord);
			}
		}
	}

	private void rightSideCenterTableAction() {
		if (templateWindow.rightSideCenterTable.getSelectedRow() != -1)
			 
		templateWindow.propertyTable
		.setPropertiesValues(templateWindow.model
				.getRow(templateWindow.rightSideCenterTable
						.getSelectedRow()));
		templateWindow.setTemplate(templateWindow.model.getRow(templateWindow.rightSideCenterTable .getSelectedRow()));
setTemplate(templateWindow.model .getRow(templateWindow.rightSideCenterTable .getSelectedRow()));
	}

	private void searchTextAction() {
		loadButtonAction();
	}

	// check Null pointerException.
	private void deleteButtonAction() {
		try {
			if (ReferenceDataCache.deleteTemplate(template)) {
				if (templateWindow.rightSideCenterTable.getSelectedRow() != -1) {
					templateWindow.model
							.delRow(templateWindow.rightSideCenterTable
									.getSelectedRow());
				}
				setTemplate(null);
				templateWindow.propertyTable.clearPropertyValues();
			}
		} catch (Exception e) {
			commonUTIL.displayError(TemplateConstants.WINDOW_NAME + "Util",
					"deleteButtonAction", e);
		}
	}

	@Override
	public void windowstartUpData() {
	}

	@Override
	public void clearALL() {
		// TODO Auto-generated method stub

	}
}
