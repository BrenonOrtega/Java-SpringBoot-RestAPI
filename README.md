# Desatio Técnico - Orange Talents ZUP
## Contexto:

Você está fazendo uma API REST que precisará controlar a aplicação de vacinas entre a população brasileira. O primeiro passo deve ser a construção de um cadastro de usuários, sendo obrigatórios dados como: nome, e-mail, CPF e data de nascimento, onde e-mail e CPF devem ser únicos. 

O segundo passo é criar um cadastro de aplicação de vacinas, sendo obrigatórios dados como: nome da vacina, e-mail do usuário e a data que foi realizada a vacina. 

Você deve construir apenas dois endpoints neste sistema, o cadastro do usuário e o cadastro da aplicação da vacina. Caso os cadastros estejam corretos, é necessário voltar o Status 201, caso hajam erros de preenchimento de dados, o Status deve ser 400.
 
## Seu desafio:
Dado que você fosse implementar esse sistema utilizando Java como linguagem e Spring + Hibernate como stacks de tecnologia fundamentais da aplicação: Escreva um post de blog explicando de maneira detalhada tudo que você faria para implementar esse código. 

No texto, queremos que você:
* Explique quais as tecnologias do mundo Spring você usaria;

*	Conte qual o papel das tecnologias escolhidas e quais benefícios elas trazem para a implementação do código;
*	Diga quais classes seriam criadas nesse processo e traga trechos autorais explicando a construção de cada classe do código;
*	Explique as etapas do processo de construção do seu código e como faria a implementação do sistema na Web;
*	Traga trechos de códigos autorais que justifiquem a implementação de cada tecnologia (não precisamos do código inteiro);
*	Os itens acima são obrigatórios, mas não se limite. Se quiser escrever mais, fique à vontade (não existe quantidade máxima de páginas ou caracteres).

## Pontos Chave

1. API REST - Controle de aplicação de vacinas entre a população brasileira.

* Cadastro de usuários:
Obrigatórios
* * Nome 
* * E-mail - Único
* * CPF - Único

* Cadastro de aplicação de vacina:
Obrigatórios
* * Nome da vacina
* * E-mail do usuário - Identifier
* * Data de Vacinação 

2. Regras

* Dois Endpoints:
O cadastro do usuário e o cadastro da aplicação da vacina. 
Caso os cadastros estejam corretos, é necessário voltar o Status 201, caso hajam erros de preenchimento de dados, o Status deve ser 400.


### Técnologias Empregadas 

#### Banco de Dados - PostgreSQL

Apesar da eficiência do MySQL ser a implementação padrão para aplicações em java devido a sua velocidade e por muitas vezes a mais recomendada.
O postgreSQL é totalmente gratuito para uso, sem nenhum tipo de restrição, sendo um banco de dados do tipo Object-relational database nos permite trabalhar com modelos customizados, facilitando a sua extensão em caso de necessidade.
Possuindo como base as fundações da orientação a objetos como polimorfismo, herança e encapsulamento e etc, faz as interações entre os objetos se torna uma tarefa trivial, enquanto em um banco relacional podemos encontrar as relações através de joins das tabelas, podemos utilizando os atributos dos objetos encontrar facilmente suas relações, algo que normalmente é feito puramente através dos ORM's.

#### Spring boot

A escolha do spring boot em vez do spring "puro" se assim podemos chamar para a criação desta aplicação, se deve aos seguintes fatos:

1. Facilidade de configuração

Com o auxilio do Spring Iniatilzr é possível configurar totalmente a aplicação de maneira rápida e intuitiva: selecionando se será um projeto  java, kotlin ou groovy, qual será o nosso gerenciador de dependencias Maven ou o graddle, já gerando os respectivos arquivos de configuração dependendo da escolha,versão do spring boot, configuração do metadata do projeto qual será o tipo de empacotamento do projeto (Jar ou War) e com qual versão do Java iremos trabalhar.

Isso abstrai muitos passos na inicialização do projeto e dentro da própria aplicação também abstrai diversos detalhes que podem tanto gerar erros no desenvolvimento da aplicação quanto atraso nas entregas.

2. Rápida configuração de dependências

Tendo em mente o tipo de aplicação que será construída, podemos facilmente já gerar nosso projeto com as dependências adicionadas.
As dependências utilizadas serão especificadas mais para frente.

3. Portabilidade da aplicação

O spring boot possuíndo internamente configurado o tomcat, abstraí a responsabilidade de hostear o nosso app Java em um servidor externo sendo totalmente conforme com as arquiteturas de microsserviços, facilitando a portabilidade da nossa aplicação em diversos ambientes como um container no docker, servidores linux ou windows, até mesmo em embarcados e tudo isso de maneira eficiente, devido ao tipo de empacotamento Jar que o spring boot utiliza de maneira eficiente.

4. Amplamente utilizado

Sendo um dos frameworks mais conhecidos, contando com uma consolidada e bem documentada construção e  uma grande comunidade de usuários, existem diversas soluções para diversos possíveis problemas encontrados durante o desenvolvimento, muitos desenvolvedores já conhecem ou trabalharam com o framework e portanto a manutenção do código (dado que este utilize de boas práticas), adição de pessoas a equipe e futuras melhorias e implementações 
serão facilitadas e executadas de maneira consistente e ágil.

#### Spring Data JPA - Hibernate e a persistencia dos dados.

O uso do spring Data JPA facilita a implementação do padrão de repositórios já garantindo um "safeguard" contra erros e más práticas comuns durante desenvolvimento e utilizando todos os padrões de inversão de controle que o spring oferece por padrão.
O Spring Data JPA por padrão implementa o Hibernate, mesmo podendo implementar outros frameworks como o eclipseLink.

#### SpringFox - Swagger - Documentação automática da API

Tão importante quanto desenvolver a API, documentá-la para a utilização de outros desenvolvedores e facilitar a integração com outros sistemas é uma prioridade em aplicações REST.

Podemos facilmente implementar a automatização da documentação da API utilizando Swagger 2 e sua configuração é feita quase automaticamente com o auxilio do Spring boot, bastando a implementação de uma classe de configuração única que após a indicação dos dominios da aplicação, o schema e roteamento da API poderá ser exposta através do Swagger UI.


