<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerW.jsp" %>

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
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Forum</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
       <%ResultSet rs = (ResultSet)request.getAttribute("prob"); %>
       <form method="post" action="/CMS/Forum_search?consti=<%=consti%>&ward=<%=ward%>" name="probsearch">
          <input type="hidden" id="searchtype" name="searchtype" />
            <p>
              <label>Text Search 
             &nbsp;&nbsp;   
             <input type="text" name="searchtxt" id="searchtxt" />
              </label></p>OR
             <p><label>Search by Reference ID&nbsp;&nbsp;&nbsp;
             <input type="text" name="searchref" id="searchref" />
            </label></p>
            <input type="button" id="txtser" value="Search" onclick="search(this.id)" />
          </form><br /><hr />
       <h2 align="center">Posts</h2>
       <table width="100%" border="1">
            <tr>
              <th width="40" scope="col">Forum ID</th>
              <th width="100" scope="col">Post Title</th>
              <th width="60" scope="col">Posted by</th>
              <th width="130" scope="col">Posted on</th>
            </tr>
       <%int i=0;
       long time;
       long fid;
       java.text.DateFormat df = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.LONG,java.text.DateFormat.MEDIUM);               
       while(i<20 && rs.next()) {%>
         <tr>
              <%fid = rs.getLong("FOR_ID");%>
              <td><%=fid%></td>
              <td><a href="/CMS/Forum_view?consti=<%=consti%>&ward=<%=ward%>&forid=<%=fid%>"><%=rs.getString("TITLE")%></a></td>
              <td><%=rs.getString("NICKNAME")%></td>
              <% time = rs.getTimestamp("DATE").getTime();%>
              
              <td> <%=df.format(new java.util.Date(time))%></td>
              </tr>
              <%i++;} %>
              </table>
              <br/>
              <br/>
              <%if(request.getAttribute("row").equals("1")){ %>
         <p>&nbsp;</p>
          <%}else{ %>
		  <a href = "/CMS/Forum_prev?consti=<%=consti%>&ward=<%=ward%>&row=<%=request.getAttribute("row")%>&searchtype1=<%=request.getAttribute("sertype")%>">Previous</a>
		  <%} %>
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  <%if(i==20 && rs.next()){ %>
		  <a href = "/CMS/Forum_nxt?consti=<%=consti%>&ward=<%=ward%>&row=<%=request.getAttribute("row")%>&searchtype1=<%=request.getAttribute("sertype")%>">Next</a>
          <%}else{ %>
         <p>&nbsp;</p>
          <%}%>
          <h2 align="center"><a href = "/CMS/<%=session.getAttribute("Access").toString().charAt(1)%>/post_forum.jsp?consti=<%=consti%>&ward=<%=ward%>">New Post</a></h2>
          
          
          
          
        </div>
      </div>
       </div>         

              <%@ include file="/Templates/footer.jsp" %>        
       