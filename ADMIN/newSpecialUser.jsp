<%@ include file="/Templates/initial_admin.jsp" %>
<%@ include file="/Templates/headerA.jsp" %>

<script type="text/javascript" src="/CMS/ajax.js"></script>
  
<script type="text/javascript">

function belongsTo()
{   
    document.add.submit();
 	return false;
}



</script>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Add User</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        <%String utype = (String)request.getAttribute("usertype"); %>
        <%ResultSet rst = (ResultSet)request.getAttribute("cons"); %>
       
        
        <form  name="add" id="add" action="/CMS/Addwards" method="post" >
        <input type="hidden" name="utype" id="utype" value="<%=utype %>" />
        <p>
	     User Type:</p>
		 <p> <label>
		 <%=utype %>
		 </label></p>
		 
		 <p>
		 The new user belongs to: 
	     </p>
		 <p> <label>
		 <select id="belong" name="belong" >
		<option value="c">Constituency</option>
		<option value="w">Ward</option>
		</select>
		 </label></p>
		 
		 <p>
		 Select the Constituency: 
	     </p>
		 <p> <label>
		 <select id="constituency" name="constituency" onchange="belongsTo()">
		<option>&nbsp;</option>
		<%while(rst.next()){ %>
			<option value="<%=rst.getString("CODE")%>"><%=rst.getString("CONSTI_NAME") %></option>
			<%} %>
		</select>
		 </label></p>
		 
		 
        </form>
        </div>
        <div class="column1-unit"></div>                                    
      </div>
      </div>         

      
 <%@ include file="/Templates/footerA.jsp" %>