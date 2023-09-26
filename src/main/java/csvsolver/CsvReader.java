package csvsolver;

import driver.AcceptedPool;
import driver.DriverInfo;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Read csv file and store converted drivers to the pool
 */
public class CsvReader {

  public static final String ROOT = "./src/main/resources/";
  private String csvFile;

  /**
   * Constructor for CsvReader
   * @param csvFile source file
   */
  public CsvReader(String csvFile) {
    this.csvFile = ROOT + csvFile;
  }

  /**
   * Main method to read file, construct drivers and add drivers to pool
   * @return a pool of accepted drivers
   */
  public AcceptedPool readToPool() {
    String line;
    List<String> keys;
    List<String> values;
    Map<String, String> csvMap = new HashMap<>();
    AcceptedPool pool = new AcceptedPool();
    try (BufferedReader inputCSVFile = new BufferedReader(new InputStreamReader(
        new FileInputStream(csvFile), StandardCharsets.UTF_8))) {
      line = inputCSVFile.readLine();
      keys = CsvParser.parseLine(line);
      while ((line = inputCSVFile.readLine()) != null) {
        values = CsvParser.parseLine(line);
        csvMap = CsvParser.addMap(keys, values);
        CreateDriver creator = new CreateDriver();
        for (Map.Entry<String, String> entry : csvMap.entrySet()) {
          FieldMap fieldMap = new FieldMap(entry.getKey() , csvMap);
          creator.makeExtractor(fieldMap);
        }
        DriverInfo driver = creator.create();
        if (driver.isValid())
          pool.addPool(driver);
      }
    } catch (FileNotFoundException fnfe) {
      System.out.println("*** OOPS! A file was not found : " + fnfe.getMessage());
      fnfe.printStackTrace();
    } catch (IOException e) {
      System.out.println("Something went wrong! : " + e.getMessage());
      e.printStackTrace();
    }
    return pool;
  }
}
