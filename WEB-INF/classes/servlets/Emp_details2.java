package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Emp_details2
 *
 */
 public class Emp_details2 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Emp_details2() {
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
		String qual =(String) request.getParameter("highest_qual");
		String skills =(String) request.getParameter("skills");
		String exp =(String) request.getParameter("experience");
		String consti = (String) session.getAttribute("Consti");
		String nick = (String) session.getAttribute("Nick");
		String ward= (String) session.getAttribute("Wards");
		Statement stmt=null;
		
		try {
	        InitialContext ctx = new InitialContext();
	        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
	        Connection con = ds.getConnection();
	        stmt = con.createStatement();
	        
	        nick=(nick.split("-"))[1];
	        //update details
	        String query="UPDATE "+consti+"_"+ward+
	        		"_USER_DETAILS SET(QUALIFICATION, SKILLS, EXPERIENCE)=('" +qual+"','"+skills+"','"+exp+"')"+
	        		" WHERE NICKNAME = '"+nick +"'";
	        
	        stmt.executeUpdate(query);
	        
			stmt.close();
			stmt=null;
			con.close();
			con = null;
	        
	        //redirect	        
	        RequestDispatcher view = request.getRequestDispatcher("./Home_load?consti="+consti+"&ward="+ward);
			view.forward(request, response);
	        
	        
		}
		
		catch (Exception e)
		{}
	}
	
}