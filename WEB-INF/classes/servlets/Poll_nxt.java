package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Grievance_load
 * 
 */
public class Poll_nxt extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Poll_nxt() {
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
		String user = (String) session.getAttribute("User");
		String privstr = (String) session.getAttribute("Access");
		String type = (String) session.getAttribute("Type");
		String constiA = (String) session.getAttribute("Consti");
		String wards = (String) session.getAttribute("Wards");
		String categories = (String) session.getAttribute("Categories");
		String nick = (String) session.getAttribute("Nick");
		
		char priv;
		
		String constiB = (String)request.getParameter("consti");
		String wardB = (String)request.getParameter("ward");
		String rowtemp = request.getParameter("row");
		String row1 = String.valueOf((Integer.parseInt(rowtemp) + 20)) ;
		String row2 = String.valueOf((Integer.parseInt(rowtemp) + 40)) ;
		String rowq = String.valueOf((Integer.parseInt(row1) - 1)) ;
		
		Boolean ward_belong; 
		
		if(wards==null)
		{	
			ward_belong = false;
		}
		else
		{
			ward_belong= wards.contains(wardB) || wards.equalsIgnoreCase("ALL");
		}
		
		if(privstr == null || (!type.equalsIgnoreCase("ADMIN") && (!constiA.equals(constiB) || !ward_belong))  )
		{
			priv = '1';
		}
		else
		{
			priv=privstr.charAt(2);
		}
		
		java.sql.Timestamp stamp = new java.sql.Timestamp(new java.util.Date().getTime());
		try {
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			Connection con = ds.getConnection();
			String url = constiB + "_" + wardB + "_POLL";
			PreparedStatement ps = con.prepareStatement("SELECT POLLNO, QUESTION, NICKNAME " +
					"FROM "+url+" WHERE CAST(? AS DATETIME) - INTERVAL 86400 SECOND" +
					" > CAST(CREATE_INST AS DATETIME) ORDER BY POLLNO DESC LIMIT " + rowq + ", 20");
			ps.setTimestamp(1, stamp);
			ResultSet rs = ps.executeQuery();

			request.setAttribute("poll", rs);
			request.setAttribute("row", row1);
			 
			RequestDispatcher view = request
					.getRequestDispatcher("./"+priv+"/poll_archive.jsp?consti="+constiB+"&ward="+wardB);
			view.forward(request, response);
			
			rs.close();
			rs=null;
			ps.close();
			ps=null;
			con.close();
			con=null;
			
		} catch (Exception e) {
		}
	}
}