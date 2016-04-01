import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class testingImages extends JFrame {

	private static final long serialVersionUID = 1L;
	private JButton jLabel0;
	private JToolBar jToolBar0;
	private JButton jButton0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public testingImages() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJToolBar0(), new Constraints(new Leading(12, 302, 12, 12), new Leading(3, 24, 10, 10)));
		setSize(320, 240);
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setIcon(new ImageIcon(getClass().getResource("/resources/icon/save.png")));
		}
		return jButton0;
	}

	private JToolBar getJToolBar0() {
		if (jToolBar0 == null) {
			jToolBar0 = new JToolBar();
			jToolBar0.add(getJLabel0());
			jToolBar0.add(getJButton0());
		}
		return jToolBar0;
	}

	private JButton getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JButton();
			jLabel0.setIcon(new ImageIcon(getClass().getResource("/resources/icon/sql.jpg")));
			jLabel0.setSize(4, 4);
		//	jLabel0.setText("Tesing");
		}
		return jLabel0;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				testingImages frame = new testingImages();
				frame.setDefaultCloseOperation(testingImages.EXIT_ON_CLOSE);
				frame.setTitle("testingImages");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
