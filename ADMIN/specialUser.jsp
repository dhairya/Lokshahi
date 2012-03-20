<%@ include file="/Templates/initial_admin.jsp" %>
<%@ include file="/Templates/headerA.jsp" %>


<script type="text/javascript">

 function usertype(type) {
 	document.getElementById("utype").value=type.options[type.selectedIndex].value;
 	document.add.submit();
 	return false;
 }
 
</script>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Add Special User</h1>
		  <div class="column1-unit">
		  
		  <p>&nbsp;</p>
		  <%ResultSet rst = (ResultSet)request.getAttribute("template"); %>
		  <%String temp=""; %>
		  <%if(rst.next()){ %>
		 <p>
		Choose the type of user you want to add: 
		</p>
		<form method="post" action="/CMS/Specialuser2" name="add">
		 <p> <label>
		 <select id="user" name="user" onchange="usertype(this)">
		<option>&nbsp;</option>
			<%do { 
			temp = rst.getString("NAME");
			%>
			<option value="<%=temp%>"><%=temp %></option>
			<%}while(rst.next()); %>
		</select>
	    </label></p>
	    <label> <p>OR</p> </label>
	    <%} %>
	    <br />
	    <input type="hidden" name="utype" id="utype" />	
	    </form>
		 	
		<br />
		<p>
		
		</p>
		  <p><label>
		  <a href="/CMS/ADMIN/newTemplate.jsp">Add User Template</a>
		  </label></p>
		  <br />
		
		 
         </div>
		</div>
      </div>         

      
       <%@ include file="/Templates/footerA.jsp" %>