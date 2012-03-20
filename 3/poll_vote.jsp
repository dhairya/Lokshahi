<%@page import="java.util.Date"%>
<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerW.jsp" %>


<script type="text/javascript">

 function buttonid(name) {
 	document.getElementById("Command").value=name;
 	document.vote_poll.submit();
 	return false;
 	}
</script>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Poll - Vote</h1>

        <!-- Content unit - One column -->
        <form name="vote_poll" method="post" action="/CMS/Poll_vote?consti=<%=consti%>&ward=<%=ward%>">
          <% ResultSet rs= (ResultSet)request.getAttribute("poll");
            	while(rs.next()){%>
         <input type="hidden" name="pollid" id="pollid" value="<%=rs.getString("POLLNO") %>"></input>
         <input type="hidden" name="Command" id="Command" /> 
        <div class="column1-unit">
        <h1>Q :<%=rs.getString("QUESTION") %></h1>
        <div class="column1-unit">
          <p>&nbsp;</p>
            <p></p>
            <table width="200">
              <tr>
                <td><label>
                  <input type="radio" name="option" value="1" id="option" />
                  <%=rs.getString("OPT1") %></label></td>
              </tr>
              <tr>
                <td><label>
                  <input type="radio" name="option" value="2" id="option" />
                  <%=rs.getString("OPT2") %></label></td>
              </tr>
              <tr>
                <td><label>
                  <input type="radio" name="option" value="3" id="option" />
                  <%=rs.getString("OPT3") %></label></td>
              </tr>
              <tr>
                <td><label>
                  <input type="radio" name="option" value="4" id="option" />
                  <%=rs.getString("OPT4") %></label></td>
              </tr>
            </table>
            <br />
            &nbsp;&nbsp;&nbsp;
            <input type="button" value="Vote" id="Vote" onclick="buttonid(this.id)" />
            <br />
            &nbsp;&nbsp;&nbsp;
            
            <%long time = rs.getTimestamp("CREATE_INST").getTime();%>
            <%time = time + 86400000; %>
            <%java.util.Date dt = new Date(time); %>
            <%java.text.DateFormat df = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.LONG,java.text.DateFormat.MEDIUM);  %>
            Voting open till : <%=df.format(dt) %>
            
            <br />
            Submitted By: <%=rs.getString("NICKNAME") %>
            <p>&nbsp;</p>
            <p></p>
          </div>
        </div>
        <%} %>
        </form>
        <div class="column1-unit"></div>                                    
      </div>
       </div>  

       
       <%@ include file="/Templates/footer.jsp" %>        
       