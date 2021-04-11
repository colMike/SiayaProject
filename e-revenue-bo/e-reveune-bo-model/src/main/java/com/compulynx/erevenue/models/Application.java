/*     */ package com.compulynx.erevenue.models;
/*     */ 
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Application
/*     */ {
/*     */   public int businessId;
/*     */   public String businessName;
/*     */   public int noOfEmployees;
/*     */   public int permitTypeId;
/*     */   public double permitFee;
/*     */   public String waterAccNo;
/*     */   public String electricityAccNo;
/*     */   public int area;
/*     */   public String regNo;
/*     */   public String businessDesc;
/*     */   public String postalAdd;
/*     */   public String postalCode;
/*     */   public String email;
/*     */   public String fax;
/*     */   public String mobileNo;
/*     */   public String landLineNo;
/*     */   public int wardId;
/*     */   public String landZone;
/*     */   public String plotNo;
/*     */   public boolean active;
/*     */   public int createdBy;
/*     */   public int respCode;
/*     */   public int count;
/*     */   public List<PermitYear> yearList;
/*     */   public int appId;
/*     */   public String appNo;
/*     */   public String mpesaCode;
/*     */   public String businessNo;
/*     */   public String preSbp;
/*     */   public double fee;
/*     */   public boolean paid;
/*     */   public String paidDate;
/*     */   public int appliedFor;
/*     */   public String appliedOn;
/*     */   public double penalty;
/*     */   public String appType;
/*     */   public String remarks;
/*     */   public String status;
/*     */   public String permitStatus;
/*     */   public String amountInWords;
/*     */   public int approvedBy;
/*     */   public String approvedOn;
/*     */   public int receiptBy;
/*     */   public String receiptOn;
/*     */   public String applicant;
/*     */   public String approvedUser;
/*     */   public String paidStatus;
/*     */   public String rejectReason;
/*     */   public int validity;
/*     */   public String expiryDate;
/*     */   public String permitNo;
/*     */   public String permitUser;
/*     */   public String permitQr;
/*     */   public int paidBy;
/*     */   public String paidUser;
/*     */   public String nationalId;
/*     */   public int linkId;
/*     */   public String bankName;
/*     */   public String accNo;
/*     */   public String transNo;
public int application_fee;
public int advertisement_fee;
public int billboard_fee;
public int tradename_fee;
public String detail_activity;
public String pin_number;
public String vat_number;
public String total_in_words;
public int total;
/*     */   
/*     */   public Application() {}
/*     */   
/*     */   public Application(int businessId, String businessNo, String businessName, int noOfEmployees,
		int permitTypeId, double permitFee, String waterAccNo, String electricityAccNo,
		int area, String regNo, String businessDesc, String postalAdd, String postalCode, 
		String email, String fax, String mobileNo, String landLineNo, int wardId, String landZone,
		
		String plotNo, boolean active, int respCode, int count, String appStatus, int createdBy,
		String nationalId,int application_fee,int advertisement_fee,int billboard_fee,
		int tradename_fee,String detail_activity,String pin_number,String applicant,String vat_number) {
/*  91 */     this.businessId = businessId;
/*  92 */     this.businessNo = businessNo;
/*  93 */     this.businessName = businessName;
/*  94 */     this.noOfEmployees = noOfEmployees;
/*  95 */     this.permitTypeId = permitTypeId;
/*  96 */     this.permitFee = permitFee;
/*  97 */     this.waterAccNo = waterAccNo;
/*  98 */     this.electricityAccNo = electricityAccNo;
/*  99 */     this.area = area;
/* 100 */     this.regNo = regNo;
/* 101 */     this.businessDesc = businessDesc;
/* 102 */     this.postalAdd = postalAdd;
/* 103 */     this.postalCode = postalCode;
/* 104 */     this.email = email;
/* 105 */     this.fax = fax;
/* 106 */     this.mobileNo = mobileNo;
/* 107 */     this.landLineNo = landLineNo;
/* 108 */     this.wardId = wardId;
/* 109 */     this.landZone = landZone;
/* 110 */     this.plotNo = plotNo;
/* 111 */     this.active = active;
/* 112 */     this.respCode = respCode;
/* 113 */     this.count = count;
/* 114 */     this.status = appStatus;
/* 115 */     this.createdBy = createdBy;
/* 116 */     this.nationalId = nationalId;
this.application_fee=application_fee;
this.advertisement_fee=advertisement_fee;
this.billboard_fee=billboard_fee;
this.tradename_fee=tradename_fee;
this.detail_activity=detail_activity;
this.pin_number=pin_number;
this.applicant=applicant;
this.vat_number=vat_number;
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\Application.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */