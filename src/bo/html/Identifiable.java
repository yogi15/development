package bo.html;

public interface Identifiable {
	/**
     * Returns the <code>MimeType</code> format which defines
     * this <code>Document</code>.
     * <p>
     * For instance, an HTML file document will have a mime type
     * set to "text/html" and the data will be available through
     * the getter/setter methods: <code>GetDocument()</code> and
     * <code>setDocument()</code>.  Binary mime types are those types
     * which do not start with the "text/" prefix.  The data for
     * these documents will be available via the <code>getBinaryDocument()</code>
     * and <code>setBinaryDocument()</code> methods.
     *
     * @see #setMimeType
     * @see com.calypso.tk.refdata.MimeType
     * @return  the <code>MimeType</code> associated with this document.
     */
    public MimeType getMimeType();
    /**
     * Sets the <code>MimeType</code> format which defines
     * this <code>Document</code>.
     * <p>
     * For instance, an HTML file document will have a mime type
     * set to "text/html" and the data will be available through
     * the getter/setter methods: <code>GetDocument()</code> and
     * <code>setDocument()</code>.  Binary mime types are those types
     * which do not start with the "text/" prefix.  The data for
     * these documents will be available via the <code>getBinaryDocument()</code>
     * and <code>setBinaryDocument()</code> methods.
     *
     * @see #setMimeType
     * @see com.calypso.tk.refdata.MimeType
     * @param  t  the <code>MimeType</code> associated with this document.
     */
    public void setMimeType(MimeType t);
    /**
     *
     */    
    public StringBuffer getDocument();
    /**
     *
     */
    public void setDocument(StringBuffer s);    
    /**
     * @return the binary document associated with this document or null when
     * not applicable.
     */
    public byte[] getBinaryDocument();
    /**
     *
     */
    public void setBinaryDocument(byte[] document);
    /**
     * @return the id of the <code>BOMessage</code> associated with this document,
     *  or 0 if this document is not linked to any message.
     */
    public int getAdviceId();
    /**
     * @param id  the id of the <code>BOMessage</code> associated with this document.
     */
    public void setAdviceId(int id);

}



