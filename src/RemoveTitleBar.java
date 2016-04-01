import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.JLabel;

public class RemoveTitleBar
extends JFrame {


public RemoveTitleBar() {
super("Frame Without TitleBar");


JInternalFrame iFrame = new JInternalFrame();


// Get the titlebar and set it to null
setRootPaneCheckingEnabled(false);
javax.swing.plaf.InternalFrameUI ifu= iFrame.getUI();
((javax.swing.plaf.basic.BasicInternalFrameUI)ifu).setNorthPane(null);
 


iFrame.setBounds(30,30,200,200);
iFrame.setVisible(true);


JDesktopPane jp = new JDesktopPane();
jp.add(iFrame);
setContentPane(jp);
}

public static void main(String[] args){
RemoveTitleBar rtb = new RemoveTitleBar();
rtb.setBounds(20,20,400,400);
rtb.setVisible(true);
rtb.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
