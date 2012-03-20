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
  <div class="main-content">
    <div id="translation"></div>
    <div id="article">
      <!-- Pagetitle -->
      <h1 class="pagetitle">View Problems</h1>
      
        <!-- Content unit - One column -->
        <div class="column1-unit">
        <form action="/CMS/Grievance_change?consti=<%=consti%>&ward=<%=ward%>" method="post" name="update">
          <%ResultSet rs= (ResultSet)request.getAttribute("prob");
    	rs.next();%>
          <input type="hidden" name="ProbID" id="ProbID" value="<%=rs.getString("REF_ID")%>"/>
          <h1><%=rs.getString("TITLE")%></h1>
          <%long time = rs.getTimestamp("DATE").getTime();%>
          <%java.text.DateFormat df = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.LONG,java.text.DateFormat.MEDIUM);  %>
          <h3>Posted on <%=df.format(new java.util.Date(time))%> by <%=rs.getString("NICKNAME")%></h3>
          <%  String statusl= null; %>
          <%int status = rs.getInt("STATUS"); 
          
            if(status == 0)
               statusl = "Unread by civic official";
            if(status == 1)
            	statusl = "Read by civic official";
            if(status == 2)
            	statusl = "Problem solved by civic official";
            if(status == 10)
            	statusl = "The claim that this problem has been solved, is challenged once by citizens";
            if(status == 20)
            	statusl = "The claim that this problem has been solved, is challenged twice by citizens";
            if(status == 30)
            	statusl = "The claim that this problem has been solved, is challenged thrice by citizens";
            if(status == 40)
            	statusl = "The claim that this problem has been solved, is challenged four times by citizens";%>
          <p>Status:<%=statusl %></p>
          <input type="hidden" name="Status" value="<%=status%>"/>
          <p>Category:<%=rs.getString("DEPARTMENT")%></p>
          <p>No. of people joined :<%=rs.getInt("NUM_PPL_JOINED")%></p>
          <p>Admin Rating :<%=rs.getFloat("ADMIN_RATING")%></p>
          <p>Public Rating :<%=rs.getFloat("PUBLIC_RATING")%></p>
          <p>Intelli Rating :<%=rs.getFloat("INTELLI_RATING")%></p>
          <p>Description :<br/>
          <textarea name="Description"><%=rs.getString("DESCRIPTION")%></textarea></p>
          <input type="button" value="Edit" id="Edit" onclick="buttonid(this.id)"/>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>Rate it :
            <select name="Rate">
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
            </select>
          </p>
          <%if(session.getAttribute("Nick").equals(rs.getString("NICKNAME"))){ %>
          <input type="button" value="Rate it!" id="Rate" disabled="true"/>
          <%}else{ %>
          <input type="button" value="Rate it!" id="Rate" onclick="buttonid(this.id)"/>
          <%} %>
          <%if(request.getAttribute("join").toString().equals("0") || session.getAttribute("Nick").equals(rs.getString("NICKNAME"))){ %>
          <input type="button" value="Join the Problem" id="Join" disabled="true"/>
          &nbsp;&nbsp;
          <%}else{ %>
          <input type="button" value="Join the Problem" id="Join" onclick="buttonid(this.id)"/>
          &nbsp;&nbsp;
          <%} %>
          <input type="button" value="Delete Problem" id="Delete" onclick="buttonid(this.id)" />
          &nbsp;&nbsp;
          <%if(request.getAttribute("challenge").toString().equals("0") || (status!=2 && status!=12 && status!=22 && status!=32)){ %>
          <input type="button" value="Challenge" id="Challenge" disabled="true" />
          <%}else{ %>
          <input type="button" value="Challenge" id="Challenge" onclick="buttonid(this.id)" />
          <%} %>
          
          <%String mycats[] = session.getAttribute("Categories").toString().split(",");
          int solvable=0;
          for(int i=0;i<mycats.length;i++){
          	if(mycats[i].equals(rs.getString("DEPARTMENT")))
          	{
          		solvable=1;
          		break;
          	}
          }%>
          <%if(status!=2 && status!=12 && status!=22 && status!=32 && status!=40 && solvable==1){ %>
          <input type="button" value="Solve" id="Solve" onclick="buttonid(this.id)" />
          <%}else{ %>
          <input type="button" value="Solve" id="Solve"  disabled = "true"/>
          <%} %>
          <input type="hidden" name="Command" id="Command" />
          </form>
        </div>
        <hr class="clear-contentunit" />
        <!-- Content unit - One column -->
        <div class="column1-unit">
          <%ResultSet rs1= (ResultSet)request.getAttribute("comments");
    	while(rs1.next()){%>
          <h1></h1>
          <%long time1 = rs1.getTimestamp("DATE").getTime();%>
          <%java.text.DateFormat df1 = java.text.DateFormat.getDateTimeInstance(java.text.DateFormat.LONG,java.text.DateFormat.MEDIUM);  %>
          <h3>Posted on <%=df1.format(new java.util.Date(time1))%> by <%=rs1.getString("NICKNAME")%></h3>
          <p><%=rs1.getString("COMMENTS")%></p>
        </div>
        <hr class="clear-contentunit" />
        <%}%>
        <!-- Content unit - One column -->
        <div class="column1-unit">
        <form action="/CMS/Grievance_change?consti=<%=consti%>&ward=<%=ward%>" method="post" name="update1">
        <input type="hidden" name="ProbID" id="ProbID" value="<%=rs.getString("REF_ID")%>"/>
          <h1>Post a Comment</h1>
          <p>Comment:</p>
           
          <textarea name="Comments" rows="4" cols="60"></textarea>
          <br />
          <br />
          <p>&nbsp;
            <input type="button" value="Add Comment" id="Add_comm" onclick = "document.update1.submit()"/>
            <input type="hidden" name="Command1" id="Command1" value="Add_comm"  />
          </p>
      	  </form>
        </div>
      <!-- Content unit - One column -->
      <div class="column1-unit">
        <h1>&nbsp;</h1>
      </div>
      <div class="column1-unit"></div>
    </div>
  </div>

  <%@ include file="/Templates/footer.jsp" %> 