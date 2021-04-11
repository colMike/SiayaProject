/*     */ package com.compulynx.erevenue.dal.impl;
/*     */ 
/*     */ import com.compulynx.erevenue.dal.PermitTypeDal;
/*     */ import com.compulynx.erevenue.dal.operations.DBOperations;
/*     */ import com.compulynx.erevenue.dal.operations.Queryconstants;
/*     */ import com.compulynx.erevenue.models.ErevenueResponse;
/*     */ import com.compulynx.erevenue.models.PermitType;
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
/*     */ public class PermitTypeDalImpl
/*     */   implements PermitTypeDal
/*     */ {
/*     */   private DataSource dataSource;
/*     */   
/*     */   public PermitTypeDalImpl(DataSource dataSource) {
/*  31 */     this.dataSource = dataSource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ErevenueResponse UpdatePermitType(PermitType permitType) {
/*  40 */     Connection connection = null;
/*  41 */     PreparedStatement preparedStatement = null;
/*  42 */     ResultSet resultSet = null;
/*     */     try {
/*  44 */       connection = this.dataSource.getConnection();
/*  45 */       if (permitType.permitTypeId == 0) {
/*  46 */         if (CheckPermitTypeName(permitType.permitTypeName)) {
/*  47 */           return new ErevenueResponse(201, "Permit type name already exists");
/*     */         }
/*     */         
/*  50 */         if (CheckPermitTypeCode(permitType.permitTypeCode)) {
/*  51 */           return new ErevenueResponse(201, "Permit type code already exists");
/*     */         }
/*     */         
/*  54 */         preparedStatement = connection.prepareStatement(Queryconstants.insertPermitType);
/*     */         
/*  56 */         preparedStatement.setString(1, permitType.permitTypeCode);
/*  57 */         preparedStatement.setString(2, permitType.permitTypeName);
/*  58 */         preparedStatement.setDouble(3, permitType.permitFee);
/*  59 */         preparedStatement.setBoolean(4, permitType.active);
/*  60 */         preparedStatement.setInt(5, permitType.createdBy);
/*  61 */         preparedStatement.setTimestamp(6, new Timestamp((new Date()).getTime()));
/*  62 */         preparedStatement.setString(7, permitType.permitType);
/*     */       } else {
/*     */         
/*  65 */         if (CheckPermitTypeNameByCode(permitType.permitTypeName, permitType.permitTypeCode)) {
/*  66 */           return new ErevenueResponse(201, "Permit Type name already exists");
/*     */         }
/*     */         
/*  69 */         preparedStatement = connection.prepareStatement(Queryconstants.updatePermitType);
/*     */         
/*  71 */         preparedStatement.setString(1, permitType.permitTypeName);
/*  72 */         preparedStatement.setDouble(2, permitType.permitFee);
/*  73 */         preparedStatement.setBoolean(3, permitType.active);
/*  74 */         preparedStatement.setInt(4, permitType.createdBy);
/*  75 */         preparedStatement.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */         
/*  77 */         preparedStatement.setString(6, permitType.permitType);
/*  78 */         preparedStatement.setInt(7, permitType.permitTypeId);
/*     */       } 
/*     */       
/*  81 */       return (preparedStatement.executeUpdate() > 0) ? new ErevenueResponse(200, "Records Updated") : new ErevenueResponse(201, "Nothing To Update");
/*     */     
/*     */     }
/*  84 */     catch (SQLException sqlEx) {
/*  85 */       sqlEx.printStackTrace();
/*  86 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     }
/*  88 */     catch (Exception ex) {
/*  89 */       ex.printStackTrace();
/*  90 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     } finally {
/*  92 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<PermitType> GetAllPermitTypes() {
/* 102 */     Connection connection = null;
/* 103 */     PreparedStatement preparedStatement = null;
/* 104 */     ResultSet resultSet = null;
/* 105 */     int count = 1;
/*     */     try {
/* 107 */       connection = this.dataSource.getConnection();
/* 108 */       preparedStatement = connection.prepareStatement(Queryconstants.getPermitTypes);
/*     */       
/* 110 */       resultSet = preparedStatement.executeQuery();
/* 111 */       List<PermitType> permittypes = new ArrayList<>();
/* 112 */       while (resultSet.next()) {
/* 113 */         PermitType permitType = new PermitType();
/*     */         
/* 115 */         permitType.permitTypeId = resultSet.getInt("ID");
/* 116 */         permitType.permitTypeCode = resultSet.getString("permit_type_Code");
/* 117 */         permitType.permitTypeName = resultSet.getString("permit_type_Name");
/* 118 */         permitType.permitType = resultSet.getString("permit_type");
/* 119 */         permitType.permitFee = resultSet.getDouble("permit_fee");
/* 120 */         permitType.active = resultSet.getBoolean("active");
/* 121 */         permitType.respCode = 200;
/* 122 */         permitType.count = count;
/* 123 */         permittypes.add(permitType);
/* 124 */         count++;
/*     */       } 
/* 126 */       return permittypes;
/* 127 */     } catch (Exception ex) {
/* 128 */       ex.printStackTrace();
/* 129 */       return null;
/*     */     } finally {
/* 131 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean CheckPermitTypeName(String permitTypeName) {
/* 142 */     Connection connection = null;
/* 143 */     PreparedStatement preparedStatement = null;
/* 144 */     ResultSet resultSet = null;
/*     */     try {
/* 146 */       connection = this.dataSource.getConnection();
/* 147 */       preparedStatement = connection.prepareStatement(Queryconstants.checkPermitTypeName);
/*     */       
/* 149 */       preparedStatement.setString(1, permitTypeName);
/*     */       
/* 151 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 153 */       if (resultSet.next()) {
/* 154 */         return true;
/*     */       }
/* 156 */       return false;
/*     */     }
/* 158 */     catch (Exception ex) {
/* 159 */       ex.printStackTrace();
/* 160 */       return false;
/*     */     } finally {
/* 162 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean CheckPermitTypeCode(String permitTypeCode) {
/* 172 */     Connection connection = null;
/* 173 */     PreparedStatement preparedStatement = null;
/* 174 */     ResultSet resultSet = null;
/*     */     try {
/* 176 */       connection = this.dataSource.getConnection();
/* 177 */       preparedStatement = connection.prepareStatement(Queryconstants.checkPermitTypecode);
/*     */       
/* 179 */       preparedStatement.setString(1, permitTypeCode);
/*     */       
/* 181 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 183 */       if (resultSet.next()) {
/* 184 */         return true;
/*     */       }
/* 186 */       return false;
/*     */     }
/* 188 */     catch (Exception ex) {
/* 189 */       ex.printStackTrace();
/* 190 */       return false;
/*     */     } finally {
/* 192 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean CheckPermitTypeNameByCode(String permitTypeName, String permitTypeCode) {
/* 203 */     Connection connection = null;
/* 204 */     PreparedStatement preparedStatement = null;
/* 205 */     ResultSet resultSet = null;
/*     */     try {
/* 207 */       connection = this.dataSource.getConnection();
/* 208 */       preparedStatement = connection.prepareStatement(Queryconstants.checkPermitNameByCode);
/*     */       
/* 210 */       preparedStatement.setString(1, permitTypeName);
/* 211 */       preparedStatement.setString(2, permitTypeCode);
/*     */       
/* 213 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 215 */       if (resultSet.next()) {
/* 216 */         return true;
/*     */       }
/* 218 */       return false;
/*     */     }
/* 220 */     catch (Exception ex) {
/* 221 */       ex.printStackTrace();
/* 222 */       return false;
/*     */     } finally {
/* 224 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\impl\PermitTypeDalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */