package apps.window.util.windowUtil;

import java.awt.Color;
import java.awt.Font;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.AbstractTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.commonUTIL;
import beans.Attribute;
import beans.Folder;
import beans.StartUPData;
import dsServices.RemoteReferenceData;

//VS4E -- DO NOT REMOVE THIS LINE!
public class attributeUtil extends   javax.swing.JDialog  {

	public static final long serialVersionUID = 1L;
	public JTable jTable0;
	public JScrollPane jScrollPane0;
	public JButton jButton0;
	String col [] = {"AttributeName","AttributeValue"};
	public JPanel jPanel0;
	public Vector<StartUPData>  attributeData = null;
    Hashtable<String,String> values = null;
    public TableModelAttbributeUtil model2 = null;
    public Vector<Attribute> data = new Vector<Attribute>();
    /**
	 * @return the values
	 */
	public Hashtable<String, String> getValues() {
		return values;
	}

	/**
	 * @param values the values to set
	 */
	public void setValues(Hashtable<String, String> values) {
		this.values = values;
	}


	TableModelAttbributeUtil model = null;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public attributeUtil(Vector<StartUPData> attributeData,Hashtable<String,String> values)  {
		this.attributeData = attributeData;
		this.values = values;
		initComponents();
	//	fillAttribute(attributeData,values,model);
		
	}
	public attributeUtil()  {
		this.attributeData = attributeData;
		this.values = values;
		initComponents();
	//	fillAttribute(attributeData,values,model);
		
	}

	public void fillAttribute(Vector attributeData2,
			Hashtable<String, String> values2) {
		// TODO Auto-generated method stub
		Vector<Attribute> attributeData = new Vector<Attribute>();
		if(commonUTIL.isEmpty(attributeData2)) {
			return;
		} else {
			Iterator it = attributeData2.iterator();
		
			while (it.hasNext()) {

				StartUPData stData = (StartUPData) it.next();

				Attribute att = new Attribute();
				att.setName(stData.getName());
				att.setValue(stData.getValue());
				if(!commonUTIL.isEmpty(values2)) {
					String value = values2.get(stData.getName());
					att.setValue(value);
				}
				attributeData.add(att);
			
				
			
			}
			
			}
		
		
		
			model2 = new TableModelAttbributeUtil(attributeData, col);
			jTable0.setModel(model2);
		
		
	}

	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Bilateral(7, 12, 0), new Bilateral(8, 12, 0)));
		setSize(381, 447);
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJScrollPane0(), new Constraints(new Leading(5, 349, 10, 10), new Leading(7, 302, 10, 10)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(14, 10, 10), new Leading(347, 10, 10)));
		}
		return jPanel0;
	}

	public JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("APPLY");
			
		}
		return jButton0;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}

	private JTable getJTable0() {
		if (jTable0 == null) {
			jTable0 = new JTable();
			model2 = new TableModelAttbributeUtil(data,col);
			jTable0.setModel(model2);
			//model2.
		}
		return jTable0;
	}


class TableModelAttbributeUtil extends AbstractTableModel {

	final String[] columnNames;

	final Vector<Attribute> data;
	RemoteReferenceData remoteRF;
	Hashtable counterParty;
	Hashtable folder;

	public TableModelAttbributeUtil(Vector<Attribute> myData, String col[]) {
		this.columnNames = col;
		this.data = myData;
		
	}

	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return data.size();
	}

	public String getColumnName(int col) {
		return columnNames[col];
	}

	public Object getValueAt(int row, int col) {
		Object value = null;

		Attribute attribute = (Attribute) data.get(row);
		switch (col) {
		case 0:
			value =attribute.getName();
			break;
		case 1:
			value = attribute.getValue();
			break;
	

		}
		return value;
	}

	private Folder getFolder(int i) {
		Folder fold1 = null;
		Enumeration fenu = folder.elements();
		while (fenu.hasMoreElements()) {
			fold1 = (Folder) fenu.nextElement();
			if (fold1.getId() == i) {
				break;
			}
		}
		return fold1;

	}

	

	public boolean isCellEditable(int row, int col) {
		return true;
	}

	public void setValueAt(Object value, int row, int col) {

		if (value instanceof Attribute) {
			data.set(row, (Attribute) value);
			//this.fireTableDataChanged();
			fireTableCellUpdated(row, col);
		}
		if( value instanceof String ) {
			Attribute att = new Attribute();
			att.setName((String) this.getValueAt(row, 0));
			att.setValue((String) value);
			data.set(row, att);
			//this.fireTableDataChanged();
			fireTableCellUpdated(row, col);
		}

	}

	public void addRow(Object value) {

		data.add((Attribute) value);
		this.fireTableDataChanged();

	}

	public void delRow(int row) {

		data.remove(row);
		this.fireTableDataChanged();

	}

	public void udpateValueAt(Object value, int row, int col) {

		data.set(row, (Attribute) value);
		fireTableCellUpdated(row, col);
	

	}
 
}
   
    	
}
