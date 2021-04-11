package com.compulynx.erevenue.dal;

import com.compulynx.erevenue.models.ErevenueResponse;
import com.compulynx.erevenue.models.PermitType;
import java.util.List;

public interface PermitTypeDal {
  ErevenueResponse UpdatePermitType(PermitType paramPermitType);
  
  List<PermitType> GetAllPermitTypes();
}


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\PermitTypeDal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */