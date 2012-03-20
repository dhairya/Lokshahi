package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Forum_change
 *
 */
 public class Forum_change extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Forum_change() {
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
		PrintWriter out = response.getWriter();
		String user = (String) session.getAttribute("User");
		String privstr = (String) session.getAttribute("Access");
		String type = (String) session.getAttribute("Type");
		String constiA = (String) session.getAttribute("Consti");
		String wards = (String) session.getAttribute("Wards");
		String categories = (String) session.getAttribute("Categories");
		String nick = (String) session.getAttribute("Nick");
		
		char priv;
		
		String constiB = (String)request.getParameter("consti");
		String wardB = (String)request.getParameter("ward");
		long probid = Long.parseLong(request.getParameter("forid"));
		String value = (String)request.getParameter("value");
		String subid = (String)request.getParameter("subid");
		String reply = (String)request.getParameter("reply");
		String descript = (String)request.getParameter("postreply");
		
		
		Boolean ward_belong; 
		
		if(wards==null)
		{	
			ward_belong = false;
		}
		else
		{
			ward_belong= wards.contains(wardB) || wards.equalsIgnoreCase("ALL");
		}

		
		if(privstr == null || (!type.equalsIgnoreCase("ADMIN") &&(!constiA.equals(constiB) || !ward_belong))  )
		{
			priv = '1';
		}
		else
		{
			priv=privstr.charAt(1);
		}
		
	
		Connection con;
		Statement stmt;
		Statement stmt1;
		java.sql.Timestamp stamp = new java.sql.Timestamp(new java.util.Date().getTime());
		
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			con = ds.getConnection();
			stmt = con.createStatement();
			stmt1 = con.createStatement();
			
			
			if(reply == null && value.equals("deleteorig"))
			{
				String query = "Delete from " + constiB + "_" + wardB + "_FORUM where FOR_ID = "+probid;
				stmt.executeUpdate(query);
				
				String query1 = "Delete from " + constiB + "_" + wardB + "_FORUM_SUBPOST where FOR_ID = "+probid;
				stmt.executeUpdate(query1);
			}
			else if(reply == null && value.equals("deletesub"))
			{   
				String query = "Delete from " + constiB + "_" + wardB + "_FORUM_SUBPOST where FOR_ID = "+probid+" AND SUB_ID = "+subid+"";
				stmt.executeUpdate(query);
			}
			else
			{
			  PreparedStatement ps=con.prepareStatement("INSERT INTO " + constiB + "_" + wardB + "_FORUM_SUBPOST(FOR_ID, NICKNAME, DESCRIPTION, DATE) VALUES(?, ?, ?, ?)");
			  ps.setLong(1,probid);
		      ps.setString(2,nick);
		      ps.setString(3,descript );
		      ps.setTimestamp(4, stamp);
		        int rs = ps.executeUpdate();
		        
		        ps.close();
				ps=null;  
			}
			
			
			stmt.close();
			stmt=null;
			stmt1.close();
			stmt1=null;
			con.close();
			con=null;
			
			RequestDispatcher view = request.getRequestDispatcher("./Forum_main?consti="+constiB+"&ward="+wardB);
			view.forward(request, response);
		}
		catch(Exception e)
		{
			out.println(e);
		}
		
		
	}   	  	    
}