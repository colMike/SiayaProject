/*    */ package com.compulynx.erevenue.models;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ 
/*    */ public class Rights
/*    */ {
/*    */   public String headerName;
/*    */   public String headerIconCss;
/*    */   public String headerIconColor;
/*    */   public List<RightsDetail> rightsList;
/*    */   
/*    */   public Rights(String headerName, String headerIconCss, String headerIconColor) {
/* 14 */     this.headerName = headerName;
/* 15 */     this.headerIconCss = headerIconCss;
/* 16 */     this.headerIconColor = headerIconColor;
/* 17 */     this.rightsList = new ArrayList<RightsDetail>();
/*    */   }
/*    */   
/*    */   public Rights() {}
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\Rights.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */