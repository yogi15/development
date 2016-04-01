import javax.swing.JFrame;
import javax.swing.JPanel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;


//VS4E -- DO NOT REMOVE THIS LINE!
public class TrialBalance extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;

	public TrialBalance() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(12, 853, 12, 12), new Leading(12, 295, 12, 12)));
		setSize(943, 383);
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
		}
		return jPanel0;
	}

}
