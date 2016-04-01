package beans;

import java.io.Serializable;

public class AccessWindow implements Serializable{

	private static final long serialVersionUID = 4700046137771510706L;
	private String groupname;
	private String windowName;
	int isAccessable;
	
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getWindowName() {
		return windowName;
	}
	public void setWindowName(String windowName) {
		this.windowName = windowName;
	}
	public int getIsAccessable() {
		return isAccessable;
	}
	public void setIsAccessable(int isAccessable) {
		this.isAccessable = isAccessable;
	}
	
	
}
