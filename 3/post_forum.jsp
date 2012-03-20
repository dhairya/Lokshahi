<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerW.jsp" %>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
        <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">New Post</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit"><br />
       <form action="/CMS/Forum_add?consti=<%=consti%>&ward=<%=ward%>" method="post">
      Title: <br/>
      <input type="text" name="title" /><br/><br/><br/>
      Description: <br/>
      <textarea rows="5" cols="50" name="postdesc"></textarea>
          <br /><br />
          <input type="submit" name="Submit" value="Submit Post"/></form></div>
        <hr class="clear-contentunit" />          

        <!-- Content unit - One column -->
        </div>
       </div>         

<%@ include file="/Templates/footer.jsp" %>        

       