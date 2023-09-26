package driver;

import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;

/**
 * Class to store driver license information
 */
public class DriverLicenseInfo implements IValid{
  private String Uid;
  private Name driverName;
  private String driverAddress;
  private LocalDate driverBirthdate;
  private String country;
  private String state;
  private LocalDate issuanceDate;
  private LocalDate expirationDate;
  private static final String US = "US";
  private static final String Canada = "Canada";
  private static final int DEFAULT_MONTH = 6;
  private static final int MONTHS_IN_YEAR = 12;

  /**
   * Constructor for DriverLicenseInfo
   * @param uid the license unique number
   * @param driverName a driver’s name
   * @param driverAddress a driver’s address
   * @param driverBirthdate a driver’s birthdate
   * @param country  country of issuance
   * @param state state of issuance
   * @param issuanceDate issuance date
   * @param expirationDate expiration date
   */
  public DriverLicenseInfo(String uid, Name driverName, String driverAddress,
      LocalDate driverBirthdate, String country, String state, LocalDate issuanceDate,
      LocalDate expirationDate) {
    Uid = uid;
    this.driverName = driverName;
    this.driverAddress = driverAddress;
    this.driverBirthdate = driverBirthdate;
    this.country = country;
    this.state = state;
    this.issuanceDate = issuanceDate;
    this.expirationDate = expirationDate;
  }

  /**
   * Getter for uid
   * @return the license unique number
   */
  public String getUid() {
    return Uid;
  }

  /**
   * Getter for driver's name
   * @return driver's name
   */
  public Name getDriverName() {
    return driverName;
  }

  /**
   * Getter for driver's address
   * @return driver's address
   */
  public String getDriverAddress() {
    return driverAddress;
  }

  /**
   * Getter for driver's birthdate
   * @return driver's birthdate
   */
  public LocalDate getDriverBirthdate() {
    return driverBirthdate;
  }

  /**
   * Getter for country of issuance
   * @return country of issuance
   */
  public String getCountry() {
    return country;
  }
  /**
   * Getter for state of issuance
   * @return state of issuance
   */
  public String getState() {
    return state;
  }

  /**
   * Getter for issuance date
   * @return the date of issuance
   */
  public LocalDate getIssuanceDate() {
    return issuanceDate;
  }

  /**
   * Getter for expiration date
   * @return expiration date
   */
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  /**
   * Check whether the license issued country was US or Canada
   * @return True or False
   */
  private Boolean countryValid(){
    return country.equalsIgnoreCase(US)||country.equalsIgnoreCase(Canada);
  }
  /**
   * Check whether the license issued less than six months ago
   * @return True or False
   */
  private Boolean issuanceValid(){
    Period period = Period.between(issuanceDate,LocalDate.now());
    int months = period.getYears() * MONTHS_IN_YEAR + period.getMonths();
    return months >= DEFAULT_MONTH;
  }
  /**
   * Check whether the license expired
   * @return True or False
   */
  private Boolean expirationValid(){
    return LocalDate.now().isBefore(expirationDate);

  }
  @Override
  public Boolean isValid() {
    return countryValid()&&issuanceValid()&&expirationValid();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DriverLicenseInfo that = (DriverLicenseInfo) o;
    return Objects.equals(Uid, that.Uid) && Objects.equals(driverName,
        that.driverName) && Objects.equals(driverAddress, that.driverAddress)
        && Objects.equals(driverBirthdate, that.driverBirthdate)
        && Objects.equals(country, that.country) && Objects.equals(state,
        that.state) && Objects.equals(issuanceDate, that.issuanceDate)
        && Objects.equals(expirationDate, that.expirationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(Uid, driverName, driverAddress, driverBirthdate, country, state,
        issuanceDate,
        expirationDate);


  }

  @Override
  public String toString() {
    return "DriverLicenseInfo{" +
        "Uid='" + Uid + '\'' +
        ", driverName=" + driverName +
        ", driverAddress='" + driverAddress + '\'' +
        ", driverBirthdate=" + driverBirthdate +
        ", country='" + country + '\'' +
        ", state='" + state + '\'' +
        ", issuanceDate=" + issuanceDate +
        ", expirationDate=" + expirationDate +
        '}';
  }

}
