package apps.window.util.propertyPane.enumsList;


import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import util.ReferenceDataCache;
import util.RemoteServiceUtil;
import util.commonUTIL;

import apps.window.uti.propertypane.enums.FutureContractPropertyEnum;
import apps.window.uti.propertypane.enums.SDIPropertyEnum;
import apps.window.util.property.FutureContractProperty;
import apps.window.util.property.SDIProperty;

import constants.FuturesConstants;
import constants.SDIConstants;

import beans.StartUPData;

import com.jidesoft.grid.Property;
public enum SDIPropertyEnumList {
	
	SDI_BENEFICARY_PROPERTIES(getBeneficaryPropertyList()),
	SDI_AGENT_PROPERTIES(getAgentPropertyList());
	
	private List<SDIProperty> propertyList = new ArrayList<SDIProperty>();
	public static Hashtable<String, String> propertyListHash = new Hashtable<String, String>();
	
	
	public void clearALLList() {
		propertyList.clear();
	}
	
	
	public List<SDIProperty> getPropertyList(String name) {
		if(name.equalsIgnoreCase(SDIConstants.SDI_BENEFICARY_PROPERTIES)) 
			return getBeneficaryPropertyList();
		if(name.equalsIgnoreCase(SDIConstants.SDI_AGENT_PROPERTIES)) 
			return getAgentPropertyList();
		
		return propertyList;
	}

	public void setPropertyList(List<SDIProperty> propertyList) {
		this.propertyList = propertyList;
	}

	SDIPropertyEnumList(List<SDIProperty> propertyList) {

		this.propertyList = propertyList;

	}

	
	
	private static List<SDIProperty> getBeneficaryPropertyList() {

		List<SDIProperty> propertyList = new ArrayList<SDIProperty>();
		
		propertyList.add((SDIProperty) SDIPropertyEnum.ROLE.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.BENEFICARY.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.CURRENCY.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.PRODUCTTYPE.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.CONTACTS.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.METHOD.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.CASHSECURITY.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.PAYREC.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.PO.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.PREFERRED.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.PRIORITY.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.AGENTCODE.getProperty());
		
		propertyList.add((SDIProperty) SDIPropertyEnum.GL.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.ACC.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.AGENTCONTACT.getProperty());
propertyList.add((SDIProperty) SDIPropertyEnum.INTER1AGENT.getProperty());
		
	//	propertyList.add((SDIProperty) SDIPropertyEnum.GL.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.INTER1ACC.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.INTER1AGENTCONTACT.getProperty());

	   //    propertyList.add((SDIProperty) SDIPropertyEnum.INTER1GL.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.INTER2AGENT.getProperty());
		//	propertyList.add((SDIProperty) SDIPropertyEnum.GL.getProperty());
			propertyList.add((SDIProperty) SDIPropertyEnum.INTER2ACC.getProperty());
			propertyList.add((SDIProperty) SDIPropertyEnum.INTER2AGENTCONTACT.getProperty());

		  //     propertyList.add((SDIProperty) SDIPropertyEnum.INTER2GL.getProperty());
		
		
	//	propertyList.add((SDIProperty) SDIPropertyEnum.INTER1AGENTCODE.getProperty());
	//	propertyList.add((SDIProperty) SDIPropertyEnum.INTER1AGENTCODEACC.getProperty());
		//propertyList.add((SDIProperty) SDIPropertyEnum.INTER1AGENTCODEGL.getProperty());
	//	propertyList.add((SDIProperty) SDIPropertyEnum.INTER1AGENTCODEACC.getProperty());
		
		return propertyList;
	}
	private static List<SDIProperty> getAgentPropertyList() {

		List<SDIProperty> propertyList = new ArrayList<SDIProperty>();
		
		propertyList.add((SDIProperty) SDIPropertyEnum.AGENTCODE.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.ACC.getProperty());
		propertyList.add((SDIProperty) SDIPropertyEnum.GL.getProperty());
		return propertyList;
	}

  
}
