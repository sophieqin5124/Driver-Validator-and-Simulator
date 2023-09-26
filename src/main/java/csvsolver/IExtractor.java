package csvsolver;

public interface IExtractor<T> {

  /**
   * Generic method to extract values from hashmaps
   * @param map hashmap
   * @return returned classes
   */
  T extract(FieldMap map);

}
