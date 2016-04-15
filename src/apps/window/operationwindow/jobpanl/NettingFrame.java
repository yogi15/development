package apps.window.operationwindow.jobpanl;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellRenderer;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.commonUTIL;

import dsEventListener.TransferEvtListener;
import dsEventProcessor.TaskEventProcessor;
import dsServices.RemoteBOProcess;
import dsServices.RemoteProduct;

import beans.NettingConfig;
import beans.Product;
import beans.Task;
import beans.Trade;
import beans.Transfer;
import beans.Users;
import beans.WFConfig;


//VS4E -- DO NOT REMOVE THIS LINE!
public class NettingFrame extends JFrame {

	
	private static final long serialVersionUID = 1L;
	JPopupMenu popupMenu = new JPopupMenu();
	String col[] = { "Transfer ID", "Trade id", "EventType", "TranferType",
			"AMOUNT", "Settlement", "Quantity", "Product", "Status",
			"SettlementDate", "Currency", "ValueDate", "Payer", "Receiver" };
	private Vector data = new Vector();
	private Vector settlementdata = new Vector();
	TableModelUtil model = null;
	TableModelUtil settlementmodel = null;
//	RemoteBOProcess remoteBO = null;
	RemoteProduct remoteProduct = null;
	
	Users users = null;
	public Users getUsers() {
		return users;
	}

	
	JMenu actions = new JMenu("Action");
	public JPopupMenu getPopupMenu() {
		return popupMenu;
	}
	private JTable jTable2;
	private JScrollPane jScrollPane2;
	private JPanel jPanel1;
	   Hashtable<String,JTable> dynTables = new  Hashtable<String,JTable>();
	private JTabbedPane jTabbedPane0;
	private JPanel jPanel0;
	private static final String PREFERRED_LOOK_AND_FEEL = "javax.swing.plaf.metal.MetalLookAndFeel";
	TransferEvtListener transferEvts = null;
	public NettingFrame() {
		initComponents();
		transferEvts = new TransferEvtListener("localhost",commonUTIL.getLocalHostName(),"");
		transferEvts.start(transferEvts);
		transferEvts.setNettingEvtListener(this);
	}

	private void initComponents() {
		setLayout(new GroupLayout());
		add(getJPanel0(), new Constraints(new Bilateral(4, 6, 1025), new Bilateral(6, 7, 10, 227)));
		setSize(1146, 240);
		getPopupMenu();
		popupMenu.add(actions);
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJTabbedPane0(), new Constraints(new Bilateral(5, 12, 5), new Leading(7, 211, 10, 10)));
		}
		return jPanel0;
	}

	private JTabbedPane getJTabbedPane0() {
		if (jTabbedPane0 == null) {
			jTabbedPane0 = new JTabbedPane();
			jTabbedPane0.addTab("SettlementPanel", getJPanel1());
		}
		return jTabbedPane0;
	}

	
	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getJScrollPane2(), new Constraints(new Leading(9, 1010,
					10, 10), new Leading(6, 187, 10, 10)));
		}
		return jPanel1;
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
			Vector nettedTransfers = new Vector();
			
			TableModelUtil mutil = new TableModelUtil(nettedTransfers, col, null, false);
			jTable2.setModel(mutil);
		}
		jTable2.addMouseListener(new java.awt.event.MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int selectRow = jTable2.getSelectedRow();
				if (selectRow >= 0) {
					Transfer transfer = (Transfer) settlementdata
							.get(selectRow);
					Vector nettedTransfers = null;
					
						nettedTransfers = (Vector) filter.getNettedTransfers(transfer.getId());
					
					boolean flag = true;

					for (int i = 0; i < jTabbedPane0.getTabCount(); i++) {
						String name = jTabbedPane0.getTitleAt(i);
						if (name.equalsIgnoreCase((transfer.getDeliveryDate())))
							flag = false;
					}

					if (flag)
						jTabbedPane0.addTab(transfer.getDeliveryDate(),
								getDynJPanel(nettedTransfers, users.getId(),transfer.getDeliveryDate()));
				}

			}
		});

		return jTable2;
	}
	public void fillJTabel(Vector transferData,Task task) {
		this.settlementdata = transferData;
		model = new TableModelUtil(settlementdata, col,  task, false);
		jTable2.setModel(model);
		//trade = getTrade();
		
	}
	protected JPanel getDynJPanel(Vector nettedTransfers, int userID,String deliveryDate) {
		// TODO Auto-generated method stub
		JPanel panel = new JPanel();
		panel.setLayout(new GroupLayout());
		JScrollPane jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(getTableDyn(nettedTransfers, userID,deliveryDate));
		panel.add(jScrollPane, new Constraints(new Leading(9, 1240, 10, 10),
				new Leading(6, 187, 10, 10)));

		return panel;
	}

	protected JTable getTableDyn(final Vector nettedTransfers, int userID,String deliveryDate) {
		
		TableModelUtil mutil = new TableModelUtil(nettedTransfers, col,
				 null, false);
		final JTable jTable = new JTable(mutil) {
			public Component prepareRenderer(TableCellRenderer renderer,
					int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);

				// Color row based on a cell value

				if (!isRowSelected(row)) {
					c.setBackground(getBackground());
					int modelRow = convertRowIndexToModel(row);
					String type = (String) getModel().getValueAt(modelRow,
							8);
					
					if ("SETTLED".equals(type))
						c.setBackground(Color.pink);

				}

					return c;
				}
			};
		jTable.addMouseListener(new java.awt.event.MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isRightMouseButton(e) == true) {
					int row = jTable.rowAtPoint(e.getPoint());
					int selectrow[] = jTable.getSelectedRows();
					Transfer transfer = (Transfer) nettedTransfers.get(row);
					Vector actions = (Vector) filter.getActionOnTransfer(transfer.getId());
					fillActioninPopupMenu(
							actions,nettedTransfers,selectrow,
							 users.getId(), row);
					/*for(int i=0;i<selectrow.length;i++) {
						Transfer transfer = (Transfer) nettedTransfers.get(selectrow[i]);
						
						fillActioninPopupMenu(
								(Vector) filter.getActionOnTransfer(transfer.getId()),
								transfer, users.getId(), row);
						popupMenu.show(e.getComponent(), e.getX(), e.getY());
					}*/
					
					/*Transfer transfer = (Transfer) nettedTransfers.get(row);
					
						fillActioninPopupMenu(
								(Vector) filter.getActionOnTransfer(transfer.getId()),
								transfer, users.getId(), row); */
						popupMenu.show(e.getComponent(), e.getX(), e.getY()); 
					

				}
			}
		});
		dynTables.put(deliveryDate,jTable);
		return jTable;
	}
	
	protected void updateTransferOnAction(Transfer transfer, int userID, int row) {
		// TODO Auto-generated method stub
		
			filter.updateTransferAndPublissh(transfer, userID);
		//	model.udpateValueAt(transfer, row, jTable0.getSelectedColumn());
		

	}

	protected void fillActioninPopupMenu(Vector onlyAction,
			final Transfer transfer, final int userID, final int row) {
		// TODO Auto-generated method stub

		actions.removeAll();
		if (onlyAction == null || onlyAction.isEmpty())
			return;
		JMenuItem actionItem[] = new JMenuItem[onlyAction.size()];
		for (int a = 0; a < onlyAction.size(); a++) {
			WFConfig wf = (WFConfig) onlyAction.get(a);
			actionItem[a] = new JMenuItem(wf.getAction());
			actions.add(actionItem[a]);
			actionItem[a].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String action = arg0.getActionCommand().toString();
					transfer.setAction(action);
					updateTransferOnAction(transfer, userID, row);
				}
			}); // TODO Auto-generated method stub

		}

	}
	
	protected void fillActioninPopupMenu(Vector onlyAction,final Vector nettedTransfers,
			final int [] transfersid, final int userID, final int row) {
		// TODO Auto-generated method stub

		actions.removeAll();
		if (onlyAction == null || onlyAction.isEmpty())
			return;
		JMenuItem actionItem[] = new JMenuItem[onlyAction.size()];
		for (int a = 0; a < onlyAction.size(); a++) {
			WFConfig wf = (WFConfig) onlyAction.get(a);
			actionItem[a] = new JMenuItem(wf.getAction());
			actions.add(actionItem[a]);
			actionItem[a].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					for(int t=0;t<transfersid.length;t++) {
						Transfer transfer = (Transfer) nettedTransfers.get(Integer.valueOf(transfersid[t]));
					String action = arg0.getActionCommand().toString();
					transfer.setAction(action);
					updateTransferOnAction(transfer, userID, row);
				}
				}
			}); // TODO Auto-generated method stub

		}

	}
	protected void fillActioninPopupMenu(Vector onlyAction,
			final Transfer [] transfers, final int userID, final int row) {
		// TODO Auto-generated method stub

		actions.removeAll();
		if (onlyAction == null || onlyAction.isEmpty())
			return;
		JMenuItem actionItem[] = new JMenuItem[onlyAction.size()];
		for (int a = 0; a < onlyAction.size(); a++) {
			WFConfig wf = (WFConfig) onlyAction.get(a);
			actionItem[a] = new JMenuItem(wf.getAction());
			actions.add(actionItem[a]);
			actionItem[a].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String action = arg0.getActionCommand().toString();
					for(int i=0;i<transfers.length;i++) {
						transfers[i].setAction(action);
					updateTransferOnAction(transfers[i], userID, row);
				}
				}
			}); // TODO Auto-generated method stub

		}

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

