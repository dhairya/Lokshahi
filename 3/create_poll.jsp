<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerW.jsp" %>

 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Create Poll</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        <form action="/CMS/Poll_create?consti=<%=consti%>&ward=<%=ward%>" method="post">
        <h1>Question</h1>
        <p>&nbsp;&nbsp;<input type="text" name="question" size="50" />
        </p>
        <p>Option 1  <input type="text" name="opt1"/></p>
        <p>Option 2  <input type="text" name="opt2"/></p>
        <p>Option 3  <input type="text" name="opt3"/></p>
        <p>Option 4  <input type="text" name="opt4"/></p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;<input type="submit" value="Create Poll" /></p>
          </div>
        <div class="column1-unit"></div>
        <div class="column1-unit"></div>                                    
      </div>
       </div>         

              <%@ include file="/Templates/footer.jsp" %>        
       