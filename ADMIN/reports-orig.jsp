 <%@ include file="/Templates/initial_admin.jsp" %>
<%@ include file="/Templates/headerA.jsp" %>


      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Generate Reports</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        <form method=post action="/CMS/Generatereports">
        <br/>
        <h1> Constituency Level </h1>
        <p>Enter Constituency Code</p>
        <input type=text name="code"/>
        
        <p>Select the report category</p>
        <select name="report1"  onchange="if (this.selectedIndex==2){this.form['pollno'].style.visibility='visible'}else {this.form['pollno'].style.visibility='hidden'};">
        <option value="dept">Departmentwise Problems Report</option>
        <option value="fund">Fund Utilization Report</option>
        <option value="poll">Polls Report</option>
        <option value="userdetails">Userdetails Report</option>
		<option value="grievance">Grievances Report</option>
		</select>
		<br /> <BR />
		Poll no. &nbsp; &nbsp;
		<input style="visibility:hidden;" type="text" name="pollno">
		<br />
		<p>Select report type</p>
        <select name="type1">
        <option value="pdf">pdf</option>
        <option value="xls">xls</option>
        <option value="csv">csv</option>
        <option value="rtf">rtf</option>
		<option value="html">html</option>
		<option value="odf">odf</option>

        </select>
        <br/><br/>
        
        	<input type="submit" value="Generate Report" name="generate" />
           </form>
		<hr/>
		<br/>
		<form method=post action="/CMS/Generatereports2">
		<h1> State Level </h1>
        <p>Select the report category</p>
        <select name="report2">
        <option value="fundstate">Fund Utilization Report</option>
        <option value="userdetailstate">Userdetails Report</option>
		</select>
		<br></br>
		<p>Select report type</p>
        <select name="type2">
        <option value="pdf">pdf</option>
        <option value="xls">xls</option>
        <option value="csv">csv</option>
        <option value="rtf">rtf</option>
		<option value="html">html</option>
		<option value="odf">odf</option>

        </select>
        <br/><br/>
        <input type="submit" value="Generate Report" name="generate" />
           </form>
		
        <h1>&nbsp;</h1>
        <p>&nbsp;</p>
        </div>
        <div class="column1-unit"></div>                                    
      </div>
      </div>         

 <%@ include file="/Templates/footerA.jsp" %>

