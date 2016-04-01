package apps;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.Vector;
import beans.DealBean;
import beans.HelperDealBean;
import beans.Trade;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dsServices.RemoteDealStation;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;

import util.csvReader.CSVFileHandler;

import beans.HelperDealBean;

public class newDealInvoker  extends javax.swing.JDialog implements Runnable  {
	 public static String path = "E:\\deals";
	 public static Date currentDate = null;
	 public int size = 0;
	 public SimpleDateFormat format = new SimpleDateFormat("yyyy dd mm hh:mm:ss");
	 public   Map filecollector = Collections.synchronizedMap(new HashMap());
	 ServerConnectionUtil de = null;
	 RemoteTrade remoteTrade;
	 RemoteReferenceData remoteReference;
	 RemoteProduct remoteProduct;
	public  DefaultTableModel model = null;
	  DealSender dealSender = new DealSender();
	newDealInvoker() {
		
		
		currentDate = new Date();
		 System.out.println(" New Time start reader " + format.format(currentDate));
		 
	}
	public newDealInvoker(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        currentDate = new Date();
	    System.out.println(" New Time start reader " + format.format(currentDate));
        initComponents();
        this.setTitle("Deal Invoker");
    }
	
	  @SuppressWarnings("unchecked")
	    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
	    private void initComponents() {
		  de =  ServerConnectionUtil.connect("localhost", 1099,"127.0.0.1" );
		  try {
			  remoteProduct = (RemoteProduct) de.getRMIService("Product");
			  remoteTrade = (RemoteTrade) de.getRMIService("Trade");
			  remoteReference = (RemoteReferenceData) de.getRMIService("ReferenceData");
			
			  dealSender.setRemoteProduct(remoteProduct);
			  dealSender.setRemoteTrade(remoteTrade);
			  dealSender.setRemoteRefernce(remoteReference);
			  
				//	System.out.println(deals.toString());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        jButton1 = new javax.swing.JButton();
	        jButton2 = new javax.swing.JButton();
	        jButton3 = new javax.swing.JButton();
	        jButton4 = new javax.swing.JButton();
	        jLabel1 = new javax.swing.JLabel();
	        jLabel2 = new javax.swing.JLabel();
	        jLabel3 = new javax.swing.JLabel();
	        jLabel4 = new javax.swing.JLabel();
	        jLabel5 = new javax.swing.JLabel();
	        jTextField1 = new javax.swing.JTextField();
	        jTextField1.setEditable(false);
	        jTextField2 = new javax.swing.JTextField();
	        jTextField3 = new javax.swing.JTextField();
	        jTextField4 = new javax.swing.JTextField();
	        jScrollPane1 = new javax.swing.JScrollPane();
	        jTable1 = new javax.swing.JTable();

	        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

	        jButton1.setText("Save AS New");
	        jButton1.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	              //  jButton1ActionPerformed(evt);
	            }
	        });
	        jButton3.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	              //  jButton3ActionPerformed(evt);
	            }

				
	        });
	        jButton2.addActionListener(new java.awt.event.ActionListener() {
	            public void actionPerformed(java.awt.event.ActionEvent evt) {
	               // jButton2ActionPerformed(evt);
	            }

				
	        });
	        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {

				@Override
				public void mouseClicked(MouseEvent e) {
				/*	int rowindex = jTable1.getSelectedRow(); 
					TableModel model = jTable1.getModel();
					Integer ss = (Integer) model.getValueAt(rowindex, 0);
					Vector vector = (Vector) process.selectRecordProcess(ss.intValue());
					Book book = (Book) vector.get(0);
					jTextField4.setText(new Integer(book.getPrice()).toString());
					jTextField3.setText(book.getAuthor());
					jTextField2.setText(book.getTitle());
					jTextField1.setText(new Integer(book.getBook_ID()).toString()); */
					
				}

				
	        
	        	
	        });
	        
	        jButton2.setText("Edit");

	        jButton3.setText("Delete");

	        jButton4.setText("Search");

	        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 20));
	        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        jLabel1.setText("Deal Browser");

	        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11));
	        jLabel2.setText("");

	        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11));
	        jLabel3.setText("");

	        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11));
	        jLabel4.setText("");

	        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11));
	        jLabel5.setText("");
	        String col[] ={"Member", "DEALERname ", "Market", "SubMarket","OrderNumber","TradeDate", "TradeTime ", "TradeNumber", "TradeType","SettlementType","Settlement", "Date ", "ISIN", "Genspec","Security","MaturityDate", "Amount ", "FV", "TradePrice","TradeYield","TradeAmount","LastInterest","PaymentDate"};
	        
	        model = new DefaultTableModel(col,0);
	    //      processTableData(model);

	        jTable1.setModel(model);
	           jScrollPane1.setViewportView(jTable1);

	        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
	        getContentPane().setLayout(layout);
	        layout.setHorizontalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(layout.createSequentialGroup()
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
	                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                        .addGap(26, 26, 26)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
	                            .addComponent(jLabel3)
	                            .addComponent(jLabel4)
	                            .addComponent(jLabel5))
	                        .addGap(80, 80, 80)
	                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	                            .addComponent(jTextField4, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
	                            .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
	                            .addComponent(jTextField2, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE)
	                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
	                        .addGap(66, 66, 66)
	                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
	                .addGap(187, 187, 187))
	            .addGroup(layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
	                .addContainerGap())
	            .addGroup(layout.createSequentialGroup()
	                .addGap(176, 176, 176)
	                .addComponent(jButton1)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jButton2)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jButton3)
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
	                .addComponent(jButton4)
	                .addContainerGap(194, Short.MAX_VALUE))
	        );
	        layout.setVerticalGroup(
	            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
	            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
	                .addContainerGap()
	                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
	                .addGap(22, 22, 22)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel2)
	                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel3)
	                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel4)
	                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jLabel5)
	                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
	                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
	                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
	                .addGap(18, 18, 18)
	                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
	                    .addComponent(jButton4)
	                    .addComponent(jButton3)
	                    .addComponent(jButton2)
	                    .addComponent(jButton1))
	                .addContainerGap())
	        );

	        pack();
	    }// </editor-fold>//GEN-END:initComponents
	public void run() { 
		try { 
			for(; ; ) { 
				
				
				
				readFolder(path,currentDate);
				Thread.sleep(600); 
			} 
		} catch (InterruptedException e) { 
			System.out.println("newDealInvoker not getting started... "); 
		} 
			
	} 
	
	
	public  void readFolder(String path,Date currentTime) {
	//	String path = "."; 
		 
		  String files;
		  File folder = new File(path);
		  File[] listOfFiles = folder.listFiles(); 
		  if(size < listOfFiles.length) {
			 System.out.println(" New files ready");
		  for (int i = 0; i < listOfFiles.length; i++)   {
		 		   if (listOfFiles[i].isFile()) 	 {	
		 			   if(!filecollector.containsKey(listOfFiles[i].getName())) {
		 			    System.out.println( listOfFiles[i].getPath() );
		 			    DealBean  bean = null;//(DealBean) CSVFileHandler.read(listOfFiles[i].getPath() ,new HelperDealBean());
		 			    System.out.println(" bean "+bean);
		 			    Trade trade =  dealSender.buildTrade(bean);
		 		    	 try {
							int tradeid = remoteTrade.saveTrade(trade);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		 				filecollector.put(listOfFiles[i].getName(),bean);
		 				if(size == 0) {
		 				  model.insertRow(i, new Object[]{bean.getMember(),bean.getDEALERname(), bean.getMarket(), bean.getSubMarket(),bean.getOrderNumber(),bean.getTradeDate(), bean.getTradeTime(), bean.getTradeNumber(), bean.getTradeType(),bean.getSettlementType(),bean.getSettlement(), bean.getDate(), bean.getISIN(), bean.getGenspec(),bean.getSecurity(),bean.getMaturityDate(), bean.getAmount(), bean.getFV(), bean.getTradePrice(),bean.getTradeYield(),bean.getTradeAmount(),bean.getLastInterest(),bean.getPaymentDate()});
		 				}else {
		 					TableModel mo = jTable1.getModel();
		 					model.insertRow(mo.getRowCount(),new Object[]{bean.getMember(),bean.getDEALERname(), bean.getMarket(), bean.getSubMarket(),bean.getOrderNumber(),bean.getTradeDate(), bean.getTradeTime(), bean.getTradeNumber(), bean.getTradeType(),bean.getSettlementType(),bean.getSettlement(), bean.getDate(), bean.getISIN(), bean.getGenspec(),bean.getSecurity(),bean.getMaturityDate(), bean.getAmount(), bean.getFV(), bean.getTradePrice(),bean.getTradeYield(),bean.getTradeAmount(),bean.getLastInterest(),bean.getPaymentDate()});
		 				}
		 				}
		 		   }
		  	}
		  
		  }
		  size = listOfFiles.length;
	}
	
	
	    private javax.swing.JButton jButton1;
	    private javax.swing.JButton jButton2;
	    private javax.swing.JButton jButton3;
	    private javax.swing.JButton jButton4;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JLabel jLabel2;
	    private javax.swing.JLabel jLabel3;
	    private javax.swing.JLabel jLabel4;
	    private javax.swing.JLabel jLabel5;
	    private javax.swing.JScrollPane jScrollPane1;
	    private javax.swing.JTable jTable1;
	    private javax.swing.JTextField jTextField1;
	    private javax.swing.JTextField jTextField2;
	    private javax.swing.JTextField jTextField3;
	    private javax.swing.JTextField jTextField4;
	
	public static void main(String args[]) {
		 java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	            	
	            	newDealInvoker  nd = new newDealInvoker(new javax.swing.JFrame(), true);
	            	Thread t = new Thread(nd);
	            	t.start();
	            	nd.addWindowListener(new java.awt.event.WindowAdapter() {
             public void windowClosing(java.awt.event.WindowEvent e) {
                 System.exit(0);
             }
         });
	            	nd.setVisible(true);
     }
 });
	}
		}



