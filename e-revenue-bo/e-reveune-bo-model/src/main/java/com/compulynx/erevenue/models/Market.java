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
/*    */ 
/*    */ 
/*    */ public class Market
/*    */ {
/*    */   public int marketId;
/*    */   public String marketCode;
/*    */   public String marketName;
/*    */   public int wardId;
/*    */   public String wardName;
/*    */   public int subCountyId;
/*    */   public String subCountyName;
/*    */   public boolean active;
/*    */   public int createdBy;
/*    */   public int respCode;
/*    */   public int count;
/*    */   public List<Market> marketDetails;
/*    */   public String status;
/*    */   public double serviceValue;
/*    */   
/*    */   public Market() {}
/*    */   
/*    */   public Market(int marketId, String marketCode, String marketName, int wardId, String wardName, int subCountyId, String subCountyName, boolean active, int respCode, int count, String status) {
/* 39 */     this.marketId = marketId;
/* 40 */     this.marketCode = marketCode;
/* 41 */     this.marketName = marketName;
/* 42 */     this.wardId = wardId;
/* 43 */     this.wardName = wardName;
/* 44 */     this.subCountyId = subCountyId;
/* 45 */     this.subCountyName = subCountyName;
/* 46 */     this.active = active;
/* 47 */     this.respCode = respCode;
/* 48 */     this.count = count;
/* 49 */     this.status = status;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Market(int marketId, String marketName) {
/* 55 */     this.marketId = marketId;
/* 56 */     this.marketName = marketName;
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\Market.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */