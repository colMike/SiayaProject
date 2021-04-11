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
/*     */ public class Service
/*     */ {
/*     */   public int parentServiceId;
/*     */   public int level;
/*     */   public boolean hasChild;
/*     */   public int serviceId;
/*     */   public String serviceCode;
/*     */   public String serviceName;
/*     */   public double quantiy;
/*     */   public double price;
/*     */   public double serviceValue;
/*     */   public boolean active;
/*     */   public int createdBy;
/*     */   public int respCode;
/*     */   public boolean isActive;
/*     */   public List<Params> params;
/*     */   public int count;
/*     */   public String level2;
/*     */   public String active2;
/*     */   public int serviceType;
/*     */   public boolean parentType;
/*     */   public List<SubCounty> subCountyList;
/*     */   public List<Ward> wardList;
/*     */   public List<Market> marketList;
/*     */   public boolean isDisabled;
/*     */   public String iconStyle;
/*     */   public List<Service> serviceList;
/*     */   public int marketId;
/*     */   
/*     */   public Service() {}
/*     */   
/*     */   public Service(int respCode) {
/*  47 */     this.respCode = respCode;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Service(int serviceId, String serviceCode, String serviceName, boolean active, int createdBy, int respCode, int level, boolean hasChild, double serviceValue) {
/*  53 */     this.serviceId = serviceId;
/*  54 */     this.serviceCode = serviceCode;
/*  55 */     this.serviceName = serviceName;
/*  56 */     this.active = active;
/*  57 */     this.createdBy = createdBy;
/*  58 */     this.respCode = respCode;
/*  59 */     this.level = level;
/*  60 */     this.hasChild = hasChild;
/*  61 */     this.serviceValue = serviceValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public Service(int serviceId, String serviceCode, String serviceName, boolean active, int createdBy, int respCode, String active2, int count, boolean isDisabled, String iconStyle) {
/*  66 */     this.serviceId = serviceId;
/*  67 */     this.serviceCode = serviceCode;
/*  68 */     this.serviceName = serviceName;
/*  69 */     this.active = active;
/*  70 */     this.createdBy = createdBy;
/*  71 */     this.respCode = respCode;
/*  72 */     this.active2 = active2;
/*  73 */     this.count = count;
/*  74 */     this.isDisabled = isDisabled;
/*  75 */     this.iconStyle = iconStyle;
/*     */   }
/*     */   
/*     */   public Service(String level2, int serviceId, String serviceName, String active2, boolean active, int count, boolean isDisabled, String iconStyle) {
/*  79 */     this.level2 = level2;
/*  80 */     this.serviceId = serviceId;
/*  81 */     this.serviceName = serviceName;
/*  82 */     this.active2 = active2;
/*  83 */     this.active = active;
/*  84 */     this.count = count;
/*  85 */     this.isDisabled = isDisabled;
/*  86 */     this.iconStyle = iconStyle;
/*     */   }
/*     */ 
/*     */   
/*     */   public Service(int serviceId, String serviceCode, String serviceName, boolean active, int createdBy, int respCode, String active2, int count) {
/*  91 */     this.serviceId = serviceId;
/*  92 */     this.serviceCode = serviceCode;
/*  93 */     this.serviceName = serviceName;
/*  94 */     this.active = active;
/*  95 */     this.createdBy = createdBy;
/*  96 */     this.respCode = respCode;
/*  97 */     this.active2 = active2;
/*  98 */     this.count = count;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Service(int serviceId, String serviceName, boolean isActive, int createdBy, int respCode) {
/* 104 */     this.serviceId = serviceId;
/* 105 */     this.serviceName = serviceName;
/* 106 */     this.isActive = isActive;
/* 107 */     this.createdBy = createdBy;
/* 108 */     this.respCode = respCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Service(int serviceId, String serviceName, double serviceValue) {
/* 115 */     this.serviceId = serviceId;
/* 116 */     this.serviceName = serviceName;
/* 117 */     this.serviceValue = serviceValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public Service(int serviceId, String serviceName, double serviceValue, String active2, boolean active, int count, boolean isDisabled, String iconStyle) {
/* 122 */     this.serviceId = serviceId;
/* 123 */     this.serviceName = serviceName;
/* 124 */     this.serviceValue = serviceValue;
/* 125 */     this.active2 = active2;
/* 126 */     this.active = active;
/* 127 */     this.count = count;
/* 128 */     this.isDisabled = isDisabled;
/* 129 */     this.iconStyle = iconStyle;
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\Service.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */