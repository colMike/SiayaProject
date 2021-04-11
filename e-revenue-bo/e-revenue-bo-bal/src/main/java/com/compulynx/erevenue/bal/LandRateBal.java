package com.compulynx.erevenue.bal;

import com.compulynx.erevenue.models.ErevenueResponse;
import com.compulynx.erevenue.models.LandRate;
import java.util.List;

public interface LandRateBal {
  ErevenueResponse CreateRegistrationForm(LandRate paramLandRate);
  
  List<LandRate> GetAllRegs();
  
  List<LandRate> GetAllLandInvoices();
  
  ErevenueResponse UpdateLandInvocieStatus(LandRate paramLandRate);
  
  ErevenueResponse UpdateLandPermitRenewal(LandRate paramLandRate);
  
  List<LandRate> GetPaidLandDetails();
  
  List<LandRate> GetAllLandPermits();
  
  List<LandRate> GetAllLandsByLinkId(int paramInt1, String paramString, int paramInt2);
  
  List<LandRate> GetAllLandInvoicesByLinkId(int paramInt1, String paramString, int paramInt2);
}


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\LandRateBal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */