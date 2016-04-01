package util.csvReader;

import java.io.*;
import java.util.*;


public class CSVSimpleReader {
  private BufferedReader _reader;
  private String _currentLine;
  private String _delim = ",";

  public void setDelim(String delim) {
    _delim = delim;
  }

  public void close() throws IOException {
    _reader.close();
  }
  public CSVSimpleReader(String filename) throws IOException {
    if (!new File(filename).exists()) {
      System.out.println( "file not found: " + filename);
      System.out.println( "file not found: " + filename);
      throw new IOException();
    }

    _reader = new BufferedReader(new FileReader(filename));
    readNextLine();
  }

  public boolean hasNext() {
    return _currentLine != null;
  }

  public List next() throws IOException {

    List columns = new ArrayList();

    if (_currentLine == null) {
      return null;
    }

    final int start = 1;
    final int quote_begin = 2;
    final int quote_end = 3;
    final int coma_within = 4;

    int state = start;
    boolean use_split = true;
    if (use_split) {

//      for (int i = 0;i < _currentLine.length();i++) {
//        char c = _currentLine.charAt(i);
//        switch (state) {
//          case start:
//            if (c == '"') {
//              state = quote_begin;
//            }
//            break;
//          case quote_begin:
//            break;
//          case quote_end:
//            break;
//          case coma_within:
//            break;
//        }
//      }

      String[] strs = _currentLine.split("\\,");
      for (int i = 0;i < strs.length;i++) {
        String str = strs[i];

        if ((str.startsWith("\"")) && !(str.endsWith("\""))) {
          if (i + 1 < strs.length) {
            if ((strs[i + 1].endsWith("\"")) && !(strs[i + 1].startsWith("\""))) {
              str = str + "," + strs[i + 1];
              i += 1;
            }
          }
        }

        str = processToken(str);


        columns.add(str);
      }

    } else {

      StringTokenizer tokenizer = new StringTokenizer(_currentLine, _delim);
      while (tokenizer.hasMoreElements()) {
        String str = tokenizer.nextToken();

        str = processToken(str);

        columns.add(str);
      }
    }


    readNextLine();
    return columns;
  }

  private String processToken(String str) {

    /// remove leading and ending quotes, if both presents
    if (str.startsWith("\"")) {
      if (str.endsWith("\"")) {
        str = str.substring(1,str.length()-1);
      }
    }

    return str;
  }
  private void processToken2(String str) {
    boolean comaStarted = false;
    boolean use_coma = true;
    String comaStr = null;

    // in case there are comas in the field
    // the tokenizer chops them , so we need to concatenate
    // 1,234,567.89 -Excel->  "1,234,567.89" -tokenizer-> "1 234 567.89"  -reader-> 1,234,567.89
    if (!comaStarted) {
      if (_delim.equals(",")) {
        if (str.substring(0,1).equals("\"")) {
          comaStarted = true;
          comaStr = str.substring(1, str.length());
        }
      }
    } else {
      if (str.substring(str.length() - 1,str.length()).equals("\"")) {
        if (use_coma) comaStr += ",";  // reintroduce coma
        comaStr += str.substring(0, str.length() - 1);
        str = comaStr;
        comaStarted = false;
      } else {
        if (use_coma) comaStr += ",";  // reintroduce coma
        comaStr += str;
      }
    }


    // remove double quotes  "ZZ" --> ZZ
    if (str.substring(0,1).equals("\"")) {
      if (str.substring(str.length() - 1,str.length()).equals("\"")) {
        str = str.substring(1, str.length() - 1);
      }
    }

    // replace single quote  with double single quote  y'all --> y''all (Oracle needs that)
    if (str.indexOf("'") > 0) {
      str = str.replaceAll("'", "''");
    }

    // replace 1 leading space , often found in the VAR.txt file
    if (str.startsWith(" ")) {
      str = str.substring(1,str.length());
    }

    boolean negative = false;
    // replace first and last parenthesis with - sign , often found in the VAR.txt file
    if ((str.startsWith("(")) && (str.endsWith(")"))) {
      str = str.substring(1,str.length()-1);
      negative = true;
    }

    // remove leading $ sign , happens with VAR file if someone forget the format
    if (str.startsWith("$")) {
      str = str.substring(1, str.length());
      str = str.replaceAll(",","");
    }

    if (negative) {
      str = "-" + str;
    }

    // NOT NEEDED: because all done in KeyValueLookupSQL
    // fixed for VAR which sometimes has counterparties expressed in lower cases
    //      str = str.toUpperCase();
  }

  private void readNextLine() throws IOException {
    _currentLine = _reader.readLine();
  }

  // For testing this class
  // Usage: pgm file
  public static void main(String[] args) {
    String file = args[0];

    CSVSimpleReader csv = null;
    try {
      csv = new CSVSimpleReader(file);
      csv.setDelim(",");

      while (csv.hasNext()) {
        List list = csv.next();

        for (int i = 0;i < list.size();i++) {
          System.out.print("col " + i + ": ");
          System.out.println(list.get(i));
        }
        System.out.println();

      }
    }
    catch (IOException ex) {
    }

  }
}
