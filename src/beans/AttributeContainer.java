package beans;
  
import java.util.Vector;

import util.commonUTIL;

public class AttributeContainer implements BaseBean {
	
	String attributeName = "";
	
	/**
	 * @return the attributeName
	 */
	public String getAttributeName() {
		return attributeName;
	}

	/**
	 * @param attributeName the attributeName to set
	 */
	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	Vector<Attribute> attributes = new Vector<Attribute>();

	/**
	 * @return the attributes
	 */
	public Vector<Attribute> getAttributes() {
		return attributes;
	}
	
	public void setAttributeValue(String name,String value) {
		boolean  valueGot =false;
		int valueAt =0;
		String val = "";
		if(!commonUTIL.isEmpty(attributes)) {
			for(int i=0;i<attributes.size();i++) {
				if(attributes.get(i).getName().equalsIgnoreCase(name)) {
					attributes.get(i).setValue(value);
					//val = value;
					valueGot = true;
					valueAt = i;
					break;
				}
			}
		}
		/*if(valueGot) {
			Attribute	att = attributes.get(valueAt);
			att.setValue(value);
			attributes.add(valueAt, att);
		}*/
		 
		 
	
	}
	public String getAttributeValue(String name ) {
		String attributeValue = "";
		if(commonUTIL.isEmpty(attributes)) {
			for(int i=0;i<attributes.size();i++) {
				if(attributes.get(i).getName().equalsIgnoreCase(name)) {
					attributeValue = attributes.get(i).getValue();
					break;
				}
			}
		}
		return attributeValue;
	}
	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Vector<Attribute> attributes) {
		this.attributes = attributes;
	}
     
	
	@Override
	public Object getPropertyValue(String propertyPaneColumnName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setPropertyValue(String propertyPaneColumnName, Object object) {
		// TODO Auto-generated method stub
		
	}
	
	
	

}
