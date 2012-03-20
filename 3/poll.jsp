<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerW.jsp" %>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Poll</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
          <% int i=1; 
            ResultSet rs= (ResultSet)request.getAttribute("poll");
            	while(rs.next()){%>
        <h1>Q :<a href="/CMS/Poll_view?consti=<%=consti%>&ward=<%=ward%>&pollid=<%=rs.getLong("POLLNO")%>"><%=rs.getString("QUESTION") %></a></h1>
        <br />
        <%i++;} %>
        <%if(i<11){ %>
          <h1><a href="/CMS/<%=session.getAttribute("Access").toString().charAt(2)%>/create_poll.jsp?consti=<%=consti%>&ward=<%=ward%>">Create your own poll</a>
          <%} %>	&nbsp;&nbsp;&nbsp;
           <a href="/CMS/Poll_archives?consti=<%=consti%>&ward=<%=ward%>">Archive</a></h1>
        </div>
        <div class="column1-unit">
          <p>&nbsp;</p>
        </div>                                    
      </div>
       </div>         

       
       <%@ include file="/Templates/footer.jsp" %>        
       