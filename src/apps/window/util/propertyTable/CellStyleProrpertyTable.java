package apps.window.util.propertyTable;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import util.commonUTIL;
import apps.window.util.property.CellDesignProperty;
import apps.window.util.propertyPane.enumsList.CellStylePropertyEnumList;
import beans.PropertyCellStyle;

import com.jidesoft.grid.PropertyTable;
import com.jidesoft.grid.PropertyTableModel;

 

public class CellStyleProrpertyTable   implements PropertyChangeListener {
	
	private PropertyTable propertyTable = null;
	List<CellDesignProperty> cellStylesproperties = null;
	JList<String> fieldNames = null;
	static HashMap<String, PropertyCellStyle> map = new HashMap<String, PropertyCellStyle>();
	
	
	/**
	 * @param map the map to set
	 */
	public static void setCellStyle(  Vector<PropertyCellStyle> propCellSty) {
		map.clear();
		for(int i=0;i<propCellSty.size();i++) {
			PropertyCellStyle style = propCellSty.get(i);
			map.put(style.getPropertyname(), style);
		}
	}
	public Vector<PropertyCellStyle> getPropertyCellStyles() {
		Vector<PropertyCellStyle> cellStyles = new Vector<PropertyCellStyle>();
		 if(!commonUTIL.isEmpty(map)) {
			 Set set = map.entrySet();
			    Iterator it = set.iterator();
			    while (it.hasNext()) {
			      Map.Entry entry = (Map.Entry) it.next();
			     
			      cellStyles.add(map.get(entry.getKey()));
			    }

		 }
		return cellStyles;
	}
	public CellStyleProrpertyTable(String productName) {
		

	}

		 public CellStyleProrpertyTable(String string, JList<String> fieldNames) {
			this. fieldNames = fieldNames;
			fieldNames.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// TODO Auto-generated method stub
					 
					if(((JList) e.getSource()).getSelectedIndex() == -1) 
							return;
					 String propertyName = ((JList) e.getSource()).getSelectedValue().toString();
					String fontStyle;
            		String foreColor;
            		String backColor;
            		if(!commonUTIL.isEmpty(map)) {
						 
						PropertyCellStyle cstyle =  map.get(propertyName);
						if(cstyle != null) {
						for(int i=0;i<cellStylesproperties.size();i++) {
							 
							CellDesignProperty cellProp = cellStylesproperties.get(i);
							 
							if(cellProp.getName().equalsIgnoreCase("Background")) {
								cellProp.setValue(cstyle.getColor(cstyle.getBackground()));
							}
							if(cellProp.getName().equalsIgnoreCase("Foreground")) {
								cellProp.setValue(cstyle.getColor(cstyle.getForeground()));
							}
							if(cellProp.getName().equalsIgnoreCase("Font")) {
								cellProp.setValue(cstyle.getFont(cstyle.getFontType()));
							}
						}
						}
						
					}
            		PropertyCellStyle styleCell = new PropertyCellStyle();
					for(int i=0;i<cellStylesproperties.size();i++) {
						
						CellDesignProperty cellProp = cellStylesproperties.get(i);
						 
						if(cellProp.getName().equalsIgnoreCase("Background")) {
							Color color = (Color)cellProp.getValue();
							backColor = color.getRed() + "," + color.getGreen() + "," + color.getBlue();
							styleCell.setBackground(backColor);
						}
						if(cellProp.getName().equalsIgnoreCase("Foreground")) {
							Color color = (Color)cellProp.getValue();
							foreColor = color.getRed() + "," + color.getGreen() + "," + color.getBlue();
							styleCell.setForeground(foreColor);
						}
						if(cellProp.getName().equalsIgnoreCase("Font")) {
							Font font = (Font)cellProp.getValue();
							fontStyle = font.getName() + ","+font.getStyle()+ ","+font.getSize();
		                	if(font.isBold()) 
		                		styleCell.setFontisbold(true);
		                	if(font.isItalic()) 
		                		styleCell.setFontisitalic(true);
		                	
		                	styleCell.setFontType(fontStyle);
							
						}
						styleCell.setPropertyname(propertyName);
						map.put(propertyName, styleCell);
					}
					
						
						
						
						
					
					
				}
			});
		// TODO Auto-generated constructor stub
	}

		 
		 public void clearFieldNames() {
			 fieldNames.removeAll();
		 }
		 public void addFieldNames(DefaultListModel<String> fNames) {
			 fieldNames.setModel(fNames);
		 }
		public PropertyTable getCellStyleProrpertyTable() {
	       
			 cellStylesproperties = getCellStyleProperties();
			 
			 propertyTable = new PropertyTable();

			PropertyTableModel<CellDesignProperty> model = new PropertyTableModel<CellDesignProperty>(
					cellStylesproperties);
			model.setMiscCategoryName(" ");
			// model.addPropertyChangeListener(listener);
			propertyTable.setModel(model);
			propertyTable.getColumnModel().getColumn(0).setMinWidth(100);
			propertyTable.expandAll();
			propertyTable.addPropertyChangeListener(this);
			//propertyTable.addPropertyChangeListener("Exchange", this);
			return propertyTable;
	    }

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			// TODO Auto-generated method stub
			
		}
		private List<CellDesignProperty> getCellStyleProperties() {

			List<CellDesignProperty> properties = null;

			properties =  CellStylePropertyEnumList.DESIGN_PROPERTY
				.getCellDesignPropertyList();
				return properties;

		
		}
}
