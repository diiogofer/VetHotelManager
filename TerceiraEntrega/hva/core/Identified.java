package hva.core;

import java.io.Serializable;

public abstract class Identified implements Comparable<Identified>, Serializable{
  private String _id;
  private String _name;

  Identified(String id, String name) {
    _id = id;
    _name = name;
  }
  protected String getId() {return _id;}
  protected String getName() {return _name;}

  public int compareTo(Identified iden) {
    return (_id.toLowerCase()).compareTo(iden.getId().toLowerCase());
  }
  public boolean equals(Identified iden) {
    return _id.equalsIgnoreCase(iden.getId());
  }
}
