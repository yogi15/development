package bo.swift;


/**
 * Object encapsulating a Tag Option/Value.  
 * <p>
 * In SWIFT, many fields not only carry a particular value,
 * but they also have an associated tag option (53a, 57a, etc...)
 * where 'a' is typically A or D, depending on whether or not
 * the legal entity referenced has a SWIFT BIC code or not.
 * <p>
 * Specifically, for the following SWIFT field:
 * <pre>
 *    :57D:CRLYFRPPXXXX
 * </pre>
 * The Tag is '57', the Option is 'D', and the value is 'CRLYFRPPXXXX'
 */
public class ValueTag extends Object {
    public final static String OPTION_CHAR = "a";
    
    private String  _tag = null;
    private String  _option = null;
    private String  _value = null;
    private int     _legalEntityId = 0;
    private String  _bicCode = null;
    private boolean _overrideOptionB = false;

    public ValueTag() {
        this(null, null, null,0);
    }
    
    public ValueTag(String tag) {
        this(tag, null, null,0);
    }

    public ValueTag(String tag, String option, String value, int legalEntityId) {
        setTag(tag);
        setOption(option);
        setValue(value);
        setLegalEntityId(legalEntityId);
    }

    public ValueTag(String tag, String option, String value,int legalEntityId, String bicCode) {
        this(tag, option, value, legalEntityId);
        _bicCode = bicCode;
    }

    public ValueTag(String tag, String option, String value,int legalEntityId, boolean overrideOptionB) {
        _tag = tag;
        _option = option;
        _value = value;
        _legalEntityId = legalEntityId;
        _overrideOptionB = overrideOptionB;
    }

    public ValueTag(String tag, String option, String value,int legalEntityId, String bicCode, boolean overrideOptionB) {
        this(tag, option, value, legalEntityId, overrideOptionB);
        _bicCode = bicCode;
    }

    public void setTag(String tag)       { _tag = tag; }
    public void setOption(String option) { _option = option; }
    public void setValue(String value)   { _value = value; }
    public void setLegalEntityId(int id) { _legalEntityId = id;}
    public void setBicCode(String bicCode) { _bicCode = bicCode;}
    public void setOverrideOptionB(boolean overrideOptionB) { _overrideOptionB = overrideOptionB; }

    public String getTag()    { return _tag; }
    public String getOption() { return _option; }
    public String getValue()  { return _value; }
    public int    getLegalEntityId() {return _legalEntityId;}
    public String getBicCode() {return _bicCode;}
    public boolean getOverrideOptionB() { return _overrideOptionB; }

    public String getTagOption() {
        int index = _tag.indexOf(OPTION_CHAR);
        if (index == -1 && !_overrideOptionB)
            return _tag;
        else {
            if (index > -1)
                return _tag.substring(0, index) + 
                    (_option != null ? _option : "") +
                    _tag.substring(index+1);
            else if (_overrideOptionB) {
                // "a" was not set on the XML tag field
                // but we still want to keep the tag
                // option computed with the tag value 
                StringBuffer buff = new StringBuffer(":");
                for (int i = 1; i < _tag.length(); i++) {
                    char charact = _tag.charAt(i);
                    if (Character.isDigit(charact))
                        buff.append(charact);
                    else
                        break;
                }
                buff.append(_option).append(':');
                return buff.toString();
            }
            return _tag;
        }
    }
}
