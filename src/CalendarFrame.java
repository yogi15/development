/*
 *
 * Copyright (c) 2000 by Calypso Technology, Inc.
 * 595 Market Street, Suite 1980, San Francisco, CA  94105, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Calypso Technology, Inc. ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Calypso Technology.
 *
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;


public class CalendarFrame extends JFrame
{

    private void jbInit() throws Exception
    {

        getContentPane().setLayout(null);
        setSize(496,378);
        setVisible(false);
        getContentPane().add(dayOfWeekChoice);
        dayOfWeekChoice.setBounds(87,9,91,24);
        dayText.setText("1");
        getContentPane().add(dayText);
        dayText.setBounds(182,9,63,24);
        getContentPane().add(monthChoice);
        monthChoice.setBounds(251,9,90,24);
        yearText.setText("1999");
        getContentPane().add(yearText);
        yearText.setBounds(347,9,63,24);
        label1.setText("Holidays");
        getContentPane().add(label1);
        label1.setBounds(45,47,59,21);
        hideButton.setText("Close");
        hideButton.setActionCommand("Hide");
        getContentPane().add(hideButton);
        hideButton.setBounds(403,322,76,24);
        refreshButton.setText("Refresh");
        refreshButton.setActionCommand("Refresh");
        getContentPane().add(refreshButton);
        refreshButton.setBounds(13,322,88,24);
        JScrollPane1.setOpaque(true);
        getContentPane().add(JScrollPane1);
        JScrollPane1.setBounds(133,70,347,144);
        JScrollPane1.getViewport().add(calendarTable);
        calendarTable.setBounds(0,0,344,0);
        JScrollPane2.setOpaque(true);
        getContentPane().add(JScrollPane2);
        JScrollPane2.setBounds(12,69,115,147);
        JScrollPane2.getViewport().add(codeList);
        codeList.setBounds(0,0,112,144);
        JPanel1.setLayout(null);
        getContentPane().add(JPanel1);
        JPanel1.setBounds(13,220,466,90);
        JLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        JLabel1.setText("Start");
        JPanel1.add(JLabel1);
        JLabel1.setBounds(15,9,34,24);
        JPanel1.add(startDateText);
        startDateText.setBounds(58,8,93,24);
        JLabel2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        JLabel2.setText("End");
        JPanel1.add(JLabel2);
        JLabel2.setBounds(176,8,29,24);
        JPanel1.add(endDateText);
        endDateText.setBounds(210,8,93,24);
        JPanel1.add(dcChoice);
        dcChoice.setBounds(361,8,99,24);
        JLabel3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        JLabel3.setText("DayCount");
        JPanel1.add(JLabel3);
        JLabel3.setBounds(302,8,58,24);
        JLabel4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        JLabel4.setText("Day Diff");
        JPanel1.add(JLabel4);
        JLabel4.setBounds(4,51,48,24);
        dayDiffText.setEditable(false);
        JPanel1.add(dayDiffText);
        dayDiffText.setBounds(58,51,93,24);
        JLabel5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        JLabel5.setText("Year Diff");
        JPanel1.add(JLabel5);
        JLabel5.setBounds(152,51,53,24);
        yearDiffText.setEditable(false);
        JPanel1.add(yearDiffText);
        yearDiffText.setBounds(210,52,93,24);
        calcButton.setText("Calc");
        calcButton.setActionCommand("Calc");
        JPanel1.add(calcButton);
        calcButton.setBounds(391,52,69,24);
        couponPanel.setLayout(null);
        JPanel1.add(couponPanel);
        couponPanel.setBounds(315,52,67,24);
        couponPanel.setVisible(false);
        JLabel6.setText("Cpn.");
        couponPanel.add(JLabel6);
        JLabel6.setBounds(5,5,25,15);
        couponTextField.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        couponTextField.setToolTipText("Number of Coupons Per Year");
        couponTextField.setText("1");
        couponPanel.add(couponTextField);
        couponTextField.setBounds(33,2,29,21);
        //$$ popTable.move(0,0);
        startMenuItem.setRequestFocusEnabled(true);
        startMenuItem.setText("=> Start");
        startMenuItem.setActionCommand("=> Start");
        setTitle("Calendar Window");
        popTable.add(startMenuItem);
        endMenuItem.setRequestFocusEnabled(true);
        endMenuItem.setText("=> End");
        endMenuItem.setActionCommand("=> End");
        popTable.add(endMenuItem);
        showBeforeButton.setText("<=");
        showBeforeButton.setActionCommand("Before Button");
        getContentPane().add(showBeforeButton);
        showBeforeButton.setBounds(132,48,62,19);
        showTodayButton.setText("Today");
        showTodayButton.setActionCommand("Today");
        getContentPane().add(showTodayButton);
        showTodayButton.setForeground(java.awt.Color.red);
        showTodayButton.setBounds(245,48,120,19);
        showAfterButton.setText("=>");
        showAfterButton.setActionCommand("After Button");
        getContentPane().add(showAfterButton);
        showAfterButton.setBounds(420,48,57,19);

        SymWindow aSymWindow = new SymWindow();
        this.addWindowListener(aSymWindow);
        SymItem lSymItem = new SymItem();
        monthChoice.addItemListener(lSymItem);
        SymAction lSymAction = new SymAction();
        yearText.addActionListener(lSymAction);
        refreshButton.addActionListener(lSymAction);
        hideButton.addActionListener(lSymAction);
        calcButton.addActionListener(lSymAction);
        startMenuItem.addActionListener(lSymAction);
        endMenuItem.addActionListener(lSymAction);
        dcChoice.addActionListener(lSymAction);
        showBeforeButton.addActionListener(lSymAction);
        showTodayButton.addActionListener(lSymAction);
        showAfterButton.addActionListener(lSymAction);
    }

    public CalendarFrame()
    {
        try {
            jbInit();
        } catch (Exception e) {
         //  Log.error(this, e);
        }
	initDomains();

	calendarTable.addMouseListener(new java.awt.event.MouseAdapter() {
		public void mousePressed(java.awt.event.MouseEvent e) {maybeShowPopup(e);}
		public void mouseReleased(java.awt.event.MouseEvent e) {maybeShowPopup(e);}
		private void maybeShowPopup(java.awt.event.MouseEvent e) {
		    if (e.isPopupTrigger()) {
			popTable.show(e.getComponent(),e.getX(),e.getY());
			Point p = new Point(e.getX(),e.getY());
			if(calendarTable != null) {
			    r = calendarTable.rowAtPoint(p);
			    c = calendarTable.columnAtPoint(p);
			    calendarTable.clearSelection();
			    calendarTable.addColumnSelectionInterval(c,c);
			    calendarTable.addRowSelectionInterval(r,r);
			}
		    }
		}
		public void mouseClicked (java.awt.event.MouseEvent e) {
                    if (e.getClickCount() <2) return;
		    Point p = new Point(e.getX(),e.getY());
		    int row = calendarTable.rowAtPoint(p);
		    int col = calendarTable.columnAtPoint(p);
		    Object value= calendarTable.getValueAt(row,col);
		    if(value == null) return;
		    String sday=(String)value;
		    int day=0;
		    if(sday.indexOf('(') < 0) day=Integer.parseInt(sday);
		    else day=Integer.parseInt(sday.substring(sday.indexOf('(')+1,sday.indexOf(')')));
		    int year=Integer.parseInt(yearText.getText());
		    if(year < 100) year += 1900;
		    int month=monthChoice.getSelectedIndex()+1;
		    JDate d = DateU.vvalueOf(year,month,day);
		    dayOfWeekChoice.setSelectedIndex(col);
		    dayText.setText((Integer.valueOf(day)).toString());
		    if(_source != null) {
			_source.setText(Util.dateToString(d));
			setVisible(false);
		    }
                    setTableModelValue(d);
		}
	    });
    }

    int r = 0;
    int c = 0;

    public CalendarFrame(String title)
    {
	this();
	setTitle(title);
    }
    public void setVisible(boolean b)
    {
	if(b)
	    {
		setLocation(50, 50);
	    }
	super.setVisible(b);
    }

    static public void main (String args[])
    {
	(new CalendarFrame()).setVisible(true);
    }

    public void addNotify()
    {
	// Record the size of the window prior to calling parents addNotify.
	Dimension d = getSize();

	super.addNotify();
	/*
	  if (fComponentsAdjusted)
	  return;

	  // Adjust components according to the insets
	  Insets ins = getInsets();
	  setSize(ins.left + ins.right + d.width, ins.top + ins.bottom + d.height);
	  Component components[] = getComponents();
	  for (int i = 0; i < components.length; i++)
	  {
	  Point p = components[i].getLocation();
	  p.translate(ins.left, ins.top);
	  components[i].setLocation(p);
	  }
	*/
	fComponentsAdjusted = true;
    }

    // Used for addNotify check.
    boolean fComponentsAdjusted = false;

    class SymWindow extends java.awt.event.WindowAdapter
    {
	public void windowClosing(java.awt.event.WindowEvent event)
	{
	    Object object = event.getSource();
	    if (object == CalendarFrame.this)
		CalendarFrame_WindowClosing(event);
	}
    }

    void CalendarFrame_WindowClosing(java.awt.event.WindowEvent event)
    {
	hideButton_ActionPerformed(null);
    }
    //{{DECLARE_CONTROLS
	JComboBox dayOfWeekChoice = new JComboBox();
	javax.swing.JTextField dayText = new javax.swing.JTextField();
	JComboBox monthChoice = new JComboBox();
	javax.swing.JTextField yearText = new javax.swing.JTextField();
	javax.swing.JLabel label1 = new javax.swing.JLabel();
	javax.swing.JButton hideButton = new javax.swing.JButton();
	javax.swing.JButton refreshButton = new javax.swing.JButton();
	javax.swing.JScrollPane JScrollPane1 = new javax.swing.JScrollPane();
	javax.swing.JTable calendarTable = new javax.swing.JTable();
	javax.swing.JScrollPane JScrollPane2 = new javax.swing.JScrollPane();
	javax.swing.JList codeList = new javax.swing.JList();
	javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
	javax.swing.JLabel JLabel1 = new javax.swing.JLabel();
	javax.swing.JTextField startDateText = new javax.swing.JTextField();
	javax.swing.JLabel JLabel2 = new javax.swing.JLabel();
	javax.swing.JTextField endDateText = new javax.swing.JTextField();
	JComboBox dcChoice = new JComboBox();
	javax.swing.JLabel JLabel3 = new javax.swing.JLabel();
	javax.swing.JLabel JLabel4 = new javax.swing.JLabel();
	javax.swing.JTextField dayDiffText = new javax.swing.JTextField();
	javax.swing.JLabel JLabel5 = new javax.swing.JLabel();
	javax.swing.JTextField yearDiffText = new javax.swing.JTextField();
	javax.swing.JButton calcButton = new javax.swing.JButton();
	javax.swing.JPanel couponPanel = new javax.swing.JPanel();
	javax.swing.JLabel JLabel6 = new javax.swing.JLabel();
	javax.swing.JTextField couponTextField = new javax.swing.JTextField();
	javax.swing.JPopupMenu popTable = new javax.swing.JPopupMenu();
	javax.swing.JMenuItem startMenuItem = new javax.swing.JMenuItem();
	javax.swing.JMenuItem endMenuItem = new javax.swing.JMenuItem();
	javax.swing.JButton showBeforeButton = new javax.swing.JButton();
	javax.swing.JButton showTodayButton = new javax.swing.JButton();
	javax.swing.JButton showAfterButton = new javax.swing.JButton();

    //{{DECLARE_MENUS


    class SymItem implements java.awt.event.ItemListener
    {
	public void itemStateChanged(java.awt.event.ItemEvent event)
	{
	    Object object = event.getSource();
	    if (object == monthChoice)
		monthChoice_ItemStateChanged(event);
	}
    }

    class SymAction implements java.awt.event.ActionListener
    {
	public void actionPerformed(java.awt.event.ActionEvent event)
	{
	    Object object = event.getSource();
	    if (object == yearText)
		yearText_ActionPerformed(event);
	    else if (object == refreshButton)
		refreshButton_ActionPerformed(event);
	    else if (object == hideButton)
		hideButton_ActionPerformed(event);
	    else if (object == calcButton)
		calcButton_actionPerformed(event);
	    else if (object == startMenuItem)
		startMenuItem_actionPerformed(event);
	    else if (object == endMenuItem)
		endMenuItem_actionPerformed(event);
	    else if (object == dcChoice)
		dcChoice_actionPerformed(event);
	    else if (object == showBeforeButton)
		showBeforeButton_actionPerformed(event);
	    else if (object == showTodayButton)
		showTodayButton_actionPerformed(event);
	    else if (object == showAfterButton)
		showAfterButton_actionPerformed(event);
	}
    }

    protected void initDomains() {
	AppUtil.set(codeList,Holiday.getCurrent().getHolidayCodeList());
	Vector v= new Vector();
	v.addElement("JAN");
	v.addElement("FEB");
	v.addElement("MAR");
	v.addElement("APR");
	v.addElement("MAY");
	v.addElement("JUN");
	v.addElement("JUL");
	v.addElement("AUG");
	v.addElement("SEP");
	v.addElement("OCT");
	v.addElement("NOV");
	v.addElement("DEC");
	//AppUtil.set(monthChoice,v);
	v= new Vector();
	v.addElement("MON");
	v.addElement("TUE");
	v.addElement("WED");
	v.addElement("THU");
	v.addElement("FRI");
	v.addElement("SAT");
	v.addElement("SUN");
	//AppUtil.set(dayOfWeekChoice,v);

	DateU d= d.getd
	_model = new CalendarModel(7,5);
	_model.setTo(calendarTable,false);
	AppUtil.set(dcChoice,DayCount.getDomain());
	TableUtil.adjust(calendarTable);
	calendarTable.setCellSelectionEnabled(true);
	showDate(d);
	AppUtil.addChoiceListener(monthChoice);
	AppUtil.addChoiceListener(dayOfWeekChoice);
	AppUtil.addChoiceListener(dcChoice);
	startDateText.setText(Util.dateToString(d));
	endDateText.setText(Util.dateToString(d));
	AppUtil.addDateListener(startDateText);
	AppUtil.addDateListener(endDateText,startDateText);
	startDateText.postActionEvent();
	endDateText.postActionEvent();
	Vector hol = new Vector();
	try {
	    hol = DSConnection.getDefault().getUserDefaults().getHolidays();
	} catch (Exception e) {}
	if (hol != null) AppUtil.setSelections(codeList,hol);
	refreshButton_ActionPerformed(null);
    }

    void monthChoice_ItemStateChanged(java.awt.event.ItemEvent event) {
	refreshButton_ActionPerformed(null);
    }

    void yearText_ActionPerformed(java.awt.event.ActionEvent event) {
	refreshButton_ActionPerformed(null);
    }

    void startMenuItem_actionPerformed(java.awt.event.ActionEvent event) {
	Object value= calendarTable.getValueAt(r,c);
	if(value == null) return;
	String sday=(String)value;
	int day=0;
	if(sday.indexOf('(') < 0) day=Integer.parseInt(sday);
	else day=Integer.parseInt(sday.substring(sday.indexOf('(')+1,sday.indexOf(')')));
	int year=Integer.parseInt(yearText.getText());
	if(year < 100) year += 1900;
	int month=monthChoice.getSelectedIndex()+1;
	JDate d = JDate.valueOf(year,month,day);
	dayOfWeekChoice.setSelectedIndex(c);
	dayText.setText((Integer.valueOf(day)).toString());
	if(_source != null) {
	    _source.setText(Util.dateToString(d));
	    setVisible(false);
	}
        setTableModelValue(d);
	startDateText.setText(Util.dateToString(d));
    }

    void endMenuItem_actionPerformed(java.awt.event.ActionEvent event) {
	Object value= calendarTable.getValueAt(r,c);
	if(value == null) return;
	String sday=(String)value;
	int day=0;
	if(sday.indexOf('(') < 0) day=Integer.parseInt(sday);
	else day=Integer.parseInt(sday.substring(sday.indexOf('(')+1,sday.indexOf(')')));
	int year=Integer.parseInt(yearText.getText());
	if(year < 100) year += 1900;
	int month=monthChoice.getSelectedIndex()+1;
	JDate d = JDate.valueOf(year,month,day);
	dayOfWeekChoice.setSelectedIndex(c);
	dayText.setText((Integer.valueOf(day)).toString());
	if(_source != null) {
	    _source.setText(Util.dateToString(d));
	    setVisible(false);
	}
        setTableModelValue(d);
	endDateText.setText(Util.dateToString(d));
    }

    void refreshButton_ActionPerformed(java.awt.event.ActionEvent event) {
	TableCellRenderer renderer = new CustomTableCellRenderer();
	int year=Integer.parseInt(yearText.getText());
	if(year < 100) year += 1900;
	int month=monthChoice.getSelectedIndex()+1;
	JDate d = JDate.valueOf(year,month,1);
	int wd=d.getDayOfWeek();
	int lastDay=d.getMonthLength();
	_model.reinitRows(4);
	Vector hols=AppUtil.getSelections(codeList);
	int pos = (d.getDayOfWeek()+5) %7 ;
	for(int i=1;i<=lastDay;i++,pos++) {
	    d= JDate.valueOf(d.getYear(),d.getMonth(),i);
	    int col=pos % 7;
	    int row=pos/7;
	    if(row == _model.getRowCount()) _model.addRow();
	    if(Holiday.getCurrent().isBusinessDay(d,hols))
		_model.setValueAt(row,col, String.valueOf(i));
	    else{
		if(col == 5 || col == 6){
		    _model.setValueAt(row,col,"(" + String.valueOf(i) + ")");
		}
		else{
		    _model.setValueAt(row,col,"[" + String.valueOf(i) + "]");
		}
	    }
	    try{
		calendarTable.setDefaultRenderer(java.lang.String.class, renderer);
	    }catch(Exception e){
		Log.error(this, e);
	    }
	}
	_model.refreshStructureChanged();
	_model.refresh();
	TableUtil.adjust(calendarTable);
	JDate dd = JDate.getNow();
	startDateText.setText(Util.dateToString(dd));
	startDateText.postActionEvent();
	endDateText.setText(Util.dateToString(dd));
	endDateText.postActionEvent();
	dcChoice.setSelectedItem("");
	dayDiffText.setText("");
	yearDiffText.setText("");
    }

    void hideButton_ActionPerformed(java.awt.event.ActionEvent event)
    {
	setVisible(false);
	dispose();
    }

    public void showDate(JDate d) {
	if(d==null) {
	    d= JDate.getNow();
	}
	dayOfWeekChoice.calypsoSetSelectedItem(d.getDayOfWeekAsString());
	dayText.setText(String.valueOf(d.getDayOfMonth()));
	monthChoice.calypsoSetSelectedIndex(d.getMonth()-1);
	yearText.setText(String.valueOf(d.getYear()));
	refreshButton_ActionPerformed(null);
    }

    public void showDates(JDate start, JDate end) {
    	if(start == null || end == null) return;

    	startDateText.setText(Util.dateToString(start));
    	endDateText.setText(Util.dateToString(end));
    	calcButton_actionPerformed(null);
    }


    void calcButton_actionPerformed(java.awt.event.ActionEvent event)
    {
	JDate start=Util.stringToJDate(startDateText.getText());
	if(start==null) {
	    AppUtil.displayWarning("Please input a Start Date",this);
	    return;
	}
	JDate end=Util.stringToJDate(endDateText.getText());
	if(end==null) {
	    AppUtil.displayWarning("Please input an End Date",this);
	    return;
	}
	try {
	    DayCount dc=DayCount.get((String)dcChoice.getSelectedItem());

	    if (!dc.equals(DayCount.D_ACTB_ACTB)) {
		dayDiffText.setText(String.valueOf(dc.dayDiff(start, end)));
		yearDiffText.setText(Util.numberToString(dc.yearDiff(start, end)));
	    }
	    else {
		int periods = Integer.parseInt(couponTextField.getText());
		if (periods == 0) periods = 1;
		dayDiffText.setText(String.valueOf(dc.dayDiff(start, end)));
		yearDiffText.setText(Util.numberToString(dc.yearDiffACTB_ACTB(start, end,12/periods)));

	    }
	}
	catch (Exception e) { Log.error(this, e);}
    }
    class CalendarModel extends TableModelUtil {
	CalendarModel(int cols, int rows) {
	    super(cols, rows);
	    setColumnName(0, "MON");
	    setColumnName(1, "TUE");
	    setColumnName(2, "WED");
	    setColumnName(3, "THU");
	    setColumnName(4, "FRI");
	    setColumnName(5, "SAT");
	    setColumnName(6, "SUN");
	}
	public void dblClicked(int row, int col) {
	    Object value= getValueAt(row,col);
	    if(value == null) return;
	    String sday=(String)value;
	    int day=0;
	    if(sday.indexOf('(') < 0) day=Integer.parseInt(sday);
	    else day=Integer.parseInt(sday.substring(sday.indexOf('(')+1,sday.indexOf(')')));
	    int year=Integer.parseInt(yearText.getText());
	    if(year < 100) year += 1900;
	    int month=monthChoice.getSelectedIndex()+1;
	    JDate d = JDate.valueOf(year,month,day);
	    dayOfWeekChoice.setSelectedIndex(col);
	    dayText.setText((Integer.valueOf(day)).toString());
	    if(_source != null) {
		_source.setText(Util.dateToString(d));
		setVisible(false);
	    }
            setTableModelValue(d);
	}
    }

    public void showHolidays(Vector holidays) {
	if(holidays==null) holidays=new Vector();
	AppUtil.setSelections(codeList,holidays);
	//calcButton_actionPerformed(null);
	refreshButton_ActionPerformed(null);
    }

    TableModelUtil _sourceTableModel = null;
    int _row = -1;
    int _col = -1;
    public void setTableModel(TableModelUtil model, int row, int col) {
        _sourceTableModel = model;
        _row  = row;
        _col = col;
    }

    public void setTableModelValue(JDate d) {
      if(_sourceTableModel != null && _row != -1 && _col != -1) {
            _sourceTableModel.notifyOnNewValue(false);
            _sourceTableModel.setValueAt(_row, _col, d);
            _sourceTableModel.notifyOnNewValue(true);
            _sourceTableModel.fireTableDataChanged();
            setVisible(false);
        }
    }

    JTextField _source;
    public void setTextField(JTextField tf) {
	_source=tf;
    }
    TableModelUtil _model;



    void dcChoice_actionPerformed(java.awt.event.ActionEvent event)
    {
	// to do: code goes here.
	String dc = (String)dcChoice.getSelectedItem();
	if (dc.equals(DayCount.S_ACTB_ACTB))
	    couponPanel.setVisible(true);
	else
	    couponPanel.setVisible(false);
    }

    void showBeforeButton_actionPerformed(java.awt.event.ActionEvent event)
    {
	int day = Integer.parseInt(dayText.getText());
	int month = monthChoice.getSelectedIndex();
	int year=Integer.parseInt(yearText.getText());
    if(month < 1) {
        month = month + 12;
        year--;
    }
	if(year < 100) year += 1900;
	JDate d = JDate.valueOf(year,month,day);
	showDate(d);

    }

    void showTodayButton_actionPerformed(java.awt.event.ActionEvent event)
    {
	JDate d=  JDate.getNow();
	showDate(d);

    }

    void showAfterButton_actionPerformed(java.awt.event.ActionEvent event)
    {
	int day = Integer.parseInt(dayText.getText());
	int month = monthChoice.getSelectedIndex()+2;
	int year=Integer.parseInt(yearText.getText());
    if(month > 12) {
        month = month - 12;
        year++;
    }
	if(year < 100) year += 1900;
	JDate d = JDate.valueOf(year,month,day);
	showDate(d);

    }

    public class CustomTableCellRenderer implements TableCellRenderer{
       	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
  						       boolean hasFocus, int row, int column){
	    String selectedDate = dayText.getText();
	    int selectedYear=Integer.parseInt(yearText.getText());
	    int selectedMonth=monthChoice.getSelectedIndex()+1;

	    JDate currentDate=  JDate.getNow();
	    int currentYear = currentDate.getYear();
	    int currentMonth = currentDate.getMonth();
	    int dayOfMonth = currentDate.getDayOfMonth();
	    JLabel cell = new JLabel();
	    cell.setOpaque(true);
	    String cellValue = (String) value;
	    String tempCellValue = cellValue;

	    if(Util.isEmptyString(cellValue)){
		cell.setBackground(Color.white);
		if (isSelected) cell.setBackground(new Color(0,78,155));
		cell.setText("");
		return cell;
	    }

	    if(tempCellValue.indexOf("(") != -1)
		tempCellValue = tempCellValue.substring(tempCellValue.indexOf("(")+1, tempCellValue.indexOf(")"));

	    if(tempCellValue.indexOf("[") != -1)
		tempCellValue = tempCellValue.substring(tempCellValue.indexOf("[")+1, tempCellValue.indexOf("]"));

	    if( dayOfMonth == Integer.parseInt(tempCellValue)
		&& (selectedYear == currentYear) && (selectedMonth == currentMonth)){
		cell.setBackground(Color.red);
	    }

	    else if(cellValue.indexOf("(") >= 0 || cellValue.indexOf("[") >= 0){
		cell.setBackground(Color.lightGray);
	    }
	    else
	      cell.setBackground(Color.white);

	    cell.setText(cellValue);

	    if (isSelected) cell.setBackground(new Color(0,78,155));

	    return cell;

	}

    }

}










