package dsServices;

import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Vector;

import util.ReferenceDataCache;
import util.commonUTIL;

import dbSQL.LimitConfigSQL;
import dbSQL.LimitSQL;
import dbSQL.LimitUsageSQL;
import dbSQL.dsSQL;
import dsEventProcessor.LimitEventProcessor;

import limit.util.LimitUtil;
import beans.LegalEntity;
import beans.Limit;
import beans.LimitConfig;
import beans.LimitConfigCriteria;
import beans.LimitUsage;
import beans.Trade;

public class LimitImp implements RemoteLimit {

	@Override
	public boolean checkLimitsExceedOnTrade(Trade trade,String Type) throws RemoteException {
		
		// TODO Auto-generated method stub
		boolean flag = true;
	   
		if(Type.equalsIgnoreCase("CounterPartyLimit")) 
			flag = isCounterPartyLimitExceed(trade.getCpID(),trade);
		if(flag)
			flag = isTraderLimitExceed(trade.getTraderID(),trade);
		
		return flag;
	}
	@Override
	public Collection getBreachDetailsOnLimit(Trade trade) throws RemoteException {
		
		Vector  limitEvts = new Vector();
		Vector<Limit> limits = getLimitsOnCounterParty(trade.getCpID(), trade);
		for(int i=0;i<limits.size();i++) {
			Limit limit = limits.get(i);
			LimitUsage limitU = getLimitUsage(limit);
			LimitConfig limitConfig = getLimitConfig(limit.getLimitConfigId());
			limitEvts.add(getLimitEvent(limit,limitConfig,limitU));
			
		}
		 limits = getLimitsOnTrader(trade.getTraderID(), trade);
		for(int i=0;i<limits.size();i++) {
			Limit limit = limits.get(i);
			LimitUsage limitU = getLimitUsage(limit);
			LimitConfig limitConfig = getLimitConfig(limit.getLimitConfigId());
			limitEvts.add(getLimitEvent(limit,limitConfig,limitU));
			
		}
		return limitEvts;
		
	}

	private LimitEventProcessor getLimitEvent(Limit limit, LimitConfig limitConfig,
			LimitUsage limitU) {
		// TODO Auto-generated method stub
		LimitEventProcessor limitEvent = new LimitEventProcessor();
		limitEvent.setLimitID(limit.getId());
		limitEvent.setLimitType(limit.getLimitType());
		limitEvent.setLimitStartDate(limit.getLimitDate());
		limitEvent.setLimitExpiryDate(limit.getLimitExpiryDate());
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
		if(limitU.getAmount_used() > limit.getLimitTolarance()) {
			limitEvent.setLimitThreshold(1);
		}
		limitEvent.setLimitWarning(limit.getLimitWarning());
		limitEvent.setLimitThreshold(limit.getLimitTolarance());
		limitEvent.setlimitMinValue(limit.getLimitmin());
		limitEvent.setlimitMaxValue(limit.getLimitmax());
		
		return limitEvent;
	}
	
	
	@Override
	public Vector<Limit> getLimitsOnTrader(int TraderID,Trade trade) throws RemoteException {
		// TODO Auto-generated method stub
		Vector<LimitConfig> limitConfigs = getTraderLimitConfig(trade.getTraderID());
		String limitConfigIDs = LimitUtil.filterLimitsConfigIDs(limitConfigs,trade);
		return	(Vector<Limit>) LimitSQL.getLiveLimitsOnLimitConfigID(limitConfigIDs,dsSQL.getConn());
		
	}

	@Override
	public Vector<Limit> getLimitsOnCounterParty(int CounterPartyID,Trade trade)
			throws RemoteException {
		// TODO Auto-generated method stub
		Vector<LimitConfig> limitConfigs = getCounterPartyLimitConfig(trade.getCpID());
		
		String limitConfigIDs = LimitUtil.filterLimitsConfigIDs(limitConfigs,trade);
		return (Vector<Limit>) LimitSQL.getLiveLimitsOnLimitConfigID(limitConfigIDs,dsSQL.getConn());
		
	}

	
	private boolean  isCounterPartyLimitExceed(int CounterPartyID,Trade trade)
			throws RemoteException {
		// TODO Auto-generated method stub
		boolean  flag = true;
		Vector<LimitConfig> limitConfigs = getCounterPartyLimitConfig(trade.getCpID());
		
		String limitConfigIDs = LimitUtil.filterLimitsConfigIDs(limitConfigs,trade);
		Vector<Limit> cpLimits =  (Vector)LimitSQL.getLiveLimitsOnLimitConfigID(limitConfigIDs,dsSQL.getConn());
		
		if(cpLimits != null) {
			for(int i=0;i< cpLimits.size();i++) {
				if(!isUnderLimit(cpLimits.get(i),trade)) {
					flag = false;
					break;
				}
			}
			
		}
		return flag;
		
	}
	
	private boolean  isTraderLimitExceed(int CounterPartyID,Trade trade)
			throws RemoteException {
		// TODO Auto-generated method stub
		boolean  flag = true;
		Vector<LimitConfig> limitConfigs = getTraderLimitConfig(trade.getCpID());
		
		String limitConfigIDs = LimitUtil.filterLimitsConfigIDs(limitConfigs,trade);
		Vector<Limit> cpLimits =  (Vector)LimitSQL.getLiveLimitsOnLimitConfigID(limitConfigIDs,dsSQL.getConn());
		
		if(cpLimits != null) {
			for(int i=0;i< cpLimits.size();i++) {
				if(!isUnderLimit(cpLimits.get(i),trade)) {
					flag = false;
					break;
				}
			}
			
		}
		return flag;
		
	}
	
