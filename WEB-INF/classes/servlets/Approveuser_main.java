package servlets;

import java.io.IOException;
import java.io.PrintWriter;
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

import beans.*;
/**
 * Servlet implementation class for Servlet: Approveuser_main
 *
 */
 public class Approveuser_main extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Approveuser_main() {
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
		PrintWriter out=response.getWriter();
		//String acceptid = (String)request.getParameter("accept");
		String rejectid = (String)request.getParameter("reject");
		String vid = (String)request.getParameter("text");
		String consti_code=request.getParameter("code");
		System.out.println(consti_code);
		//Mailbean mail = new Mailbean();
		//String code;
		//String id;
		//code = vid.substring(0,3);
		//id = vid.substring(3);
		String pwd,fname,mname,lname,nickname, day,month,year,gender,address,email,phone,cell;
		String ward=null;
		Connection con=null;
		Statement stmt=null;
		Statement stmt1=null;
		Statement stmt2=null;
		Statement stmt3=null, stmt4=null;
		ResultSet rs=null, rs1=null;
		String consti_ward = null;
		if(rejectid==null)
		{
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			
			stmt = con.createStatement();
			stmt1 = con.createStatement();
			stmt2 = con.createStatement();
			stmt3 = con.createStatement();
			stmt4 = con.createStatement();
			
			String query = "select * from TEMPUSER where VOTERID = '"+vid+"'"+
			               " AND CONSTI_CODE='"+consti_code+"'";
			rs = stmt.executeQuery(query);
			
			rs.next();
			pwd = rs.getString("PASSWORD");
			fname = rs.getString("FNAME");
			mname = rs.getString("MNAME");
			lname = rs.getString("LNAME");
			nickname = rs.getString("NICKNAME");
			day = rs.getString("DDATE");
			month = rs.getString("MMONTH");
			year = rs.getString("YYEAR");
			gender = rs.getString("GENDER");
			address = rs.getString("ADDRESS");
			email = rs.getString("EMAIL");
			phone = rs.getString("PHONE");
			cell = rs.getString("CELL");
			ward = rs.getString("WARD");
			
			
			
			
			String query1 = "insert into "+consti_code+"_"+ward+"_USER_DETAILS(VOTERID,"+
							" FNAME, MNAME, LNAME, NICKNAME, ACCESS_INFO, SOLVABLE_CATEGORIES,"+
							" DDATE, MMONTH, YYEAR, GENDER, ADDRESS, EMAIL, PHONE, CELL) values ('"+vid+"','"
                             +fname+"','"+mname+"','"+lname+"','"+nickname+"','332112211','empty','"
                             +day+"','"+month+"','"+year+"','"+gender+"','"
                             +address+"','"+email+"','"+phone+"','"+cell+"')";
		    stmt1.executeUpdate(query1);
		    
		    /*String query2 = "insert into "+code+"_LOGIN values('"+id+"','"+pwd+"')";
		    stmt2.executeUpdate(query2);*/
		    
		    consti_ward = consti_code+"_"+ward;
		    
		    String query2 = "INSERT INTO GLOBAL VALUES ('"+vid+"','"+pwd+"','"+consti_ward+"')";
		    stmt2.executeUpdate(query2);
			
		    String query3 = "DELETE FROM TEMPUSER WHERE VOTERID = '"+vid+"' AND CONSTI_CODE='"+consti_code+"' AND WARD='"+ward+"'";
			 stmt3.executeUpdate(query3);
			
			 String query4 = "SELECT * FROM CATEGORIES";
				rs1 = stmt4.executeQuery(query4);
				
			/*String emaillist[] = new String[1];
			emaillist[0]=email;
			mail.setTo(emaillist);
			mail.setSubject("ppl2.0: VOTERID ACCEPTED");
			mail.setMessage("Dear user, \n Your voterid no. "+vid+" has been accepted. Your nickname is "+nickname+" and password is "+pwd+"." +
					" You can now login into ppl2.0 with your voterid and password and access your account");
			mail.SendMail();*/
			 
			 
			 String msg = "Registered successfully. UserID:"+vid+"Password:"+pwd; 
			 Classes.SMS send = new Classes.SMS();
			 send.SMSsend(msg, cell);
			 
			 
			
			
			
			request.setAttribute("cat",rs1);
			request.setAttribute("ward",ward);
			request.setAttribute("cons",consti_code);
			request.setAttribute("vid",vid);
			 RequestDispatcher view = request
	     		.getRequestDispatcher("./ADMIN/approveuser2.jsp");
	     view.forward(request, response);
	     rs.close();
	 	 rs1.close();
	 	
	 	 stmt.close();
	 	 stmt1.close();
	 	 stmt2.close();
	 	 stmt3.close();
	 	 stmt4.close();
	 	 
	 	 con.close();
		}	
		catch(Exception e)
		{
			out.println(e);
		}
		}
		else
		{  
			try
			{
			request.setAttribute("vid",vid);
		    request.setAttribute("consti",consti_code);
		    request.setAttribute("ward", ward);
			RequestDispatcher view = request
     		.getRequestDispatcher("Rejectuser");
     view.forward(request, response);
     rs.close();
 	 rs1.close();
 	 
 	 stmt.close();
 	 stmt1.close();
 	 stmt2.close();
 	 stmt3.close();
 	 stmt4.close();
 	
 	 con.close();
			}
			catch(Exception e)
			{
				
			}
		}
   }
}