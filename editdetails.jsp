 <%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerG.jsp" %>

      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Edit Details</h1>
       
        <%String username = (String)session.getAttribute("User"); %>
        <%String type = (String)session.getAttribute("Type"); %>
        <!-- Content unit - One column -->
        <div class="column1-unit">
        <%ResultSet rsd = (ResultSet) request.getAttribute("info"); %>
        <%String pwd = (String) request.getAttribute("pwd"); %>
        <%rsd.next(); %>
        <form method="post" action="/CMS/Editdetails" >
        <%if(username.equalsIgnoreCase("ADMIN")){ %>
        <p>Change Password:
        <input type="password" name="pwd" id="pwd" value="<%=rsd.getString("PASSWORD") %>" />
        </p>
       <%} else if(type.equals("CITIZEN")){ %>
       <p>Change Password:
        <input type="password" name="pwd" id="pwd" value="<%=pwd %>" />
        </p>
        <p>Change EmailID:
        <input type="text" name="email" id="email" value="<%=rsd.getString("EMAIL") %>"/></p>
        <p>Change Contact Number:
        <input type="text" name="phone" id="phone" value="<%=rsd.getString("PHONE") %>"/></p>
        <p>Change cell Number:
        <input type="text" name="cell" id="cell" value="<%=rsd.getString("CELL") %>"/></p>
        <%} else {%>
        <p>Change Password:
        <input type="password" name="pwd" id="pwd" value="<%=rsd.getString("PASSWORD") %>" />
        </p>
        <p>Change EmailID:
        <input type="text" name="email" id="email" value="<%=rsd.getString("EMAILID") %>"/></p>
        <p>Change Contact Number:
        <input type="text" name="cell" id="cell" value="<%=rsd.getString("PHONE") %>"/></p>
        <%} %>
        <input type="submit" value="Submit" />
        </form>
        </div>
        <hr class="clear-contentunit" />          

        <!-- Content unit - One column -->
        <div class="column1-unit">        
          
        </div>          
        </div>
       </div>         

       <%@ include file="/Templates/footerA.jsp" %>