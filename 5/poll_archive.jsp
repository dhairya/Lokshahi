<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerW.jsp" %>

 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Poll - Archive</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
          <% int i=0; 
            ResultSet rs= (ResultSet)request.getAttribute("poll");
            	while(i<20 && rs.next()){%>
        <h1>Q :<a href="/CMS/Poll_res_view?consti=<%=consti%>&ward=<%=ward%>&pollid=<%=rs.getLong("POLLNO")%>"><%=rs.getString("QUESTION") %></a></h1>
        <br />
        <%i++;} %>
        <br /><br />
        <%if(request.getAttribute("row").equals("1")){ %>
          Previous
          <%}else{ %>
		  <a href = "/CMS/Poll_prev?consti=<%=consti%>&ward=<%=ward%>&row=<%=request.getAttribute("row")%>">Previous</a>
		  <%} %>
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  <%if(i==20 && rs.next()){ %>
		  <a href = "/CMS/Poll_nxt?consti=<%=consti%>&ward=<%=ward%>&row=<%=request.getAttribute("row")%>">Next</a>
          <%}else{ %>
          Next
          <%} %>
          </div>
          <p></p>
          <h1>&nbsp;</h1>
        <div class="column1-unit">
          </div>
        <div class="column1-unit"></div>                                    
      </div>
       </div>         

       <%@ include file="/Templates/footer.jsp" %> 