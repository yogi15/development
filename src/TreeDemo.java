import java.awt.*;
   import java.awt.event.*;
   import javax.swing.*;
  import javax.swing.event.*;
  import javax.swing.tree.*;

public class TreeDemo {
	
	
	public static void main(String[] args) {
		   JFrame f = new JFrame("TreeDemo");
		      f.addWindowListener(new WindowAdapter() {
		        public void windowClosing(WindowEvent e) {
		          System.exit(0);
		        }
		      });
		      TreeDemo tre = new TreeDemo(f);
		//      f.getContentPane().add(f, new TreeDemo(f));
		      f.pack();
		      f.show();
		    }
	
	 private JTree jTree = null;

	public TreeDemo(JFrame f)
	   {
	      f.setLayout(new BorderLayout());
	      
	      // create the tree component
	      MutableTreeNode root = new DefaultMutableTreeNode("Root");
	      jTree = new JTree(root);
	      
	      // create command buttons
	      JToolBar toolbar = new JToolBar(JToolBar.HORIZONTAL);
	    //  toolbar.setl
	      InsertAction ia = new InsertAction();
	      jTree.addTreeSelectionListener(ia);
	      RemoveAction ra = new RemoveAction();
	      jTree.addTreeSelectionListener(ra);
	      toolbar.add(ia).setToolTipText((String) ia.getValue(ia.SHORT_DESCRIPTION));
	      toolbar.add(ra).setToolTipText((String) ra.getValue(ia.SHORT_DESCRIPTION));
	     
	      f.add(new JScrollPane(jTree));
	     f.add(toolbar, "North");
	    }


	protected class InsertAction extends AbstractAction
	    implements TreeSelectionListener
	    {
	      InsertAction()
	      {
	        putValue(NAME, "Insert");
	        putValue(SHORT_DESCRIPTION, "Insert a new node into the tree");
	      }
	      
	      public void actionPerformed(ActionEvent e)
	      {
	        // get the selection
	        TreePath path = jTree.getSelectionPath();
	        if (path != null)
	        {
	          // query for the string to be inserted
	        String s = JOptionPane.showInputDialog(jTree, "Insert:");
	          if (s != null && !s.equals(""))
	          {
	            // create the node and add it into it's parent
	            MutableTreeNode parent = (MutableTreeNode) path.getLastPathComponent();
	            MutableTreeNode node = new DefaultMutableTreeNode(s);
	            int index = parent.getChildCount();
	            DefaultTreeModel model = (DefaultTreeModel) jTree.getModel();
	            model.insertNodeInto(node, parent, index);
	            // select the newly added node
	            TreePath newPath = path.pathByAddingChild(node);
	            jTree.clearSelection();
	            jTree.addSelectionPath(newPath);
	          }
	        }
	      }
	      public void valueChanged(TreeSelectionEvent e)
	       {
	            TreePath path = jTree.getSelectionPath();
	            setEnabled(path != null);
	          }
	        }
    
	protected class RemoveAction extends AbstractAction
	  implements TreeSelectionListener
	  {
	    RemoveAction()
	    {
	      putValue(NAME, "Remove");
	      putValue(SHORT_DESCRIPTION, "Remove selected node(s) from the tree");
	   }
	    
	    public void actionPerformed(ActionEvent e)
	    {
	      TreePath[] paths = jTree.getSelectionPaths();
	      DefaultTreeModel model = (DefaultTreeModel) jTree.getModel();
	      for (int i = 0; i < paths.length; i++)
	      {
	        MutableTreeNode node = (MutableTreeNode) paths[i].getLastPathComponent();
	        // do not remove the root node
	        if (node != model.getRoot())
	         model.removeNodeFromParent(node);
	      }
	    }
	
	    
	    public void valueChanged(TreeSelectionEvent e)
	       {
	          TreePath[] paths = jTree.getSelectionPaths();
	          boolean ena = false;
	          if (paths != null)
	          {
	            DefaultTreeModel model = (DefaultTreeModel) jTree.getModel();
	            for (int i = 0; i < paths.length; i++)
	            {
	              // one of the selected nodes should not be root
	              if (paths[i].getLastPathComponent() != model.getRoot())
	                ena = true;
	            }
	          }
	          setEnabled(ena);
	       }
	     }
	      
	    }



