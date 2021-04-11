/*     */ package com.compulynx.erevenue.dal.impl;
/*     */ 
/*     */ import com.compulynx.erevenue.dal.ServiceDal;
/*     */ import com.compulynx.erevenue.dal.operations.DBOperations;
/*     */ import com.compulynx.erevenue.dal.operations.Queryconstants;
/*     */ import com.compulynx.erevenue.models.ErevenueResponse;
/*     */ import com.compulynx.erevenue.models.Market;
/*     */ import com.compulynx.erevenue.models.Params;
/*     */ import com.compulynx.erevenue.models.Service;
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
/*     */ public class ServiceDalImpl
/*     */   implements ServiceDal
/*     */ {
/*     */   private DataSource dataSource;
/*     */   
/*     */   public ServiceDalImpl(DataSource dataSource) {
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
/*     */   public boolean checkServiceCode(String serviceCode) {
/*  44 */     Connection connection = null;
/*  45 */     PreparedStatement preparedStatement = null;
/*  46 */     ResultSet resultSet = null;
/*     */     try {
/*  48 */       connection = this.dataSource.getConnection();
/*  49 */       preparedStatement = connection.prepareStatement(Queryconstants.getServiceByName);
/*     */       
/*  51 */       preparedStatement.setString(1, serviceCode);
/*     */       
/*  53 */       resultSet = preparedStatement.executeQuery();
/*     */       
/*  55 */       if (resultSet.next()) {
/*  56 */         return true;
/*     */       }
/*  58 */       return false;
/*     */     }
/*  60 */     catch (Exception ex) {
/*  61 */       ex.printStackTrace();
/*  62 */       return false;
/*     */     } finally {
/*  64 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ErevenueResponse UpdateService(Service service) {
/*  75 */     Connection connection = null;
/*  76 */     PreparedStatement preparedStatement = null;
/*  77 */     PreparedStatement preparedStatement1 = null;
/*  78 */     ResultSet resultSet = null;
/*  79 */     ResultSet resultSet1 = null;
/*  80 */     ResultSet rs = null;
/*  81 */     int parentServiceId = 0;
/*  82 */     int subServiceId = 0;
/*     */     try {
/*  84 */       connection = this.dataSource.getConnection();
/*  85 */       if (service.parentServiceId > 0) {
/*  86 */         if (service.isActive == true && service.level == -1 && (service.serviceName == "" || service.serviceName == null)) {
/*     */ 
/*     */           
/*  89 */           preparedStatement = connection.prepareStatement(Queryconstants.updateSubServiceDetails);
/*     */           
/*  91 */           preparedStatement.setBoolean(1, service.active);
/*  92 */           preparedStatement.setInt(2, service.createdBy);
/*  93 */           preparedStatement.setTimestamp(3, new Timestamp((new Date()).getTime()));
/*     */           
/*  95 */           preparedStatement.setInt(4, service.level);
/*  96 */           preparedStatement.setBoolean(5, service.hasChild);
/*  97 */           preparedStatement.setInt(6, service.parentServiceId);
/*  98 */           subServiceId = service.parentServiceId;
/*     */         } else {
/* 100 */           DBOperations.DisposeSql(preparedStatement);
/* 101 */           preparedStatement = connection.prepareStatement(Queryconstants.insertSubServiceDetails, 1);
/*     */ 
/*     */           
/* 104 */           preparedStatement.setInt(1, service.parentServiceId);
/* 105 */           preparedStatement.setString(2, service.serviceName);
/* 106 */           preparedStatement.setBoolean(3, service.hasChild);
/* 107 */           preparedStatement.setInt(4, service.level);
/* 108 */           preparedStatement.setBoolean(5, service.active);
/* 109 */           preparedStatement.setInt(6, service.createdBy);
/* 110 */           preparedStatement.setTimestamp(7, new Timestamp((new Date()).getTime()));
/*     */           
/* 112 */           preparedStatement.setBoolean(8, service.parentType);
/*     */         } 
/* 114 */         if (preparedStatement.executeUpdate() > 0) {
/* 115 */           if (service.isActive == true) {
/* 116 */             if (subServiceId == 0) {
/* 117 */               rs = preparedStatement.getGeneratedKeys();
/* 118 */               rs.next();
/* 119 */               subServiceId = rs.getInt(1);
/*     */             } 
/* 121 */             DBOperations.DisposeSql(preparedStatement);
/* 122 */             preparedStatement = connection.prepareStatement(Queryconstants.deleteParams);
/*     */             
/* 124 */             preparedStatement.setInt(1, subServiceId);
/* 125 */             preparedStatement.executeUpdate();
/* 126 */             for (int i = 0; i < service.params.size(); i++) {
/*     */               
/* 128 */               DBOperations.DisposeSql(preparedStatement);
/* 129 */               preparedStatement = connection.prepareStatement(Queryconstants.insertParams);
/*     */               
/* 131 */               preparedStatement.setInt(1, ((Params)service.params.get(i)).paramId);
/*     */               
/* 133 */               preparedStatement.setInt(2, subServiceId);
/* 134 */               preparedStatement.setBoolean(3, ((Params)service.params.get(i)).isActive);
/*     */               
/* 136 */               preparedStatement.setInt(4, service.createdBy);
/* 137 */               preparedStatement.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */ 
/*     */ 
/*     */               
/* 141 */               if (preparedStatement.executeUpdate() <= 0) {
/* 142 */                 throw new Exception("Failed to Insert Param Id " + ((Params)service.params.get(i)).paramId);
/*     */               }
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 148 */           if (service.subCountyList != null && 
/* 149 */             service.subCountyList.size() > 0) {
/* 150 */             ArrayList<Market> marketList = new ArrayList<>(); int i;
/* 151 */             for (i = 0; i < service.subCountyList.size(); i++) {
/* 152 */               if (((SubCounty)service.subCountyList.get(i)).serviceValue > 0.0D || ((SubCounty)service.subCountyList.get(i)).serviceValue == -1.0D) {
/*     */                 
/* 154 */                 preparedStatement1 = connection.prepareStatement(Queryconstants.getMarketsForSubcounty);
/*     */                 
/* 156 */                 preparedStatement1.setInt(1, ((SubCounty)service.subCountyList.get(i)).subCountyId);
/*     */ 
/*     */                 
/* 159 */                 resultSet1 = preparedStatement1.executeQuery();
/*     */ 
/*     */                 
/* 162 */                 while (resultSet1.next()) {
/* 163 */                   Market objMarket = new Market();
/* 164 */                   objMarket.marketId = resultSet1.getInt("id");
/*     */                   
/* 166 */                   objMarket.serviceValue = ((SubCounty)service.subCountyList.get(i)).serviceValue;
/*     */                   
/* 168 */                   marketList.add(objMarket);
/*     */                 } 
/*     */               } 
/*     */             } 
/* 172 */             for (i = 0; i < marketList.size(); i++) {
/* 173 */               preparedStatement1 = connection.prepareStatement(Queryconstants.insertMatketPriceDtl);
/*     */               
/* 175 */               preparedStatement1.setInt(1, ((Market)marketList.get(i)).marketId);
/*     */               
/* 177 */               preparedStatement1.setInt(2, subServiceId);
/* 178 */               preparedStatement1.setDouble(3, ((Market)marketList.get(i)).serviceValue);
/*     */               
/* 180 */               preparedStatement1.setInt(4, service.createdBy);
/* 181 */               preparedStatement1.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 187 */               if (preparedStatement1.executeUpdate() <= 0) {
/* 188 */                 throw new Exception("Failed to Insert Market Id " + ((Market)marketList.get(i)).marketId);
/*     */               }
/*     */             } 
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 195 */           if (service.wardList != null && 
/* 196 */             service.wardList.size() > 0) {
/* 197 */             ArrayList<Market> marketList = new ArrayList<>(); int i;
/* 198 */             for (i = 0; i < service.wardList.size(); i++) {
/* 199 */               if (((Ward)service.wardList.get(i)).serviceValue > 0.0D || ((Ward)service.wardList.get(i)).serviceValue == -1.0D) {
/*     */                 
/* 201 */                 preparedStatement1 = connection.prepareStatement(Queryconstants.getMarketsForWards);
/*     */                 
/* 203 */                 preparedStatement1.setInt(1, ((Ward)service.wardList.get(i)).wardId);
/*     */                 
/* 205 */                 resultSet1 = preparedStatement1.executeQuery();
/*     */ 
/*     */                 
/* 208 */                 while (resultSet1.next()) {
/* 209 */                   Market objMarket = new Market();
/* 210 */                   objMarket.marketId = resultSet1.getInt("id");
/*     */                   
/* 212 */                   objMarket.serviceValue = ((Ward)service.wardList.get(i)).serviceValue;
/*     */                   
/* 214 */                   marketList.add(objMarket);
/*     */                 } 
/*     */               } 
/*     */             } 
/* 218 */             for (i = 0; i < marketList.size(); i++) {
/* 219 */               preparedStatement1 = connection.prepareStatement(Queryconstants.insertMatketPriceDtl);
/*     */               
/* 221 */               preparedStatement1.setInt(1, ((Market)marketList.get(i)).marketId);
/*     */               
/* 223 */               preparedStatement1.setInt(2, subServiceId);
/* 224 */               preparedStatement1.setDouble(3, ((Market)marketList.get(i)).serviceValue);
/*     */               
/* 226 */               preparedStatement1.setInt(4, service.createdBy);
/* 227 */               preparedStatement1.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 233 */               if (preparedStatement1.executeUpdate() <= 0) {
/* 234 */                 throw new Exception("Failed to Insert Market Id " + ((Market)marketList.get(i)).marketId);
/*     */               }
/*     */             } 
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/* 241 */           if (service.marketList != null && 
/* 242 */             service.marketList.size() > 0) {
/* 243 */             for (int i = 0; i < service.marketList.size(); i++) {
/* 244 */               if (((Market)service.marketList.get(i)).serviceValue > 0.0D || ((Market)service.marketList.get(i)).serviceValue == -1.0D) {
/*     */                 
/* 246 */                 preparedStatement1 = connection.prepareStatement(Queryconstants.insertMatketPriceDtl);
/*     */                 
/* 248 */                 preparedStatement1.setInt(1, ((Market)service.marketList.get(i)).marketId);
/*     */                 
/* 250 */                 preparedStatement1.setInt(2, subServiceId);
/* 251 */                 preparedStatement1.setDouble(3, ((Market)service.marketList.get(i)).serviceValue);
/*     */ 
/*     */ 
/*     */                 
/* 255 */                 preparedStatement1.setInt(4, service.createdBy);
/*     */                 
/* 257 */                 preparedStatement1.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 262 */                 if (preparedStatement1.executeUpdate() <= 0) {
/* 263 */                   throw new Exception("Failed to Insert Market Id " + ((Params)service.params.get(i)).paramId);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           }
/*     */ 
/*     */ 
/*     */           
/* 271 */           return new ErevenueResponse(200, "Records Updated");
/*     */         } 
/* 273 */         return new ErevenueResponse(201, "Failed to Updated");
/*     */       } 
/*     */ 
/*     */       
/* 277 */       if (service.serviceId == 0) {
/* 278 */         if (checkServiceCode(service.serviceCode)) {
/* 279 */           return new ErevenueResponse(201, "Service Code Already Exists");
/*     */         }
/*     */         
/* 282 */         preparedStatement = connection.prepareStatement(Queryconstants.insertServiceDetails, 1);
/*     */ 
/*     */         
/* 285 */         preparedStatement.setString(1, service.serviceCode);
/* 286 */         preparedStatement.setString(2, service.serviceName);
/* 287 */         preparedStatement.setBoolean(3, service.active);
/* 288 */         preparedStatement.setInt(4, service.createdBy);
/* 289 */         preparedStatement.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */         
/* 291 */         if (preparedStatement.executeUpdate() > 0) {
/* 292 */           if (service.serviceId == 0) {
/* 293 */             rs = preparedStatement.getGeneratedKeys();
/* 294 */             rs.next();
/* 295 */             parentServiceId = rs.getInt(1);
/*     */           } 
/* 297 */           if (service.isActive == true) {
/* 298 */             DBOperations.DisposeSql(preparedStatement, rs);
/* 299 */             preparedStatement = connection.prepareStatement(Queryconstants.insertSubServiceDetails, 1);
/*     */ 
/*     */             
/* 302 */             preparedStatement.setInt(1, parentServiceId);
/* 303 */             preparedStatement.setString(2, "Parent");
/* 304 */             preparedStatement.setBoolean(3, service.hasChild);
/* 305 */             preparedStatement.setInt(4, service.level);
/* 306 */             preparedStatement.setBoolean(5, service.active);
/* 307 */             preparedStatement.setInt(6, service.createdBy);
/* 308 */             preparedStatement.setTimestamp(7, new Timestamp((new Date()).getTime()));
/*     */ 
/*     */ 
/*     */             
/* 312 */             preparedStatement.setBoolean(8, service.parentType);
/* 313 */             if (preparedStatement.executeUpdate() > 0) {
/* 314 */               if (subServiceId == 0) {
/* 315 */                 rs = preparedStatement.getGeneratedKeys();
/* 316 */                 rs.next();
/* 317 */                 subServiceId = rs.getInt(1);
/*     */               } 
/* 319 */               DBOperations.DisposeSql(preparedStatement);
/* 320 */               preparedStatement = connection.prepareStatement(Queryconstants.deleteParams);
/*     */               
/* 322 */               preparedStatement.setInt(1, subServiceId);
/* 323 */               preparedStatement.executeUpdate();
/* 324 */               for (int i = 0; i < service.params.size(); i++) {
/*     */                 
/* 326 */                 DBOperations.DisposeSql(preparedStatement);
/* 327 */                 preparedStatement = connection.prepareStatement(Queryconstants.insertParams);
/*     */                 
/* 329 */                 preparedStatement.setInt(1, ((Params)service.params.get(i)).paramId);
/*     */                 
/* 331 */                 preparedStatement.setInt(2, subServiceId);
/* 332 */                 preparedStatement.setBoolean(3, ((Params)service.params.get(i)).isActive);
/*     */                 
/* 334 */                 preparedStatement.setInt(4, service.createdBy);
/*     */                 
/* 336 */                 preparedStatement.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 341 */                 if (preparedStatement.executeUpdate() <= 0) {
/* 342 */                   throw new Exception("Failed to Insert Param Id " + ((Params)service.params.get(i)).paramId);
/*     */                 }
/*     */               } 
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/* 349 */           return new ErevenueResponse(200, "Records Updated");
/*     */         } 
/* 351 */         return new ErevenueResponse(201, "Failed to Updated");
/*     */       } 
/*     */       
/* 354 */       if (service.subCountyList.size() > 0) {
/* 355 */         ArrayList<Market> marketList = new ArrayList<>(); int i;
/* 356 */         for (i = 0; i < service.subCountyList.size(); i++) {
/* 357 */           if (((SubCounty)service.subCountyList.get(i)).serviceValue > 0.0D || ((SubCounty)service.subCountyList.get(i)).serviceValue == -1.0D) {
/*     */             
/* 359 */             preparedStatement1 = connection.prepareStatement(Queryconstants.getMarketsForSubcounty);
/*     */             
/* 361 */             preparedStatement1.setInt(1, ((SubCounty)service.subCountyList.get(i)).subCountyId);
/*     */             
/* 363 */             resultSet1 = preparedStatement1.executeQuery();
/*     */             
/* 365 */             while (resultSet1.next()) {
/* 366 */               Market objMarket = new Market();
/* 367 */               objMarket.marketId = resultSet1.getInt("id");
/* 368 */               objMarket.serviceValue = ((SubCounty)service.subCountyList.get(i)).serviceValue;
/*     */               
/* 370 */               marketList.add(objMarket);
/*     */             } 
/*     */           } 
/*     */         } 
/* 374 */         for (i = 0; i < marketList.size(); i++) {
/* 375 */           preparedStatement1 = connection.prepareStatement(Queryconstants.insertMatketPriceDtl);
/*     */           
/* 377 */           preparedStatement1.setInt(1, ((Market)service.marketList.get(i)).marketId);
/*     */           
/* 379 */           preparedStatement1.setInt(2, subServiceId);
/* 380 */           preparedStatement1.setDouble(3, ((Market)service.marketList.get(i)).serviceValue);
/*     */           
/* 382 */           preparedStatement1.setInt(4, service.createdBy);
/* 383 */           preparedStatement1.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */ 
/*     */ 
/*     */           
/* 387 */           if (preparedStatement1.executeUpdate() <= 0) {
/* 388 */             throw new Exception("Failed to Insert Market Id " + ((Market)service.marketList.get(i)).marketId);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 393 */       if (service.wardList.size() > 0) {
/* 394 */         ArrayList<Market> marketList = new ArrayList<>(); int i;
/* 395 */         for (i = 0; i < service.wardList.size(); i++) {
/* 396 */           if (((Ward)service.wardList.get(i)).serviceValue > 0.0D || ((Ward)service.wardList.get(i)).serviceValue == -1.0D) {
/*     */             
/* 398 */             preparedStatement1 = connection.prepareStatement(Queryconstants.getMarketsForWards);
/*     */             
/* 400 */             preparedStatement1.setInt(1, ((Ward)service.wardList.get(i)).wardId);
/*     */             
/* 402 */             resultSet1 = preparedStatement1.executeQuery();
/*     */             
/* 404 */             while (resultSet1.next()) {
/* 405 */               Market objMarket = new Market();
/* 406 */               objMarket.marketId = resultSet1.getInt("id");
/* 407 */               objMarket.serviceValue = ((Ward)service.wardList.get(i)).serviceValue;
/*     */               
/* 409 */               marketList.add(objMarket);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 414 */         for (i = 0; i < marketList.size(); i++) {
/* 415 */           preparedStatement1 = connection.prepareStatement(Queryconstants.insertMatketPriceDtl);
/*     */           
/* 417 */           preparedStatement1.setInt(1, ((Market)service.marketList.get(i)).marketId);
/*     */           
/* 419 */           preparedStatement1.setInt(2, subServiceId);
/* 420 */           preparedStatement1.setDouble(3, ((Market)service.marketList.get(i)).serviceValue);
/*     */           
/* 422 */           preparedStatement1.setInt(4, service.createdBy);
/* 423 */           preparedStatement1.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */ 
/*     */ 
/*     */           
/* 427 */           if (preparedStatement1.executeUpdate() <= 0) {
/* 428 */             throw new Exception("Failed to Insert Market Id " + ((Market)service.marketList.get(i)).marketId);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 433 */       if (service.marketList.size() > 0) {
/* 434 */         for (int i = 0; i < service.marketList.size(); i++) {
/* 435 */           if (((Market)service.marketList.get(i)).serviceValue > 0.0D || ((Market)service.marketList.get(i)).serviceValue == -1.0D) {
/*     */             
/* 437 */             preparedStatement1 = connection.prepareStatement(Queryconstants.insertMatketPriceDtl);
/*     */             
/* 439 */             preparedStatement1.setInt(1, ((Market)service.marketList.get(i)).marketId);
/*     */             
/* 441 */             preparedStatement1.setInt(2, subServiceId);
/* 442 */             preparedStatement1.setDouble(3, ((Market)service.marketList.get(i)).serviceValue);
/*     */             
/* 444 */             preparedStatement1.setInt(4, service.createdBy);
/* 445 */             preparedStatement1.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */ 
/*     */ 
/*     */             
/* 449 */             if (preparedStatement1.executeUpdate() <= 0) {
/* 450 */               throw new Exception("Failed to Insert Market Id " + ((Params)service.params.get(i)).paramId);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       }
/*     */ 
/*     */       
/* 457 */       return new ErevenueResponse(201, "Nothing to update");
/*     */     
/*     */     }
/* 460 */     catch (SQLException sqlEx) {
/* 461 */       sqlEx.printStackTrace();
/* 462 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     }
/* 464 */     catch (Exception ex) {
/* 465 */       ex.printStackTrace();
/* 466 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     } finally {
/* 468 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Service> GetAllServices() {
/* 479 */     Connection connection = null;
/* 480 */     PreparedStatement preparedStatement = null;
/* 481 */     ResultSet resultSet = null;
/* 482 */     int count = 1;
/*     */     try {
/* 484 */       connection = this.dataSource.getConnection();
/* 485 */       preparedStatement = connection.prepareStatement(Queryconstants.getServices);
/*     */ 
/*     */       
/* 488 */       resultSet = preparedStatement.executeQuery();
/* 489 */       List<Service> services = new ArrayList<>();
/* 490 */       while (resultSet.next()) {
/* 491 */         services.add(new Service(resultSet.getInt("ID"), resultSet.getString("Service_Code"), resultSet.getString("Service_Name"), resultSet.getBoolean("Active"), resultSet.getInt("Created_By"), 200, (resultSet.getBoolean("Active") == true) ? "Active" : "Inactive", count, true, "glyphicon glyphicon-pencil"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 498 */         count++;
/*     */       } 
/* 500 */       return services;
/* 501 */     } catch (Exception ex) {
/* 502 */       ex.printStackTrace();
/* 503 */       return null;
/*     */     } finally {
/* 505 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Service GetServiceById(int serviceId) {
/* 516 */     Connection connection = null;
/* 517 */     PreparedStatement preparedStatement = null;
/* 518 */     ResultSet resultSet = null;
/* 519 */     int count = 1;
/*     */     try {
/* 521 */       connection = this.dataSource.getConnection();
/*     */       
/* 523 */       preparedStatement = connection.prepareStatement(Queryconstants.getServiceById);
/*     */       
/* 525 */       preparedStatement.setInt(1, serviceId);
/*     */       
/* 527 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 529 */       if (resultSet.next()) {
/* 530 */         return new Service(resultSet.getInt("ServiceID"), resultSet.getString("ServiceCode"), resultSet.getString("ServiceName"), resultSet.getBoolean("active"), resultSet.getInt("CreatedBy"), 200, (resultSet.getBoolean("Active") == true) ? "Active" : "Inactive", count, true, "glyphicon glyphicon-pencil");
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 540 */       return new Service(201);
/*     */     }
/* 542 */     catch (Exception ex) {
/* 543 */       ex.printStackTrace();
/* 544 */       return null;
/*     */     } finally {
/* 546 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
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
/*     */   public List<Service> GetSubServiceById(int serviceId) {
/* 558 */     Connection connection = null;
/* 559 */     PreparedStatement preparedStatement = null;
/* 560 */     ResultSet resultSet = null;
/* 561 */     ResultSet resultSet2 = null;
/* 562 */     int count = 1;
/*     */     try {
/* 564 */       connection = this.dataSource.getConnection();
/*     */       
/* 566 */       preparedStatement = connection.prepareStatement(Queryconstants.getSubServiceById);
/*     */       
/* 568 */       preparedStatement.setInt(1, serviceId);
/*     */       
/* 570 */       resultSet = preparedStatement.executeQuery();
/* 571 */       List<Service> services = new ArrayList<>();
/* 572 */       while (resultSet.next()) {
/* 573 */         Service service = new Service();
/* 574 */         service.serviceId = resultSet.getInt("ID");
/* 575 */         service.serviceCode = resultSet.getString("subService_Name");
/* 576 */         service.serviceName = resultSet.getString("subService_Name");
/* 577 */         service.active = resultSet.getBoolean("active");
/* 578 */         service.createdBy = resultSet.getInt("Created_By");
/* 579 */         service.level = resultSet.getInt("level");
/* 580 */         service.hasChild = resultSet.getBoolean("has_child");
/* 581 */         service.parentServiceId = resultSet.getInt("parent_Service_Id");
/* 582 */         service.count = count;
/* 583 */         if (resultSet.getInt("level") == -1) {
/* 584 */           preparedStatement = connection.prepareStatement(Queryconstants.getServiceParams);
/*     */           
/* 586 */           preparedStatement.setInt(1, service.serviceId);
/* 587 */           preparedStatement.setInt(2, service.serviceId);
/* 588 */           resultSet2 = preparedStatement.executeQuery();
/* 589 */           List<Params> params = new ArrayList<>();
/* 590 */           while (resultSet2.next()) {
/* 591 */             Params objParam = new Params();
/* 592 */             objParam.paramId = resultSet2.getInt("Param_Id");
/* 593 */             objParam.paramName = resultSet2.getString("param_Name");
/* 594 */             objParam.isActive = resultSet2.getBoolean("active");
/* 595 */             params.add(objParam);
/*     */           } 
/* 597 */           service.params = params;
/*     */         } 
/* 599 */         services.add(service);
/* 600 */         count++;
/*     */       } 
/* 602 */       return services;
/*     */     }
/* 604 */     catch (Exception ex) {
/* 605 */       ex.printStackTrace();
/* 606 */       return null;
/*     */     } finally {
/* 608 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Params> GetAllParams() {
/* 618 */     Connection connection = null;
/* 619 */     PreparedStatement preparedStatement = null;
/* 620 */     ResultSet resultSet = null;
/* 621 */     int count = 1;
/*     */     try {
/* 623 */       connection = this.dataSource.getConnection();
/* 624 */       preparedStatement = connection.prepareStatement(Queryconstants.getParams);
/*     */ 
/*     */       
/* 627 */       resultSet = preparedStatement.executeQuery();
/* 628 */       List<Params> params = new ArrayList<>();
/* 629 */       while (resultSet.next()) {
/* 630 */         params.add(new Params(resultSet.getInt("ID"), resultSet.getString("Param_Name"), resultSet.getString("Param_type"), resultSet.getBoolean("Active"), resultSet.getInt("Created_By"), 200, count, resultSet.getString("status")));
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 635 */         count++;
/*     */       } 
/* 637 */       return params;
/* 638 */     } catch (Exception ex) {
/* 639 */       ex.printStackTrace();
/* 640 */       return null;
/*     */     } finally {
/* 642 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ErevenueResponse UpdateParam(Params param) {
/* 652 */     Connection connection = null;
/* 653 */     PreparedStatement preparedStatement = null;
/* 654 */     ResultSet resultSet = null;
/*     */     try {
/* 656 */       connection = this.dataSource.getConnection();
/*     */       
/* 658 */       if (param.paramId == 0) {
/* 659 */         if (checkParamName(param.paramName)) {
/* 660 */           return new ErevenueResponse(201, "Param Name Already Exists");
/*     */         }
/*     */ 
/*     */         
/* 664 */         preparedStatement = connection.prepareStatement(Queryconstants.insertParamInfo);
/*     */         
/* 666 */         preparedStatement.setString(1, param.paramName);
/* 667 */         preparedStatement.setString(2, param.paramType);
/* 668 */         preparedStatement.setBoolean(3, param.active);
/* 669 */         preparedStatement.setInt(4, param.createdBy);
/* 670 */         preparedStatement.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */       } else {
/*     */         
/* 673 */         if (checkParamNameById(param.paramName, param.paramId)) {
/* 674 */           return new ErevenueResponse(201, "Param Name Already Exists");
/*     */         }
/*     */         
/* 677 */         preparedStatement = connection.prepareStatement(Queryconstants.updateParamInfo);
/*     */         
/* 679 */         preparedStatement.setString(1, param.paramName);
/* 680 */         preparedStatement.setString(2, param.paramType);
/* 681 */         preparedStatement.setBoolean(3, param.active);
/* 682 */         preparedStatement.setInt(4, param.createdBy);
/* 683 */         preparedStatement.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */         
/* 685 */         preparedStatement.setInt(6, param.paramId);
/*     */       } 
/* 687 */       return (preparedStatement.executeUpdate() > 0) ? new ErevenueResponse(200, "Records Updated") : new ErevenueResponse(201, "Nothing To Update");
/*     */     
/*     */     }
/* 690 */     catch (SQLException sqlEx) {
/* 691 */       sqlEx.printStackTrace();
/* 692 */       if (sqlEx.getMessage().indexOf("Cannot insert duplicate key") != 0) {
/* 693 */         return new ErevenueResponse(201, "Param name Already Exists");
/*     */       }
/* 695 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     }
/* 697 */     catch (Exception ex) {
/* 698 */       ex.printStackTrace();
/* 699 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     } finally {
/* 701 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
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
/*     */   public boolean checkParamName(String paramName) {
/* 713 */     Connection connection = null;
/* 714 */     PreparedStatement preparedStatement = null;
/* 715 */     ResultSet resultSet = null;
/*     */     try {
/* 717 */       connection = this.dataSource.getConnection();
/* 718 */       preparedStatement = connection.prepareStatement(Queryconstants.getParamName);
/*     */       
/* 720 */       preparedStatement.setString(1, paramName);
/*     */       
/* 722 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 724 */       if (resultSet.next()) {
/* 725 */         return true;
/*     */       }
/* 727 */       return false;
/*     */     }
/* 729 */     catch (Exception ex) {
/* 730 */       ex.printStackTrace();
/* 731 */       return false;
/*     */     } finally {
/* 733 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
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
/*     */   public boolean checkParamNameById(String paramName, int paramId) {
/* 745 */     Connection connection = null;
/* 746 */     PreparedStatement preparedStatement = null;
/* 747 */     ResultSet resultSet = null;
/*     */     try {
/* 749 */       connection = this.dataSource.getConnection();
/* 750 */       preparedStatement = connection.prepareStatement(Queryconstants.getParamNameById);
/*     */       
/* 752 */       preparedStatement.setString(1, paramName);
/* 753 */       preparedStatement.setInt(2, paramId);
/*     */       
/* 755 */       resultSet = preparedStatement.executeQuery();
/*     */       
/* 757 */       if (resultSet.next()) {
/* 758 */         return true;
/*     */       }
/* 760 */       return false;
/*     */     }
/* 762 */     catch (Exception ex) {
/* 763 */       ex.printStackTrace();
/* 764 */       return false;
/*     */     } finally {
/* 766 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Service> GetAllSubServices() {
/* 777 */     Connection connection = null;
/* 778 */     PreparedStatement preparedStatement = null;
/* 779 */     ResultSet resultSet = null;
/* 780 */     int count = 1;
/*     */     try {
/* 782 */       connection = this.dataSource.getConnection();
/* 783 */       preparedStatement = connection.prepareStatement(Queryconstants.getAllSubServices);
/*     */ 
/*     */       
/* 786 */       resultSet = preparedStatement.executeQuery();
/* 787 */       List<Service> subSer = new ArrayList<>();
/* 788 */       while (resultSet.next()) {
/* 789 */         subSer.add(new Service((resultSet.getInt("level") == -1) ? "Final Service" : "Not Final Service", resultSet.getInt("Id"), resultSet.getString("SubService_Name"), (resultSet.getBoolean("Active") == true) ? "Active" : "InActive", resultSet.getBoolean("Active"), count, true, "glyphicon glyphicon-pencil"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 796 */         count++;
/*     */       } 
/* 798 */       return subSer;
/* 799 */     } catch (Exception ex) {
/* 800 */       ex.printStackTrace();
/* 801 */       return null;
/*     */     } finally {
/* 803 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ErevenueResponse UpdateSerPrice(Service ser) {
/* 814 */     Connection connection = null;
/* 815 */     PreparedStatement preparedStatement = null;
/* 816 */     ResultSet resultSet = null;
/*     */     try {
/* 818 */       connection = this.dataSource.getConnection();
/*     */       
/* 820 */       if (ser.serviceType == 1) {
/* 821 */         for (int i = 0; i < ser.serviceList.size(); i++) {
/* 822 */           preparedStatement = connection.prepareStatement(Queryconstants.updateParentSerInfo);
/*     */           
/* 824 */           preparedStatement.setString(1, ((Service)ser.serviceList.get(i)).serviceName);
/*     */           
/* 826 */           preparedStatement.setBoolean(2, ((Service)ser.serviceList.get(i)).active);
/*     */           
/* 828 */           preparedStatement.setInt(3, ser.createdBy);
/* 829 */           preparedStatement.setTimestamp(4, new Timestamp((new Date()).getTime()));
/*     */           
/* 831 */           preparedStatement.setInt(5, ((Service)ser.serviceList.get(i)).serviceId);
/*     */           
/* 833 */           if (preparedStatement.executeUpdate() <= 0) {
/* 834 */             throw new Exception("Failed to update parent services details ");
/*     */           }
/*     */         }
/*     */       
/* 838 */       } else if (ser.serviceType == 2) {
/* 839 */         for (int i = 0; i < ser.serviceList.size(); i++) {
/* 840 */           preparedStatement = connection.prepareStatement(Queryconstants.updateSubSerInfo);
/*     */           
/* 842 */           preparedStatement.setString(1, ((Service)ser.serviceList.get(i)).serviceName);
/*     */           
/* 844 */           preparedStatement.setBoolean(2, ((Service)ser.serviceList.get(i)).active);
/*     */           
/* 846 */           preparedStatement.setInt(3, ser.createdBy);
/* 847 */           preparedStatement.setTimestamp(4, new Timestamp((new Date()).getTime()));
/*     */           
/* 849 */           preparedStatement.setInt(5, ((Service)ser.serviceList.get(i)).serviceId);
/*     */           
/* 851 */           if (preparedStatement.executeUpdate() <= 0) {
/* 852 */             throw new Exception("Failed to update sub services details ");
/*     */           }
/*     */         }
/*     */       
/*     */       }
/* 857 */       else if (ser.serviceType == 3) {
/* 858 */         preparedStatement = connection.prepareStatement(Queryconstants.deleteMarketPriceDtl);
/*     */         
/* 860 */         preparedStatement.setInt(1, ser.marketId);
/* 861 */         preparedStatement.executeUpdate();
/* 862 */         DBOperations.DisposeSql(preparedStatement);
/* 863 */         for (int i = 0; i < ser.serviceList.size(); i++) {
/* 864 */           if (((Service)ser.serviceList.get(i)).serviceValue > 0.0D || ((Service)ser.serviceList.get(i)).serviceValue == -1.0D) {
/*     */             
/* 866 */             preparedStatement = connection.prepareStatement(Queryconstants.insertMatketPriceDtl);
/*     */             
/* 868 */             preparedStatement.setInt(1, ser.marketId);
/* 869 */             preparedStatement.setInt(2, ((Service)ser.serviceList.get(i)).serviceId);
/*     */             
/* 871 */             preparedStatement.setDouble(3, ((Service)ser.serviceList.get(i)).serviceValue);
/*     */             
/* 873 */             preparedStatement.setInt(4, ser.createdBy);
/* 874 */             preparedStatement.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 879 */             if (preparedStatement.executeUpdate() <= 0) {
/* 880 */               throw new Exception("Failed to Insert Service Id " + ((Service)ser.serviceList.get(i)).serviceId);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 887 */       return new ErevenueResponse(200, "Update Successfully");
/* 888 */     } catch (SQLException sqlEx) {
/* 889 */       sqlEx.printStackTrace();
/* 890 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     }
/* 892 */     catch (Exception ex) {
/* 893 */       ex.printStackTrace();
/* 894 */       return new ErevenueResponse(202, "Exception Occured");
/*     */     } finally {
/* 896 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Service> GetMarketSubServices(int marketId) {
/* 907 */     Connection connection = null;
/* 908 */     PreparedStatement preparedStatement = null;
/* 909 */     ResultSet resultSet = null;
/* 910 */     int count = 1;
/*     */     try {
/* 912 */       connection = this.dataSource.getConnection();
/* 913 */       preparedStatement = connection.prepareStatement(Queryconstants.getMarketPriceDetails);
/*     */       
/* 915 */       preparedStatement.setInt(1, marketId);
/* 916 */       preparedStatement.setInt(2, marketId);
/* 917 */       preparedStatement.setInt(3, marketId);
/* 918 */       resultSet = preparedStatement.executeQuery();
/* 919 */       List<Service> subSer = new ArrayList<>();
/* 920 */       while (resultSet.next()) {
/* 921 */         subSer.add(new Service(resultSet.getInt("ser_id"), resultSet.getString("subservice_name"), resultSet.getDouble("fee"), (resultSet.getBoolean("Active") == true) ? "Active" : "Inactive", resultSet.getBoolean("Active"), count, true, "glyphicon glyphicon-pencil"));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 927 */         count++;
/*     */       } 
/* 929 */       return subSer;
/* 930 */     } catch (Exception ex) {
/* 931 */       ex.printStackTrace();
/* 932 */       return null;
/*     */     } finally {
/* 934 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\impl\ServiceDalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */