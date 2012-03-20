
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Generatereports
 *
 */
 public class Generatereports extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Generatereports() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String code = (String)request.getParameter("code");
		code = code.toUpperCase();
		String value = (String)request.getParameter("report1");
		String rtype = (String)request.getParameter("type1");
	
		String tablename;
		if(value.equals("dept"))
		{
			tablename="DB2ADMIN."+code+"_GRIEVANCES";
			request.setAttribute("table",tablename);
			request.setAttribute("reporttype", rtype);
			request.setAttribute("code", code);
			RequestDispatcher view = request
			.getRequestDispatcher("./report_dept.jsp");
	view.forward(request, response);
		}
		if(value.equals("fund"))
		{
			
			tablename="SELECT "+
		     "DB2ADMIN."+code+"_FUNDS.\"COST\" AS FUNDS_COST, "+
		     "DB2ADMIN."+code+"_FUNDS.\"TITLE\" AS FUNDS_TITLE, "+
		     "DB2ADMIN."+code+"_FUNDS.\"FUND_ID\" AS FUNDS_FUND_ID, "+
		     "DB2ADMIN."+code+"_FUNDS.\"DESCRIPTION\" AS FUNDS_DESCRIPTION "+
		     "FROM DB2ADMIN."+code+"_FUNDS";
			request.setAttribute("table",tablename);
			request.setAttribute("reporttype", rtype);
			request.setAttribute("code", code);
			RequestDispatcher view = request
			.getRequestDispatcher("./report_fund.jsp");
	view.forward(request, response);
		}
		if(value.equals("poll"))
		{
			String poll = (String)request.getParameter("pollno");
			tablename="SELECT "+
		     "DB2ADMIN."+code+"_POLL.\"POLLNO\" AS POLL_POLLNO, "+
		     "DB2ADMIN."+code+"_POLL.\"OPT1_CNT\" AS POLL_OPT1_CNT, "+
		     "DB2ADMIN."+code+"_POLL.\"OPT2_CNT\" AS POLL_OPT2_CNT, "+
		     "DB2ADMIN."+code+"_POLL.\"OPT3_CNT\" AS POLL_OPT3_CNT, "+
		     "DB2ADMIN."+code+"_POLL.\"OPT4_CNT\" AS POLL_OPT4_CNT, "+
		     "DB2ADMIN."+code+"_POLL.\"OPT1\" AS POLL_OPT1, "+
		     "DB2ADMIN."+code+"_POLL.\"OPT2\" AS POLL_OPT2, "+
		     "DB2ADMIN."+code+"_POLL.\"OPT3\" AS POLL_OPT3, "+
		     "DB2ADMIN."+code+"_POLL.\"OPT4\" AS POLL_OPT4, "+
		     "DB2ADMIN."+code+"_POLL.\"QUESTION\" AS POLL_QUESTION, "+
		     "DB2ADMIN."+code+"_POLL.\"NICKNAME\" AS POLL_NICKNAME "+
		"FROM DB2ADMIN."+code+"_POLL "+
		"WHERE DB2ADMIN."+code+"_POLL.\"POLLNO\" = "+poll;
			request.setAttribute("table",tablename);
			request.setAttribute("reporttype", rtype);
			request.setAttribute("pollno",poll);
			request.setAttribute("code", code);
			RequestDispatcher view = request
			.getRequestDispatcher("./report_poll.jsp");
	view.forward(request, response);
		}
		if(value.equals("userdetails"))
		{
				tablename="SELECT "+
			     "DB2ADMIN."+code+"_USER_DETAILS.\"VOTERID\" AS USER_DETAILS_VOTERID, "+
			     "DB2ADMIN."+code+"_USER_DETAILS.\"FNAME\" AS USER_DETAILS_FNAME, "+
			     "DB2ADMIN."+code+"_USER_DETAILS.\"MNAME\" AS USER_DETAILS_MNAME, "+
			     "DB2ADMIN."+code+"_USER_DETAILS.\"LNAME\" AS USER_DETAILS_LNAME, "+
			     "DB2ADMIN."+code+"_USER_DETAILS.\"NICKNAME\" AS USER_DETAILS_NICKNAME, "+
			     "DB2ADMIN."+code+"_USER_DETAILS.\"EMAIL\" AS USER_DETAILS_EMAIL "+
			     "FROM DB2ADMIN."+code+"_USER_DETAILS";
				request.setAttribute("table",tablename);
				request.setAttribute("reporttype", rtype);
				request.setAttribute("code", code);
				RequestDispatcher view = request
				.getRequestDispatcher("./report_userdetails.jsp");
		view.forward(request, response);
		}
		if(value.equals("grievance"))
		{
			request.setAttribute("reporttype", rtype);
			request.setAttribute("code", code);
			RequestDispatcher view = request
			.getRequestDispatcher("./report_grievances.jsp");
	view.forward(request, response);
		}
	}   	  	    
}