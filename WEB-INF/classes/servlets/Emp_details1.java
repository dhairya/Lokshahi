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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Emp_details1
 *
 */
 public class Emp_details1 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Emp_details1() {
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
		Statement stmt = null;
		int flag=0;
		String consti = (String) session.getAttribute("Consti");
		String nick = (String) session.getAttribute("Nick");
		String ward= (String) session.getAttribute("Wards");
		
		try {
	        InitialContext ctx = new InitialContext();
	        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
	        Connection con = ds.getConnection();
	        stmt = con.createStatement();
	        ResultSet rs;
	        
	        nick=(nick.split("-"))[1];
	        //Select the details of the user
	        String query="SELECT QUALIFICATION,SKILLS,EXPERIENCE FROM "+consti+"_"
	        				+ward+"_USER_DETAILS WHERE NICKNAME = '"+nick+"'";
	        rs=stmt.executeQuery(query);
	        rs.next();
	        
	        //extract details
	        String qual=rs.getString("QUALIFICATION");
	        String skills=rs.getString("SKILLS");
	        String exp=rs.getString("EXPERIENCE");
	        
	        //set attributes
	        request.setAttribute("qual", qual);
	        request.setAttribute("skills", skills);
	        request.setAttribute("exp", exp);
	        
	        rs.close();
			rs=null;
			stmt.close();
			stmt=null;
			con.close();
			con = null;
	        
	        RequestDispatcher view2 = request.getRequestDispatcher("./edit_emp_profile.jsp?consti="+consti);
	        	view2.forward(request, response);
	        	
	        		
	        	
	        }
		catch(Exception e){}
		
		
	}   	  	    
}