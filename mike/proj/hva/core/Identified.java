package hva.core;

public abstract class Identified {
  private String _id;
  Identified(String id) {
    _id = id;
  }
  String getId() {return _id;}
}
