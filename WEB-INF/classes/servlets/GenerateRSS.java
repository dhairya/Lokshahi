package servlets;

import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.sql.Timestamp;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class for Servlet: GenerateRSS
 *
 */
 public class GenerateRSS extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public GenerateRSS() {
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
		Connection con;
		Statement stmt;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		Statement stmt1;
		Statement stmt2;
		try{
			
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt = con.createStatement();
			 stmt1 = con.createStatement();
			 stmt2 = con.createStatement();
			 String query = "Select CODE from CONSTITUENCY";
			 rs = stmt.executeQuery(query);
			//for Grievances 
             int c=0,i=0,j=0,k=0,var=0;
			 while(rs.next())
				 c++;
			
			 String code[] = new String[c];
			 int count[] = new int[c];
			 //int pcount[] = new int[c];
			 for(i=0;i<count.length;i++)
				 {count[i]=0;
				  //pcount[i]=0;
				 }
		
			 i = 0;
			 rs = stmt.executeQuery(query);
		
			 while(rs.next())
			 {code[i] = rs.getString("CODE");
			  i++;
			 }
			 
			
			 c=0;
			 rs = stmt.executeQuery(query);
			 while(rs.next())
			 {
				 String query1 = "select TITLE,DATE from "+code[j]+"_GRIEVANCES";
				 rs1 = stmt1.executeQuery(query1);
				 while(rs1.next())
					 c++;
				 j++;
			 }
		
			 long time[] = new long[c];
			 String title[] = new String[c];
			 rs = stmt.executeQuery(query);
			 i=0;
			 j = 0;
			 while(rs.next())
			 {
				 String query1 = "select TITLE,DATE from "+code[j]+"_GRIEVANCES";
				 rs1 = stmt1.executeQuery(query1);
				 while(rs1.next())
				 {
					 time[i] = rs1.getTimestamp("DATE").getTime();
					 title[i] = rs1.getString("TITLE");
					 i++;
				 }
				 if(j>0)
				 { 
			      var = 0;
				  for(k=0;k<count.length;k++)
					  var = var+count[k];
				  count[j] = i - var;
				 }
				 else
				   count[j] = i;
				 j++;
			 }
			 
		
			java.sql.Timestamp stamp = new java.sql.Timestamp(new java.util.Date().getTime());
			long tym = stamp.getTime();
		 	
		    String f="C:\\Newfolder\\CMS\\WebContent\\rss.xml";
		    FileOutputStream fop = new  FileOutputStream(f);
		    String str="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n"
		          +"<rss version=\"2.0\"><channel>\n<title>ppl2.0</title>\n<description>Constituency Manangement System</description>\n<link></link>\n";
		    k=0;
		
		          for(i=0;i<code.length;i++)
		          {  for(j=0;j<count[i];j++,k++)
		             { 
		        	  
		        	  if(tym-time[k]<=(24*60*60*1000))
		        	   {
		        		  str = str+"<item>\n<title>"+code[i]+" - GRIEVANCE ZONE</title>\n"; 
		        	   	  str = str+"<description>"+title[k]+"</description>\n";
						  str = str+"<link></link>\n</item>\n";
		        	   }
		             }
		          }
		          
		        
		          
		     //for polls
		      c=0;
		      j=0;
			  rs = stmt.executeQuery(query);
			  while(rs.next())
			  {
				String query1 = "select QUESTION,CREATE_INST from "+code[j]+"_POLL";
				rs2 = stmt2.executeQuery(query1);
				while(rs2.next())
					c++;
				j++;
			  }
			 
			  long polltime[] = new long[c];
			  String ques[] = new String[c];
			  rs = stmt.executeQuery(query);
			  i=0;
			  j = 0;
			  while(rs.next())
			  {
				  String query1 = "select QUESTION,CREATE_INST from "+code[j]+"_POLL";
					 rs2 = stmt2.executeQuery(query1);
					 while(rs2.next())
					 {
						 polltime[i] = rs2.getTimestamp("CREATE_INST").getTime();
						 ques[i] = rs2.getString("QUESTION");
						 i++;
					 }
					 if(j>0)
					 { 
				      var = 0;
					  for(k=0;k<count.length;k++)
						  var = var+count[k];
					  count[j] = i - var;
					 }
					 else
					   count[j] = i;
					 j++;
				 }
		
			  k=0;
	          for(i=0;i<code.length;i++)
	          {  for(j=0;j<count[i];j++,k++)
	             { 
	        	  
	        	  if(tym-polltime[k]<=(24*60*60*1000))
	        	   {
	        		  str = str+"<item>\n<title>"+code[i]+" - POLL</title>\n"; 
	        	   	  str = str+"<description>"+ques[k]+"</description>\n";
					  str = str+"<link></link>\n</item>\n";
	        	   }
	             }
	          }
	          
		           str = str+"</channel>\n</rss>\n";
		            
		            fop.write(str.getBytes());

			        fop.flush();
			        fop.close();
			        rs.close();
			        rs1.close();
			        rs2.close();
			        stmt.close();
			        stmt1.close();
			        stmt2.close();
			        response.sendRedirect("./ADMIN/cpanel.jsp");  
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}   	  	    
}