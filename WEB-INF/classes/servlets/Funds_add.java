package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Enumeration;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Funds_add
 *
 */
 public class Funds_add extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Funds_add() {
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
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		long cost = Long.parseLong(request.getParameter("cost"));
		java.sql.Timestamp stamp = new java.sql.Timestamp(new java.util.Date().getTime());
		long id;
		
		if(privstr == null || (!type.equalsIgnoreCase("ADMIN") && !constiA.equals(constiB) )  )
		{
			priv = '1';
		}
		else
		{
			priv=privstr.charAt(3);
		}
		
		
		try {
	        InitialContext ctx = new InitialContext();
	        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
	        Connection con = ds.getConnection();
	        String url = constiB +"_FUNDS";
	        PreparedStatement ps=con.prepareStatement("INSERT INTO "+url+"(NICKNAME, DESCRIPTION, COST, DATE, TITLE) VALUES(?, ?, ?, ?, ?)");
	        ps.setString(1,nick);
	        ps.setString(2, description);
	        ps.setLong(3, cost);
	        ps.setTimestamp(4, stamp);
	        ps.setString(5, title);
	        int rs1 = ps.executeUpdate();
	        //RequestDispatcher view = request.getRequestDispatcher("./home.jsp?consti="+consti);
	        //view.forward(request, response);
	        //response.sendRedirect("./Home_load?consti="+consti);
	        
			ps.close();
			ps=null;
			con.close();
			con=null;
			
	        if(wardB==null){
				RequestDispatcher view = request
						.getRequestDispatcher("./Funds_main?consti="+constiB);
				view.forward(request, response);
				}
				else
				{
					RequestDispatcher view = request
					.getRequestDispatcher("./Funds_main?consti="+constiB+"&ward="+wardB);
			view.forward(request, response);
				}
	        
		}catch(Exception e){}
	}   	  	    
}