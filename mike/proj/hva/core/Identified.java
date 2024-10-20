package hva.core;

public abstract class Identified implements Comparable<Identified>{
  private String _id;
  Identified(String id) {
    _id = id;
  }
  public String getId() {return _id;}
  public int compareTo(Identified iden) {
    return (_id.toLowerCase()).compareTo(iden.getId().toLowerCase());
  }
  public boolean equals(Identified iden) {
    return _id.equalsIgnoreCase(iden.getId());
  }
}
