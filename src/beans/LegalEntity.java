package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import constants.LegalEntityConstants;

import apps.window.util.propertyUtil.Selection;
 
import util.commonUTIL;

public class LegalEntity implements BaseBean {

	int id;
	String Status = null;
	String name;
	String role;
	String attributes = "";
	String alias = "";
	String contact = "";
	String country = "";
	
	
	AttributeContainer attributesData = null;
	

	/**
	 * @return the attributesData
	 */
	public AttributeContainer getAttributesData() {
		return attributesData;
	}

	/**
	 * @param attributesData the attributesData to set
	 */
	public void setAttributesData(AttributeContainer attributesData) {
		this.attributesData = attributesData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getstatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getAttributes() {
		return attributes;
	}

	public void setAttributes(String attributes) {
		this.attributes = attributes;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getAttributeValue(String attributeDataName) {

		String attr1 = getAttributes();
		String attributes[] = attr1.split(";");
		String value = "";

		for (int i = 0; i < attributes.length; i++) {
			String attribute = attributes[i];

			if (attribute.contains(attributeDataName)) {
				value = attribute.substring(attribute.indexOf("=") + 1,
						attribute.length());
				if (!commonUTIL.isEmpty(value))
					break;
			}
		}

		return value;

	}

	@Override
	public Object getPropertyValue(String propertyPaneColumnName) {
		Object obj = null;
		if (propertyPaneColumnName.equalsIgnoreCase(LegalEntityConstants.NAME)) {
			return obj = getName();
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LegalEntityConstants.ID)) {
			return obj = getId();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(LegalEntityConstants.COUNTRY)) {
			return obj = getCountry();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(LegalEntityConstants.ATTRIBUTES)) {
			return obj = getAttributes();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(LegalEntityConstants.ALIAS)) {
			obj = getAlias();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(LegalEntityConstants.ROLES)) {
			return obj = getRole();
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(LegalEntityConstants.ATTRIBUTES)) {
			return obj =getAttributesData();
		}
		
		return obj;
	}

	@Override
	public void setPropertyValue(String propertyPaneColumnName, Object object) {
		if (propertyPaneColumnName.equalsIgnoreCase(LegalEntityConstants.NAME)) {
			setName((String) object);
		}
		if (propertyPaneColumnName.equalsIgnoreCase(LegalEntityConstants.ID)) {
			//setId((Integer) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(LegalEntityConstants.COUNTRY)) {
			setCountry((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(LegalEntityConstants.ATTRIBUTES)) {
			setAttributes((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(LegalEntityConstants.ALIAS)) {
			setAlias((String) object);
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(LegalEntityConstants.ROLES)) {
			Selection<String> s = (Selection<String>) object;
		 
			setRole(commonUTIL.collectionToString(s.getItems()) );
		}
		if (propertyPaneColumnName
				.equalsIgnoreCase(LegalEntityConstants.ATTRIBUTES)) {
			AttributeContainer attribD = (AttributeContainer) object;
		 
		 setAttributesData(attribD);
		}
	}
}
