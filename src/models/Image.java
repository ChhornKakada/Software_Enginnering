package models;

public class Image extends Model {
  private Hotel hotel;
  private String imagepath;

  public Image(int id, Hotel hotel, String imagepath) {
    super(id);
    this.hotel = hotel;
    this.imagepath = imagepath;
  }

  public Hotel getHotel() {
    return hotel;
  }

  public void setHotel(Hotel hotel) {
    this.hotel = hotel;
  }

  public String getImagepath() {
    return imagepath;
  }

  public void setImagepath(String imagepath) {
    if (imagepath == null || imagepath.isBlank())
      this.imagepath = "";
    else
      this.imagepath = imagepath;
  }

}
