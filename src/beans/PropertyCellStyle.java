package src.beans;

import java.awt.Color;
import java.awt.Font;

import util.commonUTIL;

import com.jidesoft.grid.CellStyle;

public class PropertyCellStyle implements BaseBean{

	int columnserialno;

	public void setColumnserialno(int columnserialno) {
		this.columnserialno = columnserialno;
	}

	public int getColumnserialno() {
		return columnserialno;
	}

	String windowname;

	public void setWindowname(String windowname) {
		this.windowname = windowname;
	}

	public String getWindowname() {
		return windowname;
	}

	String fontType;

	public void setFontType(String font) {
		this.fontType = font;
	}

	public String getFontType() {
		return fontType;
	}

	String background;

	public void setBackground(String background) {
		this.background = background;
	}

	public String getBackground() {
		return background;
	}

	String foreground;

	public void setForeground(String foreground) {
		this.foreground = foreground;
	}

	public String getForeground() {
		return foreground;
	}

	boolean fontisbold;

	public void setFontisbold(boolean fontisbold) {
		this.fontisbold = fontisbold;
	}

	public boolean getFontisbold() {
		return fontisbold;
	}

	boolean fontisitalic;

	public void setFontisitalic(boolean fontisitalic) {
		this.fontisitalic = fontisitalic;
	}

	public boolean getFontisitalic() {
		return fontisitalic;
	}

	String propertyname;

	public void setPropertyname(String propertyname) {
		this.propertyname = propertyname;
	}

	public String getPropertyname() {
		return propertyname;
	}
	public CellStyle getCellStyle() {
		CellStyle prop = new CellStyle();
	  
	  String strArray[] =  getBackground().split(",");
		
		
		
		 if(!commonUTIL.isEmpty(getBackground()))  {
			 int b [] = processLine( getBackground().split(","));
		 prop.setBackground(new Color(b[0],b[1],b[2]));
		 }
		   
		 if(!commonUTIL.isEmpty(getForeground()))  {
			 int f [] =  processLine( getForeground().split(","));
		 prop.setForeground(new Color(f[0],f[1],f[2]));
		 }
		 if(!commonUTIL.isEmpty(getFontType())) {
			 String ss [] = getFontType() .split(",");
			 
				 java.awt.Font f = new java.awt.Font(ss[0],Integer.parseInt(ss[1]),Integer.parseInt(ss[2]));
		 	if(getFontisbold()) {
		 		f.isBold();	 
		 		}
		 if(getFontisitalic()) {
			 f.isItalic();
		 }
		 prop.setFont( f);
		 }
		 return prop;
	}
	
	public Color getColor(String colorParmeter) {
		 int  b [] = processLine( colorParmeter.split(","));
		return new Color(b[0],b[1],b[2]);
	}
	public Font getFont(String font ) {
		 String   ss [] = font.split(",");
		 java.awt.Font f = new java.awt.Font(ss[0],Integer.parseInt(ss[1]),Integer.parseInt(ss[2]));
		 	if(getFontisbold()) {
		 		f.isBold();	 
		 		}
		 if(getFontisitalic()) {
			 f.isItalic();
		 }
		 return f;
	}
	
	private int [] processLine(String[] strings) {
		int myar[]=new int[strings.length];

	    int i=0;
	    for(String str:strings){
	    	myar[i]=Integer.parseInt(str);//Exception in this line
	    	i++;
	        
	    }
	    return myar;
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
