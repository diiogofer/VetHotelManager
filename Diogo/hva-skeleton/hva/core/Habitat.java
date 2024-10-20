package hva.core;

import java.util.*;

public class Habitat extends Identified implements Responsibility {
    private String _name;
    private int _area;
    private Map<String, Animal> _animalMap = new HashMap<>();
    private Map<String, SpeciesAdequacy> _adequacies = new HashMap<>();
    private Set<Tree> _treeSet = new HashSet<>();
    
    Habitat(String habitatId, String habitatName, int habitatArea) { 
        super(habitatId);
        _name = habitatName;
        _area = habitatArea;
    }

    void addAnimal(Animal newAnimal) {
        _animalMap.putIfAbsent(newAnimal.getId().toLowerCase(), newAnimal);
    }

    void removeAnimal(Animal animal) {
        _animalMap.remove(animal.getId().toLowerCase());
    }

    void addTree(Tree tree) {
        _treeSet.add(tree);
    }
    void removeTree(Tree tree) {
        _treeSet.remove(tree);
    }

    //to calculate animal satisfaction: 
    int countSpecies(Species species) {
        int counter = 0;
        for(Animal a : _animalMap.values()) {
            if(a.getSpecies().equals(species)) counter++;
        }
        return counter;
    }
  
    int countPopulation() {
        return _animalMap.size();
    }
  
    int getArea() {
        return _area;
    }
    
    void setArea(int area) {
        _area = area;
    }
  
    int getAdequacy(Species species) {
        SpeciesAdequacy adequacy = _adequacies.get(species.getId().toLowerCase());
        return adequacy == null ? SpeciesAdequacy.NEUTRAL.getValue() : adequacy.getValue();
    }


    public List<Tree> getAllTrees() {
        List<Tree> list = new ArrayList<>(_treeSet);
        Collections.sort(list);
        return Collections.unmodifiableList(list);
  }   
    public String toString() {
        return "HABITAT|" + getId() + "|" + _name + "|" + _area + "|" + _treeSet.size();
    }
}
