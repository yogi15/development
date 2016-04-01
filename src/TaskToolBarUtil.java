import java.awt.Font;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JToolBar;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.Leading;


public class TaskToolBarUtil {
	
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private JToolBar jToolBar0;
	
	
	
	
	
	
	public JToolBar getJToolBar0() {
		if (jToolBar0 == null) {
			jToolBar0 = new JToolBar();
			jToolBar0.add(Box.createHorizontalGlue());
			jToolBar0.setBorderPainted(false);
			
			 
			jToolBar0.add(getJButton0(), new Constraints(new Bilateral(7, 0, 9), new Leading(9, 13, 10, 10)));
			jToolBar0.add(getJButton1(), new Constraints(new Bilateral(7, 0, 9), new Leading(9, 13, 10, 10)));
			jToolBar0.add(getJButton2(), new Constraints(new Bilateral(7, 0, 9), new Bilateral(7, 0, 9)));
			jToolBar0.setAlignmentX(90);
			

			jToolBar0.setFloatable(false);
		}
		return jToolBar0;
	}
	public JToolBar getJToolBar1() {
		if (jToolBar0 == null) {
			jToolBar0 = new JToolBar();
			//jToolBar0.add(Box.createHorizontalGlue());
			jToolBar0.setBorderPainted(false);
			
			 
			jToolBar0.add(getJButton0(), new Constraints(new Bilateral(7, 0, 9), new Leading(9, 13, 10, 10)));
			jToolBar0.add(getJButton1(), new Constraints(new Bilateral(7, 0, 9), new Leading(9, 13, 10, 10)));
			jToolBar0.add(getJButton2(), new Constraints(new Bilateral(7, 0, 9), new Bilateral(7, 0, 9)));
			jToolBar0.setAlignmentX(90);
			

			jToolBar0.setFloatable(false);
		}
		return jToolBar0;
	}
	
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setFont(new Font("Arial", Font.PLAIN, 10));
			jButton2.setText("2b");
		}
		return jButton2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setFont(new Font("Arial", Font.PLAIN, 10));
			jButton1.setText("1B");
		}
		return jButton1;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setFont(new Font("Arial", Font.PLAIN, 10));
			jButton0.setText("j0");
		}
		return jButton0;
	}

}
