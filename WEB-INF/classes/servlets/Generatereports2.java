
package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.export.*;
import net.sf.jasperreports.view.*;
import net.sf.jasperreports.view.save.*;
import net.sf.jasperreports.engine.design.*;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.engine.data.JRMapArrayDataSource;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;

/**
 * Servlet implementation class for Servlet: Generatereports
 *
 */
 public class Generatereports2 extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
   static final long serialVersionUID = 1L;
   
    /* (non-Java-doc)
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public Generatereports2() {
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
		PrintWriter out=response.getWriter();
		String value = (String)request.getParameter("report2");
		String rtype = (String)request.getParameter("type2");
		
		
		if(value.equals("userdetailstate"))
		{
			try {
				InitialContext ctx = new InitialContext();
				DataSource ds = (DataSource) ctx
						.lookup("java:comp/env/jdbc/MyDataSource");
				Connection con = ds.getConnection();
				Statement st = con.createStatement();
				String query = "SELECT COUNT(*) AS COUNT FROM DB2ADMIN.CONSTITUENCY";
				ResultSet rs = st.executeQuery(query);
				rs.next();
				int rowcnt = rs.getInt("COUNT");
				HashMap[] data = new HashMap[rowcnt];
				String[] constilist = new String[rowcnt];
				Statement st1 = con.createStatement();
				String query1 = "SELECT * FROM DB2ADMIN.CONSTITUENCY";
				ResultSet rs1 = st1.executeQuery(query1);
				int i=0;
				while(rs1.next())
				{	
					constilist[i]=rs1.getString("CODE");
					i++;
					
				}
				for(i=0;i<constilist.length;i++)
				{
					
					Statement st2 = con.createStatement();
					String query2="SELECT COUNT(*) AS COUNT FROM DB2ADMIN."+constilist[i]+"_LOGIN";
					ResultSet rs2 = st2.executeQuery(query2);
					rs2.next();
					int cnt = rs2.getInt("COUNT");
					HashMap temp = new HashMap();
					temp.put("consti", constilist[i]);
					temp.put("count", cnt);
					data[i]=temp;
					
					st2.close();
					st2=null;
					rs2.close();
					rs2=null;
				}

				ServletContext application = this.getServletContext();
			File reportFile1= new File(application.getRealPath("/reports/NO_OF_USERS.jrxml"));
			JRMapArrayDataSource ds1 = new JRMapArrayDataSource(data);	
			JasperDesign design = JRXmlLoader.load(reportFile1);
			JasperReport jasperReport = JasperCompileManager.compileReport(design);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,new HashMap(), ds1);


			File reportFile2 = new File(application.getRealPath("/HEAD//Reports/"));
			if( "pdf".equalsIgnoreCase(rtype) )
			{
				JasperExportManager.exportReportToPdfFile(jasperPrint,
				reportFile2+"\\NO_OF_USERS.pdf");
				response.setContentType("application/pdf");
			}
			else if( "rtf".equalsIgnoreCase(rtype) )
			{
			  response.setContentType("application/rtf");
			  response.setHeader("Content-Disposition", "inline; filename=\"file.rtf\"");
			  File destFile = new File(reportFile2+"\\NO_OF_USERS.rtf");

			  JRRtfExporter exporter = new JRRtfExporter();

			  exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			  exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());

			  exporter.exportReport();
			  //exporter = new JRRtfExporter();
			//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			}
			else if( "html".equalsIgnoreCase(rtype) )
			{
				
				JasperExportManager.exportReportToHtmlFile(jasperPrint, reportFile2+"\\NO_OF_USERS.html");
				//response.setContentType("application/pdf");
				//here not use it but for image add imageclass
			}
			else if( "xls".equalsIgnoreCase(rtype) )
			{
			  response.setContentType("application/xls");
			  response.setHeader("Content-Disposition", "inline; filename=\"file.xls\"");
			  File destFile = new File(reportFile2+"\\NO_OF_USERS.xls");
			  JRXlsExporter xlsExporter = new JRXlsExporter();

			  xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			  xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
			  xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);

			  xlsExporter.exportReport();
			//exporter = new JRXlsExporter();
			//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			}
			else if( "csv".equalsIgnoreCase(rtype) )
			{
			  response.setContentType("application/csv");
			  response.setHeader("Content-Disposition", "inline; filename=\"file.csv\"");
			  File destFile = new File(reportFile2+"\\NO_OF_USERS.csv");

			  JRCsvExporter exporter = new JRCsvExporter();

			  exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			  exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());

			  exporter.exportReport();
			//exporter = new JRCsvExporter();
			//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			}
			else if( "odf".equalsIgnoreCase(rtype) )
			{
			  File destFile = new File(reportFile2+"\\NO_OF_USERS.odf");

			  JROdtExporter exporter = new JROdtExporter();

			  exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			  exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());

			  exporter.exportReport();
			//exporter = new JRCsvExporter();
			//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			}
			
			rs.close();
			rs=null;
			rs1.close();
			rs1=null;
			st.close();
			st=null;
			st1.close();
			st1=null;
			con.close();
			con = null;
			

			RequestDispatcher view = request.getRequestDispatcher("./ADMIN/cpanel.jsp");
			view.forward(request, response);
				
			} catch (Exception e) {
			}
			
    	}
		
		if(value.equals("fundstate"))
		{
			try {
				InitialContext ctx = new InitialContext();
				DataSource ds = (DataSource) ctx
						.lookup("java:comp/env/jdbc/MyDataSource");
				Connection con = ds.getConnection();
				Statement st = con.createStatement();
				String query = "SELECT COUNT(*) AS COUNT FROM DB2ADMIN.CONSTITUENCY";
				ResultSet rs = st.executeQuery(query);
				rs.next();
				int rowcnt = rs.getInt("COUNT");
				HashMap[] data = new HashMap[rowcnt];
				String[] constilist = new String[rowcnt];
				Statement st1 = con.createStatement();
				String query1 = "SELECT * FROM DB2ADMIN.CONSTITUENCY";
				ResultSet rs1 = st1.executeQuery(query1);
				int i=0;
				while(rs1.next())
				{	
					constilist[i]=rs1.getString("CODE");
					i++;
					
				}
				for(i=0;i<constilist.length;i++)
				{
					
					Statement st2 = con.createStatement();
					String query2="SELECT SUM(COST) AS FUNDS FROM DB2ADMIN."+constilist[i]+"_FUNDS";
					ResultSet rs2 = st2.executeQuery(query2);
					rs2.next();
					long fund = rs2.getLong("FUNDS");
					HashMap temp = new HashMap();
					temp.put("consti", constilist[i]);
					temp.put("funds", fund);
					data[i]=temp;
					
					st2.close();
					st2=null;
					rs2.close();
					rs2=null;
				}

				ServletContext application = this.getServletContext();
			File reportFile1= new File(application.getRealPath("/reports/STATE_FUNDS.jrxml"));
			JRMapArrayDataSource ds1 = new JRMapArrayDataSource(data);	
			JasperDesign design = JRXmlLoader.load(reportFile1);
			JasperReport jasperReport = JasperCompileManager.compileReport(design);
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,new HashMap(), ds1);


			File reportFile2 = new File(application.getRealPath("/HEAD//Reports/"));
			if( "pdf".equalsIgnoreCase(rtype) )
			{
				JasperExportManager.exportReportToPdfFile(jasperPrint,
				reportFile2+"\\FUND_UTILIZATION.pdf");
				response.setContentType("application/pdf");
			}
			else if( "rtf".equalsIgnoreCase(rtype) )
			{
			  response.setContentType("application/rtf");
			  response.setHeader("Content-Disposition", "inline; filename=\"file.rtf\"");
			  File destFile = new File(reportFile2+"\\FUND_UTILIZATION.rtf");

			  JRRtfExporter exporter = new JRRtfExporter();

			  exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			  exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());

			  exporter.exportReport();
			  //exporter = new JRRtfExporter();
			//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			}
			else if( "html".equalsIgnoreCase(rtype) )
			{
				
				JasperExportManager.exportReportToHtmlFile(jasperPrint, reportFile2+"\\FUND_UTILIZATION.html");
				//response.setContentType("application/pdf");
				//here not use it but for image add imageclass
			}
			else if( "xls".equalsIgnoreCase(rtype) )
			{
			  response.setContentType("application/xls");
			  response.setHeader("Content-Disposition", "inline; filename=\"file.xls\"");
			  File destFile = new File(reportFile2+"\\FUND_UTILIZATION.xls");
			  JRXlsExporter xlsExporter = new JRXlsExporter();

			  xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			  xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
			  xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);

			  xlsExporter.exportReport();
			//exporter = new JRXlsExporter();
			//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			}
			else if( "csv".equalsIgnoreCase(rtype) )
			{
			  response.setContentType("application/csv");
			  response.setHeader("Content-Disposition", "inline; filename=\"file.csv\"");
			  File destFile = new File(reportFile2+"\\FUND_UTILIZATION.csv");

			  JRCsvExporter exporter = new JRCsvExporter();

			  exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			  exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());

			  exporter.exportReport();
			//exporter = new JRCsvExporter();
			//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			}
			else if( "odf".equalsIgnoreCase(rtype) )
			{
			  File destFile = new File(reportFile2+"\\FUND_UTILIZATION.odf");

			  JROdtExporter exporter = new JROdtExporter();

			  exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			  exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());

			  exporter.exportReport();
			//exporter = new JRCsvExporter();
			//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
			//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
			}
			
			rs.close();
			rs=null;
			rs1.close();
			rs1=null;
			st.close();
			st=null;
			st1.close();
			st1=null;
			con.close();
			con=null;

			RequestDispatcher view = request.getRequestDispatcher("./ADMIN/cpanel.jsp");
			view.forward(request, response);
				
			} catch (Exception e) {
			}
			
    	}
	}   	  	    
}