package util.document;

public interface DocumentViewer {

    /**
     * Displays the file specified by the URL. Equivalent to displayURL(url, false).
     * 
     * @param url
     * 
     * @see #displayURL(String, boolean)
     */
    public void viewURL(String url);

    /**
     * Displays the file specified by the URL.
     * 
     * @param url
     *            a URL (String)
     * @param useQuotes
     *            if true, the URL is put in quotes before passing the default
     *            application. (Windows only)
     */
    public void viewURL(String url, boolean useQuotes);

}



