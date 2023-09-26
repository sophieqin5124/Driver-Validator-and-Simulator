package driver;

import java.util.Objects;

/**
 * Class to store name information
 */
public class Name {
private String firstName;
private String lastName;

  /**
   * Constructor for Name class
   * @param firstName first name
   * @param lastName last name
   */
  public Name(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  /**
   * Getter for first name
   * @return first name
   */
  public String getFirstName() {
    return firstName;
  }
  /**
   * Getter for last name
   * @return last name
   */
  public String getLastName() {
    return lastName;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Name name = (Name) o;
    return Objects.equals(firstName, name.firstName) && Objects.equals(lastName,
        name.lastName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName);
  }

  @Override
  public String toString() {
    return lastName + ", " + firstName;
  }
}
