package hva.core;

import java.util.*;

public class Habitat extends Identified{
  private String _name;
  private int _area;
  private Map<String, Animal> _animalMap = new HashMap<>();
  private Set<Tree> _treeSet = new HashSet<>();

  Habitat(String id, String name, int area) {
    super(id);
    _name = name;
    _area = area;
  }
  void addAnimal(Animal newAnimal) {
    _animalMap.putIfAbsent(newAnimal.getId().toLowerCase(), newAnimal);
  }

  void addTree(Tree tree) {_treeSet.add(tree);}
  void removeTree(Tree tree) {_treeSet.remove(tree);}

  public List<Tree> getAllTrees() {
    List<Tree> list = new ArrayList<>(_treeSet);
    Collections.sort(list);
    return Collections.unmodifiableList(list);
  } 

  public String toString() {
    return "HABITAT|" + getId() + "|" + _name + "|" + _area + "|" + _treeSet.size();
  }
}
