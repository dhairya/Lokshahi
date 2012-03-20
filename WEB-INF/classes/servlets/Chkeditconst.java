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
 * Servlet implementation class for Servlet: Chkeditconst
 *
 */
 public class Chkeditconst extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Chkeditconst() {
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
		String strCons = (String)request.getParameter("cons");
		strCons = strCons.toUpperCase();
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt = con.createStatement();
			 String query = "SELECT CODE FROM CONSTITUENCY WHERE CODE = '"+strCons+"'";
			 rs = stmt.executeQuery(query);
			 if(rs.next())
			 {
				 rs.close();
				 stmt.close();
				 con.close();
				 response.setContentType("text/xml");
				 response.setHeader("Cache-Control","no-cache");
				 out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?><xmlresponse><valid>Valid Constituency Code.</valid><valid>1</valid></xmlresponse>");
			 }
			 else
			 {
				 rs.close();
				 stmt.close();
				 con.close();
				 response.setContentType("text/xml");
				 response.setHeader("Cache-Control","no-cache");
				 out.write("<?xml version=\"1.0\" encoding=\"utf-8\"?><xmlresponse><valid>Sorry a constituency with the name "+strCons+" does not exist.</valid><valid>0</valid></xmlresponse>");
			 }
		}
		catch(Exception e){
			out.println(e);
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