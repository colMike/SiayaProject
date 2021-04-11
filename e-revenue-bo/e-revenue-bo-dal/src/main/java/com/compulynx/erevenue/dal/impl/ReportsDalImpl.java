/*     */ package com.compulynx.erevenue.dal.impl;
/*     */ 
/*     */ import com.compulynx.erevenue.dal.ReportsDal;
/*     */ import com.compulynx.erevenue.dal.operations.DBOperations;
/*     */ import com.compulynx.erevenue.dal.operations.Queryconstants;
/*     */ import com.compulynx.erevenue.models.Reports;
/*     */ import com.compulynx.erevenue.models.ZDetails;
/*     */ import java.sql.CallableStatement;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.text.DecimalFormat;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import javax.sql.DataSource;
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
/*     */ public class ReportsDalImpl
/*     */   implements ReportsDal
/*     */ {
/*     */   private DataSource dataSource;
/*     */   
/*     */   public ReportsDalImpl(DataSource dataSource) {
/*  37 */     this.dataSource = dataSource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Reports> GetAllUserTxn() {
/*  44 */     DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0.00");
/*  45 */     Connection connection = null;
/*  46 */     PreparedStatement preparedStatement = null;
/*  47 */     ResultSet resultSet = null;
/*  48 */     int counter = 1;
/*     */     try {
/*  50 */       connection = this.dataSource.getConnection();
/*  51 */       preparedStatement = connection.prepareStatement(Queryconstants.getAllUserTransactions);
/*     */ 
/*     */       
/*  54 */       resultSet = preparedStatement.executeQuery();
/*  55 */       List<Reports> reports = new ArrayList<>();
/*  56 */       while (resultSet.next()) {
/*  57 */         Reports objReport = new Reports();
/*  58 */         objReport.count = counter;
/*  59 */         objReport.userName = resultSet.getString("userName");
/*  60 */         objReport.tickets = resultSet.getString("tickets");
/*  61 */         objReport.totalAmount = df.format(resultSet.getDouble("txnTotal"));
/*  62 */         reports.add(objReport);
/*  63 */         counter++;
/*     */       } 
/*  65 */       return reports;
/*  66 */     } catch (Exception ex) {
/*  67 */       ex.printStackTrace();
/*  68 */       return null;
/*     */     } finally {
/*  70 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Reports> GetAllDeviceTxn() {
/*  79 */     DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0.00");
/*  80 */     Connection connection = null;
/*  81 */     PreparedStatement preparedStatement = null;
/*  82 */     ResultSet resultSet = null;
/*  83 */     int counter = 1;
/*     */     try {
/*  85 */       connection = this.dataSource.getConnection();
/*  86 */       preparedStatement = connection.prepareStatement(Queryconstants.getAllDeviceTransactions);
/*     */ 
/*     */       
/*  89 */       resultSet = preparedStatement.executeQuery();
/*  90 */       List<Reports> reports = new ArrayList<>();
/*  91 */       while (resultSet.next()) {
/*  92 */         Reports objReport = new Reports();
/*  93 */         objReport.count = counter;
/*  94 */         objReport.serialNo = resultSet.getString("serialNo");
/*  95 */         objReport.tickets = resultSet.getString("tickets");
/*  96 */         objReport.totalAmount = df.format(resultSet.getDouble("txnTotal"));
/*  97 */         reports.add(objReport);
/*  98 */         counter++;
/*     */       } 
/*     */       
/* 101 */       return reports;
/* 102 */     } catch (Exception ex) {
/* 103 */       ex.printStackTrace();
/* 104 */       return null;
/*     */     } finally {
/* 106 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Reports> GetUserTxnsDetails(Reports txnDetails) {
/* 115 */     DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0.00");
/* 116 */     Connection connection = null;
/* 117 */     CallableStatement callableStatement = null;
/* 118 */     ResultSet resultSet = null;
/* 119 */     int counter = 1;
/*     */     try {
/* 121 */       connection = this.dataSource.getConnection();
/* 122 */       callableStatement = connection.prepareCall(Queryconstants.getUserTxnsDetails);
/*     */       
/* 124 */       callableStatement.setInt(1, txnDetails.userId);
/* 125 */       callableStatement.setString(2, txnDetails.fromDate);
/* 126 */       callableStatement.setString(3, txnDetails.toDate);
/* 127 */       resultSet = callableStatement.executeQuery();
/* 128 */       List<Reports> txnList = new ArrayList<>();
/* 129 */       while (resultSet.next()) {
/* 130 */         Reports objReport = new Reports();
/* 131 */         objReport.count = counter;
/* 132 */         objReport.userName = resultSet.getString("userName");
/* 133 */         objReport.totalAmount = df.format(resultSet.getDouble("totalCollected"));
/* 134 */         objReport.invalidAmount = df.format(resultSet.getDouble("totalInvalid"));
/* 135 */         objReport.voidAmount = df.format(resultSet.getDouble("voidAmount"));
/* 136 */         objReport.netAmount = df.format(resultSet.getDouble("NETAmount"));
/* 137 */         txnList.add(objReport);
/* 138 */         counter++;
/*     */       } 
/* 140 */       return txnList;
/* 141 */     } catch (Exception ex) {
/* 142 */       ex.printStackTrace();
/* 143 */       return null;
/*     */     } finally {
/* 145 */       DBOperations.DisposeSql(connection, callableStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Reports> GetDeviceTxnsDetails(Reports txnDetails) {
/* 154 */     DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0.00");
/* 155 */     Connection connection = null;
/* 156 */     CallableStatement callableStatement = null;
/* 157 */     ResultSet resultSet = null;
/* 158 */     int counter = 1;
/*     */     try {
/* 160 */       connection = this.dataSource.getConnection();
/* 161 */       callableStatement = connection.prepareCall(Queryconstants.getDeviceTxnsDetails);
/*     */       
/* 163 */       callableStatement.setInt(1, txnDetails.deviceId);
/* 164 */       callableStatement.setString(2, txnDetails.fromDate);
/* 165 */       callableStatement.setString(3, txnDetails.toDate);
/* 166 */       resultSet = callableStatement.executeQuery();
/* 167 */       List<Reports> txnList = new ArrayList<>();
/* 168 */       while (resultSet.next()) {
/* 169 */         Reports objReport = new Reports();
/* 170 */         objReport.count = counter;
/* 171 */         objReport.totalAmount = df.format(resultSet.getDouble("totalCollected"));
/* 172 */         objReport.invalidAmount = df.format(resultSet.getDouble("totalInvalid"));
/* 173 */         objReport.voidAmount = df.format(resultSet.getDouble("voidAmount"));
/* 174 */         objReport.netAmount = df.format(resultSet.getDouble("NETAmount"));
/* 175 */         objReport.serialNo = resultSet.getString("serialNo");
/* 176 */         txnList.add(objReport);
/* 177 */         counter++;
/*     */       } 
/* 179 */       return txnList;
/* 180 */     } catch (Exception ex) {
/* 181 */       ex.printStackTrace();
/* 182 */       return null;
/*     */     } finally {
/* 184 */       DBOperations.DisposeSql(connection, callableStatement, resultSet);
/*     */     } 
/*     */   }
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
/*     */   public List<Reports> GetAllTxnsDetails(Reports txnDetails) {
/* 266 */     DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0.00");
/* 267 */     Connection connection = null;
/* 268 */     CallableStatement callableStatement = null;
/* 269 */     ResultSet resultSet = null;
/* 270 */     int counter = 1;
/* 271 */     String today = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
/*     */     try {
/* 273 */       connection = this.dataSource.getConnection();
/* 274 */       callableStatement = connection.prepareCall(Queryconstants.getAllTransactions);
/*     */ 
/*     */       
/* 277 */       callableStatement.setString(1, (txnDetails.fromDate == null) ? today : txnDetails.fromDate);
/* 278 */       callableStatement.setString(2, (txnDetails.toDate == null) ? today : txnDetails.toDate);
/*     */ 
/*     */       
/* 281 */       resultSet = callableStatement.executeQuery();
/* 282 */       List<Reports> txnList = new ArrayList<>();
/* 283 */       while (resultSet.next()) {
/* 284 */         Reports objReport = new Reports();
/* 285 */         objReport.count = counter;
/* 286 */         objReport.userName = resultSet.getString("agent");
/* 287 */         objReport.tickets = resultSet.getString("tickets");
/* 288 */         objReport.totalAmount = df.format(resultSet.getDouble("txntotal"));
/* 289 */         objReport.billNo = resultSet.getString("tickets");
/* 290 */         objReport.gateName = resultSet.getString("market");
/* 291 */         objReport.serviceName = resultSet.getString("servicename");
/* 292 */         objReport.txnDate = resultSet.getString("transaction_date");
/* 293 */         objReport.serialNo = resultSet.getString("serial_no");
/* 294 */         objReport.status = resultSet.getString("status");
/* 295 */         objReport.reprintStatus = resultSet.getString("reprint_status");
/* 296 */         txnList.add(objReport);
/* 297 */         counter++;
/*     */       } 
/* 299 */       return txnList;
/* 300 */     } catch (Exception ex) {
/* 301 */       ex.printStackTrace();
/* 302 */       return null;
/*     */     } finally {
/* 304 */       DBOperations.DisposeSql(connection, callableStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ZDetails> GetZDetails(ZDetails zDetails) {
/* 313 */     DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0.00");
/* 314 */     Connection connection = null;
/* 315 */     CallableStatement callableStatement = null;
/* 316 */     ResultSet resultSet = null;
/* 317 */     String today = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
/*     */     try {
/* 319 */       connection = this.dataSource.getConnection();
/*     */       
/* 321 */       callableStatement = connection.prepareCall(Queryconstants.getZDetails);
/*     */ 
/*     */       
/* 324 */       callableStatement.setString(1, (zDetails.fromDate == null) ? today : zDetails.fromDate);
/* 325 */       callableStatement.setString(2, (zDetails.toDate == null) ? today : zDetails.toDate);
/*     */ 
/*     */       
/* 328 */       resultSet = callableStatement.executeQuery();
/* 329 */       List<ZDetails> zList = new ArrayList<>();
/* 330 */       while (resultSet.next()) {
/* 331 */         ZDetails objZ = new ZDetails();
/* 332 */         objZ.zNumber = resultSet.getString("z_number");
/* 333 */         objZ.gateName = resultSet.getString("market");
/* 334 */         objZ.serialNo = resultSet.getString("device_serial");
/* 335 */         objZ.totOnlineTxns = resultSet.getInt("total_txn_count");
/* 336 */         objZ.totOnlineAmount = df.format(resultSet.getDouble("total_amount"));
/* 337 */         objZ.totOfflineTxns = resultSet.getInt("total_offline_txns");
/* 338 */         objZ.totOfflineAmount = df.format(resultSet.getDouble("total_offline_amount"));
/* 339 */         objZ.totInvalidTxns = resultSet.getInt("failed_txn_count");
/* 340 */         objZ.totInvalidAmount = df.format(resultSet.getDouble("failed_amount"));
/* 341 */         objZ.totVoidTxns = resultSet.getInt("void_txn_count");
/* 342 */         objZ.totVoidAmount = df.format(resultSet.getDouble("void_amount"));
/* 343 */         objZ.totNetTxns = resultSet.getInt("net_txn_count");
/* 344 */         objZ.totNetAmount = df.format(resultSet.getDouble("net_amount"));
/* 345 */         objZ.userName = resultSet.getString("username");
/* 346 */         objZ.zDate = resultSet.getString("created_at");
/* 347 */         objZ.overAllAmount = df.format(resultSet.getDouble("total_amount"));
/*     */         
/* 349 */         zList.add(objZ);
/*     */       } 
/*     */       
/* 352 */       return zList;
/* 353 */     } catch (Exception ex) {
/* 354 */       ex.printStackTrace();
/* 355 */       return null;
/*     */     } finally {
/* 357 */       DBOperations.DisposeSql(connection, callableStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Reports> GetCurrentTxnsDetails() {
/* 367 */     DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0.00");
/* 368 */     Connection connection = null;
/* 369 */     PreparedStatement preparedStatement = null;
/* 370 */     ResultSet resultSet = null;
/*     */     try {
/* 372 */       connection = this.dataSource.getConnection();
/* 373 */       preparedStatement = connection.prepareStatement(Queryconstants.getCurrentDateTxns);
/*     */ 
/*     */       
/* 376 */       resultSet = preparedStatement.executeQuery();
/* 377 */       List<Reports> txnList = new ArrayList<>();
/* 378 */       while (resultSet.next()) {
/* 379 */         Reports objReport = new Reports();
/* 380 */         objReport.totalAmount = df.format(resultSet.getDouble("totalCollected"));
/* 381 */         objReport.voidAmount = df.format(resultSet.getDouble("voidAmount"));
/* 382 */         objReport.invalidAmount = df.format(resultSet.getDouble("totalInvalid"));
/*     */         
/* 384 */         double netamount = resultSet.getDouble("totalCollected") - resultSet.getDouble("voidAmount") - resultSet.getDouble("totalInvalid");
/* 385 */         objReport.netAmount = df.format(netamount);
/* 386 */         objReport.billNo = resultSet.getString("totalBills");
/* 387 */         txnList.add(objReport);
/*     */       } 
/* 389 */       return txnList;
/* 390 */     } catch (Exception ex) {
/* 391 */       ex.printStackTrace();
/* 392 */       return null;
/*     */     } finally {
/* 394 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\impl\ReportsDalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */