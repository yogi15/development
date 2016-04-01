import java.awt.BorderLayout;   
import java.awt.Container;   
import java.awt.event.ActionEvent;   
import java.awt.event.ActionListener;   
import javax.swing.JButton;   
import javax.swing.JFrame;   
import javax.swing.JScrollPane;   
import javax.swing.JTable;   
import javax.swing.table.DefaultTableModel;   
public class AdTblCol {   
  public static void main(String[] args) {   
    final JFrame frame = new JFrame("AdTblCol");   
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
    String[] columns = {"First","Second","Third","Fourth"};   
    String[][] data = {{"One","Two","Three","Four"},   
                       {"Six","Seven","Eight","Nine"},   
                       {"Eleven","Twelve","Thirteen","Fourteen"},   
                       {"Sixteen","Seventeen","Eighteen","Nineteen"}};   
    DefaultTableModel model = new DefaultTableModel(data, columns);   
    final JTable table = new JTable(model);   
    JScrollPane scrollPane = new JScrollPane(table);   
    JButton button = new JButton("Add");   
    button.addActionListener(new ActionListener() {   
      public void actionPerformed(ActionEvent actionEvent) {   
DefaultTableModel dtm = (DefaultTableModel) table.getModel();   
        dtm.addColumn("Fifth",   
                      new String[]{"Five","Ten","Fifteen","Twenty"});   
      }   
    });   
    Container contentPane = frame.getContentPane();   
    contentPane.add(scrollPane);   
    contentPane.add(button, BorderLayout.SOUTH);   
    frame.setSize(400, 500);   
    frame.setVisible(true);   
  }   
}  
