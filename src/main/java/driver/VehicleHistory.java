package driver;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Class to store vehicle history information
 */
public class VehicleHistory implements IValid{
  private Set<Incident> violations;
  private Set<Incident> crashes;
  private static final int DEFAULT_MONTH = 6;
  private static final int MONTHS_IN_YEAR = 12;

  /**
   * Constructor for VehicleHistory class
   * @param violations a set of violations
   * @param crashes a set of crashes
   */
  public VehicleHistory(Set<Incident> violations, Set<Incident> crashes) {
    this.violations = violations;
    this.crashes = crashes;
  }

  /**
   * Getter for violations
   * @return violations
   */
  public Set<Incident> getViolations() {
    return violations;
  }
  /**
   * Getter for crashes
   * @return crashes
   */
  public Set<Incident> getCrashes() {
    return crashes;
  }

  /**
   * helper method to check whether the date of incident was within 6 months
   * @param date the date when incidents happened
   * @return True or False
   */
  private Boolean timeValid(LocalDate date){
    Period period = Period.between(date,LocalDate.now());
    int months = period.getYears() * MONTHS_IN_YEAR + period.getMonths();
    return months <= DEFAULT_MONTH;
  }

  /**
   * Check are there any crashes or moving violations committed with this vehicle in the last
   * six months
   * @param incidents crashes or violations
   * @param list list to figure out types of incidents
   * @return True or False
   */
  private Boolean incidentsValid(Set<Incident> incidents, String[] list){
    if (incidents!=null) {
      for (Incident item : incidents) {
        List<String> incidentsList = Incident.getIncidents(list);
          if (incidentsList.contains(item.getIncident().toLowerCase()) &&
              timeValid(item.getDate())) {
            return Boolean.FALSE;
          }
      }
    }
    return Boolean.TRUE;
  }

  @Override
  public Boolean isValid() {
   return incidentsValid(violations,Incident.movingList) &&
       incidentsValid(crashes,Incident.crashList);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VehicleHistory that = (VehicleHistory) o;
    return Objects.equals(violations, that.violations) && Objects.equals(crashes,
        that.crashes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(violations, crashes);
  }

  @Override
  public String toString() {
    return "VehicleHistory{" +
        "violations=" + violations +
        ", crashes=" + crashes +
        '}';
  }

}
