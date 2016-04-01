package util.document.contentvarience;

import java.util.Vector;

import util.commonUTIL;


public class CDiff  extends Object {
    public static final String ADDITION_COLOR  = "#40FF2F";
    public static final String DELETION_COLOR  = "#FF2F40";
    public static final String CHANGE_COLOR    = "#FFFF2F";
	
    public static final String START_TAG  = "<table border=0><tr><td bgcolor=\"@@COLOR@@\" valign=\"top\"><b><u>";
    public static final String END_TAG       = "</u></b></td></tr></table>";
    public final static char    ADDITION = 'A';
    public final static char    DELETION = 'D';
    public final static char    CHANGE = 'C';
    public final static char    SAME = 'S';

    private Vector   _base;
    private Vector   _compare;
    public CDiff() {
    	_base = new Vector();
    	_compare = new Vector();
    }

    public boolean isDifferent() {
        return (_base.size() != _compare.size() ||
                _base.size() > 1);
    }
    
    public String getBaseAsHtml() {
    	return getAsHtml(_base);
    }

    public String getCompareAsHtml() {
    	return getAsHtml(_compare);
    }


        
    public void addBase(String s) {
    	addBase(s, ADDITION);
    }
    public void addBase(String s, int type) {
    	//System.out.println("<<< "+s);
    	_base.addElement(new LineInfo(s, type));
    }
    public void addCompare(String s) {
    	addBase(s, DELETION);
    }
    public void addCompare(String s, int type) {
    	//System.out.println(">>> "+s);
    	_compare.addElement(new LineInfo(s, type));
    }

    private static String getAsHtml(Vector v) {
    	LineInfo info;
    	StringBuffer html = new StringBuffer();
    	for (int i=0; i < v.size(); i++) {
    	    info = (LineInfo)v.elementAt(i);
    	    html.append(getStartTag(info._type));
    	    html.append(commonUTIL.htmlEncode(info._line));
    	    html.append(getEndTag(info._type));
    	    html.append("\n");
    	}
    	
    	return html.toString();
    }
    
    private static String getStartTag(int type) {
    	if (type == SAME)
    	    return "";
    	
    	String s = START_TAG;
    	String color=null;
    	if (type == ADDITION)
    	    color = ADDITION_COLOR;
    	else if (type == DELETION)
    	    color = DELETION_COLOR;
    	else 
    	    color = CHANGE_COLOR;

        int index = s.indexOf("@@COLOR@@");
        if (index == -1) return s;
        
        return s.substring(0, index) + color + s.substring(index+9);
    }

    private static String getEndTag(int type) {
    	if (type == SAME)
    	    return "";
    	return END_TAG;
    }
        
    class LineInfo {
        public String _line;
        public int  _type;
    
        public LineInfo(String s, int type) {
    	    _line = s;
    	    _type = type;
        }
    }
}
