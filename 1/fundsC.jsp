<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerC.jsp" %>


 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">  <div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Funds</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        <p>&nbsp;</p>
        <table width="200%" border="1">
          <tr>
            <th width="55" scope="col">Fund ID</th>
            <th width="181" scope="col">Description</th>
            <th width="70" scope="col">Amount</th>
          </tr>
          <% int i=0; 
          	 long funds=0;
            ResultSet rs= (ResultSet)request.getAttribute("funds");
           	while(i<20 && rs.next()){%>
          <tr>
            <td><%=rs.getLong("FUND_ID")%></td>
            <td><a href="/CMS/Funds_view?consti=<%=consti%>&fundid=<%=rs.getLong("FUND_ID")%>"><%=rs.getString("TITLE")%></a></td>
            <td><%=rs.getLong("COST")%><%funds+=rs.getLong("COST");%></td>
          </tr>
          <%} %>
        </table>
        <p>&nbsp;</p>
        Total Funds Alloted : Rs. 2,00,00,000
        <br />
        <br />
        Total Funds Used : <%=funds%> 
        <br /><br />
		Total Funds Available :<%=(20000000 - funds) %>
		<br /><br />
		<%if(request.getAttribute("row").equals("1")){ %>
          Previous
          <%}else{ %>
		  <a href = "/CMS/Funds_prev?consti=<%=consti%>&row=<%=request.getAttribute("row")%>">Previous</a>
		  <%} %>
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  <%if(i==20 && rs.next()){ %>
		  <a href = "/CMS/Funds_nxt?consti=<%=consti%>&row=<%=request.getAttribute("row")%>">Next</a>
          <%}else{ %>
          Next
          <%} %>
        </div>
        <div class="column1-unit"></div>                                    
      </div>
       </div>         
       
       <%@ include file="/Templates/footer.jsp" %> 



