package com.compulynx.erevenue.bal;

import com.compulynx.erevenue.models.ErevenueResponse;
import com.compulynx.erevenue.models.User;
import com.compulynx.erevenue.models.UserGroup;
import com.compulynx.erevenue.models.UserType;
import java.util.List;

public interface UserBal {
  ErevenueResponse UpdateUser(User paramUser);
  
  List<User> GetAllUsers();
  
  List<UserGroup> GetActiveGroups();
  
  List<UserType> GetUserTypes();
}


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-bal-0.0.1.jar!\com\compulynx\erevenue\bal\UserBal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */