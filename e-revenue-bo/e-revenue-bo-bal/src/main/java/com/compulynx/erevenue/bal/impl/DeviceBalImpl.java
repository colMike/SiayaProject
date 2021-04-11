/*    */ package com.compulynx.erevenue.bal.impl;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.DeviceBal;
/*    */ import com.compulynx.erevenue.dal.impl.DeviceDalImpl;
/*    */ import com.compulynx.erevenue.models.Device;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.User;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DeviceBalImpl
/*    */   implements DeviceBal
/*    */ {
/*    */   @Autowired
/*    */   DeviceDalImpl deviceDal;
/*    */   
/*    */   public boolean checkDevicedSerialNo(String serialNo) {
/* 21 */     return this.deviceDal.checkDevicedSerialNo(serialNo);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ErevenueResponse UpdateDeviceInfo(Device deviceInfo) {
/* 27 */     return this.deviceDal.UpdateDeviceInfo(deviceInfo);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Device> GetAllDevicesInfo() {
/* 33 */     return this.deviceDal.GetAllDevicesInfo();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ErevenueResponse UpdateIssueDeviceInfo(Device deviceInfo) {
/* 39 */     return this.deviceDal.UpdateIssueDeviceInfo(deviceInfo);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Device> GetAllIssueDevicesInfo() {
/* 45 */     return this.deviceDal.GetAllIssueDevicesInfo();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<User> GetActiveUsers() {
/* 50 */     return this.deviceDal.GetActiveUsers();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Device> GetActiveDevicesInfo() {
/* 56 */     return this.deviceDal.GetActiveDevicesInfo();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\impl\DeviceBalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */