/*     */ package com.compulynx.erevenue.bo.servlet;
/*     */ 
/*     */ import com.compulynx.erevenue.dal.operations.DBOperations;
/*     */ import com.compulynx.erevenue.dal.operations.Queryconstants;
import com.compulynx.erevenue.dal.operations.Utility;
import com.compulynx.erevenue.models.CompasProperties;
/*     */ import com.compulynx.erevenue.models.ErevenueResponse;
/*     */ import com.mysql.jdbc.Statement;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.naming.Context;
/*     */ import javax.naming.InitialContext;
/*     */ import javax.naming.NamingException;
/*     */ import javax.servlet.ServletException;
/*     */ import javax.servlet.ServletOutputStream;
/*     */ import javax.servlet.http.HttpServlet;
/*     */ import javax.servlet.http.HttpServletRequest;
/*     */ import javax.servlet.http.HttpServletResponse;
/*     */ import javax.sql.DataSource;
/*     */ import javax.ws.rs.core.Response;
/*     */ import net.sf.jasperreports.engine.JRException;
/*     */ import net.sf.jasperreports.engine.JRExporterParameter;
/*     */ import net.sf.jasperreports.engine.JasperCompileManager;
/*     */ import net.sf.jasperreports.engine.JasperExportManager;
/*     */ import net.sf.jasperreports.engine.JasperFillManager;
/*     */ import net.sf.jasperreports.engine.JasperPrint;
/*     */ import net.sf.jasperreports.engine.JasperReport;
/*     */ import net.sf.jasperreports.engine.design.JasperDesign;
/*     */ import net.sf.jasperreports.engine.export.JExcelApiExporterParameter;
/*     */ import net.sf.jasperreports.engine.export.JRXlsExporterParameter;
/*     */ import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
/*     */ import net.sf.jasperreports.engine.xml.JRXmlLoader;
/*     */ import org.joda.time.DateTime;
/*     */ import org.joda.time.ReadableInstant;
/*     */ import org.joda.time.format.DateTimeFormat;
/*     */ import org.joda.time.format.DateTimeFormatter;
/*     */ import org.springframework.stereotype.Component;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Component
/*     */ public class Reports
/*     */   extends HttpServlet
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*  76 */   private static String REPORTS_PATH = "";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private DataSource dataSource;
/*     */ 
/*     */ 
/*     */   
/*     */   private Connection connection;
/*     */ 
/*     */ 
/*     */   
/*     */   private Statement statement;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void init() throws ServletException {
/*     */     try {
/*  96 */       Context initContext = new InitialContext();
/*  97 */       Context envContext = (Context)initContext.lookup("java:/comp/env");
/*  98 */       this.dataSource = (DataSource)envContext.lookup("jdbc/erevenueDS");
/*     */     }
/* 100 */     catch (NamingException e) {
/* 101 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/* 127 */     JasperReport jasperReport = null;
/* 128 */     JasperPrint jasperPrint = null;
/* 129 */     JasperDesign jasperDesign = null;
/* 130 */     Map<String, String> parameters = new HashMap<>();
/* 131 */     ServletOutputStream outstream = null;
/* 132 */     Connection connection = null;
/* 133 */     String reportType = request.getParameter("type");
/* 134 */     String exportType = request.getParameter("eType");
              new CompasProperties();
              Utility util = new Utility();
              String param = "report.filepath";
             CompasProperties compasProperties = util.getCompasProperties(param);
            //logger.info("Done Reading Props File##" + compasProperties.bnfUploadFilePath);
              this.REPORTS_PATH = compasProperties.bnfUploadFilePath;
               //logger.info("Reports File Path##" + this.PATH);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 139 */       InitialContext initialContext = new InitialContext();
/* 140 */       Context context = (Context)initialContext.lookup("java:/comp/env");
/* 141 */       DataSource ds = (DataSource)context.lookup("jdbc/erevenueDS");
/* 142 */       connection = ds.getConnection();
/*     */       
/* 144 */       if (reportType.equalsIgnoreCase("I")) {
/* 145 */         String businessNo = request.getParameter("businessNo");
/* 146 */         String approvedUser = request.getParameter("approvedUser");
/* 147 */         String year = request.getParameter("year");
/* 148 */         parameters.put("businessNo", businessNo);
/* 149 */         parameters.put("approvedUser", approvedUser);
/* 150 */         parameters.put("year", year);
/* 151 */         System.out.println("calling servlet## Invoice Pdf");
/*     */ 
/*     */         
/* 154 */         jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/RptInvoice.jrxml");
/*     */         
/* 156 */         jasperReport = JasperCompileManager.compileReport(jasperDesign);
/* 157 */         jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */         
/* 160 */         if (jasperPrint.getPages().size() != 0) {
/* 161 */           byte[] pdfasbytes = JasperExportManager.exportReportToPdf(jasperPrint);
/*     */           
/* 163 */           outstream = response.getOutputStream();
/* 164 */           response.setContentType("application/pdf");
/* 165 */           response.setContentLength(pdfasbytes.length);
/* 166 */           outstream.write(pdfasbytes, 0, pdfasbytes.length);
/*     */         } else {
/*     */           
/* 169 */           System.out.println("No data");
/*     */         } 
/*     */       } 
/*     */       
/* 173 */       if (reportType.equalsIgnoreCase("P")) {
/*     */         
/* 175 */         String permitNo = request.getParameter("permitNo");
/*     */         
/* 177 */         parameters.put("permitNo", permitNo);
/*     */         
/* 179 */         jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/RptPermit.jrxml");
/*     */         
/* 181 */         jasperReport = JasperCompileManager.compileReport(jasperDesign);
/* 182 */         jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */         
/* 185 */         if (jasperPrint.getPages().size() != 0) {
/* 186 */           byte[] pdfasbytes = JasperExportManager.exportReportToPdf(jasperPrint);
/*     */           
/* 188 */           outstream = response.getOutputStream();
/* 189 */           response.setContentType("application/pdf");
/* 190 */           response.setContentLength(pdfasbytes.length);
/* 191 */           outstream.write(pdfasbytes, 0, pdfasbytes.length);
/* 192 */           ErevenueResponse response2 = UpdatePermitStatus(permitNo, "PR");
/*     */           
/* 194 */           if (response2.respCode == 200) {
/* 195 */             Response.status(200).entity(response2).build();
/*     */             return;
/*     */           } 
/* 198 */           Response.status(201).entity(response2).build();
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 203 */         System.out.println("No data");
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 208 */       if (reportType.equalsIgnoreCase("R")) {
/* 209 */         String businessNo = request.getParameter("businessNo");
/* 210 */         String paidUser = request.getParameter("paidUser");
/* 211 */         String year = request.getParameter("year");
/* 212 */         parameters.put("businessNo", businessNo);
/* 213 */         parameters.put("paidUser", paidUser);
/* 214 */         parameters.put("year", year);
/* 215 */         System.out.println("calling servlet## Invoice Receipt Pdf");
/* 216 */         jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/RptInvoiceReceipt.jrxml");
/*     */ 
/*     */         
/* 219 */         jasperReport = JasperCompileManager.compileReport(jasperDesign);
/* 220 */         jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */         
/* 223 */         if (jasperPrint.getPages().size() != 0)
/*     */         {
/* 225 */           if (jasperPrint.getPages().size() != 0) {
/*     */             
/* 227 */             byte[] pdfasbytes = JasperExportManager.exportReportToPdf(jasperPrint);
/*     */             
/* 229 */             outstream = response.getOutputStream();
/* 230 */             response.setContentType("application/pdf");
/* 231 */             response.setContentLength(pdfasbytes.length);
/* 232 */             outstream.write(pdfasbytes, 0, pdfasbytes.length);
/*     */           } else {
/*     */             
/* 235 */             System.out.println("No data");
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 241 */       if (reportType.equalsIgnoreCase("LRInv")) {
/* 242 */         String plotNumber = request.getParameter("plotNumber");
/* 243 */         String approvedUser = request.getParameter("approvedUser");
/* 244 */         String year = request.getParameter("year");
/* 245 */         parameters.put("plotNumber", plotNumber);
/* 246 */         parameters.put("approvedUser", approvedUser);
/* 247 */         parameters.put("year", year);
/*     */         
/* 249 */         System.out.println("calling servlet## LandInvoice Pdf"+this.REPORTS_PATH);
/* 250 */         jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/RptLandrateInvoice.jrxml");
/*     */         
/* 252 */         jasperReport = JasperCompileManager.compileReport(jasperDesign);
/* 253 */         jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */         
/* 256 */         if (jasperPrint.getPages().size() != 0) {
/* 257 */           byte[] pdfasbytes = JasperExportManager.exportReportToPdf(jasperPrint);
/*     */           
/* 259 */           outstream = response.getOutputStream();
/* 260 */           response.setContentType("application/pdf");
/* 261 */           response.setContentLength(pdfasbytes.length);
/* 262 */           outstream.write(pdfasbytes, 0, pdfasbytes.length);
/*     */         } else {
/*     */           
/* 265 */           System.out.println("No data");
/*     */         } 
/*     */       } 
/*     */ //landRates Report
if (reportType.equalsIgnoreCase("LRP")) {
	String fromDate = request.getParameter("FrDt");
	String toDate = request.getParameter("ToDt");
	String subcounty = request.getParameter("subcounty");

	DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
	// Parsing the date
	DateTime from = dtf.parseDateTime(fromDate);
	DateTime to = dtf.parseDateTime(toDate);
	// Format for output
	DateTimeFormatter dtFrm = DateTimeFormat
			.forPattern("dd-MMM-yyyy");
	// String memberNo = request.getParameter("MemNo");
	parameters.put("FromDt", fromDate);
	parameters.put("ToDt", toDate);
	parameters.put("subcounty", subcounty);
	// parameters.put("MemNo", memberNo);
	//parameters.put("dtFrm", dtFrm.print(from).toString());
	//parameters.put("toFrm", dtFrm.print(to).toString());

	if (exportType.equalsIgnoreCase("P")) {
		System.out.println("calling servlet##Transaction Summary Pdf"+this.REPORTS_PATH);
		jasperDesign = JRXmlLoader
				.load(this.REPORTS_PATH +"rptLandRate.jrxml");
		jasperReport = JasperCompileManager
				.compileReport(jasperDesign);
		jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, connection);

		if (jasperPrint.getPages().size() != 0) {
			byte[] pdfasbytes = JasperExportManager
					.exportReportToPdf(jasperPrint);
			outstream = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setContentLength(pdfasbytes.length);
			outstream.write(pdfasbytes, 0, pdfasbytes.length);

		} else {
			System.out.println("No data");
		}
	}else{
		System.out.println("calling servlet## Summary Excel");
		jasperDesign = JRXmlLoader
				.load(this.REPORTS_PATH +"rptLandRate_xls.jrxml");
		jasperReport = JasperCompileManager
				.compileReport(jasperDesign);
		jasperPrint = JasperFillManager.fillReport(jasperReport,
				parameters, connection);

		if (jasperPrint.getPages().size() != 0) {
			JRXlsxExporter exporter = getCommonXlsxExporter();
		       ByteArrayOutputStream baos = new ByteArrayOutputStream();
		                exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
		                exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
		                exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
		                exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
		                exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
		                exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
		                exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
						
		             exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
		             exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, baos); // fill byte array output stream

		             exporter.exportReport();
		             
		             response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		             response.setHeader("Content-disposition", "attachment; filename=" + "Landrate report"+".xlsx");
		             response.setContentLength(baos.size());
		             response.getOutputStream().write(baos.toByteArray());      

		} else {
			System.out.println("No data");
		}
	}
}
/*     */       
/* 270 */       if (reportType.equalsIgnoreCase("LRRec")) {
/* 271 */         String plotNumber = request.getParameter("plotNumber");
/* 272 */         String paidUser = request.getParameter("paidUser");
/* 273 */         String year = request.getParameter("year");
/* 274 */         parameters.put("plotNumber", plotNumber);
/* 275 */         parameters.put("paidUser", paidUser);
/* 276 */         parameters.put("year", year);
/*     */         
/* 278 */         System.out.println("calling servlet##Land Invoice Receipt Pdf");
/* 279 */         jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/RptLandInvoiceReceipt.jrxml");
/*     */ 
/*     */         
/* 282 */         jasperReport = JasperCompileManager.compileReport(jasperDesign);
/*     */         
/* 284 */         jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */         
/* 287 */         if (jasperPrint.getPages().size() != 0)
/*     */         {
/* 289 */           if (jasperPrint.getPages().size() != 0) {
/*     */             
/* 291 */             byte[] pdfasbytes = JasperExportManager.exportReportToPdf(jasperPrint);
/*     */             
/* 293 */             outstream = response.getOutputStream();
/* 294 */             response.setContentType("application/pdf");
/* 295 */             response.setContentLength(pdfasbytes.length);
/* 296 */             outstream.write(pdfasbytes, 0, pdfasbytes.length);
/*     */           } else {
/*     */             
/* 299 */             System.out.println("No data");
/*     */           } 
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 305 */       if (reportType.equalsIgnoreCase("TX")) {
/* 306 */         String fromDate = request.getParameter("FrDt");
/* 307 */         String toDate = request.getParameter("ToDt");
/* 308 */         DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
/*     */         
/* 310 */         DateTime from = dtf.parseDateTime(fromDate);
/* 311 */         DateTime to = dtf.parseDateTime(toDate);
/*     */         
/* 313 */         DateTimeFormatter dtFrm = DateTimeFormat.forPattern("dd-MMM-yyyy");
/*     */ 
/*     */         
/* 316 */         parameters.put("FromDt", fromDate);
/* 317 */         parameters.put("ToDt", toDate);
/*     */         
/* 319 */         parameters.put("dtFrm", dtFrm.print((ReadableInstant)from).toString());
/* 320 */         parameters.put("toFrm", dtFrm.print((ReadableInstant)to).toString());
/*     */         
/* 322 */         if (!exportType.equalsIgnoreCase("P")) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 343 */           System.out.println("calling servlet## Summary Excel");
/* 344 */           jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/rpt_transaction_details_xls.jrxml");
/*     */           
/* 346 */           jasperReport = JasperCompileManager.compileReport(jasperDesign);
/*     */           
/* 348 */           jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */           
/* 351 */           if (jasperPrint.getPages().size() != 0) {
/* 352 */             JRXlsxExporter exporter = getCommonXlsxExporter();
/* 353 */             ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 354 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
/* 355 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
/* 356 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
/* 357 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
/* 358 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
/* 359 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
/* 360 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
/*     */             
/* 362 */             exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
/* 363 */             exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, baos);
/*     */             
/* 365 */             exporter.exportReport();
/*     */             
/* 367 */             response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
/* 368 */             response.setHeader("Content-disposition", "attachment; filename=TransactionDetails.xlsx");
/* 369 */             response.setContentLength(baos.size());
/* 370 */             response.getOutputStream().write(baos.toByteArray());
/*     */           } else {
/*     */             
/* 373 */             System.out.println("No data");
/*     */           } 
/*     */         } 
/*     */       } 
/* 377 */       if (reportType.equalsIgnoreCase("S")) {
/* 378 */         String fromDate = request.getParameter("FrDt");
/* 379 */         String toDate = request.getParameter("ToDt");
/* 380 */         DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
/*     */         
/* 382 */         DateTime from = dtf.parseDateTime(fromDate);
/* 383 */         DateTime to = dtf.parseDateTime(toDate);
/* 384 */         DateTimeFormatter dtFrm = DateTimeFormat.forPattern("dd-MMM-yyyy");
/*     */         
/* 386 */         parameters.put("FromDt", fromDate);
/* 387 */         parameters.put("ToDt", toDate);
/* 388 */         parameters.put("dtFrm", dtFrm.print((ReadableInstant)from).toString());
/* 389 */         parameters.put("toFrm", dtFrm.print((ReadableInstant)to).toString());
/*     */         
/* 391 */         if (exportType.equalsIgnoreCase("P")) {
/* 392 */           System.out.println("calling servlet## Summary Pdf");
/* 393 */           jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/RptServiceSummary.jrxml");
/*     */           
/* 395 */           jasperReport = JasperCompileManager.compileReport(jasperDesign);
/*     */           
/* 397 */           jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */           
/* 400 */           if (jasperPrint.getPages().size() != 0) {
/* 401 */             byte[] pdfasbytes = JasperExportManager.exportReportToPdf(jasperPrint);
/*     */             
/* 403 */             outstream = response.getOutputStream();
/* 404 */             response.setContentType("application/pdf");
/* 405 */             response.setContentLength(pdfasbytes.length);
/* 406 */             outstream.write(pdfasbytes, 0, pdfasbytes.length);
/*     */           } else {
/*     */             
/* 409 */             System.out.println("No data");
/*     */           } 
/*     */         } else {
/* 412 */           System.out.println("calling servlet## Summary Excel");
/* 413 */           jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/rpt_service_summary_xls.jrxml");
/*     */           
/* 415 */           jasperReport = JasperCompileManager.compileReport(jasperDesign);
/*     */           
/* 417 */           jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */           
/* 420 */           if (jasperPrint.getPages().size() != 0) {
/* 421 */             JRXlsxExporter exporter = getCommonXlsxExporter();
/* 422 */             ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 423 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
/* 424 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
/* 425 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
/* 426 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
/* 427 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
/* 428 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
/* 429 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
/* 430 */             exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
/* 431 */             exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, baos);
/*     */             
/* 433 */             exporter.exportReport();
/*     */             
/* 435 */             response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
/* 436 */             response.setHeader("Content-disposition", "attachment; filename=ServiceWiseSummary.xlsx");
/* 437 */             response.setContentLength(baos.size());
/* 438 */             response.getOutputStream().write(baos.toByteArray());
/*     */           } else {
/*     */             
/* 441 */             System.out.println("No data");
/*     */           } 
/*     */         } 
/*     */       } 
/* 445 */       if (reportType.equalsIgnoreCase("M")) {
/* 446 */         String fromDate = request.getParameter("FrDt");
/* 447 */         String toDate = request.getParameter("ToDt");
/* 448 */         DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
/*     */         
/* 450 */         DateTime from = dtf.parseDateTime(fromDate);
/* 451 */         DateTime to = dtf.parseDateTime(toDate);
/* 452 */         DateTimeFormatter dtFrm = DateTimeFormat.forPattern("dd-MMM-yyyy");
/*     */         
/* 454 */         parameters.put("FromDt", fromDate);
/* 455 */         parameters.put("ToDt", toDate);
/* 456 */         parameters.put("dtFrm", dtFrm.print((ReadableInstant)from).toString());
/* 457 */         parameters.put("toFrm", dtFrm.print((ReadableInstant)to).toString());
/*     */         
/* 459 */         if (exportType.equalsIgnoreCase("P")) {
/* 460 */           System.out.println("calling servlet## Summary Pdf");
/* 461 */           jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/RptServiceSummary.jrxml");
/*     */           
/* 463 */           jasperReport = JasperCompileManager.compileReport(jasperDesign);
/*     */           
/* 465 */           jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */           
/* 468 */           if (jasperPrint.getPages().size() != 0) {
/* 469 */             byte[] pdfasbytes = JasperExportManager.exportReportToPdf(jasperPrint);
/*     */             
/* 471 */             outstream = response.getOutputStream();
/* 472 */             response.setContentType("application/pdf");
/* 473 */             response.setContentLength(pdfasbytes.length);
/* 474 */             outstream.write(pdfasbytes, 0, pdfasbytes.length);
/*     */           } else {
/*     */             
/* 477 */             System.out.println("No data");
/*     */           } 
/*     */         } else {
/* 480 */           System.out.println("calling servlet## Summary Excel");
/* 481 */           jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/rpt_marketwise_summary_xls.jrxml");
/*     */           
/* 483 */           jasperReport = JasperCompileManager.compileReport(jasperDesign);
/*     */           
/* 485 */           jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */           
/* 488 */           if (jasperPrint.getPages().size() != 0) {
/* 489 */             JRXlsxExporter exporter = getCommonXlsxExporter();
/* 490 */             ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 491 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
/* 492 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
/* 493 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
/* 494 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
/* 495 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
/* 496 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
/* 497 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
/* 498 */             exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
/* 499 */             exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, baos);
/*     */             
/* 501 */             exporter.exportReport();
/*     */             
/* 503 */             response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
/* 504 */             response.setHeader("Content-disposition", "attachment; filename=MarketWiseSummary.xlsx");
/* 505 */             response.setContentLength(baos.size());
/* 506 */             response.getOutputStream().write(baos.toByteArray());
/*     */           } else {
/*     */             
/* 509 */             System.out.println("No data");
/*     */           } 
/*     */         } 
/*     */       } 
/* 513 */       if (reportType.equalsIgnoreCase("D")) {
/* 514 */         String fromDate = request.getParameter("FrDt");
/* 515 */         String toDate = request.getParameter("ToDt");
/* 516 */         DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
/*     */         
/* 518 */         DateTime from = dtf.parseDateTime(fromDate);
/* 519 */         DateTime to = dtf.parseDateTime(toDate);
/* 520 */         DateTimeFormatter dtFrm = DateTimeFormat.forPattern("dd-MMM-yyyy");
/*     */         
/* 522 */         parameters.put("FromDt", fromDate);
/* 523 */         parameters.put("ToDt", toDate);
/* 524 */         parameters.put("dtFrm", dtFrm.print((ReadableInstant)from).toString());
/* 525 */         parameters.put("toFrm", dtFrm.print((ReadableInstant)to).toString());
/*     */         
/* 527 */         if (exportType.equalsIgnoreCase("P")) {
/* 528 */           System.out.println("calling servlet## Summary Pdf");
/* 529 */           jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/RptServiceSummary.jrxml");
/*     */           
/* 531 */           jasperReport = JasperCompileManager.compileReport(jasperDesign);
/*     */           
/* 533 */           jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */           
/* 536 */           if (jasperPrint.getPages().size() != 0) {
/* 537 */             byte[] pdfasbytes = JasperExportManager.exportReportToPdf(jasperPrint);
/*     */             
/* 539 */             outstream = response.getOutputStream();
/* 540 */             response.setContentType("application/pdf");
/* 541 */             response.setContentLength(pdfasbytes.length);
/* 542 */             outstream.write(pdfasbytes, 0, pdfasbytes.length);
/*     */           } else {
/*     */             
/* 545 */             System.out.println("No data");
/*     */           } 
/*     */         } else {
/* 548 */           System.out.println("calling servlet## Summary Excel");
/* 549 */           jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/rpt_device_wise_summary_xls.jrxml");
/*     */           
/* 551 */           jasperReport = JasperCompileManager.compileReport(jasperDesign);
/*     */           
/* 553 */           jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */           ByteArrayOutputStream baos;
/* 556 */           if (jasperPrint.getPages().size() != 0) {
	baos = new ByteArrayOutputStream();
/* 557 */             JRXlsxExporter exporter = getCommonXlsxExporter();
/* 558 */                     exporter.setParameter(JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
exporter.setParameter(JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
exporter.setParameter(JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
exporter.setParameter(JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
exporter.setParameter(JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
exporter.setParameter(JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, baos);
/*     */             
/* 569 */             exporter.exportReport();
/*     */             
/* 571 */             response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
/* 572 */             response.setHeader("Content-disposition", "attachment; filename=DeviceWiseSummary.xlsx");
/* 573 */             response.setContentLength(baos.size());
/* 574 */             response.getOutputStream().write(baos.toByteArray());
/*     */           } else {
/*     */             
/* 577 */             System.out.println("No data");
/*     */           } 
/*     */         } 
/*     */       } 
/* 581 */       if (reportType.equalsIgnoreCase("U")) {
/* 582 */         String fromDate = request.getParameter("FrDt");
/* 583 */         String toDate = request.getParameter("ToDt");
/* 584 */         DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
/*     */         
/* 586 */         DateTime from = dtf.parseDateTime(fromDate);
/* 587 */         DateTime to = dtf.parseDateTime(toDate);
/* 588 */         DateTimeFormatter dtFrm = DateTimeFormat.forPattern("dd-MMM-yyyy");
/*     */         
/* 590 */         parameters.put("FromDt", fromDate);
/* 591 */         parameters.put("ToDt", toDate);
/* 592 */         parameters.put("dtFrm", dtFrm.print((ReadableInstant)from).toString());
/* 593 */         parameters.put("toFrm", dtFrm.print((ReadableInstant)to).toString());
/*     */         
/* 595 */         if (exportType.equalsIgnoreCase("P")) {
/* 596 */           System.out.println("calling servlet## Summary Pdf");
/* 597 */           jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/RptServiceSummary.jrxml");
/*     */           
/* 599 */           jasperReport = JasperCompileManager.compileReport(jasperDesign);
/*     */           
/* 601 */           jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */           
/* 604 */           if (jasperPrint.getPages().size() != 0) {
/* 605 */             byte[] pdfasbytes = JasperExportManager.exportReportToPdf(jasperPrint);
/*     */             
/* 607 */             outstream = response.getOutputStream();
/* 608 */             response.setContentType("application/pdf");
/* 609 */             response.setContentLength(pdfasbytes.length);
/* 610 */             outstream.write(pdfasbytes, 0, pdfasbytes.length);
/*     */           } else {
/*     */             
/* 613 */             System.out.println("No data");
/*     */           } 
/*     */         } else {
/* 616 */           System.out.println("calling servlet## Summary Excel");
/* 617 */           jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/rpt_userwise_summary_xls.jrxml");
/*     */           
/* 619 */           jasperReport = JasperCompileManager.compileReport(jasperDesign);
/*     */           
/* 621 */           jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */           
/* 624 */           if (jasperPrint.getPages().size() != 0) {
/* 625 */             JRXlsxExporter exporter = getCommonXlsxExporter();
/* 626 */             ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 627 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
/* 628 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
/* 629 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
/* 630 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
/* 631 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
/* 632 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
/* 633 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
/* 634 */             exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
/* 635 */             exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, baos);
/*     */             
/* 637 */             exporter.exportReport();
/*     */             
/* 639 */             response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
/* 640 */             response.setHeader("Content-disposition", "attachment; filename=UserWiseSummary.xlsx");
/* 641 */             response.setContentLength(baos.size());
/* 642 */             response.getOutputStream().write(baos.toByteArray());
/*     */           } else {
/*     */             
/* 645 */             System.out.println("No data");
/*     */           } 
/*     */         } 
/*     */       } 
/* 649 */       if (reportType.equalsIgnoreCase("W")) {
/* 650 */         String fromDate = request.getParameter("FrDt");
/* 651 */         String toDate = request.getParameter("ToDt");
/* 652 */         DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
/*     */         
/* 654 */         DateTime from = dtf.parseDateTime(fromDate);
/* 655 */         DateTime to = dtf.parseDateTime(toDate);
/* 656 */         DateTimeFormatter dtFrm = DateTimeFormat.forPattern("dd-MMM-yyyy");
/*     */         
/* 658 */         parameters.put("FromDt", fromDate);
/* 659 */         parameters.put("ToDt", toDate);
/* 660 */         parameters.put("dtFrm", dtFrm.print((ReadableInstant)from).toString());
/* 661 */         parameters.put("toFrm", dtFrm.print((ReadableInstant)to).toString());
/*     */         
/* 663 */         if (exportType.equalsIgnoreCase("P")) {
/* 664 */           System.out.println("calling servlet## Summary Pdf");
/* 665 */           jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/RptServiceSummary.jrxml");
/*     */           
/* 667 */           jasperReport = JasperCompileManager.compileReport(jasperDesign);
/*     */           
/* 669 */           jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */           
/* 672 */           if (jasperPrint.getPages().size() != 0) {
/* 673 */             byte[] pdfasbytes = JasperExportManager.exportReportToPdf(jasperPrint);
/*     */             
/* 675 */             outstream = response.getOutputStream();
/* 676 */             response.setContentType("application/pdf");
/* 677 */             response.setContentLength(pdfasbytes.length);
/* 678 */             outstream.write(pdfasbytes, 0, pdfasbytes.length);
/*     */           } else {
/*     */             
/* 681 */             System.out.println("No data");
/*     */           } 
/*     */         } else {
/* 684 */           System.out.println("calling servlet## Summary Excel");
/* 685 */           jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/rpt_wardwise_summary_xls.jrxml");
/*     */           
/* 687 */           jasperReport = JasperCompileManager.compileReport(jasperDesign);
/*     */           
/* 689 */           jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */           
/* 692 */           if (jasperPrint.getPages().size() != 0) {
/* 693 */             JRXlsxExporter exporter = getCommonXlsxExporter();
/* 694 */             ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 695 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
/* 696 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
/* 697 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
/* 698 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
/* 699 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
/* 700 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
/* 701 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
/* 702 */             exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
/* 703 */             exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, baos);
/*     */             
/* 705 */             exporter.exportReport();
/*     */             
/* 707 */             response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
/* 708 */             response.setHeader("Content-disposition", "attachment; filename=WardWiseSummary.xlsx");
/* 709 */             response.setContentLength(baos.size());
/* 710 */             response.getOutputStream().write(baos.toByteArray());
/*     */           } else {
/*     */             
/* 713 */             System.out.println("No data");
/*     */           } 
/*     */         } 
/*     */       } 
/* 717 */       if (reportType.equalsIgnoreCase("SB")) {
/* 718 */         String fromDate = request.getParameter("FrDt");
/* 719 */         String toDate = request.getParameter("ToDt");
/* 720 */         DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyy-MM-dd");
/*     */         
/* 722 */         DateTime from = dtf.parseDateTime(fromDate);
/* 723 */         DateTime to = dtf.parseDateTime(toDate);
/* 724 */         DateTimeFormatter dtFrm = DateTimeFormat.forPattern("dd-MMM-yyyy");
/*     */         
/* 726 */         parameters.put("FromDt", fromDate);
/* 727 */         parameters.put("ToDt", toDate);
/* 728 */         parameters.put("dtFrm", dtFrm.print((ReadableInstant)from).toString());
/* 729 */         parameters.put("toFrm", dtFrm.print((ReadableInstant)to).toString());
/*     */         
/* 731 */         if (exportType.equalsIgnoreCase("P")) {
/* 732 */           System.out.println("calling servlet## Summary Pdf");
/* 733 */           jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/RptServiceSummary.jrxml");
/*     */           
/* 735 */           jasperReport = JasperCompileManager.compileReport(jasperDesign);
/*     */           
/* 737 */           jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */           
/* 740 */           if (jasperPrint.getPages().size() != 0) {
/* 741 */             byte[] pdfasbytes = JasperExportManager.exportReportToPdf(jasperPrint);
/*     */             
/* 743 */             outstream = response.getOutputStream();
/* 744 */             response.setContentType("application/pdf");
/* 745 */             response.setContentLength(pdfasbytes.length);
/* 746 */             outstream.write(pdfasbytes, 0, pdfasbytes.length);
/*     */           } else {
/*     */             
/* 749 */             System.out.println("No data");
/*     */           } 
/*     */         } else {
/* 752 */           System.out.println("calling servlet## Summary Excel");
/* 753 */           jasperDesign = JRXmlLoader.load(this.REPORTS_PATH + "/rpt_subcountywise_summary_xls.jrxml");
/*     */           
/* 755 */           jasperReport = JasperCompileManager.compileReport(jasperDesign);
/*     */           
/* 757 */           jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, connection);
/*     */ 
/*     */           
/* 760 */           if (jasperPrint.getPages().size() != 0) {
/* 761 */             JRXlsxExporter exporter = getCommonXlsxExporter();
/* 762 */             ByteArrayOutputStream baos = new ByteArrayOutputStream();
/* 763 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.TRUE);
/* 764 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.TRUE);
/* 765 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
/* 766 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
/* 767 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
/* 768 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
/* 769 */             exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_IGNORE_GRAPHICS, Boolean.TRUE);
/* 770 */             exporter.setParameter(JRXlsExporterParameter.JASPER_PRINT, jasperPrint);
/* 771 */             exporter.setParameter(JRXlsExporterParameter.OUTPUT_STREAM, baos);
/*     */             
/* 773 */             exporter.exportReport();
/*     */             
/* 775 */             response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
/* 776 */             response.setHeader("Content-disposition", "attachment; filename=SubCountyWiseSummary.xlsx");
/* 777 */             response.setContentLength(baos.size());
/* 778 */             response.getOutputStream().write(baos.toByteArray());
/*     */           } else {
/*     */             
/* 781 */             System.out.println("No data");
/*     */           } 
/*     */         } 
/*     */       } 
/* 785 */     } catch (JRException e) {
/*     */       
/* 787 */       e.printStackTrace();
/* 788 */     } catch (NamingException e) {
/*     */       
/* 790 */       e.printStackTrace();
/* 791 */     } catch (SQLException e) {
/*     */       
/* 793 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private JRXlsxExporter getCommonXlsxExporter() {
/* 807 */     JRXlsxExporter exporter = new JRXlsxExporter();
/* 808 */     exporter.setParameter(JRXlsExporterParameter.IGNORE_PAGE_MARGINS, Boolean.TRUE);
/*     */     
/* 810 */     exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_ONE_PAGE_PER_SHEET, Boolean.FALSE);
/*     */     
/* 812 */     exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_AUTO_DETECT_CELL_TYPE, Boolean.TRUE);
/*     */     
/* 814 */     exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.FALSE);
/*     */     
/* 816 */     exporter.setParameter((JRExporterParameter)JRXlsExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.TRUE);
/*     */ 
/*     */     
/* 819 */     exporter.setParameter((JRExporterParameter)JExcelApiExporterParameter.IS_DETECT_CELL_TYPE, Boolean.TRUE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 825 */     return exporter;
/*     */   }
/*     */   
/*     */   public ErevenueResponse UpdatePermitStatus(String permitNo, String status) {
/* 829 */     Connection connection = null;
/* 830 */     PreparedStatement preparedStatement = null;
/* 831 */     ResultSet resultSet = null;
/* 832 */     String qrImage = "";
/* 833 */     BufferedImage image = null;
/*     */     try {
/* 835 */       connection = this.dataSource.getConnection();
/*     */       
/* 837 */       preparedStatement = connection.prepareStatement(Queryconstants.updPermitStatus);
/*     */ 
/*     */       
/* 840 */       preparedStatement.setString(1, status);
/*     */       
/* 842 */       preparedStatement.setString(2, permitNo);
/*     */       
/* 844 */       return (preparedStatement.executeUpdate() > 0) ? new ErevenueResponse(200, "Records Updated") : new ErevenueResponse(201, "Nothing To Update");
/*     */     
/*     */     }
/* 847 */     catch (SQLException sqlEx) {
/* 848 */       sqlEx.printStackTrace();
/* 849 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     }
/* 851 */     catch (Exception ex) {
/* 852 */       ex.printStackTrace();
/* 853 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     } finally {
/* 855 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\classes\com\compulynx\erevenue\bo\servlet\Reports.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */