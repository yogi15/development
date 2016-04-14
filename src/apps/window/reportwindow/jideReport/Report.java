package apps.window.reportwindow.jideReport;

import java.util.Vector;


/**
 * Simple Report interface
 */
public interface Report {

    Vector<String> getDataHeader();

    Class<?>[] getDataType();

    Vector<Vector<Object>> getData();
}