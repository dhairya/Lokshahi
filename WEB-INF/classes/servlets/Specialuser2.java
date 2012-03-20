package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Specialuser2
 *
 */
 public class Specialuser2 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Specialuser2() {
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
		String type = request.getParameter("utype");
		//System.out.println(type);
		Connection con;
		Statement stmt1;
		ResultSet rs1;
		
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt1 = con.createStatement();
			 
			 String query1 = "SELECT * FROM CONSTITUENCY";
			 rs1 = stmt1.executeQuery(query1);
			 
			 //String query2 = "SELECT * FROM CATEGORIES";
			// rs2 = stmt2.executeQuery(query2);
			 
			 
		     request.setAttribute("usertype",type);
		     request.setAttribute("cons",rs1);
		    // request.setAttribute("cats",rs2);
		     RequestDispatcher view = request
		     .getRequestDispatcher("/ADMIN/newSpecialUser.jsp");
             view.forward(request, response); 
             rs1.close();
             stmt1.close();
             con.close();
	      }
	    catch (Exception e)
	      {
		
	      }
	}	
}