/*      */ package com.compulynx.erevenue.dal.impl;
/*      */ 
/*      */ import com.compulynx.erevenue.dal.LandRateDal;
/*      */ import com.compulynx.erevenue.dal.operations.DBOperations;
/*      */ import com.compulynx.erevenue.dal.operations.Queryconstants;
import com.compulynx.erevenue.models.CompasResponse;
/*      */ import com.compulynx.erevenue.models.ErevenueResponse;
/*      */ import com.compulynx.erevenue.models.LandRate;
/*      */ import com.compulynx.erevenue.models.PermitYear;
import com.mysql.jdbc.Statement;

/*      */ import java.awt.image.BufferedImage;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
/*      */ import java.math.BigDecimal;
/*      */ import java.sql.Connection;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Timestamp;
/*      */ import java.text.DateFormat;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import javax.sql.DataSource;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class LandRateDalImpl
/*      */   implements LandRateDal
/*      */ {
/*      */   private DataSource dataSource;
/*      */   
/*      */   public LandRateDalImpl(DataSource dataSource) {
/*   33 */     this.dataSource = dataSource;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ErevenueResponse CreateRegistrationForm(LandRate lr) {
/*   42 */     Connection connection = null;
/*   43 */     PreparedStatement preparedStatement = null;
/*   44 */     PreparedStatement preparedStatement1 = null;
/*   45 */     PreparedStatement preparedStatement2 = null;
/*   46 */     ResultSet resultSet = null;
/*      */     
/*   48 */     int id = 0;
/*   49 */     int regId = 0;
/*      */     try {
/*   51 */       connection = this.dataSource.getConnection();
/*   52 */       if (lr.id == 0) {
/*      */         
/*   54 */         if (checkPlotNumber(lr.plotNumber)) {
/*   55 */           return new ErevenueResponse(201, "Plot Number already exists");
/*      */         }
/*      */         
/*   58 */         if (checkTitleDeedNumber(lr.titleDeedNumber)) {
/*   59 */           return new ErevenueResponse(201, "title deed number already exists");
/*      */         }
/*      */         
/*   62 */         if (lr.linkId == 4)
/*      */         {
/*   64 */           if (!ValidateNationalId(lr.nationalIdNumber)) {
/*   65 */             return new ErevenueResponse(201, "National id doesn't match with sign up user's national Id");
/*      */           }
/*      */         }
/*      */         
/*   69 */         preparedStatement = connection.prepareStatement(Queryconstants.insertRegistrationForm, 1);
/*      */ 
/*      */         
/*   72 */         preparedStatement.setString(1, lr.plotNumber);
/*   73 */         preparedStatement.setString(2, lr.mapSheetNumber);
/*   74 */         preparedStatement.setString(3, lr.location);
/*   75 */         preparedStatement.setString(4, lr.acreage);
/*   76 */         preparedStatement.setString(5, lr.titleDeedNumber);
/*   77 */         preparedStatement.setInt(6, lr.permitTypeId);
/*   78 */         preparedStatement.setString(7, lr.name);
/*   79 */         preparedStatement.setString(8, lr.krapin);
/*   80 */         preparedStatement.setString(9, lr.nationalIdNumber);
/*   81 */         preparedStatement.setInt(10, lr.wardId);
/*   82 */         preparedStatement.setInt(11, lr.subCountyId);
/*   83 */         preparedStatement.setString(12, lr.createdBy);
                   preparedStatement.setString(13, lr.address);
                   preparedStatement.setString(14, lr.code);
/*   84 */         preparedStatement.setTimestamp(15, new Timestamp((new Date()).getTime()));
/*      */         
/*   86 */         if (preparedStatement.executeUpdate() > 0) {
/*   87 */           if (lr.id == 0) {
/*   88 */             ResultSet rs = preparedStatement.getGeneratedKeys();
/*   89 */             rs.next();
/*   90 */             id = rs.getInt(1);
/*      */           } 
/*      */           
/*   93 */           DBOperations.DisposeSql(preparedStatement);
/*   94 */           preparedStatement = connection.prepareStatement(Queryconstants.updateLandNo);
/*      */           
/*   96 */           preparedStatement.setString(1, GetLandNo(id));
/*   97 */           preparedStatement.setInt(2, id);
/*      */           
/*   99 */           Calendar year2 = Calendar.getInstance();
/*  100 */           int year = year2.get(1);
/*  101 */           if (preparedStatement.executeUpdate() > 0) {
/*  102 */             preparedStatement1 = connection.prepareStatement(Queryconstants.insertlandregistrationDtl, 1);
/*      */ 
/*      */             
/*  105 */             preparedStatement1.setInt(1, id);
/*  106 */             preparedStatement1.setDouble(2, lr.fee);
/*  107 */             preparedStatement1.setDouble(3, 0.0D);
/*  108 */             preparedStatement1.setString(4, "New");
/*  109 */             preparedStatement1.setString(5, lr.status);
/*  110 */             preparedStatement1.setString(6, lr.createdBy);
/*  111 */             preparedStatement1.setTimestamp(7, new Timestamp((new Date()).getTime()));
/*      */ 
/*      */ 
/*      */             
/*  115 */             preparedStatement1.setString(8, "");
/*  116 */             preparedStatement1.setInt(9, year);
/*  117 */             if (preparedStatement1.executeUpdate() > 0) {
/*  118 */               if (lr.regId == 0) {
/*  119 */                 ResultSet rs = preparedStatement1.getGeneratedKeys();
/*  120 */                 rs.next();
/*  121 */                 regId = rs.getInt(1);
/*      */               } 
/*  123 */               DBOperations.DisposeSql(preparedStatement1);
/*  124 */               preparedStatement1 = connection.prepareStatement(Queryconstants.updateRegNo);
/*      */               
/*  126 */               preparedStatement1.setString(1, GetRegNo(regId));
/*  127 */               preparedStatement1.setInt(2, regId);
/*  128 */               if (preparedStatement1.executeUpdate() > 0) {
/*  129 */                 return new ErevenueResponse(200, "Records Updated");
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  137 */         return new ErevenueResponse(201, "Records Updated");
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/*  142 */       if (checkPlotByTitleDeed(lr.plotNumber, lr.titleDeedNumber, lr.id))
/*      */       {
/*  144 */         return new ErevenueResponse(201, "Plot number already exists");
/*      */       }
/*      */       
/*  147 */       if (lr.linkId == 4)
/*      */       {
/*  149 */         if (!ValidateNationalId(lr.nationalIdNumber)) {
/*  150 */           return new ErevenueResponse(201, "National id doesn't match with sign up user's national Id");
/*      */         }
/*      */       }
/*      */       
/*  154 */       preparedStatement = connection.prepareStatement(Queryconstants.updateRegistrationForm);
/*      */       
/*  156 */       preparedStatement.setString(1, lr.plotNumber);
/*  157 */       preparedStatement.setString(2, lr.mapSheetNumber);
/*  158 */       preparedStatement.setString(3, lr.location);
/*  159 */       preparedStatement.setString(4, lr.acreage);
/*  160 */       preparedStatement.setString(5, lr.titleDeedNumber);
/*  161 */       preparedStatement.setString(6, lr.name);
/*  162 */       preparedStatement.setString(7, lr.krapin);
/*  163 */       preparedStatement.setString(8, lr.nationalIdNumber);
/*  164 */       preparedStatement.setInt(9, lr.wardId);
/*  165 */       preparedStatement.setInt(10, lr.subCountyId);
/*  166 */       preparedStatement.setString(11, lr.createdBy);
                 preparedStatement.setString(12, lr.address);
                 preparedStatement.setString(13, lr.code);

/*  167 */       preparedStatement.setTimestamp(14, new Timestamp((new Date()).getTime()));
/*      */       
/*  169 */       preparedStatement.setInt(15, lr.id);
/*  170 */       if (preparedStatement.executeUpdate() > 0) {
/*  171 */         DBOperations.DisposeSql(preparedStatement);
/*  172 */         if (lr.status.equalsIgnoreCase("AM")) {
/*  173 */           preparedStatement = connection.prepareStatement(Queryconstants.updateLandAmendedStatus);
/*      */           
/*  175 */           preparedStatement.setString(1, lr.status);
/*  176 */           preparedStatement.setString(2, lr.createdBy);
/*  177 */           preparedStatement.setTimestamp(3, new Timestamp((new Date()).getTime()));
/*      */ 
/*      */ 
/*      */           
/*  181 */           preparedStatement.setInt(4, lr.id);
/*      */         } 
/*      */       } 
/*      */       
/*  185 */       return (preparedStatement.executeUpdate() > 0) ? new ErevenueResponse(200, "Records Updated") : new ErevenueResponse(201, "Nothing To Update");
/*      */ 
/*      */     
/*      */     }
/*  189 */     catch (SQLException sqlEx) {
/*  190 */       sqlEx.printStackTrace();
/*  191 */       return new ErevenueResponse(202, "Exception Occured");
/*      */     }
/*  193 */     catch (Exception ex) {
/*  194 */       ex.printStackTrace();
/*  195 */       return new ErevenueResponse(202, "Exception Occured");
/*      */     } finally {
/*  197 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean ValidateNationalId(String nationalIdNumber) {
/*  209 */     Connection connection = null;
/*  210 */     PreparedStatement preparedStatement = null;
/*  211 */     ResultSet resultSet = null;
/*      */     try {
/*  213 */       connection = this.dataSource.getConnection();
/*  214 */       preparedStatement = connection.prepareStatement(Queryconstants.validateLandNationalId);
/*      */       
/*  216 */       preparedStatement.setString(1, nationalIdNumber);
/*      */       
/*  218 */       resultSet = preparedStatement.executeQuery();
/*      */       
/*  220 */       if (resultSet.next()) {
/*  221 */         return true;
/*      */       }
/*  223 */       return false;
/*      */     }
/*  225 */     catch (Exception ex) {
/*  226 */       ex.printStackTrace();
/*  227 */       return false;
/*      */     } finally {
/*  229 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkPlotByTitleDeed(String plotNumber, String titleDeedNumber, int id) {
/*  243 */     Connection connection = null;
/*  244 */     PreparedStatement preparedStatement = null;
/*  245 */     ResultSet resultSet = null;
/*      */     try {
/*  247 */       connection = this.dataSource.getConnection();
/*  248 */       preparedStatement = connection.prepareStatement(Queryconstants.checkPlotNumberByTitleDeed);
/*      */       
/*  250 */       preparedStatement.setString(1, plotNumber);
/*  251 */       preparedStatement.setString(2, titleDeedNumber);
/*  252 */       preparedStatement.setInt(3, id);
/*      */       
/*  254 */       resultSet = preparedStatement.executeQuery();
/*      */       
/*  256 */       if (resultSet.next()) {
/*  257 */         return true;
/*      */       }
/*  259 */       return false;
/*      */     }
/*  261 */     catch (Exception ex) {
/*  262 */       ex.printStackTrace();
/*  263 */       return false;
/*      */     } finally {
/*  265 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private boolean checkTitleDeedNumber(String titleDeedNumber) {
/*  276 */     Connection connection = null;
/*  277 */     PreparedStatement preparedStatement = null;
/*  278 */     ResultSet resultSet = null;
/*      */     try {
/*  280 */       connection = this.dataSource.getConnection();
/*  281 */       preparedStatement = connection.prepareStatement(Queryconstants.checkTitleDeedNumber);
/*      */       
/*  283 */       preparedStatement.setString(1, titleDeedNumber);
/*      */       
/*  285 */       resultSet = preparedStatement.executeQuery();
/*      */       
/*  287 */       if (resultSet.next()) {
/*  288 */         return true;
/*      */       }
/*  290 */       return false;
/*      */     }
/*  292 */     catch (Exception ex) {
/*  293 */       ex.printStackTrace();
/*  294 */       return false;
/*      */     } finally {
/*  296 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String GetLandNo(int id) {
/*  307 */     Connection connection = null;
/*  308 */     PreparedStatement preparedStatement = null;
/*  309 */     ResultSet resultSet = null;
/*  310 */     String landNo = "";
/*      */     try {
/*  312 */       DateFormat dateFormat = new SimpleDateFormat("yy");
/*  313 */       Date date = new Date();
/*  314 */       System.out.println(dateFormat.format(date));
/*  315 */       connection = this.dataSource.getConnection();
/*  316 */       preparedStatement = connection.prepareStatement(Queryconstants.getlandWard);
/*      */ 
/*      */       
/*  319 */       resultSet = preparedStatement.executeQuery();
/*      */       
/*  321 */       if (resultSet.next()) {
/*  322 */         System.out.println("landno###" + dateFormat.format(date) + String.format("%06d", new Object[] { Integer.valueOf(id) }));
/*      */         
/*  324 */         landNo = dateFormat.format(date) + String.format("%06d", new Object[] { Integer.valueOf(id) });
/*      */       } 
/*  326 */       return landNo;
/*  327 */     } catch (Exception ex) {
/*  328 */       ex.printStackTrace();
/*  329 */       return landNo = "";
/*      */     } finally {
/*  331 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private String GetRegNo(int regId) {
/*  343 */     Connection connection = null;
/*  344 */     PreparedStatement preparedStatement = null;
/*  345 */     ResultSet resultSet = null;
/*  346 */     String landNo = "";
/*      */     try {
/*  348 */       DateFormat dateFormat = new SimpleDateFormat("yy");
/*  349 */       Date date = new Date();
/*  350 */       System.out.println(dateFormat.format(date));
/*  351 */       connection = this.dataSource.getConnection();
/*  352 */       preparedStatement = connection.prepareStatement(Queryconstants.getlandCountyCode);
/*      */       
/*  354 */       resultSet = preparedStatement.executeQuery();
/*      */       
/*  356 */       if (resultSet.next()) {
/*  357 */         System.out.println("regno###" + dateFormat.format(date) + String.format("%06d", new Object[] { Integer.valueOf(regId) }));
/*      */         
/*  359 */         landNo = resultSet.getString("value") + dateFormat.format(date) + String.format("%06d", new Object[] { Integer.valueOf(regId) });
/*      */       } 
/*      */       
/*  362 */       return landNo;
/*  363 */     } catch (Exception ex) {
/*  364 */       ex.printStackTrace();
/*  365 */       return landNo = "";
/*      */     } finally {
/*  367 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkPlotNumber(String plotNumber) {
/*  379 */     Connection connection = null;
/*  380 */     PreparedStatement preparedStatement = null;
/*  381 */     ResultSet resultSet = null;
/*      */     try {
/*  383 */       connection = this.dataSource.getConnection();
/*  384 */       preparedStatement = connection.prepareStatement(Queryconstants.checkPlotNumber);
/*      */       
/*  386 */       preparedStatement.setString(1, plotNumber);
/*      */       
/*  388 */       resultSet = preparedStatement.executeQuery();
/*      */       
/*  390 */       if (resultSet.next()) {
/*  391 */         return true;
/*      */       }
/*  393 */       return false;
/*      */     }
/*  395 */     catch (Exception ex) {
/*  396 */       ex.printStackTrace();
/*  397 */       return false;
/*      */     } finally {
/*  399 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<LandRate> GetAllRegs() {
/*  407 */     Connection connection = null;
/*  408 */     PreparedStatement preparedStatement = null;
/*  409 */     ResultSet resultSet = null;
/*  410 */     int count = 1;
/*      */     try {
/*  412 */       connection = this.dataSource.getConnection();
/*  413 */       preparedStatement = connection.prepareStatement(Queryconstants.getAllRegistrations);
/*      */       
/*  415 */       resultSet = preparedStatement.executeQuery();
/*  416 */       List<LandRate> lr = new ArrayList<>();
/*  417 */       while (resultSet.next()) {
/*  418 */         lr.add(new LandRate(resultSet.getInt("id"), resultSet.getString("plot_number"), resultSet.getString("mapsheet_number"), resultSet.getString("location"), resultSet.getString("acreage"), resultSet.getString("title_deed_number"), resultSet.getInt("land_type_id"), resultSet.getString("name"), resultSet.getString("kra_pin"), resultSet.getString("national_id_number"), resultSet.getInt("ward_id"), resultSet.getInt("subCounty_id"), 200, count, resultSet.getString("invoice_status"), resultSet.getString("created_by"),resultSet.getString("address"),resultSet.getString("code")));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  431 */         count++;
/*      */       } 
/*  433 */       return lr;
/*  434 */     } catch (Exception ex) {
/*  435 */       ex.printStackTrace();
/*  436 */       return null;
/*      */     } finally {
/*  438 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<LandRate> GetAllLandInvoices() {
/*  447 */     Connection connection = null;
/*  448 */     PreparedStatement preparedStatement = null;
/*  449 */     PreparedStatement preparedStatement1 = null;
/*  450 */     ResultSet resultSet = null;
/*  451 */     ResultSet resultSet2 = null;
/*  452 */     int count = 1;
/*      */     try {
/*  454 */       connection = this.dataSource.getConnection();
/*  455 */       preparedStatement = connection.prepareStatement(Queryconstants.getLandInvoices);
/*      */       
/*  457 */       resultSet = preparedStatement.executeQuery();
/*  458 */       List<LandRate> lr = new ArrayList<>();
/*  459 */       while (resultSet.next()) {
/*  460 */         LandRate objlr = new LandRate();
/*  461 */         objlr.id = resultSet.getInt("id");
/*  462 */         objlr.landNo = resultSet.getString("land_no");
/*  463 */         objlr.plotNumber = resultSet.getString("plot_number");
/*  464 */         objlr.mapSheetNumber = resultSet.getString("mapsheet_number");
/*  465 */         objlr.location = resultSet.getString("location");
/*  466 */         objlr.acreage = resultSet.getString("acreage");
/*  467 */         objlr.titleDeedNumber = resultSet.getString("title_deed_number");
/*      */         
/*  469 */         objlr.name = resultSet.getString("owner");
/*  470 */         objlr.krapin = resultSet.getString("kra_pin");
/*  471 */         objlr.nationalIdNumber = resultSet.getString("national_id_number");
/*      */         
/*  473 */         objlr.wardId = resultSet.getInt("ward_id");
/*      */         
/*  475 */         objlr.applicant = resultSet.getString("username");
/*  476 */         objlr.appliedOn = resultSet.getString("created_on");
/*  477 */         objlr.regNo = resultSet.getString("reg_no");
/*  478 */         objlr.landId = resultSet.getInt("land_id");
/*  479 */         objlr.status = resultSet.getString("invoice_status");
/*  480 */         objlr.paidStatus = resultSet.getString("paid_status");
/*  481 */         objlr.subCountyId = resultSet.getInt("subCounty_id");
/*  482 */         objlr.approvedBy = resultSet.getInt("approved_by");
/*  483 */         objlr.approvedOn = resultSet.getString("approved_On");
/*  484 */         objlr.rejectReason = resultSet.getString("rejected_reason");
/*  485 */         objlr.fee = resultSet.getDouble("fee");
objlr.balance = resultSet.getDouble("balance");
objlr.amount = resultSet.getDouble("paid_amount");
objlr.fee = resultSet.getDouble("fee");
/*  486 */         objlr.penalty = resultSet.getDouble("penalty");
/*  487 */         objlr.paidDate = resultSet.getString("paid_date");
/*  488 */         objlr.permitTypeId = resultSet.getInt("land_type_id");
/*  489 */         objlr.appliedFor = resultSet.getInt("applied_for");
/*  490 */         objlr.subCountyName = resultSet.getString("name");
/*  491 */         objlr.wardName = resultSet.getString("ward_name");
/*  492 */         objlr.preLr = resultSet.getString("previous_lr");
/*  493 */         objlr.landTypeName = resultSet.getString("permit_type_name");
/*  494 */         objlr.landType = resultSet.getString("land_type");
/*  495 */         if (objlr.approvedBy > 0) {
/*  496 */           preparedStatement1 = connection.prepareStatement(Queryconstants.getLandApprovedUser);
/*      */           
/*  498 */           preparedStatement1.setInt(1, objlr.approvedBy);
/*  499 */           resultSet2 = preparedStatement1.executeQuery();
/*  500 */           if (resultSet2.next()) {
/*  501 */             objlr.approvedUser = resultSet2.getString("username");
/*      */           }
/*      */         } 
/*      */         
/*  505 */         if (resultSet.getInt("receipt_by") > 0) {
/*  506 */           preparedStatement1 = connection.prepareStatement(Queryconstants.getPaidLandUser);
/*      */           
/*  508 */           preparedStatement1.setInt(1, resultSet.getInt("receipt_by"));
/*      */           
/*  510 */           resultSet2 = preparedStatement1.executeQuery();
/*  511 */           if (resultSet2.next()) {
/*  512 */             objlr.paidUser = resultSet2.getString("username");
/*      */           }
/*      */         } 
/*      */         
/*  516 */         objlr.count = count;
/*  517 */         lr.add(objlr);
/*  518 */         count++;
/*      */       } 
/*  520 */       return lr;
/*  521 */     } catch (Exception ex) {
/*  522 */       ex.printStackTrace();
/*  523 */       return null;
/*      */     } finally {
/*  525 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<LandRate> GetAllLandInvoicesByLinkId(int linkId, String nationalIdNo, int agentId) {
/*  543 */     Connection connection = null;
/*  544 */     PreparedStatement preparedStatement = null;
/*  545 */     PreparedStatement preparedStatement1 = null;
/*  546 */     ResultSet resultSet = null;
/*  547 */     ResultSet resultSet2 = null;
/*  548 */     int count = 1;
/*      */     try {
/*  550 */       connection = this.dataSource.getConnection();
/*  551 */       List<LandRate> lr = new ArrayList<>();
/*  552 */       if (linkId == 3) {
/*  553 */         preparedStatement = connection.prepareStatement(Queryconstants.getLandInvoicesByAgentId);
/*      */         
/*  555 */         preparedStatement.setInt(1, agentId);
/*  556 */         resultSet = preparedStatement.executeQuery();
/*      */         
/*  558 */         while (resultSet.next()) {
/*  559 */           LandRate objlr = new LandRate();
/*  560 */           objlr.id = resultSet.getInt("id");
/*  561 */           objlr.landNo = resultSet.getString("land_no");
/*  562 */           objlr.plotNumber = resultSet.getString("plot_number");
/*  563 */           objlr.mapSheetNumber = resultSet.getString("mapsheet_number");
/*      */           
/*  565 */           objlr.location = resultSet.getString("location");
/*  566 */           objlr.acreage = resultSet.getString("acreage");
/*  567 */           objlr.titleDeedNumber = resultSet.getString("title_deed_number");
/*      */           
/*  569 */           objlr.name = resultSet.getString("name");
/*  570 */           objlr.krapin = resultSet.getString("kra_pin");
/*  571 */           objlr.nationalIdNumber = resultSet.getString("national_id_number");
/*      */           
/*  573 */           objlr.wardId = resultSet.getInt("ward_id");
/*      */           
/*  575 */           objlr.applicant = resultSet.getString("username");
/*  576 */           objlr.appliedOn = resultSet.getString("created_on");
/*  577 */           objlr.regNo = resultSet.getString("reg_no");
/*  578 */           objlr.landId = resultSet.getInt("land_id");
/*  579 */           objlr.status = resultSet.getString("invoice_status");
/*  580 */           objlr.paidStatus = resultSet.getString("paid_status");
/*  581 */           objlr.subCountyId = resultSet.getInt("subCounty_id");
/*  582 */           objlr.approvedBy = resultSet.getInt("approved_by");
/*  583 */           objlr.approvedOn = resultSet.getString("approved_On");
/*  584 */           objlr.rejectReason = resultSet.getString("rejected_reason");
/*  585 */           objlr.fee = resultSet.getDouble("fee");
/*  586 */           objlr.penalty = resultSet.getDouble("penalty");
/*  587 */           objlr.paidDate = resultSet.getString("paid_date");
/*  588 */           objlr.permitTypeId = resultSet.getInt("land_type_id");
/*  589 */           objlr.appliedFor = resultSet.getInt("applied_for");
/*  590 */           objlr.subCountyName = resultSet.getString("name");
/*  591 */           objlr.wardName = resultSet.getString("ward_name");
/*  592 */           objlr.preLr = resultSet.getString("previous_lr");
/*  593 */           objlr.landTypeName = resultSet.getString("permit_type_name");
/*      */           
/*  595 */           if (objlr.approvedBy > 0) {
/*  596 */             preparedStatement1 = connection.prepareStatement(Queryconstants.getLandApprovedUser);
/*      */             
/*  598 */             preparedStatement1.setInt(1, objlr.approvedBy);
/*  599 */             resultSet2 = preparedStatement1.executeQuery();
/*  600 */             if (resultSet2.next()) {
/*  601 */               objlr.approvedUser = resultSet2.getString("username");
/*      */             }
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  607 */           if (resultSet.getInt("receipt_by") > 0) {
/*  608 */             preparedStatement1 = connection.prepareStatement(Queryconstants.getPaidLandUser);
/*      */             
/*  610 */             preparedStatement1.setInt(1, resultSet.getInt("receipt_by"));
/*      */             
/*  612 */             resultSet2 = preparedStatement1.executeQuery();
/*  613 */             if (resultSet2.next()) {
/*  614 */               objlr.paidUser = resultSet2.getString("username");
/*      */             }
/*      */           } 
/*      */           
/*  618 */           objlr.count = count;
/*  619 */           lr.add(objlr);
/*  620 */           count++;
/*      */         } 
/*      */       } 
/*  623 */       if (linkId == 4) {
/*  624 */         preparedStatement = connection.prepareStatement(Queryconstants.getLandInvoicesByIndividual);
/*      */         
/*  626 */         preparedStatement.setString(1, nationalIdNo);
/*  627 */         resultSet = preparedStatement.executeQuery();
/*      */         
/*  629 */         while (resultSet.next()) {
/*  630 */           LandRate objlr = new LandRate();
/*  631 */           objlr.id = resultSet.getInt("id");
/*  632 */           objlr.landNo = resultSet.getString("land_no");
/*  633 */           objlr.plotNumber = resultSet.getString("plot_number");
/*  634 */           objlr.mapSheetNumber = resultSet.getString("mapsheet_number");
/*      */           
/*  636 */           objlr.location = resultSet.getString("location");
/*  637 */           objlr.acreage = resultSet.getString("acreage");
/*  638 */           objlr.titleDeedNumber = resultSet.getString("title_deed_number");
/*      */           
/*  640 */           objlr.name = resultSet.getString("name");
/*  641 */           objlr.krapin = resultSet.getString("kra_pin");
/*  642 */           objlr.nationalIdNumber = resultSet.getString("national_id_number");
/*      */           
/*  644 */           objlr.wardId = resultSet.getInt("ward_id");
/*      */           
/*  646 */           objlr.applicant = resultSet.getString("username");
/*  647 */           objlr.appliedOn = resultSet.getString("created_on");
/*  648 */           objlr.regNo = resultSet.getString("reg_no");
/*  649 */           objlr.landId = resultSet.getInt("land_id");
/*  650 */           objlr.status = resultSet.getString("invoice_status");
/*  651 */           objlr.paidStatus = resultSet.getString("paid_status");
/*  652 */           objlr.subCountyId = resultSet.getInt("subCounty_id");
/*  653 */           objlr.approvedBy = resultSet.getInt("approved_by");
/*  654 */           objlr.approvedOn = resultSet.getString("approved_On");
/*  655 */           objlr.rejectReason = resultSet.getString("rejected_reason");
/*  656 */           objlr.fee = resultSet.getDouble("fee");
/*  657 */           objlr.penalty = resultSet.getDouble("penalty");
/*  658 */           objlr.paidDate = resultSet.getString("paid_date");
/*  659 */           objlr.permitTypeId = resultSet.getInt("land_type_id");
/*  660 */           objlr.appliedFor = resultSet.getInt("applied_for");
/*  661 */           objlr.subCountyName = resultSet.getString("name");
/*  662 */           objlr.wardName = resultSet.getString("ward_name");
/*  663 */           objlr.preLr = resultSet.getString("previous_lr");
/*  664 */           objlr.landTypeName = resultSet.getString("permit_type_name");
/*      */           
/*  666 */           if (objlr.approvedBy > 0) {
/*  667 */             preparedStatement1 = connection.prepareStatement(Queryconstants.getLandApprovedUser);
/*      */             
/*  669 */             preparedStatement1.setInt(1, objlr.approvedBy);
/*  670 */             resultSet2 = preparedStatement1.executeQuery();
/*  671 */             if (resultSet2.next()) {
/*  672 */               objlr.approvedUser = resultSet2.getString("username");
/*      */             }
/*      */           } 
/*      */ 
/*      */ 
/*      */           
/*  678 */           if (resultSet.getInt("receipt_by") > 0) {
/*  679 */             preparedStatement1 = connection.prepareStatement(Queryconstants.getPaidLandUser);
/*      */             
/*  681 */             preparedStatement1.setInt(1, resultSet.getInt("receipt_by"));
/*      */             
/*  683 */             resultSet2 = preparedStatement1.executeQuery();
/*  684 */             if (resultSet2.next()) {
/*  685 */               objlr.paidUser = resultSet2.getString("username");
/*      */             }
/*      */           } 
/*      */           
/*  689 */           objlr.count = count;
/*  690 */           lr.add(objlr);
/*  691 */           count++;
/*      */         } 
/*      */       } 
/*  694 */       return lr;
/*  695 */     } catch (Exception ex) {
/*  696 */       ex.printStackTrace();
/*  697 */       return null;
/*      */     } finally {
/*  699 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ErevenueResponse UpdateLandInvoiceStatus(LandRate lr) {
/*  711 */     Connection connection = null;
/*  712 */     PreparedStatement preparedStatement = null;
/*  713 */     PreparedStatement preparedStatement1 = null;
/*  714 */     ResultSet resultSet = null;
/*      */     try {
/*  716 */       connection = this.dataSource.getConnection();
/*  717 */       connection.setAutoCommit(false);
/*  718 */       if (lr.status.equalsIgnoreCase("A")) {
/*  719 */         preparedStatement = connection.prepareStatement(Queryconstants.updateLandApprovedStatus);
/*      */         
/*  721 */         preparedStatement.setString(1, lr.status);
/*  722 */         preparedStatement.setString(2, lr.createdBy);
/*  723 */         preparedStatement.setTimestamp(3, new Timestamp((new Date()).getTime()));
/*      */ 
/*      */         
/*  726 */         preparedStatement.setInt(4, lr.id);
/*  727 */       } else if (lr.status.equalsIgnoreCase("P")) {
/*  728 */         preparedStatement = connection.prepareStatement(Queryconstants.updateLandPaidStatus);
/*      */         
/*  730 */         preparedStatement.setString(1, lr.status);
/*  731 */         preparedStatement.setString(2, lr.mpesaCode);
/*  732 */         preparedStatement.setBoolean(3, true);
/*  733 */         preparedStatement.setTimestamp(4, new Timestamp((new Date()).getTime()));
/*      */         
/*  735 */         preparedStatement.setString(5, lr.createdBy);
/*  736 */         preparedStatement.setTimestamp(6, new Timestamp((new Date()).getTime()));
/*      */         
/*  738 */         preparedStatement.setString(7, lr.bankName);
/*  739 */         preparedStatement.setString(8, lr.accNo);
/*  740 */         preparedStatement.setString(9, lr.transNo);
                   preparedStatement.setDouble(10, lr.amount);
/*  741 */         preparedStatement.setInt(11, lr.id);
/*  742 */         if (preparedStatement.executeUpdate() > 0)
/*      */         {
/*  744 */           Calendar calendarEnd = Calendar.getInstance();
/*  745 */           int year = calendarEnd.get(1);
/*  746 */           calendarEnd.set(1, year);
/*  747 */           calendarEnd.set(2, 11);
/*  748 */           calendarEnd.set(5, 31);
/*      */           
/*  750 */           Date endDate = calendarEnd.getTime();
/*  751 */           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*      */           
/*  753 */           DBOperations.DisposeSql(preparedStatement);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  760 */           if (lr.landType.equalsIgnoreCase("Renew")) {
/*  761 */             preparedStatement = connection.prepareStatement(Queryconstants.updatelandPermit);
/*      */             
/*  763 */             preparedStatement.setInt(1, lr.appliedFor);
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  768 */             preparedStatement.setBoolean(2, true);
/*  769 */             preparedStatement.setString(3, lr.createdBy);
/*  770 */             preparedStatement.setTimestamp(4, new Timestamp((new Date()).getTime()));
/*      */ 
/*      */ 
/*      */             
/*  774 */             preparedStatement.setString(5, lr.preLr);
/*      */           } else {
/*  776 */             preparedStatement = connection.prepareStatement(Queryconstants.insertlandPermit);
/*      */             
/*  778 */             preparedStatement.setString(1, GetPermitNo(lr.landId));
/*  779 */             preparedStatement.setInt(2, lr.landId);
/*  780 */             preparedStatement.setInt(3, lr.id);
/*  781 */             preparedStatement.setInt(4, year);
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  786 */             preparedStatement.setBoolean(5, true);
/*  787 */             preparedStatement.setString(6, lr.createdBy);
/*  788 */             preparedStatement.setTimestamp(7, new Timestamp((new Date()).getTime()));
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*  798 */       else if (lr.status.equalsIgnoreCase("R")) {
/*  799 */         preparedStatement = connection.prepareStatement(Queryconstants.updateLandRejectedStatus);
/*      */         
/*  801 */         preparedStatement.setString(1, lr.status);
/*  802 */         preparedStatement.setString(2, lr.rejectReason);
/*  803 */         preparedStatement.setString(3, lr.createdBy);
/*  804 */         preparedStatement.setTimestamp(4, new Timestamp((new Date()).getTime()));
/*      */         
/*  806 */         preparedStatement.setInt(5, lr.id);
/*  807 */       } else if (lr.status.equalsIgnoreCase("I")) {
/*  808 */         preparedStatement = connection.prepareStatement(Queryconstants.updateLandInspectedStatus);
/*      */         
/*  810 */         preparedStatement.setString(1, lr.status);
/*  811 */         preparedStatement.setString(2, lr.createdBy);
/*  812 */         preparedStatement.setTimestamp(3, new Timestamp((new Date()).getTime()));
/*      */         
/*  814 */         preparedStatement.setInt(4, lr.id);
/*  815 */       } else if (lr.status.equalsIgnoreCase("RU")) {
/*  816 */         preparedStatement = connection.prepareStatement(Queryconstants.updateLandReferUser);
/*      */         
/*  818 */         preparedStatement.setString(1, lr.status);
/*  819 */         preparedStatement.setString(2, lr.createdBy);
/*  820 */         preparedStatement.setTimestamp(3, new Timestamp((new Date()).getTime()));
/*      */         
/*  822 */         preparedStatement.setInt(4, lr.id);
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  829 */       if (preparedStatement.executeUpdate() > 0) {
/*  830 */         connection.commit();
/*  831 */         return new ErevenueResponse(200, "Records Updated");
/*      */       } 
/*  833 */       connection.rollback();
/*  834 */       return new ErevenueResponse(201, "Nothing To Update");
/*      */     }
/*  836 */     catch (SQLException sqlEx) {
/*  837 */       sqlEx.printStackTrace();
/*      */       try {
/*  839 */         connection.rollback();
/*  840 */       } catch (SQLException e) {
/*      */         
/*  842 */         e.printStackTrace();
/*      */       } 
/*  844 */       return new ErevenueResponse(202, "Exception Occured");
/*      */     }
/*  846 */     catch (Exception ex) {
/*      */       
/*  848 */       ex.printStackTrace();
/*      */       try {
/*  850 */         connection.rollback();
/*  851 */       } catch (SQLException e) {
/*      */         
/*  853 */         e.printStackTrace();
/*      */       } 
/*  855 */       return new ErevenueResponse(202, "Exception Occured");
/*      */     } finally {
/*  857 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String GetPermitNo(int landId) {
/*  868 */     Connection connection = null;
/*  869 */     PreparedStatement preparedStatement = null;
/*  870 */     ResultSet resultSet = null;
/*  871 */     String permitNo = "";
/*      */     try {
/*  873 */       DateFormat dateFormat = new SimpleDateFormat("yyyy");
/*  874 */       Date date = new Date();
/*  875 */       System.out.println(dateFormat.format(date));
/*  876 */       connection = this.dataSource.getConnection();
/*  877 */       System.out.println("landpermitno###" + dateFormat.format(date) + landId);
/*      */       
/*  879 */       permitNo = dateFormat.format(date) + String.format("%06d", new Object[] { Integer.valueOf(landId) });
/*      */       
/*  881 */       return permitNo;
/*  882 */     } catch (Exception ex) {
/*  883 */       ex.printStackTrace();
/*  884 */       return permitNo = "";
/*      */     } finally {
/*  886 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ErevenueResponse UpdateLandPermitRenewal(LandRate lr) {
/*  896 */     Connection connection = null;
/*  897 */     PreparedStatement preparedStatement = null;
/*  898 */     ResultSet resultSet = null;
/*  899 */     String penaltyPer = "";
/*  900 */     String penaltyMonth = "";
/*  901 */     BufferedImage image = null;
/*  902 */     ResultSet rs = null;
/*  903 */     int id = 0;
/*  904 */     int regId = 0;
/*      */     try {
/*  906 */       connection = this.dataSource.getConnection();
/*  907 */       preparedStatement = connection.prepareStatement(Queryconstants.getConfigValue);
/*      */       
/*  909 */       preparedStatement.setString(1, "004");
/*      */       
/*  911 */       resultSet = preparedStatement.executeQuery();
/*      */       
/*  913 */       while (resultSet.next()) {
/*  914 */         penaltyPer = resultSet.getString("value");
/*      */       }
/*      */       
/*  917 */       DBOperations.DisposeSql(preparedStatement, resultSet);
/*  918 */       preparedStatement = connection.prepareStatement(Queryconstants.getConfigValue);
/*      */       
/*  920 */       preparedStatement.setString(1, "005");
/*  921 */       resultSet = preparedStatement.executeQuery();
/*      */       
/*  923 */       while (resultSet.next()) {
/*  924 */         penaltyMonth = resultSet.getString("value");
/*      */       }
/*      */       
/*  927 */       Date date = (new SimpleDateFormat("MMMM")).parse(penaltyMonth);
/*  928 */       Calendar cal = Calendar.getInstance();
/*  929 */       cal.setTime(date);
/*  930 */       int deadlineMonth = cal.get(2) + 1;
/*  931 */       System.out.println("Penalty Month##" + deadlineMonth);
/*      */       
/*  933 */       Date date1 = new Date();
/*  934 */       Calendar cal1 = Calendar.getInstance();
/*  935 */       cal.setTime(date1);
/*  936 */       int currentmonth = cal1.get(2) + 1;
/*  937 */       System.out.println("current month##" + currentmonth);
/*      */       
/*  939 */       int noOfMonths = currentmonth - deadlineMonth;
/*  940 */       BigDecimal d = (new BigDecimal(penaltyPer.trim().replace("%", ""))).divide(BigDecimal.valueOf(100L));
/*      */ 
/*      */       
/*  943 */       System.out.println("amount##" + lr.fee);
/*  944 */       System.out.println("rate##" + d.doubleValue());
/*  945 */       System.out.println("no of month##" + noOfMonths);
/*      */       
/*  947 */       double penalty = calculate(lr.fee, d.doubleValue(), noOfMonths);
/*  948 */       System.out.println("penalty##" + penalty);
/*  949 */       if (ValidateRenewLandId(lr.id)) {
/*  950 */         return new ErevenueResponse(201, "Landrates Application is already in renewal state");
/*      */       }
/*      */ 
/*      */       
/*  954 */       Calendar year2 = Calendar.getInstance();
/*  955 */       int year = year2.get(1);
/*  956 */       preparedStatement = connection.prepareStatement(Queryconstants.insertlandregistrationDtl, 1);
/*      */ 
/*      */       
/*  959 */       preparedStatement.setInt(1, lr.id);
/*  960 */       preparedStatement.setDouble(2, lr.fee);
/*  961 */       preparedStatement.setDouble(3, penalty);
/*  962 */       preparedStatement.setString(4, "Renew");
/*  963 */       preparedStatement.setString(5, "RW");
/*  964 */       preparedStatement.setString(6, lr.createdBy);
/*  965 */       preparedStatement.setTimestamp(7, new Timestamp((new Date()).getTime()));
/*      */       
/*  967 */       preparedStatement.setString(8, lr.permitNo);
/*  968 */       preparedStatement.setInt(9, lr.appliedFor);
/*      */       
/*  970 */       if (preparedStatement.executeUpdate() > 0) {
/*  971 */         if (lr.regId == 0) {
/*  972 */           rs = preparedStatement.getGeneratedKeys();
/*  973 */           rs.next();
/*  974 */           regId = rs.getInt(1);
/*      */         } 
/*  976 */         DBOperations.DisposeSql(preparedStatement);
/*  977 */         preparedStatement = connection.prepareStatement(Queryconstants.updateRegNo);
/*      */         
/*  979 */         preparedStatement.setString(1, GetRegNo(regId));
/*  980 */         preparedStatement.setInt(2, regId);
/*  981 */         if (preparedStatement.executeUpdate() > 0) {
/*  982 */           return new ErevenueResponse(200, "Records Updated");
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/*  987 */       return new ErevenueResponse(200, "Records Updated");
/*  988 */     } catch (SQLException sqlEx) {
/*  989 */       sqlEx.printStackTrace();
/*  990 */       return new ErevenueResponse(202, "Exception Occured");
/*      */     }
/*  992 */     catch (Exception ex) {
/*  993 */       ex.printStackTrace();
/*  994 */       return new ErevenueResponse(202, "Exception Occured");
/*      */     } finally {
/*  996 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean ValidateRenewLandId(int land_id) {
/* 1007 */     Connection connection = null;
/* 1008 */     PreparedStatement preparedStatement = null;
/* 1009 */     ResultSet resultSet = null;
/*      */     try {
/* 1011 */       connection = this.dataSource.getConnection();
/* 1012 */       preparedStatement = connection.prepareStatement(Queryconstants.validateRenewLandId);
/*      */       
/* 1014 */       preparedStatement.setInt(1, land_id);
/*      */       
/* 1016 */       resultSet = preparedStatement.executeQuery();
/*      */       
/* 1018 */       if (resultSet.next()) {
/* 1019 */         return true;
/*      */       }
/* 1021 */       return false;
/*      */     }
/* 1023 */     catch (Exception ex) {
/* 1024 */       ex.printStackTrace();
/* 1025 */       return false;
/*      */     } finally {
/* 1027 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double calculate(double p, double r, int n) {
/* 1037 */     double amount = p * Math.pow(1.0D + r, n);
/*      */     
/* 1039 */     double interest = amount - p;
/*      */     
/* 1041 */     System.out.println("Compond Interest is " + interest);
/*      */     
/* 1043 */     return interest;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<LandRate> GetPaidLandDetails() {
/* 1051 */     Connection connection = null;
/* 1052 */     PreparedStatement preparedStatement = null;
/*      */     
/* 1054 */     ResultSet resultSet = null;
/*      */     
/* 1056 */     int count = 1;
/*      */     
/*      */     try {
/* 1059 */       connection = this.dataSource.getConnection();
/* 1060 */       preparedStatement = connection.prepareStatement(Queryconstants.getLandDetails);
/*      */       
/* 1062 */       preparedStatement.setString(1, "P");
/* 1063 */       resultSet = preparedStatement.executeQuery();
/* 1064 */       List<LandRate> lr = new ArrayList<>();
/* 1065 */       while (resultSet.next()) {
/* 1066 */         LandRate objlr = new LandRate();
/* 1067 */         objlr.plotNumber = resultSet.getString("plot_number");
/* 1068 */         objlr.titleDeedNumber = resultSet.getString("title_deed_number");
/*      */         
/* 1070 */         objlr.validity = resultSet.getInt("validity");
/*      */         
/* 1072 */         objlr.acreage = resultSet.getString("acreage");
/* 1073 */         objlr.landNo = resultSet.getString("land_no");
/* 1074 */         objlr.fee = resultSet.getDouble("fee");
/* 1075 */         objlr.name = resultSet.getString("name");
/* 1076 */         objlr.regNo = resultSet.getString("reg_no");
/* 1077 */         objlr.status = resultSet.getString("status");
/* 1078 */         objlr.permitUser = resultSet.getString("username");
/* 1079 */         objlr.permitTypeId = resultSet.getInt("land_type_id");
/* 1080 */         objlr.appliedFor = resultSet.getInt("applied_for");
/*      */         
/* 1082 */         objlr.count = count;
/* 1083 */         objlr.id = resultSet.getInt("land_id");
/* 1084 */         SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
/*      */         
/* 1086 */         Calendar year1 = Calendar.getInstance();
/* 1087 */         int year = year1.get(1);
/* 1088 */         year1.set(1, year + 1);
/* 1089 */         year1.set(2, 11);
/* 1090 */         year1.set(5, 31);
/* 1091 */         Date endDate = year1.getTime();
/*      */         
/* 1093 */         System.out.println(sdf.format(Long.valueOf(endDate.getTime())));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1105 */         ArrayList<PermitYear> yearList = new ArrayList<>();
/* 1106 */         yearList.add(new PermitYear(sdf.format(Long.valueOf(endDate.getTime()))));
/*      */ 
/*      */         
/* 1109 */         objlr.yearList = yearList;
/* 1110 */         lr.add(objlr);
/* 1111 */         count++;
/*      */       } 
/*      */       
/* 1114 */       return lr;
/* 1115 */     } catch (Exception ex) {
/* 1116 */       ex.printStackTrace();
/* 1117 */       return null;
/*      */     } finally {
/* 1119 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<LandRate> GetAllLandPermits() {
/* 1128 */     Connection connection = null;
/* 1129 */     PreparedStatement preparedStatement = null;
/*      */     
/* 1131 */     ResultSet resultSet = null;
/*      */     
/* 1133 */     int count = 1;
/*      */     
/*      */     try {
/* 1136 */       connection = this.dataSource.getConnection();
/* 1137 */       preparedStatement = connection.prepareStatement(Queryconstants.getLandPermitDetails);
/*      */ 
/*      */       
/* 1140 */       resultSet = preparedStatement.executeQuery();
/* 1141 */       List<LandRate> lr = new ArrayList<>();
/*      */       
/* 1143 */       while (resultSet.next()) {
/* 1144 */         LandRate objlr = new LandRate();
/*      */         
/* 1146 */         objlr.permitNo = resultSet.getString("land_permit_no");
/* 1147 */         objlr.landNo = resultSet.getString("land_no");
/* 1148 */         objlr.validity = resultSet.getInt("validity");
/* 1149 */         objlr.fee = resultSet.getDouble("fee");
/*      */         
/* 1151 */         objlr.plotNumber = resultSet.getString("plot_number");
/* 1152 */         objlr.mapSheetNumber = resultSet.getString("mapsheet_number");
/* 1153 */         objlr.location = resultSet.getString("location");
/* 1154 */         objlr.acreage = resultSet.getString("acreage");
/* 1155 */         objlr.titleDeedNumber = resultSet.getString("title_deed_number");
/*      */         
/* 1157 */         objlr.name = resultSet.getString("name");
/* 1158 */         objlr.regNo = resultSet.getString("reg_no");
/* 1159 */         objlr.krapin = resultSet.getString("kra_pin");
/* 1160 */         objlr.permitUser = resultSet.getString("username");
/* 1161 */         objlr.status = resultSet.getString("permit_status");
/* 1162 */         objlr.permitTypeId = resultSet.getInt("land_type_id");
/* 1163 */         objlr.landType = resultSet.getString("land_type");
/* 1164 */         objlr.permitStatus = resultSet.getString("newStatus");
/* 1165 */         objlr.count = count;
/* 1166 */         objlr.id = resultSet.getInt("land_id");
/*      */         
/* 1168 */         SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
/*      */         
/* 1170 */         Calendar year1 = Calendar.getInstance();
/* 1171 */         int year = year1.get(1);
/* 1172 */         year1.set(1, year + 1);
/* 1173 */         year1.set(2, 11);
/* 1174 */         year1.set(5, 31);
/* 1175 */         Date endDate = year1.getTime();
/*      */         
/* 1177 */         System.out.println(sdf.format(Long.valueOf(endDate.getTime())));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/* 1189 */         ArrayList<PermitYear> yearList = new ArrayList<>();
/* 1190 */         yearList.add(new PermitYear(sdf.format(Long.valueOf(endDate.getTime()))));
/*      */ 
/*      */         
/* 1193 */         objlr.yearList = yearList;
/* 1194 */         lr.add(objlr);
/* 1195 */         count++;
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1204 */       return lr;
/* 1205 */     } catch (Exception ex) {
/* 1206 */       ex.printStackTrace();
/* 1207 */       return null;
/*      */     } finally {
/* 1209 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<LandRate> GetAllLandsByLinkId(int linkId, String nationalIdNo, int agentId) {
/* 1227 */     Connection connection = null;
/* 1228 */     PreparedStatement preparedStatement = null;
/* 1229 */     ResultSet resultSet = null;
/* 1230 */     int count = 1;
/*      */     try {
/* 1232 */       connection = this.dataSource.getConnection();
/* 1233 */       List<LandRate> lrs = new ArrayList<>();
/* 1234 */       if (linkId == 3) {
/* 1235 */         preparedStatement = connection.prepareStatement(Queryconstants.getAllLandByAgentId);
/*      */         
/* 1237 */         preparedStatement.setInt(1, agentId);
/* 1238 */         resultSet = preparedStatement.executeQuery();
/*      */         
/* 1240 */         while (resultSet.next()) {
/* 1241 */           lrs.add(new LandRate(resultSet.getInt("id"), resultSet.getString("plot_number"), resultSet.getString("mapsheet_number"), resultSet.getString("location"), resultSet.getString("acreage"), resultSet.getString("title_deed_number"), resultSet.getInt("land_type_id"), resultSet.getString("name"), resultSet.getString("kra_pin"), resultSet.getString("national_id_number"), resultSet.getInt("ward_id"), resultSet.getInt("subCounty_id"), 200, count, resultSet.getString("invoice_status"), resultSet.getString("created_by"),resultSet.getString("address"),resultSet.getString("code")));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1254 */           count++;
/*      */         } 
/*      */       } 
/*      */       
/* 1258 */       if (linkId == 4) {
/* 1259 */         preparedStatement = connection.prepareStatement(Queryconstants.getAllLandsForIndividual);
/*      */         
/* 1261 */         preparedStatement.setString(1, nationalIdNo);
/* 1262 */         resultSet = preparedStatement.executeQuery();
/*      */         
/* 1264 */         while (resultSet.next()) {
/* 1265 */           lrs.add(new LandRate(resultSet.getInt("id"), resultSet.getString("plot_number"), resultSet.getString("mapsheet_number"), resultSet.getString("location"), resultSet.getString("acreage"), resultSet.getString("title_deed_number"), resultSet.getInt("land_type_id"), resultSet.getString("name"), resultSet.getString("kra_pin"), resultSet.getString("national_id_number"), resultSet.getInt("ward_id"), resultSet.getInt("subCounty_id"), 200, count, resultSet.getString("invoice_status"), resultSet.getString("created_by"),resultSet.getString("address"),resultSet.getString("code")));
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1278 */           count++;
/*      */         } 
/*      */       } 
/*      */       
/* 1282 */       return lrs;
/* 1283 */     } catch (Exception ex) {
/* 1284 */       ex.printStackTrace();
/* 1285 */       return null;
/*      */     } finally {
/* 1287 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }







public CompasResponse UploadPlot(String filePath, String fileName, String uploadedBy, int branchId) {
	// TODO Auto-generated method stub
	List<LandRate> list = new ArrayList<LandRate>();
	List<LandRate> usrUpload = new ArrayList<LandRate>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	try {
		connection = dataSource.getConnection();
		if (checkifFileExists(fileName)) {
			return new CompasResponse(204, "File Already Uploaded!!");
		}
		preparedStatement = connection.prepareStatement(Queryconstants.insertfileDtl);
		preparedStatement.setString(1, fileName);
		preparedStatement.setString(2, uploadedBy);
		preparedStatement.setTimestamp(3, new Timestamp(new Date().getTime()));

		if (preparedStatement.executeUpdate() > 0) {
			list = getAccountsExcel(filePath, uploadedBy, branchId);
		}
			for (LandRate detail : list) {
				usrUpload.add(detail);
			}
			
			if (usrUpload.size() > 0) {
				for (LandRate detail : usrUpload) {
		
				  if (UpdateImportUser(detail).respCode != 200) {
				  System.out.println(UpdateImportLand(detail).respCode + "detail==============");
				 // System.out.println(UpdateImportLand(detail).respCode+ "UpdateImportLand(detail).respCode");
				  
				  return new CompasResponse(207, "Plot number"+detail.plotNumber+" already exist");
			
				 
				 }
			
				  
				
				 
				
				}
		
				}
			return new CompasResponse(200, "Uploaded Successfully  ");
	} catch (Exception ex) {
		return new CompasResponse(201, "Server Error occurred, Please try again");
	} finally {
		DBOperations.DisposeSql(connection, preparedStatement, resultSet);
	}
}

private ArrayList<LandRate> getAccountsExcel(String pathToFile, String createdBy, int branchId) throws IOException {
	ArrayList<LandRate> userObjs = new ArrayList<LandRate>();

	try {

		//logger.info("FileNameInUploadExcel##" + pathToFile);
		FileInputStream file = new FileInputStream(new File(pathToFile));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		workbook.getNumberOfSheets();
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		
		DataFormatter df = new DataFormatter();
		for (int r = 1; r < rows; r++) {
			
			XSSFRow row = sheet.getRow(r);
			XSSFCell cell;
			LandRate userr = new LandRate();
			
			userr.name = df.formatCellValue(row.getCell(0));
			userr.nationalIdNumber = df.formatCellValue(row.getCell(1));
			userr.krapin = df.formatCellValue(row.getCell(2));
			//userr.subCountyName = df.formatCellValue(row.getCell(3));
			//userr.location = df.formatCellValue(row.getCell(4));
			userr.plotNumber = df.formatCellValue(row.getCell(3));
			//userr.mapSheetNumber = df.formatCellValue(row.getCell(2));
			
			
			
			
			userr.address =  df.formatCellValue(row.getCell(4));
			userr.phone =  df.formatCellValue(row.getCell(5));
			userr.rates = df.formatCellValue(row.getCell(6));
			//userr.acreage = df.formatCellValue(row.getCell(10));
			userr.titleDeedNumber = df.formatCellValue(row.getCell(7));
			userr.arrears = df.formatCellValue(row.getCell(8));
			userr.total = df.formatCellValue(row.getCell(9));
			userr.createdBy=createdBy;
			userr.subCountyId=branchId;
			userObjs.add(userr);

		}
		
		file.close();

	} catch (Exception e) {
		e.printStackTrace();
	}
	return userObjs;
}

public CompasResponse UpdateImportUser(LandRate user) {


	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	//String userNameFullName = user.firstName + " " + user.surname + " " + user.otherName;
	String usernamee = "";
	//String password = randomPassword();
	try {
		connection = dataSource.getConnection();

	
	  if (checkPlotNumber(user.plotNumber)) { return new CompasResponse(207,
	  "Plot Number already exists"); }
	 
	/*
	 * if (checkTitleDeedNumber(user.titleDeedNumber)) { return new
	 * CompasResponse(201, "title deed number already exists"); }
	 */
		preparedStatement = connection.prepareStatement(Queryconstants.insertUploadedPlot,Statement.RETURN_GENERATED_KEYS);
			/*
			 * preparedStatement.setString(1, user.subCountyName);
			 * 
			 * preparedStatement.setString(2, user.location);
			 */
		//preparedStatement.setString(3, user.mapSheetNumber);
		preparedStatement.setString(1, user.plotNumber );
		preparedStatement.setString(2, user.name);
		preparedStatement.setString(3, user.nationalIdNumber );
		preparedStatement.setString(4, user.krapin );
		preparedStatement.setString(5, user.address );
		preparedStatement.setString(6, user.phone);
		preparedStatement.setString(7, user.rates);
		//preparedStatement.setString(11, user.acreage);
		preparedStatement.setString(8, user.titleDeedNumber);
		preparedStatement.setString(9, user.arrears);
		preparedStatement.setString(10, user.total);
		preparedStatement.setString(11, "2");
		preparedStatement.setTimestamp(12, new Timestamp(new Date().getTime()));
		preparedStatement.setString(13, user.createdBy);
		preparedStatement.setInt(14, user.subCountyId);

	  if(preparedStatement.executeUpdate()>0) {

		ResultSet rs;
		int id=1;
		
		 if (user.id == 0) { rs = preparedStatement.getGeneratedKeys(); rs.next(); id
		 = rs.getInt(1); }
		 

	
		 //DBOperations.DisposeSql(preparedStatement); 
		 preparedStatement = connection
		  .prepareStatement(Queryconstants.updateLandNo);
		 System.out.println(user.titleDeedNumber +"user.titleDeedNumber");
		 preparedStatement.setString(1, user.titleDeedNumber);
		preparedStatement.setInt(2, id);
		 
		// @year form current date
		Calendar year2 = Calendar.getInstance();
		int year = year2.get(Calendar.YEAR);
		if (preparedStatement.executeUpdate() > 0) {
			PreparedStatement preparedStatement1 = connection.prepareStatement(
					Queryconstants.insertlandregistrationDtlUpload,
					Statement.RETURN_GENERATED_KEYS);
			preparedStatement1.setInt(1, id);

			preparedStatement1.setDouble(2, GetLandFee("S"));

			preparedStatement1.setDouble(3, 0.00);
			preparedStatement1.setString(4, "New");
			preparedStatement1.setString(5, "N");
			preparedStatement1.setString(6, user.createdBy);
			preparedStatement1.setTimestamp(
					7,
					new java.sql.Timestamp(new java.util.Date()
							.getTime()));
			preparedStatement1.setString(8, "");
			preparedStatement1.setInt(9, year);
		
				//preparedStatement1.setDouble(10, GetLandFee(user.permitTypeId));
	
			
			preparedStatement1.setString(10,user.arrears);
			
			preparedStatement1.setString(11,user.total);
			if (user.arrears.equals("0")) {
				preparedStatement1.setInt(12,1);
				preparedStatement1.setString(13,user.createdBy);
			}else {
				preparedStatement1.setInt(12,0);
				preparedStatement1.setString(13,null);
			}
			//preparedStatement1.setDouble(11,0);

			/*
			 * if(lr.permitTypeId==15){ double balance=(lr.fee *
			 * lr.acreage)-lr.amount;
			 * preparedStatement1.setDouble(10, balance-lr.balance);
			 * preparedStatement1.setDouble(11, (lr.amount)); }else{
			 * double balance=(lr.fee)-lr.amount;
			 * preparedStatement1.setDouble(10, balance-lr.balance);
			 * preparedStatement1.setDouble(11, (lr.amount)); }
			 */
			int regId=0;
			if (preparedStatement1.executeUpdate() > 0) {
				
				if (user.regId == 0) {
					rs = preparedStatement1.getGeneratedKeys();
					rs.next();
					regId = rs.getInt(1);
				}
				DBOperations.DisposeSql(preparedStatement1);
				preparedStatement1 = connection
						.prepareStatement(Queryconstants.updateRegNo);
				preparedStatement1.setString(1, GetRegNo(regId));
				preparedStatement1.setInt(2, regId);
				if (preparedStatement1.executeUpdate() > 0) {
					return new CompasResponse(200,
							"Records Updated");
				} 
					return new CompasResponse(201,
							"fail");
				
			}
			
		}
	
	}
	return new CompasResponse(200,
			"Records Updated");
	} catch (Exception ex) {
		ex.printStackTrace();

		return new CompasResponse(404, "Exception Occured");
	} finally {
		DBOperations.DisposeSql(connection, preparedStatement, resultSet);
	}
	
	
}
public boolean checkifFileExists(String fileName) {
	String sql = "Select id from file_import where file_name=? ";
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	try {

		connection = dataSource.getConnection();
		preparedStatement = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
				ResultSet.CONCUR_READ_ONLY);
		preparedStatement.setString(1, fileName);
		resultSet = preparedStatement.executeQuery();

		if (resultSet.next()) {
			return true;
		} else {
			return false;
		}
	} catch (Exception ex) {
		ex.printStackTrace();
		return false;
	} finally {
		DBOperations.DisposeSql(connection, preparedStatement, resultSet);
	}

}

public CompasResponse UploadLand(String filePath, String fileName, String uploadedBy, int branchId) {
	// TODO Auto-generated method stub
	ArrayList<LandRate> list = new ArrayList<LandRate>();
	ArrayList<LandRate> usrUpload = new ArrayList<LandRate>();
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	ResultSet resultSet = null;
	
	System.out.println(branchId +"branch id");
	try {
		connection = dataSource.getConnection();
		if (checkifFileExists(fileName)) {
			return new CompasResponse(204, "File Already Uploaded!!");
		}
		System.out.println("============here3=================");
		preparedStatement = connection.prepareStatement(Queryconstants.insertfileDtl);
		preparedStatement.setString(1, fileName);
		preparedStatement.setString(2, uploadedBy);
		preparedStatement.setTimestamp(3, new Timestamp(new Date().getTime()));

		if (preparedStatement.executeUpdate() > 0) {
			list = getAccountsLandExcel(filePath, uploadedBy, branchId);
		}
			for (LandRate detail : list) {
			
				usrUpload.add(detail);
			}
			
			if (usrUpload.size() > 0) {
				//System.out.println(branchId +" subcounty id");
				for (LandRate detail : usrUpload) {
		
				  if (UpdateImportLand(detail).respCode != 200) {
				 // System.out.println(UpdateImportLand(detail).respCode + "detail==============");
				 // System.out.println(UpdateImportLand(detail).respCode+ "UpdateImportLand(detail).respCode");
				  
				  return new CompasResponse(207, "Exception Occured");
			
				 
				 }
			
				  
				
				 
				
				}
		
				}
			return new CompasResponse(200, "Uploaded Successfully  ");

			
			
	} catch (Exception ex) {
		return new CompasResponse(201, "Server Error occurred, Please try again");
	} 
	
	
	
}
private ArrayList<LandRate> getAccountsLandExcel(String pathToFile, String createdBy, int branchId) throws IOException {
	ArrayList<LandRate> userObjs = new ArrayList<LandRate>();

	try {

		//logger.info("FileNameInUploadExcel##" + pathToFile);
		FileInputStream file = new FileInputStream(new File(pathToFile));
		XSSFWorkbook workbook = new XSSFWorkbook(file);
		workbook.getNumberOfSheets();
		XSSFSheet sheet = workbook.getSheetAt(0);
		int rows = sheet.getPhysicalNumberOfRows();
		
		DataFormatter df = new DataFormatter();
		for (int r = 1; r < rows; r++) {
			
			XSSFRow row = sheet.getRow(r);
			XSSFCell cell;
			LandRate userr = new LandRate();
			
			userr.name = df.formatCellValue(row.getCell(0));
			userr.nationalIdNumber = df.formatCellValue(row.getCell(1));
			userr.krapin = df.formatCellValue(row.getCell(2));
			userr.address = df.formatCellValue(row.getCell(3));
			userr.phone = df.formatCellValue(row.getCell(4));
			//userr.blocknumber = df.formatCellValue(row.getCell(5));
			//userr.parcelNumber =  df.formatCellValue(row.getCell(6));
			userr.acreage= df.formatCellValue(row.getCell(5));
			userr.titleDeedNumber = df.formatCellValue(row.getCell(6));
			//userr.titleNature = df.formatCellValue(row.getCell(9));
			//userr.plotNumber = df.formatCellValue(row.getCell(10));
			userr.mapSheetNumber = df.formatCellValue(row.getCell(7));
			userr.rates = df.formatCellValue(row.getCell(8));
			userr.arrears = df.formatCellValue(row.getCell(9));
			userr.total = df.formatCellValue(row.getCell(10));
			userr.createdBy=createdBy;
			userr.subCountyId=branchId;
			
			userObjs.add(userr);

		}
		
		file.close();

	} catch (Exception e) {
		e.printStackTrace();
	}
	return userObjs;
}
public ErevenueResponse UpdateImportLand(LandRate user) {

	
	Connection connection = null;
	PreparedStatement preparedStatement = null;
	PreparedStatement preparedStatement3 = null;
	ResultSet resultSet = null;
	ResultSet rs=null;
	//String userNameFullName = user.firstName + " " + user.surname + " " + user.otherName;
	String usernamee = "";
	//String password = randomPassword();
	try {
		connection = dataSource.getConnection();
		System.out.println("============================1  "+ user.plotNumber);
	
	
	
	
		
	
	  if (checkPlotNumber(user.plotNumber)) { 
		  System.out.println("============================ 2 "+ user.mapSheetNumber);
		  return new ErevenueResponse(207,"Plot number already exists");
		  }
	  System.out.println("============================ 3 "+ user.plotNumber);

		System.out.println("============here4=================");
		preparedStatement = connection.prepareStatement(Queryconstants.insertUploadedLand,Statement.RETURN_GENERATED_KEYS);
		preparedStatement.setString(1, user.name);
		
		preparedStatement.setString(2, user.nationalIdNumber );
		preparedStatement.setString(3, user.krapin );
		preparedStatement.setString(4, user.address);
		preparedStatement.setString(5, user.phone );
		preparedStatement.setString(6, user.blocknumber );
		//preparedStatement.setString(7, user.parcelNumber );
		preparedStatement.setString(7, user.acreage);
		preparedStatement.setString(8, user.titleDeedNumber);
		//preparedStatement.setString(9, user.titleNature);
		preparedStatement.setString(9, user.mapSheetNumber);
		preparedStatement.setString(10, user.mapSheetNumber);
		preparedStatement.setString(11, user.rates);
		preparedStatement.setString(12, user.arrears);
		preparedStatement.setString(13, user.total);
		preparedStatement.setString(14, "2");
		preparedStatement.setTimestamp(15, new Timestamp(new Date().getTime()));


		preparedStatement.setString(16, user.createdBy);
		System.out.println(user.subCountyId +"qwetu");
		preparedStatement.setInt(17, user.subCountyId);

		if(preparedStatement.executeUpdate()>0) {

			
			int id=1;
		
		  if (user.id == 0) { 
			  System.out.println(user.id +" ##############################"); 
		 
		  rs =preparedStatement.getGeneratedKeys(); 
		  rs.next(); id = rs.getInt(1); 
		  }
		 
		  DBOperations.DisposeSql(preparedStatement); 
		  System.out.println(id+" ##################3 @@@@@@@@@"); 
		  preparedStatement = connection.prepareStatement(Queryconstants.updateLandNo);
		 
		 preparedStatement.setString(1, user.titleDeedNumber);
		 preparedStatement.setInt(2, id);
		 
			System.out.println("is it skipping ");
			// @year form current date
			Calendar year2 = Calendar.getInstance();
			int year = year2.get(Calendar.YEAR);
			if (preparedStatement.executeUpdate() > 0) {
				PreparedStatement preparedStatement1 = connection.prepareStatement(
						Queryconstants.insertlandregistrationDtlUpload,
						Statement.RETURN_GENERATED_KEYS);
				preparedStatement1.setInt(1, id);

				preparedStatement1.setDouble(2, GetLandFee("L"));

				preparedStatement1.setDouble(3, 0.00);
				preparedStatement1.setString(4, "New");
				preparedStatement1.setString(5, "N");
				preparedStatement1.setString(6, user.createdBy);
				preparedStatement1.setTimestamp(
						7,
						new java.sql.Timestamp(new java.util.Date()
								.getTime()));
				preparedStatement1.setString(8, "");
				preparedStatement1.setInt(9, year);
			
					//preparedStatement1.setDouble(10, GetLandFee(user.permitTypeId));
		
				
				preparedStatement1.setString(10,user.arrears);
				
				preparedStatement1.setString(11,user.total);
				if (user.arrears.equals("0")) {
					preparedStatement1.setInt(12,1);
					preparedStatement1.setString(13,user.createdBy);
				}else {
					preparedStatement1.setInt(12,0);
					preparedStatement1.setString(13,null);
				}
		
				//preparedStatement1.setDouble(11,0);

				/*
				 * if(lr.permitTypeId==15){ double balance=(lr.fee *
				 * lr.acreage)-lr.amount;
				 * preparedStatement1.setDouble(10, balance-lr.balance);
				 * preparedStatement1.setDouble(11, (lr.amount)); }else{
				 * double balance=(lr.fee)-lr.amount;
				 * preparedStatement1.setDouble(10, balance-lr.balance);
				 * preparedStatement1.setDouble(11, (lr.amount)); }
				 */
				int regId=0;
				if (preparedStatement1.executeUpdate() > 0) {
					
					if (user.regId == 0) {
						rs = preparedStatement1.getGeneratedKeys();
						rs.next();
						regId = rs.getInt(1);
					}
					DBOperations.DisposeSql(preparedStatement1);
					preparedStatement1 = connection
							.prepareStatement(Queryconstants.updateRegNo);
					preparedStatement1.setString(1, GetRegNo(regId));
					preparedStatement1.setInt(2, regId);
					if (preparedStatement1.executeUpdate() > 0) {
						return new ErevenueResponse(200,
								"Records Updated");
					} 
						return new ErevenueResponse(201,
								"fail");
					
				}
				
			}
		}
		return new ErevenueResponse(201,
				"fail");
		 
	} catch (Exception ex) {
		ex.printStackTrace();

		return new ErevenueResponse(404, "Exception Occured");
	} finally {
		DBOperations.DisposeSql(connection, preparedStatement, resultSet);
	}
}
public double GetLandFee(String landId){
    Connection connection   =   null;
    PreparedStatement preparedStatement =   null;
    ResultSet resultSet =   null;
    
    try
    {
        connection  =   dataSource.getConnection();
        preparedStatement   =   connection.prepareStatement(Queryconstants.getLandFee);
        preparedStatement.setString(1, landId);
        resultSet   =   preparedStatement.executeQuery();
       if(resultSet != null)
       {
           resultSet.next();
           
           return resultSet.getDouble("permit_fee");
       }
       else
       {
           return 0;
       }
    }
    catch(SQLException e)
    { 
        return 0;
    }
}

/*      */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\impl\LandRateDalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */