package apps.window.reportwindow.jideReport;


import java.util.Vector;


public class NullReport implements Report {

    @Override
    public Vector<Vector<Object>> getData() {
        return EMPTY_DATA;
    }

    @Override
    public Vector<String> getDataHeader() {
        return EMPTY_HEADER;
    }

    @Override
    public Class<?>[] getDataType() {
        return EMPTY_TYPE;
    }
    private static Class<?>[] EMPTY_TYPE= {};
    private static Vector<String> EMPTY_HEADER = new Vector<String>();
    private static Vector<Vector<Object>> EMPTY_DATA = new Vector<Vector<Object>>();


}
