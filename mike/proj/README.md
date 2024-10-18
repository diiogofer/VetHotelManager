# PROJECT

## Global
- ids are case insensitive (A = a)
- names are case insensitive (A = a)


## Entities

### Hotel
- ? _habitat[]
- ? _employee[]
- ? _animal[]
- ? _species[]
- ? _season
#### Notes
- implement: habitat[]
- implement: employee[]
- implement: animal[]
- implement: species[]
- initial _season = Spring


### Identified implements Serializable
- final String _id
- _getId()


### Habitat extends Identified
- String _name
- int _area
- Map<String, Animal> _animalMap -> HashMap(animalId, animal)
- ? Tree[]
- ? Adequacy[]
- int keeperCount
- int countSpecies(Species species)
- int countAnimals()
- ? claculateEffort()
- int getKeeperCount()
#### Notes
- _id unique between Habitats
- calculateCleaningEffort
- - area
- - population
- - foreach tree -> tree.calculateCleaningEffort()

#### TODO
- implement: adequacy
- implement: Trees


### Animal extends Identified
- String _name
- Species _species
- Habitat _habitat
- String _healthLog
- ? calculateSatisfaction()
#### Notes
- _id unique between Animals
- animal always has a habitat
- ? has information about health -> _healthLog
- - health is a String with the format ACIDENTE,ERRO,NORMAL from VaccineEvents
- ? calculate satisfaction
- - animals same species in same habitat -> _habitat.countSpecies(_species)
- - animals different species in same habitat -> _habitat.countAnimals() - _habitat.countSpecies(_species)
- - area -> _habitat.getArea()
- - population -> _habitat.countAnimals()
- - ? adequacy -> 20 if positive, -20 if negative, 0 if neutral (0 if missing)
#### TODO
- implement: has information about health
- implement: calculate satisfaction
- implement: adequacy


### Species extends Identified
- final String _name
- Map<String, Animal> _animalMap -> Hasmap(animalId, animal)
- int _vetCount
- void addAnimal(Animal animal)
- int countAnimals()
- int getVetCount()
#### Notes
- _id unique between Species
- _name unique between Species
- _vetCount is the number of vets that can vaccinate species


### Employee extends Identified
- String _name
- calculateSatisfaction()
#### Notes
- _id unique between Employees
### Keeper extends Employee
- ? _habitat[]
- calculateSatisfaction()
#### Notes
- 0 or more habitats
- calculate satisfaction
- - for each habitat they have
- - effort -> habitat.calculateEffort()
- - n keepers that take care of habitat -> habitat.getKeeperCount()
#### TODO
- implement: habitat[]
- implement: species[]
### Vet extends Employee
- ? _canVacinate
- calculateSatisfaction()
#### Notes
- has species that can vaccinate
- calculate satisfaction
- - for each species they can vaccinate
- - population -> species.countAnimals()
- - n vet that can vaccinate species -> species.getVetCount()
#### TODO
- implement: canVacinate


### Tree extends Identified
- String _name
- int _age
- int _baseCleaningEffort
- ? _season
- ? _seasonalCleaningEffort
- ? calculateCleaningEffot()
#### Notes
- _id unique between Trees
- when planted _season = hotel's season
- calculate cleaning effort
- - _baseCleaningEffort
- - _seasonalCleaningEffot
- - _age
- age++ every 4 seasons
#### TODO
- implement: season
- implement seasonalCleaningEffort
### Perene extends Tree
### Caduca extends Tree


### Vaccine extends Identified
String _name
? Species[]
List<VaccineEvent> _eventList
#### Notes
- _id unique between Vaccines
### VaccineEvent implements Serializable
Employee _vet
Animal _animal
? _damage