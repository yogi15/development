package limit;

import java.rmi.RemoteException;
import java.util.Hashtable;
import java.util.Vector;

import util.commonUTIL;

import beans.Limit;
import beans.LimitConfig;
import beans.LimitUsage;
import beans.Trade;
import beans.Transfer;
import dsEventProcessor.EventProcessor;
import dsEventProcessor.LimitEventProcessor;
import dsEventProcessor.TradeEventProcessor;
import dsServices.RemoteBOProcess;
import dsServices.RemoteLimit;
import dsServices.RemoteReferenceData;
import dsServices.RemoteTrade;

public class LimitProcessor {

	RemoteTrade remoteTrade = null;
	RemoteLimit remoteLimit = null;
	public RemoteLimit getRemoteLimit() {
		return remoteLimit;
	}
	public void setRemoteLimit(RemoteLimit remoteLimit) {
		this.remoteLimit = remoteLimit;
	}

	RemoteBOProcess remoteBOProcess = null;
	RemoteReferenceData refData = null;
	Hashtable<Integer,LimitConfig> limitConfigs = new Hashtable<Integer,LimitConfig>();
Vector<LimitEventProcessor> publishLimits = new Vector<LimitEventProcessor>();
	
	public void setRemoteBOProcess(RemoteBOProcess remoteBO) {
		// TODO Auto-generated method stub
		this.remoteBOProcess = remoteBO;
	}
	public RemoteTrade getRemoteTrade() {
		return remoteTrade;
	}

	public void setRemoteTrade(RemoteTrade remoteTrade) {
		this.remoteTrade = remoteTrade;
	}

	public RemoteReferenceData getRefData() {
		return refData;
	}

	public void setRefData(RemoteReferenceData refData) {
		this.refData = refData;
	}

	public RemoteBOProcess getRemoteBOProcess() {
		return remoteBOProcess;
	}
	public void processLimitOnTrade(Trade trade,TradeEventProcessor tradeEvent) {
		if(trade == null)
			return;
		try {
				if(tradeEvent.isLimitBreach()) {
				Vector<Limit> limits = remoteLimit.getLimitsOnCounterParty(trade.getCpID(), trade);
							for(int i=0;i<limits.size();i++) {
								Limit limit = limits.get(i);
								LimitUsage limitU = remoteLimit.getLimitUsage(limit);
								LimitConfig limitConfig = getLimitConfig(limit.getLimitConfigId());
								getLimitEvent(limit,limitConfig,limitU);
								
							}
				
			
				} else {
						Vector<Limit> limits = remoteLimit.getLimitsOnCounterParty(trade.getCpID(), trade);
						 if(!commonUTIL.isEmpty(limits))
						commonUTIL.display("LimitManager>>>> ", limits.size() + " limits found for Trade " + trade.getId() + " on CounterParty "+ trade.getCpID() );
						for(int i=0;i<limits.size();i++) {
										boolean flag = true;
										Limit limit = limits.get(i);
										LimitUsage limitU = remoteLimit.getLimitUsage(limit);
										double amountUsed = limitU.getAmount_used() + getAmount(trade);
										if( amountUsed  > limit.getLimitmax()) {
												flag = false;
											} 
										if(flag) {
												if( amountUsed  < limit.getLimitmin()) {
													flag = false;
												}
										}
										if(flag) {
												limitU =	generateNewLimitUsage(limit,trade);
												remoteLimit.saveLimitUsage(limitU);
										}
						}
						 limits = remoteLimit.getLimitsOnTrader(trade.getTraderID(), trade);
						 if(!commonUTIL.isEmpty(limits))
						 commonUTIL.display("LimitManager>>>> ", limits.size() + " limits found for Trade " + trade.getId() + " on CounterParty "+ trade.getCpID() );
						 
						 for(int i=0;i<limits.size();i++) {
								boolean flag = true;
								Limit limit = limits.get(i);
								LimitUsage limitU = remoteLimit.getLimitUsage(limit);
								double amountUsed = limitU.getAmount_used() +getAmount(trade);
								if( amountUsed  > limit.getLimitmax()) {
										flag = false;
									} 
								if(flag) {
										if( amountUsed  < limit.getLimitmin()) {
											flag = false;
										}
								}
								if(flag) {
										limitU =	generateNewLimitUsage(limit,trade);
										remoteLimit.saveLimitUsage(limitU);
								}
				}
					}
			
		
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}
	
	private double getAmount(Trade trade) {
		double tradeAmount = 0.0;
		if(trade.getProductType().equalsIgnoreCase("FX")) {
			if(trade.getNominal()  < 0)
			  tradeAmount = trade.getNominal() * -1;
			else 
				  tradeAmount = trade.getNominal();
			
		} else {
		
			tradeAmount = trade.getTradeAmount();
			
		}
		return tradeAmount;
	}
	private LimitUsage generateNewLimitUsage(Limit limit,Trade trade) {
		LimitUsage limitU = new LimitUsage();
		limitU.setLimitConfigId(limit.getLimitConfigId());
		limitU.setLimitId(limit.getId());
		limitU.setAmount_used(getAmount(trade));
		limitU.setTradeId(trade.getId());
		return limitU;
		
		
		// TODO Auto-generated method stub
		
	}
	private void getLimitEvent(Limit limit, LimitConfig limitConfig,
			LimitUsage limitU) {
		// TODO Auto-generated method stub
		LimitEventProcessor limitEvent = new LimitEventProcessor();
		limitEvent.setLimitID(limit.getId());
		limitEvent.setLimitName(limitConfig.getConfig_name());
		limitEvent.setLimitUsage(limitU.getAmount_used());
		if( limitU.getAmount_used() > limit.getLimitWarning()) {
			limitEvent.setLimitWarning(1);
		}
		if( limitU.getAmount_used() > limit.getLimitTolarance()) {
			limitEvent.setLimitWarning(1);
		}
		if(limitU.getAmount_used() > limit.getLimitmax()) {
			limitEvent.setLimiMaxFlag(1);
		}
		limitEvent.setlimitMinValue(limit.getLimitmin());
		limitEvent.setlimitMaxValue(limit.getLimitmax());
		
		
	}
	private LimitConfig getLimitConfig(int limitConfigId) throws RemoteException {
		// TODO Auto-generated method stub
		LimitConfig limitConfig = null;
		synchronized (limitConfigs) {
			limitConfig = limitConfigs.get(limitConfigId);
			
		}
		if(limitConfig == null) {
			limitConfig = remoteLimit.getLimitConfig(limitConfigId);
			limitConfigs.put(limitConfig.getId(), limitConfig);
		}
		
		return limitConfig;
		
	}

	

}
