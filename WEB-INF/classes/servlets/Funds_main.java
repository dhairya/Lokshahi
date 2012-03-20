package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Funds_main
 * 
 */
public class Funds_main extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Funds_main() {
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
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		
		String user = (String) session.getAttribute("User");
		String privstr = (String) session.getAttribute("Access");
		String type = (String) session.getAttribute("Type");
		String constiA = (String) session.getAttribute("Consti");
		String wards = (String) session.getAttribute("Wards");
		String categories = (String) session.getAttribute("Categories");
		String nick = (String) session.getAttribute("Nick");
		
		char priv;
		
		String constiB = (String)request.getParameter("consti");
		String wardB=null;
		for (Enumeration e = request.getParameterNames() ; e.hasMoreElements() ;) {
	         if(e.nextElement().toString().equals("ward"))
	         {
	        	 wardB= (String)request.getParameter("ward");
	         }
		}
		
		
		
		if(privstr == null || (!type.equalsIgnoreCase("ADMIN") && !constiA.equals(constiB) )  )
		{
			priv = '1';
		}
		else
		{
			priv=privstr.charAt(3);
		}

		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			Connection con = ds.getConnection();
			String url = constiB + "_FUNDS";
			PreparedStatement ps = con.prepareStatement("SELECT * FROM " + url
					+ " ORDER BY FUND_ID DESC " + "LIMIT 21");
			ResultSet rs = ps.executeQuery();
			request.setAttribute("row", "1");
			request.setAttribute("funds", rs);
			
			if(wardB==null){
			RequestDispatcher view = request
					.getRequestDispatcher("./"+priv+"/fundsC.jsp?consti="+constiB);
			view.forward(request, response);
			}
			else
			{
				RequestDispatcher view = request
				.getRequestDispatcher("./"+priv+"/funds.jsp?consti="+constiB+"&ward="+wardB);
		view.forward(request, response);
			}
			
			rs.close();
			rs=null;
			ps.close();
			ps=null;
			con.close();
			con=null;
			
		} catch (Exception e) {out.println(e);
		}

	}
}