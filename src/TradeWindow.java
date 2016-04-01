import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class TradeWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTabbedPane jTabbedPane0;
	private JPanel jPanel1;
	private JScrollPane jScrollPane0;
	private JPanel jPanel2;
	private JTabbedPane jTabbedPane1;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public TradeWindow() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel2(), new Constraints(new Bilateral(8, 12, 0), new Leading(8, 599, 10, 10)));
		setSize(1097, 617);
	}

	private JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.setBorder(new LineBorder(Color.black, 1, false));
		}
		return jTabbedPane1;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			jPanel2.setForeground(Color.lightGray);
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getJTabbedPane0(), new Constraints(new Leading(8, 1060, 10, 10), new Leading(6, 269, 10, 10)));
			jPanel2.add(getJPanel1(), new Constraints(new Leading(8, 1060, 12, 12), new Leading(539, 55, 10, 10)));
			jPanel2.add(getJTabbedPane1(), new Constraints(new Bilateral(8, 12, 5), new Leading(283, 247, 10, 10)));
		}
		return jPanel2;
	}

	

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(new LineBorder(Color.black, 1, false));
			jPanel1.setLayout(new GroupLayout());
		}
		return jPanel1;
	}

	private JTabbedPane getJTabbedPane0() {
		if (jTabbedPane0 == null) {
			jTabbedPane0 = new JTabbedPane();
			jTabbedPane0.setBorder(new LineBorder(Color.black, 1, false));
		}
		return jTabbedPane0;
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
			@Override
			public void run() {
				TradeWindow frame = new TradeWindow();
				frame.setDefaultCloseOperation(TradeWindow.EXIT_ON_CLOSE);
				frame.setTitle("TradeWindow");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
