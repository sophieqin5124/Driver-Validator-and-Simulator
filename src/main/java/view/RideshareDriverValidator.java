package view;

import csvsolver.CsvReader;
import driver.AcceptedPool;
import java.util.Scanner;

/**
 * RideshareDriverValidator class to run the program
 */
public class RideshareDriverValidator {
  public static final String csv = "driver-info.csv";

  public static void main(String[] args) {
      CsvReader reader = new CsvReader(csv);
      AcceptedPool pool = reader.readToPool();
      Scanner scanner = new Scanner(System.in);
      System.out.println("Search registered drivers by last name: ");
      String commandLine = scanner.nextLine();
    System.out.println(pool.provideDriverInformation(commandLine));
    }
  }