#### ModelMapper

Ao enviar e receber dados em uma API Rest é muito importante a aplicação do conceito de Data Transfer Objects, expor toda a entidade ao mundo externo não é interessante por diversos motivos, sendo que a usabilidade da aplicação, segurança e privacidade estão incluidos neles.
O Model mapper entra justamente neste caso, seu objetivo é fazer mapeamentos automaticos de um objeto para outro, facilitando assim a transferencia de dados entre objetos e também diminuindo o acoplamento entre eles e facilitando a representação dos dados tanto na criação quanto na leitura dos dados do servidor.

#### Bean Validation

Ao fazer a persistencia dos dados no servidor é importante garantir que eles sigam um padrão e respeitem algumas regras.
Claro que sempre podemos desenvolver os métodos e garantias dentro da nossa aplicação porém é de boa prática e seguro utilizar métodos documentados e  que já foram "battle-tested"
portanto a utilização de Bean Validation implementa as regras necessárias nos dados que serão manipulados pela API, mantendo também o código mais legível e de facil manutenção e modificação.

### A Arquitetura.

Para garantir a implementação de uma arquitetura já consolidada e que também dê flexibilidade a aplicação, foi utilizado o padrão MVC (Model View Controller).

Segue a estrutura da aplicação:
<pre>
Java/
├─ Comando pandoc.txt .................................................. 
├─ DesafioOrangeTalents.docx ........................................... 
├─ DesafioOrangeTalents.md ............................................. 
├─ RegistroVacinacaodto.docx ........................................... 
├─ Zup - Teste Técnico.pdf ............................................. 
└─ orange_talents/ ..................................................... 
   ├─ HELP.md .......................................................... 
   ├─ mvnw ............................................................. 
   ├─ mvnw.cmd ......................................................... 
   ├─ orange_talents.iml ............................................... 
   ├─ pom.xml .......................................................... 
   ├─ README.md ........................................................ 
   ├─ src/ ............................................................. 
      ├─ main/ ......................................................... 
         ├─ java/ ...................................................... 
         │  └─ zup/ .................................................... 
         │     └─ orange_talents/ ...................................... 
         │        ├─ ProvaApplication.java ............................. 
         │        ├─ SpringFoxConfig.java .............................. 
         │        ├─ config/ ........................................... 
         │        │  ├─ DataIntegrityViolation/ ........................ 
         │        │  │  └─ DataIntegrityViolationExceptionHandler.java . 
         │        │  └─ validation/ .................................... 
         │        │     ├─ ValidationErrorDto.java ..................... 
         │        │     └─ ValidationErrorHandler.java ................. 
         │        ├─ controllers/ ...................................... 
         │        │  ├─ CadastroController.java ........................ 
         │        │  └─ RegistroVacinacaoController.java ............... 
         │        ├─ dtos/ ............................................. 
         │        │  ├─ Cadastro/ ...................................... 
         │        │  │  ├─ CadastroCreateDto.java ...................... 
         │        │  │  ├─ CadastroReadDto.java ........................ 
         │        │  │  ├─ CadastroUpdateDto.java ...................... 
         │        │  │  ├─ RegistroVacinacaoCadastroDto.java ........... 
         │        │  │  └─ VacinaCadastroDto.java ...................... 
         │        │  └─ RegistroVacinacao/ ............................. 
         │        │     ├─ CadastroRegistroVacinacaoDto.java ........... 
         │        │     ├─ RegistroVacinacaoCreateDto.java ............. 
         │        │     ├─ RegistroVacinacaoReadDto.java ............... 
         │        │     ├─ VacinaRegistroVacinacaoCreateDto.java ....... 
         │        │     └─ VacinaRegistroVacinacaoDto.java ............. 
         │        ├─ models/ ........................................... 
         │        │  ├─ CadastroModel.java ............................. 
         │        │  ├─ RegistroVacinacaoModel.java .................... 
         │        │  └─ VacinaModel.java ............................... 
         │        ├─ repository/ ....................................... 
         │        │  ├─ CadastroRepository.java ........................ 
         │        │  ├─ RegistroVacinacaoRepository.java ............... 
         │        │  └─ VacinaRepository.java .......................... 
         │        └─ services/ ......................................... 
         │           └─ Services.java .................................. 
         └─ resources/ ................................................. 
            ├─ application.properties .................................. 
            ├─ static/ ................................................. 
            ├─ templates/ .............................................. 
            └─ util/ ................................................... 

Foram excluiras as pastas de Target e Teste para tornar isto um pouco mais sucinto
</pre>

* Models 
É onde se concentram as entidades do nosso projeto, portanto eles são a representação completa dos dados de cadastros, vacinas e registros de vacinação.

* Controllers
São as classes responsaveis pelo controle de todo o sistema, recebem os dados externos, neste sistema foi implementado uma camada extra de "Services" para fazer toda a manipulação de dados dos repositórios, portanto apesar dos controllers terem conhecimento do repositório, não existe manipulação alguma de dados diretamente neles, eles agem apenas como mediadores das requisições entregando os dados da requisição para uma instancia da classe Services que manipula diretamente todos os dados.

* Dto's 
São os Data Transfer Objects, objetos criados com intuito de representar os dados da maneira que devem ser entregues a camada de view ou recebidos para processamento dentro do servidor, asseguram que nossos modelos não serão expostos e são divididos pelos dominios da nossa aplicação.

* Repository 

São os repositorios da aplicação, utilizando o Spring Data JPA e implementando a interface JPARepository, temos o acesso ao banco de dados, sendo cada um dos repositorios responsável por acessar um recurso da aplicação especificamente, porém devido aos relacionamentos é possível também consultar outros recursos através de cada um, os controllers também são responsáveis por gerar a resposta HTTP indicada para cada situação, como em casos de bad request ( status 400), not found (status 404), created (201),  ok (200) entre outros.

* config

Dentro de config estão localizados os exceptionHandlers da API, utilizando algumas anotações do Spring é possível direcionar os diversos tipos de exceções passíveis de ocorrerem e tratar cada uma da maneira que devem ser, no caso desta API foram tratados erros de validação, onde é devolvido os Códigos HTTP correspondentes no caso de dados que não seguem a conformidade das regras e Erros de Integridade dos dados, onde os dados violam as constraints da aplicação, como por exemplo repetição de CPF's ou Email de usuário.

* Services 

É onde ocorre a manipulação dos dados, a classe única services trabalha manipulando todos os dados, ela possuí conhecimento de todas as entidades e repositórios da aplicação, cada requisição do controller é direcionado para o método correspondente da instância vigente de services, onde o mesmo acessa o banco de dados e entrega apenas as representações correspondente ao controller em caso de requisições com verbo HTTP GET, em caso de PUT, POST ou DELETE, services executa as validações necessárias de acordo com a regra de negócio, verificando a existência ou não do recurso solicitado e fazendo a persistência ou remoção do mesmo. 

### Implementação

#### Entidades 
Inicialmente foram desenvolvidas as entidades e suas relações.

Um cadastro pode possuir muitos registros de vacinação e cada registro pode possuir apenas um cadastro, portanto foram utilizadas as notações @OneToMany e @ManyToOne, sendo que a classe "filha" deve ser a proprietaria desta relação, visto que ela é bidirecional, devido a isto o parametro mappedBy foi usado apontando para o atributo "pessoa" da Entidade RegistroVacinaçãoModel e o mesmo vale para a Entidade VacinaModel que foi apontada para o atributo Vacina, a propriedade "fetch=FetchType.LAZY é utilizada para evitar a sobrecarga do banco ao buscar pelas entidades, de maneira que essas propriedades só realmente serão buscadas ao explicitamente chamar por elas.

Um detalhe a se notar é que foi implementado um campo "isActive" para o caso de uma desativação de cadastro, um "soft delete" pode ser utilizado para manter os dados registrados em vez de explicitamente ser manifestado o desejo de total deleção conforme a recém criada Lei Geral de Proteção de Dados (LGPD), ou no caso de ser um sistema estatal, que possuí permissão para reter dados, evitar o apagamento total dos dados, bastando alterar o metodo de deleção que existe em services para alterar esta variavel para false e alterar a assinatura do método de query do repositório para não buscar os recursos marcados como "false".

> `CadastroModel.java`
> ```Java 
> //Imports Acima ~
> @Entity(name = "cadastros")
> @Table(schema="tenant_name")
> public class CadastroModel {
>
>    //#region Atributos
>    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
>    @Column(name="cadastro_id", updatable = false)
>    private Long id;
>
>    @CreationTimestamp
>    private Timestamp createdAt;
>
>    @Email
>    @Column(nullable = false, unique = true, length = 150)
>    private String email;
>
>    
>    @Column(nullable = false, unique = true, length=17)
>    private String cpf;
>
>    @OneToMany(mappedBy = "pessoa", fetch = FetchType.LAZY) @Column>(name="registro_vacina_id")
>    private List<RegistroVacinacaoModel> registroVacinacoes = new >ArrayList<RegistroVacinacaoModel>();
>
>    @Column(nullable = false)
>    private Date dataNascimento;
>
>    @Column(length = 30, nullable = false)
>    private String primeiroNome;
>
>    @Column(length = 50, nullable = false)
>    private String sobrenome;
>
>    @Column(nullable = false)
>    private Boolean isActive = true;
>
>    // Getters e Setters ~
> ```

> `RegistroVacinacaoModel.java`
> ```Java 
> //Imports Acima
> @Entity(name="registro_vacinacao")
> @Table(schema="tenant_name")
> public class RegistroVacinacaoModel {
> 
>     @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
>     @Column(name="registro_vacina_id", updatable = false)
>     private Long id;
> 
>     @Column(length = 150)
>     @Email 
>     private String emailVacinado;
> 
>     @Column(nullable = false) @CreationTimestamp
>     private Timestamp vacinatedAt;
> 
>     @ManyToOne(fetch = FetchType.LAZY)
>     private CadastroModel pessoa;    
> 
>     @ManyToOne
>         private VacinaModel vacina;
> 	
> 	// Getters e Setters Abaixo
> ```

>`VacinaModel.java`
> ```Java 
> //Imports Acima
> @Entity(name="vacinas")
> @Table(schema="tenant_name")
> public class VacinaModel {
> 
>     @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
>     @Column(name="vacina_id", updatable = false)
>     private Long id;
> 
>     @Column(nullable = false, length = 100)
>     private String nome;
> 
>     @Column(length = 60)
>     private String fabricante;
> 
>     @Column(length = 150, nullable = false)
>     private String doenca;
> 
>     @OneToMany(mappedBy = "vacina", fetch = FetchType.LAZY)
>     private List<RegistroVacinacaoModel> registroVacinacao = new ArrayList<RegistroVacinacaoModel>();
> 
> 	
> 	// Getters e Setters Abaixo
> ```

As notações utilizadas servem tanto para aplicar prontamente algumas constraints no banco de dados quanto para melhorar o nosso schema, fazendo a definição do length em cada um das columns necessárias podemos assegurar que não serão criadas colunas com varchar=255 onde não é necessário, isso diminui a memória necessária para alocação dentro do banco e com algumas validações como @email e @cpf podemos garantir que as Strings recebidas nos atributos seguirão sempre o padrão apropriado.

#### Banco de Dados e Propriedades da aplicação.

Após a escolha do banco, era necessário configurar a conexão da aplicação com o postgres, o driver, caminho, qual o banco que será usado, o schema é definido diretamente nas entidades portanto não precisa ser dito aqui, isso facilita para o uso de aplicações multitenant, definir os dialetos que o hibernate usará para gerar as queries da maneira mais eficiente e como será feito o acesso ao banco.
 
> `application.properties`
> ``` java 
> # Database Configuration
> spring.datasource.driverClassName=org.postgresql.Driver
> spring.datasource.url=jdbc:postgresql://localhost:5432/postgres
> spring.datasource.username=UsuárioBacanadoBancoDeDados
> spring.datasource.password=SenhaSecretaDoBancodeDados
> 
> #ServerConfiguration
> server.servlet.context-path=/api/v1
> 
> #Spring Data JPA hibernate configuration
> spring.jpa.hibernate.ddl-auto=update
> 
> ## Dialeto do spring JPA para o postgreSQL
> spring.jpa.properties.dialect=org.hibernate.dialect.postgreSQLDialect
> 
> ```

Também aqui foi definido o "Contexto" da aplicação, por ser uma primeira versão foi definido o caminho "api/v1" facilitando assim a manutenção e sustentação no caso de uma nova versão que possua muitas "Breaking Changes".

#### Testes

O ideal é gerar testes para cada uma das features do sistema, cada classe e método criado, porém não seria possível desenvolver a aplicação no tempo determinado devido a minha pouca experiência com desenvolvimento em Java, por isso ficarei devendo neste ponto.

#### Dtos

Sabendo da necessidade de entregar e receber os dados do cliente, estudando quais são os requisitos de cada entidade foram Criados os DTO's, eles foram pensados de maneira que o mapeamento destes e também dos seus relacionamentos feito pelo ModelMapper fosse fácil e automatico sem exigir maiores configurações, como mostrados a seguir.
A nomenclatura dos DTO's foi feita para facilitar a identificação tanto do dominio ao qual ele pertence quanto da sua utilização, apesar de tornar um pouco mais "verboso" o processo de escrita, acredito deixar explicito de onde vem e qual a utilidade do objeto desta maneira.

>`` Dominio - Cadastro ``
>
> `CadastroReadDto.java`
> 
> ```java
> public class CadastroReadDto {
>     //#region Atributos
>     public Long id;
>     public String cpf;
>     public String primeiroNome;
>     public String sobrenome;
>     public String email;
>     public Date dataNascimento;
>     public List<RegistroVacinacaoCadastroDto> registroVacinacoes = new ArrayList<>();
>     
>     //Getters and Setters
>  ```
>
>`RegistroVacinacaoCadastroDto.java`
> ```java
> public class RegistroVacinacaoCadastroDto {
>     private Long id;
>     private Date vacinatedAt;
>     private VacinaCadastroDto vacina;
> 
>     public RegistroVacinacaoCadastroDto() {}
> ```

>`RegistroVacinacaoCadastroDto.java`
> ```java
> public class VacinaCadastroDto {
>     private String nome;
>     private String doenca;
> 
>     public VacinaCadastroDto(){}
>     
>     //Getters and Setters
> ```
> `CadastroCreateDto.java`
> ```java 
> public class CadastroCreateDto {
>     //#region Declaração de Atributos
>     @NotNull @Email 
>     private String email;
> 
>     @NotNull @CPF
>     private String cpf;
> 
>     @NotNull @Past
>     private Date dataNascimento;
> 
>     @NotNull @NotBlank
>     private String primeiroNome;
> 
>     @NotNull @NotBlank
>     private String sobrenome;
>     //#endregion
> 
>     //#region Getters and Setters
> ```
>
>`CadastroUpdateDto.java`
>```java 
>//imports acima
>public class CadastroUpdateDto {
>    //#region Declaração de Atributos
>    @NotNull @Email 
>    private String email;
>
>    @Past @Nullable
>    private Date dataNascimento;
>
>    @NotNull
>    private String primeiroNome;
>
>    @NotNull 
>    private String sobrenome;
>    //#endregion
>
>    //#region Getters and Setters
>}
>```

Sendo apenas DTO's de leitura, eles não exigem o uso do Bean Validation, apenas precisam possuir os atributos com os respectivos nomes dos atributos da entidade para que o mapeamento pelo ModelMapper ocorra, diferente dos Dtos de Criação e atualização que já possuem as anotações de validação para em caso de preenchimento incorreto do formulário, a exceção seja lançada e então tratada pelo handler, informando ao usuário os campos que estão preenchidos incorretamente, isto será mostrado mais para frente.

>`` Dominio - RegistroVacinacao ``
>
> `RegistroVacinacaoReadDto.java`
> ```java
> //imports acima
> public class RegistroVacinacaoReadDto {
> 
>     //#region Atributos
>     private Timestamp vacinatedAt;
>     private VacinaRegistroVacinacaoDto vacina;
>     private CadastroRegistroVacinacaoDto pessoa;
>     //#endregion
>     //#Region Getters and Setters abaixo
> ```
>
> `CadastroRegistroVacinacaoDto.java`
> ```java
> //imports acima
> public class CadastroRegistroVacinacaoDto {
>
>    //#region Atributos
>    public String cpf;
>    public String primeiroNome;
>    public String sobrenome;
>    public String email;
>    public Date dataNascimento;
>    //#endregion
>
>
>    //#region Getters and Setters abaixo
> ```
>
> `VacinaRegistroVacinacaoDto.java`
> ```java
> //imports acima
> public class VacinaRegistroVacinacaoDto {
>    private String nome;
>    private String doenca;
>
>    //#region Getters and Setters abaixo
> ```

O dominio dos registros não exigiu um DTO para Atualização do recurso, visto que por ser simples e poderia ter as propriedades validadas dentro de Services, era ideal que se mantivesse apenas as informações necessárias, portanto passar o email, data da vacinação e nome da vacina se mantiveram com suas anotações.
Não foi criado um DTO principal para a entidade de vácinas devido a esta não possuir um endpoint próprio, porém ela foi pensada para aceitar isso numa necessidade futura (tanto que seu relacionamento com os Registros de Vacinação já foram até mapeados nos modelos).

#### Repository

Foram criadas as interfaces de repositório para cada entidade, herdando de JPARepository e utilizando os mecanismos de geração de queries automático do spring, foram definidas as assinaturas de método que serão utilizadas dentro dos controllers, conforme a seguir.
Para algumas implementações futuras seriam necessárias mais queries e a criação de queries customizadas com a anotação @Query("SELECT ..."), porém para esta aplicação simples não foi necessário nada além destas.

>`CadastroRepository.java`
>```java
>//imports acima
>public interface CadastroRepository extends JpaRepository<CadastroModel, Long> {
>    CadastroModel findByCpfIgnoreCase(String cpf);
>    CadastroModel findByEmailIgnoreCase(String cpf);
>    boolean existsCadastroByEmailIgnoreCase(String email);
>    boolean existsCadastroByCpfIgnoreCase(String cpf);
>
>}
>```

>`RegistroVacinacaoRepository.java`
>```java
>//imports acima
>public interface RegistroVacinacaoRepository extends JpaRepository<RegistroVacinacaoModel, Long>{
>}
>```

>`VacinaRepository.java`
>```java
>//imports acima
>public interface VacinaRepository extends JpaRepository<VacinaModel, Long> {
>    VacinaModel findByNomeIgnoreCase(String nome);
>    boolean existsByNomeIgnoreCase(String Nome);
>}
>```

### Controllers

utilizando as anotações que o spring disponibiliza, foram criadas as rotas de cada um dos controladores, sendo "/cadastros" para acessar e manipular os recursos relacionados ao cadastro de pessoas e "/registros" para manipular os recursos de registros de vacinação. Como dito anteriormente, visto que por definição só existiriam estes dois endpoints, apenas controllers para registros de vacinação e cadastros foram criados, mesmo existindo a entidade "Vacina" que será manipulada dentro do controller de registros.

### Cadastro Controller:
Dentro deste controller existem as operações básicas de CRUD (Create, Read Update e Delete), as anotações utilizadas asseguram a que cada verbo HTTP será roteado para o método correto e receberá o devido tratamento, seja para entregar os dados no caso do verbo GET, validar se as informações recebidas estão de acordo antes de serem persistidas no banco de Dados ou alteradas, no caso dos verbos POST ou Put.

O controller age apenas notificando a instância de services de qual ação ele deseja executar e services executa a devida ação, como dito anteriormente, isso permitiu assegurar que o controller não conheça a implementação das entidades, possuindo apenas conhecimento dos DTO's para trabalhar com as representações adequadas.

>`CadastroController.java`
>```java 
>//imports acima
>@RestController
>@RequestMapping("/cadastros")
>public class CadastroController {
>
>    private Services services;
>
>    private CadastroController(CadastroRepository cadastroRepository){
>        this.services = new Services(cadastroRepository);
>    }
>
>    @GetMapping
>    ResponseEntity<List<CadastroReadDto>> getCadastros(){
>        List<CadastroReadDto> listaCadastrosDto = services.getAllCadastros();
>        return listaCadastrosDto.isEmpty() 
>                ? ResponseEntity.noContent().build() 
>                : ResponseEntity.ok(listaCadastrosDto);
>    }
>
>    @GetMapping(path = "/{id}")
>    ResponseEntity<CadastroReadDto> getCadastroById(@PathVariable Long id) {
>        if ( services.hasCadastro(id)){
>            CadastroReadDto  cadastroDto = services.getCadastro(id);
>            return ResponseEntity.ok().body(cadastroDto);
>        }
>        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CadastroReadDto());
>    }
>
>    @PostMapping
>    public ResponseEntity<CadastroReadDto> createCadastro(
>                @RequestBody CadastroCreateDto novoCadastro,
>                UriComponentsBuilder uriBuilder ){
>        CadastroReadDto cadastroReadDto = services.createAndSaveCadastro(novoCadastro);
>        URI uri = uriBuilder.path("/cadastros/{id}")
>                .buildAndExpand(cadastroReadDto.getId()).toUri();
>        return ResponseEntity.created(uri).body( cadastroReadDto );
>    }
>
>    @PutMapping(path="/{email}")
>    public ResponseEntity<?> updateCadastro(@RequestBody @Valid CadastroUpdateDto cadastroUpdate,
>                                            @PathVariable @Email String email) {
>        if (services.hasCadastro(email)) {
>            CadastroReadDto updatedCadastro = services.updateCadastro(cadastroUpdate, email);
>            return ResponseEntity.ok(updatedCadastro);
>        }
>        return ResponseEntity.status(HttpStatus.NOT_FOUND)
>                    .contentType(MediaType.APPLICATION_JSON)
>                    .body(services.CADASTRO_NAO_ENCONTRADO);
>    }
>
>    @DeleteMapping(path="/{email}")
>    public ResponseEntity<?> deleteCadastro(@PathVariable @Email String email){
>        boolean cadastroDeleted = services.deleteCadastro(email);
>
>        return cadastroDeleted 
>            ? ResponseEntity.noContent().build() 
>            : ResponseEntity.status(HttpStatus.NOT_FOUND)
>                        .body(services.CADASTRO_NAO_ENCONTRADO); 
>    }
>}
>```

### Registro Controller 

O controlador para o recurso de registros é o que possuí a tarefa de direcionar todas as requisições relacionadas aos registros de vacinação para a sua instância de services, devolver as representações de leitura e entregar a services as representações de criação ou atualização. 

> `RegistroVacinacaoController.java`
> ```java
>  //imports acima
>@RestController 
>@RequestMapping(path = "/registros")
>public class RegistroVacinacaoController {
>
>    private Services services;
>
>    public RegistroVacinacaoController( RegistroVacinacaoRepository registroVacinacaoRepository,
>                                        VacinaRepository vacinaRepository,
>                                        CadastroRepository cadastroRepository){
>
>        this.services = new Services(vacinaRepository, registroVacinacaoRepository, cadastroRepository);
>    }
>
>    @GetMapping
>    ResponseEntity<List<RegistroVacinacaoReadDto>> getAllRegistros(){
>        List<RegistroVacinacaoReadDto> listaRegistrosDto = services.getAllRegistros();
>
>        return listaRegistrosDto.isEmpty() 
>                ? ResponseEntity.noContent().build() 
>                : ResponseEntity.ok(listaRegistrosDto); 
>    }
>
>    @GetMapping(path="/{id}")
>    ResponseEntity<RegistroVacinacaoReadDto> getRegistro(@PathVariable long id){
>        RegistroVacinacaoReadDto registro = services.getRegistroById(id);
>
>        return registro == null  
>                ? ResponseEntity.status(HttpStatus.NOT_FOUND).build()
>                : ResponseEntity.ok(registro); 
>    }
>
>    @PostMapping
>    ResponseEntity<?> novoRegistro(@RequestBody @Valid RegistroVacinacaoCreateDto novoRegistroCreateDto,
>                                                            UriComponentsBuilder uriBuilder){
>        String email = novoRegistroCreateDto.getEmailVacinado();
>        String nomeVacina = novoRegistroCreateDto.getNomeVacina();
>
>        if (services.hasCadastro(email) == false){
>            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(services.CADASTRO_NAO_ENCONTRADO);
>        }   
>
>        if (services.isCadastroVacinated(email, nomeVacina)){ 
>            return ResponseEntity.badRequest().body(services.CADASTRO_JA_VACINADO);
>        }
>        var createdRegistro =  services.CreateAndSaveRegistroVacinacao(novoRegistroCreateDto);
>        URI uri = uriBuilder.path("/registros/{id}").buildAndExpand(createdRegistro.getId()).toUri();
>        return ResponseEntity.created(uri).body(createdRegistro);
>    }
>
>    @PutMapping(path="/{id}")
>    ResponseEntity<RegistroVacinacaoReadDto> updateRegistro(@RequestBody RegistroVacinacaoCreateDto registroUpdate, 
>                                        @PathVariable long id) {
>        RegistroVacinacaoReadDto updatedRegistro =  services.updateRegistro(registroUpdate, id);
>        boolean isRegistroUpdated = updatedRegistro != null;                                  
>        return isRegistroUpdated 
>            ? ResponseEntity.ok(updatedRegistro) 
>            : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
>    }
>
>    @DeleteMapping(path="/{id}")
>    ResponseEntity<?> deleteCadastro(@PathVariable long id){
>        boolean isRegistroDeleted = services.deleteRegistro(id);
>        return isRegistroDeleted 
>        ? ResponseEntity.noContent().build()
>        : ResponseEntity.status(HttpStatus.NOT_FOUND).body(services.REGISTRO_NAO_ENCONTRADO);
>    }
>    
>}
> ``` 
 

### Services

Este é o verdadeiro centro de toda a aplicação, responsável por manipular as entidades, acessar o banco de dados e aplicar a regra de negócio nos dados, o ideal seria ter desenvolvido o mesmo utilizando partial classes, porém devido ao tempo que estava se esgotando o mesmo não foi implementado desta maneira.
O ponto principal desta classe é que ela recebe as instâncias de repositórios necessárias para cada um de seus controllers e a partir disso trabalha todos os dados, entregando as representações de leitura quando solicitada pelos controllers e recebendo as representações de criação, manipula as mesmas para criar representações da entidade que podem então ser persistidas no banco ou entregar a sinalização de falhas no caso de dados inválidos.
Este componente da aplicação permitiu o encapsulamento dos modelos da aplicação, garantindo assim que nenhum dos controllers manipule diretamente o banco de dados, ou possuam métodos que poderiam fazer isso.

#### Declaração da classe e construtores

Aqui são declarados os repositórios, mensagens de erro que serão disponibilizadas aos controladores e construtores que são utilizados por cada controller.

>`services.java`
>```java
>//Imports Acima 
>public class Services {
>    
>    
>    RegistroVacinacaoRepository registroVacinacaoRepository;
>    CadastroRepository cadastroRepository;
>    VacinaRepository vacinaRepository; 
>    static ModelMapper modelMapper = new ModelMapper();
>
>    public final String CADASTRO_JA_VACINADO = "{\"error\":\"Este usuário já foi vacinado.\"}";
>    public final String CADASTRO_NAO_ENCONTRADO = "{\"error\":\"Cadastro não Encontrado.\"}";
>    public final String REGISTRO_NAO_ENCONTRADO = "{\"error\":\"Registro não Encontrado\"}";
>    
>    //#region Construtores
>    public Services(VacinaRepository vacinaRepository, 
>        RegistroVacinacaoRepository registroVacinacaoRepository,
>		CadastroRepository cadastroRepository) {
>            this.cadastroRepository = cadastroRepository;
>            this.vacinaRepository = vacinaRepository;
>            this.registroVacinacaoRepository = registroVacinacaoRepository;
>	}
>
>    public Services (CadastroRepository cadastroRepository){
>        this.cadastroRepository = cadastroRepository;
>    }
>
>    //#endRegion
>```

#### Métodos booleanos

Nesta região da classe estão definidos os métodos boleanos que podem tanto ser utilizados pelos controllers para gerar alguma sinalização, ou internamente para validação da existência da entidade ou da existência de registro relacionada a algum cadastro.

>`services.java`
>```java
>    //#region Booleans
>	public boolean hasCadastro(String email){ 
>        return cadastroRepository.existsCadastroByEmailIgnoreCase(email);
>    }
>
>    public boolean hasCadastro(long id){
>        return cadastroRepository.existsById(id);
>    }
>
>    public boolean hasVacina(String nomeVacina){
>        return vacinaRepository.existsByNomeIgnoreCase(nomeVacina);
>    }
>
>    public boolean isCadastroVacinated(String emailCadastro, String nomeVacina){
>        boolean alreadyVacinated = false;
>        if (hasCadastro(emailCadastro) && hasVacina(nomeVacina)){
>            CadastroModel cadastro = cadastroRepository.findByEmailIgnoreCase(emailCadastro);
>            VacinaModel vacina = vacinaRepository.findByNomeIgnoreCase(nomeVacina);
>            for(int i = 0; i < cadastro.getRegistroVacinacoes().size(); i++){
>                if (cadastro.getRegistroVacinacoes().get(i).getVacina().getNome() == vacina.getNome()){
>                    alreadyVacinated = true;
>                    break;
>                }
>            }
>        }
>        return alreadyVacinated;
>    }
>
>    //#endregion
>
>```


#### Aquisição de instância única

Estes métodos são responsáveis por entregar uma representação de leitura da entidade única requisitada através dos parametros de Id, toda a lógica de mapeamentos das representações aninhadas também residem aqui, a utilização do modelMapper torna automática a geração das representações de leitura, sendo necessários apenas utilizar o metodo .map() com a instância de cada entidade e a classe para a qual deve ser mapeada como parametro para a geração de uma instância da representação que já pode ser entregue ao controller.

