package models;

import exceptions.InvalidCountryException;

public class Country extends Model {
  private String country;

  public Country(int id, String country) {
    super(id);
    this.setCountry(country);
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    if (country == null || country.isBlank())
      throw new InvalidCountryException();
    this.country = country;
  }

}
