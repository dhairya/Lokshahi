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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: Verify_emplogin
 *
 */
 public class Emp_register extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Emp_register() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userid =(String) request.getParameter("userid");
		String pass= (String)request.getParameter("pass");
		String comp_name =(String) request.getParameter("comp_name");
		String comp_add =(String) request.getParameter("comp_add");
		String comp_regno =(String) request.getParameter("comp_regno");
		String industry =(String) request.getParameter("comp_industry");
		String telno =(String) request.getParameter("tel");
		String email =(String) request.getParameter("email");
		String website =(String) request.getParameter("website");
		String code =(String) request.getParameter("code");
		String contact_name =(String) request.getParameter("contact_name");
		String contact_tel =(String) request.getParameter("contact_tel");
		String owner_name =(String) request.getParameter("owner_name");
		String owner_add =(String) request.getParameter("owner_add");
		String vpno =(String) request.getParameter("vpno");
						
		Connection con;
		Statement stmt;
		
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt = con.createStatement();
			 
			 //insert into company details
			 String  query= "INSERT INTO COMPANY_DETAILS VALUES ('"+ userid +"' , '"+ pass +"' , '"
			 				+ comp_name+"' , '"+ comp_add+"' , '"+ owner_name+"' , '"+ owner_add+"' , '"
			 				+ code +"' , '"+ comp_regno +"' , '"+ telno+"' , '"+ email+"' , '"
			 				+ website+"' , '"+ industry +"' , '"+ contact_name +"' , '"+ contact_tel +"' , '"
			 				+ vpno + "')";
			 
			 stmt.executeUpdate(query);
			 
			 
				stmt.close();
				stmt=null;
				con.close();
				con = null;
			 
			 RequestDispatcher view = request.getRequestDispatcher("./emp_login.jsp");
		        view.forward(request, response);
		}
		
		catch(Exception e)
		{}
		
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}
	
 }
