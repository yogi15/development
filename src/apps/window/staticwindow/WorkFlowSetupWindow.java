package apps.window.staticwindow;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.plaf.ActionMapUIResource;
import javax.swing.table.AbstractTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import util.commonUTIL;

import apps.window.util.windowUtil.JDialogBoxForChoice;
import beans.Folder;
import beans.LegalEntity;
import beans.StartUPData;
import beans.Users;
import beans.WFConfig;
import dsServices.RemoteReferenceData;
import dsServices.ServerConnectionUtil;

//VS4E -- DO NOT REMOVE THIS LINE!
public class WorkFlowSetupWindow extends BasePanel {

	private static final long serialVersionUID = 1L;
	ActionMap actionMap =null;
	DefaultMutableTreeNode root;
	 DefaultMutableTreeNode parent, leaf;
	  public static DefaultMutableTreeNode nNode;
	  Vector copyData = new Vector();
	  Hashtable copyD = new Hashtable();
	    public static MutableTreeNode mNode;
	   Hashtable selectTransitions = new Hashtable<String, Vector>();
	    DefaultTreeModel       _treeModel;
	    DefaultMutableTreeNode _root;
	    DefaultMutableTreeNode _productName  = null;
	    DefaultMutableTreeNode _productSubName = null;
	    public static  ServerConnectionUtil de = null;
	 //   DefaultTableModel tmodel;
		 RemoteReferenceData remoteBORef;
		 TableModelUtil model;
		 String col[] = {"Id","Current status", "Action ", "New Status","Group","Auto","Rules","Type","Task","Diff User"};
		 String type = ""; 
		 Vector data = null;
		 Vector tradeRules = new Vector();
		 Vector transferRules = new Vector();
		 javax.swing.DefaultComboBoxModel  payRec = new javax.swing.DefaultComboBoxModel();
	private JPanel jPanel1;
	private JPanel jPanel0;
	private JPanel jPanel3;
	private JPanel jPanel2;
	private JTree jTree0;
	private JScrollPane jScrollPane0;
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JLabel jLabel0;
	private JComboBox jComboBox0;
	private JLabel jLabel3;
	private JComboBox jComboBox2;
	private JTextField jTextField0;
	private JComboBox jComboBox4;
	private JLabel jLabel4;
	private JCheckBox jCheckBox0;
	private JButton jButton4;
	private JComboBox jComboBox1;
	private JComboBox jComboBox3;
	private JLabel jLabel2;
	private JLabel jLabel5;
	private JLabel jLabel1;
	private JTable jTable0;
	private JScrollPane jScrollPane1;
	DefaultComboBoxModel  action = new javax.swing.DefaultComboBoxModel();
	 DefaultComboBoxModel  newStatus = new javax.swing.DefaultComboBoxModel();
	 DefaultComboBoxModel  orgStatus = new javax.swing.DefaultComboBoxModel();
	 DefaultComboBoxModel  groups = new javax.swing.DefaultComboBoxModel();
     DefaultComboBoxModel  types = new javax.swing.DefaultComboBoxModel();
     DefaultListModel Ruleemodel ;
     
     DefaultListModel tradeRuleemodel = new DefaultListModel();
     DefaultListModel messageRuleemodel = new DefaultListModel();
 	DefaultListModel transferRuleemodel = new DefaultListModel();
	private JCheckBox jCheckBox1;
	JDialogBoxForChoice choice12 = null;
	private JCheckBox diffUser;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public WorkFlowSetupWindow() {
		init();
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel1(), new Constraints(new Bilateral(178, 12, 0), new Leading(14, 100, 10, 10)));
		add(getJPanel2(), new Constraints(new Leading(178, 909, 12, 12), new Leading(431, 51, 10, 10)));
		add(getJPanel3(), new Constraints(new Bilateral(181, 16, 860), new Leading(124, 297, 10, 10)));
		add(getJPanel0(), new Constraints(new Leading(12, 151, 10, 10), new Bilateral(12, 12, 492)));
		setSize(1109, 526);
		
	}

	private JCheckBox getJCheckBox2() {
		if (diffUser == null) {
			diffUser = new JCheckBox();
			diffUser.setSelected(false);
			diffUser.setText("Diff User");
		}
		return diffUser;
	}

	private JCheckBox getJCheckBox1() {
		if (jCheckBox1 == null) {
			jCheckBox1 = new JCheckBox();
			jCheckBox1.setText("Create Task");
		}
		return jCheckBox1;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTable0());
		}
		return jScrollPane1;
	}

	private JTable getJTable0() {
		if (jTable0 == null) {
			jTable0 = new JTable();
		
		}jTable0.addMouseListener(new java.awt.event.MouseAdapter() {

	  		@Override
	  		public void mouseClicked(MouseEvent e) {
	  			jTextField0.setText("");
	  			int i=jTable0.getSelectedRow();
	  			
	  			if(i >= 0 ) {
	  			
	  				jCheckBox0.setSelected(false);
	  				jCheckBox1.setSelected(false);
	  				jComboBox0.setSelectedItem(jTable0.getValueAt(i, 1).toString()); // for org status combox
	  				jComboBox1.setSelectedItem(jTable0.getValueAt(i, 2).toString()); // for action combox
		  			jComboBox2.setSelectedItem(jTable0.getValueAt(i, 3).toString()); // for newStatus combox
		  			jComboBox3.setSelectedItem(jTable0.getValueAt(i, 4).toString()); // for Group 
		  			jComboBox4.setSelectedItem(jTable0.getValueAt(i, 7).toString()); // Type
		  			
		  			if(jTable0.getValueAt(i, 6) != null)
		  				jTextField0.setText(jTable0.getValueAt(i, 6).toString());
		  			else 
		  				jTextField0.setText("");
	  			
		  			int a = ((Integer) jTable0.getValueAt(i, 5)).intValue();
		  			int auto = new Integer(a).intValue();
		  			
		  			if(auto == 0) {
		  				jCheckBox0.setSelected(false);
		  			} else {
		  				jCheckBox0.setSelected(true);
		  			}
		  			
		  			if(jTable0.getValueAt(i, 8).toString().equalsIgnoreCase("1")) {
		  				jCheckBox1.setSelected(true);
			        } else {
	                	if(jCheckBox1.isSelected()) 
	                	    jCheckBox1.setSelected(false);
	                 	}
		  			}
	  			if(jTable0.getValueAt(i, 9).toString().equalsIgnoreCase("1")) {
	  				diffUser.setSelected(true);
		        } else {
                	if(diffUser.isSelected()) 
                		diffUser.setSelected(false);
                 	}
	  			
	  		//	if(jTable0.getValueAt(i, 8).toString())
	  		}

	      });
		return jTable0;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJLabel5(), new Constraints(new Leading(323, 42, 12, 12), new Leading(54, 12, 12)));
			jPanel1.add(getJCheckBox1(), new Constraints(new Leading(494, 10, 10), new Leading(51, 7, 7)));
			jPanel1.add(getJComboBox3(), new Constraints(new Leading(377, 107, 10, 10), new Leading(45, 28, 12, 12)));
			jPanel1.add(getJComboBox4(), new Constraints(new Trailing(12, 176, 689, 689), new Leading(51, 28, 12, 12)));
			jPanel1.add(getJComboBox2(), new Constraints(new Leading(732, 176, 10, 10), new Leading(8, 28, 12, 12)));
			jPanel1.add(getJLabel3(), new Constraints(new Leading(609, 12, 12), new Leading(12, 12, 12)));
			jPanel1.add(getJLabel4(), new Constraints(new Trailing(197, 603, 603), new Leading(54, 12, 12)));
			jPanel1.add(getJComboBox1(), new Constraints(new Leading(411, 176, 10, 10), new Leading(8, 28, 12, 12)));
			jPanel1.add(getJLabel2(), new Constraints(new Leading(337, 47, 10, 10), new Leading(14, 12, 12, 12)));
			jPanel1.add(getJLabel0(), new Constraints(new Leading(0, 107, 10, 10), new Leading(12, 12, 12)));
			jPanel1.add(getJComboBox0(), new Constraints(new Leading(110, 176, 10, 10), new Leading(6, 28, 12, 12)));
			jPanel1.add(getJButton4(), new Constraints(new Leading(289, 32, 10, 10), new Leading(48, 12, 12)));
			jPanel1.add(getJTextField0(), new Constraints(new Leading(107, 176, 238, 238), new Leading(46, 28, 12, 12)));
			jPanel1.add(getJCheckBox0(), new Constraints(new Leading(-2, 10, 10), new Leading(48, 8, 8)));
			jPanel1.add(getJLabel1(), new Constraints(new Leading(71, 30, 238, 238), new Leading(51, 12, 12)));
			jPanel1.add(getJCheckBox2(), new Constraints(new Leading(601, 97, 10, 10), new Leading(51, 12, 12)));
		}
		return jPanel1;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setText("RULE");
		}
		return jLabel1;
	}

	private JLabel getJLabel5() {
		if (jLabel5 == null) {
			jLabel5 = new JLabel();
			jLabel5.setText("GROUP");
		}
		return jLabel5;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setText("ACTION");
		}
		return jLabel2;
	}

	private JComboBox getJComboBox3() {
		if (jComboBox3 == null) {
			jComboBox3 = new JComboBox();
			jComboBox3.setModel(groups);
		}
		return jComboBox3;
	}

	private JComboBox getJComboBox1() {
		if (jComboBox1 == null) {
			jComboBox1 = new JComboBox();
			jComboBox1.setModel(action);
		}
		return jComboBox1;
	}
	
	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setText("jButton4");
		}jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	String type = jComboBox4.getSelectedItem().toString();
            	if(commonUTIL.isEmpty(type))
            		return;
            	if(type.equalsIgnoreCase("TRADE")) {
            	choice12 =	new JDialogBoxForChoice(tradeRuleemodel);
            		choice12.jList3.setModel(tradeRuleemodel);
    				choice12.setLocationRelativeTo(choice12);
    				//choice12.setSize(200,200);
    				choice12.setVisible(true);
				} 
            	if(type.equalsIgnoreCase("MESSAGE")) {
                	choice12 =	new JDialogBoxForChoice(messageRuleemodel);
                		choice12.jList3.setModel(messageRuleemodel);
        				choice12.setLocationRelativeTo(choice12);
        				//choice12.setSize(200,200);
        				choice12.setVisible(true);
    				}else {
					choice12 =	new JDialogBoxForChoice(transferRuleemodel);
					choice12.jList3.setModel(transferRuleemodel);
    				choice12.setLocationRelativeTo(choice12);
    				//choice12.setSize(200,200);
    				choice12.setVisible(true);
				}
            	
            
            
            choice12.addWindowListener(new WindowAdapter() {            
    	        public void windowClosing(WindowEvent e) {
    	           // System.out.println("Window closing");
    	            try {
    	            	String ss = "";
    	              Object obj [] =   choice12.getObj();
    	             for(int i =0;i<obj.length;i++)
    	            	 ss = ss + (String) obj[i] + ",";
    	             if(ss.trim().length() > 0)
    	            	 jTextField0.setText(ss.substring(0, ss.length()-1));
    	            } catch (Throwable t) {
    	                t.printStackTrace();
    	            }                
    	        }
    		});
            }
		});
		return jButton4;
	}
	
	

	private JCheckBox getJCheckBox0() {
		if (jCheckBox0 == null) {
			jCheckBox0 = new JCheckBox();
			jCheckBox0.setText("AUTO");
		}
		return jCheckBox0;
	}

	private JLabel getJLabel4() {
		if (jLabel4 == null) {
			jLabel4 = new JLabel();
			jLabel4.setText("TYPE");
		}
		return jLabel4;
	}

	private JComboBox getJComboBox4() {
		if (jComboBox4 == null) {
			jComboBox4 = new JComboBox();
			jComboBox4.setModel(types);
		}jComboBox4.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				String type = jComboBox4.getSelectedItem().toString();
				
				
			}
		});
		return jComboBox4;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
		//	jTextField0.setText("jTextField0");
		}
		return jTextField0;
	}

	private JComboBox getJComboBox2() {
		if (jComboBox2 == null) {
			jComboBox2 = new JComboBox();
			jComboBox2.setModel(newStatus);
		}
		return jComboBox2;
	}

	private JLabel getJLabel3() {
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText("RESULTING STATUS");
		}
		return jLabel3;
	}

	private JComboBox getJComboBox0() {
		if (jComboBox0 == null) {
			jComboBox0 = new JComboBox();
			jComboBox0.setModel(orgStatus);
		}
		return jComboBox0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("ORGINAL STATUS");
		}
		return jLabel0;
	}

	private JButton getJButton3() {
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("DELETE");
		}jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	int i=jTable0.getSelectedRow();
      			if(i >= 0 ) {
            	int r = ((Integer) jTable0.getValueAt(jTable0.getSelectedRow(), 0)).intValue();
            	WFConfig config = new WFConfig();
            	config.setId(r);
            	try {
					if(remoteBORef.removeWF(config)) {
						model.delRow(jTable0.getSelectedRow());
						//tmodel.removeRow(jTable1.getSelectedRow());
						//jTable1.setModel(tmodel);
					}
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
      			}
            }
        });
		return jButton3;
	}

	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("SAVE AS NEW");
		}jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                WFConfig config = new WFConfig();
                config.setProductType(_productName.toString());     // this will get Tree node selected  as product
                config.setProductSubType(_productSubName.toString());  // this will get Tree node selected  as productSubType
               if( jCheckBox0.isSelected())
                config.setAuto(1);
               else 
            	   config.setAuto(0);
               if( jCheckBox1.isSelected())
                   config.setTask(true);
               if( diffUser.isSelected())
                   config.setDiffUser(1);
               if(jComboBox0.getSelectedItem() == null) {
               	commonUTIL.showAlertMessage("Select Orginal Status on Transition");
               	return;
               }
                config.setCurrentStatus(jComboBox0.getSelectedItem().toString()); // for org status
                if(jComboBox1.getSelectedItem() == null) {
                   	commonUTIL.showAlertMessage("Select Action on Transition");
                   	return;
                   }
                config.setAction(jComboBox1.getSelectedItem().toString()); // for action
                if(jComboBox2.getSelectedItem() == null) {
                   	commonUTIL.showAlertMessage("Select new status on Transition");
                   	return;
                   }
                config.setOrgStatus(jComboBox2.getSelectedItem().toString());  // for new status
                config.setRule(jTextField0.getText());  // for rule 
                config.setLe(0);
                config.setUsers(0);
                if(jComboBox4.getSelectedItem() == null) {
                	commonUTIL.showAlertMessage("Select Type of Transition");
                	return;
                }
                String groupName = jComboBox3.getSelectedItem().toString();
				if(!commonUTIL.isEmpty(groupName))  {
					config.setGroupName(groupName);
				} else {
					commonUTIL.showAlertMessage("Select Group please");
					return;
				}
                config.setType(jComboBox4.getSelectedItem().toString());
                try {
                	if(!checkAllTableValues()) {
                		JOptionPane.showMessageDialog(null,"Transition Already Exists ",null,
                  				JOptionPane.INFORMATION_MESSAGE);
                		return;
                	} if(!checkReverTableValues())  {
                		commonUTIL.showAlertMessage("Reverse Transition not Allowed");
                		return;
                	}
                	else {
                		if(config.getAuto() == 1 && (config.getCurrentStatus().equalsIgnoreCase(config.getOrgStatus()))) {
                			commonUTIL.showAlertMessage("STP not allowed on same Transitions with old and new status");
                			return;
                		}
					int i = remoteBORef.saveWF(config);
					if(i == -1)  {
						commonUTIL.showAlertMessage("Reverse Transition not allowed ");
						return;
					}
					config.setId(i);
					
					model.addRow(config);
				//	filltable(config,tmodel.getRowCount());
                	}
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            }

			
        });
		return jButton2;
	}
	 public boolean checkReverTableValues() {
		   boolean flag = true;
		   for(int i=0;i<jTable0.getModel().getRowCount();i++) {
			   String orgstatus =  (String) jTable0.getValueAt(i, 1);
			   String action =  (String) jTable0.getValueAt(i, 2);
			   String newstatus =  (String) jTable0.getValueAt(i, 3);
			   String newType = (String) jTable0.getValueAt(i, 7);
			   if(!orgstatus.equalsIgnoreCase(newstatus))
			   if(checkValidation(jComboBox2,orgstatus)  && checkValidation(jComboBox0,newstatus) && jCheckBox0.isSelected() )
				flag = false;
			   
		   }
		   return flag;
	   }
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("SAVE");
		} jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	int i=jTable0.getSelectedRow();
      			if(i >= 0 ) {
      				
      				
      				WFConfig config = new WFConfig();
      				config.setId(new Integer(jTable0.getValueAt(i, 0).toString()).intValue());
                    config.setProductType(_productName.toString());     // this will get Tree node selected  as product
                    config.setProductSubType(_productSubName.toString());  // this will get Tree node selected  as productSubType
                    		if( jCheckBox0.isSelected())
                    				config.setAuto(1);
                    		else 
                    			config.setAuto(0);
                    		if( jCheckBox1.isSelected())
                				config.setTask(true);
                		else 
                			config.setTask(false);
                    		if( diffUser.isSelected())
                				config.setDiffUser(1);
                		else 
                			config.setDiffUser(0);
                    		if(jComboBox0.getSelectedItem() == null) {
                    				commonUTIL.showAlertMessage("Select Orginal Status on Transition");
                    				return;
                    		}
                    		config.setCurrentStatus(jComboBox0.getSelectedItem().toString()); // for org status
                    		if(jComboBox1.getSelectedItem() == null) {
                    			commonUTIL.showAlertMessage("Select Action on Transition");
                    			return;
                    		}
                    		config.setAction(jComboBox1.getSelectedItem().toString()); // for action
                    		if(jComboBox2.getSelectedItem() == null) {
                    			commonUTIL.showAlertMessage("Select new status on Transition");
                    			return;
                    		}
                    		config.setOrgStatus(jComboBox2.getSelectedItem().toString());  // for new status
                    		config.setRule(jTextField0.getText());  // for rule 
                    		config.setLe(0);
                    		config.setUsers(0);
                    		if(jComboBox4.getSelectedItem() == null) {
                    			commonUTIL.showAlertMessage("Select Type of Transition");
                    			return;
                    		}
                    	
                    		config.setType(jComboBox4.getSelectedItem().toString());
                    		if(jComboBox3.getSelectedIndex() == -1)  {
                    			commonUTIL.showAlertMessage("Select Group");
                    			return;
                    		}
                    			
                    		config.setGroupName(jComboBox3.getSelectedItem().toString());
                    		try {
                    			if(!checkAllTableValues(i)) {
                    				JOptionPane.showMessageDialog(null,"Transition Already Exists ",null,
                      				JOptionPane.INFORMATION_MESSAGE);
                    				return;
                    			} if(!checkReverTableValues())  {
                    					commonUTIL.showAlertMessage("Reverse Transition not Allowed");
                    					return;
                    			}
                    	else {
                    		if(config.getAuto() == 1 && (config.getCurrentStatus().equalsIgnoreCase(config.getOrgStatus()))) {
                    			commonUTIL.showAlertMessage("STP not allowed on same Transitions with old and new status");
                    			return;
                    		}
                    		boolean flag = remoteBORef.updateWFconfig(config);
    					if(i == -1)  {
    						commonUTIL.showAlertMessage("Reverse Transition not allowed ");
    						return;
    					}
    					if(flag) {
    						commonUTIL.showAlertMessage("Transition Updated");
    					config.setId(i);
    					model.udpateValueAt(config, jTable0.getSelectedRow(),  jTable0.getSelectedColumn());
                    	}
                    	}
      	  
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				commonUTIL.displayError("WorkFlowSetupWindown", "UpdateMehtod", e);
			}
      	  			}
            }
        });
		return jButton1;
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("NEW");
		}
		return jButton0;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTree0());
		}
		return jScrollPane0;
	}

	private JTree getJTree0() {
		if (jTree0 == null) {
			jTree0 = new JTree();
			
		}
		buildTree();
		jTree0.addMouseListener(new java.awt.event.MouseAdapter() {
    	 	JPopupMenu popupMenu = new JPopupMenu();
			@Override
			public void mouseClicked(MouseEvent e) {
				popupMenu.removeAll();
				 JMenuItem menuCopy = new JMenuItem("COPY");
			      menuCopy.addActionListener(new ActionListener() {
				        public void actionPerformed(ActionEvent arg0) {
				        	 String action = arg0.getActionCommand().toString();
				        	
				        	 String sql = " productType = '" + _productName.toString() + "' and productSubType = '" + _productSubName.toString() + "' and type ='"+type+"'";
								Vector selectData = (Vector) selectTransitions.get(sql);
								if(selectData != null && (!selectData.isEmpty())) {
								  copyData = selectData;
								  copyD.put("COPY", copyData);
								}
				        	 
				        }
			});	
			      JMenuItem menuPaste = new JMenuItem("PASTE");
			      menuPaste.addActionListener(new ActionListener() {
				        public void actionPerformed(ActionEvent arg0) {
				        	 String _prodType =_productName.toString();
				        	 String _prodSubType = _productSubName.toString();
				        	 String _pType = type;
				        	 String sql = " productType = '" + _prodType + "' and productSubType = '" +_prodSubType + "' and type ='"+_pType+"'";
				        	 Vector dataCopied = (Vector) copyD.get("COPY");
				        	 Vector<WFConfig> datatoCopied = new Vector<WFConfig>();
				        	 for(int i=0;i<dataCopied.size();i++) {
				        		 WFConfig wf = (WFConfig) dataCopied.get(i);
				        		 wf.setId(0);
				        		 wf.setProductSubType(_prodSubType);
				        		 wf.setProductType(_prodType);
				        		 wf.setType(_pType);
				        		 datatoCopied.addElement(wf);
				        	 }
				        	 
				        	 fillnewData(datatoCopied);
				        	 selectTransitions.put(sql, datatoCopied);
				        	 copyD.clear();
				        }

						private void fillnewData(Vector<WFConfig> datatoCopied) {
							// TODO Auto-generated method stub
							int id = 0;
							Vector<WFConfig> toshowTable = new Vector<WFConfig>();
							if(!datatoCopied.isEmpty())
								for(int i=0;i<datatoCopied.size();i++) {
							      try {
							    	  WFConfig wf = datatoCopied.get(i);
									id = remoteBORef.saveWF(wf);
									wf.setId(id);
									toshowTable.addElement(wf);
								} catch (RemoteException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								}
							model.removeALL();
							 data = toshowTable;
							 model = new TableModelUtil(toshowTable, col, remoteBORef);
							 jTable0.setModel(model);
							 popupMenu.removeAll();
						}
			});	  
			      
				if(SwingUtilities.isRightMouseButton(e) == true) 					{
					
					Vector copyData =  (Vector) copyD.get("COPY");
					if(copyData == null) { 
						String sql = " productType = '" + _productName.toString() + "' and productSubType = '" + _productSubName.toString() + "' and type ='"+type+"'";
						Vector selectData = (Vector) selectTransitions.get(sql);
						if(selectData != null && (!selectData.isEmpty())) {
						popupMenu.removeAll();
						popupMenu.add(menuCopy);
					  popupMenu.show(e.getComponent(), e.getX(), e.getY());	
						}
					}
					if(copyData != null) { 
						 String sql = " productType = '" + _productName.toString() + "' and productSubType = '" + _productSubName.toString() + "' and type ='"+type+"'";
							Vector selectData = (Vector) selectTransitions.get(sql);
							if(selectData != null && (!selectData.isEmpty())) {
							  copyData = selectData;
							  copyD.put("COPY", copyData);
							  popupMenu.removeAll();
								popupMenu.add(menuCopy);
							   popupMenu.show(e.getComponent(), e.getX(), e.getY());
							}else {
						popupMenu.removeAll();
						popupMenu.add(menuPaste);
					   popupMenu.show(e.getComponent(), e.getX(), e.getY());	
							}
					}
				}
			
		}
	});
		
		
		
      jTree0.addTreeSelectionListener(new TreeSelectionListener() {
    	
    		
			@Override
			public void valueChanged(TreeSelectionEvent e){
				// TODO Auto-generated method stub
			
			
			    TreePath selPath = e.getNewLeadSelectionPath();
			 //  System.out.println( selPath.getPathCount());
			    if (selPath == null) return;
			    String productType  = "";
			    String productSubType = "";
			    
			 
			    if (selPath.getPathCount() > 2) {
			    	type =  ((DefaultMutableTreeNode)selPath.getPathComponent(1)).toString();
			    	_productName =
			        (DefaultMutableTreeNode)selPath.getPathComponent(2);
			        productType = _productName.toString();
			        if (selPath.getPathCount() > 3) {
			        	_productSubName =
			            (DefaultMutableTreeNode)selPath.getPathComponent(3);
			        	if(_productSubName == null)
			        		return;
			        productSubType = _productSubName.toString();
			        }
			    }
			    
				
				System.out.println(productType + "  " + productSubType);
				  jTable0.removeAll();
				  String col[] = {"Id","Current status", "Action ", "New Status","Group","Auto","Rules","Type"};
			    //    tmodel = new DefaultTableModel (col,0);
				selectProductWF(productType,productSubType,type);
			//	jTable1.setModel(tmodel);
			
      	
      	
      	
			   }});
      
		return jTree0;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getJButton0(), new Constraints(new Leading(20, 10, 10), new Leading(22, 10, 10)));
			jPanel2.add(getJButton1(), new Constraints(new Leading(89, 73, 10, 10), new Leading(22, 10, 10)));
			jPanel2.add(getJButton2(), new Constraints(new Leading(180, 101, 10, 10), new Leading(22, 10, 10)));
			jPanel2.add(getJButton3(), new Constraints(new Leading(293, 73, 10, 10), new Leading(22, 10, 10)));
		}
		return jPanel2;
	}

	private JPanel getJPanel3() {
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
			jPanel3.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel3.setLayout(new GroupLayout());
			jPanel3.add(getJScrollPane1(), new Constraints(new Bilateral(2, 0, 23), new Leading(4, 268, 10, 10)));
		}
		return jPanel3;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJScrollPane0(), new Constraints(new Leading(1, 136, 10, 10), new Leading(0, 459, 12, 12)));
		}
		return jPanel0;
	}

	public void init() {
		 de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP());
	   	 try {
	   		 remoteBORef = (RemoteReferenceData) de.getRMIService("ReferenceData");
	   		 getDataOnComboBox(orgStatus, "Status");
	   		 getDataOnComboBox(action, "Action");
	   		 getDataOnComboBox(newStatus, "Status");
	   		
	        getDataOnComboBox(groups, "UserGroup");
	        getDataOnComboBox(types,"WFType");
	        getRulesDataCombo1();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	   	actionMap = new ActionMapUIResource();
	    actionMap.put("action_save", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	if( jTable0.getSelectedRowCount() !=-1)  {
	        		 
	        	  //   buildWFConfig(wfconfig,"save");
	        	   
	        }
	        }
	    });
	    actionMap.put("action_new", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	//commonUTIL.showAlertMessage("New action performed.");
	        	 
	        	// buildWFConfig(wfconfig,"NEW");
	        }
	    });
	    actionMap.put("action_del", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	if( jTable0.getSelectedRowCount() !=-1)  {
	        	 
	        		// buildWFConfig(wfconfig,"Delete");
	        	}
	        }
	    });
	    actionMap.put("action_saveasnew", new AbstractAction() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	//commonUTIL.showAlertMessage("Save As New action performed.");
	         
	        	// buildWFConfig(wfconfig,"saveAsNew");
	        	 
	        //	commonUTIL.showAlertMessage("Save As new  action performed.");
	        }

			
	    });
	}
	private void buildTree() {
		 Vector v = new Vector();
		 Vector wfType = new Vector();
		// StartUPData data1 = new StartUPData();
	     //  data1.setName("ProductType");
		 DefaultMutableTreeNode type = null;
		 try {
			v = (Vector) remoteBORef.getStartUPData("ProductType");
			wfType = (Vector) remoteBORef.getStartUPData("WFType");
		   // v = util.SortShell.sort(v,ComparatorFactory.getNonSenseStringComparator());

		   root =
		        new DefaultMutableTreeNode("ProductName");
		   for(int t=0;t<wfType.size();t++) {
			    StartUPData datatype = (StartUPData)wfType.elementAt(t);
		        String domainType = datatype.getName();
		        type = new DefaultMutableTreeNode(domainType);
		        root.add(type);
		    for (int i=0; i<v.size(); i++) {
		    	StartUPData data = (StartUPData)v.elementAt(i);
		        String domain = data.getName();
		        parent = new DefaultMutableTreeNode(domain);
		        leaf = new   DefaultMutableTreeNode("ALL");
		        type.add(parent);
		        parent.add(leaf);
		        Vector values = (Vector) remoteBORef.getStartUPData(domain+".subType");
		            if (values == null)continue;
		        for(int j=0;j<values.size();j++) {
		        	
		        leaf = new DefaultMutableTreeNode(((StartUPData)values.elementAt(j)).getName());
		        parent.add(leaf);
		        }
		    } 
		   }
		    _root = root;
		    _treeModel = new DefaultTreeModel(root);
		    jTree0.setModel(_treeModel);
		
		    
		 } catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    }
		
	public void getRulesDataCombo1() {
		Vector vector;
		try {
			
				vector = (Vector) remoteBORef.getStartUPData("TradeRule");
			   Iterator it = vector.iterator();
	    	 int i =0;
	    	while(it.hasNext()) {
	    		
	    		StartUPData data = (StartUPData) it.next();
	    		tradeRuleemodel.addElement(data.getName());
	    		i++;
		}	
	    	vector = (Vector) remoteBORef.getStartUPData("TransferRule");
	 			 it = vector.iterator();
	 	    	  i = 0;
	 	    	while(it.hasNext()) {
	 	    		
	 	    		StartUPData data = (StartUPData) it.next();
		    		transferRuleemodel.addElement(data.getName());
		    		i++;
	 	    	
	 		}	
	 	    	vector = (Vector) remoteBORef.getStartUPData("MessageRule");
	 			 it = vector.iterator();
	 	    	  i = 0;
	 	    	while(it.hasNext()) {
	 	    		
	 	    		StartUPData data = (StartUPData) it.next();
		    		messageRuleemodel.addElement(data.getName());
		    		i++;
	 	    	
	 		}	
	 	    	Ruleemodel = tradeRuleemodel;
		}catch (RemoteException e) {
					// TODO Auto-generated catch block
			commonUTIL.displayError("JFrameReportApplicatoin","getLEDataCombo1", e);
				}
		
		
	}
	 private void getDataOnComboBox( javax.swing.DefaultComboBoxModel combodata,String name) {
			Vector vector;
			try {
				vector = (Vector) remoteBORef.getStartUPData(name);
				if(vector.size() > 0) {
				Iterator it = vector.iterator();
		    	int i =0;
		    	while(it.hasNext()) {
		    		
		    		StartUPData data = (StartUPData) it.next();
		    	
	   		
	   			
	   		combodata.insertElementAt(data.getName(), i);
	   		i++;
	   	}	
				}
			}catch (RemoteException e) {
	   				// TODO Auto-generated catch block
	   				e.printStackTrace();
	   			}
	   	
	   	
	   }
	// check for duplicate for transition. 
	   public boolean checkValidation(JComboBox combo,String selectValue) {
		//   System.out.println(selectValue);
		   if(combo.getSelectedItem().toString().equalsIgnoreCase(selectValue)) 
			   return true;
		   else 
			   return false;
	   }
	   public boolean checkValidationAction(JComboBox combo,String selectValue) {
		   System.out.println(selectValue);
		   if(combo.getSelectedItem().toString().equalsIgnoreCase(selectValue)) 
			   return true;
		   else 
			   return false;
	   }
	 public boolean checkAllTableValues() {
		   boolean flag = true;
		   for(int i=0;i<jTable0.getModel().getRowCount();i++) {
			   String orgstatus =  (String) jTable0.getValueAt(i, 1);
			   String action =  (String) jTable0.getValueAt(i, 2);
			   String newstatus =  (String) jTable0.getValueAt(i, 3);
			   String newType = (String) jTable0.getValueAt(i, 7);
			   if((checkValidation(jComboBox0,orgstatus) && checkValidation(jComboBox1,action)) && (checkValidation(jComboBox2,newstatus) && checkValidation(jComboBox4,newType)) )
				flag = false;
			   
		   }
		   return flag;
		   
	   }
	   public boolean checkAllTableValues(int avoidRow) {
		   boolean flag = true;
		   for(int i=0;i<jTable0.getModel().getRowCount();i++) {
			   if(i != avoidRow) {
			   String orgstatus =  (String) jTable0.getValueAt(i, 1);
			   String action =  (String) jTable0.getValueAt(i, 2);
			   String newstatus =  (String) jTable0.getValueAt(i, 3);
			   String newType = (String) jTable0.getValueAt(i, 7);
			   if(checkValidation(jComboBox0,orgstatus) && checkValidation(jComboBox1,action) && checkValidation(jComboBox2,newstatus) && checkValidation(jComboBox4,newType) )
				flag = false;
			   }
			   
		   }
		   return flag;
	   }
	   
	   
