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
/*    */ public class Agent
/*    */ {
/*    */   public int agentId;
/*    */   public String agentDesc;
/*    */   public int branchId;
/*    */   public boolean active;
/*    */   public int createdBy;
/*    */   public int respCode;
/*    */   public List<Programme> programmes;
/*    */   
/*    */   public Agent(int agentId, String agentDesc, int branchId, boolean active, int createdBy, int respCode) {
/* 24 */     this.agentId = agentId;
/* 25 */     this.agentDesc = agentDesc;
/* 26 */     this.branchId = branchId;
/* 27 */     this.active = active;
/* 28 */     this.createdBy = createdBy;
/* 29 */     this.respCode = respCode;
/*    */   }
/*    */   
/*    */   public Agent() {}
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\Agent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */