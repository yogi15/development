package apps.window.uti.propertypane.enums;



import java.rmi.RemoteException;
import java.util.Collection;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.Vector;

import javax.swing.CellEditor;

import com.jidesoft.grid.CellEditorFactory;
import com.jidesoft.grid.CellEditorManager;
import com.jidesoft.grid.CheckBoxListComboBoxCellEditor;
import com.jidesoft.grid.EditorContext;

import apps.window.util.property.SDIProperty;
import beans.CurrencyPair;
import beans.FutureContract;
import beans.Holiday;
import beans.LegalEntity;
import beans.Product;
import beans.StartUPData;

import util.ReferenceDataCache;
import util.RemoteServiceUtil;
import util.SortShell;
import util.commonUTIL;
import constants.CommonConstants;
import constants.FuturesConstants;

import constants.SDIConstants;

public enum SDIPropertyEnum {
	
	  ROLE(SDIConstants.ROLE,getPropertyValue(SDIConstants.ROLE), SDIConstants.SDI_BENEFICARY_PROPERTIES),
	  BENEFICARY(SDIConstants.BENEFICIARY,getPropertyValue(SDIConstants.BENEFICIARY), SDIConstants.SDI_BENEFICARY_PROPERTIES),
	  CURRENCY(SDIConstants.CURRENCY,getPropertyValue(SDIConstants.CURRENCY), SDIConstants.SDI_BENEFICARY_PROPERTIES),
	  PRODUCTTYPE(SDIConstants.PRODUCT,getPropertyValue(SDIConstants.PRODUCT), SDIConstants.SDI_BENEFICARY_PROPERTIES),
	  CONTACTS(SDIConstants.CONTACT,getPropertyValue(SDIConstants.CONTACT), SDIConstants.SDI_BENEFICARY_PROPERTIES),
	  METHOD(SDIConstants.METHOD,getPropertyValue(SDIConstants.METHOD), SDIConstants.SDI_BENEFICARY_PROPERTIES),
	  CASHSECURITY(SDIConstants.CASH_SECURITY,getPropertyValue(SDIConstants.CASH_SECURITY), SDIConstants.SDI_BENEFICARY_PROPERTIES),
	  PO(SDIConstants.PO,getPropertyValue(SDIConstants.PO), SDIConstants.SDI_BENEFICARY_PROPERTIES),
	  PAYREC(SDIConstants.PAY_REC,getPropertyValue(SDIConstants.PAY_REC), SDIConstants.SDI_BENEFICARY_PROPERTIES),
	  PREFERRED(SDIConstants.PREFERRED,getCheckBOXProperty(SDIConstants.PREFERRED),SDIConstants.SDI_BENEFICARY_PROPERTIES),
	  PRIORITY(SDIConstants.PRIORITY,getIntegerProperty(SDIConstants.PRIORITY), SDIConstants.SDI_BENEFICARY_PROPERTIES),
	  AGENTCODE(SDIConstants.AGENTCODE,getPropertyValue(SDIConstants.AGENTCODE), SDIConstants.SDI_AGENT_PROPERTIES),
	  ACC(SDIConstants.ACC,getStringProperty(SDIConstants.ACC), SDIConstants.SDI_AGENT_PROPERTIES), //string type
	  GL(SDIConstants.GL,getPropertyValue(SDIConstants.GL), SDIConstants.SDI_AGENT_PROPERTIES),	 
	  AGENTCONTACT(SDIConstants.AGENTCONTACT,getPropertyValue(SDIConstants.AGENTCONTACT), SDIConstants.SDI_AGENT_PROPERTIES),
	  
	  INTER1AGENT(SDIConstants.INTER1AGENTCODE,getPropertyValue(SDIConstants.INTER1AGENTCODE), SDIConstants.SDI_INTERMEDIARY_PROPERTIES1),
	  
	  INTER1ACC(SDIConstants.INTER1ACC,getStringProperty(SDIConstants.INTER1ACC), SDIConstants.SDI_INTERMEDIARY_PROPERTIES1),  //string type
	  INTER1GL(SDIConstants.INTER1GL,getPropertyValue(SDIConstants.INTER1GL), SDIConstants.SDI_INTERMEDIARY_PROPERTIES1), 
	  INTER1AGENTCONTACT(SDIConstants.INTER1AGENTCONTACT,getPropertyValue(SDIConstants.INTER1AGENTCONTACT), SDIConstants.SDI_INTERMEDIARY_PROPERTIES1),
		

	  INTER2AGENT(SDIConstants.INTER2AGENTCODE,getPropertyValue(SDIConstants.INTER2AGENTCODE), SDIConstants.SDI_INTERMEDIARY_PROPERTIES2),
	  
	  INTER2ACC(SDIConstants.INTER2ACC,getStringProperty(SDIConstants.INTER2ACC), SDIConstants.SDI_INTERMEDIARY_PROPERTIES2), //string type
	  INTER2GL(SDIConstants.INTER2GL,getPropertyValue(SDIConstants.INTER2GL), SDIConstants.SDI_INTERMEDIARY_PROPERTIES2),
	  INTER2AGENTCONTACT(SDIConstants.INTER2AGENTCONTACT,getPropertyValue(SDIConstants.INTER2AGENTCONTACT), SDIConstants.SDI_INTERMEDIARY_PROPERTIES2);
		
		
	//  INTER1AGENTCODE(SDIConstants.INTER1AGENTCODE,getPropertyValue(SDIConstants.INTER1AGENTCODE), SDIConstants.SDI_INTERMIATE_PROPERTIES1),
	  //INTER1AGENTCODEACC(SDIConstants.ACC,getStringProperty(SDIConstants.ACC), SDIConstants.SDI_INTERMIATE_PROPERTIES1),
	 // INTER1AGENTCODEGL(SDIConstants.GL,getPropertyValue(SDIConstants.GL), SDIConstants.SDI_INTERMIATE_PROPERTIES1),	 
	 // INTER1AGENTCODEAGENTCONTACT(SDIConstants.AGENTCONTACT,getPropertyValue(SDIConstants.AGENTCONTACT), SDIConstants.SDI_INTERMIATE_PROPERTIES1);
		
		

	
	private String propertyName;
	private String _description;
	private SDIProperty property;
	static String role = "";


	SDIPropertyEnum(String propertyName, SDIProperty property, String category) {
		this.propertyName = propertyName;
		this.property = property;
		property.setName(propertyName);
		property.setCategory(category);
	}
	

	public static SDIProperty getPropertyValue(String domainName) {
		// TODO Auto-generated method stub
		Vector<String> propertyValuesVec = null;
		 SDIProperty property = null;
		   if(domainName.equalsIgnoreCase(SDIConstants.ROLE))  {
			  
			 propertyValuesVec = getPropertyValueVec(domainName);
			 property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
			
		   } else  if(domainName.equalsIgnoreCase(SDIConstants.BENEFICIARY))  {
			 propertyValuesVec = getPropertyValueBenificary("CounterParty");
			 property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
			
		   } else if(domainName.equalsIgnoreCase(SDIConstants.CURRENCY))  {
			 propertyValuesVec = getPropertyValueVec(domainName);
			 property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
			
		   }else if(domainName.equalsIgnoreCase(SDIConstants.PRODUCT))  {
			 propertyValuesVec = getPropertyValueVec(domainName);
			 property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
			
		   }else if(domainName.equalsIgnoreCase(SDIConstants.CONTACT))  {
			 propertyValuesVec = getPropertyValueVec(domainName);
			 property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
			
		   }else if(domainName.equalsIgnoreCase(SDIConstants.METHOD))  {
			 propertyValuesVec = getPropertyValueVec(domainName);
			 property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
			
		   }else if(domainName.equalsIgnoreCase(SDIConstants.CASH_SECURITY))  {
			 propertyValuesVec = getPropertyValueVec(domainName);
			 property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
			
		   }else if(domainName.equalsIgnoreCase(SDIConstants.PAY_REC))  {
			   Vector<String> payRec = new Vector<String>();
			   payRec.add("PAY");
			   payRec.add("REC");
			 propertyValuesVec = payRec;
			 property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
			
		   
	}else if(domainName.equalsIgnoreCase(SDIConstants.AGENTCONTACT))  {
		 propertyValuesVec = getPropertyValueVec(SDIConstants.CONTACT); // pass the value of startup data
		 property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
		
	   }
		   else if(domainName.equalsIgnoreCase(SDIConstants.PO))  {
			 //  SDIProperty property = SDIProperty.createPOBox(SDIConstants.PO, "Exchange", propertyValuesVec,null,"PO");
			//	return property;


				 propertyValuesVec = getPropertyValueBenificary("PO");
				 property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
			
		   }
		   else if(domainName.equalsIgnoreCase(SDIConstants.AGENTCODE))  {
			//   SDIProperty property = SDIProperty.createPOBox(SDIConstants.PO, "Exchange", propertyValuesVec,null,"Agent");
			//	return property;

				 propertyValuesVec = getPropertyValueBenificary("Agent");
				 property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
		   }
		   else if(domainName.equalsIgnoreCase(SDIConstants.GL))  {
				//   SDIProperty property = SDIProperty.createPOBox(SDIConstants.PO, "Exchange", propertyValuesVec,null,"Agent");
				//	return property;
			   propertyValuesVec =   getPropertyValueBenificary("Agent");
			   property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
				
			   }
		   else if(domainName.equalsIgnoreCase(SDIConstants.INTER1GL))  {
				//   SDIProperty property = SDIProperty.createPOBox(SDIConstants.PO, "Exchange", propertyValuesVec,null,"Agent");
				//	return property;
			   propertyValuesVec =   getPropertyValueBenificary("Agent");
			   property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
			   }
		   else if(domainName.equalsIgnoreCase(SDIConstants.INTER2GL))  {
				//   SDIProperty property = SDIProperty.createPOBox(SDIConstants.PO, "Exchange", propertyValuesVec,null,"Agent");
				//	return property;
			   propertyValuesVec =   getPropertyValueBenificary("Agent");
			   property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
				
			   }
		   else if(domainName.equalsIgnoreCase(SDIConstants.GL))  {
				//   SDIProperty property = SDIProperty.createPOBox(SDIConstants.PO, "Exchange", propertyValuesVec,null,"Agent");
				//	return property;
			   propertyValuesVec =   getPropertyValueBenificary("Agent");
			   property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
				
			   }
		   else if(domainName.equalsIgnoreCase(SDIConstants.INTER1AGENTCODE))  {
				//   SDIProperty property = SDIProperty.createPOBox(SDIConstants.PO, "Exchange", propertyValuesVec,null,"Agent");
				//	return property;

					 propertyValuesVec = getPropertyValueBenificary("Agent");
					 property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
			   }
			   
				   
			   else if(domainName.equalsIgnoreCase(SDIConstants.INTER1AGENTCONTACT))  {
					//   SDIProperty property = SDIProperty.createPOBox(SDIConstants.PO, "Exchange", propertyValuesVec,null,"Agent");
					//	return property;
				   propertyValuesVec =   getPropertyValueVec(SDIConstants.CONTACT);
				   property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
					
				   }
			   else if(domainName.equalsIgnoreCase(SDIConstants.INTER2AGENTCONTACT))  {
					//   SDIProperty property = SDIProperty.createPOBox(SDIConstants.PO, "Exchange", propertyValuesVec,null,"Agent");
					//	return property;
				   propertyValuesVec =   getPropertyValueVec(SDIConstants.CONTACT);
				   property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
					
				   }
			  
			   else if(domainName.equalsIgnoreCase(SDIConstants.INTER2AGENTCODE))  {
					//   SDIProperty property = SDIProperty.createPOBox(SDIConstants.PO, "Exchange", propertyValuesVec,null,"Agent");
					//	return property;

						 propertyValuesVec = getPropertyValueBenificary("Agent");
						 property = SDIProperty.createAutoCompleteComboBox(domainName, "Exchange",propertyValuesVec);
				   }
		 
				   
			
			return property;
	}
	public static SDIProperty getPropertyBeneficiaryOnRole(String role) {
		// TODO Auto-generated method stub
		Vector<String> propertyValuesVec = null;
		
		   
			 propertyValuesVec = null;// getPropertyValueBenificary(role);
			  
		   
		//SDIProperty property = SDIProperty.createAutoCompleteComboBox(SDIConstants.BENEFICIARY, "Exchange", propertyValuesVec);
			 SDIProperty property = SDIProperty.createLEBox(SDIConstants.BENEFICIARY, "Exchange", propertyValuesVec,null,role);
			return property;
	}
	public static SDIProperty getPropertyPO(LegalEntity le,String role) {
		// TODO Auto-generated method stub
		Vector<String> propertyValuesVec = null;
		
		   
			 propertyValuesVec = null;// getPropertyValueBenificary(role);
			  
		   
		//SDIProperty property = SDIProperty.createAutoCompleteComboBox(SDIConstants.BENEFICIARY, "Exchange", propertyValuesVec);
			 
			 SDIProperty property = null;//
			 if(role.equalsIgnoreCase("PO"))  {
				 property =  SDIProperty.getProperty("NOPO", "Exchange", String.class);
					
					property.setEditable(false);
				 return property;
			 } else {
				 property =  SDIProperty.createPOBox(SDIConstants.PO, "Exchange", propertyValuesVec,le,"PO");
				 property.setEditable(true);
			 }
			return property;
	}
	public static SDIProperty getPropertyAgent(String role) {
		// TODO Auto-generated method stub
		Vector<String> propertyValuesVec = null;
		
		   
			 propertyValuesVec = null;// getPropertyValueBenificary(role);
			  
		   
		//SDIProperty property = SDIProperty.createAutoCompleteComboBox(SDIConstants.BENEFICIARY, "Exchange", propertyValuesVec);
			 SDIProperty property = SDIProperty.createPOBox(SDIConstants.AGENTCODE, "Exchange", propertyValuesVec,null,"Agent");
			return property;
	}
	public static SDIProperty getPropertyDisable() {
		SDIProperty disProperty = new SDIProperty("Disable","DisablePO");
		disProperty.setEditable(false);
		return disProperty;
		
	}
	public static SDIProperty getPropertyAccount(String currency,LegalEntity po) {
		// TODO Auto-generated method stub
		Vector<String> propertyValuesVec = null;
		
		   
			 propertyValuesVec = null;// getPropertyValueBenificary(role);
			  
		   
		//SDIProperty property = SDIProperty.createAutoCompleteComboBox(SDIConstants.BENEFICIARY, "Exchange", propertyValuesVec);
			 SDIProperty property = SDIProperty.createAccontBox(SDIConstants.GL, "Exchange", propertyValuesVec,po,currency);
			return property;
	}
	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public SDIProperty getProperty(String propertyName, SDIProperty property, String category) {
		this.propertyName = propertyName;
		this.property = property;
		property.setName(propertyName);
		property.setCategory(category);
	
		return property;
	}
	
	public SDIProperty getProperty() {
		return property;
	}
	public void setProperty(SDIProperty property) {
		this.property = property;
	}
	
public static SDIProperty getCheckBOXProperty(String domainName) {
		
		SDIProperty property = SDIProperty.createCheckBOX(domainName, "Exchange", Boolean.class);
		property.setValue(Boolean.TRUE);
		property.setEditable(Boolean.TRUE);
		
		return property;
		
	}
	
	public static SDIProperty getIntegerProperty(String domainName) {
		
		SDIProperty property = SDIProperty.getProperty(domainName, "Exchange", Integer.class);
		
		return property;
		
	}
	
	public static SDIProperty getStringProperty(String domainName) {
		
		SDIProperty property = SDIProperty.getProperty(domainName, "Exchange", String.class);
		
		return property;
		
	}
	
	public static SDIProperty getDoubleProperty(String domainName) {
		
		SDIProperty property = SDIProperty.getProperty(domainName, "Exchange", Double.class);
		
		return property;
		
	}
private static Vector<String> getPropertyValueVec(String domainName) {
		
		Vector<String> propertyValuesVec = new Vector<String>();
		
		StartUPData startUPData = new StartUPData();
		startUPData.setName(domainName);
		//FutureContractProperty property = new FutureContractProperty();
			
		Collection<StartUPData> startUPDatas = new Vector<StartUPData>();
		
		try {
		 startUPDatas =	RemoteServiceUtil.getRemoteReferenceDataService().selectStartUPData(startUPData);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<StartUPData> iterator = startUPDatas.iterator();
		while (iterator.hasNext()) {
			StartUPData data = iterator.next();
			propertyValuesVec.add(data.getValue());
		}
		
		return propertyValuesVec;
	}
private static Vector<String> getPropertyValueBenificary(String domainName) {
	
	Vector<String> propertyValuesVecEX = new Vector<String>();
	
	StartUPData startUPData = new StartUPData();
	startUPData.setName(domainName);
	if(commonUTIL.isEmpty(domainName))
		return null;
	//FutureContractProperty property = new FutureContractProperty();
		
	Collection<StartUPData> startUPDatas = new Vector<StartUPData>();
	
	Collection<LegalEntity> exchanges = new Vector<LegalEntity>();
	
	try {
		exchanges =	RemoteServiceUtil.getRemoteReferenceDataService().getLegalEntityDataOnRole(domainName);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	Iterator<LegalEntity> iterator = exchanges.iterator();
	while (iterator.hasNext()) {
		LegalEntity le = iterator.next();
		propertyValuesVecEX.add(le.getAlias());
	}
	
	return propertyValuesVecEX;
}

}
