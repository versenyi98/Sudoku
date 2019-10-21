Sudoku Rendszerterv
=============================

### 1. A rendszer célja
##### 1.1 Jelenlegi célok
A rendszer célja jelenleg az, hogy egy platformot tudjunk biztosítani a Sudoku játék rajongóinak.
A platformon eleinte elérhető lenne a "kényelmes", (casual) illetve "versengő" (competitive).
A rendszerben Normál Sudoku, Killer Sudoku, X Sudoku, Samurai Sudoku, Szabálytalan Sudoku, illetve személyre
szabott Sudoku játékmód lesz elérhető első lépésként, kezdő, normál és nehéz nehézségben.   
A játék során szerzett ismerettségeket fenntartását, illetve a barátainkkal való kapcsolattartást játékon belül a barátlista funkció 
támogatja.
##### 1.2 Jövőbeli célok
A platform hosszútávú célja Sudoku-event-ek rendezése, országos-, regionális-, világbajnokságok rendezése,
non-profit adománygyűjtő események szervezése. Több játékmód, illetve a "Lehetetlen" nehézségi mód implementálása
is elkerülhetetlen a nagyobb eseményeken való megfelelő nehézségű feladat biztosításához.
Csetrendszer implementálása, így a barátlista funkció több értelmet nyer.
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
##### 2.2 Feladatkövető rendszer használata
Trello-n minden egyes nagyobb feladathoz (továbbiakban issue) létrehozunk egy Epicet, amelyet a megfelelő "Epics" listában tárolunk.
A feladatot részekre bontjuk és mindegyik részfeladatnak külön issue-számot adunk, és kártyát hozunk létre a "Backlog" listába. Minden részfeladathoz beállítjuk a határidőt,
illetve a megbízott fejlesztőt. Ha a feladatot a fejlesztő elkezdi csinálni, akkor a "Backlog" listából átkerül a feladat kártyája az "In progress" listába.
Ha a fejlesztő kész van az adott issue-val, és egy másik fejlesztő jóváhagyására vár, akkor az "In testing" listába kerül a kártya.
Ezután, ha a jóváhagyás megtörtént, akkor a kártya a "Done" listába kerül át, és "kész"-nek jelöli a fejlesztő.
Ha egy Epic minden részfeladata készen van, akkor az Epic-et a "Complete Epic" funkcióval le kell zárni-
Minden Sprinthez tartozik egy kártya, aminek nincs issue-száma. A sprint kártyákhoz csatolva vannak a sprintben aktuális Epic kártyák, és
az Epic nélküli issue-k is.
#### 2.3 Verziókezelő rendszer használata
Minden Trello-s issue-hoz, amely a forrás változtatását kívánja, létrehoz a kijelölt fejlesztő egy branch-ot, "S*n*" névvel, ahol az *n* az issue számát jelöli.
Minden commit-ot a branch nevével kell kezdeni, jelen időben (Present Simple). A commit címének végén nincs pont. Angol nyelven kell commit-ot írni, a fejezet címek és fájl nevek 
magyarról angolra fordítása nem szükséges, de megengedett. Ha az issue kész, akkor egy Pull Request-et kell létrehozni, ahol egy, az issue 
megoldásával megbízott fejlesztőn kívüli fejlesztőnek jóvá kell hagyni a módosításokat. A módosítások jóváhagyása a master branch-ra való merge-eléssel történik.
#### 2.4 Fejlesztők
A játékrendszer fejlesztésére négy fejlesztő áll rendelkezésre. 
A fejlesztők fő feladatkörei jól elválaszthatóak: 
1. Felhasználói felülettel kapcsolatos feladatok megoldása
2. Mesterséges intelligencával kapcsolatos feladatok megoldása:
    1. Megoldott Sudoku-t visszalépéssel generáló algoritmus
    2. Megoldandó Sudoku-t visszalépéssel generáló algoritmus:   
    Az algoritmus egy megoldott Sudoku-ból hagy el számokat, úgy, 
    hogy a hátralevő mezők alapján megoldható legyen a Sudoku, és
    egyértelmű legyen, tehát csak egy megoldás létezzen.
3. Adatbázissal való kommunikáció kiépítése
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
##### 10.1 Sudoku generálás
A Sudoku generálás feladatát egy mesterséges intelligenciás algoritmus, pontosabban
egy visszalépéses algoritmus implementálása fogja megoldani.   
Egy megoldott hagyományos Sudoku játék generálásának az állapottérreprezentációja:
* Állapotok halmaza:
    * Minden állapothoz tartozik:
        * egy 9 x 9-es mátrix, ami a jelenlegi Sudoku táblát reprezentálja
        * egy (x, y) pozíciót, amely táblában a legelső üres cellát jelöli
    * Kényszerfeltételek
        * a jelenlegi (x, y) pozíció előtti (tehát a fentebbi sorokban, 
        illetve az adott *y* sorban levő, de *x* pozíciót megelőző) cellák 
        mind nem egyenlők nullával
        * a jelenleg (x, y) pozícióban, és utána levő cellák értéke mind 
        egyenlő nullával
* Kezdőállapot: (x, y) = (0, 0) és minden cella értéke egyenlő nullával
* Célállapot:   (x, y) = (9, 0) és minden i = [0, 8] és j = [0, 8] tartományon belül
    igaz, hogy a mátrix *(i, j)* indexű eleme nem nulla.
* Operátor: Az (x, y) indexre *n* értékű szám elhelyezése
* Operátor alkalmazási előfeltételek:
    * 1 <= n <= 9
    * x < 9
* Operátor hatása
    * a mátrix (x, y) indexű eleme *n* értékű lesz
    * y = egészrész((x + 1) / 9)
    * x = (x + 1) (mod 9)

##### 10.2 Teljes Sudoku-ból hiányos Sudoku generálása
###### Lépések *n* darab érték elhagyásához. visszalépéses keresővel:
1. Az összes még nem elhagyott cella véletlenszerű sorbaállítása
2. Ha a sorban nincs több cella, akkor visszalépés.
2. Ellenőrizni, ha a sorrendben első cellát elhagyjuk, akkor egyértelműen
fog létezni a megoldás vagy sem?
4. Ha nem, akkor a cella kivétele a sor elejéről és a 2. lépés elvégzése újra.
5. Ha igen, akkor a cella elhagyása a mátrixból.
6. Ellenőrizni, hogy elhagytunk-e *n* darab cellát?
7. Ha nem, akkor új állapot létrehozása, és az 1. lépés elvégzése újra.
8. Ha igen, akkor végeztünk
### 11. Tesztterv
### 12. Telepítési terv
### 13. Karbantartási  terv
