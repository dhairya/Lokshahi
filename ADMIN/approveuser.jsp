 <%@ include file="/Templates/initial_admin.jsp" %>
<%@ include file="/Templates/headerA.jsp" %>

      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Approve New Users</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        <%List<Map<String, Object>> rs = null; %>
        <jsp:useBean id="approve" class="beans.Registerbean">
		<jsp:setProperty name="approve" property="*"/>
		<%approve.init(); %>
        <%rs = approve.execQueryApprove();
        	approve.terminate();%>
        </jsp:useBean>
         
       
        
        <% String vid=null;
        String consti_code=null;
		ListIterator<Map<String,Object>> litrreg = rs.listIterator(); 

        if(litrreg.hasNext()){%>
         <%do {
         	
        	Map<String,Object> mapreg = litrreg.next();
    	    vid=mapreg.get("VOTERID").toString();
    	    consti_code=mapreg.get("CONSTI_CODE").toString();
    	    
        %>
         <form action="/CMS/Approveuser_main" method="post">
        <table width="100%">
        <input type="hidden" value=<%=vid %> name="text" />
        <input type="hidden" value=<%=consti_code%> name="code" id="code" />
        <tr>
        <td width="60" align="left"><%=vid%></td>
        <td width="40"><%=consti_code%></td>        
        <td width="50"><input type="submit" value="ACCEPT" name="accept" /></td>
        <td  width="50"><input type="submit" value="REJECT" name="reject" /></td>
       </tr>
          <%}while (litrreg.hasNext()); %>
        </table>
        </form>
      
        <%}else{ %>
        <p>No new users to approve or reject!</p>
        <%} %>
        </div>
        <div class="column1-unit"></div>                                    
      </div>
      </div>         

 <%@ include file="/Templates/footerA.jsp" %>	