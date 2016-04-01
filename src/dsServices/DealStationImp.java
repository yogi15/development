package dsServices;

import java.rmi.RemoteException;

import beans.DealBean;

public class DealStationImp implements RemoteDealStation{

	@Override
	public void viewDealData(DealBean bean) throws RemoteException {
		System.out.println();
		System.out.println();
		
		System.out.println("Member Name " +  bean.getMemberName()  + "   Member id  " + bean.getMember());
		
		System.out.println("Dealer Name  " +  bean.getDEALERname() + "  Dealer id  " + bean.getDealer());
		
		System.out.println("Market  " +  bean.getMarket()  + " Sub Market " + bean.getSubMarket());
		
		System.out.println("order id  " +  bean.getOrderNumber()  + "  Trade id  " +bean.getTradeNumber());
		System.out.println("Trade Date" +  bean.getTradeDate()  + "   TradeTime  " +bean.getTradeTime());
		System.out.println(" BUY " +  bean.getTradeType()  + "  Settlement Type " + bean.getSettlementType());
		
		System.out.println("  Settlement Date " +  bean.getSettlement());// + "Member id  " + bean.getMember());
		System.out.println(" ISIN" +  bean.getISIN()  + "  GENSC " + bean.getGenspec());
		System.out.println(" Security " +  bean.getSecurity() + "   MaturityDate " + bean.getMaturityDate());
		System.out.println("Face Value " +  bean.getFV()  + "   Trade Price " + bean.getTradePrice());
		System.out.println("Yield  " +  bean.getTradeYield()  + "    Trade Amount  " + bean.getTradeAmount());
		
		System.out.println("PaymentDate  " +  bean.getPaymentDate()  + "    NumberofBrokenPeriodDays  " + bean.getNumberofBrokenPeriodDays());
		System.out.println("AccruedInterest  " +  bean.getAccruedInterest()  + "   SettConsiderationRs " + bean.getSettConsiderationRs());
		
		//aturityDate
	}

}
