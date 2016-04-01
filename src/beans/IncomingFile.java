package beans;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;




import util.commonUTIL;

import bo.html.HTMLDocument;
import bo.html.MimeType;

public class IncomingFile implements HTMLDocument,Serializable {
	String _datetime;
	 public IncomingFile() 
	    {
		_datetime = commonUTIL.getCurrentDateTime();
	    }
	    

	 public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public int get_adviceId() {
		return _adviceId;
	}

	public void set_adviceId(int _adviceId) {
		this._adviceId = _adviceId;
	}

	public int get_tradeId() {
		return _tradeId;
	}

	public void set_tradeId(int _tradeId) {
		this._tradeId = _tradeId;
	}

	public MimeType get_mimeType() {
		return _mimeType;
	}

	public void set_mimeType(MimeType _mimeType) {
		this._mimeType = _mimeType;
	}

	public byte[] get_bytes() {
		return _bytes;
	}

	public void set_bytes(byte[] _bytes) {
		this._bytes = _bytes;
	}

	public String get_characterEncoding() {
		return _characterEncoding;
	}

	public void set_characterEncoding(String _characterEncoding) {
		this._characterEncoding = _characterEncoding;
	}

	public String get_comment() {
		return _comment;
	}

	public void set_comment(String _comment) {
		this._comment = _comment;
	}

	protected int          _id;
	    protected int          _adviceId;
	    protected int          _tradeId;
	//    protected JDatetime    _datetime;
	    protected MimeType     _mimeType;
	    protected byte[]       _bytes;
	    protected String       _characterEncoding = commonUTIL.ENCODING;
	    protected String       _comment;
	    
	    public int hashCode() 
	    {
		return _id;
	    }
	    
	    public boolean equals(Object o) 
	    {
	    	IncomingFile ad=(IncomingFile)o;
		return _id == ad._id;
	    }
	@Override
	public MimeType getMimeType() {
		// TODO Auto-generated method stub
	 return _mimeType;
	}

	@Override
	public void setMimeType(MimeType t) {
		// TODO Auto-generated method stub
		_mimeType=t;
		
	}
	 public final int getTradeId(){  return _tradeId;}
	    public final void setTradeId(int s){  _tradeId =s;}
	@Override
	public StringBuffer getDocument() {
		// TODO Auto-generated method stub
		if (_mimeType.isBinaryFormat()) {
    		return null;
    	}
    	
    	try {
    		byte doc[] = null;// SerialUtil.gunzip(getBytes());
    		return new StringBuffer(new String(doc, commonUTIL.ENCODING));
    	} catch (UnsupportedEncodingException e) {
    		throw new RuntimeException(e);
    	}
	}
	private byte[] getBytes() {
    	return _bytes;
    }
    
    private void setBytes(byte[] value) {
    	_bytes = value;
    }
    final public  String getDescription() 
    { 
        String idxName = "";
        return "IncomingDocument/" + getTradeId() + "/" + getAdviceId() + " / " +getMimeType()+
	    " /" + commonUTIL.getCurrentDateTime();
    }

	@Override
	public void setDocument(StringBuffer s) {
		 try {
				setBytes(s.toString().getBytes(commonUTIL.ENCODING));
			} catch (UnsupportedEncodingException e) {
			    throw new RuntimeException(e);
			}
		
	}

	@Override
	public byte[] getBinaryDocument() {
		// TODO Auto-generated method stub
		return _mimeType.isBinaryFormat() ? getBytes() : null;
	}

	@Override
	public void setBinaryDocument(byte[] document) {
		// TODO Auto-generated method stub
		   setBytes(document);
	}

	@Override
	public int getAdviceId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setAdviceId(int id) {
		// TODO Auto-generated method stub
		
	}
	 public int getEntityId() { return get_id(); }
	    public String getEntityType() { return "IncomingDocument"; }
}
