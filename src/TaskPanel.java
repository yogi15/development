package apps.window.tradewindow.panelWindow;


import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JTable;

import apps.window.tradewindow.BackOfficePanel;
import beans.Task;

public class TaskPanel extends BackOfficePanel {
	private javax.swing.JPanel jPanel2;
	 private javax.swing.JPanel jPanel1;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTable jTable1;
	    private javax.swing.JLabel jLabel;
	    DefaultTableModel model; 
	 public TaskPanel() {
	        initComponents();
	    }
	 
	 private void initComponents() {

	        jPanel2 = new javax.swing.JPanel();
	        jLabel = new javax.swing.JLabel();
	       
	        jPanel1 = new javax.swing.JPanel();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        jTable1 = new javax.swing.JTable();

	       // setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	        jLabel.setText("Task");
	        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Task"));
	        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Task"));

	        model = (new javax.swing.table.DefaultTableModel(
	            new Object [][] {

	            },
	            new String [] {
	                
	                "task Id ", "Trade id ","Product","TradeDate", "Trade Status","Exception"
	            }
	        ) {
	            Class[] types = new Class [] {
	            		java.lang.String.class, java.lang.String.class,java.lang.String.class, java.lang.String.class,java.lang.String.class, java.lang.String.class
	            };

	            public Class getColumnClass(int columnIndex) {
	                return types [columnIndex];
	            }
	        });
	        jTable1.setModel(model);
	        jScrollPane1.setViewportView(jTable1);
	        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
	        jPanel1.setLayout(jPanel1Layout);
	        jPanel1Layout.setHorizontalGroup(
	                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(jPanel1Layout.createSequentialGroup()
	                    .addContainerGap()
	                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
	                    .addContainerGap())
	            );
	            jPanel1Layout.setVerticalGroup(
	                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(jPanel1Layout.createSequentialGroup()
	                    .addContainerGap()
	                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
	                    .addContainerGap())
	            );
	        
	            
	            
	            
	            
	            
	            
	            
	            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	            setLayout(layout);
	            layout.setHorizontalGroup(
	                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                    .addContainerGap()
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                    //    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                    .addContainerGap())
	            );
	            layout.setVerticalGroup(
	                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(layout.createSequentialGroup()
	                    .addContainerGap()
	                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                  //  .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	            );
	        
	 }
	 
	
	@Override
	public void fillJTabel(Vector data ) {
		// TODO Auto-generated method stub
		if(data != null ) {
		Iterator it = data.iterator();
		int r = model.getRowCount();
		System.out.println(r);
		for(int rows =r;rows > 0;rows--)  {
		   model.removeRow(rows-1);
		}
		jTable1.repaint();
		int i =0;
		while(it.hasNext()) {
   		
   		Task task = (Task) it.next();
   		//if(checkAlreadyExists(task))
		model.insertRow(i, new Object[]{new Integer(task.getId()).toString(),task.getTradeID(),task.getProductID(),task.getTaskDate(),task.getStatus(), task.getStatusDone()});
		i++;
		}
		
		//model.insertRow(row, rowData)
	//	
		
		//jTable1.setModel(model12);
		jTable1.repaint();
		}
	}
	public boolean checkAlreadyExists(Task task) {
		 boolean flag = true;
		 for(int i = 1;i < model.getRowCount();i++) {
			 String s = (String) model.getValueAt(i, 0);
			 int ss = new Integer(s).intValue();
			 if(task.getId() == ss) {
				
				  flag = false;
			 }
			 
			
		 }
		 return flag;
	 }	
		
	}
