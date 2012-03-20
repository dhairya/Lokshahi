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
 public class Grievance_view extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Grievance_view() {
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
		long probid = Long.parseLong(request.getParameter("refid"));
		int flag=0,joined=0,challenged=0;
		
		
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
	        String url = constiB + "_" + wardB + "_GRIEVANCES";
	        
	        if(priv=='5' || priv=='6'){
	        	int status = Integer.parseInt(request.getParameter("status"));
	        	if(status!=2 && status!=12 && status!=22 && status!=32 && status!=40){
	        	status=(status/10)*10 + 1;
	        	PreparedStatement ps=con.prepareStatement("UPDATE "+url+" SET STATUS=? WHERE REF_ID=?");
		        ps.setInt(1, status);
		        ps.setLong(2,probid);
		        ps.executeUpdate();
		        ps.close();
		        ps=null;
	        	 }
	        }
	        
	        PreparedStatement ps=con.prepareStatement("select * from "+url+" where REF_ID=?");
	        ps.setLong(1,probid);
	        ResultSet rs=ps.executeQuery();
	        
	        url = constiB + "_" + wardB + "_GRIEVANCE_COMMENTS";
	        PreparedStatement ps1=con.prepareStatement("select * from "+url+" where REF_ID=? ORDER BY DATE DESC");
	        ps1.setLong(1,probid);
	        ResultSet rs1=ps1.executeQuery();
	        
	        if(flag == 0 && priv!='1'){
	        	url = constiB + "_" + wardB +"_VOTERS_JOINED";
	        	PreparedStatement ps2=con.prepareStatement("select * from "+url+" where REF_ID=? AND NICKNAME=?");
		        ps2.setLong(1,probid);
		        ps2.setString(2,nick);
		        ResultSet rs2=ps2.executeQuery();
		        if(!rs2.next()){
		        	joined=1;
		        }
		        ps2.close();
		        url = constiB + "_" + wardB +"_INVESTIGATION";
	        	ps2=con.prepareStatement("select * from "+url+" where REF_ID=? AND NICKNAME=?");
		        ps2.setLong(1,probid);
		        ps2.setString(2,nick);
		        rs2=ps2.executeQuery();
		        if(!rs2.next()){
		        	challenged=1;
		        }
		        
		        rs2.close();
		        rs2=null;
		        ps2.close();
		        ps2=null;
		        
	        }
	        request.setAttribute("join", joined);
	        request.setAttribute("challenge", challenged);
	        request.setAttribute("prob", rs);
	        request.setAttribute("comments", rs1);
	        
	        
	        
	        RequestDispatcher view = request.getRequestDispatcher("./"+priv+"/view_grievance.jsp?consti="+constiB+"&ward="+wardB);       
	        view.forward(request, response);
	        
	        rs.close();
			rs=null;
			rs1.close();
			rs1=null;
			ps.close();
			ps=null;
			ps1.close();
			ps1=null;
			con.close();
			con=null;
			
	    } catch(Exception e) {
	    	System.out.println(e);
	    }

	}   	  	    
}