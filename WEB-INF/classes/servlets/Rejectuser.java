package servlets;

import beans.*;

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
 * Servlet implementation class for Servlet: Approveuser
 * 
 */
public class Rejectuser extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Rejectuser() {
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
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String vid = (String)request.getAttribute("vid");
		String consti_code=(String) request.getAttribute("consti");
		String ward=(String) request.getAttribute("ward");
		
		Mailbean mail = new Mailbean();
		Connection con;
		Statement stmt;
		Statement stmt1;
		ResultSet rs;
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			
			stmt = con.createStatement();
			stmt1 = con.createStatement();
			String query = "select EMAIL from TEMPUSER where VOTERID = '"+vid+"' AND CONSTI_CODE='"+consti_code+"' AND WARD='"+ward+"'";
			rs = stmt.executeQuery(query);
			rs.next();
			String email = rs.getString("EMAIL");
			String query1 = "DELETE FROM TEMPUSER WHERE VOTERID = '"+vid+"' AND CONSTI_CODE='"+consti_code+"'";
			stmt1.executeUpdate(query1);
			String emaillist[] = new String[1];
			emaillist[0]=email;
			mail.setTo(emaillist);
			mail.setSubject("ppl2.0: VOTERID REJECTED");
			mail.setMessage("Dear user,\n Your voterid no "+vid+" has been rejected. Your entered constituency is "+consti_code+" and entered ward is "+ward+". Please check the details entered." +
					" These details need to match with the those provided at the election commission");
			mail.SendMail();
			 RequestDispatcher view = request
	     		.getRequestDispatcher("./ADMIN/approveuser.jsp");
	     view.forward(request, response);
	     rs.close();
	     stmt.close();
	     stmt1.close();
	     con.close();
		}
		catch(Exception e)
		{
			out.println(e);
		}


	}
}