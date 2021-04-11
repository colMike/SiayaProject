/*    */ package com.compulynx.erevenue.bo.svc;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.impl.DeviceBalImpl;
/*    */ import com.compulynx.erevenue.models.Device;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.User;
/*    */ import java.util.List;
/*    */ import javax.ws.rs.Consumes;
/*    */ import javax.ws.rs.GET;
/*    */ import javax.ws.rs.POST;
/*    */ import javax.ws.rs.Path;
/*    */ import javax.ws.rs.Produces;
/*    */ import javax.ws.rs.core.Response;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ @Path("/device")
/*    */ public class DeviceSvc
/*    */ {
/*    */   @Autowired
/*    */   DeviceBalImpl deviceBal;
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtDevices")
/*    */   public Response GetDevices() {
/* 31 */     List<Device> devices = this.deviceBal.GetAllDevicesInfo();
/* 32 */     return Response.status(200).entity(devices).build();
/*    */   }
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtActiveUsers")
/*    */   public Response GetActiveUsers() {
/* 39 */     List<User> users = this.deviceBal.GetActiveUsers();
/* 40 */     return Response.status(200).entity(users).build();
/*    */   }
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtActiveDevices")
/*    */   public Response GetActiveDevices() {
/* 47 */     List<Device> devices = this.deviceBal.GetActiveDevicesInfo();
/* 48 */     return Response.status(200).entity(devices).build();
/*    */   }
/*    */   
/*    */   @POST
/*    */   @Produces({"application/json"})
/*    */   @Consumes({"application/json"})
/*    */   @Path("/updDevice")
/*    */   public Response UpdateDevice(Device device) {
/* 56 */     ErevenueResponse response = this.deviceBal.UpdateDeviceInfo(device);
/* 57 */     return Response.status(200).entity(response).build();
/*    */   }
/*    */   
/*    */   @GET
/*    */   @Produces({"application/json"})
/*    */   @Path("/gtIssueDevices")
/*    */   public Response GetIssueDevices() {
/* 64 */     List<Device> issueDevices = this.deviceBal.GetAllIssueDevicesInfo();
/* 65 */     return Response.status(200).entity(issueDevices).build();
/*    */   }
/*    */   
/*    */   @POST
/*    */   @Produces({"application/json"})
/*    */   @Consumes({"application/json"})
/*    */   @Path("/updIssueDevice")
/*    */   public Response UpdateIssueDevice(Device device) {
/* 73 */     ErevenueResponse response = this.deviceBal.UpdateIssueDeviceInfo(device);
/* 74 */     return Response.status(200).entity(response).build();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\classes\com\compulynx\erevenue\bo\svc\DeviceSvc.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */