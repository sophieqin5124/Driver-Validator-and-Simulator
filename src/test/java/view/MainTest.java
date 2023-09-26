package view;

import static org.junit.jupiter.api.Assertions.*;
import static view.RideshareDriverValidator.csv;

import csvsolver.CsvReader;
import driver.AcceptedPool;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MainTest {

  @BeforeEach
  void setUp() {
  }

  @Test
  void main() {
    CsvReader reader = new CsvReader(csv);
    AcceptedPool pool = reader.readToPool();
    String expectedOutput = "Search registered drivers by last name: " + System.lineSeparator() +
        pool.provideDriverInformation("Qin") + System.lineSeparator();
    String input = "Qin"+ System.lineSeparator();
    InputStream in = new ByteArrayInputStream(input.getBytes());
    System.setIn(in);
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    RideshareDriverValidator.main(new String[]{});
    assertEquals(expectedOutput, outContent.toString());
  }
}