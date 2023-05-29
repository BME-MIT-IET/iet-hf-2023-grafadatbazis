## Maven integráció
# Projekt migrálása Mavenre
A projekt eredetileg nem Mavenben készült, az alkalmazás struktúrája nem 
is volt erre felkeszítve. Ennek megoldásához generáltam egy üres Maven projektet CLI-ben,
és ebbe bekötöttem az eredeti alkalmazást. 
A projekt forrásfájlai innentől kezdve az elvárt src/java/.. struktúrában vannak. 
A pom.xml kezdeti beállításait is elvégeztem. 

# Maven CI beállítása

A gyakorlaton is ismertetett módon létrehoztam a maven.yml fájlt, amiben beállítottam, hogy 
a 'main' branchre mergelésnél, illetve pull request létrehozásakor triggerelődjön a workflow.
Ezzel együtt protecteddé tettem a 'main' branchet, legalább 1 Approved review szükséges változtatások 
mergeléséhez, ezzel biztosítva, a trunk-based development módszert, miszerint csak a 'main' branchet fejlesztjük, minden egyes kis változtatást önálló branchen csinálunk, és azokat mergeljük.

# CI bővítése

A BDD tesztek felvételekor a pom.xml módosításával hozzáadtam a CI-hoz, hogy buildeléskor az egységtesztek mellett a későbbi Cucumber tesztek is fussanak. Ezen kívül létrehoztam külön CI jobokat
a könnyebb olvashatóság kedvéért a BDD teszteknek és egységteszteknek is.