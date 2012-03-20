package servlets;

import java.io.IOException;
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
 * Servlet implementation class for Servlet: Add_category2
 *
 */
 public class Add_category2 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Add_category2() {
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
		
		String cat_name = request.getParameter("cat");
		String cat_con = "";
		String utypes[]=null;
		
		utypes = request.getParameterValues("utypes");
		//System.out.println("1");
		String code="";
		String ward="";
		long uid, id;
		int i=0;
		Connection con;
		Statement stmt;
		Statement stmt1;
		Statement stmt2;
		Statement stmt3;
		Statement stmt4;
		Statement stmt5;
		Statement stmt6;
		Statement stmt7;
		
		ResultSet rs = null, rs1=null, rs2=null, rs3=null, rs4=null;
		
		
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
			 stmt5 = con.createStatement();
			 stmt6 = con.createStatement();
			 stmt7 = con.createStatement();
			 
			 String query = "INSERT INTO CATEGORIES (CATEGORY) VALUES ('" +
			 				cat_name+"')";
			 stmt.executeUpdate(query);
			 
			 //String query1 = "SELECT CAT_ID FROM CATEGORIES WHERE CAT_ID=(SELECT MAX(CAT_ID";
			 String query1 = "SELECT CAT_ID FROM CATEGORIES ORDER BY CAT_ID DESC LIMIT 1";
			 rs = stmt1.executeQuery(query1);
			 rs.next();
			 id = rs.getLong("CAT_ID");

			 String cat_id = Long.toString(id);
			 //System.out.println("2");
			 if(utypes[0].equals("all"))
			 {
				 
				 String query2 = "SELECT CODE FROM CONSTITUENCY";
				 rs1 = stmt2.executeQuery(query2);
				 while(rs1.next())
				 {
					 code = rs1.getString("CODE");
					 String query3 = "SELECT USER_ID, SOLVABLE_CATEGORIES FROM "+
					                 code+"_SPECIALUSERS";
					 rs2 = stmt3.executeQuery(query3);
					 while(rs2.next())
					 {
						 uid = rs2.getLong("USER_ID");
						 cat_con = rs2.getString("SOLVABLE_CATEGORIES")+cat_id;
						 String query4 = "UPDATE "+code+"_SPECIALUSERS"+
						                 " SET SOLVABLE_CATEGORIES = "+
						                 "'"+cat_con+"' WHERE USER_ID="+uid;
						 stmt4.executeUpdate(query4);
						 cat_con="";
					 }
					 String query5 = "SELECT NUMBER FROM "+code+
					 				 "_WARDS";
					 rs3 = stmt5.executeQuery(query5);
					 while(rs3.next())
					 {
						 ward = rs3.getString("NUMBER");
						 String query6 = "SELECT USER_ID, SOLVABLE_CATEGORIES FROM "+
		                                 code+"_"+ward+"_SPECIALUSERS";
		                 rs4 = stmt6.executeQuery(query6);
		                 while(rs4.next())
		                 {
		                	 uid = rs4.getLong("USER_ID");
		                	 cat_con = rs4.getString("SOLVABLE_CATEGORIES")+cat_id;
							 String query7 = "UPDATE "+code+"_"+ward+"_SPECIALUSERS"+
							                 " SET SOLVABLE_CATEGORIES = "+
							                 "'"+cat_con+"' WHERE USER_ID ="+uid;
							 stmt7.executeUpdate(query7);
							 cat_con=""; 
		                 }
					 }
				 }
				 
			 }
			 
			 if(utypes != null)
			 {
				
				 String query2 = "SELECT CODE FROM CONSTITUENCY";
				 rs1 = stmt2.executeQuery(query2);
				 while(rs1.next())
				 {
					 code = rs1.getString("CODE");
					 for(i=0;i<utypes.length;i++)
					 {
					 String query3 = "SELECT USER_ID, SOLVABLE_CATEGORIES FROM "+
	                                 code+"_SPECIALUSERS WHERE USERTYPE='"+utypes[i]+"'";
	                 rs2 = stmt3.executeQuery(query3);
	                 while(rs2.next())
					 {
						 uid = rs2.getLong("USER_ID");
						 cat_con = rs2.getString("SOLVABLE_CATEGORIES")+cat_id;
						 String query4 = "UPDATE "+code+"_SPECIALUSERS"+
						                 " SET SOLVABLE_CATEGORIES = "+
						                 "'"+cat_con+"' WHERE USER_ID="+uid+" AND USERTYPE='"+utypes[i]+"'";
						 stmt4.executeUpdate(query4);
						 cat_con="";
					 }
	                 String query5 = "SELECT NUMBER FROM "+code+"_WARDS";
	                 rs3 = stmt5.executeQuery(query5);
	                 while(rs3.next())
	                 {
		                  ward = rs3.getString("NUMBER");
		                  String query6 = "SELECT USER_ID, SOLVABLE_CATEGORIES FROM "+
                                          code+"_"+ward+"_SPECIALUSERS WHERE USERTYPE='"+utypes[i]+"'";
                          rs4 = stmt6.executeQuery(query6);
                          while(rs4.next())
                       {
        	                uid = rs4.getLong("USER_ID");
        	                cat_con = rs4.getString("SOLVABLE_CATEGORIES")+cat_id;
			                String query7 = "UPDATE "+code+"_"+ward+"_SPECIALUSERS"+
			                                " SET SOLVABLE_CATEGORIES = "+
			                                "'"+cat_con+"' WHERE USER_ID ="+uid+" AND USERTYPE='"+utypes[i]+"'";
			               stmt7.executeUpdate(query7);
			               cat_con=""; 
                       }
	                 }
	                 
				   }
				 }
				
			 }
			 
			 RequestDispatcher view = request
		 	 	.getRequestDispatcher("Add_category1");
		 	 view.forward(request, response);
		     rs.close();
		 	 rs1.close();
		 	 rs2.close();
		 	 rs3.close();
		 	 stmt.close();
		 	 stmt1.close();
		 	 stmt2.close();
		 	 stmt3.close();
		 	 stmt4.close();
		 	 stmt5.close();
		 	 stmt6.close();
		 	 stmt7.close();
		 	 con.close();
		}
		catch(Exception e)
		{
			
		}
	}   	  	    
}