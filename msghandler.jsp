<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerG.jsp" %>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle"></h1>
		<div class="column1-unit">
        <% String msg = (String)request.getAttribute("msg");%>
         
        <%if(msg != null) { %>
            <%if(msg.equals("wrong code")){%>
             <p>The <u><b>code</b></u> you mentioned in the voterid is incorrect. To re-try <a href="/CMS/index.jsp"> click here</a></p>
             <%}else if(msg.equals("wrong password or id")){ %>
              <p>The <u>voterid</u> or <u>password</u> you mentioned is incorrect or does not exist. </p>
           <%}else if(msg.equals("wrong")){ %>
              <p>The <u>voterid</u> or <u>password</u> you mentioned is incorrect or does not exist. </p><%} %>
        	<%if(msg.equals("admin")){ %><h1 class="pagetitle">Enter Verification Code</h1>
        	<form method="post" action="/CMS/Verify">
        	<label><p>Enter verification code:
        	<input type="text" name="vcode" />
        	</p></label>
        	<input type="submit" value="Submit" />
        	</form>
        	<%} %>
        	<%if(msg.equals("verified")){ %><p>Dear Admin, your password has been reset to ADMIN.</p><%} %>
        	<%if(msg.equals("other")) { %><p> Dear User, your password details have been sent to the cell number you provided during registration</p><%} %>
        	<%if(msg.equals("not exists")){ %><p>The username you provided does not exist in the system. Please enter the correct username to recover your password</p><%} %>
        	<%if(msg.equals("no ward")){ %><p>No wards exist in the constituency you have selected. <a href="./ADMIN/create_ward.jsp">Click here </a>to create wards.</p><%} %>
        	<%if(msg.equals("registered")) {%><p>Your request has been successfully send to the Administrator. Your application will be processed and accordingly a mail will be sent to the email ID and an sms to the cell number you submitted during registration.<%} %>
        <%} %>
        
        </div>                                    
      </div>
       </div>         

 <%@ include file="/Templates/footerG.jsp" %> 
