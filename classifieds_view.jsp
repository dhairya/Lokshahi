<%@ include file="/Templates/initial.jsp" %>
<%@ include file="/Templates/headerG.jsp" %>
 
      <!-- B.2 MAIN CONTENT -->
      <div class="main-content">
         <div id="article">
        <!-- Pagetitle -->
        <% ResultSet rs1= (ResultSet)request.getAttribute("details");
        rs1.next();
        String fname=(String) rs1.getString("FNAME");
		String mname=(String) rs1.getString("MNAME");
		String lname=(String) rs1.getString("LNAME");
		String name= fname +" "+mname+ " "+lname;
		%>
          
        <h1 class="pagetitle"> <%=name %></h1>
                       		
    <br />
        
        <!-- Content unit - One column -->
        <div class="column1-unit">
        
        <%
                
        String ward=(String) request.getParameter("ward");
		String qual=(String) rs1.getString("QUALIFICATION");
		String skills=(String) rs1.getString("SKILLS");
		String exp=(String) rs1.getString("EXPERIENCE");
		String cell=(String) rs1.getString("CELL");
		String email=(String) rs1.getString("EMAIL");
		String add=(String) rs1.getString("ADDRESS");
		
		if(qual.equals("1"))
       		qual = "None";
       if(qual.equals("2"))
    		qual = "Primary Education";
       if(qual.equals("3"))
    		qual = "Secondary Education";
       if(qual.equals("4"))
    		qual = "Matriculation (X pass)";
       if(qual.equals("5"))
    		qual = "XII Pass";
       if(qual.equals("6"))
    		qual = "Graduation";
       if(qual.equals("7"))
    	    qual = "Post Graduation";
             %>
         
        Highest Qualification:<%=qual %><br />
        Skills:<%=skills %><br />
        Experience:<%=exp %><br />    
        Email ID:<%=email %><br />
        Phone no:<%=cell %><br />
        Address:<%=add %><br />
        
        <p></p>
        
        <a href="/CMS/View_classifieds2?ward1=<%=ward %>">Go Back</a>
                                 
        </div>          
        <hr class="clear-contentunit" />                             
                                              
      </div>
       </div>         

       <%@ include file="/Templates/footerE.jsp" %> 