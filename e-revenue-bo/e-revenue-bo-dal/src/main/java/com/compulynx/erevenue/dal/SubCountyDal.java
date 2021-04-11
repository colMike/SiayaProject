package com.compulynx.erevenue.dal;

import com.compulynx.erevenue.models.ErevenueResponse;
import com.compulynx.erevenue.models.SubCounty;
import java.util.List;

public interface SubCountyDal {
  ErevenueResponse UpdateSubCounty(SubCounty paramSubCounty);
  
  List<SubCounty> GetAllSubCounties();
}


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\SubCountyDal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */