package hva.core;

import java.io.Serializable;

/**
 * Abstract base class for entities identified by a unique ID.
 * Implements Comparable and Serializable interfaces.
 */
public abstract class Identified implements Comparable<Identified>, Serializable{
  
  /** The unique ID of the entity. */
  private String _id;

  /** The name of the entity. */
  private String _name;

  /**
   * Constructor for the Identified class.
   * 
   * @param id The unique ID of the entity.
   * @param name The name of the entity.
   */
  Identified(String id, String name) {
    _id = id;
    _name = name;
  }

  /**
   * Gets the ID of the entity.
   * 
   * @return The ID of the entity.
   */
  protected String getId() {
    return _id;
  }

  /**
   * Gets the name of the entity.
   * 
   * @return The name of the entity.
   */
  protected String getName() {
    return _name;
  }

  /**
   * Compares this entity to another based on their IDs.
   * 
   * @param iden Another entity to compare to.
   * @return A negative, zero, or positive integer if this entity is less than, equal to, or greater than the specified entity.
   */
  public int compareTo(Identified iden) {
    return (_id.toLowerCase()).compareTo(iden.getId().toLowerCase());
  }

  /**
   * Checks if this entity is equal to another based on their IDs.
   * 
   * @param iden Another entity to compare to.
   * @return true if the IDs are equal, false otherwise.
   */
  public boolean equals(Identified iden) {
    return _id.equalsIgnoreCase(iden.getId());
  }
}
