package apps.window.operationwindow.jobpanl;
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

import javax.swing.JPopupMenu;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;   
import java.util.*;   
import javax.swing.*;   
import javax.swing.plaf.basic.BasicComboBoxRenderer;  

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;

import java.awt.event.WindowAdapter;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.Box;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.border.LineBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.commonUTIL;
import util.common.DateU;


import apps.window.util.windowUtil.JDialogBoxForChoice;

import beans.FilterBean;
import beans.Folder;
import beans.LegalEntity;
import beans.StartUPData;
import beans.Task;
import beans.UserJob;
import beans.UserJobsDetails;
import beans.Users;
import dsEventProcessor.TaskEventProcessor;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;



//VS4E -- DO NOT REMOVE THIS LINE!
public class childTaskPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	JSplitPane jSplitPane1;
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
	private JPanel jPanel4;
	private JToolBar jToolBar1;
	private JScrollPane jScrollPane1;
	private JPanel jPanel3;
	private JPanel jPanel5;
	private JPopupMenu jPopupMenu0;
	RemoteTask remoteTask;
	RemoteTrade remoteTrade;
	private JToolBar jToolBar2;
	private JButton jButton0;
	private JButton jButton1;
	private JButton jButton2;
	private JButton jButton3;
	private JTableX jTable0;
	private JTable jTable1;
	private JMenuItem jMenuItem0;
	private JLabel jLabel3;
	private JScrollPane jScrollPane2;
	private JTable jTable2;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	Vector<StartUPData> columnNames;
	Vector<StartUPData> searchCriteria;
	DefaultListModel columnNamesListModel;
	String col [] = {"Name","Criteria","Values","And/Or"};
	TableModelUtil crieriaModel;
	Vector<FilterBean> filters;
	RemoteReferenceData remoteRef;
	private FilterValues filtersValues;
	JComboBox  searchCriteriaC = null;
	RowEditorModel rm;
	private UserJob job;
	private Vector<UserJobsDetails> jobdetails;
	JScrollPane jScrollPane0;
	JobDatePanel datePanel;
	ReportPanel reportPanel;
	private JPanel jPanel6 = null;
	Vector<Task> data = new Vector<Task>();
	DataPanel jobdataPanel = null;
	boolean setDataCriteria = false;
	String name = "";
	public String getName() {
		if(!commonUTIL.isEmpty(name)) {
			String actPath = name.substring(name.indexOf(',')+1, name.indexOf(']'));
			return actPath.trim().toUpperCase();
		}
		
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	DateU StarSQLtDate = null;
	DateU EndSQLDate = null;
	
	public childTaskPanel(String name,Vector searchCriteria,Vector searchColumn,FilterValues filterValues,UserJob job, Vector<UserJobsDetails> jobd,RemoteTask remoteTask,RemoteTrade remoteTrade,Users user) {
		setName(name);
		setSearchCriteria(searchCriteria);
		
		setRemoteTask(remoteTrade);
		setRemoteTask(remoteTask);
		setColumnNames(searchColumn);
		this.filtersValues = filterValues;
	//	jobdataPanel = new DataPanel();
		this.job = job;
		this.jobdetails = jobd;
		setUser(user);
		setUserJob(job);
		setUserJobsDetails(jobd);
		initComponents();
		
		
		
		
	}

	private void setRemoteTask(RemoteTrade remoteTrade) {
		// TODO Auto-generated method stub
		this.remoteTrade = remoteTrade;
	}

	private void setUserJobsDetails(Vector<UserJobsDetails> jobd) {
		// TODO Auto-generated method stub
		
	}

	private void setUserJob(UserJob job2) {
		// TODO Auto-generated method stub
		
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		filters = new Vector<FilterBean>();
		//columnNamesListModel = new DefaultListModel();
		add(getJPanel0(), new Constraints(new Bilateral(10, 10, 0), new Bilateral(9, 13, 10)));
		setSize(721, 472);
	}
	
	private void setJtableRowForDbRecord() {
		rm = jTable0.getRowEditorModel();
		if(jobdetails != null) {
			setDataCriteria = true;
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
		           				attributeName = attributeName.replace("[", "");
		           				attributeName = attributeName.replace("]", "");
		           				ids = ids.replace("[", "");
		           				ids = ids.replace("]", "");
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
	}

	private JTable getJTable0() {
		if (jTable0 == null ) {
			rm = new RowEditorModel();
			crieriaModel = new TableModelUtil(filters, col, remoteRef);
			jTable0 =  new JTableX(crieriaModel);
			jTable0.setRowSelectionAllowed(true);
			jTable0.setSelectionBackground(Color.GRAY);
			jTable0.setColumnSelectionAllowed(false);
			 searchCriteriaC = new JComboBox();
			 jTable0.setRowEditorModel(rm);
     //  	  filtersValues.fillStartUPData(getSearchCriteria(), searchCriteriaC);
			
			jTable0.setModel(crieriaModel);
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
		final JDialogBoxForChoice choice12 = new JDialogBoxForChoice(columnNamesListModel);
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
    	});
		if( searchCriteriaC == null) {
      	  
      	
      //	  filtersValues.fillStartUPData(searchCriteria, searchCriteriaC);
        }
		
		return jButton3;
	}

	
	
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			jButton2.setText("B3");
			jButton2.setToolTipText("Delete");
		}jButton2.addActionListener(new ActionListener() {
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
					// 	
					e.printStackTrace();
				}
            }
		});
		return jButton2;
	}

	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			jButton1.setText("b2");
			jButton1.setToolTipText("Save");
			
		}jButton1.addActionListener(new ActionListener() {
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

			
		});
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
		}jButton0.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	crieriaModel.removeALL();
            	jobdetails.removeAllElements();
            }
		});
		return jButton0;
	}

	private JScrollPane getJScrollPane1() {
		if (jScrollPane1 == null) {
			jScrollPane1 = new JScrollPane();
			jScrollPane1.setViewportView(getJTable0());
		}
		setJtableRowForDbRecord();
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
			//jPanel2.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
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
				System.out.println("PPPP");
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
			
		}jLabel0.addMouseListener(new MouseAdapter() {
			
			

			public void mouseClicked(MouseEvent event) {
				String sql = "";
				if(reportPanel == null) {
				  reportPanel = new ReportPanel(getRemoteTask());
				  reportPanel.setRemoteTask(getRemoteTask());
				}
				String where =  filtersValues.createWhere(filters);
				//where = where + " and tasktype = '" + getName() + "'";
				String startDate = (String) datePanel.jTable1.getValueAt(0, 1);
				String endDate = (String) datePanel.jTable1.getValueAt(0, 3);
				
				sql = generateSQL(where,startDate,endDate,jobdataPanel);
			//	sql = " id > 1";
				try {
					//commonUTIL.showAlertMessage(sql);
				data = (Vector)	remoteTask.selectTaskWhere(sql);
				jobdataPanel.setDataCreteria(data,filtersValues);
				jobdataPanel.setJobdetails(filters);
				
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			//	getJPanel6(reportPanel,sql,"task",null);
			}
		});
		return jLabel0;
	}

	
	
	private void initialStart() {
		String where =  filtersValues.createWhere(filters);
		
		where = where + " and type = '" + getName() + "'";
		String startDate = (String) datePanel.jTable1.getValueAt(0, 1);
		String endDate = (String) datePanel.jTable1.getValueAt(0, 3);
		String sql = generateSQL(where,startDate,endDate,jobdataPanel);
		//sql = " id = 1";
		try {
		data = (Vector)	remoteTask.selectTaskWhere(sql);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	//	getJPanel6(reportPanel,sql,"task",null);
	}
	
	
	private String  generateSQL(String where,String StartDate,String endDate,DataPanel jobdetPanel) {
		String sql = "";
		String startDate = StartDate;
		String EndDate = endDate;
		if(StartDate == null)
			startDate = "";
		if(endDate == null)
			EndDate = "";
		if(!commonUTIL.isStringDate(startDate)) {
		    commonUTIL.showAlertMessage("Select Start Date");
		    return sql;
		}
		if(EndDate.isEmpty() || (startDate.equalsIgnoreCase(EndDate)))  {
			sql = where + " and to_date(taskDate,'dd/mm/yyyy') >= to_date('" +startDate +"','dd/mm/yyyy')";
			DateU startDataU = DateU.valueOf(commonUTIL.stringToDate(startDate,true));
			StarSQLtDate = startDataU;
			if(jobdetPanel != null)
			jobdetPanel.setStartDate(startDataU);
			return sql;
		}
		if(!EndDate.isEmpty() ) {
			if(!commonUTIL.isStringDate(EndDate)) {
			    commonUTIL.showAlertMessage("Select End  Date");
			    return sql;
			}
			DateU startDataU = DateU.valueOf(commonUTIL.stringToDate(startDate,true));
			DateU endDateU = DateU.valueOf(commonUTIL.stringToDate(EndDate,true));
			StarSQLtDate = startDataU;
			EndSQLDate = endDateU;
			if(startDataU.gte(endDateU)) {
				commonUTIL.showAlertMessage("StartDate is greater then End Date ");
				return sql;
			}if(jobdetPanel != null) {
			jobdetPanel.setStartDate(startDataU);
			jobdetPanel.setEndDate(endDateU);
			}
			sql =   where + " and to_date(taskDate,'dd/MM/yyyy')   between to_date('"+startDate +"','dd/MM/yyyy') and to_date('" + EndDate +"','dd/MM/yyyy')";
		}
		return sql;
		
	}
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			//jPanel1.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJToolBar1(), new Constraints(new Bilateral(3, 3, 149), new Leading(5, 26, 10, 10)));
			jPanel1.add(getJScrollPane1(), new Constraints(new Bilateral(6, 7, 23), new Bilateral(40, 8, 272)));
		}
		return jPanel1;
	}

	private JToolBar getJToolBar0() {
		if (jToolBar0 == null) {
			jToolBar0 = new JToolBar();
		//	jToolBar0.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
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
			jSplitPane0.setDividerLocation(300);
			jSplitPane0.setFont(new Font("Arial", Font.PLAIN, 10));
			jSplitPane0.setLeftComponent(getJInternalFrame0());
			jSplitPane0.setRightComponent(getJPanel3());
		}
		return jSplitPane0;
	}
	
	private JPanel getJPanel3() {
		
		if (jPanel3 == null) {
			jPanel3 = new JPanel();
		//	jPanel3.setBorder(new LineBorder(Color.black, 1, false));
			jPanel3.setLayout(new GroupLayout());
			jPanel3.add(getJSplitPane1(), new Constraints(new Bilateral(6, 6, 473), new Bilateral(6, 5, 10, 412)));
		}
		return jPanel3;
	}
	private JSplitPane getJSplitPane1() {
		if(reportPanel == null) {
			reportPanel = new ReportPanel(getRemoteTask());
			reportPanel.setRemoteTask(getRemoteTask());
			
		}
		if (jSplitPane1 == null) {
			jSplitPane1 = new JSplitPane();
			jSplitPane1.setDividerLocation(25);
			jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
			datePanel = new JobDatePanel("Task");
			jSplitPane1.setTopComponent(datePanel.getJPanel4());
			jSplitPane1.setBottomComponent(getJPanel5());
			
			datePanel.jLabel3.addMouseListener(new MouseAdapter() {
				
				

				public void mouseClicked(MouseEvent event) {
					if(jSplitPane1.getDividerLocation() >0) {
						jSplitPane1.setDividerLocation(0);
						return;
				}
					
					if(jSplitPane1.getDividerLocation() == 0) {
						jSplitPane1.setDividerLocation(88);
					
					}
				}
			});
		}
		return jSplitPane1;
	}
	
	
	
	private JPanel getJPanel5() {
		
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			jPanel5.setBorder(new LineBorder(Color.black, 1, false));
			jPanel5.setLayout(new GroupLayout());
			
if(setDataCriteria) {
	initialStart();
	jobdataPanel = new DataPanel(getName(),data,filtersValues);	
	jobdataPanel.setStartDate(StarSQLtDate);
	jobdataPanel.setEndDate(EndSQLDate);//dataPanel.setDBDataCreteria(data, filtersValues);
	jobdataPanel.setJobdetails(filters);
	jobdataPanel.setUser(getUser());
			}
if(jobdataPanel == null) {
jobdataPanel = new DataPanel(getName(),data,filtersValues);  
jobdataPanel.setStartDate(StarSQLtDate);
jobdataPanel.setEndDate(EndSQLDate);
jobdataPanel.setJobdetails(filters);
jobdataPanel.setUser(getUser());
		}
			jPanel5.add(jobdataPanel.getJPanel6() ,new Constraints(new Bilateral(6, 6, 473), new Bilateral(6, 5, 10, 412)));
		}
		return jPanel5;
	}
	
	private JPanel getJPanel6() {
		if (jPanel6 == null) {
			
			jPanel6 = new JPanel();
			jPanel6.setBorder(new LineBorder(Color.black, 1, false));
			jPanel6.setLayout(new BorderLayout());
			jPanel6.add(getJScrollPane2(),BorderLayout.CENTER);
		
		}
		return jPanel6;
	}
	private JScrollPane getJScrollPane2() {
		
		if (jScrollPane2 == null) {
			jScrollPane2 = new JScrollPane();
			jScrollPane2.setViewportView(getJTable2());
		}
		return jScrollPane2;
	}
	private JTable getJTable2() {
		
		if (jTable2 == null) {
			jTable2 = new JTable();
			jTable2.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});
			jTable2.setComponentPopupMenu(getJPopupMenu0());
		}
		return jTable2;
	}

	private JPanel getJPanel4() {
		
		if (jPanel4 == null) {
			jPanel4 = new JPanel();
			jPanel4.setLayout(new GroupLayout());
			jPanel4.add(getJToolBar2(), new Constraints(new Bilateral(5, 10, 13), new Leading(7, 23, 10, 10)));
			jPanel4.add(getJScrollPane0(), new Constraints(new Bilateral(9, 14, 23), new Leading(36, 41, 10, 10)));
		}
		return jPanel4;
	}
	private JPopupMenu getJPopupMenu0() {
		
		if (jPopupMenu0 == null) {
			jPopupMenu0 = new JPopupMenu();
			jPopupMenu0.add(getJMenuItem0());
		}
		return jPopupMenu0;
	}
	private JMenuItem getJMenuItem0() {
		
		if (jMenuItem0 == null) {
			jMenuItem0 = new JMenuItem();
			jMenuItem0.setText("jMenuItem0");
			jMenuItem0.setOpaque(false);
		}
		return jMenuItem0;
	}
	private JScrollPane getJScrollPane0() {
		
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTable1());
		}
		return jScrollPane0;
	}
	private JTable getJTable1() {
		
		if (jTable1 == null) {
			jTable1 = new JTable();
			
		/*	jTable1.setModel(new DefaultTableModel(new Object[][] { { "0x0", "0x1", }, { "1x0", "1x1", }, }, new String[] { "Title 0", "Title 1", }) {
				private static final long serialVersionUID = 1L;
				Class<?>[] types = new Class<?>[] { Object.class, Object.class, };
	
				public Class<?> getColumnClass(int columnIndex) {
					return types[columnIndex];
				}
			});*/
			jTable1.setRowHeight(30);
			jTable1.setColumnSelectionAllowed(false);
			jTable1.setRowSelectionAllowed(true);
			jTable1.setPreferredScrollableViewportSize(new Dimension(400, 200));
		}
		return jTable1;
	}
	private JToolBar getJToolBar2() {
		
		if (jToolBar2 == null) {
			jToolBar2 = new JToolBar();
			//jToolBar2.setBorder(new LineBorder(Color.gray, 1, false));
			jToolBar2.add(getJLabel3());
		}
		return jToolBar2;
	}
	private JLabel getJLabel3() {
	
		if (jLabel3 == null) {
			jLabel3 = new JLabel();
			jLabel3.setText(">>");
		}jLabel3.addMouseListener(new MouseAdapter() {
			
			

			public void mouseClicked(MouseEvent event) {
				
				
				if(jSplitPane1.getDividerLocation() == 88) {
					jSplitPane1.setDividerLocation(14);
				} else {
					jSplitPane1.setDividerLocation(88);
				}
			}
		});
		return jLabel3;
	}
	private JInternalFrame getJInternalFrame0() {
		if (jInternalFrame0 == null) {
			jInternalFrame0 = new JInternalFrame();
		//	jInternalFrame0.setBackground(Color.LIGHT_GRAY);
		//	jInternalFrame0.setBorder(null);
			jInternalFrame0.setVisible(true);
			jInternalFrame0.setLayout(new GroupLayout());
			javax.swing.plaf.InternalFrameUI ifu= jInternalFrame0.getUI();
			((javax.swing.plaf.basic.BasicInternalFrameUI)ifu).setNorthPane(null);
			jInternalFrame0.add(getJToolBar0(), new Constraints(new Bilateral(8, 11, 2), new Leading(6, 26, 17, 17)));
			jInternalFrame0.add(getJPanel1(), new Constraints(new Bilateral(8, 10, 0), new Bilateral(38, 11, 55)));
		}
		return jInternalFrame0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
		//	jPanel0.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJTabbedPane0(), new Constraints(new Bilateral(8, 10, 5), new Bilateral(7, 9, 10, 200)));
		}
		return jPanel0;
	}

	private JTabbedPane getJTabbedPane0() {
		if (jTabbedPane0 == null) {
			jTabbedPane0 = new JTabbedPane();
		//	jTabbedPane0.setBorder(new LineBorder(Color.LIGHT_GRAY, 1, false));
			ImageIcon icon = new ImageIcon(  this.getClass().getResource("/resources/icon/report_filter.png"));
			jTabbedPane0.addTab("",icon, getJSplitPane0());
			jTabbedPane0.setTabPlacement(JTabbedPane.LEFT);
			jTabbedPane0.setFont(new Font("Arial", Font.PLAIN, 10));
			
		//	jTabbedPane0.settsetTitleAt(0, "<HTML> F<BR>i<BR>l<BR>t<BR>e<BR>r<BR>p<BR>s<BR>");
			jTabbedPane0.addMouseListener(new MouseAdapter() {
				
				public void mouseEntered(MouseEvent event) {
				//	jTabbedPane0MouseMouseEntered(event);
				}
	
				public void mouseClicked(MouseEvent event) {
					jTabbedPane0MouseMouseClicked(event);
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
	//	 jSplitPane0.setOneTouchExpandable(true);
		// jSplitPane0.setDividerLocation(0);
		 if( jSplitPane0.getDividerLocation() == 300) {
			 jSplitPane0.setDividerLocation(0);
		 } else {
			 jSplitPane0.setDividerLocation(300);
		 }

	}
	
	public Vector<StartUPData> getColumnNames() {
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
public Users getUser() {
	return user;
}
Users user = null;
public void processTask(TaskEventProcessor task) {
	// TODO Auto-generated method stub
	System.out.println(" Name " + name + " "+ task.getTask().getType());
//	Task addTask = task.getTask();
	if(getName().contains(task.getTask().getType())) {
	   jobdataPanel.addtaskData(task);
	}
	
}
}
