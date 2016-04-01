import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;


//VS4E -- DO NOT REMOVE THIS LINE!
public class JInternalFrameUtil extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JToolBar jToolBar0;
	private JToolBar jToolBar1;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public JInternalFrameUtil() {
		initComponents();
	}

	private void initComponents() {
		setBackground(new Color(208, 220, 238));
		setBorder(new LineBorder(Color.lightGray, 1, false));
		setVisible(true);
		setFrameIcon(new ImageIcon(getClass().getResource("")));
		setAlignmentX(0.0f);
		setAlignmentY(0.0f);
		add(getJToolBar1(), BorderLayout.NORTH);
		setSize(320, 240);
	}

	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
		}
		return jToolBar1;
	}

	private JToolBar getJToolBar0() {
		if (jToolBar0 == null) {
			jToolBar0 = new JToolBar();
			jToolBar0.setBorderPainted(false);
			jToolBar0.setEnabled(false);
			jToolBar0.setFocusable(false);
		}
		return jToolBar0;
	}

}
