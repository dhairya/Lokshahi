<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerW.jsp" %>


  <!-- B.2 MAIN CONTENT -->
  <div class="main-content">
    <div id="translation"></div>
    <div id="article">
      <!-- Pagetitle -->
      <h1 class="pagetitle">Grievance Zone</h1>
      <!-- Content unit - One column -->
      <div class="column1-unit">
        <%ResultSet rs= (ResultSet)request.getAttribute("prob");
    	rs.next();%>
        <form action="/CMS/Grievance_solve2?consti=<%=consti%>&ward=<%=ward%>" method="post">
          <input type="hidden" name="refid" id="refid" value="<%=rs.getString("REF_ID")%>"/>
          <h1><%=rs.getString("TITLE")%></h1>
          <%long time = rs.getTimestamp("DATE").getTime();%>
          <%java.text.DateFormat df = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.LONG,java.text.DateFormat.MEDIUM);  %>
          <h3>Posted on <%=df.format(new java.util.Date(time))%> by <%=rs.getString("NICKNAME")%></h3>
          <% int status = rs.getInt("STATUS"); %>
          <p>Status:<%=status%></p>
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
          <p> Reply :</p>
          <p>&nbsp;
            <textarea rows="5" cols="65" name="Reply" id = "Reply"></textarea>
          </p>
          <p>
            <input type="submit" value="Solved" />
          </p>
        </form>
      </div>
    </div>
  </div>

  <%@ include file="/Templates/footer.jsp" %>        
  