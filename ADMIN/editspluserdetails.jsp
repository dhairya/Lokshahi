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

  var vid=document.reg.vid.value;
  xmlHttp.open("GET","/CMS/Chkloginid?vid="+vid ,true)
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
        <h1 class="pagetitle">Edit Details of Special User</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        <form method="post" action="/CMS/Editdetails" name="reg" >
        <p> Enter Login ID of the Special User:
        <input type="text" name="vid" id="vid" onblur="startRequest()" /></p>
        <label> <p id="result"></p> </label>
        <p>Change Name:
        <input type="text" name="uname" id="uname" /></p>
        <p>Change password:
        <input type="password" name="pwd" id="pwd" /></p>
        <p>Change Email:
        <input type="text" name="email" id="email" /></p>
        <p>Change contact number:
        <input type="text" name="phone" id="phone" /></p>
        <p><input type="submit" value="submit" /></p>
       </form>
        </div>
        <div class="column1-unit"></div>                                    
      </div>
      </div>         

 <%@ include file="/Templates/footerA.jsp" %>

