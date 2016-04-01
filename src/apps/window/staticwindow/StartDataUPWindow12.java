package apps.window.staticwindow;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.Vector;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import beans.StartUPData;

import util.ComparatorFactory;
import util.commonUTIL;

import dsServices.RemoteReferenceData;
import dsServices.ServerConnectionUtil;


public class StartDataUPWindow12 extends javax.swing.JPanel  {
	 private javax.swing.JPanel jPanel2;
	 private javax.swing.JPanel jPanel1;
	 private javax.swing.JPanel jPanel3;
	 private javax.swing.JButton jadd;
	 private javax.swing.JButton jremove;
	 private javax.swing.JButton jsave;
	 private javax.swing.JTextField tname;
	 public static  ServerConnectionUtil de = null;
	 RemoteReferenceData remoteBORef;
	 private javax.swing.JTextField tvalue;
	 private javax.swing.JTextField tdesc;
	 private javax.swing.JLabel jLname;
	 private javax.swing.JLabel jLvalue;
	 private javax.swing.JLabel jLdes;
	 DefaultMutableTreeNode root;
	 DefaultMutableTreeNode parent, leaf;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTree jTree;
	    public static DefaultMutableTreeNode nNode;
	    public static MutableTreeNode mNode;
	   
	    DefaultTreeModel       _treeModel;
	    DefaultMutableTreeNode _root;
	    DefaultMutableTreeNode _selectedName  = null;
	    DefaultMutableTreeNode _selectedValue = null;
	    boolean refereshTree = false;
	 public StartDataUPWindow12() {
	        initComponents();
	    }
	 
	 private void initComponents() {
		 de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP());
	   	 try {
	   		 remoteBORef = (RemoteReferenceData) de.getRMIService("ReferenceData");
				
				System.out.println(remoteBORef);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        jPanel2 = new javax.swing.JPanel();
	        jPanel3 = new javax.swing.JPanel();
	        jLname = new javax.swing.JLabel();
	        jLvalue = new javax.swing.JLabel("Value");
	        jLdes = new javax.swing.JLabel("Description");
	        tname = new javax.swing.JTextField(20);
	        tvalue = new javax.swing.JTextField(20);
	        tdesc = new javax.swing.JTextField(20);
	        jadd = new javax.swing.JButton("ADD");
	        jremove  = new javax.swing.JButton("REMOVE");
	        jsave = new javax.swing.JButton("SAVE");
	        
	        
	        jLname.setFont(Font.decode("SansSerif-11"));
	        jLvalue.setFont(Font.decode("SansSerif-11"));
	        jLdes.setFont(Font.decode("SansSerif-11"));
	        tname.setFont(Font.decode("SansSerif-11"));
	        tvalue.setFont(Font.decode("SansSerif-11"));
	        tdesc.setFont(Font.decode("SansSerif-11"));
	        jadd.setFont(Font.decode("SansSerif-11"));
	        jremove.setFont(Font.decode("SansSerif-11"));
	        jsave.setFont(Font.decode("SansSerif-11"));
	        
	        jPanel1 = new javax.swing.JPanel();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        

	       // setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	        jLname.setText("Name");
	        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
	        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
	        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("StartUPData"));

	       
	        _root = new DefaultMutableTreeNode ("InitialData");
	        jTree = new javax.swing.JTree(_root);
	       jTree.setFont(Font.decode("SansSerif-12"));
	        jScrollPane1.setViewportView(jTree);
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
	                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
	                    .addContainerGap())
	            );
	        
	            
	           javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
	            jPanel3.setLayout(jPanel3Layout);
	            jPanel3Layout.setHorizontalGroup(
	                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
	                    .addContainerGap(192, Short.MAX_VALUE)
	                    .addComponent(jLname)
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                    .addComponent(tname)
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                    .addComponent(jLvalue)
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                    .addComponent(tvalue)
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                    .addComponent(jLdes)
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                    .addComponent(tdesc)
	                    .addContainerGap())
	            );
	            jPanel3Layout.setVerticalGroup(
	                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
	                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jLname)
	                        .addComponent(tname)
	                        .addComponent(jLvalue)
	                        .addComponent(tvalue)
	                        .addComponent(jLdes)
	                        .addComponent(tdesc)))
	            ); 
	            
	            
	            
	            javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
	            jPanel2.setLayout(jPanel2Layout);
	            jPanel2Layout.setHorizontalGroup(
	                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
	                    .addContainerGap(192, Short.MAX_VALUE)
	                    .addComponent(jadd)
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                    .addComponent(jremove)
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                    .addComponent(jsave)
	                    .addContainerGap())
	            );
	            jPanel2Layout.setVerticalGroup(
	                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
	                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                        .addComponent(jadd)
	                        .addComponent(jremove)
	                        .addComponent(jsave)))
	            );
	            
	            
	            
	            
	            javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	            setLayout(layout);
	            layout.setHorizontalGroup(
	                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                    .addContainerGap()
	                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                        .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
	                        .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	                    .addContainerGap())
	            );
	            layout.setVerticalGroup(
	                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                .addGroup(layout.createSequentialGroup()
	                    .addContainerGap()
	                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
	                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
	            );
	            
	            jadd.addActionListener(new java.awt.event.ActionListener() {
	                public void actionPerformed(java.awt.event.ActionEvent evt) {
	                	String value = tvalue.getText();
	                	String name = tname.getText();
	                	StartUPData data = new StartUPData();
					    data.setName(name);
					    data.setValue(value);
					    data.setCommts(tdesc.getText());
					    try {
							System.out.println(remoteBORef.saveStartUPData(data));
								if(name.equalsIgnoreCase("InitialData")) {
									refereshTree = true;
									buildTree();
									refereshTree = false;
									//root.add(new DefaultMutableTreeNode("ppp"));
									tname.setText("");
								    tvalue.setText("");
								}
							
							
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
	                	DefaultMutableTreeNode node = new DefaultMutableTreeNode(value);
	                	System.out.println(_selectedName);
	    	            _treeModel.insertNodeInto(node, _selectedName, 0);
	    	            jTree.setSelectionPath(new TreePath(node.getPath()));
	    	            
	    	            }
	                

	    			
	            });
	            buildTree();
	            jTree.addTreeSelectionListener(new TreeSelectionListener() {

					@Override
					public void valueChanged(TreeSelectionEvent e){
						// TODO Auto-generated method stub
						if(!refereshTree) {
						_selectedName  = null;
					    _selectedValue = null;
					    TreePath selPath = e.getNewLeadSelectionPath();
					 //  System.out.println( selPath.getPathCount());
					    if (selPath == null) return;
					    String name  = "";
					    String value = "";
					    
					    
					    if (selPath.getPathCount() >= 2) {
					        _selectedName =
					        (DefaultMutableTreeNode)selPath.getPathComponent(1);
					        name = _selectedName.toString();
					        if (selPath.getPathCount() >= 3) {
					        _selectedValue =
					            (DefaultMutableTreeNode)selPath.getPathComponent(2);
					        value = _selectedValue.toString();
					        }
					    }
					    
						 tname.setText(name);
						    tvalue.setText(value);
						}
						    
						    
					
	            	
	            	
	            	
					   }});
	            
	            jremove.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						 TreePath path =jTree.getNextMatch(tvalue.getText(), 0, Position.Bias.Forward);
	                	  mNode = (MutableTreeNode)path.getLastPathComponent();
	                	  _treeModel.removeNodeFromParent(mNode);
	                	  _treeModel.reload();
	                	  JOptionPane.showMessageDialog(null, "Node are deleted from tree!");
	                	  StartUPData data1 = new StartUPData();
		    	            data1.setName(tname.getText());
		    	            data1.setValue(tvalue.getText());
		    	            try {
		    	            	remoteBORef.removeStartUPData(data1);
								
								//JOptionPane.showMessageDialog(null, "Node are deleted from DB!");
							} catch (RemoteException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}	 
	                	
	    	            
						
						// TODO Auto-generated method stub
						
					}
	            	
	            });
	          
	            
	        
	 }
	 
	 private void buildTree() {
		 Vector v = new Vector();
		 StartUPData data1 = new StartUPData();
	       data1.setName("InitialData");
		 
		 try {
			v = (Vector) remoteBORef.getStartUPDataName();
		  v.add(data1);
		   // v = util.SortShell.sort(v,ComparatorFactory.getNonSenseStringComparator());

		   root =
		        new DefaultMutableTreeNode("InitialData: "+v.size());
		   
		    for (int i=0; i<v.size(); i++) {
		    	StartUPData data = (StartUPData)v.elementAt(i);
		        String domain = data.getName();
		        parent = new DefaultMutableTreeNode(domain);
		        root.add(parent);
		        Vector values = (Vector) remoteBORef.selectStartUPData(data);
		            if (values == null)continue;
		        for(int j=0;j<values.size();j++) {
		        leaf = new DefaultMutableTreeNode(((StartUPData)values.elementAt(j)).getValue());
		        parent.add(leaf);
		        }
		    } 
		    _root = root;
		    _treeModel = new DefaultTreeModel(root);
		    jTree.setModel(_treeModel);
		    tname.setText("");
		    tvalue.setText("");
		    tdesc.setText("");
		    
		 } catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    }
		
	

	public static void main(String args[]) {
		StartDataUPWindow test = new StartDataUPWindow();
	    	JFrame testing = new JFrame();
	    	testing.add(test);
	    	testing.setSize(800,750);
	    	testing.setVisible(true);
	    	
	    }

}
