# Übung 1: OR Mapper (Einführung)

## Zielsetzung

Einsatz von JPA als OR-Mapper

*   Sammeln von Erfahrung in der Abbildung von Entities und Beziehungen
    zur DB.
*   Umsetzung von Lese- und Schreiboperationen auf dem persistenten
    Objektmodell.

## Vorbereitung

Für diese Übung verwenden Sie JPA mit EclipseLink basierend auf einer
PostgreSQL Datenbank. 

[^gl_Projektvorlage]: [/../builds/artifacts/master/download?job=OR-Mapper_JPA](/../builds/artifacts/master/download?job=OR-Mapper_JPA)
[^Übungsordner]: [https://skripte.hsr.ch/Informatik/Fachbereich/Datenbanksysteme_2/Dbs2/Uebungen/UebW01_OR-Mapper_JPA/Vorlagen/OR-Mapper_JPA.zip](https://skripte.hsr.ch/Informatik/Fachbereich/Datenbanksysteme_2/Dbs2/Uebungen/UebW01_OR-Mapper_JPA/Vorlagen/OR-Mapper_JPA.zip)

-   Die Projektvorlage[^gl_Projektvorlage] für die Aufgaben finden Sie auch auf dem Skripte Server im 
    Übungsordner[^Übungsordner].
    In dieser finden Sie das Eclipse-Projekt [`dbs2.jpa_exercise`](OR-Mapper_JPA/.project) mit Java Klassen, 
    jedoch noch ohne JPA-Annotationen.

-   Erzeugen Sie die neue Datenbank `bank` in PostgreSQL mittels des
    Skripts [`0_runAllScripts.sql`](OR-Mapper_JPA/src/db/0_runAllScripts.sql) aus dem Ordner [`src/db`](OR-Mapper_JPA/src/db) der
    Übungsvorlage.

Die Eclipse-Projektvorlage beinhaltet bereits die JPA-Libraries und die PostgreSQL Drivers für Java-8. Falls
Sie diese selber einrichten wollen oder Drivers für eine andere Java Version benötigen, können Sie wie folgt vorgehen:

[^EclipseLinkZip]: [https://www.eclipse.org/eclipselink/#download](https://www.eclipse.org/eclipselink/#download)

-   Beziehen Sie die neuste Version von EclipseLink Installer Zip[^EclipseLinkZip]. Im
    Paket finden Sie zwei JAR-Libraries, die Sie für das JPA Programm
    benötigen (Build Path / Class Path):

    -   [`eclipselink/jlib/eclipselink.jar`](OR-Mapper_JPA/lib/eclipselink.jar)

    -   [`eclipselink/jlib/jpa/javax.persistence_2.1.1.v201509150925.jar`](OR-Mapper_JPA/lib/javax.persistence_2.1.1.v201509150925.jar)

-   In der Vorlage zur Übung finden Sie auch das File [`persistence.xml`](OR-Mapper_JPA/src/META-INF/persistence.xml),
    das Ihnen als Vorlage zur Konfiguration Ihres JPA-Programms dienen
    kann. Sie müssen dieses noch für Ihre Umgebung entsprechend
    anpassen.

    -   Das File [`persistence.xml`](OR-Mapper_JPA/src/META-INF/persistence.xml) wird von JPA im Folder
        [`src/META-INF`](OR-Mapper_JPA/src/META-INF) im Eclipse-Projekt bzw. im Folder `META_INF` im JAR-File benötigt.

    -   Mit der Einstellung
        [`<property name="eclipselink.logging.level" value="ALL"/>`](OR-Mapper_JPA/src/META-INF/persistence.xml#L14) können
        Sie für die Übungen das Log Level von Eclipse Link konfigurieren,
        damit Sie zum Beispiel die generierten SQL Queries sehen.

[^JDBC-Driver]: [https://jdbc.postgresql.org/download.html](https://jdbc.postgresql.org/download.html)

-   Zwecks Anbindung zur Datenbank wird auch noch ein passender
    JDBC-Driver PostgreSQL[^JDBC-Driver] benötigt (Build-Path / Class Path).


Die Verzeichnisstruktur eines Java Eclipse Projektes für JPA sieht demnach ungefähr wie folgt aus:

```
	|-- lib
	|   |-- eclipselink.jar
	|   |-- javax.persistence_2.1.0.v201509150925.jar
	|   `-- postgresql-42.0.0.jar
	|-- src
	|   |-- ch
	|   |   `-- hsr
	|   |   ...
	|   |-- db
	|   |   |-- 0_runAllScripts.sql
	|   |   ...
	|   `-- META-INF
	|       `-- persistence.xml
```



**Hinweis**
:    Eine gute Dokumentation zu JPA und Annotationen finden Sie bei `objectdb.org`[^objectdb]. Sie können aber auch in der neuen EclipseLink Dokumentation[^EclipseLinkDokuNeu] oder **besser** in der alten EclipseLink Dokumentation[^EclipseLinkDokuAlt] nachschlagen.


## Ausgangslage

Ausgangslage ist die Datenbank `bank` mit folgendem Relationenmodell:

![Datenbank Relationenmodell][]


Ergänzen Sie die vorbereiteten Klassen sukzessive mit den entsprechenden
JPA Annotationen, sodass folgendes Objektmodell daraus resultiert:

![Persistentes Objektmodell][]


## Aufgaben

### Aufgabe 1: Entity Mapping


1.  Bilden Sie die Tabellen `BankCustomer`, `BankAccount`, `BankManager`
    und `Address` mit JPA als entsprechende Entity-Klassen **vorerst
    ohne Beziehungen** in ein Java-Programm ab. Setzen Sie zunächst die
    einzelnen Klassen mit den Basisattributen (Integer, Double, String,
    Date, etc.) um.

    **Tipp**
    :   Beginnen Sie mit einer einfachen Abbildung, z.B. `Address`

2.  Schreiben Sie ein Programm, welches alle Instanzen der
    Entity-Klassen von der DB lädt und deren Zustände in der Standard
    Output Konsole ausgibt.

### Aufgabe 2: Entity Relations


Erweitern Sie das Programm der Aufgabe [Entity Mapping][], so dass es
auch Beziehungen zwischen den Entities unterstützt.

1.  Implementieren Sie alle Beziehungen zwischen den Datenbank-Tabellen
    als entsprechende Beziehungen zwischen den Entities.

2.  Schreiben Sie ein Programm, welches alle Instanzen der
    `BankCustomer` direkt von der DB lädt und dann über den
    Objektgraph zu allen benachbarten Entities navigiert (`BankAccount`,
    `Address`, `BankManager`).

3.  Bei welchen der modellierten Beziehungen verwendet JPA Lazy Loading?
    Konfigurieren Sie diese Relationen auch für Eager Loading.

### Aufgabe 3: Entity Updates


Implementieren Sie eine Methode, die eine Banküberweisung zwischen zwei
Konten durchführt. Dies soll über JPA mittels Änderungen auf den
Entities realisiert werden.

```java
	transfer(fromAccountId, toAccountId, amount)
```

Bei nicht existenten Konten soll eine Exception geworfen werden.

### Aufgabe 4: Entity Deletes


Entwickeln Sie eine Funktionalität, um ein Bankkonto zu schliessen. Dazu
soll die entsprechende `BankAccount` Entity in JPA entfernt werden.

```java
	closeAccount(accountId)
```

Falls das Konto nicht existiert, soll eine Exception geworfen werden.

### Aufgabe 5: Entity Inserts


Programmieren Sie die Funktionalität zum Erfassen eines neuen
Bankkunden. Konkret soll via JPA eine neue persistente `BankCustomer`
Entity alloziert werden und in die DB gespeichert werden.

```java
	openAccount(name, birthDate)
```

#### Hinweis
*   Verwenden Sie die Annotation `@GeneratedValue` bei dem
    Id-Field/Property der Entity-Klasse. Dies bewirkt, dass automatisch
    ein neuer Primary Key für die Id der Entity beim Speichern generiert
    wird.

    ```java
	@Entity
	public class BankCustomer {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private long customerId;
		...
	}
    ```
    
    Denken Sie daran, dass Sie eventuell selber sicherstellen müssen, dass die entsprechende `SEQUENCE` den richtigen Startwert gesetzt hat.


#### Fakultativ
*   Beim Erfassen des neuen Kunden kann auch gleichzeitig ein neues
    zugehöriges BankAccount-Objekt mit Kontostand 0 angelegt und in der
    DB gespeichert werden.


## Musterlösung

Die Musterlösung zu den Übungen finden Sie im Branch Solutions[^gl_BranchSolutions] oder als zip-Datei[^gl_SolutionsZip].

[^gl_BranchSolutions]: [/../tree/Solutions/OR-Mapper_JPA](/../tree/Solutions/OR-Mapper_JPA)
[^gl_SolutionsZip]: [/../builds/artifacts/Solutions/download?job=OR-Mapper_JPA](/../builds/artifacts/Solutions/download?job=OR-Mapper_JPA)

[Datenbank Relationenmodell]: img/Visio1.jpg "Relationenmodell"
[Persistentes Objektmodell]: img/Visio2.jpg "Objektmodell"
[Entity Mapping]: #aufgabe-1-entity-mapping

[^objectdb]: [https://objectdb.org/java/jpa](http://objectdb.org/java/jpa)
[^EclipseLinkDokuNeu]: [https://wiki.eclipse.org/EclipseLink](http://wiki.eclipse.org/EclipseLink)
[^EclipseLinkDokuAlt]: [https://wiki.eclipse.org/EclipseLink/UserGuide/JPA](http://wiki.eclipse.org/EclipseLink/UserGuide/JPA)

