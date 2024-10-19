# PROJECT

## Perguntas
- Pode-se aceitar string vazios? (id, nome, etc)
- Hotel
- - registerEmployee() mas instancia-se employee no app (DoRegisterEmployee) com construtores publicos?
- - ou
- - registerVet() e registerKeeper()

- 4.5.2
- - espaços brancos sao irrelevantes? n se tem que fazer nada ou é preciso removelos?
- 4.2.2
- - despois de registar especie tenta-se registar o mesmo animal outra vez?

## Global
- ids are case insensitive (A = a)
- names are case insensitive (A = a)
- open-closed: should be able to add new Subclasses without refactoring
- don't assume only 1 hotel instance


### Exceptions
- DuplicateAnimalException()
- DuplicateEmployeeException()
- InvalidInputException (String msg)
- UnknownHabitaException(id)
- UnknownSpeciesException(id)
- UnknownResponsibilityException(id)


## Entities

### Hotel implements Serializable
- Map<String, Animal> -> HashMap<animalId, animal>
- Map<String, Species> -> HasMap<speciesId, Species>
- Map<String, Employee> -> HashMap<employeeId, Employee>
- Map<String, Habitat> -> HashMap<habitatId, Habitat>
- ? _season
- <T extends Identified> Boolean containsIdentified(T identified, Map<String, T> map)
- <T extends Identified> void addIdentified(T identified, Map<String, T> map)
- void registerAnimal(String animalId, String animalName, String speciesId, String habitatId)
- void registerSpecies(String speciesId, String name)
- void registerEmployee(Employee employee)
- void registerVet(String vetId, String vetName)
- void registerKeeper(String keeperId, String keeperName)
- Habitat getHabitat(String habitatId)
- Species getSpecies(String speciesId)
- void addResponsibility(String employeeId, String responsibilityId)
- void removeResponsibility(String employeeId, String responsibilityId)
#### Notes
- initial _season = Spring
- containsIdentified(id, map) -> map.containsKey(id.toLowerCase())
- addIdentified(identified, map) -> map.addIfAbsent(identified.getId().toLowerCase(), identified)
- registerAnimal() checks:
- - animal id already exists
- - habitat id already exists
- - species id already exists


### Identified implements Serializable
- final String _id
- String getId()

### Responsibility - PROBABLY NOT NEEDED
- String getId()


### Habitat extends Identified implements Responsibility
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
- void addKeeper()
- void removeKeeper()
- void addAnimal(Animal animal)
- void removeAnimal(Animal animal)
#### Notes
- _id unique between Habitats
- calculateCleaningEffort
- - area
- - population
- - foreach tree -> tree.calculateCleaningEffort()
- all Id Collection operations (add/remove) are case insensitive

#### TODO
- implement: adequacy
- implement: Trees


### Animal extends Identified
- String _name
- Species _species
- Habitat _habitat
- String _healthLog
- ? calculateSatisfaction()
- setHabitat(Habitat habitat)
- String toString()
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
- toString()
- - ANIMAL|getId()|_name|_species.getId()|DAMAGE,DAMAGE,DAMAGE|_habitat.getId()
- - ANIMAL|getId()|_name|_species.getId()|VOID|_habitat.getId()
#### TODO
- implement: has information about health
- implement: calculate satisfaction
- implement: adequacy


### Species extends Identified implements Responsibility
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


### abstract Employee extends Identified
- String _name
- Hotel _hotel
//- Map<String, Responsibility> -> responsibilityId, Responsibility
- calculateSatisfaction()
- String toString()
- abstract String employeeTypeToString()
- abstract String responsibilitiesToString()
- abstract addResponsibility(String responsibilityId)
- abstract removeResponsibility(String responsibilityId)
- Hotel getHotel()
#### Notes
- _id unique between Employees
- _calculateSatisfaction() must allow the user to change in runtime how satisfaction is calculated
- - the different ways don't need to be added, the default way is the one enunciated in the project
- - ? application of Strategy pattern for calculateSatisfaction()
- toString()
- - type|getId()|_name|resp.getId(),resp.getId()
- - type|getId()|_name

### Keeper extends Employee
- Map<String, Habitat> _habitatMap -> HashMap(habitatId, habitat)
- calculateSatisfaction()
- String employeeTypeToString()
- addResponsibility(String habitatId)
- removeResponsibility(String habitatId)
#### Notes
- 0 or more habitats (responsibility)
- calculate satisfaction
- - for each habitat they have
- - effort -> habitat.calculateEffort()
- - n keepers that take care of habitat -> habitat.getKeeperCount()
- employeeTypeToString() -> TRT
#### TODO
- implement: habitat[]
- implement: species[]

### Vet extends Employee
- Map<String, Species> _speciesMap -> HashMap(speciesId, species)
- calculateSatisfaction()
- String employeeTypeToString()
- addResponsibility(String speciesId)
- removeResponsibility(String speciesId)
#### Notes
- has species that can vaccinate (responsibility)
- calculate satisfaction
- - for each species they can vaccinate
- - population -> species.countAnimals()
- - n vet that can vaccinate species -> species.getVetCount()
- employeeTypeToString() -> VET
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
- _eventList must be in order of events
### VaccineEvent implements Serializable
Employee _vet
Animal _animal
? _damage


## FUNCTIONALITY
### 4.2 ANIMALS hva.app.animal

#### 4.2.1 DoShowAllAnimals
##### Needed
- hotel.getAllAnimals()
- animal.toString()
- - ANIMAL|id|name|speciesId|healthLog|habitatId
##### Notes
- foreach animal a -> _display.addLine(a)

#### 4.2.2 DoRegisterAnimal
##### Needed
- Form
- - animalKey
- - animalName
- - speciesKey
- - habitatKey
- if no speciesId registered
- - speciesName
- hva.core.exception
- - DuplicateAnimalException()
- - InvalidInputException("Species name already registered.")
##### Notes
- id and name checks have to be case insensitive
- animal already exists (id)
- - DuplicateAnimalKeyException
- species doesn't exist (id)
- - prompt for species name
- - register species
- species name already exists
- - System.out.println(ex.getMessage())
- add animal to it's habitat and species


#### 4.2.3 DoTransferToHabitat
##### Needed
- Form
- - animalKey
- - habitatKey

##### Notes
- any error
- - no changes to hotel
- - System.out.println(ex.getMessage())
- no error
- - if newHabitat.contains(Animal animal) return; 
- - else:
- - remove animal from previous habitat
- - change animal habitat
- - add animal to new habitat

#### 4.2.4 DoShowSatisfactionOfAnimal
##### Needed
- Form
- - animalKey
##### Notes
- result rounded int Math.round()
- any error
- - System.out.println(ex.getMessage())

### 4.3 EMPLOYEES hva.app.employee
#### 4.3.1 DoShowAllEmployees
##### Needed
- hotel.getAllEmployees()
- employee.responsibilitiesToString()
- employee.toString()
- - employeeType|id|name|responsibilityId1,responsibilityId2,...
- no responsibility
- - employeeType|id|name
##### Notes
- foreach Employee e -> _display.addLine(e)


#### 4.3.2 DoRegisterEmployee
##### Needed
- Form
- - employeeKey
- - employeeName
- - employeeType
- DuplicateEmployeeException()
- hotel.registerEmployee()
##### Notes
- employeeType uses addOptionField("employeeType", hva.app.employee.Prompt.employeeType(), "TRT", "VET")
- DuplicateEmployeeException -> DuplicateEmployeeKeyException


#### 4.3.3 DoAddResponsibility
##### Needed
- Form
- - employeeKey
- - responsibilityKey
- Hotel
- - addEmployeeResponsibility(String employeeId, String responsibilityId)
- - getHabitat(String habitatId)
- - getSpecies(String speciesId)
- Employee
- - abstract addResponsibility(String responsibilityId)
##### Notes
- UnknownHabitaException(id) -> UnknownResponsibilityException(id) -> NoResponsibilityException
- UnknownSpeciesException(id) -> UnknownResponsibilityException(id) -> NoResponsibilityException


#### 4.3.4 DoRemoveResponsibility
##### Needed
- Form
- - employeeKey
- - responsibilityKey
- Hotel
- - removeResponsibility(String employeeId, responsibilityId)
- Employee
- - removeResponsibility(String responsibilityId)
##### Notes
- UnknownHabitaException(id) -> UnknownResponsibilityException(id) -> NoResponsibilityException
- UnknownSpeciesException(id) -> UnknownResponsibilityException(id) -> NoResponsibilityException


#### 4.3.5 DoShowSatisfactionOfEmployee
##### Needed
- Form
- - employeeId
##### Notes
- result is int rounded with Math.round


### 4.4 HABITAT hva.app.habitat
#### 4.4.1
##### Needed
- hotel.getAllHabitats()
- habitat.toString()
- - HABITAT|id|name|area|nTrees
- tree.toString()
- - ÁRVORE|id|name|ageInYears|baseDifficulty|treeType|bioCycle
##### Notes
- bioCycle
- - needs to be implemented with pattern described in section 2
#### 4.4.2
##### Needed
##### Notes
- habitat exists
- - DuplicateHabitatException(id) -> DuplicateHabitatKeyException(key)
#### 4.4.5
##### Needed
- Form
- - String habitatKey
- - String treeKey
- - String treeName
- - int treeAge
- - int baseCleaningEffort
- - option treeType
##### Notes
- use option to get input until "PERENE" or "CADUCA"
- tree id exists
- - DuplicateTreeException -> DuplicateTreeKeyException


### 4.5 VACCINE hva.app.vaccine
#### 4.5.2 DoRegisterVaccine
##### Needed
- Form
- - vaccineKey
- - vaccineName
- - animalIds
##### Notes
- animalIds is a String of ids with format: id1,id2,id3
- white spaces are irrelevant?
- vaccine exists: DuplicateVaccineException -> DuplicateVaccineKeyException