package Classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;


public class Intelli_rating {
	ResultSet rs, rs1;
	private String code;
	private String ward;
	private long probid;
	private Connection conn;
	private Statement stmt;
	private Statement stmt1;
	
	public Intelli_rating(ResultSet rs) {
		super();
		this.rs = rs;
	}
	//call this function 2 pass consti code n probid----mite b in Grievance_change.java!!!
	//i have made changes in grievance_change.java
	public void setValues(String x,String z, long y)throws Exception
	{
		this.code = x;
		this.ward = z;
		this.probid = y;
	}
	
	public float rating() throws Exception
	{   InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
        conn = ds.getConnection();
        stmt = conn.createStatement();
        stmt1 = conn.createStatement();
		int i=0, joined, jdate;
		float rating;
		
		String query = "Select PUBLIC_RATING, ADMIN_RATING, DATE from DB2ADMIN."+this.code+"_"+this.ward+"_GRIEVANCES where REF_ID = "+this.probid;
		rs = stmt.executeQuery(query);
		
		
		
		String query1 = "select NICKNAME from DB2ADMIN."+this.code+"_"+this.ward+"_VOTERS_JOINED where REF_ID = "+this.probid;
		rs1 = stmt1.executeQuery(query1);
		
		
		rs.next();
		long timej = rs.getTimestamp("DATE").getTime();
		java.sql.Timestamp stamp = new java.sql.Timestamp(new java.util.Date().getTime());
		long timen = stamp.getTime();
		if(timen - timej < 2*24*60*60 )
			jdate=0;
		else if(timen - timej < 10*24*60*60)
			jdate=1;
		else if(timen - timej < 25*24*60*60)
			jdate=2;
		else if(timen - timej < 60*24*60*60)
			jdate=3;
		else if(timen - timej < 120*24*60*60)
			jdate=4;
		else
			jdate=5;
		
		while(rs1.next())
		{
			i++;
		}
		if(i==0)
		    joined=0;
		else if(i<10)
			joined=1;
		else if(i<50)
			joined=2;
		else if(i<200)
			joined=3;
		else if(i<500)
			joined=4;
		else 
			joined=5;
		
		
		
		rating = (rs.getFloat("PUBLIC_RATING")*30+rs.getFloat("ADMIN_RATING")*30+
		         joined*25+jdate*15)/100;
		
		rs.close();
		rs=null;
		rs1.close();
		rs1=null;
        stmt.close();
		stmt=null;
		stmt1.close();
		stmt1=null;
		conn.close();
		conn=null;
		
		return rating;
	}
	

}