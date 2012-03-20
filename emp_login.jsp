 <%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerG.jsp" %>

      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Login for Employers</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        
               
        <form action="/CMS/Verify_emplogin" method="post">
        	<p>User ID:  <br />
        	<input type="text" name="empid" /></p>
        	<p> Password: <br />
			<input type="password" name="pswd" id="password" tabindex="2" class="field" value="" /></p>
			<p><input type="submit" name="emplogin" class="button" value="LOGIN"></p>
		</form>
		
        <p><a href="/CMS/forgotemppwd.jsp" id="forgotpsswd">Password forgotten?</a></p>
       	<p><a href="/CMS/empregister.jsp">New Companies/Employers Register Here</a></p>
                  
        </div>          
        <hr class="clear-contentunit" />                             
                      
 
        <!-- Content unit - One column -->
        <div class="column1-unit">
          
        </div>                                    
      </div>
       </div>         

       <%@ include file="/Templates/footeronly.jsp" %>