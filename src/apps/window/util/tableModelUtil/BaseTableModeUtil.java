package apps.window.util.tableModelUtil;
 
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Vector;

import beans.WindowSheet;

public class BaseTableModeUtil {
	
	Vector< Object> data ;
	/**
	 * @return the data
	 */
	public Vector<  Object> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Vector<  Object> data) {
		this.data = data;
	}

	/**
	 * @return the col
	 */
	public String[] getCol() {
		return col;
	}

	/**
	 * @param col the col to set
	 */
	public void setCol(String[] col) {
		this.col = col;
	}
	public static void chill(final Vector<?> aListWithSomeType) {
        // Here I'd like to get the Class-Object 'SpiderMan'
        System.out.println(aListWithSomeType.getClass().getGenericSuperclass());
        Type type [] = aListWithSomeType.getClass().getTypeParameters();
        for(int i=0;i<type.length;i++) {
        	Type tt = type[i];
        	 if (tt instanceof ParameterizedType) {
		            ParameterizedType pt = (ParameterizedType) tt;
		            System.out.println("raw type: " + pt.getRawType());
		            System.out.println("owner type: " + pt.getOwnerType());
		            System.out.println("actual type args:");
		            for (Type t : pt.getActualTypeArguments()) {
		                System.out.println("    " + t);
		            }
		        }
        }
            
            
    }

	String [] col;
	public BaseTableModeUtil( ) {
	 

		Field field;
		try {
			field = BaseTableModeUtil.class.getDeclaredField("data");
			 Type type = field.getGenericType();
		        System.out.println("type: " + type);
		        if (type instanceof ParameterizedType) {
		            ParameterizedType pt = (ParameterizedType) type;
		            System.out.println("raw type: " + pt.getRawType());
		            System.out.println("owner type: " + pt.getOwnerType());
		            System.out.println("actual type args:");
		            for (Type t : pt.getActualTypeArguments()) {
		                System.out.println("    " + t);
		            }
		        }

		} catch (NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 

	public static void main(String args[]) {
		Vector<WindowSheet> vec = new Vector<WindowSheet>();
		String c1 [] = {"test1","test2"}; 
		BaseTableModeUtil btable = new  BaseTableModeUtil( );
		btable.chill(vec);
	}
	

}
