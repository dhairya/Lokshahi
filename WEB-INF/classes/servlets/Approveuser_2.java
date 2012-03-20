package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Approveuser_2
 *
 */
 public class Approveuser_2 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Approveuser_2() {
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
		String gzone = request.getParameter("gzone");
		String forums = request.getParameter("forums");
		
		String polls = request.getParameter("poll");
		String funds = request.getParameter("funds");
		String chat = request.getParameter("chat");
		String employment = request.getParameter("employment");
		String report = request.getParameter("report");
		String approve = request.getParameter("approve");
		String access = gzone+forums+polls+funds+chat+employment+report+approve;
		String vid=request.getParameter("vid");
		String ward=request.getParameter("ward");
		String cons=request.getParameter("cons");
		String categories=null;
		String consti_ward=cons+"_"+ward;
		String cat[] = request.getParameterValues("cat");
		
		if(cat !=null){
		if(cat.length!=0){
		for(int i=0;i<cat.length;i++)
		{
			categories = categories + cat[i] + ","; 
		}
		}
		}
		
		Connection con;
		Statement stmt;
		try
		{  
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt = con.createStatement();
			 
			 String query = "UPDATE "+consti_ward+"_USER_DETAILS SET ACCESS_INFO='"+access+"', SOLVABLE_CATEGORIES='"+categories+"' WHERE VOTERID='"+vid+"'";
			 stmt.executeUpdate(query);
			 RequestDispatcher view = request
	     		.getRequestDispatcher("/ADMIN/approveuser.jsp");
	             view.forward(request, response);
	            
			 	 stmt.close();
			 	 con.close();
		}
		catch(Exception e)
		{
			
		}
		}   	  	    
}