import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.LineBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;


public class TaskSplitPane {
	
	private JPanel rightSidePanel;
	private JSplitPane jSplitPane0;
	private JPanel jPanel0;
	
	public JSplitPane getJSplitPane0() {
		if (jSplitPane0 == null) {
			jSplitPane0 = new JSplitPane();
			jSplitPane0.setBorder(new LineBorder(Color.black, 1, false));
			jSplitPane0.setDividerLocation(134);
			jSplitPane0.setLeftComponent(getJPanel0());
			jSplitPane0.setRightComponent(getPanelRightSide());
			
		}
		return jSplitPane0;
	}
	
	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(new TaskInternalFrameUtil().getJInternalFrame0(), new Constraints(new Bilateral(7, 0, 39), new  Bilateral(7, 0, 39)));
		}
		return jPanel0;
	}
	
	private JPanel getPanelRightSide() {
		
		if (rightSidePanel == null) {
			rightSidePanel = new JPanel();
			rightSidePanel.setLayout(new GroupLayout());
			//jPanel0.add(getJSplitPane0(), new Constraints(new Bilateral(7, 0, 39), new  Bilateral(7, 0, 39)));
		}
		return rightSidePanel;
	}

}
