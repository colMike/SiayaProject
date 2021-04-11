package com.compulynx.erevenue.dal;

import com.compulynx.erevenue.models.Device;
import com.compulynx.erevenue.models.ErevenueResponse;
import com.compulynx.erevenue.models.User;
import java.util.List;

public interface DeviceDal {
  boolean checkDevicedSerialNo(String paramString);
  
  ErevenueResponse UpdateDeviceInfo(Device paramDevice);
  
  List<Device> GetAllDevicesInfo();
  
  ErevenueResponse UpdateIssueDeviceInfo(Device paramDevice);
  
  List<Device> GetAllIssueDevicesInfo();
  
  List<User> GetActiveUsers();
  
  List<Device> GetActiveDevicesInfo();
}


/* Location:              C:\Users\USER\Downloads\erevenue.war!\WEB-INF\lib\e-revenue-bo-dal-0.0.1.jar!\com\compulynx\erevenue\dal\DeviceDal.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */