package apps.window.operationwindow.jobpanl;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.Date;
import java.util.EventObject;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import util.commonUTIL;


import apps.window.util.windowUtil.DialogBoxForNumberOnly;
import apps.window.util.windowUtil.JDialogBoxForChoice;
import apps.window.util.windowUtil.JDialogBoxForDualChoice;
import beans.FilterBean;

import com.jidesoft.combobox.MultiSelectListExComboBox;
import com.jidesoft.grid.DateCellEditor;
import com.jidesoft.grid.SortableTable;
import com.jidesoft.grid.TextFieldCellEditor;
import com.jidesoft.hints.ListDataIntelliHints;

public class searchCriteriaPanel extends JPanel {
	Vector<FilterBean> data = new Vector<FilterBean>();
	 TableModelUtil mod;
	 String col[] = { "Name", "Criteria", "Values", "And/Or" };
	 public EachRowEditor rowEditor;
  FilterValues fvalues = null;
  
	 /**
 * @return the fvalues
 */
public FilterValues getFvalues() {
	return fvalues;
}
/**
 * @param fvalues the fvalues to set
 */
public void setFvalues(FilterValues fvalues) {
	this.fvalues = fvalues;
}
	public Vector<FilterBean> getFilterBeanData() {
		 return data;
	 }

	 SortableTable table = null;
		private JScrollPane jScrollPane1;
		

	public searchCriteriaPanel() {
		createGUI();
	}
	public void clearllCriterial() {
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
	private void createGUI() {
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED));
		 mod = new TableModelUtil(data,col);
		 FilterBean bean = new FilterBean();
		 
		 table  =new SortableTable(mod);
		 rowEditor = new EachRowEditor(table);
		 
		// mod.addRow(bean);
		 
		 jScrollPane1 = new JScrollPane();
		 jScrollPane1.setViewportView(table);
		 add(jScrollPane1,BorderLayout.CENTER);
		 table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				if(table.getSelectedRow() != -1) {
					int row = table.getSelectedRow();
					 int column = table.getSelectedColumn();
					String value = (String) table.getModel().getValueAt(  row,0);
					String selectedValues = (String) table.getModel().getValueAt(  row,2);
					String firstColumnValue = (String) table.getModel().getValueAt(  row,1);
					if(column == 2)
					addDialogBox(value,selectedValues,row,firstColumnValue);
					
					
				}
				
			}
		});
	     
	        
	}
	 private void getVectorforSelectedValues( String [] values,String selectedValues) {
		 if(!commonUTIL.isEmpty(values)) {
			 
		    		 
		        	int i =0;
		        for(int s=0;s< values.length;s++)
		        		 
		        	 
		        		i++;
		    	}	
		 }
	 private void setdataForDiaglog(Vector<String> criteria1,Vector<String> selectedValueData,String [] cvalues,String selectedValues) {
		 if(!commonUTIL.isEmpty(cvalues)) {
			 String sValues [] = null;
			  
			 if(!commonUTIL.isEmpty(selectedValues) && selectedValues.contains(",")) {
				 sValues = selectedValues.split(",");
				 
			 } else {
				 
			 }
		    		 
		        	int i =0;
		        for(int s=0;s< cvalues.length;s++) {
		        	if(commonUTIL.isEmpty(sValues))	  {
		        		if(!isValueExist(cvalues[s],selectedValues))
		        			criteria1.addElement(cvalues[s]);
		        		else 
		        			selectedValueData.addElement(selectedValues);
		        		
		        	} else {
		        		if(!isValueExist(sValues,cvalues[s]))
		        			criteria1.addElement(cvalues[s]);
		        		else 
		        			selectedValueData.addElement(cvalues[s]);
		        	}
		        }
		    	}	
		 }
	 
	 
	 
	 private boolean isValueExist(String [] selValues, String creiteriaValue) {
		 boolean flag = false;
		 if(!commonUTIL.isEmpty(selValues) ) {
			 for(int i=0;i<selValues.length;i++) {
				 if(selValues[i].equalsIgnoreCase(creiteriaValue)) {
					 flag = true;
					 break;
				 }
			 }
		 }
			 return flag;
	 }
	 private boolean isValueExist(String   selValues, String creiteriaValue) {
		 boolean flag = false;
		 if(!commonUTIL.isEmpty(selValues) ) {
			 
				 if(selValues.equalsIgnoreCase(creiteriaValue)) {
					 flag = true;
					  
				 
			 }
		 }
			 return flag;
	 }
	 private void addDialogBox( String fieldName,String selectedValues,final int rowID,final String firstColumValue) {
		 if(fieldName.endsWith("ID")) {
			  
			 if(commonUTIL.isEmpty(firstColumValue))  {
				  commonUTIL.showAlertMessage("Select Criertia for Selected Row");
				  return;
			 }
					 
			 final DialogBoxForNumberOnly choice12 = new DialogBoxForNumberOnly("Enter " + fieldName,firstColumValue,fieldName); 
             
				choice12.jButton0.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						String text1Value = choice12.jTextField0.getText();//criteriaC.getText();
						if(!commonUTIL.isEmpty(text1Value)) {
							text1Value = text1Value.substring(0, text1Value.indexOf("."));
						}
						if(firstColumValue.equalsIgnoreCase("between")) {
							String text2Value = choice12.jTextField1.getText();//criteriaC.getText();
							if(!commonUTIL.isEmpty(text2Value)) {
								text2Value = text2Value.substring(0, text2Value.indexOf("."));
								text1Value = text1Value +","+text2Value;
							}
						}
						int i = rowID;
						FilterBean filler =  getFilterBeanAtRow(i);
						//textField.setText(attributeName);
						//table.getModel().setValueAt(arg0, arg1, arg2)
						//table.getCellEditor(table.getSelectedColumn(), table.getSelectedRow()).
						filler.setColumnValues(text1Value);
						 removeFilterBeanAtRow(i);
						 addFilterBeanAt(i, filler);
						choice12.dispose();
					}
				});
				choice12.setLocationRelativeTo(choice12);
				choice12.setVisible(true);
				
		
		 } else {
			 Vector dataValues = getFvalues().getValuesonColumn(fieldName,	null);
			 	String mvalues[] = getFvalues().convertVectortoSringArray(dataValues, fieldName, 0);
			  Vector<String> selectData = new Vector<String>();
			  Vector<String> crieteriaData = new Vector<String>();
			  setdataForDiaglog(crieteriaData,selectData,mvalues,selectedValues);
				final JDialogBoxForDualChoice choice12 = new JDialogBoxForDualChoice( crieteriaData, selectData);
				choice12.setLocationRelativeTo(choice12);
				//choice12.setSize(200,200);
			//	choice12.jList3.setModel(templates);
				choice12.setVisible(true);
				choice12.addWindowListener(new WindowAdapter() {            
		            public void windowClosing(WindowEvent e) {
						// TODO Auto-generated method stub
		            	Vector<String> vec =  choice12.getObj();
		            	String attributeName = "";
		                  
		                  if(!commonUTIL.isEmpty(vec)) {
		                	  for(int i=0;i<vec.size();i++)
		                		  attributeName =attributeName + (String) vec.get(i) + ",";
		                  }
		                  if(!commonUTIL.isEmpty(attributeName)) {
		                	  attributeName = attributeName.substring(0, attributeName.length()-1);
		                  }
		               //   textField.setText(attributeName);
					//	String attributeName = choice12.jTextField0.getText();//criteriaC.getText();
						int i =  rowID;
						FilterBean filler = getFilterBeanAtRow(i);
						filler.setColumnValues(attributeName);
						 removeFilterBeanAtRow(i);
						 addFilterBeanAt(i, filler);
						 choice12.ClearALL();
						choice12.dispose();
					}
				});
				choice12.setLocationRelativeTo(choice12);
				choice12.setVisible(true);
		 }
	 }
	 /**
	 * @return the rowEditor
	 */
	
	public void addNewRow(FilterBean bean) {
		if(mod != null) 
	         mod.addRow(bean);
	}
	
	
	public FilterBean getFilterBeanAtRow(int row) {
		System.out.println("getFilterBeanAtRow "+row);
		return ( FilterBean) data.get(row);
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
	public void addRowEditor(int row,int col,JTextField textField,String columnName) {
		DefaultCellEditor ed = new DefaultCellEditor(textField);
		rowEditor.setEditorForRowCol(row,col,ed);
		 table.getColumn(columnName).setCellEditor(rowEditor);
	}
	public void addIntellIHintColumn(int column,final String [] values) {
		table.getColumnModel().getColumn(column)
		.setCellEditor(new TextFieldCellEditor(String.class) {
			private static final long serialVersionUID = 2023654568542192380L;

			@Override
			protected JTextField createTextField() {
				JTextField cellEditorTextField = new JTextField();
				final ListDataIntelliHints fontIntellihints = new ListDataIntelliHints<String>(
						cellEditorTextField, values);
				fontIntellihints.setCaseSensitive(false);
				cellEditorTextField.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						String attributeName = fontIntellihints.getSelectedHint().toString();
						int i =  table.getSelectedRow();
						FilterBean filler = getFilterBeanAtRow(i);
						filler.setSearchCriteria(attributeName);
						 removeFilterBeanAtRow(i);
						 addFilterBeanAt(i, filler);
						
					}
				});
					cellEditorTextField.addFocusListener(new FocusListener() {
	
						@Override
						public void focusLost(FocusEvent arg0) {
		// TODO Auto-generated method stub
							String attributeName = fontIntellihints.getSelectedHint().toString();
							int i =  table.getSelectedRow();
							FilterBean filler = getFilterBeanAtRow(i);
							filler.setSearchCriteria(attributeName);
							 removeFilterBeanAtRow(i);
							 addFilterBeanAt(i, filler);
							
						}
	
						@Override
						public void focusGained(FocusEvent arg0) {
		// TODO Auto-generated method stub
		
						}
					}); 
					
				 
			 
				return cellEditorTextField;
			}
		});
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
	private void setTable(SortableTable table) {
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
				value = filter.getAnd_or();
				break;
			}
			return value;
		}

		public boolean isCellEditable(int row, int col) {
			if (col == 0)
				return false;
			return true;
		}

		public void setValueAt(Object value, int row, int col) {
			if (value == null)
				return;
			System.out.println("Setting value at " + row + "," + col + " to "
					+ value + " (an instance of " + value.getClass() + ")");
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
	    	Vector columns = (Vector) editors.get(new Integer(row));
	         return (TableCellEditor)columns.get(col);
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
			if(anEvent instanceof KeyEvent) 
				selectEditor((KeyEvent) anEvent);
			if(anEvent instanceof MouseEvent)
			selectEditor((MouseEvent) anEvent);
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
			selectEditor((MouseEvent) anEvent);
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
			if(cols == null)
				return;
			editor = (TableCellEditor) cols.get(col-1);
			}
			
			if (editor == null) {
				editor = defaultEditor;
			}
		}
		protected void selectEditor(KeyEvent e) {
			int row =0;
			int col =0;
			
			if (e == null) {
				row = table.getSelectionModel().getAnchorSelectionIndex();
				col = table.getSelectedColumn();
			} else {
				//row = table.get
				//col = table.columnAtPoint(e.getPoint());
			}
			System.out.println("From selectEditor row == " + row + " col == " + col);
			if(col == -1) {
				editor = defaultEditor;
			} else 
			if(col == 0) {
				editor = defaultEditor;
			} else {
			
			Vector cols = (Vector) editors.get(new Integer(row));
			if(cols == null)
				return;
			editor = (TableCellEditor) cols.get(col-1);
			}
			
			if (editor == null) {
				editor = defaultEditor;
			}
		}
	}

	class MultiSelectionRenderer extends DefaultCellEditor {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

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

}
