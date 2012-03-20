<%@ include file="/Templates/initial_admin.jsp" %>
<%@ include file="/Templates/headerA.jsp" %>
 <script type="text/javascript">
function disp_categories() {
  var val;
for(i=0;i<document.getElementById('addk').gzone.length;i++)
{
	if(document.getElementById('addk').gzone[i].checked)
		val=document.getElementById('addk').gzone[i].value;
}

  if(val.toString()=="5" || val.toString()=="6")
  {
  	for(i=0;i<document.getElementById('addk').cat.length;i++)
  	{
  		document.getElementById('addk').cat[i].disabled=false;	
  	}
  }
  else
  {
  	for(i=0;i<document.getElementById('addk').cat.length;i++)
  	{
  		document.getElementById('addk').cat[i].disabled=true;	
  	}
  }
 }
</script>
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Access Rights of new user</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        <form  name="addk" id="addk" action="/CMS/Approveuser_2" method="post" >
        <%String cons = (String) request.getAttribute("cons"); %>
        <%String ward = (String) request.getAttribute("ward"); %>
        <%String vid = (String) request.getAttribute("vid"); %>
        <input type="hidden" value="<%=cons%>" name="cons" id="cons" />
        <input type="hidden" value="<%=ward %>" name="ward" id="ward" />
        <input type="hidden" value="<%=vid %>" name="vid" id="vid" />
        <p> Choose the access rights for the new user:</p><br />
	     <p>
	     Grievance Zone: &nbsp;</p>
	     <p> Read
	    <input type ="radio" name="gzone" id="gzone" value="1" onclick="disp_categories()" />&nbsp;
	     Read/Write
	   <input type ="radio" name="gzone" id="gzone" value="2"  onclick="disp_categories()" />&nbsp;
	     R/W/Self Edit/Self Delete
	    <input type ="radio" name="gzone" id="gzone" value="3" onclick="disp_categories()" CHECKED />&nbsp;
	    R/W/E/D
	    <input type ="radio" name="gzone" id="gzone" value="4"  onclick="disp_categories()" />&nbsp;
	    Solve/R/W/Self Edit/Self Delete
	    <input type ="radio" name="gzone" id="gzone" value="5"  onclick="disp_categories()" />
		Solve/R/W/E/D
	    <input type ="radio" name="gzone" id="gzone" value="6" onclick="disp_categories()" /></p>
	    <br />
	   <p> Forums: &nbsp;</p>
	   <p>   Read
	    <input type ="radio" name="forums" id="forums" value="1" />
	    Read/Write
	    <input type ="radio" name="forums" id="forums" value="2" />
	     R/W/Self Edit/Self Delete
	   <input type ="radio" name="forums" id="forums" value="3" CHECKED />
	     R/W/E/D
	    <input type ="radio" name="forums" id="forums" value="4" /></p>
	    <br/>
	    <p> Polls: &nbsp;</p>
	     <p> Read
	    <input type ="radio" name="poll" id="poll" value="1" />
	     Read/Vote
	    <input type ="radio" name="poll" id="poll" value="2" CHECKED/>
	     Read/Write/Vote
	    <input type ="radio" name="poll" id="poll" value="3" />
	     R/W/Self Edit/Self Delete/Vote
	    <input type ="radio" name="poll" id="poll" value="4" />
	    R/W/E/D/Vote
	    <input type ="radio" name="poll" id="poll" value="5" /></p>
	    <br/>
	    <p> Funds: &nbsp;</p>
	     <p> Read
	     <input type ="radio" name="funds" id="funds" value="1" CHECKED />
	      R/W/E/D
	   <input type ="radio" name="funds" id="funds" value="2" /></p>
	    <br/>
	    <p> Chat: &nbsp;</p>
	    <p> Can't Chat
	    <input type ="radio" name="chat" id="chat" value="1" />
	     Can Chat
	    <input type ="radio" name="chat" id="chat" value="2" CHECKED />
	     Create and can chat
	    <input type ="radio" name="chat" id="chat" value="3" /></p>
	    <br />
	     <p> Employment: &nbsp;</p>
	   <p> Can't Access
	    <input type ="radio" name="employment" id="employment" value="1" />
	     Can Access
	    <input type ="radio" name="employment" id="employment" value="2" CHECKED /></p>
	    <br />
	    <p> Reports: &nbsp;</p>
	     <p> Read
	    <input type ="radio" name="report" id="report" value="1"  CHECKED />
	   Read/Write
	    <input type ="radio" name="report" id="report" value="2"  /></p>
	    <br />
	    <p> Right to Approve: &nbsp;</p>
	   <p> No
	    <input type ="radio" name="approve" id="approve" value="1" CHECKED />
	     Yes
	    <input type ="radio" name="approve" id="approve" value="2"  /></p>
         
         <%ResultSet rst1 = (ResultSet)request.getAttribute("cat"); %>
         <%if(rst1.next()){ %>
         <p>
		 Select the categories solvable by the user:
	     </p><p>
		 <label>
		 <%do{ 
		 %>
		 <p><%=rst1.getString("CATEGORY") %></p>
		 <input type = "checkbox" name="cat" id="cat" value="<%=rst1.getLong("CAT_ID") %>" />
		 <%}while(rst1.next()); %><br/>
		 
		 </label></p>
         <%} else {%>
         <label><p>No categories exist currently!!</p></label>
         <%} %>
         <input type="submit" value="Submit" />
        </form>
        </div>
        <div class="column1-unit"></div>                                    
      </div>
      </div>         

       <%@ include file="/Templates/footerA.jsp" %>