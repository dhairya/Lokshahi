package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Home_load
 *
 */
 public class Home_load extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Home_load() {
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
		try
		{
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String constiB = (String)request.getParameter("consti");
		String wardB = (String)request.getParameter("ward");
		
		/*RequestDispatcher view = request
		.getRequestDispatcher("./home.jsp?consti="+consti);
view.forward(request, response);*/
		if(wardB==null){
			response.sendRedirect("./homeC.jsp?consti="+constiB);
		}
		else{
			response.sendRedirect("./homeW.jsp?consti="+constiB+"&ward="+wardB);
		}
		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}	  	  	    
}