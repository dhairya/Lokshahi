<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerW.jsp" %>

<script type="text/javascript" src="/CMS/css/yetii.js"></script>
<script type="text/javascript">
 function search(name)
 {
 	document.getElementById("searchtype").value=name;
 	document.probsearch.submit();
 	return false;
 }
</script>

    <!-- B.2 MAIN CONTENT -->
    <div class="main-content">
      <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Grievance Zone</h1>
        <!-- Content unit - One column -->
        <div class="column1-unit">
          <p>&nbsp;</p>
          <h2 align="center"> Latest Posted Grievances</h2>
          <table width="100%" border="1">
            <tr>
              <th width="40" scope="col"> Temp Ref ID </th>
              <th width="140" scope="col">Grievance</th>
            </tr>
            <% int i=0; 
            ResultSet rs= (ResultSet)request.getAttribute("prob");
            String status = null;
         	while(i<20 && rs.next()){%>
            <tr>
              <td><%=rs.getLong("REF_ID")%></td>
              <td><a href="/CMS/Appr_Grievance_view?consti=<%=consti%>&ward=<%=ward%>&refid=<%=rs.getLong("REF_ID")%>"><%=rs.getString("TITLE")%></a></td>
            </tr>
            <%i++;} %>
          </table>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp; </p>
          <%if(request.getAttribute("row").equals("1")){ %>
          <p>&nbsp;</p>
          <%}else{ %>
          <h2><a href = "/CMS/Appr_Grievance_prev?consti=<%=consti%>&ward=<%=ward%>&row=<%=request.getAttribute("row")%>">Previous</a></h2>
          <%} %>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <%if(i==20 && rs.next()){ %>
          <h2><a href = "/CMS/Appr_Grievance_nxt?consti=<%=consti%>&ward=<%=ward%>&row=<%=request.getAttribute("row")%>">Next</a></h2>
          <%}else{ %>
          <p>&nbsp;</p>
          <%} %>
        </div>
      </div>
    </div>

      <%@ include file="/Templates/footer.jsp" %>