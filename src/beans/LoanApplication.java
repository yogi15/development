package beans;

public class LoanApplication {

	int loanapplicationid;

	public void setLoanapplicationid(int loanapplicationid) {
		this.loanapplicationid = loanapplicationid;
	}

	public int getLoanapplicationid() {
		return loanapplicationid;
	}

	String customername;

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getCustomername() {
		return customername;
	}

	String loantype;

	public void setLoantype(String loantype) {
		this.loantype = loantype;
	}

	public String getLoantype() {
		return loantype;
	}

	double loanamount;

	public void setLoanamount(double loanamount) {
		this.loanamount = loanamount;

	}
	public double getLoanamount( ) {
		return loanamount;

	}
}
