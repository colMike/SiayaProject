/*     */ package com.compulynx.erevenue.models;
/*     */ 
/*     */ import java.util.Date;
/*     */ 
/*     */ public class Transaction
/*     */ {
/*     */   private int counter;
/*     */   private String transactionId;
/*     */   private String billNo;
/*     */   private String amount;
/*     */   private String service;
/*     */   private String subservice;
/*     */   private String market;
/*     */   private String deviceSerial;
/*     */   private String user;
/*     */   private Date transactionDate;
/*     */   private boolean voided;
/*     */   private boolean online;
/*     */   private boolean recieptPrinted;
/*     */   private String paymentMethod;
/*     */   private int status;
/*     */   private String zNumber;
/*     */   private String verifyStatus;
/*     */   private Date verifyDate;
/*     */   
/*     */   public int getCounter() {
/*  27 */     return this.counter;
/*     */   }
/*     */   public void setCounter(int counter) {
/*  30 */     this.counter = counter;
/*     */   }
/*     */   public String getTransactionId() {
/*  33 */     return this.transactionId;
/*     */   }
/*     */   public void setTransactionId(String transactionId) {
/*  36 */     this.transactionId = transactionId;
/*     */   }
/*     */   public String getBillNo() {
/*  39 */     return this.billNo;
/*     */   }
/*     */   public void setBillNo(String billNo) {
/*  42 */     this.billNo = billNo;
/*     */   }
/*     */   public String getAmount() {
/*  45 */     return this.amount;
/*     */   }
/*     */   public void setAmount(String amount) {
/*  48 */     this.amount = amount;
/*     */   }
/*     */   public String getService() {
/*  51 */     return this.service;
/*     */   }
/*     */   public void setService(String service) {
/*  54 */     this.service = service;
/*     */   }
/*     */   public String getSubservice() {
/*  57 */     return this.subservice;
/*     */   }
/*     */   public void setSubservice(String subservice) {
/*  60 */     this.subservice = subservice;
/*     */   }
/*     */   public String getMarket() {
/*  63 */     return this.market;
/*     */   }
/*     */   public void setMarket(String market) {
/*  66 */     this.market = market;
/*     */   }
/*     */   public String getDeviceSerial() {
/*  69 */     return this.deviceSerial;
/*     */   }
/*     */   public void setDeviceSerial(String deviceSerial) {
/*  72 */     this.deviceSerial = deviceSerial;
/*     */   }
/*     */   public String getUser() {
/*  75 */     return this.user;
/*     */   }
/*     */   public void setUser(String user) {
/*  78 */     this.user = user;
/*     */   }
/*     */   public Date getTransactionDate() {
/*  81 */     return this.transactionDate;
/*     */   }
/*     */   public void setTransactionDate(Date transactionDate) {
/*  84 */     this.transactionDate = transactionDate;
/*     */   }
/*     */   public boolean isVoided() {
/*  87 */     return this.voided;
/*     */   }
/*     */   public void setVoided(boolean voided) {
/*  90 */     this.voided = voided;
/*     */   }
/*     */   public boolean isOnline() {
/*  93 */     return this.online;
/*     */   }
/*     */   public void setOnline(boolean online) {
/*  96 */     this.online = online;
/*     */   }
/*     */   public boolean isRecieptPrinted() {
/*  99 */     return this.recieptPrinted;
/*     */   }
/*     */   public void setRecieptPrinted(boolean recieptPrinted) {
/* 102 */     this.recieptPrinted = recieptPrinted;
/*     */   }
/*     */   public String getPaymentMethod() {
/* 105 */     return this.paymentMethod;
/*     */   }
/*     */   public void setPaymentMethod(String paymentMethod) {
/* 108 */     this.paymentMethod = paymentMethod;
/*     */   }
/*     */   public int getStatus() {
/* 111 */     return this.status;
/*     */   }
/*     */   public void setStatus(int status) {
/* 114 */     this.status = status;
/*     */   }
/*     */   public String getzNumber() {
/* 117 */     return this.zNumber;
/*     */   }
/*     */   public void setzNumber(String zNumber) {
/* 120 */     this.zNumber = zNumber;
/*     */   }
/*     */   public String getVerifyStatus() {
/* 123 */     return this.verifyStatus;
/*     */   }
/*     */   public void setVerifyStatus(String verifyStatus) {
/* 126 */     this.verifyStatus = verifyStatus;
/*     */   }
/*     */   public Date getVerifyDate() {
/* 129 */     return this.verifyDate;
/*     */   }
/*     */   public void setVerifyDate(Date verifyDate) {
/* 132 */     this.verifyDate = verifyDate;
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-model-0.0.1.jar!\com\compulynx\erevenue\models\Transaction.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */