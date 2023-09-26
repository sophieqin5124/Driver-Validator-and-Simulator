package csvsolver;

import driver.DriverHistory;
import driver.DriverInfo;
import driver.VehicleInfo;
import java.time.LocalDate;
import driver.Name;
import driver.DriverLicenseInfo;
import driver.VehicleInsurance;
import driver.VehicleHistory;

/**
 * Class to create a new driver
 */
public class CreateDriver {
  private Name name;
  private LocalDate birthDate;
  private DriverLicenseInfo driverLicenseInfo;
  private VehicleInfo vehicleInfo;
  private VehicleInsurance vehicleInsurance;
  private DriverHistory driverHistory;
  private VehicleHistory vehicleHistory;

  /**
   * Constructor for CreateDriver class
   */
  public CreateDriver() {
    this.name = null;
    this.birthDate = null;
    this.driverLicenseInfo = null;
    this.vehicleInfo = null;
    this.vehicleInsurance = null;
    this.driverHistory = null;
    this.vehicleHistory = null;
  }

  /**
   * Make extractor according to different hashmaps
   * @param fieldMap hashmap to store information for different classes
   */
  public void makeExtractor(FieldMap fieldMap) {
    String input = fieldMap.getCsvKey();
    switch (input) {
      case "name" -> name = new Extractor.NameExtractor().extract(fieldMap);
      case "birthdate" -> birthDate = new Extractor.BirthDateExtractor().extract(fieldMap);
      case "driver_license_info" ->
          driverLicenseInfo = new Extractor.DriverLicenseExtractor().extract(fieldMap);
      case "vehicle_info" -> vehicleInfo = new Extractor.VehicleExtractor().extract(fieldMap);
      case "vehicle_insurance_info" ->
          vehicleInsurance = new Extractor.VehicleInsuranceExtractor().extract(fieldMap);
      case "driver_history_info" ->
          driverHistory = new Extractor.DriverHistoryExtractor().extract(fieldMap);
      case "vehicle_history_info" ->
          vehicleHistory = new Extractor.VehicleHistoryExtractor().extract(fieldMap);
    }
  }

  /**
   * Create Driver class
   * @return Driver
   */
  public DriverInfo create(){
    return new DriverInfo(name,birthDate,driverLicenseInfo,vehicleInfo,vehicleInsurance,
        driverHistory,vehicleHistory);
  }
}
