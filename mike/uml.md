4.2 Animals

4.2.1 Visualize all animals
want: ANIMAL | idAnimal | nameAnimal | idSpecies | healthHistory | idHabitat

Hotel
    -_animals:List<Animal>
    + getAllAnimals():List<Animal>
Animal
    - _id:String
    - getID():String
    - _name:String
    - getName():String
    - _species:List<Species>
    - getSpecies():Species                  (1)
    - _health:???
    - getHealth():???
    - _habitat:Habitat
    - getHabitat():Habitat                  (2)
    + toString():String
(1)
Species
    - _id:???
    + getID():???
(2)
Habitat
    - _id:String
    + getID():String

4.2.2 Registar novo animal

Prompt.animalID()
-verify if animal with id exists
Hotel
    -_animals:List<Animal>
    + getAllAnimals():List<Animal>
Animal
    - _id:String
    + getID():String
-exists: exception
-doesn't exist: continue

Prompt.animalName()
Prompt.speciesID()
-verify if species with id exists
Hotel
    -_species:List<Species>
    +getAllSpecies:List<Species>
Species
    -id:???
    +getID():???
-exists: continue
-doesn't exist: create species

Prompt.speciesName()
Species
    +Species(id:???, name:String)
Hotel
    -_species:List<Species>
    +addSpecies(species:Species)

Promp.habitatID()
-verify if habitat exists
Hotel
    -_habitats:List<Habitat>
    +getAllHabitats():List<Habitats>
Habitat
    -_id:String
    +getID():String
-exists: continue
-doesn't exist: exception

-create animal
Animal
    Animal(id:String, name:String, species:Species, habitat:Habitat)
Hotel
    -_animals:List<Animal>
    +addAnimal(animal:Animal)
Species
    -_animals:List<Animal>
    +addAnimal(animal:Animal)
Habitat
    -_animals:List<Animal>
    +addAnimal(animal:Animal)


4.2.3 Transfer animal to another habitat
Prompt.animalID()
-verify if animal with id exists
Hotel
    -_animals:List<Animal>
    + getAllAnimals():List<Animal>
Animal
    - _id:String
    + getID():String
-exists: continue
-doesn't exist: exception

Promp.habitatID()
-verify if habitat exists
Hotel
    -_habitats:List<Habitat>
    +getAllHabitats():List<Habitats>
Habitat
    -_id:String
    +getID():String
-exists: continue
-doesn't exist: exception

-move animal
Animal
    +setHabitat()
Habitat
    -_animals:List<Animal>
    +addAnimal(animal:Animal)
    +removeAnimal(animal:Animal)


4.2.4 Calculate satisfaction of animal
Prompt.animalID()
-verify if animal with id exists
Hotel
    -_animals:List<Animal>
    + getAllAnimals():List<Animal>
Animal
    - _id:String
    + getID():String
-exists: continue
-doesn't exist: exception

-get satisfaction
want: 20 + 3*sameSpecies(a,h) - 2*diferentSpecies(a,h) + (area(h) / population(h)) + suitability(a, h)
Animal
    -_habitat:Habitat
    +getHabitat():Habitat
    +getSatisfaction():int
    -_species:Species
    +getSpecies():Species
    +getSatisfaction():int
Species
    +equals(species:Species):boolean
Habitat
    -_animals:List<Animal>
    +getAnimals():List<Animals>
    -_area:int
    +getArea():int
    +getPopulation():int
    -_speciesSuitability:List<AnimalHabitatSuitability>
    +getSuitability(species:Species):int
AnimalHabitatSuitability
    -_species:Species
    -_suitability:???? String or int?
    +getSpecies():Species

4.3 Employee

4.3.1 Show all employees
want: type | id | name [| idResponsibilities]
type: VET or TRT
idResponsibilites:  VET => speciesID1, speciesID2, ...
                    TRT => habitatID1, habitatID2, ...

Hotel
    -_employees:List<Employee>
    +getAllEmployees():List<Employee>
Employee
    {abstract} #getEmployeeType():String
    -_id:String
    +getID():String
    -_name:String
    {abstract} #getResponsibilites():List<?????>
    +toString():String
Veterenarian
    +getEmloyeeType():String
    -_responsibilites:List<Species>
    +getResponsibilities():List<Species>
Caretaker
    +getEmloyeeType():String
    -_responsibilites:List<Habitat>
    +getResponsibilities():List<Habitat>

4.3.2 Register new employee

Prompt.employeeID() -> String
Hotel
    -_employees:List<Employee>
    +getAllEmployees():List<Employee>
Employee
    -_id:String
    +getID():String
-exists: exception
-doesn't exist: continue

Prompt.employeeName() -> String
Prompt.employeeType() -> VET or TRT
Hotel
    -_employees:List<Employee>
    +addEmployee()
Employee
    -_id:String
    -_name:String
    +Employee(id:String, name:String)
    {abstract} #getEmployeeType():String
Veterenarian
    -_responsibilities:List<Specie>
    +Veterenarian(id:String, name:String)
    +getEmployeeType():String
Caretaker
    -_responsibility:List<Habitat>
    +Caretaker(id:String, name:String)
    +getEmployeeType():String


4.3.3 Give employee new responsibility
Prompt.employeeID()
Hotel
    -_employees:List<Employee>
    +getAllEmployees():List<Employee>
Employee
    -_id:String
    +getID():String
-exists: continue
-doesn't exist: exception

-check type of employee
Employee
    {abstract} #getEmployeeType():String
Veterenarian
    +getEmployeeType():String
Caretaker
    +getEmployeeType():String

-add new responsibility

-VET
Prompt.speciesID()
-verify if species with id exists
Hotel
    -_species:List<Species>
    +getAllSpecies:List<Species>
Species
    -id:???
    +getID():???
-exists: continue
-doesn't exist: exception
-add responsibility
Veterenarian
    -_responsibilities:List<Species>
    +addResponsibility(species:Species)

-TRT
Promp.responsibilityKey()
-verify if habitat exists
Hotel
    -_habitats:List<Habitat>
    +getAllHabitats():List<Habitats>
Habitat
    -_id:String
    +getID():String
-exists: continue
-doesn't exist: exception
-add responsibility
Caretaker
    -_responsibilities:List<Habitat>
    +addResponsibility(habitat:Habitat)







