/*     */ package com.compulynx.erevenue.dal.impl;
/*     */ 
/*     */ import com.compulynx.erevenue.dal.DashBoardDal;
/*     */ import com.compulynx.erevenue.dal.operations.DBOperations;
/*     */ import com.compulynx.erevenue.dal.operations.Queryconstants;
/*     */ import com.compulynx.erevenue.models.DashBoard;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.text.DecimalFormat;
/*     */ import java.util.ArrayList;
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
/*     */ public class DashBoardDalImpl
/*     */   implements DashBoardDal
/*     */ {
/*     */   private DataSource dataSource;
/*     */   
/*     */   public DashBoardDalImpl(DataSource dataSource) {
/*  28 */     this.dataSource = dataSource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<DashBoard> GetDashBoardCountDetail() {
/*  36 */     Connection connection = null;
/*  37 */     PreparedStatement preparedStatement = null;
/*  38 */     ResultSet resultSet = null;
/*     */     try {
/*  40 */       connection = this.dataSource.getConnection();
/*  41 */       preparedStatement = connection.prepareStatement(Queryconstants.getDashBoardDetailCount);
/*     */ 
/*     */       
/*  44 */       resultSet = preparedStatement.executeQuery();
/*  45 */       List<DashBoard> detail = new ArrayList<>();
/*  46 */       while (resultSet.next()) {
/*  47 */         detail.add(new DashBoard(resultSet.getInt("COUNTNO"), resultSet.getString("Name"), 200));
/*     */       }
/*     */ 
/*     */       
/*  51 */       return detail;
/*  52 */     } catch (Exception ex) {
/*  53 */       ex.printStackTrace();
/*  54 */       return null;
/*     */     } finally {
/*  56 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DashBoard GetMonthStatisticsDetails() {
/*  65 */     DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0.00");
/*  66 */     Connection connection = null;
/*  67 */     PreparedStatement preparedStatement = null;
/*  68 */     ResultSet resultSet = null;
/*     */     try {
/*  70 */       connection = this.dataSource.getConnection();
/*  71 */       preparedStatement = connection.prepareStatement(Queryconstants.getCurrentMonthStatistics);
/*     */ 
/*     */       
/*  74 */       resultSet = preparedStatement.executeQuery();
/*  75 */       DashBoard detail = new DashBoard();
/*  76 */       List<DashBoard> currentMonth = new ArrayList<>();
/*  77 */       while (resultSet.next()) {
/*  78 */         DashBoard obj = new DashBoard();
/*  79 */         obj.subCountyName = resultSet.getString("name");
/*  80 */         obj.amount = df.format(resultSet.getDouble("total"));
/*  81 */         currentMonth.add(obj);
/*     */       } 
/*  83 */       DBOperations.DisposeSql(preparedStatement, resultSet);
/*  84 */       preparedStatement = connection.prepareStatement(Queryconstants.getPreviousMonthStatistics);
/*     */ 
/*     */       
/*  87 */       resultSet = preparedStatement.executeQuery();
/*  88 */       List<DashBoard> previousMonth = new ArrayList<>();
/*  89 */       while (resultSet.next()) {
/*  90 */         DashBoard obj = new DashBoard();
/*  91 */         obj.subCountyName = resultSet.getString("name");
/*  92 */         obj.amount = df.format(resultSet.getDouble("total"));
/*  93 */         previousMonth.add(obj);
/*     */       } 
/*  95 */       DBOperations.DisposeSql(preparedStatement, resultSet);
/*  96 */       preparedStatement = connection.prepareStatement(Queryconstants.getTwoMonthStatistics);
/*     */ 
/*     */       
/*  99 */       resultSet = preparedStatement.executeQuery();
/* 100 */       List<DashBoard> twoMonth = new ArrayList<>();
/* 101 */       while (resultSet.next()) {
/* 102 */         DashBoard obj = new DashBoard();
/* 103 */         obj.subCountyName = resultSet.getString("name");
/* 104 */         obj.amount = df.format(resultSet.getDouble("total"));
/* 105 */         twoMonth.add(obj);
/*     */       } 
/* 107 */       detail.currentMonth = currentMonth;
/* 108 */       detail.previousMonth = previousMonth;
/* 109 */       detail.twoMonth = twoMonth;
/* 110 */       return detail;
/* 111 */     } catch (Exception ex) {
/* 112 */       ex.printStackTrace();
/* 113 */       return null;
/*     */     } finally {
/* 115 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DashBoard GetUserStatisticsDetails() {
/* 124 */     DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0.00");
/* 125 */     Connection connection = null;
/* 126 */     PreparedStatement preparedStatement = null;
/* 127 */     ResultSet resultSet = null;
/*     */     try {
/* 129 */       connection = this.dataSource.getConnection();
/* 130 */       preparedStatement = connection.prepareStatement(Queryconstants.getUserDscStatistics);
/*     */ 
/*     */       
/* 133 */       resultSet = preparedStatement.executeQuery();
/* 134 */       DashBoard detail = new DashBoard();
/* 135 */       List<DashBoard> userDsc = new ArrayList<>();
/* 136 */       while (resultSet.next()) {
/* 137 */         DashBoard obj = new DashBoard();
/* 138 */         obj.userName = resultSet.getString("name");
/* 139 */         obj.tickets = resultSet.getInt("tickets");
/* 140 */         obj.amount = df.format(resultSet.getDouble("amount"));
/* 141 */         userDsc.add(obj);
/*     */       } 
/* 143 */       DBOperations.DisposeSql(preparedStatement, resultSet);
/* 144 */       preparedStatement = connection.prepareStatement(Queryconstants.getUserAscStatistics);
/*     */ 
/*     */       
/* 147 */       resultSet = preparedStatement.executeQuery();
/* 148 */       List<DashBoard> userAsc = new ArrayList<>();
/* 149 */       while (resultSet.next()) {
/* 150 */         DashBoard obj = new DashBoard();
/* 151 */         obj.userName = resultSet.getString("name");
/* 152 */         obj.tickets = resultSet.getInt("tickets");
/* 153 */         obj.amount = df.format(resultSet.getDouble("amount"));
/* 154 */         userAsc.add(obj);
/*     */       } 
/*     */       
/* 157 */       detail.userDsc = userDsc;
/* 158 */       detail.userAsc = userAsc;
/*     */       
/* 160 */       return detail;
/* 161 */     } catch (Exception ex) {
/* 162 */       ex.printStackTrace();
/* 163 */       return null;
/*     */     } finally {
/* 165 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<DashBoard> GetFlowChartCountDetail() {
/* 174 */     Connection connection = null;
/* 175 */     PreparedStatement preparedStatement = null;
/* 176 */     ResultSet resultSet = null;
/*     */     try {
/* 178 */       connection = this.dataSource.getConnection();
/* 179 */       preparedStatement = connection.prepareStatement(Queryconstants.getFlowChartDetails);
/*     */ 
/*     */       
/* 182 */       resultSet = preparedStatement.executeQuery();
/* 183 */       List<DashBoard> detail = new ArrayList<>();
/* 184 */       while (resultSet.next()) {
/* 185 */         detail.add(new DashBoard(resultSet.getString("service"), resultSet.getInt("trans_count")));
/*     */       }
/*     */       
/* 188 */       return detail;
/* 189 */     } catch (Exception ex) {
/* 190 */       ex.printStackTrace();
/* 191 */       return null;
/*     */     } finally {
/* 193 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<DashBoard> GetDashBoardAmountDetail() {
/* 202 */     DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0.00");
/* 203 */     Connection connection = null;
/* 204 */     PreparedStatement preparedStatement = null;
/* 205 */     ResultSet resultSet = null;
/*     */     try {
/* 207 */       connection = this.dataSource.getConnection();
/* 208 */       preparedStatement = connection.prepareStatement(Queryconstants.getAmountDetails);
/*     */ 
/*     */       
/* 211 */       resultSet = preparedStatement.executeQuery();
/* 212 */       List<DashBoard> detail = new ArrayList<>();
/* 213 */       while (resultSet.next()) {
/* 214 */         DashBoard obj = new DashBoard();
/* 215 */         obj.detailDescription = resultSet.getString("name");
/* 216 */         obj.amount = df.format(resultSet.getDouble("countno"));
/*     */         
/* 218 */         detail.add(obj);
/*     */       } 
/* 220 */       return detail;
/* 221 */     } catch (Exception ex) {
/* 222 */       ex.printStackTrace();
/* 223 */       return null;
/*     */     } finally {
/* 225 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<DashBoard> GetTransChartDetail() {
/* 233 */     Connection connection = null;
/* 234 */     PreparedStatement preparedStatement = null;
/* 235 */     ResultSet resultSet = null;
/*     */     
/*     */     try {
/* 238 */       connection = this.dataSource.getConnection();
/* 239 */       preparedStatement = connection.prepareStatement(Queryconstants.getTransChartDetail);
/*     */ 
/*     */       
/* 242 */       resultSet = preparedStatement.executeQuery();
/* 243 */       List<DashBoard> chartDetail = new ArrayList<>();
/* 244 */       while (resultSet.next()) {
/* 245 */         chartDetail.add(new DashBoard(resultSet.getString("month"), resultSet.getDouble("TOTALAMOUNT"), resultSet.getDouble("VOIDAMOUNT"), resultSet.getDouble("INVALIDAMOUNT"), resultSet.getDouble("NETAMOUNT")));
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 251 */       return chartDetail;
/* 252 */     } catch (Exception ex) {
/* 253 */       ex.printStackTrace();
/* 254 */       return null;
/*     */     } finally {
/* 256 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<DashBoard> GetDashBoardCollectionDetail() {
/* 265 */     DecimalFormat df = new DecimalFormat("##,##,##,##,##,##,##0.00");
/* 266 */     Connection connection = null;
/* 267 */     PreparedStatement preparedStatement = null;
/* 268 */     ResultSet resultSet = null;
/*     */     try {
/* 270 */       connection = this.dataSource.getConnection();
/* 271 */       preparedStatement = connection.prepareStatement(Queryconstants.getTotalCollectionInfo);
/*     */ 
/*     */       
/* 274 */       resultSet = preparedStatement.executeQuery();
/* 275 */       List<DashBoard> detail = new ArrayList<>();
/* 276 */       while (resultSet.next()) {
/* 277 */         DashBoard obj = new DashBoard();
/* 278 */         obj.detailDescription = resultSet.getString("name");
/* 279 */         obj.amount = df.format(resultSet.getDouble("collection"));
/* 280 */         obj.totalTxns = resultSet.getDouble("collection");
/* 281 */         detail.add(obj);
/*     */       } 
/*     */       
/* 284 */       return detail;
/* 285 */     } catch (Exception ex) {
/* 286 */       ex.printStackTrace();
/* 287 */       return null;
/*     */     } finally {
/* 289 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\impl\DashBoardDalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */