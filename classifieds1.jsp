<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerG.jsp" %>
  

<script type="text/javascript">
function getward()
{
	document.wardlist.submit();
	return false;
}
</script>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Classifieds</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        <% ResultSet rs1=(ResultSet) request.getAttribute("ward_list"); %>
        
        <form name="wardlist" id= "wardlist" action="/CMS/View_classifieds2" method="post">
        <p>Choose the ward:</p>
        <select name="ward_list" onchange="getward()">
        <option>&nbsp;</option>
        <%while (rs1.next()){
        	String ward= (String)rs1.getString("NUMBER"); %>
       	<option value="<%=ward %>"> <%=ward %> </option>        	
       <%} %>  
        </select>
        </form>
        </div>          
        <hr class="clear-contentunit" />      
        
        <!-- Content unit - One column -->
        <div class="column1-unit">
         </div>                                    
      </div>
       </div>         

       <%@ include file="/Templates/footerE.jsp" %> 
