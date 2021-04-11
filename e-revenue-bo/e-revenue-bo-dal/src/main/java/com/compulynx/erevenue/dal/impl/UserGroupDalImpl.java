/*     */ package com.compulynx.erevenue.dal.impl;
/*     */ 
/*     */ import com.compulynx.erevenue.dal.UserGroupDal;
/*     */ import com.compulynx.erevenue.dal.operations.DBOperations;
/*     */ import com.compulynx.erevenue.dal.operations.Queryconstants;
/*     */ import com.compulynx.erevenue.models.ErevenueResponse;
/*     */ import com.compulynx.erevenue.models.RightsDetail;
/*     */ import com.compulynx.erevenue.models.UserGroup;
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
/*     */ public class UserGroupDalImpl
/*     */   implements UserGroupDal
/*     */ {
/*     */   private DataSource dataSource;
/*     */   
/*     */   public UserGroupDalImpl(DataSource dataSource) {
/*  28 */     this.dataSource = dataSource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<UserGroup> GetUserGroups() {
/*  36 */     Connection connection = null;
/*  37 */     PreparedStatement preparedStatement = null;
/*  38 */     ResultSet resultSet = null;
/*  39 */     ResultSet resultSet2 = null;
/*  40 */     int counter = 1;
/*     */     try {
/*  42 */       connection = this.dataSource.getConnection();
/*  43 */       preparedStatement = connection.prepareStatement(Queryconstants.getGroups);
/*     */ 
/*     */       
/*  46 */       resultSet = preparedStatement.executeQuery();
/*  47 */       List<UserGroup> groups = new ArrayList<>();
/*  48 */       while (resultSet.next()) {
/*  49 */         UserGroup objGroup = new UserGroup();
/*  50 */         objGroup.count = counter;
/*  51 */         objGroup.groupId = resultSet.getInt("ID");
/*  52 */         objGroup.groupName = resultSet.getString("Group_Name");
/*  53 */         objGroup.active = resultSet.getBoolean("status");
/*  54 */         objGroup.createdBy = resultSet.getInt("created_By");
/*  55 */         objGroup.roleTypeId = resultSet.getInt("group_type");
/*  56 */         preparedStatement = connection.prepareStatement(Queryconstants.getGroupById);
/*     */         
/*  58 */         preparedStatement.setInt(1, objGroup.groupId);
/*  59 */         preparedStatement.setInt(2, objGroup.groupId);
/*  60 */         preparedStatement.setInt(3, objGroup.groupId);
/*  61 */         resultSet2 = preparedStatement.executeQuery();
/*  62 */         List<RightsDetail> rightList = new ArrayList<>();
/*  63 */         while (resultSet2.next()) {
/*  64 */           rightList.add(new RightsDetail(resultSet2.getInt("Right_ID"), resultSet2.getString("Right_Display_Name"), resultSet2.getBoolean("RightAdd"), resultSet2.getBoolean("RightEdit"), resultSet2.getBoolean("RightDelete"), resultSet2.getBoolean("RightView"), !resultSet2.getBoolean("Allow_View") ? false : resultSet2.getBoolean("Allow_Add"), !resultSet2.getBoolean("Allow_View") ? false : resultSet2.getBoolean("Allow_Edit"), !resultSet2.getBoolean("Allow_View") ? false : resultSet2.getBoolean("Allow_Delete"), resultSet2.getBoolean("Allow_View"), 200));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*  82 */         objGroup.rights = rightList;
/*  83 */         groups.add(objGroup);
/*  84 */         counter++;
/*     */       } 
/*  86 */       return groups;
/*  87 */     } catch (Exception ex) {
/*  88 */       ex.printStackTrace();
/*  89 */       return null;
/*     */     } finally {
/*  91 */       DBOperations.DisposeSql(resultSet2);
/*  92 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ErevenueResponse UpdateUserGroup(UserGroup group) {
/* 101 */     Connection connection = null;
/* 102 */     PreparedStatement preparedStatement = null;
/* 103 */     ResultSet resultSet = null;
/* 104 */     ResultSet rs = null;
/* 105 */     int groupId = 0;
/*     */     try {
/* 107 */       connection = this.dataSource.getConnection();
/* 108 */       connection.setAutoCommit(false);
/* 109 */       if (!group.active && 
/* 110 */         CheckUserAssignGroup(group.groupId)) {
/* 111 */         return new ErevenueResponse(201, "Group Already Assign to user,cannot deactivate");
/*     */       }
/*     */       
/* 114 */       if (group.groupId == 0) {
/*     */         
/* 116 */         if (checkGroupByName(group.groupName)) {
/* 117 */           return new ErevenueResponse(201, "Group Name is Already Exists");
/*     */         }
/* 119 */         preparedStatement = connection.prepareStatement(Queryconstants.insertUserGroup, 1);
/*     */ 
/*     */         
/* 122 */         preparedStatement.setString(1, group.groupName);
/* 123 */         preparedStatement.setBoolean(2, group.active);
/* 124 */         preparedStatement.setInt(3, group.createdBy);
/* 125 */         preparedStatement.setTimestamp(4, new Timestamp((new Date()).getTime()));
/*     */         
/* 127 */         preparedStatement.setInt(5, group.roleTypeId);
/*     */       } else {
/*     */         
/* 130 */         preparedStatement = connection.prepareStatement(Queryconstants.updateUserGroup);
/*     */         
/* 132 */         preparedStatement.setString(1, group.groupName);
/* 133 */         preparedStatement.setBoolean(2, group.active);
/* 134 */         preparedStatement.setInt(3, group.createdBy);
/* 135 */         preparedStatement.setTimestamp(4, new Timestamp((new Date()).getTime()));
/*     */         
/* 137 */         preparedStatement.setInt(5, group.roleTypeId);
/* 138 */         preparedStatement.setInt(6, group.groupId);
/* 139 */         groupId = group.groupId;
/*     */       } 
/*     */       
/* 142 */       if (preparedStatement.executeUpdate() > 0) {
/*     */ 
/*     */         
/* 145 */         if (group.groupId == 0) {
/* 146 */           rs = preparedStatement.getGeneratedKeys();
/* 147 */           rs.next();
/* 148 */           groupId = rs.getInt(1);
/*     */         } 
/* 150 */         DBOperations.DisposeSql(preparedStatement, rs);
/* 151 */         preparedStatement = connection.prepareStatement(Queryconstants.deleteGroupRights);
/*     */         
/* 153 */         preparedStatement.setInt(1, groupId);
/* 154 */         preparedStatement.executeUpdate();
/*     */         
/* 156 */         DBOperations.DisposeSql(preparedStatement);
/*     */         
/* 158 */         preparedStatement = connection.prepareStatement(Queryconstants.insertGroupRights);
/*     */         
/* 160 */         for (int i = 0; i < group.rights.size(); i++) {
/* 161 */           preparedStatement.setInt(1, ((RightsDetail)group.rights.get(i)).rightId);
/* 162 */           preparedStatement.setInt(2, groupId);
/* 163 */           preparedStatement.setBoolean(3, ((RightsDetail)group.rights.get(i)).rightView);
/*     */           
/* 165 */           preparedStatement.setBoolean(4, ((RightsDetail)group.rights.get(i)).rightAdd);
/*     */           
/* 167 */           preparedStatement.setBoolean(5, ((RightsDetail)group.rights.get(i)).rightEdit);
/*     */           
/* 169 */           preparedStatement.setBoolean(6, ((RightsDetail)group.rights.get(i)).rightDelete);
/*     */           
/* 171 */           preparedStatement.setInt(7, ((RightsDetail)group.rights.get(i)).createdBy);
/* 172 */           preparedStatement.setTimestamp(8, new Timestamp((new Date()).getTime()));
/*     */           
/* 174 */           if (preparedStatement.executeUpdate() <= 0) {
/* 175 */             throw new Exception("Failed to Insert Right Id " + ((RightsDetail)group.rights.get(i)).rightId);
/*     */           }
/*     */         } 
/*     */         
/* 179 */         connection.commit();
/* 180 */         return new ErevenueResponse(200, "Records Updated");
/*     */       } 
/*     */       
/* 183 */       connection.rollback();
/* 184 */       return new ErevenueResponse(201, "Nothing To Update");
/*     */     
/*     */     }
/* 187 */     catch (SQLException sqlEx) {
/* 188 */       sqlEx.printStackTrace();
/* 189 */       if (sqlEx.getMessage().indexOf("Cannot insert duplicate key") != 0) {
/* 190 */         return new ErevenueResponse(201, "Group Name is Already Exists");
/*     */       }
/* 192 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     }
/* 194 */     catch (Exception ex) {
/* 195 */       ex.printStackTrace();
/*     */       try {
/* 197 */         connection.rollback();
/* 198 */       } catch (SQLException e) {
/* 199 */         e.printStackTrace();
/*     */       } 
/* 201 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     } finally {
/* 203 */       DBOperations.DisposeSql(rs);
/* 204 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkGroupByName(String groupName) {
/* 214 */     Connection connection = null;
/* 215 */     PreparedStatement preparedStatement = null;
/* 216 */     ResultSet resultSet = null;
/*     */     try {
/* 218 */       connection = this.dataSource.getConnection();
/* 219 */       preparedStatement = connection.prepareStatement(Queryconstants.getGroupByName);
/*     */       
/* 221 */       preparedStatement.setString(1, groupName);
/* 222 */       resultSet = preparedStatement.executeQuery();
/* 223 */       if (resultSet.next()) {
/* 224 */         return true;
/*     */       }
/* 226 */       return false;
/*     */     }
/* 228 */     catch (Exception ex) {
/* 229 */       ex.printStackTrace();
/* 230 */       return false;
/*     */     } finally {
/* 232 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean CheckUserAssignGroup(int groupId) {
/* 242 */     Connection connection = null;
/* 243 */     PreparedStatement preparedStatement = null;
/* 244 */     ResultSet resultSet = null;
/*     */     try {
/* 246 */       connection = this.dataSource.getConnection();
/* 247 */       preparedStatement = connection.prepareStatement(Queryconstants.checkUserAssignGroup);
/*     */       
/* 249 */       preparedStatement.setInt(1, groupId);
/* 250 */       resultSet = preparedStatement.executeQuery();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<RightsDetail> GetRights() {
/* 269 */     Connection connection = null;
/* 270 */     PreparedStatement preparedStatement = null;
/* 271 */     ResultSet resultSet = null;
/*     */     try {
/* 273 */       connection = this.dataSource.getConnection();
/* 274 */       preparedStatement = connection.prepareStatement(Queryconstants.getRights);
/*     */ 
/*     */       
/* 277 */       resultSet = preparedStatement.executeQuery();
/* 278 */       List<RightsDetail> rights = new ArrayList<>();
/* 279 */       while (resultSet.next()) {
/* 280 */         rights.add(new RightsDetail(resultSet.getInt("ID"), resultSet.getString("Right_Display_Name"), resultSet.getBoolean("Allow_View"), !resultSet.getBoolean("Allow_View") ? false : resultSet.getBoolean("Allow_Add"), !resultSet.getBoolean("Allow_View") ? false : resultSet.getBoolean("Allow_Edit"), !resultSet.getBoolean("Allow_View") ? false : resultSet.getBoolean("Allow_Delete"), 200));
/*     */       }
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
/* 292 */       return rights;
/* 293 */     } catch (Exception ex) {
/* 294 */       ex.printStackTrace();
/* 295 */       return null;
/*     */     } finally {
/* 297 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\impl\UserGroupDalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */