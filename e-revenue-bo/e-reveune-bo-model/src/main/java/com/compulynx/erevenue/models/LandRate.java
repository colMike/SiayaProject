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
/*    */ public class LandRate
/*    */ {
/*    */   public int id;
/*    */   public String plotNumber;
/*    */   public String mapSheetNumber;
/*    */   public String location;
/*    */   public String acreage;
/*    */   public String titleDeedNumber;
/*    */   public int permitTypeId;
/*    */   public String name;
/*    */   public String krapin;
/*    */   public String nationalIdNumber;
/*    */   public String createdBy;
/*    */   public String created_at;
/*    */   public int respCode;
/*    */   public int count;
/*    */   public int wardId;
/*    */   public int subCountyId;
/*    */   public int landId;
/*    */   public String landNo;
/*    */   public int approvedBy;
/*    */   public String approvedOn;
/*    */   public String rejectReason;
/*    */   public String approvedUser;
/*    */   public String status;
/*    */   public String mpesaCode;
/*    */   public String paidStatus;
/*    */   public double fee;
/*    */   public double penalty;
/*    */   public int regId;
/*    */   public String regNo;
/*    */   public String applicant;
/*    */   public String appliedOn;
/*    */   public String paidDate;
/*    */   public int paidBy;
/*    */   public String paidUser;
/*    */   public int receiptBy;
/*    */   public int validity;
/*    */   public String permitUser;
/*    */   public List<PermitYear> yearList;
/*    */   public int amendedBy;
/*    */   public int appliedFor;
/*    */   public int linkId;
/*    */   public String subCountyName;
/*    */   public String wardName;
/*    */   public String bankName;
/*    */   public String accNo;
/*    */   public String transNo;
/*    */   public String landType;
/*    */   public String permitNo;
/*    */   public String preLr;
/*    */   public String landTypeName;
/*    */   public String permitStatus;
public String address;
public String phone;
public String rates;
public String arrears;
public String total;
public String blocknumber;
public String titleNature;
public double balance;
public double amount;
public String code;
/*    */   
/*    */   public LandRate() {}
/*    */   
/*    */   public LandRate(int id, String plotNumber, String mapSheetNumber, String location, String acreage, String titleDeedNumber, int permitTypeId, String name, String krapin, String nationalIdNumber, int wardId, int subCountyId, int respCode, int count, String landStatus, String createdBy,String address,String code) {
/* 76 */     this.id = id;
/* 77 */     this.plotNumber = plotNumber;
/* 78 */     this.mapSheetNumber = mapSheetNumber;
/* 79 */     this.location = location;
/* 80 */     this.acreage = acreage;
/* 81 */     this.titleDeedNumber = titleDeedNumber;
/* 82 */     this.permitTypeId = permitTypeId;
/* 83 */     this.name = name;
/* 84 */     this.krapin = krapin;
/* 85 */     this.nationalIdNumber = nationalIdNumber;
/* 86 */     this.wardId = wardId;
/* 87 */     this.subCountyId = subCountyId;
/* 88 */     this.respCode = respCode;
/* 89 */     this.count = count;
/* 90 */     this.status = landStatus;
/* 91 */     this.createdBy = createdBy;
             this.address = address;
             this.code=code;
/*    */   }
/*    */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\LandRate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */