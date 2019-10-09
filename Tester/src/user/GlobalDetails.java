package user;

import java.io.File;

public class GlobalDetails {
	private static String userName; 
	private static File file;

	public static String getUserName() {
		return userName;
	}

	public static void setUserName(String userName) {
		GlobalDetails.userName = userName;
	}

	public static File getFile() {
		return file;
	}

	public static void setFile(File file) {
		GlobalDetails.file = file;
	}
}
