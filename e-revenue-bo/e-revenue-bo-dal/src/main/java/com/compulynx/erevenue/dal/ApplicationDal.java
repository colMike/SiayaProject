package com.compulynx.erevenue.dal;

import com.compulynx.erevenue.models.Application;
import com.compulynx.erevenue.models.ErevenueResponse;
import com.compulynx.erevenue.models.PermitType;
import com.compulynx.erevenue.models.Reports;
import com.compulynx.erevenue.models.Ward;
import java.util.List;

public interface ApplicationDal {
  ErevenueResponse UpdateApplication(Application paramApplication);
  
  List<PermitType> GetActivePermitTypes(String paramString);
  
  List<Application> GetAllApplications();
  
  List<Application> GetAllInvoices();
  
  ErevenueResponse UpdateInvocieStatus(Application paramApplication);
  
  List<Application> GetAllPermits();
  
  ErevenueResponse UpdateQrImagePath(Application paramApplication);
  
  ErevenueResponse UpdatePermitRenewal(Application paramApplication);
  
  List<Application> GetAllAppsByLinkId(int paramInt1, String paramString, int paramInt2);
  
  ErevenueResponse UpdatePermitStatus(Application paramApplication);
  
  List<Ward> GetWardsBySubCounty(int paramInt);
  
  List<Application> GetPermitsByDate(Reports paramReports);
  
  List<Application> GetAllInvoicesByLinkId(int paramInt1, String paramString, int paramInt2);
}


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\ApplicationDal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */