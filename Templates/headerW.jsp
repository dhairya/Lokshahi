<!-- Global IE fix to avoid layout crash when single word size wider than column width -->
<!--[if IE]><style type="text/css"> body {word-wrap: break-word;}</style><![endif]-->

<body>
<%String consti = request.getParameter("consti"); 
String ward = request.getParameter("ward");%> 
<%List<Map<String,Object>> rs_chat = null; %>

<jsp:useBean id="chat" class="beans.Chat_notify">
<jsp:setProperty name="chat" property="*"/>
<%chat.setCode(consti);%>

<% rs_chat = chat.loadChat();%>
</jsp:useBean>
<% String d,t;
  d = rs_chat.get(0).get("LEADER_DATE").toString();
  t = rs_chat.get(0).get("LEADER_TIME").toString();
  %>
  <!-- Main Page Container -->
  <div id="translation"></div>
 
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
          <%if(session.getAttribute("Consti")==null){%>
          <%if(session.getAttribute("Type")==null){ %>
          
            <li><a href="/CMS/index.jsp" class="menulink">ppl2.0</a></li>
          
          <%}else if(session.getAttribute("Type").equals("ADMIN")){ %>
          
            <li><a href="/CMS/index.jsp" class="menulink">ppl2.0</a></li>
          
		  <li><a href="/CMS/ADMIN/cpanel.jsp" class="menulink">Admin Panel</a></li>
          
          <%}}else if(session.getAttribute("User").toString().startsWith("C")){ %>
          
            <li><a href="/CMS/index.jsp" class="menulink">ppl2.0</a></li>
          
          
            <li><a href="/CMS/Home_load?consti=<%=session.getAttribute("Consti")%>"  class="menulink">Home</a></li>
          
         <%}else{ %>
         
            <li><a href="/CMS/index.jsp" class="menulink">ppl2.0</a></li>
            <li><a class="menulink" href="/CMS/Home_load?consti=<%=session.getAttribute("Consti")%>&ward=<%=session.getAttribute("Wards")%>">Home</a></li>
          <%}%>
          
          <!-- Navigation item -->
         <li><a href="/CMS/index.jsp" class="menulink">Constituencies</a>
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
          <!-- Navigation item -->
         

		 
            <li><a href="/CMS/Grievance_main?consti=<%=consti%>&ward=<%=ward%>"  class="menulink">Grievance Zone</a></li>
            <li><a href="/CMS/Forum_main?consti=<%=consti%>&ward=<%=ward%>"  class="menulink">Forum</a></li>
            <li><a href="/CMS/Poll_main?consti=<%=consti%>&ward=<%=ward%>" class="menulink">Poll</a></li>
          </ul>
        
          <!-- Navigation item -->
         
        </div>  
  </div>

      <!-- A.4 HEADER BREADCRUMBS -->

      <!-- Breadcrumbs -->
      <div class="header-breadcrumbs">
        <ul>
      
        
        </ul>

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
         <dt><a href="/CMS/kbaseW.jsp?consti=<%=consti%>&ward=<%=ward%>">Knowledge Base</a></dt>
          <dt><a href="/CMS/download_reportsW.jsp?consti=<%=consti%>&ward=<%=ward%>">Reports</a></dt>
            <dt><a href="/CMS/Funds_main?consti=<%=consti%>&ward=<%=ward%>">Funds</a></dt>
            <dt><a href="/CMS/mapW.jsp?consti=<%=consti%>&ward=<%=ward%>">Map</a></dt>
            <%if(consti.equals((String)session.getAttribute("Consti")) && session.getAttribute("Access").toString().charAt(4)!='1') {%>
            <dt><a href="/CMS/chatW.jsp?consti=<%=consti%>&ward=<%=ward%>" target="new">Chat</a></dt>
           <%}%>	
           <%if(consti.equals((String)session.getAttribute("Consti")) && session.getAttribute("Access").toString().charAt(4)=='3') {%>
           <dt><a href="/CMS/chat_notifyW.jsp?consti=<%=consti %>&ward=<%=ward%>">Chat Notification</a></dt>
           <%} %>
           <%if(consti.equals((String)session.getAttribute("Consti")) && (session.getAttribute("Wards").toString().contains(ward) || session.getAttribute("Wards").toString().equalsIgnoreCase("ALL") ) && session.getAttribute("Access").toString().charAt(7)=='2'){ %>
           <dt><a href="/CMS/Appr_Grievance_main?consti=<%=consti%>&ward=<%=ward%>">Approve Grievances</a></dt>
           <%} %>
           <%if(consti.equals((String)session.getAttribute("Consti")) && ward.equals((String)session.getAttribute("Wards")) && session.getAttribute("Access").toString().charAt(5)=='2'){ %>
           <dt><a href="/CMS/Emp_details1?consti=<%=consti%>&ward=<%=ward%>">Looking for Employment?</a></dt><%} %> 
          </dl>                                  

        
      
      </div>