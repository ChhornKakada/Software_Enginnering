package Model;

public class Address extends Model {
  private String houseno;
  private String streetno;
  private String streetname;
  private String villagename;
  private String districtname;
  private String communnename;
  private String provincename;
  private String cityname;
  private String countryname;
  private Integer iscurrent;

  public Address(int id, String houseno, String streetno, String streetname, 
      String villagename, String districtname, String communnename, String provincename, 
      String cityname, String countryname, Integer iscurrent) {
    super(id);
    this.houseno = houseno;
    this.streetno = streetno;
    this.streetname = streetname;
    this.villagename = villagename;
    this.districtname = districtname;
    this.communnename = communnename;
    this.provincename = provincename;
    this.cityname = cityname;
    this.countryname = countryname;
    this.iscurrent = iscurrent;
  }

  public String getHouseno() {
    return houseno;
  }

  public void setHouseno(String houseno) {
    this.houseno = houseno;
  }

  public String getStreetno() {
    return streetno;
  }

  public void setStreetno(String streetno) {
    this.streetno = streetno;
  }

  public String getStreetname() {
    return streetname;
  }

  public void setStreetname(String streetname) {
    this.streetname = streetname;
  }

  public String getVillagename() {
    return villagename;
  }

  public void setVillagename(String villagename) {
    this.villagename = villagename;
  }

  public String getDistrictname() {
    return districtname;
  }

  public void setDistrictname(String districtname) {
    this.districtname = districtname;
  }

  public String getCommunnename() {
    return communnename;
  }

  public void setCommunnename(String communnename) {
    this.communnename = communnename;
  }

  public String getProvincename() {
    return provincename;
  }

  public void setProvincename(String provincename) {
    this.provincename = provincename;
  }

  public String getCityname() {
    return cityname;
  }

  public void setCityname(String cityname) {
    this.cityname = cityname;
  }

  public String getCountryname() {
    return countryname;
  }

  public void setCountryname(String countryname) {
    this.countryname = countryname;
  }

  public Integer isIscurrent() {
    return iscurrent;
  }

  public void setIscurrent(Integer iscurrent) {
    this.iscurrent = iscurrent;
  }

}
