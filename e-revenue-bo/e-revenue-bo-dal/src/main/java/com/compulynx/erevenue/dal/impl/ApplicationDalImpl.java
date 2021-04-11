/*      */ package com.compulynx.erevenue.dal.impl;
/*      */ 
/*      */ import com.compulynx.erevenue.dal.ApplicationDal;
/*      */ import com.compulynx.erevenue.dal.operations.DBOperations;
/*      */ import com.compulynx.erevenue.dal.operations.Queryconstants;
/*      */ import com.compulynx.erevenue.dal.operations.Utility;
/*      */ import com.compulynx.erevenue.models.Application;
import com.compulynx.erevenue.models.CompasProperties;
/*      */ import com.compulynx.erevenue.models.ErevenueResponse;
/*      */ import com.compulynx.erevenue.models.PermitType;
/*      */ import com.compulynx.erevenue.models.PermitYear;
/*      */ import com.compulynx.erevenue.models.Reports;
/*      */ import com.compulynx.erevenue.models.Ward;
import com.mysql.jdbc.util.Base64Decoder;

/*      */ import java.awt.image.BufferedImage;
/*      */ import java.io.ByteArrayInputStream;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.math.BigDecimal;
/*      */ import java.sql.Connection;
/*      */ import java.sql.PreparedStatement;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.SQLException;
/*      */ import java.sql.Timestamp;
/*      */ import java.text.DateFormat;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.ArrayList;
import java.util.Base64;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.List;
/*      */ import javax.imageio.ImageIO;
/*      */ import javax.sql.DataSource;
/*      */ import net.glxn.qrgen.QRCode;
/*      */ import net.glxn.qrgen.image.ImageType;
/*      */ import sun.misc.BASE64Decoder;
/*      */ import sun.misc.BASE64Encoder;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ApplicationDalImpl
/*      */   implements ApplicationDal
/*      */ {
	CompasProperties compasProperties = new CompasProperties();
/*      */   private DataSource dataSource;
/*      */   
/*      */   public ApplicationDalImpl(DataSource dataSource) {
/*   49 */     this.dataSource = dataSource;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ErevenueResponse UpdateApplication(Application app) {
/*   59 */     Connection connection = null;
/*   60 */     PreparedStatement preparedStatement = null;
/*   61 */     PreparedStatement preparedStatement1 = null;
/*   62 */     ResultSet resultSet = null;
/*   63 */     ResultSet rs = null;
/*   64 */     int businessId = 0;
/*   65 */     int appId = 0;
/*      */     try {
/*   67 */       connection = this.dataSource.getConnection();
/*   68 */       if (app.businessId == 0) {
/*      */         
/*   70 */         if (checkBusinessName(app.businessName)) {
/*   71 */           return new ErevenueResponse(201, "Business name already exists");
/*      */         }
/*      */         
/*   74 */         if (app.linkId == 4 && 
/*   75 */           !ValidateNationalId(app.nationalId)) {
/*   76 */           return new ErevenueResponse(201, "National id doesn't match with sign up user's national Id");
/*      */         }
/*      */ 
/*      */ 
/*      */         
/*   81 */         preparedStatement = connection.prepareStatement(Queryconstants.insertApplication, 1);
/*      */ 
/*      */         
/*   84 */         preparedStatement.setString(1, app.businessName);
/*   85 */         preparedStatement.setInt(2, app.noOfEmployees);
/*   86 */         preparedStatement.setDouble(3, app.area);
/*   87 */         preparedStatement.setString(4, app.electricityAccNo);
/*   88 */         preparedStatement.setString(5, app.waterAccNo);
/*   89 */         preparedStatement.setString(6, app.regNo);
/*   90 */         preparedStatement.setInt(7, app.permitTypeId);
/*   91 */         preparedStatement.setString(8, app.businessDesc);
/*   92 */         preparedStatement.setString(9, app.postalAdd);
/*   93 */         preparedStatement.setString(10, app.postalCode);
/*   94 */         preparedStatement.setInt(11, app.wardId);
/*   95 */         preparedStatement.setString(12, app.landZone);
/*   96 */         preparedStatement.setString(13, app.plotNo);
/*   97 */         preparedStatement.setString(14, app.mobileNo);
/*   98 */         preparedStatement.setString(15, app.landLineNo);
/*   99 */         preparedStatement.setString(16, app.fax);
/*  100 */         preparedStatement.setString(17, app.email);
/*  101 */         preparedStatement.setBoolean(18, app.active);
/*  102 */         preparedStatement.setInt(19, app.createdBy);
/*  103 */         preparedStatement.setTimestamp(20, new Timestamp((new Date()).getTime()));
/*      */         
/*  105 */         preparedStatement.setString(21, app.nationalId);
preparedStatement.setInt(22, app.application_fee);
preparedStatement.setInt(23, app.advertisement_fee);
preparedStatement.setInt(24, app.billboard_fee);
preparedStatement.setInt(25, app.tradename_fee);
preparedStatement.setString(26, app.detail_activity);
preparedStatement.setString(27, app.pin_number);
preparedStatement.setString(28, app.applicant);
preparedStatement.setString(29, app.vat_number);


/*  106 */         if (preparedStatement.executeUpdate() > 0) {
/*  107 */           Calendar calendarEnd = Calendar.getInstance();
/*  108 */           int year = calendarEnd.get(1);
/*  109 */           if (app.businessId == 0) {
/*  110 */             rs = preparedStatement.getGeneratedKeys();
/*  111 */             rs.next();
/*  112 */             businessId = rs.getInt(1);
/*      */           } 
/*      */           
/*  115 */           DBOperations.DisposeSql(preparedStatement);
/*  116 */           preparedStatement = connection.prepareStatement(Queryconstants.updateBusinessNo);
/*      */           
/*  118 */           preparedStatement.setString(1, GetBusinessNo(businessId));
/*  119 */           preparedStatement.setInt(2, businessId);
/*  120 */           if (preparedStatement.executeUpdate() > 0) {
/*  121 */             preparedStatement1 = connection.prepareStatement(Queryconstants.insertApplicationDtl, 1);
/*      */ 
/*      */             
/*  124 */             preparedStatement1.setInt(1, businessId);
/*  125 */             preparedStatement1.setDouble(2, app.permitFee);
/*  126 */             preparedStatement1.setDouble(3, 0.0D);
/*  127 */             preparedStatement1.setString(4, "New");
/*  128 */             preparedStatement1.setString(5, app.status);
/*  129 */             preparedStatement1.setInt(6, app.createdBy);
/*  130 */             preparedStatement1.setTimestamp(7, new Timestamp((new Date()).getTime()));
/*      */ 
/*      */ 
/*      */             
/*  134 */             preparedStatement1.setString(8, "");
/*  135 */             preparedStatement1.setInt(9, year);
/*  136 */             if (preparedStatement1.executeUpdate() > 0) {
/*  137 */               if (app.appId == 0) {
/*  138 */                 rs = preparedStatement1.getGeneratedKeys();
/*  139 */                 rs.next();
/*  140 */                 appId = rs.getInt(1);
/*      */               } 
/*  142 */               DBOperations.DisposeSql(preparedStatement1);
/*  143 */               preparedStatement1 = connection.prepareStatement(Queryconstants.updateAppNo);
/*      */               
/*  145 */               preparedStatement1.setString(1, GetApplicationNo(appId));
/*      */               
/*  147 */               preparedStatement1.setInt(2, appId);
/*  148 */               if (preparedStatement1.executeUpdate() > 0) {
/*  149 */                 return new ErevenueResponse(200, "Records Updated");
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  157 */         return new ErevenueResponse(201, "Records Updated");
/*      */       } 
/*      */       
/*  160 */       if (checkBusinessNameById(app.businessName, app.businessId)) {
/*  161 */         return new ErevenueResponse(201, "Business name already exists");
/*      */       }
/*      */       
/*  164 */       if (app.linkId == 4)
/*      */       {
/*  166 */         if (!ValidateNationalId(app.nationalId)) {
/*  167 */           return new ErevenueResponse(201, "National id doesn't match with sign up user's national Id");
/*      */         }
/*      */       }
/*      */       
/*  171 */       preparedStatement = connection.prepareStatement(Queryconstants.updateApplication);
/*      */       
/*  173 */       preparedStatement.setString(1, app.businessName);
/*  174 */       preparedStatement.setInt(2, app.noOfEmployees);
/*  175 */       preparedStatement.setInt(3, app.area);
/*  176 */       preparedStatement.setString(4, app.electricityAccNo);
/*  177 */       preparedStatement.setString(5, app.waterAccNo);
/*  178 */       preparedStatement.setString(6, app.regNo);
/*  179 */       preparedStatement.setInt(7, app.permitTypeId);
/*  180 */       preparedStatement.setString(8, app.businessDesc);
/*  181 */       preparedStatement.setString(9, app.postalAdd);
/*  182 */       preparedStatement.setString(10, app.postalCode);
/*  183 */       preparedStatement.setInt(11, app.wardId);
/*  184 */       preparedStatement.setString(12, app.landZone);
/*  185 */       preparedStatement.setString(13, app.plotNo);
/*  186 */       preparedStatement.setString(14, app.mobileNo);
/*  187 */       preparedStatement.setString(15, app.landLineNo);
/*  188 */       preparedStatement.setString(16, app.fax);
/*  189 */       preparedStatement.setString(17, app.email);
/*  190 */       preparedStatement.setBoolean(18, app.active);
/*  191 */       preparedStatement.setInt(19, app.createdBy);
/*  192 */       preparedStatement.setTimestamp(20, new Timestamp((new Date()).getTime()));
/*      */       
/*  194 */       preparedStatement.setString(21, app.nationalId);
preparedStatement.setInt(22, app.application_fee);
preparedStatement.setInt(23, app.advertisement_fee);
preparedStatement.setInt(24, app.billboard_fee);
preparedStatement.setInt(25, app.tradename_fee);
preparedStatement.setString(26, app.detail_activity);
preparedStatement.setString(27, app.pin_number);
preparedStatement.setString(28, app.fax);
preparedStatement.setString(29, app.applicant);
preparedStatement.setString(30, app.vat_number);
/*  195 */       preparedStatement.setInt(31, app.businessId);
/*      */       
/*  197 */       if (preparedStatement.executeUpdate() > 0) {
/*  198 */        // DBOperations.DisposeSql(preparedStatement);
/*  199 */         if (app.status.equalsIgnoreCase("AM")) {
/*  200 */           preparedStatement = connection.prepareStatement(Queryconstants.updateAmendedStatus);
/*      */           
/*  202 */           preparedStatement.setString(1, app.status);
/*  203 */           preparedStatement.setInt(2, app.createdBy);
/*  204 */           preparedStatement.setTimestamp(3, new Timestamp((new Date()).getTime()));
/*      */ 
/*      */ 
String total_in_words=Utility.convertNumberToWords(app.total);
/*      */           preparedStatement.setString(4, total_in_words);
/*  208 */           preparedStatement.setInt(5, app.businessId);
/*      */         } 
/*      */       } 
/*  211 */       return (preparedStatement.executeUpdate() > 0) ? new ErevenueResponse(200, "Records Updated") : new ErevenueResponse(201, "Nothing To Update");
/*      */ 
/*      */ 
/*      */     
/*      */     }
/*  216 */     catch (SQLException sqlEx) {
/*  217 */       sqlEx.printStackTrace();
/*  218 */       return new ErevenueResponse(202, "Exception Occured");
/*      */     }
/*  220 */     catch (Exception ex) {
/*  221 */       ex.printStackTrace();
/*  222 */       return new ErevenueResponse(202, "Exception Occured");
/*      */     } finally {
/*  224 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
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
/*      */   public boolean checkBusinessNameById(String businessName, int businessId) {
/*  236 */     Connection connection = null;
/*  237 */     PreparedStatement preparedStatement = null;
/*  238 */     ResultSet resultSet = null;
/*      */     try {
/*  240 */       connection = this.dataSource.getConnection();
/*  241 */       preparedStatement = connection.prepareStatement(Queryconstants.checkBusinessNameById);
/*      */       
/*  243 */       preparedStatement.setString(1, businessName);
/*  244 */       preparedStatement.setInt(2, businessId);
/*      */       
/*  246 */       resultSet = preparedStatement.executeQuery();
/*      */       
/*  248 */       if (resultSet.next()) {
/*  249 */         return true;
/*      */       }
/*  251 */       return false;
/*      */     }
/*  253 */     catch (Exception ex) {
/*  254 */       ex.printStackTrace();
/*  255 */       return false;
/*      */     } finally {
/*  257 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String GetBusinessNo(int businessId) {
/*  267 */     Connection connection = null;
/*  268 */     PreparedStatement preparedStatement = null;
/*  269 */     ResultSet resultSet = null;
/*  270 */     String businessNo = "";
/*      */     try {
/*  272 */       DateFormat dateFormat = new SimpleDateFormat("yy");
/*  273 */       Date date = new Date();
/*  274 */       System.out.println(dateFormat.format(date));
/*  275 */       connection = this.dataSource.getConnection();
/*  276 */       preparedStatement = connection.prepareStatement(Queryconstants.getWardCode);
/*      */       
/*  278 */       resultSet = preparedStatement.executeQuery();
/*      */       
/*  280 */       if (resultSet.next()) {
/*  281 */         businessNo = dateFormat.format(date) + String.format("%06d", new Object[] { Integer.valueOf(businessId) });
/*      */       }
/*      */       
/*  284 */       return businessNo;
/*  285 */     } catch (Exception ex) {
/*  286 */       ex.printStackTrace();
/*  287 */       return businessNo = "";
/*      */     } finally {
/*  289 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String GetApplicationNo(int appId) {
/*  299 */     Connection connection = null;
/*  300 */     PreparedStatement preparedStatement = null;
/*  301 */     ResultSet resultSet = null;
/*  302 */     String businessNo = "";
/*      */     try {
/*  304 */       DateFormat dateFormat = new SimpleDateFormat("yy");
/*  305 */       Date date = new Date();
/*  306 */       System.out.println(dateFormat.format(date));
/*  307 */       connection = this.dataSource.getConnection();
/*  308 */       preparedStatement = connection.prepareStatement(Queryconstants.getConfigValue);
/*      */       
/*  310 */       preparedStatement.setString(1, "001");
/*  311 */       resultSet = preparedStatement.executeQuery();
/*      */       
/*  313 */       if (resultSet.next()) {
/*  314 */         System.out.println("appno###" + dateFormat.format(date) + String.format("%06d", new Object[] { Integer.valueOf(appId) }));
/*      */         
/*  316 */         businessNo = resultSet.getString("value") + dateFormat.format(date) + String.format("%06d", new Object[] { Integer.valueOf(appId) });
/*      */       } 
/*      */ 
/*      */       
/*  320 */       return businessNo;
/*  321 */     } catch (Exception ex) {
/*  322 */       ex.printStackTrace();
/*  323 */       return businessNo = "";
/*      */     } finally {
/*  325 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String GetPermitNo(int appId) {
/*  336 */     Connection connection = null;
/*  337 */     PreparedStatement preparedStatement = null;
/*  338 */     ResultSet resultSet = null;
/*  339 */     String permitNo = "";
/*      */     try {
/*  341 */       DateFormat dateFormat = new SimpleDateFormat("yyyy");
/*  342 */       Date date = new Date();
/*  343 */       System.out.println(dateFormat.format(date));
/*  344 */       connection = this.dataSource.getConnection();
/*  345 */       System.out.println("permitno###" + dateFormat.format(date) + appId);
/*  346 */       permitNo = dateFormat.format(date) + String.format("%06d", new Object[] { Integer.valueOf(appId) });
/*      */       
/*  348 */       return permitNo;
/*  349 */     } catch (Exception ex) {
/*  350 */       ex.printStackTrace();
/*  351 */       return permitNo = "";
/*      */     } finally {
/*  353 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean checkBusinessName(String businessName) {
/*  364 */     Connection connection = null;
/*  365 */     PreparedStatement preparedStatement = null;
/*  366 */     ResultSet resultSet = null;
/*      */     try {
/*  368 */       connection = this.dataSource.getConnection();
/*  369 */       preparedStatement = connection.prepareStatement(Queryconstants.checkBusinessName);
/*      */       
/*  371 */       preparedStatement.setString(1, businessName);
/*      */       
/*  373 */       resultSet = preparedStatement.executeQuery();
/*      */       
/*  375 */       if (resultSet.next()) {
/*  376 */         return true;
/*      */       }
/*  378 */       return false;
/*      */     }
/*  380 */     catch (Exception ex) {
/*  381 */       ex.printStackTrace();
/*  382 */       return false;
/*      */     } finally {
/*  384 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean ValidateNationalId(String nationalId) {
/*  395 */     Connection connection = null;
/*  396 */     PreparedStatement preparedStatement = null;
/*  397 */     ResultSet resultSet = null;
/*      */     try {
/*  399 */       connection = this.dataSource.getConnection();
/*  400 */       preparedStatement = connection.prepareStatement(Queryconstants.validateNationalId);
/*      */       
/*  402 */       preparedStatement.setString(1, nationalId);
/*      */       
/*  404 */       resultSet = preparedStatement.executeQuery();
/*      */       
/*  406 */       if (resultSet.next()) {
/*  407 */         return true;
/*      */       }
/*  409 */       return false;
/*      */     }
/*  411 */     catch (Exception ex) {
/*  412 */       ex.printStackTrace();
/*  413 */       return false;
/*      */     } finally {
/*  415 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<PermitType> GetActivePermitTypes(String permitType) {
/*  425 */     Connection connection = null;
/*  426 */     PreparedStatement preparedStatement = null;
/*  427 */     ResultSet resultSet = null;
/*  428 */     int count = 1;
/*      */     try {
/*  430 */       connection = this.dataSource.getConnection();
/*  431 */       preparedStatement = connection.prepareStatement(Queryconstants.getActivePermitTypes);
/*      */       
/*  433 */       preparedStatement.setString(1, permitType);
/*  434 */       resultSet = preparedStatement.executeQuery();
/*  435 */       List<PermitType> types = new ArrayList<>();
/*  436 */       while (resultSet.next()) {
/*  437 */         types.add(new PermitType(resultSet.getInt("id"), resultSet.getString("permit_type_name") + "--" + "(Ksh. " + resultSet.getDouble("permit_Fee") + ")"));
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  442 */         count++;
/*      */       } 
/*  444 */       return types;
/*  445 */     } catch (Exception ex) {
/*  446 */       ex.printStackTrace();
/*  447 */       return null;
/*      */     } finally {
/*  449 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<Application> GetAllApplications() {
/*  458 */     Connection connection = null;
/*  459 */     PreparedStatement preparedStatement = null;
/*  460 */     ResultSet resultSet = null;
/*  461 */     int count = 1;
/*      */     
/*      */     try {
/*  464 */       connection = this.dataSource.getConnection();
/*  465 */       preparedStatement = connection.prepareStatement(Queryconstants.getAllApplications);
/*      */       
/*  467 */       resultSet = preparedStatement.executeQuery();
/*  468 */       List<Application> apps = new ArrayList<>();
/*  469 */       while (resultSet.next()) {
/*  470 */         apps.add(new Application(resultSet.getInt("ID"), resultSet.getString("business_No"), resultSet.getString("business_name"), resultSet.getInt("no_of_employees"), resultSet.getInt("permit_type_id"), resultSet.getDouble("permit_fee"), resultSet.getString("electricity_no"), resultSet.getString("water_acc_no"), resultSet.getInt("area"), resultSet.getString("reg_no"), resultSet.getString("business_desc"), resultSet.getString("postal_add"), resultSet.getString("postal_code"), resultSet.getString("email"), resultSet.getString("fax"), 
		resultSet.getString("mobile_no"), resultSet.getString("land_line_no"), 
		resultSet.getInt("ward_id"), resultSet.getString("land_zone"), resultSet.getString("plot_no"), 
		resultSet.getBoolean("active"), 200, count, resultSet.getString("invoice_status"),
		resultSet.getInt("created_by"), resultSet.getString("national_id")
		, resultSet.getInt("application_fee")
		, resultSet.getInt("advertisement_fee")
		, resultSet.getInt("billboard_fee")
		, resultSet.getInt("tradename_fee")
		, resultSet.getString("pin_number")
		, resultSet.getString("detail_activity")
		, resultSet.getString("applicant")
		, resultSet.getString("vat_number")
		
		));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  493 */         count++;
/*      */       } 
/*  495 */       return apps;
/*  496 */     } catch (Exception ex) {
/*  497 */       ex.printStackTrace();
/*  498 */       return null;
/*      */     } finally {
/*  500 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
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
/*      */   public List<Application> GetAllAppsByLinkId(int linkId, String nationalIdNo, int agentId) {
/*  513 */     Connection connection = null;
/*  514 */     PreparedStatement preparedStatement = null;
/*  515 */     ResultSet resultSet = null;
/*  516 */     int count = 1;
/*      */     try {
/*  518 */       connection = this.dataSource.getConnection();
/*  519 */       List<Application> apps = new ArrayList<>();
/*  520 */       if (linkId == 3) {
/*  521 */         preparedStatement = connection.prepareStatement(Queryconstants.getAllAppByAgentId);
/*      */         
/*  523 */         preparedStatement.setInt(1, agentId);
/*  524 */         resultSet = preparedStatement.executeQuery();
/*      */         
/*  526 */         while (resultSet.next()) {
/*  527 */           apps.add(new Application(resultSet.getInt("ID"), resultSet.getString("business_No"), resultSet.getString("business_name"), resultSet.getInt("no_of_employees"), resultSet.getInt("permit_type_id"), resultSet.getDouble("permit_fee"), resultSet.getString("electricity_no"), resultSet.getString("water_acc_no"), resultSet.getInt("area"), resultSet.getString("reg_no"), resultSet.getString("business_desc"), resultSet.getString("postal_add"), resultSet.getString("postal_code"), resultSet.getString("email"), resultSet.getString("fax"), resultSet.getString("mobile_no"), resultSet.getString("land_line_no"), resultSet.getInt("ward_id"), resultSet.getString("land_zone"), resultSet.getString("plot_no"), resultSet.getBoolean("active"), 200, count, resultSet.getString("invoice_status"), resultSet.getInt("created_by"), resultSet.getString("national_id")
		
		, resultSet.getInt("application_fee")
		, resultSet.getInt("advertisement_fee")
		, resultSet.getInt("billboard_fee")
		, resultSet.getInt("tradename_fee")
		, resultSet.getString("pin_number")
		, resultSet.getString("detail_activity")
		, resultSet.getString("applicant")
		, resultSet.getString("vat_number")
		));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  550 */           count++;
/*      */         } 
/*      */       } 
/*      */       
/*  554 */       if (linkId == 4) {
/*  555 */         preparedStatement = connection.prepareStatement(Queryconstants.getAllAppsForIndividual);
/*      */         
/*  557 */         preparedStatement.setString(1, nationalIdNo);
/*  558 */         resultSet = preparedStatement.executeQuery();
/*      */         
/*  560 */         while (resultSet.next()) {
/*  561 */           apps.add(new Application(resultSet.getInt("ID"), resultSet.getString("business_No"), resultSet.getString("business_name"), resultSet.getInt("no_of_employees"), resultSet.getInt("permit_type_id"), resultSet.getDouble("permit_fee"), resultSet.getString("electricity_no"), resultSet.getString("water_acc_no"), resultSet.getInt("area"), resultSet.getString("reg_no"), resultSet.getString("business_desc"), resultSet.getString("postal_add"), resultSet.getString("postal_code"), resultSet.getString("email"), resultSet.getString("fax"), resultSet.getString("mobile_no"), resultSet.getString("land_line_no"), resultSet.getInt("ward_id"), resultSet.getString("land_zone"), resultSet.getString("plot_no"), resultSet.getBoolean("active"), 200, count, resultSet.getString("invoice_status"), resultSet.getInt("created_by"), resultSet.getString("national_id")
		, resultSet.getInt("application_fee")
		, resultSet.getInt("advertisement_fee")
		, resultSet.getInt("billboard_fee")
		, resultSet.getInt("tradename_fee")
		, resultSet.getString("pin_number")
		, resultSet.getString("detail_activity")
		, resultSet.getString("applicant")
		, resultSet.getString("vat_number")
		));
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  584 */           count++;
/*      */         } 
/*      */       } 
/*      */       
/*  588 */       return apps;
/*  589 */     } catch (Exception ex) {
/*  590 */       ex.printStackTrace();
/*  591 */       return null;
/*      */     } finally {
/*  593 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<Application> GetAllInvoices() {
/*  602 */     Connection connection = null;
/*  603 */     PreparedStatement preparedStatement = null;
/*  604 */     PreparedStatement preparedStatement1 = null;
/*  605 */     ResultSet resultSet = null;
/*  606 */     ResultSet resultSet2 = null;
/*  607 */     int count = 1;
/*      */     try {
/*  609 */       connection = this.dataSource.getConnection();
/*  610 */       preparedStatement = connection.prepareStatement(Queryconstants.getInvoices);
/*      */       
/*  612 */       resultSet = preparedStatement.executeQuery();
/*  613 */       List<Application> apps = new ArrayList<>();
/*  614 */       while (resultSet.next()) {
/*  615 */         Application objApp = new Application();
/*  616 */         objApp.appId = resultSet.getInt("id");
/*  617 */         objApp.businessNo = resultSet.getString("business_No");
/*  618 */         objApp.businessName = resultSet.getString("business_name");
/*  619 */         objApp.appliedOn = resultSet.getString("created_on");
/*  620 */         objApp.status = resultSet.getString("invoice_Status");
/*  621 */         objApp.paidStatus = resultSet.getString("paid_status");
/*  622 */         objApp.permitFee = resultSet.getDouble("fee");
/*  623 */         objApp.penalty = resultSet.getDouble("penalty");
/*  624 */         objApp.postalAdd = resultSet.getString("postal_add");
/*  625 */         objApp.postalCode = resultSet.getString("postal_code");
/*  626 */         objApp.email = resultSet.getString("email");
/*  627 */         objApp.mobileNo = resultSet.getString("mobile_no");
/*  628 */         objApp.appNo = resultSet.getString("app_no");
/*  629 */         objApp.applicant = resultSet.getString("username");
/*  630 */         objApp.noOfEmployees = resultSet.getInt("no_of_employees");
/*  631 */         objApp.area = resultSet.getInt("area");
/*  632 */         objApp.waterAccNo = resultSet.getString("water_acc_no");
/*  633 */         objApp.regNo = resultSet.getString("reg_no");
/*  634 */         objApp.electricityAccNo = resultSet.getString("electricity_no");
/*  635 */         objApp.businessId = resultSet.getInt("business_id");
/*  636 */         objApp.approvedBy = resultSet.getInt("approved_by");
/*  637 */         objApp.approvedOn = resultSet.getString("approved_On");
/*  638 */         objApp.rejectReason = resultSet.getString("rejected_reason");
/*  639 */         objApp.paidDate = resultSet.getString("paid_date");
/*  640 */         objApp.appType = resultSet.getString("app_type");
/*  641 */         objApp.preSbp = resultSet.getString("previous_sbp");
/*  642 */         objApp.appliedFor = resultSet.getInt("applied_for");
objApp.application_fee = resultSet.getInt("application_fee");
objApp.advertisement_fee = resultSet.getInt("advertisement_fee");
objApp.billboard_fee = resultSet.getInt("billboard_fee");
objApp.tradename_fee = resultSet.getInt("tradename_fee");

/*  643 */         if (objApp.approvedBy > 0) {
/*  644 */           preparedStatement1 = connection.prepareStatement(Queryconstants.getApprovedUser);
/*      */           
/*  646 */           preparedStatement1.setInt(1, objApp.approvedBy);
/*  647 */           resultSet2 = preparedStatement1.executeQuery();
/*  648 */           if (resultSet2.next()) {
/*  649 */             objApp.approvedUser = resultSet2.getString("username");
/*      */           }
/*      */         } 
/*  652 */         if (resultSet.getInt("receipt_by") > 0) {
/*  653 */           preparedStatement1 = connection.prepareStatement(Queryconstants.getApprovedUser);
/*      */           
/*  655 */           preparedStatement1.setInt(1, resultSet.getInt("receipt_by"));
/*      */           
/*  657 */           resultSet2 = preparedStatement1.executeQuery();
/*  658 */           if (resultSet2.next()) {
/*  659 */             objApp.paidUser = resultSet2.getString("username");
/*      */           }
/*      */         } 
/*  662 */         objApp.count = count;
/*  663 */         apps.add(objApp);
/*  664 */         count++;
/*      */       } 
/*  666 */       return apps;
/*  667 */     } catch (Exception ex) {
/*  668 */       ex.printStackTrace();
/*  669 */       return null;
/*      */     } finally {
/*  671 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
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
/*      */   public List<Application> GetAllInvoicesByLinkId(int linkId, String nationalIdNo, int agentId) {
/*  683 */     Connection connection = null;
/*  684 */     PreparedStatement preparedStatement = null;
/*  685 */     PreparedStatement preparedStatement1 = null;
/*  686 */     ResultSet resultSet = null;
/*  687 */     ResultSet resultSet2 = null;
/*  688 */     int count = 1;
/*      */     try {
/*  690 */       connection = this.dataSource.getConnection();
/*  691 */       List<Application> apps = new ArrayList<>();
/*  692 */       if (linkId == 3) {
/*  693 */         preparedStatement = connection.prepareStatement(Queryconstants.getInvoicesByAgentId);
/*      */         
/*  695 */         preparedStatement.setInt(1, agentId);
/*  696 */         resultSet = preparedStatement.executeQuery();
/*      */         
/*  698 */         while (resultSet.next()) {
/*  699 */           Application objApp = new Application();
/*  700 */           objApp.appId = resultSet.getInt("id");
/*  701 */           objApp.businessNo = resultSet.getString("business_No");
/*  702 */           objApp.businessName = resultSet.getString("business_name");
/*  703 */           objApp.appliedOn = resultSet.getString("created_on");
/*  704 */           objApp.status = resultSet.getString("invoice_Status");
/*  705 */           objApp.paidStatus = resultSet.getString("paid_status");
/*  706 */           objApp.permitFee = resultSet.getDouble("fee");
/*  707 */           objApp.penalty = resultSet.getDouble("penalty");
/*  708 */           objApp.postalAdd = resultSet.getString("postal_add");
/*  709 */           objApp.postalCode = resultSet.getString("postal_code");
/*  710 */           objApp.email = resultSet.getString("email");
/*  711 */           objApp.mobileNo = resultSet.getString("mobile_no");
/*  712 */           objApp.appNo = resultSet.getString("app_no");
/*  713 */           objApp.applicant = resultSet.getString("username");
/*  714 */           objApp.noOfEmployees = resultSet.getInt("no_of_employees");
/*  715 */           objApp.area = resultSet.getInt("area");
/*  716 */           objApp.waterAccNo = resultSet.getString("water_acc_no");
/*  717 */           objApp.regNo = resultSet.getString("reg_no");
/*  718 */           objApp.electricityAccNo = resultSet.getString("electricity_no");
/*      */           
/*  720 */           objApp.businessId = resultSet.getInt("business_id");
/*  721 */           objApp.approvedBy = resultSet.getInt("approved_by");
/*  722 */           objApp.approvedOn = resultSet.getString("approved_On");
/*  723 */           objApp.rejectReason = resultSet.getString("rejected_reason");
/*      */           
/*  725 */           objApp.paidDate = resultSet.getString("paid_date");
/*  726 */           objApp.appType = resultSet.getString("app_type");
/*  727 */           objApp.preSbp = resultSet.getString("previous_sbp");
/*  728 */           objApp.appliedFor = resultSet.getInt("applied_for");
/*  729 */           if (objApp.approvedBy > 0) {
/*  730 */             preparedStatement1 = connection.prepareStatement(Queryconstants.getApprovedUser);
/*      */             
/*  732 */             preparedStatement1.setInt(1, objApp.approvedBy);
/*  733 */             resultSet2 = preparedStatement1.executeQuery();
/*  734 */             if (resultSet2.next()) {
/*  735 */               objApp.approvedUser = resultSet2.getString("username");
/*      */             }
/*      */           } 
/*      */           
/*  739 */           if (resultSet.getInt("receipt_by") > 0) {
/*  740 */             preparedStatement1 = connection.prepareStatement(Queryconstants.getApprovedUser);
/*      */             
/*  742 */             preparedStatement1.setInt(1, resultSet.getInt("receipt_by"));
/*      */             
/*  744 */             resultSet2 = preparedStatement1.executeQuery();
/*  745 */             if (resultSet2.next()) {
/*  746 */               objApp.paidUser = resultSet2.getString("username");
/*      */             }
/*      */           } 
/*  749 */           objApp.count = count;
/*  750 */           apps.add(objApp);
/*  751 */           count++;
/*      */         } 
/*      */       } 
/*  754 */       if (linkId == 4) {
/*  755 */         preparedStatement = connection.prepareStatement(Queryconstants.getInvoicesByIndividual);
/*      */         
/*  757 */         preparedStatement.setString(1, nationalIdNo);
/*  758 */         resultSet = preparedStatement.executeQuery();
/*      */         
/*  760 */         while (resultSet.next()) {
/*  761 */           Application objApp = new Application();
/*  762 */           objApp.appId = resultSet.getInt("id");
/*  763 */           objApp.businessNo = resultSet.getString("business_No");
/*  764 */           objApp.businessName = resultSet.getString("business_name");
/*  765 */           objApp.appliedOn = resultSet.getString("created_on");
/*  766 */           objApp.status = resultSet.getString("invoice_Status");
/*  767 */           objApp.paidStatus = resultSet.getString("paid_status");
/*  768 */           objApp.permitFee = resultSet.getDouble("fee");
/*  769 */           objApp.penalty = resultSet.getDouble("penalty");
/*  770 */           objApp.postalAdd = resultSet.getString("postal_add");
/*  771 */           objApp.postalCode = resultSet.getString("postal_code");
/*  772 */           objApp.email = resultSet.getString("email");
/*  773 */           objApp.mobileNo = resultSet.getString("mobile_no");
/*  774 */           objApp.appNo = resultSet.getString("app_no");
/*  775 */           objApp.applicant = resultSet.getString("username");
/*  776 */           objApp.noOfEmployees = resultSet.getInt("no_of_employees");
/*  777 */           objApp.area = resultSet.getInt("area");
/*  778 */           objApp.waterAccNo = resultSet.getString("water_acc_no");
/*  779 */           objApp.regNo = resultSet.getString("reg_no");
/*  780 */           objApp.electricityAccNo = resultSet.getString("electricity_no");
/*      */           
/*  782 */           objApp.businessId = resultSet.getInt("business_id");
/*  783 */           objApp.approvedBy = resultSet.getInt("approved_by");
/*  784 */           objApp.approvedOn = resultSet.getString("approved_On");
/*  785 */           objApp.rejectReason = resultSet.getString("rejected_reason");
/*      */           
/*  787 */           objApp.paidDate = resultSet.getString("paid_date");
/*  788 */           objApp.appType = resultSet.getString("app_type");
/*  789 */           objApp.preSbp = resultSet.getString("previous_sbp");
/*  790 */           objApp.appliedFor = resultSet.getInt("applied_for");
/*  791 */           if (objApp.approvedBy > 0) {
/*  792 */             preparedStatement1 = connection.prepareStatement(Queryconstants.getApprovedUser);
/*      */             
/*  794 */             preparedStatement1.setInt(1, objApp.approvedBy);
/*  795 */             resultSet2 = preparedStatement1.executeQuery();
/*  796 */             if (resultSet2.next()) {
/*  797 */               objApp.approvedUser = resultSet2.getString("username");
/*      */             }
/*      */           } 
/*      */           
/*  801 */           if (resultSet.getInt("receipt_by") > 0) {
/*  802 */             preparedStatement1 = connection.prepareStatement(Queryconstants.getApprovedUser);
/*      */             
/*  804 */             preparedStatement1.setInt(1, resultSet.getInt("receipt_by"));
/*      */             
/*  806 */             resultSet2 = preparedStatement1.executeQuery();
/*  807 */             if (resultSet2.next()) {
/*  808 */               objApp.paidUser = resultSet2.getString("username");
/*      */             }
/*      */           } 
/*  811 */           objApp.count = count;
/*  812 */           apps.add(objApp);
/*  813 */           count++;
/*      */         } 
/*      */       } 
/*  816 */       return apps;
/*  817 */     } catch (Exception ex) {
/*  818 */       ex.printStackTrace();
/*  819 */       return null;
/*      */     } finally {
/*  821 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ErevenueResponse UpdateInvocieStatus(Application app) {
/*  832 */     Connection connection = null;
/*  833 */     PreparedStatement preparedStatement = null;
/*  834 */     PreparedStatement preparedStatement1 = null;
/*  835 */     ResultSet resultSet = null;
/*      */     try {
/*  837 */       connection = this.dataSource.getConnection();
/*  838 */       connection.setAutoCommit(false);
/*  839 */       if (app.status.equalsIgnoreCase("A")) {
/*  840 */         preparedStatement = connection.prepareStatement(Queryconstants.updateApprovedStatus);
/*      */         
/*  842 */         preparedStatement.setString(1, app.status);
/*  843 */         preparedStatement.setInt(2, app.createdBy);
/*  844 */         preparedStatement.setTimestamp(3, new Timestamp((new Date()).getTime()));
/*      */          String total_in_words=Utility.convertNumberToWords(app.total);
                    preparedStatement.setString(4, total_in_words);
/*      */         
/*  847 */         preparedStatement.setInt(5, app.businessId);
/*  848 */       } else if (app.status.equalsIgnoreCase("P")) {
/*  849 */         preparedStatement = connection.prepareStatement(Queryconstants.updatePaidStatus);
/*      */         
/*  851 */         preparedStatement.setString(1, app.status);
/*  852 */         preparedStatement.setString(2, app.mpesaCode);
/*  853 */         preparedStatement.setBoolean(3, true);
/*  854 */         preparedStatement.setTimestamp(4, new Timestamp((new Date()).getTime()));
/*      */         
/*  856 */         preparedStatement.setInt(5, app.createdBy);
/*  857 */         preparedStatement.setTimestamp(6, new Timestamp((new Date()).getTime()));
/*      */         
/*  859 */         preparedStatement.setString(7, app.bankName);
/*  860 */         preparedStatement.setString(8, app.accNo);
/*  861 */         preparedStatement.setString(9, app.transNo);
/*  862 */         preparedStatement.setInt(10, app.businessId);
/*  863 */         if (preparedStatement.executeUpdate() > 0)
/*      */         {
/*  865 */           Calendar calendarEnd = Calendar.getInstance();
/*  866 */           int year = calendarEnd.get(1);
/*  867 */           calendarEnd.set(1, year);
/*  868 */           calendarEnd.set(2, 11);
/*  869 */           calendarEnd.set(5, 31);
/*      */           
/*  871 */           Date endDate = calendarEnd.getTime();
/*  872 */           SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
/*      */           
/*  874 */           DBOperations.DisposeSql(preparedStatement);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  880 */           if (app.appType.equalsIgnoreCase("Renew")) {
/*  881 */             preparedStatement = connection.prepareStatement(Queryconstants.updatePermit);
/*      */             
/*  883 */             preparedStatement.setInt(1, app.appliedFor);
/*  884 */             preparedStatement.setString(2, sdf.format(Long.valueOf(endDate.getTime())));
/*      */             
/*  886 */             preparedStatement.setBoolean(3, true);
/*  887 */             preparedStatement.setInt(4, app.createdBy);
/*  888 */             preparedStatement.setTimestamp(5, new Timestamp((new Date()).getTime()));
/*      */ 
/*      */ 
/*      */             
/*  892 */             preparedStatement.setString(6, app.preSbp);
/*      */           } else {
/*  894 */             preparedStatement = connection.prepareStatement(Queryconstants.insertPermit);
/*      */             
/*  896 */             preparedStatement.setString(1, GetPermitNo(app.appId));
/*  897 */             preparedStatement.setInt(2, app.appId);
/*  898 */             preparedStatement.setInt(3, app.businessId);
/*  899 */             preparedStatement.setInt(4, year);
/*  900 */             preparedStatement.setString(5, sdf.format(Long.valueOf(endDate.getTime())));
/*      */             
/*  902 */             preparedStatement.setBoolean(6, true);
/*  903 */             preparedStatement.setInt(7, app.createdBy);
/*  904 */             preparedStatement.setTimestamp(8, new Timestamp((new Date()).getTime()));
/*      */ 
/*      */           
/*      */           }
/*      */ 
/*      */         
/*      */         }
/*      */ 
/*      */       
/*      */       }
/*  914 */       else if (app.status.equalsIgnoreCase("R")) {
/*  915 */         preparedStatement = connection.prepareStatement(Queryconstants.updateRejectedStatus);
/*      */         
/*  917 */         preparedStatement.setString(1, app.status);
/*  918 */         preparedStatement.setString(2, app.rejectReason);
/*  919 */         preparedStatement.setInt(3, app.createdBy);
/*  920 */         preparedStatement.setTimestamp(4, new Timestamp((new Date()).getTime()));
/*      */         
/*  922 */         preparedStatement.setInt(5, app.businessId);
/*  923 */       } else if (app.status.equalsIgnoreCase("I")) {
/*  924 */         preparedStatement = connection.prepareStatement(Queryconstants.updateInspectedStatus);
/*      */         
/*  926 */         preparedStatement.setString(1, app.status);
/*  927 */         preparedStatement.setInt(2, app.createdBy);
/*  928 */         preparedStatement.setTimestamp(3, new Timestamp((new Date()).getTime()));
/*      */         String total_in_words=Utility.convertNumberToWords(app.total);
                    preparedStatement.setString(4, total_in_words);
/*  930 */         preparedStatement.setInt(5, app.businessId);
/*  931 */       } else if (app.status.equalsIgnoreCase("RU")) {
/*  932 */         preparedStatement = connection.prepareStatement(Queryconstants.updateReferUserStatus);
/*      */         
/*  934 */         preparedStatement.setString(1, app.status);
/*  935 */         preparedStatement.setInt(2, app.createdBy);
/*  936 */         preparedStatement.setTimestamp(3, new Timestamp((new Date()).getTime()));
/*      */         
/*  938 */         preparedStatement.setInt(4, app.businessId);
/*      */       } 
/*      */       
/*  941 */       if (preparedStatement.executeUpdate() > 0) {
/*  942 */         connection.commit();
/*  943 */         return new ErevenueResponse(200, "Records Updated");
/*      */       } 
/*  945 */       connection.rollback();
/*  946 */       return new ErevenueResponse(201, "Nothing To Update");
/*      */     }
/*  948 */     catch (SQLException sqlEx) {
/*  949 */       sqlEx.printStackTrace();
/*      */       try {
/*  951 */         connection.rollback();
/*  952 */       } catch (SQLException e) {
/*      */         
/*  954 */         e.printStackTrace();
/*      */       } 
/*  956 */       return new ErevenueResponse(202, "Exception Occured");
/*      */     }
/*  958 */     catch (Exception ex) {
/*      */       
/*  960 */       ex.printStackTrace();
/*      */       try {
/*  962 */         connection.rollback();
/*  963 */       } catch (SQLException e) {
/*      */         
/*  965 */         e.printStackTrace();
/*      */       } 
/*  967 */       return new ErevenueResponse(202, "Exception Occured");
/*      */     } finally {
/*  969 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<Application> GetAllPermits() {
/*  978 */     Connection connection = null;
/*  979 */     PreparedStatement preparedStatement = null;
/*      */     
/*  981 */     ResultSet resultSet = null;
/*      */     
/*  983 */     int count = 1;
/*      */     
/*      */     try {
/*  986 */       connection = this.dataSource.getConnection();
/*  987 */       preparedStatement = connection.prepareStatement(Queryconstants.getPermitDetails);
/*      */ 
/*      */       
/*  990 */       resultSet = preparedStatement.executeQuery();
/*  991 */       List<Application> apps = new ArrayList<>();
/*      */       
/*  993 */       while (resultSet.next()) {
/*  994 */         Application objApp = new Application();
/*      */         
/*  996 */         objApp.permitNo = resultSet.getString("permit_no");
/*  997 */         objApp.businessNo = resultSet.getString("business_No");
/*  998 */         objApp.validity = resultSet.getInt("validity");
/*  999 */         objApp.expiryDate = resultSet.getString("expiry_date");
/* 1000 */         objApp.businessName = resultSet.getString("business_name");
/* 1001 */         objApp.businessDesc = resultSet.getString("business_Desc");
/* 1002 */         objApp.permitFee = resultSet.getDouble("fee");
/* 1003 */         objApp.postalAdd = resultSet.getString("postal_add");
/* 1004 */         objApp.postalCode = resultSet.getString("postal_code");
/* 1005 */         objApp.email = resultSet.getString("email");
/* 1006 */         objApp.appNo = resultSet.getString("app_no");
/* 1007 */         objApp.plotNo = resultSet.getString("plot_no");
/* 1008 */         objApp.permitUser = resultSet.getString("username");
/* 1009 */         objApp.permitQr = generateQrImageBase64String(objApp.permitNo);
/* 1010 */         objApp.status = resultSet.getString("permit_status");
/* 1011 */         objApp.appType = resultSet.getString("app_type");
/* 1012 */         objApp.count = count;
/* 1013 */         objApp.businessId = resultSet.getInt("business_id");
/* 1014 */         objApp.permitStatus = resultSet.getString("newStatus");
/*      */         
/* 1016 */         int feeValue = (int)Math.round(objApp.permitFee);
/* 1017 */         objApp.amountInWords = Utility.convertNumberToWords(feeValue);
/*      */         
/* 1019 */         SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
/*      */         
/* 1021 */         Calendar year1 = Calendar.getInstance();
/* 1022 */         int year = year1.get(1);
/* 1023 */         year1.set(1, year + 1);
/* 1024 */         year1.set(2, 11);
/* 1025 */         year1.set(5, 31);
/* 1026 */         Date endDate = year1.getTime();
/*      */         
/* 1028 */         System.out.println(sdf.format(Long.valueOf(endDate.getTime())));
/*      */ 
/*      */         
/* 1031 */         Calendar year2 = Calendar.getInstance();
/* 1032 */         int nyear = year2.get(1);
/* 1033 */         year2.set(1, year + 2);
/* 1034 */         year2.set(2, 11);
/* 1035 */         year2.set(5, 31);
/* 1036 */         Date secondyear = year2.getTime();
/*      */         
/* 1038 */         System.out.println(sdf.format(Long.valueOf(secondyear.getTime())));
/*      */         
/* 1040 */         ArrayList<PermitYear> yearList = new ArrayList<>();
/* 1041 */         yearList.add(new PermitYear(sdf.format(Long.valueOf(endDate.getTime()))));
/*      */         
/* 1043 */         objApp.yearList = yearList;
/* 1044 */         apps.add(objApp);
/* 1045 */         count++;
/*      */       } 
/*      */       
/* 1048 */       return apps;
/* 1049 */     } catch (Exception ex) {
/* 1050 */       ex.printStackTrace();
/* 1051 */       return null;
/*      */     } finally {
/* 1053 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
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
/*      */   public String generateQrImageBase64String(String permitNo) {
             String imageString = null;
		String qrText = permitNo;
		// String qrText =
		// voucher.getVoucher()+"$"+voucher.getVoucher()+voucher.getVoucher()+voucher.getVoucher();

		qrText = getBase64QrCodeText(qrText);
		// System.out.println("QrTextLength$$" + qrText.length());
		ByteArrayOutputStream bos = QRCode.from(qrText).to(ImageType.PNG).stream();
		byte[] imageBytes = bos.toByteArray();

		BASE64Encoder encoder = new BASE64Encoder();
		
		imageString = "data:image/png;base64," + encoder.encode(imageBytes);
		Base64Decoder decoder=new Base64Decoder();
		String img=imageString.split(",")[1];
		System.out.println(img+"  imageString");
		imageBytes=decoder.decode(imageBytes,11,11);  
		//String dStr = new String(decoder.decode(imageBytes)); 
		System.out.println(imageBytes+"  imageBytes");

		return imageString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getBase64QrCodeText(String qrText) {
   String imageString = "";
byte[] stringBytes = qrText.getBytes();
BASE64Encoder encoder = new BASE64Encoder();
imageString = encoder.encode(stringBytes);
  Base64.Decoder decoder = Base64.getDecoder();
    // Decoding string
    String dStr = new String(decoder.decode(imageString));
    System.out.println("Decoded string: "+dStr);

return dStr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static BufferedImage decodeToImage(String imageString) {
/* 1102 */   		BufferedImage image = null;
byte[] imageByte;
try {
	BASE64Decoder decoder = new BASE64Decoder();
	imageByte = decoder.decodeBuffer(imageString);
	ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
	image = ImageIO.read(bis);
	bis.close();

} catch (Exception e) {
	e.printStackTrace();
}
return image;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String encodeToString(BufferedImage image, String type) {
/* 1124 */     String imageString = null;
/* 1125 */     ByteArrayOutputStream bos = new ByteArrayOutputStream();
/*      */     
/*      */     try {
/* 1128 */       ImageIO.write(image, type, bos);
/* 1129 */       byte[] imageBytes = bos.toByteArray();
/*      */       
/* 1131 */       BASE64Encoder encoder = new BASE64Encoder();
/* 1132 */       imageString = encoder.encode(imageBytes);
/*      */       
/* 1134 */       bos.close();
/* 1135 */     } catch (IOException e) {
/* 1136 */       e.printStackTrace();
/*      */     } 
/* 1138 */     return imageString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ErevenueResponse UpdateQrImagePath(Application app) {
Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		String qrImage = "";
		BufferedImage image = null;
		try {
			connection = dataSource.getConnection();
			// String reportPath="D:\\MRM\\"+app.permitNo+".jpg";
			String param = "qr.filePathQrCode";
			Utility util = new Utility();
			compasProperties = util.getCompasProperties(param);

			// TOMCAT_HOME = System.getProperty("catalina.base");
			// fileName = fileDetail.getFileName();

			//PATH = compasProperties.bnfUploadFilePath;
			//String JAVAPATH = System.getProperty("catalina.base");
			String path = compasProperties.bnfUploadFilePath + app.permitNo
					+ ".jpg";
			System.out.println("qrpath##" + path);
			//System.out.println(image+" image");
			//System.out.println(app.permitQr+" app.permitQr");
			System.out.println(app.amountInWords+" app.permitQr");
			String base64Image = app.permitQr.split(",")[1];
			image = decodeToImage(base64Image);
			File outputfile = new File(path);
			ImageIO.write(image, "jpg", outputfile);

			int feeValue = (int) Math.round(app.permitFee);
			System.out.println(feeValue);
			String amountInWords = Utility.convertNumberToWords(feeValue);
			preparedStatement = connection
					.prepareStatement(Queryconstants.updQrImagePath);
			preparedStatement.setString(1, path);
			preparedStatement.setString(2, app.amountInWords);
			preparedStatement.setString(3, app.permitNo);

			return (preparedStatement.executeUpdate() > 0) ? new ErevenueResponse(
					200, "Records Updated") : new ErevenueResponse(201,
					"Nothing To Update");
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
			return new ErevenueResponse(202, "Exception Occured");

		} catch (Exception ex) {
			ex.printStackTrace();
			return new ErevenueResponse(202, "Exception Occured");
		} finally {
			DBOperations.DisposeSql(connection, preparedStatement, resultSet);
		}
   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ErevenueResponse UpdatePermitStatus(Application app) {
/* 1193 */     Connection connection = null;
/* 1194 */     PreparedStatement preparedStatement = null;
/* 1195 */     ResultSet resultSet = null;
/* 1196 */     String qrImage = "";
/* 1197 */     BufferedImage image = null;
/*      */     try {
/* 1199 */       connection = this.dataSource.getConnection();
/* 1200 */       int feeValue = (int)Math.round(app.permitFee);
/* 1201 */       String amountInWords = Utility.convertNumberToWords(feeValue);
/* 1202 */       preparedStatement = connection.prepareStatement(Queryconstants.updPermitStatus);
/*      */ 
/*      */       
/* 1205 */       preparedStatement.setString(1, app.permitStatus);
/*      */       
/* 1207 */       preparedStatement.setString(2, app.permitNo);
/*      */       
/* 1209 */       return (preparedStatement.executeUpdate() > 0) ? new ErevenueResponse(200, "Records Updated") : new ErevenueResponse(201, "Nothing To Update");
/*      */     
/*      */     }
/* 1212 */     catch (SQLException sqlEx) {
/* 1213 */       sqlEx.printStackTrace();
/* 1214 */       return new ErevenueResponse(202, "Exception Occured");
/*      */     }
/* 1216 */     catch (Exception ex) {
/* 1217 */       ex.printStackTrace();
/* 1218 */       return new ErevenueResponse(202, "Exception Occured");
/*      */     } finally {
/* 1220 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public ErevenueResponse UpdatePermitRenewal(Application app) {
/* 1230 */     Connection connection = null;
/* 1231 */     PreparedStatement preparedStatement = null;
/* 1232 */     ResultSet resultSet = null;
/* 1233 */     String penaltyPer = "";
/* 1234 */     String penaltyMonth = "";
/* 1235 */     ResultSet rs = null;
/* 1236 */     int appId = 0;
/*      */     try {
/* 1238 */       connection = this.dataSource.getConnection();
/* 1239 */       preparedStatement = connection.prepareStatement(Queryconstants.getConfigValue);
/*      */       
/* 1241 */       preparedStatement.setString(1, "002");
/*      */       
/* 1243 */       resultSet = preparedStatement.executeQuery();
/*      */       
/* 1245 */       while (resultSet.next()) {
/* 1246 */         penaltyPer = resultSet.getString("value");
/*      */       }
/*      */       
/* 1249 */       DBOperations.DisposeSql(preparedStatement, resultSet);
/* 1250 */       preparedStatement = connection.prepareStatement(Queryconstants.getConfigValue);
/*      */       
/* 1252 */       preparedStatement.setString(1, "003");
/* 1253 */       resultSet = preparedStatement.executeQuery();
/*      */       
/* 1255 */       while (resultSet.next()) {
/* 1256 */         penaltyMonth = resultSet.getString("value");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1263 */       Calendar calendarEnd = Calendar.getInstance();
/* 1264 */       int year = calendarEnd.get(1);
/* 1265 */       Date date = (new SimpleDateFormat("MMMM")).parse(penaltyMonth);
/* 1266 */       Calendar cal = Calendar.getInstance();
/* 1267 */       cal.setTime(date);
/* 1268 */       int deadlineMonth = cal.get(2) + 1;
/*      */ 
/*      */       
/* 1271 */       Date date1 = new Date();
/* 1272 */       Calendar cal1 = Calendar.getInstance();
/* 1273 */       cal.setTime(date1);
/* 1274 */       int currentmonth = cal1.get(2) + 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1281 */       int noOfMonths = currentmonth - deadlineMonth;
/* 1282 */       BigDecimal d = (new BigDecimal(penaltyPer.trim().replace("%", ""))).divide(BigDecimal.valueOf(100L));
/*      */       
/* 1284 */       double penalty = Utility.calculate(app.permitFee, d.doubleValue(), noOfMonths);
/*      */       
/* 1286 */       if (ValidateRenewBusinessId(app.businessId)) {
/* 1287 */         return new ErevenueResponse(201, "Application is already in renewal state");
/*      */       }
/*      */       
/* 1290 */       preparedStatement = connection.prepareStatement(Queryconstants.insertApplicationDtl, 1);
/*      */ 
/*      */       
/* 1293 */       preparedStatement.setInt(1, app.businessId);
/* 1294 */       preparedStatement.setDouble(2, app.permitFee);
/* 1295 */       preparedStatement.setDouble(3, penalty);
/* 1296 */       preparedStatement.setString(4, "Renew");
/* 1297 */       preparedStatement.setString(5, "RW");
/* 1298 */       preparedStatement.setInt(6, app.createdBy);
/* 1299 */       preparedStatement.setTimestamp(7, new Timestamp((new Date()).getTime()));
/*      */       
/* 1301 */       preparedStatement.setString(8, app.permitNo);
/* 1302 */       preparedStatement.setInt(9, app.validity);
/* 1303 */       if (preparedStatement.executeUpdate() > 0) {
/* 1304 */         if (app.appId == 0) {
/* 1305 */           rs = preparedStatement.getGeneratedKeys();
/* 1306 */           rs.next();
/* 1307 */           appId = rs.getInt(1);
/*      */         } 
/* 1309 */         DBOperations.DisposeSql(preparedStatement);
/* 1310 */         preparedStatement = connection.prepareStatement(Queryconstants.updateAppNo);
/*      */         
/* 1312 */         preparedStatement.setString(1, GetApplicationNo(appId));
/* 1313 */         preparedStatement.setInt(2, appId);
/* 1314 */         if (preparedStatement.executeUpdate() > 0) {
/* 1315 */           return new ErevenueResponse(200, "Records Updated");
/*      */         }
/*      */       } 
/*      */ 
/*      */       
/* 1320 */       return new ErevenueResponse(200, "Records Updated");
/* 1321 */     } catch (SQLException sqlEx) {
/* 1322 */       sqlEx.printStackTrace();
/* 1323 */       return new ErevenueResponse(202, "Exception Occured");
/*      */     }
/* 1325 */     catch (Exception ex) {
/* 1326 */       ex.printStackTrace();
/* 1327 */       return new ErevenueResponse(202, "Exception Occured");
/*      */     } finally {
/* 1329 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean ValidateRenewBusinessId(int businessId) {
/* 1339 */     Connection connection = null;
/* 1340 */     PreparedStatement preparedStatement = null;
/* 1341 */     ResultSet resultSet = null;
/*      */     try {
/* 1343 */       connection = this.dataSource.getConnection();
/* 1344 */       preparedStatement = connection.prepareStatement(Queryconstants.validateRenewBusinessId);
/*      */       
/* 1346 */       preparedStatement.setInt(1, businessId);
/*      */       
/* 1348 */       resultSet = preparedStatement.executeQuery();
/*      */       
/* 1350 */       if (resultSet.next()) {
/* 1351 */         return true;
/*      */       }
/* 1353 */       return false;
/*      */     }
/* 1355 */     catch (Exception ex) {
/* 1356 */       ex.printStackTrace();
/* 1357 */       return false;
/*      */     } finally {
/* 1359 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<Ward> GetWardsBySubCounty(int subCountyId) {
/* 1370 */     Connection connection = null;
/* 1371 */     PreparedStatement preparedStatement = null;
/* 1372 */     ResultSet resultSet = null;
/* 1373 */     int count = 1;
/*      */     try {
/* 1375 */       connection = this.dataSource.getConnection();
/* 1376 */       preparedStatement = connection.prepareStatement(Queryconstants.getWardsBySubCounty);
/*      */       
/* 1378 */       preparedStatement.setInt(1, subCountyId);
/* 1379 */       resultSet = preparedStatement.executeQuery();
/* 1380 */       List<Ward> wards = new ArrayList<>();
/* 1381 */       while (resultSet.next()) {
/* 1382 */         wards.add(new Ward(resultSet.getInt("id"), resultSet.getString("ward_Name")));
/*      */         
/* 1384 */         count++;
/*      */       } 
/* 1386 */       return wards;
/* 1387 */     } catch (Exception ex) {
/* 1388 */       ex.printStackTrace();
/* 1389 */       return null;
/*      */     } finally {
/* 1391 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public List<Application> GetPermitsByDate(Reports permit) {
/* 1400 */     Connection connection = null;
/* 1401 */     PreparedStatement preparedStatement = null;
/* 1402 */     String today = (new SimpleDateFormat("yyyy-MM-dd")).format(new Date());
/*      */     
/* 1404 */     ResultSet resultSet = null;
/*      */     
/* 1406 */     int count = 1;
/*      */     try {
/* 1408 */       connection = this.dataSource.getConnection();
/* 1409 */       preparedStatement = connection.prepareStatement(Queryconstants.getPermitDetailsByDate);
/*      */ 
/*      */       
/* 1412 */       preparedStatement.setString(1, (permit.fromDate == null) ? today : permit.fromDate);
/*      */       
/* 1414 */       preparedStatement.setString(2, (permit.toDate == null) ? today : permit.toDate);
/*      */ 
/*      */       
/* 1417 */       resultSet = preparedStatement.executeQuery();
/* 1418 */       List<Application> apps = new ArrayList<>();
/*      */       
/* 1420 */       while (resultSet.next()) {
/* 1421 */         Application objApp = new Application();
/*      */         
/* 1423 */         objApp.permitNo = resultSet.getString("permit_no");
/* 1424 */         objApp.businessNo = resultSet.getString("business_No");
/* 1425 */         objApp.validity = resultSet.getInt("validity");
/* 1426 */         objApp.expiryDate = resultSet.getString("expiry_date");
/* 1427 */         objApp.businessName = resultSet.getString("business_name");
/* 1428 */         objApp.businessDesc = resultSet.getString("business_Desc");
/* 1429 */         objApp.permitFee = resultSet.getDouble("fee");
/* 1430 */         objApp.postalAdd = resultSet.getString("postal_add");
/* 1431 */         objApp.postalCode = resultSet.getString("postal_code");
/* 1432 */         objApp.email = resultSet.getString("email");
/* 1433 */         objApp.appNo = resultSet.getString("app_no");
/* 1434 */         objApp.plotNo = resultSet.getString("plot_no");
/* 1435 */         objApp.permitUser = resultSet.getString("username");
System.out.println(objApp.permitNo);
/* 1436 */         objApp.permitQr = generateQrImageBase64String(objApp.permitNo);
/* 1437 */         objApp.status = resultSet.getString("permit_status");
/* 1438 */         objApp.appType = resultSet.getString("app_type");
/* 1439 */         objApp.count = count;
/* 1440 */         objApp.businessId = resultSet.getInt("business_id");
/* 1441 */         objApp.permitStatus = resultSet.getString("newStatus");
/*      */         
/* 1443 */         int feeValue = (int)Math.round(objApp.permitFee);
/* 1444 */         objApp.amountInWords = Utility.convertNumberToWords(feeValue);
/*      */         
/* 1446 */         SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
/*      */         
/* 1448 */         Calendar year1 = Calendar.getInstance();
/* 1449 */         int year = year1.get(1);
/* 1450 */         year1.set(1, year + 1);
/* 1451 */         year1.set(2, 11);
/* 1452 */         year1.set(5, 31);
/* 1453 */         Date endDate = year1.getTime();
/*      */         
/* 1455 */         System.out.println(sdf.format(Long.valueOf(endDate.getTime())));
/*      */ 
/*      */         
/* 1458 */         Calendar year2 = Calendar.getInstance();
/* 1459 */         int nyear = year2.get(1);
/* 1460 */         year2.set(1, year + 2);
/* 1461 */         year2.set(2, 11);
/* 1462 */         year2.set(5, 31);
/* 1463 */         Date secondyear = year2.getTime();
/*      */         
/* 1465 */         System.out.println(sdf.format(Long.valueOf(secondyear.getTime())));
/*      */         
/* 1467 */         ArrayList<PermitYear> yearList = new ArrayList<>();
/* 1468 */         yearList.add(new PermitYear(sdf.format(Long.valueOf(endDate.getTime()))));
/* 1469 */         yearList.add(new PermitYear(sdf.format(Long.valueOf(secondyear.getTime()))));
/* 1470 */         objApp.yearList = yearList;
/* 1471 */         apps.add(objApp);
/* 1472 */         count++;
/*      */       } 
/*      */       
/* 1475 */       return apps;
/* 1476 */     } catch (Exception ex) {
/* 1477 */       ex.printStackTrace();
/* 1478 */       return null;
/*      */     } finally {
/* 1480 */       DBOperations.DisposeSql(connection, preparedStatement, resultSet);
/*      */     } 
/*      */   }
/*      */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\impl\ApplicationDalImpl.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */