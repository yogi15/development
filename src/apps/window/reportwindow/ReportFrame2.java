package apps.window.reportwindow;
import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JPanel;

import util.commonUTIL;

import com.jidesoft.plaf.LookAndFeelFactory;

import dsServices.RemoteTrade;
import dsServices.ServerConnectionUtil;

import apps.window.reportwindow.jideReport.PivotReport;
import apps.window.reportwindow.jideReport.SwingReportDemo;
public class ReportFrame2 extends JPanel {
	public ReportFrame2() {
        initComponents();
    }
	 private void initComponents() {
	    	String reportsColumns ="producttype,id ,tradedesc1,cpID,traderID,status,type,tradeDate,bookId,quantity,price,nominal";
	    	PivotReport pReport = new PivotReport(null);
	    	 String sql = " select " + reportsColumns + " from trade";
	    	 
	   	   ServerConnectionUtil de = null;
			try {
				de =ServerConnectionUtil.connect("localhost", 1099,commonUTIL.getServerIP());
				RemoteTrade remoteTrade;
				//S
				
				remoteTrade = (RemoteTrade)	de.getRMIService("Trade");
				Vector v1 = (Vector) remoteTrade.getTradesforReport(sql);
				pReport.setHeader((Vector) v1.get(0));
				pReport.setdatatype((Vector) v1.get(1));
				pReport.setData((Vector) v1.get(2));
				
				System.out.println("ppp");
			
			}catch(Exception ed) {
				System.out.println(ed);
				
			}
			 
			JPanel jpanel2 = new JPanel();
			//jpanel2 = new javax.swing.JPanel();
			 LookAndFeelFactory.installDefaultLookAndFeelAndExtension();
			
	  ReportTradeParamPanel tradePanel = new ReportTradeParamPanel();
	        
	      
	        
	        
	    
			SwingReportDemo  demo = new SwingReportDemo(pReport);
//			 jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Report"));
	        JPanel jpenl = demo.run();
	        this.setLayout(new BorderLayout());
	        this.add(demo.getControlPanel(null), BorderLayout.NORTH);
	        this.add(demo.getView().getJidePanel(), BorderLayout.CENTER);
	       this.add(tradePanel,BorderLayout.WEST);
	       
	       
	    //   jpanel2.setLayout(new BorderLayout());
	   //    jpanel2.add(l1);
	     //  jpanel2.add(dd);
	       //jpanel2.add(l2);
	      // jpanel2.add(dd1);
	       
	     //  jpanel2.add(l3,BorderLayout.EAST);
	     //  jpanel2.add(dd2);
	       
	 //  add(jPanel1);
	     //  setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
	       
	        
	       // pack();
	    }// </editor-fold>

	    /**
	     * @param args the command line arguments
	     */

	    
	    // Variables declaration - do not modify
	    private javax.swing.JPanel jPanel1;
	    private javax.swing.JButton jButton1;
	    private javax.swing.JLabel jLabel1;
	    private javax.swing.JPanel jPanel2;
	    // End of variables declaration
	}

