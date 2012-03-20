package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import beans.*;



/**
 * Servlet implementation class for Servlet: Register
 *
 */
 public class Register extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Register() {
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
		String voterid=(String) request.getParameter("vid");
		String cons =  request.getParameter("cons");
		String ward = request.getParameter("ward");
		String password=(String) request.getParameter("cpass");
		String fname=(String) request.getParameter("fname");
		String mname=(String) request.getParameter("mname");
		String lname=(String) request.getParameter("lname");
		String nickname=(String) request.getParameter("nick");
		String gender=(String) request.getParameter("gender");
		String date=(String) request.getParameter("d");
		String month=(String) request.getParameter("m");
		String year=(String) request.getParameter("y");
		String address=(String) request.getParameter("address");
		String email=(String) request.getParameter("email");
		String phone=(String) request.getParameter("tel");
		String cell=(String) request.getParameter("cell");

		//String s=voterid.substring(0,3);

		Registerbean registeruser=null;
		try {
			registeruser = new Registerbean();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			registeruser.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		registeruser.setFname(fname);
		registeruser.setLname(lname);
		registeruser.setMname(mname);
		registeruser.setvoterid(voterid);
		registeruser.setconsti(cons);
		registeruser.setWard(ward);
		registeruser.setpassword(password);
		registeruser.setnickname(nickname);
		registeruser.setgender(gender);
		registeruser.setdate(date);
		registeruser.setmonth(month);
		registeruser.setyear(year);
		registeruser.setaddress(address);
		registeruser.setemail(email);
		registeruser.setphone(phone);
		registeruser.setcell(cell);
		

		try {
			registeruser.execQueryRegister();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			registeruser.terminate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		request.setAttribute("msg", "registered");
		RequestDispatcher view = request
		.getRequestDispatcher("./msghandler.jsp");
		view.forward(request, response);
	}   	  	    
}