package apps.window.util.windowUtil;
import java.awt.Color;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsConfiguration;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.commonUTIL;

import com.jidesoft.combobox.DateComboBox;

import constants.CommonConstants;

import apps.window.reportwindow.TradeSearchPanel;
import beans.FilterBean;
import beans.StartUPData;
import beans.Trade;

import dsServices.RemoteBOProcess;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;


//VS4E -- DO NOT REMOVE THIS LINE!
public class openTradeDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	
	
	private JPanel jPanel2;
	private JPanel jPanel0;
	public JTable jTable1;
	private JScrollPane jScrollPane0;
	RemoteTrade remoteTrade = null;
	openSearchPanel searchPanel = null;
	/**
	 * @return the remoteTrade
	 */
	public RemoteTrade getRemoteTrade() {
		return remoteTrade;
	}

	/**
	 * @param remoteTrade the remoteTrade to set
	 */
	public void setRemoteTrade(RemoteTrade remoteTrade) {
		this.remoteTrade = remoteTrade;
	}

	/**
	 * @return the remoteRefData
	 */
	public RemoteReferenceData getRemoteRefData() {
		return remoteRefData;
	}

	/**
	 * @param remoteRefData the remoteRefData to set
	 */
	public void setRemoteRefData(RemoteReferenceData remoteRefData) {
		this.remoteRefData = remoteRefData;
	}

	RemoteReferenceData remoteRefData = null;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	public openTradeDialog(Window parent) {
		super(parent);
		initComponents();
	}

	public openTradeDialog(Dialog parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public openTradeDialog(Dialog parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	public openTradeDialog(Window parent, String title,RemoteTrade remoteTrade,RemoteReferenceData remoteRef,RemoteBOProcess remoteBo,RemoteTask remoteTask) {
		super(parent, title);
		this.remoteTrade = remoteTrade;
		this.remoteRefData = remoteRef;
		searchPanel = new  openSearchPanel(remoteTrade,remoteRef,remoteBo,remoteTask);
		
		initComponents();
		
	}

	public openTradeDialog(Window parent, String title,
			ModalityType modalityType, GraphicsConfiguration arg) {
		super(parent, title, modalityType, arg);
		initComponents();
	}

	public openTradeDialog(Window parent, String title,
			ModalityType modalityType) {
		super(parent, title, modalityType);
		initComponents();
	}

	public openTradeDialog(Window parent, String title) {
		super(parent, title);
		initComponents();
	}

	public openTradeDialog(Window parent, ModalityType modalityType) {
		super(parent, modalityType);
		initComponents();
	}

	public openTradeDialog(Frame parent, String title) {
		super(parent, title);
		initComponents();
	}

	public openTradeDialog(Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public openTradeDialog(Frame parent) {
		super(parent);
		initComponents();
	}

	public openTradeDialog() {
		initComponents();
	}

	public openTradeDialog(Dialog parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	public openTradeDialog(Dialog parent) {
		super(parent);
		initComponents();
	}

	public openTradeDialog(Frame parent, String title, boolean modal,
			GraphicsConfiguration arg) {
		super(parent, title, modal, arg);
		initComponents();
	}

	public openTradeDialog(Frame parent, String title, boolean modal) {
		super(parent, title, modal);
		initComponents();
	}

	private void initComponents() {
		setFont(new Font("Dialog", Font.PLAIN, 12));
		setResizable(false);
		setBackground(Color.white);
		setForeground(Color.black);
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Leading(5, 830, 10, 10), new Leading(8, 379, 10, 10)));
		setSize(880, 410);
		
		searchPanel.jButton0.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Vector<FilterBean> beans = searchPanel.searchCriteria();
				if(!commonUTIL.isEmpty(beans)) {
				String where =  searchPanel.getFilterValues().createWhere(beans,"Trade");
				if (where.equals(CommonConstants.BLANKSTRING)) {
					commonUTIL.showAlertMessage("Please select atleast one criteria");
					return;
				}
				try {
					jTable1.removeAll();
					String col [] = {"TradeID","ProductType","ProductSubType"};
					DefaultTableModel model = new DefaultTableModel(col, 0) {
						@Override
						public boolean isCellEditable(int row, int column) {
							// all cells false
							return false;
						}

					};
					Vector vector = (Vector) remoteTrade.selectforOpen("where " + where);
					Iterator it = vector.iterator();
					int i = 0;
					while (it.hasNext()) {
						Trade trade = (Trade) it.next();

						model.insertRow(i,
								new Object[] { trade.getId(), trade.getProductType(),trade.getTradedesc1() });
						i++;
					}
					jTable1.setModel(model);

				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
			}
		});
		
		
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTable0());
		}
		return jScrollPane0;
	}

	private JTable getJTable0() {
		if (jTable1 == null) {
			jTable1 = new JTable();
			String col [] = {"TradeID","ProductType","ProductSubType"};
			DefaultTableModel model = new DefaultTableModel(col, 0);
			jTable1.setModel(model);
		}
		return jTable1;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(searchPanel, new Constraints(new Leading(4, 253, 10, 10), new Leading(2, 370, 10, 10)));
			jPanel0.add(getJPanel2(), new Constraints(new Leading(263, 557, 10, 10), new Leading(5, 367, 12, 12)));
		}
		return jPanel0;
	}

	private JPanel getJPanel2() {
		if (jPanel2 == null) {
			jPanel2 = new JPanel();
			jPanel2.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel2.setLayout(new GroupLayout());
			jPanel2.add(getJScrollPane0(), new Constraints(new Leading(6, 543, 10, 10), new Leading(5, 352, 10, 10)));
		}
		return jPanel2;
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
	 protected void processDomainData(javax.swing.DefaultComboBoxModel combodata, Vector<String> domainData) {
 		
 		Vector ledata;
 		
 				//String roleType = " role like 'PO' ";
 			ledata =domainData;

 			Iterator it = ledata.iterator();
 			int p = 0;
 			combodata.insertElementAt("", p++);
 			while (it.hasNext()) {

 				StartUPData stData = (StartUPData) it.next();

 				combodata.insertElementAt(stData.getName(), p++);
 				
 			
 			}

 		

 	}
      
	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		installLnF();
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				openTradeDialog dialog = new openTradeDialog();
				dialog.setDefaultCloseOperation(openTradeDialog.DISPOSE_ON_CLOSE);
				dialog.setTitle("openTradeDialog");
				dialog.setLocationRelativeTo(null);
				dialog.getContentPane().setPreferredSize(dialog.getSize());
				dialog.pack();
				dialog.setVisible(true);
			}
		});
	}

}
