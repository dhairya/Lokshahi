package beans;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

public class Kbasebean {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private String consti;
	
	
	public Kbasebean() throws Exception {}
	
	public void setConsti(String x) {
		this.consti = x;
		
	}
	
	
	
	public List<Map<String, Object>> kbaseQuery() throws Exception
	{  
		InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
        
        QueryRunner run = new QueryRunner(ds);
        
        MapListHandler  h = new MapListHandler();
        String query = "select * from KBASE where CODE = '"+this.consti+"'";
        
        List<Map<String,Object>> rs = run.query(query, h);
        
        return rs;
	}
	
	public String kbaseConsti() throws Exception
	{
		InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
        conn = ds.getConnection();
        stmt = conn.createStatement();
        String query = "select CONSTI_NAME from CONSTITUENCY where CODE = '"+this.consti+"'";
        rs = stmt.executeQuery(query);
    
        rs.next();
        String consti = rs.getString("CONSTI_NAME");
        
        rs.close();
        rs = null;
        
        stmt.close();
        stmt.close();
        
        conn.close();
        conn=null;
        
        return consti;
	}
}