>`services.java`
>```java
>//#region Get Single instance
>    public CadastroReadDto getCadastro(long id){
>        CadastroModel cadastro = cadastroRepository.findById(id).orElse(null);
>        CadastroReadDto mappedCadastro =this.mapCadastroDto(cadastro);
>        return mappedCadastro;    
>    }
>
>    public RegistroVacinacaoReadDto getRegistroById(long id) {
>        RegistroVacinacaoModel registro = registroVacinacaoRepository.findById(id).orElse(null);
>        if (registro ==null) { return null; }
>
>        RegistroVacinacaoReadDto registroDto = this.mapRegistroVacinacaoDto(registro);
>        return registroDto;
>    }
>
>    //#endregion
>```

####Aquisição de listas de Entidades.

Aqui a mesma lógica da aquisição única é utilizada porém para uma lista de métodos, novamente a utilização do modelMapper permitiu a simplificação do código, visto que o mesmo abstraí a utilização dos collectors da biblioteca Stream do Java. 

>`services.java`
>```java
>    //#region get lists
>    public List<RegistroVacinacaoReadDto> getAllRegistros() {
>        List<RegistroVacinacaoReadDto> listaRegistrosDto = new ArrayList<>();
>        registroVacinacaoRepository.findAll().forEach(registro -> {
>            RegistroVacinacaoReadDto mappedRegistro = this.mapRegistroVacinacaoDto(registro);
>            listaRegistrosDto.add(mappedRegistro);
>        });
>        return listaRegistrosDto;
>    }
>
>    public List<CadastroReadDto> getAllCadastros(){
>        List<CadastroReadDto> listaCadastrosDto = new ArrayList<>();
>
>        cadastroRepository.findAll().forEach(cadastro -> {
>            CadastroReadDto mappedCadastro =this.mapCadastroDto(cadastro);
>            listaCadastrosDto.add(mappedCadastro);
>        });
>        return listaCadastrosDto;
>    }
>
>    //#endregion
>```

#### Funções Map

O mapeamento das propriedades aninhadas (.mapCadastroDto e .mapRegistroVacinacaoDto) foi abstraído para estas duas funções abaixo, evitando assim a repetição de código para o mapeamento das entidades, criando assim reusabilidade e diminuindo a quantidade de locais de mudança no caso de alteração nas propriedades das entidades, visto que o processo de mapeamento para as entidades era identico nos dois casos, alterando apenas que no caso das funções de listas, era necessário executar o mapeamento para cada instância que foi encontrada pelo repositório com a função forEach() .

>`services.java`
>```java
>//#region Entity Map Functions
>    private RegistroVacinacaoReadDto mapRegistroVacinacaoDto(RegistroVacinacaoModel registro){
>        RegistroVacinacaoReadDto mappedRegistro = modelMapper.map(registro, RegistroVacinacaoReadDto.class);
>        mappedRegistro.setPessoa( modelMapper.map(registro.getPessoa(), CadastroRegistroVacinacaoDto.class) );
>        mappedRegistro.setVacina( modelMapper.map(registro.getVacina(), VacinaRegistroVacinacaoDto.class) );
>        return mappedRegistro;
>    }
>
>    private CadastroReadDto mapCadastroDto(CadastroModel cadastro) {
>        CadastroReadDto mappedCadastro = modelMapper.map(cadastro, CadastroReadDto.class);
>            cadastro.getRegistroVacinacoes().forEach(registro -> {
>                RegistroVacinacaoCadastroDto mappedRegistro = modelMapper.map(registro, RegistroVacinacaoCadastroDto.class);
>                VacinaCadastroDto vacina = modelMapper.map(registro.getVacina(), VacinaCadastroDto.class);
>                mappedRegistro.setVacina(vacina);
>                mappedCadastro.registroVacinacoes.add(mappedRegistro);
>            });
>            return mappedCadastro;
>    }
>
>    //#endregion
>
>```

#### Update de recursos

Esta região da classe aplica as validações que as anotações do próprio DTO não lidam ou não podem lidar por serém contraditórias (como no caso do @nullable e @NotBlank, visto que o @NotBlank lança uma exceção para nulos). Com certeza é a parte mais ineficiente e que possui mais "code smells", porém infelizmente não consegui me aprofundar o suficiente na linguagem para encontrar uma maneira mais "clean" de aplicar as regras de negócio, porém para um MVP funciona, com certeza estarei refatorando e melhorando este ponto da aplicação num futuro próximo.

A partir da busca de uma entidade com o dado email ou iD, é verificado se existe um registro correspondente no banco de dados, existindo é então verificado quais são os parametros que serão alterados e entregue as variaveis no caso de mudança, caso não exista mudanças nas variaveis correspondentes do CadastroUpdateDto, então são adicionados os próprios parametros que existem atualmente e no fim desta procedure, a entidade é salva, como o spring data jpa só precisa da própria entidade gerenciada para executar a atualização, bastar usar os setters correspondentes e no final chamar o método .save() passando a entidade como parametro para executar a atualização, então é devolvido para o controller uma representação de leitura do recurso com o código correto.

