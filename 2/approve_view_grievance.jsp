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
  <div class="main-content">
    <div id="translation"></div>
    <div id="article">
      <!-- Pagetitle -->
      <h1 class="pagetitle">View Problems</h1>
      <form action="/CMS/Appr_Grievance?consti=<%=consti%>&ward=<%=ward%>" method="post" name="update">
        <!-- Content unit - One column -->
        <div class="column1-unit">
          <%ResultSet rs= (ResultSet)request.getAttribute("prob");
    	rs.next();%>
          <input type="hidden" name="ProbID" id="ProbID" value="<%=rs.getString("REF_ID")%>"/>
          <h1><%=rs.getString("TITLE")%></h1>
          <%long time = rs.getTimestamp("DATE").getTime();%>
          <%java.text.DateFormat df = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.LONG,java.text.DateFormat.MEDIUM);  %>
          <h3>Posted on <%=df.format(new java.util.Date(time))%> by <%=rs.getString("NICKNAME")%></h3>
          <p>Category:<%=rs.getString("DEPARTMENT")%></p>
          <p>Description :<%=rs.getString("DESCRIPTION")%></p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          
          <input type="button" value="Approve" id="Approve" onclick="buttonid(this.id)"/>
          &nbsp;&nbsp;
          <input type="button" value="Discard" id="Discard" onclick="buttonid(this.id)"/>
          &nbsp;&nbsp;
          <input type="hidden" name="Command" id="Command" />
          
        </div>
        </form>
        <hr class="clear-contentunit" />
        <!-- Content unit - One column -->
    </div>
  </div>

  <%@ include file="/Templates/footer.jsp" %>