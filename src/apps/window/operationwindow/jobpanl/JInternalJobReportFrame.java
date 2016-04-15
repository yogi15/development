package apps.window.operationwindow.jobpanl;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.commonUTIL;

import apps.window.operationwindow.jobpanl.FilterValues;
import apps.window.positionwindow.ManualPositionPanel;
import apps.window.referencewindow.DateCellEditor12;
import apps.window.reportwindow.ReportPanel;
import apps.window.util.windowUtil.ButtonsIconsFactory;
import apps.window.util.windowUtil.JDialogBoxForChoice;
import apps.window.util.windowUtil.JDialogTable;
import beans.FilterBean;
import beans.StartUPData;
import beans.Task;
import beans.UserJob;
import beans.UserJobsDetails;
import beans.Users;

import com.jidesoft.combobox.MultiSelectListExComboBox;
import com.jidesoft.docking.DockableFrame; 

public class JInternalJobReportFrame extends JInternalFrame {

	private searchCriteriaPanel searchPanel = null;
	FilterValues filterValues = null;
	ReportPanel reportPanel = null;
	String andORColumnValus [] = {"Select Values","AND","OR"};
	private UserJob job=null;
	private Vector<UserJobsDetails> jobdetails;
	Vector<Task> data = new Vector<Task>();
	public DataPanel jobdataPanel = null;
	/**
	 * @return the jobdataPanel
	 */
	public DataPanel getJobdataPanel() {
		return jobdataPanel;
	}

	/**
	 * @param jobdataPanel the jobdataPanel to set
	 */
	public void setJobdataPanel(DataPanel jobdataPanel2) {
		this.jobdataPanel = jobdataPanel2;
		//jobdataPanel.setJobdetails();
	}

	/**
	 * @return the reportPanel
	 */
	public ReportPanel getReportPanel() {
		return reportPanel;
	}

	/**
	 * @param reportPanel the reportPanel to set
	 */
	public void setReportPanel(ReportPanel reportPanel) {
		this.reportPanel =  reportPanel;
	}

	String reportType = "";
	/**
	 * @return the reportType
	 */
	public String getReportType() {
		return reportType;
	}

	/**
	 * @param reportType the reportType to set
	 */
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	int selectRow = 0;
	int selectCol = 0;

	Vector<StartUPData> searchCriteria;
	DefaultListModel columnNamesListModel;
	Vector<StartUPData> searchColumn;

	JButton jButton0 = null;
	JButton jButton1 = null;
	JButton jButton2 = null;
	JButton jButton3 = null;
	JButton jButton4 = null;
	JButton jButton5 = null;
	private JToolBar jToolBar1;
	Vector<FilterBean> filters = null;
	String templateName[] = {"ID", "Template Name"};
	DefaultTableModel tablemodel = new DefaultTableModel(templateName, 0);
	