	private boolean  isIssuerLimitExceed(int CounterPartyID,Trade trade)
			throws RemoteException {
		// TODO Auto-generated method stub
		boolean  flag = true;
		Vector<LimitConfig> limitConfigs = getIssuerLimitConfig(trade.getCpID());
		
		String limitConfigIDs = LimitUtil.filterLimitsConfigIDs(limitConfigs,trade);
		Vector<Limit> cpLimits =  (Vector)LimitSQL.getLiveLimitsOnLimitConfigID(limitConfigIDs,dsSQL.getConn());
		
		if(cpLimits != null) {
			for(int i=0;i< cpLimits.size();i++) {
				if(!isUnderLimit(cpLimits.get(i),trade)) {
					flag = false;
					break;
				}
			}
			
		}
		return flag;
		
	}
	
	@Override
	public int saveLimit(Limit limit) throws RemoteException {
		// TODO Auto-generated method stub
		return LimitSQL.save(limit, dsSQL.getConn());
	}
	
	

	@Override
	public int updateLimit(Limit limit) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean deleteLimit(Limit limit) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int saveLimitConfig(LimitConfig limitConfig,boolean flagtogetId) throws RemoteException {
		// TODO Auto-generated method stub
		if(flagtogetId) {
		int id = LimitConfigSQL.selectMax(dsSQL.getConn());
		limitConfig.setId(id);
		limitConfig.setParentID(id);
		}
		return LimitConfigSQL.save(limitConfig, dsSQL.getConn());
	}

	@Override
	public LimitConfig getLimitConfig(int limitConfigID) throws RemoteException {
		// TODO Auto-generated method stub
		return LimitConfigSQL.getUniqueLimitConfig(limitConfigID,dsSQL.getConn());
	}

	@Override
	public LimitUsage getLimitUsage(Limit limit)
			throws RemoteException {
		// TODO Auto-generated method stub
		return  LimitUsageSQL.getLimitUsage(limit,dsSQL.getConn());
	}

	@Override
	public Vector<LimitConfig> getCounterPartyLimitConfig(int cpid) throws RemoteException {
		LegalEntity le = ReferenceDataCache.getLegalEntity(cpid);
		String  sql = "limittype = 'CounterPartyLimit' and filtervalue = '"+le.getId()+"'";
		return (Vector<LimitConfig>) LimitConfigSQL.getLimitConfigWhere(sql, dsSQL.getConn());
		// TODO Auto-generated method stu
		
	}

	@Override
	public Vector<LimitConfig> getTraderLimitConfig(int cpid) throws RemoteException {
		LegalEntity le = ReferenceDataCache.getLegalEntity(cpid);
		String  sql = "limittype = 'TraderLimit' and filtervalue = '"+le.getId()+"'";
		return (Vector<LimitConfig>) LimitConfigSQL.getLimitConfigWhere(sql, dsSQL.getConn());
		// TODO Auto-generated method stu
		
	}
	@Override
	public Vector<LimitConfig> getIssuerLimitConfig(int cpid) throws RemoteException {
		LegalEntity le = ReferenceDataCache.getLegalEntity(cpid);
		String  sql = "limittype = 'IssuerLimit' and filtervalue = '"+le.getId()+"'";
		return (Vector<LimitConfig>) LimitConfigSQL.getLimitConfigWhere(sql, dsSQL.getConn());
		// TODO Auto-generated method stu
		
	}
	@Override
	public Vector<LimitConfig> getTraderLimitConfig(String traderID,Trade trade)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Vector<LimitConfig> getNominalLimitConfig(String productType,
			String currency,Trade trade) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isUnderLimit(Limit limit,Trade trade) {
		boolean flag = true;
	    LimitUsage limitU = LimitUsageSQL.getLimitUsage(limit,dsSQL.getConn());
	    double amountused =  limitU.getAmount_used() + getAmount(trade);
	    if(amountused > limit.getLimitmax())  {
	    	flag = false;
	    }
	    if(flag) {
	    	if(amountused < limit.getLimitmin())  {
		    	flag = false;
		    }
	    }
	    return flag;
	}
	
	
	
	private double getAmount(Trade trade) {
		double tradeAmount = 0.0;
		if(trade.getProductType().equalsIgnoreCase("FX")) {
			if(trade.getNominal() < 0)  {
				tradeAmount = trade.getNominal() * -1;
			} else {
			tradeAmount = trade.getNominal();
			}
			
		} else {
		//if(trade.getProductType().equalsIgnoreCase("BOND")) {
			tradeAmount = trade.getTradeAmount();
			
		}
		return tradeAmount;
	}
	@Override
	public Vector<LimitConfig> getSettlementDate(String deliveryDate,Trade trade)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean saveLimitUsage(LimitUsage limitUsage) throws RemoteException {
		// TODO Auto-generated method stub
		return LimitUsageSQL.save(limitUsage, dsSQL.getConn());
	}
	@Override
	public Collection getAllUnquineLimitConfig() throws RemoteException {
		// TODO Auto-generated method stub
		return LimitConfigSQL.selectALL(dsSQL.getConn());
	}
	@Override
	public Vector<LimitConfig> getLimitConfig(String limitConfigName)
			throws RemoteException {
		// TODO Auto-generated method stub
		String sql = " config_name = '"+limitConfigName.trim()+"'";
		return (Vector<LimitConfig>) LimitConfigSQL.getLimitConfigSQLWhere(sql, dsSQL.getConn());
	}
	@Override
	public Vector<Limit> getLimitOnLimitConfig(int limitConfigID)
			throws RemoteException {
		// TODO Auto-generated method stub
		return (Vector) LimitSQL.selectLimitOnLimitConfigId(limitConfigID, dsSQL.getConn());
	}

}
