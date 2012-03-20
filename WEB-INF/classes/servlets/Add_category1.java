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
 * Servlet implementation class for Servlet: Add_category1
 *
 */
 public class Add_category1 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Add_category1() {
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
		Connection con;
		Statement stmt;
		ResultSet rs;
		Statement stmt1;
		ResultSet rs1;
		
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt = con.createStatement();
			 stmt1 = con.createStatement();
			 
			 String query = "SELECT * FROM TEMPLATES";
			 rs = stmt.executeQuery(query);
			 
			 String query1 = "SELECT CATEGORY FROM CATEGORIES";
			 rs1 = stmt1.executeQuery(query1);
			 
			 request.setAttribute("template",rs);
			 request.setAttribute("cat",rs1);
			 RequestDispatcher view = request
				.getRequestDispatcher("/ADMIN/add_category.jsp");
		view.forward(request, response);
		rs.close();
		rs1.close();
		stmt.close();
		stmt1.close();
		con.close();
		}
		catch(Exception e)
		{
			
		}
	}   	  	    
}