	public JInternalJobReportFrame(FilterValues filterValues,Vector<StartUPData> searchCriteria,Vector<StartUPData> searchColumn,String reportType) {
		setFilterValues(filterValues);
		setSearchCriteria(searchCriteria);
		setSearchColumn(searchColumn);
		setVisible(true);
		setReportType(reportType);
		setLayout(new GroupLayout());
		filters = new Vector<FilterBean>();
		javax.swing.plaf.InternalFrameUI ifu = this.getUI();
		((javax.swing.plaf.basic.BasicInternalFrameUI) ifu).setNorthPane(null);
		setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, null,null, null, null));
		add(getJToolBar1(), new Constraints(new Bilateral(8, 11, 2),new Leading(6, 26, 17, 17)));
		add(getJPanel1(), new Constraints(new Bilateral(8, 10, 0),new Bilateral(38, 11, 55)));
		

	}

	private JToolBar getJToolBar1() {
		if (jToolBar1 == null) {
			jToolBar1 = new JToolBar();
			jToolBar1.setFloatable(false);
			jToolBar1.add(getJButton0());
			jToolBar1.addSeparator();

			jToolBar1.add(getJButton1());
			jToolBar1.addSeparator();
			//jToolBar1.add(getJButton2());
		//	jToolBar1.addSeparator();
			jToolBar1.add(getJButton3());
			jToolBar1.addSeparator();
			jToolBar1.add(getJButton4());
			jToolBar1.addSeparator();
			jToolBar1.add(getJButton5());
			jToolBar1.addSeparator();
			jToolBar1.add(getStartLabelDate());
			jToolBar1.add(getStartDateText());
			jToolBar1.addSeparator();
			jToolBar1.add(getEndLabelDate());
			jToolBar1.add(getEndDateText());
			jToolBar1.addSeparator();
		}
		return jToolBar1;
	}
	JLabel jDateStartLabel = null;
	JLabel jDateEndLabel = null;
	JTextField textDate = null;
	JTextField endtextDate = null;
	private JLabel getStartLabelDate() {
		// TODO Auto-generated method stub
		jDateStartLabel = new JLabel();
		jDateStartLabel.setText("StartDate  ");
		return jDateStartLabel;
	}
	private JLabel getEndLabelDate() {
		// TODO Auto-generated method stub
		jDateEndLabel = new JLabel();
		jDateEndLabel.setText("EndDate  ");
		return jDateEndLabel;
	}
	private JTextField getStartDateText() {
		// TODO Auto-generated method stub
		textDate = new JTextField();
	//	JTextField.setText("   StartDate  ");
		return textDate;
	}
	private JTextField getEndDateText() {
		// TODO Auto-generated method stub
		endtextDate = new JTextField();
	//	JTextField.setText("   StartDate  ");
		return endtextDate;
	}
	private JPanel getJPanel1() {
		// TODO Auto-generated method stub
		searchPanel = new searchCriteriaPanel();
		searchPanel.setFvalues(getFilterValues());
		return searchPanel;
	}
	private JButton getJButton4() {
		if (jButton4 == null) {
			jButton4 = new JButton();
			jButton4.setMaximumSize(new Dimension(20, 18));
			// jButton0.setText("B0");
			
			jButton4.setIcon(ButtonsIconsFactory.getImageIcon(ButtonsIconsFactory.Buttons.CLEARALL));

			jButton4.setToolTipText("Clear All");
			jButton4.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {
	            	searchPanel.clearllCriteriaModel();
	            	searchPanel.getFilterBeanData().clear();
	            	job = null;
	            }
			});
			
		}
		return jButton4;
	}
	private JButton getJButton5() {
		if (jButton5 == null) {
			jButton5 = new JButton();
			jButton5.setMaximumSize(new Dimension(20, 18));
			// jButton0.setText("B0");
			
			jButton5.setIcon(ButtonsIconsFactory
					.getImageIcon(ButtonsIconsFactory.Buttons.DELETE));

			jButton5.setToolTipText("Delete Criteria");
			jButton5.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent arg0) {
	            	if(searchPanel.table.getSelectedRow() != -1)  {
	            		searchPanel.deleteRowCriteria(searchPanel.table.getSelectedRow());
	            		filters.remove(searchPanel.table.getSelectedRow());
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
	            			if(job !=null )
	    					reportPanel.getRemoteTask().saveUserJobsDetails(jobdets,job.getId());
	    				} catch (RemoteException e) {
	    					// 	
	    					e.printStackTrace();
	    				}
	            	}
	            }
			});
		}
		return jButton5;
	}
	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setMaximumSize(new Dimension(20, 18));
			// jButton0.setText("B0");
			jButton0.setForeground(Color.white);
			ImageIcon icon = new ImageIcon("/resources/icon/hide12.png");
			jButton0.setIcon(ButtonsIconsFactory
					.getImageIcon(ButtonsIconsFactory.Buttons.REFRESH));

			jButton0.setToolTipText("Execute");
		}jButton0.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
				String sql = "";
				String where = "";
				 where =  filterValues.createWhere(searchPanel.getFilterBeanData());
			try {
					//commonUTIL.showAlertMessage(sql);
				data = (Vector)	filterValues.remoteTask.selectTaskWhere(where);
				jobdataPanel.setDataCreteria(data,filterValues);
				jobdataPanel.setJobdetails(searchPanel.getFilterBeanData());
				
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			});
		return jButton0;
	}

	
	
	/*
	 * 1.  Create Table Model
	 * 2.  Create Renederer/Editor  in this  (remember getCellEditorValue method must return value )
	 * 3.  Create Combox
	 * 4.  assign sting [] for combox
	 * 5.  assign combox to editor
	 * 6.  when combox is assign to editor setEditorForRowCol  method is call where for each row and column wise editor is created.
	 * 7.  when column and row is clicked selectEditor is called.
	 * 8.  when combox box of that editor is clicked the jcombox mouse event is called. 
	 * 7.  in mouse event of combox, bean assign to the Table  model  is mapped and displayed in table
	 */
	private JButton getJButton3() {

		if (jButton3 == null) {
			jButton3 = new JButton();
			// jButton3.setText("B4");
			jButton3.setIcon(ButtonsIconsFactory
					.getImageIcon(ButtonsIconsFactory.Buttons.ADD));
			jButton3.setToolTipText("Add Search Criteria");
		}
		final JDialogBoxForChoice choice12 = new JDialogBoxForChoice(columnNamesListModel);
		jButton3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				choice12.jList3.setModel(columnNamesListModel);
				choice12.setLocationRelativeTo(choice12);
				choice12.setVisible(true);
			}
		});
		choice12.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				String ss = "";
				Object obj[] = choice12.getObj();
	  			int rowCount = searchPanel.getTableRowCount();
				String criteria[] = null;
				criteria = getFilterValues().convertVectortoSringArray(searchCriteria,"SearchCriteria");
               	for (int i = 0; i < obj.length; i++) {
               			ss = (String) obj[i];
						FilterBean filter = new FilterBean();
						filter.setColumnName(ss);
	  					searchPanel.addNewRow(filter);
	  					rowCount = searchPanel.getTableRowCount();
	  					if(ss.endsWith("ID")) {
	  						   final JTextField numberValues = getTextField(filter.getColumnName());
								DefaultCellEditor valuesCed = new DefaultCellEditor(numberValues);
	  							criteria = getFilterValues().getDateSearchCriteria();
	  							searchPanel.addRowEditor(rowCount -1, 1,  getJComboxBox(criteria) ,"Criteria");
	  							searchPanel.addRowEditor(rowCount -1, 2,  getTextField(ss) ,"Values");
	  							searchPanel.addRowEditor(rowCount -1, 3,  getJComboxBox(andORColumnValus) ,"And/Or");
	  							
	  							
	  					} 
	  					
	  									final JTextField numberValues = getTextField(filter.getColumnName());
	  									DefaultCellEditor valuesCed = new DefaultCellEditor(numberValues);
	  									 
	  									searchPanel.addRowEditor(rowCount -1, 1, getJComboxBox(criteria),"Criteria");
										searchPanel.addRowEditor(rowCount-1, 2, numberValues,"Values");
										searchPanel.addRowEditor(rowCount -1, 3,  getJComboxBox(andORColumnValus) ,"And/Or");
			  							
						
	  							
	  					}  
								 	
									
					
					
				
			}
		});
		return jButton3;
	}
	
	public MultiSelectListExComboBox getMultiSelectListExComboBox(String val []) {
		final MultiSelectListExComboBox values = new MultiSelectListExComboBox(val, String[].class);
		values.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (values.getSelectedIndex() != -1) {
					// TODO Auto-generated method stub
					String attributeName = values.getSelectedItem().toString();
					String idSelected ="";
					int ids [] = values.getSelectedIndices();
					String ss = "";
					Object obj[] = values.getSelectedObjects();
					for (int i = 0; i < values.getSelectedObjects().length; i++) {
						    ss = ss + (String) obj[i] + ",";
						    idSelected = idSelected + ids[i] +",";
					}
					int i = searchPanel.table.getSelectedRow();
					FilterBean filler = searchPanel.getFilterBeanAtRow(i);
					filler.setColumnValues(ss.substring(0, ss.length()-1));
					filler.setIdSelected(idSelected.substring(0, idSelected.length()-1));
					searchPanel.removeFilterBeanAtRow(i);
					searchPanel.addFilterBeanAt(i, filler);

				}
			}
		});
		return values;
	}
	public MultiSelectListExComboBox getMultiSelectListExComboBox(String val [],String value) {
		final MultiSelectListExComboBox values = new MultiSelectListExComboBox(val, String[].class);
		values.setSelectedItem(value);
		values.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (values.getSelectedIndex() != -1) {
					// TODO Auto-generated method stub
					String attributeName = values.getSelectedItem().toString();
					String idSelected ="";
					int ids [] = values.getSelectedIndices();
					String ss = "";
					Object obj[] = values.getSelectedObjects();
					for (int i = 0; i < values.getSelectedObjects().length; i++) {
						    ss = ss + (String) obj[i] + ",";
						    idSelected = idSelected + ids[i] +",";
					}
					int i = searchPanel.table.getSelectedRow();
					FilterBean filler = searchPanel.getFilterBeanAtRow(i);
					filler.setColumnValues(ss.substring(0, ss.length()-1));
					filler.setIdSelected(idSelected.substring(0, idSelected.length()-1));
					searchPanel.removeFilterBeanAtRow(i);
					searchPanel.addFilterBeanAt(i, filler);

				}
			}
		});
		return values;
	}
	
	 public JComboBox getJComboxBox(final String [] criteria) {
		final JComboBox criteriaC = new JComboBox(criteria);
			criteriaC.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if (criteriaC.getSelectedIndex() > 0) {
						// TODO Auto-generated method stub
						String attributeName = criteriaC.getSelectedItem().toString();
						int i = searchPanel.table.getSelectedRow();
						int c = searchPanel.table.getSelectedColumn();
						if(i != -1) {
						FilterBean filler = searchPanel.getFilterBeanAtRow(i);
						if(c == 1)
							filler.setSearchCriteria(attributeName);
							if(c == 3)
						filler.setAnd_or(attributeName);
						searchPanel.removeFilterBeanAtRow(i);
						searchPanel.addFilterBeanAt(i, filler);
						}

					}
				}
			});
			return criteriaC;
		 
	 }
	 public JComboBox getJComboxBox(final String [] criteria,final String value) {
			final JComboBox criteriaC = new JComboBox(criteria);
			criteriaC.setSelectedItem(value);
				criteriaC.addItemListener(new ItemListener() {

					@Override
					public void itemStateChanged(ItemEvent e) {
						if (criteriaC.getSelectedIndex() != -1) {
							// TODO Auto-generated method stub
							String attributeName = criteriaC.getSelectedItem().toString();
							int i = searchPanel.table.getSelectedRow();
							int c = searchPanel.table.getSelectedColumn();
							FilterBean filler = searchPanel.getFilterBeanAtRow(i);
							if(c == 1)
							filler.setSearchCriteria(attributeName);
							if(c == 3)
								filler.setAnd_or(attributeName);
							searchPanel.removeFilterBeanAtRow(i);
							searchPanel.addFilterBeanAt(i, filler);

						}
					}
				});
				
				return criteriaC;
			 
		 }
	 
	 public JTextField getTextField(final String fieldName) {
			final JTextField criteriaC = new JTextField( );
			criteriaC.setEditable(false);
				criteriaC.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						
						//addDialogBox(criteriaC,fieldName);
							// TODO Auto-generated method stub
							

						}
					
				});
				criteriaC.addFocusListener(new FocusAdapter() {
					public void focusLost(FocusEvent event) {
						if(fieldName.endsWith("ID")) {
						try {
							
							String number = criteriaC.getText();
							//int tradeId = Integer.parseInt(number);
							int i = searchPanel.table.getSelectedRow();
							FilterBean filler = (FilterBean) searchPanel.getFilterBeanAtRow(i);
							filler.setColumnValues(number);
							filler.setIdSelected("0"); // this are just // combox ids
							searchPanel.removeFilterBeanAtRow(i);
							searchPanel.addFilterBeanAt(i, filler);
							
						} catch (NumberFormatException ec) {
							 commonUTIL.showAlertMessage("Enter Number only");
							 criteriaC.setText("");
						}
						
					} else {
						String values = criteriaC.getText();
						//int tradeId = Integer.parseInt(number);
						int i = searchPanel.table.getSelectedRow();
						if(i != -1 || !commonUTIL.isEmpty(filters)) {
						FilterBean filler = (FilterBean) searchPanel.getFilterBeanAtRow(i);
						filler.setColumnValues(values);
						filler.setIdSelected("0"); // this are just // combox ids
						searchPanel.removeFilterBeanAtRow(i);
						searchPanel.addFilterBeanAt(i, filler);
						}
					}
					}
				});
				criteriaC.addMouseListener(new MouseAdapter() {
								 
					@Override
					public void mouseClicked(MouseEvent arg0) {
						// TODO Auto-generated method stub
					//	addDialogBox(criteriaC,fieldName);
					}
				});
					
				return criteriaC;
			 
		 }
	 private void setTemplates(DefaultListModel templates,DefaultListModel templates2,String selvalues,String [] values) {
		 if(!commonUTIL.isEmpty(values)) {
			 String sValues [] = null;
			 if(!commonUTIL.isEmpty(selvalues) && selvalues.contains(",")) {
				 sValues = selvalues.split(",");
				 
			 }
		    		 
		        	int i =0;
		        for(int s=0;s< values.length;s++)
		        		 
		        	templates.addElement(values[s]);
		        		i++;
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
	 public JComboBox getDateCriteriaJComboxBox(final String [] criteria) {
		 final JComboBox criteriaD = new JComboBox(criteria);
			criteriaD.addItemListener(new ItemListener() {

				@Override
				public void itemStateChanged(ItemEvent e) {
					if (criteriaD.getSelectedIndex() != -1) {
						// TODO Auto-generated method stub
						String attributeName = criteriaD.getSelectedItem().toString();
						
						int i = searchPanel.table.getSelectedRow();
						if(i != -1) {
						FilterBean filler = searchPanel.getFilterBeanAtRow(i);
						filler.setSearchCriteria(attributeName);
						searchPanel.removeFilterBeanAtRow(i);
						searchPanel.addFilterBeanAt(i, filler);
						}

					}
				}
			});
				return criteriaD;
			 
		 }
	public DateCellEditor12 getDateCellEditor(final String name) {
		DateCellEditor12 datece2 = new DateCellEditor12();
		datece2.addCellEditorListener(new CellEditorListener() {
			@Override
			public void editingCanceled(ChangeEvent e) {
													}

			@Override
			public void editingStopped(ChangeEvent e) {
				
			DateCellEditor12 dd =  (DateCellEditor12)e.getSource();								
			int i = searchPanel.table.getSelectedRow();									
			FilterBean filler = searchPanel.getFilterBeanAtRow(i);
			 if(name.equalsIgnoreCase("Values")) {
			     filler.setColumnValues(dd.getCellEditorValue().toString());
			} else {
				 filler.setAnd_or(dd.getCellEditorValue().toString());
			}
			searchPanel.removeFilterBeanAtRow(i);
			searchPanel.addFilterBeanAt(i, filler);
												}
        	
        });
		return datece2;
		
	}
	
	
	private JButton getJButton2() {
		if (jButton2 == null) {
			jButton2 = new JButton();
			// jButton2.setText("B3");
			jButton2.setIcon(ButtonsIconsFactory.getImageIcon(ButtonsIconsFactory.Buttons.DELETE));
			jButton2.setToolTipText("Load Template");
		}jButton2.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
				//tablemodel
			//	
				final JDialogTable showTemplates = new JDialogTable(tablemodel);
				showTemplates.setVisible(true);
				showTemplates.setLocationRelativeTo(jButton2);
				
				showTemplates.jTable1.addMouseListener(new java.awt.event.MouseAdapter() {

					public void mouseClicked(MouseEvent e) {

						String jobid = showTemplates.jTable1.getValueAt(showTemplates.jTable1.getSelectedRow(), 0).toString();
						
						try {
							job = new UserJob();
							job.setId(new Integer(jobid).intValue());
						Vector<UserJobsDetails>	jobdetails = (Vector) reportPanel.getRemoteTask().getDetailsJob(new Integer(jobid).intValue());
						loadJobs(jobdetails);
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						showTemplates.dispose();

					}

					
			});
				
			}
		});
		return jButton2;
	}
	 
	private JButton getJButton1() {
		if (jButton1 == null) {
			jButton1 = new JButton();
			//jButton1.setText("B1");
			// ImageIcon icon = new ImageIcon("/resources/icon/save.png");
			// jButton1.setIcon(icon);
			jButton1.setIcon(ButtonsIconsFactory		.getImageIcon(ButtonsIconsFactory.Buttons.SMALLSAVE));
			jButton1.setToolTipText("Save"); 

		}
		jButton1.addActionListener(new AbstractAction() {
			public void actionPerformed(ActionEvent e) {
			//	System.out.println(job.getId());
				saveJobDetails();
			}
		});
		return jButton1;
	}

	/**
	 * @param searchColumn
	 *            the searchColumn to set
	 */
	public void setSearchColumn(Vector<StartUPData> searchColumn) {
		this.searchColumn = searchColumn;
		if (searchColumn.size() > 0 && searchColumn != null) {
			Iterator<StartUPData> it = searchColumn.iterator();
			columnNamesListModel = new DefaultListModel();
			while (it.hasNext()) {
				columnNamesListModel.addElement(((StartUPData) it.next()).getName());
			}

		} 

	}
	public void loadTemplates(int userID) {
		Vector templates = null;
       try {
    	   templates = (Vector) reportPanel.getRemoteTask().getUserJob(userID);
    	   if(commonUTIL.isEmpty(templates)) {
    		   return;
    	   }
    	   for(int i=0;i<templates.size();i++) {
    		   UserJob userjobs = (UserJob) templates.get(i);
    	       tablemodel.insertRow(i,
						new Object[] { userjobs.getId(), userjobs.getTreeNodeName() });
    	   }
    	   
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
	}

	/**
	 * @param searchPanel
	 *            the searchPanel to set
	 */
	public void setSearchPanel(searchCriteriaPanel searchPanel) {
		this.searchPanel = searchPanel;
	}

	/**
	 * @param searchCriteria
	 *            the searchCriteria to set
	 */
	public void setSearchCriteria(Vector<StartUPData> searchCriteria) {
		this.searchCriteria = searchCriteria;
	}

	/**
	 * @return the filterValues
	 */
	private FilterValues getFilterValues() {
		return filterValues;
	}

	/**
	 * @param filterValues
	 *            the filterValues to set
	 */
	public void setFilterValues(FilterValues filterValues) {
		this.filterValues = filterValues;
	}
    /**
	 * @return the user
	 */
	private Users getUser() {
		return user;
	}

	Users user = null;
	public void setUser(Users user) {
		// TODO Auto-generated method stub
		this.user = user;
		//loadTemplates(user.getId());
	}
	public void saveTemplate(String templateName,int userID) {
		if(!commonUTIL.isEmpty(templateName)) {
			job = new UserJob();
			job.setId(0);
			job.setTabid(0);
			job.setUserID(user.getId());
			job.setType(getReportType().toUpperCase());
			job.setTreeNodeName(templateName);
			setTemplateFrame(templateName);
			try {
				job = (UserJob) reportPanel.getRemoteTask().saveUserJob(job);
				tablemodel.insertRow(tablemodel.getRowCount(), new Object[]{ job.getId(), job.getTreeNodeName()} );
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
    DockableFrame dFrame = null;
	public void setTemplateFrame(DockableFrame frame) {
		// TODO Auto-generated method stub
		dFrame = frame;
		
	}
	public void setTemplateFrame(String tempalateName) {
		dFrame.setTitle(tempalateName);
	}
	private void loadJobs(Vector<UserJobsDetails> jobdetails) {
		// TODO Auto-generated method stub
		if(!commonUTIL.isEmpty(jobdetails)) {
			searchPanel.clearllCriterial();
			for(int i=0;i<jobdetails.size();i++) {
				UserJobsDetails jd = jobdetails.get(i);
				FilterBean bean = new FilterBean();
				
				bean.setSearchCriteria(jd.getCriteria());
				bean.setColumnName(jd.getColumnName());
				bean.setColumnValues(jd.getValues());
				bean.setAnd_or(jd.getAnd_or());
				bean.setIdSelected(jd.getFilterValues());
				searchPanel.addNewRow(bean);
				int rowCount = searchPanel.getTableRowCount();
				String criteria[] = null;
				criteria = getFilterValues().convertVectortoSringArray(searchCriteria,"SearchCriteria");
				if(bean.getColumnName().endsWith("ID")) {
						criteria = getFilterValues().getDateSearchCriteria();
						searchPanel.addRowEditor(rowCount -1, 1, getJComboxBox(criteria),"Criteria");
						final JTextField numberValues = getTextField(bean.getColumnName());
						DefaultCellEditor valuesCed = new DefaultCellEditor(numberValues);
					
						searchPanel.addRowEditor(rowCount -1, 2, numberValues,"Values");
						searchPanel.addRowEditor(rowCount -1, 3,  getJComboxBox(andORColumnValus) ,"And/Or");
							
					//	searchPanel.addRowEditor(rowCount -1, 3,  getDateCellEditor("And/Or"),"And/Or");	
				} else {
					final JTextField numberValues = getTextField(bean.getColumnName());
						DefaultCellEditor valuesCed = new DefaultCellEditor(numberValues);
						 
						searchPanel.addRowEditor(rowCount -1, 1, getJComboxBox(criteria),"Criteria");
					searchPanel.addRowEditor(rowCount-1, 2, numberValues,"Values");
					searchPanel.addRowEditor(rowCount -1, 3,  getJComboxBox(andORColumnValus) ,"And/Or");
						
				}
				
				//bean.setIdSelected(jd.);
			}
		}
		
		
		
	}

	public void setUserJob(UserJob job2) {
		job = job2;
		Vector<UserJobsDetails> jobdetails;
		
			jobdetails = (Vector)job.getDetailsJobs();// filterValues.remoteTask.getDetailsJob(job.getId());
			
			loadJobs(jobdetails);
		
		
		// TODO Auto-generated method stub
		
	}

	public void saveJobDetails() {
		// TODO Auto-generated method stub
		if(job == null) {
			commonUTIL.showAlertMessage("Select Template");
			return;
	}
		
		int rowCount =searchPanel.table.getRowCount();
			Vector<UserJobsDetails> jobdets = new Vector<UserJobsDetails>();
			for(int i=0;i<searchPanel.getFilterBeanData().size();i++) {
       			FilterBean filterBean = searchPanel.getFilterBeanData().get(i);
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
			try {
				
				//if(!reportPanel.getColumnSQL().equalsIgnoreCase(job.getSql())) {
					//job.setSql(reportPanel.getColumnSQL());
					//filterValues.remoteTask.updateJob(job);
				//}
					filterValues.remoteTask.saveUserJobsDetails(jobdets,job.getId());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
}
