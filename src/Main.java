
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;


/**
 *
 * @author stella
 */
public class Main extends JPanel 
implements ListSelectionListener {
 String name;
 private JLabel picture;
 public JList list;
 JFrame frame;
 JPanel panel;

 private JSplitPane splitPane;
 private String[] imageNames = { "wedding","stel"};

 public Main(){
 list = new JList(imageNames);
 list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
 list.setSelectedIndex(0);
 list.addListSelectionListener(this);

 JScrollPane listScrollPane = new JScrollPane(list);
 picture = new JLabel();
 picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
 picture.setHorizontalAlignment(JLabel.CENTER);

 JScrollPane pictureScrollPane = new JScrollPane(picture);

 splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
 listScrollPane, pictureScrollPane);
 // splitPane.setOneTouchExpandable(true);
 splitPane.setDividerLocation(150);

 //Provide minimum sizes for the two components in the split pane.
 Dimension minimumSize = new Dimension(1000, 2000);
 listScrollPane.setMinimumSize(minimumSize);
 pictureScrollPane.setMinimumSize(minimumSize);

 //Provide a preferred size for the split pane.
 splitPane.setPreferredSize(new Dimension(1000, 1000));
 updateLabel(imageNames[list.getSelectedIndex()]);
 }
 public void valueChanged(ListSelectionEvent e) {
 JList list = (JList)e.getSource();
 updateLabel(imageNames[list.getSelectedIndex()]);
 }

 //Renders the selected image
 public void updateLabel (String name) {

 // java.net.URL imageURL = cldr.getResource("hello/images/" +name ".JPG");
 //ImageIcon icon=new ImageIcon(imageURL);
 ImageIcon icon = createImageIcon("hello/images/" + name + ".JPG");
 picture.setIcon(icon);
 if (icon != null) {
 picture.setText(null);
 } else {
 picture.setText("Image not found");
 }
 }
 //Used by SplitPaneDemo2
 public JList getImageList() {
 return list;
 }

 public JSplitPane getSplitPane() {
 return splitPane;
 }

 public static void main(String[] args) {
 Main gui=new Main();
 gui.Main1();

 // TODO code application logic here
 //javax.swing.SwingUtilities.invokeLater(new Runnable() {
 //// public void run() {
 // createAndShowGUI();
 System.out.println("Hi i am new so pl better work fine");
 // }
 // });
 }

 public ImageIcon createImageIcon(String path) {
 ClassLoader cldr=Main.class.getClassLoader();
 java.net.URL imgURL = cldr.getResource(path);
 if (imgURL != null) {
 return new ImageIcon(imgURL);
 } else {
 System.err.println("Couldn't find file: " + path);
 return null;
 }
 }
 /**
 * Create the GUI and show it. For thread safety,
 * this method should be invoked from the
 * event-dispatching thread.
 */

 public void Main1() {

 frame = new JFrame("FrameDemo");
 panel=new JPanel();
 panel.setBackground(Color.PINK);
 //frame.setLocationRelativeTo(null);
 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 Main obj=new Main();
 frame.getContentPane().add(obj.getSplitPane());
 // JButton but=new JButton("Exit");


 // panel.add(but);
 // but.addActionListener(this);
 //ClassLoader cldr=this.getClass().getClassLoader();
 // java.net.URL imageURL = cldr.getResource("hello/images/DSC_0347.JPG");
 //ImageIcon image=new ImageIcon(imageURL);
 //JLabel im = new JLabel(image);
 // panel.add(im);


 //im.setOpaque(false);
 frame.getContentPane().add(BorderLayout.EAST, panel);
 //frame.setSize(1550, 1500); 


//frame.getContentPane().add(im, BorderLayout.CENTER);
 frame.pack();
 frame.setVisible(true);

 }
}
 // public void actionPerformed(ActionEvent e) {
 // Toolkit.getDefaultToolkit().beep();
 // JOptionPane.showMessageDialog(frame, "Bye");
 // System.exit(0);
 // }
