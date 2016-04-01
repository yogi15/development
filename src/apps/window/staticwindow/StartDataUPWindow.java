package apps.window.staticwindow;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.text.Position;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import net.sf.cglib.transform.impl.AddStaticInitTransformer;

import org.dyno.visual.swing.layouts.Constraints;
import org.dyno.visual.swing.layouts.GroupLayout;
import org.dyno.visual.swing.layouts.Leading;
import org.dyno.visual.swing.layouts.Trailing;

import beans.StartUPData;

import util.commonUTIL;

import dsServices.RemoteReferenceData;
import dsServices.ServerConnectionUtil;

//VS4E -- DO NOT REMOVE THIS LINE!
public class StartDataUPWindow extends BasePanel {
	ActionMap actions = null;
	public static ServerConnectionUtil de = null;
	DefaultMutableTreeNode root;
	DefaultMutableTreeNode parent, leaf;
	private static final long serialVersionUID = 1L;
	private JPanel jPanel0;
	private JLabel nameLabel;
	private JLabel valueLabel;
	private JLabel descLabel;
	private JTextField nameTextField;
	private JTextField valueTextField;
	private JTextField descTextField;
	private JPanel jPanel1;
	private JTree jTree0;
	private JScrollPane jScrollPane0;
	private JButton removeButton;
	private JButton saveButton;
	private JButton addButton;
	public static DefaultMutableTreeNode nNode;
	public static MutableTreeNode mNode;
    Hashtable<String,String> treeData = new Hashtable<String,String>();
	DefaultTreeModel _treeModel;
	DefaultMutableTreeNode _root;
	DefaultMutableTreeNode _selectedName = null;
	DefaultMutableTreeNode _selectedValue = null;
	boolean refereshTree = false;
	private static final String PREFERRED_LOOK_AND_FEEL = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

	Vector initialDataVec = null;
	Vector initialDataName = new Vector(); ;
	
	public StartDataUPWindow() {

		init();
		initComponents();
	}

	private void initComponents() {

		setLayout(new GroupLayout());
		add(getJPanel1(), new Constraints(new Trailing(12, 833, 12, 12),
				new Trailing(12, 90, 414, 414)));
		add(getJPanel0(), new Constraints(new Leading(5, 835, 10, 10),
				new Leading(12, 409, 10, 10)));
		setSize(876, 532);

	}

	private JButton getAddButton() {
		if (addButton == null) {
			addButton = new JButton();
			addButton.setText("ADD");
		}
		addButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {

				if (validateData()) {

					String value = valueTextField.getText();
					String name = nameTextField.getText();
					StartUPData data = new StartUPData();
					data.setName(name);
					data.setValue(value);
					data.setCommts(descTextField.getText());
					try {
						if(checkDuplicateValue(name,value)) {
							commonUTIL.showAlertMessage("Duplication Value not allowed");
							return;
						}
					 remoteBORef.saveStartUPData(data);
						if (name.equalsIgnoreCase("InitialData")) {
							refereshTree = true;
							buildTree();
							refereshTree = false;
							// root.add(new DefaultMutableTreeNode("ppp"));
							nameTextField.setText("");
							valueTextField.setText("");
						}

					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					DefaultMutableTreeNode node = new DefaultMutableTreeNode(
							value);
					System.out.println(_selectedName);
					_treeModel.insertNodeInto(node, _selectedName, 0);
					jTree0.setSelectionPath(new TreePath(node.getPath()));

				}
			}
		});

		return addButton;

	}

	private JButton getSaveButton() {
		if (saveButton == null) {
			saveButton = new JButton();
			saveButton.setText("SAVE");
		}
		return saveButton;
	}

	private JButton getRemoveButton() {
		if (removeButton == null) {
			removeButton = new JButton();
			removeButton.setText("REMOVE");
		}
		removeButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

			
					TreePath path = jTree0.getNextMatch(
							valueTextField.getText(), 0, Position.Bias.Forward);

					mNode = (MutableTreeNode) path.getLastPathComponent();
					_treeModel.removeNodeFromParent(mNode);

					JOptionPane.showMessageDialog(null,
							"Node are deleted from tree!");

					StartUPData data1 = new StartUPData();
					data1.setName(nameTextField.getText());
					data1.setValue(valueTextField.getText());

					try {

						remoteBORef.removeStartUPData(data1);

					} catch (RemoteException e1) {

						e1.printStackTrace();

					}

				

			}

		});

		return removeButton;
	}

	private JPanel getJPanel0() {
		if (jPanel0 == null) {
			jPanel0 = new JPanel();
			jPanel0.setBorder(BorderFactory.createTitledBorder(null,
					"StartUPData", TitledBorder.LEADING,
					TitledBorder.DEFAULT_POSITION, new Font("Tahoma",
							Font.PLAIN, 11), Color.black));
			jPanel0.setLayout(new GroupLayout());
			jPanel0.add(getJScrollPane0(), new Constraints(new Leading(11, 800,
					10, 10), new Leading(0, 376, 10, 10)));
		}
		return jPanel0;
	}

	private JScrollPane getJScrollPane0() {
		if (jScrollPane0 == null) {
			jScrollPane0 = new JScrollPane();
			jScrollPane0.setViewportView(getJTree0());
		}
		return jScrollPane0;
	}

	private JTree getJTree0() {
		if (jTree0 == null) {
			jTree0 = new JTree();
		}
		buildTree();
		jTree0.addTreeSelectionListener(new TreeSelectionListener() {

			@Override
			public void valueChanged(TreeSelectionEvent e) {
				// TODO Auto-generated method stub
				if (!refereshTree) {
					_selectedName = null;
					_selectedValue = null;
					TreePath selPath = e.getNewLeadSelectionPath();
					// System.out.println( selPath.getPathCount());
					if (selPath == null)
						return;
					String name = "";
					String value = "";

					if (selPath.getPathCount() >= 2) {
						_selectedName = (DefaultMutableTreeNode) selPath
								.getPathComponent(1);
						name = _selectedName.toString();
						if (selPath.getPathCount() >= 3) {
							_selectedValue = (DefaultMutableTreeNode) selPath
									.getPathComponent(2);
							value = _selectedValue.toString();
						}
					}

					nameTextField.setText(name);
					valueTextField.setText(value);
				}

			}
		});
		return jTree0;
	}

	private JPanel getJPanel1() {
		if (jPanel1 == null) {
			jPanel1 = new JPanel();
			jPanel1.setLayout(new GroupLayout());
			jPanel1.add(getNameLabel(), new Constraints(
					new Leading(12, 12, 12), new Leading(12, 12, 12)));
			jPanel1.add(getValueLabel(), new Constraints(new Leading(245, 12,
					12), new Leading(16, 12, 12)));
			jPanel1.add(getDescLabel(), new Constraints(new Leading(520, 32,
					12, 12), new Leading(16, 12, 12)));
			jPanel1.add(getNameTextField(), new Constraints(new Leading(57,
					170, 10, 10), new Leading(12, 24, 12, 12)));
			jPanel1.add(getValueTextField(), new Constraints(new Leading(293,
					215, 10, 10), new Leading(12, 24, 12, 12)));
			jPanel1.add(getDescTextField(), new Constraints(new Leading(585,
					215, 10, 10), new Leading(12, 24, 10, 10)));
			jPanel1.add(getAddButton(), new Constraints(
					new Leading(12, 12, 12), new Leading(50, 12, 12)));
			jPanel1.add(getSaveButton(), new Constraints(
					new Leading(82, 12, 12), new Leading(50, 12, 12)));
			jPanel1.add(getRemoveButton(), new Constraints(new Leading(155, 10,
					10), new Leading(50, 12, 12)));
			jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

		}
		return jPanel1;
	}

	private JTextField getDescTextField() {
		if (descTextField == null) {
			descTextField = new JTextField();
			descTextField.setText("descTextField");
		}
		return descTextField;
	}

	private JTextField getValueTextField() {
		if (valueTextField == null) {
			valueTextField = new JTextField();
			// valueTextField.setText("valueTextField");
		}
		return valueTextField;
	}

	private JTextField getNameTextField() {
		if (nameTextField == null) {
			nameTextField = new JTextField();
			// nameTextField.setText("nameTextField");
		}
		return nameTextField;
	}

	private JLabel getDescLabel() {
		if (descLabel == null) {
			descLabel = new JLabel();
			descLabel.setText("Desc");
		}
		return descLabel;
	}

	private JLabel getValueLabel() {
		if (valueLabel == null) {
			valueLabel = new JLabel();
			valueLabel.setText("Value");
		}
		return valueLabel;
	}

	private JLabel getNameLabel() {
		if (nameLabel == null) {
			nameLabel = new JLabel();
			nameLabel.setText("Name");
		}
		return nameLabel;
	}

	private void buildTree() {
        
		StartUPData data1 = new StartUPData();
		data1.setName("InitialData");

		try {

			initialDataVec.add(data1);
			// v =
			// util.SortShell.sort(v,ComparatorFactory.getNonSenseStringComparator());
			
			int size = initialDataVec.size();
			
			root = new DefaultMutableTreeNode("InitialData: " + size);

			for (int i = 0; i < size; i++) {

				StartUPData data = (StartUPData) initialDataVec.elementAt(i);
				String domain = data.getName();
				parent = new DefaultMutableTreeNode(domain);
				root.add(parent);
				treeData.put("InitialData"+"_"+domain, domain);
				Vector values = (Vector) remoteBORef.selectStartUPData(data);
				if (values == null) {
					continue;
				}
				for (int j = 0; j < values.size(); j++) {
					leaf = new DefaultMutableTreeNode(
							((StartUPData) values.elementAt(j)).getValue());
				
					parent.add(leaf);
					treeData.put(domain+"_"+leaf.toString(), leaf.toString());
				}
			}
			_root = root;
			_treeModel = new DefaultTreeModel(root);
			jTree0.setModel(_treeModel);
			nameTextField.setText("");
			valueTextField.setText("");
			descTextField.setText("");

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean validateData() {

		boolean flag = true;

		String name = nameTextField.getText();
		String value = valueTextField.getText();
		
		  if ( name.length() == 0 ) {
		  
			  commonUTIL.showAlertMessage("Please enter Name"); 
			  
			  flag = false;
		  
		  } else if (!initialDataName.contains(name) && !name.equals("InitialData")) {
			  
			  commonUTIL.showAlertMessage("'" + name + "' Name missing in InitialData. Please save '" + name + "' in InitialData first"); 
			  flag = false;			  
			  
		  }  else if (initialDataName.contains(value)) {
			  
			  commonUTIL.showAlertMessage("'" + value + "' already exists in InitialData"); 
			  flag = false;			  
			  
		  } else if ( value.length() == 0 ) {
		  
			  commonUTIL.showAlertMessage("Please enter Value"); 
			  flag = false;
		  
		  } 
		 

		return flag;

	}

	public void init() {

		de = ServerConnectionUtil.connect("localhost", 1099, commonUTIL.getServerIP());

		try {
			remoteBORef = (RemoteReferenceData) de
					.getRMIService("ReferenceData");
			
			initialDataVec = (Vector) remoteBORef.getStartUPDataName();

			StartUPData val = null;
			
			int size = initialDataVec.size();
			
			for ( int i = 0; i < size; i++) {
				
				val = (StartUPData)initialDataVec.get(i);
				initialDataName.add(val.getName());
				
			}
			//System.out.println(remoteBORef);
		
		} catch (RemoteException e) {
		
			e.printStackTrace();
		
		}
	}
	
	private boolean checkDuplicateValue(String name,String value) {
		Enumeration<DefaultMutableTreeNode> en = root.children();
		boolean flag = false;
		while(en.hasMoreElements()) {
			DefaultMutableTreeNode dd = en.nextElement();
			System.out.println(dd.toString());
			if(dd.toString().equalsIgnoreCase(name)) {
				if(dd.getChildCount() > 0) {
					Enumeration<DefaultMutableTreeNode> childs = dd.children();
					while(childs.hasMoreElements()) {
						DefaultMutableTreeNode ddc = childs.nextElement();
						if(ddc.toString().equalsIgnoreCase(value)) {
							flag = true;
							break;
						}
					}
				}
			}
			if(flag) {
				break;
			}
		}
		return flag;
	}

	RemoteReferenceData remoteBORef;

	@Override
	public ActionMap getHotKeysActionMapper() {
		// TODO Auto-generated method stub
		return actions;
	}

	@Override
	public JPanel getHotKeysPanel() {
		// TODO Auto-generated method stub
		return jPanel1;
	}

	@Override
	public ArrayList<Component> getFocusOrderList() {
		// TODO Auto-generated method stub
ArrayList<Component> list = new ArrayList<Component>();
list.add(jTree0);
list.add(nameTextField);
list.add(valueTextField);
		list.add(addButton);
		list.add(saveButton);
		list.add(removeButton);
		
		return list;
	}

	@Override
	public void setWindowValidationUtil() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTopLeftSidePanelComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addTopRigthSidePanelComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPropertyPaneTable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addCenterRightSidePanelComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWindowActionListener() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel createChildPanel(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public JPanel createChildPanel(String id) {
		// TODO Auto-generated method stub
		return null;
	}
}
