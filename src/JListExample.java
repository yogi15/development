import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class JListExample extends JFrame
{   
private JPanel p1, p2;
private JList jList;                                                                        // instance variables        
private JScrollPane scrollPane;
private JTextField jtfChoice;

public JListExample()                                                                   // constructor
{
    String[] itemList =  {"alpla", "beta", "gamma", "delta", "omega"};      // array  of Strings for list of items
    jList = new JList(itemList);
    jList.setSelectedIndex(1);                                                          // default item selected
    jList.setVisibleRowCount(3);                                                        // no. of visible rows
    jList.setSize(220, 200);

    p1 = new JPanel();
    p1.add(jtfChoice = new JTextField(8), BorderLayout.CENTER);

    p2 = new JPanel();
    p2.add(scrollPane = new JScrollPane(jList), BorderLayout.WEST);
    p2.add(p1);

    add(p2, BorderLayout.EAST);
    ListenerClass ListSelectionListener = new ListenerClass();
    jList.addListSelectionListener(ListSelectionListener);
}

public static void main(String[] args)
{       
    JListExample frame = new JListExample();                                    // new frame object 
    frame.setTitle("JList Example");                                            // set frame title
    frame.pack();                                                                           // sizes the frame so components fit frame  
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);                   // ends program on frame closing
    frame.setLocationRelativeTo(null);                                              // centre frame
    frame.setVisible(true);                                                 // make frame visible
}
private class ListenerClass implements ListSelectionListener
{   
    public void valueChanged(ListSelectionEvent e)
    {
    	jtfChoice.setText(jList.getSelectedValue().toString());

    }
}
}