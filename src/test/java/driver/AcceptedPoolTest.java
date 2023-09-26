package driver;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AcceptedPoolTest {
  private AcceptedPool testPool;
  private Name testName;
  private Name testName2;
  private Name testName3;
  private LocalDate testBirthdate;
  private DriverInfo testDriver;
  private DriverInfo testDriver2;
  private DriverInfo testDriver3;
  private DriverInfo testDriver4;
  private DriverLicenseInfo testDriverLicense;
  private VehicleInfo testVehicle;
  private VehicleInfo testVehicle2;
  private VehicleInfo testVehicle3;
  private VehicleInfo testVehicle4;
  private VehicleInsurance testInsurance;
  private DriverHistory testDriverHistory;
  private VehicleHistory testVehicleHistory;

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
    testVehicle.setDrive(true);
    testInsurance = new VehicleInsurance(testName, null,
        LocalDate.of(2024,5,1));
    testDriverHistory = new DriverHistory(null);
    testVehicleHistory = new VehicleHistory(null,null);
    testDriver = new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);

    testName2 = new Name("Gong","Qin");
    testName3 = new Name ("A", "Qin");
    testVehicle2 = new VehicleInfo(2015,"Black","Honda","Accord",
        "1234",testName2);
    testVehicle3 = new VehicleInfo(2015,"Black","Honda","Accord",
        "1234",testName2);
    testVehicle3.setDrive(true);
    testVehicle4 =new VehicleInfo(2015,"Yellow","Honda","Accord",
        "1234",testName);
    testDriver2 = new DriverInfo(testName2,testBirthdate,testDriverLicense,testVehicle2,testInsurance,
        testDriverHistory,testVehicleHistory);
    testDriver3 = new DriverInfo(testName3,testBirthdate,testDriverLicense,testVehicle3,testInsurance,
        testDriverHistory,testVehicleHistory);
    testDriver4 =  new DriverInfo(testName,testBirthdate,testDriverLicense,testVehicle4,testInsurance,
        testDriverHistory,testVehicleHistory);

    testPool = new AcceptedPool();
    testPool.getAcceptedDrivers().add(testDriver);
    testPool.getAcceptedDrivers().add(testDriver2);
    testPool.getAcceptedDrivers().add(testDriver3);
    testPool.getAcceptedDrivers().add(testDriver4);
  }

  @Test
  void addPool_1() {
    testPool.addPool(testDriver);
    assertEquals(4,testPool.getAcceptedDrivers().size());
  }

  @Test
  void addPool_2() {
    testDriver = new DriverInfo(testName2,testBirthdate,testDriverLicense,testVehicle,testInsurance,
        testDriverHistory,testVehicleHistory);
    testPool.addPool(testDriver);
    assertEquals(5,testPool.getAcceptedDrivers().size());
  }

  @Test
  void setDrive_1() {
    testPool.setDrive(testDriver4);
    assertFalse(testDriver4.getDrive());
  }

  @Test
  void setDrive_2() {
    testPool.setDrive(testDriver2);
    assertFalse(testDriver2.getDrive());
  }

  @Test
  void setDrive_3() {
    VehicleInfo testVehicle5 =new VehicleInfo(2015,"Green","Honda","Accord",
        "1234",testName);
    DriverInfo testDriver5 = new DriverInfo(testName2,testBirthdate,testDriverLicense,testVehicle5,testInsurance,
        testDriverHistory,testVehicleHistory);
    testPool.addPool(testDriver5);
    testPool.setDrive(testDriver5);
    assertTrue(testDriver5.getDrive());
  }

  @Test
  void getAcceptedDrivers() {
    Set<DriverInfo> drivers = new HashSet<>();
    drivers.add(testDriver);
    drivers.add(testDriver2);
    drivers.add(testDriver3);
    drivers.add(testDriver4);
    assertEquals(testPool.getAcceptedDrivers(),drivers);
  }
}