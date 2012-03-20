package beans;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

public class Load_Constibean {
	private Connection conn;
	private Statement stmt;
	private Statement stmt1[];
	private ResultSet rs;
	private ResultSet rst[];
	private String wards[][];
	public Load_Constibean() throws Exception {}
	
	
	public List<Map<String, Object>> loadConsti() throws Exception
	{
		InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
        
        QueryRunner run = new QueryRunner(ds);
        MapListHandler  h = new MapListHandler();        
        String query = "SELECT * FROM CONSTITUENCY";
        
        List<Map<String,Object>> rs = run.query(query, h);
       
		return rs;
	}
	
	public int count() throws Exception
	{
		InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
        conn = ds.getConnection();
        stmt = conn.createStatement();
        
        int i=0;
        String query = "SELECT * FROM CONSTITUENCY";
		rs = stmt.executeQuery(query);
		
		while(rs.next())
        {
        	i++;
        }
		
		rs.close();
		rs=null;
		stmt.close();
		stmt=null;
		conn.close();
		conn=null;
		
		return i;
		
	}
	
	public void loadLength(int[] len, int i) throws Exception
	{
		
		InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
        conn = ds.getConnection();
        stmt = conn.createStatement();
        //stmt1 = conn.createStatement();
        int j,k=0;
       
        rst=new ResultSet[i]; 
        stmt1=new Statement[i];
        //wards=new String[i][];
        for(j=0;j<stmt1.length;j++)
        {
        	stmt1[j]=conn.createStatement();
        }
        
        j=0;
        
        String query = "SELECT * FROM CONSTITUENCY";
		rs = stmt.executeQuery(query);	
        
		while(rs.next())
        {
        	
        	String cons=rs.getString("CODE");
        	String query1 = "SELECT NUMBER FROM "+cons+"_WARDS";
        	rst[j]=stmt1[j].executeQuery(query1);
        	while(rst[j].next())
        	{
        		k++;
        	}
			len[j]=k;
        	k=0;
        	j++;
        	
        }
        
		for(int z=0;z<i;z++)
		{
			rst[z].close();
			rst[z] = null;
			stmt1[z].close();
			stmt1[z] = null;
		}
		
		rs.close();
		rs=null;
		stmt.close();
		stmt=null;
		conn.close();
		conn=null;
        
        return;
	}
	
	public void loadWards(String wards[], int i) throws Exception
	{
		
		InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
        conn = ds.getConnection();
        stmt = conn.createStatement();
        //stmt1 = conn.createStatement();
        int j,k=0;
       
        rst=new ResultSet[i]; 
        stmt1=new Statement[i];
        //wards=new String[i][];
        for(j=0;j<stmt1.length;j++)
        {
        	stmt1[j]=conn.createStatement();
        }
        
        j=0;
        String query = "SELECT * FROM CONSTITUENCY";
		rs = stmt.executeQuery(query);	
        while(rs.next())
        {
        	
        	String cons=rs.getString("CODE");
        	String query1 = "SELECT NUMBER FROM "+cons+"_WARDS";
        	rst[j]=stmt1[j].executeQuery(query1);
        	
        	while(rst[j].next()){
        		wards[k]=rst[j].getString("NUMBER");
        		k++;
        	}
        	j++;
        	
        }
        
        for(int z=0;z<i;z++)
		{
			rst[z].close();
			rst[z] = null;
			stmt1[z].close();
			stmt1[z] = null;
		}
		
		rs.close();
		rs=null;
		stmt.close();
		stmt=null;
		conn.close();
		conn=null;
		
        return;
	}
	
}
