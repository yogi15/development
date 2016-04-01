package util;



import java.io.BufferedReader;
import java.io.FileReader;
import java.io.StringReader;
import java.util.Hashtable;
import java.util.Vector;

import util.document.contentvarience.CDiff;


public class FileDiffUtil extends Object {

    public static CDiff compareFiles(String baseFileName, String compareFileName) {
    	String baseFile = readFile(baseFileName);
    	String compareFile = readFile(compareFileName);
        return compare(baseFile, compareFile);
    }
    
    protected static String readFile(String fileName) {
    	try {
            BufferedReader rdr = new BufferedReader(new FileReader(fileName));
	    StringBuffer file = new StringBuffer();
            for (;;) {
                String line = rdr.readLine();
                if (line == null) break;
                file.append(line+"\n");
            }
            return file.toString();
        }
        catch (Exception e) {
          
            commonUTIL.displayError("DiffUtil", "readFile", e);
        }
        return "";
    }

    public static CDiff compare(String base, String compare) {
        Vector baseLines = getLines(base);
        Vector compareLines = getLines(compare);

	// Get a hash of lines to optimize comparison.
        Hashtable baseHash = getLineHash(baseLines);
        Hashtable compareHash = getLineHash(compareLines);

	int m = baseLines.size();
	int n = compareLines.size();
	int[][] lcs = new int[m+2][n+2];
	int i, j;

	// Prepare LCS matrix
	for (i = m; i >= 0; i--) {
	    for (j = n; j >= 0; j--) {	
	    	Line i1 = (Line)baseHash.get(Integer.valueOf(i));
	    	Line i2 = (Line)compareHash.get(Integer.valueOf(j));
	    	if (i1 == null || i2 == null)
	    	    lcs[i][j] = 0;
	    	else if (i1._hash == i2._hash &&
	    	    i1._line.equals(i2._line)) {
	    	    lcs[i][j] = 1 + lcs[i+1][j+1];	
		}
		else {
		    int v1 = lcs[i+1][j];
		    int v2 = lcs[i][j+1];
		    if (v1 < v2)
		        lcs[i][j] = v2;
		    else
		        lcs[i][j] = v1;
		}									
	    }
	}	

	Vector matchedPairs = new Vector();                                                      
	i = 0;j = 0;
	// We now walk through the LCS tree and constitute the diffs
	// matchedPairs is a vector that matches the lines in the base file to the same line
	// in the compare file.
        while (i < m && j < n) {
	    Line i1 = (Line)baseHash.get(Integer.valueOf(i));
	    Line i2 = (Line)compareHash.get(Integer.valueOf(j));
            if (i1._hash == i2._hash &&
                i1._line.equals(i2._line)) {
                matchedPairs.addElement(new Pair(i+1, j+1));
                i++;
                j++;
            }
	    else if (lcs[i+1][j] >= lcs[i][j+1]) {
	        i++;
            }
	    else {
		j++;
            }
        }

	// Code up until this point has been meticulously verified and is functionally
	// correct.  
	
        CDiff diff = new CDiff();

	int first;			// Line number of current pair in base file
	int second;		// Line number of current pair in compare file
	int blockFirst;		// This is the size of block of change in the base file
	int blockSecond;	// This is the size of block of change in the compare file
        int preFirst;		// This is the line number in the base file for the previous pair
        int preSecond;	// This is the line number in the compare file for the previous pair
        int bigBlock;		// This is used when working on changes in both files at the
        				// same time and represents the larger of the sets of changes
        				// to the base and compare files.
	
	first=preFirst=second=preSecond = 0;
	for (int iter = 0; iter < matchedPairs.size(); iter++) {
	    Pair pair = (Pair)matchedPairs.elementAt(iter);
	    first = pair._a;		// base file line
	    second = pair._b;	// matches this line in the compare file
	    blockFirst = first - preFirst; 	// number of lines in the base file between this
	    							// match and the previous
	    blockSecond = second - preSecond; 	// number of lines in the compare file
	    									// between this match and the previous
	    if ((blockFirst == 1) && (blockSecond > 1)) {
                // This is the next line in the base file. It is is not the next line in the compare
                // file so there must have been some lines added in the base file. We need to
                // add the block of lines from the compare file.
		for (i=1; i<blockSecond; i++) {
		    diff.addBase(" ");			// no line exists in base - add in blank line
		    diff.addCompare((String)compareLines.elementAt(preSecond+i-1),
		    		CDiff.ADDITION); // Note that the line numbers will be off
		    								// by one from the indexes into the file
		    								// line vectors.
		}
	    }
	    else if ((blockSecond == 1) && (blockFirst > 1)) {
	    	// This is the next line in the compare file. It is not the next line in the base file
	    	// so there must have been some lines removed from the base file. We need
	    	// to mark those lines as deleted.
		for (i=1; i<blockFirst; i++) {
		    diff.addCompare(" ");			// no line exists in compare - add a blank line
		    diff.addBase((String)baseLines.elementAt(preFirst+i-1),
		    		CDiff.DELETION); // Note that the line numbers will be off by
		    			 				// one from the indexes into the file line
		    			 				// vector
		}
	    }
	    else if((blockFirst != 1) || (blockSecond != 1)) {
		//Changed Block
		bigBlock = (blockFirst>blockSecond) ? blockFirst : blockSecond;
		// walk down the block of unmatched lines between previous match_pair and
		// this match_pair. If the size of the change in the base file is different from
		// the size of the chnage in the compare file then at some point the shorter
		// block will be padded with blank lines. Until then each line in the base and
		// compare files will be marked as changed.
		for (i=1; i<bigBlock; i++) {			
		    if (preFirst+i < first) {
		    	// still within the base file block - mark as changed
		        diff.addBase((String)baseLines.elementAt(preFirst+i-1), CDiff.CHANGE);
		    } else {
		    	// we have gone beyond the end of the base file block - add blank lines
			diff.addBase(" ");
  		    }
		    if (preSecond+i < second) {
			// still within the compare file block - mark as changed
			diff.addCompare((String)compareLines.elementAt(preSecond+i-1), CDiff.CHANGE);
		    } else {
			// we have gone beyond the end of the compare file block - add blank lines
			diff.addCompare(" ");
		    }
		}
	    }
	    
	    // And we know that these two lines match so output the line.
	    diff.addBase((String)baseLines.elementAt(first-1), CDiff.SAME);
	    diff.addCompare((String)compareLines.elementAt(second-1), CDiff.SAME);
            
	    preFirst = first;			// And we start again from this match in the base file
	    preSecond = second;		// likewise for the compare file.
	}

	// Now there needs to be some clean up. Any remaining lines need to be handled.
	if(preFirst == m) {
		// we have finished with the base file - add any additional lines in the compare
		// as additions.
		for(i = 0; i < (n - preSecond); i++) { 
			diff.addCompare((String)compareLines.elementAt(preSecond+i),
							CDiff.ADDITION); // Note that unlike before, the indexing
											// is already working on offset not line
											// number (the index is from 0 not 1 in
											// this loop.
		}
	} else if(preSecond == n) {
		// We have finished with the compare file - remove any remaining lines in the
		// base file.
		for(i = 0; i < (m - preFirst); i++) { 
			diff.addBase((String)baseLines.elementAt(preFirst+i),
							CDiff.DELETION); // Note that unlike before, the indexing
											// is already working on offset not line
											// number (the index is from 0 not 1 in
											// this loop.
		}
	} else if((preFirst != m) && (preSecond != n)) {
		// There are changes at the end for both files.
		blockFirst = m - preFirst;
		blockSecond = n - preSecond;
		bigBlock = (blockFirst>blockSecond) ? blockFirst : blockSecond;
		// If the size of the change in the base file is different from
		// the size of the change in the compare file then at some point the shorter
		// block will be padded with blank lines. Until then each line in the base and
		// compare files will be marked as changed.
		for (i=0; i<bigBlock; i++) {			
			if (preFirst+i < m) {
				// still within the base file block - mark as changed
				diff.addBase((String)baseLines.elementAt(preFirst+i), CDiff.CHANGE);
			} else {
				// we have gone beyond the end of the base file block - add blank lines
				diff.addBase(" ");
			}
			if (preSecond+i < n) {
				// still within the compare file block - mark as changed
				diff.addCompare((String)compareLines.elementAt(preSecond+i), CDiff.CHANGE);
			} else {
				// we have gone beyond the end of the compare file block - add blank lines
				diff.addCompare(" ");
			}
		}
	} else {
		// We ended at the end of the base and compare file - so we are complete.
	}
	return diff;
    }

    /**
     * Utility method to split a String representing the whole contents of
     * an HTML document into a Vector of <code>String</code> objects.
     */
    private static Vector getLines(String s) {
    	try {
    	    BufferedReader rdr = new BufferedReader(new StringReader(s));
            Vector lines = new Vector();
            for (;;) {
                String line = rdr.readLine();
                if (line == null) break;
                line = line.trim();
                if (line.equals("")) line=" ";
                lines.addElement(line);
            }
            return lines;
        }
        catch (Exception e) {
         
            commonUTIL.displayError("DiffUtil", "getLines", e);
        }
        return new Vector();
    }

	/**
	 * Utility method to generate hashtable of <code>Line</code> objects from
	 * Vector representation of an HTML file.
	 * 
	 * @param lines A vector of String where each element represents the line of a
	 * file. The element at index 0 is the first line in the file and so on.
	 * @return Hashtable that maps the line number to a <code>Line</code> instance
	 * that represents that line.
	 */
    private static Hashtable getLineHash(Vector lines) {
    	try {
    	    Hashtable h = new Hashtable();
    	    for (int i=0; i < lines.size(); i++) {
    	    	String line = (String)lines.elementAt(i);
    	    	h.put(Integer.valueOf(i), new Line(line));
    	    }
            return h;
        }
        catch (Exception e) {
        
            commonUTIL.displayError("DiffUtil", "getLineHash", e);
        }
        return new Hashtable();
    }

    /**
     * This main tests the diff algorithm with the example identical to that
     * found here: http://www.ics.uci.edu/~eppstein/161/960229.html
     * If problems with algorithm, you should go through document and read
     * through since logic and algorithm used there is transcribed here to Java
     * as it is in public sphere.
     */
    public static void main(String[] args) {

        String filename1 = args[args.length-2];
        String filename2 = args[args.length-1];

        //VDiff diff = DiffUtil.compare("n\ne\nm\na\nt\no\nd\ne\n \nk\nn\no\nw\nl\ne\nd\ng\ne\n", "e\nm\np\nt\ny\n \nb\no\nt\nt\nl\ne\n");
        CDiff diff = FileDiffUtil.compareFiles(filename1, filename2);
       commonUTIL.display("DiffUtil","------------------------------------------------------------------------------");
       commonUTIL.display("DiffUtil",diff.getBaseAsHtml());
       commonUTIL.display("DiffUtil","------------------------------------------------------------------------------");
       commonUTIL.display("DiffUtil","\n\n\n\n");
       commonUTIL.display("DiffUtil","------------------------------------------------------------------------------");
       commonUTIL.display("DiffUtil",diff.getCompareAsHtml());	
       commonUTIL.display("DiffUtil","------------------------------------------------------------------------------");
    }
}

class Line  {
    public int     _hash;
    public String  _line;
    
    public Line(String line) {
        _line = line;
        _hash = line.hashCode();
    }
}

class Pair {
    public int _a;
    public int _b;
    
    public Pair(int a, int b) {
    	_a = a;
    	_b = b;
    }
    
    public String toString() { return _a+","+_b; }
}


