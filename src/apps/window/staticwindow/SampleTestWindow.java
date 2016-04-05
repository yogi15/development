package src.apps.window.staticwindow;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.ActionMap;
import javax.swing.JPanel;
import javax.swing.JSplitPane;

public class SampleTestWindow  extends BasePanel {

	@Override
	public ActionMap getHotKeysActionMapper() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel getHotKeysPanel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Component> getFocusOrderList() {
		// TODO Auto-generated method stub
		return null;
	}
	private JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitLeftPanel, splitRightPanel);
	@Override
	public void setWindowValidationUtil() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTopLeftSidePanelComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTopRigthSidePanelComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPropertyPaneTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCenterRightSidePanelComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel createChildPanel(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel createChildPanel(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWindowActionListener() {
		// TODO Auto-generated method stub
		
	}
}
