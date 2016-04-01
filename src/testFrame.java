import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class testFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTabbedPane jTabbedPane0;
	private JButton jButton0;
	private JPanel jPanel0;
	private JButton jButton1;
	int i = 0;
	private JLabel jLabel0;
	private JTabbedPane jTabbedPane1;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	public testFrame() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJTabbedPane0(), new Constraints(new Leading(8, 398, 10, 10), new Leading(9, 144, 10, 10)));
		add(getJPanel0(), new Constraints(new Leading(231, 100, 10, 10), new Leading(181, 100, 10, 10)));
		add(getJButton1(), new Constraints(new Leading(53, 10, 10), new Leading(233, 10, 10)));
		setSize(422, 310);
	}

	private JTabbedPane getJTabbedPane1() {
		if (jTabbedPane1 == null) {
			jTabbedPane1 = new JTabbedPane();
			jTabbedPane1.setBackground(new Color(255, 128, 0));
			jTabbedPane1.addTab("jPanel0", getJPanel0());
		}
		return jTabbedPane1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("jLabel0");
		}
		return jLabel0;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("jButton1");
			jButton1.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jButton1MouseMouseClicked(event);
				}
			});
		}
		return jButton1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(new LineBorder(Color.black, 1, false));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJButton0(), new Constraints(new Leading(9, 10, 10), new Leading(36, 10, 10)));
			jPanel0.add(getJLabel0(), new Constraints(new Leading(160, 10, 10), new Leading(25, 10, 10)));
		}
		return jPanel0;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("jButton0");
		}jButton0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	String input =  JOptionPane.showInputDialog(null 
                        ,"Enter in some text:");
                

            }
		});
		return jButton0;
	}

	private JTabbedPane getJTabbedPane0() {
		if (jTabbedPane0 == null) {
			jTabbedPane0 = new JTabbedPane();
			jTabbedPane0.setBackground(Color.magenta);
			jTabbedPane0.setBorder(new LineBorder(Color.black, 1, false));
			jTabbedPane0.setForeground(Color.yellow);
			jTabbedPane0.addTab("jPanel0", getJPanel0());
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
				testFrame frame = new testFrame();
				frame.setDefaultCloseOperation(testFrame.EXIT_ON_CLOSE);
				frame.setTitle("testFrame");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	private void jButton1MouseMouseClicked(MouseEvent event) {
		//jTabbedPane0.add("Test", jPanel0);
		testPanelT it = new testPanelT();
	//	pane1.add(getJPanel0());
		jTabbedPane0.add(it, i);
		i = i+1;
	}

}
