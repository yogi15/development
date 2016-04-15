package apps.window.reportwindow;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.EventObject;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.AbstractCellEditor;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;

import util.ClassInstantiateUtil;
import util.commonUTIL;

 
import beans.FilterBean;
import beans.UserJobsDetails;

import com.jidesoft.combobox.MultiSelectListExComboBox;
import com.jidesoft.grid.DateCellEditor;

public class searchCriteriaPanel extends JPanel {
	Vector<FilterBean> data = new Vector<FilterBean>();
	 TableModelUtil mod;
	 String col[] = { "Name", "Criteria", "Values", "And/Or" };
	 SearchCriteriaType searchType = null;
	 public EachRowEditor rowEditor;

	 public Vector<FilterBean> getFilterBeanData() {
		 return searchType.searchCriteria();///data;
	 }

	public JTable table; 
		private JScrollPane jScrollPane1;
		

	public searchCriteriaPanel(String reportType) {
		createGUI(reportType);
	}
	public void clearllCriterial() {
		searchType.clearllCriterial();
		mod = null;
		mod = new TableModelUtil(data,col);
		table.setModel(mod);
		data.clear();
		
		
	}
	public void clearllCriteriaModel() {
		mod.removeALL();
		
		data.clear();
		
		
	}
	public void deleteRowCriteria(int row) {
		mod.delRow(row);
		table.repaint();
		mod.fireTableDataChanged();
	}
	private void createGUI(String reportType) {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		 mod = new TableModelUtil(data,col);
		 FilterBean bean = new FilterBean();
		 table  = new JTable() ;
		 rowEditor = new EachRowEditor(table);
		 bean.setAnd_or("AND");
		 bean.setColumnName("ProductType");
		 bean.setSearchCriteria("=");
		 bean.setColumnValues("Bond");
		// mod.addRow(bean);
		 table.setModel(mod);
		 jScrollPane1 = new JScrollPane();
		 searchType = makeSearchTypePanel(reportType);
		 
		// jScrollPane1.setViewportView(table);
		// add(jScrollPane1,BorderLayout.CENTER);
		add(searchType,BorderLayout.CENTER);
	        
	}
	 /**
	 * @return the rowEditor
	 */
	
	public void addNewRow(FilterBean bean) {
		if(mod != null) 
	         mod.addRow(bean);
	}
	
	
	public FilterBean getFilterBeanAtRow(int row) {
		if(row >= 0)
		return ( FilterBean) data.get(row);
		return null;
	}
	public void removeFilterBeanAtRow(int row) {
		data.remove(row);
	}
	
	public void addFilterBeanAt(int row,FilterBean filterBean) {
		data.add(row,filterBean);
	}
	public int getTableRowCount() {
		return table.getRowCount();
	}
	public void addRowEditor(int row,MultiSelectListExComboBox multiCombox,String columnName) {
		 final MultiSelectionRenderer ed = new MultiSelectionRenderer(multiCombox,null);
		rowEditor.setEditorAt(row,ed);
		 table.getColumn(columnName).setCellEditor(rowEditor);
	}
	public void addRowEditor(int row,int col,MultiSelectListExComboBox multiCombox,String columnName) {
		 final MultiSelectionRenderer ed = new MultiSelectionRenderer(multiCombox,null);
		rowEditor.setEditorForRowCol(row,col,ed);
		 table.getColumn(columnName).setCellEditor(rowEditor);
	}
	public void addRowEditor(int row,int col,JTextField jtextField,String columnName) {
		MyTableCellEditor1 ed = new MyTableCellEditor1(jtextField,null);
		rowEditor.setEditorForRowCol(row,col,ed);
		 table.getColumn(columnName).setCellEditor(rowEditor);
	}
	public void addRowEditor(int row,int col,DateCellEditor multiCombox,String columnName) {
	//	 final MultiSelectionRenderer ed = new MultiSelectionRenderer(multiCombox,null);
		rowEditor.setEditorForRowCol(row,col,multiCombox);
		 table.getColumn(columnName).setCellEditor(rowEditor);
	}
	public void addRowEditor(int row,JComboBox combox,String columnName) {
		DefaultCellEditor ed = new DefaultCellEditor(combox);
		rowEditor.setEditorAt(row,ed);
		 table.getColumn(columnName).setCellEditor(rowEditor);
	}
	public void addRowEditor(int row,int col,JComboBox combox,String columnName) {
		DefaultCellEditor ed = new DefaultCellEditor(combox);
		rowEditor.setEditorForRowCol(row,col,ed);
		 table.getColumn(columnName).setCellEditor(rowEditor);
	}
	private EachRowEditor getRowEditor() {
		return rowEditor;
	}
	/**
	 * @param rowEditor the rowEditor to set
	 */
	private void setRowEditor(EachRowEditor rowEditor) {
		this.rowEditor = rowEditor;
	}
	/**
	 * @return the table
	 */
	private JTable getTable() {
		return table;
	}
	/**
	 * @param table the table to set
	 */
	private void setTable(JTable table) {
		this.table = table;
	}
	class TableModelUtil extends AbstractTableModel {
		final String[] columnNames;

