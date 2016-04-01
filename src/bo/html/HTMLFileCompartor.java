package bo.html;


public interface HTMLFileCompartor {

	    /**
	     * Compares two documents and presents the result to the user.
	     * 
	     * @param a a document
	     * @param b another document
	     */
	    public void compare(HTMLDocument a, HTMLDocument b);

	    /**
	     * Tests if two documents are different.
	     * 
	     * @param a a document
	     * @param b another document
	     * @return true if the document are different, false otherwise.
	     */
	    public boolean isDifferent(HTMLDocument a, HTMLDocument b);

}
