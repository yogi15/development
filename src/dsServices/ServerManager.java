package dsServices;

import java.io.Serializable;

public abstract class ServerManager  implements Serializable {
	
	public abstract void start();
	public abstract void stop();

}
