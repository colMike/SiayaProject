package com.compulynx.erevenue.dal;

import com.compulynx.erevenue.models.ErevenueResponse;
import com.compulynx.erevenue.models.Market;
import com.compulynx.erevenue.models.SubMarket;
import java.util.List;

public interface SubMarketDal {
  ErevenueResponse UpdateSubMarket(SubMarket paramSubMarket);
  
  List<SubMarket> GetAllSubMarkets();
  
  List<Market> GetActiveMarkets();
}


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\SubMarketDal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */