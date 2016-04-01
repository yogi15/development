import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2005</p>
 * <p>Company: </p>
 * @author not attributable
 * @version 1.0
 */

public class TableWithCheckBoxEX extends JFrame {
  JPanel contentPane;
  JPanel jPanel1 = new JPanel();
  JScrollPane jScrollPane1 = new JScrollPane();
  JTable jTable1 = new JTable(50,12);
  BorderLayout borderLayout1 = new BorderLayout();

  //Construct the frame
  public TableWithCheckBoxEX() {
    enableEvents(AWTEvent.WINDOW_EVENT_MASK);
    try {
      jbInit();
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  //Component initialization
  private void jbInit() throws Exception  {
    contentPane = (JPanel) this.getContentPane();
    contentPane.setLayout(null);
    this.setSize(new Dimension(521, 458));
    this.setTitle("Frame Title");
    TableColumn includeColumn = jTable1.getColumnModel().getColumn(0);
            includeColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));



    
    jPanel1.setBorder(BorderFactory.createEtchedBorder());
    jPanel1.setBounds(new Rectangle(71, 93, 390, 215));
    jPanel1.setLayout(borderLayout1);
    jScrollPane1.setAutoscrolls(true);
    contentPane.add(jPanel1, null);
    
      jScrollPane1.getViewport().add(jTable1, null);
    jPanel1.add(jScrollPane1, BorderLayout.CENTER);
    
    
  
   jTable1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
  }

  //Overridden so we can exit when window is closed
  protected void processWindowEvent(WindowEvent e) {
    super.processWindowEvent(e);
    if (e.getID() == WindowEvent.WINDOW_CLOSING) {
      System.exit(0);
    }
  }
