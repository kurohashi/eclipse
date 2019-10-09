package com.authentication;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class DigitalId {

	private static long finalId;

	public static String generateId() {

		try {
			finalId = 0;
			if (searchForMac().hashCode() == -1023890944) {

				InetAddress ip;
				ip = InetAddress.getLocalHost();
				NetworkInterface network = NetworkInterface.getByInetAddress(ip);
				byte[] mac = network.getHardwareAddress();
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < mac.length - 1; i++) {
					sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "" : ""));
				}
				finalId += sb.toString().hashCode();
			} else {
				finalId += searchForMac().hashCode();
			}
			Class<?> c = null;
			Object o = null;
			Method method = null;

			if (System.getProperty("os.name").toLowerCase().contains("windows")) {

				c = Class.forName("com.sun.security.auth.module.NTSystem");
				o = Class.forName("com.sun.security.auth.module.NTSystem").newInstance();
				method = c.getDeclaredMethod("getName");
			} else if (System.getProperty("os.name").toLowerCase().contains("linux")) {

				c = Class.forName("com.sun.security.auth.module.UnixSystem");
				o = Class.forName("com.sun.security.auth.module.UnixSystem").newInstance();
				method = c.getDeclaredMethod("getUsername");
			} else if (System.getProperty("os.name").toLowerCase().contains("solaris")) {

				c = Class.forName("com.sun.security.auth.module.SolarisSystem");
				o = Class.forName("com.sun.security.auth.module.SolarisSystem").newInstance();
				method = c.getDeclaredMethod("getUsername");
			}

			if (c != null) {

				finalId += method.invoke(o).hashCode() * 23;
			}
		} catch (Exception e) {
			finalId = 91129120328568l;
		}
		return String.valueOf(finalId);
	}

	static String searchForMac() {
		try {
			String firstInterface = null;
			Map<String, String> addressByNetwork = new HashMap<String, String>();
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
		} catch (SocketException e) {
			System.out.println(e);
			return "9999999999";
		}
		return "9999999999";
	}

}
