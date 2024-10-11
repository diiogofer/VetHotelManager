package hva.core;

import java.io.Serializable;

public abstract class Identified implements Comparable<Identified>, Serializable {
  
  private final String _id;

  /*
  Nome não incluido porque comportamento de nome é diferente entre subclasses. 
  (Species tem nomes unicos)
  */

  protected Identified(String id) {
    _id = id;
  }
  public String getId() {
    return _id;
  }
  public int compareTo(Identified iden) {
    return _id.compareToIgnoreCase(iden.getId());
  }
  public boolean equals(Identified iden) {
    return _id.equalsIgnoreCase(iden.getId());
  }
  public int hashCode() {
    return _id.toLowerCase().hashCode();
  }
}
