package mo.positionmang;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import util.commonUTIL;

import com.jidesoft.converter.BooleanConverter;
import com.jidesoft.converter.DateConverter;
import com.jidesoft.converter.DoubleConverter;
import com.jidesoft.converter.IntegerConverter;
import com.jidesoft.converter.MonthNameConverter;
import com.jidesoft.converter.ObjectConverterManager;
import com.jidesoft.converter.PercentConverter;
import com.jidesoft.converter.QuarterNameConverter;
import com.jidesoft.converter.YearNameConverter;
import com.jidesoft.grid.CellEditorManager;
import com.jidesoft.grid.CellRendererManager;
import com.jidesoft.grouper.ObjectGrouperManager;
import com.jidesoft.plaf.LookAndFeelFactory;

import dsServices.RemoteMO;
import dsServices.RemoteProduct;
import dsServices.RemoteReferenceData;
import dsServices.ServerConnectionUtil;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.rmi.RemoteException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import beans.Book;
import beans.Liquidation;
import beans.Openpos;
import beans.Position;
import beans.Product;


public class PositionManagerFrame extends JFrame {
	
	RemoteReferenceData referenceData = null;
	RemoteProduct remoteproduct = null;
	RemoteMO remoteMO = null;
	Hashtable<Integer ,Book> bookData = new Hashtable<Integer,Book>();
	Hashtable<Integer ,Product> productData = new Hashtable<Integer,Product>();
	 public static  ServerConnectionUtil de = null;
	 String username ="";
	 String groupName = "";
   public 	PositionManagerFrame(String name) {
	   username = name;
		
		de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP());
	   	 try {
	   		//hostName = commonUTIL.getLocalHostName();
	   		referenceData = (RemoteReferenceData) de.getRMIService("ReferenceData");
	   		referenceData.selectALLBooks();
	   	
	   		remoteproduct = (RemoteProduct) de.getRMIService("Product");
	   		remoteMO = (RemoteMO) de.getRMIService("MO");
	   		 
	   		
				
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			initJide();
			initComponents();
		
   } 
   
   void initJide() {
       // Initialize JIDE settings.
       // JIDE Licenses Verification

       LookAndFeelFactory.installDefaultLookAndFeelAndExtension();

       //---------------------------------------------------------------------
       // JIDE ObjectConverterManager.initDefaultConverter
       CellEditorManager.initDefaultEditor();
       CellRendererManager.initDefaultRenderer();
       ObjectConverterManager.initDefaultConverter();
       ObjectGrouperManager.initDefaultGrouper();

       // Note: Apply special converters to display class in something useful to the user.
       ObjectConverterManager.registerConverter(Date.class, new DateConverter(), DateConverter.DATE_CONTEXT);
       ObjectConverterManager.registerConverter(Date.class, new DateConverter(), DateConverter.DATETIME_CONTEXT);
       ObjectConverterManager.registerConverter(boolean.class, new BooleanConverter());
       ObjectConverterManager.registerConverter(Boolean.class, new BooleanConverter());

       // Special formatting of integers
       DecimalFormat myFormatter = new DecimalFormat("#########"); // removes the thousand separator
       IntegerConverter intConverter = new IntegerConverter();
       intConverter.setNumberFormat(myFormatter);
       ObjectConverterManager.registerConverter(int.class, intConverter);
       ObjectConverterManager.registerConverter(Integer.class, intConverter);

       // Choose formatting of doubles
       NumberFormat format = NumberFormat.getNumberInstance();
       format.setMinimumFractionDigits(9);
       format.setMaximumFractionDigits(10);
       DoubleConverter converter = new DoubleConverter(format);
       ObjectConverterManager.registerConverter(Double.class, converter);
       ObjectConverterManager.registerConverter(double.class, converter);

       ObjectConverterManager.registerConverter(double.class,
           new PercentConverter(), PercentConverter.CONTEXT);
       ObjectConverterManager.registerConverter(Double.class,
           new PercentConverter(), PercentConverter.CONTEXT);

       // Register convertors for date class.
       ObjectConverterManager.registerConverter(Date.class, new YearNameConverter(), YearNameConverter.CONTEXT);
       ObjectConverterManager.registerConverter(Date.class, new QuarterNameConverter(), QuarterNameConverter.CONTEXT);
       ObjectConverterManager.registerConverter(Date.class, new MonthNameConverter(), MonthNameConverter.CONTEXT);
   }
   private void initComponents() {

       jPanel1 = new javax.swing.JPanel();
       jPanel2 = new javax.swing.JPanel();
       jLabel1 = new javax.swing.JLabel();
       jComboBox1 = new javax.swing.JComboBox();
       jLabel2 = new javax.swing.JLabel();
       jComboBox2 = new javax.swing.JComboBox();
       jLabel3 = new javax.swing.JLabel();
       jComboBox3 = new javax.swing.JComboBox();
       jPanel3 = new javax.swing.JPanel();
       jScrollPane1 = new javax.swing.JScrollPane();
       jTable1 = new javax.swing.JTable();
       jPanel4 = new javax.swing.JPanel();
       jScrollPane2 = new javax.swing.JScrollPane();
       jTable2 = new javax.swing.JTable();
       jPanel5 = new javax.swing.JPanel();
       jScrollPane3 = new javax.swing.JScrollPane();
       jTable3 = new javax.swing.JTable();
       processBookDataCombo1(bookComboModel,bookData);
       processProductDataCombo1(productTypeComboModel, productData);
       jComboBox1.setModel(bookComboModel);
       jComboBox2.setModel(productTypeComboModel);
       setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

       jLabel1.setText("BOOK");
       jLabel2.setText("PRODUCT TYPE");
       jLabel3.setText("PRODUCT SUB TYPE");
       javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
       jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(" "));
       jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(" "));
       
       jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(" "));
       jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(" "));
       jPanel2.setLayout(jPanel2Layout);
       jPanel2Layout.setHorizontalGroup(
           jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel2Layout.createSequentialGroup()
               .addGap(214, 214, 214)
               .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGap(30, 30, 30)
               .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGap(29, 29, 29)
               .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGap(29, 29, 29)
               .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGap(29, 29, 29)
               .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGap(29, 29, 29)
               .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addContainerGap(183, Short.MAX_VALUE))
       );

       jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox1, jComboBox2, jComboBox3, jLabel1, jLabel2, jLabel3});

       jPanel2Layout.setVerticalGroup(
           jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
           .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
           .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
           .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
           .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
           .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
       );

       jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox1, jComboBox2, jComboBox3, jLabel1, jLabel2, jLabel3});

       String col[] = {"PositionID", "Book", "Product","ProductSubType","UnRealised","Realised","Buy","Sell","Avg Price"};
       tmodel = new DefaultTableModel (col,0);
    
       jTable1.setModel(tmodel);
       
       
       jScrollPane1.setViewportView(jTable1);
   
       javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
       jPanel3.setLayout(jPanel3Layout);
       jPanel3Layout.setHorizontalGroup(
           jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel3Layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 569, Short.MAX_VALUE)
               .addContainerGap())
       );
       jPanel3Layout.setVerticalGroup(
           jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel3Layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 598, Short.MAX_VALUE)
               .addContainerGap())
       );
       String liq[] = {"First Trade ", "Second Trade", "Quantity","PositionID"};
	   liqModel = new DefaultTableModel (liq,0);
	   String col1[] = {"Trade ID", "Quantity", "OpenQuantity","Book Name","Product Type","Sign"};
      	
   	  openPostions = new DefaultTableModel (col1,0);
       jTable2.setModel(liqModel);
       jScrollPane2.setViewportView(jTable2);

       javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
       jPanel4.setLayout(jPanel4Layout);
       jPanel4Layout.setHorizontalGroup(
           jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel4Layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
               .addContainerGap())
       );
       jPanel4Layout.setVerticalGroup(
           jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel4Layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
               .addContainerGap())
       );

       jTable3.setModel(openPostions);
       jScrollPane3.setViewportView(jTable3);
       refresh();
       jTable1.addMouseListener(new MouseAdapter() {
        	 public void mouseClicked(MouseEvent e) {
        		 int rowindex = jTable1.getSelectedRow(); 
        		
     				Integer positionId = (Integer) jTable1.getValueAt(rowindex, 0);
     				try {
  						Vector  Liqu = (Vector) remoteMO.getLiquidationsOnPosition(positionId.intValue());
  						Vector  openPos = (Vector) remoteMO.getOpenPosition(positionId.intValue());
  						insertRowinLiqTable(Liqu, jTable2, liqModel);
  						insertRowinOpenPositionTable(openPos, jTable3, openPostions);
  						
  					} catch (RemoteException e1) {
  						// TODO Auto-generated catch block
  						e1.printStackTrace();
  					}
     				
  					
  				}
  		});
       
       
       
       jScrollPane3.setBorder(BorderFactory.createTitledBorder(null, "Open Position", TitledBorder.LEFT, TitledBorder.TOP, new Font("null", Font.BOLD, 12), Color.BLACK));
       jScrollPane2.setBorder(BorderFactory.createTitledBorder(null, "Liquidation", TitledBorder.LEFT, TitledBorder.TOP, new Font("null", Font.BOLD, 12), Color.BLACK));
       jScrollPane1.setBorder(BorderFactory.createTitledBorder(null, "Position", TitledBorder.LEFT, TitledBorder.TOP, new Font("null", Font.BOLD, 12), Color.BLACK));
       
       jScrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       jScrollPane3.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       jScrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       jScrollPane2.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       jScrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
       jScrollPane1.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
       
       javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
       jPanel5.setLayout(jPanel5Layout);
       jPanel5Layout.setHorizontalGroup(
           jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel5Layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 474, Short.MAX_VALUE)
               .addContainerGap())
       );
       jPanel5Layout.setVerticalGroup(
           jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel5Layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
               .addGap(21, 21, 21))
       );

       javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
       jPanel1.setLayout(jPanel1Layout);
       jPanel1Layout.setHorizontalGroup(
           jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel1Layout.createSequentialGroup()
               .addContainerGap()
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(jPanel1Layout.createSequentialGroup()
                       .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addGap(18, 18, 18)
                       .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                           .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                           .addGroup(jPanel1Layout.createSequentialGroup()
                               .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                               .addGap(9, 9, 9))))
                   .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
               .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
       );
       jPanel1Layout.setVerticalGroup(
           jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(jPanel1Layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
               .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                   .addGroup(jPanel1Layout.createSequentialGroup()
                       .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                       .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                   .addGroup(jPanel1Layout.createSequentialGroup()
                       .addGap(20, 20, 20)
                       .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                       .addGap(18, 18, 18)
                       .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
               .addContainerGap(33, Short.MAX_VALUE))
       );

       javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
       getContentPane().setLayout(layout);
       layout.setHorizontalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addContainerGap())
       );
       layout.setVerticalGroup(
           layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
           .addGroup(layout.createSequentialGroup()
               .addContainerGap()
               .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
               .addContainerGap())
       );

       pack();
   }// </editor-fold>

   
   public void processBookDataCombo1( javax.swing.DefaultComboBoxModel combodata,Hashtable ids) {
		Vector vector;
		try {
			
			vector = (Vector) referenceData.selectALLBooks();
			
			Iterator it = vector.iterator();
	    	int i =0;
	    	while(it.hasNext()) {
	    		
	    	Book book =	(Book) it.next();
	    	
   		
   		combodata.insertElementAt(book.getBook_name(), i);
	    	ids.put(i, book);
   		i++;
   	}	
		}catch (RemoteException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
   	
   	
   }
   
   public void processProductDataCombo1( javax.swing.DefaultComboBoxModel combodata,Hashtable ids) {
		Vector vector;
		try {
			
			vector = (Vector) remoteproduct.selectALLProducts();
			
			Iterator it = vector.iterator();
	    	int i =0;
	    	while(it.hasNext()) {
	    		
	    		Product product =	(Product) it.next();
	    	
   		
   		combodata.insertElementAt(product.getProductType(), i);
	    	ids.put(i, product);
   		i++;
   	}	
		}catch (RemoteException e) {
   				// TODO Auto-generated catch block
   				e.printStackTrace();
   			}
   	
   	
   }
   
   
   public String getBookKey(Hashtable t , int id) {
		String bookName = "";
		Set set = t.entrySet();
	    Iterator it = set.iterator();
	    while (it.hasNext()) {
	      Map.Entry entry = (Map.Entry) it.next();
	     Book book =  ((Book) entry.getValue());
	     if(id == book.getBookno())
	    	 bookName = book.getBook_name();
	    }
      return bookName;	    
   }
   
   public String getProductKey(Hashtable t , int id) {
		String productType = "";
		Set set = t.entrySet();
	    Iterator it = set.iterator();
	    while (it.hasNext()) {
	      Map.Entry entry = (Map.Entry) it.next();
	      Product product =  ((Product) entry.getValue());
	     if(id == product.getId())
	    	 productType = product.getProductType();
	    }
      return productType;	    
   }
   
   public void refresh() {
   	int r= tmodel.getRowCount();
		for(int rows =r;rows > 0;rows--)  {
			   tmodel.removeRow(rows-1);
			}
		jTable1.repaint();
		insertRowinTable();
       }


	private void insertRowinTable() {
		try {
		Vector positions = 	(Vector) remoteMO.getALLPosition();
	
		
			if(positions !=  null) {
				
				 Iterator it = positions.iterator();
		    	 int i =0;
		    	 while(it.hasNext()) {
		    		 Position position = (Position) it.next();
		    		// {"PositionID", "Book", "Product","ProductSubType","UnRealised","Realised","Buy","Sell","Avg Price"};
		    		 
		    		  tmodel.insertRow(tmodel.getRowCount(), new Object[]{position.getPositionId(),getBookKey(bookData,position.getBookId()),getProductKey(productData, position.getProductId()),position.getProductsubType(),position.getUnrealized(),position.getRealized(),position.getBuy_amount(),position.getSell_amount(),position.getAvg_price()});
		    	 }
			}
		}catch(Exception e) {
			
		}
		
	}
	public void refresh(JTable jtable,DefaultTableModel tmodel) {
   	int r= tmodel.getRowCount();
		for(int rows =r;rows > 0;rows--)  {
			   tmodel.removeRow(rows-1);
			}
		jtable.repaint();
		
       }
	private void insertRowinLiqTable(Vector liqPositions,JTable jtable,DefaultTableModel tmodel) {
		try {
			refresh(jtable,tmodel);
		Vector positions = 	liqPositions;
	
		
			if(positions !=  null) {
				
				 Iterator it = positions.iterator();
		    	 int i =0;
		    	 while(it.hasNext()) {
		    		 Liquidation liqp = (Liquidation) it.next();
		    	//	 {"ftradeid", "stradeid", "quantity","POSITIONID"};
		    		 
		    		  tmodel.insertRow(tmodel.getRowCount(), new Object[]{liqp.getfTradeId(),liqp.getsTradeId(),liqp.getQuantity(),liqp.getPositionId()});
		    	 }
			}
		}catch(Exception e) {
			
		}
	}
		private void insertRowinOpenPositionTable(Vector liqPositions,JTable jtable,DefaultTableModel tmodel) {
			try {
				refresh(jtable,tmodel);
			Vector positions = 	liqPositions;
		
			
				if(positions !=  null) {
					
					 Iterator it = positions.iterator();
			    	 int i =0;
			    	 while(it.hasNext()) {
			    		 Openpos openp = (Openpos) it.next();
			    	
			    		// {"tradeid", "quantity", "openquantity","bookid","positionid","sign"};
			    		  tmodel.insertRow(tmodel.getRowCount(), new Object[]{openp.getTradeId(),openp.getQuantity(),openp.getOpenQuantity(),getBookKey(bookData,openp.getBookId()),getProductKey(productData,openp.getProductId()),openp.getSign()});
			    	 }
				}
			}catch(Exception e) {
				
			}
		
	}
   
   
   /**
    * @param args the command line arguments
    */
  
   // Variables declaration - do not modify
		
		 DefaultTableModel  openPostions = null;
		 DefaultComboBoxModel bookComboModel = new DefaultComboBoxModel();
		 DefaultComboBoxModel productTypeComboModel = new DefaultComboBoxModel();
		 DefaultTableModel tmodel = null;
		 DefaultTableModel liqModel = null;
   private javax.swing.JComboBox jComboBox1;
   private javax.swing.JComboBox jComboBox2;
   private javax.swing.JComboBox jComboBox3;
   private javax.swing.JLabel jLabel1;
   private javax.swing.JLabel jLabel2;
   private javax.swing.JLabel jLabel3;
   private javax.swing.JPanel jPanel1;
   private javax.swing.JPanel jPanel2;
   private javax.swing.JPanel jPanel3;
   private javax.swing.JPanel jPanel4;
   private javax.swing.JPanel jPanel5;
   private javax.swing.JScrollPane jScrollPane1;
   private javax.swing.JScrollPane jScrollPane2;
   private javax.swing.JScrollPane jScrollPane3;
   private javax.swing.JTable jTable1;
   private javax.swing.JTable jTable2;
   private javax.swing.JTable jTable3;
}