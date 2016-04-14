package apps.window.reportwindow.jideReport;


import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.table.TableModel;

import apps.window.reportwindow.DatePanel;
import apps.window.util.windowUtil.ButtonsIconsFactory;

import com.jidesoft.converter.BooleanConverter;
import com.jidesoft.converter.ConverterContext;
import com.jidesoft.converter.DateConverter;
import com.jidesoft.converter.DoubleConverter;
import com.jidesoft.converter.IntegerConverter;
import com.jidesoft.converter.MonthNameConverter;
import com.jidesoft.converter.ObjectConverterManager;
import com.jidesoft.converter.PercentConverter;
import com.jidesoft.converter.QuarterNameConverter;
import com.jidesoft.converter.YearNameConverter;
import com.jidesoft.grid.CalculatedTableModel;
import com.jidesoft.grid.CellEditorManager;
import com.jidesoft.grid.CellRendererManager;
import com.jidesoft.grid.ExpressionCalculatedColumn;
import com.jidesoft.grid.SingleColumn;
import com.jidesoft.grid.SortableTable;
import com.jidesoft.grouper.ObjectGrouper;
import com.jidesoft.grouper.ObjectGrouperManager;
import com.jidesoft.grouper.date.DateMonthGrouper;
import com.jidesoft.grouper.date.DateQuarterGrouper;
import com.jidesoft.grouper.date.DateYearGrouper;
import com.jidesoft.hssf.HssfTableUtils;
import com.jidesoft.plaf.LookAndFeelFactory;


/**
 * SwingReportDemo glues all the classes together.
 * Starting point for the application.
 */
public class SwingReportDemo extends JPanel {

    /**
     * Main entry point into application
     */
	static Report report;
    public SwingReportDemo(Report report2) {
		// TODO Auto-generated constructor stub
    	//this.report= report2;
    	//this.m_report = report2;
    	setReport(report2);
    	setM_report(report2);
	} 

	public static void main(final String[] args) {
        final SwingReportDemo app = new SwingReportDemo(report);
        app.run();
    }

    /**
     * run() is implemented for sample purposes.
     *
     * Design should be modified:
     * <ul>
     * <li>initJide() only needs to be called once, therefor move to the application startup code.</li>
     * <li>Pass in the data, URI to the data, or the Report itself.</li>
     * </ul>
     */
    public JPanel run() {
        initJide();
        m_basePanel = new JPanel();
       // m_frame = new JFrame("Swing Report Demo");

        // Create a Runnable to set the main visible, and get Swing to invoke.
      //  EventQueue.invokeLater(new Runnable() {
          //  public void run() {
             //   m_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            //    getBasePanel().setLayout(new BorderLayout());
           //     getBasePanel().add(getControlPanel(), BorderLayout.NORTH);
           //     getBasePanel().add(getView().getJidePanel(), BorderLayout.CENTER);
            /*    m_frame.add(getBasePanel());
                m_frame.pack();
                m_frame.setSize(1000, 600);
                JideSwingUtilities.globalCenterWindow(m_frame);
                m_frame.toFront();
                m_frame.setVisible(true); */
                return m_basePanel;
            }
      //  });
    

    /**
     * Initialize JIDE settings for application.
     * <ul>
     * <li>Specialized converts & formatters can be configured on app startup</li>
     * <li>Once JIDE has been purchased, the verifyLicense code will also go here</li>
     * </ul>
     */
    void initJide() {
        // Initialize JIDE settings.
        // JIDE Licenses Verification

        LookAndFeelFactory.installDefaultLookAndFeelAndExtension();

        //---------------------------------------------------------------------
        // JIDE ObjectConverterManager.initDefaultConverter
        CellEditorManager.initDefaultEditor();
        CellRendererManager.initDefaultRenderer();
        ObjectConverterManager.initDefaultConverter();
        ObjectGrouperManager.initDefaultGrouper();

        // Note: Apply special converters to display class in something useful to the user.
        ObjectConverterManager.registerConverter(Date.class, new DateConverter(), DateConverter.DATE_CONTEXT);
        ObjectConverterManager.registerConverter(Date.class, new DateConverter(), DateConverter.DATETIME_CONTEXT);
        ObjectConverterManager.registerConverter(boolean.class, new BooleanConverter());
        ObjectConverterManager.registerConverter(Boolean.class, new BooleanConverter());

        // Special formatting of integers
        DecimalFormat myFormatter = new DecimalFormat("#########"); // removes the thousand separator
        IntegerConverter intConverter = new IntegerConverter();
        intConverter.setNumberFormat(myFormatter);
        ObjectConverterManager.registerConverter(int.class, intConverter);
        ObjectConverterManager.registerConverter(Integer.class, intConverter);

        // Choose formatting of doubles
        NumberFormat format = NumberFormat.getNumberInstance();
        format.setMinimumFractionDigits(2);
        format.setMaximumFractionDigits(2);
        DoubleConverter converter = new DoubleConverter(format);
        ObjectConverterManager.registerConverter(Double.class, converter);
        ObjectConverterManager.registerConverter(double.class, converter);

        ObjectConverterManager.registerConverter(double.class,
            new PercentConverter(), PercentConverter.CONTEXT);
        ObjectConverterManager.registerConverter(Double.class,
            new PercentConverter(), PercentConverter.CONTEXT);

        // Register convertors for date class.
        ObjectConverterManager.registerConverter(Date.class, new YearNameConverter(), YearNameConverter.CONTEXT);
        ObjectConverterManager.registerConverter(Date.class, new QuarterNameConverter(), QuarterNameConverter.CONTEXT);
        ObjectConverterManager.registerConverter(Date.class, new MonthNameConverter(), MonthNameConverter.CONTEXT);
    }


    /**
     * Lazy construction of the AggregateView
     * @return JideView
     */
 public   JideView getAggregateView() {
        if (m_aggregateView == null) {
            m_aggregateView = AggregateView.newUi(getCalculatedModel());
        }
        return m_aggregateView;
    }

    JPanel getBasePanel() {
        if (m_basePanel == null) {
            m_basePanel = new JPanel();
        }
        return m_basePanel;
    }
    JideView getSortingView() {
        if (m_sortView == null) {
        	m_sortView = SortingView.newUi(getTableModel());
        }
        return m_sortView;
    }

    /**
     * Lazy construction of CalculatedTableModel
     * Wraps javax.swing.table.TableModel
     * @return CalculatedTableModel;
     */
    CalculatedTableModel getCalculatedModel() {
        if (m_calculatedModel == null) {
            // Wraps and creates a new model type based on TableModel
            // CalculatedTableModel is used for conversions and calculations
            // Sample of the date conversion technique below:
            m_calculatedModel = new CalculatedTableModel(getTableModel());
            m_calculatedModel.addAllColumns();
            System.out.println("Column count     *****************  " + m_calculatedModel.getColumnCount());
            // Assume: datatype and data header are the same size
            for( int index = 0; index<getM_report().getDataType().length; index++) {
                final Class<?> classType = m_report.getDataType()[index];

                if (classType.equals(Date.class)
                        && index<getM_report().getDataHeader().size()) {
                    String header = getM_report().getDataHeader().get(index);
                    // Split up the date field using JIDE converts and groupers
                    // Provides additional grouping & sorting capabilities.
                    addCalculatedColumn(m_calculatedModel, getTableModel(),
                        header, "Year", new DateYearGrouper(),
                        YearNameConverter.CONTEXT);

                    addCalculatedColumn(m_calculatedModel, getTableModel(),
                        header, "Quarter", new DateQuarterGrouper(),
                        QuarterNameConverter.CONTEXT);

                    addCalculatedColumn(m_calculatedModel, getTableModel(),
                        header, "Month", new DateMonthGrouper(),
                        MonthNameConverter.CONTEXT);
                }
            }
        }
       
        return m_calculatedModel;
    }

    public final JButton hideButton  = new JButton("Hide");
    
  public   JPanel getControlPanel(DatePanel panel) {
	 
        if (m_controlPanel == null) {
        	m_toolBar =panel.getJToolBar2();
            m_controlPanel = new JPanel();
           
            if(panel != null) {
            	//jdatePanel = panel;
            m_controlPanel.add(panel.getJPanel4(),BorderLayout.NORTH );
           // m_toolBar = panel.get
            }
         //   m_controlPanel.add(m_toolBar,BorderLayout.WEST);
         //   hideButton = 
            hideButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
                    // Add an calculated column
                    
                }
            });
          //  m_toolBar.add(hideButton);

            final JButton addFormulaButton = new JButton("Add formula");
            addFormulaButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(final ActionEvent e) {
                    // Add an calculated column
                    try {
                        final ExpressionCalculatedColumn commission =
                            new ExpressionCalculatedColumn(getTableModel(),
                                "Commission", "([Amount])*.01");
                        getCalculatedModel().addColumn(commission);
                        getCalculatedModel().fireTableStructureChanged();
                    } catch (final IllegalArgumentException iae) {
                        iae.printStackTrace();
                    }
                    addFormulaButton.setEnabled(false);
                }
            });
            m_toolBar.add(addFormulaButton);

            final JButton exportButton = new JButton("ExportToExcel");
            exportButton.setIcon(apps.window.util.windowUtil.ButtonsIconsFactory.getImageIcon(apps.window.util.windowUtil.ButtonsIconsFactory.Buttons.EXPORTTOEXCEL));
            exportButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    uiExport();
                }
            });
            m_toolBar.add(exportButton);


            final JButton saveLayoutButton = new JButton("SaveLayout");
            saveLayoutButton.setIcon(ButtonsIconsFactory.getImageIcon(ButtonsIconsFactory.Buttons.SAVELAYOUT));
            saveLayoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    uiSaveLayout();
                }
            });
            m_toolBar.add(saveLayoutButton);


            final JButton loadLayoutButton = new JButton("LoadLayout");

            loadLayoutButton.setIcon(ButtonsIconsFactory.getImageIcon(ButtonsIconsFactory.Buttons.LOADLAYOUT));
            loadLayoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    uiLoadLayout();
                }
            });
            m_toolBar.add(loadLayoutButton);


            final JButton switchViewButton = new JButton("SwitchView");
            switchViewButton.setIcon(ButtonsIconsFactory.getImageIcon(ButtonsIconsFactory.Buttons.SWITCHLAYOUT));
            switchViewButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    getBasePanel().remove(getView().getJidePanel());
                    m_useAggregateView = !m_useAggregateView;
                    if (m_useAggregateView) {
                        m_jideView = getAggregateView();

                        // Note: Aggregate & Pivot layouts are different,
                        // hence distinct extensions are used to save layouts.
                        m_configExtension =  AGG_EXTENSION;
                        getBasePanel().add(m_jideView.getJidePanel(),
                                BorderLayout.CENTER);
                    } else {
                    	 final String[] header = { "Date Month" };
                         final String[] row = { "Item Type", "Item Name" };
                         // final String[] data = { "Qty", "Amount" };
                         final String[] data = { "Amount" };
                         
                     //    m_jideView = PivotView.newUi(getCalculatedModel(), header, row, data);
                    	 PivotView view  = PivotView.newUi(getCalculatedModel(), header, row, data);
                        m_configExtension =  "pivot";
                        getBasePanel().add(view.getJidePanel(),
                                BorderLayout.CENTER);
                        m_jideView  = view;
                    }
                   
                    getBasePanel().revalidate();
                    getBasePanel().updateUI();
                }
            });
            
            
            m_toolBar.add(switchViewButton);
            final JButton runSQLButton = new JButton("RUN");
            runSQLButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(final ActionEvent e) {
                    getBasePanel().remove(getView().getJidePanel());
                    getBasePanel().revalidate();
                    getBasePanel().updateUI();
                    m_useAggregateView = !m_useAggregateView;
                   // m_report = null;
                    m_reportTableModel = null;
                    m_jideView = null;
                    m_aggregateView = null;
                    m_calculatedModel = null;
                    JideView view = AggregateView.newUi(getCalculatedModel());
                   
                    getBasePanel().add(view.getJidePanel(),
                            BorderLayout.CENTER);
                    m_jideView = view;
                    getBasePanel().revalidate();
                    getBasePanel().updateUI();
                }
            });
         //   m_toolBar.add(runSQLButton);
            m_configExtension =  AGG_EXTENSION;

        }
        return m_controlPanel;
    }
 
  
  public void runNewPanel() {
	  getBasePanel().remove(getView().getJidePanel());
      getBasePanel().revalidate();
      getBasePanel().updateUI();
      m_useAggregateView = !m_useAggregateView;
     // m_report = null;
      m_reportTableModel = null;
      m_jideView = null;
      m_aggregateView = null;
      m_calculatedModel = null;
      JideView view = AggregateView.newUi(getCalculatedModel());
     
      getBasePanel().add(view.getJidePanel(),
              BorderLayout.CENTER);
      m_jideView = view;
      getBasePanel().revalidate();
      getBasePanel().updateUI();
  }
  public void runSearchPanel() {
	  getBasePanel().remove(getView().getJidePanel());
      getBasePanel().revalidate();
      getBasePanel().updateUI();
      m_useAggregateView = !m_useAggregateView;
     // m_report = null;
      m_reportTableModel = null;
      m_jideView = null;
      m_aggregateView = null;
      m_calculatedModel = null;
      JideView view = AggregateView.newUi(getCalculatedModel());
     
      getBasePanel().add(view.getJidePanel(),
              BorderLayout.CENTER);
      m_jideView = view;
      getBasePanel().revalidate();
      getBasePanel().updateUI();
  }
  
  public void runSortingTable() {
	  getBasePanel().remove(getView().getJidePanel());
      getBasePanel().revalidate();
      getBasePanel().updateUI();
      m_useAggregateView = !m_useAggregateView;
     // m_report = null;
      m_reportTableModel = null;
      m_jideView = null;
      m_aggregateView = null;
      m_sortView = null;
      m_calculatedModel = null;
      JideView view = SortingView.newUi(  getCalculatedModel());
      getBasePanel().add(view.getJidePanel(),
              BorderLayout.CENTER);
      m_jideView = view;
      getBasePanel().revalidate();
      getBasePanel().updateUI();
      
  }
  public TableModel getSortTableModel() {
	   m_reportTableModel = null;
	      m_jideView = null;
	      m_aggregateView = null;
	      m_sortView = null;
	      m_calculatedModel = null;
	      JideView view = SortingView.newUi(  getCalculatedModel());
	 return getCalculatedModel();
      
      
  }
  
  public SortableTable getSortTable() {
	   m_reportTableModel = null;
	      m_jideView = null;
	      m_aggregateView = null;
	      m_sortView = null;
	      m_calculatedModel = null;
	      SortingView view =   new SortingView(   getCalculatedModel());
	 return view.getSortTable();
     
     
 }
  
  public   JPanel getSortingControlPanel() {
      if (m_sortingControlPanel == null) {
    	  m_sortingControlPanel = new JPanel();
       //   hideButton = 
          hideButton.addActionListener(new ActionListener() {

              @Override
              public void actionPerformed(final ActionEvent e) {
                  // Add an calculated column
                  
              }
          });
          m_sortingControlPanel.add(hideButton);

          
          
          
        
          final JButton runSQLButton = new JButton("RUN");
          runSQLButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(final ActionEvent e) {
                  getBasePanel().remove(getView().getJidePanel());
                  getBasePanel().revalidate();
                  getBasePanel().updateUI();
                  m_useAggregateView = !m_useAggregateView;
                 // m_report = null;
                  m_reportTableModel = null;
                  m_jideView = null;
                  m_aggregateView = null;
                  m_calculatedModel = null;
                  m_sortView = null;
                  JideView view = SortingView.newUi(getCalculatedModel());
                 
                  getBasePanel().add(view.getJidePanel(),
                          BorderLayout.CENTER);
                  m_jideView = view;
                  getBasePanel().revalidate();
                  getBasePanel().updateUI();
              }
          });
      //    m_sortingControlPanel.add(runSQLButton);
        //  m_configExtension =  AGG_EXTENSION;

      }
      return m_sortingControlPanel;
  }
  
  public void genearateNewView() {
	  getBasePanel().remove(getView().getJidePanel());
      getBasePanel().revalidate();
      getBasePanel().updateUI();
      m_useAggregateView = !m_useAggregateView;
     // m_report = null;
      m_reportTableModel = null;
      m_jideView = null;
      m_aggregateView = null;
      m_calculatedModel = null;
      m_sortView = null;
      JideView view = SortingView.newUi(getCalculatedModel());
     
      getBasePanel().add(view.getJidePanel(),
              BorderLayout.CENTER);
      m_jideView = view;
      getBasePanel().revalidate();
      getBasePanel().updateUI();
  }
    /**
     * Lazy construction of the PivotView
     * @return JideView
     */
    PivotView getPivotView() {
        if (m_pivotView == null) {
            final String[] header = { "Date Month" };
            final String[] row = { "Item Type", "Item Name" };
            // final String[] data = { "Qty", "Amount" };
            final String[] data = { "Amount" };
            
            m_pivotView = PivotView.newUi(getCalculatedModel(), header, row, data);
        }
        return m_pivotView;
    }


    ReportTableModel getTableModel() {
        if (m_reportTableModel == null) {
            m_reportTableModel =
                ReportTableModel.newModel(getReport());
            
        }
        return m_reportTableModel;
    }

    /**
     * Get the JIDE UI.  Default is Aggregate view
     * @return JideView
     */
  public  JideView getView() {
        if (m_jideView == null) {
            m_jideView = getAggregateView();
        }
        return m_jideView;
    }
  public   JideView getSortView() {
      if (m_jideView == null) {
          m_jideView = getSortingView();
      }
      return m_jideView;
  }
  public   SortableTable getTableSortView() {
	  SortableTable table = null;
      if (m_jideView == null) {
    	  table = getSortingView().getTable();
      }
      return table;
  }
    /**
     * Export data to excel.
     */
    void uiExport() {
        if (!HssfTableUtils.isHssfInstalled()) {
            JOptionPane.showMessageDialog(getView().getJidePanel(),
                "Export to Excel feature is disabled because "
                    + "hssf.jar is not in the classpath.");
            return;
        }

        final JFileChooser chooser = new JFileChooser() {
            @Override
            protected JDialog createDialog(final Component parent)
                    throws HeadlessException {
                final JDialog dialog = super.createDialog(parent);
                dialog.setTitle("Export the content to an \".xls\" file");
                return dialog;
            }
        };
        chooser.setCurrentDirectory(new File(m_lastDirectory));

        final int result = chooser.showDialog(getView().getJidePanel(), "Export");
        if (result == JFileChooser.APPROVE_OPTION) {
            m_lastDirectory = chooser.getCurrentDirectory().getAbsolutePath();
            getView().export(chooser.getSelectedFile().getAbsolutePath(),
                "JIDE Table", false);
        }
    }

    /**
     * Load and apply JIDE view layout.
     * TODO: Improvements could include restoring state from a repository.
     */
    void uiLoadLayout() {
        final JFileChooser chooser = new JFileChooser() {
            @Override
            protected JDialog createDialog(final Component parent)
                    throws HeadlessException {
                final JDialog dialog = super.createDialog(parent);
                dialog.setTitle("Load an \"."+m_configExtension+"\" file");
                return dialog;
            }
        };
        chooser.setCurrentDirectory(new File(m_lastDirectory));
        final int result = chooser.showDialog(getView().getJidePanel(), "Open");
        if (result == JFileChooser.APPROVE_OPTION) {
            m_lastDirectory = chooser.getCurrentDirectory().getAbsolutePath();
            getView().loadLayout(chooser.getSelectedFile().getAbsolutePath());
        }
    }

    /**
     * Save JIDE view layout.
     * TODO: Improvements could include saving state into a repository.
     */
    void uiSaveLayout() {
        final JFileChooser chooser = new JFileChooser() {
            @Override
            protected JDialog createDialog(final Component parent)
                    throws HeadlessException {
                final JDialog dialog = super.createDialog(parent);
                dialog.setTitle("Save an \""+m_configExtension+"\" file");
                return dialog;
            }
        };
        chooser.setCurrentDirectory(new File(m_lastDirectory));
        final int result = chooser.showDialog(getView().getJidePanel(), "Save");
        if (result == JFileChooser.APPROVE_OPTION) {
            m_lastDirectory = chooser.getCurrentDirectory().getAbsolutePath();
            getView().saveLayout(chooser.getSelectedFile().getAbsolutePath());
        }
    }


    /**
     * Construct a column based exit table column.  Adds a direct mapping
     * from a single existing column of a TableModel to CalculatedTableModel.
     *
     * A support method used help with consistence of additional columns.
     */
    void addCalculatedColumn(final CalculatedTableModel calculatedModel,
            final TableModel tableModel, final String baseName,
            final String appendString, final ObjectGrouper grouper,
            final ConverterContext context) {

        final String newColumnName = baseName + " " + appendString;

        final SingleColumn column =
            new SingleColumn(tableModel, baseName, newColumnName, grouper);

        column.setConverterContext(context);
        calculatedModel.addColumn(column);
    }

    Report getReport() {
        if (m_report == null) {
            // Decide on the best way to read and build the data
            // In this sample, simply read the text file from disc.
            final StringBuffer csvData = loadTextFileFromStream(
                getClass().getResourceAsStream("/jideReport/SalesDetail.txt"));

            m_report = getReport();//CsvReport.newReport(csvData.toString());
        }
        return getM_report();
    }

    /**
     * Will alway return a valid StringBuffer.  If an error occurs, the
     * buffer will be a blank string.
     *
     * @param InputStream in
     * @return StringBuffer representation of the object
     */
    public static StringBuffer loadTextFileFromStream(final InputStream in) {
        final StringBuffer buffer = new StringBuffer("");

        if (in != null) {
            final BufferedReader inReader = new BufferedReader(
                    new InputStreamReader(in));

            String line = null;
            try {
                do {
                    if(line!=null) {
                        buffer.append("\r\n");
                    }
                    line = inReader.readLine();
                  //  if( StringUtils.isNotBlank(line) ) {
                   //     buffer.append(StringUtils.chomp(line.trim()));
                   // }
                } while (line != null);

            } catch (final IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    inReader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return buffer;
    }


    JideView m_aggregateView;

    JPanel m_basePanel;

    JPanel m_controlPanel;
    JToolBar m_toolBar;
    JPanel m_sortingControlPanel;
    JFrame m_frame;

    JideView m_jideView;

    String m_lastDirectory = ".";

    PivotView m_pivotView;
    JideView m_sortView;

    boolean m_useAggregateView = true;

    private CalculatedTableModel m_calculatedModel;

    String m_configExtension;

    private Report m_report;

    private ReportTableModel m_reportTableModel;

    private static final String AGG_EXTENSION = "agg";
	public Report getM_report() {
		return m_report;
	}

	public void setM_report(Report m_report) {
		this.m_report = m_report;
	}

	public ReportTableModel getM_reportTableModel() {
		return m_reportTableModel;
	}

	public void setM_reportTableModel(ReportTableModel tableModel) {
		this.m_reportTableModel = tableModel;
	}

	public  void setReport(Report report) {
		System.out.println("Data size  ************** " + report.getData().size());
		this.report = report;
	}
}
