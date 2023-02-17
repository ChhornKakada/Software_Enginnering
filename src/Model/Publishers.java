package Model;

import Exception.InvalidPublisherException;

public class Publishers extends Model {
  private String publisherName;
  private Address address;

  public Publishers(int id, String publisherName, Address address) {
    super(id);
    this.publisherName = publisherName;
    this.address = address;
  }

  public String getPublisherName() {
    return publisherName;
  }

  public void setPublisherName(String publisherName) {
    if (publisherName.isBlank() || publisherName == null) {
      throw new InvalidPublisherException();
    }
    this.publisherName = publisherName;
  }

  public Address getAddress() {
    return address;
  }

  public void setAddress(Address address) {
    this.address = address;
  }
}
