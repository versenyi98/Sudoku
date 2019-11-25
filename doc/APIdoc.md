Sudoku API specifikáció
=======================

### 1. Általános
- minden API végpont egy JSON objektum formájában adja meg a kimenetét
  - a sikerességet a `success` kulcsú boolean tartalmazza
  - sikertelenség esetén a `errorCode` (szám) és `errorMessage` (szöveg) tartalmazza a részleteket
- hiba esetén minden API végpont saját hibakódolással és -üzenettel, és HTTP status code-al is jelzi, mi történt
- saját hibakódolás felépítése
  - kód 1: adatbázishiba
  - kód 2: autentikáció hiánya
  - kód 3: hibás autentikáció
  - kód 4: hiányos bemenet (ha az nem az autentikációhoz kapcsolódik)

### 2. Felhasználókezelés
##### 2.1 Regisztráció
- Elérés: a `sudoku/register.php` útvonalon `POST` kéréssel
- Bemenet:
  - `username` kulcs: a regisztrálandó felhasználó neve
  - `password` elem: a regisztrálandó felhasználó jelszava
- Kimenet: nem tartalmaz extra információt a sikerességen kívül.
  
##### 2.2 Bejelentkezés
- Elérés: a `sudoku/login.php` útvonalon `POST` kéréssel
- Bemenet:
  - `username` kulcs: felhaszálónév
  - `password` kulcs: jelszó
- Kimenet:
  - a `token` kulcs tartalmazza a munkamenethez tartozó véletlenszerű 32 karakteres stringet, melyet innentől a felhasználó/munkamenet azonosítására lehet használni kijelentkezésig (amikor a token megszűnik), vagy kijelentkezés nélküli újabb bejelentkezésig (amikor a token érvénytelenné válik)

##### 2.3 Kijelentkezés
- Elérés: a `sudoku/logout.php` útvonalon `POST` kéréssel
- Bemenet:
  - `token` kulcs: a bejelentkezéskor kapott token
- Kimenet: a sikerességen kívül nem tartalmaz extra információt

### 3. Toplisták
##### 3.1 Általános be- és kimenet
Minden toplista azonos be- és kimenettel tartozik, melyeknek a felépítése a lentiekben következő.
- Bemenet: minden szűrés opcionális
  - `type`: játéktípusra szűrés
  - `mode`: játékmódra szűrés
  - `difficulty`: nehézségre való szűrés
  - `topnum`: maximum hány felhasználót jelenítsen meg a listában. Amennyiben nincs megadva, az alapértelmezett érték 10.
- kimenet:
  - `list`: egy JSON array, melynek minden i. eleme egy JSON objektum, a toplista (i+1). helyezettje. Az egyes objektumok két kulccsal rendelkeznek:
    - `name`: a felhasználó neve
    - `value`: az érték, ami alapján elérte a felhasználó a helyezését. Ennek típusa toplistánként változik, azonban mindig olvasható szöveges reprezentáció.

##### 3.1 Megnyert játékok száma szerint
- Elérés: a `sudoku/topveteran.php` útvonalon `GET` kéréssel

##### 3.2 A játékok átlagideje szerint
- Elérés: a `sudoku/toptime.php` útvonalon `GET` kéréssel