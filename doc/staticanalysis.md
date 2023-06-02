## Statikus analízis SonarLint használatával

A statikus kódanalízist Visual Studio Code-ban SonarLint használatával kezdtem meg. Kezdetkor az alábbi állapot fogadott:   
- 408 Problems [(1)](SonarLintStart1.png) [(2)](SonarLintStart2.png)
- Előforduló gyakori típusok:
    - The import is never used.
    - Rename this local variable/method to match the regular expression
    - Add a nested comment explaining why this method is empty, throw an UnsupportedOperationException or complete the implementation.
    - Remove this unused local variable.
    - Refactor this method to reduce its Cognitive Complexity.
    - Either re-interrupt this method or rethrow the "InterruptedException" that was caught.
    - Merge this if statement with the enclosing one.
    - Save and re-use this "Random"
    - Strings and Boxed types should be compared using "equals()".
    - Use static access. (javax.swing)
    - Use a StringBuilder instead.
    - Különböző adattípusokkal kapcsolatos problémák.
- Összes probléma JSON formátumban [ITT](sonarlintproblems.json) található.
  
A házi keretében ezeknek a számát próbáljuk csökkenteni majd, előre viszont nem tudjuk meghatározni hogy ez a szám mennyire fogja megközelíteni a nullát a végén. A hibák javítását Issue-kban lehet majd nyomon követni, illetve később lehetséges hogy más eszközöket mint például a SonarCloud és a SpotBugs is bevetünk majd a hibák megtalálására és javítására.

---

## Statikus analízis SonarCloud használatával

A SonarLint használata mellett a SonarCloud is be lett üzemelve a projektre. A SonarLint-hez képest egy összegző felületet is kapunk ahol átnézhetjük a projekt jelenlegi állapotát. A SonarCloud-on a projekt jelenlegi állapota [ITT](https://sonarcloud.io/summary/overall?id=BME-MIT-IET_iet-hf-2023-grafadatbazis) érhető el.  
 A SonarCloud-on a projekt állapota a következő volt a SonarCloud hibák javítása előtt: [(1)](SonarCloudStart.png)

---

## 1. iteráció: Törlések

Legelsőnek az egyszerűen orvosolható hibáknak estem neki amiknek a legtöbbje szimplán megadott kulcsszavak vagy sorok törlésével megoldható. Ilyenek például a: "The import is never used.", "Remove this unused local variable.", "Unnecessary @SuppressWarnings", "Redundant Superinterface".  
A törlések elvégzése után a SonarLint által jelzett problémák száma 285-re csökkent.

[PR #13](https://github.com/BME-MIT-IET/iet-hf-2023-grafadatbazis/pull/13)

---

## 2. iteráció: Átnevezések, áthelyezések

A második iterációban a következő legkönnyebben megoldható dolgokat kezdtem el javítani. Ezek egyszerű átnevezések, áthelyezések, átírások és kibővítések voltak. Az alábbi "problémák" reprezentálták többnyire a feladatokat amiket elvégeztem: "Rename this method name to match the regular expression", "Strings and Boxed types should be compared using "equals()".", "Use a StringBuilder instead.", "Use a primitive boolean expression here.", stb.
Az iteráció végén a SonarLint által jelzett problémák száma 113-ra csökkent. A legtöbbje ezeknek a függvények és változók átnevezése volt.

[PR #14](https://github.com/BME-MIT-IET/iet-hf-2023-grafadatbazis/pull/14)

---

## 3. iteráció: SonarCloud Security Hotspots, Blocker-Critical-Major Issues

Harmadik iterációban a SonarCloud által jelzett Security Hotspot-okat és a Blocker-Critical-Major súlyosságú hibákat kezdtem el javítani. A legtöbb alapvetően bent felejtett Debug üzenetek voltak. A súlyos Issue-k többnyire üres metódusok, hiányzó default case-k switchnél, System.out használata, try-with-resources hiánya, static access hiánya, re-interrupt/rethrow hiánya, egybezárható if-ek, magas kognitív komplexitású függvények, NullPointerException lehetőségének fennállása.  

Ebben az iterációban fontos még kiemelni hogy a kognitív komplexitásra vonatkozó hibákkal nem foglalkoztam. Ezeknek a javítása valószínűleg egy következő iterációban fog megtörténni.

Számügyileg kezdetkor az alábbi volt az állapot:
- Security Hotspots: 10
- Issues:
    - Blocker: 6
    - Critical: 16
    - Major: 30
    - Minor: 38
    - Info: 2

A javítások után az állapot a következő lett: [(1)](SonarCloudIt3End.png)
- Security Hotspots: 0
- Issues:
    - Blocker: 0
    - Critical: 5 (Cognitive Complexity)
    - Major: 1 (Static Instance)
    - Minor: 36
    - Info: 2

SonarLint által jelzett problémák: 54

[PR #18](https://github.com/BME-MIT-IET/iet-hf-2023-grafadatbazis/pull/18)

---

## 4. iteráció: Utolsó SonarLint/Cloud iteráció

Az utolsó iterációban a maradék Issue-k lettek javítva amik nagyban típusok átírása, kognitív komplexitás csökkentése volt. Az utóbbit többnyire függvényekre való darabolással, illetve a függvényekben lévő if-ek egyszerűsítésével lehetett elérni. A javítások után és az Instance-l kapcsolatos hiba megjelölése után a SonarLint és a SonarCloud által jelzett problémák száma ténylegesen 0-ra csökkent.  

A SonarCloud által jelzett állapot: [(1)](SonarCloudIt4End.png)  

A javítások nem csak hatékonyságot növeltek hanem a kód sokkal olvashatóbb és szabályosabb/biztonságosabb lett, nem nyúlunk keresztül osztályokon csak úgy, nem másolunk feleslegesen listákat, ahol lehetséges, kódot egyszerűsítettünk/kiemeltünk.

[PR #25](https://github.com/BME-MIT-IET/iet-hf-2023-grafadatbazis/pull/25)

## Manuális kódelemzés és javítások

A kiinduló projekt nem rendelkezett semmiféle struktúrával az osztályok funkcióját, kapcsolatát illetően. Megpróbáltam nagyjából funckionalitás szerint mappákba rendezni őket, ez rengeteg egyéb, súlyos hibát elő is hozott - minden tagváltozó értéke közvetlenül volt írva és olvasva, ezt az azonos package-ben levőség package láthatóságon keresztül lehetővé tette. Ezeket ott, ahol a meglévő tesztek elkapták a problémát javítottam, de továbbra is rengeteg ilyen van a kódban, ezeknek a kijavítása mindenképpen prioritás lenne, ha maradna rá idő. 