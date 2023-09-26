package driver;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * A class for driver
 */
public class DriverInfo implements IValid{
  private Name name;
  private LocalDate birthDate;
  private DriverLicenseInfo driverLicenseInfo;
  private VehicleInfo vehicleInfo;
  private VehicleInsurance vehicleInsurance;
  private DriverHistory driverHistory;
  private VehicleHistory vehicleHistory;
  private boolean drive;
 private static final int DEFAULT_AGE = 21;

  /**
   * Constructor for DriverInfo class
   * @param name driver's name
   * @param birthDate driver's birthdate
   * @param driverLicenseInfo driver's driver license
   * @param vehicleInfo vehicle information submitted for application
   * @param vehicleInsurance driver's vehicle insurance
   * @param driverHistory driver's driving history
   * @param vehicleHistory vehicle's history
   */
  public DriverInfo(Name name, LocalDate birthDate, DriverLicenseInfo driverLicenseInfo,
      VehicleInfo vehicleInfo, VehicleInsurance vehicleInsurance, DriverHistory driverHistory,
      VehicleHistory vehicleHistory) {
    this.name = name;
    this.birthDate = birthDate;
    this.driverLicenseInfo = driverLicenseInfo;
    this.vehicleInfo = vehicleInfo;
    this.vehicleInsurance = vehicleInsurance;
    this.driverHistory = driverHistory;
    this.vehicleHistory = vehicleHistory;
    this.drive = vehicleInfo.getDrive();
  }

  /**
   * Getter for name
   * @return name
   */
  public Name getName() {
    return name;
  }

  /**
   * Getter for birthdate
   * @return birthdate
   */
  public LocalDate getBirthDate() {
    return birthDate;
  }

  /**
   * Getter for driver license
   * @return driver license
   */
  public DriverLicenseInfo getDriverLicenseInfo() {
    return driverLicenseInfo;
  }
  /**
   * Getter for vehicle
   * @return vehicle
   */
  public VehicleInfo getVehicleInfo() {
    return vehicleInfo;
  }
  /**
   * Getter for vehicle insurance
   * @return vehicle insurance
   */
  public VehicleInsurance getVehicleInsurance() {
    return vehicleInsurance;
  }
  /**
   * Getter for driver history
   * @return driver history
   */
  public DriverHistory getDriverHistory() {
    return driverHistory;
  }

  /**
   * Getter for vehicle history
   * @return vehicle history
   */
  public VehicleHistory getVehicleHistory() {
    return vehicleHistory;
  }

  /**
   * Getter for drive status
   * @return drive status, True or False
   */
  public boolean getDrive() {
    return drive;
  }

  /**
   * Change drive status
   */
  public void setDrive() {
    vehicleInfo.setDrive(true);
    this.drive = vehicleInfo.getDrive();
  }

  /**
   * Check if the prospective driver is underage
   * @return True or False
   */
  private Boolean ageCheck(){
    Period period = Period.between(birthDate,LocalDate.now());
    return period.getYears() >= DEFAULT_AGE;
  }

  /**
   * Check any differences between the name provided on the application and the name on the license
   * @return True or False
   */
  private Boolean nameCheck(){
    return name.equals(driverLicenseInfo.getDriverName());
  }

  /**
   * Check any differences between the birthdate provided on the application and the date on the license
   * @return True or False
   */
  private Boolean birthdateCheck(){
    return birthDate.equals(driverLicenseInfo.getDriverBirthdate());
  }

  /**
   * Check is the prospective driver the official owner of the vehicle, if not,
   * is the prospective driver listed as an insured driver
   * @return True or False
   */
  private Boolean insuranceCheck(){
    if (name.equals(vehicleInsurance.getOwner()))
      return Boolean.TRUE;
    else {
      return vehicleInsurance.getInsuredDrivers().contains(name);
    }
  }

  @Override
  public Boolean isValid() {
    return nameCheck() && birthdateCheck() && ageCheck() && driverLicenseInfo.isValid() &&
        vehicleInfo.isValid() && insuranceCheck() && vehicleInsurance.isValid() &&
        driverHistory.isValid() && vehicleHistory.isValid();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DriverInfo that = (DriverInfo) o;
    return Objects.equals(name, that.name) && Objects.equals(vehicleInfo,
        that.vehicleInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, vehicleInfo);
  }

  @Override
  public String toString() {
    return "DriverInfo{" +
        "name=" + name +
        ", birthDate=" + birthDate +
        ", driverLicenseInfo=" + driverLicenseInfo +
        ", vehicleInfo=" + vehicleInfo +
        ", vehicleInsurance=" + vehicleInsurance +
        ", driverHistory=" + driverHistory +
        ", vehicleHistory=" + vehicleHistory +
        '}';
  }
}
