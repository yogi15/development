package src.apps.window.staticwindow;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.CosmosException;
import util.commonUTIL;
import apps.window.staticwindow.util.BaseWindowUtil;
import apps.window.util.propertyPane.enumsList.JavaScriptPropertyEnumList;
import apps.window.util.propertyPane.enumsList.WindowSheetEnumList; 

import com.jidesoft.dialog.ButtonPanel;
import com.jidesoft.grid.HierarchicalPanel;
import com.jidesoft.grid.HierarchicalTable;
import com.jidesoft.grid.HierarchicalTableComponentFactory;
import com.jidesoft.grid.IFilterableTableModel;
import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.grid.QuickTableFilterField;
import com.jidesoft.grid.RowStripeTableStyleProvider;
import com.jidesoft.grid.SortableTable;
import com.jidesoft.grid.TableColumnChooser;
import com.jidesoft.hints.ListDataIntelliHints;
import com.jidesoft.pane.BookmarkPane;
import com.jidesoft.plaf.UIDefaultsLookup;
import com.jidesoft.plaf.basic.ThemePainter;
import com.jidesoft.swing.JideBoxLayout;
import com.jidesoft.swing.JideSplitPane;
import com.jidesoft.swing.JideSwingUtilities;
import com.jidesoft.swing.JideTitledBorder;
import com.jidesoft.swing.PartialEtchedBorder;
import com.jidesoft.swing.PartialLineBorder;
import com.jidesoft.swing.PartialSide;
import com.jidesoft.swing.Resizable;
import com.jidesoft.swing.ResizablePanel;
import com.jidesoft.swing.SelectAllUtils;

import constants.CommonConstants;
import constants.JavaScriptConstants;
import constants.WindowSheetConstants;

public abstract class BasePanel extends JPanel {
	public JFrame frame = null;
	HierarchicalTable childWindow = null;
	JPanel leftFrame = new JPanel();

    private QuickTableFilterField _filterField;
	  private TableModelListener _listener;
	  JPanel quickSearchPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
	/**
	 * @return the leftFrame
	 */
	public JPanel getLeftFrame() {
		leftFrame.add(leftTopbuttonsPanel, BorderLayout.NORTH);
		leftFrame.add(leftCenterPanel, BorderLayout.CENTER);
		leftFrame.add(leftBottombuttonsPanel, BorderLayout.SOUTH);
		return leftFrame;
	}

	/**
	 * @return the centerFrame
	 */
	public JPanel getCenterFrame() {
	//	centerFrame.add(quickSearchPanel, BorderLayout.BEFORE_FIRST_LINE);
		centerFrame.add(rightTopbuttonsPanel, BorderLayout.NORTH);
		centerFrame.add(centerRightSidePanel, BorderLayout.CENTER);
		return centerFrame;
	}

	JPanel centerFrame = new JPanel();
	/**
	 * @return the parentRow
	 */
	public int getParentRow() {
		return parentRow;
	}

	/**
	 * @param parentRow
	 *            the parentRow to set
	 */
	public void setParentRow(int parentRow) {
		this.parentRow = parentRow;
	}

	/**
	 * @return the hierarchicalTable
	 */
	public HierarchicalTable getHierarchicalTable() {
		return hierarchicalTable;
	}

	/**
	 * @param hierarchicalTable
	 *            the hierarchicalTable to set
	 */
	public void setHierarchicalTable(HierarchicalTable hierarchicalTable) {
		this.hierarchicalTable = hierarchicalTable;
	}

	int parentRow = 0;

	/**
	 * @return the frame
	 */
	public JFrame getFrame() {
		return frame;
	}

	/**
	 * @param frame
	 *            the frame to set
	 */
	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public void close() {

		validationActionUtil.clearALL();
		validationActionUtil = null;
		frame.dispose();
	}

	/**
	 * 
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static final Color BACKGROUND1 = new Color(253, 253, 244);
	protected static final Color BACKGROUND2 = new Color(230, 230, 255);
	protected static final Color BACKGROUND3 = new Color(210, 255, 210);

	protected static final Color FOREGROUND1 = new Color(0, 0, 10);

	protected static final Color BACKGROUND4 = new Color(0, 128, 0);
	protected static final Color FOREGROUND4 = new Color(255, 255, 255);

	public BaseWindowUtil validationActionUtil = null;

	public abstract ActionMap getHotKeysActionMapper();

	public abstract JPanel getHotKeysPanel();

	public abstract ArrayList<Component> getFocusOrderList();

	public abstract void setWindowValidationUtil();

	public abstract void addTopLeftSidePanelComponents();

	public abstract void addTopRigthSidePanelComponents();

	public abstract void createPropertyPaneTable();

	public abstract void addCenterRightSidePanelComponents();

	public abstract JPanel createChildPanel(int id);

	public abstract JPanel createChildPanel(String id);

	/* add listerener to each Jcompenonts added subclass panel. */
	public abstract void setWindowActionListener();

	public JScrollPane scrollPane = new JScrollPane();
	public SortableTable rightSideCenterTable = new SortableTable();
	public HierarchicalTable hierarchicalTable = null;
	JInternalFrame jInternalFrame1;
	JInternalFrame jInternalFrame2;
	public final JTextField WindowSheetSearchTextField = new JTextField(
			"WindowSheetTextField"); // search textfield in leftTopPanel Data

	protected JPanel splitLeftPanel = new JPanel(new BorderLayout());
	protected JPanel splitRightPanel = new JPanel(new BorderLayout());
	protected JPanel centerRightSidePanel = new JPanel(new BorderLayout());
	protected JSplitPane splitPane = new JSplitPane(
			JSplitPane.HORIZONTAL_SPLIT, splitLeftPanel, splitRightPanel);

	protected JPanel rightTopbuttonsPanel = new JPanel();
	protected JPanel leftBottombuttonsPanel = new JPanel();
	protected JPanel leftTopbuttonsPanel = new JPanel();

	protected JPanel leftCenterPanel = new JPanel(new BorderLayout());
	protected JPanel leftCenterPanelNoLeftTopPanel = new JPanel(
			new BorderLayout());

	protected JButton loadButton = new JButton("Load");
	protected JButton newButton = new JButton("New");
	protected JButton deleteButton = new JButton("Delete");
	protected JButton saveButton = new JButton("Save");
	protected JButton saveAsNewButton = new JButton("Save As New");
	protected JButton closeButton = new JButton("Close");
	boolean isHierarchicalTableSelected = false;
	EventAction lSymAction = new EventAction();

	protected void createSingleSplitPaneLayout(int splitPaneLocation) {
		leftFrame.setLayout(new BorderLayout());
		centerFrame.setLayout(new BorderLayout());
		validationActionUtil.windowstartUpData();
		addTopLeftSidePanelComponents();
		addTopRigthSidePanelComponents();
		createPropertyPaneTable();
		splitPane.setDividerLocation(splitPaneLocation);
		scrollPane.getViewport().add(rightSideCenterTable);
	   
		 
		splitRightPanel.add(centerRightSidePanel, BorderLayout.CENTER);
		if (!isHierarchicalTableSelected) {
			rightSideCenterTable
					.setTableStyleProvider(new RowStripeTableStyleProvider(
							new Color[] { BACKGROUND1, BACKGROUND3 }));
			rightSideCenterTable.setName(CommonConstants.RIGHTSIDECENTERTABLE);
			rightSideCenterTable.setRowSelectionAllowed(true);
		}

		splitLeftPanel.add(leftTopbuttonsPanel, BorderLayout.NORTH);
		splitLeftPanel.add(leftCenterPanel, BorderLayout.CENTER);
		splitLeftPanel.add(leftBottombuttonsPanel, BorderLayout.SOUTH);
		
		splitRightPanel.add(rightTopbuttonsPanel, BorderLayout.NORTH);
		rightSideCenterTable.setModel(_filterField.getDisplayTableModel() );
		createleftBottomsPanel();
		setRightPanelTopComp();
		addCenterRightSidePanelComponents();

		setEventActionListener();
		
	
		
		add(splitPane, BorderLayout.CENTER);
	}

	public IFilterableTableModel getFilterModel() {
		return  _filterField.getDisplayTableModel();
	}
	protected void setQuickSearchOnTable(TableModel model) {
		
	        _filterField = new QuickTableFilterField(model, new int[]{0,1,    });
	        _filterField.setHintText("Type here to filter songs");
	        rightTopbuttonsPanel.add(_filterField);
	        quickSearchPanel.setBorder(new JideTitledBorder(new PartialEtchedBorder(PartialEtchedBorder.LOWERED, PartialSide.NORTH), "QuickTableFilterField", JideTitledBorder.LEADING, JideTitledBorder.ABOVE_TOP));
	        _filterField.setTable(rightSideCenterTable); 
	        _filterField.getDisplayTableModel().addTableModelListener(_listener);
	     
	        
	       
	}
	protected void createSingleSplitPaneLayout() {

		validationActionUtil.windowstartUpData();
		addTopLeftSidePanelComponents();

		createPropertyPaneTable();

		// setLayout(new JideBoxLayout(splitLeftPanel, JideBoxLayout.X_AXIS));

		createleftBottomsPanel();

		setLayout(new BorderLayout(4, 4));
		setBorder(BorderFactory.createCompoundBorder(
				new JideTitledBorder(new PartialLineBorder(UIDefaultsLookup
						.getColor("controlShadow"), 1, PartialSide.NORTH),
						"Contact", JideTitledBorder.RIGHT,
						JideTitledBorder.CENTER), BorderFactory
						.createEmptyBorder(2, 2, 2, 2)));
		// add(leftCenterPanel, BorderLayout.CENTER);
		add(leftTopbuttonsPanel, BorderLayout.NORTH);
		add(leftCenterPanel, BorderLayout.CENTER);
		add(leftBottombuttonsPanel, BorderLayout.SOUTH);

		JideSwingUtilities.setOpaqueRecursively(this, false);
		rightSideCenterTable
				.setTableStyleProvider(new RowStripeTableStyleProvider(
						new Color[] { BACKGROUND1, BACKGROUND3 }));
		rightSideCenterTable.setName(CommonConstants.RIGHTSIDECENTERTABLE);
		rightSideCenterTable.setRowSelectionAllowed(true);
		setEventActionListener();
		setOpaque(true);
		// add(splitLeftPanel, BorderLayout.CENTER);

	}

	public void init() {
		setWindowValidationUtil();
	}

	protected void setEventActionListener() {
		loadButton.addActionListener(lSymAction);
		newButton.addActionListener(lSymAction);
		deleteButton.addActionListener(lSymAction);
		saveAsNewButton.addActionListener(lSymAction);
		saveButton.addActionListener(lSymAction);

		closeButton.addActionListener(lSymAction);
		rightSideCenterTable.addMouseListener(lSymAction);
		rightSideCenterTable.addKeyListener(lSymAction);
		setWindowActionListener();
	}

	protected void setEventListener(JComponent comp) throws CosmosException {
		if (comp instanceof AbstractButton) {
			((JButton) comp).addActionListener(lSymAction);
		} else if (comp instanceof JTextField) {
			((JTextField) comp).addKeyListener(lSymAction);

		} else if (comp instanceof JComboBox) {
			((JComboBox<String>) comp).addItemListener(lSymAction);
		} else if (comp instanceof SortableTable) {
			((SortableTable) comp).addMouseListener(lSymAction);
			((SortableTable) comp).addKeyListener(lSymAction);
		}

	}

	protected void createleftBottomsPanel() {
		JPanel buttonPanel = new ButtonPanel(SwingConstants.RIGHT);

		buttonPanel
				.add(buttons2ColumnForLeftBottonPanel(loadButton, newButton));
		buttonPanel.add(buttons2ColumnForLeftBottonPanel(saveAsNewButton,
				saveButton));
		buttonPanel.add(buttons2ColumnForLeftBottonPanel(deleteButton,
				closeButton));
		leftBottombuttonsPanel.add(buttonPanel, JideBoxLayout.FLEXIBLE);

	}

	protected void setRightPanelTopComp() {
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.LEFT);
		rightTopbuttonsPanel.setLayout(layout);

	}

	protected void setSearchOnLeftTopPanel(JTextField textField, String[] values) {

		SelectAllUtils.install(textField);
		if (!commonUTIL.isEmpty(values)) {
			ListDataIntelliHints<String> fontIntelliHints = new ListDataIntelliHints<String>(
					textField, values);
			fontIntelliHints.setCaseSensitive(false);
		}

	}

	/*
	 * protected void setSearchOnLeftTopPanel(JTextField textField,List<String>
	 * values) {
	 * 
	 * 
	 * SelectAllUtils.install(textField); if(!commonUTIL.isEmpty(values)) {
	 * ListDataIntelliHints<String> fontIntelliHints = new
	 * ListDataIntelliHints<String>( textField, values);
	 * fontIntelliHints.setCaseSensitive(false); }
	 * 
	 * 
	 * }
	 */
	private ButtonPanel buttons2ColumnForLeftBottonPanel(JButton topButton,
			JButton botButton) {
		ButtonPanel buttonPanel = new ButtonPanel(SwingConstants.TOP);
		buttonPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
		buttonPanel.add(topButton, ButtonPanel.AFFIRMATIVE_BUTTON);
		buttonPanel.add(botButton, ButtonPanel.AFFIRMATIVE_BUTTON);
		return buttonPanel;

	}

	public List<Property> generateProperty(String windowName) {
		List<Property> prope = validationActionUtil
				.generateProperty(windowName);

		if (commonUTIL.isEmpty(prope)) {
			if (windowName.equalsIgnoreCase("JavaScriptWindow"))
				prope = JavaScriptPropertyEnumList.JAVASCRIPT
						.getPropertyList(JavaScriptConstants.WINDOW_NAME);
			else
				prope = WindowSheetEnumList.WINDOWSHEET
						.getPropertyList(WindowSheetConstants.WINDOW_NAME);
		}
		return prope;
	}

	protected void setLeftSidePropertyPanePanel(PropertyTable propertyTable) {
		JPanel quickSearchPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));

		QuickTableFilterField filterField = new QuickTableFilterField(
				propertyTable.getModel());
		quickSearchPanel.add(filterField);
		filterField.setHintText("Type here to filter properties");
		filterField.setObjectConverterManagerEnabled(true);

		propertyTable.setModel(filterField.getDisplayTableModel());

		propertyTable.setTableStyleProvider(new RowStripeTableStyleProvider());
		JPanel basePanel = new JPanel();
		JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.getViewport().add(propertyTable);
		basePanel.setLayout(new BorderLayout());

		basePanel.add(quickSearchPanel, BorderLayout.BEFORE_FIRST_LINE);

		basePanel.add(tableScrollPane, BorderLayout.CENTER);

		leftCenterPanel.add(basePanel, BorderLayout.CENTER);
	}

	public HierarchicalTable createTable(AbstractTableModel model,
			final BasePanel contpanel) {
		isHierarchicalTableSelected = true;
		final HierarchicalTable table = new HierarchicalTable(model);

		table.setTableStyleProvider(new RowStripeTableStyleProvider());
		table.setName("HieRarachical Table");
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setHierarchicalColumn(-1);
		table.setSingleExpansion(true);
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					public void valueChanged(ListSelectionEvent e) {
						if (table.getSelectedRow() != -1) {
							table.expandRow(table.getSelectedRow());
						}
					}
				});
		table.setComponentFactory(new HierarchicalTableComponentFactory() {
			public Component createChildComponent(HierarchicalTable table,
					Object value, int row) {
				final BookmarkPane pane = new BookmarkPane() {
					@Override
					public void insertTab(String title, Icon icon,
							Component component, String tip, int index) {
						if (!(component instanceof ResizablePanel)) {
							ResizablePanel panel = new ResizablePanel(
									new BorderLayout()) {
								@Override
								protected Resizable createResizable() {
									Resizable resizable = new Resizable(this);
									resizable
											.setResizableCorners(Resizable.LOWER);
									return resizable;
								}
							};
							panel.add(component);

							panel.setBorder(new EmptyBorder(0, 0, 4, 0) {
								@Override
								public void paintBorder(Component c,
										Graphics g, int x, int y, int width,
										int height) {
									super.paintBorder(c, g, x, y, width, height);
									ThemePainter painter = (ThemePainter) UIDefaultsLookup
											.get("Theme.painter");
									if (painter != null) {
										Insets insets = getBorderInsets(c);
										Rectangle rect = new Rectangle(0, y
												+ height - insets.bottom,
												width, insets.bottom);
										painter.paintGripper((JComponent) c, g,
												rect,
												SwingConstants.HORIZONTAL,
												ThemePainter.STATE_DEFAULT);
									}
								}
							});
							super.insertTab(title, icon, panel, tip, index);
						} else {
							super.insertTab(title, icon, component, tip, index);
						}
					}
				};
				pane.setBorder(new HierarchicalPanelBorder());

				contpanel.setHierarchicalTable(table);

				hierarchicalTable.getSelectionModel().addListSelectionListener(
						new ListSelectionListener() {
							public void valueChanged(ListSelectionEvent e) {
								if (hierarchicalTable.getSelectedRow() != -1) {

									parentRow = ((Integer) hierarchicalTable
											.getModel().getValueAt(
													hierarchicalTable
															.getSelectedRow(),
													0)).intValue();
									setParentRow(parentRow);

								}
							}
						});

				//
				pane.addTab(
						"ALLContact",
						new DetailPanel(table, row, contpanel
								.createChildPanel(getParentRow())));// getJInternalFrame2(contpanel.createChildPanel(getParentRow())));
				pane.addTab("Contact", getJInternalFrame1(contpanel));
				return new HierarchicalPanel(pane, BorderFactory
						.createEmptyBorder());
			}

			public void destroyChildComponent(HierarchicalTable table,
					Component component, int row) {
			}
		});
		table.setPreferredScrollableViewportSize(new Dimension(600, 400));
		table.getSelectionModel().setAnchorSelectionIndex(0);
		// table.getSelectionModel().setLeadSelectionIndex(0);

		return table;

	}
	
	public void setCornerForScrollPane(String [] cols) {
		scrollPane.setCorner(JScrollPane.UPPER_RIGHT_CORNER, TableColumnChooser.getTableColumnChooserButton(rightSideCenterTable,
                new int[]{0},cols));
        _listener = new TableModelListener() {
            public void tableChanged(TableModelEvent e) {
                if (e.getSource() instanceof TableModel) {
                    int count = ((TableModel) e.getSource()).getRowCount();
                  //  label.setText(count + " out of " + tableModel.getRowCount() + " songs");
                }
            }
        };
	}

	public void setParentKeyID(int primaryKey) {

	}

	public void setParentKeyID(String primaryKey) {

	}

	private JInternalFrame getJInternalFrame1(JPanel panel) {

		if (jInternalFrame1 == null) {
			jInternalFrame1 = new JInternalFrame();
			jInternalFrame1.setVisible(true);
			jInternalFrame1.setLayout(new GroupLayout());

			javax.swing.plaf.InternalFrameUI ifu = jInternalFrame1.getUI();
			((javax.swing.plaf.basic.BasicInternalFrameUI) ifu)
					.setNorthPane(null);

			jInternalFrame1.add(panel, new Constraints(new Leading(4, 341, 10,
					10), new Leading(2, 353, 10, 10)));
		}
		return jInternalFrame1;
	}

	class HierarchicalPanelBorder implements Border {
		public void paintBorder(Component c, Graphics g, int x, int y,
				int width, int height) {
			g.setColor(UIDefaultsLookup.getColor("controlShadow"));
			g.drawLine(x, y, x + width - 1, y);
			g.setColor(UIDefaultsLookup.getColor("controlShadow"));
			g.drawLine(x, y + height - 1, x + width - 1, y + height - 1);
			g.setColor(UIDefaultsLookup.getColor("control"));
			g.drawLine(x + width - 1, y, x + width - 1, y + height - 1);
			g.drawLine(x + width - 1, y, x + width - 1, y + height - 1);
		}

		public Insets getBorderInsets(Component c) {
			return new Insets(1, 1, 1, 1);
		}

		public boolean isBorderOpaque() {
			return true;
		}
	}

	static class DetailPanel extends JPanel {
		private HierarchicalTable _table;
		private int _row;

		public DetailPanel(HierarchicalTable table, int row, JPanel panl) {
			_table = table;
			_row = row;
			initComponents(panl);
		}

		public DetailPanel(JPanel panl) {
			initComponents(panl);
		}

		void initComponents() {
			setLayout(new BorderLayout(4, 4));
			setBorder(BorderFactory.createCompoundBorder(
					new JideTitledBorder(new PartialLineBorder(UIDefaultsLookup
							.getColor("controlShadow"), 1, PartialSide.NORTH),
							"Trade", JideTitledBorder.RIGHT,
							JideTitledBorder.CENTER), BorderFactory
							.createEmptyBorder(2, 2, 2, 2)));
			add(createDetailPanel(), BorderLayout.CENTER);
			JideSwingUtilities.setOpaqueRecursively(this, false);
			setOpaque(true);
			// setBackground(BG1);
		}

		void initComponents(JPanel panel) {
			setLayout(new BorderLayout(4, 4));
			setBorder(BorderFactory.createCompoundBorder(
					new JideTitledBorder(new PartialLineBorder(UIDefaultsLookup
							.getColor("controlShadow"), 1, PartialSide.NORTH),
							"Trade", JideTitledBorder.RIGHT,
							JideTitledBorder.CENTER), BorderFactory
							.createEmptyBorder(2, 2, 2, 2)));
			add(panel, BorderLayout.CENTER);
			JideSwingUtilities.setOpaqueRecursively(this, false);
			setOpaque(true);
			// setBackground(BG1);
		}

		JComponent createDetailPanel() {
			JPanel panel = new JPanel(new BorderLayout());
			JTable table = new JTable(10, 5);
			panel.add(new JScrollPane(table));
			return panel;
		}
	}

	class EventAction implements java.awt.event.ActionListener,
			java.awt.event.ItemListener, java.awt.event.MouseListener,
			java.awt.event.KeyListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			Cursor origCursor = getCursor();
			if (event.getActionCommand().equalsIgnoreCase(
					CommonConstants.CLOSEBUTTON)) {
				close();
				return;
			}
			validationActionUtil.actionMapper(event.getActionCommand());
			setCursor(origCursor);

		}

		@Override
		public void itemStateChanged(ItemEvent event) {
			// TODO Auto-generated method stub

			Cursor origCursor = getCursor();
			validationActionUtil.actionMapper(event.getItem().toString());
			setCursor(origCursor);

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			// window
			Cursor origCursor = getCursor();
			if (e.getComponent().getName()
					.equalsIgnoreCase(CommonConstants.CLOSEBUTTON)) {
				close();
			}

			validationActionUtil.actionMapper(e.getComponent().getName());
			setCursor(origCursor);

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			if (e.getKeyCode() == KeyEvent.VK_UP
					|| e.getKeyCode() == KeyEvent.VK_DOWN
					|| e.getKeyCode() == KeyEvent.VK_ENTER) {
				Cursor origCursor = getCursor();
				validationActionUtil.actionMapper(e.getComponent().getName());
				setCursor(origCursor);
			}
		}

	}

}
