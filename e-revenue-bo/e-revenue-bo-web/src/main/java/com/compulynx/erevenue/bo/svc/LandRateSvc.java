/*     */ package com.compulynx.erevenue.bo.svc;
/*     */ 
/*     */ import com.compulynx.erevenue.bal.impl.LandRateBalImpl;
import com.compulynx.erevenue.models.CompasResponse;
/*     */ import com.compulynx.erevenue.models.ErevenueResponse;
/*     */ import com.compulynx.erevenue.models.LandRate;
import com.sun.jersey.multipart.FormDataParam;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
/*     */ import java.util.List;
/*     */ import javax.ws.rs.Consumes;
/*     */ import javax.ws.rs.GET;
/*     */ import javax.ws.rs.POST;
/*     */ import javax.ws.rs.Path;
/*     */ import javax.ws.rs.PathParam;
/*     */ import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
/*     */ import javax.ws.rs.core.Response;
/*     */ import org.springframework.beans.factory.annotation.Autowired;
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
/*     */ @Component
/*     */ @Path("/landrate")
/*     */ public class LandRateSvc
/*     */ {
/*     */   @Autowired
/*     */   LandRateBalImpl landrateBal;
String TOMCAT_HOME = "";
String fileName = "";
String PATH = "";
CompasResponse response = null;
@POST
@Path("/uploadPlotRentDetails/")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
public Response uploadFilePlot(@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("createdBy") String createdBy, @FormDataParam("branchId") String branchId,
		@FormDataParam("file") FormDataContentDisposition fileDetail) {
	TOMCAT_HOME = System.getProperty("catalina.base");
	fileName = fileDetail.getFileName();
	PATH = TOMCAT_HOME + "/plotrent_uploads/";
	//logger.info("Uploading file now Backend##" + PATH + fileName);
	String uploadedFileLocation = PATH + fileName;
	if(!(fileName.trim().split("\\.")[1].equalsIgnoreCase("xlsx")) || fileName.length() ==0){
		return Response.status(200).entity(new CompasResponse(201,"Wrong file format uploaded, Please try again")).build();
	}
	// save it
	if (writeToFile(uploadedInputStream, uploadedFileLocation)) {

		response = new CompasResponse();

		response = landrateBal.UploadPlot(uploadedFileLocation, fileName, createdBy, Integer.parseInt(branchId));
	} else {
		return Response.status(200).entity(new CompasResponse(201, "Error uploading file, Please try again"))
				.build();
	}
	return Response.status(200).entity(response).build();
}
@POST
@Path("/uploadLandRateDetails/")
@Consumes(MediaType.MULTIPART_FORM_DATA)
@Produces(MediaType.APPLICATION_JSON)
public Response uploadFileLand(@FormDataParam("file") InputStream uploadedInputStream,
		@FormDataParam("createdBy") String createdBy, @FormDataParam("branchId") String branchId,
		@FormDataParam("file") FormDataContentDisposition fileDetail) {
	TOMCAT_HOME = System.getProperty("catalina.base");
	fileName = fileDetail.getFileName();
	PATH = TOMCAT_HOME + "/landrate_uploads/";
	//logger.info("Uploading file now Backend##" + PATH + fileName);
	String uploadedFileLocation = PATH + fileName;
	if(!(fileName.trim().split("\\.")[1].equalsIgnoreCase("xlsx")) || fileName.length() ==0){
		return Response.status(200).entity(new CompasResponse(201,"Wrong file format uploaded, Please try again")).build();
	}
	System.out.println("============here1=================");
	// save it
	if (writeToFile(uploadedInputStream, uploadedFileLocation)) {

		response = new CompasResponse();

		response = landrateBal.UploadLand(uploadedFileLocation, fileName, createdBy, Integer.parseInt(branchId));
	} else {
		return Response.status(200).entity(new CompasResponse(201, "Error uploading file, Please try again"))
				.build();
	}
	return Response.status(200).entity(response).build();
}

/*     */   
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtRegs")
/*     */   public Response GetAllRegs() {
/*  39 */     List<LandRate> lr = this.landrateBal.GetAllRegs();
/*  40 */     return Response.status(200).entity(lr).build();
/*     */   }
/*     */   
/*     */   @POST
/*     */   @Produces({"application/json"})
/*     */   @Consumes({"application/json"})
/*     */   @Path("/updRegistration")
/*     */   public Response CreateRegistrationForm(LandRate lr) {
/*  48 */     ErevenueResponse response = this.landrateBal.CreateRegistrationForm(lr);
/*  49 */     return Response.status(200).entity(response).build();
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtlandInvoices")
/*     */   public Response GetAllLandInvoices() {
/*  56 */     List<LandRate> landinvoices = this.landrateBal.GetAllLandInvoices();
/*  57 */     return Response.status(200).entity(landinvoices).build();
/*     */   }
/*     */   @POST
/*     */   @Produces({"application/json"})
/*     */   @Consumes({"application/json"})
/*     */   @Path("/updlandInvoiceStatus")
/*     */   public Response UpdateLandInvocieStatus(LandRate lr) {
/*  64 */     ErevenueResponse response = this.landrateBal.UpdateLandInvoiceStatus(lr);
/*  65 */     return Response.status(200).entity(response).build();
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtPaidlands")
/*     */   public Response GetPaidlands() {
/*  72 */     List<LandRate> lr = this.landrateBal.GetPaidLandDetails();
/*  73 */     return Response.status(200).entity(lr).build();
/*     */   }
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtPermitlands")
/*     */   public Response GetPermitlands() {
/*  79 */     List<LandRate> lr = this.landrateBal.GetAllLandPermits();
/*  80 */     return Response.status(200).entity(lr).build();
/*     */   }
/*     */   @POST
/*     */   @Produces({"application/json"})
/*     */   @Consumes({"application/json"})
/*     */   @Path("/updLandRenew")
/*     */   public Response UpdateLandPermitRenewal(LandRate lr) {
/*  87 */     ErevenueResponse response = this.landrateBal.UpdateLandPermitRenewal(lr);
/*  88 */     return Response.status(200).entity(response).build();
/*     */   }
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtLandsByLinkId/{linkId},{nationalIdNo},{agentId}")
/*     */   public Response GetAllLandsByLinkId(@PathParam("linkId") int linkId, @PathParam("nationalIdNo") String nationalIdNo, @PathParam("agentId") int agentId) {
/*  94 */     List<LandRate> lrs = this.landrateBal.GetAllLandsByLinkId(linkId, nationalIdNo, agentId);
/*  95 */     return Response.status(200).entity(lrs).build();
/*     */   }
/*     */   
/*     */   @GET
/*     */   @Produces({"application/json"})
/*     */   @Path("/gtLandInvoicesByLinkId/{linkId},{nationalIdNo},{agentId}")
/*     */   public Response GetAllLandInvoicesByLinkId(@PathParam("linkId") int linkId, @PathParam("nationalIdNo") String nationalIdNo, @PathParam("agentId") int agentId) {
/* 102 */     List<LandRate> lrs = this.landrateBal.GetAllLandInvoicesByLinkId(linkId, nationalIdNo, agentId);
/* 103 */     return Response.status(200).entity(lrs).build();
/*     */   }

private boolean writeToFile(InputStream uploadedInputStream, String uploadedFileLocation) {
	//logger.info("#Writing to file function##");
	try {
		OutputStream out = new FileOutputStream(new File(uploadedFileLocation));
		int read = 0;
		byte[] bytes = new byte[1024];

		out = new FileOutputStream(new File(uploadedFileLocation));
		while ((read = uploadedInputStream.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.flush();
		out.close();

		//logger.info("##Sucessfully written to file");
		return true;
	} catch (IOException e) {
		e.printStackTrace();
	}

	//logger.info("##Failed to write to file");
	return false;
}
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\classes\com\compulynx\erevenue\bo\svc\LandRateSvc.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */