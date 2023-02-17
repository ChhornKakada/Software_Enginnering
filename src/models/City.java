package models;

import exceptions.InvalidCityException;

public class City extends Model {
  private String city;
  private Country country;
  private String ucity;

  public City(int id, String city, Country country) {
    super(id);
    setCity(city);
    setCountry(country);
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    if (city == null || city.isBlank()) {
      throw new InvalidCityException();
    } else {
      this.city = city;
      this.ucity = city + "_";
      if (this.country != null)
        this.ucity += country.getId();
    }

  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
    this.ucity = city + "_";
    if (this.country != null)
      this.ucity += country.getId();
  }

  public String getUcity() {
    return ucity;
  }
}
