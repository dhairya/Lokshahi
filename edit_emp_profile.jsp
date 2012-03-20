 <%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerW.jsp" %>

<!-- B.2 MAIN CONTENT -->
<div class="main-content">
<div id="translation"></div>
<div id="article"><!-- Pagetitle -->
<h1 class="pagetitle">Edit Classifieds Details</h1>

<!-- Content unit - One column -->
	<div class="column1-unit">

<%  //=request.getAttribute("flag")  %>
<%  //int flag=(int)request.getAttribute("flag"); %>
<%  //if (flag==1) { %> 
<!-- form display because flag is set i.e. details not entered

-->

<%	String qual= (String)request.getAttribute("qual");
	String skills= (String)request.getAttribute("skills");
	String exp=(String)request.getAttribute("exp");
	if(qual==null){
		qual = "0";
	}
	
	%>
<form  name="edit_emp" id="edit_emp" action="/CMS/Emp_details2" method="post" onSubmit="return ValidateForm()">

<p>Highest Qualification</p>
<label>
<select name="highest_qual">   <!--display qual value obtained from the servlet  -->
	<option value="0" <%if(qual.equals("0")){ %> selected="true" <%}%> >None</option>
	<option value="1"<%if(qual.equals("1")){ %> selected="true" <%}%> >Primary Education</option>
	<option value="2"<%if(qual.equals("2")){ %> selected="true" <%}%> >Secondary Education</option>
	<option value="3"<%if(qual.equals("3")){ %> selected="true" <%}%> >Matriculation-X Pass</option>
	<option value="4"<%if(qual.equals("4")){ %> selected="true" <%}%> >XII Pass</option>
	<option value="5"<%if(qual.equals("5")){ %> selected="true" <%}%> >Graduation</option>
	<option value="6"<%if(qual.equals("6")){ %> selected="true" <%}%> >Post Graduation</option>
</select></label>

<p> Skills </p>
<p>
  <label>
	  <textarea name="skills" cols="40" rows="5" value="<%=skills%>"></textarea>
  </label>
		</p>
	
<p>Experience</p>
<p>
	<label>
		<textarea name="experience" rows="5" cols="40" value="<%=exp%>"></textarea>	
	</label>
		</p>


<p>
	<input type="submit" value="Submit" ></input>&nbsp; &nbsp; &nbsp;
	<input type="reset" value="Reset"></input>
</p>
</form>


<%    // } else  { %>

<!-- -display edit button with action to the same servlet as above -->

<%   // } %>
</div>
</div>
</div>


<%@ include file="/Templates/footer.jsp" %>