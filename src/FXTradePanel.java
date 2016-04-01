import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import apps.window.tradewindow.FXPanels.BasicData;
import apps.window.tradewindow.FXPanels.Funtionality;
import apps.window.tradewindow.FXPanels.Swap;
import apps.window.tradewindow.FXPanels.TradeAttributes;
import apps.window.tradewindow.FXPanels.outRight;

import com.jidesoft.plaf.LookAndFeelFactory;


public class FXTradePanel extends JFrame {
	
	public FXTradePanel() {
        initComponents();
    }
	
	  private void initComponents() {
		  JPanel jpanel2 = new JPanel();
			//jpanel2 = new javax.swing.JPanel();
			 LookAndFeelFactory.installDefaultLookAndFeelAndExtension();
			 JPanel fxData = new JPanel();
			 jpanel2.setLayout(new BorderLayout());
			 JPanel tradeData = new JPanel();
			 JPanel swapData = new JPanel();
			 tradeData.setLayout(new BorderLayout());
			 JPanel basicData = new BasicData();
			 JPanel attributes = new TradeAttributes();
			 JPanel functionality = new Funtionality();
			 JPanel out = new outRight();
			 JPanel swap = new Swap();
			 tradeData.add(basicData,BorderLayout.BEFORE_FIRST_LINE);
			 tradeData.add(out,BorderLayout.BEFORE_LINE_BEGINS);
			 swapData.setLayout(new BorderLayout());
			 swapData.add(tradeData,BorderLayout.BEFORE_FIRST_LINE);
			 swapData.add(swap,BorderLayout.BEFORE_LINE_BEGINS);
			 fxData.setLayout(new BorderLayout());
			 fxData.add(swapData,BorderLayout.BEFORE_FIRST_LINE);
			 fxData.add(functionality,BorderLayout.BEFORE_LINE_BEGINS);
			// jpanel2.add(swapData,BorderLayout.);
			 jpanel2.add(fxData,BorderLayout.LINE_START);
			 jpanel2.add(attributes,BorderLayout.EAST);
			 this.add(jpanel2);
			 
		//	 jpanel2.
	  }

	  
	  JLabel currencyPair = new JLabel();
	  JTextField cpText = new JTextField();
	  
	  public static void main(String args[]) {
		  FXTradePanel fx = new FXTradePanel();
		  fx.setVisible(true);
		  fx.setSize(900,500);
	  }
	  
}
