import java.awt.Color;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import util.commonUTIL;
import beans.LegalEntity;
import beans.StartUPData;
import dsServices.RemoteReferenceData;
import dsServices.ServerConnectionUtil;

 
public class CounterPartyWindow extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JTextField jTextField0;
	private JLabel jLabel3;
	private JTextField jTextField1;
	private JLabel jLabel2;
	private JComboBox roleComboBox;
	private JTextField nameField;
	private JLabel jLabel4;
	private JLabel jLabel1;
	private JTextField jTextField3;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private JPanel jPanel0;
	private JPanel jPanel1;
	private JButton saveButton;
	private JButton saveAsNew;
	private JButton deleteButton;
	private JButton searchButton;
	private JPanel jPanel2;
	private JTable jTable1;
	private JScrollPane jScrollPane1;
	public static  ServerConnectionUtil de = null;
	RemoteReferenceData remoteBORef;
	public  DefaultTableModel model = null;
	  String col[] = {"CP ID", "Name ", "Role", "Status"};
	public CounterPartyWindow() {
	//	init();
		initComponents();
	}

	private void init() {
		// TODO Auto-generated method stub
		Vector roles = null;
   	 de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP() );
   	 try {
   		 remoteBORef = (RemoteReferenceData) de.getRMIService("ReferenceData");
			  roles = (Vector) remoteBORef.getStartUPData("Roles");
			System.out.println(remoteBORef);
			if(roles != null) {
	        	Iterator its = roles.iterator();
	        	while(its.hasNext()) {
	        		StartUPData role = (StartUPData) its.next();
	        		roleComboBox.addItem(role.getName());
	        	}
	        }
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void initComponents() {
		setBorder(new LineBorder(Color.black, 1, false));
		setLayout(new GroupLayout());
		add(getJPanel1(), new Constraints(new Bilateral(12, 16, 860), new Bilateral(212, 71, 10)));
		add(getJPanel0(), new Constraints(new Bilateral(10, 14, 862), new Leading(5, 203, 10, 10)));
		add(getJPanel2(), new Constraints(new Bilateral(12, 16, 860), new Trailing(15, 10, 505)));
		setSize(890, 574);
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTable1());
		}
		return jScrollPane1;
	}

	private JTable getJTable1() {
		if (jTable1 == null) {
			jTable1 = new JTable();
			jTable1.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
		}jTable1.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				int rowindex = jTable1.getSelectedRow(); 
				TableModel model = jTable1.getModel();
				Integer ss = (Integer) model.getValueAt(rowindex, 0);
				LegalEntity le;
				
					le = (LegalEntity) remoteBORef.selectLE(ss.intValue());
				
				
			//	jTextField4.setText(new Integer(book.getPrice()).toString());
				
				for(int i=0;i<roleComboBox.getItemCount();i++) {
					 String role = (String) roleComboBox.getItemAt(i);
					 if(role.equalsIgnoreCase(le.getRole())) {
						 roleComboBox.setSelectedIndex(i);
					 }
					
				}
				nameField.setText(le.getName());
				jTextField1.setText(new Integer(le.getId()).toString());
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			
        
        	
        });
		return jTable1;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(new LineBorder(Color.black, 1, false));
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getSaveButton(), new Constraints(new Leading(266, 10, 10), new Leading(12, 12, 12)));
			jPanel2.add(getSaveAsNewButton(), new Constraints(new Leading(146, 12, 12), new Leading(12, 12, 12)));
			jPanel2.add(getDeleteButton(), new Constraints(new Leading(344, 10, 10), new Leading(12, 12, 12)));
			jPanel2.add(getSearchButton(), new Constraints(new Leading(434, 10, 10), new Leading(12, 12, 12)));
		}
		return jPanel2;
	}

	private JButton getSearchButton() {
		if (searchButton == null) {
			searchButton = new JButton();
			searchButton.setText("Search");
		}
		return searchButton;
	}

	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton();
			deleteButton.setText("Delete");
		}deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	 int rowindex = jTable1.getSelectedRow(); 
                 model.removeRow(rowindex);
                 jTable1.repaint();
                 clearData();
            }

			
        }); 
		return deleteButton;
	}

	private JButton getSaveAsNewButton() {
		if (saveAsNew == null) {
			saveAsNew = new JButton();
			saveAsNew.setText("Save As New");
		}saveAsNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	LegalEntity le = new LegalEntity();
            	
            	String leName = nameField.getText();
            	String role = roleComboBox.getSelectedItem().toString();
            	
          	  le.setName(leName);
          	  le.setRole(role);
          	  boolean flag = false;
          	  
          	  if ( checkLE(leName, role) ) {
          		  
          	  
          		  try {
          			  int id = remoteBORef.saveLe(le);
    
                //String mess =  process.newRecordProcess(book);
	                if(id <= 0) {
	
	                	commonUTIL.showAlertMessage("not saved ");
	             	} else {
	               		TableModel mo = jTable1.getModel();
	               		le.setId(id);
	               		model.insertRow(mo.getRowCount(), new Object[]{le.getId(),le.getName(),le.getRole()});
	               		repaint();
	               		commonUTIL.showAlertMessage("Legal Entity  saved with id " + le.getId());
	               	}
	          	  } catch (RemoteException e) {
	        			// TODO Auto-generated catch block
	        			e.printStackTrace();
	        		}
	          	  
          	  }
           }

			
       }); 
		return saveAsNew;
	}
	
	private boolean checkLE( String leName, String role ) {
		
		return true;
	}
	private JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton();
			saveButton.setText("Save");
		}saveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	 try {
            	    	LegalEntity le = new LegalEntity();
            	    	le.setId(checkNumberFormat(" ID ", jTextField1.getText().trim()));
            	  	  le.setName(nameField.getText());
            	  	  le.setRole(roleComboBox.getSelectedItem().toString());
            	  	  le.setStatus("");
            	       boolean falg = false;
            	       falg = remoteBORef.updateLe(le);
            		
            	      //  
            	        	
            	          //String mess =  process.editRecordProcess(book);
            	          if(!falg) {

            	        		commonUTIL.showAlertMessage("There was a problem while updating Legal Entity");
            	        	} else {
            	         		int rowindex = jTable1.getSelectedRow();
            	         		model.setValueAt(le.getName(), rowindex, 1);
            	         		model.setValueAt(le.getRole(), rowindex, 3);
            	         		//model.setValueAt(le.getId(), rowindex, 1);
            	         		commonUTIL.showAlertMessage("Legal Entity Updated ");
            	         		jTable1.repaint();
            	         	}
            	    	 } catch (RemoteException e) {
            	    			// TODO Auto-generated catch block
            	    			e.printStackTrace();
            	    		}
            	    }
              
		 });   
		return saveButton;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(new LineBorder(Color.black, 1, false));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJScrollPane1(), new Constraints(new Bilateral(6, 7, 845), new Bilateral(3, 6, 10, 278)));
		}
		return jPanel1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(new LineBorder(Color.black, 1, false));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJScrollPane0(), new Constraints(new Bilateral(411, 5, 444), new Leading(6, 180, 12, 12)));
			jPanel0.add(getJLabel3(), new Constraints(new Leading(14, 57, 33, 464), new Trailing(12, 24, 146, 146)));
			jPanel0.add(getJTextField0(), new Constraints(new Leading(90, 204, 33, 464), new Trailing(12, 28, 148, 151)));
			jPanel0.add(getJLabel2(), new Constraints(new Leading(14, 48, 33, 464), new Trailing(52, 103, 103)));
			jPanel0.add(getJComboBox0(), new Constraints(new Leading(90, 205, 33, 464), new Trailing(48, 28, 104, 112)));
			jPanel0.add(getJLabel1(), new Constraints(new Leading(14, 33, 464), new Leading(85, 80, 80)));
			jPanel0.add(getJTextField2(), new Constraints(new Leading(90, 266, 33, 464), new Leading(84, 28, 85, 88)));
			jPanel0.add(getJLabel4(), new Constraints(new Leading(14, 33, 33, 464), new Leading(57, 80, 80)));
			jPanel0.add(getJTextField3(), new Constraints(new Leading(90, 72, 33, 464), new Leading(47, 28, 85, 88)));
			jPanel0.add(getJLabel0(), new Constraints(new Leading(14, 28, 10, 10), new Leading(16, 21, 10, 10)));
			jPanel0.add(getJTextField1(), new Constraints(new Leading(90, 68, 33, 464), new Leading(8, 28, 85, 88)));
		}
		return jPanel0;
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
			jTable0.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
			
			
		}
		return jTable0;
	}

	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setText("jTextField3");
		}
		return jTextField3;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("Name");
		}
		return jLabel1;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("Alias");
		}
		return jLabel4;
	}

	private JTextField getJTextField2() {
		if (nameField == null) {
			nameField = new JTextField();
			nameField.setText("nameField");
		}
		return nameField;
	}

	private JComboBox getJComboBox0() {
		if (roleComboBox == null) {
			roleComboBox = new JComboBox();
			roleComboBox.setModel(new DefaultComboBoxModel(new Object[] { "item0", "item1", "item2", "item3" }));
			roleComboBox.setDoubleBuffered(false);
			roleComboBox.setBorder(null);
		}
		return roleComboBox;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("Role");
		}
		return jLabel2;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		//	jTextField0.setText("jTextField1");
		}
		return jTextField0;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("Contact");
		}
		return jLabel3;
	}

	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setText("jTextField0");
		}
		return jTextField1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("ID");
		}
		return jLabel0;
	}
	public static int checkNumberFormat(String fieldName, String s) {
    	try {
    	Integer ii = new Integer(s);
    	return ii.intValue();
    	}catch(Exception e) {
    		commonUTIL.showAlertMessage(" enter number ");
    		return 0;
    	}
    }
    private void clearData() {
    	roleComboBox.removeAll();
		jTextField3.removeAll();
		nameField.setText("");
		jTextField1.setText("");
    	repaint();
    	//processTableData(model);
    }
    
    
    private void processTableData(DefaultTableModel model) {
		// TODO Auto-generated method stub
    	Vector vector;
		try {
			vector = (Vector) remoteBORef.selectAllLs();
			Iterator it = vector.iterator();
	    	int i =0;
	    	while(it.hasNext()) {
	    		
	    		LegalEntity le = (LegalEntity) it.next();
	    	
	    		model.insertRow(i, new Object[]{le.getId(),le.getName(),le.getRole()});
	    		i++;
	    		}
	    		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    		
    	
    	
		
	}
}
