/*    */ package com.compulynx.erevenue.bal.impl;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.SubMarketBal;
/*    */ import com.compulynx.erevenue.dal.impl.SubMarketDalImpl;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.Market;
/*    */ import com.compulynx.erevenue.models.SubMarket;
/*    */ import java.util.List;
/*    */ import org.springframework.beans.factory.annotation.Autowired;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class SubMarketBalImpl
/*    */   implements SubMarketBal
/*    */ {
/*    */   @Autowired
/*    */   SubMarketDalImpl subMarketDal;
/*    */   
/*    */   public ErevenueResponse UpdateSubMarket(SubMarket submarket) {
/* 30 */     return this.subMarketDal.UpdateSubMarket(submarket);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<SubMarket> GetAllSubMarkets() {
/* 36 */     return this.subMarketDal.GetAllSubMarkets();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Market> GetActiveMarkets() {
/* 42 */     return this.subMarketDal.GetActiveMarkets();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\impl\SubMarketBalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */