4.1 MAIN
-verificar nomes usados no esqueleto

4.1.1 SALVAGUARDAR ESTADO
-verificar isto tudo com o prof/esqueleto
HotelManager
    create()
    open()
    save()

Hotel
    _hotelModified:boolean
    (private) setHotelModified(value:boolean)

4.1.2 AVANÇAR ESTACAO
-avançar estacao do ano
-print estacao actual
HotelManager
    advanceSeason():int
Hotel
    advanceSeason():int
    _currentSeason:int
Tree
    advanceSeason()

4.1.3 SATISFACAO GLOBAL
HotelManage
    getGlobalSatisfaction():int
Hotel
    getGlobalSatisfaction():int
Animal
    4.2.4
Employee
    4.3.5

4.1.4 CONSULTAS ?????



4.2 Animals

4.2.1 Visualize all animals
want: ANIMAL | idAnimal | nameAnimal | idSpecies | healthHistory | idHabitat

-get list of animals
-print each animal with string from toString();

Hotel - get list of animals
    +getAllAnimals():Collection<Animal>
Animal - get String to print
    +toString():String
    +healthHistoryToString():String
Species
    +getID():String


4.2.2 Register new animal
Prompt.animalID()
Prompt.animalName()
Prompt.speciesID()
Prompt.habitatID
Prompt.speciesName()

-get animal (null if doesn't exist)
-get species (null if doesnt exist)
-get habitat (null if doesn't exist)
-create species (if doesn't exist)
-create a new animal and add it to hotel, species and habitat

Hotel - get animal, species, habitat
    +getAnimal(animalID:String):Animal
    +getSpecies(speciedID:String):Species
    +getHabitat(habitatID:String):Habitat
Species - create species if doesn't exist
    +Species(speciesID:String, speciesName:String, hotel:Hotel)
Animal - create animal if doesn't exist
    +Animal(animalID:String, animalName:String, species:Species, habitat:Habitat, hotel:Hotel)
Hotel - add created species
    +addSpecies(species:Species)
Hotel - add animal to hotel
    +addAnimal(animal:Animal)
Habitat - add animal to habitat
    +addAnimal(animal:Animal)
Species - add animal to species
    +addAnimal(animal:Animal)

Atributes
Hotel
    -_habitats:Collection<Habitat>
    -_animals:Collection<Animal>
    -_species:Collection<Species>
Habitat
    -_hotel:Hotel
    -_animals:Collection<Animal>
Species
    -_hotel:Hotel
    -_animals:Collection<Animal>
    -_id:String
    -_name:String
Animal
    -_hotel:Hotel
    -_id:String
    -_name:String
    -_species:Species
    -_habitat:Habitat



4.2.3 Transfer animal to another habitat
Prompt.animalID()
Promp.habitatID()

-get animal
-get habitat
-remove and add animal to habitat

Hotel
    +getAnimal(animalID:String)
    +getHabitat(habitatID:String)
Animal
    +setHabitat(newHabitat:Habitat)
Habitat
    +addAnimal(animal:Animal)
    +removeAnimal(animal:Animal)

Attributes
Hotel  
    -_animals:Collection<Animal>
Animal
    -_habitat:Habitat
Habitat
    -_animals:Collection<Animals>



4.2.4 Calculate satisfaction of animal
Prompt.animalID()

- get animal
- calculate satisfaction 20 +3*sameSpecies(a,h) - 2*differentSpecies(a,h) + (area(h)/population(h) + adequacy(a,h))
- return animal's satisfaction rounded to int (math.round())


Hotel
    +getAnimal(animalID:String):Animal
Animal
    +calculateSatisfaction():int
Habitat
    +getNumberOfAnimalsOfSpecies(species:Species):int
    +getPopulation():int
    +getArea():int
    +getAdequacy(species:Species):int
Species
    equals(species:Species):boolean
Adequacy
    getSpecies():Species

Atributes
Hotel
    -_animals:Collection<Animal>
Animal
    -_habitat:Habitat
Habitat
    -_animals:Collection<Animal>
    -_area
    -_speciesAdequacy:Collection<Adequacy>
Adequacy
    -_species:Species



4.3 Employee

4.3.1 Show all employees
want: type | id | name [| idResponsibilities]
type: VET or TRT
idResponsibilites:  VET => speciesID1, speciesID2, ...
                    TRT => habitatID1, habitatID2, ...

-get collection of Employees
-get a string to print from each Employee

Hotel
    getAllEmployees():Collection<Employee>
Employee
    toString():String
    {abstract}getEmployeeType():String
    {abstract}responsibilitiesToString():String
Veterinarian
    getEmployeeType():String
    responsibiltiesToString():String
Keeper
    getEmployeeType():String
    responsibilitiesToString():String

Attributes:
Hotel
    -_employees:Collection<Employee>
Employee
    -_id:String
    -_name:String
Veterinarian
    -_responsibilities:Collection<Species>
Keeper
    -_responsibilities:Collection<Habitat>


4.3.2 Register new employee
Prompt.employeeID() -> String
Prompt.employeeName() -> String
Prompt.employeeType() -> VET or TRT

-get employee (null doesn't exist)
-create employee
-add to Collections

Hotel - get employee
    +getEmployee(employeeID:String):Employee
Employee
    Employee(employeeID:String, name:String, hotel:Hotel)
Veterinarian
    Veterinarian()
Keeper
    Keeper()
Hotel
    +addEmployee(employeeID:String, employeeName:String, employeeType:String)

Attributes
Hotel
    -_employees:Collection<Employee>
Employee
    -_id:String
    -_name: String
    -_hotel:Hotel
Veterinarian
    -_responsibilities:Collection<Species>
Keeper
    -_responsibilities:Collection<Habitat>


4.3.3 Give employee new responsibility - ?????? NOT SURE usar overload com ID ou se fazemos instanceof? exception pode ser lançada dentro do addResponsibility?
Prompt.employeeID()
Prompt.responsibilityKey

-get employee
-get responsibility (species or habitat)
-add responsibility to employee

Hotel
    getEmployee(employeeID:String):Employee
Employee
    {abstract}addResponsibility(id:String)
Veterinarian
    addResponsibility(speciesID:String)
Hotel
    getSpecies(id:String)
Species
    addVeterinarian(vet:Veterinarian)
Keeper
    addResponsibility(habitatID:String)
Hotel
    getHabitat(id:String)
Habitat
    addKeeper(keeper:Keeper)

Atributes
Hotel
    -_employees:Collection<Employee>
    -_species:Collection<Species>
Veterinarian
    -_responsibilities:Collection<Species>
Keeper
    -_responsibilities:Collection<Habitat>
Species
    -_veterinarians:Collection<Veterinarian>
Habitat
    -_keepers:Collection<Keeper>



4.3.4 Remove responsibility from employee
Prompt.employeeID()
Prompt.responsibilityKey()

-get employee
-get responsibility (species or habitat)
-remove responsibility to employee

Hotel
    getEmployee(employeeID:String):Employee
Employee
    {abstract}removeResponsibility(id:String)
Veterinarian
    removeResponsibility(speciesID:String)
Hotel
    getSpecies(id:String)
Species
    removeVeterinarian(vet:Veterinarian)
Keeper
    removeResponsibility(habitatID:String)
Hotel
    getHabitat(id:String)
Habitat
    removeKeeper(keeper:Keeper)

Atributes
Hotel
    -_employees:Collection<Employee>
    -_species:Collection<Species>
Veterinarian
    -_responsibilities:Collection<Species>
Keeper
    -_responsibilities:Collection<Habitat>
Species
    -_veterinarians:Collection<Veterinarian>
Habitat
    -_keepers:Collection<Keeper>


4.3.5 Calculate employee satisfaction
Prompt.employeeID()
VET: 20 - (for each Species in _responsibilities) += population(species) / n_vets(can treat species)
KEEP: 300 - (for each Habitat in _responsibilities) += habitat_work(habitat) / n_keepers(habitat)
    habitat_work = area(habitat) + 3* population(habitat) + (for each Tree in _trees) += cleaningEffort(tree)
    cleaning_effort(tree) = base_effort(tree) + seazonal_effort(tree) + log(age(tree) + 1)
-get employee
-calculate satisfaction of employee
-return satisfaction (int Math.round)

Hotel
    getEmployee(employeeID:String):Employee
Employee
    {abstract} calculateSatisfaction():int
Veterinarian
    calculateSatisfaction():int
Species
    getNumberOfAnimals():int
    getNumberOfVets():int
Keeper
    calculateSatisfaction():int
Habitat
    calculateWork():float
    getNumberOfKeepers():int
Tree
    getCleaningEffort():float
    getSeazonalEffort():int
    getAge():int
Hotel
    getSeason():int


Attributes
Hotel
    -_employees:Collection<Employee>
Veterinarian
    -_responsibilities:Collection<Species>
Species
    -_animals:Collection<Animal>
    -_veterinarians:Collection<Veterinarian>
Keep
    -_responsibilities:Collection<Habitat>
Habitat
    -_animals:Collection<Animal>
    -_keepers:Collection<Keepers>
    -_area:int
Hotel
    -_season:Season                             ??? enum Season?
Tree:
    -_hotel:Hotel
    -_baseCleaningDifficulty:int
    -_treeType:TreeType                         ??? enum TreeType?
    -_ageInSeasons:int

------MENU HABITATS------

4.4.1 VISUALIZAR TODOS OS HABITATS
HABITAT|idHabitat|nome|area|numeroArvores

Hotel 
    getAllHabitats(): Collection<Habitat>
Habitat
    toString(): String 
    getAllTrees(): Collection<Tree>
Tree
    toString(): String
    getCicloBiologico(Seanson: int): String

4.4.2 rEGISTAR nOVO hABITAT

Hotel   getHabitat(id)
        addHabitat(id, nome, area: int)
Habitat
        Habitat(id, nome, area)
Tratador
    addHabitat(Habitat: Habitat)

4.4.3 Alterar Area do HABITAT
    Hotel: SetHabitat(id)
    Habitat: SetArea(area: int)

4.4.4 Alterar influencia de um habitat sobre uma especie
    Hotel   getHabitat(id): habitat
    Habitat: setInfluence(SpecieId, influence: String)

4.4.5  Plantar uma nova arvore num habitat
Hotel
    getHabitat(id:String)
    getTree(id:String)
    addTree(id,nome,idade,dificulty: int, type:String)
Tree
    Tree(id,nome,idade,dificulty: int, type:String)
Habitat
    addTree(tree: Tree)

4.4.6 Visualizar todas as arvores de um habitat
Hotel: getHabitat(id)
Habitat: getAllTrees():Collection<Tree>
Tree: toString(): String


4.5
4.5.1
-lista de vacinas 
-toString de cada vacina da lista
Hotel
    getAllVacinas():Collection<Vacine>
Vacine
    toString():String

4.5.2
Hotel
    getVacine(vacineID:String):Vacine
    getSpecies(speciesID:String):Species
    addVacine(id:String, name:String, species:Collection<Species>)
Vacine
    Vacine(id:String, name:String, species:Collection<Species>)

4.5.3 VACINAR ANIMAL
-verificar se vacina existe
-verificar se emplyee existe e se é veterenario
-verificar que animal existe e obetr speciesID
-verificar que veterenarian tem a responsabilidade
-criar VacineEvent
Hotel
    getVacine(vacineID:String):Vacine
    getEmployee(employeeID:String):Employee
    getAnimal(animalID:String):Animal
Animal
    getSpecies():Species
Species
    getID():String
Employee
    {abstract} getType():String                    VET ou TRT
    {abstract} hasResponsibility(responsibilityID:String):boolean
Hotel
    addVacineEvent(vacine:Vacine, vet:Veterenarian animal:Animal)       Ver onde é preciso adicionar estes eventos
VacineEvent
    VacineEvent(vacine:Vacine, vet:Veterenarian, animal:Animal)
    calculateEventDamage(vacine:Vacine, animal:Animal)
    no fim o VacineEvent vai ter: _vacideID:String, _vetID:String, _speciesID:String, _damageToAnimal:int
Animal
    addVacineEvent(vacineEvent:VacineEvent)
Veterenarian
    addVacineEvent(vacineEvent:VacineEvent)

4.5.4
-obter lista de vacinas
-toString de cada vacina
Hotel
    getAllVacinesEvents():Collection<VacineEvents>
VacineEvent
    toString():String

4.6
4.6.1
-obter habitat
-obter lista de animal no habitat
-animal toString
Hotel
    getHabitat(habitatID:String):Habitat
Habitat
    getAllAnimals():Collection<Animal>
Animal
    toString():String

4.6.2
-obter animal
-obter vacineEvents do animal
-vacineEvent toString() para cada evento

Hotel
    getAnimal(animalID:String):Animal
Animal
    getAllVacineEvents():Collection<VacineEvent>
VacineEvent
    toString():String

4.6.3
-obter veterenario
-obter vacineEvents do Veterenario
-vacineEvent toString() para cada evento

Hotel
    getEmployee(employeeID):Employee
Employee
    {abstract} getType():String                    VET ou TRT
Veterenarian
    getAllVacineEvents():Collection<VacineEvent>
VacineEvent
    toString():String

4.6.4
-obter lista de vacinas
-filtrar as que dano > 0
Hotel
    getBadVacineEvent():Collection<Vacine>


