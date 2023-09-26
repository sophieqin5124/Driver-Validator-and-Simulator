package driver;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Abstract class for all incidents, including moving, non-moving violations
 * and crashes
 */
public abstract class Incident {
  protected LocalDate date;
  protected String incident;

  protected Name name;
  public static String[] movingList = {"Distracted driving","Reckless driving","Speeding",
      "Driving under influence","Failure to respect traffic signs",
      "Driving without a valid license and/or insurance"};
  public static String[] nonMovingList = {"Parking violation","Paperwork issues",
      "Problems with the vehicle"};
  public static String[] crashList = {"A fender-bender","A crash without bodily injuries",
      "A crash involving bodily injuries"};

  /**
   * Constructor for Incident
   * @param date the date incident happened
   * @param incident specific incident type
   * @param name the name of offending driver
   */
  public Incident(LocalDate date, String incident, Name name) {
    this.date = date;
    this.incident = incident;
    this.name = name;
  }

  /**
   * Constructor for Incident
   * @param date the date incidents happened
   * @param incident specific incident type
   */
  public Incident(LocalDate date, String incident) {
    this.date = date;
    this.incident = incident;
  }

  /**
   * Convert Array to List<String>
   * @param list An array needs to be converted
   * @return List<String> after converting
   */
  private static List<String> listConverter(String[] list){
    return Stream.of(list).map(String::toLowerCase).collect(Collectors.toList());
  }

  /**
   * Getter for incidents list
   * @param list incidents list needs to be converted
   * @return List<String> incidents list
   */
  public static List<String> getIncidents(String[] list) {
    return listConverter(list);
  }

  /**
   * Figure out which type of violations
   * @param date the date of violation
   * @param input specific violation type
   * @return MovingViolation or NonMovingViolation object
   */
  public static Incident validateViolation(LocalDate date, String input){
    String incident = input.trim().toLowerCase();
    if (getIncidents(movingList).contains(incident))
      return new MovingViolation(date, input.trim());
    if (getIncidents(nonMovingList).contains(incident))
      return new NonMovingViolation(date, input.trim());
    return null;
  }

  /**
   * Figure out which type of violations
   * @param date the date of violation
   * @param input specific violation type
   * @param name the name of offending driver
   * @return MovingViolation or NonMovingViolation object
   */
  public static Incident validateViolation(LocalDate date, String input, Name name){
    String incident = input.trim().toLowerCase();
    if (getIncidents(movingList).contains(incident))
      return new MovingViolation(date, input.trim(),name);
    if (getIncidents(nonMovingList).contains(incident))
      return new NonMovingViolation(date, input.trim(),name);
    return null;
  }

  /**
   * Figure out crashes
   * @param date the date of crash
   * @param input specific crash type
   * @param name the name of offending driver
   * @return Crash object
   */
  public static Incident validateCrash(LocalDate date, String input, Name name){
    String incident = input.trim().toLowerCase();
    if (getIncidents(crashList).contains(incident))
      return new Crash(date, input.trim(),name);
    return null;
  }

  /**
   * Getter for date
   * @return date
   */
  public LocalDate getDate() {
    return date;
  }

  /**
   * Getter for incident
   * @return incident
   */
  public String getIncident() {
    return incident;
  }

  /**
   * Getter for name
   * @return name
   */
  public Name getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Incident incident1 = (Incident) o;
    return Objects.equals(date, incident1.date) && Objects.equals(incident,
        incident1.incident) && Objects.equals(name, incident1.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, incident, name);
  }

  /**
   * Nested class for moving violations
   */
  static class MovingViolation extends Incident {
    /**
     * Constructor for moving violations
     * @param date the date of violation
     * @param incident the specific incident type
     * @param name the name of offending driver
     */
    public MovingViolation(LocalDate date, String incident, Name name) {
      super(date, incident, name);

    }

    /**
     * Constructor for moving violations
     * @param date the date of violation
     * @param incident the specific incident type
     */
    public MovingViolation(LocalDate date, String incident) {
      super(date, incident);
    }

    @Override
    public String toString() {
      return "MovingViolation{" +
          "date=" + date +
          ", moving violation='" + incident + '\'' +
          ", name=" + name +
          '}';
    }
  }

  /**
   * Nested class for non-moving violations
   */
  static class NonMovingViolation extends Incident{
    /**
     * Constructor for non-moving violations
     * @param date the date of violation
     * @param incident the specific incident type
     * @param name the name of offending driver
     */
    public NonMovingViolation(LocalDate date, String incident, Name name) {
      super(date, incident, name);
    }
    /**
     * Constructor for non-moving violations
     * @param date the date of violation
     * @param incident the specific incident type
     */
    public NonMovingViolation(LocalDate date, String incident) {
      super(date, incident);
    }

    @Override
    public String toString() {
      return "NonMovingViolation{" +
          "date=" + date +
          ", non moving violation='" + incident + '\'' +
          ", name=" + name +
          '}';
    }
  }

  /**
   * Nested class for crashes
   */
  static class Crash extends Incident{
    /**
     * Constructor for crashes
     * @param date the date of crash
     * @param incident the specific incident type
     * @param name the name of offending driver
     */
    public Crash(LocalDate date, String incident, Name name) {
      super(date, incident, name);
    }

    @Override
    public String toString() {
      return "Crash{" +
          "date=" + date +
          ", crash='" + incident + '\'' +
          ", name=" + name +
          '}';
    }
  }
}
