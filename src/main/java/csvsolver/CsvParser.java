package csvsolver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Class to store parsing methods
 */
public class CsvParser {

  /**
   * Convert each csv line to ArrayList<String>
   * @param line csv line
   * @return ArrayList<String>
   */
  public static ArrayList<String> parseLine(String line) {
    ArrayList<String> result = new ArrayList<>();
    List<String> items = List.of(line.split(","));
    for(String item:items) {
      item = item.replaceAll("\uFEFF", "");
      result.add(item);
    }
    return result;
  }

  /**
   * Create hashmaps from string lists
   * @param keys List<String> to store keys
   * @param values List<String> to store values
   * @return Map<String, String>
   */
  public static Map<String, String> addMap(List<String> keys, List<String> values) {
    Map<String, String> map = new HashMap<>();
    Iterator<String> kIterator = keys.iterator();
    Iterator<String> vIterator = values.iterator();
    while (kIterator.hasNext() && vIterator.hasNext()) {
      map.put(kIterator.next(), vIterator.next());
    }
    return map;
  }
}
