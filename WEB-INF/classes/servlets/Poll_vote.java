package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Grievance
 *
 */
 public class Poll_vote extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Poll_vote() {
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
		long pollid = Long.parseLong(request.getParameter("pollid"));
		String opt = request.getParameter("option");
		String command = request.getParameter("Command");
		
		Boolean ward_belong; 
		
		if(wards==null)
		{	
			ward_belong = false;
		}
		else
		{
			ward_belong= wards.contains(wardB) || wards.equalsIgnoreCase("ALL");
		}

		
		if(privstr == null || (!type.equalsIgnoreCase("ADMIN") && (!constiA.equals(constiB) || !ward_belong))  )
		{
			priv = '1';
		}
		else
		{
			priv=privstr.charAt(2);
		}
		
		try {
	        InitialContext ctx = new InitialContext();
	        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
	        Connection con = ds.getConnection();
	        String url = constiB + "_" + wardB + "_POLL";
	        if(command.equals("Vote")){
	        String url1="OPT"+opt+"_CNT";
	        PreparedStatement ps=con.prepareStatement("UPDATE "+url+" SET "+url1+" = "+url1+" + 1 WHERE POLLNO=?");
	        ps.setLong(1,pollid);
	        int rs=ps.executeUpdate();
	        ps.close();
			ps=null;
	        }
	        else if(command.equals("Delete")){
	        	PreparedStatement ps=con.prepareStatement("DELETE from "+url+" where POLLNO=?");
		        ps.setLong(1,pollid);
		        int rs=ps.executeUpdate();
		        ps.close();
				ps=null;
	        	
	        }

			
			con.close();
			con=null;
			
	        //RequestDispatcher view = request.getRequestDispatcher("./"+priv+"/view_poll_arch.jsp?consti="+consti);       
	        //view.forward(request, response);
	        response.sendRedirect("/CMS/Poll_main?consti="+constiB+"&ward="+wardB);
	    } catch(Exception e) {}

	}   	  	    
}