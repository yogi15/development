package dbSQL;

import java.sql.Connection;
import java.util.Vector;

import beans.Attribute;
 

public interface AttributeProvider {
	
	void saveAttributes(Vector  attributes,Connection con,int id);
	void updateAttributes(Vector  attributes,int id,Connection con);

	Vector<Attribute> selectAttributes(int id,Connection con);

}
