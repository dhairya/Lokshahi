package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class for Servlet: Classifieds_next
 * 
 */
public class Classifieds_next extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Classifieds_next() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Statement stmt;
		ResultSet rs;
		HttpSession session = request.getSession();
		String consti = (String) session.getAttribute("Consti");
		String rowtemp = (String)request.getParameter("row");
		String ward= (String)request.getParameter("ward");
		String row1 = String.valueOf((Integer.parseInt(rowtemp) + 20)) ;
		String row2 = String.valueOf((Integer.parseInt(rowtemp) + 40)) ; 
		String rowq = String.valueOf((Integer.parseInt(row1) - 1)) ;
		
		
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			Connection con = ds.getConnection();
			stmt = con.createStatement();
			
			String query = "SELECT NICKNAME,FNAME,LNAME,QUALIFICATION FROM " + 
							consti + "_" +ward+ "_USER_DETAILS WHERE QUALIFICATION IS NOT NULL ORDER BY VOTERID DESC LIMIT " + rowq + ", 20"; 
			rs= stmt.executeQuery(query);
			
			request.setAttribute("row", row1);
			request.setAttribute("list", rs);
			
			RequestDispatcher view = request.getRequestDispatcher("classifieds2.jsp?ward="+ward);
			view.forward(request, response);
			
			rs.close();
			rs=null;
			stmt.close();
			stmt=null;
			con.close();
			con = null;
			
		}
		
		catch (Exception e)
		{}
		
		
	}
}