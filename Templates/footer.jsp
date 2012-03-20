      <!-- B.3 SUBCONTENT -->
      <div class="main-subcontent">

        <!-- Subcontent unit -->
        <div class="subcontent-unit-border-orange">
          <div class="round-border-topleft"></div><div class="round-border-topright"></div>
          <%if(session.getAttribute("Type")==null){ %>
          <h1 class="orange">Login</h1>
          <div class="loginform">
            <form method="post" action="/CMS/Verify_login"> 
              <p><input type="hidden" name="rememberme" value="0" /></p>
              <fieldset>
                <p><label for="username" class="top">User:</label><br />
                  <input type="text" name="username" id="username" tabindex="1" class="field" value="" /></p>
    	        <p><label for="password" class="top">Password:</label><br />
                  <input type="password" name="password" id="password" tabindex="2" class="field" value="" /></p>
    	        <p><input type="submit" name="cmdweblogin" class="button" value="LOGIN"  /></p>
	            <p><a href="/CMS/forgotpwd.jsp" id="forgotpsswd">Password forgotten?</a></p>
	          </fieldset>
            </form>
            <p><a href="/CMS/register.jsp">New Users Register Here</a></p>
          </div>
          <%}else{ %>
          <h1 class="orange">Welcome</h1>
          Welcome <%=session.getAttribute("Type") %><br /><br />
          <%if(!session.getAttribute("Type").equals("ADMIN")){  %>
          Your home constituency is <%=session.getAttribute("Consti") %>
          <%if(!(session.getAttribute("Wards")==null)){ %>
          <br /> Your home ward is <%=session.getAttribute("Wards") %>
          <%}} %><br /><br />
          <a href="/CMS/Editprofile">Edit your details</a><br /><br/>
          <form method="post" action="/CMS/Logout">
          <input type="submit" value="Logout"></input>
        </form>
          <%} %>
        </div>

       <!-- Subcontent unit -->
<%if(d.equals("NA")) {%>
<div class="subcontent-unit-border-orange">
<div class="round-border-topleft"></div>
<div class="round-border-topright"></div>
<h1 class="orange">Chat Notifications</h1>
<p>Next Chat on: yet to be scheduled</p>
<p>&nbsp;</p>
<%}else{ %>
<div class="subcontent-unit-border-orange">
<div class="round-border-topleft"></div>
<div class="round-border-topright"></div>
<h1 class="orange">Chat Notifications</h1>
<p>Next Chat on: <%=d%></p>
<p>At: <%=t%></p>
<%} %>
</div>

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