package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import beans.*;
/**
 * Servlet implementation class for Servlet: Add_newUser
 *
 */
 public class Add_newUser extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Add_newUser() {
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
		String categories ="";
		String usertype = request.getParameter("utype");
		String belong = request.getParameter("belong");
		String cons = request.getParameter("cons");
		String wards[] = request.getParameterValues("wards");
		String ward = request.getParameter("ward");
		String cat[] = request.getParameterValues("cat");
		if(cat != null)
		{
			for(int i=0;i<cat.length;i++)
			{
				categories = categories + cat[i] + ","; 
			}
		}
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String login, temp, wlist="";
		Connection con;
		Statement stmt1, stmt;
		ResultSet rs, rs1;
		Statement stmt2;
		String access;
		Mailbean mail = new Mailbean();
		long id;
		
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt = con.createStatement();
			 stmt1 = con.createStatement();
			 stmt2 = con.createStatement();
			 
			 String query2 = "SELECT ACCESS_INFO FROM TEMPLATES WHERE NAME='"+usertype+"'";
			 rs=stmt.executeQuery(query2);
			 rs.next();
			 access = rs.getString("ACCESS_INFO");
			 if(belong.equals("w"))
			 {
				 //String query = "SELECT USER_ID FROM "+cons+"_"+ward+"_SPECIALUSERS ORDER BY USER_ID DESC FETCH FIRST 1 ROW ONLY";
				 String query = "SELECT USER_ID FROM "+cons+"_"+ward+"_SPECIALUSERS ORDER BY USER_ID DESC LIMIT 1";
				 rs1 = stmt1.executeQuery(query);
				 if(rs1.next())
				 {
				  id = rs1.getLong("USER_ID");
				  id = id+1;
				  temp = Long.toString(id);
				  login = "W"+cons+ward+temp;
				 }
				 else
				 {
					 login = "W"+cons+ward+1;
				 }
				 String query1 = "INSERT INTO "+cons+"_"+ward+"_SPECIALUSERS (NAME, LOGIN, PASSWORD, ACCESS_INFO, EMAILID, PHONE, USERTYPE, SOLVABLE_CATEGORIES) "+
				                 " VALUES ('"+name+"','"+login+"','ppl2.0','"+access+"','"+email+"','"+phone+"','"+usertype+"','"+categories+"')";
				 stmt2.executeUpdate(query1);
			 }
			 else
			 {
				 for(int i=0;i<wards.length;i++)
					{
						wlist = wlist + wards[i] + ","; 
					} 
				 //String query = "SELECT USER_ID FROM "+cons+"_SPECIALUSERS ORDER BY USER_ID DESC FETCH FIRST 1 ROW ONLY";
				 String query = "SELECT USER_ID FROM "+cons+"_SPECIALUSERS ORDER BY USER_ID DESC LIMIT 1";
				 rs1 = stmt1.executeQuery(query);
				 if(rs1.next())
				 {
				  id = rs1.getLong("USER_ID");
				  id = id+1;
				  temp = Long.toString(id);
				  login = "C"+cons+temp;
				 }
				 else
				 {
					 login = "C"+cons+1;
				 }
				 
				 String query1 = "INSERT INTO "+cons+"_SPECIALUSERS (NAME, LOGIN, PASSWORD, ACCESS_INFO, EMAILID, PHONE, USERTYPE, SOLVABLE_CATEGORIES, ACCESS_WARDS) "+
                 " VALUES ('"+name+"','"+login+"','ppl2.0','"+access+"','"+email+"','"+phone+"','"+usertype+"','"+categories+"','"+wlist+"')";
                 stmt2.executeUpdate(query1);
			 }
			 
			 String emaillist[] = new String[1];
			 emaillist[0]=email;
			 mail.setTo(emaillist);
			 mail.setSubject("ppl2.0: ID and Password");
			 mail.setMessage("Dear user, \n You have been added as a "+usertype+" in your constituency/ward.\n"+
					         "Your login id is "+login+" and passward is ppl2.0.\n You can change your password after logging in.");
		     mail.SendMail();
		     
		     
		     String msg = "Registered successfully. UserID:"+login+"Password:ppl2.0"; 
			 Classes.SMS send = new Classes.SMS();
			 send.SMSsend(msg, phone);
			 
			 RequestDispatcher view = request
				.getRequestDispatcher("/ADMIN/cpanel.jsp");
		view.forward(request, response);
		 rs.close();
	 	 rs1.close();
	 	 stmt.close();
	 	 stmt1.close();
	 	 stmt2.close();
	 	 
	 	 con.close();
		}
		catch(Exception e)
		{
			
		}
	}   	  	    
}