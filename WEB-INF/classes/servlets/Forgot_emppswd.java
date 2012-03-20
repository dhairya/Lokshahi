package servlets;

import java.io.IOException;
import beans.*;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet implementation class for Servlet: Forgot_emppswd
 *
 */
 public class Forgot_emppswd extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Forgot_emppswd() {
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

		Connection con;
		Statement stmt;
		ResultSet rs;
		String userid= (String)request.getParameter("empid");
		//Mailbean mail = new Mailbean();		
		
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt=con.createStatement();
			 
			 //extract email id
			 String query= "SELECT EMAIL_ID,PASSWORD,PHONE_NO FROM COMPANY_DETAILS WHERE USER_ID = '"+ userid +"'";
			 rs=stmt.executeQuery(query);
			 if(rs.next()){
			 String emailid= (String)rs.getString("EMAIL_ID");
			 String password= (String)rs.getString("PASSWORD");
			 String cell= (String)rs.getString("PHONE_NO");
			 
			 String msg = "Your Password:"+password; 
			 Classes.SMS send = new Classes.SMS();
			 send.SMSsend(msg, cell);
			 
			 //send mail
			/* String emaillist[] = new String[1];
			 emaillist[0]=emailid;
			 mail.setTo(emaillist);
			 mail.setSubject("ppl2.0: PASSWORD RETRIVED");
			 mail.setMessage("Dear user your user id is " + userid +"  and password is " +password +"." +
			"You can now login into ppl2.0 with your user id and password and access your account");
			 mail.SendMail();*/
			 }
			 //redirect
			 
			 	rs.close();
				rs=null;
				stmt.close();
				stmt=null;
				con.close();
				con = null;
			 RequestDispatcher view = request.getRequestDispatcher("./index.jsp");
			 view.forward(request, response);
		 }
		
		catch (Exception e){}
	}
 }
