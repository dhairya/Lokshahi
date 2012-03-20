package beans;
import java.sql.*;
import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.sql.DataSource;

public class Createconstibean {
	private String cname;
	private String code;
	
	private Connection conn;
	private Statement stmt;
	private Statement stmt1;
	/*private Statement stmt2;
	private Statement stmt3;
	private Statement stmt4;
	private Statement stmt5;
	private Statement stmt6;*/
	private Statement stmt7;
/*	private Statement stmt8;
	private Statement stmt9;
	private Statement stmt10;
	private Statement stmt11;*/
	private Statement stmt12;
	private Statement stmt13;
	private Statement stmt14;
	private Statement stmt15;
	private Statement stmt16;
	private ResultSet rs;
	
	public Createconstibean() throws Exception {}
	public void init() throws Exception {
		InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
        conn = ds.getConnection();
        rs = null;
        stmt = conn.createStatement();
        stmt1 = conn.createStatement();
      /*  stmt2 = conn.createStatement();
        stmt3 = conn.createStatement();
        stmt4 = conn.createStatement();
        stmt5= conn.createStatement();
        stmt6 = conn.createStatement();*/
        stmt7 = conn.createStatement();
 /*       stmt8 = conn.createStatement();
        stmt9 = conn.createStatement();
        stmt10 = conn.createStatement();
        stmt11 = conn.createStatement();*/
        stmt12 = conn.createStatement();
        stmt13 = conn.createStatement();
        stmt14 = conn.createStatement();
        stmt15 = conn.createStatement();
        stmt16 = conn.createStatement();
	}
	public void terminate() throws Exception {
		if (rs != null){
			rs.close();
			rs=null;
		}
		stmt.close();
		stmt = null;
		
		stmt1.close();
		stmt1 = null;
/*		
		stmt2.close();
		stmt2 = null;
		
		stmt3.close();
		stmt3 = null;
		
		stmt4.close();
		stmt4 = null;
		
		stmt5.close();
		stmt5 = null;
		
		stmt6.close();
		stmt6 = null;
*/		
		stmt7.close();
		stmt7 = null;
/*		
		stmt8.close();
		stmt8 = null;
		
		stmt9.close();
		stmt9 = null;
		
		stmt10.close();
		stmt10 = null;
		
		stmt11.close();
		stmt11 = null;
*/		
		stmt12.close();
		stmt12 = null;
		
		stmt13.close();
		stmt13 = null;
		
		stmt14.close();
		stmt14 = null;
		
		stmt15.close();
		stmt15 = null;
		
		stmt16.close();
		stmt16 = null;
		
		conn.close();
		conn = null;
	}
	
	public void setConstiname(String x) {
		this.cname = x;
	}
	public void setCode(String x) {
		this.code = x;
	}
	public String execCreateTables() throws Exception
	{   
		String query = "select * FROM CONSTITUENCY WHERE CODE = '"+this.code+"'";
		rs = stmt.executeQuery(query);
		if(rs.next())
		{	
		return("fail");
		}
		else{
			//insert code and name into table constituency
			String query1 = "insert into CONSTITUENCY values('"+this.code+"','"+this.cname+"')";
			stmt1.executeUpdate(query1);
			/*
			//create table userdetails
			String query2 = "create table "+this.code+"_USER_DETAILS(VOTERID VARCHAR(6) NOT NULL," +
					"FNAME VARCHAR(25)," +
					"MNAME VARCHAR(25)," +
					"LNAME VARCHAR(25), " +
					"NICKNAME VARCHAR(25) NOT NULL, " +
					"DDATE CHAR(2) , " +
					"MMONTH CHAR(2), " +
					"YYEAR CHAR(4) , " +
					"GENDER CHAR(1) , " +
					"ADDRESS VARCHAR(2000)," +
					"EMAIL VARCHAR(25) , " +
					"PHONE VARCHAR(18), " +
					"CELL CHAR(12)," +
					" PRIMARY KEY(NICKNAME))";
			stmt2.executeUpdate(query2);
			
			
			//create table poll
			String query3 = "Create table "+this.code+"_POLL (POLLNO BIGINT GENERATED ALWAYS AS IDENTITY(START WITH 0," +
			          "INCREMENT BY 1),NICKNAME VARCHAR(25) NOT NULL ,"+
					  "OPT1 VARCHAR(20) NOT NULL ,OPT1_CNT BIGINT NOT NULL WITH DEFAULT 0 ,"+  
					  "OPT2 VARCHAR(20) NOT NULL ,OPT2_CNT BIGINT NOT NULL WITH DEFAULT 0 ,"+  		
					  "OPT3 VARCHAR(20) NOT NULL ,OPT3_CNT BIGINT NOT NULL WITH DEFAULT 0 ,"+  
					  "OPT4 VARCHAR(20) NOT NULL ,OPT4_CNT BIGINT NOT NULL WITH DEFAULT 0 ,"+  
					  "QUESTION VARCHAR(2000), CREATE_INST TIMESTAMP, PRIMARY KEY(POLLNO))"; 
			stmt3.executeUpdate(query3);
			
			//create table login
			String query4 ="CREATE TABLE "+this.code+"_LOGIN(VOTER_ID VARCHAR(10) NOT NULL," +
					"PASSWORD VARCHAR(25) NOT NULL, PRIMARY KEY(VOTER_ID))"; 
			stmt4.executeUpdate(query4);
		
			
			//create table forum
			String query5 = "CREATE TABLE "+this.code+"_FORUM (FOR_ID BIGINT GENERATED ALWAYS AS IDENTITY(START WITH 0," +
			          "INCREMENT BY 1) ," +
					"NICKNAME VARCHAR(25) NOT NULL,TITLE VARCHAR(200) NOT NULL, DESCRIPTION VARCHAR(3500) NOT NULL , " +
					"DATE TIMESTAMP, PRIMARY KEY(FOR_ID) )"; 
			stmt5.executeUpdate(query5);
			
			//create table forum_subpost
			String query6 = "CREATE TABLE "+this.code+"_FORUM_SUBPOST (FOR_ID BIGINT NOT NULL, " +
					" NICKNAME VARCHAR(25) NOT NULL, DESCRIPTION VARCHAR(3500) NOT NULL, DATE TIMESTAMP, " +
					" FOREIGN KEY(FOR_ID) " +
					"REFERENCES "+this.code+"_FORUM ON DELETE CASCADE ON UPDATE NO ACTION)";
			stmt6.executeUpdate(query6);*/
			
			//create table funds
			String query7 = "CREATE TABLE "+this.code+"_FUNDS ( FUND_ID BIGINT NOT NULL AUTO_INCREMENT, NICKNAME VARCHAR(25) NOT NULL ,  " +
					"DESCRIPTION VARCHAR(3000) NOT NULL , COST BIGINT NOT NULL , " +
					" TITLE VARCHAR(350) NULL, DATE TIMESTAMP NULL, PRIMARY KEY(FUND_ID))";
			stmt7.executeUpdate(query7);

			
			/*create table grievance
			String query8 = "CREATE TABLE "+this.code+"_GRIEVANCES (REF_ID BIGINT NOT NULL GENERATED ALWAYS AS IDENTITY ( START WITH +0," +
					"INCREMENT BY +1) , " +
					"NICKNAME VARCHAR(25) NOT NULL ,  " +
					"DESCRIPTION VARCHAR(1500) NOT NULL , " +
					"NUM_PPL_RATED INTEGER NOT NULL WITH DEFAULT 0 , " +
					"PUBLIC_RATING REAL NOT NULL WITH DEFAULT 0 , " +
					"ADMIN_RATING REAL NOT NULL WITH DEFAULT 0 , " +
					" INTELLI_RATING REAL NOT NULL WITH DEFAULT 0 , " +
					"STATUS INTEGER NOT NULL WITH DEFAULT 0 , " + 
					"NUM_PPL_JOINED INTEGER NOT NULL WITH DEFAULT 0 , " +
					"TITLE VARCHAR(100) , DEPARTMENT VARCHAR(20) , " +
					" REPLY VARCHAR(1500) , DATE TIMESTAMP, PRIMARY KEY(REF_ID))";
			stmt8.executeUpdate(query8);
			
			
			//create table grievance comments
			String query9 = "CREATE TABLE "+this.code+"_GRIEVANCE_COMMENTS ( REF_ID BIGINT NOT NULL, " +
					"NICKNAME VARCHAR(25) NOT NULL ,  COMMENTS VARCHAR(3000) NOT NULL ," +
					" DATE TIMESTAMP, FOREIGN KEY(REF_ID) REFERENCES "+this.code+"_GRIEVANCES ON DELETE CASCADE ON UPDATE NO ACTION)";
			stmt9.executeUpdate(query9);
			
			
			//create table investigation
			String query10 = "CREATE TABLE "+this.code+"_INVESTIGATION " +
					"(REF_ID BIGINT NOT NULL, NICKNAME VARCHAR(25) NOT NULL , " +
					"DATE TIMESTAMP, FOREIGN KEY(REF_ID) REFERENCES "+this.code+"_GRIEVANCES ON DELETE CASCADE ON UPDATE NO ACTION)";
			stmt10.executeUpdate(query10);
			
			
			//create table voters_joined
			String query11 = "CREATE TABLE "+this.code+"_VOTERS_JOINED (REF_ID BIGINT NOT NULL ," +
					" NICKNAME VARCHAR(25) NOT NULL , DATE TIMESTAMP, FOREIGN KEY(REF_ID) " +
					"REFERENCES "+this.code+"_GRIEVANCES ON DELETE CASCADE ON UPDATE NO ACTION)";   
			stmt11.executeUpdate(query11);*/
			
			//create table chat
			String query12 = "CREATE TABLE "+this.code+"_CHAT (MESSAGE_ID BIGINT NOT NULL AUTO_INCREMENT, USERNAME VARCHAR(25) NULL, MESSAGE VARCHAR(3500) NULL , DATE TIMESTAMP NULL, LEADER_DATE VARCHAR(25) NULL, LEADER_TIME VARCHAR(25) NULL, PRIMARY KEY(MESSAGE_ID))";
			stmt12.executeUpdate(query12);
			
			String query13 = "insert into "+this.code+"_CHAT (USERNAME,LEADER_DATE,LEADER_TIME) " +
					" values ('LEADER','NA','NA') ";
			stmt13.executeUpdate(query13);
	        
			//adding consti code to kbase
			String query14 = "INSERT into KBASE (CODE) VALUES ('"+this.code+"')";
			stmt14.executeUpdate(query14);
			
			// create table special users for consti
			String query15 = "CREATE TABLE "+this.code+"_SPECIALUSERS (USER_ID BIGINT NOT NULL AUTO_INCREMENT, USERTYPE VARCHAR(100), NAME VARCHAR(50), LOGIN VARCHAR(30), PASSWORD VARCHAR(100), ACCESS_INFO VARCHAR(10), EMAILID VARCHAR(50), PHONE VARCHAR(20), SOLVABLE_CATEGORIES VARCHAR(10), ACCESS_WARDS VARCHAR(30), PRIMARY KEY(USER_ID))";
			stmt15.executeUpdate(query15);
			
			String query16 = "CREATE TABLE "+this.code+"_WARDS (NUMBER VARCHAR(10))";
			stmt16.executeUpdate(query16);
			return("done");
		}
	
	  
	}

}
