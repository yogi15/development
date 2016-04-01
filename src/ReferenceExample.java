import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class ReferenceExample extends JPanel {
   private JDesktopPane desktop = new JDesktopPane();
   private Random random = new Random();

   public ReferenceExample() {
      JButton addInternalFrameBtn = new JButton("Add Internal Frame");
      addInternalFrameBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            addInternalFrame();
         }
      });
      JPanel btnPanel = new JPanel();
      btnPanel.add(addInternalFrameBtn);

      setPreferredSize(new Dimension(600, 450));
      setLayout(new BorderLayout());
      add(new JScrollPane(desktop), BorderLayout.CENTER);
      add(btnPanel, BorderLayout.SOUTH);
   }

   public void addInternalFrame() {
      MyInternalFrame intFrame = new MyInternalFrame(ReferenceExample.this);
      int x = random.nextInt(getWidth() - intFrame.getPreferredSize().width);
      int y = random.nextInt(getHeight() - intFrame.getPreferredSize().height);
      intFrame.setLocation(x, y);
      desktop.add(intFrame);
      intFrame.setVisible(true);
   }

   private static void createAndShowUI() {
      JFrame frame = new JFrame("Reference Eg");
      frame.getContentPane().add(new ReferenceExample());
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
   }

   public static void main(String[] args) {
      java.awt.EventQueue.invokeLater(new Runnable() {
         public void run() {
            createAndShowUI();
         }
      });
   }
}

class MyInternalFrame extends JInternalFrame {

   // pass in the reference in the constructor
   public MyInternalFrame(final ReferenceExample refEg) {
      setPreferredSize(new Dimension(200, 200));
      setClosable(true);

      JButton addInternalFrameBtn = new JButton("Add Internal Frame");
      addInternalFrameBtn.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            // use the reference here
            refEg.addInternalFrame();
         }
      });
      JPanel panel = new JPanel();
      panel.add(addInternalFrameBtn);
      getContentPane().add(panel);
      pack();
   }
}