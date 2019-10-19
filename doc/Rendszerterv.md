Sudoku Rendszerterv
=============================

### 1. A rendszer célja
### 2. Projekt terv
##### 2.1 Sprintek
A fejlesztést kéthetes szakaszokra, sprintekre osztjuk. Minden sprint végén összegezzük az sprint feladatait, és megtervezzük a következő sprintet:
* Kitaláljuk milyen feladatokat teljesítünk
* Felosztjuk egymás között a feladatokat
* Beosztjuk, hogy melyik feladatot meddig illene megcsinálni 

A sprintek témája a fejlesztés során fog kialakulni. A sprintek időbeli beosztása:
*  \#1. Sprint: 2019. 10. 08. - 2019. 10. 21.
*  \#2. Sprint: 2019. 10. 22. - 2019. 11. 04.
*  \#3. Sprint: 2019. 10. 05. - 2019. 11. 18.
*  \#4. Sprint: 2019. 11. 19. - 2019. 12. 02.
*  \#5. Sprint: 2019. 12. 03. - 2019. 12. 16.

### 3. Üzleti folyamatok modellje
### 4. Követelmények
### 5. Funkcionális  terv
### 6. Fizikai környezet
##### 6.1 Választott programozási nyelv
A játékrendszer fejlesztése Java programozási nyelven for történni.
Pontosabban a JDK-8 használata lesz az alapértelmezett.
A Java programozási nyelv eszközei mellett, a grafikus felhasználói felület (*GUI - Graphical User Interface*) biztosítására a JavaFX szoftver platform lesz használva.
##### 6.2 Integrált fejlesztői környezet
A feljesztés során használt integrált fejlesztői környezet (*IDE - Integrated Developement Environment*) a JetBrains által fejlesztett IntelliJ IDEA lesz.
A sikeres fejlesztéshez elegendő az IntelliJ IDEA Community Edition, tehát a mindenki számára ingyenesen elérhető verzió. 
A kényelmes fejlesztéshez használható ugyen az IntelliJ IDEA Ultimate Edition, tehát az a verzió, amely minden eszközzel rendelkezik, de a licenszek korlátozott száma miatt 
ez nem minden fejlesztőnknek elérhető.
##### 6.3 Platform
A fejlesztés Linux operációs rendszeren fog történni, de a Java nyelv platformfüggetlensége miatt a rendszer cross-platform (*más szóval multi-platform*) módon fog működni, ami jelen esetben azt jelenti, hogy elérhető lesz Windows, Linux, és Mac operációs rendszeren.
##### 6.4 Verziókezelő rendszer
A verziókezeléshez Git lesz használva, pontosabban Github. A rendszer forrása a kiadás pillanatáig privát repository-ban fog helyet foglalni, csak a fejlesztők és a megrendelő hozzáférése garantált.
##### 6.5 Feladatkövető rendszer  
A (kötelezően) választott feladatkövető rendszer a Trello lesz. A verziókezelő rendszerrel ellentétben a kiadás pillanata után is privát marad a rendszer fejlesztése során felmerült feladatokat tartalmazó tábla. 

### 7. Absztrakt domain modell
### 8. Architekturális terv
### 9. Adatbázis terv
##### 9.1 Környezet
A backendet egy linux szerver alkotja, mely két komponenst futtat: egy MySQL adatbázist, és egy Apache HTTP szervert. Az adatbázis elérése HTTP GET és/vagy POST kéréseken keresztül történik, melyeket PHP dolgoz fel.

##### 9.2 Adatbázis szerkezet
Az adatbázis szerkezete a következőképpen fog kinézni:
![A sudoku program adatbázisának szerkezete](./resource/Sudoku_DB.svg)

Az itt megadott terv szükség szerint módosítható fejlesztés közben ha arra igény merül fel.

Ezzel a szerkezettel megvalósítható a felhasználók, játékstatisztikák, ranglétrák követése normalizált módon.

A táblák céljai:
- **User**: a felhasználó bejelentkezési adatait tárolja el.
- **Game**: a lejátszott játékok adatait tárolja el, hogy ebből felépíthetőek legyenek a ranglétrák és statisztikák.
- **FriendSystem**: "barátpárokat" tárol, amely játékosok egyszerűen párbajba léphetnek
- **GameMode**: megadja, hogy egy- vagy kétjátékos a játék.
- **GameType**: megadja a megoldandó sudokuk típusát.
- **Difficulty**: megadja a megoldandó sudokuk nehézségét.

##### 9.3 Adatbázis elérése
Az apache HTTP szerver elérést biztosít több PHP fájlhoz is, melyek API végpontokként szolgálnak a kliens számára. Minden végpont egy, az adatbázist módosító vagy lekérő feladatot lát el. Ilyen feladatok például a játékos regisztrálása, a bejelentkezés, a kijelentkezés, a befejezett játék beregisztrálása, stb. Azt, hogy pontosan milyen részfeladatokra érdemes osztani az API működését a könnyű és hatékony munka érdekében, csak fejlesztés közben fogjuk látni, így itt kimerítő lista közlése nem lehetséges.

A műveletek végeredményét a kliens számára JSON formátumban továbbítja a HTTP szerver. Ezek eljárások (tehát visszatérési érték nélküli műveletek) esetében a sikerességet tartalmazzák, függvények (visszatérési értékkel rendelkező műveletek) esetén pedig mind a sikerességet, mind pedig a kért adatokat tartalmazzák.

Amennyiben szükséges biztonságos továbbítás, POST kérés történik, egyébként GET kérés.

### 10. Implementációs terv
### 11. Tesztterv
### 12. Telepítési terv
### 13. Karbantartási  terv
