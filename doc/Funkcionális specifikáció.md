Sudoku Funkcionális specifikáció
==========================================

### 1. Jelenlegi helyzet

### 2. Vágyálom rendszer
##### 2.1 Főmenü
A játék elindításakor a felhasználó a főmenüt látja először. Innen gombok segítségével almenükbe navigálhat tovább, melyek a következőek:
- Új játék: Ez a menüpont egy olyan oldalra visz tovább, ahol megadhatjuk a játékunk beállításait, majd elkezdhetjük azt.
- Statisztikák/Ranglétra: A saját statisztikáinkat és a többi játékossal összemért eredményeinket láthatjuk.
- Beállítások: A program konfigurálására alkalmas oldalra visz.
- Kilépés: Bezárja a programot.

##### 2.2 Új játék menüpont
Ebben a menüpontban a játékos kiválaszthatja, milyen játékot szeretne indítani, majd továbbnavigálhat a játék oldalra. Alternatívaként visszamehet a főmenübe, ha nem szeretne elindítani egy játékot.

Az új játék tulajdonságait három, egymástól függetlenül állítható listából lehet kiválasztani:
- Játékmód: kiválasztható, hogy milyen típusú sudoku játék induljon.
- Nehézség: megadja, hogy mekkora kihívást jelentő pályát generáljon a játék.
- Játékosszám: eldönthető, hogy egyedül, vagy párharcban szeretnénk játszani.

##### 2.3 Játék
A játék során a felhasználó kap egy sudoku táblát, melyet a játék szabályainak megfelelően ki kell töltenie. Minden kitöltött elemnél a program opcionálisan ellenőrzi, hogy a cellába a megfelelő szám került-e, jelezve hiba esetén.

A kitöltés módszere:
- Navigálás a cellák között a nyílbillentyűk (<kbd>&uarr;</kbd>, <kbd>&larr;</kbd>, <kbd>&darr;</kbd>, <kbd>&rarr;</kbd>), vagy a <kbd>W</kbd>, <kbd>A</kbd>, <kbd>S</kbd>, <kbd>D</kbd> billentyűk segítségével. Amennyiben az adott irányba már nincs több cella, a kiválasztás a tábla másik felére ugrik.
- Számok bevitele az <kbd>1</kbd>-<kbd>9</kbd> billentyűk vagy numpad <kbd>1</kbd>-<kbd>9</kbd> billentyűk segítségével.
- A kitöltés és a javaslat mód közötti váltás az <kbd>S</kbd> (mint "suggestion") billentyűvel, vagy az erre való gombbal.

A játék befejezésekor eredményünket regisztrálhatjuk a ranglétrára, és új játékot kezdhetünk azonos, vagy más beállításokkal, esetleg visszatérhetünk a főmenübe.

##### 2.4 Statisztikák és ranglétra

Ebben a menüpontban megtekinthetjük, hogy profilunk milyen játékadatokkal rendelkezik, és hogyan teljesít a többi játékossal szemben.

Első kategóriába olyan adatok tartoznak, mint a játékban töltött összidő, a lejátszott és a sikeresen kitöltött játékok száma, a megnyert párbajok száma, valamint ugyanezen adatok sudoku típusonként lebontva.

A második kategória egyszerű listákból áll, ahol láthatjuk, melyik játékos hanyadik helyen helyezkedik el a listában. Időtartamonként létezik egy-egy ilyen lista, például a napi, heti, havi, éves, és örök ranglétra. A ranglétrában időtartam mellett szűrhetünk sudoku típusra, vagy játékmódra is.

### 3. Jelenlegi üzleti folyamatok modellje

### 4. Igényelt üzleti folyamatok modellje