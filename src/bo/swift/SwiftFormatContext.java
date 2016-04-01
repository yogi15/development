package bo.swift;



import com.jidesoft.converter.AbstractContext;

public class SwiftFormatContext extends AbstractContext {
	  public static SwiftFormatContext DEFAULT_CONTEXT = new SwiftFormatContext("");

	    /**
	     * Creates a converter context with a name.
	     *
	     * @param name the name of the converter context
	     */
	    public SwiftFormatContext(String name) {
	        super(name);
	    }

	    /**
	     * Creates a converter context with a name and an object.
	     *
	     * @param name the name of the converter context
	     * @param object the user object. It can be used as any object to pass information along.
	     */
	    public SwiftFormatContext(String name, Object object) {
	        super(name, object);
	    }
}
