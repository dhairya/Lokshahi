package servlets;
import java.io.*;
import beans.*;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class for Servlet: Createconst
 *
 */
 public class Createconst extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Createconst() {
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
		//HttpSession session = request.getSession();
		String consti_name=(String) request.getParameter("name");
		String code=(String) request.getParameter("code");
	    code = code.toUpperCase();
	    consti_name = consti_name.toUpperCase();
		Createconstibean constituency = null;
		PrintWriter out=response.getWriter();
		String result = null;
		
		try {
			constituency =new Createconstibean();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		try {
			constituency.init();
		} catch (Exception e) {
			// TODO Auto-generated catch block
		}
		constituency.setCode(code);
		constituency.setConstiname(consti_name);
		try {
			result = constituency.execCreateTables();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			out.println(e);
		}
		try{
			constituency.terminate();
		}
		catch(Exception e)
		{
			out.println(e);
		}
		if(result.equals("done"))
		{
		request.setAttribute("Consti",code);
		//session.setAttribute("const",code);
		RequestDispatcher view = request
		.getRequestDispatcher("/ADMIN/create_const2.jsp");
view.forward(request, response);
		}
		else
		{
			request.setAttribute("consti",code);
			request.setAttribute("errorcreate","exists");
			RequestDispatcher view = request
				.getRequestDispatcher("/ADMIN/edit_consterr.jsp");
		view.forward(request, response);
		}
		
	}   	  	    
}