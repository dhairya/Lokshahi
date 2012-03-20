<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<%@page import="java.sql.ResultSet" %>
<%@ page import="java.util.*" %>

<link rel="stylesheet" href="/CMS/css/style.css" type="text/css" />
<script type="text/javascript" src="/CMS/script.js"></script>
 
 <meta http-equiv="content-type" content="text/html; charset=utf-8" />
  <meta http-equiv="cache-control" content="no-cache" />
  <meta http-equiv="expires" content="3600" />
  <meta name="revisit-after" content="2 days" />
  <meta name="robots" content="index,follow" />
  <meta name="distribution" content="global" />
<link rel="stylesheet" type="text/css" media="screen,projection,print" href="/CMS/css/layout4_setup.css" />
  <link rel="stylesheet" type="text/css" media="screen,projection,print" href="/CMS/css/layout4_text.css" />
  <link rel="icon" type="image/x-icon" href="/CMS/img/favicon.ico" />
  <link href="/CMS/rss.xml" rel="alternate" type="application/rss+xml" title="Sitewide RSS Feed" />
  <title>ppl2.0 -people + web 2.0 </title>
  <style type="text/css">
<!--
.style1 {color: #FF8000}
.style2 {color: #000000}
-->
  </style>
 </head>

<!-- Global IE fix to avoid layout crash when single word size wider than column width -->
<!--[if IE]><style type="text/css"> body {word-wrap: break-word;}</style><![endif]-->

<body>

  <!-- Main Page Container -->

 
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
         <!-- Navigation item -->
	   <div class="nav2">
	<ul class="menu" id="menu">
	
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
	<li><a href="/CMS/emp_login.jsp" class="menulink">Employment Opportunities</a></li>
	</ul>

<script type="text/javascript">
	var menu=new menu.dd("menu");
	menu.init("menu","menuhover");
</script>
  </div>
		   <!-- Navigation item -->
		
	  </div>

      <!-- A.4 HEADER BREADCRUMBS -->

      <!-- Breadcrumbs -->
      <div class="header-breadcrumbs">
       
        
       

    
      </div>
    </div>

    <!-- For alternative headers END PASTE here -->

    <!-- B. MAIN -->
    <div class="main">
 
      <!-- B.1 MAIN NAVIGATION -->
      <div class="main-navigation">

        <!-- Navigation Level 3 -->
        <div class="round-border-topright"></div>
         <h1 class="first">Features</h1>

        <!-- Navigation with grid style -->
                   
        <dl class="nav3-grid">
            <dt><a><img src="/CMS/img/forum.jpeg" height="30" width="60" />Forums&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </a></dt>
            <dt><a><img src="/CMS/img/grievance.jpeg" height="30" width="60" />Grievance Zone</a></dt>
             <dt><a><img src="/CMS/img/chat.jpeg" height="30" width="60" />Chat&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </a></dt>
                        	
             <dt><a><img src="/CMS/img/kiosk.jpeg" height="30" width="60" />Kiosk Users&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </a></dt>
              <dt><a><img src="/CMS/img/funds.jpeg" height="30" width="60" />Funds&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </a></dt>
               <dt><a><img src="/CMS/img/poll.jpeg" height="30" width="60" />Poll&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </a></dt>
                <dt><a><img src="/CMS/img/reports.jpeg" height="30" width="60" />Reports&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </a></dt>
                 <dt><a><img src="/CMS/img/kbase.jpeg" height="30" width="60" />Knowledge Base</a></dt>
                  <dt><a><img src="/CMS/img/map.jpeg" height="30" width="60" />Maps&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </a></dt>
                   <dt><a><img src="/CMS/img/intelli.jpeg" height="30" width="60" />Intelli Rating&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </a></dt>
                    <dt><a><img src="/CMS/img/sms.jpeg" height="30" width="60" />SMS Updates&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </a></dt>
                     <dt><a><img src="/CMS/img/mail.jpeg" height="30" width="60" />Mail Updates&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </a></dt>
                      <dt><a><img src="/CMS/img/phgallery.jpeg" height="30" width="60" />Photo Gallery&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </a></dt>
                       <dt><a><img src="/CMS/img/rss.jpeg" height="30" width="60" />RSS&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </a></dt>
                         	
          </dl>    

   
      </div>
 
      <!-- B.2 MAIN CONTENT -->
       <div class="main-content"><div id="translation"></div>
         <div id="article">
        <!-- Pagetitle -->
        <h1 class="pagetitle">Welcome!</h1>

        <!-- Content unit - One column -->
        <div class="column1-unit">
        <h1><center>Constituency Management System</center></h1> 
        <center>                           
        <img src="/CMS/img/egovernance.jpg"></img></center>
         <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
          <p align="center"></p><br/>
     <p>                                                                      
ppl2.0, a constituency management system, is a web based approach empowering citizens to network and communicates with their leaders and with each other on a common social platform on the issues related to their constituency.</p><br /><br />
	<p>
To exercise political liberty, citizens need basic opportunities. Given the enormous legal, political constraints and time lags, securing these opportunities themselves is difficult. The government is also paralyzed at times and is unable to reach out to all of its masses. Aimed at improving this government-citizen relationship, ppl2.0 the people’ social web is here. The purpose is to advocate a transparent, real time and accountable system using the power and ideology of social media, incorporating both citizens and their chosen officials for effective and efficient constituency management.</p> 

        </div>
        <hr class="clear-contentunit" />          

                                        
      </div>
       </div>         
      <!-- B.3 SUBCONTENT -->
      <div class="main-subcontent">

        <!-- Subcontent unit -->
        <div class="subcontent-unit-border-orange">
          <div class="round-border-topleft"></div><div class="round-border-topright"></div>
          <%if(session.getAttribute("Type")==null){ %>
          <h1 class="orange">Login</h1>
          <div class="loginform">
            <form method="post" action="/CMS/Verify_login"> 
              <p><input type="hidden" name="rememberme" value="0" /></p>
              <fieldset>
                <p><label for="username" class="top">User:</label><br />
                  <input type="text" name="username" id="username" tabindex="1" class="field" value="" /></p>
    	        <p><label for="password" class="top">Password:</label><br />
                  <input type="password" name="password" id="password" tabindex="2" class="field" value="" /></p>
    	        <p><input type="submit" name="cmdweblogin" class="button" value="LOGIN"  /></p>
	            <p><a href="/CMS/forgotpwd.jsp" id="forgotpsswd">Password forgotten?</a></p>
	          </fieldset>
            </form>
            <p><a href="/CMS/register.jsp">New Users Register Here</a></p>
          </div>
          <%}else{ %>
          <h1 class="orange">Welcome</h1>
          Welcome <%=session.getAttribute("Type") %><br /><br />
          <%if(!session.getAttribute("Type").equals("ADMIN")){  %>
          Your home constituency: <%=session.getAttribute("Consti") %>
          <%if(!(session.getAttribute("Wards")==null)){ %>
          <br /> Your home ward: <%=session.getAttribute("Wards") %>
          <%}} %><br /><br />
          <a href="/CMS/Editprofile">Edit your details</a><br /><br/>
          <form method="post" action="/CMS/Logout">
          <input type="submit" value="Logout"></input>
        </form>
          <%} %>
        </div>


       

        <!-- Subcontent unit -->
        <div class="subcontent-unit-border-orange">
          <div class="round-border-topleft"></div><div class="round-border-topright"></div>
          <h1 class="orange">Chat Notifications</h1>
     <p>Chat with your fellow citizens and your Leader. </p>

        </div>

        <!-- Subcontent unit -->
        <div class="subcontent-unit-border-green">
          <div class="round-border-topleft"></div><div class="round-border-topright"></div>
          <h1 class="green">Photo Gallery !</h1>
          <p>Enjoy pics of your constituency </p>
          <p>Thanks!</p>
        </div>
        </div>
        
      </div>
    
      
    <!-- C. FOOTER AREA -->      

    <script type="text/javascript">var menu=new menu.dd("menu");menu.init("menu","menuhover");</script><div class="footer">
      <p>This product is owned and copyrighted by <a href="http://ppl2point0.sourceforge.net">ppl2.0 Technologies</a> | All Rights Reserved</p>
      <p>Licensed under GNU GPL v3 License and the Creative Commons License v2.5 under the strict attributes of by-nc-nd. Read the license
      <a href="/CMS/license.txt">here</a></p>
    </div>      
  </div> 

</body>
</html>



