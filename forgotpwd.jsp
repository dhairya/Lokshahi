 <%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerG.jsp" %>

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
 
 
 function enable()
 {
 	alert("1");
 	
   var uname = document.reg.username.value;
   var start = uname.charAt(0);
   if(start == 'C')
   {
   		document.getElementById('cons').disabled=false;
     	document.getElementById('ward').disabled=false;
   }
    else if(start == 'W')
  {
  	 document.getElementById('cons').disabled=true;
     document.getElementById('ward').disabled=true;
  }
  else if(start == 'A' || start == 'a')
  {
  	 document.getElementById('cons').disabled=true;
     document.getElementById('ward').disabled=true;
  }
  else
  {
  	 document.getElementById('cons').disabled=false;
     document.getElementById('ward').disabled=false;
  }
 
 }
 
</script>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Forgot Password</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        
               
        <form action="/CMS/Forgotpwd" method="post" name="reg" >
        <p>Password forgotten???</p><br />
       <p> Please enter your ppl2.0 username:
        <input type=text name="username" id="username" onblur="enable()"/></p><br />
        <label>  </label>
         <%List<Map<String, Object>> rst;%>
 <jsp:useBean id="consti" class="beans.Load_Constibean">
		<jsp:setProperty name="consti" property="*"/>
		<% rst = consti.loadConsti(); %>
		  </jsp:useBean>
		  <p> Select Constituency  </p>
           <p>
            <label>
            <select name="cons" id="cons" onchange="getWards(this)" disabled="true">
            <option>&nbsp;</option>
            <%ListIterator<Map<String,Object>> litrst = rst.listIterator();  %>
			<%while(litrst.hasNext()) { Map maprst = litrst.next();%>
            <option value="<%=maprst.get("CODE").toString() %>"><%=maprst.get("CONSTI_NAME").toString() %></option>
            <%} %>
            </select>
            </label>
<p> Ward Number</p>
<p><label>
<select id="ward" name="ward" disabled="true">
		            </select>
</label></p>
<label> <p id="result"></p> </label>
        <input type="submit" value="Submit" />
        </form>          
        </div>          
        <hr class="clear-contentunit" />                             
                      
 
        <!-- Content unit - One column -->
        <div class="column1-unit">
          
        </div>                                    
      </div>
       </div>         
       
              <%@ include file="/Templates/footerG.jsp" %>
