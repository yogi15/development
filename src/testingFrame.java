import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class testingFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JToolBar jToolBar0;
	private JButton jButton0;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	public testingFrame() {
		initComponents();
	}

	private void initComponents() {
		setVisible(true);
		setLayout(new GroupLayout());
		add(getJToolBar0(), new Constraints(new Bilateral(8, 12, 18), new Leading(8, 44, 10, 10)));
		setSize(320, 240);
	}

	private JToolBar getJToolBar0() {
		if (jToolBar0 == null) {
			jToolBar0 = new JToolBar();
			jToolBar0.setFloatable(false);
			jToolBar0.setBorderPainted(false);
			jToolBar0.add(getJButton0());
		}
		return jToolBar0;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("jButton0");
			jButton0.setRolloverEnabled(false);
		}
		return jButton0;
	}

}
