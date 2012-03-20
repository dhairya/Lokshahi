package servlets;
import beans.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.mail.MessagingException;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Forgotpwd
 *
 */
 public class Forgotpwd extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Forgotpwd() {
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
		String username = (String)request.getParameter("username");
		String cons = request.getParameter("cons");
		String ward = request.getParameter("ward"); 
		String email = null;
		String cell =null;
		String password = null;
		ResultSet rs = null,rs1;
	//	Mailbean mail = new Mailbean();
		
		Connection con;
		Statement stmt, stmt1;
		
		
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt = con.createStatement();
			 stmt1 = con.createStatement();
			 
			 if(username.equals("ADMIN") || username.equals("admin"))
			 {
				 String query = "UPDATE GLOBAL_DATA SET PASSWORD='ADMIN' WHERE USERNAME='ADMIN' OR USERNAME='admin'";
				 stmt.executeUpdate(query);
				 request.setAttribute("msg","admin");
				 RequestDispatcher view = request.getRequestDispatcher("./msghandler.jsp");
					view.forward(request, response);
					rs.close();
					 stmt.close();
					 stmt1.close();
					 con.close();

			 }
			 else if(username.startsWith("C"))
			 {
				 cons = username.substring(1,4);
				 String query = "SELECT PASSWORD, EMAILID, PHONE FROM "+cons+"_SPECIALUSERS "+
				 				" WHERE LOGIN = '"+username+"'";
				 rs = stmt.executeQuery(query);
				 rs.next();
				 email = rs.getString("EMAILID");
				 password = rs.getString("PASSWORD");
				 cell = rs.getString("PHONE");
				 request.setAttribute("msg","other");
				 

			 }
			 else if(username.startsWith("W"))
			 {
				 cons = username.substring(1,4);
				 ward = username.substring(4,7);
				 String query = "SELECT PASSWORD, EMAILID,PHONE FROM "+cons+"_"+ward+"_SPECIALUSERS "+
	 							" WHERE LOGIN = '"+username+"'";
				 rs = stmt.executeQuery(query);
				 rs.next();
				 email = rs.getString("EMAILID");
				 cell = rs.getString("PHONE");
				 password = rs.getString("PASSWORD");
				 request.setAttribute("msg","other");
			 }
			 else
			 {
				 String consti_ward = cons+"_"+ward;
				 String query1 = "SELECT PASSWORD FROM GLOBAL WHERE VOTERID='"+username+"' AND CONSTI_WARD='"+consti_ward+"'";
				 rs1 = stmt1.executeQuery(query1);
				 
				 if(rs1.next())
				 {
					 String query = "SELECT EMAIL, PHONE, CELL FROM "+consti_ward+"_USER_DETAILS WHERE VOTERID='"+username+"'";
					 rs = stmt.executeQuery(query);
					 
					 rs.next();
					 
					 email = rs.getString("EMAIL");
					 cell = rs.getString("CELL");
					 password = rs1.getString("PASSWORD");
					 request.setAttribute("msg","other");
				 }
				 else
				 {
					 request.setAttribute("msg","not exists");
				 }
				 
				 
			 }
			 
			 String msg = "Your Password:"+password; 
			 Classes.SMS send = new Classes.SMS();
			 send.SMSsend(msg, cell);
		
			 /*String emaillist[] = new String[1];
			 emaillist[0]=email;
			 mail.setTo(emaillist);
			 mail.setSubject("ppl2.0: Forgot password");
			 mail.setMessage("Dear user, \n Your ppl2.0 login id is "+username+" and password is "+password+". You can now login to ppl2.0 and access your account.");
		     mail.SendMail();*/
		     
		     RequestDispatcher view = request.getRequestDispatcher("./msghandler.jsp");
				view.forward(request, response);
				rs.close();
				 stmt.close();
				 stmt1.close();
				 con.close();

		}
		catch(Exception e)
		{
			
		}
		
	}   	  	    
}