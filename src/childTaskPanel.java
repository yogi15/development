
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import beans.UserJob;
import beans.UserJobsDetails;
import dsServices.RemoteTask;



//VS4E -- DO NOT REMOVE THIS LINE!
public class childTaskPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JSplitPane jSplitPane0;
	private JTabbedPane jTabbedPane0;
	private JPanel jPanel0;
	private JInternalFrame jInternalFrame0;
	private JToolBar jToolBar0;
	private JPanel jPanel1;
	private JLabel jLabel0;
	private JLabel jLabel1;
	private JLabel jLabel2;
	private JPanel jPanel2;
	private JToolBar jToolBar1;
	private JScrollPane jScrollPane1;
	RemoteTask remoteTask;
	
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	//private JTableX jTable0;
	private JTable jTable0;
	private JToolBar jToolBar3;
	private JPanel jPanel4;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public childTaskPanel(){
	initComponents();
}

/*	public childTaskPanel(Vector searchCriteria,Vector searchColumn,FilterValues filterValues,UserJob job, Vector<UserJobsDetails> jobd) {
	/*	setSearchCriteria(searchCriteria);
		setColumnNames(searchColumn);
		this.filtersValues = filterValues;
		this.job = job;
		this.jobdetails = jobd;
		setUserJob(job);
		setUserJobsDetails(jobd);
		initComponents();
	}*/

	private void setUserJobsDetails(Vector<UserJobsDetails> jobd) {
		// TODO Auto-generated method stub
		
	}

	private void setUserJob(UserJob job2) {
		// TODO Auto-generated method stub
		
	}

	
	
	/*private void setJtableRowForDbRecord() {
		rm = jTable0.getRowEditorModel();
		if(jobdetails != null) {
			Vector<FilterBean> fillterBeans = new Vector<FilterBean>();
			//rm = new RowEditorModel();
			for(int i=0;i<jobdetails.size();i++) {
				UserJobsDetails jd = jobdetails.get(i);
				FilterBean filBean = new FilterBean();
				filBean.setColumnName(jd.getColumnName());
				filBean.setSearchCriteria(jd.getCriteria());
				filBean.setColumnValues(jd.getValues() );
				filBean.setIdSelected(jd.getFilterValues());
			    filBean.setAnd_or(jd.getAnd_or());
				fillterBeans.add(filBean);
				
			}
			filters = fillterBeans;
			crieriaModel = new TableModelUtil(filters, col, remoteRef);
			//jTable0 =  new JTableX(crieriaModel);
			
			jTable0.setModel(crieriaModel);
		
			 for(int i=0;i<filters.size();i++) {
				 FilterBean bean = filters.get(i);
				 String andor [] = {"  ","and","or"};
				 DefaultComboBoxModel andormodelC = new DefaultComboBoxModel(andor); 
		         final	JComboBox andorComb  = new JComboBox(andormodelC);
		         DefaultCellEditor andorCombCedd = new DefaultCellEditor(andorComb);
		         
		         andorComb.addItemListener(new ItemListener () {

          			@Override
          			public void itemStateChanged(ItemEvent e) {
          				if(andorComb.getSelectedIndex() != -1) {
          				// TODO Auto-generated method stub
          				
          					
          				String attributeName = andorComb.getSelectedItem().toString();
          			//System.out.println(data.size() + "  " + jTable0.getSelectedRow());
          			int i = jTable0.getSelectedRow();
          			if(i == -1) 
          				i = 0;
          			FilterBean filler = (FilterBean) filters.get(i);
          				filler.setAnd_or(attributeName);
          				filters.remove(i);
          				filters.add(i, filler);
          				//mod.setValueAt(attributeName, table.getSelectedRow(), table.getSelectedColumn());
          		//		mod.fireTableDataChanged();
                  //		 table.repaint();
          				}
          			}
          	 });
			 String values1 [] =  filtersValues.convertVectortoSringArray(searchCriteria,"SearchCriteria");
			final SelectionManager manager = new SelectionManager(); 
			final    Renderer renderer = new Renderer(manager);
			 DefaultComboBoxModel modelC = new DefaultComboBoxModel(values1); 
	         final	JComboBox searchCriteriaComb  = new JComboBox(modelC);
	     
	         	 DefaultCellEditor searchCriteriaCedd = new DefaultCellEditor(searchCriteriaComb);
	         	    	rm.addEditorForRow(i,0,searchCriteriaCedd);
	         	    	
	         	    	searchCriteriaComb.addItemListener(new ItemListener () {

	             			@Override
	             			public void itemStateChanged(ItemEvent e) {
	             				if(searchCriteriaComb.getSelectedIndex() != -1) {
	             				// TODO Auto-generated method stub
	             				
	             					
	             				String attributeName = searchCriteriaComb.getSelectedItem().toString();
	             			//System.out.println(data.size() + "  " + jTable0.getSelectedRow());
	             			int i = jTable0.getSelectedRow();
	             			if(i == -1) 
	             				i = 0;
	             			FilterBean filler = (FilterBean) filters.get(i);
	             				filler.setSearchCriteria(attributeName);
	             				filters.remove(i);
	             				filters.add(i, filler);
	             				//mod.setValueAt(attributeName, table.getSelectedRow(), table.getSelectedColumn());
	             		//		mod.fireTableDataChanged();
	                     //		 table.repaint();
	             				}
	             			}
	             	 });
	         	    	Vector dataValues =  filtersValues.getValuesonColumn(bean.getColumnName(), remoteRef); 
	                 	String values [] =    filtersValues.convertVectortoSringArray(dataValues,bean.getColumnName(),0);   	      
	           	  	    final JComboBox comvalues   = new JComboBox(values);
	           	  	    
	           	  	    DefaultCellEditor valuesCed = new DefaultCellEditor(comvalues);
	           	  	comvalues.addActionListener(manager);   
	           	 comvalues.setRenderer(renderer);   
	     	        manager.setNonSelectable(values[0]);
	           	  	     rm.addEditorForRow(i,1,valuesCed);
	           	  	rm.addEditorForRow(i,2,andorCombCedd);
	           	  	comvalues.addItemListener(new ItemListener () {

            			@Override
            			public void itemStateChanged(ItemEvent e) {
            				if(comvalues.getSelectedIndex() != -1) {
            			           				String attributeName = manager.getSelectedItems().toString();
            			           				String ids = manager.getIDSelectedItems().toString();
            			               			int i = jTable0.getSelectedRow();
            			               			if(i == -1) 
            			               				i = 0;
            			               			FilterBean filler = (FilterBean) filters.get(i);
            			               			filler.setColumnValues(attributeName);
            			               			filler.setIdSelected(ids);
            			               			filters.remove(i);
            			               			filters.add(i, filler);
            		
            				}
            			} 
            	 });
			 }
		}
	}*/

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(2, 946, 10, 10), new Bilateral(4, 3, 10)));
		setSize(956, 472);
	}

	private JPanel getJPanel4() {
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setBorder(new LineBorder(Color.black, 1, false));
			jPanel4.setPreferredSize(new Dimension(100, 100));
			jPanel4.setLayout(new GroupLayout());
		}
		return jPanel4;
	}

	private JToolBar getJToolBar3() {
		if (jToolBar3 == null) {
			jToolBar3 = new JToolBar();
			jToolBar3.setBorder(new LineBorder(Color.black, 1, false));
		}
		return jToolBar3;
	}

	private JTable getJTable0() {
		if (jTable0 == null ) {
			jTable0 = new JTable();
		/*	rm = new RowEditorModel();
			crieriaModel = new TableModelUtil(filters, col, remoteRef);
			jTable0 =  new JTableX(crieriaModel);
			jTable0.setRowSelectionAllowed(true);
			jTable0.setSelectionBackground(Color.GRAY);
			jTable0.setColumnSelectionAllowed(false);
			 searchCriteriaC = new JComboBox();
			 jTable0.setRowEditorModel(rm);*/
     //  	  filtersValues.fillStartUPData(getSearchCriteria(), searchCriteriaC);
			
			//jTable0.setModel(crieriaModel);
			//jTable0.set
			
		
			
		}
		return jTable0;
	}

	
	
	private JButton getJButton3() {
		
		if (jButton3 == null) {
			jButton3 = new JButton();
			jButton3.setText("B4");
			jButton3.setToolTipText("Add Search Criteria");
		}
	/*	final JDialogBoxForChoice choice12 = new JDialogBoxForChoice(columnNamesListModel);
		jButton3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	choice12.jList3.setModel(columnNamesListModel);
				choice12.setLocationRelativeTo(choice12);
				//choice12.setSize(200,200);
				choice12.setVisible(true);
            	
            }
		});
		choice12.addWindowListener(new WindowAdapter() {  
			
            public void windowClosing(WindowEvent e) {
            	
            	
                System.out.println("Window closing");
            	 try {
            				String ss = "";
            				Object obj [] =  choice12.getObj();
            				String andor [] = {"  ","and","or"};
           				 DefaultComboBoxModel andormodelC = new DefaultComboBoxModel(andor); 
           		         final	JComboBox andorComb  = new JComboBox(andormodelC);
           		         DefaultCellEditor andorCombCedd = new DefaultCellEditor(andorComb);
           		      
           		         andorComb.addItemListener(new ItemListener () {

                     			@Override
                     			public void itemStateChanged(ItemEvent e) {
                     				if(andorComb.getSelectedIndex() != -1) {
                     				// TODO Auto-generated method stub
                     				
                     					
                     				String attributeName = andorComb.getSelectedItem().toString();
                     			//System.out.println(data.size() + "  " + jTable0.getSelectedRow());
                     			int i = jTable0.getSelectedRow();
                     			if(i == -1) 
                     				i = 0;
                     			FilterBean filler = (FilterBean) filters.get(i);
                     				filler.setAnd_or(attributeName);
                     				filters.remove(i);
                     				filters.add(i, filler);
                     				//mod.setValueAt(attributeName, table.getSelectedRow(), table.getSelectedColumn());
                     		//		mod.fireTableDataChanged();
                             //		 table.repaint();
                     				}
                     			}
                     	 });
            				String values1 [] =  filtersValues.convertVectortoSringArray(searchCriteria,"SearchCriteria");
			                final	JComboBox searchCriteriaC12  = new JComboBox(values1);
			                	 DefaultCellEditor searchCriteriaCed = new DefaultCellEditor(searchCriteriaC12);
			                	 searchCriteriaC12.addItemListener(new ItemListener () {

			             			@Override
			             			public void itemStateChanged(ItemEvent e) {
			             				if(searchCriteriaC12.getSelectedIndex() != -1) {
			             				// TODO Auto-generated method stub
			             				String attributeName = searchCriteriaC12.getSelectedItem().toString();
			             			//System.out.println(data.size() + "  " + jTable0.getSelectedRow());
			             			int i = jTable0.getSelectedRow();
			             			FilterBean filler = (FilterBean) filters.get(i);
			             				filler.setSearchCriteria(attributeName);
			             				filters.remove(i);
			             				filters.add(i, filler);
			             				//mod.setValueAt(attributeName, table.getSelectedRow(), table.getSelectedColumn());
			             		//		mod.fireTableDataChanged();
			                     //		 table.repaint();
			             				}
			             			}
			             	 });
			                 for(int i =0;i<obj.length;i++) {
			                	  	ss = (String) obj[i] ;
			                	  	final SelectionManager manager = new SelectionManager(); 
			            		    Renderer renderer = new Renderer(manager);
			                	  	Vector dataValues =  filtersValues.getValuesonColumn(ss, remoteRef); 
			                	  	String values [] =    filtersValues.convertVectortoSringArray(dataValues,ss,0);   	      
				           	  	
				           	  	 DefaultComboBoxModel model = new DefaultComboBoxModel(values);
				           	    final JComboBox comvalues   = new JComboBox(model);
				           	  	    DefaultCellEditor valuesCed = new DefaultCellEditor(comvalues);
				           	  	comvalues.addActionListener(manager);   
				           	    comvalues.setRenderer(renderer);   
				     	        manager.setNonSelectable(values[0]);
			                	  	 
			                	  	  
			                	      
			                	 
			                //	  	filtersValues.fillStartUPData(dataValues, comvalues);
			                	  	FilterBean filter = new FilterBean();
			                	  	
			                	  	filter.setColumnName(ss);
			                		crieriaModel.addRow(filter);
			                		int rowCount = crieriaModel.getRowCount();
			                		   
			                		  rm.addEditorForRow(rowCount-1,0,searchCriteriaCed);
			                		  rm.addEditorForRow(rowCount-1,1,valuesCed);
			                		  rm.addEditorForRow(rowCount-1,2,andorCombCedd);
			                		comvalues.addItemListener(new ItemListener () {

			                			@Override
			                			public void itemStateChanged(ItemEvent e) {
			                				if(comvalues.getSelectedIndex() != -1) {
			                			           				String attributeName = manager.getSelectedItems().toString();
			                			           				String ids = manager.getIDSelectedItems().toString();
			                			           				attributeName = attributeName.replace("[", "");
			                			           				attributeName = attributeName.replace("]", "");
			                			           				ids = ids.replace("[", "");
			                			           				ids = ids.replace("]", "");
			                			               			int i = jTable0.getSelectedRow();
			                			               			FilterBean filler = (FilterBean) filters.get(i);
			                			               			filler.setColumnValues(attributeName);
			                			               			
			                			               			filler.setIdSelected(ids); // this are just combox ids 
			                			               			filters.remove(i);
			                			               			filters.add(i, filler);
			                		
			                				}
			                			} 
			                	 });
			                	
               
			                  }
                                         crieriaModel.fireTableDataChanged();
            		 jTable0.repaint();
               
             
                } catch (Throwable t) {
                    t.printStackTrace();
                }             
            }
    	});*/
		
		
		return jButton3;
	}

	
	
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("B3");
			jButton2.setToolTipText("Delete");
		}/*jButton2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	if(jTable0.getSelectedRow() != -1)
            	crieriaModel.delRow(jTable0.getSelectedRow());
            	Vector<UserJobsDetails> jobdets = new Vector<UserJobsDetails>();
        		for(int i=0;i<filters.size();i++) {
        			FilterBean filterBean = filters.get(i);
        			UserJobsDetails ud = new UserJobsDetails();
        			ud.setJobId(job.getId());
        			ud.setColumnName(filterBean.getColumnName());
        			ud.setCriteria(filterBean.getSearchCriteria());
        			ud.setValues(filterBean.getColumnValues() );
        			ud.setFilterValues(filterBean.getIdSelected());
        			ud.setRowid(i);
        			jobdets.add(ud);
        			
        		}
        		try {
					remoteTask.saveUserJobsDetails(jobdets,job.getId());
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
		});*/
		return jButton2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("b2");
			jButton1.setToolTipText("Save");
			
		}/*jButton1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	System.out.println(job.getId());
            	 try {
            	if(job.getId() == 0) {
            		 
            			  job.setUserID(user.getId());
					 job = remoteTask.saveUserJob(job);
					
            		int rowCount = crieriaModel.getRowCount();
            		Vector<UserJobsDetails> jobdets = new Vector<UserJobsDetails>();
            		for(int i=0;i<rowCount;i++) {
            			FilterBean filterBean = filters.get(i);
            			UserJobsDetails ud = new UserJobsDetails();
            			ud.setJobId(job.getId());
            			ud.setColumnName(filterBean.getColumnName());
            			ud.setCriteria(filterBean.getSearchCriteria());
            			ud.setValues(filterBean.getColumnValues() );
            			ud.setFilterValues(filterBean.getIdSelected() );
            			ud.setAnd_or(filterBean.getAnd_or());
            			ud.setRowid(i);
            			jobdets.add(ud);
            			
            		}
            		remoteTask.saveUserJobsDetails(jobdets,job.getId());
            	} else {
            		int rowCount = crieriaModel.getRowCount();
            		Vector<UserJobsDetails> jobdets = new Vector<UserJobsDetails>();
            		for(int i=0;i<filters.size();i++) {
            			FilterBean filterBean = filters.get(i);
            			UserJobsDetails ud = new UserJobsDetails();
            			ud.setJobId(job.getId());
            			ud.setColumnName(filterBean.getColumnName());
            			ud.setCriteria(filterBean.getSearchCriteria());
            			ud.setValues(filterBean.getColumnValues() );
            			ud.setFilterValues(filterBean.getIdSelected() );
            			ud.setAnd_or(filterBean.getAnd_or());
            			ud.setRowid(i);
            			
            			jobdets.add(ud);
            			}
            		remoteTask.saveUserJobsDetails(jobdets,job.getId());
            		
            	}
            	 } catch (RemoteException e) {
						// TODO Auto-generated catch block
						commonUTIL.displayError("ChildTaskPanel" , "Save Bution", e);
					}
            }

			
		});*/
		return jButton1;
	}
	private boolean checkRowAlreadExists(UserJobsDetails ud) {
		// TODO Auto-generated method stub
		return false;
	}
		

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("B1");
			jButton0.setToolTipText("Clear All");
		}/*jButton0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	crieriaModel.removeALL();
            	jobdetails.removeAllElements();
            }
		});*/
		return jButton0;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTable0());
		}
	//	setJtableRowForDbRecord();
		return jScrollPane1;
	}

	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setFloatable(false);
			jToolBar1.add(getJButton0());
			jToolBar1.add(getJButton1());
			jToolBar1.add(getJButton2());
			jToolBar1.add(getJButton3());
		}
		return jToolBar1;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
			jPanel2.setLayout(new GroupLayout());
		}
		return jPanel2;
	}

	private JLabel getJLabel2() {
		if (jLabel2 == null) {
			jLabel2 = new JLabel();
			jLabel2.setFont(new Font("Arial Narrow", Font.PLAIN, 14));
			jLabel2.setText(" L4");
		}
		return jLabel2;
	}

	private JLabel getJLabel1() {
		if (jLabel1 == null) {
			jLabel1 = new JLabel();
			jLabel1.setFont(new Font("Arial Narrow", Font.PLAIN, 14));
			jLabel1.setText(" L2");
			jLabel1.setToolTipText("Hide");
		}jLabel1.addMouseListener(new MouseAdapter() {
			
			

			public void mouseClicked(MouseEvent event) {
				jTabbedPane0MouseMouseClicked(event);
			}
		});
		
		return jLabel1;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setFont(new Font("Arial Narrow", Font.PLAIN, 14));
			jLabel0.setText(" L1");
			jLabel0.setToolTipText("Execute");
			
		}/*jLabel0.addMouseListener(new MouseAdapter() {
			
			

			public void mouseClicked(MouseEvent event) {
				String where =  filtersValues.createWhere(filters);
				if(where == null) 
					return;
				commonUTIL.showAlertMessage(where);
			}
		});*/
		return jLabel0;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setBorder(new LineBorder(Color.lightGray, 1, false));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJToolBar1(), new Constraints(new Bilateral(3, 3, 149), new Leading(5, 26, 10, 10)));
		}
		return jPanel1;
	}

	private JToolBar getJToolBar0() {
		if (jToolBar0 == null) {
			jToolBar0 = new JToolBar();
			jToolBar0.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
			jToolBar0.add(Box.createHorizontalGlue());
			jToolBar0.add(getJLabel0());
			jToolBar0.add(getJLabel1());
			jToolBar0.add(getJLabel2());
		}
		return jToolBar0;
	}

	private JSplitPane getJSplitPane0() {
		if (jSplitPane0 == null) {
			jSplitPane0 = new JSplitPane();
			jSplitPane0.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			jSplitPane0.setDividerLocation(104);
			jSplitPane0.setFont(new Font("Arial", Font.PLAIN, 10));
			jSplitPane0.setLeftComponent(getJInternalFrame0());
		//	jSplitPane0.setRightComponent(getJPanel3());
		}
		return jSplitPane0;
	}

	private JInternalFrame getJInternalFrame0() {
		if (jInternalFrame0 == null) {
			jInternalFrame0 = new JInternalFrame();
			jInternalFrame0.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			jInternalFrame0.setVisible(true);
			jInternalFrame0.setFont(new Font("Arial", Font.PLAIN, 10));
			jInternalFrame0.setLayout(new GroupLayout());
			jInternalFrame0.add(getJToolBar0(), new Constraints(new Bilateral(8, 11, 2), new Leading(6, 26, 17, 17)));
			jInternalFrame0.add(getJPanel1(), new Constraints(new Bilateral(8, 10, 0), new Bilateral(38, 11, 55)));
		}
		return jInternalFrame0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(new LineBorder(Color.lightGray, 1, false));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJTabbedPane0(), new Constraints(new Bilateral(3, 15, 916), new Bilateral(3, 2, 10, 458)));
		}
		return jPanel0;
	}

	private JTabbedPane getJTabbedPane0() {
		URL imgURL = null;
		if (jTabbedPane0 == null) {
			jTabbedPane0 = new JTabbedPane();
			jTabbedPane0.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
			jTabbedPane0.setTabPlacement(JTabbedPane.LEFT);
			jTabbedPane0.setFont(new Font("Arial", Font.PLAIN, 10));
			//jTabbedPane0.addTab("<HTML> F<BR>i<BR>l<BR>t<BR>e<BR>r<BR>p<BR>s<BR>", getJSplitPane0());
			 imgURL = this.getClass().getResource("/resources/icon/report_filter.png");
			 ImageIcon icon = new ImageIcon(  this.getClass().getResource("/resources/icon/report_filter.png"));
		
			jTabbedPane0.addTab("", icon,getJSplitPane0());
			jTabbedPane0.addMouseListener(new MouseAdapter() {
	
				public void mouseClicked(MouseEvent event) {
					jTabbedPane0MouseMouseClicked(event);
				}
	
				public void mouseEntered(MouseEvent event) {
					jTabbedPane0MouseMouseEntered(event);
				}
			});
		}
		return jTabbedPane0;
	}

	private void jTabbedPane0MouseMouseEntered(MouseEvent event) {
		 jSplitPane0.setOneTouchExpandable(true);
		 jSplitPane0.setDividerLocation(300);
		// jTabbedPane0.disable();

		
	}
	private void jTabbedPane0MouseMouseClicked(MouseEvent event) {
		 jSplitPane0.setOneTouchExpandable(true);
		 jSplitPane0.setDividerLocation(0);

	}
}
/*	public Vector<StartUPData> getColumnNames() {
		return columnNames;
	}

	public void setColumnNames(Vector columnNames) {
		this.columnNames = columnNames;
		
		if(columnNames.size() > 0 && columnNames != null) {
			Iterator<StartUPData> it = columnNames.iterator();
			columnNamesListModel = new DefaultListModel();
	    	while(it.hasNext()) {
	    		columnNamesListModel.addElement(((StartUPData) it.next()).getName());
	    	}

		}
		
	}

	public Vector<StartUPData> getSearchCriteria() {
		return searchCriteria;
	}

	public void setSearchCriteria(Vector<StartUPData> searchCriteria) {
		this.searchCriteria = searchCriteria;
	//	filtersValues.getValuesonColumn("SearchCriteria", remoteRef);
	}
	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
	//	frame.add(new childTaskPanel());
		

		frame.setVisible(true);
		frame.setSize(500,500);
	}
	 public void setRemoteRef(RemoteReferenceData remoteRef) {
			this.remoteRef = remoteRef;
		}
	
	
	 class SelectionManager implements ActionListener {
	        JComboBox combo = null;
	        private List<Object> selectedItems = new ArrayList<Object>();
	        private List<Object> selectedItemsId = new ArrayList<Object>();
	        private Object nonSelectable;

	        public void setNonSelectable(Object val) {
	            nonSelectable = val;
	        }
	        public void actionPerformed(ActionEvent e) {
	            if (combo == null) {
	                combo = (JComboBox) e.getSource();
	            }
	            Object item = combo.getSelectedItem();
	            int i = combo.getSelectedIndex();
	            // Toggle the selection state for item.  
	            if (selectedItems.contains(item)) {
	                selectedItems.remove(item);
	                selectedItemsId.remove(new Integer(i));
	            } else if (!item.equals(nonSelectable)) {
	                selectedItems.add(item);
	                selectedItemsId.add(new Integer(i));
	            }

	            combo.setSelectedIndex(1);
	        }

	        public List<Object> getSelectedItems() {
	            return selectedItems;
	        }
	        public List<Object> getIDSelectedItems() {
	            return selectedItemsId;
	        }
	        
	    }
	 class Renderer extends BasicComboBoxRenderer {
	        SelectionManager selectionManager;

	        public Renderer(SelectionManager sm) {
	            selectionManager = sm;
	        }

	        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected,
	                boolean cellHasFocus) {
	            setFont(list.getFont());

	            if (index == 0) { // first item shows currently selected items delimited by ;
	                StringBuffer firstItem = new StringBuffer();
	                for (Object sel : selectionManager.getSelectedItems()) {
	                    firstItem.append(sel + "; ");
	                }
	                if (firstItem.toString().endsWith("; ")) {
	                    firstItem.deleteCharAt(firstItem.length() - 2);
	                }
	                setText((value == null) ? "" : firstItem.toString());
	            } else {// other items
	                setText((value == null) ? "" : value.toString());
	            }

	            return this;
	        }
	    }
class TableModelUtil extends AbstractTableModel {   
    
	 final String[] columnNames;  
	    
	 Vector<FilterBean> data;   
	 RemoteReferenceData remoteRef ;
	        JComboBox cref;
	 public TableModelUtil( Vector<FilterBean> myData,String col [],RemoteReferenceData remoteRef) {   
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
	 
	     FilterBean filter = (FilterBean) data.get(row);
	    
		 switch (col) {
		
	     case 0:
	         value =  filter.getColumnName();
	         break;
	     case 1:
	    	 value =  filter.getSearchCriteria();
	         break;
	     case 2:
	    	 value =  filter.getColumnValues();
	         break;
	     case 3:
	         value =  filter.getAnd_or();
	         break;
		 }
	     return value;
	 }   
	   
	   
	  public boolean isCellEditable(int row, int col)
     {
         if (col==0)
             return false;
         return true;
     }  
	 public void setValueAt(Object value, int row, int col) {
		 if(value == null)
			 return;
	         System.out.println("Setting value at " + row + "," + col   
	                            + " to " + value   
	                            + " (an instance of "    
	                            + value.getClass() + ")");  
	         if(value instanceof Folder) {
	     data.set(row,(FilterBean) value) ;
	     this.fireTableDataChanged();   
	         System.out.println("New value of data:");   
	         }
	        
	 }   
	    
	 public void addRow(Object value) {   
	    
		 data.add((FilterBean) value) ;
	 this.fireTableDataChanged();   
	   
	 }   
	    
	 public void delRow(int row) {   
	    if(row != -1) {
	 data.remove(row);          
	 this.fireTableDataChanged();   
	    }
	    
	 }   
	
	 public void udpateValueAt(Object value, int row, int col) {   
	     
	  
	     data.set(row,(FilterBean) value) ;
	     for(int i=0;i<columnNames.length;i++)
	    	 	fireTableCellUpdated(row, i);   
	    
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
	   // data = null;
	  	 this.fireTableDataChanged();  
	    }
}



public RemoteTask getRemoteTask() {
	return remoteTask;
}

public void setRemoteTask(RemoteTask remoteTask) {
	this.remoteTask = remoteTask;
}
public void setFilterValues(FilterValues filterValues) {
	// TODO Auto-generated method stub
	this.filtersValues = filterValues;
	
}

public void setUser(Users user) {
	// TODO Auto-generated method stub
	this.user = user;
}
Users user = null;
}*/
