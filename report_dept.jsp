<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page language="java" import="net.sf.jasperreports.engine.*" %>
<%@ page import="net.sf.jasperreports.engine.export.*" %>
<%@ page import="java.sql.*,java.io.*,java.util.*" %>
<%@ page import="net.sf.jasperreports.view.*" %> 
<%@ page import="net.sf.jasperreports.view.save.*" %>
<%@ page import="net.sf.jasperreports.engine.design.*" %>
<%@ page import="net.sf.jasperreports.engine.xml.JRXmlLoader" %>
<%@ page import="javax.sql.DataSource" %>
<%@ page import="javax.naming.InitialContext" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="net.sf.jasperreports.engine.export.oasis.JROdtExporter"%>
<html>
<head>

<%

//Connection

InitialContext ctx = new InitialContext();
DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDataSource");
Connection con = ds.getConnection();
//parameter set
String tablename = (String)request.getAttribute("table");
String reporttype = (String)request.getAttribute("reporttype");
String consti = (String)request.getAttribute("code");
Map parameters = new HashMap();
parameters.put("table", tablename);
//load 

File reportFile = new File(application.getRealPath("/reports/DEPTWISE_PROBLEMS.jrxml"));
JasperDesign design = JRXmlLoader.load(reportFile);

//compile

JasperReport jasperReport = JasperCompileManager.compileReport(design);


// fill report

JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,parameters, con);
//JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,null, conn);

File reportFile2 = new File(application.getRealPath("/genreports/"));
System.out.println(reportFile2.toString());
if( "pdf".equalsIgnoreCase(reporttype) )
{
	JasperExportManager.exportReportToPdfFile(jasperPrint,
	reportFile2+"\\"+consti+"_DEPTWISE_PROBLEMS.pdf");
	response.setContentType("application/pdf");
}
else if( "rtf".equalsIgnoreCase(reporttype) )
{
  response.setContentType("application/rtf");
  response.setHeader("Content-Disposition", "inline; filename=\"file.rtf\"");
  File destFile = new File(reportFile2+"\\"+consti+"_DEPTWISE_PROBLEMS.rtf");

  JRRtfExporter exporter = new JRRtfExporter();

  exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
  exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());

  exporter.exportReport();
  //exporter = new JRRtfExporter();
//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
}
else if( "html".equalsIgnoreCase(reporttype) )
{
	
	JasperExportManager.exportReportToHtmlFile(jasperPrint, reportFile2+"\\"+consti+"_DEPTWISE_PROBLEMS.html");
	//response.setContentType("application/pdf");
	//here not use it but for image add imageclass
}
else if( "xls".equalsIgnoreCase(reporttype) )
{
  response.setContentType("application/xls");
  response.setHeader("Content-Disposition", "inline; filename=\"file.xls\"");
  File destFile = new File(reportFile2+"\\"+consti+"_DEPTWISE_PROBLEMS.xls");
  JRXlsExporter xlsExporter = new JRXlsExporter();

  xlsExporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
  xlsExporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());
  xlsExporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);

  xlsExporter.exportReport();
//exporter = new JRXlsExporter();
//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
}
else if( "csv".equalsIgnoreCase(reporttype) )
{
  response.setContentType("application/csv");
  response.setHeader("Content-Disposition", "inline; filename=\"file.csv\"");
  File destFile = new File(reportFile2+"\\"+consti+"_DEPTWISE_PROBLEMS.csv");

  JRCsvExporter exporter = new JRCsvExporter();

  exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
  exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());

  exporter.exportReport();
//exporter = new JRCsvExporter();
//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
}
else if( "odf".equalsIgnoreCase(reporttype) )
{
  File destFile = new File(reportFile2+"\\"+consti+"_DEPTWISE_PROBLEMS.odf");

  JROdtExporter exporter = new JROdtExporter();

  exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
  exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, destFile.toString());

  exporter.exportReport();
//exporter = new JRCsvExporter();
//exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
}


con.close();
con=null;


RequestDispatcher view = request.getRequestDispatcher("./ADMIN/cpanel.jsp");
view.forward(request, response);
%>
<title>Generating Report...</title>
</head>
<body>


</body>
</html>