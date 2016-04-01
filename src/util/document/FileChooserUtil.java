package util.document;

import java.io.File;
import java.util.Properties;

import util.commonUTIL;

public class FileChooserUtil extends javax.swing.JFileChooser
{

public FileChooserUtil(){
	super();
	String dirname = commonUTIL.getUserDirName();
	String fileName = dirname + File.separator +
	    "lastremembered.txt";
	Properties p = commonUTIL.getPropertiesFromFile(fileName);
	if (p != null && p.size() >0) {
	    String filename = (String) p.get("JFileChooser");
	    if (!commonUTIL.isEmpty(filename))
	    setCurrentDirectory(new File(filename));
	}
	else {
	    String cwd = System.getProperty("user.dir");
	   
	    setCurrentDirectory(new File(cwd));
	}
    }
    public FileChooserUtil(String initDirectory){
	super(initDirectory);
    }

public  File getSelectedFile(){
	Properties p=new Properties();
	File f=super.getSelectedFile();
	String dirname = commonUTIL.getUserDirName();
	String fileName= dirname + File.separator +
				  "lastremembered.txt";
	if (f!=null){
	    p.put("JFileChooser", f.getParent()  );
	    commonUTIL.saveToFile(p, fileName, " Login Details");
	}
	return f;
    }



}
