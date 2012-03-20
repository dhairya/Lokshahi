package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Date;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
/**
 * Servlet implementation class for Servlet: Deleteuser
 *
 */
 public class Deleteuser extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Deleteuser() {
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
		
		String id = (String)request.getParameter("vid");
		String cons = request.getParameter("cons");
		String ward = request.getParameter("ward");
		String option = request.getParameter("user");
		String type = request.getParameter("type");
		String spl_nickname = null;
		String consti_ward = null;
		String spl_cons=null, spl_ward=null;
		String access_wards=null;
		Connection con;
		Statement stmt;
		Statement stmt1;
		Statement stmt2;
		Statement stmt3;
		Statement stmt4;
		Statement stmt5;
		Statement stmt6;
		Statement stmt7;
		Statement stmt8;
		Statement stmt9;
		Statement stmt10;
		Statement stmt11;
		ResultSet rs = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		try{
			InitialContext ctx = new InitialContext();
			DataSource ds = (DataSource) ctx
					.lookup("java:comp/env/jdbc/MyDataSource");
			 con = ds.getConnection();
			 stmt = con.createStatement();
			 stmt1 = con.createStatement();
			 stmt2 = con.createStatement();
			 stmt3 = con.createStatement();
			 stmt4 = con.createStatement();
			 stmt5 = con.createStatement();
			 stmt6 = con.createStatement();
			 stmt7 = con.createStatement();
			 stmt8 = con.createStatement();
			 stmt9 = con.createStatement();
			 stmt10 = con.createStatement();
			 stmt11 =  con.createStatement();
			
			if(option.equals("delete"))
			{   
				if(type.equals("spluser"))
				{
					spl_cons=id.substring(1,4);
					if(id.charAt(0)=='C')
					{
						String query = "SELECT USERTYPE,ACCESS_WARDS FROM "+spl_cons+
						               "_SPECIALUSERS WHERE LOGIN='"+id+"'";
						rs = stmt.executeQuery(query);
						rs.next();
						spl_nickname = rs.getString("USERTYPE")+"-"+id;
						access_wards=rs.getString("ACCESS_WARDS");
						String query4 = "UPDATE "+spl_cons+"_FUNDS SET NICKNAME = 'EMPTYUSER'" +
		                 				"WHERE NICKNAME = '"+spl_nickname+"'";
                        stmt4.executeUpdate(query4);
                       
						if(access_wards.equals("all"))
						{
							String query1 = "SELECT NUMBER FROM "+spl_cons+
							                "_WARDS";
							rs1 = stmt1.executeQuery(query1);
							while(rs1.next())
							{
								spl_ward=rs.getString("NUMBER");
								String query3 = "UPDATE "+spl_cons+"_"+spl_ward+"_POLL SET NICKNAME = 'EMPTYUSER'" +
			 		             "WHERE NICKNAME = '"+spl_nickname+"'";
			                    stmt3.executeUpdate(query3);
			                    
			                    
		                        
		                        String query5 = "UPDATE "+spl_cons+"_"+spl_ward+"_FORUM SET NICKNAME = 'EMPTYUSER'" +
	 		                     "WHERE NICKNAME = '"+spl_nickname+"'";
	                            stmt5.executeUpdate(query5);

	                            String query6 = "UPDATE "+spl_cons+"_"+spl_ward+"_GRIEVANCES SET NICKNAME = 'EMPTYUSER'" +
		                         "WHERE NICKNAME = '"+spl_nickname+"'";
                                stmt6.executeUpdate(query6);

                                String query7 = "UPDATE "+spl_cons+"_"+spl_ward+"_GRIEVANCE_COMMENTS SET NICKNAME = 'EMPTYUSER'" +
	                             "WHERE NICKNAME = '"+spl_nickname+"'";
                                stmt7.executeUpdate(query7);

                                String query8 = "UPDATE "+spl_cons+"_"+spl_ward+"_INVESTIGATION SET NICKNAME = 'EMPTYUSER'" +
                                "WHERE NICKNAME = '"+spl_nickname+"'";
                                stmt8.executeUpdate(query8);

                                String query9 = "UPDATE "+spl_cons+"_"+spl_ward+"_VOTERS_JOINED SET NICKNAME = 'EMPTYUSER'" +
                                "WHERE NICKNAME = '"+spl_nickname+"'";
                                stmt9.executeUpdate(query9);
							}
						}
						else
						{
							String wards[]=access_wards.split(",");
							for(int i=0;i<wards.length;i++)
							{
								
								String query3 = "UPDATE "+spl_cons+"_"+wards[i]+"_POLL SET NICKNAME = 'EMPTYUSER'" +
			 		             "WHERE NICKNAME = '"+spl_nickname+"'";
			                    stmt3.executeUpdate(query3);
			     
			                   /* String query4 = "UPDATE "+spl_cons+"_"+wards[i]+"_FUNDS SET (NICKNAME) = ('EMPTYUSER')" +
		 		                 "WHERE NICKNAME = '"+spl_nickname+"'";
			                    stmt4.executeUpdate(query4);*/
		 
			                    String query5 = "UPDATE "+spl_cons+"_"+wards[i]+"_FORUM SET NICKNAME = 'EMPTYUSER'" +
	 		                     "WHERE NICKNAME = '"+spl_nickname+"'";
			                    stmt5.executeUpdate(query5);
	 
			                    String query6 = "UPDATE "+spl_cons+"_"+wards[i]+"_GRIEVANCES SET NICKNAME = 'EMPTYUSER'" +
		                         "WHERE NICKNAME = '"+spl_nickname+"'";
			                    stmt6.executeUpdate(query6);

			                    String query7 = "UPDATE "+spl_cons+"_"+wards[i]+"_GRIEVANCE_COMMENTS SET NICKNAME = 'EMPTYUSER'" +
	                             "WHERE NICKNAME = '"+spl_nickname+"'";
			                    stmt7.executeUpdate(query7);

			                    String query8 = "UPDATE "+spl_cons+"_"+wards[i]+"_INVESTIGATION SET NICKNAME = 'EMPTYUSER'" +
                                "WHERE NICKNAME = '"+spl_nickname+"'";
			                    stmt8.executeUpdate(query8);

			                    String query9 = "UPDATE "+spl_cons+"_"+wards[i]+"_VOTERS_JOINED SET NICKNAME = 'EMPTYUSER'" +
                                "WHERE NICKNAME = '"+spl_nickname+"'";
			                    stmt9.executeUpdate(query9);

							}
						}
						String query10 = "DELETE FROM "+spl_cons+"_SPECIALUSERS WHERE LOGIN='"+id+"'";
						stmt10.executeUpdate(query10);
					}
					else
					{
						
						spl_ward=id.substring(4,7);
						String query = "SELECT USERTYPE FROM "+spl_cons+
						               "_"+spl_ward+"_SPECIALUSERS WHERE LOGIN='"+id+"'";
						rs = stmt.executeQuery(query);
						rs.next();
						spl_nickname=rs.getString("USERTYPE")+"-"+id;
						
						String query3 = "UPDATE "+spl_cons+"_"+spl_ward+"_POLL SET NICKNAME = 'EMPTYUSER'" +
	 		             "WHERE NICKNAME = '"+spl_nickname+"'";
	                    stmt3.executeUpdate(query3);
	                    
	                    String query4 = "UPDATE "+spl_cons+"_FUNDS SET NICKNAME = 'EMPTYUSER'" +
		                 "WHERE NICKNAME = '"+spl_nickname+"'";
                        stmt4.executeUpdate(query4);
                       
                       String query5 = "UPDATE "+spl_cons+"_"+spl_ward+"_FORUM SET NICKNAME = 'EMPTYUSER'" +
	                     "WHERE NICKNAME = '"+spl_nickname+"'";
                       stmt5.executeUpdate(query5);

                       String query6 = "UPDATE "+spl_cons+"_"+spl_ward+"_GRIEVANCES SET NICKNAME = 'EMPTYUSER'" +
                        "WHERE NICKNAME = '"+spl_nickname+"'";
                       stmt6.executeUpdate(query6);

                       String query7 = "UPDATE "+spl_cons+"_"+spl_ward+"_GRIEVANCE_COMMENTS SET NICKNAME = 'EMPTYUSER'" +
                        "WHERE NICKNAME = '"+spl_nickname+"'";
                       stmt7.executeUpdate(query7);

                       String query8 = "UPDATE "+spl_cons+"_"+spl_ward+"_INVESTIGATION SET NICKNAME = 'EMPTYUSER'" +
                       "WHERE NICKNAME = '"+spl_nickname+"'";
                       stmt8.executeUpdate(query8);

                       String query9 = "UPDATE "+spl_cons+"_"+spl_ward+"_VOTERS_JOINED SET NICKNAME = 'EMPTYUSER'" +
                       "WHERE NICKNAME = '"+spl_nickname+"'";
                       stmt9.executeUpdate(query9);
                       
                       String query10 = "DELETE FROM "+spl_cons+"_"+spl_ward+"_SPECIALUSERS WHERE LOGIN='"+id+"'";
						stmt10.executeUpdate(query10);
					}
					
				}
				else
				{
					consti_ward = cons+"_"+ward;
					String query = "SELECT NICKNAME,VOTERID FROM "+consti_ward+"_USER_DETAILS WHERE NICKNAME = '"+id+"'";
					rs = stmt.executeQuery(query);
					if(rs != null && rs.next())
					{
						String nickname = rs.getString("NICKNAME");
						nickname = "Citizen-"+nickname;
						id = rs.getString("VOTERID");
						

					String query3 = "UPDATE "+consti_ward+"_POLL SET NICKNAME = 'EMPTYUSER'" +
		             "WHERE NICKNAME = '"+nickname+"'";
					stmt3.executeUpdate(query3);
    
					String query4 = "UPDATE "+cons+"_FUNDS SET NICKNAME = 'EMPTYUSER'" +
	                 "WHERE NICKNAME = '"+nickname+"'";
					stmt4.executeUpdate(query4);

					String query5 = "UPDATE "+consti_ward+"_FORUM SET NICKNAME = 'EMPTYUSER'" +
                     "WHERE NICKNAME = '"+nickname+"'";
					stmt5.executeUpdate(query5);

					String query6 = "UPDATE "+consti_ward+"_GRIEVANCES SET NICKNAME = 'EMPTYUSER'" +
                    "WHERE NICKNAME = '"+nickname+"'";
					stmt6.executeUpdate(query6);

					String query7 = "UPDATE "+consti_ward+"_GRIEVANCE_COMMENTS SET NICKNAME = 'EMPTYUSER'" +
                    "WHERE NICKNAME = '"+nickname+"'";
					stmt7.executeUpdate(query7);

					String query8 = "UPDATE "+consti_ward+"_INVESTIGATION SET NICKNAME = 'EMPTYUSER'" +
                    "WHERE NICKNAME = '"+nickname+"'";
					stmt8.executeUpdate(query8);

					String query9 = "UPDATE "+consti_ward+"_VOTERS_JOINED SET NICKNAME = 'EMPTYUSER'" +
                    "WHERE NICKNAME = '"+nickname+"'";
					stmt9.executeUpdate(query9);

					String query2 = "DELETE FROM GLOBAL WHERE VOTERID = '"+id+"' AND CONSTI_WARD='"+consti_ward+"'";
					stmt2.executeUpdate(query2);
					
					String query11 = "DELETE FROM "+consti_ward+"_USER_DETAILS WHERE VOTERID='"+id+"'";
					stmt11.executeUpdate(query11);
					}
					else
					{
						System.out.println("nickname not exists");
					}
				}
				RequestDispatcher view = request
        		.getRequestDispatcher("/ADMIN/delUsers.jsp");
                view.forward(request, response);
                rs.close();
                rs1.close();
                rs2.close();
                stmt.close();
                stmt1.close();
                stmt2.close();
                stmt3.close();
                stmt4.close();
                stmt5.close();
                stmt6.close();
                stmt7.close();
                stmt8.close();
                stmt9.close();
                stmt10.close();
                con.close();

			}
			if(option.equals("edit"))
			{
				String query1 = "SELECT * FROM CATEGORIES";
				rs1=stmt1.executeQuery(query1);
				if(type.equals("spluser"))
				{
					spl_cons=id.substring(1,4);
					String query2 = "SELECT NUMBER FROM "+spl_cons+"_WARDS";
					rs2=stmt2.executeQuery(query2);
					if(id.charAt(0)=='C')
					{
						String query = "SELECT * FROM "+spl_cons+"_SPECIALUSERS WHERE LOGIN='"+id+"'";
						rs=stmt.executeQuery(query);
						
						request.setAttribute("wards",rs2);
						request.setAttribute("cat",rs1);
						request.setAttribute("userinfo",rs);
						request.setAttribute("type","c");
						request.setAttribute("spl_cons",spl_cons);
						RequestDispatcher view = request
			     		.getRequestDispatcher("/ADMIN/editUser.jsp");
						view.forward(request, response);
						rs.close();
		                rs1.close();
		                rs2.close();
		                stmt.close();
		                stmt1.close();
		                stmt2.close();
		                stmt3.close();
		                stmt4.close();
		                stmt5.close();
		                stmt6.close();
		                stmt7.close();
		                stmt8.close();
		                stmt9.close();
		                stmt10.close();
		                con.close();

					}
					else
					{
						spl_ward=id.substring(4,7);
						String query = "SELECT * FROM "+spl_cons+
						               "_"+spl_ward+"_SPECIALUSERS WHERE LOGIN='"+id+"'";
						rs = stmt.executeQuery(query);
						
						
						request.setAttribute("cat",rs1);
						request.setAttribute("userinfo",rs);
						request.setAttribute("type","w");
						request.setAttribute("spl_cons",spl_cons);
						request.setAttribute("spl_ward",spl_ward);
						RequestDispatcher view = request
			     		.getRequestDispatcher("/ADMIN/editUser.jsp");
						view.forward(request, response);
						rs.close();
		                rs1.close();
		                rs2.close();
		                stmt.close();
		                stmt1.close();
		                stmt2.close();
		                stmt3.close();
		                stmt4.close();
		                stmt5.close();
		                stmt6.close();
		                stmt7.close();
		                stmt8.close();
		                stmt9.close();
		                stmt10.close();
		                con.close();

					}
				}
				else
				{
					consti_ward = cons+"_"+ward;
					String query = "SELECT VOTERID, NICKNAME, ACCESS_INFO, SOLVABLE_CATEGORIES FROM "+consti_ward+
	                 				 "_USER_DETAILS WHERE NICKNAME='"+id+"'";
					rs = stmt.executeQuery(query);
					
					
					
					request.setAttribute("cat",rs1);
					request.setAttribute("userinfo",rs);
					request.setAttribute("consti_ward",consti_ward);
					RequestDispatcher view = request
		     		.getRequestDispatcher("/ADMIN/editUser.jsp");
					view.forward(request, response);
					rs.close();
	                rs1.close();
	                rs2.close();
	                stmt.close();
	                stmt1.close();
	                stmt2.close();
	                stmt3.close();
	                stmt4.close();
	                stmt5.close();
	                stmt6.close();
	                stmt7.close();
	                stmt8.close();
	                stmt9.close();
	                stmt10.close();
	                con.close();

				}
			}
		}
		
		catch(Exception e)
		{
			
		}
	}   	  	    
}