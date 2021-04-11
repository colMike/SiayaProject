/*    */ package com.compulynx.erevenue.bal.impl;
/*    */ 
/*    */ import com.compulynx.erevenue.bal.MarketBal;
/*    */ import com.compulynx.erevenue.dal.impl.MarketDalImpl;
/*    */ import com.compulynx.erevenue.models.ErevenueResponse;
/*    */ import com.compulynx.erevenue.models.Market;
/*    */ import com.compulynx.erevenue.models.Ward;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Component
/*    */ public class MarketBalImpl
/*    */   implements MarketBal
/*    */ {
/*    */   @Autowired
/*    */   MarketDalImpl marketDal;
/*    */   
/*    */   public ErevenueResponse UpdateMarket(Market market) {
/* 30 */     return this.marketDal.UpdateMarket(market);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Market> GetAllMarkets() {
/* 36 */     return this.marketDal.GetAllMarkets();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public List<Ward> GetActiveWards() {
/* 42 */     return this.marketDal.GetActiveWards();
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\impl\MarketBalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */