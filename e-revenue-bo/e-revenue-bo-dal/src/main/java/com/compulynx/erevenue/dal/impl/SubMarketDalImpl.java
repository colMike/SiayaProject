/*     */ package com.compulynx.erevenue.dal.impl;
/*     */ 
/*     */ import com.compulynx.erevenue.dal.SubMarketDal;
/*     */ import com.compulynx.erevenue.dal.operations.DBOperations;
/*     */ import com.compulynx.erevenue.dal.operations.Queryconstants;
/*     */ import com.compulynx.erevenue.models.ErevenueResponse;
/*     */ import com.compulynx.erevenue.models.Market;
/*     */ import com.compulynx.erevenue.models.SubMarket;
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
/*     */ public class SubMarketDalImpl
/*     */   implements SubMarketDal
/*     */ {
/*     */   private DataSource dataSource;
/*     */   
/*     */   public SubMarketDalImpl(DataSource dataSource) {
/*  33 */     this.dataSource = dataSource;
/*     */   }
/*     */   
/*     */   public ErevenueResponse UpdateSubMarket(SubMarket submarket) {
/*  37 */     Connection connection = null;
/*  38 */     PreparedStatement preparedStatement = null;
/*  39 */     ResultSet resultSet = null;
/*     */     try {
/*  41 */       connection = this.dataSource.getConnection();
/*  42 */       if (submarket.subMarketId == 0) {
/*     */         
/*  44 */         for (int i = 0; i < submarket.subMarketDetails.size(); i++) {
/*  45 */           if (CheckSubMarketName(((SubMarket)submarket.subMarketDetails.get(i)).subMarketName)) {
/*  46 */             return new ErevenueResponse(201, "Sub-Market name already exists");
/*     */           }
/*     */           
/*  49 */           if (CheckSubMarketCode(((SubMarket)submarket.subMarketDetails.get(i)).subMarketCode)) {
/*  50 */             return new ErevenueResponse(201, "Sub-Market code already exists");
/*     */           }
/*     */           
/*  53 */           preparedStatement = connection.prepareStatement(Queryconstants.insertSubMarket);
/*     */           
/*  55 */           preparedStatement.setString(1, ((SubMarket)submarket.subMarketDetails.get(i)).subMarketCode);
/*     */           
/*  57 */           preparedStatement.setString(2, ((SubMarket)submarket.subMarketDetails.get(i)).subMarketName);
/*     */           
/*  59 */           preparedStatement.setInt(3, submarket.marketId);
/*  60 */           preparedStatement.setBoolean(4, submarket.active);
/*  61 */           preparedStatement.setInt(5, submarket.createdBy);
/*  62 */           preparedStatement.setTimestamp(6, new Timestamp((new Date()).getTime()));
/*     */           
/*  64 */           if (preparedStatement.executeUpdate() <= 0) {
/*  65 */             throw new Exception("Failed to Insert Sub-Market");
/*     */           }
/*     */         } 
/*  68 */         return new ErevenueResponse(200, "Records Updated");
/*     */       } 
/*     */       
/*  71 */       if (CheckSubMarketNameByCode(submarket.subMarketName, submarket.subMarketCode)) {
/*  72 */         return new ErevenueResponse(201, "Sub-Market name already exists");
/*     */       }
/*     */       
/*  75 */       preparedStatement = connection.prepareStatement(Queryconstants.updateSubMarket);
/*     */       
/*  77 */       preparedStatement.setString(1, submarket.subMarketName);
/*  78 */       preparedStatement.setInt(2, submarket.marketId);
/*  79 */       preparedStatement.setBoolean(3, submarket.active);
/*  80 */       preparedStatement.setInt(4, submarket.createdBy);
/*  81 */       preparedStatement.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */       
/*  83 */       preparedStatement.setInt(6, submarket.subMarketId);
/*  84 */       return (preparedStatement.executeUpdate() > 0) ? new ErevenueResponse(200, "Records Updated") : new ErevenueResponse(201, "Nothing To Update");
/*     */ 
/*     */ 
/*     */     
/*     */     }
/*  89 */     catch (SQLException sqlEx) {
/*  90 */       sqlEx.printStackTrace();
/*  91 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     }
/*  93 */     catch (Exception ex) {
/*  94 */       ex.printStackTrace();
/*  95 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     } finally {
/*  97 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<SubMarket> GetAllSubMarkets() {
/* 103 */     Connection connection = null;
/* 104 */     PreparedStatement preparedStatement = null;
/* 105 */     ResultSet resultSet = null;
/* 106 */     int count = 1;
/*     */     try {
/* 108 */       connection = this.dataSource.getConnection();
/* 109 */       preparedStatement = connection.prepareStatement(Queryconstants.getSubMarkets);
/*     */       
/* 111 */       resultSet = preparedStatement.executeQuery();
/* 112 */       List<SubMarket> submarkets = new ArrayList<>();
/* 113 */       while (resultSet.next()) {
/* 114 */         submarkets.add(new SubMarket(resultSet.getInt("ID"), resultSet.getString("sub_market_Code"), resultSet.getString("sub_market_Name"), resultSet.getInt("market_id"), resultSet.getString("mkt_name"), resultSet.getBoolean("active"), 200, count, resultSet.getString("status")));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 121 */         count++;
/*     */       } 
/* 123 */       return submarkets;
/* 124 */     } catch (Exception ex) {
/* 125 */       ex.printStackTrace();
/* 126 */       return null;
/*     */     } finally {
/* 128 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public List<Market> GetActiveMarkets() {
/* 134 */     Connection connection = null;
/* 135 */     PreparedStatement preparedStatement = null;
/* 136 */     ResultSet resultSet = null;
/* 137 */     int count = 1;
/*     */     try {
/* 139 */       connection = this.dataSource.getConnection();
/* 140 */       preparedStatement = connection.prepareStatement(Queryconstants.getActiveMarkets);
/*     */       
/* 142 */       resultSet = preparedStatement.executeQuery();
/* 143 */       List<Market> markets = new ArrayList<>();
/* 144 */       while (resultSet.next()) {
/* 145 */         markets.add(new Market(resultSet.getInt("id"), resultSet.getString("mkt_Name")));
/*     */         
/* 147 */         count++;
/*     */       } 
/* 149 */       return markets;
/* 150 */     } catch (Exception ex) {
/* 151 */       ex.printStackTrace();
/* 152 */       return null;
/*     */     } finally {
/* 154 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */   public boolean CheckSubMarketName(String subMarketName) {
/* 158 */     Connection connection = null;
/* 159 */     PreparedStatement preparedStatement = null;
/* 160 */     ResultSet resultSet = null;
/*     */     try {
/* 162 */       connection = this.dataSource.getConnection();
/* 163 */       preparedStatement = connection.prepareStatement(Queryconstants.checkSubMarketName);
/*     */       
/* 165 */       preparedStatement.setString(1, subMarketName);
/*     */       
/* 167 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 169 */       if (resultSet.next()) {
/* 170 */         return true;
/*     */       }
/* 172 */       return false;
/*     */     }
/* 174 */     catch (Exception ex) {
/* 175 */       ex.printStackTrace();
/* 176 */       return false;
/*     */     } finally {
/* 178 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean CheckSubMarketCode(String subMarketode) {
/* 183 */     Connection connection = null;
/* 184 */     PreparedStatement preparedStatement = null;
/* 185 */     ResultSet resultSet = null;
/*     */     try {
/* 187 */       connection = this.dataSource.getConnection();
/* 188 */       preparedStatement = connection.prepareStatement(Queryconstants.checkSubMarketCode);
/*     */       
/* 190 */       preparedStatement.setString(1, subMarketode);
/*     */       
/* 192 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 194 */       if (resultSet.next()) {
/* 195 */         return true;
/*     */       }
/* 197 */       return false;
/*     */     }
/* 199 */     catch (Exception ex) {
/* 200 */       ex.printStackTrace();
/* 201 */       return false;
/*     */     } finally {
/* 203 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean CheckSubMarketNameByCode(String subMarketName, String Code) {
/* 208 */     Connection connection = null;
/* 209 */     PreparedStatement preparedStatement = null;
/* 210 */     ResultSet resultSet = null;
/*     */     try {
/* 212 */       connection = this.dataSource.getConnection();
/* 213 */       preparedStatement = connection.prepareStatement(Queryconstants.checkSubMarketNameByCode);
/*     */       
/* 215 */       preparedStatement.setString(1, subMarketName);
/* 216 */       preparedStatement.setString(2, Code);
/*     */       
/* 218 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 220 */       if (resultSet.next()) {
/* 221 */         return true;
/*     */       }
/* 223 */       return false;
/*     */     }
/* 225 */     catch (Exception ex) {
/* 226 */       ex.printStackTrace();
/* 227 */       return false;
/*     */     } finally {
/* 229 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\impl\SubMarketDalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */