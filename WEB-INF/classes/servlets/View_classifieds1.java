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
 * Servlet implementation class for Servlet: View_classifieds
 *
 */
 public class View_classifieds1 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public View_classifieds1() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		/*
		 * just displays a drop down menu showing list of wards
		 */
		
		
		HttpSession session = request.getSession();
		String consti = (String) session.getAttribute("EmpConsti");
		
		Statement stmt1;
		ResultSet rs1;
		
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			Connection con = ds.getConnection();
			
			//select list of wards
			stmt1=con.createStatement();
			String query1="SELECT NUMBER FROM " + consti +"_WARDS";
			rs1=stmt1.executeQuery(query1);
			
			request.setAttribute("ward_list", rs1);
			
			RequestDispatcher view = request.getRequestDispatcher("classifieds1.jsp");
			view.forward(request, response);
			
			rs1.close();
			rs1=null;
			stmt1.close();
			stmt1=null;
			con.close();
			con=null;
								
		}
		
		catch (Exception e)
		{}
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

	}
	
 }


// SELECT * FROM DB2ADMIN." + consti +"_USER_DETAILS WHERE QUALIFICATION IS NOT NULL"