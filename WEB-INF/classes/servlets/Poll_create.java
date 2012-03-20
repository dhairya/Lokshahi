package servlets;

import java.io.IOException;
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
 * Servlet implementation class for Servlet: Grievance_add
 *
 */
 public class Poll_create extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Poll_create() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		String question = request.getParameter("question");
		String opt1 = request.getParameter("opt1");
		String opt2 = request.getParameter("opt2");
		String opt3 = request.getParameter("opt3");
		String opt4 = request.getParameter("opt4");
		
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
	        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
	        Connection con = ds.getConnection();
	        String url = constiB + "_" + wardB + "_POLL";
	        PreparedStatement ps=con.prepareStatement("INSERT INTO "+url+"(NICKNAME, OPT1, OPT2, OPT3, OPT4, QUESTION, CREATE_INST) VALUES(?, ?, ?, ?, ?, ?, ?)");
	        ps.setString(1,nick);
	        ps.setString(2,opt1);
	        ps.setString(3,opt2);
	        ps.setString(4,opt3);
	        ps.setString(5,opt4);
	        ps.setString(6, question);
	        ps.setTimestamp(7, stamp);
	        int rs = ps.executeUpdate();
	        
	        Statement stmt = con.createStatement();
	        String query = "SELECT VOTERID,EMAIL,CELL FROM " + constiB + "_" + wardB + "_USER_DETAILS";
	        ResultSet rsl = stmt.executeQuery(query);
	        String celllist[];
	        int c = 0;
	        
	        while(rsl.next())
	        {	
	        	c++;
	        }
	        
	        celllist = new String[c];
	        c=0;
	        rsl = stmt.executeQuery(query);
	        while(rsl.next())
	        {
	        	celllist[c] = rsl.getString("CELL");
	        	c++;
	        }
	        
	        
	        String msg = "New Poll:"+question; 
			Classes.SMS send = new Classes.SMS();
	        
			for(int i=0;i<celllist.length;i++)
	        {
				 send.SMSsend(msg, celllist[i]);
	        }

			rsl.close();
			rsl=null;
			stmt.close();
			stmt=null;
			ps.close();
			ps=null;
			con.close();
			con=null;
			
	        //RequestDispatcher view = request.getRequestDispatcher("./Poll_main?consti="+consti);
	        //view.forward(request, response);

			response.sendRedirect("./Poll_main?consti="+constiB+"&ward="+wardB);
		}catch(Exception e){}
	}   	  	    
}