<%@ include file="/Templates/initial_admin.jsp" %>
<%@ include file="/Templates/headerA.jsp" %>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Add Category</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        <%ResultSet rst = (ResultSet)request.getAttribute("template"); %>
        <%ResultSet rst1 = (ResultSet)request.getAttribute("cat"); %>
        <%String temp=""; %>
        <%if(rst1.next()){ %>
        <p><label>
        Currently existing categories:
        <select name="category">
        <%do { %>
        <option value="category"><%=rst1.getString("CATEGORY") %></option>
        <%}while(rst1.next());%>
        </select>
        </label></p>
        <%} %>
        <form  name="add" id="add" action="/CMS/Add_category2" method="post" >
        <p>
	     Enter the category:</p>
		 <p> <label>
		 <input type="text" name="cat" id="cat"/>
		 </label></p>
		 <%if(rst.next()){ %>
		 <p>Select the user templates who can solve this category:</p>
		 <p><label>
		 <%do { %>
		 <%temp=rst.getString("NAME"); %>
		 <%=temp %>
		 <input type="checkbox" name="utypes" id="utypes" value="<%=temp %>" /><%}while(rst.next()); %>
		 ALL Special Users<input type="checkbox" name="utypes" id="utypes" value="all" />
		 </label></p>
		 <%} else { %>
		 <label><p>No user templates exist currently to associate the category with. Please create a special user template first!</p></label>
		 <%} %>
         <p><input type="submit" value="Submit" /></p>
        </form>
        </div>
        <div class="column1-unit"></div>                                    
      </div>
      </div>         


 <%@ include file="/Templates/footerA.jsp" %>