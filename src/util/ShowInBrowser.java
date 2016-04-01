package util;


import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;
import java.util.Vector;

import util.document.DefaultDocumentViewer;
import util.document.DocumentViewer;
import util.document.HTMLDocumentUtil;


public class ShowInBrowser {
private static final String FILE_URL_PREFIX = "file://";
	
    static private int _counter = 1;
    static final public String _logCategory = "ShowInBrowser";
    static private DocumentViewer _player = null;
    static {
        String displayerClass = null;//Defaults.getProperty(Defaults.DOCUMENT_DISPLAYER);
        try {
            if (displayerClass != null) {
               // _player = (DocumentViewer) InstantiateUtil.getInstance(displayerClass,    true);
            }
        } catch (Exception ex) {
            if (displayerClass != null) {
              
                commonUTIL.displayError(_logCategory,  "Cannot instantiate specified document displayer: "
                        + displayerClass, ex);
            }
        }
        if (_player == null) {
            _player = new DefaultDocumentViewer();
        }
    }

    /**
     * Creates a document with a given extension, and displays it in
     * the default browser.
     *
     * @param document a document content (String)
     * @param ext      a document extension (String)
     */
    static public void view(String document,String ext) {
    	view(document,ext,null);
    }

    /**
     * Creates a document with a given extension and title, and displays it
     * in the default browser.
     *
     * @param document a document content (String)
     * @param ext      a document extension (String)
     * @param title    a document title (String)
     */
    static public void view(String document,String ext,String title) {
    	view(document,ext,title,null);
    }

    /**
     * Creates a document with a given extension and title, using a given
     * character encoding, and displays it in the default browser.
     *
     * @param document          a document content (String)
     * @param ext               a document extension (String)
     * @param title             a document title (String)
     * @param characterEncoding a character encoding (String)
     */
    static public void view(String document,String ext,String title,
                               String characterEncoding) {
    	view( document, ext, title,characterEncoding,null);
    }
    
    /**
     * Creates a document with a given extension and title, using a given
     * character encoding, and displays it in the default browser.
     *
     * @param document          a document content (String)
     * @param ext               a document extension (String)
     * @param title             a document title (String)
     * @param characterEncoding a character encoding (String)
     * @param sequenceIndex     a sequence number for ordering multiple docs(String)
     */
    static public void view(String document,String ext,String title,
                               String characterEncoding,String sequenceIndex) {
        try {
            String url = buildDocument(document, ext, title, characterEncoding,sequenceIndex);
            if (url != null) displayURL(url);
        } catch(Exception e) {
           // Log.error(_logCategory, e);
            commonUTIL.displayError(_logCategory, "display", e);
        }
    }

    /**
     * Creates a document with a given extension and title, and displays it
     * in the default browser.
     *
     * @param bytes a document content (byte array)
     * @param ext   a document extension (String)
     * @param title a document title (String)
     */
    static public void view(byte[] bytes,String ext,String title) {
        try {
            String url=buildDocument(bytes, ext, title);
            if (url != null) displayURL(url);
        } catch(Exception e) {
        	commonUTIL.displayError(_logCategory, "display", e);
        }
    }

    /**
     * Creates a document (String) with a given extension and title.
     *
     * @param document a document content (String)
     * @param ext      a document extension (String)
     * @param title    a document title (String)
     */
    static public String buildDocument(String document,
                                       String ext,
                                       String title) {
        return buildDocument(document, ext, title, null, true, SAVE_POL);
    }

    /**
     * Creates a document (String) with a given extension and title.
     *
     * @param document     a document content (String)
     * @param ext          a document extension (String)
     * @param title        a document title (String)
     * @param deleteOnExit
     */
    static public String buildDocument(String document,
                                       String ext,
                                       String title,
                                       boolean deleteOnExit) {
        return buildDocument(document, ext, title, null, deleteOnExit, SAVE_POL);
    }

    /**
     * Creates a document (String) with a given extension and title.
     *
     * @param document     a document content (String)
     * @param ext          a document extension (String)
     * @param title        a document title (String)
     * @param deleteOnExit
     * @param SavingPolicy Overwritte or not an existing file
     */
    static public String buildDocument(String document,
                                       String ext,
                                       String title,
                                       boolean deleteOnExit,
                                       int SavingPolicy) {
        return buildDocument(document, ext, title, null, deleteOnExit,SavingPolicy);
    }

    /**
     * Creates a document (String) with a given extension and title, using a
     * given character encoding.
     *
     * @param document a document content (String)
     * @param ext      a document extension (String)
     * @param title    a document title (String)
     * @param encoding a character encoding (String)
     */
     static public String buildDocument(String document,
                                       String ext,
                                       String title,
                                       String encoding) {
        return buildDocument(document, ext, title, encoding,null);
    }
    
    /**
     * Creates a document (String) with a given extension and title, using a
     * given character encoding.
     *
     * @param document a document content (String)
     * @param ext      a document extension (String)
     * @param title    a document title (String)
     * @param encoding a character encoding (String)
     * @param sequenceIndex     a sequence number for ordering multiple docs(String)
     */
    static public String buildDocument(String document,
                                       String ext,
                                       String title,
                                       String encoding,
                                       String sequenceIndex) {
        return buildDocument(document, ext, title, encoding, true, SAVE_POL, sequenceIndex);
    }

    /**
     * Creates a document (String) with a given extension and title, using a
     * given character encoding.
     *
     * @param document          a document content (String)
     * @param ext               a document extension (String)
     * @param title             a document title (String)
     * @param characterEncoding a character encoding (String)
     * @param deleteOnExit
     */
    static public String buildDocument(String document,
                                       String ext,
                                       String title,
                                       String characterEncoding,
                                       boolean deleteOnExit) {

        return buildDocument(document, ext, title, characterEncoding, deleteOnExit, SAVE_POL);
    }

    /**
    * Creates a document (String) with a given extension and title, using a
    * given character encoding.
    *
    * @param document          a document content (String)
    * @param ext               a document extension (String)
    * @param title             a document title (String)
    * @param characterEncoding a character encoding (String)
    * @param deleteOnExit
    * @param SavingPolicy Overwritte or not an existing file
    */
   static public String buildDocument(String document,
                                      String ext,
                                      String title,
                                      String characterEncoding,
                                      boolean deleteOnExit,
                                      int SavingPolicy) {
       return buildDocument(document, ext, title, characterEncoding,deleteOnExit, SavingPolicy,null);
   }
   
   /**
    * Creates a document (String) with a given extension and title, using a
    * given character encoding.
    *
    * @param document          a document content (String)
    * @param ext               a document extension (String)
    * @param title             a document title (String)
    * @param characterEncoding a character encoding (String)
    * @param deleteOnExit
    * @param SavingPolicy Overwritte or not an existing file
    * @param sequenceIndex     a sequence number for ordering multiple docs(String)
    */
   static public String buildDocument(String document,
                                      String ext,
                                      String title,
                                      String characterEncoding,
                                      boolean deleteOnExit,
                                      int SavingPolicy,
                                      String sequenceIndex) {
       if (characterEncoding == null) {
           characterEncoding = HTMLDocumentUtil.getCharacterEncoding();
       }

       byte bytes[] = getBytes(document, characterEncoding);
       return buildDocument(bytes, ext, title, deleteOnExit, SavingPolicy,sequenceIndex);
   }

   /**
    * Creates a document (String) with a given policies and output path.
    *
    * @param document a document content (String)
    * @param deleteOnExit
    * @param SavingPolicy Overwritten or not an existing file
    * @param outputFileName optional absolute path of the file name to create
    */
   static public String buildDocument(String document,
                                      boolean deleteOnExit,
                                      int SavingPolicy,
                                      String outputFilePath)
   {
       String characterEncoding = HTMLDocumentUtil.getCharacterEncoding();
       byte bytes[] = getBytes(document, characterEncoding);
       return buildDocument(bytes, deleteOnExit, SavingPolicy, outputFilePath);
   }
   
    /**
     * Creates a document (String) with a given extension and title.
     *
     * @param bytes a document content (byte array)
     * @param ext   a document extension (String)
     * @param title a document title (String)
     */
    static public String buildDocument(byte[] bytes,
                                       String ext,
                                       String title) {
        return buildDocument(bytes, ext, title, true, SAVE_POL);
    }

    /**
     * Creates a document (String) with a given extension and title.
     *
     * @param bytes a document content (byte array)
     * @param ext   a document extension (String)
     * @param title a document title (String)
     * @param deleteOnExit
     */
    static public String buildDocument(byte[] bytes,
                                       String ext,
                                       String title,
                                       boolean deleteOnExit)
    {
        return buildDocument(bytes, ext, title, deleteOnExit, SAVE_POL);
    }

    /**
     * Creates a document (String) with a given extension and title.
     *
     * @param bytes a document content (byte array)
     * @param ext   a document extension (String)
     * @param title a document title (String)
     * @param deleteOnExit
     * @param SavingPolicy Overwritte or not an existing file
     */
    static public String buildDocument(byte[] bytes,
                                       String ext,
                                       String title,
                                       boolean deleteOnExit,
                                       int SavingPolicy)
    {
        return buildDocument(bytes,ext,title,deleteOnExit,SavingPolicy,null);
    }
    
    
    /**
     * Creates a document (String) with a given extension and title.
     *
     * @param bytes a document content (byte array)
     * @param ext   a document extension (String)
     * @param title a document title (String)
     * @param deleteOnExit
     * @param SavingPolicy Overwritte or not an existing file
     *  @param sequenceIndex     a sequence number for ordering multiple docs(String)
     */
    static public String buildDocument(byte[] bytes,
                                       String ext,
                                       String title,
                                       boolean deleteOnExit,
                                       int SavingPolicy,
                                       String sequenceIndex) {
        String fileName = null;
        if (commonUTIL.isEmpty(title))
            fileName = getFileName(ext, title,sequenceIndex);
        else
            fileName = getFileFullName(ext, title,sequenceIndex);
        return buildDocument(bytes,deleteOnExit,SavingPolicy, fileName);   
    }
    
    /**
     * Creates a document (String) with a given extension and title.
     *
     * @param bytes a document content (byte array)
     * @param deleteOnExit
     * @param SavingPolicy Overwritte or not an existing file
     * @param outputFileName optional absolute path of the file name to create
     */
    static public String buildDocument(byte[] bytes,
                                       boolean deleteOnExit,
                                       int SavingPolicy,
                                       String outputFilePath)
    {
        String fileName = outputFilePath;
        try {
          //  if (SavingPolicy == SAVE_POL)
           //     Log.renameIfExist(fileName);
           // else
            	if (SavingPolicy == OVERW_POL) {
                File temp = new File(fileName);
                if (temp.exists())
                    temp.delete();
            }

            File file = new File(fileName);
            if (deleteOnExit)
                deleteOnExit(file);
            FileOutputStream stream = new FileOutputStream(file);
            stream.write(bytes);
            stream.flush();
            stream.close();
        } catch (Exception e) {
         //   Log.error(_logCategory, e);
            commonUTIL.displayError("ShowInBrowser", "buildDocument", e);
            return null;
        }
        return FILE_URL_PREFIX + fileName;
    }

    static public String getFileName(String ext, String title) {
        return getFileName(ext,title,null);
    }

    static public String getFileName(String ext, String title,String sequenceIndex) {
        //open file
        String defaultDir = commonUTIL.getUserDirName();

        String fileName = null;
        if(title != null && title.indexOf(File.separator) >=0){
            if (!title.endsWith("." + ext)){  //ext not present
                fileName = title 
                + (!commonUTIL.isEmpty(sequenceIndex) ? sequenceIndex : "") 
                + "." + (ext != null ? ext : "html");
            }
            else {
                fileName = applySequenceIndex(title,ext,sequenceIndex);
            }
        }
        else {
            fileName=defaultDir + File.separator
            + (title != null ? title : "tempDocument")
            + (!commonUTIL.isEmpty(sequenceIndex) ? (sequenceIndex +"_"+ _counter ) : ("_" + _counter++) ) 
            + "_" + commonUTIL.getCurrentDateTime() + "." + (ext != null ? ext : "html");
       }
        return fileName;
    }

    static protected boolean isAbsolutePath(String name) {
        File fh = new File(name);
        return fh.isAbsolute();
    }

    static public String getFileFullName(String ext, String title) {
        return getFileFullName(ext,title,null);
    }
    
    static public String getFileFullName(String ext, String title,String sequenceIndex) {
        // bz 18297
        String fileName = null;
        if (title != null && title.indexOf("\\") == -1) {
            String defaultDir = commonUTIL.getUserDirName();
            if (title != null && isAbsolutePath(title)) {
                if (!title.endsWith("." + ext))  //ext already present
                    fileName = title + (!commonUTIL.isEmpty(sequenceIndex) ? sequenceIndex : "")  + "." + (ext != null ? ext : "html");
                else fileName = applySequenceIndex(title,ext,sequenceIndex);
            }
            else {
                fileName = defaultDir + File.separator
                    + (title != null ? title : "tempDocument")
                    + (!commonUTIL.isEmpty(sequenceIndex) ? sequenceIndex : "")
                    + "." + (ext != null ? ext : "html");
            }
        }
        else {
            if (title != null && !title.endsWith("." + ext))  //ext already present
                fileName = title + (!commonUTIL.isEmpty(sequenceIndex) ? sequenceIndex : "")  + "." + ext;
            else
                fileName = applySequenceIndex(title,ext,sequenceIndex);
        }
        return fileName;
    }

    private static String applySequenceIndex(String title,String ext,String sequenceIndex){
        String result = null;
        if(commonUTIL.isEmpty(title)) return result;
        try{
            int totalLength = title.length();
            String ext2 = (ext != null) ? ext : "html";
            String s = title.substring(0, (totalLength - (ext2.length()+ 1) ));
            s = s + (!commonUTIL.isEmpty(sequenceIndex) ? sequenceIndex : "") + "." + ext2;
            result = s;
        }catch(IndexOutOfBoundsException e){
          //  Log.error("DisplayInBrowser",e);
            commonUTIL.displayError("ShowInBrowser", "applySequenceIndex", e);
        }catch(Exception ex){
          //  Log.error("DisplayInBrowser",ex);
            commonUTIL.displayError("ShowInBrowser", "applySequenceIndex", ex);
        }
        return result;
    }

    /**
     * Equivalent to displayURL(url, false)
     * 
     * @see #displayURL(String, boolean)
     * @param url a URL
     */
    public static void displayURL(String url) {
        displayURL(url, false);
    }
    
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
     * @param useQuotes if true, the URL in put in quotes before passing the default application. (Windows only)
     */
    public static void displayURL(String url, boolean useQuotes) {
        _player.viewURL(url, useQuotes);
    }
    
    /**
     * Returns true if the current platform is a Windows platform, or false
     * otherwise.
     */
    public static boolean isWindowsPlatform() {
        String os = System.getProperty("os.name");
        if ( os != null && os.startsWith(WIN_ID))
            return true;
        else
            return false;
    }

    private static byte[] getBytes(String document, String characterEncoding) {
        try {
            return getRawBytes(document, characterEncoding);
        }
        catch (java.nio.BufferOverflowException boe) {}

        return getBytesJDKWorkardound(document, characterEncoding);
    }


    private static final int   BITS_24 = 16777216;

    private static byte[] getBytesJDKWorkardound(String document, String characterEncoding) {
        if (document == null || document.length() == 0) return new byte[0];

        if (document.length() < BITS_24)
            return getRawBytes(document, characterEncoding);

        int bufferCount = (int)(document.length() / BITS_24) + 1;
      //  Log.debug(_logCategory,"bufferCount = "+bufferCount);  
        commonUTIL.display("ShowInBrowser","bufferCount = "+bufferCount);
        byte[][] buffers = new byte[bufferCount][];
        int totalBytes = 0;
        for (int i=0; i < bufferCount; i++) {
            if (document.length() < BITS_24) {
                buffers[i] = getRawBytes(document, characterEncoding);
            }
            else {
                buffers[i] = getRawBytes(document.substring(0, BITS_24), characterEncoding);
                document = document.substring(BITS_24);
            }
            totalBytes += buffers[i].length;
        }

       // Log.debug(_logCategory,"total bytes = "+totalBytes); 
        commonUTIL.display("ShowInBrowser", "total bytes = "+totalBytes);
        
        byte[] fullBytes = new byte[totalBytes];
        int offset=0;
        for (int i=0; i < bufferCount; i++) {
            System.arraycopy(buffers[i], 0, fullBytes, offset, buffers[i].length);
            offset += BITS_24;
        }

        return fullBytes;
    }

    private static byte[] getRawBytes(String document, String characterEncoding) {
        try {
            if (characterEncoding == null)
                return document.getBytes();
            else
                return document.getBytes(characterEncoding);
        }
        catch (java.io.UnsupportedEncodingException e) {
        	  commonUTIL.displayError("ShowInBrowser", "display", e);
        }

        return document.getBytes();
    }
   
    private static final String WIN_ID = "Windows";
    private static final String WIN_PATH = "rundll32";
    private static final String WIN_FLAG = "url.dll,FileProtocolHandler";
    private static final String UNIX_PATH = "firefox";
    private static final String UNIX_FLAG = "-remote openURL";
    public  static final int    SAVE_POL  = 0; /** Save files policy */
    public  static final int    OVERW_POL = 1; /** Overwritte files policy*/
    public static final Object[] EMPTY_ARRAY = new Object[0];
    /**
     * Used in the reflection calls when no argument is needed.
     */
    public static final Class[] EMPTY_CLASS_ARRAY = new Class[0];
    public static void  deleteOnExit(File file) {
        try {
            Class cl=Class.forName("java.io.File");
            Method m = cl.getMethod("deleteOnExit",(Class[]) EMPTY_ARRAY);
            if (m != null) {
                m.invoke(file,EMPTY_ARRAY);
            }
        }
        catch (Exception e) {   commonUTIL.displayError("ShowInBrowser", "deleteOnExit", e);}
    }

    /**
     * Test method for internal use only.
     */
    static public void main(String args[]) {
        String doc = "<HTML> <HEAD> Test </HEAD> <TITLE> foo </TITLE> " +
                     "<BODY> Hello World </BODY> </HTML>";
        view(doc,"html");
        doc = "Wa Lili Y alili Jean Marie et Notepad  Hakim";
        view(doc,"txt");
    }

    /**
     * Creates document/s with a given extension and title, and displays it
     * in the default browser.
     *
     * @param documents  document content in String form (Vector)
     * @param ext      a document extension (String)
     * @param title    a document title (String)
     */
    static public void view(Vector documents,String ext,String title) {
        if(commonUTIL.isEmpty(documents)) return;
        try{
            boolean applySequence = documents.size() > 1;
        for(int i=0;i<documents.size();i++){
            Object content = documents.elementAt(i);
            String sequenceIndex = null;
            if(content instanceof String){
                sequenceIndex = String.valueOf(i);
                view((String)content,ext,title,null,(applySequence ? sequenceIndex : null));
            }else{
            	  commonUTIL.display("ShowInBrowser", "display --> Document Content of type String expected. Found:");
             //   Log.error("DisplayInBrowser","Document Content of type String expected. Found:" + content.getClass().getName());
            }
        }
        }catch(Exception e){
          
            commonUTIL.displayError("ShowInBrowser", "display", e);
        }finally{
            _counter++;
        }
    }
}



