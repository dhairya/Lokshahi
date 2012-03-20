package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.Mailbean;

/**
 * Servlet implementation class for Servlet: Grievance_add
 *
 */
 public class Grievance_add extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Grievance_add() {
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
		 Statement stmt = null;
		 //Mailbean mail = new Mailbean();
		 
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
			String problem = request.getParameter("grev");
			String title = request.getParameter("grev_title");
			String dept = request.getParameter("dept");
			
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
		
		java.sql.Timestamp stamp = new java.sql.Timestamp(new java.util.Date().getTime());
		try {
	        InitialContext ctx = new InitialContext();
	        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
	        Connection con = ds.getConnection();
	        stmt = con.createStatement();
	        ResultSet rsl;
	        
	        String url = constiB + "_" + wardB + "_TEMPGRIEVANCES";
	        PreparedStatement ps=con.prepareStatement("INSERT INTO "+url+"(NICKNAME, DESCRIPTION, TITLE, DATE, DEPARTMENT) VALUES(?, ?, ?, ?, ?)");
	        ps.setString(1,nick);
	        ps.setString(2,problem);
	        ps.setString(3, title);
	        ps.setTimestamp(4, stamp);
	        ps.setString(5, dept);
	        int rs = ps.executeUpdate();
	        
	        //Code to send mail to all members of ward
	       /* String query = "SELECT VOTERID,EMAIL FROM DB2ADMIN." + constiB + "_" + wardB + "_USER_DETAILS";
	        rsl = stmt.executeQuery(query);
	        String emaillist[];
	        int c = 0;
	        
	        while(rsl.next())
	        {	
	        	c++;
	        }
	        
	        emaillist = new String[c];
	        c=0;
	        rsl = stmt.executeQuery(query);
	        while(rsl.next())
	        {
	        	emaillist[c] = rsl.getString("EMAIL");
	        	c++;
	        }
	       
	        mail.setTo(emaillist);
	        mail.setSubject("ppl2.0: New Grievance Posted");
	        mail.setMessage("Dear user\n" +
	        		" a new Grievance has been posted by "+nick+" in your constituency." +
	        		" The Grievance is: "+title+".\n For more information about this grievance" +
	        		"check out the Grievance Zone of your constituency. You may support the issue by joining it and rating it");
	        mail.SendMail();*/
	   
	        ps.close();
			ps=null;
			con.close();
			con=null;
	        
	        RequestDispatcher view = request.getRequestDispatcher("./Grievance_main?consti="+constiB+"&ward="+wardB);
	        view.forward(request, response);
		}catch(Exception e){}
	}   	  	    
}