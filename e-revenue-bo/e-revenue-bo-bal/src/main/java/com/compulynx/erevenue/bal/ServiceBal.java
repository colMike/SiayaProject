package com.compulynx.erevenue.bal;

import com.compulynx.erevenue.models.ErevenueResponse;
import com.compulynx.erevenue.models.Params;
import com.compulynx.erevenue.models.Service;
import java.util.List;

public interface ServiceBal {
  ErevenueResponse UpdateService(Service paramService);
  
  List<Service> GetAllServices();
  
  List<Params> GetAllParams();
  
  List<Service> GetSubServiceById(int paramInt);
  
  ErevenueResponse UpdateParam(Params paramParams);
  
  List<Service> GetAllSubServices();
  
  ErevenueResponse UpdateSerPrice(Service paramService);
  
  List<Service> GetMarketSubServices(int paramInt);
}


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\ServiceBal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */