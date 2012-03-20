 <%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerC.jsp" %>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
		 <div id="column1-unit">
		 <form method="post" action="/CMS/Funds_add?consti=<%=consti%>">
			<p><h1>
			Fund Manager:</h1>
			<p><label>Title&nbsp;&nbsp;<input type="text" name="title" id="title" /></label></p>
		   <p><label>Total Fund Required &nbsp;<input type="text" name="cost" id="cost"/></label></p>
			<p>Description:</p>
			<p>&nbsp;<textarea rows="5" cols="50" name="description" id="description"></textarea></p>
            <p>&nbsp;&nbsp;&nbsp;<input type="submit" value="Submit" /></p>
		   <br />
			

		</div>
		</div>
       </div>         

       <%@ include file="/Templates/footer.jsp" %>