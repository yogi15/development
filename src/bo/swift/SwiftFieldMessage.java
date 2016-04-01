package bo.swift;

import java.io.Serializable;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SwiftFieldMessage  implements Serializable,Cloneable
 {
	protected byte   _status;
    protected String _tag;
    protected String _name;
    protected String _value;
    protected String _format;
    transient protected String _sequence;
    transient protected int    _legalEntityId = 0;
    transient protected String _bicCode = null;
    public SwiftFieldMessage() {}
    public SwiftFieldMessage(boolean mandatory) {
    	this();
    	if (mandatory)
    	    _status = (byte)'M';
    	else
    	    _status = (byte)'O';
    }

    public SwiftFieldMessage(byte status, String tag, String name, String value){
        this(status, tag, name, value, null);
    }

    public SwiftFieldMessage(byte status, String tag, String name, String value, String format){
	_status = status;
	_tag = tag;
	_name = name;
    _value = value;
    _format = format;
    }final public byte   getStatus() { return _status;}
    final public String getTAG()    { return _tag;}
    final public String getName()   { return _name;}
    final public String getValue()  { return _value;}
    final public String getSequence()  { return _sequence;}
    final public String getFormat()  { return _format;}
    //These fields are not persistent.
    //Just here for information when formatting the message
    final public int getLegalEntityId() {return _legalEntityId;}
    final public String getBicCode() {return _bicCode;}

    final public void setStatus(byte b) {  _status = b;}
    final public void setTAG(String s)    {  _tag = s;}
    final public void setName(String s)   {  _name = s;}
    final public void setValue(String s)  {  _value = s;}
    final public void setSequence(String s)  {  _sequence = s;}
    final public void setFormat(String s)  {  _format = s;}
    final public void setLegalEntityId(int id) {_legalEntityId = id;}
    final public void setBicCode(String bicCode) {_bicCode = bicCode;}

    final public String checkFormat()  {
      return checkFormat(null);
    }
   final public String checkFormat(Vector semantics) {
        if (getFormat() == null)
            return null;
        Vector labels = new Vector();
        Pattern p = COSSWIFTPatternFactory.compile(getFormat(), labels);
        Matcher m = p.matcher(getValue());
        if (!m.matches()) {
            return "Field does not match format " + getFormat();
        }
        if (semantics != null) {
            for (int i = 0; i < labels.size(); i++) {
                semantics.add(new String[] { (String)labels.get(i), m.group(i + 1) });
            }
        }
        return null;
    } 

	
}
