package security;

public class PolynomialOperation {
	public static String add(String b1, String b2) {

		StringBuffer b = new StringBuffer("");
		String bigB, shortB;

		if (b1.length() > b2.length()) {
			bigB = b1;
			shortB = b2;
		} else {
			bigB = b2;
			shortB = b1;
		}

		for (int i = 0; i < bigB.length() - shortB.length(); i++) {
			b.append(bigB.charAt(i));
		}

		int j = 0;
		for (int i = bigB.length() - shortB.length(); i < bigB.length() ; i++) {
			int val = Integer.parseInt(bigB.charAt(i) + "") ^ Integer.parseInt(shortB.charAt(j++) + "");
			b.append(val + "");
		}

		return b.toString();
	}
	
	public static String multiply(String b1, String b2) {
		StringBuffer[] b = new StringBuffer[8];
		String irReduciblePolynomial = new String("11011");
		StringBuffer temp = new StringBuffer(b1.toString());
		int k = 0;
		
		for (int i = 0; i < 8; i++) {
			if (b2.charAt(i) == '1') {
				for (int j = 0; j < 7 - i; j++) {
					char checkForModulus = temp.charAt(0);
					b[k] = new StringBuffer("");
					temp.delete(0, 1);
					temp.append('0');
					b[k].append(temp.substring(0, 3));
					if (checkForModulus == '1') {
						for (int l = 0; l < 5; l++) {
							int t = Integer.parseInt(temp.charAt(3 + l) + "") ^ Integer.parseInt(irReduciblePolynomial.charAt(l) + "");
							b[k].append(t);
						}
					} else {
						b[k].append(temp.substring(3));
					}
					k++;
				}
				k = 0;
				temp.replace(0, 7, b1.toString());
			}
		}
		
		for (int i = 1; i < k; i++) {
			for (int l = 0; l < 8; l++) {
				int t = Integer.parseInt(b[0].charAt(l) + "") ^ Integer.parseInt(b[i].charAt(l) + "");
				b[0].replace(l, l + 1, t + "");
			}
		}
		
		return b[0].toString();
	}
	
	public static void main(String[] args) {
		System.out.println(multiply("10101010", "01010101"));
	}
}

