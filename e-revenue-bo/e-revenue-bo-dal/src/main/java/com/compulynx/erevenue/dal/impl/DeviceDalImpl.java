/*     */ package com.compulynx.erevenue.dal.impl;
/*     */ 
/*     */ import com.compulynx.erevenue.dal.DeviceDal;
/*     */ import com.compulynx.erevenue.dal.operations.DBOperations;
/*     */ import com.compulynx.erevenue.dal.operations.Queryconstants;
/*     */ import com.compulynx.erevenue.models.Device;
/*     */ import com.compulynx.erevenue.models.ErevenueResponse;
/*     */ import com.compulynx.erevenue.models.User;
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
/*     */ public class DeviceDalImpl
/*     */   implements DeviceDal
/*     */ {
/*     */   private DataSource dataSource;
/*     */   
/*     */   public DeviceDalImpl(DataSource dataSource) {
/*  30 */     this.dataSource = dataSource;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkDevicedSerialNo(String serialNo) {
/*  39 */     Connection connection = null;
/*  40 */     PreparedStatement preparedStatement = null;
/*  41 */     ResultSet resultSet = null;
/*     */     try {
/*  43 */       connection = this.dataSource.getConnection();
/*  44 */       preparedStatement = connection.prepareStatement(Queryconstants.getDeviceSerialNo);
/*     */       
/*  46 */       preparedStatement.setString(1, serialNo);
/*     */       
/*  48 */       resultSet = preparedStatement.executeQuery();
/*     */       
/*  50 */       if (resultSet.next()) {
/*  51 */         return true;
/*     */       }
/*  53 */       return false;
/*     */     }
/*  55 */     catch (Exception ex) {
/*  56 */       ex.printStackTrace();
/*  57 */       return false;
/*     */     } finally {
/*  59 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean checkDevicedSerialNoById(String serialNo, int id) {
/*  70 */     Connection connection = null;
/*  71 */     PreparedStatement preparedStatement = null;
/*  72 */     ResultSet resultSet = null;
/*     */     try {
/*  74 */       connection = this.dataSource.getConnection();
/*  75 */       preparedStatement = connection.prepareStatement(Queryconstants.getDeviceSerialNoById);
/*     */       
/*  77 */       preparedStatement.setString(1, serialNo);
/*  78 */       preparedStatement.setInt(2, id);
/*  79 */       resultSet = preparedStatement.executeQuery();
/*     */       
/*  81 */       if (resultSet.next()) {
/*  82 */         return true;
/*     */       }
/*  84 */       return false;
/*     */     }
/*  86 */     catch (Exception ex) {
/*  87 */       ex.printStackTrace();
/*  88 */       return false;
/*     */     } finally {
/*  90 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
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
/*     */   public boolean checkUserAssignDevice(int deviceId) {
/* 161 */     Connection connection = null;
/* 162 */     PreparedStatement preparedStatement = null;
/* 163 */     ResultSet resultSet = null;
/*     */     try {
/* 165 */       connection = this.dataSource.getConnection();
/* 166 */       preparedStatement = connection.prepareStatement(Queryconstants.checkUserAssignDevice);
/*     */       
/* 168 */       preparedStatement.setInt(1, deviceId);
/*     */       
/* 170 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 172 */       if (resultSet.next()) {
/* 173 */         return true;
/*     */       }
/* 175 */       return false;
/*     */     }
/* 177 */     catch (Exception ex) {
/* 178 */       ex.printStackTrace();
/* 179 */       return false;
/*     */     } finally {
/* 181 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ErevenueResponse UpdateDeviceInfo(Device deviceInfo) {
/* 190 */     Connection connection = null;
/* 191 */     PreparedStatement preparedStatement = null;
/* 192 */     ResultSet resultSet = null;
/*     */     try {
/* 194 */       connection = this.dataSource.getConnection();
/* 195 */       if (!deviceInfo.status && 
/* 196 */         checkUserAssignDevice(deviceInfo.id)) {
/* 197 */         return new ErevenueResponse(201, "Device Already Assign to user cannot deactivate");
/*     */       }
/*     */       
/* 200 */       if (deviceInfo.id == 0) {
/* 201 */         if (checkDevicedSerialNo(deviceInfo.serialNo)) {
/* 202 */           return new ErevenueResponse(201, "SerialNo Already Exists");
/*     */         }
/*     */         
/* 205 */         preparedStatement = connection.prepareStatement(Queryconstants.insertDeviceInfo);
/*     */         
/* 207 */         preparedStatement.setString(1, deviceInfo.serialNo);
/* 208 */         preparedStatement.setBoolean(2, deviceInfo.status);
/* 209 */         preparedStatement.setInt(3, deviceInfo.createdBy);
/* 210 */         preparedStatement.setTimestamp(4, new Timestamp((new Date()).getTime()));
/*     */       } else {
/*     */         
/* 213 */         if (checkDevicedSerialNoById(deviceInfo.serialNo, deviceInfo.id)) {
/* 214 */           return new ErevenueResponse(201, "SerialNo Already Exists");
/*     */         }
/*     */         
/* 217 */         preparedStatement = connection.prepareStatement(Queryconstants.updateDeviceInfo);
/*     */         
/* 219 */         preparedStatement.setString(1, deviceInfo.serialNo);
/* 220 */         preparedStatement.setBoolean(2, deviceInfo.status);
/* 221 */         preparedStatement.setInt(3, deviceInfo.createdBy);
/* 222 */         preparedStatement.setTimestamp(4, new Timestamp((new Date()).getTime()));
/*     */         
/* 224 */         preparedStatement.setInt(5, deviceInfo.id);
/*     */       } 
/* 226 */       return (preparedStatement.executeUpdate() > 0) ? new ErevenueResponse(200, "Records Updated") : new ErevenueResponse(201, "Nothing To Update");
/*     */     
/*     */     }
/* 229 */     catch (SQLException sqlEx) {
/* 230 */       sqlEx.printStackTrace();
/* 231 */       if (sqlEx.getMessage().indexOf("Cannot insert duplicate key") != 0) {
/* 232 */         return new ErevenueResponse(201, "Serial No Already Exists");
/*     */       }
/* 234 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     }
/* 236 */     catch (Exception ex) {
/* 237 */       ex.printStackTrace();
/* 238 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     } finally {
/* 240 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Device> GetAllDevicesInfo() {
/* 249 */     Connection connection = null;
/* 250 */     PreparedStatement preparedStatement = null;
/* 251 */     ResultSet resultSet = null;
/* 252 */     int count = 1;
/*     */     try {
/* 254 */       connection = this.dataSource.getConnection();
/* 255 */       preparedStatement = connection.prepareStatement(Queryconstants.getDevices);
/*     */ 
/*     */       
/* 258 */       resultSet = preparedStatement.executeQuery();
/* 259 */       List<Device> devices = new ArrayList<>();
/* 260 */       while (resultSet.next()) {
/* 261 */         devices.add(new Device(resultSet.getInt("id"), resultSet.getString("serial_no"), resultSet.getBoolean("status"), resultSet.getInt("created_by"), count, 200));
/*     */ 
/*     */ 
/*     */         
/* 265 */         count++;
/*     */       } 
/*     */       
/* 268 */       return devices;
/* 269 */     } catch (Exception ex) {
/* 270 */       ex.printStackTrace();
/* 271 */       return null;
/*     */     } finally {
/* 273 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Device> GetActiveDevicesInfo() {
/* 282 */     Connection connection = null;
/* 283 */     PreparedStatement preparedStatement = null;
/* 284 */     ResultSet resultSet = null;
/* 285 */     int count = 1;
/*     */     try {
/* 287 */       connection = this.dataSource.getConnection();
/* 288 */       preparedStatement = connection.prepareStatement(Queryconstants.getActiveDevices);
/*     */ 
/*     */       
/* 291 */       resultSet = preparedStatement.executeQuery();
/* 292 */       List<Device> devices = new ArrayList<>();
/* 293 */       while (resultSet.next()) {
/* 294 */         devices.add(new Device(resultSet.getInt("id"), resultSet.getString("serial_no"), resultSet.getBoolean("status"), resultSet.getInt("created_by"), count, 200));
/*     */ 
/*     */ 
/*     */         
/* 298 */         count++;
/*     */       } 
/* 300 */       return devices;
/* 301 */     } catch (Exception ex) {
/* 302 */       ex.printStackTrace();
/* 303 */       return null;
/*     */     } finally {
/* 305 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ErevenueResponse UpdateIssueDeviceInfo(Device deviceInfo) {
/* 315 */     Connection connection = null;
/* 316 */     PreparedStatement preparedStatement = null;
/* 317 */     ResultSet resultSet = null;
/*     */     try {
/* 319 */       connection = this.dataSource.getConnection();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 329 */       if (deviceInfo.issueId == 0) {
/*     */         
/* 331 */         if (ValidateUserId(deviceInfo.userId)) {
/* 332 */           return new ErevenueResponse(201, "User already assigned to another device");
/*     */         }
/*     */         
/* 335 */         if (ValidateDeviceId(deviceInfo.devId)) {
/* 336 */           return new ErevenueResponse(201, "Device already assigned to another user");
/*     */         }
/*     */ 
/*     */         
/* 340 */         if (CheckUserIdReturnedState(deviceInfo.issuedTo)) {
/* 341 */           preparedStatement = connection.prepareStatement(Queryconstants.updateReturnedUser);
/*     */           
/* 343 */           preparedStatement.setInt(1, deviceInfo.devId);
/* 344 */           preparedStatement.setBoolean(2, deviceInfo.status);
/* 345 */           preparedStatement.setInt(3, deviceInfo.createdBy);
/* 346 */           preparedStatement.setTimestamp(4, new Timestamp((new Date()).getTime()));
/*     */           
/* 348 */           preparedStatement.setInt(5, deviceInfo.issuedTo);
/*     */         
/*     */         }
/* 351 */         else if (CheckDeviceIdReturnedState(deviceInfo.devId)) {
/* 352 */           preparedStatement = connection.prepareStatement(Queryconstants.updateReturnedDevice);
/*     */           
/* 354 */           preparedStatement.setInt(1, deviceInfo.issuedTo);
/* 355 */           preparedStatement.setBoolean(2, deviceInfo.status);
/* 356 */           preparedStatement.setInt(3, deviceInfo.createdBy);
/* 357 */           preparedStatement.setTimestamp(4, new Timestamp((new Date()).getTime()));
/*     */           
/* 359 */           preparedStatement.setInt(5, deviceInfo.devId);
/*     */         }
/*     */         else {
/*     */           
/* 363 */           preparedStatement = connection.prepareStatement(Queryconstants.insertIssueDeviceInfo);
/*     */           
/* 365 */           preparedStatement.setInt(1, deviceInfo.devId);
/* 366 */           preparedStatement.setInt(2, deviceInfo.issuedTo);
/* 367 */           preparedStatement.setBoolean(3, deviceInfo.status);
/* 368 */           preparedStatement.setInt(4, deviceInfo.createdBy);
/* 369 */           preparedStatement.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */         
/*     */         }
/*     */ 
/*     */       
/*     */       }
/*     */       else {
/*     */ 
/*     */         
/* 378 */         preparedStatement = connection.prepareStatement(Queryconstants.updateIssueDeviceInfo);
/*     */         
/* 380 */         preparedStatement.setInt(1, deviceInfo.issuedTo);
/* 381 */         preparedStatement.setBoolean(2, deviceInfo.status);
/* 382 */         preparedStatement.setInt(3, deviceInfo.createdBy);
/* 383 */         preparedStatement.setTimestamp(4, new Timestamp((new Date()).getTime()));
/*     */         
/* 385 */         preparedStatement.setInt(5, deviceInfo.issueId);
/*     */       } 
/* 387 */       return (preparedStatement.executeUpdate() > 0) ? new ErevenueResponse(200, "Records Updated") : new ErevenueResponse(201, "Nothing To Update");
/*     */     
/*     */     }
/* 390 */     catch (SQLException sqlEx) {
/* 391 */       sqlEx.printStackTrace();
/* 392 */       if (sqlEx.getMessage().indexOf("Cannot insert duplicate key") != 0) {
/* 393 */         return new ErevenueResponse(201, "Serial No Already Exists");
/*     */       }
/* 395 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     }
/* 397 */     catch (Exception ex) {
/* 398 */       ex.printStackTrace();
/* 399 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     } finally {
/* 401 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean CheckUserIdReturnedState(int userId) {
/* 407 */     Connection connection = null;
/* 408 */     PreparedStatement preparedStatement = null;
/* 409 */     ResultSet resultSet = null;
/*     */     try {
/* 411 */       connection = this.dataSource.getConnection();
/* 412 */       preparedStatement = connection.prepareStatement(Queryconstants.checkUserReturnedStatus);
/*     */       
/* 414 */       preparedStatement.setInt(1, userId);
/*     */       
/* 416 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 418 */       if (resultSet.next()) {
/* 419 */         return true;
/*     */       }
/* 421 */       return false;
/*     */     }
/* 423 */     catch (Exception ex) {
/* 424 */       ex.printStackTrace();
/* 425 */       return false;
/*     */     } finally {
/* 427 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */   public boolean CheckDeviceIdReturnedState(int devId) {
/* 431 */     Connection connection = null;
/* 432 */     PreparedStatement preparedStatement = null;
/* 433 */     ResultSet resultSet = null;
/*     */     try {
/* 435 */       connection = this.dataSource.getConnection();
/* 436 */       preparedStatement = connection.prepareStatement(Queryconstants.checkDeviceReturnedStatus);
/*     */       
/* 438 */       preparedStatement.setInt(1, devId);
/*     */       
/* 440 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 442 */       if (resultSet.next()) {
/* 443 */         return true;
/*     */       }
/* 445 */       return false;
/*     */     }
/* 447 */     catch (Exception ex) {
/* 448 */       ex.printStackTrace();
/* 449 */       return false;
/*     */     } finally {
/* 451 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ValidateDeviceId(int devId) {
/* 462 */     Connection connection = null;
/* 463 */     PreparedStatement preparedStatement = null;
/* 464 */     ResultSet resultSet = null;
/*     */     try {
/* 466 */       connection = this.dataSource.getConnection();
/* 467 */       preparedStatement = connection.prepareStatement(Queryconstants.validateDevId);
/*     */       
/* 469 */       preparedStatement.setInt(1, devId);
/* 470 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 472 */       if (resultSet.next()) {
/* 473 */         return true;
/*     */       }
/* 475 */       return false;
/*     */     }
/* 477 */     catch (Exception ex) {
/* 478 */       ex.printStackTrace();
/* 479 */       return false;
/*     */     } finally {
/* 481 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean ValidateUserId(int userId) {
/* 491 */     Connection connection = null;
/* 492 */     PreparedStatement preparedStatement = null;
/* 493 */     ResultSet resultSet = null;
/*     */     try {
/* 495 */       connection = this.dataSource.getConnection();
/* 496 */       preparedStatement = connection.prepareStatement(Queryconstants.validateUserId);
/*     */       
/* 498 */       preparedStatement.setInt(1, userId);
/*     */       
/* 500 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 502 */       if (resultSet.next()) {
/* 503 */         return true;
/*     */       }
/* 505 */       return false;
/*     */     }
/* 507 */     catch (Exception ex) {
/* 508 */       ex.printStackTrace();
/* 509 */       return false;
/*     */     } finally {
/* 511 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Device> GetAllIssueDevicesInfo() {
/* 520 */     Connection connection = null;
/* 521 */     PreparedStatement preparedStatement = null;
/* 522 */     ResultSet resultSet = null;
/* 523 */     int count = 1;
/*     */     try {
/* 525 */       connection = this.dataSource.getConnection();
/* 526 */       preparedStatement = connection.prepareStatement(Queryconstants.getIssueDevices);
/*     */ 
/*     */       
/* 529 */       resultSet = preparedStatement.executeQuery();
/* 530 */       List<Device> issueDevices = new ArrayList<>();
/* 531 */       while (resultSet.next()) {
/* 532 */         issueDevices.add(new Device(resultSet.getInt("id"), resultSet.getInt("dev_id"), resultSet.getString("serial_no"), resultSet.getInt("issued_to"), resultSet.getString("UserName"), resultSet.getInt("created_by"), resultSet.getBoolean("status"), 200, count, resultSet.getString("name"), resultSet.getString("mkt_name")));
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
/* 543 */         count++;
/*     */       } 
/* 545 */       return issueDevices;
/* 546 */     } catch (Exception ex) {
/* 547 */       ex.printStackTrace();
/* 548 */       return null;
/*     */     } finally {
/* 550 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<User> GetActiveUsers() {
/* 559 */     Connection connection = null;
/* 560 */     PreparedStatement preparedStatement = null;
/* 561 */     ResultSet resultSet = null;
/* 562 */     ResultSet resultSet2 = null;
/*     */     try {
/* 564 */       connection = this.dataSource.getConnection();
/* 565 */       preparedStatement = connection.prepareStatement(Queryconstants.getActiveUsers);
/*     */ 
/*     */       
/* 568 */       resultSet = preparedStatement.executeQuery();
/* 569 */       List<User> users = new ArrayList<>();
/* 570 */       while (resultSet.next()) {
/* 571 */         User objUser = new User();
/* 572 */         objUser.userId = resultSet.getInt("ID");
/* 573 */         objUser.userName = resultSet.getString("UserName");
/* 574 */         objUser.userFullName = resultSet.getString("name");
/*     */         
/* 576 */         users.add(objUser);
/*     */       } 
/* 578 */       return users;
/* 579 */     } catch (Exception ex) {
/* 580 */       ex.printStackTrace();
/* 581 */       return null;
/*     */     } finally {
/* 583 */       DBOperations.DisposeSql(resultSet2);
/* 584 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\impl\DeviceDalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */