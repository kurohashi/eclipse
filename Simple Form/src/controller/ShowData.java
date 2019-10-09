package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ExecuteSql;

@WebServlet("/show_data")
public class ShowData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String billno = request.getParameter("ShowBill").toString();
		ExecuteSql exec = new ExecuteSql();
		String[] result = exec.getData(billno);
		String res = "<!DOCTYPE html>\n" + 
				"<html>\n" + 
				"<body>\n" + 
				"\n" + 
				"\n" +
				" <table>\n" + 
				"  <tbody> \n" + 
				"  <tr> \n" + 
				"   <td>Billno</td> \n" + 
				"   <td>" + result[0] + "</td>\n" +
				"  </tr>\n" + 
				"  <tr>\n" + 
				"   <td>Date</td>\n" + 
				"   <td>" + result[1] + "</td>\n" + 
				"  </tr>\n" + 
				"  <tr>\n" + 
				"   <td>Customer</td>\n" + 
				"   <td>" + result[2] + "</td>\n" +
				"  </tr>\n" + 
				"  <tr>\n" + 
				"   <th>Item</th>\n" + 
				"   <th>Qty</th>\n" + 
				"   <th>Rate</th>\n" + 
				"   <th>Amount</th>\n" + 
				"  </tr>\n" + 
				"  <tr>\n" + 
				"   <td>" + result[3] + "</td>\n" + 
				"   <td>" + result[4] + "</td>\n" +  
				"   <td>" + result[5] + "</td>\n" + 
				"   <td>" + result[6] + "</td>\n" +  
				"  </tr>\n" + 
				"  <tr>\n" + 
				"   <td>" + result[7] + "</td>\n" + 
				"   <td>" + result[8] + "</td>\n" +  
				"   <td>" + result[9] + "</td>\n" + 
				"   <td>" + result[10] + "</td>\n" + 
				"  </tr>\n" + 
				"  <tr>\n" + 
				"   <td>" + result[11] + "</td>\n" + 
				"   <td>" + result[12] + "</td>\n" +  
				"   <td>" + result[13] + "</td>\n" + 
				"   <td>" + result[14] + "</td>\n" +
				"  </tr>\n" + 
				"  <tr>\n" + 
				"  </tr>\n" + 
				"  </tbody>\n" + 
				" </table>\n" + 
				"\n" + 
				"</body>\n" + 
				"</html>";
		response.getWriter().println(res);
	}

}
