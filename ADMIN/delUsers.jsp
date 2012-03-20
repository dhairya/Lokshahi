<%@ include file="/Templates/initial_admin.jsp" %>
<%@ include file="/Templates/headerA.jsp" %>



<script src="/CMS/ajax.js"></script>
<script type="text/javascript">
var ajax = new Array();

function getWards(consti)
{
	var cons = consti.options[consti.selectedIndex].value;
	document.getElementById('ward').options.length = 0;	// Empty Subject select box
	if(cons.length>0){
		var index = ajax.length;
		ajax[index] = new sack();
		
		ajax[index].requestFile = '/CMS/List_wards?cons='+cons;	// Specifying which file to get
		
		ajax[index].onCompletion = function(){ createWards(index) };	// Specify function that will be executed after file has been found
		ajax[index].runAJAX();		// Execute AJAX function
	}
}

function createWards(index)
{
	var obj = document.getElementById('ward');
	eval(ajax[index].response);	// Executing the response from Ajax as Javascript code	
}


 
 function chktype(utype) {
 
  var type = utype.options[utype.selectedIndex].value;

  if(type=="spluser")
   {
     document.getElementById('cons').disabled=true;
     document.getElementById('ward').disabled=true;
   }
   if(type=="citizen")
   {
     document.getElementById('cons').disabled=false;
     document.getElementById('ward').disabled=false;
   }
 }
 
   var xmlHttp;// global instance of XMLHttpRequest
function createXmlHttpRequest()
{
       if(window.ActiveXObject)
       {
        xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
      }

    else if(window.XMLHttpRequest)
    {
        xmlHttp=new XMLHttpRequest();
     }

}

function startRequest()
{
  createXmlHttpRequest();
  var vid = document.del.vid.value;
  
  var cons="no";
  var ward="no";
  if(document.getElementById('cons').disabled==false)
  {
  	cons = document.del.cons.options[document.del.cons.selectedIndex].value;
    ward = document.del.ward.options[document.del.ward.selectedIndex].value;
  }
  xmlHttp.open("GET","/CMS/Chkloginid?cons="+cons+"&ward="+ward+"&vid="+vid ,true)
  xmlHttp.onreadystatechange=handleStateChange;
  xmlHttp.send(null);

}

function handleStateChange()
{
    if(xmlHttp.readyState==4)
    {
        if(xmlHttp.status==200)
            {
          var message =  
             xmlHttp.responseXML
                    .getElementsByTagName("valid")[0]
                    .firstChild.data;

             document.getElementById("result").innerHTML=message;
            
          var error = 
               xmlHttp.responseXML
                    .getElementsByTagName("valid")[1]
                    .firstChild.data;
                    
            if (error == 1 ) 
            {
              document.getElementById("vid").value="";
              document.getElementById("vid").focus();
            }
            
            }
        else
        {
           alert("Error loading page"+ xmlHttp.status +
":"+xmlHttp.statusText);
        }
    }
}

</script>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Edit/Delete Users</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        <p><b><a href="/CMS/ADMIN/editspluserdetails.jsp">Edit Special User Details</a><br/>
        <form method=post action="/CMS/Deleteuser" name="del">
        <p>Select to delete or edit User:</p><br />
        <p>Edit&nbsp;<input type="radio" value="edit" name="user" id="user" /></p>
        <p>Delete&nbsp;<input type="radio" value="delete" name="user" id="user" /></p>
        <p>Select the type of user</p>
        <p><label>
        <select name="type" id="type" onchange="chktype(this)" >
        <option>&nbsp;</option>
        <option value="spluser">Special User</option>
        <option value="citizen">Citizen</option>
        </select></label></p>
        
 <%List<Map<String, Object>> rst=null; %>
 <jsp:useBean id="constituency" class="beans.Load_Constibean">
		<jsp:setProperty name="constituency" property="*"/>
		<% rst = constituency.loadConsti(); %>
		  </jsp:useBean>
		  <p> Select Constituency  </p>
           <p>
            <label>
            <select name="cons" id="cons" onchange="getWards(this)" disabled="true">
            <option>&nbsp;</option>
            <%ListIterator<Map<String,Object>> litrdel = rst.listIterator();  %>
            <%while(litrdel.hasNext()) { 
            	Map<String,Object> mapdel = litrdel.next(); %>
            <option value="<%=mapdel.get("CODE").toString() %>"><%=mapdel.get("CONSTI_NAME").toString() %></option>
            <%} %>
            </select>
            </label>
<p> Ward Number</p>
<p><label>
<select id="ward" name="ward" disabled="true">
		            </select>
</label></p>
        <p>Enter Voter ID for SpecialUser/Nickname for Citizen: &nbsp;<input type="text" name="vid" id="vid" onblur="startRequest()"/> </p>
        <label> <p id="result"></p> </label>
        <input type="submit" name="Submit" value="Submit" />
        </form>
        </div>
        <div class="column1-unit"></div>                                    
      </div>
      </div>         

 <%@ include file="/Templates/footerA.jsp" %>
 
 

