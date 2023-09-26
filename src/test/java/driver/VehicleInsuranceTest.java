package driver;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VehicleInsuranceTest {
  private VehicleInsurance testInsurance;
  private VehicleInsurance expectedInsurance;
  private Set<Name> insuredDrivers;

  @BeforeEach
  void setUp() {
    insuredDrivers = new HashSet<>();
    insuredDrivers.add(new Name("A", "Qin"));
    testInsurance = new VehicleInsurance(new Name("Sophie", "Qin"), insuredDrivers,
        LocalDate.of(2024,5,1));
  }

  @Test
  void getExpirationDate() {
    assertEquals(LocalDate.of(2024,5,1),testInsurance.getExpirationDate());
  }

  @Test
  void equals_sameObjects() {
    assertTrue(testInsurance.equals(testInsurance));
  }

  @Test
  void equals_null() {
    assertFalse(testInsurance.equals(null));
  }

  @Test
  void equals_differentObjects() {
    assertFalse(testInsurance.equals(insuredDrivers));
  }

  @Test
  void equals_sameFields() {
    expectedInsurance = new VehicleInsurance(new Name("Sophie", "Qin"), insuredDrivers,
        LocalDate.of(2024,5,1));
    assertTrue(expectedInsurance.equals(testInsurance));
  }

  @Test
  void testHashCode() {
    expectedInsurance = new VehicleInsurance(new Name("Sophie", "Qin"), insuredDrivers,
        LocalDate.of(2024,5,1));
    assertEquals(expectedInsurance.hashCode(),testInsurance.hashCode());
  }
}