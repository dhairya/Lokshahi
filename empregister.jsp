 <%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerG.jsp" %>
        
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

/*function startRequest() //for checking constituency
{
  createXmlHttpRequest();

  var nick=document.reg.nick.value;
  var cons=document.reg.cons.value;
  xmlHttp.open("GET","/CMS/Chkuser?nick="+nick+"&cons="+cons ,true)
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
              document.getElementById("cons").value="";
              document.getElementById("nick").value="";
              
            }
              
                 if (error == 2 ) 
            {
             
              document.getElementById("nick").value="";
              document.getElementById("cons").value="";
            }     
            
            }
        else
        {
           alert("Error loading page"+ xmlHttp.status +
":"+xmlHttp.statusText);
        }
    }
}
    */

 
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

/*function echeck(str) {

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
	}   */

function ValidateForm(){
	var Phone=document.reg.cell_no
	var PhoneT=document.reg.contact_tel
	var userid=document.reg.userid
	var pass=document.reg.pass
	var cpass=document.reg.cpass
	var contact_name=document.reg.contact_name
	var code=document.reg.code
	var vpno= document.reg.vpno
	var owner_name=document.reg.owner_name
	var owner_add=document.reg.owner_add
	//var emailID=document.reg.email
	
	
	//alert(cpass.value);
	if (contact_name.value==""){
		alert('Please Enter a Valid Contact Name')
		contact_name.focus()
		return false
	} 
	
	if (owner_name.value==""){
		alert('Please Enter a Valid Name of the Owner')
		owner_name.focus()
		return false
	} 
	
	if (owner_add.value==""){
		alert('Please Enter a Valid Address of the Owner')
		owner_add.focus()
		return false
	} 
	if (code.value==""){
		alert('Please Enter a Valid Constituency Code')
		code.focus()
		return false
	}
	if (vpno.value=="") {
	    alert('Please Enter a Voter ID / PAN Card no.')
	    vpno.focus()
	    return false
	 }
	
	if (userid.value=="") {
	    alert('Please Enter a User ID no.')
	    vpno.focus()
	    return false
	 }
	 
	if(pass.value != cpass.value) {
	
		alert('Passwords dont match.')
		return false
	}
	
/*	if ((emailID.value==null)||(emailID.value=="")){
		alert("Please Enter your Email ID")
		emailID.focus()
		return false
	}
	if (echeck(emailID.value)==false){
		emailID.value=""
		emailID.focus()
		return false
	}      */
	
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
        <h1 class="pagetitle">Employer's Profile</h1>
		<div class="column1-unit">
		<form  name="empreg" id="empreg" action="/CMS/Emp_register" method="post" onSubmit="return ValidateForm()">



        

			<h2>Login Details </h2> 
			
        <p>User ID : </p>
       
          <p>
            <label>
            <input type="text" name="userid" id="userid" />
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
       
          <h2>Company Details</h2>
		  
		  <p>Name of the Company :</p>
		  <p>
		  <label>
            <input type="text" name="comp_name" id="comp_name" />
            </label>
         </p>
		  <p>Address of the Company</p>
		  <p>
		  <label>
		  <textarea name="comp_add" cols="40" rows="5"></textarea>
			</label>
		</p>
		  <p>Company Registration Number:</p>
		  <p>
		  <label>
		  <input type="text" name="comp_regno" id="comp_regno" />
		  </label>
		 </p>
		 <p>Industry</p>
		 <select name="comp_industry" size="1">
			<option value="000" selected>-</option>
			<option value="A&amp;M">Advertising &amp; Marketing &amp; Promotion / PR</option>
			<option value="A&amp;A">Aerospace &amp; Aviation</option>
			<option value="AGR">Agricultural</option>
			<option value="APP">Apparel</option>
			<option value="A&amp;D">Arts &amp; Design</option>
			<option value="AUT">Automotive / Automobile / Vehicle</option>
			<option value="B&amp;F">Banking &amp; Financial Services</option>
			<option value="CRG">Cargo &amp; Freight Services</option>
			<option value="CHE">Chemical / Biochemical</option>
			<option value="CHW">Computer / Information Technology (Hardware)</option>
			<option value="COM">Computer / Information Technology (Software)</option>
			<option value="CON">Construction/Building</option>
			<option value="COS">Consulting (Business &amp; Management)</option>
			<option value="COT">Consulting (Science, Engineering &amp; Technical)</option>
			<option value="CSM">Consumer Products</option>
			<option value="EDU">Education</option>
			<option value="EEE">Electrical &amp; Electronics</option>
			<option value="ENT">Entertainment / Media</option>
			<option value="ENV">Environment / Health / Safety</option>
			<option value="FOO">Food Service / Catering / Restaurant</option>
			<option value="GTR">General Trading</option>
			<option value="GOV">Government</option>
			<option value="GRO">Grooming &amp; Deportment</option>
			<option value="HEA">Healthcare / Medical</option>
			<option value="HEI">Heavy Industrial / Machinery / Equipment</option>
			<option value="HOT">Hotel / Hospitality</option>
			<option value="HRS">Human Resources Management / Consulting</option>
			<option value="INS">Insurance</option>
			<option value="JOU">Journalism</option>
			<option value="LAW">Law / Legal</option>
			<option value="LIB">Library/Museum</option>
			<option value="MAN">Management</option>
			<option value="MNF">Manufacturing</option>
			<option value="MAR">Marine / Aquaculture</option>
			<option value="MIN">Mining</option>
			<option value="NPO">Non Profit Organisation</option>
			<option value="OIL">Oil / Gas / Petroleum</option>
			<option value="OTH">Others</option>
			<option value="PHA">Pharmaceutical</option>
			<option value="PLA">Plantations</option>
			<option value="POL">Polymer / Plastic / Rubber</option>
			<option value="PRI">Printing</option>
			<option value="PRO">Property / Real Estate</option>
			<option value="PUB">Publishing</option>
			<option value="R&amp;D">R&amp;D</option>
			<option value="SAL">Sales / Business / Trade / Retail / Merchandise</option>
			<option value="SCI">Science &amp; Technology</option>
			<option value="SHI">Shipping</option>
			<option value="SPO">Sports</option>
			<option value="STO">Stockbroking / Securities</option>
			<option value="TEL">Telecommunication</option>
			<option value="TEX">Textiles / Garment</option>
			<option value="TOB">Tobacco</option>
			<option value="TRA">Transportation Services</option>
			<option value="TRV">Travel/Tourism</option>
			<option value="UTI">Utilities</option>
			<option value="WOO">Wood &amp; Fibre</option>  
		</select>
		<p>
		  <p>Telephone no.</p>
     <p>
            <label>
            <input type="text" name="tel" id="tel" />
            </label>
          </p>
		  <p>E-Mail :</p>
               <p>
            <label>
            <input type="text" name="email" id="email" />
            </label>
          </p>
		  <p>Website:</p>
               <p>
            <label>
            <input type="text" name="website" id="website" />
            </label>
          </p>
		 <p> Constituency Code </p>
           <p>
            <label>
            <input type="text" name="code" id="code" />
            </label> 
			<p>
			
		 <h2>Contact Person Details</h2>
		 
		 <p>Name:</p>
           <p>
            <label>
            <input type="text" name="contact_name" id="contact_name" />
            </label>
          </p>
		 <p>Telephone no.:</p>
     <p>
            <label>
            <input type="text" name="contact_tel" id="contact_tel" />
            </label>
          </p>
		  <p>
		  
		  <h2>Personal Details</h2>                            
         
          <p>Name of the Owner:</p>
           <p>
            <label>
            <input type="text" name="owner_name" id="owner_name" />
            </label>
          </p>
         <p>Address of the Owner</p>
		  <p>
		  <label>
		  <textarea name="owner_add" cols="40" rows="5"></textarea>
			</label> 
         <p>Voter ID / PAN Card no. : </p>
           <p>
            <label>
            <input type="text" name="vpno" id="vpno" />
            </label>
          </p>
         <p>Mobile no.</p>
     <p>
            <label>
            <input type="text" name="cell_no" id="cell_no" />
            </label>
          </p>
          
          <p>
          <input type="submit" value="Submit" /> &nbsp; &nbsp; &nbsp;
          <input type="reset" value="Reset" />
         </form>
		  </div>                                    
      </div>
       </div>         

       
       <%@ include file="/Templates/footeronly.jsp" %>