package util.document;

import java.io.IOException;


import util.ShowInBrowser;
import util.commonUTIL;

public class DefaultDocumentViewer implements  DocumentViewer {

	
	protected static final String URL_REPLACEMENT_TEXT = "%u";
    protected static final String WIN_PATH = "rundll32";
    protected static final String WIN_FLAG = "url.dll,FileProtocolHandler";
    protected static final String UNIX_PATH = "firefox";
    protected static final String UNIX_FLAG = "-remote openURL";
	
    /**
     * Displays a given URL in the default browser.
     *
     * The default browser is used on Windows and the Netscape browser is used
     * on Unix. This browser association can be changed by using BROWSER_PATH
     * property in the User Environment which specifies the browser to use. If
     * no path information is provided by the property then the browser needs
     * to be on the user's path.
     *
     * @param url a URL (String)
     * @param useQuotes if true, the URL is put in quotes before passing the default application. (Windows only)
     */
	@Override
	public void viewURL(String url) {
		// TODO Auto-generated method stub
		viewURL(url, false);
		
	}

	@Override
	public void viewURL(String url, boolean useQuotes) {
		// TODO Auto-generated method stub
		  String browserCommand = null;//Defaults.getProperty(Defaults.BROWSER_PATH);
	        if (browserCommand == null) {
	            // Use the default
	            boolean windows = isWindowsPlatform();
	            String cmd = null;
	            try {
	                if (windows) {
	                    // cmd = 'rundll32 url.dll,FileProtocolHandler http://...'
	                	String viewerUrl = mapToViewerUrl(url);
	                	StringBuffer buffer = new StringBuffer();
	                    buffer.append(WIN_PATH);
	                    buffer.append(' ');
	                    buffer.append(WIN_FLAG);
	                    buffer.append(' ');
	                    if (useQuotes) {
	                        buffer.append('"');
	                    }
	                    buffer.append(viewerUrl);
	                    if (useQuotes) {
	                        buffer.append('"');
	                    }
	                    cmd = buffer.toString();
	                    commonUTIL.display("DefaultDocumentView", cmd);
	                 //   if (Log.isCategoryLogged(_logCategory) && Log.isDebug()) {
	                   // 	Log.debug(_logCategory, "Invoking command: " + cmd);
	                    //}
	                    Runtime.getRuntime().exec(cmd);
	                } else {
	                    // Under Unix, Netscape has to be running for the "-remote"
	                    // command to work.  So, we try sending the command and
	                    // check for an exit value.  If the exit command is 0,
	                    // it worked, otherwise we need to start the browser.
	                    // cmd = 'firefox -remoteopenURL(http://www.javaworld.com)'
	                    cmd = UNIX_PATH + " " + UNIX_FLAG + "(" + url + ")";
	                    Process p = Runtime.getRuntime().exec(cmd);
	                    try {
	                        // wait for exit code -- if it's 0, command worked,
	                        // otherwise we need to start the browser up.
	                        int exitCode = p.waitFor();
	                        if (exitCode != 0) {
	                            // Command failed, start up the browser
	                            // cmd = 'firefox http://www.javaworld.com'
	                            cmd = UNIX_PATH + " " + url;
	                            p = Runtime.getRuntime().exec(cmd);
	                        }
	                    }
	                    catch (InterruptedException x) {
	                    	//Log.error(_logCategory, "Error bringing up browser, cmd=" + cmd, x);
	                    	commonUTIL.displayError("DefaultDocumentViewer", "ViewDisplay", x);
	                    }
	                }
	            } catch (IOException x) {
	                // couldn't exec browser
	            	commonUTIL.displayError("DefaultDocumentViewer",  "Could not invoke browser, command=", x);
	            
	            }
	        } else {
	            // Use the specified browser.
	            String command;
	            if(browserCommand.indexOf(URL_REPLACEMENT_TEXT) != -1) {
	                command = browserCommand.replace(URL_REPLACEMENT_TEXT, url);
	            } else {
	                command = browserCommand + " " + url;
	            }
	            try {

                    commonUTIL.display("DefaultDocumentView", command);
	                Runtime.getRuntime().exec(command);
	            }
	            catch (IOException x) {
	             
	            	commonUTIL.displayError("DefaultDocumentViewer",  "Could not invoke browser, command=", x);
	            }
	        }
		
		
	}
	
	protected String mapToViewerUrl(String url) {
		return url;
	}

	protected boolean isWindowsPlatform() {		
		return ShowInBrowser.isWindowsPlatform();
	}


}
