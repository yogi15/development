
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.table.TableModel;

import com.calypso.apps.reporting.TableReportViewer;
import com.calypso.apps.util.TableModelUtil;
import com.calypso.tk.core.Amount;
import com.calypso.tk.core.Price;
import com.jidesoft.pivot.PivotDataModel;
import com.jidesoft.pivot.PivotField;

/**
 * JidePivotTableBuilder. Builder used to test Jide pivot grids. Similar to
 * PivotTableBuilder.
 * 
 * @author gokul_krishna
 * 
 */
public class JidePivotTableBuilder {

    // Test Data Columns
    static String HEADER[] = {
                              "CounterParty", "Trade Cur.", "Trade Id",
                              "Product Description", "Trade Date",
                              "Settle Date", "Entered Date", "Entered User",
                              "Bundle Name", "Bundle Type", "Quantity",
                              "Price", "Comment", "Book", "Status", "Trader",
                              "Settle Cur."};

    // Test Data Values
    static Object DATA[][] = {
                              {
                               "CREDILIONPAR", "USD", "1501",
                               "BondUST/5Y/12/01/2004/4.5%", "1/14/2004 11:23",
                               "1/15/2004", "1/14/2004 11:17", "calypso_user",
                               "b", "q", new Amount("2.00"),
                               new Price("55-000", 100), "c", "TRADINGC",
                               "VERIFIED", "NONE", "USD"},
                              {
                               "CREDILIONPAR", "USD", "1505",
                               "BondUST/5Y/12/01/2004/4.5%", "1/14/2004 11:39",
                               "1/19/2004", "1/14/2004 11:39", "calypso_user",
                               "q", "w", new Amount("-1.00"),
                               new Price("99-000", 100), "c", "TRADINGC",
                               "VERIFIED", "TRADER1", "USD"},
                              {
                               "CREDILIONPAR", "USD", "1701",
                               "BondUST/5Y/12/01/2004/4.5%", "1/9/2004 13:37",
                               "1/12/2004", "1/20/2004 13:39", "calypso_user",
                               "w", "q", new Amount("1.00"),
                               new Price("", 100), "c", "TRADINGC", "VERIFIED",
                               "TRADER2", "USD"},
                              {
                               "TST_CPTY1", "USD", "1024",
                               "Cap/USD/LIBOR/3M/4.00000%QTR/12/03/2004",
                               "12/1/1999 23:59", "12/3/1999",
                               "9/14/2000 6:00", "tst_user1", "e", "w",
                               new Amount("1.00"), new Price("1.00", 100), "c",
                               "TST_BOOK1", "VERIFIED", "NONE", "USD"},
                              {
                               "CREDILIONPAR", "USD", "1103", "FX/EUR/USD",
                               "1/8/2004 11:59", "1/23/2004", "1/8/2004 11:59",
                               "calypso_user", "r", "q", new Amount("500.00"),
                               new Price("1.10", 100), "c", "TRADINGC",
                               "VERIFIED", "TRADER1", "USD"},
                              {
                               "CREDILIONPAR", "JPY", "1201", "FX/USD/JPY",
                               "1/12/2004 17:05", "1/15/2004",
                               "1/12/2004 17:05", "calypso_user", "t", "w",
                               new Amount("-1,000,000.00"),
                               new Price("124.00", 100), "Any Comment!",
                               "TRADINGC", "VERIFIED", "TRADER1", "JPY"},
                              {
                               "CREDILIONPAR", "USD", "1101",
                               "FXForward/EUR/USD", "1/8/2004 11:40",
                               "1/12/2004", "1/8/2004 11:40", "calypso_user",
                               "y", "q", new Amount("1,000,000.00"),
                               new Price("0.80", 100), "c", "TRADINGC",
                               "VERIFIED", "TRADER1", "USD"},
                              {
                               "CREDILIONPAR", "USD", "1102",
                               "FXForward/EUR/USD", "1/8/2004 11:42",
                               "1/12/2004", "1/8/2004 11:42", "calypso_user",
                               "u", "w", new Amount("-1,000,000.00"),
                               new Price("0.80", 100), "c", "TRADINGC",
                               "VERIFIED", "TRADER1", "USD"},
                              {
                               "CREDILIONPAR",
                               "USD",
                               "2101",
                               "Repo(BondUST/5Y/12/01/2004/4.5%)/01/16/2004/01/29/2004/0.00000",
                               "1/14/2004 10:46", "1/16/2004",
                               "1/22/2004 10:51", "calypso_user", "i", "e",
                               new Amount("1.00"), new Price("1.00", 100), "c",
                               "TRADINGC", "VERIFIED", "TRADER4", "USD"},
                              {
                               "CREDILIONPAR",
                               "USD",
                               "2401",
                               "Sell Protection on CREDILIONPAR SENIOR_UNSECURED/Receive: USD 5.00000",
                               "1/28/2004 16:34", "1/29/2004",
                               "1/28/2004 16:42", "calypso_user", "o", "r",
                               new Amount("-1.00"), new Price("0.00", 100),
                               "c", "TRADINGC", "VERIFIED", "NONE", "USD"},
                              {
                               "CREDILIONPAR", "USD", "1401",
                               "StructuredProduct StructuredProduct",
                               "1/14/2004 11:15", "1/14/2004",
                               "1/14/2004 11:15", "calypso_user", "p", "e",
                               new Amount("1.00"), new Price("0.00", 100), "c",
                               "TRADINGC", "VERIFIED", "NONE", "USD"},
                              {
                               "TST_CPTY1",
                               "USD",
                               "1402",
                               "Swap/01/16/2002/P:USD 5.00000 /R:USD/LIBOR/6M + 50.00bp",
                               "1/14/2004 11:33", "12/3/1999",
                               "1/14/2004 11:33", "calypso_user", "q", "w",
                               new Amount("1.00"), new Price("1.00", 100),
                               "Calypso Test Program Generated Swap",
                               "TST_BOOK1", "VERIFIED", "TRADER1", "USD"},
                              {
                               "CREDILIONPAR",
                               "USD",
                               "1502",
                               "Swap/01/16/2004/P:USD 5.00000 /R:USD/LIBOR/6M + 50.00bp",
                               "1/14/2004 11:22", "12/3/1999",
                               "1/14/2004 11:22", "calypso_user", "w", "w",
                               new Amount("1.00"), new Price("1.00", 100),
                               "Calypso Test Program Generated Swap",
                               "TRADINGC", "VERIFIED", "TRADER1", "USD"},
                              {
                               "CREDILIONPAR",
                               "USD",
                               "2201",
                               "Swap/01/28/2004/P:USD 5.00000 /R:USD/LIBOR/6M + 50.00bp",
                               "1/14/2004 11:22", "12/3/1999",
                               "1/23/2004 10:51", "calypso_user", "e", "w",
                               new Amount("1.00"), new Price("1.00", 100),
                               "Calypso Test Program Generated Swap",
                               "TRADINGC", "VERIFIED", "TRADER3", "USD"},
                              {
                               "TST_CPTY1",
                               "USD",
                               "1001",
                               "Swap/03/01/2004/P:USD 5.00000 /R:USD/LIBOR/6M + 50.00bp",
                               "12/1/1999 23:59", "12/3/1999",
                               "9/14/2000 6:00", "tst_user1", "e", "w",
                               new Amount("1.00"), new Price("1.00", 100),
                               "Calypso test1 test2 test3", "TST_BOOK1",
                               "VERIFIED", "foo,foo2", "USD"}};

    /**
     * Populates the model with test data.
     * 
     * @param model
     */
    public static void populateTestData(TableModelUtil model) {
        int cols = HEADER.length;
        int rows = DATA.length;

        for (int i = 0; i < cols; i++)
            model.setColumnName(i, HEADER[i]);
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < rows; j++) {
                model.setValueAt(DATA[j][i], j, i);
            }
            model.setColumnEditable(i, true);
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        // Create view and controller
        JidePivotTableReportViewer jideViewer = new JidePivotTableReportViewer();
        JidePivotTableReportController jidePivotTableReportController = new JidePivotTableReportController(jideViewer);

        // Create base model and populate with test data
        TableModelUtil model = new TableReportViewer(HEADER.length, DATA.length);
        populateTestData(model);

        // Create pivot data model
        TableModel calculatedModel = JidePivotTableUtils.createCalculatedTableModel(model);
        PivotDataModel jidePivotDataModel = JidePivotTableUtils.createPivotDataModel(calculatedModel);

        jidePivotTableReportController.initialize(jidePivotDataModel, model);

        // Initialize with preset column config
        jidePivotDataModel.getField("Trade Cur.")
                .setAreaType(PivotField.AREA_ROW);
        jidePivotDataModel.getField("Trade Cur.").setAreaIndex(1);
        jidePivotDataModel.getField("Book").setAreaType(PivotField.AREA_ROW);
        jidePivotDataModel.getField("Book").setAreaIndex(2);
        jidePivotDataModel.getField("Bundle Name")
                .setAreaType(PivotField.AREA_ROW);
        jidePivotDataModel.getField("Bundle Name").setAreaIndex(3);
        jidePivotDataModel.getField("Status")
                .setAreaType(PivotField.AREA_COLUMN);
        jidePivotDataModel.getField("Status").setAreaIndex(1);
        jidePivotDataModel.getField("Bundle Type")
                .setAreaType(PivotField.AREA_COLUMN);
        jidePivotDataModel.getField("Bundle Type").setAreaIndex(2);
        jidePivotDataModel.getField("Quantity")
                .setAreaType(PivotField.AREA_DATA);
        jidePivotDataModel.getField("Quantity").setAreaIndex(1);
        jidePivotDataModel.getField("Price").setAreaType(PivotField.AREA_DATA);
        jidePivotDataModel.getField("Price").setAreaIndex(2);
        jidePivotDataModel.getField("Trade Date")
                .setAreaType(PivotField.AREA_FILTER);
        jidePivotDataModel.getField("Trade Date").setAreaIndex(1);

        jideViewer.getPivotTablePane().fieldsUpdated();
        jidePivotTableReportController.getPivotDataModel().calculate();

        // Test frame to display
        JFrame testFrame = new JFrame("Test Jide Pivot Table");
        testFrame.getContentPane().add(jideViewer);
        testFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        testFrame.setSize(600, 900);
        testFrame.pack();
        testFrame.setVisible(true);

    }

}