/*    */ package com.compulynx.erevenue.bal.impl;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.DashBoardBal;
/*    */ import com.compulynx.erevenue.dal.impl.DashBoardDalImpl;
/*    */ import com.compulynx.erevenue.models.DashBoard;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ import org.springframework.stereotype.Component;
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class DashBoardBalImpl
/*    */   implements DashBoardBal
/*    */ {
/*    */   @Autowired
/*    */   DashBoardDalImpl dashBoardDal;
/*    */   
/*    */   public List<DashBoard> GetDashBoardCountDetail() {
/* 19 */     return this.dashBoardDal.GetDashBoardCountDetail();
/*    */   }
/*    */ 
/*    */   
/*    */   public List<DashBoard> GetTransChartDetail() {
/* 24 */     return this.dashBoardDal.GetTransChartDetail();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<DashBoard> GetDashBoardAmountDetail() {
/* 30 */     return this.dashBoardDal.GetDashBoardAmountDetail();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<DashBoard> GetFlowChartCountDetail() {
/* 36 */     return this.dashBoardDal.GetFlowChartCountDetail();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public DashBoard GetMonthStatisticsDetails() {
/* 42 */     return this.dashBoardDal.GetMonthStatisticsDetails();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public DashBoard GetUserStatisticsDetails() {
/* 48 */     return this.dashBoardDal.GetUserStatisticsDetails();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<DashBoard> GetDashBoardCollectionDetail() {
/* 54 */     return this.dashBoardDal.GetDashBoardCollectionDetail();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\impl\DashBoardBalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */