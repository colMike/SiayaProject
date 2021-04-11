/*     */ package com.compulynx.erevenue.dal.impl;
/*     */ 
/*     */ import com.compulynx.erevenue.dal.WardDal;
/*     */ import com.compulynx.erevenue.dal.operations.DBOperations;
/*     */ import com.compulynx.erevenue.dal.operations.Queryconstants;
/*     */ import com.compulynx.erevenue.models.ErevenueResponse;
/*     */ import com.compulynx.erevenue.models.SubCounty;
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
/*     */ public class WardDalImpl
/*     */   implements WardDal
/*     */ {
/*     */   private DataSource dataSource;
/*     */   
/*     */   public WardDalImpl(DataSource dataSource) {
/*  33 */     this.dataSource = dataSource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ErevenueResponse UpdateWard(Ward ward) {
/*  44 */     Connection connection = null;
/*  45 */     PreparedStatement preparedStatement = null;
/*  46 */     PreparedStatement preparedStatement1 = null;
/*  47 */     ResultSet resultSet = null;
/*     */     try {
/*  49 */       connection = this.dataSource.getConnection();
/*  50 */       if (ward.wardId == 0) {
/*  51 */         if (checkWardName(ward.wardName)) {
/*  52 */           return new ErevenueResponse(201, "Ward name already exists");
/*     */         }
/*  54 */         if (checkWardCode(ward.wardCode)) {
/*  55 */           return new ErevenueResponse(201, "Ward code already exists");
/*     */         }
/*  57 */         preparedStatement = connection.prepareStatement(Queryconstants.insertWard);
/*     */         
/*  59 */         preparedStatement.setString(1, ward.wardCode);
/*  60 */         preparedStatement.setString(2, ward.wardName);
/*  61 */         preparedStatement.setInt(3, ward.subCountyId);
/*  62 */         preparedStatement.setBoolean(4, ward.active);
/*  63 */         preparedStatement.setInt(5, ward.createdBy);
/*  64 */         preparedStatement.setTimestamp(6, new Timestamp((new Date()).getTime()));
/*     */       } else {
/*     */         
/*  67 */         if (checkWardNameByCode(ward.wardName, ward.wardCode)) {
/*  68 */           return new ErevenueResponse(201, "Ward name already exists");
/*     */         }
/*  70 */         preparedStatement = connection.prepareStatement(Queryconstants.updateWard);
/*     */         
/*  72 */         preparedStatement.setString(1, ward.wardName);
/*  73 */         preparedStatement.setInt(2, ward.subCountyId);
/*  74 */         preparedStatement.setBoolean(3, ward.active);
/*  75 */         preparedStatement.setInt(4, ward.createdBy);
/*  76 */         preparedStatement.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */         
/*  78 */         preparedStatement.setInt(6, ward.wardId);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  84 */         preparedStatement1 = connection.prepareStatement(Queryconstants.actiDeactiMarketsByWard);
/*     */         
/*  86 */         preparedStatement1.setBoolean(1, ward.active);
/*  87 */         preparedStatement1.setInt(2, ward.createdBy);
/*  88 */         preparedStatement1.setTimestamp(3, new Timestamp((new Date()).getTime()));
/*     */         
/*  90 */         preparedStatement1.setInt(4, ward.wardId);
/*  91 */         preparedStatement1.executeUpdate();
/*     */       } 
/*     */ 
/*     */       
/*  95 */       return (preparedStatement.executeUpdate() > 0) ? new ErevenueResponse(200, "Records Updated") : new ErevenueResponse(201, "Nothing To Update");
/*     */     
/*     */     }
/*  98 */     catch (SQLException sqlEx) {
/*  99 */       sqlEx.printStackTrace();
/* 100 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     }
/* 102 */     catch (Exception ex) {
/* 103 */       ex.printStackTrace();
/* 104 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     } finally {
/* 106 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/* 107 */       DBOperations.DisposeSql(preparedStatement1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Ward> GetAllWards() {
/* 117 */     Connection connection = null;
/* 118 */     PreparedStatement preparedStatement = null;
/* 119 */     ResultSet resultSet = null;
/* 120 */     int count = 1;
/*     */     try {
/* 122 */       connection = this.dataSource.getConnection();
/* 123 */       preparedStatement = connection.prepareStatement(Queryconstants.getWards);
/*     */       
/* 125 */       resultSet = preparedStatement.executeQuery();
/* 126 */       List<Ward> wards = new ArrayList<>();
/* 127 */       while (resultSet.next()) {
/* 128 */         wards.add(new Ward(resultSet.getInt("ID"), resultSet.getString("ward_Code"), resultSet.getString("ward_Name"), resultSet.getInt("sub_county_id"), resultSet.getString("name"), resultSet.getBoolean("active"), 200, count, resultSet.getString("status")));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 134 */         count++;
/*     */       } 
/* 136 */       return wards;
/* 137 */     } catch (Exception ex) {
/* 138 */       ex.printStackTrace();
/* 139 */       return null;
/*     */     } finally {
/* 141 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<SubCounty> GetActiveSubCounties() {
/* 150 */     Connection connection = null;
/* 151 */     PreparedStatement preparedStatement = null;
/* 152 */     ResultSet resultSet = null;
/* 153 */     int count = 1;
/*     */     try {
/* 155 */       connection = this.dataSource.getConnection();
/* 156 */       preparedStatement = connection.prepareStatement(Queryconstants.getActiveSubCounties);
/*     */       
/* 158 */       resultSet = preparedStatement.executeQuery();
/* 159 */       List<SubCounty> subcounties = new ArrayList<>();
/* 160 */       while (resultSet.next()) {
/* 161 */         subcounties.add(new SubCounty(resultSet.getInt("ID"), resultSet.getString("name")));
/*     */         
/* 163 */         count++;
/*     */       } 
/* 165 */       return subcounties;
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
/*     */   public boolean checkWardName(String wardName) {
/* 180 */     Connection connection = null;
/* 181 */     PreparedStatement preparedStatement = null;
/* 182 */     ResultSet resultSet = null;
/*     */     try {
/* 184 */       connection = this.dataSource.getConnection();
/* 185 */       preparedStatement = connection.prepareStatement(Queryconstants.checkWardName);
/*     */       
/* 187 */       preparedStatement.setString(1, wardName);
/* 188 */       resultSet = preparedStatement.executeQuery();
/* 189 */       if (resultSet.next()) {
/* 190 */         return true;
/*     */       }
/* 192 */       return false;
/*     */     }
/* 194 */     catch (Exception ex) {
/* 195 */       ex.printStackTrace();
/* 196 */       return false;
/*     */     } finally {
/* 198 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkWardCode(String wardCode) {
/* 208 */     Connection connection = null;
/* 209 */     PreparedStatement preparedStatement = null;
/* 210 */     ResultSet resultSet = null;
/*     */     try {
/* 212 */       connection = this.dataSource.getConnection();
/* 213 */       preparedStatement = connection.prepareStatement(Queryconstants.checkWardCode);
/*     */       
/* 215 */       preparedStatement.setString(1, wardCode);
/*     */       
/* 217 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 219 */       if (resultSet.next()) {
/* 220 */         return true;
/*     */       }
/* 222 */       return false;
/*     */     }
/* 224 */     catch (Exception ex) {
/* 225 */       ex.printStackTrace();
/* 226 */       return false;
/*     */     } finally {
/* 228 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkWardNameByCode(String wardName, String Code) {
/* 239 */     Connection connection = null;
/* 240 */     PreparedStatement preparedStatement = null;
/* 241 */     ResultSet resultSet = null;
/*     */     try {
/* 243 */       connection = this.dataSource.getConnection();
/* 244 */       preparedStatement = connection.prepareStatement(Queryconstants.checkWardNameByCode);
/*     */       
/* 246 */       preparedStatement.setString(1, wardName);
/* 247 */       preparedStatement.setString(2, Code);
/*     */       
/* 249 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 251 */       if (resultSet.next()) {
/* 252 */         return true;
/*     */       }
/* 254 */       return false;
/*     */     }
/* 256 */     catch (Exception ex) {
/* 257 */       ex.printStackTrace();
/* 258 */       return false;
/*     */     } finally {
/* 260 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\impl\WardDalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */