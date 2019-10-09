package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import database.ExecuteSql;

@WebServlet("/Receiver")
public class Receiver extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ExecuteSql exec = new ExecuteSql();
		String query = "insert into Bill values ('" + request.getParameter("Billno").toString() + "', '" + request.getParameter("Date").toString() + "', '" + request.getParameter("Customer") + "');";
		exec.runQuery(query);
		query = "insert into Billtr values ('" + request.getParameter("Billno").toString() + "', " + exec.getItemId(request.getParameter("IValue1")) + ", " + request.getParameter("QValue1") + ", " + request.getParameter("RValue1") + ", " + request.getParameter("AValue1") + ");";
		exec.runQuery(query);
		query = "insert into Billtr values ('" + request.getParameter("Billno").toString() + "', " + exec.getItemId(request.getParameter("IValue2")) + ", " + request.getParameter("QValue2") + ", " + request.getParameter("RValue2") + ", " + request.getParameter("AValue2") + ");";
		exec.runQuery(query);
		query = "insert into Billtr values ('" + request.getParameter("Billno").toString() + "', " + exec.getItemId(request.getParameter("IValue3")) + ", " + request.getParameter("QValue3") + ", " + request.getParameter("RValue3") + ", " + request.getParameter("AValue3") + ");";
		exec.runQuery(query);
		response.getWriter().append("Your data is saved.");
		
	}
}
