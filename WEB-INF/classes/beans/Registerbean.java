package beans;
import java.sql.*;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

public class Registerbean {
	private String voterid;
	private String password;
	private String fname;
	private String lname;
	private String mname;
	private String nickname;
	private String gender;
	private String date;
	private String month;
	private String year;
	private String address;
	private String email;
	private String phone;
	private String cell;
    private String code;
    private String ward;
    private String compleid =null;
    
	private Connection conn;
	private QueryRunner run;
	private Statement stmt;
	private Statement stmt1;
	private Statement stmt2;
	private Statement stmt3;
	private ResultSet rs;
	private ResultSet rs1;
	private ResultSet rs2;
	private ResultSet rs3;
	
	public Registerbean() throws Exception {}
	
	public void init() throws Exception {
		InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
        conn = ds.getConnection();
        run = new QueryRunner(ds);
        rs = null;
        rs1 = null;
        rs2 = null;
        rs3 = null;
        stmt = conn.createStatement();
        stmt1 = conn.createStatement();
        stmt2 = conn.createStatement();
        stmt3 = conn.createStatement();
	}
	
	public void terminate() throws Exception {
		if (rs != null){
			rs.close();
			rs=null;
		}
		if (rs1 != null){
			rs1.close();
			rs1=null;
		}
		if (rs2 != null){
			rs2.close();
			rs2=null;
		}
		if (rs3 != null){
			rs3.close();
			rs3=null;
		}
		
		stmt.close();
		stmt=null;
		
		stmt1.close();
		stmt1=null;
		
		stmt2.close();
		stmt2=null;
		
		stmt3.close();
		stmt3=null;
		
		conn.close();
		conn=null;
	}
	public void setFname(String x) {
		this.fname = x;
	}

	public void setMname(String x) {
		this.mname = x;
	}

	public void setLname(String x) {
		this.lname = x;
	}
	public void setvoterid(String x) {
		this.voterid = x;
	}

	public void setpassword(String x) {
		this.password = x;
	}

	public void setnickname(String x) {
		this.nickname = x;
	}
	public void setgender(String x) {
		this.gender = x;
	}

	public void setdate(String x) {
		this.date = x;
	}

	public void setmonth(String x) {
		this.month = x;
	}
	public void setyear(String x) {
		this.year = x;
	}

	public void setaddress(String x) {
		this.address = x;
	}

	public void setemail(String x) {
		this.email = x;
	}
	public void setphone(String x) {
		this.phone = x;
	}

	public void setcell(String x) {
		this.cell = x;
	}
	public void setconsti(String x){
		this.code=x;
	}
	public void setWard(String x){
		this.ward=x;
	}
	public String getemail()
	{
		return this.email;
	}
	
     
	public void execQueryRegister() throws Exception
	{
              	
	    	rs1 = stmt1
                      .executeQuery("Select CODE from CONSTITUENCY where CODE = '" 
      		                         + this.code + "'"); 
		if(rs1.next())
		{
			String query="Insert into TEMPUSER values('"+this.voterid+"','"+this.password+"','"
			              +this.fname+"','"+this.mname+"','"+this.lname+"','"+this.nickname+"','"+this.code+"','"+this.ward+"','"
			              +this.date+"','"+this.month+"','"+this.year+"','"+this.gender+"','"
			              +this.address+"','"+this.email+"','"+this.phone+"','"+this.cell+"')";
			
			stmt.executeUpdate(query);
			return;
		}
		else 
		{
			return;
		}
		
	}
	public List<Map<String, Object>> execQueryApprove() throws Exception
	{
		String query1="Select * from TEMPUSER";
	    
        MapListHandler  h = new MapListHandler();
                
        List<Map<String,Object>> rs2_map = run.query(query1, h);
        return rs2_map;
	}
	
	public String execQueryFinalApprove() throws Exception
	{   
	    String query3 = "Select * from TEMPUSER where VOTERID = '"
	    	+this.voterid+ "'";
	    		
		rs = stmt.executeQuery(query3);
		
		fname = rs.getString("FNAME");
		mname = rs.getString("MNAME");
		lname = rs.getString("LNAME");
		password = rs.getString("PASSWORD");
		nickname = rs.getString("NICKNAME");
		voterid = rs.getString("VOTERID");
		gender = rs.getString("GENDER");
		date = rs.getString("DDATE");
		month = rs.getString("MMONTH");
		year = rs.getString("YYEAR");
		address = rs.getString("ADDRESS");
		email = rs.getString("EMAIL");
		phone = rs.getString("PHONE");
		cell = rs.getString("CELL");
		
		String s=voterid.substring(0,3);
		String s1=voterid.substring(3);
		String s2,s3;
		s=s.toUpperCase();
		s2=""+s+"_LOGIN";
		s3=""+s+"_USER_DETAILS";
		
		String query2 = "Insert into" +s3+ "values('"+s1+"','"
        +fname+"','"+mname+"','"+lname+"','"+nickname+"','"
        +date+"','"+month+"','"+year+"','"+gender+"','"
        +address+"','"+email+"','"+phone+"','"+cell+"')";
		
		stmt1.executeUpdate(query2);
		
		String query4 = "Insert into" +s2+ "values('"+s1+"',"
		                +password+"')";
		
		stmt2.executeUpdate(query4);
		
		String query5 = "Delete from TEMPUSER where VOTERID = '"+this.voterid+"'"; 
		
		stmt3.executeUpdate(query5);
		
		return email;   
	}
	
	public String execQueryFinalReject() throws Exception
	{
	    String query6 = "Select EMAIL from TEMPUSER where VOTERID = '" +this.voterid+"'";
	    rs = stmt.executeQuery(query6);
	    email=rs.getString("EMAIL");
	    
	    String query7 = "Delete from TEMPUSER where VOTERID = '"+this.voterid+"'"; 
	    stmt1.executeUpdate(query7);
	    return email;
	    
	}

}
