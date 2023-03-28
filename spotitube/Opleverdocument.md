
# Spotitube applicatie

* Naam: Güven Ciddioglu
* Studentennummer: 1670577
* Datum: 24-03-2022
* Docent: Bart van der Wal

## Inhoudsopgave

1. [Inleiding](#inleiding)
2. [Package diagram](#package-diagram)
3. [Deployment diagram](#deployment-diagram)
4. [Ontwerpbeslissingen](#ontwerpbeslissingen)
5. [Patterns](#patterns)
5. [Technische keuzes](#technische-keuzes)
6. [Technische uitdagingen](#technische-uitdagingen)
7. [Testen](#testen)

## 1 Inleiding

Dit README is mijn opleverdocument voor het Spotitube opdracht. In dit document zal ik onder andere relevante UML diagrammen tonen en uitleggen. Ook zal ik mijn ontwerpbeslissingen en technische keuzes uitleggen. Tot slot zal ik ook de technische uitdagingen en testen bespreken. Zoals bijvoorbeeld de patterns die ik heb toegepast in mijn applicatie.

## 2 Package diagram

![Package diagram](https://user-images.githubusercontent.com/56688699/227621669-25f58527-0101-45c5-9f20-f674d31fbc1b.png)

Mijn Diagram is opgedeeld in 4 lagen. Deze lagen zijn:

* Presentatie laag
* Service laag
* Data laag
* Cross cutting concerns

### 2.1 Presentatie laag

Ik denk dat deze laag er best duidelijk op zit. De filters heb ik ook in de presentatie gestopt, omdat de presentatie laag deze filters gebruiken.

### 2.2 Service laag

In deze laag heb ik de service klasses staan en de interfaces voor de data mappers. De interfaces voor de data mappers heb ik hier geplaats, zodat ik voldoe aan de Seperated Interfaces pattern. Voor de rest heb ik het zo duidelijk mogelijk in de package diagram proberen weer te geven.

### 2.3 Data laag

In deze laag heb ik de DAO klasses staan en de data mappers. De data mappers heb ik hier geplaats, zodat ik voldoe aan de Seperated Interfaces pattern. Voor de rest heb ik op het laatste moment toch de exceptions hier gezet. Ik had ze eerst in de CCC staan, maar omdat ze eigenlijk het meest hier worden gebruikt, vond ik het verstandiger om het hier neer te zetten.

De exceptions package bevat nog 2 packages:

* mappers
* custom

De custom package bevat alle custom exceptions die ik heb gemaakt. De mappers package bevat alle exception mappers die deze exceptions omzetten oppakken.

#### controllers
In deze package heb ik de volgende klasses staan:


## 3 Deployment diagram

![Deployment diagram](https://user-images.githubusercontent.com/56688699/227632584-a1690b61-7cc7-47c1-823d-91223132f8ec.png)

In mijn deployment diagram heb ik de volgende componenten:

* Webserver en Angular
* Wildfly app die op mijn laptop runt
* Database die op localhost draait

De webserver en Angular componenten zijn de componenten die de gebruiker ziet. De applicatie draait op een wildfly server die op mijn laptop runt. En ik host de MySQL server op mijn eigen laptop.

## 4 Ontwerpbeslissingen/Patterns

Ik heb in mijn applicatie gebruikt gemaakt van meerdere patterns. Deze zouden ook onder ontwerpbeslissingen kunnen vallen, maar ik heb ze hier toch apart gezet voor de duidelijkheid. Ik heb in mijn applicatie gebruik gemaakt van de volgende patterns:

### 4.1 Singleton

#### Reden

Ik heb in mijn applicatie gebruik gemaakt van de singleton pattern. Deze pattern heb ik toegepast op 1 specifieke klasse, de TokenDB klasse. Deze klasse is verantwoordelijk voor het opslaan van de tokens in een lokale HashMap.

De reden dat ik hier voor de Singleton pattern heb gekozen, is omdat ik maar 1 instantie van deze klasse wil hebben. Ik wil namelijk niet dat er meerdere instanties van deze klasse zijn, omdat ik dan meerdere hashmaps zou hebben. Dit zou er misschien voor kunnen zorgen dat ik perongeluk twee lokale databases heb runnen, wat niet de bedoeling is.

#### Alternatief

Een alternatief voor de Singleton pattern is de DAO pattern. Met de DAO pattern zou ik de tokens kunnen opslaan in een database. Dit zou ook een goede oplossing zijn, maar ik heb gekozen voor de Singleton pattern omdat ik hiermee niet de database belast en geen queries hoef te schrijven en te versturen naar de database.

### 4.2 Layered Architecture pattern

#### Reden

Ik heb in mijn applicatie gebruik gemaakt van de Layered Architecture pattern. Hierdoor heb ik mijn applicatie opgedeeld in verschillende lagen. Deze lagen zijn:

* Data Access Layer
Deze laag is verantwoordelijk voor het opslaan en ophalen van data uit de database. Ik heb in deze laag onder andere de DAO klasses, de TokenDB klasse, de data mappers en de DatabaseConnection klasse. Dus eigenlijk alles wat met de database en tokens te maken heeft, heb ik in deze laag gezet.

* Business/Service Logic Layer
Deze laag is verantwoordelijk voor het verwerken van de data die uit de database komt. Ik heb in deze laag onder andere de Service klasses in staan. Ik heb hier dus ook mijn Service Layer pattern toegepast, maar daar kom ik later op terug. Deze laag is eigenlijk een tussenlaag tussen de Data Access Layer en de presentatie laag. Dit voorkomt dat de presentatie laag direct met de database communiceert en informatie heeft over hoe de database is opgebouwd. 

* Presentation Layer
Deze laag is verantwoordelijk voor het presenteren van de data die uit de database komt. Ik heb in deze laag onder andere de REST API/Controller klasses in staan. Deze laag is eigenlijk de laag die de gebruiker ziet. Deze laag communiceert met de service laag en de service laag communiceert weer met de data access laag. In deze laag heb ik dus mijn controllers staan die ervoor zorgen dat de inkomende requests worden verwerkt en dat de juiste data wordt teruggegeven.

#### Alternatief

Model
Dit is de laag die verantwoordelijk is voor de data van de applicatie. Ik zou hier dus de data mappers en de DAO klasses in kunnen zetten. Dit is een alternatief voor de Data Access Layer. 

View
Dit is de laag die verantwoordelijk is voor de presentatie van de data aan de gebruiker. Ik zou hier dus de REST API/Controller klasses in kunnen zetten. Dit is een alternatief voor de Presentation Layer.

Controller
Dit kun je zien als een tussenlaag tussen de Model en de View. Ik zou hier dus de Service klasses in kunnen zetten. Dit is een alternatief voor de Business/Service Logic Layer.

### 4.3 Service Layer pattern

#### Reden

Ik heb dit pattern toegepast in mijn service laag van mijn applicatie. Deze pattern moesten wij toepassen in de opdracht. Dit zorgt ervoor dat mijn controllers niet direct communiceert met mijn data laag en dus mijn DAO's. Hierdoor worden de functies/klasses ook niet al te groot en krijgt ieder klasse een duidelijke taak.

#### Alternatief

Een alternatief voor de Service Layer pattern is de Domain Layer pattern. De domain layer bevat een stuk meer logica dan de service layer. Eigenlijk de kern functionaliteit van de applicatie. De service layer pattern die ik heb toegepast zorgt er alleen voor dat er een tussenlaag komt tussen de controller laag en de data laag. Het bevat zelf niet veel functionaliteit.

### 4.4 Dependency Injection pattern

#### Reden
Ik heb Dependecy Injection overal toegepast waar ik het kon doen. Vooral in mijn DAO en Service klasses heb ik dit gedaan. Hierdoor is de koppeling tussen de klasses minder sterk. 

Een andere grote voordeel van Dependency Injection is dat ik mijn klasses kan testen. Ik kan namelijk een mock object meegeven aan mijn klasses. Hierdoor kan ik mijn klasses testen zonder dat ik een test database hoef aan te maken. Hier vertel ik later wat meer over.

#### Alternatief

Ik heb niet echt een alternatief voor deze pattern kunnen vinden. Ik heb hier en daar wel wat gelezen over de Factory pattern, maar ik weet niet of dit als een alternatief geld voor het injecteren van dependencies.

### 4.5 Supertype pattern

#### Reden

Ik heb deze pattern gebruikt bij een van mijn DAO klasses. Ik heb namelijk een super klasse aangemaakt waarbij de connectie wordt gemaakt met de database. Alle DAO klasses extended dit eerst, maar dit moest ik helaas later aanpassen. Doordat ik hierdoor niet die connectie kon mocken met Mockito, moest ik in sommige DAO klasses deze connectie injecteren. Volgens mij valt het dan nog steeds onder de Supertype pattern, omdat de connectie nog steeds een superklasse is, maar ik weet het niet zeker.

#### Alternatief

Een alternatief voor deze pattern is de Composition over Inheritance pattern. Hierbij wordt erven vermijd en wordt er gebruik gemaakt van composition. Dit is een alternatief voor de Supertype pattern, maar ik heb hier niet voor gekozen omdat ik het niet nodig vond.

### 4.6 Seperated Interface pattern

#### Reden

Ik heb deze pattern gebruikt bij mijn data mapper klasses. Dit moesten wij doen voor de opdracht. Ik heb dit geïmplementeerd door interfaces te maken voor mijn data mappers. De implementaties van deze mapper interfaces staan in de data laag, terwijl de interfaces in de service laag staan. Hierdoor vind de Seperated Interface pattern plaats.

#### Alternatief

Misschien is het mogelijk om de adapter pattern hier als alternatief te gebruiken. Hierbij wordt een interface gebruikt om de communicatie tussen twee verschillende systemen mogelijk te maken. Hierdoor zou de service laag de adapters gebruiken om verschillende data mappers te gebruiken voor de juiste DTO's.

### 4.7 Data Mapper pattern

#### Reden

Deze pattern heb ik gebruikt om data mappers te maken. Deze data mappers zorgen ervoor dat de data uit de data laag wordt omgezet naar DTO's. Deze mappers roep ik aan in de service om de inkomende data uit de data laag om te zetten naar bruikbare DTO's. Deze DTO's worden dan weer doorgestuurd naar de controller laag.

#### Alternatief

Na wat onderzoek kwam ik uit op de Active Record pattern. Deze pattern wordt als een alternatief gezien voor de data mapper pattern. De Active Record pattern zorgt ervoor dat het ophalen en opslaan van de data door het object zelf gedaan wordt, waarbij dit bij de data mapper pattern door een aparte klasse gedaan wordt.

### 4.8 Exception Mapper pattern

#### Reden

Ik heb de Exception Mapper pattern gebruikt om de verschillende exceptions die ik heb gemaakt, om te zetten naar een HTTP Response, met de juiste satus code. De exception die dan gethrowd wordt, wordt dan omgezet naar een HTTP Response. Deze HTTP Response wordt dan weer doorgestuurd naar de gebruiker, zonder dat ik dat zelf expliciet hoef te doen.

#### Alternatief

Een alternatief voor deze pattern is om de exception helemaal vanuit de DAO laag door te geven naar de service laag en dan in de controller de exception te catchen en een gepaste response terugsturen. Dit zou meer in principe van de Layered Architecture passen, maar ik heb dit niet gedaan omdat ik dat een beetje overkill vond. 

### 4.9 DTO pattern

#### Reden

Ik heb deze pattern gebruik om data objecten binnen de applicatie te gebruiken. I.p.v. random objecten met allerlei data erin, is het veel gestructureerder om DTO's te gebruiken.

#### Alternatief

Een alternatief voor deze pattern is om gewoon de data die je krijgt in meerdere objecten te stoppen, maar dit vind ik niet zo netjes. Ik vind het veel netter om DTO's te gebruiken.

### 4.10 DAO pattern

#### Reden

Ik heb deze pattern gebruikt om de data laag te maken. Ik heb DAO klasses gemaakt die de data uit de database halen en deze data omzetten naar DTO's. Deze DTO's worden dan weer doorgestuurd naar de service laag.

#### Alternatief

Als alternatief kun je ook gewoon normale klasses maken die de data uit de database halen en deze data omzetten naar DTO's. 

## 5 Exceptions

Ik heb in mijn applicatie voor alle DAO klasses die exceptions throwen, custom exceptions gemaakt. Wanneer deze exceptions worden gegooid worden ze door een specifieke exception mapper opgevangen en omgezet in een HTTP response. Ik vind dit achteraf heel veel werk voor weinig voordeel en hierover vertel ik later meer.

## 6. Testen

Ik heb in mijn applicatie gebruik gemaakt van JUnit en Mockito. Ik heb JUnit gebruikt om mijn klasses te testen en Mockito om mijn klasses te mocken. Ik had zonder de DAO's te testen rond de 50% line coverage, maar omdat het me gelukt is om de DAO klasses en de connectie te kunnen mocken, is het me gelukt om 81% line coverage te halen. Dit werd mogelijk toen ik de connectie begon te injecten inplaats van te extenden in de klasses. Ik heb hieronder een screenshot van de coverage van mijn DAO klasses.

## 7. Wat ik anders had gedaan

### 7.1 Exceptions

Ik was niet begonnen aan zo'n uitgebreide exception handling. Ik had eerst gebruik gemaakt van specifieke Java exceptions, zoals SQLException en NullPointerException. Maar soms waren deze exceptions niet specifiek genoeg, toen begon ik met het maken van custom exceptions. Ik had dit niet moeten doen, want ik heb hierdoor veel tijd verloren aan het maken van custom exceptions en het maken van de exception mappers. Ik had beter gebruik kunnen maken van de specifieke Java exceptions en deze kunnen catchen in de controller laag. Hierdoor had ik veel tijd kunnen besparen.

### 7.2 DAO's

Ik had eerst hier voor alle DAO's een connection class gemaakt die ze dan extended, maar dit was niet handig. Ik had beter gebruik kunnen maken van Dependency Injection en de connectie kunnen injecteren in de DAO's. Hierdoor had ik de connectie kunnen mocken en had ik de DAO's kunnen testen. Ik had dit eerder moeten doen, want hierdoor had ik veel tijd kunnen besparen.

## 8 Wat ik nog wil doen

Ik kon mijn database.properties bestand maar niet werkend krijgen, waardoor ik deze variabelen in de klasse zelf moest plaatsen. Ik was hier best lang mee bezig en ik heb het uiteindelijk niet voor elkaar gekregen. Ik wil dit nog wel proberen op te lossen, zodat ik mijn database.properties bestand kan gebruiken. Maar wegens tijd te kort is dit me niet gelukt. 

## 9. Conclusie

Deze opdracht heeft mijn kennis in de Java wereld uitgebreid. Ik was eigenlijk gewend om Javascript te coderen en haatte absoluut om met Java te coderen. Ik haat het nog steeds, maar nu een stuk minder. Door nieuwe technieken toe te passen, zoals de Layered Architecture pattern, ben ik erachter gekomen hoe primitief ik eigenlijk codeerde. Ik ben blij dat ik deze opdracht heb gemaakt, omdat ik veel geleerd heb.

## 10. Bronnen

-	HAN HBO ICT ARNHEM, (08-03-2022). Spotitube opdracht. Uitgever: HAN. Geraadpleegd op 24 maart 2023, van [https://han.onderwijsonline.nl/elearning/lesson/YyR2gQjy](https://han.onderwijsonline.nl/elearning/lesson/4ymkGKwq)
-	ISAS DEA beoordeling criteria. (2021). ISAS. Geraadpleegd op 24 maart 2023, van [https://isas.han.nl/Default.aspx?F=OWEDocumenten&Eigenaar=AIM&OWE=EPD](https://isas.han.nl/Default.aspx?F=Cursus&Cursus=DISENA20)
- CHATGPT, (2022). Pattern informatie. Uitgever: Open AI. Geraadpleegd op 24 maart 2023, van https://chat.openai.com/chat