>`services.java`
>```java
>    //#region updating entities
>
>    public CadastroReadDto updateCadastro(CadastroUpdateDto cadastroUpdate, @Email String email) {
>        CadastroModel cadastro = cadastroRepository.findByEmailIgnoreCase(email);
>
>        String novoEmail = cadastroUpdate.getEmail() == "" ? cadastro.getEmail() : cadastroUpdate.getEmail();
>        Date novaDataNascimento = cadastroUpdate.getDataNascimento() == null 
>                       ? cadastro.getDataNascimento() 
>                       : cadastroUpdate.>getDataNascimento();
>        String novoPrimeiroNome = cadastroUpdate.getPrimeiroNome() == "" 
>                       ? cadastro.getPrimeiroNome() 
>                       : cadastroUpdate.getPrimeiroNome();
>        String novoSobrenome = cadastroUpdate.getSobrenome() == "" 
>                       ? cadastro.getSobrenome() 
>                       : cadastroUpdate.getSobrenome();
>        
>        cadastro.setEmail(novoEmail);
>        cadastro.setDataNascimento(novaDataNascimento);
>        cadastro.setPrimeiroNome(novoPrimeiroNome);
>        cadastro.setSobrenome(novoSobrenome);
>        
>        cadastroRepository.save(cadastro);
>        CadastroReadDto updatedCadastro = modelMapper.map(cadastro, CadastroReadDto.class);
>        return updatedCadastro;
>    }
>
>    public RegistroVacinacaoReadDto updateRegistro(@Valid RegistroVacinacaoCreateDto registroUpdate, long id) {
>        RegistroVacinacaoModel registro = registroVacinacaoRepository.findById(id).orElse(null);
>        if(registro == null) { return modelMapper.map(registro, RegistroVacinacaoReadDto.class); }
>
>        String novoNomeVacina = registroUpdate.getNomeVacina();
>        String novoEmailVacinado = registroUpdate.getEmailVacinado();
>        Timestamp novaDataVacinacao = new Timestamp(registroUpdate.getVacinatedAt().getTime());
>
>        CadastroModel pessoaVacinadaUpdate = this.hasCadastro(novoEmailVacinado) 
>            ? cadastroRepository.findByEmailIgnoreCase(novoEmailVacinado)
>            : null;
>
>        VacinaModel vacinaUpdate = this.hasVacina(novoNomeVacina) 
>            ? vacinaRepository.findByNomeIgnoreCase(novoNomeVacina)
>            : null; 
>        if (vacinaUpdate == null) {
>            vacinaUpdate = this.CreateAndSaveVacina(novoNomeVacina);
>        } 
>        if (pessoaVacinadaUpdate == null ){ return null; }
>
>        registro.setPessoa(pessoaVacinadaUpdate);
>        registro.setVacina(vacinaUpdate);
>        registro.setVacinatedAt(novaDataVacinacao);
>        registroVacinacaoRepository.save(registro);
>
>        RegistroVacinacaoReadDto registroUpdatedReadDto = modelMapper.map(registro, RegistroVacinacaoReadDto.class); 
>        return registroUpdatedReadDto;
>    }
>
>    //#endregion
>
>```

#### Deleção de recursos

São os métodos mais simples, apenas verificam se existe o recurso buscado usando o email (que é um parametro unico para o cadastro) ou o id no caso dos registros e então existindo o recurso buscado executa a deleção os métodos retornam true ou false para sinalizar ao controller que tipo de mensagem deve ser entregue ao usuário, sendo "no content" no caso de um delete bem sucedido, ou 404 "not found" no caso do recurso buscado não existir.

`services.java`
```java
public boolean deleteCadastro(String email) {
        if (this.hasCadastro(email)){
            CadastroModel cadastro = cadastroRepository.findByEmailIgnoreCase(email);
            cadastroRepository.delete(cadastro);
            return true;  
        }
        return false;
    }

    public boolean deleteRegistro(long id){
        RegistroVacinacaoModel registro = registroVacinacaoRepository.findById(id).orElse(null);

        if (registro == null) {
            return false;
        } else {
            registroVacinacaoRepository.delete(registro);
            return true;
        }
    }    
```

### Config

Dentro de config existem 2 handlers criados para lidar com as exceções de validação e de violação de constraints do banco.

O handler de violação de integridade dos dados captura todas as exceções do tipo "DataIntegrityViolationException" gerada dentro de uma chamada das classes anotadas com @RestController e entrega o código definido na anotação @ResponseStatus, portanto a construção de ambos os handlers é bem parecida.

>`DataIntegrityViolationHandler.java`
>```java
>@RestControllerAdvice
>public class DataIntegrityViolationExceptionHandler {
>
>    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
>    @ExceptionHandler( DataIntegrityViolationException.class )
>    public String handle(DataIntegrityViolationException e){
>        return e.getMostSpecificCause().getMessage();
>    }
>    
>}
>```


>`Validation.java`
>```java
>@RestControllerAdvice
>public class ValidationErrorHandler {
>    
>    private MessageSource _messageSource;
>    
>    public ValidationErrorHandler(MessageSource messageSource) {
>        this._messageSource = messageSource;
>    }
>
>    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
>    @ExceptionHandler(MethodArgumentNotValidException.class)
>    public List<ValidationErrorDto> handle(MethodArgumentNotValidException e){ 
>        List<FieldError>fieldErrors = e.getBindingResult().getFieldErrors();
>        List<ValidationErrorDto> errorList = new ArrayList<>();
>
>        fieldErrors.forEach( error -> {
>            String message = _messageSource.getMessage(error, LocaleContextHolder.getLocale());
>            ValidationErrorDto exception = new ValidationErrorDto(error.getField(), message);
>            errorList.add(exception);
>        });
>
>        return errorList;
>    }
>}
>```
>
>`ValidationErrorDto.java`
>```
>public class ValidationErrorDto {
>
>    private String campo;
>    private String erro;
>
>    public ValidationErrorDto(String campo, String erro) {
>        this.campo = campo;
>        this.erro = erro;
>    }
>    
>    public String getCampo() { return campo; }
>    public String getErro() { return erro; }
>
>}
>```

#### SpringFox Configuration

Com a criação de um Bean anotado com @configuration o spring boot já configura por padrão e busca as rotas definidas dentro do "basePackage" declarado dentro do SpringFoxConfig, isso é o suficiente para a documentação automática da API utilizando o Swagger.

>`SpringFoxConfig.java`
>```java
>@Configuration
>public class SpringFoxConfig {                                    
>    @Bean
>    public Docket api() { 
>        return new Docket(DocumentationType.SWAGGER_2)  
>          .select()                                  
>          .apis(RequestHandlerSelectors.basePackage("zup.orange_talents.controllers"))   
>          .paths(PathSelectors.any())                          
>          .build();                                           
>    }
>}
>```

### Segurança da aplicação

O spring Security pode ser facilmente implementado e definido os níveis de permissão de cada usuário podemos restringir a leitura e escrita de dados, porém dada as diretrizes do projeto, isto não será implementado.
