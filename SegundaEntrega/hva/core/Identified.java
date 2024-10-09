package hva.core;

public abstract class Identified extends HotelEntity implements Comparable<Identified>{
  private final String _id;
  protected Identified(String id) {
    _id = id;
  }
  public String getId() {return _id;}
  public int compareTo(Identified iden) {return _id.compareToIgnoreCase(iden.getId());}
  public boolean equals(Identified iden) {return _id.equals(iden.getId());}
  public int hashCode() {return _id.hashCode();}
}
