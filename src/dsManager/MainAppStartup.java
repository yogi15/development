package dsManager;

import java.awt.Toolkit;
import java.net.URL;

import apps.window.staticwindow.Login;
import dsServices.ServiceManager;

public class MainAppStartup extends ServiceManager {
	Login login = null;
	@Override
	public void start() {
		// TODO Auto-generated method stub
		
		
		
		login =  new Login();
		login.setVisible(true);
		 login.setSize(220,200);
		 login.setTitle(" Cosmos Login ");
			URL imgURL =  imgURL = this.getClass().getResource("/resources/icon/sql.jpg");
			login.setIconImage(Toolkit.getDefaultToolkit()
		      		   .getImage(imgURL));
 		login.setResizable(false);
 		  login.setLocationRelativeTo(new Login());
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		login.stop();
	}

	
}
