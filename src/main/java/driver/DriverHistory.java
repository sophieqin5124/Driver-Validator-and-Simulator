package driver;

import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Class for driver history
 */
public class DriverHistory implements IValid{

  private Set<Incident> violations;
  private static final String[] VIOLATIONS = {"Reckless driving","Speeding", "Driving under influence",
      "Driving without a valid license and/or insurance"};

  /**
   * Constructor for DriverHistory class
   * @param violations a set for all violations
   */
  public DriverHistory(Set<Incident> violations) {
    this.violations = violations;
  }

  /**
   * Getter for violations
   * @return a set of violations
   */
  public Set<Incident> getViolations() {
    return violations;
  }

  @Override
  public Boolean isValid() {
    if (violations != null) {
      List<String> violationsList = Arrays.asList(VIOLATIONS);
      List<String> lowercaseViolations = violationsList.stream()
          .map(String::toLowerCase)
          .collect(Collectors.toList());
      for (Incident item : violations) {
        if (lowercaseViolations.contains(item.getIncident().toLowerCase())) {
          return Boolean.FALSE;
        }
      }
    }
    return Boolean.TRUE;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    DriverHistory that = (DriverHistory) o;
    return Objects.equals(violations, that.violations);
  }

  @Override
  public int hashCode() {
    return Objects.hash(violations);
  }

  @Override
  public String toString() {
    StringBuffer result = new StringBuffer();
    if (violations != null) {
      for (Incident item : violations) {
        result.append("        ").append(item.getIncident()).append("\n\n");
      }
    }
    return result.toString();
  }

}
