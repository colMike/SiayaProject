/*    */ package com.compulynx.erevenue.models;
/*    */ 
/*    */ public class ErevenueResponse
/*    */ {
/*    */   public int respCode;
/*    */   public String respMessage;
/*    */   
/*    */   public ErevenueResponse(int respCode, String respMessage) {
/*  9 */     this.respCode = respCode;
/* 10 */     this.respMessage = respMessage;
/*    */   }
/*    */   
/*    */   public ErevenueResponse(int respCode) {
/* 14 */     this.respCode = respCode;
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\ErevenueResponse.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */