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
    +getAllAnimals():List<Animal>
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
-create a new animal and add it to hotel, species and habitat

Hotel - get animal, species, habitat
    +getAnimal(animalID:String):Animal
    +getSpecies(speciedID:String):Species
    +getHabitat(habitatID:String):Habitat
Species - create species if doesn't exist
    +Species(speciesID:String, speciesName:String)
Hotel - add created species
    +addSpecies(species:Species)
Animal - create animal if doesn't exist
    +Animal(animalID:String, animalName:String, species:Species, habitat:Habitat)
Hotel - add animal to hotel
    +addAnimal(animal:Animal)
Habitat - add animal to habitat
    +addAnimal(animal:Animal)
Species - add animal to species
    +addAnimal(animal:Animal)

Atributes
Hotel
    -_habitats:List<Habitat>
    -_animals:List<Animal>
    -_species:List<Species>
Habitat
    -_animals:List<Animal>
Species
    -_animals:List<Animal>
    -_id:String
    -_name:String
Animal
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
    -_animals:List<Animal>
Animal
    -_habitat:Habitat
Habitat
    -_animals:List<Animals>



4.2.4 Calculate satisfaction of animal
Prompt.animalID()

-no Habitat -> getNumberSameEspecies(specie) : int
-no Animal -> getSpecies(): Species
-no Habitat -> getNumberDifferentSpecies(Specie): int
-no Habitat -> getArea(): int
-no Habitat -> getPopulation()
-no Habitat -> getAdequação(Specie) : int 

4.3 <<Abstract>>Employee

4.3.1 Show all employees
want: type | id | name [| idResponsibilities]
type: VET or TRT
idResponsibilites:  VET => speciesID1, speciesID2, ...
                    TRT => habitatID1, habitatID2, ...

-Precisamos de uma lista da funcionarios no hotel
-e um toString() para o funcionario
-criar <<ABSTRACT>>getType():String e <<abstract>>getResponsabilidade()


4.3.2 Register new employee
Prompt.employeeID() -> String
Prompt.employeeName() -> String
Prompt.employeeType() -> VET or TRT

-> no hotel ->getFuncionario(id : String) : Funcionario
-> no hotel-> addFuncionario(id, nome, tipoDeFuncionario) no hotel
-> no funcionario -> (Construtor)Funcionario(id,nome)
-> Veterinário -> (COnstrutor)Veterinario(id, nome)
-> Tratador -> (Construtor)Veterinario(id, nome)


4.3.3 Give employee new responsibility
Prompt.employeeID()

->getFuncionario(id): funcionario no Hotel
->no Funcionario -> <<abstract>>addResponsability(id)
->no vet -> addResponsability(id) 
-> no trt -> addResponsability(id)
(para isto acontecer o hotel tem de ter um getSpecies(id): Especie, 
getHabitat(): Habitat, e o Funcionario tem de ter um atributo do tipo hotel)
-> Na Especie ->  _veterinarios: List<veterinarios>
              ->  addVet(Vet) (por causa da cena da satisfação)
              -> removeVet(Vet:Vet)
-> Habitat -> addTratador(trt:Tratador)
              -_tratador: List<Tratador>
              removerTratador(trt: Tratador)



4.3.4 Remove responsibility from employee
Prompt.employeeID()

Hotel -> getFuncionario(id): Funcionario
Funcionario -> <<abstract>> removeResponsabilidade(id) -> getResponsabilidade()
Vet -> removeResponsabilidade(id) -> getResponsabilidade(id)
Trt _> removeResponsabilidade(id) -> getResponsabilidade(id)
Na especie -> removeVet(Vet:veterinario)


_________?????????????DUVIDA????????????????_____________
MANO, Encontrar NVETERINARIOS DA ESPÉCIE MELHOR MANEIRA???????
MANO, MANO, Nós fzemos uma lista de vets, MANO
É QUE ATÉ DÁ JEITO PARA AS VACINAS MANO MANO
_________________________________________________________

4.3.5 Calculate employee satisfaction
Prompt.employeeID()

Queremos um int
worker -> <<ABSTRACT>> getSatisfaction() : int
Vet -> getSatisfação() : int
TRT->  getSatisfação(): int
Especie -> getPopulation():int
           getNumberVets(): int
TRT -> getSatisfação(): int
    ->_habitats: List<Habitat>
Habitat-> getArea(): int
          getPopulation():int
          getNumberTratadores()
          getTrabalhoNoHabit() float
TREE -> _dificuldadeBase: int
        getBaseDifficults(): int
        getEsforçoSazonal(): int
        _idade: int
        getAge(): int
        getEsforçoLimpeza: float

        getTypeOfTree() : int
        _tipo: int ------------------------> ENUMTYPE { CADUCA, PERENE }
Hotel: getSeason(): int -------------------> ENUMTYPE { PRIMAVERA VERAO OUTONO INVERNO }

-> TRABALHO NO HABITAT -> AREA, POPULAÇÃO E AS MERDAS QUE ESTÃO EM ÁRVORE 


------MENU HABITATS------

4.4.1 VISUALIZAR TODOS OS HABITATS
HABITAT|idHabitat|nome|area|numeroArvores

Hotel 
    getAllHabitats(): List<Habitat>
Habitat
    toString(): String 
    getAllTrees(): List<Tree>
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
Habitat: getAllTrees():List<Tree>
Tree: toString(): String


4.5
4.5.1
-lista de vacinas 
-toString de cada vacina da lista
Hotel
    getAllVacinas():List<Vacine>
Vacine
    toString():String

4.5.2
Hotel
    getVacine(vacineID:String):Vacine
    getSpecies(speciesID:String):Species
    addVacine(id:String, name:String, species:List<Species>)
Vacine
    Vacine(id:String, name:String, species:List<Species>)

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
    getAllVacinesEvents():List<VacineEvents>
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
    getAllAnimals():List<Animal>
Animal
    toString():String

4.6.2
-obter animal
-obter vacineEvents do animal
-vacineEvent toString() para cada evento

Hotel
    getAnimal(animalID:String):Animal
Animal
    getAllVacineEvents():List<VacineEvent>
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
    getAllVacineEvents():List<VacineEvent>
VacineEvent
    toString():String

4.6.4
-obter lista de vacinas
-filtrar as que dano > 0
Hotel
    getBadVacineEvent():List<Vacine>


