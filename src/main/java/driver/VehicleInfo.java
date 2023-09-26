package driver;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Class to store vehicle information
 */
public class VehicleInfo implements IValid{
  private static final int DEFAULT_AGE = 15;
  private int year;
  private String color;
  private String make;
  private String model;
  private String uid;
  private Name owner;
  private boolean drive;

  /**
   * Constructor for VehicleInfo
   * @param year year of the vehicle
   * @param color color of vehicle
   * @param make make of vehicle
   * @param model model of vehicle
   * @param uid unique identification number of vehicle
   * @param owner owner of vehicle
   */
  public VehicleInfo(int year, String color, String make, String model, String uid, Name owner) {
    this.year = year;
    this.color = color;
    this.make = make;
    this.model = model;
    this.uid = uid;
    this.owner = owner;
    this.drive = false;
  }

  /**
   * Getter for year
   * @return year
   */
  public int getYear() {
    return year;
  }
  /**
   * Getter for color
   * @return color
   */
  public String getColor() {
    return color;
  }

  /**
   * Getter for make
   * @return make
   */
  public String getMake() {
    return make;
  }

  /**
   * Getter for model
   * @return model
   */
  public String getModel() {
    return model;
  }

  /**
   * Getter for uid
   * @return uid
   */
  public String getUid() {
    return uid;
  }

  /**
   * Getter for owner
   * @return owner
   */
  public Name getOwner() {
    return owner;
  }

  /**
   * Getter for drive status
   * @return True or False
   */
  public boolean getDrive() {
    return drive;
  }

  /**
   * Setter for drive status
   * @param drive drive status
   */
  public void setDrive(boolean drive) {
    this.drive = drive;
  }

  @Override
  public Boolean isValid() {
    return LocalDate.now().getYear()-year < DEFAULT_AGE;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VehicleInfo that = (VehicleInfo) o;
    return year == that.year && Objects.equals(color, that.color)
        && Objects.equals(make, that.make) && Objects.equals(model, that.model)
        && Objects.equals(uid, that.uid) && Objects.equals(owner, that.owner);
  }

  @Override
  public int hashCode() {
    return Objects.hash(year, color, make, model, uid, owner);
  }

  @Override
  public String toString() {
    return "    " + year + " " + color + " " + make + " " + model + ", " + uid;
  }
}
