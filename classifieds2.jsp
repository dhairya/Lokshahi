 <%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerG.jsp" %>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Classifieds</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        
        
        <p>&nbsp;</p>
          <h2 align="center"> </h2>
          <table width="100%" border="1">
            <tr>
              <th width="50" scope="col">Name</th>
              <th width="50" scope="col">Highest Qualification</th>
                            
            </tr>
        
        <% 
        	String consti=(String)session.getAttribute("Consti");
        	int i=0; 
            ResultSet rs1= (ResultSet)request.getAttribute("list");
            String ward=(String)request.getParameter("ward");
            String qual = null;
         	while(i<20 && rs1.next()){%>
            <tr>
              <td><a href="/CMS/View_classifieds3?nname=<%=rs1.getString("NICKNAME")%>&ward=<%=ward%>"> <%=rs1.getString("FNAME") +" "+ rs1.getString("LNAME")%></a></td>
               <%
               qual = rs1.getString("QUALIFICATION");
               if(qual.equals("0"))
               		qual = "None";
               if(qual.equals("1"))
            		qual = "Primary Education";
               if(qual.equals("2"))
            		qual = "Secondary Education";
               if(qual.equals("3"))
            		qual = "Matriculation (X pass)";
               if(qual.equals("4"))
            		qual = "XII Pass";
               if(qual.equals("5"))
            		qual = "Graduation";
               if(qual.equals("6"))
            	    qual = "Post Graduation";

            	%>
              <td> <%=qual %></td>
              </tr>
            <% i++;} %>
          </table>
          
          <p>&nbsp;</p>
          <p>&nbsp;</p>
          <p>&nbsp;  </p>
          
          <%if(request.getAttribute("row").equals("1")){ %>
          <p>&nbsp;</p>
          <%}else{ %>
		  <h2><a href = "/CMS/Classifieds_prev?row=<%=request.getAttribute("row")%>&ward=<%=ward%>">Previous</a></h2>
		  <%} %>
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  
		  <h2><a href = "/CMS/classifieds1.jsp">Select Ward</a></h2>
		  
		  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  <%if(i==20 && rs1.next()){ %>
		  <h2><a href = "/CMS/Classifieds_next?row=<%=request.getAttribute("row")%>&ward=<%=ward%>">Next</a></h2>
          <%}else{ %>
         <p>&nbsp;</p>
          <%} %>	
          
          
        </div>          
        <hr class="clear-contentunit" />      
        
        <!-- Content unit - One column -->
        <div class="column1-unit">
         </div>                                    
      </div>
       </div>         

              <%@ include file="/Templates/footerE.jsp" %> 