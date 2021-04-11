package com.compulynx.erevenue.bal;

import com.compulynx.erevenue.models.DashBoard;
import java.util.List;

public interface DashBoardBal {
  List<DashBoard> GetDashBoardCountDetail();
  
  List<DashBoard> GetTransChartDetail();
  
  List<DashBoard> GetDashBoardAmountDetail();
  
  List<DashBoard> GetFlowChartCountDetail();
  
  DashBoard GetMonthStatisticsDetails();
  
  DashBoard GetUserStatisticsDetails();
  
  List<DashBoard> GetDashBoardCollectionDetail();
}


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\DashBoardBal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */