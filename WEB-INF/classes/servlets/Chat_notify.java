package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.Enumeration;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Chat_notify
 *
 */
 public class Chat_notify extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Chat_notify() {
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
		String wardB=null;
		for (Enumeration e = request.getParameterNames() ; e.hasMoreElements() ;) {
	         if(e.nextElement().toString().equals("ward"))
	         {
	        	 wardB= (String)request.getParameter("ward");
	         }
		}
		
		String date = (String)request.getParameter("chatd");
		String time = (String)request.getParameter("chatt");
		
		
		
		Connection con;
		Statement stmt;
		
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			con = ds.getConnection();
			stmt = con.createStatement();
			String query = "UPDATE "+constiB+"_CHAT SET(LEADER_DATE, LEADER_TIME) = " +
					" ('"+date+"','"+time+"') WHERE USERNAME = 'LEADER'";
			stmt.executeUpdate(query);
			
			
			stmt.close();
			stmt=null;
			con.close();
			con = null;
		}
		catch(Exception e)
		{
			
		}
		
	    request.setAttribute("set","set");
	    
	    if(wardB==null){
			RequestDispatcher view = request
					.getRequestDispatcher("./chat_notify.jsp?consti="+constiB);
			view.forward(request, response);
			}
			else
			{
				RequestDispatcher view = request
				.getRequestDispatcher("./chat_notify.jsp?consti="+constiB+"&ward="+wardB);
		view.forward(request, response);
			}
	}   	  	    
}