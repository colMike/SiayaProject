package com.compulynx.erevenue.bal;

import com.compulynx.erevenue.models.ErevenueResponse;
import com.compulynx.erevenue.models.PermitType;
import java.util.List;

public interface PermitTypeBal {
  ErevenueResponse UpdatePermitType(PermitType paramPermitType);
  
  List<PermitType> GetAllPermitTypes();
}


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\PermitTypeBal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */