package apps.window.util.propertyDialog;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class LastTradeTimePropertyDialog extends JDialog{
	private JPanel mainPanel = new JPanel();
	private JPanel northPanel = new JPanel(new FlowLayout());
	private JPanel buttonPanel = new JPanel(new FlowLayout());
	private JButton okButton,cancelButton;
	private JTextField lastTradeHourText = new JTextField();
	private JTextField lastTradeMinText = new JTextField();
	private int _lastTradeHour = 0;
	private int _lastTradeMin = 0;
	private Integer _lastTradedTimeinMinutes =  null;
		
    public String getLastTradedTimeinMinutes(){
    	String _hrs = formatHours((int) (_lastTradedTimeinMinutes / 60));
    	String _mins = formatMinutes((int) (_lastTradedTimeinMinutes % 60));
		String lastTradedTime = _hrs + ":" + _mins;
    	return lastTradedTime;
    }
    
    public void setLastTradedTimeinMinutes(int lastTradedTime){
    	_lastTradedTimeinMinutes =  new Integer(lastTradedTime);
    }
    
	public LastTradeTimePropertyDialog(Frame parent, boolean modal, int _timeinMinutes){
		this(parent, modal, null, false, true, _timeinMinutes);
	}
	

	public LastTradeTimePropertyDialog(Frame parent, boolean modal, Comparator comp, boolean showFilter, int _timeinMinutes){
		this(parent, modal, comp, showFilter, true, _timeinMinutes);
	}
	
	public LastTradeTimePropertyDialog(Frame parent, boolean modal, Comparator comp, boolean showFilter, boolean isOrderable, int _timeinMinutes) {
       super(parent, modal);
       init(_timeinMinutes);
   }
	
	private void init(int _timeinMinutes){
		_lastTradedTimeinMinutes =  new Integer(_timeinMinutes);
		_lastTradeHour = (int) (_lastTradedTimeinMinutes.intValue() / 60);
		_lastTradeMin = (int) (_lastTradedTimeinMinutes.intValue() % 60);
		try{
			createLayout();
			lastTradeHourText.setText(formatHours(_lastTradeHour));
			lastTradeMinText.setText(formatMinutes(_lastTradeMin));
		}
		catch(Exception e){
			//Log.error(this,e);
		}
	}
	private void createLayout() throws Exception {
		Container contentPane = getContentPane();
		contentPane.setSize(new java.awt.Dimension(200,200));
		mainPanel.setLayout(new BorderLayout());
		northPanel.setLayout(new FlowLayout());
		contentPane.add(mainPanel,BorderLayout.CENTER);
		mainPanel.add(northPanel,BorderLayout.NORTH);
		northPanel.add(new javax.swing.JLabel("Hours"));
	    lastTradeHourText.setPreferredSize(new java.awt.Dimension(30,20));
	    lastTradeHourText.setText("00");
	    lastTradeMinText.setText("00");
	    lastTradeMinText.setPreferredSize(new java.awt.Dimension(30,20));
        northPanel.add(lastTradeHourText,BorderLayout.NORTH);
		northPanel.add(new javax.swing.JLabel("Minutes"));
		northPanel.add(lastTradeMinText,BorderLayout.NORTH);
		mainPanel.add(northPanel,BorderLayout.NORTH);
		okButton = new JButton("Ok");
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				okButton_actionPerformed();
			}
		});
		cancelButton = new JButton("Cancel");
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		mainPanel.add(buttonPanel,BorderLayout.SOUTH);
	}
	public void okButton_actionPerformed() {
		getLastTradeHourText();
		getLastTradeMinText();
        setLastTradedTimeinMinutes(new Integer((_lastTradeHour * 60) + _lastTradeMin));
	}
	private void getLastTradeHourText() {
        String s = lastTradeHourText.getText();
        int x =  Integer.parseInt(s.replaceAll("\\+|,|\\s", ""));
        if (x == Integer.MIN_VALUE) {
            _lastTradeHour = 0;
        } else if (x == Integer.MAX_VALUE) {
          //  AppUtil.displayError("Hour must be 0 to 23. Will be reset to " + _lastTradeHour + ".", this);
            _lastTradeHour = 0;
        } else if ((x < 0) || (x > 23)) {
         //   AppUtil.displayError("Hour must be 0 to 23. Will be reset to " + _lastTradeHour + ".", this);
            _lastTradeHour = 0;
        } else {
            _lastTradeHour = x;
        }
        lastTradeHourText.setText(formatHours(_lastTradeHour));
    }
	
	  private void getLastTradeMinText() {
	        String s = lastTradeMinText.getText();
	        int x = Integer.parseInt(s.replaceAll("\\+|,|\\s", ""));
	        if (x == Integer.MIN_VALUE) {
	            _lastTradeMin = 0;
	        } else if (x == Integer.MAX_VALUE) {
	       //     AppUtil.displayError("Minute must be 0 to 59. Will be reset to " + _lastTradeMin + ".", this);
	            _lastTradeMin = 0;
	        } else if ((x < 0) || (x > 59)) {
	        //    AppUtil.displayError("Minute must be 0 to 59. Will be reset to " + _lastTradeMin + ".", this);
	            _lastTradeMin = 0;
	        } else {
	            _lastTradeMin = x;
	        }
	        lastTradeMinText.setText(formatMinutes(_lastTradeMin));
	    }
	  public JPanel getMainPanel() {
			return mainPanel;
		}

		public JButton getCancelButton() {
			return cancelButton;
		}

		public JButton getOkButton() {
			return okButton;
		}
	 private String formatHours(int hh) {
	        // convert to an integer with one or two digits
	        NumberFormat nf = NumberFormat.getInstance();
	        nf.setMinimumIntegerDigits(1);
	        nf.setMaximumIntegerDigits(2);
	        return nf.format(hh);
	    }
	 private String formatMinutes(int mm) {
	        // convert to an integer with two digits
	        NumberFormat nf = NumberFormat.getInstance();
	        nf.setMinimumIntegerDigits(2);
	        nf.setMaximumIntegerDigits(2);
	        return nf.format(mm);
	    }
	
}
