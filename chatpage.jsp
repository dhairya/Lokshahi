<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="/CMS/prototype.js"></script>
<title>Chat!!!</title>
</head>
<body>
<%String consti = (String)request.getParameter("consti"); %>
<%String username = (String)request.getParameter("username");%>
<%session.setAttribute("chatname",username); %>

<div id="chat" style="height:400px;overflow:auto;">
</div>
<script>
function addmessage()
{
  new Ajax.Updater( 'chat', 'messageadd.jsp',
  {
     method: 'post',
     parameters: $('chatmessage').serialize(),
     onSuccess: function() {
       $('messagetext').value = '';
     }
  } );
}
</script>
<form id="chatmessage">
<textarea name="message" id="messagetext">
</textarea>
</form>

<button onclick="addmessage()">Send</button>

<script>
function getMessages()
{
  new Ajax.Updater( 'chat', 'message.jsp', {
    onSuccess: function() { window.setTimeout( getMessages, 1000 ); }
  } );
}
getMessages();
</script>
</body>
</html>