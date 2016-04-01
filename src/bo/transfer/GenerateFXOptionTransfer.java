package bo.transfer;

import java.util.Vector;

import beans.NettingConfig;
import beans.Product;
import beans.Trade;
import beans.Transfer;

public class GenerateFXOptionTransfer extends BOTransfer {

	@Override
	public Vector<Transfer> generateTransfer(Trade trade,
			Vector<String> feestype, NettingConfig netConfig) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateTransfer(Trade trade, Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void fillTransfer(Transfer transfer) {
		// TODO Auto-generated method stub
		
	}

}
