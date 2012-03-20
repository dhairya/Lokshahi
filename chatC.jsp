<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerW.jsp" %>


<script type="text/javascript">
function ValidateForm(){
var pass=document.getElementById("pass").value;
var cpass=document.getElementById("cpass").value;
	
	if(pass != cpass)
	{
		alert('Passwords dont match.');
		return false;
	}
		return true
 }

</script>


<!-- B.2 MAIN CONTENT -->
<div class="main-content">
<div id="translation"></div>
<div id="article"><!-- Pagetitle -->
<h1 class="pagetitle">Chat</h1>
<!-- Content unit - One column -->
<div class="column1-unit">
<%if(session.getAttribute("Type")==null || !consti.equals(session.getAttribute("Consti")) || session.getAttribute("Access").toString().charAt(4)=='1'){ %>
<p> You are currently not logged in to chat or do not belong to this constituency.</p>
<%}else{ %>
<form action="/CMS/chatpage.jsp" method="post">
Nickname: <input type="text" name="username"><br/>
<input type="hidden" value="<%=consti %>" name="consti" />
<p>&nbsp;&nbsp;<input type="submit" value="Login"></p>
</form><%} %>

</div>

</div>
</div>

<%@ include file="/Templates/footer.jsp" %>        