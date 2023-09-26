package driver;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;

/**
 * Class to store vehicle insurance information
 */
public class VehicleInsurance implements IValid{
  private Name owner;
  private Set<Name> insuredDrivers;
  private LocalDate expirationDate;

  /**
   * Constructor for VehicleInsurance
   * @param owner the vehicle’s official owner
   * @param insuredDrivers other people covered by insurance as drivers
   * @param expirationDate the expiration date of the insurance
   */
  public VehicleInsurance(Name owner, Set<Name> insuredDrivers, LocalDate expirationDate) {
    this.owner = owner;
    this.insuredDrivers = insuredDrivers;
    this.expirationDate = expirationDate;
  }

  /**
   * Getter for owner
   * @return owner of the vehicle
   */
  public Name getOwner() {
    return owner;
  }

  /**
   * Getter for insured drivers
   * @return insured drivers
   */
  public Set<Name> getInsuredDrivers() {
    return insuredDrivers;
  }

  /**
   * Getter for expiration date
   * @return expiration date
   */
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  @Override
  public Boolean isValid() {
    return LocalDate.now().isBefore(expirationDate);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VehicleInsurance that = (VehicleInsurance) o;
    return Objects.equals(owner, that.owner) && Objects.equals(insuredDrivers,
        that.insuredDrivers) && Objects.equals(expirationDate, that.expirationDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(owner, insuredDrivers, expirationDate);
  }

  @Override
  public String toString() {
    return "VehicleInsurance{" +
        "owner=" + owner +
        ", insuredDrivers=" + insuredDrivers +
        ", expirationDate=" + expirationDate +
        '}';
  }
}