public void selectProductWF(String productType,String productSubType,String type) {
		 
		 try {
			 String sql = " productType = '" + productType + "' and productSubType = '" + productSubType + "' and type ='"+type+"'";
			 Vector v1 = null;
			v1  = (Vector) selectTransitions.get(sql);
			 if(v1 == null)
				v1  = (Vector) remoteBORef.selectWFWhere(sql);
			 
			 if(v1 == null) {
				 	 return; 
			 } else {
				 selectTransitions.put(sql,v1);
				 		 
				 	 }
			 data = v1;
			 model = new TableModelUtil(v1, col, remoteBORef);
			 jTable0.setModel(model);
			/* Iterator it = v1.iterator();;
		    	int i =0;
		    
	    	while(it.hasNext()) {
             WFConfig config = (WFConfig) it.next();
			tmodel.insertRow(i, new Object[]{config.getId(),config.getCurrentStatus(),config.getAction(),config.getOrgStatus(),"GROUP1",config.getAuto(),config.getRule(),config.getType()});
	    	}
	    	repaint(); */
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 }
	class TableModelUtil extends AbstractTableModel {   
	       
	   	 final String[] columnNames;  
	   	    
	   	 Vector<WFConfig> data;   
	   	 RemoteReferenceData remoteRef ;
	   	        
	   	 public TableModelUtil( Vector<WFConfig> myData,String col [],RemoteReferenceData remoteRef ) {   
	   	 	this.columnNames = col;
	   	this.data = myData;   
	   	this.remoteRef = remoteRef;
	   	}   

	   	    
	   	 public int getColumnCount() {   
	   	     return columnNames.length;   
	   	 }   
	   	    
	   	 public int getRowCount() {   
	   		 if(data != null)
	   	     return data.size();   
	   		 return 0;
	   	 }   
	   	 public String getColumnName(int col) {   
	   	     return columnNames[col];   
	   	 }   
	   	 public Object getValueAt(int row, int col) {   
	   	     Object value = null;  	 
	   	 
	   	  WFConfig wfconfig = (WFConfig) data.get(row);
	   	    
	   		 switch (col) {
	   	     case 0:
	   	         value = wfconfig.getId();
	   	         break;
	   	     case 1:
	   	         value =wfconfig.getCurrentStatus();
	   	         break;
	   	     case 2:
	   	    	
	   	         value =  wfconfig.getAction();
	   	         break;
	   	     case 3:
	   	         value =wfconfig.getOrgStatus();
	   	         break;
	   	     case 4:
	   	         value = wfconfig.getGroupName();
	   	         break;
	   	     case 5:
	   	         value =wfconfig.getAuto();
	   	         break;
	   	     case 6:
	   	         value = wfconfig.getRule();
	   	         break;
	   	     case 7:
	   	         value =wfconfig.getType();
	   	         break;
	   	  case 8:
	   	         if(wfconfig.isTask())
	   	        	value ="1";
	   	         else 
	   	        	value = "0";
	   	         break;
	   		 
	   	 case 9:
   	         
   	        	value =wfconfig.getDiffUser();
   	   
   	         break;
   		 }
	   	     return value;
	   	 }   
	   	   
	   	 public boolean isCellEditable(int row, int col) {   
	   	 return false;   
	   	 }   
	   	 public void setValueAt(Object value, int row, int col) {   
	   	         System.out.println("Setting value at " + row + "," + col   
	   	                            + " to " + value   
	   	                            + " (an instance of "    
	   	                            + value.getClass() + ")");  
	   	         if(value instanceof Folder) {
	   	     data.set(row,(WFConfig) value) ;
	   	     this.fireTableDataChanged();   
	   	         System.out.println("New value of data:");   
	   	         }
	   	        
	   	 }   
	   	    
	   	 public void addRow(Object value) {   
	   	    
	   		 data.add((WFConfig) value) ;
	   	 this.fireTableDataChanged();   
	   	   
	   	 }   
	   	    
	   	 public void delRow(int row) {   
	   	    if(row != -1) {
	   	 data.remove(row);          
	   	 this.fireTableDataChanged();   
	   	    }
	   	    
	   	 }   
	   	 
	   	 public void udpateValueAt(Object value, int row, int col) {   
	   	     
	   	  
	   	     data.set(row,(WFConfig) value) ;
	   	 fireTableCellUpdated(row, col);   
	   	    
	   	}   
	   	    
	   	    private LegalEntity getLeName(int leID) {
	   	    	LegalEntity le = null;
	   	    	try {
	   				le = remoteRef.selectLE(leID);
	   			} catch (RemoteException e) {
	   				// TODO Auto-generated catch block
	   				e.printStackTrace();
	   			}
	   	    	return le;
	   	    }
	   	    
	   	    public void removeALL() {
	   	    	if(data != null) {
	   	  	  data.removeAllElements();
	   	    	} 
	   	    data = null;
	   	  	 this.fireTableDataChanged();  
	   	    }
	   }
	@Override
	public ActionMap getHotKeysActionMapper() {
		// TODO Auto-generated method stub
		return actionMap;
	}

	@Override
	public JPanel getHotKeysPanel() {
		// TODO Auto-generated method stub
		return jPanel2;
	}

	@Override
	public ArrayList<Component> getFocusOrderList() {
		// TODO Auto-generated method stub
		ArrayList<Component> list = new ArrayList<Component>();
		
		return list;
	}

	@Override
	public void setWindowValidationUtil() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTopLeftSidePanelComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTopRigthSidePanelComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPropertyPaneTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCenterRightSidePanelComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel createChildPanel(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel createChildPanel(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setWindowActionListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getWindowName() {
		// TODO Auto-generated method stub
		return null;
	}

}
