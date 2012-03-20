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

  var cons=document.reg.code.value;
  xmlHttp.open("GET","/CMS/Chkeditconst?cons="+cons ,true)
  xmlHttp.onreadystatechange=handleStateChange;
  xmlHttp.send(null);

}
function startRequest1()
{
  createXmlHttpRequest();

  var cons=document.reg.code.value;
  var ward=document.reg.name.value;
  xmlHttp.open("GET","/CMS/Chkward?ward="+ward+"&cons="+cons ,true)
  xmlHttp.onreadystatechange=handleStateChange1;
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
              document.getElementById("code").value="";
              
            }
            
            }
        else
        {
           alert("Error loading page"+ xmlHttp.status +
":"+xmlHttp.statusText);
        }
    }
}

function handleStateChange1()
{
    if(xmlHttp.readyState==4)
    {
        if(xmlHttp.status==200)
            {
          var message =  
             xmlHttp.responseXML
                    .getElementsByTagName("valid")[0]
                    .firstChild.data;

             document.getElementById("result1").innerHTML=message;
             
          var error = 
               xmlHttp.responseXML
                    .getElementsByTagName("valid")[1]
                    .firstChild.data;
                    
            if (error == 0 ) 
            {
              document.getElementById("name").value="";
              
            }
            
            }
        else
        {
           alert("Error loading page"+ xmlHttp.status +
":"+xmlHttp.statusText);
        }
    }
}

 function ValidateForm(){
       var cons=document.reg.code
       var name=document.reg.name
       if (cons.value==""){
		alert('Please Enter a Valid Constituency Code')
		cons.focus()
		return false
	   }
	   if (name.value==""){
		alert('Please Enter a Valid ward name')
		name.focus()
		return false
	   }
}
 
</script>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Create New Ward</h1>
		  <div class="column1-unit">
		<form method="post" name="reg" id="reg" action="/CMS/Createward" onSubmit="return ValidateForm()">
		  
		<p>
		Enter the code of the constituency:
		</p>
		 <p> <label>
		 <input type="text" name="code" id="code" onBlur="startRequest()"/>
		 </label></p>
		<br />
		<label> <p id="result"></p> </label>
		<br />
		<p>
		  Enter the name of the ward:
		  </p>
		 <p> <label>
		 <input type="text" name="name" id="name" onBlur="startRequest1()"/>
		 </label></p>
		<br />
		<label> <p id="result1"></p> </label>
		<br />
		<p><input type="submit" value="Submit" /> </p>
</form>
		</div>
		</div>
      </div>         

       <%@ include file="/Templates/footerA.jsp" %>