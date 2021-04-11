/*     */ package com.compulynx.erevenue.dal.operations;
/*     */ 
/*     */ 
/*     */ public class Queryconstants
/*     */ {
    public static String  getLandFee  =   "select ifnull(permit_fee, 0) permit_fee from permit_type_mst where permit_type=?";

/*   6 */   public static String getUserCredentialDevice = "Select U.ID As UserID From UserMaster U,DeviceIssueDetails DI ,DeviceRegDetails DR,AgentMaster A   Where UserName =? And UserPassword =? and A.Id=U.AgentId and DI.DeviceRegId=DR.Id and serialno=?";
/*     */ 
/*     */ 
/*     */   
/*  10 */   public static String getUserCredentialManual = "select id as userid,status from users_mst Where userName = ? And password = ? ";
/*  11 */   public static String getUserGrpRights = "Select ug.status,Um.ID, Um.UserName, Um.name, Ug.ID As UserGroupID, user_type_id As LinkId,  national_id_no  As LinkExtInfo ,   Um.name AS LinkName,(select value from system_params where code='014') as appName, Hm.ID As HeaderID,Hm.Header_Name,Hm.Header_Icon_Css,Hm.Header_Icon_Color,Rm.Right_Display_Name,Rm.Right_Short_Code,\tRm.Right_View_Name,Rm.Right_Name,Ur.Allow_View,Ur.Allow_Edit,Ur.Allow_add,Ur.Allow_Delete,Rm.Right_Max_Width From User_Assigned_Rights Ur Inner Join Rights_mst Rm On Ur.Right_ID = Rm.ID Inner join Users_mst Um On Um.User_Group_ID = Ur.Group_Id  Inner join Menu_Header_mst Hm on Hm.ID = Rm.Right_Header_ID Inner Join User_Groups_mst Ug on Ug.ID = Um.User_Group_ID And Ug.ID = Ur.Group_Id Where Um.ID = ? And Ur.Allow_View = 1  Order By Hm.Header_Pos,rm.id";
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
/*  22 */   public static String getAgentGroupId = "select id from User_Groups_mst where group_type=? and status=1";
/*  23 */   public static String validateSignUpUser = "select id from users_mst where username=? and user_type_id<>3 and status=1";
/*     */   
/*  25 */   public static String insertUserDetails = "INSERT INTO Users_mst (UserName, Name, Password,User_Group_Id,Email,Phone,status,Created_By, Created_at,User_Type_Id,National_Id_no,market_id,pos_pin) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
/*  26 */   public static String updateUserDeatils = "UPDATE Users_Mst SET UserName=?, Name=?, Password=?, User_Group_Id=?,Email=?, Phone=?, status=?, Created_By=?, Created_at=?,User_Type_Id=?,national_Id_no=?,market_id=?,pos_pin=? WHERE ID=? ";
/*  27 */   public static String getUserByName = "Select ID From Users_mst Where UserName =?";
/*  28 */   public static String checkIdNumber = "Select ID From Users_mst Where national_id_no =? and username<>?";
/*  29 */   public static String checkUserById = "Select ID From Users_mst Where UserName =? and Id<>?";
/*  30 */   public static String getUsers = "SELECT a.ID,UserName,Name,Password,User_Group_ID,Email,Phone, National_Id_no,market_id ,a.status,a.Created_By,case when a.status=1 then 'Active' else 'InActive' end as active,a.Created_at,b.Group_Name,a.user_Type_id FROM Users_mst a,User_Groups_mst b where a.User_Group_ID=b.ID  ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   public static String getActiveUsers = "select U.Id,U.UserName,name from users_mst U where status=1 and user_type_id=1 and u.id not in (select issued_to from devices_issue) union select U.Id,U.UserName,name from devices_issue di,users_mst u where di.issued_to=u.id and di.status=0";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public static String getUserById = "SELECT a.ID,UserName,Name,Password,User_Group_ID,Email,Phone,a.status,a.Created_By,national_id_no,a.Created_at FROM users_mst a where a.status=1 and a.id=?,a.Created_at FROM Users_mst a where a.status=1 and a.id=?";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public static String getActiveGroups = " SELECT Id,Group_Name , Created_BY, Created_at FROM User_Groups_mst where status=1 and group_type=1 ";
/*  51 */   public static String getConfigParams = " SELECT Id, name,value, Created_BY, Created_at FROM system_Params";
/*  52 */   public static String updateConfigParam = "Update system_Params set value=?, updated_BY=?, updated_at=? where id=?";
/*  53 */   public static String updateConfigParamLogo = "Update system_Params set value=? where name='Logo'";
/*  54 */   public static String insertLogTable = "insert into log_table( ModuleName, Fieldname, Originalvalue, Newvalue, ModifiedBy, ModifiedOn)values(?,?,?,?,?,?)";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  59 */   public static String insertUserGroup = "INSERT INTO User_Groups_mst (Group_Name,status,created_by,created_at,group_type) VALUES (?,?,?,?,?)";
/*  60 */   public static String updateUserGroup = "UPDATE User_Groups_mst Set Group_Name= ?,status=?,Created_By = ?,updated_at = ?,group_type=? Where ID = ?";
/*  61 */   public static String getGroupByName = "Select ID From User_Groups_mst Where Group_Name = ? ";
/*  62 */   public static String checkUserAssignGroup = "select id from User_Groups_mst UG where UG.Id in (select user_group_id from Users_mst UM where um.User_Group_Id=UG.Id and UM.status=1)and ug.Id=?";
/*  63 */   public static String getGroupById = "SELECT U.Right_ID,R.Right_Display_Name,R.Allow_View,R.Allow_Add,R.Allow_Edit,R.Allow_Delete,U.Allow_View AS RightView,U.Allow_Add AS RightAdd,U.Allow_Edit AS RightEdit,U.Allow_Delete AS RightDelete FROM User_Groups_mst G INNER JOIN User_Assigned_Rights U ON U.Group_Id=G.ID right JOIN Rights_mst R ON R.ID=U.Right_ID WHERE G.ID=? union all SELECT r.Id,R.Right_Display_Name,R.Allow_View,R.Allow_Add,R.Allow_Edit,R.Allow_Delete,0 AS RightView,0 AS RightAdd,0 AS RightEdit,0 AS RightDelete FROM User_Groups_mst G ,Rights_mst R WHERE G.ID=? and r.Id not in(select right_id from User_Assigned_Rights WHERE User_Assigned_Rights.Group_Id=?)\t";
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
/*  74 */   public static String getGroups = "select id,group_name,status,created_by,created_at,group_type from user_groups_mst";
/*  75 */   public static String deleteGroupRights = "DELETE FROM User_Assigned_Rights WHERE Group_Id=?";
/*     */ 
/*     */   
/*  78 */   public static String getRights = "SELECT ID,Right_Display_Name,Allow_View,Allow_Add,Allow_Edit,Allow_Delete,Created_By,Created_at FROM Rights_mst ";
/*  79 */   public static String insertGroupRights = "INSERT INTO User_Assigned_Rights (Right_ID,Group_Id ,Allow_View,Allow_Add ,Allow_Edit,Allow_Delete,Created_By,Created_at) VALUES (?,?,?,?,?,?,?,?)";
/*     */ 
/*     */   
/*  82 */   public static String getSubcounties = "select id,code,name,case when active=0 then 'Inactive' else 'Active' end as status,active from sub_county_mst";
/*  83 */   public static String checkSubCountyName = "select id from sub_county_mst where name=?";
/*  84 */   public static String checkSubCountycode = "select id from sub_county_mst where code=?";
/*  85 */   public static String checkSubCountyNameByCode = "select id from sub_county_mst where name=? and code<>?";
/*  86 */   public static String insertSubCounty = "insert into sub_county_mst(code,name,active,created_by,created_at) values (?,?,?,?,?)";
/*  87 */   public static String updateSubCounty = "update sub_county_mst set name=?,active=?,updated_by=?,updated_at=?  where id=?";
/*  88 */   public static String actiDeactiWardsBySubCounty = "update ward_mst set active=?,updated_by=?,updated_at=?  where sub_county_id=?";
/*  89 */   public static String actiDeactiMarketsByWard = "update markets set active=?,updated_by=?,updated_at=?  where ward_id=?";
/*  90 */   public static String getWardBySubCountyId = "select id from ward_mst where sub_county_id=?";
/*     */ 
/*     */   
/*  93 */   public static String getWards = "select w.id,ward_code,ward_name,sub_county_id,w.active, case when w.active=0 then 'Inactive' else 'Active' end as status, s.name from ward_mst w, sub_county_mst s where s.id=w.sub_county_id";
/*     */ 
/*     */ 
/*     */   
/*  97 */   public static String checkWardName = "select id from ward_mst where ward_name=?";
/*  98 */   public static String checkWardCode = "select id from ward_mst where ward_code=?";
/*  99 */   public static String checkWardNameByCode = "select id from ward_mst where ward_name=? and ward_code<>?";
/* 100 */   public static String insertWard = "insert into ward_mst(ward_code,ward_name,sub_county_id,active,created_by,created_at) values (?,?,?,?,?,?)";
/* 101 */   public static String updateWard = "update ward_mst set ward_name=?,sub_county_id=?,active=?,updated_by=?,updated_at=?  where id=?";
/* 102 */   public static String getActiveSubCounties = "select id,name from sub_county_mst where active=1 ";
/*     */ 
/*     */   
/* 105 */   public static String getMarkets = "select m.id,mkt_code,mkt_name,ward_id,m.active,w.sub_county_id, case when m.active=0 then 'Inactive' else 'Active' end as status, w.ward_name,s.name from ward_mst w, markets m,sub_county_mst s where w.id=m.ward_id and s.id=w.sub_county_id";
/*     */ 
/*     */ 
/*     */   
/* 109 */   public static String getActiveWards = "select id,ward_name from ward_mst where active=1";
/* 110 */   public static String getWardsBySubCounty = "select id,ward_name from ward_mst where active=1 and sub_county_id=?";
/* 111 */   public static String checkMarketName = "select id from markets where mkt_name=?";
/* 112 */   public static String checkMarketCode = "select id from markets where mkt_code=?";
/* 113 */   public static String checkMarketNameByCode = "select id from markets where mkt_name=? and mkt_code<>?";
/* 114 */   public static String insertMarket = "insert into markets(mkt_code,mkt_name,ward_id,active,created_by,created_at) values (?,?,?,?,?,?)";
/* 115 */   public static String updateMarket = "update markets set mkt_name=?,ward_id=?,active=?,updated_by=?,updated_at=?  where id=?";
/*     */ 
/*     */   
/* 118 */   public static String getSubMarkets = "select s.id,sub_market_code,sub_market_name,market_id,s.active, case when s.active=0 then 'Inactive' else 'Active' end as status, m.mkt_name from sub_market_mst s, markets m where m.id=s.market_id";
/*     */ 
/*     */ 
/*     */   
/* 122 */   public static String getActiveMarkets = "select id,mkt_name from markets where active=1";
/* 123 */   public static String checkSubMarketName = "select id from sub_market_mst where sub_market_name=?";
/* 124 */   public static String checkSubMarketCode = "select id from sub_market_mst where sub_market_code=?";
/* 125 */   public static String checkSubMarketNameByCode = "select id from sub_market_mst where sub_market_name=? and sub_market_code<>?";
/* 126 */   public static String insertSubMarket = "insert into sub_market_mst(sub_market_code,sub_market_name,market_id,active,created_by,created_at) values (?,?,?,?,?,?)";
/* 127 */   public static String updateSubMarket = "update sub_market_mst set sub_market_name=?,market_id=?,active=?,updated_by=?,updated_at=?  where id=?";
/*     */ 
/*     */   
/* 130 */   public static String getPermitTypes = "select id,permit_type,permit_type_code,permit_type_name,permit_fee,active from permit_type_mst";
/* 131 */   public static String checkPermitTypeName = "select id from permit_type_mst where permit_type_name=?";
/* 132 */   public static String checkPermitTypecode = "select id from permit_type_mst where permit_type_code=?";
/* 133 */   public static String checkPermitNameByCode = "select id from permit_type_mst where permit_type_name=? and permit_type_code<>?";
/* 134 */   public static String insertPermitType = "insert into permit_type_mst(permit_type_code,permit_type_name,permit_fee,active,created_by,created_at,permit_type) values (?,?,?,?,?,?,?)";
/* 135 */   public static String updatePermitType = "update permit_type_mst set permit_type_name=?,permit_fee=?,active=?,updated_by=?,updated_at=?,permit_type=?  where id=?";
/*     */ 
/*     */   
/* 138 */   public static String insertApplication = "insert into application_mst(business_name,no_of_employees,area,electricity_no,water_acc_no,reg_no,permit_type_id,business_desc,postal_add,postal_code,ward_id,land_zone,plot_no,mobile_no,land_line_no,fax,email,active,created_by,created_at,national_id,application_fee,\r\n" + 
		"advertisement_fee,\r\n" + 
		"billboard_fee,\r\n" + 
		"tradename_fee,\r\n" + 
		"detail_activity,\r\n" + 
		"pin_number,applicant,vat_number) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
/*     */ 
/*     */ 
/*     */   
/* 142 */   public static String insertApplicationDtl = "insert into application_dtl(business_id,fee,penalty,app_type,status,created_by,created_on,previous_sbp,applied_for)values (?,?,?,?,?,?,?,?,?)";
/*     */ 
/*     */   
/* 145 */   public static String updateApplication = "update application_mst set business_name=?,no_of_employees=?,area=?,electricity_no=?,water_acc_no=?,reg_no=?,permit_type_id=?,business_desc=?,postal_add=?,postal_code=?,ward_id=?,land_zone=?,plot_no=?,mobile_no=?,land_line_no=?,fax=?,email=?,active=?,updated_by=?,updated_at=?,national_id=? ,application_fee=?,advertisement_fee=?,billboard_fee=?,tradename_fee=?,detail_activity=?,pin_number=? ,fax=?,applicant=?,vat_number=?where id=?";
/*     */ 
/*     */   
/* 148 */   public static String checkBusinessName = "select id from application_mst where business_name=?";
/* 149 */   public static String validateNationalId = "select id from users_mst where national_id_no=?";
/* 150 */   public static String getActivePermitTypes = "select id,permit_type_name,permit_fee from permit_type_mst where active=1 and permit_type=?";
/* 151 */   public static String getAllApplications = "select a.id,business_name,no_of_employees,business_no,national_id,area,electricity_no,water_acc_no,reg_no,permit_type_id,business_desc,postal_add,postal_code,ward_id,land_zone,plot_no,mobile_no,land_line_no,fax,email,a.active,p.permit_fee,case d.status when 'N'  then 'Pending' when 'A' then 'Approved' when 'R' then 'Rejected' when 'I' then 'Inspected' when 'P' then 'Paid' when 'RU' then 'Refer to User' when 'AM' then 'Amended' when 'RW' then 'Renew' end as invoice_status,a.created_by,application_fee,advertisement_fee,billboard_fee,tradename_fee,detail_activity,pin_number,applicant,vat_number  from application_mst a,permit_type_mst p,application_dtl d where p.id=a.permit_type_id and a.id=d.business_id";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public static String getAllAppByAgentId = "select a.id,business_name,no_of_employees,business_no,national_id,area,electricity_no,water_acc_no,reg_no,permit_type_id,business_desc,postal_add,postal_code,ward_id,land_zone,plot_no,mobile_no,land_line_no,fax,email,a.active,p.permit_fee,case d.status when 'N'  then 'Pending' when 'A' then 'Approved' when 'R' then 'Rejected' when 'I' then 'Inspected' when 'P' then 'Paid' when 'RU' then 'Refer to User' when 'RW' then 'Renew' when 'AM' then 'Amended' end as invoice_status,a.created_by  from application_mst a,permit_type_mst p,application_dtl d where p.id=a.permit_type_id and a.id=d.business_id and a.created_by=?";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   public static String getAllAppsForIndividual = "select a.id,business_name,no_of_employees,business_no,national_id,area,electricity_no,water_acc_no,reg_no,permit_type_id,business_desc,postal_add,postal_code,ward_id,land_zone,plot_no,mobile_no,land_line_no,fax,a.email,a.active,p.permit_fee,case d.status when 'N'  then 'Pending' when 'A' then 'Approved' when 'R' then 'Rejected' when 'I' then 'Inspected' when 'P' then 'Paid' when 'RU' then 'Refer to User' when 'AM' then 'Amended' when 'RW' then 'Renew' end as invoice_status,a.created_by  from application_mst a,permit_type_mst p,application_dtl d,users_mst u where p.id=a.permit_type_id and a.id=d.business_id and a.national_id=? and a.national_id=u.national_id_no";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 172 */   public static String checkBusinessNameById = "select id from application_Mst where business_name=? and id<>?";
/*     */   
/* 174 */   public static String getInvoices = "select  application_fee,advertisement_fee,billboard_fee, tradename_fee,a.id as business_id,app_type,d.id,app_no,business_no,business_name,d.created_on,penalty,postal_code,postal_add,a.email,mobile_no,u.username,no_of_employees,area,reg_no,electricity_no,water_acc_no,rejected_reason,previous_sbp,approved_by,approved_on,  ifnull((ifnull(penalty,0))+(fee),0) as total,paid_date ,receipt_by,applied_for,  case d.status when 'N'  then 'Pending' when 'A' then 'Approved' when 'R' then 'Rejected' when 'I' then 'Inspected' when 'P' then 'Paid' when 'RU' then 'Refer to User' when 'RW' then 'Renew' when 'AM' then 'Amended' end as invoice_status,case paid when 0 then 'Pending Payment' else 'Paid' end as paid_status,fee from application_mst a,application_dtl d,permit_type_mst p,users_mst u where p.id=a.permit_type_id and a.id=d.business_id and u.id=a.created_by and d.status!='C'";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 181 */   public static String getInvoicesByAgentId = "select a.id as business_id,app_type,d.id,app_no,business_no,business_name,d.created_on,penalty,postal_code,postal_add,a.email,mobile_no,u.username,no_of_employees,area,reg_no,electricity_no,water_acc_no,rejected_reason,previous_sbp,approved_by,approved_on,  ifnull((ifnull(penalty,0))+(fee),0) as total,paid_date ,receipt_by,applied_for,  case d.status when 'N'  then 'Pending' when 'A' then 'Approved' when 'R' then 'Rejected' when 'I' then 'Inspected' when 'P' then 'Paid' when 'RU' then 'Refer to User' when 'RW' then 'Renew' when 'AM' then 'Amended' end as invoice_status,case paid when 0 then 'Pending Payment' else 'Paid' end as paid_status,fee from application_mst a,application_dtl d,permit_type_mst p,users_mst u where p.id=a.permit_type_id and a.id=d.business_id and u.id=a.created_by and d.status!='C' and a.created_by=?";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 188 */   public static String getInvoicesByIndividual = "select a.id as business_id,app_type,d.id,app_no,business_no,business_name,d.created_on,penalty,postal_code,postal_add,a.email,mobile_no,u.username,no_of_employees,area,reg_no,electricity_no,water_acc_no,rejected_reason,previous_sbp,approved_by,approved_on,  ifnull((ifnull(penalty,0))+(fee),0) as total,paid_date ,receipt_by,applied_for,  case d.status when 'N'  then 'Pending' when 'A' then 'Approved' when 'R' then 'Rejected' when 'I' then 'Inspected' when 'P' then 'Paid' when 'RU' then 'Refer to User' when 'RW' then 'Renew' when 'AM' then 'Amended' end as invoice_status,case paid when 0 then 'Pending Payment' else 'Paid' end as paid_status,fee from application_mst a,application_dtl d,permit_type_mst p,users_mst u where p.id=a.permit_type_id and a.id=d.business_id and u.id=a.created_by and d.status!='C' and a.national_id=? and a.national_id=u.national_id_no";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 195 */   public static String getApprovedUser = "select username from users_mst um,application_dtl ad where um.id=ad.approved_by and approved_by=?";
/* 196 */   public static String getPaidUser = "select username from users_mst um,application_dtl ad where um.id=ad.receipt_by and receipt_by=?";
/* 197 */   public static String getWardCode = "select w.ward_code from application_mst a, ward_mst w where w.id=a.ward_id";
/* 198 */   public static String updateBusinessNo = "update application_mst set business_no=? where id=?";
/* 199 */   public static String getConfigValue = "select value from system_params where code=?";
/* 200 */   public static String getConfigPenaltyMonth = "select value from system_params where code=?";
/* 201 */   public static String updateAppNo = "update application_dtl set app_no=? where id=?";
/* 202 */   public static String updateApprovedStatus = "update application_dtl set status=?,approved_by=?,approved_on=?,total_in_words=? where business_Id=?";
/* 203 */   public static String updateRejectedStatus = "update application_dtl set status=?,rejected_reason=?,Rejected_by=?,Rejected_on=? where business_Id=?";
/* 204 */   public static String updatePaidStatus = "update application_dtl set status=?,receipt_no=?,paid=?,paid_date=?,receipt_by=?,receipt_on=?,bank_name=?,bank_acc_no=?,bank_trans_no=? where business_Id=?";
/* 205 */   public static String updateInspectedStatus = "update application_dtl set status=?,inspected_by=?,inspected_on=?,total_in_words=? where business_Id=?";
/* 206 */   public static String insertPermit = "insert into permit_mst (permit_no,app_id,business_id,validity,expiry_date,status,created_by,created_at) values (?,?,?,?,?,?,?,?)";
/* 207 */   public static String updatePermit = "update permit_mst set validity=?,expiry_date=?,status=?,updated_by=?,updated_at=? where permit_no=?";
/* 208 */   public static String validateMpesaCode = "select mpesa_code from mpesa_detail where mpesa_code=?";
/* 209 */   public static String updateReferUserStatus = "update application_dtl set status=?,refered_by=?,refered_at=? where business_id=?";
/* 210 */   public static String updateAmendedStatus = "update application_dtl set status=?,amended_by=?,amended_at=?,total_in_words=? where business_id=?";
/*     */ 
/*     */   
/* 213 */   public static String getPermitDetails = "select app_type,previous_sbp,permit_no,validity,expiry_date,a.business_name,a.business_desc,a.business_no,permit_status as newStatus ,a.email,a.reg_no,d.fee,d.app_no,a.postal_add,a.postal_code,username,plot_no,pm.business_id,case d.status when 'RW' then 'Renew' else 'New' end as permit_status from permit_mst pm,application_mst a,application_dtl d,users_mst u where a.id=pm.business_id and pm.app_id=d.id and u.id=pm.created_by";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 218 */   public static String getQrPermitNo = "select permit_no from permits_mst p , application_mst a where business_no=? and a.id=p.business_id";
/* 219 */   public static String updQrImagePath = "update permit_mst set qr_image=?,fee_inwords=? where permit_no=?";
/* 220 */   public static String validateRenewBusinessId = "select id from application_dtl where business_id=? and status='RW'";
/* 221 */   public static String updPermitStatus = "update permit_mst set permit_status=? where permit_no=?";
/* 222 */   public static String getPermitDetailsByDate = "call Prc_GetPermitDetailsByDate(?,?)";
/*     */ 
/*     */ 
/*     */   
/* 226 */   public static String getBranches = "SELECT B.Id, BranchCode, BranchName, merchantId,classId,Active, B.CreatedBy, B.CreatedOn From BranchMaster B where classid=? and merchantId=?";
/* 227 */   public static String insertBranches = "Insert into BranchMaster(BranchCode, BranchName, classId,merchantId,Active,CreatedBy, CreatedOn) Values(?,?,?,?,?,?,?)";
/* 228 */   public static String updateBranches = "Update BranchMaster  Set  BranchName=?,Active=? ,CreatedBy=?, CreatedOn=? Where Id=? and classId=? and merchantId=?";
/* 229 */   public static String getBranchByName = "Select [ID] From [dbo].[BranchMaster] Where [BranchName] = ? and classid=? and merchantid=?";
/* 230 */   public static String getCompanies = "SELECT Id,companyCode,CompanyName, CreatedBy, CreatedOn From CompanyMaster";
/*     */   
/* 232 */   public static String insertDeviceInfo = "Insert into devices(serial_no,status,created_by,created_at) Values(?,?,?,?)";
/* 233 */   public static String getDeviceSerialNo = "Select id From devices Where serial_no = ? ";
/* 234 */   public static String getDeviceSerialNoById = "Select id From devices Where serial_no = ? and id<>?";
/* 235 */   public static String updateDeviceInfo = "Update devices  Set serial_no=?, status=?, created_by=?, updated_at=? Where id=?";
/* 236 */   public static String getDevices = "SELECT id,serial_no,status, created_by, created_at From devices";
/*     */ 
/*     */   
/* 239 */   public static String getActiveDevices = "select D.Id,D.serial_no,D.status,D.created_by from devices D where status=1 and d.id not in (select dev_id from devices_issue) union select di.dev_id,d.serial_no,d.status,d.created_by from devices_issue di,devices d where di.dev_id=d.id and di.status=0";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 245 */   public static String checkUserAssignDevice = "select id from devices d where d.id in (select dev_id from devices_issue di where di.dev_id=d.id and di.status=1)and d.id=?";
/* 246 */   public static String checkUserReturnedStatus = "select * from devices_issue where issued_to=?";
/* 247 */   public static String checkDeviceReturnedStatus = "select * from devices_issue where dev_id=?";
/* 248 */   public static String updateReturnedUser = "update devices_issue set dev_id=?,status=?,created_by=?,updated_at=? where issued_to=?";
/* 249 */   public static String updateReturnedDevice = "update devices_issue set issued_to=?,status=?,created_by=?,updated_at=? where dev_id=?";
/*     */   
/* 251 */   public static String insertIssueDeviceInfo = "Insert into devices_issue(dev_id,issued_to,status,created_by,date_issued) Values(?,?,?,?,?)";
/* 252 */   public static String updateIssueDeviceInfo = "Update devices_issue set  issued_to=?,status=?,created_by=?,updated_at=? where id=?";
/*     */   
/* 254 */   public static String getIssueDevices = "select DI.id,DI.dev_id,d.serial_no,DI.status,DI.issued_to,UM.username,DI.created_by,mkt_name,name from devices_issue DI, devices d,users_mst UM,markets m where DI.dev_id=d.id and DI.issued_to=UM.id and DI.status=1 and m.id=um.market_id ";
/*     */ 
/*     */   
/* 257 */   public static String getUserDeviceSerialNo = "Select id from devices_issue  WHERE issued_to=? and dev_id=? ";
/*     */ 
/*     */   
/* 260 */   public static String getDeviceExists = "Select id from devices_issue  WHERE dev_id=? ";
/*     */   
/* 262 */   public static String validateUserId = "select id from devices_issue where issued_to=? and status=1";
/*     */   
/* 264 */   public static String validateDevId = "select id from devices_issue where dev_id=? and status=1";
/*     */ 
/*     */   
/* 267 */   public static String insertStockTakeDetails = "Insert into StockTakeDetails(StockTakeNo, PidNo,BatchNo,Quantity,LocationId,CreatedBy, CreatedOn) Values(?,?,?,?,?,?,?)";
/*     */ 
/*     */   
/* 270 */   public static String getServices = "SELECT Id, Service_Code, Service_Name, Active, Created_By, Created_at FROM Services_mst";
/* 271 */   public static String getServiceById = "SELECT Service_Code, Service_Name,Active,Created_By,Created_at FROM Services_mst WHERE ID=?";
/* 272 */   public static String getSubServiceById = "SELECT * FROM SubService_Map where parent_service_id=? ";
/* 273 */   public static String getServiceByName = "SELECT ID FROM Services_mst WHERE Service_Code=?";
/* 274 */   public static String insertServiceDetails = "INSERT INTO Services_mst(Service_Code, Service_Name, Active, Created_by,Created_at) VALUES (?,?,?,?,?)";
/* 275 */   public static String updateServiceDetails = "UPDATE Services_Mst SET Service_Code=?, Service_Name=?, Active=?, Created_By=?, Created_On=? WHERE ID=?";
/* 276 */   public static String insertSubServiceDetails = "INSERT INTO SubService_Map(Parent_Service_Id, SubService_Name,Has_Child,level,Active, Created_By,Created_at,PARENT_TYPE) VALUES (?,?,?,?,?,?,?,?)";
/* 277 */   public static String updateSubServiceDetails = "UPDATE SubService_Map SET  Active=?, Created_By=?,Created_at=?,level=?,has_child=? WHERE id=?";
/* 278 */   public static String getParams = "SELECT Id, Param_Name,param_type, Active,case when active=0 then 'Inactive' else 'Active' end as status, Created_By,Created_at FROM Param_Mst ";
/* 279 */   public static String insertParams = "INSERT INTO Param_Details(Param_Id,sub_service_Id, Active, Created_By,Created_at) Values (?,?,?,?,?)";
/* 280 */   public static String deleteParams = "Delete from Param_Details where sub_service_Id=?";
/* 281 */   public static String insertMatketPriceDtl = "insert into price_config(market_id,ser_id,fee,created_by,created_at) values (?,?,?,?,?)";
/* 282 */   public static String getMarketsForSubcounty = "SELECT m.id FROM sub_county_mst s ,ward_mst w,markets m where s.id=w.sub_county_id and w.id=m.ward_id and s.id=? and m.active=1";
/*     */   
/* 284 */   public static String getMarketsForWards = "SELECT m.id FROM ward_mst w,markets m where  w.id=m.ward_id and w.id=? and m.active=1";
/* 285 */   public static String getMarketPriceDetails = "select p.ser_id,s.subservice_name,s.active,p.fee from price_config p , subservice_map s where market_id=? and s.id=p.ser_id and level=-1  union select s.id as ser_id,s.subservice_name,s.active,0 as fee from  subservice_map s,price_config p where s.id in(select distinct ser_id from price_config where market_id <> ? and ser_id not in (select distinct ser_id from price_config where market_id = ?)) and level=-1 ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 293 */   public static String getServiceParams = "select PD.Param_Id,PM.Param_Name,pd.active from Param_mst PM,Param_Details PD WHERE PD.Param_Id=PM.Id AND pd.active=1 AND sub_Service_Id=? UNION ALL select PD.Param_Id,PM.Param_Name,pd.active from Param_Mst PM,Param_Details PD WHERE PD.Param_Id=PM.Id  AND pd.active=0 AND sub_Service_Id=?";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 298 */   public static String getActiveParams = "select PD.Param_Id,PM.Param_Name from Param_Master PM,Param_Details PD WHERE PD.Param_Id=PM.Id AND pd.active=1 AND sub_Service_Id=? AND PM.ACTIVE=1";
/*     */ 
/*     */ 
/*     */   
/* 302 */   public static String getParamName = "Select ID From Param_Mst Where param_Name = ? ";
/* 303 */   public static String getParamNameById = "Select ID From Param_Mst Where param_Name = ? and id<>?";
/* 304 */   public static String insertParamInfo = "Insert into Param_Mst(param_name,param_type,Active,Created_By, Created_at) Values(?,?,?,?,?)";
/* 305 */   public static String updateParamInfo = "Update Param_Mst  Set param_name=?,param_type=?, Active=?, Created_By=?, Created_at=? Where Id=?";
/*     */ 
/*     */   
/* 308 */   public static String getUserTypes = " SELECT Id, type_name FROM User_Type_Mst";
/*     */ 
/*     */   
/* 311 */   public static String getAllUserTransactions = "select u.UserName,count(bill_no) as tickets,SUM(amount) as txnTotal from bills_mst bm, UserMaster u where bm.user_id=u.Id and userTypeid=1 group by u.UserName ";
/* 312 */   public static String getUserTxnsDetails = "{call PRC_GetUserTransaction (?,?,?)}";
/*     */ 
/*     */   
/* 315 */   public static String getCurrentDateTxns = " select ifnull(SUM(amount),0) as totalCollected,COUNT(id) as totalBills, (select ifnull(SUM(amount),0) from transaction_mst where voided=1  and DATE_FORMAT(transaction_date,'%m-%d-%Y')=DATE_FORMAT(NOW(),'%m-%d-%Y')) as voidAmount, (select ifnull(SUM(amount),0) as totalInvalid from transaction_mst where receipt_printed=0 and DATE_FORMAT(transaction_date,'%m-%d-%Y')=DATE_FORMAT(NOW(),'%m-%d-%Y')) as  totalInvalid from transaction_mst where DATE_FORMAT(transaction_date,'%m-%d-%Y')=DATE_FORMAT(NOW(),'%m-%d-%Y') ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 322 */   public static String getAmountDetails = "select 'TOTAL AMOUNT' AS NAME,IfNULL(SUM(AMOUNT),0) AS COUNTNO from transaction_mst  M where DATE_FORMAT(transaction_date,'%m-%d-%Y')=DATE_FORMAT(NOW(),'%m-%d-%Y')   UNION  select 'NET AMOUNT' AS NAME,IfNULL(SUM(AMOUNT),0) AS COUNTNO from transaction_mst  M  WHERE VOIDED=0 and receipt_printed=1 and DATE_FORMAT(transaction_date,'%m-%d-%Y')=DATE_FORMAT(NOW(),'%m-%d-%Y') UNION   select 'VOIDED AMOUNT' AS NAME,IfNULL(SUM(AMOUNT),0) AS COUNTNO from transaction_mst  M  WHERE VOIDED=1 and DATE_FORMAT(transaction_date,'%m-%d-%Y')=DATE_FORMAT(NOW(),'%m-%d-%Y')   UNION  select 'INVALID AMOUNT' AS NAME,IfNULL(SUM(AMOUNT),0) AS COUNTNO from transaction_mst  M  WHERE receipt_printed=0 and receipt_printed=1 and DATE_FORMAT(transaction_date,'%m-%d-%Y')=DATE_FORMAT(NOW(),'%m-%d-%Y')";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 329 */   public static String getDashBoardDetailCount = "select 'MARKETS' AS NAME,COUNT(M.ID) AS COUNTNO from markets M UNION  SELECT 'USERS' AS NAME,COUNT(U.ID) AS COUNTNO FROM Users_mst U UNION  SELECT 'REGISTERED DEVICES' AS NAME,COUNT(D.ID) AS COUNTNO FROM  Devices D  where status=1 UNION SELECT 'SERVICES' AS NAME,COUNT(ID) AS COUNTNO FROM SUBSERVICE_MAP WHERE LEVEL=-1 ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 337 */   public static String getFlowChartDetails = "select sm.Service_Name as service,count(bm.bill_no) as trans_count  from Services_mst sm inner join transaction_mst bm on bm.service_id = sm.Id GROUP BY sm.Service_Name order by trans_count desc";
/*     */   
/* 339 */   public static String getTransChartDetail = "select distinct monthname(current_date) as Month, (select IfNULL(SUM(AMOUNT),0) AS COUNTNO from transaction_mst  M where month(transaction_date) = month(current_date)) as totalamount, (select IfNULL(SUM(AMOUNT),0) AS COUNTNO from transaction_mst  M  WHERE VOIDED=1 and month(transaction_date) = month(current_date)) as voidamount, (select IfNULL(SUM(AMOUNT),0) AS COUNTNO from transaction_mst  M  WHERE receipt_printed=0 and month(transaction_date) = month(current_date)) as invalidamount, (select IfNULL(SUM(AMOUNT),0) AS COUNTNO from transaction_mst  M  WHERE VOIDED=0 and receipt_printed=1 and month(transaction_date) = month(current_date)) as netamount from transaction_mst where month(transaction_date) = month(current_date)";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 346 */   public static String getCurrentMonthStatistics = "select name,total from (select distinct s.name,(select sum(amount) from transaction_mst bm ,ward_mst wm,sub_county_mst sm,markets mm where  VOIDED=0 and receipt_printed=1 and bm.market_id=mm.id and bm.market_id=b.market_id and mm.ward_id=wm.id and wm.sub_county_id=sm.id and month(transaction_date)=month(CURRENT_DATE)) as total from transaction_mst b, ward_mst w ,sub_county_mst s,markets m where b.market_id=m.id and m.ward_id=w.id and w.sub_county_id=s.id and month(transaction_date)=month(CURRENT_DATE)union  select s.name,0 as amount from sub_county_mst s,ward_mst w,markets m where s.id=w.sub_county_id and m.ward_id=w.id and m.id not in (select market_id from transaction_mst where month(transaction_date)=month(CURRENT_DATE))) s group by name order by total desc ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 354 */   public static String getPreviousMonthStatistics = "select name,total from (select distinct s.name,(select sum(amount) from transaction_mst bm ,ward_mst wm,sub_county_mst sm,markets mm where  VOIDED=0 and receipt_printed=1 and bm.market_id=mm.id and bm.market_id=b.market_id and mm.ward_id=wm.id and wm.sub_county_id=sm.id and month(transaction_date)=month(CURRENT_DATE - INTERVAL 1 MONTH)) as total from transaction_mst b, ward_mst w ,sub_county_mst s,markets m where b.market_id=m.id and m.ward_id=w.id and w.sub_county_id=s.id and month(transaction_date)=month(CURRENT_DATE - INTERVAL 1 MONTH)union  select s.name,0 as amount from sub_county_mst s,ward_mst w,markets m where s.id=w.sub_county_id and m.ward_id=w.id and m.id not in (select market_id from transaction_mst where month(transaction_date)=month(CURRENT_DATE - INTERVAL 1 MONTH))) s group by name order by total desc  ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 361 */   public static String getTwoMonthStatistics = "select name,total from (select distinct s.name,(select sum(amount) from transaction_mst bm ,ward_mst wm,sub_county_mst sm,markets mm where  VOIDED=0 and receipt_printed=1 and bm.market_id=mm.id and bm.market_id=b.market_id and mm.ward_id=wm.id and wm.sub_county_id=sm.id and month(transaction_date)=month(CURRENT_DATE - INTERVAL 2 MONTH)) as total from transaction_mst b, ward_mst w ,sub_county_mst s,markets m where b.market_id=m.id and m.ward_id=w.id and w.sub_county_id=s.id and month(transaction_date)=month(CURRENT_DATE - INTERVAL 2 MONTH)union  select s.name,0 as amount from sub_county_mst s,ward_mst w,markets m where s.id=w.sub_county_id and m.ward_id=w.id and m.id not in (select market_id from transaction_mst where month(transaction_date)=month(CURRENT_DATE - INTERVAL 2 MONTH)))s group by name order by total desc ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 369 */   public static String getUserDscStatistics = "select distinct um.name,(select sum(amount) from transaction_mst t, users_mst u where u.id=t.created_by and VOIDED=0 and receipt_printed=1 and u.id=um.id) as amount,(select count(t.id) from transaction_mst t, users_mst u where u.id=t.created_by and VOIDED=0 and receipt_printed=1 and u.id=um.id) as tickets from transaction_mst tm,users_mst um where um.id=tm.created_by and VOIDED=0 and receipt_printed=1 order by amount desc limit 3 ";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 374 */   public static String getUserAscStatistics = "select distinct um.name,(select sum(amount) from transaction_mst t, users_mst u  where u.id=t.created_by and VOIDED=0 and receipt_printed=1 and u.id=um.id) as amount,(select count(t.id) from transaction_mst t, users_mst u where u.id=t.created_by and VOIDED=0 and receipt_printed=1 and u.id=um.id) as tickets from transaction_mst tm,users_mst um where um.id=tm.created_by and VOIDED=0 and receipt_printed=1 order by amount asc limit 3 ";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 379 */   public static String getTotalCollectionInfo = "select (sum(penalty)+sum(fee)) as collection, 'SBP Collections' as name from  application_dtl where status='p' union select (sum(penalty)+sum(fee)) as collection,'LandRates Collections' as name from  land_registration_dtl where status='p'union select sum(amount) as collection,'POS Collections' from transaction_mst where  receipt_printed=1 AND voided=0";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 385 */   public static String getAllDeviceTransactions = "select d.SerialNo,count(bill_no) as tickets,SUM(amount) as txnTotal from bills_mst bm, DeviceRegDetails D where bm.dev_id=d.id group by d.SerialNo";
/* 386 */   public static String getDeviceTxnsDetails = "{call PRC_GetDeviceTransaction (?,?,?)}";
/*     */ 
/*     */   
/* 389 */   public static String getAllGateTransactions = "select e.gateName,count(bill_no) as tickets,SUM(amount) as txnTotal from bills_mst bm, entryPointmaster E where bm.market_id=e.id group by e.gateName";
/* 390 */   public static String getGateTxnsDetails = "{call PRC_GetGateTransaction (?,?,?)}";
/*     */ 
/*     */ 
/*     */   
/* 394 */   public static String getAllTransactions = "{call Prc_GetAllTransaction (?,?)}";
/* 395 */   public static String getZDetails = "{call Prc_GetZdetailsAll (?,?)}";
/*     */ 
/*     */   
/* 398 */   public static String getParentServices = "SELECT [Id], [ServiceCode], [ServiceName], [Active], [CreatedBy], [CreatedOn] FROM [dbo].[ServiceMaster] where active=1";
/* 399 */   public static String getSubServices = "SELECT * FROM SubServiceMap WHERE ParentServiceId=? AND Active=1 AND level=-1";
/* 400 */   public static String getAllServiceTransactions = "select s.ServiceName,count(bill_no) as tickets,SUM(amount) as txnTotal from bills_mst bm, ServiceMaster s where bm.service_id=s.id group by s.ServiceName";
/* 401 */   public static String getServiceTxnsDetails = "{call PRC_GetServiceTransaction (?,?,?,?)}";
/*     */ 
/*     */   
/* 404 */   public static String getAllSubServices = "SELECT * From SUBSERVICE_MAP";
/* 405 */   public static String updateParentSerInfo = "uPdate services_mst set service_name=? , active=?,modified_By=?,modified_On=? where id=?";
/* 406 */   public static String updateSubSerInfo = "uPdate subservice_map set subservice_name=? , active=?,modified_By=?,modified_On=? where id=?";
/* 407 */   public static String deleteMarketPriceDtl = "delete from price_config where market_id=?";
/*     */ 
/*     */   
/* 410 */   public static String gateServiceTxnsDetails = "{call PRC_GetGATE_ServiceDetailes (?,?,?,?,?)}";
/* 411 */   public static String gateServiceSummary = "{call PRC_GetGATE_ServiceSummary (?,?,?,?,?)}";
/*     */ 
/*     */   
/* 414 */   public static String userServiceTxnsDetails = "{call PRC_GetService_User (?,?,?,?,?,?)}";
/* 415 */   public static String userServiceSummary = "{call PRC_GetService_UserSummary (?,?,?,?,?,?)}";
/*     */ 
/*     */   
/* 418 */   public static String deviceServiceSummary = "{call PRC_GetServicePerDevice(?,?,?,?,?,?)}";
/*     */ 
/*     */   
/* 421 */   public static String insertRegistrationForm = "insert into land_registration_mst(plot_number,mapsheet_number,location,acreage,title_deed_number,land_type_id,name,kra_pin,national_id_number,ward_id,subCounty_id,created_by,address,code,created_at) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
/*     */ 
/*     */ 
/*     */   
/* 425 */   public static String updateRegistrationForm = "update land_registration_mst set plot_number=?,mapsheet_number=?,location=?,acreage=?,title_deed_number=?,name=?,kra_pin=?,national_id_number=?,ward_id=?,subCounty_id=?,created_by=?,address=?,code=?,created_at=? where id=?";
/*     */ 
/*     */   
/* 428 */   public static String checkPlotNumber = "select id from land_registration_mst where plot_number=? ";
/*     */   
/* 430 */   public static String checkTitleDeedNumber = "select id from land_registration_mst where title_deed_number=?";
/*     */   
/* 432 */   public static String getAllRegistrations = "select code,address,lr.id,plot_number,mapsheet_number,location,acreage,title_deed_number,land_type_id,name,kra_pin,national_id_number,ward_id,subCounty_id,case d.status when 'N'  then 'Pending' when 'A' then 'Approved' when 'R' then 'Rejected' when 'I' then 'Inspected' when 'P' then 'Paid' when 'RU' then 'Refer to User' when 'RW' then 'Renew' when 'AM' then 'Amended' end as invoice_status,lr.created_by from land_registration_mst lr,land_registration_dtl d where  lr.id=d.land_id";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 440 */   public static String getLandInvoices = "select lr.id as land_id,balance,d.paid_amount,land_type,d.id,reg_no,land_no,plot_number,d.created_on,penalty,s.name,w.ward_name,p.permit_type_name,lr.name as owner,\r\n" + 
		"lr.mapsheet_number,u.name,u.username,location,acreage,title_deed_number,lr.name,kra_pin,national_id_number,rejected_reason,previous_lr,land_type_id,\r\n" + 
		"approved_by,approved_on,ward_id,subCounty_id,ifnull((ifnull(penalty,0))+(fee),0) as total,paid_date ,applied_for,balance,d.paid_amount,\r\n" + 
		"case d.status when 'N'  then 'Pending' when 'A' then 'Approved' when 'R' then 'Rejected' when 'I' then 'Inspected' \r\n" + 
		"when 'P' then 'Paid' when 'RU' then 'Refer to User' when 'RW' then 'Renew' when 'AM' then 'Amended' end as invoice_status,case paid when 0 then 'Pending Payment' else 'Paid' end as paid_status,fee,d.receipt_by \r\n" + 
		"from land_registration_mst lr  inner join land_registration_dtl d on lr.id=d.land_id  left join permit_type_mst p on  p.id=lr.land_type_id inner join users_mst u on u.id=lr.created_by left join sub_county_mst s on s.id=lr.subCounty_id left join  ward_mst w on w.id=lr.ward_id \r\n" + 
		"where d.status!='C' group by reg_no";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 447 */   public static String getLandInvoicesByAgentId = "select lr.id as land_id,d.id,reg_no,land_no,plot_number,d.created_on,penalty,s.name,w.ward_name,p.permit_type_name,lr.mapsheet_number,u.name,u.username,location,acreage,title_deed_number,lr.name,kra_pin,national_id_number,rejected_reason,previous_lr,land_type_id,approved_by,approved_on,ward_id,subCounty_id,ifnull((ifnull(penalty,0))+(fee),0) as total,paid_date ,applied_for,case d.status when 'N'  then 'Pending' when 'A' then 'Approved' when 'R' then 'Rejected' when 'I' then 'Inspected' when 'P' then 'Paid' when 'RU' then 'Refer to User' when 'RW' then 'Renew' when 'AM' then 'Amended' end as invoice_status,case paid when 0 then 'Pending Payment' else 'Paid' end as paid_status,fee,d.receipt_by from land_registration_mst lr,land_registration_dtl d,users_mst u,sub_county_mst s, ward_mst w ,permit_type_mst p where p.id=lr.land_type_id and lr.id=d.land_id and u.id=lr.created_by and d.status!='C' and s.id=subCounty_id and w.id=ward_id and w.sub_county_id=s.id and lr.created_by=?";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 454 */   public static String getLandInvoicesByIndividual = "select lr.id as land_id,d.id,reg_no,land_no,plot_number,d.created_on,penalty,s.name,w.ward_name,lr.mapsheet_number,u.name,u.username,location,acreage,title_deed_number,lr.name,kra_pin,national_id_number,rejected_reason,previous_lr,land_type_id,p.permit_type_name,approved_by,approved_on,ward_id,subCounty_id,ifnull((ifnull(penalty,0))+(fee),0) as total,paid_date ,applied_for,case d.status when 'N'  then 'Pending' when 'A' then 'Approved' when 'R' then 'Rejected' when 'I' then 'Inspected' when 'P' then 'Paid' when 'RU' then 'Refer to User' when 'RW' then 'Renew' when 'AM' then 'Amended' end as invoice_status,case paid when 0 then 'Pending Payment' else 'Paid' end as paid_status,fee,d.receipt_by from land_registration_mst lr,land_registration_dtl d,users_mst u,sub_county_mst s, ward_mst w ,permit_type_mst p where p.id=lr.land_type_id and lr.id=d.land_id and u.id=lr.created_by and d.status!='C' and s.id=subCounty_id and w.id=ward_id and w.sub_county_id=s.id and lr.national_id_number=u.national_id_no and lr.national_id_number=?";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 461 */   public static String updateLandApprovedStatus = "update land_registration_dtl set status=?,approved_by=?,approved_on=? where id=?";
/*     */   
/* 463 */   public static String updateLandAmendedStatus = "update land_registration_dtl set status=?,amended_by=?,amended_at=? where land_id=?";
/*     */   
/* 465 */   public static String updateLandPaidStatus = "update land_registration_dtl set status=?,receipt_no=?,paid=?,paid_date=?,receipt_by=?,receipt_on=?,bank_name=?,bank_acc_no=?,bank_trans_no=?,paid_amount=? where id=?";
/*     */   
/* 467 */   public static String updateLandRejectedStatus = "update land_registration_dtl set status=?,rejected_reason=?,Rejected_by=?,Rejected_on=? where id=?";
/*     */   
/* 469 */   public static String updateLandInspectedStatus = "update land_registration_dtl set status=?,inspected_by=?,inspected_on=? where id=?";
/*     */   
/* 471 */   public static String updateLandReferUser = "update land_registration_dtl set status=?,refered_by=?,refered_at=? where id=?";
/*     */   
/* 473 */   public static String updatelandPermit = "update land_permit_mst set validity=?,status=?,updated_by=?,updated_at=? where land_permit_no=?";
/* 474 */   public static String insertlandPermit = "insert into land_permit_mst (land_permit_no,land_id,reg_id,validity,status,created_by,created_at) values (?,?,?,?,?,?,?)";
/*     */   
/* 476 */   public static String getlandWard = "select w.ward_code from land_registration_mst a, ward_mst w where w.id=a.ward_id";
/*     */   
/* 478 */   public static String updateLandNo = "update land_registration_mst set land_no=? where id=?";
/*     */   
/* 480 */   public static String updateRegNo = "update land_registration_dtl set reg_no=? where id=?";
/*     */   
/* 482 */   public static String getlandCountyCode = "select value from system_params where code='001'";
/*     */   
/* 484 */   public static String insertlandregistrationDtl = "insert into land_registration_dtl(land_id,fee,penalty,land_type,status,created_by,created_on,previous_lr,applied_for)values (?,?,?,?,?,?,?,?,?)";
/*     */ 
/*     */ 
/*     */   
/* 488 */   public static String getLandApprovedUser = "select username from users_mst um,land_registration_dtl lrd where um.id=lrd.approved_by and approved_by=?";
/*     */   
/* 490 */   public static String getPaidLandUser = "select username from users_mst um,land_registration_dtl ld where um.id=ld.receipt_by and receipt_by=?";
/*     */   
/* 492 */   public static String getLandDetails = "select lr.plot_number,validity,lr.title_deed_number,lr.land_type_id,lr.acreage,lr.land_no,username,lr.name,d.reg_no,applied_for,d.status,d.land_id,d.fee from land_registration_mst lr,land_registration_dtl d,users_mst u where lr.id=d.land_id and u.id=d.created_by and d.status=?";
/*     */ 
/*     */ 
/*     */   
/* 496 */   public static String getLandPermitDetails = "select land_type,previous_lr,land_permit_no,lr.plot_number,lpm.validity,lr.title_deed_number,permit_status as newStatus ,lr.land_type_id,lr.acreage,lr.mapsheet_number,lr.location,lr.land_no,username,lr.name,lr.kra_pin,d.reg_no,applied_for,d.status,d.land_id,case d.status when 'RW' then 'Renew' else 'New' end as permit_status,d.fee from land_permit_mst lpm,land_registration_mst lr,land_registration_dtl d,users_mst u where lr.id=lpm.land_id and lpm.reg_id=d.id and u.id=lpm.created_by ";
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 501 */   public static String checkPlotNumberByTitleDeed = "select id from land_registration_mst where plot_number=? and title_deed_number=? and id<>?";
/*     */   
/* 503 */   public static String getAllLandByAgentId = "select lr.id,plot_number,mapsheet_number,location,acreage,land_no,title_deed_number,land_type_id,lr.name,kra_pin,national_id_number,ward_id,subCounty_id,fee,case d.status when 'N'  then 'Pending' when 'A' then 'Approved' when 'R' then 'Rejected' when 'I' then 'Inspected' when 'P' then 'Paid' when 'RU' then 'Refer to User' when 'AM' then 'Amended' end as invoice_status,lr.created_by  from land_registration_mst lr,permit_type_mst p,land_registration_dtl d where p.id=lr.land_type_id and lr.id=d.land_id and lr.created_by=?";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 511 */   public static String getAllLandsForIndividual = "select lr.id,plot_number,mapsheet_number,location,acreage,land_no,title_deed_number,land_type_id,lr.name,kra_pin,national_id_number,ward_id,subCounty_id,fee,u.name,u.national_id_no,case d.status when 'N'  then 'Pending' when 'A' then 'Approved' when 'R' then 'Rejected' when 'I' then 'Inspected' when 'P' then 'Paid' when 'RU' then 'Refer to User' when 'AM' then 'Amended' end as invoice_status,lr.created_by  from land_registration_mst lr,permit_type_mst p ,land_registration_dtl d ,users_mst u where p.id=lr.land_type_id and lr.id=d.land_id and lr.national_id_number=? and lr.national_id_number=u.national_id_no";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 519 */   public static String validateLandNationalId = "select id from users_mst where national_id_no=?";
/*     */   
/* 521 */   public static String validateRenewLandId = "select id from land_registration_dtl where land_id=? and status='RW'";

public static String insertlandregistrationDtlUpload = "insert into land_registration_dtl(land_id,fee,penalty,land_type,status,"
		+ "created_by,created_on,previous_lr,applied_for,balance,paid_amount,paid,receipt_by)"
		+ "values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
public static String insertUploadedPlot="INSERT INTO land_registration_mst(plot_number,name,national_id_number,kra_pin,address,phone,rates,title_deed_number,arrears,total,land_type_id,created_at,created_by,subCounty_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
public static String insertUploadedLand="INSERT INTO land_registration_mst(name,national_id_number,kra_pin,address,phone,blocknumber,acreage,title_deed_number,plot_number,mapsheet_number,rates,arrears,total,land_type_id,created_at,created_by,subCounty_id) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
public static String insertfileDtl="INSERT into file_import(file_name,uploaded_by,uploaded_on) values(?,?,?)";
/*     */ }


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\operations\Queryconstants.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */