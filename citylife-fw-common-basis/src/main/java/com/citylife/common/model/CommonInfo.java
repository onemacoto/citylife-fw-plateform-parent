package com.citylife.common.model;

import java.io.Serializable;

public class CommonInfo implements Serializable {

  private static final long serialVersionUID = 1L;

  private String mobileSystem;
  private String appVersion;
  private String longitude;
  private String latitude;

  public String getMobileSystem() {
    return mobileSystem;
  }

  public void setMobileSystem(String mobileSystem) {
    this.mobileSystem = mobileSystem;
  }


  public String getAppVersion() {
    return appVersion;
  }

  public void setAppVersion(String appVersion) {
    this.appVersion = appVersion;
  }

  public String getLongitude() {
    return longitude;
  }

  public void setLongitude(String longitude) {
    this.longitude = longitude;
  }

  public String getLatitude() {
    return latitude;
  }

  public void setLatitude(String latitude) {
    this.latitude = latitude;
  }

}
