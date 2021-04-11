package com.compulynx.erevenue.dal;

import com.compulynx.erevenue.models.ErevenueResponse;
import com.compulynx.erevenue.models.Params;
import com.compulynx.erevenue.models.Service;
import java.util.List;

public interface ServiceDal {
  ErevenueResponse UpdateService(Service paramService);
  
  List<Service> GetAllServices();
  
  Service GetServiceById(int paramInt);
  
  List<Params> GetAllParams();
  
  List<Service> GetSubServiceById(int paramInt);
  
  ErevenueResponse UpdateParam(Params paramParams);
  
  List<Service> GetAllSubServices();
  
  ErevenueResponse UpdateSerPrice(Service paramService);
  
  List<Service> GetMarketSubServices(int paramInt);
}


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\ServiceDal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */