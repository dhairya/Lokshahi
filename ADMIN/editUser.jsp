<%@ include file="/Templates/initial_admin.jsp" %>
<%@ include file="/Templates/headerA.jsp" %>

 <script type="text/javascript">
 function access_info(access1)
{	
	
	var access=String(access1);
	
	for(i=0;i<access.length;i++)
	{
		c = access.charAt(i);
		
		for(j=0;j<document.getElementById('addk').gzone.length;j++)
		{
			if(i != 0)
				break;
			if(document.getElementById('addk').gzone[j].value==c.toString())
			{
				document.getElementById('addk').gzone[j].checked=true;
			
			}
			if(document.getElementById('addk').gzone[j].value=="5" || document.getElementById('addk').gzone[j].value=="6")
			{
				for(k=0;k<document.getElementById('addk').cat.length;k++)
  				{
  					document.getElementById('addk').cat[k].disabled=false;	
  				}
			}
			
		}
		for(j=0;j<document.getElementById('addk').forums.length;j++)
		{
			if(i != 1)
				break;
			if(document.getElementById('addk').forums[j].value==c.toString())
			{
				document.getElementById('addk').forums[j].checked=true;
				break;
			}
		}
		for(j=0;j<document.getElementById('addk').poll.length;j++)
		{
			if(i != 2)
				break;
			if(document.getElementById('addk').poll[j].value==c.toString())
			{
				document.getElementById('addk').poll[j].checked=true;
				break;
			}
		}
		for(j=0;j<document.getElementById('addk').funds.length;j++)
		{
			if(i != 3)
				break;
			if(document.getElementById('addk').funds[j].value==c.toString())
			{
				document.getElementById('addk').funds[j].checked=true;
				break;
			}
		}
		for(j=0;j<document.getElementById('addk').chat.length;j++)
		{
			if(i != 4)
				break;
			if(document.getElementById('addk').chat[j].value==c.toString())
			{
				document.getElementById('addk').chat[j].checked=true;
				break;
			}
		}
		for(j=0;j<document.getElementById('addk').employment.length;j++)
		{
			if(i != 5)
				break;
			if(document.getElementById('addk').employment[j].value==c.toString())
			{
				document.getElementById('addk').employment[j].checked=true;
				break;
			}
		}
		for(j=0;j<document.getElementById('addk').report.length;j++)
		{
			if(i != 6)
				break;
			if(document.getElementById('addk').report[j].value==c.toString())
			{
				document.getElementById('addk').report[j].checked=true;
				break;
			}
		}
		for(j=0;j<document.getElementById('addk').approve.length;j++)
		{
			if(i != 7)
				break;
			if(document.getElementById('addk').approve[j].value==c.toString())
			{
				document.getElementById('addk').approve[j].checked=true;
				break;
			}
		}
	}
}

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
        <h1 class="pagetitle">Edit Rights of User</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        <form  name="addk" id="addk" action="/CMS/Edit_accessinfo" method="post" >
         <%ResultSet rst = (ResultSet)request.getAttribute("userinfo"); %>
         <%String loginid=null,access_info=null, cat=null, consti_ward=null, nickname=null, access_wards=null; %>
         <%rst.next(); %>
         <%String type = (String)request.getAttribute("type");%>
         <%access_info = rst.getString("ACCESS_INFO"); %>
         <%if(type !=null){ %>
         <%if(type.equals("c")) { %>
         <%loginid=rst.getString("LOGIN"); %>
         <%access_wards=rst.getString("ACCESS_WARDS"); %> <!-- how to do?? -->
         <p> The login id of the constituency level special user is: <%=loginid %></p>
         <input type="hidden" value="<%=loginid %>" name="login" id="login" />
         <input type="hidden" value="<%=type %>" name="type" id="type" />
         <%} else if(type.equals("w")){%>
         <%loginid=rst.getString("LOGIN"); %>
         <p> The login id of the ward level special user is: <%=loginid %></p>
         <input type="hidden" value="<%=loginid %>" name="login" id="login" />
         <input type="hidden" value="<%=type %>" name="type" id="type" />
         <%}} else {%>
         <%loginid=rst.getString("VOTERID"); %>
         <%nickname=rst.getString("NICKNAME"); %>
         <%consti_ward=(String)request.getAttribute("consti_ward"); %>
         <p> The voter id of the citizen with nickname: <%=nickname %> is <%=loginid %></p>
         <input type="hidden" value="<%=loginid %>" name="login" id="login" />
         <input type="hidden" value="<%=consti_ward %>" name="consti_ward" id="consti_ward" />
         <%} %>
        
         <p>Access Rights:</p><br />
        
        <p>
	     Grievance Zone: &nbsp;</p>
	     <p> Read
	    <input type ="radio" name="gzone" id="gzone" value="1" onclick="disp_categories()" />&nbsp;
	     Read/Write
	   <input type ="radio" name="gzone" id="gzone" value="2"  onclick="disp_categories()" />&nbsp;
	     R/W/Self Edit/Self Delete
	    <input type ="radio" name="gzone" id="gzone" value="3" onclick="disp_categories()" />&nbsp;
	    R/W/E/D
	    <input type ="radio" name="gzone" id="gzone" value="4"  onclick="disp_categories()" />&nbsp;
	    Solve/R/W/Self Edit/Self Delete
	    <input type ="radio" name="gzone" id="gzone" value="5"  onclick="disp_categories()" />
		Solve/R/W/E/D
	    <input type ="radio" name="gzone" id="gzone" value="6"  onclick="disp_categories()" /></p>
	    <br />
	   <p> Forums: &nbsp;</p>
	   <p>   Read
	    <input type ="radio" name="forums" id="forums" value="1" />
	    Read/Write
	    <input type ="radio" name="forums" id="forums" value="2" />
	     R/W/Self Edit/Self Delete
	   <input type ="radio" name="forums" id="forums" value="3" />
	     R/W/E/D
	    <input type ="radio" name="forums" id="forums" value="4" /></p>
	    <br/>
	    <p> Polls: &nbsp;</p>
	     <p> Read
	    <input type ="radio" name="poll" id="poll" value="1" />
	     Read/Vote
	    <input type ="radio" name="poll" id="poll" value="2" />
	     Read/Write/Vote
	    <input type ="radio" name="poll" id="poll" value="3" />
	     R/W/Self Edit/Self Delete/Vote
	    <input type ="radio" name="poll" id="poll" value="4" />
	    R/W/E/D/Vote
	    <input type ="radio" name="poll" id="poll" value="5" /></p>
	    <br/>
	    <p> Funds: &nbsp;</p>
	     <p> Read
	     <input type ="radio" name="funds" id="funds" value="1" />
	      R/W/E/D
	   <input type ="radio" name="funds" id="funds" value="2" /></p>
	    <br/>
	    <p> Chat: &nbsp;</p>
	    <p> Can't Chat
	    <input type ="radio" name="chat" id="chat" value="1" />
	     Can Chat
	    <input type ="radio" name="chat" id="chat" value="2" />
	     Create and can chat
	    <input type ="radio" name="chat" id="chat" value="3" /></p>
	    <br />
	     <p> Employment: &nbsp;</p>
	   <p> Can't Access
	    <input type ="radio" name="employment" id="employment" value="1" />
	     Can Access
	    <input type ="radio" name="employment" id="employment" value="2" /></p>
	    <br />
	    <p> Reports: &nbsp;</p>
	     <p> Read
	    <input type ="radio" name="report" id="report" value="1"  />
	   Read/Write
	    <input type ="radio" name="report" id="report" value="2" /></p>
	    <br />
	    <p> Right to Approve: &nbsp;</p>
	   <p> No
	    <input type ="radio" name="approve" id="approve" value="1" />
	     Yes
	    <input type ="radio" name="approve" id="approve" value="2" /></p>
         
         <%ResultSet rst1 = (ResultSet)request.getAttribute("cat"); %>
         <p>
		 Select the categories solvable by the user:
	     </p><p>
		 <label>
		 <%while(rst1.next()){ 
		 %>
		 <p><%=rst1.getString("CATEGORY") %></p>
		 <input type = "checkbox" name="cat" id="cat" value="<%=rst1.getLong("CAT_ID") %>" />
		 <%} %><br/>
		 
		 </label></p>
		  <script type="text/javascript">
         accessinfo(<%=access_info%>)
         </script>
		 <%if(type != null){ %>
        <%if(type.equals("c")){ %>
         <%ResultSet rst2 = (ResultSet)request.getAttribute("wards"); %>
         <%String temp=null; %>
        <p>
	     Select the Wards:</p>
		 <p> <label>
		 <%while(rst2.next()){ 
		 temp = rst2.getString("NUMBER");
		 %>
		 <p><%=temp %></p>
		 <input type = "checkbox" name="wards" id="wards" value="<%=temp %>" />
		 <%} %><br />
		 <p>All </p><input type="checkbox" name="wards" id="wards" value="all" />
		 </label></p>
        <%}} %>
         <input type="submit" value="Submit" />
        </form>
        </div>
        <div class="column1-unit"></div>                                    
      </div>
      </div>         


 <%@ include file="/Templates/footerA.jsp" %>      