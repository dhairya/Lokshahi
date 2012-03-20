<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<%@ page import="java.sql.ResultSet"%>
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>
<link rel="stylesheet" href="css/style.css" type="text/css" />
<script type="text/javascript" src="script.js"></script>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="3600" />
<meta name="revisit-after" content="2 days" />
<meta name="robots" content="index,follow" />
<meta name="distribution" content="global" />
<link rel="stylesheet" type="text/css" media="screen,projection,print" href="./css/layout4_setup.css" />
  <link rel="stylesheet" type="text/css" media="screen,projection,print" href="./css/layout4_text.css" />
  <link rel="icon" type="image/x-icon" href="./img/favicon.ico" />
  <link href="/CMS/rss.xml" rel="alternate" type="application/rss+xml" title="Sitewide RSS Feed" />
  <title>ppl2.0 -people + web 2.0 </title>
<style type="text/css">
<!--
.style1 {
	color: #FF8000
}

.style2 {
	color: #000000
}
-->
</style>

</head>

<!-- Global IE fix to avoid layout crash when single word size wider than column width -->
<!--[if IE]><style type="text/css"> body {word-wrap: break-word;}</style><![endif]-->

<%String consti = request.getParameter("consti"); %>
<%ResultSet rst = null; %>
<jsp:useBean id="chat" class="beans.Chat_notify">
<jsp:setProperty name="chat" property="*"/>
<%chat.setCode(consti);%>
<%rst = chat.loadChat();%>
</jsp:useBean>
<%rst.next();
  String d,t;
  d = rst.getString("LEADER_DATE");
  t = rst.getString("LEADER_TIME");
  %>

<body>
<!-- Main Page Container -->


<div class="page-container"><!-- For alternative headers START PASTE here -->

<!-- A. HEADER -->
<div class="header"><!-- A.1 HEADER TOP -->
<div class="header-top"><!-- Sitelogo and sitename --> <a
	class="sitelogo" href="#" title="Go to Start page"></a>
<div class="sitename">
<h1><a href="index.html" title="Go to Start page"><span
	class="style2">ppl</span><span class="style1">2</span>.<span
	class="style1">0</span></a></h1>
<h2><span class="style1">people</span><span class="style2">+</span><span
	class="style1">web</span><span class="style2">2</span><span
	class="style1">.</span><span class="style2">0</span></h2>
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
<div class="header-middle"><!-- Site message -->

</div>

<!-- A.3 HEADER BOTTOM -->
<div class="header-bottom"><!-- Navigation Level 2 (Drop-down menus) -->
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
		<%ResultSet rsdisp = list.loadConsti(); %>
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
			<%while(rsdisp.next()) { codedisp = rsdisp.getString("CODE");%>
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
         		 
          </ul>
        
          <!-- Navigation item -->
         
        </div>
</div>

<!-- A.4 HEADER BREADCRUMBS --> <!-- Breadcrumbs -->
<div class="header-breadcrumbs">
<ul>
	


</ul>

<!-- Search form -->
</div>
</div>

<!-- For alternative headers END PASTE here --> <!-- B. MAIN -->
<div class="main"><!-- B.1 MAIN NAVIGATION -->
<div class="main-navigation"><!-- Navigation Level 3 -->
<div class="round-border-topright"></div>
<h1 class="first">Navigation</h1>

<!-- Navigation with grid style -->
<dl class="nav3-grid">
	<dt><a href="/CMS/kbase.jsp?consti=<%=consti%>">Knowledge Base</a></dt>
	 <dt><a href="/CMS/download_reports.jsp?consti=<%=consti%>">Reports</a></dt>
	<dt><a href="/CMS/Funds_main?consti=<%=consti%>">Funds</a></dt>
	<dt><a href="/CMS/map.jsp?consti=<%=consti%>">Map</a></dt>
	<dt><a href="/CMS/chat.jsp?consti=<%=consti%>" target="new">Chat</a></dt>
</dl>


</div>

<!-- B.2 MAIN CONTENT -->
<div class="main-content">
<div id="translation"></div>
<div id="article"><!-- Pagetitle -->
<h1 class="pagetitle">Edit Profile</h1>
<!-- Content unit - One column -->
<div class="column1-unit">
<%
	ResultSet rs = (ResultSet) request.getAttribute("values");
	String email = null;
	String phone = null;
	String cell = null;
	while (rs.next()) {
		email = rs.getString("EMAIL");
		phone = rs.getString("PHONE");
		cell = rs.getString("CELL");
	}
%>
<p>
<form name="chpass" action="/CMS/Editpassword?consti=<%=consti%>" method="post"
	onsubmit="return ValidateForm()">
Change Password:
<input type="password" id="pass" name="pass" />
<br />
<br />
Confirm Password:&nbsp;
<input type="password" id="cpass" name="cpass" />
<input type="submit" value="Change Password" />
</form>

<form method="post" action="/CMS/Editdetails?consti=<%=consti%>">
<p>&nbsp;</p>
<p>Change E-mail ID: <input type="text" value="<%=email%>"
	name="email" /></p>
<p>Change Telephone no. <input type="text" name="phone"
	value="<%=phone%>" /></p>
<p>Change Cell no. <input type="text" name="cell" value="<%=cell%>" /></p>
<p><input type="submit" value="Change" /></p>
</form>
<p>&nbsp;</p>
<br />
<br />

<p>Change in Constituency / Voter ID ? <a href="/CMS/Changeid"> Click Here </a>
Warning ! your account will be deleted and you will have to go
through the process of registration again for your new Voter ID.
<p>Thank You !</p>
</div>

</div>
</div>
<!-- B.3 SUBCONTENT -->
<div class="main-subcontent"><!-- Subcontent unit -->
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
          Welcome <%=session.getAttribute("Nick") %><br /><br />
          <%if(!session.getAttribute("Type").equals("ADMIN") && !session.getAttribute("Type").equals("HEAD")){  %>
          Your home constituency is <%=session.getAttribute("Consti") %>
          <%} %><br /><br />
          <a href="/CMS/Editprofile">Edit your details</a><br /><br />
          <form method="post" action="/CMS/Logout">
          <input type="submit" value="Logout"></input>
          </form>
          <%} %>
        </div>

       <!-- Subcontent unit -->
<%if(d.equals("NA")) {%>
<div class="subcontent-unit-border-orange">
<div class="round-border-topleft"></div>
<div class="round-border-topright"></div>
<h1 class="orange">Chat Notifications</h1>
<p>Next Chat on: yet to be scheduled</p>
<p>&nbsp;</p>
<%}else{ %>
<div class="subcontent-unit-border-orange">
<div class="round-border-topleft"></div>
<div class="round-border-topright"></div>
<h1 class="orange">Chat Notifications</h1>
<p>Next Chat on: <%=d%></p>
<p>At: <%=t%></p>
<%} %>
</div>

<!-- Subcontent unit -->
<div class="subcontent-unit-border-green">
<div class="round-border-topleft"></div><div class="round-border-topright"></div>
<h1 class="green">Photo Gallery !</h1>
<p>Please <a href="/CMS/phGallery.html">click here</a> to see the photo gallery.</p>
<p>Thanks!</p>
</div>
</div>


</div>


<!-- C. FOOTER AREA -->

<script type="text/javascript">var menu=new menu.dd("menu");menu.init("menu","menuhover");</script><div class="footer">
<p>This product is owned and copyrighted by <a href="http://ppl2point0.co.nr">ppl2.0 Technologies</a> | All Rights Reserved</p>

</div>
</div>

</body>
</html>



