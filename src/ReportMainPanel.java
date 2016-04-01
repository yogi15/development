import java.awt.Color;
import java.awt.Font;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.border.LineBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class ReportMainPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JInternalFrame jInternalFrame0;
	private JTabbedPane jTabbedPane0;
	private JPanel jPanel1;
	private JPanel jPanel0;
	private JSplitPane jSplitPane0;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public ReportMainPanel() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Bilateral(7, 843, 10), new Bilateral(7, 843, 10)));
		setSize(993, 461);
	}

	private JSplitPane getJSplitPane0() {
		if (jSplitPane0 == null) {
			jSplitPane0 = new JSplitPane();
			jSplitPane0.setDividerLocation(28);
			jSplitPane0.setLeftComponent(getJPanel1());
		}
		return jSplitPane0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(new LineBorder(Color.black, 1, false));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJSplitPane0(), new Constraints(new Leading(6, 818, 10, 10), new Leading(4, 322, 10, 10)));
		}
		return jPanel0;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(new LineBorder(Color.black, 1, false));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJTabbedPane0(), new Constraints(new Leading(9, 239, 10, 10), new Leading(5, 287, 10, 10)));
		}
		return jPanel1;
	}

	private JTabbedPane getJTabbedPane0() {
		if (jTabbedPane0 == null) {
			jTabbedPane0 = new JTabbedPane();
			jTabbedPane0.setBorder(new LineBorder(Color.black, 1, false));
			jTabbedPane0.setTabPlacement(JTabbedPane.LEFT);
			jTabbedPane0.setFont(new Font("Arial", Font.PLAIN, 10));
			jTabbedPane0.addTab("TaskReport", getJInternalFrame0());
			jTabbedPane0.setTitleAt(0, "<HTML> T<BR>a<BR>s<BR>k<BR>R<BR>e<BR>p<BR>o<BR>r<BR>r<BR>t<BR>");
		}
		return jTabbedPane0;
	}

	private JInternalFrame getJInternalFrame0() {
		if (jInternalFrame0 == null) {
			jInternalFrame0 = new JInternalFrame();
			jInternalFrame0.setVisible(true);
			jInternalFrame0.setLayout(new GroupLayout());
		}
		return jInternalFrame0;
	}

}
