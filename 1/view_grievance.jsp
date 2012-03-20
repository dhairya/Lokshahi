<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerW.jsp" %>

<script type="text/javascript">
   function buttonid(name) {
 	document.getElementById("Command").value=name;
 	document.update.submit();
 	return false;
 }
</script>
 
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">View Problems</h1>
        <!-- Content unit - One column -->
        <div class="column1-unit">
       <%ResultSet rs= (ResultSet)request.getAttribute("prob");
    	rs.next();%>
        <h1><%=rs.getString("TITLE")%></h1>                
          <%long time = rs.getTimestamp("DATE").getTime();%>
        <%java.text.DateFormat df = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.LONG,java.text.DateFormat.MEDIUM);  %>                
        <h3>Posted on  <%=df.format(new java.util.Date(time))%>
          by <%=rs.getString("NICKNAME")%></h3>
          <%  String statusl= null; %>
          <%int status = rs.getInt("STATUS"); 
          
            if(status == 0)
               statusl = "Unread by civic official";
            if(status == 1)
            	statusl = "Read by civic official";
            if(status == 2)
            	statusl = "Problem solved by civic official";
            if(status == 10)
            	statusl = "The claim that this problem has been solved, is challenged once by citizens";
            if(status == 20)
            	statusl = "The claim that this problem has been solved, is challenged twice by citizens";
            if(status == 30)
            	statusl = "The claim that this problem has been solved, is challenged thrice by citizens";
            if(status == 40)
            	statusl = "The claim that this problem has been solved, is challenged four times by citizens";%>
          <p>Status:<%=statusl %></p>
          <input type="hidden" name="Status" value="<%=status%>"/>
          <p>Category:<%=rs.getString("DEPARTMENT")%></p>
           <p>No. of people joined :<%=rs.getInt("NUM_PPL_JOINED")%></p>
          <p>Admin Rating :<%=rs.getFloat("ADMIN_RATING")%></p>
          <p>Public Rating :<%=rs.getFloat("PUBLIC_RATING")%></p>
          <p>Intelli Rating :<%=rs.getFloat("INTELLI_RATING")%></p>
          <p>Description :<%=rs.getString("DESCRIPTION")%></p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
    
        </div>
        <hr class="clear-contentunit" />          

        <!-- Content unit - One column -->
        <div class="column1-unit">        
          <%ResultSet rs1= (ResultSet)request.getAttribute("comments");
    	while(rs1.next()){%>
          <h1></h1>                            
          <%long time1 = rs1.getTimestamp("DATE").getTime();%>
        <%java.text.DateFormat df1 = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.LONG,java.text.DateFormat.MEDIUM);  %>                
        <h3>Posted on  <%=df1.format(new java.util.Date(time1))%>
          by <%=rs1.getString("NICKNAME")%></h3>
          <p><%=rs1.getString("COMMENTS")%></p>          
        <hr class="clear-contentunit" />      
        <%}%>    
          </div>
       </div>         
      </div>
      
 <%@ include file="/Templates/footer.jsp" %>          
