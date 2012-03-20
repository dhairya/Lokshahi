package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Add_template
 *
 */
 public class Add_template extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Add_template() {
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
		String type = request.getParameter("type");
		String gzone = request.getParameter("gzone");
		String forums = request.getParameter("forums");
		
		String polls = request.getParameter("poll");
		String funds = request.getParameter("funds");
		String chat = request.getParameter("chat");
		String employment = request.getParameter("employment");
		String report = request.getParameter("report");
		String approve = request.getParameter("approve");
		String access = gzone+forums+polls+funds+chat+employment+report+approve;
		
		Connection con;
		Statement stmt;
		
		try
		{  
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt = con.createStatement();
			 
			 String query = "INSERT INTO TEMPLATES (NAME, ACCESS_INFO) " + 
			                "VALUES ('"+type+"','"+access+"')";
			 stmt.executeUpdate(query);
			 RequestDispatcher view = request
				.getRequestDispatcher("Specialuser1");
		view.forward(request, response);
	 	 stmt.close();
	 	 con.close();
		}
		catch(Exception e)
		{
			
		}
		
	}   	  	    
}