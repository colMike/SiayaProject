/*    */ package com.compulynx.erevenue.models;
/*    */ 
/*    */ 
/*    */ public class RightsDetail
/*    */ {
/*    */   public int rightId;
/*    */   public String rightDisplayName;
/*    */   public String rightShortCode;
/*    */   public String rightViewName;
/*    */   public String rightName;
/*    */   public boolean rightAdd;
/*    */   public boolean rightEdit;
/*    */   public boolean rightDelete;
/*    */   public boolean rightView;
/*    */   public boolean allowAdd;
/*    */   public boolean allowEdit;
/*    */   public boolean allowDelete;
/*    */   public boolean allowView;
/*    */   public int rightMaxWidth;
/*    */   public String rightDisplayIcon;
/*    */   public int createdBy;
/*    */   public int respCode;
/*    */   
/*    */   public RightsDetail(int respCode) {
/* 25 */     this.respCode = respCode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RightsDetail(String rightDisplayName, String rightShortCode, String rightViewName, String rightName, boolean allowAdd, boolean allowEdit, boolean allowDelete, boolean allowView, int rightMaxWidth) {
/* 33 */     this.rightDisplayName = rightDisplayName;
/* 34 */     this.rightShortCode = rightShortCode;
/* 35 */     this.rightViewName = rightViewName;
/* 36 */     this.rightName = rightName;
/* 37 */     this.allowAdd = allowAdd;
/* 38 */     this.allowEdit = allowEdit;
/* 39 */     this.allowDelete = allowDelete;
/* 40 */     this.allowView = allowView;
/* 41 */     this.rightMaxWidth = rightMaxWidth;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RightsDetail(int rightId, String rightDisplayName, boolean allowView, boolean allowAdd, boolean allowEdit, boolean allowDelete, int respCode) {
/* 49 */     this.rightId = rightId;
/* 50 */     this.rightDisplayName = rightDisplayName;
/* 51 */     this.allowView = allowView;
/* 52 */     this.allowAdd = allowAdd;
/* 53 */     this.allowEdit = allowEdit;
/* 54 */     this.allowDelete = allowDelete;
/*    */     
/* 56 */     this.respCode = respCode;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RightsDetail() {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public RightsDetail(int rightId, String rightDisplayName, boolean rightAdd, boolean rightEdit, boolean rightDelete, boolean rightView, boolean allowAdd, boolean allowEdit, boolean allowDelete, boolean allowView, int respCode) {
/* 68 */     this.rightId = rightId;
/* 69 */     this.rightDisplayName = rightDisplayName;
/* 70 */     this.rightAdd = rightAdd;
/* 71 */     this.rightEdit = rightEdit;
/* 72 */     this.rightDelete = rightDelete;
/* 73 */     this.rightView = rightView;
/* 74 */     this.allowAdd = allowAdd;
/* 75 */     this.allowEdit = allowEdit;
/* 76 */     this.allowDelete = allowDelete;
/* 77 */     this.allowView = allowView;
/* 78 */     this.respCode = respCode;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public RightsDetail(int rightId, String rightDisplayName, boolean rightAdd, boolean rightEdit, boolean rightDelete, boolean rightView) {
/* 84 */     this.rightId = rightId;
/* 85 */     this.rightDisplayName = rightDisplayName;
/* 86 */     this.rightAdd = rightAdd;
/* 87 */     this.rightEdit = rightEdit;
/* 88 */     this.rightDelete = rightDelete;
/* 89 */     this.rightView = rightView;
/*    */   }
/*    */ 
/*    */   
/*    */   public RightsDetail(int rightId, String rightName, int respCode) {
/* 94 */     this.rightId = rightId;
/* 95 */     this.rightName = rightName;
/* 96 */     this.respCode = respCode;
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\RightsDetail.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */