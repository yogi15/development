package util.document;




import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import util.commonUTIL;

import bo.html.MimeType;



public class MimeTypes {
	  public final static String     TYPE_TEXT = "text";
	    public final static String     TYPE_AUDIO = "audio";
	    public final static String     TYPE_IMAGE = "image";
	    public final static String     TYPE_VIDEO = "video";
	    public final static String     TYPE_APPLICATION = "application";
	    
	    static protected Map _mimeTypes = null;
	 
	    synchronized static public void setMimeTypes(Hashtable h) {
	        _mimeTypes = h == null ? new HashMap() : new HashMap(h);
	    }

	    synchronized static public Hashtable getMimeTypes() {
		return _mimeTypes == null ? new Hashtable() : new Hashtable(_mimeTypes);
	    } 
	    synchronized static public bo.html.MimeType getMimeType(String type) {
	    	if(_mimeTypes != null && type != null) {
	                Iterator i = _mimeTypes.values().iterator();
	                while (i.hasNext()) {             
	                    MimeType m = (MimeType)i.next();            
	                    if (m.getType().equals(type))
	                        return m;
	                }
	    	    //return (MimeType)_mimeTypes.get(type);
	    	} else {
	    	   commonUTIL.display("MimeTypes", "Unknown MIME Type: "+type);
	    	}
	            return null;
	        } 
	    synchronized static public MimeType getExtensionType(String extension) {
	    	 if(_mimeTypes != null && extension != null) {
	    		    return (MimeType)_mimeTypes.get(extension);
	    		} else {            
	    			 commonUTIL.display("MimeTypes", "Unknown MIME Extension: " + extension);
	    		    return null;
	    		}
	    }
	    static public boolean isBinaryType(String type)
	    {
	        MimeType mimeType = null;
		mimeType = getMimeType(type);
		if(mimeType != null)
		    return mimeType.isBinaryFormat();

	        return true;	
	    }

	    static public String getFileExtension(String type)
	    {
	        MimeType mimeType = null;
		mimeType = getMimeType(type);
		if(mimeType != null)
		    return mimeType.getExtension();
		    
	        return "bin";	// bin is file extension for octet-stream data
	    }

	    static public Vector getTextMimeTypes()
	    {
	    	return getMimeTypeFamily(TYPE_TEXT);
	    }

	    static public Vector getImageMimeTypes()
	    {
	    	return getMimeTypeFamily(TYPE_IMAGE);
	    }

	    static public Vector getAudioMimeTypes()
	    {
	    	return getMimeTypeFamily(TYPE_AUDIO);
	    }

	    static public Vector getVideoMimeTypes()
	    {
	    	return getMimeTypeFamily(TYPE_VIDEO);
	    }

	    static public Vector getApplicationMimeTypes()
	    {
	    	return getMimeTypeFamily(TYPE_APPLICATION);
	    }

	    static public Vector getMimeTypeFamily(String family)
	    {
	    	Vector types = new Vector();
	    	Iterator i = _mimeTypes.keySet().iterator();
	    	while (i.hasNext()) {
	    	    String type = (String)i.next();
	    	    if (type.toLowerCase().startsWith(family))
	    	        types.addElement(_mimeTypes.get(type));
	    	}
	    	
	    	return types;
	    }

	    static public Vector getMimeTypeStrings()
	    {
	    	Vector types = new Vector();
	    	Iterator iter = _mimeTypes.values().iterator();
	    	while (iter.hasNext()) {
	    	    MimeType type = (MimeType)iter.next();
	    	    types.addElement(type.toString());
	    	}
	    	
	    	return util.SortShell.sort(types);
	    }

	    public static MimeType getFileType(String fileName)
	    {
	    	int index = fileName.lastIndexOf('.');
	    	if (index != -1) {
	    	    String extension = fileName.substring(index+1);
	    	    return getExtensionType(extension);
	    	}
	    	return null;
	    }

}
