package apps;

import java.awt.Color;
import java.awt.Component;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.Hashtable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import apps.window.staticwindow.StartDataUPWindow;

import util.ClassInstantiateUtil;
import util.commonUTIL;
import dsServices.ServiceManager;

//VS4E -- DO NOT REMOVE THIS LINE!
public class MainApp extends JFrame {

	private static final long serialVersionUID = 1L;
	private JLabel jLabel0;
	private JTextField jTextField0;
	private JTable jTable0;
	private JScrollPane jScrollPane0;
	private JPanel jPanel0;
	Hashtable  data = new Hashtable();
	Hashtable  stareddata = new Hashtable();
	
	boolean selectedItem = false;
	boolean stopitem = false;
	public MainApp() {
		initComponents();
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Bilateral(12, 12, 0), new Bilateral(9, 12, 0)));
		setSize(438, 300);
	//	data.put("ServerControllerImp","dsServices.ServerControllerImp");
		data.put("AccountManager","dsManager.AccountManagerStartup");
		data.put("TransferManager","dsManager.TransferManagerStartup");
		data.put("PositionManager","dsManager.PositionManagerStartup");
		data.put("MessageManager","dsManager.MessageManagerStartup");
		data.put("LimitManager","dsManager.LimitManagerStartup");
		data.put("MainApp","dsManager.MainAppStartup");
	}

	private JButton getJButton0() {
		if (jButton0 == null) {
			jButton0 = new JButton();
			jButton0.setText("Stop");
		}jButton0.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ServiceManager	manager = null;
				int i = jTable0.getSelectedRow();
				String serviceName = (String) jTable0.getValueAt(i, 1);
				;
				synchronized(_managers) {
						manager = (ServiceManager) _managers.get((String) data.get(serviceName));
		       		manager.stop();
		       		_managers.remove((String) data.get(serviceName));
		       		stareddata.remove((String) data.get(serviceName));
		       		stopitem = true;
		       	  }
		       		if(manager == null) {
		       			commonUTIL.showAlertMessage("Manager Not start");
		       		}
			}
		})

			;
		
		return jButton0;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJLabel0(), new Constraints(new Leading(18, 81, 10, 10), new Leading(249, 10, 10)));
			jPanel0.add(getJScrollPane0(), new Constraints(new Leading(15, 391, 10, 10), new Leading(26, 209, 12, 12)));
			jPanel0.add(getJTextField0(), new Constraints(new Leading(113, 201, 10, 10), new Leading(247, 12, 12)));
			jPanel0.add(getJButton0(), new Constraints(new Leading(322, 64, 10, 10), new Leading(244, 12, 12)));
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
		//	jTable0 = new JTable();
			String col[] = {"ID","ServiceManager"};
			DefaultTableModel model = new DefaultTableModel(col, 0);
		//	jTable0.setModel(model);
		//	model.insertRow(0, new Object[]{"0","ServerControllerImp"});
			model.insertRow(0, new Object[]{"1","AccountManager"});
			model.insertRow(1, new Object[]{"2","TransferManager"});
			model.insertRow(1, new Object[]{"3","PositionManager"});
			model.insertRow(1, new Object[]{"4","MessageManager"});
			model.insertRow(2, new Object[]{"5","MainApp"});
		//	model = new TableModelUtil(data,cols,remoteAccount);
			  jTable0 = new JTable( model )
				{
					public Component prepareRenderer(TableCellRenderer renderer, int row, int column)
					{
						Component c = super.prepareRenderer(renderer, row, column);

						//  Color row based on a cell value

						if (!isRowSelected(row))
						{
							c.setBackground(getBackground());
							int modelRow = convertRowIndexToModel(row);
						//	String type = (String)getModel().getValueAt(modelRow, 5);
							if (selectedItem) {
								c.setBackground(Color.gray);
								selectedItem = false;
							}
							if(stopitem) {
								c.setBackground(Color.blue);
								stopitem = false;
							}
						//	if ("Sell".equals(type)) c.setBackground(Color.YELLOW);
							
							
						}

						return c;
					}
				};
		}jTable0.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int i = jTable0.getSelectedRow();
				String serviceName = (String) jTable0.getValueAt(i, 1);
				
				if(stareddata.containsKey((String) data.get(serviceName))) {
					commonUTIL.showAlertMessage("Service already running");
				} else {
				getManagerInstance((String) data.get(serviceName)).start();
				}
				selectedItem = true;
				 
				
			}
		});
		return jTable0;
	}

	private JTextField getJTextField0() {
		if (jTextField0 == null) {
			jTextField0 = new JTextField();
			jTextField0.setText("jTextField0");
		}
		return jTextField0;
	}

	private JLabel getJLabel0() {
		if (jLabel0 == null) {
			jLabel0 = new JLabel();
			jLabel0.setText("Add Serivce");
		}
		return jLabel0;
	}

	private static void installLnF() {
		try {
			String lnfClassname = PREFERRED_LOOK_AND_FEEL;
			if (lnfClassname == null)
				lnfClassname = UIManager.getCrossPlatformLookAndFeelClassName();
			UIManager.setLookAndFeel(lnfClassname);
		} catch (Exception e) {
			System.err.println("Cannot install " + PREFERRED_LOOK_AND_FEEL
					+ " on this platform:" + e.getMessage());
		}
	}
	
	public ServiceManager  getManagerInstance(String className) {
		ServiceManager manager = null;
	
		 try {
       	  synchronized(_managers) {
       		manager = (ServiceManager) _managers.get(className);
       		if(manager != null)
       		  	stareddata.put(className,manager);
     
       	  }
       		if(manager == null) {
       	        Class class1 =    ClassInstantiateUtil.getClass(className,true);
       	        manager =  (ServiceManager) class1.newInstance();
       	        if(manager != null) {
       	        	_managers.put(className, manager);
       	        	stareddata.put(className,manager);
       	        }
       	     
       	  } 
       	
       	     //  productWindow = (BondPanel) 
	        } catch (Exception e) {
	        	commonUTIL.displayError("MainApp", "getManagerInstance <<<<< not able to Start ClassName ", e);
	        	return manager;
	        }
		 
		 return manager;
	}
	static protected Hashtable _managers = new Hashtable();
	private JButton jButton0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	
	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				MainApp frame = new MainApp();
				frame.setDefaultCloseOperation(MainApp.EXIT_ON_CLOSE);
				URL imgURL =  imgURL = this.getClass().getResource("/resources/icon/sql.jpg");
    			frame.setIconImage(Toolkit.getDefaultToolkit()
    		      		   .getImage(imgURL));
				frame.setTitle("Cosmos MainApp");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