		Vector<FilterBean> data;

		public TableModelUtil(Vector<FilterBean> myData, String col[]) {
			this.columnNames = col;
			this.data = myData;
		}

		public int getColumnCount() {
			return columnNames.length;
		}

		public int getRowCount() {
			if (data != null)
				return data.size();
			return 0;
		}

		public String getColumnName(int col) {
			return columnNames[col];
		}

		public Object getValueAt(int row, int col) {
			Object value = null;

			FilterBean filter = (FilterBean) data.get(row);

			switch (col) {

			case 0:
				value = filter.getColumnName();
				break;
			case 1:
				value = filter.getSearchCriteria();
				break;
			case 2:
				value = filter.getColumnValues();
				break;
			case 3:
				String ss = filter.getAnd_or();
				value = ss;
				break;
			}
			return value;
		}

		public boolean isCellEditable(int row, int col) {
			
			return true;
		}

		public void setValueAt(Object value, int row, int col) {
			if (value == null)
				return;
			System.out.println("Setting value at " + row + "," + col + " to "
					+ value + " (an instance of " + value.getClass() + ")");
			FilterBean ff = (FilterBean)	data.get(row);
			if(col == 3) {
				ff.setAnd_or(value.toString());
				removeFilterBeanAtRow(row);
				addFilterBeanAt(row, ff);
			}
			
			if (value instanceof FilterBean) {
				data.set(row, (FilterBean) value);
				this.fireTableDataChanged();
				System.out.println("New value of data:");
			}

		}

		public void addRow(Object value) {

			data.add((FilterBean) value);
			this.fireTableDataChanged();

		}

		public void delRow(int row) {
			if (row != -1) {
				data.remove(row);
				this.fireTableDataChanged();
			}

		}

		public void udpateValueAt(Object value, int row, int col) {

			data.set(row, (FilterBean) value);
			for (int i = 0; i < columnNames.length; i++)
				fireTableCellUpdated(row, i);

		}
		public void removeALL() {
			if (data != null) {
				data.removeAllElements();
			}
			// data = null;
			this.fireTableDataChanged();
		}
	}

	class Filler {
		String columname = "";

		public String getColumname() {
			return columname;
		}

		public void setColumname(String columname) {
			this.columname = columname;
		}

		public String getColumncre() {
			return columncre;
		}

		public void setColumncre(String columncre) {
			this.columncre = columncre;
		}

		public String getColumnval() {
			return columnval;
		}

		public void setColumnval(String columnval) {
			this.columnval = columnval;
		}

		String columncre = "";
		String columnval = "";

	}

	class EachRowEditor implements TableCellEditor {
		protected Hashtable editors;

		protected TableCellEditor editor, defaultEditor;

		JTable table;

		/**
		 * Constructs a EachRowEditor. create default editor
		 * 
		 * @see TableCellEditor
		 * @see DefaultCellEditor
		 */
		public EachRowEditor(JTable table) {
			this.table = table;
			editors = new Hashtable();
			defaultEditor = new DefaultCellEditor(new JTextField());
		}

		/**
		 * @param row
		 *            table row
		 * @param editor
		 *            table cell editor
		 */
		public void setEditorAt(int row, TableCellEditor editor) {
			editors.put(new Integer(row), editor);
		}
		 public void setEditorForRowCol(int row, int col,TableCellEditor e )
	     {
	    	 System.out.println("From roweditors adding row " + row + " col " + col + " "+ e.getCellEditorValue());
	    	Vector columns = (Vector) editors.get(new Integer(row));
	    	if(columns != null) {
	    		columns.add(col-1, e);
	    	} else {
	    		columns = new Vector();
	    		columns.add(col-1, e);
	    	}
	    	 
	    	 
	    	editors.put(new Integer(row), columns);
	     }
		 public TableCellEditor getEditor(int row,int col)
	     {
	    	System.out.println("From getEditor row " + row + " col " + col);
	    	TableCellEditor c1 = null;
	    	if(row >= 0 && col >= 0) {
	    	Vector columns = (Vector) editors.get(new Integer(row)); 
	    	if(!commonUTIL.isEmpty(columns))
	    	 return (TableCellEditor)columns.get(col-1);
	    	return c1;
	    	}
	         return c1;
	     }
		public Component getTableCellEditorComponent(JTable table,
				Object value, boolean isSelected, int row, int column) {
			// editor = (TableCellEditor)editors.get(new Integer(row));
			// if (editor == null) {
			// editor = defaultEditor;
			// }
			return editor.getTableCellEditorComponent(table, value, isSelected,
					row, column);
		}

		public Object getCellEditorValue() {
			if(editor.getCellEditorValue() instanceof String) 
				return (String) editor.getCellEditorValue();
			if(editor.getCellEditorValue() instanceof Date) 
				return (String) commonUTIL.dateToString((Date)editor.getCellEditorValue());
			return null;
		}

		public boolean stopCellEditing() {
			return editor.stopCellEditing();
		}

		public void cancelCellEditing() {
			editor.cancelCellEditing();
		}

		public boolean isCellEditable(EventObject anEvent) {
			if(anEvent instanceof KeyEvent) {
				selectEditor((KeyEvent) anEvent);
			} else {
			selectEditor((MouseEvent) anEvent);
			}
			if(editor == null)
				return false;
			return editor.isCellEditable(anEvent);
		}

		public void addCellEditorListener(CellEditorListener l) {
			editor.addCellEditorListener(l);
		}

		public void removeCellEditorListener(CellEditorListener l) {
			editor.removeCellEditorListener(l);
		}

		public boolean shouldSelectCell(EventObject anEvent) {
			if(anEvent instanceof KeyEvent) {
				selectEditor((KeyEvent) anEvent);
			} else {
			selectEditor((MouseEvent) anEvent);
			}
			return editor.shouldSelectCell(anEvent);
		}
		
		protected void selectEditor(MouseEvent e) {
			int row =0;
			int col =0;
			
			if (e == null) {
				row = table.getSelectionModel().getAnchorSelectionIndex();
				col = table.getSelectedColumn();
			} else {
				row = table.rowAtPoint(e.getPoint());
				col = table.columnAtPoint(e.getPoint());
			}
			System.out.println("From selectEditor row == " + row + " col == " + col);
			if(col == -1) {
				editor = defaultEditor;
			} else 
			if(col == 0) {
				editor = defaultEditor;
			} else {
			
			Vector cols = (Vector) editors.get(new Integer(row));
			if(!commonUTIL.isEmpty(cols)) 
			editor = (TableCellEditor) cols.get(col-1);
			}
			
			if (editor == null) {
				editor = defaultEditor;
			}
		}
		protected void selectEditor(KeyEvent e) {
			int row =0;
			int col =0;
			
			
				row = table.getSelectionModel().getAnchorSelectionIndex();
				col = table.getSelectedColumn();
			
			System.out.println("From selectEditor row == " + row + " col == " + col);
			if(col == -1) {
				editor = defaultEditor;
			} else 
			if(col == 0) {
				editor = defaultEditor;
			} else {
			
			Vector cols = (Vector) editors.get(new Integer(row));
			
			editor = (TableCellEditor) cols.get(col-1);
			}
			
			if (editor == null) {
				editor = defaultEditor;
			}
		}
	}

	class MultiSelectionRenderer extends DefaultCellEditor {

		public MultiSelectionRenderer(final MultiSelectListExComboBox comboBox,
				CellEditorListener listener) {
			super(comboBox);
			_listener = listener;
			if (listener != null)
				addCellEditorListener(listener);
			comboBox.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if (comboBox.getSelectedObjects().length > 0) {
						// TODO Auto-generated method stub
						String ss = "";
						Object obj[] = comboBox.getSelectedObjects();
						for (int i = 0; i < comboBox.getSelectedObjects().length; i++) {
							ss = ss + (String) obj[i] + ",";
						}
						set_value(ss);

					}
				}
			});
			// TODO Auto-generated constructor stub
		}

		Object _value = null;

		/**
		 * @return the _value
		 */
		private Object get_value() {
			return _value;
		}

		/**
		 * @param _value
		 *            the _value to set
		 */
		private void set_value(Object _value) {
			this._value = _value;
		}

		int _row = -1;
		int _column = -1;
		CellEditorListener _listener = null;

		@Override
		public Object getCellEditorValue() {
			// TODO Auto-generated method stub
			return (String) get_value();
		}

	}
	
	class MyTableCellEditor1 extends AbstractCellEditor implements TableCellEditor {

		  JComponent component = null;
		  public MyTableCellEditor1(JTextField jtextF,CellEditorListener listener) {
			  component = (JTextField) jtextF;
			 
		  }
		  public Component getTableCellEditorComponent(final JTable table, Object value, boolean isSelected,
		      int rowIndex, int vColIndex) {

		    ((JTextField) component).setText((String) value);
		    if(component instanceof JTextField) {
		    component.addKeyListener(new KeyAdapter() {

		          @Override
		          public void keyTyped(KeyEvent e) {
		        	  //System.out.println(e.getKeyChar());
		    
		          //  if(e.getKeyChar() == KeyEvent.VK_ENTER) { 
					
		            /*	String attributeName = ((JTextField) component).getText().toString();
		              
		              	int i = table.getSelectedRow();
		              	FilterBean filler =getFilterBeanAtRow(i);
						if(filler != null) {
							filler.setAnd_or(attributeName);
							removeFilterBeanAtRow(i);
							addFilterBeanAt(i, filler); 
						}*/

		           // }
					}
		          
				});
		    component.addFocusListener(new FocusAdapter() {
		  	  public void focusGained(FocusEvent e) { 
		  	
		  		
		          	          	
		  				
		  				
		  				//	searchPanel.setFocusable(true);
		  	  }
		  				
		  			
		  	  });
		    }
		    return component;
		  }

		  public Object getCellEditorValue() {
		    return ((JTextField) component).getText();
		  }
		}

class MyTableCellEditor  extends DefaultCellEditor { 

	JTextField component = new JTextField();
    Object value  = null;
    CellEditorListener _listener = null;
 public MyTableCellEditor(JTextField jtextF,CellEditorListener listener) {
	 
	  super(jtextF);
	  component = jtextF;
	  _listener = listener;
	  if (listener != null)
			addCellEditorListener(listener);
	  component.addKeyListener(new KeyAdapter() {

          @Override
          public void keyTyped(KeyEvent e) {
        	  System.out.println(e.getKeyChar());
    
            
			
					// TODO Auto-generated method stub
            	  //System.out.println("Coming from ruennenenenene ");
              	
				
				//	searchPanel.setFocusable(true);

				
			}
          
		});
	  component.addFocusListener(new FocusAdapter() {
	  public void focusGained(FocusEvent e) { 
	
		
        	          	String attributeName = component.getText();
              	setValue(attributeName);
              	int i = table.getSelectedRow();
              	FilterBean filler =getFilterBeanAtRow(i);
				if(filler != null) {
					filler.setAnd_or(attributeName);
					removeFilterBeanAtRow(i);
					addFilterBeanAt(i, filler);
				}
				//	searchPanel.setFocusable(true);
	  }
				
			
	  });
  }
   
 public void setValue(String value1) {
	 value = value1;
 }
 public Object getValue() {
	return (String) value;
 }
 @Override
  public Object getCellEditorValue() {
    return getValue();
  }

}

public void loadTemplate(Vector<UserJobsDetails> jobdetails) {
	// TODO Auto-generated method stub
	searchType.loadFilters(jobdetails);
}
protected SearchCriteriaType makeSearchTypePanel(String name) {
	String productWindowName = "apps.window.reportwindow."
			+ name + "SearchPanel";
	SearchCriteriaType panel = null;

	try {
		Class class1 = ClassInstantiateUtil.getClass(productWindowName,
				true);
		panel = (SearchCriteriaType) class1.newInstance();
		// productWindow = (BondPanel)
	} catch (Exception e) {
		System.out.println(e);
	}

	return panel;
}

}
