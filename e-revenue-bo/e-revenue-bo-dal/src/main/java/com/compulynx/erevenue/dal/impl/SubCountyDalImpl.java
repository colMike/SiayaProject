/*     */ package com.compulynx.erevenue.dal.impl;
/*     */ 
/*     */ import com.compulynx.erevenue.dal.SubCountyDal;
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
/*     */ 
/*     */ 
/*     */ public class SubCountyDalImpl
/*     */   implements SubCountyDal
/*     */ {
/*     */   private DataSource dataSource;
/*     */   
/*     */   public SubCountyDalImpl(DataSource dataSource) {
/*  35 */     this.dataSource = dataSource;
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
/*     */   public ErevenueResponse UpdateSubCounty(SubCounty subCounty) {
/*  48 */     Connection connection = null;
/*  49 */     PreparedStatement preparedStatement = null;
/*  50 */     PreparedStatement preparedStatement1 = null;
/*  51 */     PreparedStatement preparedStatement2 = null;
/*  52 */     PreparedStatement preparedStatement3 = null;
/*  53 */     ResultSet resultSet = null;
/*  54 */     int wardId = 0;
/*     */     try {
/*  56 */       connection = this.dataSource.getConnection();
/*  57 */       connection.setAutoCommit(false);
/*  58 */       if (subCounty.subCountyId == 0) {
/*  59 */         if (checkSubCountyName(subCounty.subCountyName)) {
/*  60 */           return new ErevenueResponse(201, "Sub-County name already exists");
/*     */         }
/*     */         
/*  63 */         if (checkSubCountyCode(subCounty.subCountyCode)) {
/*  64 */           return new ErevenueResponse(201, "Sub-County code already exists");
/*     */         }
/*     */         
/*  67 */         preparedStatement = connection.prepareStatement(Queryconstants.insertSubCounty);
/*     */         
/*  69 */         preparedStatement.setString(1, subCounty.subCountyCode);
/*  70 */         preparedStatement.setString(2, subCounty.subCountyName);
/*  71 */         preparedStatement.setBoolean(3, subCounty.active);
/*  72 */         preparedStatement.setInt(4, subCounty.createdBy);
/*  73 */         preparedStatement.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */       }
/*     */       else {
/*     */         
/*  77 */         if (checkSubCountyNameByCode(subCounty.subCountyName, subCounty.subCountyCode))
/*     */         {
/*  79 */           return new ErevenueResponse(201, "Sub-County name already exists");
/*     */         }
/*     */         
/*  82 */         preparedStatement = connection.prepareStatement(Queryconstants.updateSubCounty);
/*     */         
/*  84 */         preparedStatement.setString(1, subCounty.subCountyName);
/*  85 */         preparedStatement.setBoolean(2, subCounty.active);
/*  86 */         preparedStatement.setInt(3, subCounty.createdBy);
/*  87 */         preparedStatement.setTimestamp(4, new Timestamp((new Date()).getTime()));
/*     */         
/*  89 */         preparedStatement.setInt(5, subCounty.subCountyId);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  95 */         preparedStatement1 = connection.prepareStatement(Queryconstants.actiDeactiWardsBySubCounty);
/*     */         
/*  97 */         preparedStatement1.setBoolean(1, subCounty.active);
/*  98 */         preparedStatement1.setInt(2, subCounty.createdBy);
/*  99 */         preparedStatement1.setTimestamp(3, new Timestamp((new Date()).getTime()));
/*     */         
/* 101 */         preparedStatement1.setInt(4, subCounty.subCountyId);
/* 102 */         preparedStatement1.executeUpdate();
/*     */ 
/*     */ 
/*     */         
/* 106 */         DBOperations.DisposeSql(preparedStatement1);
/* 107 */         preparedStatement2 = connection.prepareStatement(Queryconstants.getWardBySubCountyId);
/*     */         
/* 109 */         preparedStatement2.setInt(1, subCounty.subCountyId);
/* 110 */         resultSet = preparedStatement2.executeQuery();
/* 111 */         ArrayList<Ward> wardList = new ArrayList<>();
/* 112 */         while (resultSet.next()) {
/* 113 */           Ward objWard = new Ward();
/* 114 */           objWard.wardId = resultSet.getInt("id");
/* 115 */           wardList.add(objWard);
/*     */         } 
/* 117 */         DBOperations.DisposeSql(preparedStatement2, resultSet);
/* 118 */         for (int i = 0; i < wardList.size(); i++) {
/* 119 */           preparedStatement3 = connection.prepareStatement(Queryconstants.actiDeactiMarketsByWard);
/*     */           
/* 121 */           preparedStatement3.setBoolean(1, subCounty.active);
/* 122 */           preparedStatement3.setInt(2, subCounty.createdBy);
/* 123 */           preparedStatement3.setTimestamp(3, new Timestamp((new Date()).getTime()));
/*     */           
/* 125 */           preparedStatement3.setInt(4, ((Ward)wardList.get(i)).wardId);
/* 126 */           preparedStatement3.executeUpdate();
/*     */         } 
/*     */       } 
/*     */       
/* 130 */       if (preparedStatement.executeUpdate() > 0) {
/* 131 */         connection.commit();
/* 132 */         return new ErevenueResponse(200, "Records Updated");
/*     */       } 
/*     */       
/* 135 */       connection.rollback();
/* 136 */       return new ErevenueResponse(201, "Nothing To Update");
/*     */     
/*     */     }
/* 139 */     catch (SQLException sqlEx) {
/* 140 */       sqlEx.printStackTrace();
/* 141 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     }
/* 143 */     catch (Exception ex) {
/* 144 */       ex.printStackTrace();
/* 145 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     } finally {
/* 147 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/* 148 */       DBOperations.DisposeSql(preparedStatement3);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<SubCounty> GetAllSubCounties() {
/* 159 */     Connection connection = null;
/* 160 */     PreparedStatement preparedStatement = null;
/* 161 */     ResultSet resultSet = null;
/* 162 */     int count = 1;
/*     */     try {
/* 164 */       connection = this.dataSource.getConnection();
/* 165 */       preparedStatement = connection.prepareStatement(Queryconstants.getSubcounties);
/*     */       
/* 167 */       resultSet = preparedStatement.executeQuery();
/* 168 */       List<SubCounty> subCounties = new ArrayList<>();
/* 169 */       while (resultSet.next()) {
/* 170 */         subCounties.add(new SubCounty(resultSet.getInt("ID"), resultSet.getString("Code"), resultSet.getString("Name"), resultSet.getBoolean("active"), 200, count, resultSet.getString("status")));
/*     */ 
/*     */ 
/*     */         
/* 174 */         count++;
/*     */       } 
/* 176 */       return subCounties;
/* 177 */     } catch (Exception ex) {
/* 178 */       ex.printStackTrace();
/* 179 */       return null;
/*     */     } finally {
/* 181 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkSubCountyName(String subCountyName) {
/* 192 */     Connection connection = null;
/* 193 */     PreparedStatement preparedStatement = null;
/* 194 */     ResultSet resultSet = null;
/*     */     try {
/* 196 */       connection = this.dataSource.getConnection();
/* 197 */       preparedStatement = connection.prepareStatement(Queryconstants.checkSubCountyName);
/*     */       
/* 199 */       preparedStatement.setString(1, subCountyName);
/*     */       
/* 201 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 203 */       if (resultSet.next()) {
/* 204 */         return true;
/*     */       }
/* 206 */       return false;
/*     */     }
/* 208 */     catch (Exception ex) {
/* 209 */       ex.printStackTrace();
/* 210 */       return false;
/*     */     } finally {
/* 212 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkSubCountyCode(String subCountyCode) {
/* 223 */     Connection connection = null;
/* 224 */     PreparedStatement preparedStatement = null;
/* 225 */     ResultSet resultSet = null;
/*     */     try {
/* 227 */       connection = this.dataSource.getConnection();
/* 228 */       preparedStatement = connection.prepareStatement(Queryconstants.checkSubCountycode);
/*     */       
/* 230 */       preparedStatement.setString(1, subCountyCode);
/*     */       
/* 232 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 234 */       if (resultSet.next()) {
/* 235 */         return true;
/*     */       }
/* 237 */       return false;
/*     */     }
/* 239 */     catch (Exception ex) {
/* 240 */       ex.printStackTrace();
/* 241 */       return false;
/*     */     } finally {
/* 243 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
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
/*     */   public boolean checkSubCountyNameByCode(String subCountyName, String subCountyCode) {
/* 256 */     Connection connection = null;
/* 257 */     PreparedStatement preparedStatement = null;
/* 258 */     ResultSet resultSet = null;
/*     */     try {
/* 260 */       connection = this.dataSource.getConnection();
/* 261 */       preparedStatement = connection.prepareStatement(Queryconstants.checkSubCountyNameByCode);
/*     */       
/* 263 */       preparedStatement.setString(1, subCountyName);
/* 264 */       preparedStatement.setString(2, subCountyCode);
/*     */       
/* 266 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 268 */       if (resultSet.next()) {
/* 269 */         return true;
/*     */       }
/* 271 */       return false;
/*     */     }
/* 273 */     catch (Exception ex) {
/* 274 */       ex.printStackTrace();
/* 275 */       return false;
/*     */     } finally {
/* 277 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\impl\SubCountyDalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */