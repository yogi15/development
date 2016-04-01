

import javax.swing.*;
import java.awt.*;

public class JToolbarColor extends JFrame
{
	public static void main(String args[]) throws Exception
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		JToolbarColor t = new JToolbarColor();
		t.setVisible(true);
	}

	public JToolbarColor()
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		setSize(400,200);

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(Color.GREEN);

		JToolBar toolbar1 = new JToolBar();
		toolbar1.add(new JButton("Button 1"));
		toolbar1.add(new JButton("Button 2"));
		toolbar1.setBackground(Color.RED);

		JToolBar toolbar2 = new JToolBar();
		toolbar2.setOpaque(false);
		toolbar2.add(new JButton("Button A"));
		toolbar2.add(new JButton("Button B"));

		panel.add(toolbar1,BorderLayout.NORTH);
		panel.add(new JLabel("<html><body>Top toolbar should be red as background was set to <code>Color.RED</code><br><br>The bottom toolbar should be green as </code>setOpaque(false)</code> was called on the toolbar and the underlying <code>JPanel</code> has a background of <code>Color.GREEN</code>.</body></html>"),BorderLayout.CENTER);
		panel.add(toolbar2,BorderLayout.SOUTH);



		getContentPane().add(panel);
	}
}
