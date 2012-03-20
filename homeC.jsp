 <%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerC.jsp" %>

      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Welcome !</h1>
        <%String type = (String)session.getAttribute("Type"); %>
        
        <!-- Content unit - One column -->
        <div class="column1-unit">
       <%if(type==null) {%>
       <p>Welcome Guest!</p>
       <p>You are currently visiting constituency <%=consti %>. To know more about <%=consti %> <a href="/CMS/kbase.jsp?consti=<%=consti %>">click here</a>.</p>
       <%}else{ %>
       <%if(type.equals("ADMIN")){ %>
       <p>Welcome Admin!</p>
       <p>You are currently visiting constituency <%=consti %>. To know more about <%=consti %> <a href="/CMS/kbase.jsp?consti=<%=consti %>">click here</a>.</p>
       <%}else if(session.getAttribute("Consti").equals(consti)){ %>
       <p>Welcome Home!</p>
       <p>To know more about your home constituency <%=consti %> <a href="/CMS/kbase.jsp?consti=<%=consti %>">click here</a>.</p>
       <%}else {%>
       <p>Welcome Guest!</p>
       <p>You are currently visiting constituency <%=consti %>. To know more about <%=consti %> <a href="/CMS/kbase.jsp?consti=<%=consti %>">click here</a>.</p>
       <%}} %>
        </div>
        <hr class="clear-contentunit" />          

        <!-- Content unit - One column -->
        <div class="column1-unit">        
          
        </div>          
        </div>
       </div>         

       <%@ include file="/Templates/footer_ph.jsp" %>