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
        <h1 class="pagetitle">Edit Constituency - Step 2 of 2</h1>
		  <div class="column1-unit">
	<% String str = (String)request.getAttribute("consti");
	   ResultSet rslt = (ResultSet)request.getAttribute("kbase");
	   //ResultSet rslt1 = (ResultSet)request.getAttribute("mlainfo");
	  
	   rslt.next();
	   //rs1.next();
	   //rs2.next();
	   String info = rslt.getString("INFO");
	   String mlaname = rslt.getString("MLANAME");
	   String mlacont = rslt.getString("MLACONTACT");
	   String pop = rslt.getString("POPULATION");
	   String lit = rslt.getString("LITERACY_RATE");
	   String sex = rslt.getString("SEX_RATIO");
	   String nos = rslt.getString("SCHOOL");
	   String noh = rslt.getString("HOSPITALS");
	   String nops = rslt.getString("POLICE");
	   String nop = rslt.getString("PARKS");
	   String hlp = rslt.getString("HELPLINE");
	   String hps = rslt.getString("ADD_POLICE");
	   String mhos = rslt.getString("ADD_HOSPITALS");
	   String rail = rslt.getString("ADD_RAILWAY");
	   String coff = rslt.getString("COLLECTOR_OFFICE");
	   String dept = rslt.getString("DEPT_CONTACT");
	   String map = rslt.getString("MAP");
	   String link = rslt.getString("LINK_GOVT_SITES");
	   String eleoff = rslt.getString("ELECTION_OFFICE");
	   //String muname = rs1.getString("VOTER_ID");
	   //String mpwd = rs1.getString("PASSWORD");
	   //String kname = rs2.getString("VOTER_ID");
	   //String kpwd = rs2.getString("PASSWORD");
	   %>
	   <form method="post" action="/CMS/Editconst2" onsubmit="return validate_form(this)">
		  <p><br />
		</p>
		<input type="hidden" value="<%=str%>" name="code" />
		 <p>
		Incase a field is to be left empty it will be automatically be filled with 'NA'.<br/><br/>
		General Info:
		</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="info"><%=info%></textarea>
		 </label></p>
		<br />
		<p>
		 MLA Name:</p>
		 <p> <label>
		 <input type="text" name="mlaname" value="<%=mlaname%>">
		 </label></p>
		 <p>&nbsp;</p>
		 
		 <p>
		MLA Contact:</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="mlacont"><%=mlacont%></textarea>
		 </label></p>
		<br />
		
		<br />
		<p>
		Population:
		</p>
		 <p> <label>
		 <input type="text" name="ppltn" value=<%=pop%>>
		 </label></p>
		<br />
		<p>
		Literacy Rate:
		</p>
		 <p> <label>
		 <input type="text" name="lit" value="<%=lit%>">
		 </label></p>
		<br />
		<p>
		Sex Ratio:
		</p>
		 <p> <label>
		 <input type="text" name="sexr" value="<%=sex %>">
		 </label></p>
		<br />
		<p>
		No. of Schools
		</p>
		 <p> <label>
		 <input type="text" name="nos" value=<%=nos %>>
		 </label></p>
		<br />
		<p>
		No. of Hospitals:
		</p>
		 <p> <label>
		 <input type="text" name="noh" value=<%=noh %>>
		 </label></p>
		<br />
		<p>
		No. of Police Stations:
		</p>
		 <p> <label>
		 <input type="text" name="nops" value=<%=nops %> >
		 </label></p>
		<br />
		<p>
		No.of Parks:
		</p>
		 <p> <label>
		 <input type="text" name="nop" value=<%=nop %>>
		 </label></p>
		<br />
		<p>Help Line no.
		</p>
		 <p> <label>
		 <input type="text" name="hlp" value="<%=hlp %>">
		 </label></p>
		<br />
		<p>
		Address & Telephone of Head Police Station:
		</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="hps"><%=hps%></textarea>
		 </label></p>
		<br />
		<p>
		Address & Telephone of Main Hospital:
		</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="mhos"><%=mhos %></textarea>
		 </label></p>
		<br />
		<p>
		Address & Telephone of Nearest Railway Station:
</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="rail"><%=rail%></textarea>
		 </label></p>
		<br />
		<p>
		Collector's Office:
		</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="coff"><%=coff%></textarea>
		 </label></p>
		<br />
		<p>
		Departments Contact no.
		</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="dept"><%=dept%></textarea>
		 </label></p>
		<br />
		<p>
		Map:
		</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="map"><%=map%></textarea>
		 </label></p>
		<br />
		<p>
		Link to Govt. Sites
		</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="link"><%= link%></textarea>
		 </label></p>
		<br />
		<p>
		Election Office: 
		</p>
		 <p> <label>
		 <textarea rows="6" cols="50" name="eleoff"><%=eleoff%></textarea>
		 </label></p>
		<br/>
		<p><input type="submit" value="Submit" /> </p>
</form>

		</div>
		</div>
      </div>         

      
       <%@ include file="/Templates/footerA.jsp" %>