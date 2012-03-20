<%@ include file="/Templates/initial_admin.jsp" %>
<%@ include file="/Templates/headerA.jsp" %>

      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Add User</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        <%String utype = (String)request.getAttribute("usertype"); %>
        <%String belong = (String)request.getAttribute("belong"); %>
        <%String cons = (String)request.getAttribute("cons"); %>
        <%ResultSet rst = (ResultSet)request.getAttribute("wards"); %>
        <%ResultSet rst1 = (ResultSet)request.getAttribute("cats"); %>
       
        <%String temp =""; %>
        <form  name="add" id="add" action="/CMS/Add_newUser" method="post" >
        <input type="hidden" name="utype" id="utype" value="<%=utype %>" />
        <input type="hidden" name="belong" id="belong" value="<%=belong %>" />
        <input type="hidden" name="cons" id="cons" value="<%=cons %>" />
        <p>
        <label>Name:
        <input type="text" name="name" id="name" />
      </label>
        </p>
        <p>
        <label>Email Id:
        <input type="text" name="email" id="email" />
      </label>
        </p>
        <p>
        <label>Contact Number:
        <input type="text" name="phone" id="phone" />
      </label>
        </p>
        <%if(belong.equals("c")){ %>
        
        <p>
	     Select the Wards:</p>
		 <p> <label>
		 <%while(rst.next()){ 
		 temp = rst.getString("NUMBER");
		 %>
		 <p><%=temp %></p>
		 <input type = "checkbox" name="wards" id="wards" value="<%=temp %>" />
		 <%} %><br />
		 <p>All </p><input type="checkbox" name="wards" id="wards" value="all" />
		 </label></p>
		 <%} else { %>
		 <p>
	     Select the Ward:</p>
		 <p> <label>
		 <select name="ward" id="ward" >
		 <%while(rst.next()){ 
		 temp = rst.getString("NUMBER");
		 %>
		 <option value="<%=temp %>"><%=temp %></option>
		 <% } %>
		 </select>
		 
		 <% } %>
		 <%if(rst1.next()){ %>
		 <p>
		 Select the categories solvable by the user:
	     </p>  
	     <p>
		 <label>
		 <%do{ 
		 %>
		 <p><%=rst1.getString("CATEGORY") %></p>
		 <input type = "checkbox" name="cat" id="cat" value="<%=rst1.getLong("CAT_ID") %>" />
		 <%}while(rst1.next()); %><br/>
		 
		 </label></p>
		<%} else {%>
		<label><p>No categories exist currently. Please create a category first!</p></label>
		<%} %>
		 <input type="submit" value="Submit" />
		 
		 
        </form>
        </div>
        <div class="column1-unit"></div>                                    
      </div>
      </div>         

      
       <%@ include file="/Templates/footerA.jsp" %>