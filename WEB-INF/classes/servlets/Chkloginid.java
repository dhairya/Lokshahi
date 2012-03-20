package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Chkloginid
 *
 */
 public class Chkloginid extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Chkloginid() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		Connection con;
		Statement stmt;
		ResultSet rs;
		String login = request.getParameter("vid");
		String cons = request.getParameter("cons");
		String ward = request.getParameter("ward");
		//ward = ward.toUpperCase();
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt = con.createStatement();
			 if(login.charAt(0)=='C' || login.charAt(0)=='c')
			 {
				 cons=login.substring(1,4);
				 cons = cons.toUpperCase();
				 String query="SELECT LOGIN FROM "+cons+"_SPECIALUSERS WHERE LOGIN='"+login+"'";
				 rs=stmt.executeQuery(query);
				 if(rs.next())
				 {
					 rs.close();
					 stmt.close();
					 con.close();
					 response.setContentType("text/xml");
					 response.setHeader("Cache-Control","no-cache");
					 out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?><xmlresponse><valid>Special User Exists!</valid><valid>0</valid></xmlresponse>");
				 }
				 else
				 {
					 rs.close();
					 stmt.close();
					 con.close();
					 response.setContentType("text/xml");
					 response.setHeader("Cache-Control","no-cache");
					 out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?><xmlresponse><valid>Special User with login id "+login+" does not exist in "+cons+" constituency.</valid><valid>1</valid></xmlresponse>");
				 }
			 }
			 else if(login.charAt(0)=='W' || login.charAt(0)=='w')
			 {
				 cons=login.substring(1,4);
				 cons = cons.toUpperCase();
				 ward=login.substring(4,7);
				 String query="SELECT LOGIN FROM "+cons+"_"+ward+"_SPECIALUSERS WHERE LOGIN='"+login+"'";
				 rs=stmt.executeQuery(query);
				 if(rs.next())
				 {
					 response.setContentType("text/xml");
					 response.setHeader("Cache-Control","no-cache");
					 out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?><xmlresponse><valid>Special User Exists!</valid><valid>0</valid></xmlresponse>");
				 }
				 else
				 {
					 response.setContentType("text/xml");
					 response.setHeader("Cache-Control","no-cache");
					 out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?><xmlresponse><valid>Special User with login id "+login+" does not exist in ward "+ward+" and constituency "+cons+".</valid><valid>1</valid></xmlresponse>");
				 }
			 }
			 else if(login.equals("admin") || login.equals("ADMIN"))
			 {
					 response.setContentType("text/xml");
					 response.setHeader("Cache-Control","no-cache");
					 out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?><xmlresponse><valid>Admin!</valid><valid>0</valid></xmlresponse>");
			 }
			 else
			 {   
				 String query="SELECT NICKNAME FROM "+cons+"_"+ward+"_USER_DETAILS WHERE NICKNAME='"+login+"'";
				 rs=stmt.executeQuery(query);
				 if(rs.next())
					{
						 response.setContentType("text/xml");
						 response.setHeader("Cache-Control","no-cache");
						 out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?><xmlresponse><valid>Citizen Exists!</valid><valid>0</valid></xmlresponse>");	
					}
					else
					{
						 response.setContentType("text/xml");
						 response.setHeader("Cache-Control","no-cache");
						 out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?><xmlresponse><valid>Citizen does not exist!</valid><valid>1</valid></xmlresponse>");
					}
			 }
		}
		catch(Exception e)
		{
			
		}
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}   	  	    
}