/*     */ package com.compulynx.erevenue.dal.impl;
/*     */ 
/*     */ import com.compulynx.erevenue.dal.MarketDal;
/*     */ import com.compulynx.erevenue.dal.operations.DBOperations;
/*     */ import com.compulynx.erevenue.dal.operations.Queryconstants;
/*     */ import com.compulynx.erevenue.models.ErevenueResponse;
/*     */ import com.compulynx.erevenue.models.Market;
/*     */ import com.compulynx.erevenue.models.Ward;
/*     */ import java.sql.Connection;
/*     */ import java.sql.PreparedStatement;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.sql.Timestamp;
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
/*     */ public class MarketDalImpl
/*     */   implements MarketDal
/*     */ {
/*     */   private DataSource dataSource;
/*     */   
/*     */   public MarketDalImpl(DataSource dataSource) {
/*  33 */     this.dataSource = dataSource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ErevenueResponse UpdateMarket(Market market) {
/*  42 */     Connection connection = null;
/*  43 */     PreparedStatement preparedStatement = null;
/*  44 */     ResultSet resultSet = null;
/*     */     try {
/*  46 */       connection = this.dataSource.getConnection();
/*  47 */       if (market.marketId == 0) {
/*     */         
/*  49 */         for (int i = 0; i < market.marketDetails.size(); i++) {
/*  50 */           if (checkMarketName(((Market)market.marketDetails.get(i)).marketName)) {
/*  51 */             return new ErevenueResponse(201, "Market name already exists");
/*     */           }
/*     */           
/*  54 */           if (checkMarketCode(((Market)market.marketDetails.get(i)).marketCode)) {
/*  55 */             return new ErevenueResponse(201, "Market code already exists");
/*     */           }
/*     */           
/*  58 */           preparedStatement = connection.prepareStatement(Queryconstants.insertMarket);
/*     */           
/*  60 */           preparedStatement.setString(1, ((Market)market.marketDetails.get(i)).marketCode);
/*     */           
/*  62 */           preparedStatement.setString(2, ((Market)market.marketDetails.get(i)).marketName);
/*     */           
/*  64 */           preparedStatement.setInt(3, market.wardId);
/*  65 */           preparedStatement.setBoolean(4, market.active);
/*  66 */           preparedStatement.setInt(5, market.createdBy);
/*  67 */           preparedStatement.setTimestamp(6, new Timestamp((new Date()).getTime()));
/*     */           
/*  69 */           if (preparedStatement.executeUpdate() <= 0) {
/*  70 */             throw new Exception("Failed to Insert Market");
/*     */           }
/*     */         } 
/*  73 */         return new ErevenueResponse(200, "Records Updated");
/*     */       } 
/*     */       
/*  76 */       if (checkMarketNameByCode(market.marketName, market.marketCode)) {
/*  77 */         return new ErevenueResponse(201, "Market name already exists");
/*     */       }
/*     */       
/*  80 */       preparedStatement = connection.prepareStatement(Queryconstants.updateMarket);
/*     */       
/*  82 */       preparedStatement.setString(1, market.marketName);
/*  83 */       preparedStatement.setInt(2, market.wardId);
/*  84 */       preparedStatement.setBoolean(3, market.active);
/*  85 */       preparedStatement.setInt(4, market.createdBy);
/*  86 */       preparedStatement.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */       
/*  88 */       preparedStatement.setInt(6, market.marketId);
/*  89 */       return (preparedStatement.executeUpdate() > 0) ? new ErevenueResponse(200, "Records Updated") : new ErevenueResponse(201, "Nothing To Update");
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  94 */     catch (SQLException sqlEx) {
/*  95 */       sqlEx.printStackTrace();
/*  96 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     }
/*  98 */     catch (Exception ex) {
/*  99 */       ex.printStackTrace();
/* 100 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     } finally {
/* 102 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Market> GetAllMarkets() {
/* 113 */     Connection connection = null;
/* 114 */     PreparedStatement preparedStatement = null;
/* 115 */     ResultSet resultSet = null;
/* 116 */     int count = 1;
/*     */     try {
/* 118 */       connection = this.dataSource.getConnection();
/* 119 */       preparedStatement = connection.prepareStatement(Queryconstants.getMarkets);
/*     */       
/* 121 */       resultSet = preparedStatement.executeQuery();
/* 122 */       List<Market> markets = new ArrayList<>();
/* 123 */       while (resultSet.next()) {
/* 124 */         markets.add(new Market(resultSet.getInt("ID"), resultSet.getString("mkt_Code"), resultSet.getString("mkt_Name"), resultSet.getInt("ward_id"), resultSet.getString("ward_name"), resultSet.getInt("sub_county_id"), resultSet.getString("name"), resultSet.getBoolean("active"), 200, count, resultSet.getString("status")));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 133 */         count++;
/*     */       } 
/* 135 */       return markets;
/* 136 */     } catch (Exception ex) {
/* 137 */       ex.printStackTrace();
/* 138 */       return null;
/*     */     } finally {
/* 140 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Ward> GetActiveWards() {
/* 150 */     Connection connection = null;
/* 151 */     PreparedStatement preparedStatement = null;
/* 152 */     ResultSet resultSet = null;
/* 153 */     int count = 1;
/*     */     try {
/* 155 */       connection = this.dataSource.getConnection();
/* 156 */       preparedStatement = connection.prepareStatement(Queryconstants.getActiveWards);
/*     */       
/* 158 */       resultSet = preparedStatement.executeQuery();
/* 159 */       List<Ward> wards = new ArrayList<>();
/* 160 */       while (resultSet.next()) {
/* 161 */         wards.add(new Ward(resultSet.getInt("id"), resultSet.getString("ward_Name")));
/*     */         
/* 163 */         count++;
/*     */       } 
/* 165 */       return wards;
/* 166 */     } catch (Exception ex) {
/* 167 */       ex.printStackTrace();
/* 168 */       return null;
/*     */     } finally {
/* 170 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkMarketName(String marketName) {
/* 180 */     Connection connection = null;
/* 181 */     PreparedStatement preparedStatement = null;
/* 182 */     ResultSet resultSet = null;
/*     */     try {
/* 184 */       connection = this.dataSource.getConnection();
/* 185 */       preparedStatement = connection.prepareStatement(Queryconstants.checkMarketName);
/*     */       
/* 187 */       preparedStatement.setString(1, marketName);
/*     */       
/* 189 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 191 */       if (resultSet.next()) {
/* 192 */         return true;
/*     */       }
/* 194 */       return false;
/*     */     }
/* 196 */     catch (Exception ex) {
/* 197 */       ex.printStackTrace();
/* 198 */       return false;
/*     */     } finally {
/* 200 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkMarketCode(String marketode) {
/* 210 */     Connection connection = null;
/* 211 */     PreparedStatement preparedStatement = null;
/* 212 */     ResultSet resultSet = null;
/*     */     try {
/* 214 */       connection = this.dataSource.getConnection();
/* 215 */       preparedStatement = connection.prepareStatement(Queryconstants.checkMarketCode);
/*     */       
/* 217 */       preparedStatement.setString(1, marketode);
/*     */       
/* 219 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 221 */       if (resultSet.next()) {
/* 222 */         return true;
/*     */       }
/* 224 */       return false;
/*     */     }
/* 226 */     catch (Exception ex) {
/* 227 */       ex.printStackTrace();
/* 228 */       return false;
/*     */     } finally {
/* 230 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkMarketNameByCode(String marketName, String Code) {
/* 241 */     Connection connection = null;
/* 242 */     PreparedStatement preparedStatement = null;
/* 243 */     ResultSet resultSet = null;
/*     */     try {
/* 245 */       connection = this.dataSource.getConnection();
/* 246 */       preparedStatement = connection.prepareStatement(Queryconstants.checkMarketNameByCode);
/*     */       
/* 248 */       preparedStatement.setString(1, marketName);
/* 249 */       preparedStatement.setString(2, Code);
/*     */       
/* 251 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 253 */       if (resultSet.next()) {
/* 254 */         return true;
/*     */       }
/* 256 */       return false;
/*     */     }
/* 258 */     catch (Exception ex) {
/* 259 */       ex.printStackTrace();
/* 260 */       return false;
/*     */     } finally {
/* 262 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\impl\MarketDalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */