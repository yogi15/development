package bo.swift;

import java.util.Hashtable;
import java.util.Vector;
import java.util.regex.Pattern;

import util.commonUTIL;


public class COSSWIFTPatternFactory {

    
    private static final Hashtable _patternCache = new Hashtable();
    private static final Hashtable _labelsCache = new Hashtable();

    /**
     * Compiles the given SWIFT format into a pattern.  </p>
     *
     * @param  format
     *         The format expression to be compiled
     * @param  labels
     *         A vector to store the semantic labels
     *         found during the compilation
     *
     * @throws SWIFTFormatSyntaxException
     *         If the expression's syntax is invalid
     */
    public static Pattern compile(String format, Vector labels) {
        Pattern p = (Pattern)_patternCache.get(format);
        if (p != null) {
            Vector v = (Vector)_labelsCache.get(format);
            if (labels != null && v != null) {
                labels.addAll(v);
            }
            return p;
        }
        Vector v = new Vector();
        StringBuffer jformat = new StringBuffer();
        char[] buffer = format.toCharArray();
        parseFormat(jformat, buffer, v);
        p = Pattern.compile(jformat.toString());
       // if (Log.isCategoryLogged(SwiftUtil.LOG_CATEGORY))
	//    Log.debug(SwiftUtil.LOG_CATEGORY, "SWIFTPatternFactory." + format + " => " + p);
        _patternCache.put(format, p);
        _labelsCache.put(format, v);
        if (labels != null) {
            labels.addAll(v);
        }
        return p;
    }
    
    private static void parseFormat(StringBuffer result, char[] format, Vector labels) {
        result.append('^');
        parseFormat(result, format, 0, format.length - 1, 0, labels);
        result.append('$');
    }
    
    /* The formats that we can compile are a subset of the formats found in the SWIFT standards.
     * The 'grammar' currently supported is:
     * 
     * format : sequence;
     * sequence : block* ;
     * block : '[' sequence ']' | '[' '=' label '=' sequence ']' | expression | plaintext ;
     * expression : number charset | number '!' charset | number '-' number charset | number '*' number charset ;
     * number : digit* ;
     * label : A*
     * plaintext : B*
     * A : uppercase | digit | '_' ;
     * B : uppercase | '\' digit | '\' '[' | '\' ']' | '\' '{' | '\' '}' | punctuation | CRLF ;
     * charset : 'n' | 'd' | 'a' | 'x' | 'y' | 'z' | 'c' | 'h' | 's' | 'e' ;
     * uppercase : 'A' ... 'Z' ;
     * digit : '0' ... '9' ;
     * punctuation : '/' | ':' | '.' | '(' | ')' ;
     * CRLF : '\n' ;
     * 
     * The parser is designed to read a format sequence block by block, from left to right.
     * If the block being read is an optional sequence (between square brackets),
     * we recursively call the parser to parse the inner sequence.
     * Else we parse simply the block and then move on to next one.
     * 
     * Note 1: We use \n for CRLF.
     * Note 2: We escape digits when they are not part of an expression but just plain text.
     */
    private static final int NONE = 0;
    private static final int EXCL = 1;
    private static final int DASH = 2;
    private static final int STAR = 3;
    private static final String END_OF_LINE = "(?:\\n|\\r\\n|\\n\\r)";
    
    private static int parseFormat(StringBuffer result, char[] format, int currentIndex, int endIndex, int depth, Vector labels) {

        while (currentIndex <= endIndex) {

            // If we are at the beginning of a conditional or semantic block, we parse the inside of it first.
            if (format[currentIndex] == '[') {
                result.append('(');
                if (currentIndex == endIndex) {
                   commonUTIL.display("COSSWIFTPattern", "parseFormat Unexpected end of format" +String.valueOf(format) +   currentIndex);
                }
                boolean mode = format[currentIndex + 1] == '=';
                if (mode) {
                    // leave the block as matching (default)
                    // parse the label
                    currentIndex = parseLabel(labels, format, currentIndex + 2, endIndex);
                    if (format[currentIndex] != '=') {
                    	commonUTIL.display("COSSWIFTPattern", "Missing equal sign after label" +String.valueOf(format) +   currentIndex);
                      
                    }
                } else {
                    // make the block non matching in the regex
                    result.append('?');
                    result.append(':');
                }
                currentIndex = parseFormat(result, format, currentIndex + 1, endIndex, depth + 1, labels);
                if (format[currentIndex] != ']') {
                	commonUTIL.display("COSSWIFTPattern", "Missing closing square bracket" +String.valueOf(format) +   currentIndex);
                   
                }
                result.append(')');
                if (!mode) {
                    result.append('?');
                }
            }

            // If we are at the end of a conditional or semantic block, it's time to return to the previous recursion level.
            else if (format[currentIndex] == ']') {
                if (depth > 0) {
                    return currentIndex;
                } else {
                	commonUTIL.display("COSSWIFTPattern", ("Extra closing " + (format[currentIndex] == ']' ? "square" : "amgle") + " bracket" +String.valueOf(format) +   currentIndex));
                 
                }
            }

            // If the block starts with a numerical value x, it is an expression:
            // 1) xC : up to x characters in charset C;
            // 2) x!C : exactly x characters in charset C;
            // 3) x*yC : up to x lines of up to y characters in charset C.
            // 4) x-yC : exactly x to y characters in charset C.
            else if (Character.isDigit(format[currentIndex])) {
                int x = Character.digit(format[currentIndex], 10);
                int y = 1;
                int op = NONE;
                while (++currentIndex <= endIndex) {
                    if (!Character.isDigit(format[currentIndex])) {
                        break;
                    }
                    x *= 10;
                    x += Character.digit(format[currentIndex], 10);
                }
                if (currentIndex > endIndex) {
                	commonUTIL.display("COSSWIFTPattern", "Orphan number" + String.valueOf(format) + endIndex);
                    
                }
                if (format[currentIndex] == '!') {
                    op = EXCL;
                    if (++currentIndex > endIndex) {
                    	commonUTIL.display("COSSWIFTPattern", "Orphan exclamation point" +String.valueOf(format) +   endIndex);
                       
                    }
                } else if (format[currentIndex] == '-' || format[currentIndex] == '*') {
                    if (format[currentIndex] == '-') {
                        op = DASH;
                    } else {
                        op = STAR;
                    }
                    if (++currentIndex > endIndex) {
                    	commonUTIL.display("COSSWIFTPattern","Orphan " + (op == DASH ? "dash" : "star") +String.valueOf(format) +   endIndex);
                       
                    }
                    if (Character.isDigit(format[currentIndex])) {
                        y = Character.digit(format[currentIndex], 10);
                        while (++currentIndex <= endIndex) {
                            if (!Character.isDigit(format[currentIndex])) {
                                break;
                            }
                            y *= 10;
                            y += Character.digit(format[currentIndex], 10);
                        }
                        if (currentIndex > endIndex) {
                        	commonUTIL.display("COSSWIFTPattern","Orphan number"+String.valueOf(format) +   endIndex);
                           
                        }
                    } else {
                    	commonUTIL.display("COSSWIFTPattern","Missing number of lines after star"+String.valueOf(format) +   currentIndex);
                    
                    }
                }

                String charset = getCharset(format[currentIndex]);
                if (charset == null) {
                	commonUTIL.display("COSSWIFTPattern","Unknown charset symbol"+String.valueOf(format) +   currentIndex);
                    
                }

                switch (op) {
                    case NONE:
                        result.append(charset);
                        result.append('{');
                        result.append('1');
                        result.append(',');
                        result.append(x);
                        result.append('}');
                        break;
                    case EXCL:
                        result.append(charset);
                        result.append('{');
                        result.append(x);
                        result.append('}');
                        break;
                    case DASH:
                        result.append(charset);
                        result.append('{');
                        result.append(x);
                        result.append(',');
                        result.append(y);
                        result.append('}');
                        break;
                    case STAR:
                        result.append('(');
                        result.append('?');
                        result.append(':');
                        result.append(charset);
                        result.append('{');
                        result.append('1');
                        result.append(',');
                        result.append(y);
                        result.append('}');
                        result.append(END_OF_LINE);
                        result.append(')');
                        result.append('{');
                        result.append('0');
                        result.append(',');
                        result.append(x - 1);
                        result.append('}');
                        result.append(charset);
                        result.append('{');
                        result.append('1');
                        result.append(',');
                        result.append(y);
                        result.append('}');
                        break;
                }
            }

            // If escaped character exists, it can be a digit, a square bracket or a CRLF. 
            else if (format[currentIndex] == '\\') {
                if (++currentIndex > endIndex) {
                	commonUTIL.display("COSSWIFTPattern", "Missing character after escape backslash" +String.valueOf(format) +   endIndex);
                   
                }
                if (format[currentIndex] == 'n') {
                    result.append(END_OF_LINE);
                } else if (format[currentIndex] == '[' || format[currentIndex] == ']') {
                    result.append('\\');
                    result.append(format[currentIndex]);
                } else if (Character.isDigit(format[currentIndex])) {
                    result.append(format[currentIndex]);
                } else {
                	commonUTIL.display("COSSWIFTPattern", "Wrong character after escape backslash" +String.valueOf(format) +   endIndex);
                   
                }
            }

            // If we have a punctuation symbol, we escape it to prevent special meaning in java. 
            else if (format[currentIndex] == '/'
                  || format[currentIndex] == ':'
                  || format[currentIndex] == '.'
                  || format[currentIndex] == '('
                  || format[currentIndex] == ')') {
                result.append('\\');
                result.append(format[currentIndex]);
            }

            // If we have an uppercase letter, we simply copy it into the result. 
            else if (Character.isUpperCase(format[currentIndex])) {
                result.append(format[currentIndex]);
            }

            // Else something is wrong... 
            else {
            	commonUTIL.display("COSSWIFTPattern", "Unexpected character" +String.valueOf(format) +   currentIndex);
              
            }

            // Then we move on to the next block
            currentIndex++;
            
        }

        if (depth == 0) {
            return endIndex;
        } else {
        	commonUTIL.display("COSSWIFTPattern", "Unexpected end of format" +String.valueOf(format) +   endIndex);
        	return 0;
           
        }
    }

    private static int parseLabel(Vector labels, char[] format, int currentIndex, int endIndex) {
        StringBuffer label = new StringBuffer();
        while (currentIndex < endIndex) {
            if (format[currentIndex] == '_' ||
                Character.isUpperCase(format[currentIndex]) ||
                Character.isDigit(format[currentIndex])) {
                label.append(format[currentIndex++]);
            } else {
                break;
            }
        }
        labels.add(label.toString());
        return currentIndex;
    }
    
    private static String getCharset(char c) {
        switch (c) {
            case 'n':
                return "[\\p{Digit}]";
            case 'd':
                return "[\\p{Digit}[\\,]]";
            case 'a':
                return "[\\p{Upper}]";
            case 'x':
                return "[\\p{Alnum}[\\/\\-\\?\\:\\(\\)\\.\\,\\'\\+\\{\\} ]]"; // SWIFT character set
            case 'y':
                return "[\\p{Upper}\\p{Digit}[\\.\\,\\-\\(\\)\\/\\=\\'\\+\\:\\?\\!\"\\%\\&\\*\\<\\>\\; ]]"; // EDIFACT Level A character set
            case 'z':
                return "[\\p{Alnum}[\\.\\,\\-\\(\\)\\/\\=\\'\\+\\:\\?\\!\"\\%\\&\\*\\<\\>;\\{\\@\\# ]]"; // Information Service character set
            case 'c':
                return "[\\p{Upper}\\p{Digit}]";
            case 'h':
                return "[\\p{XDigit}&&[^\\p{Lower}]]";
            case 's':
                return "[\\+\\-]";
            case 'e':
                return "[\\p{Blank}]";
        }
        return null;
    }
    
}



