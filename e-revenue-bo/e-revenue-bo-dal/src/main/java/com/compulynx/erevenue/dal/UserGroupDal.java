package com.compulynx.erevenue.dal;

import com.compulynx.erevenue.models.ErevenueResponse;
import com.compulynx.erevenue.models.RightsDetail;
import com.compulynx.erevenue.models.UserGroup;
import java.util.List;

public interface UserGroupDal {
  ErevenueResponse UpdateUserGroup(UserGroup paramUserGroup);
  
  boolean checkGroupByName(String paramString);
  
  List<UserGroup> GetUserGroups();
  
  List<RightsDetail> GetRights();
}


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\UserGroupDal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */