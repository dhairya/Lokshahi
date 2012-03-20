<%@ page import="java.io.*" %>

<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerC.jsp" %>

 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <%File reportFile = new File(application.getRealPath("/genreports/"));%>;
        <h1 class="pagetitle">Download Reports</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        
       <p>Departmentwise Problems Report&nbsp; &nbsp; <br/>
       <a href="<%="genreports/"+consti+"_DEPTWISE_PROBLEMS.pdf"%>" target="_blank">pdf</a>&nbsp;
        <a href="<%="genreports/"+consti+"_DEPTWISE_PROBLEMS.xls"%>" target="_blank">xls</a>&nbsp;
        <a href="<%="genreports/"+consti+"_DEPTWISE_PROBLEMS.csv"%>" target="_blank">csv</a>&nbsp;
       <a href="<%="genreports/"+consti+"_DEPTWISE_PROBLEMS.rtf"%>" target="_blank">rtf</a>&nbsp;
		<a href="<%="genreports/"+consti+"_DEPTWISE_PROBLEMS.html"%>" target="_blank">html</a>&nbsp;
		<a href="<%="genreports/"+consti+"_DEPTWISE_PROBLEMS.odf"%>" target="_blank">odf</a>

        </p>
<br /><br />
<p>Fund Utilization Report&nbsp; &nbsp; <br/>
       
        <a href="<%="genreports/"+consti+"_FUND_UTILIZATION.pdf"%>" target="_blank">pdf</a>&nbsp;
        <a href="<%="genreports/"+consti+"_FUND_UTILIZATION.xls"%>" target="_blank">xls</a>&nbsp;
        <a href="<%="genreports/"+consti+"_FUND_UTILIZATION.csv"%>" target="_blank">csv</a>&nbsp;
        <a href="<%="genreports/"+consti+"_FUND_UTILIZATION.rtf"%>" target="_blank">rtf</a>&nbsp;
		<a href="<%="genreports/"+consti+"_FUND_UTILIZATION.html"%>" target="_blank">html</a>&nbsp;
		<a href="<%="genreports/"+consti+"_FUND_UTILIZATION.odf"%>" target="_blank">odf</a>&nbsp;

       </p>
<br /><br />
<p>Polls Report&nbsp; &nbsp; <br/>
 
        <a href="<%="genreports/"+consti+"_POLLS_REPORT.pdf"%>" target="_blank">pdf</a>&nbsp;
        <a href="<%="genreports/"+consti+"_POLLS_REPORT.xls"%>" target="_blank">xls</a>&nbsp;
        <a href="<%="genreports/"+consti+"_POLLS_REPORT.csv"%>" target="_blank">csv</a>&nbsp;
        <a href="<%="genreports/"+consti+"_POLLS_REPORT.rtf"%>" target="_blank">rtf</a>&nbsp;
		<a href="<%="genreports/"+consti+"_POLLS_REPORT.html"%>" target="_blank">html</a>&nbsp;
		<a href="<%="genreports/"+consti+"_POLLS_REPORT.odf"%>" target="_blank">odf</a>

        </p>
<br /><br />
<p>User Details Report&nbsp; &nbsp; <br/>
        
        <a href="<%="genreports/"+consti+"_USER_DETAILS.pdf"%>" target="_blank">pdf</a>&nbsp;
        <a href="<%="genreports/"+consti+"_USER_DETAILS.xls"%>" target="_blank">xls</a>&nbsp;
        <a href="<%="genreports/"+consti+"_USER_DETAILS.csv"%>" target="_blank">csv</a>&nbsp;
        <a href="<%="genreports/"+consti+"_USER_DETAILS.rtf"%>" target="_blank">rtf</a>&nbsp;
		<a href="<%="genreports/"+consti+"_USER_DETAILS.html"%>" target="_blank">html</a>&nbsp;
		<a href="<%="genreports/"+consti+"_USER_DETAILS.odf"%>" target="_blank">odf</a>&nbsp;

        </p>
<br /><br />
<p>Grievances Report&nbsp; &nbsp; <br/>
       
        <a href="<%="genreports/"+consti+"_GRIEVANCE_REPORT.pdf"%>" target="_blank">pdf</a>&nbsp;
        <a href="<%="genreports/"+consti+"_GRIEVANCE_REPORT.xls"%>" target="_blank">xls</a>&nbsp;
        <a href="<%="genreports/"+consti+"_GRIEVANCE_REPORT.csv"%>" target="_blank">csv</a>&nbsp;
        <a href="<%="genreports/"+consti+"_GRIEVANCE_REPORT.rtf"%>" target="_blank">rtf</a>&nbsp;
		<a href="<%="genreports/"+consti+"_GRIEVANCE_REPORT.html"%>" target="_blank">html</a>&nbsp;
		<a href="<%="genreports/"+consti+"_GRIEVANCE_REPORT.odf"%>" target="_blank">odf</a>&nbsp;

        </p>
<br />
        </div>
        <div class="column1-unit"></div>                                    
      </div>
      </div>         

      
       <%@ include file="/Templates/footer.jsp" %>