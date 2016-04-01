
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class ResourceModExample {
  public static void main(String[] args) {

    // A custom border for all buttons
    Border border = BorderFactory.createRaisedBevelBorder();
    Border tripleBorder = new CompoundBorder(new CompoundBorder(
      border, border), border);

    UIManager.put("Button.border", tripleBorder);

    // Custom icons for internal frames
    UIManager.put("InternalFrame.closeIcon",
      new ImageIcon("/resources/icon/sql.jpg"));
    UIManager.put("InternalFrame.iconizeIcon",
      new ImageIcon("/resources/icon/sql.png"));
    UIManager.put("InternalFrame.maximizeIcon",
      new ImageIcon("/resources/icon/down.png"));
    UIManager.put("InternalFrame.altMaximizeIcon",
      new ImageIcon("/resources/icon/right.png"));

    // A custom internal frame title font
    UIManager.put("InternalFrame.titleFont",
      new Font("Serif", Font.ITALIC, 12));

    // Make scrollbars really wide

    UIManager.put("ScrollBar.width", new Integer(30));

    // Throw together some components to show what we’ve done.
    // Nothing below here is L&F-specific.
    // ***********************************
    JFrame f = new JFrame();
    f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    Container c = f.getContentPane();

    JDesktopPane desk = new JDesktopPane();
    c.add(desk, BorderLayout.CENTER);

    JButton cut = new JButton("Cut");
    JButton copy = new JButton("Copy");
    JButton paste = new JButton("Paste");

    JPanel p = new JPanel(new FlowLayout());
    p.add(cut);
    p.add(copy);
    p.add(paste);
    c.add(p, BorderLayout.SOUTH);

    JInternalFrame inf = new JInternalFrame("", false, false, false, false);
//    inf.setUI(ui)(UIManager.getDefaults());
     JLabel l = new JLabel(new ImageIcon("/resources/icon/sql.jpg"));
    JScrollPane scroll = new JScrollPane(l);
    inf.setContentPane(scroll);
    inf.setBounds(10, 10, 350, 280);
    desk.add(inf);
    inf.setVisible(true);

    f.setSize(380, 360);
    f.setVisible(true);
  }
}
