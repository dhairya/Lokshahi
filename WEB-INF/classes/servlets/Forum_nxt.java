package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Forum_next
 *
 */
 public class Forum_nxt extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Forum_nxt() {
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
		String sertype = request.getParameter("searchtype1");
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

		
		if(privstr == null || (!type.equalsIgnoreCase("ADMIN") &&(!constiA.equals(constiB) || !ward_belong))  )
		{
			priv = '1';
		}
		else
		{
			priv=privstr.charAt(1);
		}
		
		
		Connection con;
		Statement stmt = null;
		ResultSet rs = null;
		
		
		try{
			PreparedStatement ps = null;
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			con = ds.getConnection();
			
			stmt = con.createStatement();
			String url = constiB + "_" + wardB + "_FORUM";
			if(sertype.equals("all")){
				String query = "SELECT FOR_ID, TITLE, NICKNAME, DATE " +
				"FROM "+url+" ORDER BY FOR_ID DESC LIMIT " + rowq + ", 20" ;
				rs = stmt.executeQuery(query);
				request.setAttribute("row", row1);
				request.setAttribute("prob", rs);
				request.setAttribute("sertype", "all");
			}
			
			else if(sertype.equals("txtser")){
				String ref_id1 = (String) request.getParameter("searchref");
				String searchtxt1 = (String) request.getParameter("searchtxt");
				if (ref_id1.equals("")) {
					ps = con.prepareStatement("SELECT FOR_ID, TITLE, NICKNAME, DATE " +
							"FROM "+url+" where TITLE =? ORDER BY FOR_ID DESC LIMIT " + rowq + ", 20");
					ps.setString(1, searchtxt1);
					rs = ps.executeQuery();
					request.setAttribute("row", row1);
					request.setAttribute("prob", rs);
					request.setAttribute("sertype", "txtser");
				} else if (searchtxt1.equals("")) {
					ps = con.prepareStatement("SELECT FOR_ID, TITLE, NICKNAME, DATE " +
							"FROM "+url+" where FOR_ID =? ORDER BY FOR_ID DESC LIMIT " + rowq + ", 20");
					ps.setLong(1, Long.parseLong(ref_id1));
					rs = ps.executeQuery();
					request.setAttribute("row", row1);
					request.setAttribute("prob", rs);
					request.setAttribute("sertype", "txtser");
				}
			}
			
			
			RequestDispatcher view = request
					.getRequestDispatcher("./"+priv+"/forum.jsp?consti="+constiB+"&ward="+wardB);
			view.forward(request, response);
			rs.close();
			rs=null;
			stmt.close();
			stmt=null;
			ps.close();
			ps=null;
			con.close();
			con=null;
		}
		catch(Exception e)
		{
			
		}
	}   	  	    
}