<!-- Global IE fix to avoid layout crash when single word size wider than column width -->
<!--[if IE]><style type="text/css"> body {word-wrap: break-word;}</style><![endif]-->

<body>
  <!-- Main Page Container -->
<%String consti = (String)request.getParameter("consti"); %>
 
  <div class="page-container">

   <!-- For alternative headers START PASTE here -->

    <!-- A. HEADER -->      
    <div class="header">
      
      <!-- A.1 HEADER TOP -->
      <div class="header-top">
        
        <!-- Sitelogo and sitename -->
        <a class="sitelogo" href="#" title="Go to Start page"></a>
        <div class="sitename">
          <h1><a href="index.html" title="Go to Start page"><span class="style2">ppl</span><span class="style1">2</span>.<span class="style1">0</span></a></h1>
          <h2><span class="style1">people</span><span class="style2">+</span><span class="style1">web</span><span class="style2">2</span><span class="style1">.</span><span class="style2">0</span></h2>
        </div>
    
        <!-- Navigation Level 0 -->
       
        <!-- Navigation Level 1 -->
        <div class="nav1">
          <ul>
            <li><a href="/CMS/about.jsp" title="Get to know who we are">About</a></li>
            <li><a href="/CMS/contact.jsp" title="Get in touch with us">Contact</a></li>																		
            <li><a href="/CMS/rss.xml" title="Subscribe to RSS">RSS</a></li>
          </ul>
        </div>              
      </div>
      
      <!-- A.2 HEADER MIDDLE -->
      <div class="header-middle">    
   
       
      </div>
      
      <!-- A.3 HEADER BOTTOM -->
      <div class="header-bottom">
      
        <!-- Navigation Level 2 (Drop-down menus) -->
        
       <div class="nav2">
	<ul class="menu" id="menu">
	
	        <!-- Navigation item -->
			<li><a href="/CMS/index.jsp" class="menulink">ppl2.0</a></li>
            <li><a href="/CMS/ADMIN/cpanel.jsp" class="menulink">Home</a></li>
          
  <!-- Navigation item -->
         <li><a href="/CMS/ADMIN/cpanel.jsp" class="menulink">Constituencies</a>
	 <jsp:useBean id="list" class="beans.Load_Constibean">
		<jsp:setProperty name="list" property="*"/>
		<%List<Map<String, Object>> rsdisp = list.loadConsti(); %>
              <%int idisp = list.count(); %>
			  <%int lendisp[]=new int[idisp];%>
			  <%list.loadLength(lendisp,idisp); %>
			  <%int tot_lendisp=0;
			  for(int jdisp=0;jdisp<idisp;jdisp++)
			  {
				  tot_lendisp+=lendisp[jdisp];
			  }
			  %>
              <%String wardsdisp[] = new String[tot_lendisp];%>
              <%list.loadWards(wardsdisp,idisp); %>
              <%String codedisp = null, warddisp=null;%>
              <%int jdisp=0,kdisp=0; %>
		<ul>
		<%ListIterator<Map<String,Object>> litrdisp = rsdisp.listIterator();  %>
			<%while(litrdisp.hasNext()) { codedisp = litrdisp.next().get("CODE").toString();%>
			<li><a href="/CMS/Home_load?consti=<%=codedisp%>" class="sub"><%=codedisp %></a>
					<ul>
					<%for(idisp=0;idisp<lendisp[jdisp];idisp++,kdisp++) {%>
						<%warddisp=wardsdisp[kdisp];%>
						<li><a href="/CMS/Home_load?consti=<%=codedisp%>&ward=<%=warddisp%>"><%=warddisp%></a></li>
					<%} %>
					<%jdisp++;%>
					</ul>
				</li>	
            <%} %>    		
		</ul>
		</jsp:useBean>  
	</li>

	        <li><a href="/CMS/ADMIN/edit_const.jsp"  class="menulink">Edit Constituencies<!--[if IE 7]><!--></a><!--<![endif]-->
              <!--[if lte IE 6]><table><tr><td><![endif]-->
                
              <!--[if lte IE 6]></td></tr></table></a><![endif]-->
            </li>
          
            <li><a href="/CMS/ADMIN/create_const.jsp" class="menulink">Add New Constituency</a></li>
            <li><a href="/CMS/ADMIN/create_ward.jsp" class="menulink">Add New Ward</a></li>
            <li><a href="/CMS/ADMIN/approveuser.jsp" class="menulink">Approve Users</a></li>
          
         
          <!-- Navigation item -->
         
        </div>
	  </div>

      <!-- A.4 HEADER BREADCRUMBS -->

      <!-- Breadcrumbs -->
      <div class="header-breadcrumbs">
       

        <!-- Search form -->                  
             </div>
    </div>

    <!-- For alternative headers END PASTE here -->

    <!-- B. MAIN -->
    <div class="main">
 
      <!-- B.1 MAIN NAVIGATION -->
      <div class="main-navigation">

        <!-- Navigation Level 3 -->
        <div class="round-border-topright"></div>
         <h1 class="first">Navigation</h1>

         <!-- Navigation with grid style -->
        <dl class="nav3-grid">
        <dt>&nbsp;</dt>
          <dt><a href="/CMS/ADMIN/reports.jsp">Generate Reports</a></dt>
            <dt><a href="/CMS/Specialuser1">Add Special User</a></dt>
             <dt><a href="/CMS/Add_category1">Add Category</a></dt>
              <dt><a href="/CMS/ADMIN/delUsers.jsp">Edit/Delete Users</a></dt>
             <dt><a href="/CMS/GenerateRSS">Generate RSS</a></dt>
            <dt>&nbsp;</dt>
           		
        </dl>                     

       
      
      </div>
