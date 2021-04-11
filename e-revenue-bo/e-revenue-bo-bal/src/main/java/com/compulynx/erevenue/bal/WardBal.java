package com.compulynx.erevenue.bal;

import com.compulynx.erevenue.models.ErevenueResponse;
import com.compulynx.erevenue.models.SubCounty;
import com.compulynx.erevenue.models.Ward;
import java.util.List;

public interface WardBal {
  ErevenueResponse UpdateWard(Ward paramWard);
  
  List<Ward> GetAllWards();
  
  List<SubCounty> GetActiveSubCounties();
}


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\WardBal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */