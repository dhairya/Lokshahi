  <%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerC.jsp" %>
 
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
		 <div id="column1-unit">
			<p>
		   <h1>
		   <%
            ResultSet rs= (ResultSet)request.getAttribute("funds");
           	while(rs.next()){%>
		  Fund Details</h1><p><label><%=rs.getString("TITLE") %>&nbsp;&nbsp;</label>
			</p>
		   <p><label>Total Fund Required: <%=rs.getLong("COST") %> &nbsp;</label>
		   </p>
			<p>Description:</p>
			<p>&nbsp;</p>
			<%=rs.getString("DESCRIPTION") %>
			<p>&nbsp;</p>
			<%long time = rs.getTimestamp("DATE").getTime();%>
            <%java.text.DateFormat df = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.LONG,java.text.DateFormat.MEDIUM);  %>
             Posted on: <%=df.format(new java.util.Date(time))%>
           <p>&nbsp;&nbsp;&nbsp;</p>
		   <br />
			<%} %>
		</div>
		</div>
       </div>         

              <%@ include file="/Templates/footer.jsp" %>