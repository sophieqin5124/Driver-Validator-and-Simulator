package csvsolver;

import driver.DriverHistory;
import driver.Incident;
import driver.VehicleInfo;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import driver.Name;
import driver.DriverLicenseInfo;
import driver.VehicleInsurance;
import driver.VehicleHistory;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.function.Function;

/**
 * Abstract class for extractor
 */
public abstract class Extractor {
  private static String normalPattern = "yyyy-M-d";
  private static String birthdatePattern = "M/d/yyyy";

  /**
   * split a name string to first name and last name
   * @param name name string
   * @return a name instance
   */
  protected Name nameSplit(String name){
    String[] namePair = name.split(" ");
    if (namePair.length == 2)
      return new Name(namePair[0].trim(),namePair[1].trim());
    else
      return null;
  }

  /**
   * extract values from a violation string to construct corresponding classes
   * @param incident an incident string
   * @return a MovingViolation or NonMovingViolation class
   */
  private Incident violationSplit(String incident){
    String[] incidentPair = incident.split("/");
    if (incidentPair.length == 2) {
      LocalDate date = dateConverter(incidentPair[0].trim(),normalPattern);
      return Incident.validateViolation(date,incidentPair[1].trim());
    } else if (incidentPair.length == 3) {
      LocalDate date = dateConverter(incidentPair[0].trim(),normalPattern);
      Name name = nameSplit(incidentPair[2].trim());
      return Incident.validateViolation(date,incidentPair[1].trim(),name);
    } else {
      return null;
    }
  }
  /**
   * extract values from a violation string to construct Crash class
   * @param incident an incident string
   * @return a Crash class
   */
  private Incident crashSplit(String incident){
    String[] incidentPair = incident.split("/");
 if (incidentPair.length == 3) {
      LocalDate date = dateConverter(incidentPair[0].trim(),normalPattern);
      Name name = nameSplit(incidentPair[2].trim());
      return Incident.validateCrash(date,incidentPair[1].trim(),name);
    } else {
      return null;
    }
  }

  /**
   * Convert a date string to LocalDate type
   * @param stringDate a date string
   * @param pattern a converting pattern
   * @return LocalDate instance
   */
  protected LocalDate dateConverter(String stringDate, String pattern){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
    LocalDate date = LocalDate.parse(stringDate.trim(),formatter);
    return date;
  }

  /**
   * Helper method to split incidents or names list and save converted classes to Set
   * @param elements elements list
   * @param mapper a mapper to convert each string element to corresponding classes
   * @return a Set
   * @param <T> a class strings are converted to
   */
  private <T> Set<T> splitElements(String elements, Function<String, T> mapper) {
    if (elements != null && !elements.trim().isEmpty()) {
      String[] incidentListPair = elements.split(";");
      return Stream.of(incidentListPair).map(mapper).collect(Collectors.toSet());
    } else {
      return null;
    }
  }

  /**
   * Convert String to a set of crashes
   * @param incidents crashes string
   * @return a Set
   */
  protected Set<Incident> crashListSplit(String incidents){
   return splitElements(incidents, this::crashSplit);
  }
  /**
   * Convert String to a set of violations
   * @param incidents violations string
   * @return a Set
   */
  protected Set<Incident> violationListSplit(String incidents){
    return splitElements(incidents, this::violationSplit);
  }
  /**
   * Convert String to a set of names
   * @param names names string
   * @return a Set
   */
  protected Set<Name> nameListSplit(String names){
    return splitElements(names, this::nameSplit);
  }

  /**
   * Class to construct Name class
   */
  static class NameExtractor extends Extractor implements IExtractor<Name> {
    @Override
    public Name extract(FieldMap map) {
      String key = map.getCsvKey();
      String name = map.getMap().get(key);
      return nameSplit(name);
    }
  }

  /**
   * Class to constructor BirthDate class
   */
  static class BirthDateExtractor extends Extractor implements IExtractor<LocalDate> {
    @Override
    public LocalDate extract(FieldMap map) {
      String key = map.getCsvKey();
      String birthdate = map.getMap().get(key);
      return dateConverter(birthdate,birthdatePattern);
    }
  }

  /**
   * Class to construct DriverLicenseInfo class
   */
  static class DriverLicenseExtractor extends Extractor implements IExtractor<DriverLicenseInfo> {
    private static final String NAME = "name";
    private static final String BIRTHDATE = "birthdate";
    private static final String ADDRESS = "address";
    private static final String UID = "unique_number";
    private static final String COUNTRY = "country";
    private static final String STATE = "state";
    private static final String ISSUANCE_DATE = "issuance_date";
    private static final String EXPIRATION_DATE = "expiration_date";
    @Override
    public DriverLicenseInfo extract(FieldMap map) {
      Name name =  nameSplit(map.getMap().get(NAME));
      LocalDate birthDate = dateConverter(map.getMap().get(BIRTHDATE),normalPattern);
      String uid = map.getMap().get(UID).trim();
      String address = map.getMap().get(ADDRESS).trim();
      String country = map.getMap().get(COUNTRY).trim();
      String state = map.getMap().get(STATE).trim();
      LocalDate issuanceDate = dateConverter(map.getMap().get(ISSUANCE_DATE),normalPattern);
      LocalDate expirationDate = dateConverter(map.getMap().get(EXPIRATION_DATE),normalPattern);
      return new DriverLicenseInfo(uid, name, address, birthDate, country, state, issuanceDate,
          expirationDate);

    }
  }

  /**
   * Class to construct VehicleInfo class
   */
  static class VehicleExtractor extends Extractor implements IExtractor<VehicleInfo> {
    private static final String YEAR = "year";
    private static final String COLOR = "color";
    private static final String MAKE = "make";
    private static final String MODEL = "model";
    private static final String UID = "unique_number";
    private static final String OWNER = "owner";

    @Override
    public VehicleInfo extract(FieldMap map) {
      int year = Integer.parseInt(map.getMap().get(YEAR).trim());
      String color = map.getMap().get(COLOR).trim();
      String make = map.getMap().get(MAKE).trim();
      String model = map.getMap().get(MODEL).trim();
      String uid = map.getMap().get(UID).trim();
      Name owner =  nameSplit(map.getMap().get(OWNER));
      return new VehicleInfo(year,color,make,model,uid,owner);
    }
  }

  /**
   * Class to construct VehicleInsurance class
   */
  static class VehicleInsuranceExtractor extends Extractor implements IExtractor<VehicleInsurance> {
    private static final String OWNER = "owner";
    private static final String INSURED_DRIVERS = "insured_drivers";
    private static final String EXPIRATION_DATE = "expiration_date";
    @Override
    public VehicleInsurance extract(FieldMap map) {
      Name owner =  nameSplit(map.getMap().get(OWNER).trim());
      Set<Name> insuredDrivers = nameListSplit(map.getMap().get(INSURED_DRIVERS));
      LocalDate expirationDate = dateConverter(map.getMap().get(EXPIRATION_DATE),normalPattern);
      return new VehicleInsurance(owner,insuredDrivers,expirationDate);
    }
  }

  /**
   * Class to construct DriverHistory class
   */
  static class DriverHistoryExtractor extends Extractor implements IExtractor<DriverHistory> {
    private static final String VIOLATION = "violations";
    @Override
    public DriverHistory extract(FieldMap map) {
      Set<Incident> violations = violationListSplit(map.getMap().get(VIOLATION));
      return new DriverHistory(violations);
    }
  }

  /**
   * Class to construct VehicleHistory class
   */
  static class VehicleHistoryExtractor extends Extractor implements IExtractor<VehicleHistory> {
    private static final String VIOLATION = "violations";
    private static final String CRASH = "crashes";
    @Override
    public VehicleHistory extract(FieldMap map) {
      Set<Incident> violations = violationListSplit(map.getMap().get(VIOLATION));
      Set<Incident> crashes = crashListSplit(map.getMap().get(CRASH));
      return new VehicleHistory(violations,crashes);
    }
  }

}
