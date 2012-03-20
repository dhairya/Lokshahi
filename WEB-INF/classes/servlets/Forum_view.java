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
 * Servlet implementation class for Servlet: Forum_view
 *
 */
 public class Forum_view extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Forum_view() {
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
		
		Connection con;
		Statement stmt;
		Statement stmt1;
		ResultSet rs;
		ResultSet rs1;
		
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
		
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			con = ds.getConnection();
			stmt = con.createStatement();
			stmt1 = con.createStatement();
			String url = constiB + "_" + wardB +"_FORUM";
				String query = "select * from " + constiB + "_" + wardB +"_FORUM where FOR_ID = "+probid;
				rs = stmt.executeQuery(query);
				
				String query1 = "select * from " + constiB + "_" + wardB +"_FORUM_SUBPOST where FOR_ID = "+probid;
				rs1 = stmt1.executeQuery(query1);
			
				request.setAttribute("original_post",rs);
				request.setAttribute("sub_post",rs1);
				 RequestDispatcher view = request.getRequestDispatcher("./"+priv+"/view_post.jsp?consti="+constiB+"&ward="+wardB);       
			        view.forward(request, response);
			        
			        rs.close();
					rs=null;
					rs1.close();
					rs1=null;
					stmt.close();
					stmt=null;
					stmt1.close();
					stmt1=null;
					con.close();
					con=null;
			}
		catch(Exception e)
		{
			
		}
	}   	  	    
}