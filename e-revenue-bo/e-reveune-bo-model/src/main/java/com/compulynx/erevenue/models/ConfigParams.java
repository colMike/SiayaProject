/*    */ package com.compulynx.erevenue.models;
/*    */ 
/*    */ import java.util.List;
/*    */ 
/*    */ public class ConfigParams
/*    */ {
/*    */   public int configId;
/*    */   public String name;
/*    */   public String value;
/*    */   public int createdBy;
/*    */   public String logo;
/*    */   public String oldValue;
/*    */   public List<ConfigParams> configParams;
/*    */   
/*    */   public ConfigParams(int configId, String name, String value, int createdBy, String oldValue) {
/* 16 */     this.configId = configId;
/* 17 */     this.name = name;
/* 18 */     this.value = value;
/* 19 */     this.createdBy = createdBy;
/* 20 */     this.oldValue = oldValue;
/*    */   }
/*    */   
/*    */   public ConfigParams() {}
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\ConfigParams.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */