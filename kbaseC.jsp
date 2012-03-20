 <%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerC.jsp" %>

      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Welcome !</h1>
       
        <%List<Map<String, Object>> rs = null; %>
		<%String rsl2 = null; %>
		<%String link[] = null; %>
		
        <jsp:useBean id="kbase" class="beans.Kbasebean">
		<jsp:setProperty name="kbase" property="*"/>
		<%kbase.setConsti(consti); %>
		<%rs = kbase.kbaseQuery();%>
		<%rsl2 = kbase.kbaseConsti(); %>
		 </jsp:useBean>
		  <%String var = rs.get(0).get("LINK_GOVT_SITES").toString(); %>
		  <%link = var.split(","); %>
		  <%String codes = rs.get(0).get("CODE").toString();%>
		 <%String name = rsl2; %>
		 <%String info = rs.get(0).get("INFO").toString();%>
		 <%String mlaname = rs.get(0).get("MLANAME").toString(); %>
		 <%String mlacont = rs.get(0).get("MLACONTACT").toString();%>
		 <%String pop = rs.get(0).get("POPULATION").toString(); %>
		 <%String lrate = rs.get(0).get("LITERACY_RATE").toString();%>
		 <%String sratio = rs.get(0).get("SEX_RATIO").toString(); %>
		 <%String nos = rs.get(0).get("SCHOOL").toString();%>
		 <%String noh = rs.get(0).get("HOSPITALS").toString(); %>
		 <%String nops = rs.get(0).get("POLICE").toString();%>
		 <%String nop = rs.get(0).get("PARKS").toString(); %>
		 <%String hlp = rs.get(0).get("HELPLINE").toString();%>
		 <%String hps = rs.get(0).get("ADD_POLICE").toString(); %>
		 <%String mhos = rs.get(0).get("ADD_HOSPITALS").toString();%>
		 <%String rail = rs.get(0).get("ADD_RAILWAY").toString(); %>
		 <%String coff = rs.get(0).get("COLLECTOR_OFFICE").toString();%>
		 <%String dept = rs.get(0).get("DEPT_CONTACT").toString(); %>
		 <%String eleoff = rs.get(0).get("ELECTION_OFFICE").toString();%>
		
        <!-- Content unit - One column -->
        <div class="column1-unit">
        <p>
		<b>Constituency Name:</b> <%=name %>&nbsp;&nbsp;&nbsp;<b>Code:</b> <%=codes %>
		</p>
        <p>
		<b>General Information:</b>
		</p>
		<p> <label><%=info %></label></p>
		<br />
		<p>
		 <b>MLA Name:</b></p>
		 <p> <label><%=mlaname %></label></p>
		 <br/><p>
		<b>MLA Contact:</b></p>
		 <p> <label><%=mlacont %></label></p>
		  
		  <br/><p>
		<b>Population:</b></p>
		 <p> <label><%=pop %></label></p>
		  <br/><p>
		<b>Literacy Rate:</b></p>
		 <p> <label><%=lrate %></label></p>
		  <br/><p>
		<b>Sex Ratio:</b></p>
		 <p> <label><%=sratio %></label></p>
		 <br/><p>
		<b>No. of Schools:</b></p>
		 <p> <label><%=nos %></label></p>
		 <br/><p>
		<b>No. of Hospitals:</b></p>
		 <p> <label><%=noh %></label></p>
		 <br/><p>
		<b>No. of Police Stations:</b></p>
		 <p> <label><%=nops %></label></p>
		 <br/><p>
	<b>	No.of Parks:</b></p>
		 <p> <label><%=nop %></label></p>
		 <br/><p>
		<b>Help Line no:</b></p>
		 <p> <label><%=hlp %></label></p>
		 <br/><p>
		<b>Address & Telephone of Head Police Station:</b></p>
		 <p> <label><%=hps %></label></p>
		 <br/><p>
		<b>Address & Telephone of Main Hospital:</b></p>
		 <p> <label><%=mhos %></label></p>
		  <br/><p>
		<b>Address & Telephone of Nearest Railway Station:</b></p>
		 <p> <label><%=rail %></label></p>
		  <br/><p>
		<b>Collector's Office:</b></p>
		 <p> <label><%=coff %></label></p>
		  <br/><p>
		<b>Departments Contact no:</b></p>
		 <p> <label><%=dept %></label></p>
		  <br/><p>
		<b>Link to Govt. Sites:</b></p>
		 <p> <label>
		 <%for(int i=0;i<link.length;i++) {%>
		 <a href="<%=link[i]%>" target = "new"><%=link[i] %></a><br/><%} %>
		 </label></p>
		   <br/><p>
		<b>Election Office:</b></p>
		 <p> <label><%=eleoff %></label></p>
        </div>
        <hr class="clear-contentunit" />          

        <!-- Content unit - One column -->
        <div class="column1-unit">        
          
        </div>          
        </div>
       </div>         

              <%@ include file="/Templates/footer.jsp" %>