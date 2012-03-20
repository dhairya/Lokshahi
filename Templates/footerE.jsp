      <!-- B.3 SUBCONTENT -->
      <div class="main-subcontent">

        <!-- Subcontent unit -->
        <div class="subcontent-unit-border-orange">
          <div class="round-border-topleft"></div><div class="round-border-topright"></div>
          <%if(session.getAttribute("Empid")==null){ %>
          <h1 class="orange">Login</h1>
          <div class="loginform">
            <form method="post" action="/CMS/Verify_emplogin"> 
              <p><input type="hidden" name="rememberme" value="0" /></p>
              <fieldset>
                <p><label for="username" class="top">User ID:</label><br />
                  <input type="text" <input type="text" name="empid" /> tabindex="1" class="field" value="" /></p>
    	        <p><label for="password" class="top">Password:</label><br />
                  <input type="password" name="pswd" id="password" tabindex="2" class="field" value="" /></p>
    	        <p><input type="submit" name="cmdweblogin" class="button" value="LOGIN"  /></p>
	            <p><a href="/CMS/forgotemppwd.jsp" id="forgotpsswd">Password forgotten?</a></p>
	          </fieldset>
            </form>
            <p><a href="/CMS/empregister.jsp">New Companies/Employers Register Here</a></p>
          </div>
          <%}else{ %>
          <h1 class="orange">Welcome</h1>
          Welcome <%=session.getAttribute("Empid") %><br /><br />
          
          Your home constituency is <%=session.getAttribute("EmpConsti") %>
          
          <br /><br />
          <form method="post" action="/CMS/Logout">
          <input type="submit" value="Logout"></input>
          </form>
          <%} %>
        </div>     
        <!-- Subcontent unit -->
        
         <!-- Subcontent unit -->
        
        </div>
        
      </div>
      
    <!-- C. FOOTER AREA -->      

    <script type="text/javascript">var menu=new menu.dd("menu");menu.init("menu","menuhover");</script><div class="footer">
      <p>This product is owned and copyrighted by <a href="http://ppl2point0.sourceforge.net">ppl2.0 Technologies</a> | All Rights Reserved</p>
      <p>Licensed under GNU GPL v3 License and the Creative Commons License v2.5 under the strict attributes of by-nc-nd. Read the license
      <a href="/CMS/license.txt">here</a></p>
    </div>      
  </div> 

</body>
</html>
