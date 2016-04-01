import java.math.*;
import java.text.DecimalFormat;
import java.text.ParseException;

import util.NumericTextField;

public class Bigdecimal {
	public Bigdecimal(String string) {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

	      // create a BigDecimal object
		String num = "1000000000.457";
        BigDecimal val = new BigDecimal(num);
        System.out.println("big decimal: " + val.toString());
        DecimalFormat nf = new DecimalFormat("#################.000");
        System.out.println("double: "+val.doubleValue());
        System.out.println("double formatted: "+nf.format(val.doubleValue()));

	   }


}
