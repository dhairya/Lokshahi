package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import javax.swing.text.html.Option;

/**
 * Servlet implementation class for Servlet: Chkuser
 *
 */
 public class List_wards extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public List_wards() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request,response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		String cons= request.getParameter("cons");
		
		Statement stmt;
		ResultSet rs;
		Connection con;
		String ward="";
		int i=0;
		try{
			
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 
			 stmt = con.createStatement();
			 String query = "Select * from "+cons+"_WARDS";
			 rs = stmt.executeQuery(query);
			 while(rs.next())
			 {
				 i++;
				 
			 }
			 rs = stmt.executeQuery(query);
			 
			 String rtn[] = new String[i];
			 for(i=0;i<rtn.length;i++)
			 {
				 rtn[i]="obj";
			 }
			 i=0;
			 while(rs.next())
			 {   
				 ward=rs.getString("NUMBER");
				
				 rtn[i]=rtn[i]+".options[obj.options.length] = new Option('";
				 
				 rtn[i]=rtn[i]+ward;
				 rtn[i]=rtn[i]+"','";
				 rtn[i]=rtn[i]+ward;
				 rtn[i]=rtn[i]+"')";
				 rtn[i]=rtn[i]+"\n";
				 out.write(rtn[i]);
				 i++;
				
			 }
			 rs.close();
			 stmt.close();
			 con.close();
			 
		}
		catch(Exception e)
		{
			
		}
	}   	  	    
}