package csvsolver;

import java.util.HashMap;
import java.util.Map;

/**
 * A class for hashmaps to store values from each csv cell
 */
public class FieldMap {
  private String csvKey;
  private Map<String, String> csvMap;
  private Map<String, String> map;

  /**
   * Constructor for FieldMap
   * @param csvKey keys (csv headers)
   * @param csvMap hashmaps (for each csv cell)
   */
  public FieldMap(String csvKey,Map<String, String> csvMap) {
    this.csvKey = csvKey;
    this.csvMap = csvMap;
    this.map = this.addFieldMap(csvKey, csvMap);
  }

  /**
   * Add values to csv maps
   * @param csvKey keys (csv headers)
   * @param csvMap hashmaps (for each csv cell)
   * @return hashmaps (for each csv cell)
   * @throws IllegalArgumentException throws when a key cannot be found in the csv
   */
  public Map<String, String> addFieldMap(String csvKey, Map<String, String> csvMap) throws
      IllegalArgumentException {
    Map<String, String> map = new HashMap<>();
    String input = csvMap.getOrDefault(csvKey, null);
    if (input == null)
      throw new IllegalArgumentException("Cannot find the valid key in csvMap!");
    String[] pairs = input.split("\\|");
    for (String pair : pairs) {
      String[] keyValue = pair.split(":");
      if (keyValue.length == 2) {
        String key = keyValue[0].trim();
        String value = keyValue[1].trim();
        map.put(key, value);
      } else {
        map.put(csvKey, csvMap.getOrDefault(csvKey, null));
      }
    }
    return map;
  }

  public String getCsvKey() {
    return csvKey;
  }

  public Map<String, String> getCsvMap() {
    return csvMap;
  }

  public Map<String, String> getMap() {
    return map;
  }
}
