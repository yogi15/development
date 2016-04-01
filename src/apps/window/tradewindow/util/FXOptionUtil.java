package apps.window.tradewindow.util;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JTextField;

public class FXOptionUtil {
	
	
	
	
	public static void setCallPutOnCurrPairChanged(String currPair,JLabel callPut, JTextField callPutext,JLabel call2Put,JTextField call2Putext) {
		
		String callput = callPut.getText();
		String currpair = currPair ;
		String curr1 = currpair.substring(0, 3);
		String curr2 = currpair.substring(4, 7);
    	if (callput.equalsIgnoreCase("Call/Put")) {
    		callPutext.setText("Put " + curr1);
    		callPutext.setBackground(Color.pink);
    		callPut.setText("Put/Call");
    		call2Putext.setBackground(Color.yellow);
    		call2Putext.setText("Curr2 Call " + curr2);
    	} 
    	if (callput.equalsIgnoreCase("Put/Call")) {
    		callPutext.setBackground(Color.yellow);
    		callPutext.setText("Call "+curr1);    		
    		callPut.setText("Call/Put");
    		call2Putext.setBackground(Color.pink);
    		call2Putext.setText("Put " + curr2);
    	} ;
		
	}
public static void setCurrPairChanged(String currPair,JLabel callPut, JTextField callPutext,JLabel call2Put,JTextField call2Putext,JTextField setteleCurrText) {
		
		String callput = callPut.getText();
		String currpair = currPair ;
		String curr1 = currpair.substring(0, 3);
		String curr2 = currpair.substring(4, 7);
		setteleCurrText.setText(curr2);
    	if (callput.equalsIgnoreCase("Call/Put")) {
    		callPutext.setText("Call " + curr1);
    		callPutext.setBackground(Color.pink);
    		//callPut.setText("Put/Call");
    	
    		call2Putext.setText("Curr2 Put " + curr2);
    	} 
    	if (callput.equalsIgnoreCase("Put/Call")) {
    		callPutext.setText("Put "+curr1);
    		callPut.setBackground(Color.yellow);
    	//	callPut.setText("Call/Put");
    	
    		call2Putext.setText("Curr2 Call " + curr2);
    	} ;
		
	}
}
