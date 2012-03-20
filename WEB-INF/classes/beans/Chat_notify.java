package beans;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.commons.dbutils.*;
import org.apache.commons.dbutils.handlers.*;

public class Chat_notify {
	private String code;
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	
	public Chat_notify() throws Exception {}
	
	public void setCode(String x)throws Exception
	{
		this.code = x;
	}
	
	public List<Map<String, Object>> loadChat() throws Exception
	{
		InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
        
        QueryRunner run = new QueryRunner(ds);
        
        MapListHandler  h = new MapListHandler();
        
        
        String query = "SELECT LEADER_DATE, LEADER_TIME"+
        " FROM "+this.code+"_CHAT"+
        " WHERE USERNAME = 'LEADER'";
        
        List<Map<String,Object>> rs = run.query(query, h);
        
        
        
		return rs;
        	
	}
	
	
}
