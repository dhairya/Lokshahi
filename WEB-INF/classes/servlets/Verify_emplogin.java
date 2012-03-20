package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: Verify_emplogin
 *
 */
 public class Verify_emplogin extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Verify_emplogin() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();
		String empid=(String) request.getParameter("empid");
		String pswd=(String) request.getParameter("pswd");
		Connection con;
		Statement stmt;
		ResultSet rs;
		
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt = con.createStatement();
			 
			 String query = "SELECT * FROM COMPANY_DETAILS WHERE USER_ID = '" + empid + "' AND PASSWORD='" + pswd +"'";
			 rs=stmt.executeQuery(query);
			 
			 if (rs != null && rs.next())
			 {
				//set session with company's constitution
				String consti=rs.getString("CONSTI_CODE");
				session.setAttribute("EmpConsti",consti);
				session.setAttribute("Empid", empid);
				
								
				//redirect to view classifieds
				RequestDispatcher view = request.getRequestDispatcher("./View_classifieds1");
		        view.forward(request, response);
				
			 }
			 
			 else
			 { 
			 // redirect to wrong password or userid
			 
			 }
			}
		catch (Exception e)
		{ }
		
	}
 }