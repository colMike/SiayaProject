package com.compulynx.erevenue.dal;

import com.compulynx.erevenue.models.Reports;
import com.compulynx.erevenue.models.ZDetails;
import java.util.List;

public interface ReportsDal {
  List<Reports> GetAllUserTxn();
  
  List<Reports> GetUserTxnsDetails(Reports paramReports);
  
  List<Reports> GetAllDeviceTxn();
  
  List<Reports> GetDeviceTxnsDetails(Reports paramReports);
  
  List<Reports> GetAllTxnsDetails(Reports paramReports);
  
  List<ZDetails> GetZDetails(ZDetails paramZDetails);
  
  List<Reports> GetCurrentTxnsDetails();
}


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\ReportsDal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */