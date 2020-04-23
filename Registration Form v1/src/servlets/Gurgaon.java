package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Gurgaon
 */
public class Gurgaon extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");  
	    PrintWriter out = response.getWriter();  
	    String userName = request.getParameter("uname");
		String password = request.getParameter("password");
		String rPassword = request.getParameter("rPassword");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		String city = request.getParameter("city");
		String[] technologies = request.getParameterValues("technologies");
		List<String> techno = Arrays.asList(technologies);
		int wexp = Integer.parseInt(request.getParameter("wexp"));
		
		int techNo = technologies.length;
		out.print("Name - "+userName+"<br>");
		out.print("Age - "+age+"<br>");
		out.print("Gender - "+gender+"<br>");
		out.print("City - "+city+"<br>");
		out.print("Technologies - ");
		int i=0;
		while(i!=techNo) {
			out.print( technologies[i] + " ");
			i++;
		}
		out.print("<br>");
		out.print("Work Experience - "+wexp+" years<br>");
		out.print("<br>");
		out.print("<br>");
		out.print("Jobs availability : - ");
		if(techno.contains("Oracle")) {
			out.print("DBA");
		}else if(techno.contains("Juniper")) {
			out.print("Network Administrator");
		}else {
			out.print("Sorry, No jobs available at this location");
		}
	}

}
