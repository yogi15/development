import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;


//VS4E -- DO NOT REMOVE THIS LINE!
public class JTaskInternalFrame extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JButton jButton0;
	private JButton jButton1;
	private JToolBar jToolBar0;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public JTaskInternalFrame() {
		initComponents();
	}

	private void initComponents() {
		setBorder(new LineBorder(Color.black, 1, false));
		setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		setMaximizable(false);
		setClosable(false);
	//	setContentPane(this);
		setVisible(true);
		setFrameIcon(new ImageIcon(getClass().getResource("")));
		setLayout(new GroupLayout());
		add(getJToolBar0(), new Constraints(new Trailing(0, 100, 12, 12), new Leading(-7, 24, 10, 10)));
		setSize(287, 336);
	}

	private JToolBar getJToolBar0() {
		if (jToolBar0 == null) {
			jToolBar0 = new JToolBar();
			jToolBar0.setBorder(new LineBorder(Color.black, 1, false));
			jToolBar0.add(getJButton0());
			jToolBar0.add(getJButton1());
		}
		return jToolBar0;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setBackground(new Color(255, 255, 128));
			jButton1.setIcon(new ImageIcon(getClass().getResource("/resources/icon/down.png")));
		}
		return jButton1;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setForeground(new Color(255, 255, 128));
			jButton0.setIcon(new ImageIcon(getClass().getResource("/resources/icon/right.png")));
			jButton0.setToolTipText("Open");
		}
		return jButton0;
	}

}
