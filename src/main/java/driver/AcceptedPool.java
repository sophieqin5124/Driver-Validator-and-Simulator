package driver;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Class for the pool of accepted drivers
 */
public class AcceptedPool {
  private static final String INITIAL_HOLDER = "A";
  private static final String ERROR = "No registered driver found";

  private Set<DriverInfo> acceptedDrivers;

  /**
   * Constructor for Pool class
   */
  public AcceptedPool() {
    this.acceptedDrivers = new HashSet<>();
  }

  /**
   * Add satisfied drivers to pool
   * @param driver A DriverInfo instance
   */
  public void addPool(DriverInfo driver) {
 if (!this.acceptedDrivers.contains(driver))
   this.acceptedDrivers.add(driver);
  }

  /**
   * helper method to check whether one driver has already driven a car
   * @param driver A driver requests to drive
   * @return 0 for true, 1 and -1 for false
   */
  private int sameDriverCheck(DriverInfo driver) {
    int status = -1;
    for (DriverInfo item : this.acceptedDrivers) {
      if (driver.getName().equals(item.getName()) && !driver.equals(item)) {
          if (item.getDrive())
            status = 0;
          else
            status = 1;
        }
      }
   return status;
  }

  /**
   * helper method to check whether the vehicle was driven by others
   * @param driver A driver requests to drive
   * @return 0 for true, 1 and -1 for false
   */
  private int sameVehicleCheck(DriverInfo driver) {
    int status = -1;
    for (DriverInfo item : this.acceptedDrivers) {
      if (driver.getVehicleInfo().equals(item.getVehicleInfo()) &&
          !driver.equals(item)) {
        if (item.getDrive())
          status = 0;
        else
          status = 1;
      }
    }
    return status;
  }

  /**
   * If the driver hasn't driven another car or the car requested is not occupied
   * Set the driver's status to drive
   * @param driver A driver requests to drive
   */
  public void setDrive(DriverInfo driver){
    if (sameDriverCheck(driver) !=0 && sameVehicleCheck(driver) !=0)
      driver.setDrive();
  }

  /**
   * helper method to form vehicle string
   * @param name matched name
   * @return A string
   */
  private String vehicleString(Name name) {
    StringBuffer result = new StringBuffer();
    for (DriverInfo driver : this.acceptedDrivers) {
      if (driver.getName().equals(name)) {
        result.append(driver.getVehicleInfo().toString());
        result.append("\n\n");
      }
    }
    return result.toString();
  }

  /**
   * helper method to form a driver string
   * @param name matched name
   * @return A string
   */
  public String driverString(Name name) {
    StringBuffer result = new StringBuffer();
    StringBuffer violations = new StringBuffer();

    for (DriverInfo driver : this.acceptedDrivers) {
      if (driver.getName().equals(name)) {
        result.append(driver.getName()).append("\n\n");
        String vehicleString = vehicleString(driver.getName());
        result.append(vehicleString);
        if (driver.getDriverHistory().getViolations() != null) {
          violations.append(driver.getDriverHistory().toString());
          result.append("    ").append("Driving violations:\n\n").append(violations);
        }
      }
      if (result.toString().length() > 0)
        break;
    }
    return result.toString();
  }

  /**
   * method to return a string about a driver's info
   * whose last name is the same as the queried name
   * @param lastName last name string
   * @return A string
   */
  public String provideDriverInformation(String lastName) {
    StringBuffer result = new StringBuffer();
    Name currentName = new Name(INITIAL_HOLDER,INITIAL_HOLDER);
    for (DriverInfo driver : this.acceptedDrivers) {
      if (driver.getName().getLastName().equals(lastName)) {
        if (driver.getName().equals(currentName)) {
          ;
        } else {
          currentName = driver.getName();
          result.append(driverString(driver.getName()));
        }
      }
  }
  if (result.toString().length() > 0) {
    return result.toString();
  } else
    return ERROR;
}

  /**
   * Getter for accepted drivers
   * @return an accepted drivers Set
   */
  public Set<DriverInfo> getAcceptedDrivers() {
    return acceptedDrivers;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AcceptedPool that = (AcceptedPool) o;
    return Objects.equals(acceptedDrivers, that.acceptedDrivers);
  }

  @Override
  public int hashCode() {
    return Objects.hash(acceptedDrivers);
  }

  @Override
  public String toString() {
    return "AcceptedPool{" +
        "acceptedDrivers=" + acceptedDrivers +
        '}';
  }
}