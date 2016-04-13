package apps.window.util.propertyUtil;
import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import apps.window.staticwindow.SearchPropertyWindow;


//VS4E -- DO NOT REMOVE THIS LINE!
public class SearchPropertyWindowWrapper extends JPanel {

	private static final long serialVersionUID = 1L;
	private JSplitPane jSplitPane0;

	private JPanel jPanel0;
	private JPanel jPanel1;
	private JPanel jPanel2;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public SearchPropertyWindow propWind = null;
	public SearchPropertyWindowWrapper(String searchType) {
		propWind =new SearchPropertyWindow(searchType);
		
		initComponents();
	}

	private void initComponents() {
		setBorder(BorderFactory.createTitledBorder(null, "Border Title", TitledBorder.LEADING, TitledBorder.DEFAULT_POSITION, null, null));
		setLayout(new BorderLayout());
		add(getJSplitPane0(), BorderLayout.CENTER);
		setSize(480, 240);
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(propWind.getCenterFrame(), new Constraints(new Leading(4, 446, 10, 10), new Leading(4, 206, 10, 10)));
		}
		return jPanel1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(propWind.getLeftFrame(), new Constraints(new Bilateral(2, 0, 0), new Leading(3, 206, 10, 10)));
		 
		}
		return jPanel0;
	}

	private JSplitPane getJSplitPane0() {
		if (jSplitPane0 == null) {
			jSplitPane0 = new JSplitPane();
			jSplitPane0.setDividerLocation(206);
			jSplitPane0.setLeftComponent(getJPanel0());
			jSplitPane0.setRightComponent(getJPanel1());
		}
		return jSplitPane0;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel2.setLayout(new GroupLayout());
		}
		return jPanel2;
	}

}