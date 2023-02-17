package models;

import exceptions.InvalidHotelException;

public class Hotel extends Model {
  private String hotel;
  private Country country;
  private City city;
  private short stars;
  private double cost;
  private String info;

  public Hotel(int id, String hotel, Country country, City city, short stars, double cost, String info) {
    super(id);
    this.setHotel(hotel);
    this.country = country;
    this.city = city;
    this.stars = stars;
    this.cost = cost;
    this.info = info;
  }

  public String getHotel() {
    return hotel;
  }

  public void setHotel(String hotel) {
    if(hotel == null || hotel.isBlank())
      throw new InvalidHotelException();
    this.hotel = hotel;
  }

  public Country getCountry() {
    return country;
  }

  public void setCountry(Country country) {
    this.country = country;
  }

  public City getCity() {
    return city;
  }

  public void setCity(City city) {
    this.city = city;
  }

  public short getStars() {
    return stars;
  }

  public void setStars(short stars) {
    if (stars >= 0 && stars <= 5)
      this.stars = stars;
  }

  public double getCost() {
    return cost;
  }

  public void setCost(double cost) {
    this.cost = cost;
  }

  public String getInfo() {
    return info;
  }

  public void setInfo(String info) {
    this.info = info;
  }

}
