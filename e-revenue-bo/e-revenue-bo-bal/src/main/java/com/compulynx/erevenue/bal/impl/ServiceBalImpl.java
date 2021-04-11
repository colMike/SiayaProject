/*    */ package com.compulynx.erevenue.bal.impl;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.ServiceBal;
/*    */ import com.compulynx.erevenue.dal.impl.ServiceDalImpl;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.Params;
/*    */ import com.compulynx.erevenue.models.Service;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class ServiceBalImpl
/*    */   implements ServiceBal
/*    */ {
/*    */   @Autowired
/*    */   ServiceDalImpl serviceDal;
/*    */   
/*    */   public ErevenueResponse UpdateService(Service service) {
/* 26 */     return this.serviceDal.UpdateService(service);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Service> GetAllServices() {
/* 32 */     return this.serviceDal.GetAllServices();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<Params> GetAllParams() {
/* 37 */     return this.serviceDal.GetAllParams();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Service> GetSubServiceById(int serviceId) {
/* 43 */     return this.serviceDal.GetSubServiceById(serviceId);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ErevenueResponse UpdateParam(Params param) {
/* 49 */     return this.serviceDal.UpdateParam(param);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Service> GetAllSubServices() {
/* 55 */     return this.serviceDal.GetAllSubServices();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public ErevenueResponse UpdateSerPrice(Service ser) {
/* 64 */     return this.serviceDal.UpdateSerPrice(ser);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Service> GetMarketSubServices(int marketId) {
/* 70 */     return this.serviceDal.GetMarketSubServices(marketId);
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\impl\ServiceBalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */