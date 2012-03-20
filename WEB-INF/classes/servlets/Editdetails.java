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
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import beans.Mailbean;

/**
 * Servlet implementation class for Servlet: Editprofile2
 *
 */
 public class Editdetails extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Editdetails() {
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
		String vid = request.getParameter("vid");
		String name = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String cell = request.getParameter("cell");
		String username = (String)session.getAttribute("User");
		String cons = (String)session.getAttribute("Consti");
		String ward = (String)session.getAttribute("Wards");
		Connection con;
		Statement stmt, stmt1;
		//ResultSet rs;
		Mailbean mail = new Mailbean();
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt = con.createStatement();
			 stmt1 = con.createStatement();
			 
			 if(username.equals("ADMIN"))
				{
					if(vid == null)
					{
						String query = "UPDATE GLOBAL_DATA SET PASSWORD='"+pwd+"' WHERE USERNAME='ADMIN' OR USERNAME='admin'";
						stmt.executeUpdate(query);
						stmt.close();
						con.close();
						response.sendRedirect("./ADMIN/cpanel.jsp");

					}
					else
					{
						cons=vid.substring(1,4);
						if(vid.charAt(0)=='C')
						{
							String query = "UPDATE "+cons+"_SPECIALUSERS SET NAME='"+name+"', PASSWORD='"+pwd+"'," +
										   " EMAILID='"+email+"', PHONE='"+phone+"'WHERE LOGIN='"+vid+"'";
							stmt.executeUpdate(query);
							stmt.close();
							con.close();
							response.sendRedirect("./ADMIN/cpanel.jsp");

						}
						else
						{
							ward=vid.substring(4,7);
							String query = "UPDATE "+cons+"_"+ward+"_SPECIALUSERS SET NAME='"+name+"', PASSWORD='"+pwd+"'," +
										   " EMAILID='"+email+"', PHONE='"+phone+"'WHERE LOGIN='"+vid+"'";
							stmt.executeUpdate(query);
							stmt.close();
							con.close();
							response.sendRedirect("./ADMIN/cpanel.jsp");

						}
						String emaillist[] = new String[1];
						 emaillist[0]=email;
						 mail.setTo(emaillist);
						 mail.setSubject("ppl2.0: ID and Password");
						 mail.setMessage("Dear user, \n You have been added as a special user in your constituency/ward.\n"+
								         "Your login id is "+vid+" and passward is "+pwd+" .\n You can change your password after logging in.");
					     mail.SendMail();
						RequestDispatcher view = request
						.getRequestDispatcher("/ADMIN/delUsers.jsp");
						view.forward(request, response);
						
					}
					
				}
			 else
			 {
				 if(username.charAt(0)=='C')
				 {
					 String query = "UPDATE "+cons+"_SPECIALUSERS SET  PASSWORD='"+pwd+"'," +
										   " EMAILID='"+email+"', PHONE='"+cell+"'WHERE LOGIN='"+username+"'";
					 stmt.executeUpdate(query);
					 response.sendRedirect("/CMS/Home_load?consti="+username.substring(1, 4));

				 }
				 else if(username.charAt(0)=='W')
				 {
					 String query = "UPDATE "+cons+"_"+ward+"_SPECIALUSERS SET PASSWORD='"+pwd+"'," +
										   " EMAILID='"+email+"', PHONE='"+cell+"'WHERE LOGIN='"+username+"'";
					 stmt.executeUpdate(query);
					 response.sendRedirect("/CMS/Home_load?consti="+username.substring(1, 4)+"&ward="+username.substring(4, 7));
				 }
				 else
				 {
					 String query = "UPDATE "+cons+"_"+ward+"_USER_DETAILS SET EMAIL='"+email+"', PHONE='"+phone+"', " +
					 			    "CELL='"+cell+"' WHERE VOTERID='"+username+"'";
					 stmt.executeUpdate(query);
					 String consti_ward = cons+"_"+ward;
					 String query1 = "UPDATE GLOBAL SET PASSWORD='"+pwd+"' WHERE VOTERID='"+username+
					 				"' AND CONSTI_WARD='"+consti_ward+"'";
					 stmt1.executeUpdate(query1);
					 response.sendRedirect("/CMS/Home_load?consti="+cons+"&ward="+ward);
					 stmt.close();
					 stmt1.close();
					 con.close();
					 //where to fwd - chk verify_login servlet

				 }
				 
			 }
		}
		catch(Exception e)
		{
		
		}
	}	
}  

 