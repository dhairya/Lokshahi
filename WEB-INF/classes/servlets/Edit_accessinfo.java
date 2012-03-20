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
 * Servlet implementation class for Servlet: Edit_accessinfo
 *
 */
 public class Edit_accessinfo extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Edit_accessinfo() {
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
		String spl_cons=null, spl_ward=null;
		String type=request.getParameter("type");
		String loginid=request.getParameter("login");
		String consti_ward=request.getParameter("consti_ward");
		String cat[] = request.getParameterValues("cat");
		String wards[] = request.getParameterValues("wards");
		Connection con;
		Statement stmt;
		String categories="",wlist="";
		if(cat !=null){
		for(int i=0;i<cat.length;i++)
		{
			categories = categories + cat[i] + ","; 
		}
		}
		try
		{  
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt = con.createStatement();
			 
			 

			 if(type !=null && type.equals("c"))
			 {
				 spl_cons=loginid.substring(1,4);
				 for(int i=0;i<wards.length;i++)
					{
						wlist = wlist + wards[i] + ","; 
					} 
				 String query ="UPDATE "+spl_cons+"_SPECIALUSERS SET ACCESS_INFO='"+access+"', SOLVABLE_CATEGORIES='"+categories+"', " +
				 			   "ACCESS_WARDS='"+wlist+"' WHERE LOGIN='"+loginid+"'";
				 stmt.executeUpdate(query);
			 }
			 else if(type!=null && type.equals("w"))
			 {
				 spl_cons=loginid.substring(1,4);
				 spl_ward=loginid.substring(4,7);
				 String query ="UPDATE "+spl_cons+"_"+spl_ward+"_SPECIALUSERS SET ACCESS_INFO='"+access+"', SOLVABLE_CATEGORIES='"+categories+"' WHERE LOGIN='"+loginid+"'";
				 stmt.executeUpdate(query);
			 }
			 else
			 {
				 String query = "UPDATE "+consti_ward+"_USER_DETAILS SET ACCESS_INFO='"+access+"', SOLVABLE_CATEGORIES='"+categories+"' WHERE VOTERID='"+loginid+"'";
				 stmt.executeUpdate(query);
			 }
			 RequestDispatcher view = request
     		.getRequestDispatcher("/ADMIN/delUsers.jsp");
             view.forward(request, response);
             stmt.close();
             con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}   	  	    
}