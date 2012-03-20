<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerW.jsp" %>

<script type="text/javascript">

 function buttonid(name) {
 	document.getElementById("Command").value=name;
 	document.update.submit();
 	return false;
 }
</script>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">View posts</h1>
		
        <!-- Content unit - One column -->
        <div class="column1-unit">
        <p><a href="/CMS/Forum_main?consti=<%=consti %>&ward=<%=ward%>">Back to all posts</a></p>
        <%ResultSet original = (ResultSet)request.getAttribute("original_post");%>
        <%original.next(); %>
        <%long forid = original.getLong("FOR_ID"); %>       
         <%String nickname=null; %>
         <%nickname = original.getString("NICKNAME");%> 
          <p>Forum ID: <%=forid %></p>
         <h2>Post Title:<%=original.getString("TITLE")%></h2>
        <p> <%=original.getString("DESCRIPTION")%></p>
         <%long time = original.getTimestamp("DATE").getTime();%>
              <%java.text.DateFormat df = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.LONG,java.text.DateFormat.MEDIUM);  %>
         <p>(Posted By <%=nickname %> on <%=df.format(new java.util.Date(time))%>)</p>
         
          <hr class="clear-contentunit" />  
          
         <p>Replies to this post</p>
          <hr class="clear-contentunit" />
          <%ResultSet subpost = (ResultSet)request.getAttribute("sub_post");%>
          <%java.text.DateFormat dfs = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.LONG,java.text.DateFormat.MEDIUM);  %>
          <%long times; %>
         <%while(subpost.next()){ %>
         <%nickname = subpost.getString("NICKNAME"); %>
          <p><%=subpost.getString("DESCRIPTION")%></p>
         <%times = subpost.getTimestamp("DATE").getTime();%>    
         <p>(Posted By <%=nickname %> on <%=dfs.format(new java.util.Date(times))%>)</p>
   		<%} %><br/><br/>
         
        <p><a href="/CMS/Forum_main?consti=<%=consti %>&ward=<%=ward%>">Back to all posts</a></p>
        </div>
        
        <!-- Content unit - One column -->
        <div class="column1-unit">        
        
          </div>
        
        <hr class="clear-contentunit" />                    
                
        <!-- Content unit - One column -->
        <div class="column1-unit">
          <h1>&nbsp;</h1>                            
          </div>
        <div class="column1-unit"></div>                                    
      </div>
       </div>         
       
        <%@ include file="/Templates/footer.jsp" %>    
