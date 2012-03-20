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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: View_classifieds
 *
 */
 public class View_classifieds3 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public View_classifieds3() {
		super();
	}   	
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		/*
		 * this servlet displays detailed info of a particular user
		 */
		
		HttpSession session = request.getSession();
		String consti = (String) session.getAttribute("EmpConsti");
		String ward=(String) request.getParameter("ward");    
		
		String nick=(String) request.getParameter("nname");
		Statement stmt;
		ResultSet rs;
		
		
		
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			Connection con = ds.getConnection();
			stmt = con.createStatement();
		
			String query= "SELECT * FROM "+ consti+"_"+ward+"_USER_DETAILS "
						+"WHERE NICKNAME = '"+nick+"'";
			rs=stmt.executeQuery(query);
			
			request.setAttribute("details", rs);
			
			RequestDispatcher view = request.getRequestDispatcher("classifieds_view.jsp?ward="+ward);
			view.forward(request, response);
			
			rs.close();
			rs=null;
			stmt.close();
			stmt=null;
			con.close();
			con=null;
			
			
		}
		
		catch (Exception e){}
		
	}
 }




//String nick=(String) request.getParameter("nname")