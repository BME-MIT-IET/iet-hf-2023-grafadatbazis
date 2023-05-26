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

## 1. iteráció: Törlések

Legelsőnek az egyszerűen orvosolható hibáknak estem neki amiknek a legtöbbje szimplán megadott kulcsszavak vagy sorok törlésével megoldható. Ilyenek például a: "The import is never used.", "Remove this unused local variable.", "Unnecessary @SuppressWarnings", "Redundant Superinterface".  
A törlések elvégzése után a SonarLint által jelzett problémák száma 285-re csökkent.

## 2. iteráció: Átnevezések, áthelyezések

A második iterációban a következő legkönnyebben megoldható dolgokat kezdtem el javítani. Ezek egyszerű átnevezések, áthelyezések, átírások és kibővítések voltak. Az alábbi "problémák" reprezentálták többnyire a feladatokat amiket elvégeztem: "Rename this method name to match the regular expression", "Strings and Boxed types should be compared using "equals()".", "Use a StringBuilder instead.", "Use a primitive boolean expression here.", stb.
Az iteráció végén a SonarLint által jelzett problémák száma 113-ra csökkent. A legtöbbje ezeknek a függvények és változók átnevezése volt.