public void setRemoteBO(RemoteBOProcess remoteBO) {
	// TODO Auto-generated method stub
	//this.remoteBO = remoteBO;
	
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
				NettingFrame frame = new NettingFrame();
				frame.setDefaultCloseOperation(NettingFrame.EXIT_ON_CLOSE);
				frame.setTitle("NettingFrame");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

	
class TableModelUtil extends AbstractTableModel {
		
		boolean isSettled = false;
		final String[] columnNames;
		java.util.Hashtable<Integer, String> productNames = new Hashtable();
		Vector<Transfer> data;
		RemoteBOProcess remoteBO;
		RemoteProduct remoteProduct;
        Task task = null;
		public TableModelUtil(Vector<Transfer> myData, String col[],
				 Task task,
				boolean isSettled) {
			this.columnNames = col;
			this.data = myData;
			//this.remoteBO = remoteAcc;
			this.task = task;
			this.isSettled = isSettled;
		}

		public void setModel(Vector<Transfer> data) {
			this.data = data;
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
		//	"Transfer ID", "Trade id","EventType","TranferType", "AMOUNT","Settlement","Quantity","Product","Status","SettlementDate","Currency","ValueDate","Payer","Receiver"
			Transfer transfer = (Transfer) data.get(row);

			switch (col) {
			case 0:
				value = transfer.getId();
				break;
			case 1:
				value = transfer.getTradeId();
				break;
			case 2:
				value = transfer.getEventType();
				break;
			case 3:
				value = transfer.getTransferType();
				break;

			case 4:
				if (isSettled)
					value = transfer.getSettleAmount();
				else
					value = transfer.getAmount();
				break;
			case 5:
				value = transfer.getSettleAmount();
				break;
			case 6:

				value = transfer.getQuantity();
				break;
			case 7:
				if(task == null) 
				value = transfer.getProductType();
				else 
					value = task.getProductType();
				break;
			case 8:
				value = transfer.getStatus();
				break;
			case 9:
				value = transfer.getDeliveryDate();
				break;
			case 10:
				value = transfer.getSettlecurrency();
				break;
			case 11:
				value = transfer.getValueDate();
				break;
			case 12:
				value = transfer.getPayerCode();
				break;
			case 13:
				value = transfer.getReceiverCode();
				break;
			
			}
			return value;
		}

		
		public boolean isCellEditable(int row, int col) {
			return false;
		}

		public void setValueAt(Object value, int row, int col) {
			System.out.println("Setting value at " + row + "," + col + " to "
					+ value + " (an instance of " + value.getClass() + ")");
			if (value instanceof Transfer) {
				data.set(row, (Transfer) value);
				this.fireTableDataChanged();
				System.out.println("New value of data:");
			}

		}

		public void setValueAt(Object value, int row) {
			System.out.println("Setting value at " + row + "," + value
					+ " (an instance of " + value.getClass() + ")");
			if (value instanceof Transfer) {
				data.set(row, (Transfer) value);
				this.fireTableDataChanged();
				System.out.println("New value of data:");
			}

		}

		public void addRow(Object value) {

			data.add((Transfer) value);
			this.fireTableDataChanged();

		}

		public void delRow(int row) {
			if (row != -1) {
				data.remove(row);
				this.fireTableDataChanged();
			}

		}

		public void udpateValueAt(Object value, int row, int col) {
			data.set(row, (Transfer) value);
			for (int i = 0; i < columnNames.length; i++)
				fireTableCellUpdated(row, i);

		}

		public void removeALL() {
			if (data != null) {
				data.removeAllElements();
			}
			data = null;
			this.fireTableDataChanged();
		}
	}
FilterValues filter = null;

public void setFilter(FilterValues filterValues) {
	// TODO Auto-generated method stub
	this.filter = filterValues;
	
}

public void setUsers(Users user) {
	// TODO Auto-generated method stub
	this.users = user;
	
}

public void addtaskData(TaskEventProcessor taskEvent) {
	// TODO Auto-generated method stub
	Transfer transfer = taskEvent.getTransfer();
	
	Trade ntrade = taskEvent.getTrade();
	if(ntrade == null)
		return;
	
	if(transfer == null)
		return ;
	JTable dyTable = (JTable)dynTables.get(transfer.getDeliveryDate());

	Vector nettedTransfers = null;
	
		nettedTransfers = (Vector) filter
				.getNettedTransfers(transfer.getNettedTransferID());
		TableModelUtil mutil = new TableModelUtil(nettedTransfers, col,
				null, false);
		dyTable.repaint();
		dyTable.setModel(mutil);
		dynTables.put(transfer.getDeliveryDate(),dyTable);
	
}



}

