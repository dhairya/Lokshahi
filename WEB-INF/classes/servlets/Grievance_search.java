package servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: Grievance_search
 * 
 */
public class Grievance_search extends javax.servlet.http.HttpServlet implements
		javax.servlet.Servlet {
	static final long serialVersionUID = 1L;

	/*
	 * (non-Java-doc)
	 * 
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Grievance_search() {
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
		doPost(request,response);
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
		
		String sertype = request.getParameter("searchtype");
		ResultSet rs = null;
		
		String constiB = (String)request.getParameter("consti");
		String wardB = (String)request.getParameter("ward");
		
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
			priv=privstr.charAt(0);
		}
		
		try {
			PreparedStatement ps = null;
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			Connection con = ds.getConnection();

			String url = constiB + "_" + wardB + "_GRIEVANCES";
			if (sertype.equals("txtser")) {
				String ref_id = (String) request.getParameter("searchref");
				String searchtxt = (String) request.getParameter("searchtxt");
				if (ref_id.equals("")) {
					ps = con.prepareStatement("SELECT * FROM " + url
							+ " where TITLE =? ORDER BY REF_ID DESC "
							+ "LIMIT 21");
					ps.setString(1, searchtxt);
					rs = ps.executeQuery();
					request.setAttribute("row", "1");
					request.setAttribute("sertype", "txtser");
				} else if (searchtxt.equals("")) {
					ps = con.prepareStatement("SELECT * FROM " + url
							+ " where REF_ID=? ORDER BY REF_ID DESC "
							+ "LIMIT 21");
					ps.setLong(1, Long.parseLong(ref_id));
					rs = ps.executeQuery();
					request.setAttribute("row", "1");
					request.setAttribute("sertype", "txtser");
				}
			} else if (sertype.equals("myprob")) {
				ps = con.prepareStatement("SELECT * FROM " + url
						+ " where NICKNAME = ? ORDER BY REF_ID DESC "
						+ "LIMIT 21");
				ps.setString(1,nick);
				rs = ps.executeQuery();
				request.setAttribute("row", "1");
				request.setAttribute("sertype", "myprob");

			} else if (sertype.equals("rating")) {
				ps = con.prepareStatement("SELECT * FROM " + url
						+ " ORDER BY INTELLI_RATING DESC, REF_ID DESC "
						+ "LIMIT 21");
				rs = ps.executeQuery();
				request.setAttribute("row", "1");
				request.setAttribute("sertype", "rating");

			} else if (sertype.equals("joined")) {
				String url1=constiB + "_" + wardB + "_VOTERS_JOINED";
				ps = con.prepareStatement("SELECT * FROM " + url
						+ " WHERE REF_ID IN (SELECT REF_ID FROM " + url1 +
						" WHERE NICKNAME = ?)" +
						"ORDER BY REF_ID DESC "
						+ "LIMIT 21");
				ps.setString(1,nick);
				rs = ps.executeQuery();
				request.setAttribute("row", "1");
				request.setAttribute("sertype", "joined");

			} else if(sertype.equals("status")){
				int status = Integer.parseInt(request.getParameter("filter")); 
				String statstr=null;
				if (status==0){
					statstr="STATUS=0 OR STATUS=10 OR STATUS=20 OR STATUS=30";
				}
				else if(status==1){
					statstr="STATUS=1 OR STATUS=11 OR STATUS=21 OR STATUS=31";
				}
				else if(status==2){
					statstr="STATUS=2 OR STATUS=12 OR STATUS=22 OR STATUS=32";
				}
				else if(status==10){
					statstr="STATUS=10 OR STATUS=11 OR STATUS=12";
				}
				else if(status==20){
					statstr="STATUS=20 OR STATUS=21 OR STATUS=22";
				}
				else if(status==30){
					statstr="STATUS=30 OR STATUS=31 OR STATUS=32";
				}
				else if(status==40){
					statstr="STATUS=40";
				}
				ps = con.prepareStatement("SELECT * FROM " + url
						+ " where "+statstr+" ORDER BY REF_ID DESC "
						+ "LIMIT 21");
				rs = ps.executeQuery();
				request.setAttribute("row", "1");
				request.setAttribute("sertype", "status");
			}else if(sertype.equals("all")){
				ps = con.prepareStatement("SELECT * FROM " + url
						+ " ORDER BY REF_ID DESC "
						+ "LIMIT 21");
				rs = ps.executeQuery();
				request.setAttribute("row", "1");
				request.setAttribute("sertype", "all");
			}else if(sertype.equals("mysolve")){
				String mycats[] = session.getAttribute("Categories").toString().split(",");
				String cats = "(";
				int i;
				for (i=0; i<mycats.length-1;i++){
					cats=cats+"'"+mycats[i]+"',";
				}
				cats=cats+"'"+mycats[i]+"')";

				ps = con.prepareStatement("SELECT * FROM " + url
						+ " WHERE DEPARTMENT IN " + cats + " ORDER BY REF_ID DESC "
						+ "LIMIT 21");
				rs = ps.executeQuery();
				request.setAttribute("row", "1");
				request.setAttribute("sertype", "mysolve");
			}
			
			request.setAttribute("prob", rs);
			RequestDispatcher view = request
					.getRequestDispatcher("./"+priv+"/grievance.jsp?consti="+constiB+"&ward="+wardB);
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