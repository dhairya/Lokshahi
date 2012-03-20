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
 * Servlet implementation class for Servlet: Chkuser
 *
 */
 public class Chkuser extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Chkuser() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		Connection con;
		Statement stmt, stmt1;
		ResultSet rs;
		ResultSet rs1;
		String strNick = (String)request.getParameter("nick");
		String strCons = (String)request.getParameter("cons");
		String ward = (String)request.getParameter("ward");
		strCons = strCons.toUpperCase();
		//ward = ward.toUpperCase();
		
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt = con.createStatement();
			 stmt1 = con.createStatement();
				 String query1 = "SELECT NICKNAME FROM "+strCons+"_"+ward+"_USER_DETAILS WHERE NICKNAME = '"+strNick+"'";
				 rs1 = stmt.executeQuery(query1);
				 String query = "SELECT NICKNAME FROM TEMPUSER WHERE NICKNAME='"+strNick+"' AND CONSTI_CODE='"+strCons+"' AND WARD='"+ward+"'";
				 rs = stmt1.executeQuery(query);
				 if(rs.next() || rs1.next() || strNick.equals("admin") || strNick.equals("ADMIN"))
				 {
					 rs.close();
					 stmt.close();
					 rs1.close();
					 stmt1.close();
					 con.close();
					 response.setContentType("text/xml");
					 response.setHeader("Cache-Control","no-cache");
					 out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?><xmlresponse><valid>Hi! You are from "+strCons+" constituency and "+ward+" ward, but the nickname "+strNick+" is not available</valid><valid>2</valid></xmlresponse>");
				 }
				 else
				 {
					 rs.close();
					 stmt.close();
					 rs1.close();
					 stmt1.close();
					 con.close();
					 response.setContentType("text/xml");
					 response.setHeader("Cache-Control","no-cache");
					 out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?><xmlresponse><valid>Hi! You are from "+strCons+" constituency and "+ward+" ward and the nickname "+strNick+" is available</valid><valid>0</valid></xmlresponse>");
					
				 }
			 
			 
			 
		}
		catch(Exception e){
			out.println(e);
		}
	}   	  	    
}