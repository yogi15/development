 
 
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
 
import javax.swing.AbstractButton;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
  
  
 
 
 
/**
 * @author prajna
 * 
 * Here three string arrays are used to fetch the data from three 
 * text files and displayed in a table.One textfile contains the field labels and other 
 * two contains the values for that field labels.
 * Comparison is done with the two text files if it is same 
 * it will display as same and if different will display as different.
 *
 */
public class ASCIIComparisonFiles extends JDialog implements ActionListener,MouseListener,FocusListener,ItemListener{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTable table;
    private DefaultTableModel model;
    private String arrayName[],arrayName2[],arrayName3[];
    private BufferedReader bfr, bfr1,bfr2;
    private JPanel mainPanel;
    private FormLayout form3,form;
    private CellConstraints c;
    private JFrame f ;
    private JScrollPane pane1;
    private JButton btnUpdate,btnReset,btnClose;
    private JTextField txtred,txtblue;
    private JLabel lblred,lblblue;
    int count = 0;
    Object data[][] = {{"","","",""}};
    String col []   = {"Parameter Name","New-Value","Old-Value","Stored"};
    String arrayValue;
    JTextField textBox=new JTextField();
    MainClass rendererComponent = new MainClass();
    boolean checkboxSelected;
    int rowindex;
     
     
 
     
    /**
     * 
     * @throws IOException
     */
    public ASCIIComparisonFiles() throws IOException{
                 
        f               =   new JFrame("Ascii Comparison");
        form3           =   new FormLayout( "300dlu:grow, pref,0dlu, pref", // columns 
                                            "500dlu:grow, pref,5dlu:grow, pref, 5dlu:grow, pref,0dlu"); //rows
        form = new  FormLayout( "10dlu, pref, 10dlu, pref, 10dlu", // columns 
                "5dlu,pref, 5dlu, pref, 5dlu"); //rows
        c               =   new CellConstraints();
        bfr             =   new BufferedReader(new FileReader("/root/Desktop/inputFields/input parameter list.txt"));
        bfr1            =   new BufferedReader(new FileReader("/root/Desktop/inputFields/File1-input.txt"));
        bfr2            =   new BufferedReader(new FileReader("/root/Desktop/inputFields/File2-input.txt"));
        arrayName       =   new String[47];
        arrayName2      =   new String[47];
        arrayName3      =   new String[47];
         
        mainPanel       =   new JPanel(form3);
        mainPanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
         
        JPanel panel    =   new JPanel();
        panel.setLayout(new FlowLayout());
         
        TitledBorder border = new TitledBorder(null, "Missile Simulator Input File Comparison",TitledBorder.CENTER, 0);
        border.setTitleFont( border.getTitleFont().deriveFont(Font.BOLD + Font.ITALIC) );
     
        panel.setBorder(border);
        model           =   new DefaultTableModel(data,col);/*{
            public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };*/
                 
        table           =   new JTable(model){
             
            private static final long serialVersionUID = 1L;
 
            public Class<?> getColumnClass(int column) {
                    switch (column) {
                        case 0:
                            return String.class;
                        case 1:
                            return String.class;
                        case 2:
                            return String.class;
                        case 3:
                            return String.class;
                        default:
                            return Boolean.class;
                    }
                }
            };
         
        Dimension dim   =   new Dimension(20,1);
        table.setIntercellSpacing(new Dimension(dim));
         
        SetRowHight(table);
        table.setShowGrid(true); 
        table.setShowVerticalLines(true);
        table.setGridColor(Color.LIGHT_GRAY);
        table.setSelectionBackground(Color.blue);
        //table.setColumnSelectionAllowed(true);
        //table.setRowSelectionAllowed(true);
        JTableHeader header1 = table.getTableHeader();
        table.getColumnModel().getColumn(0).setMaxWidth(250);
        table.getColumnModel().getColumn(0).setResizable(false);
        table.getTableHeader().setReorderingAllowed(false);
 
        table.getColumnModel().getColumn(1).setMaxWidth(150);
        table.getColumnModel().getColumn(1).setResizable(false);
         
        table.getColumnModel().getColumn(2).setMaxWidth(150);
        table.getColumnModel().getColumn(2).setResizable(false);
         
        table.getColumnModel().getColumn(3).setMaxWidth(230);
        table.getColumnModel().getColumn(3).setResizable(false);
         
         
         
        header1.setBackground(Color.LIGHT_GRAY);
        pane1       =   new JScrollPane(table);
        pane1.setPreferredSize(new Dimension(730,770));
        panel.add(pane1);
         
        JPanel btnpanel = new JPanel();
        btnpanel.setLayout(new FlowLayout());
        btnpanel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null)); 
          
        btnUpdate = new JButton("Update");
//      btnUpdate.setEnabled(false);
        btnReset = new JButton("Reset");
        btnClose = new JButton("Close");
         
        btnpanel.add(btnUpdate);
        btnpanel.add(btnReset);
        btnpanel.add(btnClose);
         
        //Action Listeners
        btnUpdate.addActionListener(this);
        btnReset.addActionListener(this);
        btnClose.addActionListener(this);
        table.addMouseListener(this);
        table.addFocusListener(this);
         
//      rendererComponent.addActionListener(this);
         
//      rendererComponent.addItemListener(this);
         
        JPanel txtpanel = new JPanel(form);
        txtred = new JTextField();
        txtred.setBackground(Color.red);
        txtblue = new JTextField();
        txtblue.setBackground(Color.blue);
        lblred = new JLabel("Red Indicates the new value");
        lblblue = new JLabel("Blue indicates the old value to be adopted");
     
        txtpanel.add(txtred,c.xy(1, 1));
        txtpanel.add(lblred,c.xy(2, 1));
        txtpanel.add(txtblue,c.xy(1, 2));
        txtpanel.add(lblblue,c.xy(2, 2));
         
         
        mainPanel.add(panel,c.xy(1, 1));
        mainPanel.add(btnpanel,c.xy(1, 2));
        mainPanel.add(txtpanel,c.xy(1, 3));
         
        add(mainPanel);
        setTitle("Missile Simulator");
        setVisible(true);   
        setPreferredSize(new Dimension(770, 930));
        pack();
        setLocationRelativeTo(f);
                    
        for(int i = 0; i < 47; i++){
            count++;
            model.setRowCount(count);
            arrayName[i]    =   bfr.readLine();
            arrayName2[i]   =   bfr1.readLine();
            arrayName3[i]   =   bfr2.readLine();
             
            model.setValueAt(arrayName[i], count-1, 0);
            model.setValueAt(arrayName2[i], count-1, 1);
            model.setValueAt(arrayName3[i], count-1, 2);
 
             
            if (arrayName2[i].equals(arrayName3[i])) { 
                model.setValueAt("", count-1, 3);
             
               } 
             
            else {
             
                setArrayValue(arrayName2[i]);
                setRowindex(count-1);
                 
                System.out.println("arrayName2[i]"+arrayName2[i]);
                System.out.println("count-1"+ (count-1));
                 
               ChangeColor renderer = new ChangeColor("Stored");  
//             model.setValueAt("different", count-1, 3);
               for ( int m = 0; m < table.getColumnCount(); m++)
                   table.getColumn(table.getColumnName(m)).setCellRenderer(renderer);
               
              model.setValueAt("different", count-1, 3);
               
              
 
                table.getColumnModel().getColumn(3)
                .setCellRenderer(new CheckBoxRenderer());
                 
                 table.getColumnModel().getColumn(3)
                    .setCellEditor(new CheckBoxEditor(new JCheckBox()));
         
           }
             
        }
         
//      TableColumn includeColumn = table.getColumnModel().getColumn(3);
//        includeColumn.setCellEditor(new DefaultCellEditor(new JCheckBox()));
//        
//        TableColumn soprtColumn = table.getColumnModel().getColumn(2);
//
//      soprtColumn.setCellEditor(new DefaultCellEditor (textBox));
         
        }
     
    private void SetRowHight(JTable table2) {
         
              int height = table.getRowHeight();
              table.setRowHeight(height+10);
    }
     
     
     
    /**
     * @author root
     *
     */
    class ChangeColor extends JLabel implements TableCellRenderer {
        private static final long serialVersionUID = 1L;
        private String columnName;
        /**
         * @param column: Returns The name of the column
         */
        public ChangeColor(String column) {
                this.columnName = column;
                setOpaque(true);
        }
 
        public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean selected, boolean hasFocus, int row, int column) {
             
            Object columnValue = table.getValueAt(row, table.getColumnModel()
                            .getColumnIndex(columnName));
            int row1 = row;
 
            if (value != null)
                    setText(value.toString());
            setBackground(table.getBackground());
            setForeground(table.getForeground());
 
                if (columnValue.equals("different")){
                     if (column == 2){
                  setForeground(Color.GREEN);
                   
//                setToolTipText("Accept this Value");
                     }
                      
                     if(column==3){
//                       JCheckBox rendererComponent = new JCheckBox("Select the old value");
//                  rendererComponent.
//                  rendererComponent.doClick();
//                  rendererComponent.addActionListener(new ActionListener() {
//                      
//                      @Override
//                      public void actionPerformed(ActionEvent arg0) {
//                          System.out.println(" fsgjkgsakdjfghk");
//                          
//                      }
//                  });
                     
                             
                     if (isCheckboxSelected()) {                  
                          
 
                     if (column == 2 && row == row1) {
                            setForeground(Color.orange);
                        }
                     }
                      else {
                                 if (column == 2 && row == row1) {
                                    setForeground(Color.blue);
                                }
                             }
                      
                      
//                   return rendererComponent.checkBox1;
                     }
                     }
 
            return this;
        }
    }
 
    public static void main(String[] args) throws java.io.IOException {
//     try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//          } catch(Exception e) {
//            System.out.println("Error setting native LAF: " + e);
//          }
        setUIFont (new javax.swing.plaf.FontUIResource(new Font("MS Mincho",Font.PLAIN, 12)));
        new ASCIIComparisonFiles();
    }
     
 
    private static void setUIFont(javax.swing.plaf.FontUIResource f)
    {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements())
        {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource)
            {
                UIManager.put(key, f);
            }
        }
    }
     
     
     
     
    public int getRowindex() {
        return rowindex;
    }
 
    public void setRowindex(int rowindex) {
        this.rowindex = rowindex;
    }
 
     
    public String getArrayValue() {
        return arrayValue;
    }
 
    public void setArrayValue(String arrayValue) {
        this.arrayValue = arrayValue;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
//      if(e.getSource().equals(rendererComponent)){
//          
//          if(rendererComponent.isSelected()){
//              setCheckboxSelected(true);
//          }
//          else{
//              setCheckboxSelected(false);
//          }
//      }
        /**
         * If a check box is pressed left panel check tree is selected
         */
        if (e.getSource().getClass().getName().equals("javax.swing.JCheckBox")){
             
            System.out.println("sdhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
                 
        }
        if(e.getSource().equals(btnUpdate)){
             
            String m = model.getValueAt(getRowindex(), 1).toString();
            model.setValueAt(m, getRowindex(), 2);
             
             
        }
    }
    public boolean isCheckboxSelected() {
        return checkboxSelected;
    }
 
    public void setCheckboxSelected(boolean checkboxSelected) {
        this.checkboxSelected = checkboxSelected;
    }
 
    public void mouseClicked(MouseEvent e) {
         
        if(table.getColumnModel().getColumn(3).equals("different")){
         if(isCheckboxSelected()){
             btnUpdate.setEnabled(true);
         
        textBox.setForeground(Color.cyan);
//      textBox.setToolTipText("Reject this Value");
//      model.setValueAt("dfd", rowIndex, 2);
         
         }
         else{
             btnUpdate.setEnabled(true);
         }
         }
    }
 
    public void mouseEntered(MouseEvent e){}
 
    public void mouseExited(MouseEvent e) {}
 
    public void mousePressed(MouseEvent e) {}
 
    public void mouseReleased(MouseEvent e) {}
 
    public void focusGained(FocusEvent arg0) {}
 
    public void focusLost(FocusEvent arg0) {}
 
    public void itemStateChanged(ItemEvent e) {
 
//              AbstractButton abstractButton = (AbstractButton)e.getSource();
//              Color foreground = abstractButton.getForeground();
//              Color background = abstractButton.getBackground();
//              int state = e.getStateChange();
//              if (state == ItemEvent.SELECTED) {
//                abstractButton.setForeground(background);
//                abstractButton.setBackground(foreground);
//              }
//            }
           
//      if(e.getSource().equals(rendererComponent)){
//          if(rendererComponent.isSelected()){
//              btnUpdate.setEnabled(true);
//              setCheckboxSelected(true);
//          }
//          else{
//              setCheckboxSelected(false);
//          }
//      }
         
    }
     
    class CheckBoxRenderer  implements TableCellRenderer {
 
        private static final long serialVersionUID = 1L;
        protected Border columnBorder;
        private final Color SelectedChboxTableBGColor1 = new Color(255, 255, 240);
         
        //used in the first column of the PCF Selection Window Table to display the border and its color.
        public CheckBoxRenderer() {
            //columnBorder = new LineBorder(PcfConstants.bordercolor);
            columnBorder = new LineBorder(SelectedChboxTableBGColor1);
        }
 
        public Component getTableCellRendererComponent(JTable table, Object value,
                boolean isSelected, boolean hasFocus, int row, int column) {
 
             JCheckBox rendererComponent = new JCheckBox("Select the old value");
            if (value instanceof JCheckBox) {        
                rendererComponent = (JCheckBox) value;
            }
            //chkBox.setBackground(table.getBackground());
//          chkBox.setBackground(Color.DARK_GRAY);
//          chkBox.setBorder(columnBorder);
            if (value == null)
                return null;
             
             
              
            return rendererComponent;
 
             
        }
    }
     
    class CheckBoxEditor extends DefaultCellEditor implements ItemListener {
        private static final long serialVersionUID = 1L;
        private JCheckBox button;
        protected Border columnBorder;
        private JCheckBox rendererComponent;
 
        public CheckBoxEditor(JCheckBox checkBox) {
            super(checkBox);
        }
 
         
        public Component getTableCellEditorComponent(JTable table, Object value,
                boolean isSelected, int row, int column) {
            value = new JCheckBox();
             
              rendererComponent = new JCheckBox("Select the old value");
            if (value == null){
                return null;
            }
            rendererComponent = (JCheckBox) value;
            rendererComponent.addItemListener(this);
             
            return rendererComponent;
        }
 
        public Object getCellEditorValue() {
            rendererComponent.removeItemListener(this);
            return rendererComponent;
        }
 
        public void itemStateChanged(ItemEvent e) {
             
            super.fireEditingStopped();
 
                
        }
    }
     
     
}