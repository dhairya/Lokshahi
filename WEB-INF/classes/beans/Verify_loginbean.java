package beans;

import java.sql.*;

import javax.naming.InitialContext;
import javax.sql.*;

public class Verify_loginbean {
	private String voterid;
	private String passwd;
	private String table_code;
	
	private Connection conn;
	private Statement stmt;
	private Statement stmt1;
	private Statement stmt2;
	
	private ResultSet rs;
	private ResultSet rs1;
	private ResultSet rs2;
	
	public Verify_loginbean() throws Exception {}
		public void init() throws Exception {
				InitialContext ctx = new InitialContext();
		        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
		        conn = ds.getConnection();
			rs = null;
			rs1 = null;
			rs2 = null;

			stmt = conn.createStatement();
			stmt1 = conn.createStatement();
			stmt2 = conn.createStatement();
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
			stmt.close();
			stmt = null;
			
			stmt1.close();
			stmt1 = null;
			
			stmt2.close();
			stmt2 = null;
			
			conn.close();
			conn = null;

		}
		public void setvoterid(String x) {
			this.voterid = x;
		}

		public void setPasswd(String x) {
			this.passwd = x;
		}
		public String getvoterid() {
			return this.voterid;
		}

		public String getPasswd() {
			return this.passwd;
		}
		
		public void settable_name(String x)
		{
			this.table_code=x;
		}
		public String gettable_name()
		{
			return this.table_code;
		}
		
		
		//Login Check for Citizens
		public String execQueryLogin(String init_vals[]) throws Exception

		{   
				rs = stmt
						.executeQuery("select * from GLOBAL where VOTERID = '"
								+ this.voterid
								+ "' and PASSWORD = '"
								+ this.passwd
								+ "'");
				if (rs !=null && rs.next()) {
					String table_code=rs.getString("CONSTI_WARD");
					rs1=stmt1.executeQuery("select *  from "+table_code+"_USER_DETAILS where VOTERID = '"
											+this.voterid+"'");
					if(rs1.next()){
					init_vals[0]=rs1.getString("ACCESS_INFO");
					init_vals[1]=rs1.getString("NICKNAME");
					//init_vals[2]=rs1.getString("CATEGORIES");
					init_vals[3]=table_code;
					
					}
					return "right";
				}
				else 
				{
					return("wrong");
				 }
			}
			
	
		//Login Check for special users
		public String execSpeciallogin(String init_vals[]) throws Exception{
			rs = stmt
					.executeQuery("select * from "+this.table_code+"_SPECIALUSERS where LOGIN = '"
							+ this.voterid
							+ "' and PASSWORD = '"
							+ this.passwd
							+ "'");
			if (rs.next()) {
				init_vals[0] = rs.getString("ACCESS_INFO");
				init_vals[1] = rs.getString("USERTYPE");
				init_vals[2] = rs.getString("SOLVABLE_CATEGORIES");
				if(this.voterid.startsWith("C")){
				init_vals[3] = rs.getString("ACCESS_WARDS");
				}
				return ("right");
			}
			else 
			  {return("wrong");
			  }
		}
		
		//Login Check for ADMIN
		public String execAdminlogin() throws Exception{
			rs = stmt
					.executeQuery("select USERNAME from GLOBAL_DATA where USERNAME = '"
							+ this.voterid
							+ "' and PASSWORD = '"
							+ this.passwd
							+ "'");
			if (rs.next()) {
				return ("right");
			}
			else 
			  {return("wrong");
			  }
		}
}

