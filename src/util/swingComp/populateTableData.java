package util.swingComp;





import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import apps.window.referencewindow.DateCellEditor12;

import com.jidesoft.combobox.DateComboBox;
import com.jidesoft.combobox.DateModel;
import com.jidesoft.combobox.DateModelListener;
import com.jidesoft.combobox.DefaultDateModel;
import com.jidesoft.grid.DateCellEditor;
import com.standbysoft.component.date.event.DateEvent;


public class populateTableData  extends JFrame  {
	
	private static populateTableData _frame;
	javax.swing.JScrollPane jScrollPane2 = null;
    private static JPanel _pane;
    private SimpleDateFormat _dateFormat;
	
	private static DateComboBox _dateComboBox;
	 javax.swing.JTable  jTable2;
	
	public populateTableData(String title) throws HeadlessException {
        super(title);
        String[] columnNames =  {"Fields", "Values"};
        Object[][] data =
    	{
        		 {"ID", "1"},
                 {"productId","0"},
                 {"couponType", "1"},
                 {"FixedRate", "0"},
                 {"CCY", null},
                 {"DayCount", null},
                 {"CouponAdjustMentMethod", "1"},
                 {"CouponFrequency",null},
                 {"BusinessDayConvention", "0"},
                 {"CouponDate", null},
                 {"Rating", "0"},
                 {"Ex_dividend", "1"},
                 {"recordDays", "0"},
                 {"shutDays", "1"},
                 {"AccrualDigits", "0"},
                 {"PriceDecimals", "1"},
                 {"YieldDecimals", "0"},
                 {"NominalDecimals", "1"},
                 {"AnnounceDate", "0"},
                 {"AuctionDate", "1"},
                 {"WithholdingTax", "0"},
                 {"ApplyWithholdingTax", "1"},
                 {"WhenIssueBond", "0"},
                 {"TickSize", null},
                 {"YieldMethod", null},
                 {"QuoteType", null},
                 {"Activefrom", "0"}
    	};
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        TableCellEditor t1 = ((TableCellEditor) new DateCellEditor12());
        
        System.out.println(t1.getCellEditorValue());
        System.out.println(t1);
       // )
        jTable2 = new javax.swing.JTable(model) {
        	 
        	public TableCellEditor getCellEditor(int row, int column)
    		{
    			int modelColumn = convertColumnIndexToModel( column );
    			if (modelColumn == 1 && row ==9) {
    				 TableCellEditor t1 = ((TableCellEditor) new DateCellEditor12());
    			        
    			       
    				return (TableCellEditor)t1;
    				
    			}if (modelColumn == 1 && row ==18) {
   				 TableCellEditor t1 = ((TableCellEditor) new DateCellEditor12());
			        
			        
				return (TableCellEditor)t1;
				
			}if (modelColumn == 1 && row ==19) {
				 TableCellEditor t1 = ((TableCellEditor) new DateCellEditor12());
			        
			        
				return (TableCellEditor)t1;
				
			}if (modelColumn == 1 && row ==26) {
				 TableCellEditor t1 = ((TableCellEditor) new DateCellEditor12());
			        
			       
				return (TableCellEditor)t1;
				
			}else 
      				 
    				return super.getCellEditor(1, 0);
    		}
        	
        };
        jScrollPane2 = new javax.swing.JScrollPane();;
        jScrollPane2.setViewportView(jTable2);
         // _pane.add(jScrollPane2) ;
      
        
    }
	
	public JScrollPane getpopulateTable() {
		return jScrollPane2;
	}

	private static DefaultDateModel createDefaultDateModel() {
        DefaultDateModel model = new DefaultDateModel();
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, 2010);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
        model.setMaxDate(calendar);

        calendar.set(Calendar.YEAR, 1980);
        calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
        model.setMinDate(calendar);

       
        Date currentDate = new Date();
     

        
        return model;
    }
	
	
        private static DateComboBox createDateComboBox() {
            DefaultDateModel model = new DefaultDateModel();
            Calendar calendar = Calendar.getInstance();

            calendar.set(Calendar.YEAR, 2010);
            calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
            model.setMaxDate(calendar);

            calendar.set(Calendar.YEAR, 1980);
            calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMinimum(Calendar.DAY_OF_YEAR));
            model.setMinDate(calendar);

            DateComboBox dateComboBox = new DateComboBox(model);
            Date currentDate = new Date();
            dateComboBox.setDate(currentDate);

            
            return dateComboBox;
        }

	
	
	
	
	
	public static void main(String args[]) {
		_frame = new populateTableData("Demo of DateComboBox");
    //    _frame.setIconImage(JideIconsFactory.getImageIcon(JideIconsFactory.JIDE32).getImage());
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        _pane = new JPanel(new BorderLayout());
        _pane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        _dateComboBox = createDateComboBox();
     //   final DefaultCellEditor dce12 = new DefaultCellEditor( new DateCellEditor(new DateMode());// _dateComboBox );
        
     //   final DefaultCellEditor dce16 = new DefaultCellEditor( createDateComboBox() );
      //  jTable2.getColumnModel().getColumn(0).setCellEditor((TableCellEditor) new DateCellEditor12());
        //jTable2.getColumnModel().getColumn(0).setCellRenderer(new DateCellRenderer12());
    //    jTable2.addm
        
       // jTable2.getColumnModel().getColumn(0).setCellEditor(null);
        _pane.add(_dateComboBox, BorderLayout.BEFORE_FIRST_LINE);
        _pane.add(_frame.getpopulateTable(), BorderLayout.PAGE_END);


        _frame.getContentPane().setLayout(new BorderLayout());
        _frame.getContentPane().add(_pane, BorderLayout.CENTER);
       // _frame.getContentPane().add(_frame.createOptionsPanel(), BorderLayout.AFTER_LINE_ENDS);

        _frame.setBounds(10, 10, 500, 400);

        _frame.setVisible(true);

	}

}
