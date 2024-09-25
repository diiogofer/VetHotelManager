25/09
-tem que ser ver se hotel foi alterado
4.2 Animals

4.2.1 Visualize all animals
want: ANIMAL | idAnimal | nameAnimal | idSpecies | healthHistory | idHabitat

-Precisamos de uma lista da animais
-e um toString() para o animal


4.2.2 Register new animal
Prompt.animalID()
Prompt.animalName()
Prompt.speciesID()
Prompt.speciesName()

-precisamos de um getAnimal(Animal id): -> para saber se o animal já existe
-precisamos de um addAnimal(id, nome, idEspecie, idHabitat) no hotel
-getSpecies(especie) -> para saber se a especie existe
-addSpecies() no Hotel
-Species (Construtor) recbee(id, Nome)
-na especie meter um addAnimal(animal)
-contrutor animal -> ( id, nome, Especie, Habitat)




4.2.3 Transfer animal to another habitat
Prompt.animalID()
Promp.habitatID()

-precisamos de getAnimal(animalId) no Hotel
-getHabitat(habitatId) no hotel
-no habitat -> removeAnimal(Animal) e addAnimal(Animal)



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
Tree: toAtring(): String




