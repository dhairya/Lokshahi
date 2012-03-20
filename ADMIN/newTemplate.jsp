<%@ include file="/Templates/initial_admin.jsp" %>
<%@ include file="/Templates/headerA.jsp" %>


<script type="text/javascript">
 
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

  var type=document.add.type.value;
  xmlHttp.open("GET","/CMS/Chktemplate?type="+type ,true)
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
                    
            if (error == 0 ) 
            {
              document.getElementById("type").value="";
              
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
        <h1 class="pagetitle">Add Template</h1>
		  <div class="column1-unit">
		  
		  <p>&nbsp;</p>
		<form method="post" action="/CMS/Add_template" name="add">
		 <p>
	     Enter the type of User Template:</p>
		 <p> <label>
		 <input type="text" name="type" id="type" onBlur="startRequest()" />
		 </label></p>
		 <label> <p id="result"></p> </label>
		 <br />
		 <p> Choose the access rights for the User Template:</p><br />
	     <p>
	     Grievance Zone: &nbsp;</p>
	     <p> Read
	    <input type ="radio" name="gzone" id="gzone" value="1" />&nbsp;
	     Read/Write
	   <input type ="radio" name="gzone" id="gzone" value="2" />&nbsp;
	     R/W/Self Edit/Self Delete
	    <input type ="radio" name="gzone" id="gzone" value="3" />&nbsp;
	    R/W/E/D
	    <input type ="radio" name="gzone" id="gzone" value="4" />&nbsp;
	    Solve/R/W/Self Edit/Self Delete
	    <input type ="radio" name="gzone" id="gzone" value="5" />
	    Solve/R/W/E/D
	    <input type ="radio" name="gzone" id="gzone" value="6" /></p>
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
	    <input type="radio" name"poll" id="poll" value="2" />
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
	    <input type ="radio" name="report" id="report" value="1" />
	   Read/Write
	    <input type ="radio" name="report" id="report" value="2" /></p>
	    <br />
	    <p> Right to Approve: &nbsp;</p>
	   <p> No
	    <input type ="radio" name="approve" id="approve" value="1" />
	     Yes
	    <input type ="radio" name="approve" id="approve" value="2" /></p>
	  <p>  <input type="submit" value="submit" /></p>
	    </form>
		 
		  <br />
		
		 
         </div>
		</div>
      </div>         

 <%@ include file="/Templates/footerA.jsp" %>


