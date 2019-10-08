Sudoku Követelményspecifikáció
==========================================

### 1. Áttekintés
 
### 2. Jelenlegi rendszer

### 3. Vágyalom rendszer
##### 3.1 Célok, összegyűjtött játékok
Célunk egy olyan játékprogram létrehozása, mely egy helyen gyűjti össze a különböző típusú és nehézségű sudoku játékokat, melyeket minden játékos szabadon elérhet. Ezek közé tartozik a
- hagyományos sudoku
- killer sudoku
- sudoku X
- samurai sudoku
- szabálytalan sudoku
- ezek esetleges lehetséges kombinációi, például a samurai sudoku X

Abban az esetben, ha felmerülne a felhasználói igény egy olyan típusú játék hozzáadására, mely addig nem szerepelt a játszató típusok körében, fontos, hogy az gyorsan és zökkenőmentesen hozzáadható legyen. <br/>
##### 3.2 Többjátékos mód
Ezen felül lehetősége lesz a játékosoknak tudásukat más játékosokkal összemérni akár ugyanazon rejtvény egyszerre való megoldása által, akár az átlagos teljesítményük rangsorolása által.
Előbbi esetben a játék ugyanazt a rejtvényt legenerálja mindkét játékos számára, és az a játékos nyer, aki hamarabb képes megoldani azt. A teljesítménybeli különbség további mértéke lehet, ha a program figyelembe veszi, hogy a vesztes játékos hány százalékban töltötte ki a rejtvényt addig az időpontig, amíg a nyertes játékos teljes megoldást adott.<br/>
##### 3.3 Igények
A program használatához elegendő lenne egy viszonylag kisebb teljesítményű számítógép, hiszen a sudoku nem egy grafikailag intenzív játék. Ez megengedné, hogy eljusson a potenciális játékosok egy nagyon széles köréhez, hiszen manapság ritka az olyan gyenge gép, mely ennyire statikus grafikai elemek megjelenítésével sem boldogul.
A funkciók teljes tárházának kihasználásához internetkapcsolat is szükséges, azonban a játék működne enélkül is egyjátékos módban, a rangsorok megjelenítése nélkül. A rangsort ebben az esetben helyileg menti, és helyreállt internetkapcsolat esetén szinkronizálja a szerverrel. <br/>
##### 3.4 Összefoglalás
Egy ilyen program használata jóval egyszerűbb lenne a jelenlegi helyzethez való alkalmazkodásnál, hiszen megszüntetné annak szükségét, hogy több programot használjunk nagyon hasonló cél elérésére.
A kívánt játéktípust és -nehézséget, valamint az egy- vagy többjátékos módot néhány kattintással ki lehet majd választani ahelyett, hogy a megfelelő program után kéne kutakodni minden különböző játék esetén.

### 4. Jelenlegi üzleti folyamatok modellje

### 5. Igényelt üzleti folyamatok modellje
