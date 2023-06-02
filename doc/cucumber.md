# BDD tesztek készítése
## Teszt környezet
A teszteket Cucumber környezetbe készítettem el, mert amikor összeült a csapat, ez tetszett számunkra a legjobban.

## Teszt esetek készítése
Első sorban egy rövid utána járás után, hogy hogyan működik a Cucumber rendszer (hogy kell egy egyszerű teszt esetet implementálni) neki álltam a mate-bak által jónak gondolt teszt esetek implementálását, amit a [#8-as issueban](https://github.com/BME-MIT-IET/iet-hf-2023-grafadatbazis/issues/8) leírt.

## Virológus használ egy ágenst egy másik Virológuson - [PR](https://github.com/BME-MIT-IET/iet-hf-2023-grafadatbazis/pull/22)
Elsőnek ezt a két virológus közötti interakciót teszteltem, melyben 3 külön eset van.
- Virológus képes használni az ágenst egy másik Virológuson.
- Nem képes mert nincs nála elkészített Ágens.
- Nem képes mert más mezőn állnak.

## Virológus felvesz egy felszerelést - [Eredeti PR](https://github.com/BME-MIT-IET/iet-hf-2023-grafadatbazis/pull/22), [Bővített esetek PR](https://github.com/BME-MIT-IET/iet-hf-2023-grafadatbazis/pull/24)
Következőnek leteszteltem minden felszerelésre, hogy a virológus képes-e felvenni az adott felszerelést. Ugyan a funkcionalitása nincsen tesztelve az eszköznek csak az, hogy bekerült-e a virológus aktív tárgyai közé. Eleinte szimplán egy tárgy volt letesztelve ez később lett bővítve minden felszerelésre.
- Virológus fel tud venni **köpenyt**.
- Virológus fel tud venni **kesztyűt**.
- Virológus fel tud venni **baltát**.
- Virológus fel tud venni **zsákot**.
- Virológusnál nincs felszerelés így nem tudja felvenni (ez konkrétan egy kesztyű példánnyal volt tesztelve).
- Virológusnak már több mint 3 felszerelése van felvéve.

## Virológus interakcióba lép a mezővel - [PR](https://github.com/BME-MIT-IET/iet-hf-2023-grafadatbazis/pull/28)
Itt leteszteltem, hogy a Virológusunk, hogy lép interakcióba a különböző mezőkkel amin állhat. A játékban 4 fajta mező létezik:
- Sima mező melynek semmi különös tulajdonsága nincs.
- Laboratórium ami ágens párokat tárol.
- Raktár ami eltárol nyersanyagokat az ágensek készítéséhez.
- Óvóhely ami felszerelést tárol.

Ezekhez az alábbi teszteket készítettem:
- Virológus interakcióba lép egy **laboratóriummal**.
- Virológus interakcióba lép egy **raktárral**.
- Virológus interakcióba lép egy **óvóhellyel**.

Teszt esetek megírása utána előfordult egy probléma, hogy óvóhely esetén a felszerelés felvételénél ha baltát randomolt a rendszer, akkor az egyből az aktív felszerelések közé kerül, nem pedig a karakternél lévő felszerelésekhez, és ez fals teszt esetet eredményezett.

## Virológus megöl egy medvét - [PR](https://github.com/BME-MIT-IET/iet-hf-2023-grafadatbazis/pull/32)
Ebben a teszt esetben leteszteltem, hogy virológus képes-e megölni egy medvét, ez csak akkor lehetséges ha a virológusnál van balta, és a medvével egy mezőn áll.
Ehhez az alábbi eseteket készítettem:
- Virológus meg tudja ölni a medvét.
- Virológus és a medve külön mezőn vannak.
- Virológusnak nincs baltája.

## Medve lerombol egy raktár készletet - [PR](https://github.com/BME-MIT-IET/iet-hf-2023-grafadatbazis/pull/34)
Amikor egy medve egy raktár mezőre lép, automatikusan lerombolja annak a készletét. Az alábbi esetekben ez van letesztelve:
- A medve képes lerombolni a készletet.
- A medve nem képest lerombolni a készletet (mert nem egy raktár mezőn van).

## Medve megfertőz egy virológust - [PR](https://github.com/BME-MIT-IET/iet-hf-2023-grafadatbazis/pull/34)
Amikor egy medve egy olyan mezőre lép ahol virológus van akkor automatikusan megfertőzi a virológust.
Ehhez az alábbi eseteket készítettem:
- Medve képes megfertőzni a virológust.
- Medve és virológus nem ugyanazon a mezőn van.
- Virológusnak védelme van.

## Mockito - [PR](https://github.com/BME-MIT-IET/iet-hf-2023-grafadatbazis/pull/34)
Sajnos mivel a program a felépítése szerint bizonyos függvények használják a MainFrame.Instance változót, ezért mock viselkedéssel kellett ezeket ellátni, hogy a tesztek sikeresen futhassanak.

## Javítások - [Commit](https://github.com/BME-MIT-IET/iet-hf-2023-grafadatbazis/commit/666a08d18f1379b5acdccd97de28a715cce0ee1c)
A munka során elfordult, hogy kódban történő javítások miatt vissza kellett menni már megírt teszt esetekhez és kijavítani a hibákat. (pl. agent.CreateNew() -> agent.createNew())