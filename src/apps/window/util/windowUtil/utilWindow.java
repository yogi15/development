package apps.window.util.windowUtil;


import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import org.omg.CORBA.REBIND;

import beans.LegalEntity;
import beans.StartUPData;

import util.ComparatorFactory;

import dsServices.Remote;
import dsServices.RemoteReferenceData;
import dsServices.ServerConnectionUtil;

public class utilWindow extends javax.swing.JDialog {
	private javax.swing.JPanel jPanel2;
	 private javax.swing.JPanel jPanel1;
	
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTable jTable;
	   
	    DefaultTableModel tmodel;
		 Hashtable LEData = new Hashtable();
	    boolean refereshTree = false;
	 public utilWindow() {
	        initComponents();
	    }
	 
	 private void initComponents() {
		 
			
		
		     
	        jPanel1 = new javax.swing.JPanel();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        
	        
	        
	     
	  
	       // setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	       
	        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Attribute"));

	        String col[] = {"Attribute Name", "Attribute Value"};
	        tmodel = new DefaultTableModel (col,0);
	        jTable = new javax.swing.JTable();
	        jTable.setModel(tmodel);
	        jScrollPane1.setViewportView(jTable);
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
	                        
	                    .addContainerGap())
	            );
	            layout.setVerticalGroup(
	                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(layout.createSequentialGroup()
	                    .addContainerGap()
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                 
	                    
	                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	            );
	            
	           
	            
	            
	            	
	          
	            
	        
	 }
	 
	 

	public static void main(String args[]) {
		utilWindow test = new utilWindow();
	    	
		test.setSize(700,500);
		test.setVisible(true);
	    	
	    }
}

