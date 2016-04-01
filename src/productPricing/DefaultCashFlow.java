package productPricing;

import java.util.Vector;

import beans.Flows;

public class DefaultCashFlow  {
	  final public static String INTEREST="INTEREST";
	  final public static String PRINCIPAL="PRINCIPAL";
	  final public static String SECURITY="SECURITY";
	  final public static String COLLATERAL="COLLATERAL";
	  final public static String INCOME="INCOME";
	  
	  Vector<Flows> flows = new Vector<Flows>();
	  
	  protected String  _type;
	  final public boolean isPrincipal() {
	        return _type.equals(PRINCIPAL);
	    }
	  
	  final public boolean isSecurity() {
	        return _type.equals(SECURITY);
	    }
	  final public boolean isInterest() {
	        return _type.equals(INTEREST);
	    }
	  
	  public Flows getAccuralFlow() {
			return null;
		}
	  
	 
	  public Vector<Flows> getFlows() {
			return flows;
		}

		public void setFlows(Vector<Flows> flows) {
			this.flows = flows;
		}
		
}
