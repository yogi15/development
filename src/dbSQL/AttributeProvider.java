package src.dbSQL;

import java.sql.Connection;
import java.util.Vector;

import beans.Attribute;
 

public interface AttributeProvider {
	
	void saveAttributes(Vector  attributes,Connection con);
	void updateAttributes(Vector  attributes,Connection con);

	Vector<Attribute> selectAttributes(int id,Connection con);

}
