/*     */ package com.compulynx.erevenue.dal.impl;
/*     */ 
/*     */ import com.compulynx.erevenue.dal.UserDal;
/*     */ import com.compulynx.erevenue.dal.operations.AES;
/*     */ import com.compulynx.erevenue.dal.operations.DBOperations;
/*     */ import com.compulynx.erevenue.dal.operations.Queryconstants;
/*     */ import com.compulynx.erevenue.models.ErevenueResponse;
/*     */ import com.compulynx.erevenue.models.User;
/*     */ import com.compulynx.erevenue.models.UserGroup;
/*     */ import com.compulynx.erevenue.models.UserType;
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
/*     */ public class UserDalImpl
/*     */   implements UserDal
/*     */ {
/*     */   private DataSource dataSource;
/*     */   
/*     */   public UserDalImpl(DataSource dataSource) {
/*  29 */     this.dataSource = dataSource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkUserName(String userName) {
/*  38 */     Connection connection = null;
/*  39 */     PreparedStatement preparedStatement = null;
/*  40 */     ResultSet resultSet = null;
/*     */     try {
/*  42 */       connection = this.dataSource.getConnection();
/*  43 */       preparedStatement = connection.prepareStatement(Queryconstants.getUserByName);
/*     */       
/*  45 */       preparedStatement.setString(1, userName);
/*     */       
/*  47 */       resultSet = preparedStatement.executeQuery();
/*     */       
/*  49 */       if (resultSet.next()) {
/*  50 */         return true;
/*     */       }
/*  52 */       return false;
/*     */     }
/*  54 */     catch (Exception ex) {
/*  55 */       ex.printStackTrace();
/*  56 */       return false;
/*     */     } finally {
/*  58 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkNationalId(String nationalId, String userName) {
/*  69 */     Connection connection = null;
/*  70 */     PreparedStatement preparedStatement = null;
/*  71 */     ResultSet resultSet = null;
/*     */     try {
/*  73 */       connection = this.dataSource.getConnection();
/*  74 */       preparedStatement = connection.prepareStatement(Queryconstants.checkIdNumber);
/*     */       
/*  76 */       preparedStatement.setString(1, nationalId);
/*  77 */       preparedStatement.setString(2, userName);
/*  78 */       resultSet = preparedStatement.executeQuery();
/*     */       
/*  80 */       if (resultSet.next()) {
/*  81 */         return true;
/*     */       }
/*  83 */       return false;
/*     */     }
/*  85 */     catch (Exception ex) {
/*  86 */       ex.printStackTrace();
/*  87 */       return false;
/*     */     } finally {
/*  89 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ErevenueResponse UpdateUser(User user) {
/*  99 */     Connection connection = null;
/* 100 */     PreparedStatement preparedStatement = null;
/* 101 */     ResultSet resultSet = null;
/* 102 */     ResultSet rs = null;
/* 103 */     int userId = 0;
/*     */     try {
/* 105 */       connection = this.dataSource.getConnection();
/*     */       
/* 107 */       if (user.userId == 0) {
/* 108 */         if (checkUserName(user.userName)) {
/* 109 */           return new ErevenueResponse(201, "User Name is Already Exists");
/*     */         }
/*     */         
/* 112 */         if (checkNationalId(user.userNationalId, user.userName)) {
/* 113 */           return new ErevenueResponse(201, "National id number is Already Exists");
/*     */         }
/*     */         
/* 116 */         if ((user.userTypeId == 3 || user.userTypeId == 4) && 
/* 117 */           ValidateSignUpUser(user.userName)) {
/* 118 */           return new ErevenueResponse(201, "Agent user name is already exists as system operation user");
/*     */         }
/*     */ 
/*     */         
/* 122 */         preparedStatement = connection.prepareStatement(Queryconstants.insertUserDetails, 1);
/*     */ 
/*     */         
/* 125 */         preparedStatement.setString(1, user.userName);
/* 126 */         preparedStatement.setString(2, user.userFullName);
/* 127 */         preparedStatement.setString(3, AES.encrypt(user.userPwd));
/* 128 */         if (user.userTypeId == 3) {
/* 129 */           preparedStatement.setInt(4, GetAgentGroupId(2));
/* 130 */         } else if (user.userTypeId == 4) {
/* 131 */           preparedStatement.setInt(4, GetAgentGroupId(3));
/*     */         } else {
/* 133 */           preparedStatement.setInt(4, user.groupId);
/*     */         } 
/* 135 */         preparedStatement.setString(5, user.userEmail);
/* 136 */         preparedStatement.setString(6, user.userPhone);
/* 137 */         preparedStatement.setBoolean(7, user.active);
/* 138 */         preparedStatement.setInt(8, user.createdBy);
/* 139 */         preparedStatement.setTimestamp(9, new Timestamp((new Date()).getTime()));
/*     */         
/* 141 */         preparedStatement.setInt(10, user.userTypeId);
/* 142 */         preparedStatement.setString(11, user.userNationalId);
/* 143 */         preparedStatement.setInt(12, user.marketId);
/* 144 */         preparedStatement.setString(13, user.userPwd);
/*     */       } else {
/*     */         
/* 147 */         preparedStatement = connection.prepareStatement(Queryconstants.updateUserDeatils);
/*     */         
/* 149 */         preparedStatement.setString(1, user.userName);
/* 150 */         preparedStatement.setString(2, user.userFullName);
/* 151 */         preparedStatement.setString(3, AES.encrypt(user.userPwd));
/* 152 */         if (user.userTypeId == 3) {
/* 153 */           preparedStatement.setInt(4, GetAgentGroupId(2));
/* 154 */         } else if (user.userTypeId == 4) {
/* 155 */           preparedStatement.setInt(4, GetAgentGroupId(3));
/*     */         } else {
/* 157 */           preparedStatement.setInt(4, user.groupId);
/*     */         } 
/* 159 */         preparedStatement.setString(5, user.userEmail);
/* 160 */         preparedStatement.setString(6, user.userPhone);
/* 161 */         preparedStatement.setBoolean(7, user.active);
/* 162 */         preparedStatement.setInt(8, user.createdBy);
/* 163 */         preparedStatement.setTimestamp(9, new Timestamp((new Date()).getTime()));
/*     */ 
/*     */         
/* 166 */         preparedStatement.setInt(10, user.userTypeId);
/*     */         
/* 168 */         preparedStatement.setString(11, user.userNationalId);
/* 169 */         preparedStatement.setInt(12, user.marketId);
/* 170 */         preparedStatement.setString(13, user.userPwd);
/* 171 */         preparedStatement.setInt(14, user.userId);
/*     */       } 
/*     */       
/* 174 */       return (preparedStatement.executeUpdate() > 0) ? new ErevenueResponse(200, "Records Updated") : new ErevenueResponse(201, "Nothing To Update");
/*     */     
/*     */     }
/* 177 */     catch (SQLException sqlEx) {
/* 178 */       sqlEx.printStackTrace();
/* 179 */       if (sqlEx.getMessage().indexOf("Cannot insert duplicate key") != 0) {
/* 180 */         return new ErevenueResponse(201, "User Name is Already Exists");
/*     */       }
/* 182 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     }
/* 184 */     catch (Exception ex) {
/* 185 */       ex.printStackTrace();
/*     */       try {
/* 187 */         connection.rollback();
/* 188 */       } catch (SQLException e) {
/* 189 */         e.printStackTrace();
/*     */       } 
/* 191 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     } finally {
/* 193 */       DBOperations.DisposeSql(rs);
/* 194 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ValidateSignUpUser(String userName) {
/* 204 */     Connection connection = null;
/* 205 */     PreparedStatement preparedStatement = null;
/* 206 */     ResultSet resultSet = null;
/*     */     try {
/* 208 */       connection = this.dataSource.getConnection();
/* 209 */       preparedStatement = connection.prepareStatement(Queryconstants.validateSignUpUser);
/*     */       
/* 211 */       preparedStatement.setString(1, userName);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int GetAgentGroupId(int userType) {
/* 234 */     Connection connection = null;
/* 235 */     PreparedStatement preparedStatement = null;
/* 236 */     ResultSet resultSet = null;
/* 237 */     int groupId = 0;
/*     */     try {
/* 239 */       connection = this.dataSource.getConnection();
/* 240 */       preparedStatement = connection.prepareStatement(Queryconstants.getAgentGroupId);
/*     */       
/* 242 */       preparedStatement.setInt(1, userType);
/* 243 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 245 */       if (resultSet.next()) {
/* 246 */         return groupId = resultSet.getInt("id");
/*     */       }
/* 248 */       return groupId = 0;
/*     */     }
/* 250 */     catch (Exception ex) {
/* 251 */       ex.printStackTrace();
/* 252 */       return groupId = 0;
/*     */     } finally {
/* 254 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<User> GetAllUsers() {
/* 263 */     Connection connection = null;
/* 264 */     PreparedStatement preparedStatement = null;
/* 265 */     ResultSet resultSet = null;
/* 266 */     ResultSet resultSet2 = null;
/* 267 */     int counter = 1;
/*     */     try {
/* 269 */       connection = this.dataSource.getConnection();
/* 270 */       preparedStatement = connection.prepareStatement(Queryconstants.getUsers);
/*     */ 
/*     */       
/* 273 */       resultSet = preparedStatement.executeQuery();
/* 274 */       List<User> users = new ArrayList<>();
/* 275 */       while (resultSet.next()) {
/* 276 */         User objUser = new User();
/* 277 */         objUser.count = counter;
/* 278 */         objUser.userId = resultSet.getInt("ID");
/* 279 */         objUser.userName = resultSet.getString("UserName");
/* 280 */         objUser.userFullName = resultSet.getString("Name");
/* 281 */         objUser.userPwd = AES.decrypt(resultSet.getString("Password"));
/* 282 */         objUser.userEmail = resultSet.getString("Email");
/* 283 */         objUser.userPhone = resultSet.getString("Phone");
/* 284 */         objUser.groupId = resultSet.getInt("User_Group_ID");
/* 285 */         objUser.groupName = resultSet.getString("Group_Name");
/* 286 */         objUser.active = resultSet.getBoolean("status");
/* 287 */         objUser.createdBy = resultSet.getInt("created_By");
/* 288 */         objUser.userTypeId = resultSet.getInt("user_Type_Id");
/* 289 */         objUser.userNationalId = resultSet.getString("National_Id_no");
/* 290 */         objUser.marketId = resultSet.getInt("market_Id");
/* 291 */         objUser.status = resultSet.getString("active");
/* 292 */         users.add(objUser);
/* 293 */         counter++;
/*     */       } 
/* 295 */       return users;
/* 296 */     } catch (Exception ex) {
/* 297 */       ex.printStackTrace();
/* 298 */       return null;
/*     */     } finally {
/* 300 */       DBOperations.DisposeSql(resultSet2);
/* 301 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<UserType> GetUserTypes() {
/* 310 */     Connection connection = null;
/* 311 */     PreparedStatement preparedStatement = null;
/* 312 */     ResultSet resultSet = null;
/*     */     try {
/* 314 */       connection = this.dataSource.getConnection();
/* 315 */       preparedStatement = connection.prepareStatement(Queryconstants.getUserTypes);
/*     */ 
/*     */       
/* 318 */       resultSet = preparedStatement.executeQuery();
/* 319 */       List<UserType> userTypes = new ArrayList<>();
/* 320 */       while (resultSet.next()) {
/* 321 */         userTypes.add(new UserType(resultSet.getInt("ID"), resultSet.getString("type_name")));
/*     */       }
/*     */       
/* 324 */       return userTypes;
/* 325 */     } catch (Exception ex) {
/* 326 */       ex.printStackTrace();
/* 327 */       return null;
/*     */     } finally {
/* 329 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<UserGroup> GetActiveGroups() {
/* 338 */     Connection connection = null;
/* 339 */     PreparedStatement preparedStatement = null;
/* 340 */     ResultSet resultSet = null;
/*     */     try {
/* 342 */       connection = this.dataSource.getConnection();
/* 343 */       preparedStatement = connection.prepareStatement(Queryconstants.getActiveGroups);
/*     */ 
/*     */       
/* 346 */       resultSet = preparedStatement.executeQuery();
/* 347 */       List<UserGroup> groups = new ArrayList<>();
/* 348 */       while (resultSet.next()) {
/* 349 */         groups.add(new UserGroup(resultSet.getInt("ID"), resultSet.getString("Group_name"), 200));
/*     */       }
/*     */       
/* 352 */       return groups;
/* 353 */     } catch (Exception ex) {
/* 354 */       ex.printStackTrace();
/* 355 */       return null;
/*     */     } finally {
/* 357 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\impl\UserDalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */