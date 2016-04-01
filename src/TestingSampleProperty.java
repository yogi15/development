import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;

import org.apache.poi.hssf.record.formula.functions.Proper;
import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;

import util.RemoteServiceUtil;

import apps.window.util.propertyTable.CurrencyDefaultPropertyTable;
import apps.window.util.propertyTable.SampleTestPropertyTable;
import apps.window.util.property.SampleTestProperty;
import beans.AttributeContainer;
import beans.JTableMapping;

import com.jidesoft.grid.Property;
import com.jidesoft.grid.PropertyTable;
import com.jidesoft.grid.QuickTableFilterField;
import com.jidesoft.grid.RowStripeTableStyleProvider;
import com.jidesoft.icons.JideIconsFactory;
import com.jidesoft.plaf.LookAndFeelFactory;

import constants.CurrencyDefaultConstant;
import constants.SamplesTestContant;


//VS4E -- DO NOT REMOVE THIS LINE!
public class TestingSampleProperty extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
	private PropertyTable propertyTable = null;
	private JButton button = null;
	public TestingSampleProperty() {
		initComponents();
	}

	private void initComponents() {
		 
		setLayout(new BorderLayout());
		getJPanel0();
		//add(, new Constraints(new Leading(7, 388, 10, 10), new Leading(6, 355, 10, 10)));
		createSampleTestPropertyTables();
		add(jPanel0, BorderLayout.CENTER);
		setSize(403, 366);
		JTableMapping bb = new JTableMapping();
		bb.setObjectName("testing");
		try {
			RemoteServiceUtil.getRemoteReferenceDataService().saveJTableObject(bb);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void createSampleTestPropertyTables() {

		button = new JButton( );
		
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Property  prop = propertyTable.getPropertyTableModel().getProperty("BookAttributes");
			 AttributeContainer acc = (AttributeContainer)prop.getValue();
				 System.out.println(acc.getAttributes());
				
			}
		});
		JPanel propertyTablePanel = getSamplePropertyTablePanel();
		
		jPanel0.add( getSamplePropertyTablePanel(), BorderLayout.CENTER);
		jPanel0.add(button, BorderLayout.SOUTH);
	}
	 private JPanel getSamplePropertyTablePanel() {
			// TODO Auto-generated method stub
			 propertyTable = new SampleTestPropertyTable(SamplesTestContant.SAMPLETEST).getSampleTestPropertyTable();
				 
			 JPanel quickSearchPanel = new JPanel(new FlowLayout(FlowLayout.LEADING));
		        QuickTableFilterField filterField = new QuickTableFilterField(propertyTable.getModel());
		        filterField.setHintText("Type here to filter properties");
		        filterField.setObjectConverterManagerEnabled(true);
		        quickSearchPanel.add(filterField);
		       // quickSearchPanel.setBorder(new JideTitledBorder(new PartialEtchedBorder(PartialEtchedBorder.LOWERED, PartialSide.NORTH), "QuickTableFilterField", JideTitledBorder.LEADING, JideTitledBorder.ABOVE_TOP));

		        propertyTable.setModel(filterField.getDisplayTableModel());
		        
		      //  propertyTable.setRowHeight(10, propertyTable.getRowHeight() * 3);
		        
		        propertyTable.setPaintMarginBackground(false);
		        propertyTable.setMarginRenderer(new DefaultTableCellRenderer() {
	                @Override
	                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
	                    JLabel label = (JLabel) super.getTableCellRendererComponent(table, "", false, false, row, column);
	                    label.setHorizontalAlignment(SwingConstants.CENTER);
	                    label.setOpaque(false);
	                    if (!((Property) value).hasChildren() && isSelected) {
	                        label.setIcon(JideIconsFactory.getImageIcon(JideIconsFactory.Arrow.RIGHT));
	                    }
	                    else {
	                        label.setIcon(null);
	                    }
	                    return label;
	                }
	            });
		       /* _pane = new PropertyPane(propertyTable) {
		            @Override
		            protected JComponent createToolBarComponent() {
		                CommandBar toolBar = new CommandBar();
		                toolBar.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));
		                toolBar.setFloatable(false);
		                toolBar.setStretch(true);
		                toolBar.setPaintBackground(false);
		                toolBar.setChevronAlwaysVisible(false);
		                return toolBar;
		            }
		        };*/
		      
		        propertyTable.setTableStyleProvider(new RowStripeTableStyleProvider());
		JPanel basePanel = new JPanel();
		JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.getViewport().add(propertyTable);
		basePanel.setLayout(new BorderLayout());
		 
		basePanel.add(quickSearchPanel, BorderLayout.BEFORE_FIRST_LINE);

	   // _pane.setBorder(new JideTitledBorder(new PartialEtchedBorder(PartialEtchedBorder.LOWERED, PartialSide.NORTH), "PropertyPane", JideTitledBorder.LEADING, JideTitledBorder.ABOVE_TOP));
	    basePanel.add(tableScrollPane, BorderLayout.CENTER);

		return basePanel;
		}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, null, null));
			jPanel0.setLayout(new BorderLayout());
		}
		return jPanel0;
	}

	 

	/**
	 * Main entry of the class.
	 * Note: This class is only created so that you can easily preview the result at runtime.
	 * It is not expected to be managed by the designer.
	 * You can modify it as you like.
	 */
	public static void main(String[] args) {
		 
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				   LookAndFeelFactory.installDefaultLookAndFeelAndExtension();
				TestingSampleProperty frame = new TestingSampleProperty();
				frame.setDefaultCloseOperation(TestingSampleProperty.EXIT_ON_CLOSE);
				frame.setTitle("TestingSampleProperty");
				frame.getContentPane().setPreferredSize(frame.getSize());
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
		});
	}

}
