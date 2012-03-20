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
 * Servlet implementation class for Servlet: Changeid
 *
 */
 public class Changeid extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Changeid() {
		super();
	}   	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);
	}  	
	
	/* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Changeidbean change = null;;
		try {
			change = new Changeidbean();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		HttpSession session = request.getSession();
		String voterid = (String)session.getAttribute("User");
		String nickname = (String)session.getAttribute("Nick");
		String code = (String)session.getAttribute("Consti");
		
		try {
			change.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		change.setVoterid(voterid);
		change.setNickname(nickname);
		change.setCode(code);
		try {
			change.execQueryChangeid();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		
		
		change.terminate();
		
		session.removeAttribute("User");
		session.removeAttribute("Nick");
		session.removeAttribute("Consti");
		session.removeAttribute("Type");
		session.invalidate();
		String message = "Your account has been deleted<br /> please <a href=./register.jsp>click here</a> to create a new account with your new voterid";
		request.setAttribute("msg",message );
		RequestDispatcher view = request
		.getRequestDispatcher("./msghandler.jsp");
		view.forward(request, response);
	}   	  	    
}