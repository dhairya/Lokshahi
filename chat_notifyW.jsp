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
<h1 class="pagetitle">Chat Notify</h1>
<!-- Content unit - One column -->
<div class="column1-unit">
<%String set = "set"; %>
<%if(set.equals(request.getAttribute("set")) && !d.equals("NA")) {%>
<p>The chat is scheduled for <b><%=d%></b> at <b><%=t%></b>.</p>
<br/><br/>
<%}else{ %>
<p>&nbsp;</p><%} %>
<form method="post" action="/CMS/Chat_notify?consti=<%=consti %>&ward=<%=ward%>">
Enter date:&nbsp;
<p><input type="text" name="chatd" />&nbsp;&nbsp;(dd/mm/yyyy)</p>
Enter Time:&nbsp;
<p><input type="text" name="chatt" />&nbsp;&nbsp;(12-hr format--am or pm)</p>
<p><input type="submit" value="Notify" /></p>
</form>
</div>

</div>
</div>

<%@ include file="/Templates/footer.jsp" %> 