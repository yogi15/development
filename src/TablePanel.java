import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class TablePanel extends JPanel implements ActionListener,Serializable
{
    JTable m_table;
    JComboBox combo,combo1;
    DefaultTableModel model=new DefaultTableModel();
    DefaultComboBoxModel model1=new DefaultComboBoxModel();
    DefaultComboBoxModel model2=new DefaultComboBoxModel();
        List<String> field;
    List<String> attrCode;
    TablePanel()
    {

            m_table=new JTable(model);
            m_table.setBackground(Color.WHITE);
            model.addColumn("col1");
            model.addColumn("col2");
            model.addColumn("col3");
            model.addColumn("col4");
            model.addColumn("col5");
            model.addColumn("col6");
            JScrollPane scrollpane=new JScrollPane(m_table);
            scrollpane.setBackground(Color.WHITE);
            Dimension d = m_table.getPreferredSize();
            scrollpane.setPreferredSize(
                new Dimension(d.width,m_table.getRowHeight()*15+1));
            add(scrollpane);

            
	

                    
               combo1=new JComboBox(model2);
               model1.addElement("test1");
               model1.addElement("test1");
            model1.addElement("test1");
           
           TableColumn col=m_table.getColumnModel().getColumn(0);
            col.setCellEditor((new DefaultCellEditor(combo1)));
            combo=new JComboBox(model2);
            model2.addElement("test1");
            model2.addElement("test1");
            model2.addElement("test1");
            model2.addElement("test1");
           
            col=m_table.getColumnModel().getColumn(2);

            col.setCellEditor((new DefaultCellEditor(combo)));}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	} }


