package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Editconst2
 *
 */
 public class Editconst2 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Editconst2() {
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
		//String code = (String)session.getAttribute("Consti");
		PrintWriter out=response.getWriter();
		Connection con;
		Statement stmt;		
		Statement stmt2;
		String info = (String)request.getParameter("info");
		String mlaname = (String)request.getParameter("mlaname");
		//String mlauname = (String)request.getParameter("mlauname");
		//mlauname.toUpperCase();
		//String mlapass = (String)request.getParameter("mlapass");
		String mlacont = (String)request.getParameter("mlacont");
		
		String pop = (String)request.getParameter("ppltn");
		String litrate = (String)request.getParameter("lit");
		String sexratio = (String)request.getParameter("sexr");
		String nos = (String)request.getParameter("nos");
		String noh = (String)request.getParameter("noh");
		String nops = (String)request.getParameter("nops");
		String nop = (String)request.getParameter("nop");
		String hlp = (String)request.getParameter("hlp");
		String hps = (String)request.getParameter("hps");
		String mhos = (String)request.getParameter("mhos");
		String rail = (String)request.getParameter("rail");
		String coff = (String)request.getParameter("coff");
		String dept = (String)request.getParameter("dept");
		String map = (String)request.getParameter("map");
		String link = (String)request.getParameter("link");
		String eleoff = (String)request.getParameter("eleoff");
		String code = (String)request.getParameter("code");
	
		try{
		InitialContext ctx = new InitialContext();
		DataSource ds = (DataSource) ctx
				.lookup("java:comp/env/jdbc/MyDataSource");
		 con = ds.getConnection();
		
		stmt = con.createStatement();
		
		stmt2 = con.createStatement();
		
		/*String query = "UPDATE DB2ADMIN.KBASE SET(INFO,MLANAME,MLACONTACT,POPULATION,LITERACY_RATE,SEX_RATIO," +
				"SCHOOL,HOSPITALS,POLICE,PARKS,HELPLINE,ADD_POLICE,ADD_HOSPITALS,ADD_RAILWAY,COLLECTOR_OFFICE," +
				"DEPT_CONTACT,MAP,LINK_GOVT_SITES,ELECTION_OFFICE)=('"+info+"','"+mlaname+"','"+mlacont+"','" +
				 pop+"','"+litrate+"','"+sexratio+"','"+nos+"','"+noh+"','"+nops+"','"+nop+"','"+hlp+"','"+hps+
				 "','"+mhos+"','"+rail+"','"+coff+"','"+dept+"','"+map+"','"+link+"','"+eleoff+"')WHERE " +
				 		"CODE = '"+code+"'";
		stmt.executeUpdate(query);*/
		
		String query = "UPDATE KBASE SET INFO='"+info+"', MLANAME='"+mlaname+"', MLACONTACT='"+mlacont+"', POPULATION='"+pop+"', "+
		   			   "LITERACY_RATE='"+litrate+"', SEX_RATIO='"+sexratio+"', SCHOOL='"+nos+"', HOSPITALS='"+noh+"', POLICE='"+nops+
		   			   "', PARKS='"+nop+"', HELPLINE='"+hlp+"', ADD_POLICE='"+hps+"', ADD_HOSPITALS='"+mhos+"', ADD_RAILWAY='"+rail+
		   			   "', COLLECTOR_OFFICE='"+coff+"', DEPT_CONTACT='"+dept+"', MAP='"+map+"', LINK_GOVT_SITES='"+link+
		   			   "', ELECTION_OFFICE='"+eleoff+"' WHERE CODE='"+code+"'";
		stmt.executeUpdate(query);
	
		
		/*String query2 = "UPDATE "+code+"_LOGIN SET PASSWORD"+
		                "='"+mlapass+"' where VOTER_ID = '"+mlauname+"'";
		stmt2.executeUpdate(query2);*/

        
		RequestDispatcher view = request
		.getRequestDispatcher("/ADMIN/cpanel.jsp");
view.forward(request, response);
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}   	  	    
	}   	  	    
