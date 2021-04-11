/*    */ package com.compulynx.erevenue.models;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ 
/*    */ public class DashBoard
/*    */ {
/*    */   public int detailCount;
/*    */   public String detailDescription;
/*    */   public int load;
/*    */   public int purchase;
/*    */   public int totalTransCount;
/*    */   public String monthName;
/*    */   public int respCode;
/*    */   public double totalTxns;
/*    */   public double voidTxns;
/*    */   public double netTxns;
/*    */   public String amount;
/*    */   public double invalidTxns;
/*    */   public String subCountyName;
/*    */   public String userName;
/*    */   public List<DashBoard> currentMonth;
/*    */   public List<DashBoard> previousMonth;
/*    */   public List<DashBoard> twoMonth;
/*    */   public List<DashBoard> userDsc;
/*    */   public List<DashBoard> userAsc;
/*    */   public int tickets;
/*    */   public String service;
/*    */   public int transCount;
/*    */   
/*    */   public DashBoard(String service, int transCount) {
/* 32 */     this.service = service;
/* 33 */     this.transCount = transCount;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public DashBoard(double totalTxns, double voidTxns, double netTxns, String monthName, int respCode) {
/* 39 */     this.totalTxns = totalTxns;
/* 40 */     this.voidTxns = voidTxns;
/* 41 */     this.netTxns = netTxns;
/* 42 */     this.monthName = monthName;
/* 43 */     this.respCode = respCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public DashBoard(int respCode) {
/* 48 */     this.respCode = respCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public DashBoard() {}
/*    */ 
/*    */   
/*    */   public DashBoard(int detailCount, String detailDescription, int respCode) {
/* 57 */     this.detailCount = detailCount;
/* 58 */     this.detailDescription = detailDescription;
/* 59 */     this.respCode = respCode;
/*    */   }
/*    */   
/*    */   public DashBoard(String amount, String detailDescription, int respCode) {
/* 63 */     this.amount = amount;
/* 64 */     this.detailDescription = detailDescription;
/* 65 */     this.respCode = respCode;
/*    */   }
/*    */ 
/*    */   
/*    */   public DashBoard(int load, int purchase, int totalTransCount, String monthName, int respCode) {
/* 70 */     this.load = load;
/* 71 */     this.purchase = purchase;
/* 72 */     this.totalTransCount = totalTransCount;
/* 73 */     this.monthName = monthName;
/* 74 */     this.respCode = respCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public DashBoard(String monthName, double totalTxns, double voidTxns, double invalidTxns, double netTxns) {
/* 80 */     this.monthName = monthName;
/* 81 */     this.totalTxns = totalTxns;
/* 82 */     this.voidTxns = voidTxns;
/* 83 */     this.invalidTxns = invalidTxns;
/* 84 */     this.netTxns = netTxns;
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\DashBoard.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */