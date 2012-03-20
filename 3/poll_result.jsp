 <%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerW.jsp" %>
 
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Poll - Result</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
          <div class="column1-unit">
          <% ResultSet rs= (ResultSet)request.getAttribute("poll");
            	while(rs.next()){%>
            	<% long total = rs.getLong("OPT1_CNT") + rs.getLong("OPT2_CNT") + rs.getLong("OPT3_CNT") + rs.getLong("OPT4_CNT"); %>
            <table width="200%" border="1">
              <caption>
              <h2> Q:<%=rs.getString("QUESTION") %> </h2>
              </caption>
              <tr>
                <th scope="col">Options</th>
                <th scope="col">Votes</th>
              </tr>
              <%for(int i=1; i<=4 ; i++){ %>
              <tr>
                <td><%=rs.getString("OPT"+i) %></td>
                <td><%=(((rs.getLong("OPT"+i+"_CNT"))/(float)total)*100 )%>%</td>
              </tr>
              <%} %>
            </table>
            <%long time = rs.getTimestamp("CREATE_INST").getTime();%>
            <%java.text.DateFormat df = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.LONG,java.text.DateFormat.MEDIUM);  %>
            Submitted by: <%=rs.getString("NICKNAME") %><br />
            Submitted on: <%=df.format(new java.util.Date(time))%>
            <%} %>
            <br />
          </div>
          <p></p>
          <h1>&nbsp;</h1>
        <div class="column1-unit">
          </div>
        </div>
        <div class="column1-unit"></div>                                    
      </div>
       </div>         

      <%@ include file="/Templates/footer.jsp" %>        
      