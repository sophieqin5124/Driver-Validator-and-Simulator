package driver;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DriverLicenseInfoTest {
  private DriverLicenseInfo testLicense;
  private DriverLicenseInfo expectedLicense;
  private Name testName;

  @BeforeEach
  void setUp() {
    testName = new Name("Sophie","Qin");
    testLicense = new DriverLicenseInfo("A123",testName,
        "Main Street", LocalDate.of(1995,7,5),
        "US","CA", LocalDate.of(2018,5,1),
        LocalDate.of(2024,5,1));
  }

  @Test
  void getUid() {
    assertEquals("A123",testLicense.getUid());
  }

  @Test
  void getDriverName() {
    assertEquals(testName,testLicense.getDriverName());
  }

  @Test
  void getDriverAddress() {
    assertEquals("Main Street",testLicense.getDriverAddress());
  }

  @Test
  void getDriverBirthdate() {
    assertEquals(LocalDate.of(1995,7,5),
        testLicense.getDriverBirthdate());
  }

  @Test
  void getCountry() {
    assertEquals("US",testLicense.getCountry());
  }

  @Test
  void getState() {
    assertEquals("CA",testLicense.getState());
  }

  @Test
  void getIssuanceDate() {
    assertEquals(LocalDate.of(2018,5,1),testLicense.getIssuanceDate());
  }

  @Test
  void getExpirationDate() {
    assertEquals(LocalDate.of(2024,5,1),testLicense.getExpirationDate());
  }

  @Test
  void equals_sameObjects() {
    assertTrue(testLicense.equals(testLicense));
  }

  @Test
  void equals_null() {
    assertFalse(testLicense.equals(null));
  }

  @Test
  void equals_differentObjects() {
    assertFalse(testLicense.equals(testName));
  }

  @Test
  void equals_differentID() {
    expectedLicense = new DriverLicenseInfo("A456",testName,
        "Main Street", LocalDate.of(1995,7,5),
        "US","CA", LocalDate.of(2018,5,1),
        LocalDate.of(2024,5,1));
    assertFalse(expectedLicense.equals(testLicense));
  }

  @Test
  void equals_differentName() {
    expectedLicense = new DriverLicenseInfo("A123",new Name("A","B"),
        "Main Street", LocalDate.of(1995,7,5),
        "US","CA", LocalDate.of(2018,5,1),
        LocalDate.of(2024,5,1));
    assertFalse(expectedLicense.equals(testLicense));
  }

  @Test
  void equals_differentAddress() {
    expectedLicense = new DriverLicenseInfo("A123",testName,
        "Main Street 123", LocalDate.of(1995,7,5),
        "US","CA", LocalDate.of(2018,5,1),
        LocalDate.of(2024,5,1));
    assertFalse(expectedLicense.equals(testLicense));
  }

  @Test
  void equals_differentBirthdate() {
    expectedLicense = new DriverLicenseInfo("A123",testName,
        "Main Street", LocalDate.of(1996,7,5),
        "US","CA", LocalDate.of(2018,5,1),
        LocalDate.of(2024,5,1));
    assertFalse(expectedLicense.equals(testLicense));
  }

  @Test
  void equals_differentCountry() {
    expectedLicense = new DriverLicenseInfo("A123",testName,
        "Main Street", LocalDate.of(1995,7,5),
        "China","CA", LocalDate.of(2018,5,1),
        LocalDate.of(2024,5,1));
    assertFalse(expectedLicense.equals(testLicense));
  }

  @Test
  void equals_differentState() {
    expectedLicense = new DriverLicenseInfo("A123",testName,
        "Main Street", LocalDate.of(1995,7,5),
        "US","NY", LocalDate.of(2018,5,1),
        LocalDate.of(2024,5,1));
    assertFalse(expectedLicense.equals(testLicense));
  }

  @Test
  void equals_differentIssuance() {
    expectedLicense = new DriverLicenseInfo("A123",testName,
        "Main Street", LocalDate.of(1995,7,5),
        "US","CA", LocalDate.of(2019,5,1),
        LocalDate.of(2024,5,1));
    assertFalse(expectedLicense.equals(testLicense));
  }

  @Test
  void equals_differentExpiration() {
    expectedLicense = new DriverLicenseInfo("A123",testName,
        "Main Street", LocalDate.of(1995,7,5),
        "US","CA", LocalDate.of(2018,5,1),
        LocalDate.of(2025,5,1));
    assertFalse(expectedLicense.equals(testLicense));
  }

  @Test
  void equals_sameFields() {
    expectedLicense = new DriverLicenseInfo("A123",testName,
        "Main Street", LocalDate.of(1995,7,5),
        "US","CA", LocalDate.of(2018,5,1),
        LocalDate.of(2024,5,1));
    assertTrue(expectedLicense.equals(testLicense));
  }

  @Test
  void testHashCode() {
    expectedLicense = new DriverLicenseInfo("A123",testName,
        "Main Street", LocalDate.of(1995,7,5),
        "US","CA", LocalDate.of(2018,5,1),
        LocalDate.of(2024,5,1));
    assertEquals(expectedLicense.hashCode(),testLicense.hashCode());
  }

  @Test
  void testToString() {
    String expectedString = "DriverLicenseInfo{" +
        "Uid='" + testLicense.getUid() + '\'' +
        ", driverName=" + testLicense.getDriverName() +
        ", driverAddress='" + testLicense.getDriverAddress() + '\'' +
        ", driverBirthdate=" + testLicense.getDriverBirthdate() +
        ", country='" + testLicense.getCountry() + '\'' +
        ", state='" + testLicense.getState() + '\'' +
        ", issuanceDate=" + testLicense.getIssuanceDate() +
        ", expirationDate=" + testLicense.getExpirationDate() +
        '}';
    assertEquals(expectedString,testLicense.toString());
  }
}