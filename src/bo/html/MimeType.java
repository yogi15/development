package bo.html;

import java.io.Serializable;

/**
 * The <code>MimeType</code> class describes a specific 
 * Multipurpose Internet Mail Extensions (MIME) media type.
 * <p>
 * There are five discrete top-level media types:
 * <ol>
 * <li>Text
 * <li>Image
 * <li>Audio
 * <li>Video
 * <li>Application
 * </ol>
 * <p>
 * <b><u>Text: Textual Information</u></b><br>
 * The subtype "plain" in particular indicates plain text containing 
 * no formatting commands or directives of any sort. Plain text is 
 * intended to be displayed "as-is". No special software is required to 
 * get the full meaning of the text, aside from support for the indicated 
 * character set. Other subtypes are to be used for enriched text in 
 * forms where application software may enhance the appearance of the text, 
 * but such software must not be required in order to get the general idea 
 * of the content. Possible subtypes of "text" thus include any word processor 
 * format that can be read without resorting to software that understands the 
 * format. In particular, formats that employ embeddded binary formatting 
 * information are not considered directly readable. A very simple and portable 
 * subtype, "richtext", was defined in RFC 1341, with a further revision in RFC 
 * 1896 under the name "enriched". 
 * <p>
 * <b><u>Image: Image Data</u></b></br>
 * "Image" requires a display device (such as a graphical display, a graphics 
 * printer, or a FAX machine) to view the information. Subtypes are defined for 
 * two widely-used image formats, jpeg and gif.
 * <p>
 * <b><u>Audio: Audio Data</u></b></br>
 * "Audio" requires an audio output device (such as a speaker or a telephone) 
 * to "display" the contents. An initial subtype "basic" is defined in this 
 * document.
 * <p>
 * <b><u>Video: Video Data</u></b></br>
 * "Video" requires the capability to display moving images, typically 
 * including specialized hardware and software. An initial subtype "mpeg" is 
 * defined in this document. 
 * <p>
 * <b><u>Application: Other Data</u></b></br>
 * Typically either uninterpreted binary data or information to be processed by 
 * an application. The subtype "octet-stream" is to be used in the case of 
 * uninterpreted binary data, in which case the simplest recommended action is 
 * to offer to write the information into a file for the user. 
 * <p>
 * You can find more information about MIME and the related RFCs <a href=
 * "http://www.nacs.uci.edu/indiv/ehood/MIME/MIME.html">here</a>.
 *
 * @author  $Author: thomas_leplus $
 * @version $Revision: 1.8 $ $Date: 2008/06/12 19:39:51 $
 * @since  5.2
 */
public class MimeType implements Serializable 
{
    static final long serialVersionUID = 4735120846488316020L;
    /**
     * A Text representation of the MIME Type (i.e., text/plain)
     */   
    protected String       _type;
    /**
     * The file extension typically associated with this MIME Type (i.e, txt)
     */
    protected String       _extension;
    /**
     * True if this MIME Type the default one for the extension.
     */
    protected boolean       _default;
    /**
     * A brief description of the MIME Type, often including instructions
     * on how it can be viewed.
     */
    protected String       _comment;
    
    /**
     * Default Constructor
     */
    public MimeType() 
    {
    	this(null);
    }
    
    public MimeType(String type)
    {
    	_type = type;
    }
    
    public final String getType(){  return _type;}
    public final void setType(String s){  _type=s;}

    public final String getExtension(){  return _extension;}
    public final void setExtension(String s){  _extension=s;}

    public final boolean isDefault(){  return _default;}
    public final void setDefault(boolean d){  _default=d;}

    public final String getComment(){  return _comment;}
    public final void setComment(String s){  _comment=s;}

    /**
     * Returns true if this MIME Media Type is a Binary Format,
     * or false if not.  As per the RFCs, the only MIME Type
     * which does not indicate binary data is the "text" type,
     * which includes the following well known types, to name
     * a few:
     * <ul>text/plain
     * <li>text/html
     * <li>text/richtext
     * <li>text/xml
     * <li>text/rtf
     * </ul>
     *
     * @see <a href="ftp://ftp.isi.edu/in-notes/iana/assignments/media-types/media-types">Mime Types</a>
     * @return  true if MIME Type is binary, else otherwise.
     */
    public boolean isBinaryFormat()
    {
    	if (_type != null) {
    	    if (_type.toLowerCase().startsWith("text/"))
    	        return false;
    	    
    	    return true;
    	}
    	return true;
    }

    /**
     * The MimeType's type uniquely identifies it.
     */
    public int hashCode() 
    {
	return _type.hashCode();
    }
    
    public boolean equals(Object o) {
	if (!(o instanceof MimeType)) return false;
	MimeType mime=(MimeType)o;
	return _type.equals(mime._type);
    }
    
    public String toString()
    {
    	return getType();
    }
    
    final public  String getDescription() 
    {
    	return "MimeType/" + getType() + "/" + isDefault() + "/" + getComment(); 
    }

}
