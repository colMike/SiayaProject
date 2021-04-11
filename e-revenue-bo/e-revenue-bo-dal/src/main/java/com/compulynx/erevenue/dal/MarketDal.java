package com.compulynx.erevenue.dal;

import com.compulynx.erevenue.models.ErevenueResponse;
import com.compulynx.erevenue.models.Market;
import com.compulynx.erevenue.models.Ward;
import java.util.List;

public interface MarketDal {
  ErevenueResponse UpdateMarket(Market paramMarket);
  
  List<Market> GetAllMarkets();
  
  List<Ward> GetActiveWards();
}


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\MarketDal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */