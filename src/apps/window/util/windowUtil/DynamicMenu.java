package apps.window.util.windowUtil;
 
import java.util.Enumeration;
import java.util.Hashtable; 
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import util.commonUTIL;

import beans.MenuConfiguration;


public class DynamicMenu extends JFrame {
	Vector<MenuConfiguration> MenuConfiguration = new Vector<MenuConfiguration>();
	Hashtable<String, JMenuItem[]> subMenu = new Hashtable<String, JMenuItem[]>();
	Hashtable<String, JMenu > mainMenudata = new Hashtable<String, JMenu >();
	
	public MenuConfiguration getMenuItem(String mainName,String windowName,String parentName,String childName,boolean isParentChild) {
		MenuConfiguration mconfig = new MenuConfiguration();
		mconfig.setMainMenuName(mainName);
		mconfig.setWindowName(windowName);
		mconfig.setParentMenuName(parentName);
		mconfig.setChildMenuName(childName);
		mconfig.setIsParentChild(isParentChild);
		
		return mconfig;
	}
	public void addMenuData(MenuConfiguration menuItem ) {
		MenuConfiguration.add(menuItem);
		 
	} 
	public void setMenuData(Vector<MenuConfiguration>  menuData) {
		this.MenuConfiguration = menuData;
	}
	public Hashtable<String, JMenu > getMenuKeyData() {
		return mainMenudata;
	}
	public Vector<MenuConfiguration> getMenuData() {
		return MenuConfiguration;
	}
	 
	public JMenu[] getMainMenu(Vector<MenuConfiguration> menuData) {
		 
		// First Step Get all Main menu in hash table
		for(int i=0;i<MenuConfiguration.size();i++) {
			MenuConfiguration item = MenuConfiguration.get(i);
			if(!commonUTIL.isEmpty(item.getMainMenuName())) {
				JMenu mm = new JMenu(item.getMainMenuName());
				mainMenudata.put(item.getMainMenuName(), mm);
			}
		}
		JMenu[] jmenu = new JMenu[mainMenudata.size()];
		// create child menu and subChild menu
		getMenuItem(MenuConfiguration);
		Enumeration<String> keys = 	 mainMenudata.keys();
		int i =0;
		  while(keys.hasMoreElements()) {
			  String key = keys.nextElement();
			 JMenu me = mainMenudata.get(key);
			 jmenu[i] = me;
			 i++;
		  }
		return jmenu;
	}
	// create child menu and subChild menu
	public void getMenuItem(Vector<MenuConfiguration> menuData  ) {
	Enumeration<String> keys = 	 mainMenudata.keys();
	  while(keys.hasMoreElements()) {
		  String key = keys.nextElement();
		 JMenu menK = mainMenudata.get(key);
		  JMenu menu = getMainMenuItem( menK, key);
		  addMenuItemToMenu(menu, mainMenudata.get(key));
		  
	  }
	} 
	
	
	private void addMenuItemToMenu( JMenu menuitem , JMenu jMenu) {
		// TODO Auto-generated method stub
	 
			jMenu.add(  menuitem);
		
		
	}
	private boolean isDuplicate(JMenu menu,String itemName) {
		boolean flag = false;
		for(int i=0;i<menu.getMenuComponentCount();i++) {
			JMenuItem jj =(JMenuItem) menu.getMenuComponent(i);
			if(jj.getText().equalsIgnoreCase(itemName)) {
				flag = true;
				break;
			}
				
			
		}
		return flag;
		
	}
	// on the basis of hash key find which are direct child of hash key
	// on getting any direct child of key which don't have any child itelf add to key as menuitem. (code in getMainMenuItem first if condition).
	// on getting any direct child of key which  have any child itelf add to key as Menu but check it's duplicate and is only child to key of hash table .
	
	private JMenu getMainMenuItem(JMenu menu,String key) {
		// TODO Auto-generated method stub
		Vector<JMenuItem> menuItems = new Vector<JMenuItem>();
		for(int i=0;i<MenuConfiguration.size();i++) {
			MenuConfiguration item = MenuConfiguration.get(i);
			if(!commonUTIL.isEmpty(item.getMainMenuName()) && item.getMainMenuName().equalsIgnoreCase(key)) {
				if(!item.isParentChild()) {
					 
					     menu.add(new JMenuItem(item.getParentMenuName()));
				 
				} else {
					if(!isDuplicate(menu,item.getParentMenuName()) && !checkMenuIsChild(item.getParentMenuName())) {
						
						JMenu men = new JMenu(item.getParentMenuName());
					menu.add(getParentMenuItem(men,item.getParentMenuName()));
					} 
					
				}
			}
		} 
		return menu ;
		
	}
	
	private boolean checkMenuIsChild(String menuName) {
		boolean flag = false;
		for(int i=0;i<MenuConfiguration.size();i++) {
			MenuConfiguration items = MenuConfiguration.get(i);
			if(!commonUTIL.isEmpty(items.getChildMenuName())) {
				 
			if(items.getChildMenuName().equalsIgnoreCase(menuName)) {
				flag = true;
				break;
			}
			}
	}
		return flag;
		
	}
	private boolean checkMenuIsParent(String menuName) {
		boolean flag = false;
		for(int i=0;i<MenuConfiguration.size();i++) {
			MenuConfiguration items = MenuConfiguration.get(i);
			if(!commonUTIL.isEmpty(items.getChildMenuName())) {
			if(items.getParentMenuName().equalsIgnoreCase(menuName)) {
				flag = true;
				break;
			}
			}
	}
		return flag;
		
	}
	
	// this method create menu which are not directly child of hashKey .
	private JMenuItem getParentMenuItem(JMenu menu, String key) {
		// TODO Auto-generated method stub
		Vector<JMenuItem> menuItems = new Vector<JMenuItem>();
		for(int i=0;i<MenuConfiguration.size();i++) {
			MenuConfiguration item = MenuConfiguration.get(i);
			if(!commonUTIL.isEmpty(item.getParentMenuName()) && item.getParentMenuName().equalsIgnoreCase(key)) {
				if(!item.isParentChild()) {
					  menu.add(new JMenuItem(item.getChildMenuName()));
					
				}else {
					if(!isDuplicate(menu,item.getChildMenuName()) && checkMenuIsParent(item.getChildMenuName())) {
						JMenu men = new JMenu(item.getChildMenuName());
					menu.add(getParentMenuItem(men,item.getChildMenuName()));
					}  else {
						  menu.add(new JMenuItem(item.getChildMenuName()));
					}
					
					
				}
			}
		} 
		return menu ;
	}
	public void showFrame(DynamicMenu frame ) {
		JMenu[] menu = frame.getMainMenu(frame.getMenuData());
		 
	 	JMenuBar menubar = new JMenuBar();
	for (JMenu menus : menu) {
       if(menus != null) {
		menubar.add(menus);
		 
		frame.setJMenuBar(menubar);
       }
	}
	 
	frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	frame.setTitle("frame");
	frame.setSize(200, 110);
	frame.getContentPane().setPreferredSize(frame.getSize());
	frame.pack();
	frame.setLocationRelativeTo(null);
	frame.setVisible(true);
	}
	public static void main(String args[]) {
		
		DynamicMenu frame = new DynamicMenu();
		frame.addMenuData(frame.getMenuItem("Static Configuration","","StartUpData","",false ));

		frame.addMenuData(frame.getMenuItem("Reference","","Holiday","",false ));

		frame.addMenuData(frame.getMenuItem("Reference","","Product","",false ));

		frame.addMenuData(frame.getMenuItem("Reference","","Settlement","SDI",true ));

		frame.addMenuData(frame.getMenuItem("Reference","","Settlement","NettingConfig",true ));
		frame.addMenuData(frame.getMenuItem("Trade","","FixedIncome","Bond" ,true));
		frame.addMenuData(frame.getMenuItem("Trade","","Bond","GBond" ,true));
		frame.addMenuData(frame.getMenuItem("Trade","","Bond","NGBond" ,true));
		
		frame.addMenuData(frame.getMenuItem("Static Configuration","","Faviourites","Currency",true )); 

		frame.addMenuData(frame.getMenuItem("Static Configuration","","Faviourites","Tenor",true )); 
		
		
		
	}
	
	
	
	

}
