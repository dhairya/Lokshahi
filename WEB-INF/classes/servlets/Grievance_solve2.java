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

import beans.Mailbean;

/**
 * Servlet implementation class for Servlet: Grievance
 * 
 */
public class Grievance_solve2 extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Grievance_solve2() {
		super();
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
	 *      HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		//Mailbean mail = new Mailbean();
		String user = (String) session.getAttribute("User");
		String privstr = (String) session.getAttribute("Access");
		String type = (String) session.getAttribute("Type");
		String constiA = (String) session.getAttribute("Consti");
		String wards = (String) session.getAttribute("Wards");
		String categories = (String) session.getAttribute("Categories");
		String nick = (String) session.getAttribute("Nick");
		
		char priv;
		
		long probid = Long.parseLong(request.getParameter("refid"));
		String reply = request.getParameter("Reply");
		int status = Integer.parseInt(request.getParameter("Status"));
		status=(status/10)*10 + 2;
		
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
	        String url = constiB + "_" + wardB + "_GRIEVANCES";
	        PreparedStatement ps=con.prepareStatement("UPDATE "+url+" SET STATUS=?, REPLY=? WHERE REF_ID=?");
	        ps.setInt(1, status);
	        ps.setString(2, reply);
	        ps.setLong(3,probid);
	        ps.executeUpdate();
	        ps.close();
	        ps = con.prepareStatement("select NICKNAME from " + constiB + "_" + wardB + "_GRIEVANCES " +
	        		"where REF_ID = ?  union select NICKNAME from " + constiB +
	        		"_" + wardB + "_VOTERS_JOINED WHERE REF_ID=?)");
	        ps.setLong(1,probid);
	        ps.setLong(2,probid);
	        ResultSet rs = ps.executeQuery();
	        
	        String nicks = null;
	        int cnt = 0;
	        while(rs.next()){
	        	String tempnick = rs.getString("NICKNAME");
	        	int pos=tempnick.indexOf('-');
	        	tempnick = tempnick.substring(pos+1);
	        	nicks = nicks +"'"+ tempnick +"',";
	        	cnt++;
	        }
	        
	        nicks = nicks.substring(0, nicks.length() - 1);
	        
	        
	        String celllist[] = new String[cnt];
	        cnt=0;
	        
	        PreparedStatement ps1 = con.prepareStatement("select CELL from "+constiB + "_" + wardB + "_USER_DETAILS" +
	        		" where NICKNAME IN "+ nicks);
	        ResultSet rs1 = ps1.executeQuery();
	        
	        while(rs1.next()){
	        	celllist[cnt]=rs1.getString("CELL");
	        	cnt++;
	        }
	        
	        ps1.close();
	        
	        ps1 = con.prepareStatement("select PHONE from "+constiB + "_" + wardB + "_specialusers" +
	        		" where LOGIN IN "+ nicks);
	        rs1 = ps1.executeQuery();
	        
	        while(rs1.next()){
	        	celllist[cnt]=rs1.getString("PHONE");
	        	cnt++;
	        }
	        
	        ps1.close();
	        
	        ps1 = con.prepareStatement("select PHONE from "+constiB +  "_specialusers" +
	        		" where LOGIN IN "+ nicks);
	        rs1 = ps1.executeQuery();
	        
	        while(rs1.next()){
	        	celllist[cnt]=rs1.getString("PHONE");
	        	cnt++;
	        }
	        
	        ps1.close();
	      
	        
	        //Now celllist contains list of all cell nos
	        //Sending SMS
	        String msg = "Grievance ID:"+probid+" Solved"; 
			Classes.SMS send = new Classes.SMS();
	        
			for(int i=0;i<celllist.length;i++)
	        {
				 send.SMSsend(msg, celllist[i]);
	        }
	        
	        
	        //The e-mail ID of the person who has created 
	        //the problem and persons who have joined the 
			//problem can be obtained xactly in the same way as cell nos are obtained
	        //Mail is to be sent to all of these
	        /*String emaillist[];
	        int c = 0;
	        while(rs.next())
	        {
	        	c++;
	        }
	        emaillist = new String[c];
	        c=0;
	        rs = ps.executeQuery();
	        while(rs.next())
	        {
	        	emaillist[c] = rs.getString("EMAIL");
	        	c++;
	        }
	        mail.setTo(emaillist);
	        mail.setSubject("ppl2.0: Grievance solved");
	        mail.setMessage("Dear user\n The grievance with Reference id no."+probid+" has been solved by the officials." +
	        		"You may login to the site and check the status. You may even challenge the claim of the officials.");
	        mail.SendMail();*/
			
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
	        
	        response.sendRedirect("./Grievance_main?consti="+constiB+"&ward="+wardB);
	    } catch(Exception e) {}

	}}