package servlets;


import java.io.IOException;
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
 * Servlet implementation class for Servlet: Createward
 *
 */
 public class Createward extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Createward() {
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
		Connection con;
		Statement stmt;
		Statement stmt1;
		Statement stmt2;
		Statement stmt3;
		Statement stmt4;
		Statement stmt5;
		Statement stmt6;
		Statement stmt7;
		Statement stmt8;
		Statement stmt9;
		Statement stmt10;
		String consti_code =(String) request.getParameter("code");
		String ward_code =(String) request.getParameter("name");
		consti_code = consti_code.toUpperCase();
		//ward_code = ward_code.toUpperCase();
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
			 stmt8 = con.createStatement();
			 stmt9 = con.createStatement();
			 stmt10 = con.createStatement();
			 
			 // create table user details
			 String query = "create table "+consti_code+"_"+ward_code+"_USER_DETAILS(VOTERID VARCHAR(10) NOT NULL," +
				"FNAME VARCHAR(25)," +
				"MNAME VARCHAR(25)," +
				"LNAME VARCHAR(25), " +
				"NICKNAME VARCHAR(25) NOT NULL, " +
				"ACCESS_INFO VARCHAR(10), "+
				"SOLVABLE_CATEGORIES VARCHAR(30), "+
				"DDATE CHAR(2) , " +
				"MMONTH CHAR(2), " +
				"YYEAR CHAR(4) , " +
				"GENDER CHAR(1) , " +
				"ADDRESS VARCHAR(2000)," +
				"EMAIL VARCHAR(25) , " +
				"PHONE VARCHAR(18), " +
				"CELL CHAR(12)," +
				"SKILLS VARCHAR(1000), "+
				"QUALIFICATION VARCHAR(50), "+
				"EXPERIENCE VARCHAR(50), "+
				" PRIMARY KEY(NICKNAME))";
		stmt.executeUpdate(query);
		
		//create table poll
		String query1 = "Create table "+consti_code+"_"+ward_code+"_POLL (POLLNO BIGINT NOT NULL AUTO_INCREMENT,NICKNAME VARCHAR(25) NOT NULL ,"+
				  "OPT1 VARCHAR(20) NOT NULL ,OPT1_CNT BIGINT NOT NULL DEFAULT 0 ,"+  
				  "OPT2 VARCHAR(20) NOT NULL ,OPT2_CNT BIGINT NOT NULL DEFAULT 0 ,"+  		
				  "OPT3 VARCHAR(20) NOT NULL ,OPT3_CNT BIGINT NOT NULL DEFAULT 0 ,"+  
				  "OPT4 VARCHAR(20) NOT NULL ,OPT4_CNT BIGINT NOT NULL DEFAULT 0 ,"+  
				  "QUESTION VARCHAR(2000), CREATE_INST TIMESTAMP, PRIMARY KEY(POLLNO))"; 
		stmt1.executeUpdate(query1);
		
		//create table forum
		String query2 = "CREATE TABLE "+consti_code+"_"+ward_code+"_FORUM (FOR_ID BIGINT NOT NULL AUTO_INCREMENT," +
				"NICKNAME VARCHAR(25) NOT NULL,TITLE VARCHAR(200) NOT NULL, DESCRIPTION VARCHAR(3500) NOT NULL , " +
				"DATE TIMESTAMP, PRIMARY KEY(FOR_ID) ) ENGINE = InnoDB "; 
		stmt2.executeUpdate(query2);
		
		//create table forum_subpost
		String query3 = "CREATE TABLE "+consti_code+"_"+ward_code+"_FORUM_SUBPOST (SUB_ID BIGINT NOT NULL AUTO_INCREMENT, FOR_ID BIGINT NOT NULL, " +
				" NICKNAME VARCHAR(25) NOT NULL, DESCRIPTION VARCHAR(3500) NOT NULL, DATE TIMESTAMP, PRIMARY KEY(SUB_ID), "+
				" FOREIGN KEY(FOR_ID) REFERENCES "+consti_code+"_"+ward_code+"_FORUM(FOR_ID) ON DELETE CASCADE ON UPDATE NO ACTION)" +
				" ENGINE = InnoDB ";
		stmt3.executeUpdate(query3);
		
		//create table grievance
		String query4 = "CREATE TABLE "+consti_code+"_"+ward_code+"_GRIEVANCES (REF_ID BIGINT NOT NULL AUTO_INCREMENT, " +
				"NICKNAME VARCHAR(25) NOT NULL ,  " +
				"DESCRIPTION VARCHAR(1500) NOT NULL , " +
				"NUM_PPL_RATED INTEGER NOT NULL DEFAULT 0 , " +
				"PUBLIC_RATING REAL NOT NULL DEFAULT 0 , " +
				"ADMIN_RATING REAL NOT NULL DEFAULT 0 , " +
				" INTELLI_RATING REAL NOT NULL DEFAULT 0 , " +
				"STATUS INTEGER NOT NULL DEFAULT 0 , " +
				"NUM_PPL_JOINED INTEGER NOT NULL DEFAULT 0 , " +
				"TITLE VARCHAR(100) , DEPARTMENT VARCHAR(20) , " +
				" REPLY VARCHAR(1500) , DATE TIMESTAMP, PRIMARY KEY(REF_ID)) ENGINE = InnoDB";
		stmt4.executeUpdate(query4);
		
		
		//create table grievance comments
		String query5 = "CREATE TABLE "+consti_code+"_"+ward_code+"_GRIEVANCE_COMMENTS ( REF_ID BIGINT NOT NULL, " +
				"NICKNAME VARCHAR(25) NOT NULL ,  COMMENTS VARCHAR(3000) NOT NULL ," +
				" DATE TIMESTAMP) ENGINE = InnoDB";
		stmt5.executeUpdate(query5);
		
		
		//create table investigation
		String query6 = "CREATE TABLE "+consti_code+"_"+ward_code+"_INVESTIGATION " +
				"(REF_ID BIGINT NOT NULL, NICKNAME VARCHAR(25) NOT NULL , " +
				"DATE TIMESTAMP, FOREIGN KEY(REF_ID) REFERENCES "+consti_code+"_"+ward_code+"_GRIEVANCES(REF_ID) " +
				"ON DELETE CASCADE ON UPDATE NO ACTION) ENGINE = InnoDB";
		stmt6.executeUpdate(query6);
		
		
		//create table voters_joined
		String query7 = "CREATE TABLE "+consti_code+"_"+ward_code+"_VOTERS_JOINED (REF_ID BIGINT NOT NULL ," +
				" NICKNAME VARCHAR(25) NOT NULL , DATE TIMESTAMP, FOREIGN KEY(REF_ID) " +
				"REFERENCES "+consti_code+"_"+ward_code+"_GRIEVANCES(REF_ID) ON DELETE CASCADE ON UPDATE NO ACTION) ENGINE = InnoDB";   
		stmt7.executeUpdate(query7);
		
		// create table special users for wards
		String query8 = "CREATE TABLE "+consti_code+"_"+ward_code+"_SPECIALUSERS (USER_ID BIGINT NOT NULL AUTO_INCREMENT," +
				" USERTYPE VARCHAR(100), NAME VARCHAR(50), LOGIN VARCHAR(30), PASSWORD VARCHAR(100), ACCESS_INFO VARCHAR(10), " +
				"EMAILID VARCHAR(50), PHONE VARCHAR(20), SOLVABLE_CATEGORIES VARCHAR(10), PRIMARY KEY(USER_ID))";
		stmt8.executeUpdate(query8);
		
		//create table tempgrievance
		String query9 = "CREATE TABLE "+consti_code+"_"+ward_code+"_TEMPGRIEVANCES (REF_ID BIGINT NOT NULL AUTO_INCREMENT , " +
				"NICKNAME VARCHAR(25) NOT NULL ,  " +
				"DESCRIPTION VARCHAR(1500) NOT NULL , " +
				"TITLE VARCHAR(100) , DEPARTMENT VARCHAR(20) , " +
				"DATE TIMESTAMP, PRIMARY KEY(REF_ID))";
		stmt9.executeUpdate(query9);
		
		String query10 = "INSERT into "+consti_code+"_WARDS (NUMBER) VALUES ('"+ward_code+"')";
		stmt10.executeUpdate(query10);
		
		RequestDispatcher view = request
		.getRequestDispatcher("./ADMIN/cpanel.jsp");
view.forward(request, response);
stmt.close();
 stmt1.close();
 stmt2.close();
 stmt3.close();
 stmt4.close();
 stmt5.close();
 stmt6.close();
 stmt7.close();
 stmt8.close();
 stmt9.close();
 stmt10.close();
 con.close();
		}
		catch (Exception e)
		{
			
		}
		
	}   	  	    
}