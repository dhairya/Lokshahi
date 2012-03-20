package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Mapbean {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private String consti;
	
	public Mapbean() throws Exception {}
	
	public void setConsti(String x) {
		this.consti = x;
	}
	
	public String mapQuery() throws Exception
	{
		InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
        conn = ds.getConnection();
        stmt = conn.createStatement();
        String query = "select MAP from KBASE where CODE = '"+this.consti+"'";
        rs = stmt.executeQuery(query);
        
        rs.next();
        String mapinfo = rs.getString("MAP"); 
        
        rs.close();
        rs=null;
        stmt.close();
        stmt=null;
        conn.close();
        conn=null;
        
        return mapinfo;
	}

}
