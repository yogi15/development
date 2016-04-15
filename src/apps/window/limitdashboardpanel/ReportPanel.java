package apps.window.limitdashboardpanel;

import java.awt.BorderLayout;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.dyno.visual.swing.layouts.Bilateral;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;

import dsServices.RemoteBOProcess;
import dsServices.RemoteTask;
import dsServices.RemoteTrade;

import apps.window.reportwindow.DatePanel;
import apps.window.reportwindow.jideReport.PivotReport;
import apps.window.reportwindow.jideReport.SwingReportDemo;


public class ReportPanel  extends JPanel {
	JPopupMenu jPopupMenu0;
	JPanel jPanel5;
	JScrollPane jScrollPane2 ;
	JTable jTable2;
	 PivotReport pReport = null;
	 SwingReportDemo  demo = null;
	RemoteTask remoteTask = null;
	//RemoteTrade remoteTrade = null;
	RemoteBOProcess remoteBOProcess =null;
	 LimitFilterValues filterValues;
	 JPanel jpanel6 = null;
	
	 public ReportPanel(RemoteTask remoteTask) {
		 this.remoteTask = remoteTask;
		 pReport = new PivotReport(null);
		 demo = new SwingReportDemo(pReport);
	 }
	public JPanel getJPanel5(DatePanel jobdatePanel) {
		
		if (jPanel5 == null) {
			jPanel5 = new JPanel();
			String sql = "select id,type,status,action,currency from trade";
			populateReportData(sql,true,"task");
			//  jpanel6 = demo.run();
			//  jpanel6.add(demo.getSortView().getJidePanel());
		//	jPanel5.setBorder(new LineBorder(Color.black, 1, false));
			jPanel5.setLayout(new BorderLayout());
			jPanel5.add(demo.getControlPanel(jobdatePanel),BorderLayout.NORTH);
		jpanel6=  demo.run();
		//jpanel6.add(demo.getSortView().getJidePanel());
			jPanel5.add(demo.getSortView().getJidePanel(),BorderLayout.CENTER);
			
		}
		return jPanel5;
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

private JPopupMenu getJPopupMenu0() {
	
	
	if (jPopupMenu0 == null) {
		jPopupMenu0 = new JPopupMenu();
		jPopupMenu0.add(getJMenuItem0());
	}
	return jPopupMenu0;
}
private JMenuItem getJMenuItem0() {
	
	JMenuItem jMenuItem0 = null;
	if (jMenuItem0  == null) {
		jMenuItem0 = new JMenuItem();
		jMenuItem0.setText("jMenuItem0");
		jMenuItem0.setOpaque(false);
	}
	return jMenuItem0;
}


public void setCriteria(String sql,String type) {
	// TODO Auto-generated method stub
	sql = "select * from task where id =1";
	populateReportData(sql,true,type);
	//reportPanel.populateReportData(sql,true,"task");
	//  jpanel6 = demo.run();
	//  jpanel6.add(demo.getSortView().getJidePanel());
	
}

public void populateReportData(String sql,boolean replaceColumns,String type) {
	Vector v1;
	String mainsql = " select * from trade ";
		try {
			String sq = "";
			if(replaceColumns) {
				sq = mainsql;// + " where " +  sql;
				}
			v1 = (Vector)remoteTask.generateReport(sql);
			if(v1 != null && v1.size() > 0) {
			pReport.setHeader((Vector) v1.get(0)); 
			pReport.setdatatype((Vector) v1.get(1));
			pReport.setData((Vector) v1.get(2));
			demo.setReport(pReport);
			}
			
		        
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
}
RemoteTrade remoteTrade = null;
public RemoteTrade getRemoteTrade() {
	return remoteTrade;
}
public void setRemoteTrade(RemoteTrade remoteTrade) {
	this.remoteTrade = remoteTrade;
}
public RemoteTask getRemoteTask() {
	return remoteTask;
}
public void setRemoteTask(RemoteTask remoteTask) {
	this.remoteTask = remoteTask;
}

}
