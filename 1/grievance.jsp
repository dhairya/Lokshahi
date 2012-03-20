<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerW.jsp" %>

<script type="text/javascript" src="/CMS/css/yetii.js"></script>
<script type="text/javascript">
 function search(name)
 {
 	document.getElementById("searchtype").value=name;
 	document.probsearch.submit();
 	return false;
 }
</script>

    <!-- B.2 MAIN CONTENT -->
    <div class="main-content">
      <div id="translation"></div>
      <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Grievance Zone</h1>
        <!-- Content unit - One column -->
        <div class="column1-unit">
          <form method="post" action="/CMS/Grievance_search?consti=<%=consti%>&ward=<%=ward%>" name="probsearch">
            <input type="hidden" id="searchtype" name="searchtype" />
            <div id="tab-container-1" class="ylayout">
              <ul id="tab-container-1-nav"  class="ylayout">
                <li><a href="#tab1">Status filter</a></li>
                <li><a href="#tab2">Custom</a></li>
                <li><a href="#tab3">Text Search</a></li>
                <li><a href="#tab4">Ref ID Search</a></li>
              </ul>
              <div class="tabs-container">
                <div class="tab" id="tab1">
                  <p>Status : </p>
                  <label>
                  <select name="filter" id="filter">
                    <option value="0">Unread</option>
                    <option value="1">Read</option>
                    <option value="2">Solved</option>
                    <option value="10">Challenged 1</option>
                    <option value="20">Challenged 2</option>
                    <option value="30">Challenged 3</option>
                    <option value="40">Challenged 4</option>
                  </select>
                  </label>
                  <input type="button" value="Status Filter" id="status" onclick="search(this.id)" />
                  &nbsp; </div>
                <div class="tab" id="tab2">
                  <p>&nbsp;
                    <input type="button" value="High Rated Problems" id="rating" onclick="search(this.id)"/>
                    <input type="button" value="All problems" id="all" onclick="search(this.id)"/>
                  </p>
                </div>
                <div class="tab" id="tab3">
                  <input type="text" name="searchtxt" id="searchtxt" />
                  <input type="button" id="txtser" value="Search" onclick="search(this.id)" />
                </div>
                <div class="tab" id="tab4">
                  <input type="text" name="searchref" id="searchref" />
                  <input type="button" id="txtser" value="Search" onclick="search(this.id)" />
                </div>
              </div>
            </div>
          </form>
          <script type="text/javascript">
var tabber1 = new Yetii({
id: 'tab-container-1'
});
</script>
          <p>&nbsp;</p>
          <h2 align="center"> Latest Posted Grievances</h2>
          <table width="100%" border="1">
            <tr>
              <th width="15%" scope="col"> Ref ID </th>
              <th width="35%" scope="col">Grievance</th>
              <th width="20%" scope="col">Rating</th>
              <th width="30%" scope="col">Status</th>
            </tr>
            <% int i=0; 
            ResultSet rs= (ResultSet)request.getAttribute("prob");
            String status = null;
            	while(i<20 && rs.next()){%>
            <tr>
              <td><%=rs.getLong("REF_ID")%></td>
              <td><a href="/CMS/Grievance_view?consti=<%=consti%>&ward=<%=ward%>&refid=<%=rs.getLong("REF_ID")%>&status=<%=rs.getInt("STATUS")%>"><%=rs.getString("TITLE")%></a></td>
              <td><%=rs.getFloat("INTELLI_RATING")%></td>
              <%int value = rs.getInt("STATUS");
               if(value == 0)
               status = "Unread by civic official";
            if(value == 1)
            	status = "Read by civic official";
            if(value == 2)
            	status = "Problem solved by civic official";
            if(value == 10)
            	status = "Challenged once and unread";
            if(value == 11)
            	status = "Challenged once and read";

            if(value == 12)
            	status = "Challenged once and solved";
            if(value == 20)
            	status = "Challenged twice and unread";

            if(value == 21)
            	status = "Challenged twice and read";

            if(value == 22)
            	status = "Challenged twice and solved";
            if(value == 30)
            	status = "Challenged thrice and unread";

            if(value == 31)
            	status = "Challenged thrice and read";

            if(value == 32)
            	status = "Challenged thrice and solved";
            if(value == 40)
            	status = "Challenged four times by citizens";%>
              <td><%=status %></td>
            </tr>
            <%i++;} %>
          </table>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp; </p>
          <%if(request.getAttribute("row").equals("1")){ %>
          <p>&nbsp;</p>
          <%}else{ %>
          <h2><a href = "/CMS/Grievance_prev?consti=<%=consti%>&ward=<%=ward%>&searchtype1=<%=request.getAttribute("sertype")%>&row=<%=request.getAttribute("row")%>">Previous</a></h2>
          <%} %>
          &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <%if(i==20 && rs.next()){ %>
          <h2><a href = "/CMS/Grievance_nxt?consti=<%=consti%>&ward=<%=ward%>&searchtype1=<%=request.getAttribute("sertype")%>&row=<%=request.getAttribute("row")%>">Next</a></h2>
          <%}else{ %>
          <p>&nbsp;</p>
          <%} %>
        </div>
      </div>
    </div>

    <%@ include file="/Templates/footer.jsp" %>