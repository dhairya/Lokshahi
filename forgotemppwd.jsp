 <%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerG.jsp" %>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Forgot password</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
                       
        <form action="/CMS/Forgot_emppswd" method="post">
        	<p>User ID:  <br />
        	<input type="text" name="empid" /></p>
        	<p><input type="submit" name="emppswd" class="button" value="SUBMIT"></p>
		</form>
		 <p><a href="/CMS/empregister.jsp">New Companies/Employers Register Here</a></p>
         </div>          
        <hr class="clear-contentunit" />                             
                      
        <!-- Content unit - One column -->
        <div class="column1-unit">
          
        </div>                                    
      </div>
       </div>         
       
       
       <%@ include file="/Templates/footeronly.jsp" %>