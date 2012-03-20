package servlets;

import Classes.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


/**
 * Servlet implementation class for Servlet: Grievance_change
 *
 */
 public class Grievance_change extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Grievance_change() {
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
		if(command == null)
		{
			command = request.getParameter("Command1");
		}
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

		
		if(privstr == null || (!type.equalsIgnoreCase("ADMIN") && (!constiA.equals(constiB) || !ward_belong))  )
		{
			priv = '1';
		}
		else
		{
			priv=privstr.charAt(0);
		}
		
		try {
	        InitialContext ctx = new InitialContext();
	        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
	        Connection con = ds.getConnection();
		if(command.equals("Delete"))
		{
			String url = constiB + "_" + wardB + "_GRIEVANCES";
	        PreparedStatement ps=con.prepareStatement("DELETE from "+url+" where REF_ID=?");
	        ps.setLong(1,probid);
	        int rs=ps.executeUpdate();
	        
	  
			ps.close();
			ps=null;
			con.close();
			con=null;
	  
			RequestDispatcher view = request.getRequestDispatcher("./Grievance_main?consti="+constiB+"&ward="+wardB);
			view.forward(request, response);
		}
		else if(command.equals("Join"))
		{
			java.sql.Timestamp stamp = new java.sql.Timestamp(new java.util.Date().getTime());
			String url = constiB + "_" + wardB +"_GRIEVANCES";
	        PreparedStatement ps=con.prepareStatement("UPDATE "+url+" SET NUM_PPL_JOINED = NUM_PPL_JOINED + 1 WHERE REF_ID=?");
	        ps.setLong(1,probid);
	        int rs=ps.executeUpdate();
	        url = constiB + "_" + wardB +"_VOTERS_JOINED";
	        PreparedStatement ps1=con.prepareStatement("INSERT INTO "+url+"(REF_ID, NICKNAME, DATE) VALUES(?, ?, ?)");
	        ps1.setLong(1, probid);
	        ps1.setString(2,nick);
	        ps1.setTimestamp(3, stamp);
	        int rs1=ps1.executeUpdate();
	        
			ps.close();
			ps=null;
			ps1.close();
			ps1=null;
			con.close();
			con=null;
	        
			RequestDispatcher view = request.getRequestDispatcher("./Grievance_main?consti="+constiB+"&ward="+wardB);
			view.forward(request, response);
		}
		else if(command.equals("Challenge"))
		{	
			int status = Integer.parseInt(request.getParameter("Status"));
			String url1 = constiB + "_" + wardB + "_GRIEVANCES";
			PreparedStatement ps2=con.prepareStatement("SELECT STATUS FROM "+url1+" where REF_ID=?");
			ps2.setLong(1,probid);
			ResultSet rs2=ps2.executeQuery();
			rs2.next();
			int stat = rs2.getInt("STATUS");
			
			if(stat==status){
				java.sql.Timestamp stamp = new java.sql.Timestamp(new java.util.Date().getTime());
	        status+=8;
	        String url = constiB + "_" + wardB + "_INVESTIGATION";
	        PreparedStatement ps=con.prepareStatement("INSERT INTO "+url+"(REF_ID, NICKNAME, DATE) VALUES(?, ?, ?)");
	        ps.setLong(1,probid);
	        ps.setString(2,nick);
	        ps.setTimestamp(3, stamp);
	        int rs = ps.executeUpdate();
	        url = constiB + "_" + wardB + "_GRIEVANCES";
	        PreparedStatement ps1=con.prepareStatement("UPDATE "+url+" SET STATUS = ? WHERE REF_ID = ?");
	        ps1.setInt(1, status);
	        ps1.setLong(2,probid);
	        int rs1=ps1.executeUpdate();
	        
	        ps.close();
			ps=null;
			ps1.close();
			ps1=null;
			
			}
			
			
			ps2.close();
			ps2=null;
			con.close();
			con=null;
			
			RequestDispatcher view = request.getRequestDispatcher("./Grievance_main?consti="+constiB+"&ward="+wardB);
			view.forward(request, response);
		}
		else if(command.equals("Add_comm"))
		{
			String comments = request.getParameter("Comments");
			java.sql.Timestamp stamp = new java.sql.Timestamp(new java.util.Date().getTime());
			String url = constiB + "_" + wardB + "_GRIEVANCE_COMMENTS";
	        PreparedStatement ps=con.prepareStatement("INSERT INTO "+url+"(REF_ID, NICKNAME, COMMENTS, DATE) VALUES(?, ?, ?, ?)");
	        ps.setLong(1,probid);
	        ps.setString(2,nick);
	        ps.setString(3, comments);
	        ps.setTimestamp(4, stamp);
	        int rs = ps.executeUpdate();
	        
	        ps.close();
			ps=null;
			con.close();
			con=null;
			
	        RequestDispatcher view = request.getRequestDispatcher("./Grievance_main?consti="+constiB+"&ward="+wardB);
			view.forward(request, response);
		}
		else if(command.equals("Rate"))
		{
			int rating = Integer.parseInt(request.getParameter("Rate"));
			if(type.equals("ADMIN"))
			{
				String url = constiB + "_" + wardB + "_GRIEVANCES";
		        PreparedStatement ps=con.prepareStatement("UPDATE "+url+" SET ADMIN_RATING = "+rating+" WHERE REF_ID=?");
		        ps.setLong(1,probid);
		        int rs=ps.executeUpdate();
		        PreparedStatement ps1=con.prepareStatement("SELECT * FROM "+url+" where REF_ID=?");  
		        ps1.setLong(1,probid);
		        ResultSet rs1=ps1.executeQuery();
		        Intelli_rating rateobj = new Intelli_rating(rs1);
		        //here changed
		        rateobj.setValues(constiB,wardB,probid);
		        float intelli_rating = rateobj.rating();
		        //
		        PreparedStatement ps2=con.prepareStatement("UPDATE "+url+" SET INTELLI_RATING = "+intelli_rating+" WHERE REF_ID=?");
		        ps2.setLong(1,probid);
		        int rs2=ps2.executeUpdate();
				rs1.close();
				rs1=null;		        
		        ps.close();
				ps=null;
				ps1.close();
				ps1=null;
				ps2.close();
				ps2=null;
				con.close();
				con=null;
			}
			else
			{	
				String url = constiB + "_" + wardB + "_GRIEVANCES";
		        PreparedStatement ps=con.prepareStatement("UPDATE "+url+" SET PUBLIC_RATING = (NUM_PPL_RATED * PUBLIC_RATING + ?)/(NUM_PPL_RATED + 1), NUM_PPL_RATED = NUM_PPL_RATED + 1 WHERE REF_ID=?");
		        ps.setInt(1, rating);
		        ps.setLong(2,probid);
		        int rs=ps.executeUpdate();
		        PreparedStatement ps1=con.prepareStatement("SELECT * FROM "+url+" where REF_ID=?");  
		        ps1.setLong(1,probid);
		        ResultSet rs1=ps1.executeQuery();
		        Intelli_rating rateobj = new Intelli_rating(rs1);
		        //here again
		        rateobj.setValues(constiB,wardB,probid);
		        float intelli_rating = rateobj.rating();
		        //
		       
		        PreparedStatement ps2=con.prepareStatement("UPDATE "+url+" SET INTELLI_RATING = ? WHERE REF_ID=?");
		        ps2.setFloat(1, intelli_rating);
		        ps2.setLong(2,probid);
		        int rs2=ps2.executeUpdate();
		        
				rs1.close();
				rs1=null;
		        ps.close();
				ps=null;
				ps1.close();
				ps1=null;
				ps2.close();
				ps2=null;
				con.close();
				con=null;
			}		
			RequestDispatcher view = request.getRequestDispatcher("./Grievance_main?consti="+constiB+"&ward="+wardB);
			view.forward(request, response);
		}
		else if(command.equals("Edit"))
		{
			String description = request.getParameter("Description");
			String url = constiB + "_" + wardB + "_GRIEVANCES";
	        PreparedStatement ps=con.prepareStatement("UPDATE "+url+" SET  DESCRIPTION = ? WHERE REF_ID=?");
	        ps.setString(1,description);
	        ps.setLong(2,probid);
	        int rs = ps.executeUpdate();
	        
	        ps.close();
			ps=null;
			con.close();
			con=null;
			
	        RequestDispatcher view = request.getRequestDispatcher("./Grievance_main?consti="+constiB+"&ward="+wardB);
			view.forward(request, response);
			
			
		}
		else if(command.equals("Solve"))
		{
			String url = constiB + "_" + wardB + "_GRIEVANCES";
	        PreparedStatement ps=con.prepareStatement("select * from "+url+" where REF_ID=?");
	        ps.setLong(1,probid);
	        ResultSet rs=ps.executeQuery();
	        request.setAttribute("prob", rs);
	        RequestDispatcher view = request.getRequestDispatcher("./"+priv+"/solve_grievances.jsp?consti="+constiB+"&ward="+wardB);       
	        view.forward(request, response);
	        
	        rs.close();
	        rs=null;
	        ps.close();
			ps=null;
			con.close();
			con=null;
		}
		
		
	 }catch(Exception e){
	 }	
	 
	}   	  	    
}