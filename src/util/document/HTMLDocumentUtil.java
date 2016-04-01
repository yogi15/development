package util.document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JFrame;






import util.ClassInstantiateUtil;
import util.FileDiffUtil;
import util.FileSearch;
import util.ShowInBrowser;
import util.commonUTIL;
import util.document.contentvarience.CDiff;

import beans.IncomingFile;
import beans.Message;
import bo.html.HTMLDocument;
import bo.html.HTMLFileCompartor;
import bo.html.HtmlDocumentImporter;
import bo.html.MimeType;


public class HTMLDocumentUtil {
	
protected final static String LOG_CATEGORY = "HTMLDocumentUtil";
    
    public static final String DEFAULT_CHAR_ENCODING = "us-ascii";
    public static final String DEFAULT_FONT = "Arial";
    
    
    
    static public void view(HTMLDocument document) {
    	view(document, null);
    }



	private static void view(HTMLDocument document,  String title) {
		// TODO Auto-generated method stub
		MimeType mime = document.getMimeType();

        if (mime.isBinaryFormat()) {
            byte[] bytes = document.getBinaryDocument();
            ShowInBrowser.view(bytes, mime.getExtension(), title);
        }
        else {
            StringBuffer sb= document.getDocument();
            if (sb == null) return;
            ShowInBrowser.view(sb.toString(), mime.getExtension(), title);
        }
		
	}
	  /**
     * Returns a boolean determining whether or not two documents are
     * "different", according to the diff program.
     * <p>
     * If the content of both documents is identical, then returns false.
     * In all other cases, including if the diff cannot be performed
     * (in the case of binary documents, for instance) then returns true.
     */
    public static boolean isDifferent(HTMLDocument doc1, HTMLDocument doc2) {
        
        /**
         * Let's give customers a chance to do their own comparison.
         */
    	HTMLFileCompartor comp = null;
    	Class class1 = null;
        try {
        	class1 =  ClassInstantiateUtil.getClass("apps.util.CustomDocumentComparator",true);
        	comp = (HTMLFileCompartor)  class1.newInstance();
            return comp.isDifferent(doc1, doc2);
        } catch (Exception e) {
        	commonUTIL.displayError(LOG_CATEGORY, "diff",e);
        }
        
        MimeType mime = doc1.getMimeType();
        
        /**
         * We should only compare documents of identical mime types
         */
        if (mime.getType().equals(doc2.getMimeType().getType()) == false) {
            return true;
        }
        
        /**
         * We do not perform diff on binary documents
         */
        if (mime.isBinaryFormat()) {
            return true;
        }
        
        CDiff diff = FileDiffUtil.compare(doc1.getDocument().toString(),
                                      doc2.getDocument().toString());

        return diff.isDifferent();
    }


    public static void diff(HTMLDocument doc1, HTMLDocument doc2, boolean sourceDiff) {
        
        /**
         * Let's give customers a chance to do their own comparison.
         * 
         */
    	HTMLFileCompartor comp = null;
    	Class class1 = null;
        try {
        	
        	class1 =   ClassInstantiateUtil.getClass("apps.util.CustomDocumentComparator",true); 
        	comp = (HTMLFileCompartor)  class1.newInstance();
            comp.compare(doc1, doc2);
            return;
        } catch (Exception e) {
        	commonUTIL.displayError(LOG_CATEGORY, "diff",e);
        }

        MimeType mime = doc1.getMimeType();

        /**
         * We should only compare documents of identical mime types
         */
        if (mime.getType().equals(doc2.getMimeType().getType()) == false) {
            commonUTIL.display("Diff: Cannot compare documents of different formats.", null);
            return;
        }
        
        /**
         * We do not perform diff on binary documents
         */
        if (mime.isBinaryFormat()) {
        	 commonUTIL.display("Diff: Cannot compare binary documents.", null);
            return;
        }
        
        String s1 = doc1.getDocument().toString();
        String s2 = doc2.getDocument().toString();

        if (mime.getType().equals("text/html")) {
            diffHtml(s1, s2, sourceDiff);
        }
        else {
            diffText(s1, s2);
        }
    }
	
	 public static String importDocument(HTMLDocument document) {
		 FileChooserUtil  chooser = new FileChooserUtil();
	        int returnVal = chooser.showOpenDialog(null);
	        if (returnVal != JFileChooser.APPROVE_OPTION)
	            return null;

	        return read(document, chooser.getSelectedFile());
	    }
	 public static Hashtable importDocument( Vector vCurrentDir) {
	        FileChooserUtil  chooser = new FileChooserUtil();
	        if (vCurrentDir != null && vCurrentDir.size() > 0)
	            chooser.setCurrentDirectory( (File)vCurrentDir.get(0));
	        chooser.setMultiSelectionEnabled(true);

	        int returnVal = chooser.showOpenDialog(null);
	        if (returnVal != JFileChooser.APPROVE_OPTION)
	            return null;

	        File currentDir = chooser.getCurrentDirectory();
	        if (currentDir != null) {
	            vCurrentDir.clear();
	            vCurrentDir.add( currentDir);
	        }

	        File[] files = chooser.getSelectedFiles();
	        Hashtable ht = new Hashtable();

	        for (int i=0; i<files.length; i++) {
	            File file = files[i];
	            IncomingFile document = new IncomingFile();
	            String filename = read(document, file);
	            if (commonUTIL.isEmpty(filename)) {
	                // Read failed
	                return null;
	            }
	            ht.put( filename, document);
	        }

	        return ht;
	    }
	
	
	 public static String read(HTMLDocument document, File file) {
	        MimeType mime = MimeTypes.getFileType(file.getName());
	        if (mime == null) {
	            commonUTIL.display("No MIME type configured for this kind of files.", null);
	            return null;
	        }

	        document.setMimeType(mime);
	        if (mime.isBinaryFormat()) {
	            byte[] actualDoc = null;
	            try {
	                actualDoc = new byte[0];
	                FileInputStream stream = new FileInputStream(file);
	                while (stream.available() > 0) {
	                    int available = stream.available();
	                    byte[] oldActualDoc = actualDoc;
	                    actualDoc = new byte[available +
	                                         oldActualDoc.length + 1];
	                    System.arraycopy(oldActualDoc,0,actualDoc,0,
	                                     oldActualDoc.length);
	                    stream.read(actualDoc,oldActualDoc.length,
	                                available);
	                }
	            } catch (IOException e) {
	             commonUTIL.showAlertMessage(e.getMessage());
	                actualDoc = null;
	            }
	            document.setBinaryDocument(actualDoc);
	        }
	        else {
	            try {
	                BufferedReader br=new BufferedReader(new FileReader(file));
	                StringBuffer sb= new StringBuffer();
	                String line=null;
	                while((line=br.readLine()) != null)
	                    sb.append(line + "\n");
	                br.close();
	                document.setDocument(sb);
	            } catch (Exception e) {
	            	 commonUTIL.showAlertMessage(e.getMessage());
	            	 return null;
	            }
	        }

	        return file.getName();
	    }
	
	public static File editHtml(HTMLDocument document,String dirPath) {
        MimeType mime = document.getMimeType();
        if (!mime.getType().equals("text/html") && !mime.getType().equals("text/xml")) {
           commonUTIL.displayError(LOG_CATEGORY, "editHtml",new Exception("Cannot edit HTML document.  Document is of type: "+mime.toString()));
            
            return null;
        }
        
        if (!ShowInBrowser.isWindowsPlatform()) {
            view(document);
            return null;
        }
        String editor= null;
        String cmd="";

        try {
	    // We look for customized Document importer
	    HtmlDocumentImporter importer = getDocumentImporter();
	    String documentData = null;
	    if (importer != null)
		// Customized importer expands document data
		documentData = importer.editHTML(document);
	    else
		// No cutomization, document data stays as it is
		documentData = document.getDocument().toString();

	    // Creates temporary file
            String mimeType=mime.getType();

            File file = exportDocument(documentData,mimeType,dirPath);

	    // Calls document editor
            cmd = editor + " \"" + file.getAbsolutePath() + "\"";
            Runtime.getRuntime().exec(cmd);
            return file;
        }
        catch(Exception x) {
        	 commonUTIL.displayError(LOG_CATEGORY, "",x);
            return null;
        }
	}
	 static public File exportDocument(String data,String mimeType,String  defaultdir) {
		 String defaultDir=defaultdir + File.separator + "documents";
	        File dir = new File(defaultDir);
	        if (!dir.exists()) dir.mkdir();

	        String fileExtention="html";
	        String results[];
	        if (mimeType!=null){
	            fileExtention=mimeType.replaceFirst("text/","");
	        }

	        String fileName=defaultDir + File.separator
	                + "_tempDocument_" + (_counter++) + "." + fileExtention;

	        File file = null;
	        try {
	            file = new File(fileName);
	            file.deleteOnExit();
	            FileOutputStream stream = new FileOutputStream(file);
	            stream.write(data.getBytes());
	            stream.flush();
	            stream.close();
	        } catch (Exception e) {
	           // Log.error(LOG_CATEGORY, e);
	            commonUTIL.displayError(LOG_CATEGORY, "exportDocument",e);
	            return null;
	        }
	        return file;
		 
		 
	 }
	 

	    static HtmlDocumentImporter getDocumentImporter() {
		try {
			HtmlDocumentImporter documentImporter = null;//            (HtmlDocumentImporter)InstantiateUtil.
			//getInstance("tk.bo.document.CustomDocumentImporter",true);
	            if (documentImporter != null)
		        return documentImporter;
	          return  documentImporter;
		}
		catch(Exception e) {
			}
		return null;
		}

	
	/* public static void displayDocument(Message message){
	       Document document=null;
	        try {
		    int id = message.getId();
		    if (message.isChild())
			id = message.getGroupId();
	            document=DSConnection.getDefault().getRemoteBO().
	                getLatestAdviceDocument(id,null);
	        }
	        catch(Exception e) {Log.error(LOG_CATEGORY, e);}
	        if(document != null) {
	            AppUtil.displayMessage("Displaying Document from DB",new JFrame());
	            display(document);
	            return;
	        }
	        else {
	            try {
	                document = FormatterUtil.generate(env,message, DSConnection.getDefault());
	            }
	            catch (Exception ex) {
	                Log.error(LOG_CATEGORY,ex);
	                AppUtil.displayError(ex,new JFrame());
	            }
	        }
	        try {
	            FormatterUtil.display(env,message,  DSConnection.getDefault(), document);
	        }
	        catch (Exception ex) {
	            Log.error(LOG_CATEGORY,ex);
	            AppUtil.displayError(ex,new JFrame());
	        }
	    }*/
	
	 private static void diffHtml(String s1, String s2, boolean sourceDiff) {
	        // TO DO?  Strip out HTML tags?!?!
	        // @DFO@ - use htmlToText method if yes.
	        CDiff diff = FileDiffUtil.compare(s1, s2);

	        String url1 = ShowInBrowser.buildDocument(diff.getBaseAsHtml(), "html", "old");
	        String url2 = ShowInBrowser.buildDocument(diff.getCompareAsHtml(), "html", "new");

	        String data= "<html>\n<frameset cols=\"50%,50%\" frameborder=\"YES\" border=\"2\">\n" +
	                     "<frame scrolling=\"YES\" src=\"" + url1 + "\">\n" +
	                     "<frame scrolling=\"YES\" src=\"" + url2 + "\">\n" +
	                     "</frameset>\n</html>";
	        ShowInBrowser.view(data, "html");
	    }

	 private static void diffText(String s1, String s2) {
	        CDiff diff = FileDiffUtil.compare(s1, s2);

	        String url1 = ShowInBrowser.buildDocument("<html>\n<body>\n<pre>"+diff.getBaseAsHtml()+"</pre>\n</body>\n</html>\n",
	                                                     "html",
	                                                     "old");
	        String url2 = ShowInBrowser.buildDocument("<html>\n<body>\n<pre>"+diff.getCompareAsHtml()+"</pre>\n</body>\n</html>\n",
	                                                     "html",
	                                                     "new");

	        String data= "<html>\n<frameset cols=\"50%,50%\" frameborder=\"YES\" border=\"2\">\n" +
	                     "<frame scrolling=\"YES\" src=\"" + url1 + "\">\n" +
	                     "<frame scrolling=\"YES\" src=\"" + url2 + "\">\n" +
	                     "</frameset>\n</html>";
	        ShowInBrowser.view(data, "html");
	    }
	static private String htmlToText(String html) {
        StringBuffer textBuffer = new StringBuffer();

        try {
            HtmlStatus status = new HtmlStatus();
            String line;
            StringBuffer lineBuffer = new StringBuffer();
            BufferedReader rdr = new BufferedReader(new StringReader(html));
            for (;;) {
                line = rdr.readLine();
                if (line == null) break;

                /**
                 * We first check if there's any html on this line.  If there
                 * isn't, we can immediately copy the entire line or none of
                 * it, depending on whether or not we are "within" an HTML
                 * tag.
                 */
                if (line.indexOf("<") == -1 && line.indexOf(">") == -1) {
                    if (status.isValid())
                        textBuffer.append(line);
                    textBuffer.append("\n");
                    continue;
                }

                lineBuffer.setLength(0); // clear line buffer
                htmlToText(line, lineBuffer, status);
                textBuffer.append(lineBuffer.toString().trim());
                textBuffer.append("\n");
            }
        }
        catch (Exception e) {
            commonUTIL.displayError(LOG_CATEGORY, "htmlToText",e);
        }

        return textBuffer.toString();
    }
	static private void htmlToText(String html, StringBuffer buffer, HtmlStatus status) {
        int startIndex = html.indexOf("<");
        int endIndex = html.indexOf(">");

        /**
         * No new starting or ending tags on remainder of the line.
         * We copy the text over if we're not within HTML tag(s) and
         * return the same tag depth we're currently at.
         */
        if (startIndex == -1 && endIndex == -1) {
            if (status.isValid())
                buffer.append(html);
                return;
        }

        if (startIndex == -1) startIndex = 1000000;
        if (endIndex == -1) endIndex = 1000000;

        /**
         * Since the method is recursive, we only need to deal with the first
         * encountered tag.  The other one will be dealt with on a subsequent
         * pass through the method.
         */
        if (startIndex < endIndex) {
            if (status.isValid()) {
                buffer.append(html.substring(0, startIndex));
                buffer.append(" ");
            }
            if (html.indexOf("<!--") == startIndex)
                status.in_comment = true;
            else
                status.in_html = true;
            htmlToText(html.substring(startIndex+1), buffer, status);
        }
        else {
            if (html.indexOf("-->") == endIndex-2)
                status.in_comment = false;
            else
                status.in_html = false;
            htmlToText(html.substring(endIndex+1), buffer, status);
        }
    }
    /**
     * Returns the character encoding (String) used to display Excel document
     * data.
     * <p>
     * If no character encoding is defined in the default
     * preferences, the default encoding "us-ascii" is returned.
     */
    public static String getCharacterEncoding() {
        String s = null;//Defaults.getProperty(Defaults.DOCUMENT_CHAR_ENCODING);
        if (s == null) s = DEFAULT_CHAR_ENCODING;

        return s;
    }

    /**
     * Returns the font (String) used to display Excel document
     * data.
     * <p>
     * If no font is defined in the default
     * preferences, the default font "Arial" is returned.
     */
    public static String getFont() {
        String s =null;// Defaults.getProperty(Defaults.DOCUMENT_FONT);
        if (s == null) s = DEFAULT_FONT;

        return s;
    }

	 /**
     * Prints the given HTML file directly to the printer via Microsoft
     * Office Print command.
     *
     * @param filename   The name of an existing HTML file to be printed
     */
    public static boolean printFile(String filename) {
        try {
            File file = new File(filename);
            File helperApp = FileSearch.search("C:\\Program Files\\Microsoft Office\\", "msohtmed.exe");
            if (helperApp != null) {
            	String command = helperApp.getCanonicalPath() + " /p ";
            	try {
            		Process child = Runtime.getRuntime().exec(command + file.getCanonicalPath());
            		return true;
            	}
            	catch (Throwable t) {}
            }
            helperApp = FileSearch.search("msohtmed.exe");
            if (helperApp != null) {
            	String command = helperApp.getCanonicalPath() + " /p ";
            	try {
            		Process child = Runtime.getRuntime().exec(command + file.getCanonicalPath());
            		return true;
            	}
            	catch (Throwable t) {}
            }
            return true;
        } catch (Exception e) {
            commonUTIL.displayError(LOG_CATEGORY, "printFile", e);
            return false;
        }
    }

    private static int   _counter=0;
}
	

	class HtmlStatus extends Object {
	    public boolean in_comment = false;
	    public boolean in_html = false;

	    public boolean isValid() { return !in_comment && !in_html; }
	}




