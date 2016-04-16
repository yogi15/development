package apps.window.referencewindow;

import java.awt.BorderLayout;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import apps.window.staticwindow.BasePanel;
import apps.window.staticwindow.util.BookWindowUtil;
import apps.window.util.propertyTable.BookPropertyTable;
import apps.window.util.tableModelUtil.BookTableModelUtil;
import beans.Book;
import constants.BookConstants;
import constants.CommonConstants;
import util.CosmosException;
import util.commonUTIL;

public class BookWindow extends BasePanel {
	ActionMap actions = null;
	public String searchData[];
	private static final long serialVersionUID = 1L;
	public BookTableModelUtil model = null;
	Book book = new Book(); /// used as a bean
	// used for Validation and save,update and delete and get Data from DB.
	BookWindowUtil windowUtil = null;
	Vector<Book> rightPanelJtableBookdata = new Vector<Book>(); // used maintain
																// data in
																// rightPanel in
																// center area.

	/**
	 * @return the data / public Vector<Book> getData() { return
	 *         rightPanelJtableBookdata; } /**
	 * @param data
	 *            the data to set
	 */
	public void setData(Vector<Book> data) {
		// this.data = data;
		rightPanelJtableBookdata = data;
	}

	// leftTopPanel Data
	protected JLabel bookLabelName = new JLabel("Book Name");
	public final JTextField bookSearchTextField = new JTextField("BookTextField", 15); // search
																						// textfield
																						// in
																						// leftTopPanel
																						// Data
	// rightTopPanel Data
	private JLabel bookName = new JLabel("Book Name");
	protected JButton bookDetails = new JButton("Load BookDetails");
	// leftSide PropertyTable
	public BookPropertyTable propertyTable = null;

	// Constructor
	public BookWindow() {
		try {
			initComponents();
		} catch (CosmosException e) {
			// TODO Auto-generated catch block
			commonUTIL.displayError(BookConstants.WINDOW_NAME, "Constructor", e);
		}
	}

	private void initComponents() throws CosmosException {
		/// init() data required while loading this window.
		init();
		setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
		setLayout(new BorderLayout());
		// add model to table
		model = new BookTableModelUtil(rightPanelJtableBookdata);
		setCornerForScrollPane(model.getCol());
		setQuickSearchOnTable(model, model.getCol().length);
		createSingleSplitPaneLayout(CommonConstants.SPLITWINDOWLOCATION);
		setSize(CommonConstants.WINDOWWIDTH, CommonConstants.WINDOWHIGHT);
	}

	/**
	 * @return the Book
	 */
	public Book getBook() {
		return book;
	}

	/**
	 * @param Book
	 *            the Book to set
	 */
	public void setBook(Book book) {
		this.book = book;
	}

	@Override
	public ActionMap getHotKeysActionMapper() {
		ActionMap action = new ActionMap();
		return action;
	}

	@Override
	public JPanel getHotKeysPanel() {
		return rightTopbuttonsPanel;
	}

	@Override
	public ArrayList<Component> getFocusOrderList() {
		ArrayList<Component> comps = new ArrayList<Component>();
		comps.add(loadButton);
		return comps;
	}

	// add Window Validation util for search,save,new,saveAsNew,close and other
	// custom components.
	@Override
	public void setWindowValidationUtil() {
		windowUtil = new BookWindowUtil();
		windowUtil.setWindow(this);
		this.validationActionUtil = windowUtil;
	}

	// add listerener to panel Jcompenonts.
	@Override
	public void setWindowActionListener() {
		try {
			setEventListener(bookDetails);
			setEventListener(bookSearchTextField);
		} catch (CosmosException e) {
			e.printStackTrace();
		}
	}

	// add lefttop panel componenonts
	@Override
	public void addTopLeftSidePanelComponents() {
		bookSearchTextField.setName(BookConstants.SEARCHTEXTBOX);
		leftTopbuttonsPanel.add(bookLabelName);
		setSearchOnLeftTopPanel(bookSearchTextField, searchData);
		leftTopbuttonsPanel.add(bookSearchTextField);
	}

	// add righttop panel componenonts
	@Override
	public void addTopRigthSidePanelComponents() {
		rightTopbuttonsPanel.add(bookName);
		rightTopbuttonsPanel.add(bookDetails);
	}

	// create property proprities.
	@Override
	public void createPropertyPaneTable() {
		propertyTable = new BookPropertyTable(BookConstants.WINDOW_NAME, book);
		setLeftSidePropertyPanePanel(propertyTable.getPropertyTable(generateProperty(BookConstants.WINDOW_NAME)));
	}

	@Override
	public void addCenterRightSidePanelComponents() {
		// TODO Auto-generated method stub
		centerRightSidePanel.add(scrollPane, BorderLayout.CENTER);
	}

	@Override
	public JPanel createChildPanel(int parentID) {
		return null;
	}

	@Override
	public JPanel createChildPanel(String id) {
		return null;
	}

	@Override
	public String getWindowName() {
		return BookConstants.WINDOW_NAME;
	}
}
