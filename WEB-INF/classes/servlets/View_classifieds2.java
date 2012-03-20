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
 * Servlet implementation class for Servlet: View_classifieds2
 *
 */
 public class View_classifieds2 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public View_classifieds2() {
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
		 * select the list of user (first 20 rows) to display in table format
		 */
		
		
		HttpSession session = request.getSession();
		String consti = (String) session.getAttribute("EmpConsti");
		String ward0= (String)request.getParameter("ward_list");   // ward obtained from classifieds1.jsp
		String ward1=(String) request.getParameter("ward1");   //ward obtained from classifieds_view.jsp
		String ward=null;
		Statement stmt;
		ResultSet rs;
		
		// assigns ward obtained from relevent jsp
		if (ward1==null)
		{
			ward= ward0;   //ward from list of wards
		}
		else
		{
			ward=ward1;    //ward from back link
		}
		
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			Connection con = ds.getConnection();
			stmt = con.createStatement();
		
			//select user details
			String query="SELECT * FROM " + consti + "_"+ward+"_USER_DETAILS"+
			" WHERE QUALIFICATION IS NOT NULL LIMIT 21";
			rs= stmt.executeQuery(query);
			
			request.setAttribute("row", "1");
			request.setAttribute("list", rs);
			
			RequestDispatcher view = request.getRequestDispatcher("classifieds2.jsp?ward="+ward);
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