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

  var nick=document.reg.nick.value;
  var cons=document.reg.cons.options[document.reg.cons.selectedIndex].value;
  var ward=document.reg.ward.options[document.reg.ward.selectedIndex].value;
  xmlHttp.open("GET","/CMS/Chkuser?nick="+nick+"&cons="+cons+"&ward="+ward ,true)
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
                    
                          
                 if (error == 2 ) 
            {
             
              document.getElementById("nick").value="";
              document.getElementById("nick").focus();
            }     
            
            }
        else
        {
           alert("Error loading page"+ xmlHttp.status +
":"+xmlHttp.statusText);
        }
    }
}
 

 
// Declaring required variables

// var minDigitsInIPhoneNumber = 10;
// var minDigitsInIPhoneTNumber = 6;
function isInteger(s)
{   var i;
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character is number.
        var c = s.charAt(i);
        if (((c < "0") || (c > "9"))) return false;
    }
    // All characters are numbers.
    return true;
}
function trim(s)
{   var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not a whitespace, append to returnString.
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character isn't whitespace.
        var c = s.charAt(i);
        if (c != " ") returnString += c;
    }
    return returnString;
}
function stripCharsInBag(s, bag)
{   var i;
    var returnString = "";
    // Search through string's characters one by one.
    // If character is not in bag, append to returnString.
    for (i = 0; i < s.length; i++)
    {   
        // Check that current character isn't whitespace.
        var c = s.charAt(i);
        if (bag.indexOf(c) == -1) returnString += c;
    }
    return returnString;
}

function checkInternationalPhone(strPhone){
var minDigitsInIPhoneNumber = 10;

s=trim(strPhone)
return (isInteger(s) && s.length >= minDigitsInIPhoneNumber);
}

function checkInternationalPhoneT(strPhone){

var minDigitsInIPhoneTNumber = 6;
s=trim(strPhone)
return (isInteger(s) && s.length >= minDigitsInIPhoneTNumber);
}

function echeck(str) {

		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
		   alert("Invalid E-mail ID")
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		   alert("Invalid E-mail ID")
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		    alert("Invalid E-mail ID")
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		    alert("Invalid E-mail ID")
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		    alert("Invalid E-mail ID")
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		    alert("Invalid E-mail ID")
		    return false
		 }
		
		 if (str.indexOf(" ")!=-1){
		    alert("Invalid E-mail ID")
		    return false
		 }

 		 return true					
	}

function ValidateForm(){
	var Phone=document.reg.cell
	var PhoneT=document.reg.tel
	var emailID=document.reg.email
	var pass=document.reg.pass
	var cpass=document.reg.cpass
	var nick=document.reg.nick
	var cons=document.reg.cons.options[document.reg.cons.selectedIndex].value
	var ward=document.reg.ward.options[document.reg.ward.selectedIndex].value
	var voterid= document.reg.vid
	
	//alert(cpass.value);
if (nick.value==""){
		alert('Please Enter a Valid Nick Name')
		nick.focus()
		return false
	}
	if (cons.value==""){
		alert('Please choose a Constituency')
		cons.focus()
		return false
	}
	if (ward.value==""){
		alert('Please choose a Ward')
		ward.focus()
		return false
	}
	if (voterid.value=="") {
	    alert('Please Enter a Voter ID')
	    voterid.focus()
	    return false
	 }
	
	if(pass.value != cpass.value) {
	
		alert('Passwords dont match.')
		return false
	}
	
	if ((emailID.value==null)||(emailID.value=="")){
		alert("Please Enter your Email ID")
		emailID.focus()
		return false
	}
	if (echeck(emailID.value)==false){
		emailID.value=""
		emailID.focus()
		return false
	}
	
	if ((PhoneT.value==null)||(PhoneT.value=="")){
		alert('Please Enter your Telephone Number')
		PhoneT.focus()
		return false
	}
	if (checkInternationalPhoneT(PhoneT.value)==false){
		alert("Please Enter a Valid Telephone Number")
		PhoneT.value=""
		PhoneT.focus()
		return false
	}
	
	if ((Phone.value==null)||(Phone.value=="")){
		alert('Please Enter your Cell Number')
		Phone.focus()
		return false
	}
	
	
	
	if (checkInternationalPhone(Phone.value)==false){
		alert('Please Enter a Valid Cell Number')
		Phone.value=""
		Phone.focus()
		return false
	}
	

	
	
	return true
 }
</script>
 <!-- B.2 MAIN CONTENT -->
       <div class="main-content"><div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle"></h1>
		<div class="column1-unit">
		<form  name="reg" id="reg" action="/CMS/Register" method="post" onSubmit="return ValidateForm()">



        <h1>Registration</h1>
   <p> Nickname </p>
           <p>
            <label>
            <input type="text" name="nick" id="nick" />
            </label>
            </p>
 <p> Select Constituency  </p>
 <%List<Map<String, Object>> rst;%>
 <jsp:useBean id="consti" class="beans.Load_Constibean">
		<jsp:setProperty name="consti" property="*"/>
		<% rst = consti.loadConsti(); %>
		  </jsp:useBean>
           <p>
            <label>
            <select name="cons" id="cons" onchange="getWards(this)" >
            <option>&nbsp;</option>
            <%ListIterator<Map<String,Object>> litrst = rst.listIterator();  %>
			<%while(litrst.hasNext()) { Map maprst = litrst.next();%>
            <option value="<%=maprst.get("CODE").toString() %>"><%=maprst.get("CONSTI_NAME").toString() %></option>
            <%} %>
            </select>
            </label>
          
<p> Ward Number</p>
<p><label>
<select id="ward" name="ward" >
		            </select>
</label></p>
 <label> <p id="result"></p> </label>
<h1>Login Details </h1>
        <p>Voter ID : </p>
       
          <p>
            <label>
            <input type="text" name="vid" id="vid" onfocus="startRequest()" />
            </label>
          </p>
        
        <p>Password : </p>
           <p>
            <label>
            <input type="password" name="pass" id="pass" />
            </label>
          </p>
        <p>Confirm Password :</p>
  <p>
            <label>
            <input type="password" name="cpass" id="cpass" />
            </label>
          </p>    
       
            
             <p>&nbsp;</p>
       
          
          <h1>Personal Details</h1>                            
         
          <p>First Name :</p>
           <p>
            <label>
            <input type="text" name="fname" id="fname" />
            </label>
          </p>
          <p>Middle Name :</p>
           <p>
            <label>
            <input type="text" name="mname" id="mname" />
            </label>
          </p>
          <p>Last Name :</p>
           <p>
            <label>
            <input type="text" name="lname" id="lname" />
            </label>
          </p>
       
          
          <p> Date of Birth : (mm/dd/yyyy)</p>
           <p>
            <label>
          <select name="m">
            <option value="01">Jan</option>
            <option value="02">Feb</option>
            <option value="03">Mar</option>
            <option value="04">Apr</option>
            <option value="05">May</option>
            <option value="06">Jun</option>
            <option value="07">Jul</option>
            <option value="08">Aug</option>
            <option value="09">Sep</option>
            <option value="10">Oct</option>
            <option value="11">Nov</option>
            <option value="12">Dec</option>
          </select>
            </label>
            <label>
            <select name="d">
              <option value="01">1</option>
              <option value="02">2</option>
              <option value="03">3</option>
              <option value="04">4</option>
              <option value="05">5</option>
              <option value="06">6</option>
              <option value="07">7</option>
              <option value="08">8</option>
              <option value="09">9</option>
              <option value="10">10</option>
              <option value="11">11</option>
              <option value="12">12</option>
              <option value="13">13</option>
              <option value="14">14</option>
              <option value="15">15</option>
              <option value="16">16</option>
              <option value="17">17</option>
              <option value="18">18</option>
              <option value="19">19</option>
              <option value="20">20</option>
              <option value="21">21</option>
              <option value="22">22</option>
              <option value="23">23</option>
              <option value="24">24</option>
              <option value="25">25</option>
              <option value="26">26</option>
              <option value="27">27</option>
              <option value="28">28</option>
              <option value="29">29</option>
              <option value="30">30</option>
              <option value="31">31</option>
            </select>
            </label>
            <label>
         <input type="text" width="4" maxlength="4" name="y"/>
            </label>
           </p>
          
          <p>Gender </p>
             <table width="50">
                 <tr>
                   <td><label>
                     <input type="radio" name="gender" value="f" id="female" />
                     Female</label></td>
                 </tr>
                 <tr>
                   <td><label>
                     <input type="radio" name="gender" value="m" id="male" />
                     Male</label></td>
                 </tr>
               </table>
               <p>
                
    
          <h1>Contact Details</h1>                            
     
          <p>Address</p>
<p><label><textarea name="address" cols="40" rows="5"></textarea></label></p>
          <p>E-Mail :</p>
               <p>
            <label>
            <input type="text" name="email" id="email" />
            </label>
          </p>

       
          <p>Telephone no.</p>
     <p>
            <label>
            <input type="text" name="tel" id="tel" />
            </label>
          </p>
          
          <p>Cell no.</p>
     <p>
            <label>
            <input type="text" name="cell" id="cell" />
            </label>
          </p>
          <p>
          <input type="submit" value="Submit" /> &nbsp; &nbsp; &nbsp;
          <input type="reset" value="Reset" />
         </form>
		  </div>                                    
      </div>
       </div>         

        <%@ include file="/Templates/footerG.jsp" %> 
       