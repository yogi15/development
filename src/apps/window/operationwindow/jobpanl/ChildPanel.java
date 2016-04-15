package apps.window.operationwindow.jobpanl;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;

public class ChildPanel extends JPanel {
	
	
	
	
	public ChildPanel() {
	//	testTaskPanel test = new testTaskPanel();
	//	super.add(test,new Constraints(new Bilateral(10, 10, 0), new Bilateral(9, 13, 10)));
		
	}
	
	public void add(JPanel jpanel) {
		super.add(jpanel,new Constraints(new Bilateral(4, 3, 0), new Bilateral(6, 6, 10)));
	}

}
