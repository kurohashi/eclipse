package security;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
// 12631510271593195081 my

public class DigitalId {

	public static void main(String... k) throws SocketException {

		if (searchForMac().hashCode() == -1023890944) {
			InetAddress ip;
			try {

				ip = InetAddress.getLocalHost();
				NetworkInterface network = NetworkInterface.getByInetAddress(ip);
				byte[] mac = network.getHardwareAddress();
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < mac.length - 1; i++) {
					sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "" : ""));
				}
				System.out.print((sb.toString().hashCode()));

			} catch (UnknownHostException e) {

				e.printStackTrace();

			} catch (SocketException e) {

				e.printStackTrace();

			}
		} else {
			System.out.print((searchForMac().hashCode()));
		}
		Class<?> c = null;
		Object o = null;
		Method method = null;

		if (System.getProperty("os.name").toLowerCase().contains("windows")) {

			try {
				c = Class.forName("com.sun.security.auth.module.NTSystem");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				o = Class.forName("com.sun.security.auth.module.NTSystem").newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				method = c.getDeclaredMethod("getName");
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (System.getProperty("os.name").toLowerCase().contains("linux")) {

			try {
				c = Class.forName("com.sun.security.auth.module.UnixSystem");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				o = Class.forName("com.sun.security.auth.module.UnixSystem").newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				method = c.getDeclaredMethod("getUsername");
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		else if (System.getProperty("os.name").toLowerCase().contains("solaris")) {

			try {
				c = Class.forName("com.sun.security.auth.module.SolarisSystem");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				o = Class.forName("com.sun.security.auth.module.SolarisSystem").newInstance();
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				method = c.getDeclaredMethod("getUsername");
			} catch (NoSuchMethodException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		if (c != null) {
			try {
				System.out.println((method.invoke(o)).hashCode());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	static String searchForMac() throws SocketException {
		String firstInterface = null;
		Map<String, String> addressByNetwork = new HashMap<>();
		Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();

		while (networkInterfaces.hasMoreElements()) {
			NetworkInterface network = networkInterfaces.nextElement();

			byte[] bmac = network.getHardwareAddress();
			if (bmac != null) {
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < bmac.length - 1; i++) {
					sb.append(String.format("%02X%s", bmac[i], (i < bmac.length - 1) ? "" : ""));
				}

				if (sb.toString().isEmpty() == false) {
					addressByNetwork.put(network.getName(), sb.toString());
				}

				if (sb.toString().isEmpty() == false && firstInterface == null) {
					firstInterface = network.getName();
				}
			}
		}

		if (firstInterface != null) {
			return addressByNetwork.get(firstInterface);
		}

		return null;
	}

}
