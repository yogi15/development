

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.EventListenerList;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreePath;

import com.calypso.apps.util.JTreeTable;
import com.calypso.apps.util.TreeTableModel;
import com.calypso.tk.core.Log;
import com.jidesoft.grid.JideTable;


public class SampleJTreeTable implements TreeTableModel
{
    Node   _root;
    int    _columnCount;
    Vector _columnNames   = new Vector();
    Vector _columnClasses = new Vector();
    
    public SampleJTreeTable() {
	
	_columnCount = 5;
	_columnNames.add("c1 (Boolean)");
	_columnNames.add("c2 (String)");
	_columnNames.add("c3 (Double)");
	_columnNames.add("c4 (Button)");
	_columnNames.add("c5 (Choice)");
	
	_columnClasses.add(Boolean.class);
	_columnClasses.add(String.class);
	_columnClasses.add(Double.class);
	_columnClasses.add(JComponent.class);
	_columnClasses.add(JComponent.class);

	_root = new Node("Root");
	_root.getValues().add(null);
	_root.getValues().add(null);
	_root.getValues().add(null);
	_root.getValues().add(null);
	_root.getValues().add(null);

	Node node1 = new Node("Node1");
	Node node2 = new Node("Node2");
	Node node3 = new Node("Node3");

	Vector choices = new Vector();
	choices.add("b1");
	choices.add("b2");
	choices.add("b3");

	node1.getValues().add(Boolean.TRUE);
	node1.getValues().add("data1");
	node1.getValues().add(new Double(1.1));
	node1.getValues().add(new SetButton("b1"));
	node1.getValues().add(new SetChoice("b1", choices));

	node2.getValues().add(Boolean.FALSE);
	node2.getValues().add("data2");
	node2.getValues().add(new Double(2.1));
	node2.getValues().add(new SetButton("b2"));
	node2.getValues().add(new SetChoice("b2", choices));

	node3.getValues().add(Boolean.TRUE);
	node3.getValues().add("data3");
	node3.getValues().add(new Double(3.2));
	node3.getValues().add(new SetButton("b3"));
	node3.getValues().add(new SetChoice("b3", choices));

	// Hierarchy
	node1.getChildren().add(node3);
	_root.getChildren().add(node1);
	_root.getChildren().add(node2);
    }

    public Hashtable getIconTable() { return null; }
    
    static class SetButton extends JButton
    {
	static Font AFONT = new Font("Dialog", Font.BOLD, 11);
	public SetButton(final String label) {
	    super(label);
	    addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
			Log.debug("calypsox", "Button action: "+label);
		    }
		});
	    setBorder(BorderFactory.createEtchedBorder());
	    setFont(AFONT);
	}
    }

    static class SetChoice extends JComboBox
    {
	static Font AFONT = new Font("Dialog", Font.BOLD, 11);
	public SetChoice(final String label, Vector items) {
	    super(items);
	    setSelectedItem(label);
	    addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent event) {
			Log.debug("calypsox", "ComboBox action: "+
					   getSelectedItem());
		    }
		});
	    setBorder(new EmptyBorder(0,0,0,0)); 
	    setFont(AFONT);
	}
    }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    class Node {
	public String _name;
	public Vector _values   = new Vector();
	public Vector _children = new Vector();
	public Node(String name) { _name = name; }

	public String toString()    { return _name; }
	public Vector getValues()   { return _values; }
	public Vector getChildren() { return _children; }
    }

    
    protected Vector getChildren(Object node) {
	return ((Node)node).getChildren();
    }

    protected String getName(Object node) {
	return node.toString();
    }

    //
    // The TreeModel interface
    //

    public int getChildCount(Object node) { 
	Vector children = getChildren(node); 
	return (children == null) ? 0 : children.size();
    }
    
    public Object getChild(Object node, int i) { 
	return getChildren(node).elementAt(i); 
    }
    
    public boolean isLeaf(Object node) { 
	Vector children = getChildren(node);
	if (children.size() == 0) return true;
	return false;
    }
    
    public boolean isReadOnly(Object node) { 
	return true;
    }
    
    //
    //  The TreeTableNode interface. 
    //
    
    public int getColumnCount() {
	return _columnCount+1;
    }
    
    public String getColumnName(int column) {
	if (column == 0) return "TREE";
	return (String)_columnNames.get(column-1);
    }
    
    public Class getColumnClass(int column) {
	if (column == 0) return TreeTableModel.class;
	return (Class)_columnClasses.get(column-1);
    }
    
    public Object getValueAt(Object node, int column) {
	Node n = (Node)node;
	column--;
	return n.getValues().get(column);
    }
    
    public int getRowCount() { 
	Log.debug("calypsox", "getRowCount must be redifined."); 
	return 0;
    }
    
    public Object getValueAt(int row, int column) { 
	Log.debug("calypsox", "getValueAt must be redifined."); 
	return null; 
    }
    
    public Color getRowColor(Object node) { 
	return null;
    }

    public Color getFgRowColor(Object node) { 
	return null;
    }

    public Font getRowFont(Object node) { 
	return null;
    }
    
    public void dblClicked(Object node, int row, int column) {
    }

    public void newValueAt(Object node, int row, int column, Object value) {
	//Log.debug("calypsox", "newValueAt=> row="+row+
		//	   ", column="+column+ 
			//   ", node="+node+
			  // ", value="+value);
    }
    public void newSelection(Object node,int row) {} 


    public Object getRoot() {
        return _root;
    }
    
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    protected EventListenerList _listenerList = new EventListenerList();
    
    public void valueForPathChanged(TreePath path, Object newValue) {}
    public int getIndexOfChild(Object parent, Object child) {
        for (int i = 0; i < getChildCount(parent); i++) {
	    if (getChild(parent, i).equals(child)) { 
	        return i; 
	    }
        }
	return -1; 
    }
    public void addTreeModelListener(TreeModelListener l) {
        _listenerList.add(TreeModelListener.class, l);
    }
    
    public void removeTreeModelListener(TreeModelListener l) {
        _listenerList.remove(TreeModelListener.class, l);
    }
    
    protected void fireTreeNodesChanged(Object source, Object[] path, 
                                        int[] childIndices, 
                                        Object[] children) {
        Object[] listeners = _listenerList.getListenerList();
        TreeModelEvent e = null;
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TreeModelListener.class) {
                if (e == null)
                    e = new TreeModelEvent(source, path, 
                                           childIndices, children);
                ((TreeModelListener)listeners[i+1]).treeNodesChanged(e);
            }          
        }
    }

    protected void fireTreeNodesInserted(Object source, Object[] path, 
					 int[] childIndices, 
					 Object[] children) {
        Object[] listeners = _listenerList.getListenerList();
        TreeModelEvent e = null;
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TreeModelListener.class) {
                if (e == null)
                    e = new TreeModelEvent(source, path, 
                                           childIndices, children);
                ((TreeModelListener)listeners[i+1]).treeNodesInserted(e);
            }          
        }
    }
    
    protected void fireTreeNodesRemoved(Object source, Object[] path, 
                                        int[] childIndices, 
                                        Object[] children) {
        Object[] listeners = _listenerList.getListenerList();
        TreeModelEvent e = null;
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TreeModelListener.class) {
                if (e == null)
                    e = new TreeModelEvent(source, path, 
                                           childIndices, children);
                ((TreeModelListener)listeners[i+1]).treeNodesRemoved(e);
            }          
        }
    }
    
    public void fireTreeStructureChanged(Object source, Object[] path, 
					 int[] childIndices, 
					 Object[] children) {
        Object[] listeners = _listenerList.getListenerList();
        TreeModelEvent e = null;
        for (int i = listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==TreeModelListener.class) {
                if (e == null)
                    e = new TreeModelEvent(source, path, 
                                           childIndices, children);
                ((TreeModelListener)listeners[i+1]).treeStructureChanged(e);
            }          
        }
    }


    public boolean isCellEditable(Object node, int column) { 
	if (getColumnClass(column) == TreeTableModel.class) return true;
	if (node == _root) return false;
	return true;
    }
    
    public void setValueAt(Object aValue, Object node, int column) {
	if (column == 0) return; // tree node
	Node n = (Node)node;
	n.getValues().setElementAt(aValue, column-1);
    }


    public Color getColumnColor(int row) { return null; }
    public Color getFgCellColor(Object o, int row) { return null; }
    public Color getZebraColor() { return null; }
    public int getColumnAlign(int column) { return SwingConstants.LEFT; }
    // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    // Main
    public static void main(String args[]) 
    {
	JFrame frame = new JFrame("Test JTreeTable");
	JTreeTable table = new JideTable(new SampleJTreeTable());
	JScrollPane scrollPane = new JScrollPane(table);
	JButton button = new JButton("Close");
	button.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    System.exit(0); }});
	frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
	frame.getContentPane().add(button,     BorderLayout.SOUTH);
	frame.pack();
	frame.setVisible(true);
    }

    /* (non-Javadoc)
     * @see com.calypso.apps.util.TreeTableModel#mouseClicked(java.lang.Object, int, int)
     */
    public void mouseClicked(Object node, int row, int column) { }
    public void sortByColumn(int columns[], boolean ascending[], Vector pathList) {
    }
}

public class Snippet {
	public static void main(String[] args) {
		testing.java
	}
}

    
