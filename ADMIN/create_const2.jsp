<%@ include file="/Templates/initial_admin.jsp" %>
<%@ include file="/Templates/headerA.jsp" %>


<script type="text/javascript">
// Form Validation
   function validate_required(field)
         {
           with (field)
           {
             if (value==null||value=="")
             {return false;}
               else {return true}
            }
          }
   function validate_popup(field,alerttxt)
{
with (field)
{
if (value==null||value=="")
  {alert(alerttxt);return false;}
else {return true}
}
}


function validate_form(thisform)
   {
    with (thisform)
     {
      if (validate_required(info)==false)
       {info.value="NA";}
	 if (validate_required(mlaname)==false)
       {mlaname.value="NA";}
 if (validate_required(mcont)==false)
       {mcont.value="NA";}      
 if (validate_required(ppltn)==false)
       {ppltn.value="NA";}
 if (validate_required(sexr)==false)
       {sexr.value="NA";}
if (validate_required(lit)==false)
       {lit.value="NA";}
 if (validate_required(nos)==false)
       {nos.value="NA";}
 if (validate_required(noh)==false)
       {noh.value="NA";}
 if (validate_required(nops)==false)
       {nops.value="NA";}
 if (validate_required(nop)==false)
       {nop.value="NA";}
 if (validate_required(hlp)==false)
       {hlp.value="NA";}
 if (validate_required(hps)==false)
       {hps.value="NA";}
 if (validate_required(mhos)==false)
       {mhos.value="NA";}
 if (validate_required(rail)==false)
       {rail.value="NA";}
 if (validate_required(coff)==false)
       {coff.value="NA";}
 if (validate_required(dept)==false)
       {dept.value="NA";}
 if (validate_required(link)==false)
       {link.value="NA";}
 if (validate_required(eleoff)==false)
       {eleoff.value="NA";}
if (validate_popup(mlauname,"MLA User Name cannot be empty!")==false)
   {mlauname.focus();return false;}
if (validate_popup(mlapass,"MLA Password cannot be empty!")==false)
   {mlapass.focus();return false;}
if (validate_popup(map,"Map cannot be empty!")==false)
   {map.focus();return false;}

      }
    }

</script>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Create Constituency - Step 2 of 2</h1>
		  <div class="column1-unit">
		  
		<%String str = (String)request.getAttribute("Consti"); %>
		<br />
		<p>&nbsp;</p>
	<form method=post action=/CMS/Createconst2 onsubmit="return validate_form(this)">
		<br />
		<p>Incase a field is to be left empty it will be automatically be filled with 'NA'.<br /><br />
		General Information:
		</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name=info></textarea>
		 </label></p>
		<br />
		
		<p>
	     MLA Name:</p>
		 <p> <label>
		 <input type="text" name="mlaname"/>
		 </label></p>
		 <p>&nbsp;</p>
		 <p>
	<!-- 	 MLA Username:</p>
		 <p> <label>
		 <input type="text" name="mlauname"/>
		 </label></p>
		 <p>&nbsp;</p>
		 <p>MLA Password</p>
		 <p><input type="password" name="mlapass" /><br />
		    </p> -->
		 <p>
		MLA Contact:</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="mcont"></textarea>
		 </label></p>
		<br />  
		<br />
		<p>
		Population:
		</p>
		 <p> <label>
		 <input type="text" name="ppltn"/>
		 </label></p>
		<br />
		<p>
		Literacy Rate:
		</p>
		 <p> <label>
		 <input type="text" name="lit"/>
		 </label></p>
		<br />
		<p>
		Sex Ratio:
		</p>
		 <p> <label>
		 <input type="text" name="sexr"/>
		 </label></p>
		<br />
		<p>
		No. of Schools:
		</p>
		 <p> <label>
		 <input type="text" name="nos"/>
		 </label></p>
		<br />
		<p>
		No. of Hospitals:
		</p>
		 <p> <label>
		 <input type="text" name="noh"/>
		 </label></p>
		<br />
		<p>
		No. of Police Stations:
		</p>
		 <p> <label>
		 <input type="text" name="nops"/>
		 </label></p>
		<br />
		<p>
		No.of Parks:
		</p>
		 <p> <label>
		 <input type="text" name="nop"/>
		 </label></p>
		<br />
		<p>Help Line no:
		</p>
		 <p> <label>
		 <input type="text" name="hlp"/>
		 </label></p>
		<br />
		<p>
		Address & Telephone of Head Police Station:
		</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="hps"></textarea>
		 </label></p>
		<br />
		<p>
		Address & Telephone of Main Hospital:
		</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="mhos"></textarea>
		 </label></p>
		<br />
		<p>
		Address & Telephone of Nearest Railway Station:
</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="rail"></textarea>
		 </label></p>
		<br />
		<p>
		Collector's Office:
		</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="coff"></textarea>
		 </label></p>
		<br />
		<p>
		Departments Contact no:
		</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="dept"></textarea>
		 </label></p>
		<br />
		
		<p><a href="http://maps.google.com/maps" target="new">Click here</a> to get the longitude and longitude of this constituency.</p>
		<p>
		Map:
		</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="map"></textarea>
		 </label></p>
		<br />
		<p>
		Link to Govt. Sites:
		</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="link"></textarea>
		 </label></p>
		<br />
		<p>
		Election Office: 
		</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="eleoff"></textarea>
		 </label></p>
		<br />
		<input type=hidden value=<%=str%> name=code />
		<br />
		<p><input type="submit" value="Submit" /> </p>
</form>

		</div>
		</div>
      </div>         

      
       <%@ include file="/Templates/footerA.jsp" %>