/*     */ package com.compulynx.erevenue.dal.impl;
/*     */ 
/*     */ import com.compulynx.erevenue.dal.LoginDal;
/*     */ import com.compulynx.erevenue.dal.operations.AES;
/*     */ import com.compulynx.erevenue.dal.operations.DBOperations;
/*     */ import com.compulynx.erevenue.dal.operations.Queryconstants;
/*     */ import com.compulynx.erevenue.models.ConfigParams;
/*     */ import com.compulynx.erevenue.models.ErevenueResponse;
/*     */ import com.compulynx.erevenue.models.LoginSession;
/*     */ import com.compulynx.erevenue.models.LoginUser;
/*     */ import com.compulynx.erevenue.models.Rights;
/*     */ import com.compulynx.erevenue.models.RightsDetail;
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
/*     */ public class LoginDalImpl
/*     */   implements LoginDal
/*     */ {
/*     */   private DataSource dataSource;
/*     */   
/*     */   public LoginDalImpl(DataSource dataSource) {
/*  36 */     this.dataSource = dataSource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LoginUser GetUserIdManual(String userName, String password) {
/*  47 */     Connection connection = null;
/*  48 */     PreparedStatement preparedStatement = null;
/*  49 */     ResultSet resultSet = null;
/*     */     try {
/*  51 */       connection = this.dataSource.getConnection();
/*  52 */       preparedStatement = connection.prepareStatement(Queryconstants.getUserCredentialManual);
/*     */       
/*  54 */       preparedStatement.setString(1, userName);
/*  55 */       preparedStatement.setString(2, AES.encrypt(password));
/*  56 */       resultSet = preparedStatement.executeQuery();
/*     */       
/*  58 */       if (resultSet.next()) {
/*  59 */         if (!resultSet.getBoolean("status")) {
/*  60 */           return new LoginUser(resultSet.getInt("UserID"), 202);
/*     */         }
/*  62 */         return new LoginUser(resultSet.getInt("UserID"), 200);
/*     */       } 
/*     */       
/*  65 */       return new LoginUser(201);
/*     */     
/*     */     }
/*  68 */     catch (Exception ex) {
/*  69 */       ex.printStackTrace();
/*  70 */       return new LoginUser(404);
/*     */     } finally {
/*  72 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LoginSession GetUserAssgnRightsList(int userId) {
/*  83 */     Connection connection = null;
/*  84 */     PreparedStatement preparedStatement = null;
/*  85 */     ResultSet resultSet = null;
/*     */     try {
/*  87 */       connection = this.dataSource.getConnection();
/*  88 */       preparedStatement = connection.prepareStatement(Queryconstants.getUserGrpRights);
/*     */       
/*  90 */       preparedStatement.setInt(1, userId);
/*  91 */       resultSet = preparedStatement.executeQuery();
/*  92 */       List<Rights> objlist = new ArrayList<>();
/*  93 */       LoginSession loginSession = new LoginSession();
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  98 */       int headerId = 0;
/*  99 */       Rights objRights = null;
/* 100 */       while (resultSet.next()) {
/*     */         
/* 102 */         if (!resultSet.getBoolean("status")) {
/* 103 */           loginSession.setRespCode(202);
/* 104 */           return loginSession;
/*     */         } 
/* 106 */         loginSession.setLinkId(resultSet.getInt("LinkId"));
/* 107 */         loginSession.setSessionId(resultSet.getInt("ID"));
/* 108 */         loginSession.setUserGroupId(resultSet.getInt("UserGroupID"));
/* 109 */         loginSession.setLinkName(resultSet.getString("LinkName"));
/* 110 */         loginSession.setSessionName(resultSet.getString("UserName"));
/* 111 */         loginSession.setSessionFullName(resultSet.getString("name"));
/* 112 */         loginSession.setLinkExtInfo(resultSet.getString("LinkExtInfo"));
/* 113 */         loginSession.setAppName(resultSet.getString("appname"));
/* 114 */         if (headerId != resultSet.getInt("HeaderID")) {
/*     */           
/* 116 */           if (objRights != null) {
/* 117 */             objlist.add(objRights);
/*     */           }
/* 119 */           objRights = new Rights(resultSet.getString("Header_Name"), resultSet.getString("Header_Icon_Css"), resultSet.getString("Header_Icon_Color"));
/*     */         } 
/*     */ 
/*     */         
/* 123 */         if (objRights != null) {
/* 124 */           objRights.rightsList.add(new RightsDetail(resultSet.getString("Right_Display_Name"), resultSet.getString("Right_Short_Code"), resultSet.getString("Right_View_Name"), resultSet.getString("Right_Name"), resultSet.getBoolean("Allow_Add"), resultSet.getBoolean("Allow_Edit"), resultSet.getBoolean("Allow_Delete"), resultSet.getBoolean("Allow_View"), resultSet.getInt("Right_Max_Width")));
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 135 */         headerId = resultSet.getInt("HeaderID");
/*     */       } 
/* 137 */       if (objRights != null) {
/* 138 */         objlist.add(objRights);
/*     */       }
/* 140 */       loginSession.setRightsList(objlist);
/* 141 */       loginSession.setRespCode(200);
/* 142 */       return loginSession;
/* 143 */     } catch (Exception ex) {
/* 144 */       System.out.println(ex);
/* 145 */       return new LoginSession(500);
/*     */     } finally {
/* 147 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<ConfigParams> GetConfigParams() {
/* 156 */     Connection connection = null;
/* 157 */     PreparedStatement preparedStatement = null;
/* 158 */     ResultSet resultSet = null;
/*     */     try {
/* 160 */       connection = this.dataSource.getConnection();
/* 161 */       preparedStatement = connection.prepareStatement(Queryconstants.getConfigParams);
/*     */ 
/*     */       
/* 164 */       resultSet = preparedStatement.executeQuery();
/* 165 */       List<ConfigParams> config = new ArrayList<>();
/* 166 */       while (resultSet.next()) {
/* 167 */         config.add(new ConfigParams(resultSet.getInt("Id"), resultSet.getString("name"), resultSet.getString("value"), resultSet.getInt("created_by"), resultSet.getString("value")));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 172 */       return config;
/* 173 */     } catch (Exception ex) {
/* 174 */       ex.printStackTrace();
/* 175 */       return null;
/*     */     } finally {
/* 177 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ErevenueResponse UpdateConfigParam(ConfigParams params) {
/* 186 */     Connection connection = null;
/* 187 */     PreparedStatement preparedStatement = null;
/* 188 */     PreparedStatement preparedStatement2 = null;
/* 189 */     ResultSet resultSet = null;
/*     */     try {
/* 191 */       connection = this.dataSource.getConnection();
/* 192 */       connection.setAutoCommit(false);
/*     */       
/* 194 */       preparedStatement = connection.prepareStatement(Queryconstants.updateConfigParam);
/*     */ 
/*     */ 
/*     */       
/* 198 */       for (int i = 0; i < params.configParams.size(); i++) {
/*     */         
/* 200 */         preparedStatement.setString(1, ((ConfigParams)params.configParams.get(i)).value);
/*     */         
/* 202 */         preparedStatement.setInt(2, params.createdBy);
/* 203 */         preparedStatement.setTimestamp(3, new Timestamp((new Date()).getTime()));
/*     */         
/* 205 */         preparedStatement.setInt(4, ((ConfigParams)params.configParams.get(i)).configId);
/*     */         
/* 207 */         if (preparedStatement.executeUpdate() <= 0) {
/* 208 */           throw new Exception("Failed to Insert Param Id " + ((ConfigParams)params.configParams.get(i)).configId);
/*     */         }
/*     */       } 
/*     */       
/* 212 */       DBOperations.DisposeSql(preparedStatement);
/* 213 */       preparedStatement = connection.prepareStatement(Queryconstants.updateConfigParamLogo);
/*     */       
/* 215 */       preparedStatement.setString(1, params.logo);
/* 216 */       preparedStatement.executeUpdate();
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
/* 234 */       connection.commit();
/* 235 */       return new ErevenueResponse(200, "Records Updated");
/*     */     }
/* 237 */     catch (SQLException sqlEx) {
/* 238 */       sqlEx.printStackTrace();
/* 239 */       if (sqlEx.getMessage().indexOf("Cannot insert duplicate key") != 0) {
/* 240 */         return new ErevenueResponse(201, "Failed to update");
/*     */       }
/* 242 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     }
/* 244 */     catch (Exception ex) {
/* 245 */       ex.printStackTrace();
/* 246 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     } finally {
/* 248 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\impl\LoginDalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */