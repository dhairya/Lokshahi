package beans;

import java.sql.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Changeidbean {
	private String voterid;
	private String nickname;
	private String code;
	private String compleid;
	
	private Connection conn;
	private Statement stmt;
	private Statement stmt1;
	private Statement stmt2;
	private ResultSet rs;
	private ResultSet rs1;
	
	public Changeidbean() throws Exception {}
	
	public void init() throws Exception {
		InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
        conn = ds.getConnection();
	 rs = null;
	rs1 = null;
	stmt = conn.createStatement();
	stmt1 = conn.createStatement();
	stmt2 = conn.createStatement();
	}
	
	public void terminate() {
		try{
		if (rs != null){
			rs.close();
			rs = null;
		}
		if (rs1 != null){
			rs1.close();
			rs1 = null;
		}
		
		stmt.close();
		stmt1.close();
		stmt2.close();
		conn.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	}
	
	public void setVoterid(String x) {
		this.voterid = x;
	}
	public void setNickname(String x) {
		this.nickname = x;
	}
	public void setCode(String x){
		this.code=x;
	}
	
	
	public void execQueryChangeid() throws Exception
	{   compleid = code;
		compleid = compleid.concat(voterid);
		String query = "Delete from DB2ADMIN."+code+"_USER_DETAILS where NICKNAME = '"
		               +this.nickname+"'";
		stmt.executeUpdate(query);
		
		String query1 = "Delete from DB2ADMIN."+code+"_LOGIN where VOTER_ID = '"
                         +this.voterid+"'";
		stmt1.executeUpdate(query1);
		
		String query2 = "UPDATE DB2ADMIN."+code+"_FORUM SET NICKNAME = 'DEFAULT' WHERE NICKNAME = '"+this.nickname+"'";
		stmt2.executeUpdate(query2);
		query2 = "UPDATE DB2ADMIN."+code+"_GRIEVANCE_COMMENTS SET NICKNAME = 'DEFAULT' WHERE NICKNAME = '"+this.nickname+"'";
		stmt2.executeUpdate(query2);
		query2 = "UPDATE DB2ADMIN."+code+"_GRIEVANCES SET NICKNAME = 'DEFAULT' WHERE NICKNAME = '"+this.nickname+"'";
		stmt2.executeUpdate(query2);
		query2 = "UPDATE DB2ADMIN."+code+"_INVESTIGATION SET NICKNAME = 'DEFAULT' WHERE NICKNAME = '"+this.nickname+"'";
		stmt2.executeUpdate(query2);
		query2 = "UPDATE DB2ADMIN."+code+"_POLL SET NICKNAME = 'DEFAULT' WHERE NICKNAME = '"+this.nickname+"'";
		stmt2.executeUpdate(query2);
		query2 = "UPDATE DB2ADMIN."+code+"_VOTERS_JOINED SET NICKNAME = 'DEFAULT' WHERE NICKNAME = '"+this.nickname+"'";
		stmt2.executeUpdate(query2);
	}
}
