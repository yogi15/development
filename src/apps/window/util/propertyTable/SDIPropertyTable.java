package apps.window.util.propertyTable;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.CellEditor;

import apps.window.uti.propertypane.enums.SDIPropertyEnum;
import apps.window.util.property.SDIProperty;
import apps.window.util.propertyPane.editor.LESelectionCellEditor;
import apps.window.util.propertyPane.enumsList.FutureContractProductPropertyEnumList;
import apps.window.util.propertyPane.enumsList.SDIPropertyEnumList;
import beans.Account;
import beans.LegalEntity;

import com.jidesoft.grid.CellEditorFactory;
import com.jidesoft.grid.CellEditorManager;
import com.jidesoft.grid.CheckBoxListComboBoxCellEditor;
import com.jidesoft.grid.EditorContext;
import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.grid.PropertyTableModel;

import constants.FuturesConstants;
import constants.SDIConstants;


	
public class SDIPropertyTable extends PropertyTable implements PropertyChangeListener    {
	String productName = "";
	//private PropertyTable propertyTable = null;
	LegalEntity  beneficiary = null;
	LegalEntity  po = null;
	
	Account account = null;
	String currency = "";
	Account Inter1Account = null;
	/**
	 * @return the inter1Account
	 */
	public Account getInter1Account() {
		return Inter1Account;
	}

	/**
	 * @param inter1Account the inter1Account to set
	 */
	public void setInter1Account(Account inter1Account) {
		Inter1Account = inter1Account;
	}

	/**
	 * @return the inter2Account
	 */
	public Account getInter2Account() {
		return Inter2Account;
	}

	/**
	 * @param inter2Account the inter2Account to set
	 */
	public void setInter2Account(Account inter2Account) {
		Inter2Account = inter2Account;
	}

	/**
	 * @return the interAgent1
	 */
	public LegalEntity getInterAgent1() {
		return interAgent1;
	}

	/**
	 * @param interAgent1 the interAgent1 to set
	 */
	public void setInterAgent1(LegalEntity interAgent1) {
		this.interAgent1 = interAgent1;
	}

	/**
	 * @return the interAgent2
	 */
	public LegalEntity getInterAgent2() {
		return interAgent2;
	}

	/**
	 * @param interAgent2 the interAgent2 to set
	 */
	public void setInterAgent2(LegalEntity interAgent2) {
		this.interAgent2 = interAgent2;
	}

	/**
	 * @return the account
	 */
	public Account getAccount() {
		return account;
	}

	Account Inter2Account = null;
	LegalEntity interAgent1;
	LegalEntity interAgent2;
	
	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency1) {
		this.currency = currency1;
	}

	/**
	 * @return the po
	 */
	public LegalEntity getPo() {
		return po;
	}

	/**
	 * @param po the po to set
	 */
	public void setPo(LegalEntity po) {
		this.po = po;
	}

	/**
	 * @return the agent
	 */
	public LegalEntity getAgent() {
		return agent;
	}

	/**
	 * @param agent the agent to set
	 */
	public void setAgent(LegalEntity agent) {
		this.agent = agent;
	}

	LegalEntity  agent = null;
	/**
	 * @return the beneficiary
	 */
	public LegalEntity getBeneficiary() {
		return beneficiary;
	}

	/**
	 * @param beneficiary the beneficiary to set
	 */
	public void setBeneficiary(LegalEntity beneficiary) {
		this.beneficiary = beneficiary;
	}

	List<SDIProperty> sdiProductProperties = null;
	public SDIPropertyTable(String productName) {

		this.productName = productName;
		 
	 

	}
	
	 public PropertyTable getSDIPropertyTable() {
	  
		 sdiProductProperties = getSDIProperties();
	//	 propertyTable = new PropertyTable();

		PropertyTableModel<SDIProperty> model = new PropertyTableModel<SDIProperty>(
				sdiProductProperties);
		model.setMiscCategoryName(" ");
		// model.addPropertyChangeListener(listener);
		setModel(model);
		getColumnModel().getColumn(0).setMinWidth(100);
		expandAll();
		addPropertyChangeListener(this);
		
		//propertyTable.addPropertyChangeListener("Exchange", this);
		return this;
    }
	
	 private List<SDIProperty> getSDIProperties() {

			List<SDIProperty> properties = null;

			if (productName.equals(SDIConstants.SDI_AGENT_PROPERTIES)) {

				properties = getSDI_AGENT_Properties();

			} else if (productName.equals(SDIConstants.SDI_BENEFICARY_PROPERTIES)) {

				properties = getSDI_BENEFICIARY_Properties();
				System.out.println(properties.size() + "***************************** ");

			}else if (productName.equals(SDIConstants.SDI_ATTRIBUTES)) {

				properties = getFUTURE_INTERMIDRY_Properties();

			}

			return properties;

		}
	 
		List<SDIProperty> propertiesBeneficiary = null;
		
		public List<SDIProperty> getPropertiesTrade() {
			return propertiesBeneficiary;
		}
		public void setPropertiesTrade(List<SDIProperty>  propertiesTrade) {
			this.propertiesTrade = propertiesTrade;
		}
	 
		private List<SDIProperty> getFUTURE_INTERMIDRY_Properties() {
		// TODO Auto-generated method stub
		return getSDI_Properties();
	}

		private List<SDIProperty> getSDI_BENEFICIARY_Properties() {
		// TODO Auto-generated method stub
		return getSDI_Properties();
	}

		private List<SDIProperty> getSDI_AGENT_Properties() {
		// TODO Auto-generated method stub
		return getSDI_Properties();
	}


		private List<SDIProperty> getSDI_Properties() {

	List<SDIProperty> properties = new ArrayList<SDIProperty>();
			
			if (productName.equals(SDIConstants.SDI_BENEFICARY_PROPERTIES)) {
					
				properties = SDIPropertyEnumList.SDI_BENEFICARY_PROPERTIES.getPropertyList(SDIConstants.SDI_BENEFICARY_PROPERTIES);
				return properties;
				
			} if (productName.equals(SDIConstants.SDI_AGENT_PROPERTIES)) {
				
			properties = SDIPropertyEnumList.SDI_AGENT_PROPERTIES.getPropertyList(SDIConstants.SDI_AGENT_PROPERTIES);
			return properties;
			
		} 
			return properties;

		}

		List<SDIProperty> propertiesTrade = null;
	 /**
		 * @return the role
		 */
		public String getRole() {
			return role;
		}

		/**
		 * @param role the role to set
		 */
		public void setRole(String role) {
			this.role = role;
		}

	String role ="";
	 
	 
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		// TODO Auto-generated method stub
		PropertyTable table = (PropertyTable) evt.getSource();
		String role = "";
		int row = table.getSelectedRow();
		
		if(row == -1  )
			return;
		
		Property property = table.getSelectedProperty();
		if(property.getName().equalsIgnoreCase(SDIConstants.ROLE)) {
			if(property.getValue() == null)
				return;
			
		 //  int rowid = 	getRowidONPropertyName(FuturesConstants.CONTRACT,table.getModel());
			
			
			setRole((String) property.getValue());
		
			
			 SDIProperty  proper = SDIPropertyEnum.getPropertyBeneficiaryOnRole(getRole());
			 SDIProperty properPO = null;
			
			 if(getRole().equalsIgnoreCase("PO")) {
				 property.setValueAt("", 8);
				  properPO = SDIPropertyEnum.getPropertyDisable();
				  Property poperty = table.getPropertyTableModel().getPropertyAt(8);
			//	  poperty.setHidden(true);
				  poperty.setEditable(false);
				  setPo(null);
				  SDIProperty  properAccount  = 	SDIPropertyEnum.getPropertyAccount(getCurrency(), getPo());
					 
					 sdiProductProperties.set(10, properAccount);
				//  table.getPropertyTableModel().getOriginalProperties().set(8,poperty);
				//  table.getPropertyTableModel().refresh();
					
			 } else {
				 properPO =  SDIPropertyEnum.getPropertyPO(getBeneficiary(),getRole());
				  Property poperty = table.getPropertyTableModel().getPropertyAt(8);
				//  poperty.setHidden(true);
				  poperty.setEditable(false);
				//  table.getPropertyTableModel().getOriginalProperties().set(8,poperty);
				//  table.getPropertyTableModel().refresh();
			 }
			 SDIProperty  properAgent = SDIPropertyEnum.getPropertyAgent("Agent");
			 SDIProperty  properInter1Agent = SDIPropertyEnum.getPropertyAgent("Agent");
			 SDIProperty  properInter2Agent = SDIPropertyEnum.getPropertyAgent("Agent");
			
			 if(proper != null) {
				 if(sdiProductProperties.size() > 1) {
					// sdiProductProperties.get(1)
					 sdiProductProperties.set(1, proper);
				     sdiProductProperties.set(8, properPO);
					 sdiProductProperties.set(9, properAgent);
					 sdiProductProperties.set(13, properAgent);
					 sdiProductProperties.set(17, properAgent);
					 
				 }
			 }
		}
			 if(property.getName().equalsIgnoreCase(SDIConstants.BENEFICIARY)) {
					if(property.getValue() == null)
						return;
					String name = "";
				 //  int rowid = 	getRowidONPropertyName(FuturesConstants.CONTRACT,table.getModel());
					if(property.getValue() instanceof String) {
					name  = (String) property.getValue();
					} else if(property.getValue() instanceof LegalEntity) {
						LegalEntity le =(LegalEntity) property.getValue();
						name = le.getName();
						setBeneficiary(le);
					
					}
				property.setValue(name);
					// SDIProperty  proper = SDIPropertyEnum.getPropertyBeneficiaryOnRole(value.getRole());
				/*	 if(proper != null) {
						 if(sdiProductProperties.size() > 1) {
							// sdiProductProperties.get(1)
							 sdiProductProperties.set(1, proper);
						 }
					 } */
			 }
			 if(property.getName().equalsIgnoreCase(SDIConstants.PO)) {
				
											String name = "";
					
				 //  int rowid = 	getRowidONPropertyName(FuturesConstants.CONTRACT,table.getModel());
					if(getRole().equalsIgnoreCase("PO")) {
						 SDIProperty properPO = SDIPropertyEnum.getPropertyDisable();
						 sdiProductProperties.set(8, properPO);
						 property.setEditable(false);
						setPo(null);
						// property.setHidden(true);
						 property.setValue(new String(""));
						 sdiProductProperties.set(10, properPO);
						 return;
					} else {
						property.setEditable(true);
						// property.setHidden(false);
					}
						
					if(property.getValue() instanceof String) {
					name  = (String) property.getValue();
					} else if(property.getValue() instanceof LegalEntity) {
						LegalEntity le =(LegalEntity) property.getValue();
						name = le.getName();
						setPo(le);
					//	System.out.println(currency);
						SDIProperty  properAccount  = 	SDIPropertyEnum.getPropertyAccount(getCurrency(), getPo());
						 
						 sdiProductProperties.set(10, properAccount);
						 sdiProductProperties.set(16, properAccount);
					}
				property.setValue(name);
					
			 }
			 if(property.getName().equalsIgnoreCase(SDIConstants.CURRENCY)) {
					if(property.getValue() == null)
						return;
					String name = "";
				 
					if(property.getValue() instanceof String) {
					name  = (String) property.getValue();
					setCurrency(name);
					} else if(property.getValue() instanceof LegalEntity) {
						String  currency =(String) property.getValue();
						setCurrency(currency);
						 SDIProperty  properAccount  = 	SDIPropertyEnum.getPropertyAccount(currency, getPo());
						 
						 sdiProductProperties.set(10, properAccount);
					
					}
			//	property.setValue(name);
					// SDIProperty  proper = SDIPropertyEnum.getPropertyBeneficiaryOnRole(value.getRole());
				/*	 if(proper != null) {
						 if(sdiProductProperties.size() > 1) {
							// sdiProductProperties.get(1)
							 sdiProductProperties.set(1, proper);
						 }
					 } */
			 }
			 if(property.getName().equalsIgnoreCase(SDIConstants.AGENTCODE)) {
					if(property.getValue() == null)
						return;
					String name = "";
				 //  int rowid = 	getRowidONPropertyName(FuturesConstants.CONTRACT,table.getModel());
					if(property.getValue() instanceof String) {
					name  = (String) property.getValue();
					} else if(property.getValue() instanceof LegalEntity) {
						LegalEntity le =(LegalEntity) property.getValue();
						name = le.getName();
						setAgent(le);
					
					}
				property.setValue(name);
					// SDIProperty  proper = SDIPropertyEnum.getPropertyBeneficiaryOnRole(value.getRole());
				/*	 if(proper != null) {
						 if(sdiProductProperties.size() > 1) {
							// sdiProductProperties.get(1)
							 sdiProductProperties.set(1, proper);
						 }
					 } */
			 }
			 if(property.getName().equalsIgnoreCase(SDIConstants.GL)) {
					if(property.getValue() == null)
						return;
					String name = "";
				 //  int rowid = 	getRowidONPropertyName(FuturesConstants.CONTRACT,table.getModel());
					if(property.getValue() instanceof String) {
					name  = (String) property.getValue();
					} else if(property.getValue() instanceof Account) {
						Account account =(Account) property.getValue();
						name = account.getAccountName();//.getName();
						setAccount(account);
					
					}
				property.setValue(name);
					// SDIProperty  proper = SDIPropertyEnum.getPropertyBeneficiaryOnRole(value.getRole());
				/*	 if(proper != null) {
						 if(sdiProductProperties.size() > 1) {
							// sdiProductProperties.get(1)
							 sdiProductProperties.set(1, proper);
						 }
					 } */
			 }
			 if(property.getName().equalsIgnoreCase(SDIConstants.INTER1GL)) {
					if(property.getValue() == null)
						return;
					String name = "";
				 //  int rowid = 	getRowidONPropertyName(FuturesConstants.CONTRACT,table.getModel());
					if(property.getValue() instanceof String) {
					name  = (String) property.getValue();
					} else if(property.getValue() instanceof Account) {
						Account account =(Account) property.getValue();
						name = account.getAccountName();//.getName();
						setInter1Account(account);
					
					}
				property.setValue(name);
					// SDIProperty  proper = SDIPropertyEnum.getPropertyBeneficiaryOnRole(value.getRole());
				/*	 if(proper != null) {
						 if(sdiProductProperties.size() > 1) {
							// sdiProductProperties.get(1)
							 sdiProductProperties.set(1, proper);
						 }
					 } */
			 }
			 if(property.getName().equalsIgnoreCase(SDIConstants.INTER2GL)) {
					if(property.getValue() == null)
						return;
					String name = "";
				 //  int rowid = 	getRowidONPropertyName(FuturesConstants.CONTRACT,table.getModel());
					if(property.getValue() instanceof String) {
					name  = (String) property.getValue();
					} else if(property.getValue() instanceof Account) {
						Account account =(Account) property.getValue();
						name = account.getAccountName();//.getName();
						setInter2Account(account);
					
					}
				property.setValue(name);
					// SDIProperty  proper = SDIPropertyEnum.getPropertyBeneficiaryOnRole(value.getRole());
				/*	 if(proper != null) {
						 if(sdiProductProperties.size() > 1) {
							// sdiProductProperties.get(1)
							 sdiProductProperties.set(1, proper);
						 }
					 } */
			 }
			 if(property.getName().equalsIgnoreCase(SDIConstants.INTER1AGENTCODE)) {
					if(property.getValue() == null)
						return;
					String name = "";
				 //  int rowid = 	getRowidONPropertyName(FuturesConstants.CONTRACT,table.getModel());
					if(property.getValue() instanceof String) {
					name  = (String) property.getValue();
					} else if(property.getValue() instanceof Account) {
						LegalEntity le =(LegalEntity) property.getValue();
						name = le.getName();//.getName();
						setInterAgent1(le);
					
					}			property.setValue(name);
					// SDIProperty  proper = SDIPropertyEnum.getPropertyBeneficiaryOnRole(value.getRole());
				/*	 if(proper != null) {
						 if(sdiProductProperties.size() > 1) {
							// sdiProductProperties.get(1)
							 sdiProductProperties.set(1, proper);
						 }
					 } */
			 }
			 if(property.getName().equalsIgnoreCase(SDIConstants.INTER2AGENTCODE)) {
					if(property.getValue() == null)
						return;
					String name = "";
				 //  int rowid = 	getRowidONPropertyName(FuturesConstants.CONTRACT,table.getModel());
					if(property.getValue() instanceof String) {
					name  = (String) property.getValue();
					} else if(property.getValue() instanceof Account) {
						LegalEntity le =(LegalEntity) property.getValue();
						name = le.getName();//.getName();
						setInterAgent2(le);
					
					}			property.setValue(name);
					// SDIProperty  proper = SDIPropertyEnum.getPropertyBeneficiaryOnRole(value.getRole());
				/*	 if(proper != null) {
						 if(sdiProductProperties.size() > 1) {
							// sdiProductProperties.get(1)
							 sdiProductProperties.set(1, proper);
						 }
					 } */
			 }
			 if(property.getName().equalsIgnoreCase(SDIConstants.PREFERRED)) {
					System.out.println(property.getValue());
					setPreferredFlag((boolean) property.getValue());
			 }
			 if(property.getName().equalsIgnoreCase(SDIConstants.PRIORITY)) {
				 System.out.println(property.getValue());
				 if(property.getValue() == null)
					 setPriority(0);
				 else 
					 
				 setPriority(((Integer) property.getValue()).intValue());
				
			 }
			// table.getModel().setValueAt(proper, rowid, 1);
			 
			
		

		
	//	PropertyTableModel<FutureContractProperty> propertyModel = (PropertyTableModel<FutureContractProperty>) table.getModel();
    
	}
	/**
	 * @return the priority
	 */
	public int getPriority() {
		return priority;
	}

	/**
	 * @param priority the priority to set
	 */
	public void setPriority(int priority) {
		this.priority = priority;
	}

	int priority =0;
	
    boolean preferredFlag = true;
    
	/**
	 * @return the preferredFlag
	 */
	public boolean isPreferredFlag() {
		return preferredFlag;
	}

	/**
	 * @param preferredFlag the preferredFlag to set
	 */
	public void setPreferredFlag(boolean preferredFlag) {
		this.preferredFlag = preferredFlag;
	}

	public void setAccount(Account account2) {
		// TODO Auto-generated method stub
		this.account = account2;
		
	}

}
