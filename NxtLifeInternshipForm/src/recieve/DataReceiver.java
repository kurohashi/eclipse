package recieve;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import excel_creator.ExcelSheetCreator;
import factory_classes.Candidate;

@WebServlet("/DataReceiver")
public class DataReceiver extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DataReceiver() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Candidate tempCandidate = new Candidate();
		
		tempCandidate.setName(request.getParameter("name"));
		tempCandidate.setNumberOfMonthsToCommit(Integer.parseInt(request.getParameter("number_of_months_to_commit")));		
		tempCandidate.setLatestJoiningDate(Integer.parseInt(request.getParameter("dd")), 
										   Integer.parseInt(request.getParameter("mm")), 
										   Integer.parseInt(request.getParameter("yy")));
		tempCandidate.setTechnologiesKnown(request.getParameter("technologies"));
		tempCandidate.setPhoneContact(request.getParameter("mob"));
		tempCandidate.setEmail(request.getParameter("email"));
		
		if (ExcelSheetCreator.addToSheet(tempCandidate)) {
			response.getWriter().println("Your details have been submitted successfully..." +
										 "You will be contacted if shortlisted.");
		} else {
			response.getWriter().println("Error, check your details and try again...");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
