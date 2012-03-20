package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Editconst
 *
 */
 public class Editconst extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Editconst() {
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
		//String name = (String)request.getParameter("name");
		String code = (String)request.getParameter("code");
		code = code.toUpperCase();
		//name = name.toUpperCase();
		PrintWriter out=response.getWriter();
		Connection con;
		Statement stmt;
		
		Statement stmt3;
		ResultSet rs=null;
		
		ResultSet rs3=null;
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt = con.createStatement();
			 
			 stmt3 = con.createStatement();
			 
			 String query3 = "Select * from CONSTITUENCY where CODE = '"+code+"'";
			 rs3 = stmt3.executeQuery(query3);
			 
			 if(rs3 !=null && rs3.next())
			 {
		     String query = "Select * from KBASE WHERE CODE = '"+code+"'";
		     rs = stmt.executeQuery(query);
		     
		     /* //mla details
		     String query1 = "Select * from DB2ADMIN."+code+"_LOGIN where VOTER_ID = 'MLA'";
		     rs1 = stmt1.executeQuery(query1);
		     
		     String query2 = "Select * from DB2ADMIN."+code+"_LOGIN where VOTER_ID = 'KIOSK'";
		     rs2 = stmt2.executeQuery(query2); */
		     
		     request.setAttribute("consti",code);
		     request.setAttribute("kbase",rs);
		     //request.setAttribute("mlainfo",rs1);
		     //request.setAttribute("kiosk",rs2);
		     RequestDispatcher view = request
				.getRequestDispatcher("/ADMIN/edit_const2.jsp");
		view.forward(request, response);
		rs.close();
		rs3.close();
		stmt.close();
		stmt3.close();
		con.close();
			 }
			 else
			 {
				 request.setAttribute("consti",code);
				 request.setAttribute("erroredit","nocode");
				 RequestDispatcher view = request
					.getRequestDispatcher("/ADMIN/edit_consterr.jsp");
			view.forward(request, response);
			rs.close();
			rs3.close();
			stmt.close();
			stmt3.close();
			con.close();
			 }
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}   	  	    
}