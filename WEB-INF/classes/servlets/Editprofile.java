package servlets;
import beans.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Editprofile
 *
 */
 public class Editprofile extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Editprofile() {
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
		HttpSession session = request.getSession();
		//String vid = request.getParameter("vid");
		String username = (String)session.getAttribute("User");
		String cons = (String)session.getAttribute("Consti");
		String ward = (String)session.getAttribute("Wards");
		Connection con;
		Statement stmt, stmt1;
		ResultSet rs=null,rs1=null;
		
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt = con.createStatement();
			 stmt1 = con.createStatement();
			 
			 if(username.equals("ADMIN") || username.equals("admin"))
			 {
					 String query = "SELECT PASSWORD FROM GLOBAL_DATA WHERE USERNAME='ADMIN' OR USERNAME='admin'";
					 rs=stmt.executeQuery(query);
					 request.setAttribute("info",rs);
					 RequestDispatcher view = request
						.getRequestDispatcher("./editdetails.jsp");
					 view.forward(request, response);
					 rs.close();
					 rs1.close();
					 stmt.close();
					 stmt1.close();
					 con.close();
				
			 }
			 else if(username.startsWith("C"))
			 {
				 String query = "SELECT PASSWORD, EMAILID, PHONE FROM "+cons+"_SPECIALUSERS "+
				 				" WHERE LOGIN = '"+username+"'";
				 rs = stmt.executeQuery(query);
				 request.setAttribute("info",rs);
				 RequestDispatcher view = request
					.getRequestDispatcher("./editdetails.jsp");
				 view.forward(request, response);
				 rs.close();
				 rs1.close();
				 stmt.close();
				 stmt1.close();
				 con.close();
			 }
			 else if(username.startsWith("W"))
			 {
				 String query = "SELECT PASSWORD, EMAILID, PHONE FROM "+cons+"_"+ward+"_SPECIALUSERS "+
	 							" WHERE LOGIN = '"+username+"'";
				 rs = stmt.executeQuery(query);
				 request.setAttribute("info",rs);
				 RequestDispatcher view = request
				 	.getRequestDispatcher("./editdetails.jsp");
				 view.forward(request, response);
				 rs.close();
				 rs1.close();
				 stmt.close();
				 stmt1.close();
				 con.close();
			 }
			 else
			 {
				 String consti_ward = cons+"_"+ward;
				 String query = "SELECT EMAIL, PHONE, CELL FROM "+consti_ward+"_USER_DETAILS WHERE VOTERID='"+username+"'";
				 rs = stmt.executeQuery(query);
				 
				 String query1 = "SELECT PASSWORD FROM GLOBAL WHERE VOTERID='"+username+"' AND CONSTI_WARD='"+consti_ward+"'";
				 rs1 = stmt1.executeQuery(query1);
				 rs1.next();
				 String pwd = rs1.getString("PASSWORD");
				 request.setAttribute("info",rs);
				 request.setAttribute("pwd",pwd);
				 RequestDispatcher view = request
				 	.getRequestDispatcher("./editdetails.jsp");
				 view.forward(request, response);
				 rs.close();
				 rs1.close();
				 stmt.close();
				 stmt1.close();
				 con.close();
			 }
		}
		catch(Exception e)
		{
			
		}
	}   	  	    
}