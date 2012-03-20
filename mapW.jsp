  <%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerW.jsp" %>

      <!-- B.2 MAIN CONTENT -->
       <div class="main-content"><div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
       
        <h1 class="pagetitle">Map</h1>
        <div class="column1-unit">
         <%String var = null; %>
         <%String rsl = null; %>
        <jsp:useBean id="map" class="beans.Mapbean">
		<jsp:setProperty name="map" property="*"/>
		<%map.setConsti(consti); %>
		<%rsl = map.mapQuery(); %>
		<%var = rsl; %>
		</jsp:useBean>
        <%=var %>
      
      </div>
      </div>
       </div>         
       <%@ include file="/Templates/footer.jsp" %>