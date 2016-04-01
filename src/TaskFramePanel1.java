import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class TaskFramePanel1 extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JPanel jPanel1;
	private JSplitPane jSplitPane0;
	private JTabbedPane jTabbedPane0;
	private JPanel jPanel0;
	
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public TaskFramePanel1() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(),  new Constraints(new Bilateral(7, 0, 39), new  Bilateral(7, 0, 39)));
		setSize(570, 242);
	}

	

	

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJTabbedPane0(), new Constraints(new Bilateral(7, 0, 39), new  Bilateral(7, 0, 39)));
		}
		return jPanel0;
	}

	private JTabbedPane getJTabbedPane0() {
		if (jTabbedPane0 == null) {
			jTabbedPane0 = new JTabbedPane();
			jTabbedPane0.add(getJSplitPane0(),new Constraints(new Bilateral(7, 0, 39), new  Bilateral(7, 0, 39)));
			jTabbedPane0.setTabPlacement(JTabbedPane.LEFT);
			jTabbedPane0.setFont(new Font("Arial", Font.PLAIN, 10));
			
			jTabbedPane0.setTitleAt(0, "<HTML> T<BR>a<BR>s<BR>k<BR>R<BR>e<BR>p<BR>o<BR>r<BR>r<BR>t<BR>");
			jTabbedPane0.addMouseListener(new MouseAdapter() {
	
				public void mouseEntered(MouseEvent event) {
					jTabbedPane0MouseMouseEntered(event);
				}
	
				public void mouseClicked(MouseEvent event) {
					jTabbedPane0MouseMouseClicked(event);
				}
			});
			jTabbedPane0.addMouseWheelListener(new MouseWheelListener() {
	
				public void mouseWheelMoved(MouseWheelEvent event) {
					jTabbedPane0MouseWheelMouseWheelMoved(event);
				}
			});
		}
		return jTabbedPane0;
	}

	private JSplitPane getJSplitPane0() {
		
		if (jSplitPane0 == null) {
			TaskSplitPane tsp = new TaskSplitPane();
			jSplitPane0 = tsp.getJSplitPane0();
		}
		return jSplitPane0;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GroupLayout());
		}
		return jPanel1;
	}

	private void jTabbedPane0MouseMouseClicked(MouseEvent event) {
		 jSplitPane0.setOneTouchExpandable(true);
		 jSplitPane0.setDividerLocation(10);

	}
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.add(new TaskFramePanel1());
		

		frame.setVisible(true);
		frame.setSize(500,500);
	}

	private void jTabbedPane0MouseWheelMouseWheelMoved(MouseWheelEvent event) {
		 jSplitPane0.setOneTouchExpandable(true);
		 jSplitPane0.setDividerLocation(131);

	}

	private void jTabbedPane0MouseMouseEntered(MouseEvent event) {
		 jSplitPane0.setOneTouchExpandable(true);
		 jSplitPane0.setDividerLocation(131);

		
	}
}
