Sudoku Rendszerterv
=============================

### 1. A rendszer célja
### 2. Projekt terv
### 3. Üzleti folyamatok modellje
### 4. Követelmények
### 5. Funkcionális  terv
### 6. Fizikai környezet
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