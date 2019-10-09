package logics;

import factory_classes.Candidate;

public class Validator {
	public static boolean validateCandidate(Candidate tempCandidate) {
		if (tempCandidate.getNumberOfMonthsToCommit() < 6) {
			return false;
		}
		
		char[] tech = tempCandidate.getTechnologiesKnown().toCharArray();
		int flag = 0;
		lb:for (int i = 0; i < tech.length; i++) {
			if (flag == 3) {
				if (tech[i] == 'a' || tech[i] == 'A') {
					flag++;
					if (i == tech.length-1)
					{
						break lb;
					}
					if (tech[i+1] == ',' || tech[i+1] == ' ') {
						flag++;
						break lb;
					} else {
						flag = 0;
					}
				} else {
					flag = 0;
				}			
			}
			
			if (flag == 2) {
				if (tech[i] == 'v' || tech[i] == 'V') {
					flag++;
				} else {
					flag = 0;
				}
			}
			
			if (flag == 1) {
				if (tech[i] == 'a' || tech[i] == 'A') {
					flag++;
				} else {
					flag = 0;
				}
			}
			
			if (tech[i] == 'J' || tech[i] == 'j') {
				if (i != 0) {
					if (tech[i-1] == ',' || tech[i-1] == ' ') {
						flag = 1;
					}
				} else {
					flag = 1;
				}
			}
		}
		
		if (flag != 5 && flag != 4) {
			return false;
		}
		
		return true;		
	}
	
	public static boolean validateEmail(String email) {

		boolean isValid = false;
		char[] emailArray = email.toCharArray();
		int atTheRatePosition = -1;
		int dotPositionAfterAtTheRate = -1;

		if ( ( email.startsWith("@") || email.endsWith("@") ) || ( email.startsWith(".") || email.endsWith(".") ) ) {

			return isValid;

		}

		for ( int i = 0; i < emailArray.length; i++) {

			if ( !( emailArray[i] >= 'a' && emailArray[i] <= 'z' ) ) {

				if ( emailArray[i] == '@' ) {

					if ( atTheRatePosition == -1 ) {

						isValid = true;
						atTheRatePosition = i + 1;

					} else {

						isValid = false;
						return isValid;

					} 

					if ( emailArray[i+1] == '.' ) {

						isValid = false;
						return isValid;

					}

				} else if ( emailArray[i] == '.' ) {

					if ( atTheRatePosition != -1 ) {

						if ( dotPositionAfterAtTheRate == -1 ) {

							dotPositionAfterAtTheRate = i - atTheRatePosition;
							isValid = true;

						} else {

							isValid = false;
							return isValid;

						}

					}

					if ( emailArray[i+1] == '@' ) {

						isValid = false;
						return isValid;

					}

				} else if ( emailArray[i] >= '1' || emailArray[i] <= '9' ) {

					if ( i > email.indexOf("@") ) {

						isValid = false;
						return isValid;

					}

				} else {

					isValid = false;
					return isValid;

				}

			}  

		}


		if ( atTheRatePosition < 3 || dotPositionAfterAtTheRate < 2 ) {

			isValid = false;

		}

		return isValid;
	}
	
	public static boolean validateMobile (String mobile) {
		if (mobile.charAt(0) == '+') {
			mobile = mobile.substring(1);
		}
		try {
			Long.parseLong(mobile);
		} catch (Exception e) {
			return false;
		}
		
		return true;
	}

}
