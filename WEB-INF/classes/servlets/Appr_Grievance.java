package servlets;

import java.io.IOException;
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

import Classes.Intelli_rating;

/**
 * Servlet implementation class for Servlet: Approve_Grievance
 *
 */
 public class Appr_Grievance extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Appr_Grievance() {
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
		
		
		String command = request.getParameter("Command");
		long probid = Long.parseLong(request.getParameter("ProbID"));
		String constiB = (String)request.getParameter("consti");
		String wardB = (String)request.getParameter("ward");
		
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
			priv=privstr.charAt(7);
		}
		
		try {
	        InitialContext ctx = new InitialContext();
	        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
	        Connection con = ds.getConnection();
		if(command.equals("Discard"))
		{
			String url = constiB + "_" + wardB + "_TEMPGRIEVANCES";
	        PreparedStatement ps=con.prepareStatement("DELETE from "+url+" where REF_ID=?");
	        ps.setLong(1,probid);
	        int rs=ps.executeUpdate();
	        
			ps.close();
			ps=null;
			con.close();
			con = null;
	        
	        RequestDispatcher view = request.getRequestDispatcher("./Appr_Grievance_main?consti="+constiB+"&ward="+wardB);
			view.forward(request, response);
		}
		else if(command.equals("Approve"))
		{
			String url = constiB + "_" + wardB + "_TEMPGRIEVANCES";
	        
	        PreparedStatement ps=con.prepareStatement("select * from "+url+" where REF_ID=?");
	        ps.setLong(1,probid);
	        ResultSet rs=ps.executeQuery();
			rs.next();
			
			String url1 = constiB + "_" + wardB +"_GRIEVANCES";
			PreparedStatement ps1=con.prepareStatement("INSERT INTO "+url1+"(NICKNAME, DESCRIPTION, TITLE, DATE, DEPARTMENT) VALUES(?, ?, ?, ?, ?)");
		    ps1.setString(1,rs.getString("NICKNAME"));
		    ps1.setString(2,rs.getString("DESCRIPTION"));
		    ps1.setString(3, rs.getString("TITLE"));
		    ps1.setTimestamp(4, rs.getTimestamp("DATE"));
		    ps1.setString(5, rs.getString("DEPARTMENT"));
		    int rs1 = ps1.executeUpdate();
		    
		    PreparedStatement ps2=con.prepareStatement("DELETE from "+url+" where REF_ID=?");
	        ps2.setLong(1,probid);
	        int rs2=ps2.executeUpdate();
	        
	        rs.close();
			rs=null;
			ps.close();
			ps=null;
			ps1.close();
			ps1=null;
			ps2.close();
			ps2=null;
			con.close();
			con = null;
		        
	        RequestDispatcher view = request.getRequestDispatcher("./Appr_Grievance_main?consti="+constiB+"&ward="+wardB);
			view.forward(request, response);
			
			
		}
		
	 }catch(Exception e){
	 }	
	}   	  	    
}