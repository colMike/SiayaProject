package com.compulynx.erevenue.bal;

import com.compulynx.erevenue.models.ConfigParams;
import com.compulynx.erevenue.models.ErevenueResponse;
import com.compulynx.erevenue.models.LoginSession;
import com.compulynx.erevenue.models.LoginUser;
import java.util.List;

public interface LoginBal {
  LoginUser ValidateManualLogin(String paramString1, String paramString2);
  
  LoginSession GetUserAssgnRightsList(int paramInt);
  
  List<ConfigParams> GetConfigParams();
  
  ErevenueResponse UpdateConfigParam(ConfigParams paramConfigParams);
}


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\LoginBal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */