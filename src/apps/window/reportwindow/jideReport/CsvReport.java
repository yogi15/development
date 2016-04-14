package apps.window.reportwindow.jideReport;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.commons.lang.StringUtils;

import au.com.bytecode.opencsv.CSVReader;

/**
 * A CSV report implementation.
 */
public class CsvReport implements Report {

    public static Report newReport(final String csvData) {
        if( StringUtils.isBlank(csvData)) {
            return new NullReport();
        }
        return new CsvReport(csvData);
    }

    public Vector<Vector<Object>> getData() {
        return tableData;
    }

    public Vector<String> getDataHeader() {
        return header;
    }

    public Class<?>[] getDataType() {
        return types.toArray(new Class[0]);
    }

    void parseData(final String csvData) throws IOException,
            DatatypeConfigurationException {
        // @see http://opencsv.sourceforge.net/
        final CSVReader reader = new CSVReader(new StringReader(csvData));

        DatatypeFactory.newInstance();
        int cnt = 0;
        String[] line;
        while ((line = reader.readNext()) != null) {
            // first line is the header
            // second is the java.types
            // remaining is the data
            if (cnt == 0) {
                for(final String column : line) {
                    header.add(column);
                }
            } else if (cnt == 1) {
                for(final String type : line) {
                    try {
                        Class<?> addType = Class.forName(type);
                        System.out.println(addType);
                        types.add(addType);
                    } catch (final ClassNotFoundException excep) {
                        excep.printStackTrace();
                    }
                }
            } else {
                final Vector<Object> lineData = new Vector<Object>();
                int columnCnt = 0;
                for(final String element : line) {
                    if (columnCnt >= getDataType().length) {
                        continue;
                    }
                    final Class<?> classType = getDataType()[columnCnt];
                    if (classType.equals(Date.class)) {
                        try {
                            // Sun Mar 13 19:00:00 CDT 2011
                            // EEE MMM dd HH:mm:ss z yyyy
                            final Date date = FORMAT.parse(element);
                            lineData.add(date); // order date
                        } catch (final ParseException e) {
                            e.printStackTrace();
                        }
                    } else if (classType.equals(String.class)) {
                        lineData.add(element);

                    } else if (classType.equals(double.class)
                        || classType.equals(Double.class)) {
                        lineData.add(Double.valueOf(element));

                    } else if (classType.equals(int.class)
                        || classType.equals(Integer.class)) {
                        lineData.add(Integer.valueOf(element));

                    } else {
                        lineData.add(element);
                    }
                    columnCnt++;
                }
                tableData.add(lineData);
            }
            cnt++;
        }
    }

    CsvReport(final String csvData) {
        try {
            parseData(csvData);
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final DatatypeConfigurationException excep) {
            excep.printStackTrace();
        }
    }

    final Vector<String> header = new Vector<String>();

    @SuppressWarnings("unchecked")
    final Vector<Class> types = new Vector<Class>();

    final Vector<Vector<Object>> tableData = new Vector<Vector<Object>>();

    final static transient SimpleDateFormat FORMAT =new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
}
