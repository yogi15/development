package util;




import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;


public class FileSearch {
	private static Map<String,Boolean>  searchMap = new HashMap<String,Boolean>();
	private static Map<String,File>     fileMap = new HashMap<String,File>();
	  private FileSearch() {}
	  
	  
	  public static File search(String filename) {
	        // Start at the user home
	        File directory = new File(commonUTIL.getUserHome());
	        // Now iterate up to the root directory.
	        while (directory.getParentFile() != null) {
	        	directory = directory.getParentFile();
	        }

	        return search(directory, filename);
	    }

	    public static File search(String dirname, String filename) {
	    	File dir = new File(dirname);
	    	return search(dir, filename);
	    }

	    public static File search(File directory, String filename) {
	        if (commonUTIL.isEmpty(filename)) {
	            throw new IllegalArgumentException("filename cannot be null");
	        }
	        if (searchMap.get(filename) != null) {
	        	// If we've already performed this search in the past, we
	        	// returned the cached file reference, if one exists.
	        	return fileMap.get(filename);
	        }
	    	File file = searchInFile(directory, filename);
	        if (file != null) {
	        	fileMap.put(filename, file);
	        }
	        // In any case, tag this search as done.
	        searchMap.put(filename, Boolean.TRUE);
	        
	        return file;
	    }

	    private static File searchInFile(File directory, String filename) {
	        File[] files = directory.listFiles();
	        if (files == null) {
	            return null;
	        }
	        List<File> dirs = new ArrayList<File>();
	        for (File file : files) {
	        	if (file.isDirectory()) {
	        		dirs.add(file);
	        	}
	        	else {
	        		if (file.getName().equalsIgnoreCase(filename)) {
	        			return file;
	        		}
	        	}
	        }
	        for (File file : dirs) {
	        	File match = searchInFile(file, filename);
	        	if (match != null) return match;
	        }

	        return null;
	    }
	    public static Vector<String> searchInFile(  String filename) {
	    	Vector<String> fileNames = new Vector<String>( );
	        File[] files = new File(filename).listFiles();
	        if (files == null) {
	            return null;
	        }
	        

	        for(File f: files){
               
	        	fileNames.add(f.getName().substring(0, f.getName().lastIndexOf(".")));

	        } 

	        return fileNames;
	    }
}
