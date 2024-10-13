package hva.core;

import java.io.Serializable;

/**
 * Represents an abstract class for entities that have a unique identifier.
 * Classes extending this must provide an identifier.
 * Implements {@link Comparable} to allow comparison of entities by their ID,
 * and {@link Serializable} to allow serialization of instances.
 * 
 * <p>This class also provides equality and hashing functionality
 * based on the identifier.</p>
 * 
 */
public abstract class Identified implements Comparable<Identified>, Serializable {
  /** The unique identifier for the entity. */
  private final String _id;

  /*
  Nome não incluido porque comportamento de nome é diferente entre subclasses. 
  (Species tem nomes únicos)
  */

  /**
   * Constructs an Identified entity with a given ID.
   * 
   * @param id the unique identifier for the entity
   */
  protected Identified(String id) {
    _id = id;
  }

  /**
   * Returns the unique identifier of the entity.
   * 
   * @return the identifier of the entity
   */
  public String getId() {
    return _id;
  }

  /**
   * Compares the current entity with another {@link Identified} entity by their IDs,
   * ignoring case.
   * 
   * @param iden the entity to compare to
   * @return a negative integer, zero, or a positive integer if this entity's ID
   *         is lexicographically less than, equal to, or greater than the specified entity's ID
   */
  public int compareTo(Identified iden) {
    return _id.compareToIgnoreCase(iden.getId());
  }

  /**
   * Checks whether the current entity is equal to another {@link Identified} entity by their IDs,
   * ignoring case.
   * 
   * @param iden the entity to compare to
   * @return {@code true} if the IDs are equal, {@code false} otherwise
   */
  public boolean equals(Identified iden) {
    return _id.equalsIgnoreCase(iden._id);
  }

  /**
   * Returns a hash code value for the entity based on its ID.
   * 
   * @return the hash code of the entity's ID
   */
  public int hashCode() {
    return _id.toLowerCase().hashCode();
  }
}
