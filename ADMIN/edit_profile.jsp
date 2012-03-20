<%@ include file="/Templates/initial_admin.jsp" %>
<%@ include file="/Templates/headerA.jsp" %> 


      <!-- B.2 MAIN CONTENT -->
<div class="main-content">
<div id="translation"></div>
<div id="article"><!-- Pagetitle -->
<h1 class="pagetitle">Edit Profile</h1>
<!-- Content unit - One column -->
<div class="column1-unit">
<%ResultSet rs = (ResultSet)request.getAttribute("values");
  rs.next();
  String pwd = rs.getString("PASSWORD");%>
  <form action="/CMS/Editpassword" method="post">
  <p>Enter new password:&nbsp;<input type="password" name="cpass" /></p><br/><br/>
  <input type="submit" value="Change Password" />
  </form>
</div>

</div>
</div>



 <%@ include file="/Templates/footerA.jsp" %>
	