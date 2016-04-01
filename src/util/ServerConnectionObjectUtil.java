package util;

import dsServices.ServerConnectionUtil;

public class ServerConnectionObjectUtil {

	public static ServerConnectionUtil getServerConnection() {

		return ServerConnectionUtil.connect("localhost", 1099,
				commonUTIL.getServerIP());
	}

}
