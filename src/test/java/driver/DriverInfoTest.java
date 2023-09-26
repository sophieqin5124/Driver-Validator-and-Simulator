package driver;

import static org.junit.jupiter.api.Assertions.*;

import driver.Incident.MovingViolation;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DriverInfoTest {
  private Name testName;
  private LocalDate testBirthdate;
  private DriverInfo testDriver;
  private DriverLicenseInfo testDriverLicense;
  private VehicleInfo testVehicle;
  private VehicleInfo expectedVehicle;
  private VehicleInsurance testInsurance;
  private DriverHistory testDriverHistory;
  private VehicleHistory testVehicleHistory;
  private DriverInfo expectedDriver;

  @BeforeEach
  void setUp() {
    testName = new Name("Sophie","Qin");
    testBirthdate = LocalDate.of(1995,7,5);
    testDriverLicense = new DriverLicenseInfo("A123",testName,
        "Main Street", testBirthdate,
        "US","CA",LocalDate.of(2018,5,1),
        LocalDate.of(2024,5,1));
    testVehicle = new VehicleInfo(2014,"Blue","Honda","Accord",
        "1234",testName);
    testInsurance = new VehicleInsurance(testName, null,
        LocalDate.of(2024,5,1));
    testDriverHistory = new DriverHistory(null);
    testVehicleHistory = new VehicleHistory(null,null);
    testDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);

  }

  @Test
  void getName() {
    assertEquals(testName,testDriver.getName());
  }

  @Test
  void getBirthDate() {
    assertEquals(testBirthdate,testDriver.getBirthDate());
  }

  @Test
  void getDriverLicenseInfo() {
    assertEquals(testDriverLicense,testDriver.getDriverLicenseInfo());
  }

  @Test
  void getVehicleInfo() {
    assertEquals(testVehicle,testDriver.getVehicleInfo());
  }

  @Test
  void getVehicleInsurance() {
    assertEquals(testInsurance,testDriver.getVehicleInsurance());
  }

  @Test
  void getDriverHistory() {
    assertEquals(testDriverHistory,testDriver.getDriverHistory());
  }

  @Test
  void getVehicleHistory() {
    assertEquals(testVehicleHistory,testDriver.getVehicleHistory());
  }

  @Test
  void getDrive() {
    assertFalse(testDriver.getDrive());
  }

  @Test
  void ageValid() {
    testBirthdate = LocalDate.of(2003,7,5);
    testDriverLicense = new DriverLicenseInfo("A123",testName,
        "Main Street", testBirthdate,
        "US","CA",LocalDate.of(2018,5,1),
        LocalDate.of(2024,5,1));
    testDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertFalse(testDriver.isValid());
  }

  @Test
  void nameValid() {
    Name name = new Name("A","Qin");
    testDriverLicense = new DriverLicenseInfo("A123",name,
        "Main Street", testBirthdate,
        "US","CA",LocalDate.of(2018,5,1),
        LocalDate.of(2024,5,1));
    testDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertFalse(testDriver.isValid());
  }

  @Test
  void birthdateValid() {
    testDriverLicense = new DriverLicenseInfo("A123",testName,
        "Main Street", LocalDate.of(2000,5,1),
        "US","CA",LocalDate.of(2018,5,1),
        LocalDate.of(2024,5,1));
    testDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertFalse(testDriver.isValid());
  }

  @Test
  void countryValid_1() {
    testDriverLicense = new DriverLicenseInfo("A123",testName,
        "Main Street",testBirthdate,
        "China","CA",LocalDate.of(2018,5,1),
        LocalDate.of(2024,5,1));
    testDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertFalse(testDriver.isValid());
  }

  @Test
  void countryValid_2() {
    testDriverLicense = new DriverLicenseInfo("A123",testName,
        "Main Street",testBirthdate,
        "Canada","CA",LocalDate.of(2018,5,1),
        LocalDate.of(2024,5,1));
    testDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertTrue(testDriver.isValid());
  }

  @Test
  void issuanceValid() {
    testDriverLicense = new DriverLicenseInfo("A123",testName,
        "Main Street",testBirthdate,
        "US","CA",LocalDate.of(2023,3,16),
        LocalDate.of(2024,5,1));
    testDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertFalse(testDriver.isValid());
  }

  @Test
  void expirationValid() {
    testDriverLicense = new DriverLicenseInfo("A123",testName,
        "Main Street",testBirthdate,
        "US","CA",LocalDate.of(2023,1,1),
        LocalDate.of(2023,5,1));
    testDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertFalse(testDriver.isValid());
  }

  @Test
  void vehicleValid() {
    testVehicle = new VehicleInfo(2007,"Blue","Honda","Accord",
        "1234",testName);
    testDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertFalse(testDriver.isValid());
  }

  @Test
  void insuranceValid_1() {
    testName = new Name("A","Qin");
    testInsurance = new VehicleInsurance(testName, null,
        LocalDate.of(2024,5,1));
    testDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertFalse(testDriver.isValid());
  }

  @Test
  void insuranceValid_2() {
    testName = new Name("A","Qin");
    Set<Name> insuredDrivers = new HashSet<>();
    insuredDrivers.add(new Name("A", "Qin"));
    testDriverLicense = new DriverLicenseInfo("A123",testName,
        "Main Street",testBirthdate,
        "US","CA",LocalDate.of(2023,1,1),
        LocalDate.of(2024,5,1));
    testInsurance = new VehicleInsurance(new Name("Sophie", "Qin"), insuredDrivers,
        LocalDate.of(2024,5,1));
    testDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertTrue(testDriver.isValid());
  }

  @Test
  void driverHistory_valid() {
    Set<Incident> violations = new HashSet<>();
    violations.add(new MovingViolation(LocalDate.of(2019,1,1),
        "reckless driving"));
    testDriverHistory = new DriverHistory(violations);
    testDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertFalse(testDriver.isValid());
  }

  @Test
  void vehicleHistory_valid_1() {
    Set<Incident> violations = new HashSet<>();
    violations.add(new MovingViolation(LocalDate.of(2019,1,1),
        "reckless driving"));
    testVehicleHistory = new VehicleHistory(violations, null);
    testDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertTrue(testDriver.isValid());
  }

  @Test
  void vehicleHistory_valid_2() {
    Set<Incident> violations = new HashSet<>();
    violations.add(new MovingViolation(LocalDate.of(2023,4,1),
        "reckless driving",testName));
    testVehicleHistory = new VehicleHistory(violations, null);
    testDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertFalse(testDriver.isValid());
  }

  @Test
  void vehicleHistory_valid_3() {
    Set<Incident> crashes = new HashSet<>();
    crashes.add(new MovingViolation(LocalDate.of(2023,4,1),
        "A fender-bender",testName));
    testVehicleHistory = new VehicleHistory(null, crashes);
    testDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertFalse(testDriver.isValid());
  }

  @Test
  void isValid() {
    assertTrue(testDriver.isValid());
  }

  @Test
  void equals_sameObjects() {
    assertTrue(testDriver.equals(testDriver));
  }

  @Test
  void equals_null() {
    assertFalse(testDriver.equals(null));
  }

  @Test
  void equals_differentObjects() {
    assertFalse(testDriver.equals(testName));
  }

  @Test
  void equals_differentName() {
    expectedDriver = new DriverInfo(new Name("A","Qin"),testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertFalse(expectedDriver.equals(testDriver));
  }

  @Test
  void equals_differentVehicle() {
    expectedVehicle = new VehicleInfo(2015,"Blue","Honda","Accord",
        "1234",testName);
    expectedDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,expectedVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertFalse(expectedDriver.equals(testDriver));
  }

  @Test
  void equals_sameFields() {
    expectedDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    assertTrue(expectedDriver.equals(testDriver));
  }


  @Test
  void testHashCode() {
    int result = Objects.hash(testName,testVehicle);
    assertEquals(result, testDriver.hashCode());
  }

  @Test
  void testToString() {
    String expectedString  = "DriverInfo{" +
        "name=" + testName +
        ", birthDate=" + testBirthdate +
        ", driverLicenseInfo=" + testDriverLicense +
        ", vehicleInfo=" + testVehicle +
        ", vehicleInsurance=" + testInsurance +
        ", driverHistory=" + testDriverHistory +
        ", vehicleHistory=" + testVehicleHistory +
        '}';
    assertEquals(expectedString, testDriver.toString());
  }
}