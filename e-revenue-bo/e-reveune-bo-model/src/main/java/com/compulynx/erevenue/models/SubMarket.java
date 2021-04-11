/*    */ package com.compulynx.erevenue.models;
/*    */ 
/*    */ import java.util.List;
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
/*    */ public class SubMarket
/*    */ {
/*    */   public int subMarketId;
/*    */   public String subMarketCode;
/*    */   public String subMarketName;
/*    */   public int marketId;
/*    */   public String marketName;
/*    */   public boolean active;
/*    */   public String status;
/*    */   public int createdBy;
/*    */   public int respCode;
/*    */   public int count;
/*    */   public List<SubMarket> subMarketDetails;
/*    */   
/*    */   public SubMarket() {}
/*    */   
/*    */   public SubMarket(int subMarketId, String subMarketCode, String subMarketName, int marketId, String marketName, boolean active, int respCode, int count, String status) {
/* 34 */     this.subMarketId = subMarketId;
/* 35 */     this.subMarketCode = subMarketCode;
/* 36 */     this.subMarketName = subMarketName;
/* 37 */     this.marketId = marketId;
/* 38 */     this.marketName = marketName;
/* 39 */     this.active = active;
/* 40 */     this.respCode = respCode;
/* 41 */     this.count = count;
/* 42 */     this.status = status;
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\SubMarket.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */