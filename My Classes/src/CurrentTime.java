//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class CurrentTime {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		
//		DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
//		System.out.println(df.format(date));
		date = Calendar.getInstance().getTime();
		int hr = date.getHours();
		int mm = date.getMinutes();
		int ss = date.getSeconds();
		System.out.println(""+hr+mm+ss);

	}

}
