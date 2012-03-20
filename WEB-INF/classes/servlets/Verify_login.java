package servlets;
import beans.*;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: Verify_login
 *
 */
 public class Verify_login extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Verify_login() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String username=(String) request.getParameter("username");
		String password=(String) request.getParameter("password");
		PrintWriter out = response.getWriter();

		Verify_loginbean login=null;
		String init_vals[] = new String[4];
		String s=null;
		String chck = null;
		
		try {
			login = new Verify_loginbean();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		try {
			login.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		username=username.toUpperCase();
		login.setPasswd(password);
		
		
		if(username.startsWith("C"))
		{
			s=username.substring(1, 4);
			s=s.toUpperCase();
			login.setvoterid(username);
			login.settable_name(s);
			try {
				chck=login.execSpeciallogin(init_vals);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				out.println(e);
			}
			
		}
		else if(username.startsWith("W"))
		{
			s=username.substring(1, 4)+"_"+username.substring(4, 7);
			s=s.toUpperCase();
			login.setvoterid(username);
			login.settable_name(s);
			try {
				chck=login.execSpeciallogin(init_vals);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				out.println(e);
			}
		}
		else if(username.equalsIgnoreCase("ADMIN"))
		{
			
			login.setvoterid(username);
			try {
				chck=login.execAdminlogin();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				out.println(e);
			}
		}
		else{
			login.setvoterid(username);
		
			try {
				chck=login.execQueryLogin(init_vals);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				
			}
		}

		try {
			login.terminate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		if( chck.equals("wrong"))
		{
			request.setAttribute("msg", "Wrong password or id");
			RequestDispatcher view = request.getRequestDispatcher("./msghandler.jsp");
			view.forward(request, response);
//			String url = response.encodeRedirectURL("./msghandler.jsp?msg=Wrong ID or Password");
//			response.sendRedirect("./msghandler.jsp?msg=Wrong ID or Password");
		}
		else if(chck.equals("right"))
		{
			
			if(username.startsWith("C")){
			session.setAttribute("User", username);
			session.setAttribute("Access",init_vals[0]);
			session.setAttribute("Type",init_vals[1]);
			session.setAttribute("Nick", init_vals[1]+"-"+username);
			session.setAttribute("Consti",username.substring(1, 4));
			session.setAttribute("Wards",init_vals[3]);
			session.setAttribute("Categories",init_vals[2]);
			
			//RequestDispatcher view = request.getRequestDispatcher("./Grievance_main");
	        //view.forward(request, response);
			response.sendRedirect("/CMS/Home_load?consti="+username.substring(1, 4));
			}
			else if(username.startsWith("W")){
				session.setAttribute("User", username);
				session.setAttribute("Access",init_vals[0]);
				session.setAttribute("Type",init_vals[1]);
				session.setAttribute("Nick", init_vals[1]+"-"+username);
				session.setAttribute("Consti",username.substring(1, 4));
				session.setAttribute("Wards",username.substring(4, 7));
				session.setAttribute("Categories",init_vals[2]);
			//RequestDispatcher view = request.getRequestDispatcher("./Grievance_main");
	        //view.forward(request, response);
				response.sendRedirect("/CMS/Home_load?consti="+username.substring(1, 4)+"&ward="+username.substring(4, 7));
			}
		
			else if(username.equalsIgnoreCase("ADMIN"))
			{
				session.setAttribute("User", "ADMIN");
				session.setAttribute("Access","64523222");
				session.setAttribute("Type","ADMIN");
				session.setAttribute("Nick", "ADMIN");
				
				//RequestDispatcher view = request.getRequestDispatcher("./Grievance_main");
		        //view.forward(request, response);
				response.sendRedirect("./ADMIN/cpanel.jsp");
				
			}
			else
			{
				session.setAttribute("User", username);
				session.setAttribute("Access",init_vals[0]);
				session.setAttribute("Type","CITIZEN");
				session.setAttribute("Consti",init_vals[3].substring(0, 3));
				session.setAttribute("Wards",init_vals[3].substring(4));
				session.setAttribute("Categories",init_vals[2]);
				session.setAttribute("Nick", "CITIZEN-"+init_vals[1]);
				//RequestDispatcher view = request.getRequestDispatcher("./Grievance_main");
		        //view.forward(request, response);
				response.sendRedirect("/CMS/Home_load?consti="+init_vals[3].substring(0, 3)+"&ward="+init_vals[3].substring(4));
			}  
		}
		
	}
 }
 