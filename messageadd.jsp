<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@page import="java.sql.*,javax.naming.InitialContext,javax.sql.DataSource" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
Connection con;
Statement stmt;
Statement stmt1;
ResultSet rs;
java.sql.Timestamp stamp = new java.sql.Timestamp(new java.util.Date().getTime());
long tym = stamp.getTime();
java.sql.Timestamp stampset = new java.sql.Timestamp(new java.util.Date().getTime());
String code = (String)session.getAttribute("Consti");
String chatname = (String)session.getAttribute("chatname");
String message = (String)request.getParameter("message");
java.text.DateFormat df = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.LONG,java.text.DateFormat.MEDIUM);  
long time;
try{
	InitialContext ctx = new InitialContext();
	DataSource ds = (DataSource) ctx
			.lookup("java:comp/env/jdbc/MyDataSource");
	 con = ds.getConnection();
	stmt = con.createStatement();
	stmt1 = con.createStatement();
	String query = "insert into DB2ADMIN."+code+"_CHAT (USERNAME,MESSAGE,DATE) "+
	               " values ('"+chatname+"','"+message+"','"+stampset+"')";
	stmt.executeUpdate(query);%>
	<table>
	<%String query1 = "Select MESSAGE_ID, USERNAME, MESSAGE, DATE from DB2ADMIN."+code+"_CHAT WHERE USERNAME"+
	                  "<> 'LEADER'";
	rs = stmt1.executeQuery(query1);
	while(rs.next())
	{
	time = rs.getTimestamp("DATE").getTime();
	%>
	<tr>
	<%if(tym - time<(12*60*60*1000)){ %>
	<td><%=rs.getString("USERNAME") %>:&nbsp;</td>
	<td><%=rs.getString("MESSAGE") %>&nbsp;&nbsp;</td>
	<td><%=df.format(new java.util.Date(time))%>&nbsp;</td>
	</tr>
	<%}}%>
	</table>
<%
rs.close();
rs=null;
stmt.close();
stmt=null;
stmt1.close();
stmt1=null;
con.close();
con=null;
}
catch(Exception e)
{
	
}
%>
</body>
</html>