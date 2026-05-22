# Lab 8 — Design Patterns în Kotlin

## Descriere

Trei teme independente, fiecare demonstrând un design pattern clasic:

| Temă | Pattern | Fișiere principale |
|------|---------|-------------------|
| 1 | Observer | `observer/Kitchen.kt`, `observer/MealObserver.kt`, `observer/OrderLog.kt` |
| 2 | Composite | `composite/MindObject.kt`, `composite/Calatorie.kt`, `composite/Activitate.kt`, `composite/Idee.kt`, `composite/Cadou.kt` |
| 3 | Memento | `memento/Clock.kt`, `memento/ClockMemento.kt`, `memento/ClockCaretaker.kt` |

---

## Structura proiectului

```
lab08/
  src/
    main/kotlin/ro/tuiasi/pp/lab8/
      observer/
        MealObserver.kt      ← interfața Observer
        Kitchen.kt           ← clasa Observable (stub)
        OrderLog.kt          ← Observer concret: jurnalizare comenzi (stub)
      composite/
        MindObject.kt        ← interfața Composite
        Calatorie.kt         ← rădăcina arborelui (stub)
        Activitate.kt        ← nod intern cu copii (stub)
        Idee.kt              ← frunză (stub)
        Cadou.kt             ← frunză (stub)
      memento/
        ClockMemento.kt      ← memento (valori imutabile)
        Clock.kt             ← Originator: ceas cu tickOnce (stub)
        ClockCaretaker.kt    ← Caretaker: stivă LIFO (stub)
    test/kotlin/ro/tuiasi/pp/lab8/
      ObserverTest.kt
      CompositeTest.kt
      MementoTest.kt
  build.gradle.kts
  settings.gradle.kts
  .github/workflows/classroom.yml
  ASSIGNMENT.md
  README.md
```

---

## Tema 1 — Observer: Bucătărie (Burger King)

Se implementează un sistem de notificări pentru activitatea dintr-un restaurant. La fiecare
servire, toți observatorii înregistrați sunt notificați cu angajatul, produsul și clientul.

### `MealObserver` (interfață — gata)

```kotlin
interface MealObserver {
    fun onMealServed(employee: String, product: String, customer: String)
}
```

### `Kitchen` (stub)

**Câmpuri:**
- `employees: MutableList<String>` — lista angajaților disponibili (pasată la construcție)
- `products: MutableList<String>` — lista produselor disponibile (pasată la construcție)
- `observers: MutableList<MealObserver>` — observatorii înregistrați (privat)

**Metode de implementat:**

| Metodă | Comportament |
|--------|-------------|
| `addObserver(observer)` | Adaugă observatorul la listă |
| `removeObserver(observer)` | Elimină observatorul din listă |
| `serve(employee, product, customer)` | Validează că `employee` există în `employees` și `product` există în `products` (aruncă `IllegalArgumentException` altfel), apoi notifică toți observatorii prin `onMealServed` |

### `OrderLog` (stub)

Observer concret care înregistrează fiecare servire.

**Câmpuri:**
- `entries: MutableList<String>` — lista înregistrărilor

**Metode de implementat:**

| Metodă | Comportament |
|--------|-------------|
| `onMealServed(employee, product, customer)` | Adaugă un string de forma `"Client: <customer>, Produs: <product>, Angajat: <employee>"` în `entries` |
| `writeToFile(path: String)` | Scrie fiecare intrare din `entries` pe câte o linie în fișierul `path` |

**Exemplu:**
```kotlin
val kitchen = Kitchen(
    employees = mutableListOf("Bob", "Alice"),
    products  = mutableListOf("Burger", "Fries")
)
val log = OrderLog()
kitchen.addObserver(log)
kitchen.serve("Bob", "Burger", "Maria")
// log.entries == ["Client: Maria, Produs: Burger, Angajat: Bob"]
```

---

## Tema 2 — Composite: Mind-Map Conferință

Se modelează un arbore comportamental (mind-map) al participării la o conferință.

### `MindObject` (interfață — gata)

```kotlin
interface MindObject {
    fun showContent(level: Int)
    fun addChild(obj: MindObject)
    fun removeChild(obj: MindObject)
}
```

### Noduri de implementat

| Clasă | Tip | Comportament `showContent` | `addChild` / `removeChild` |
|-------|-----|---------------------------|---------------------------|
| `Calatorie(destination)` | Rădăcină | Afișează `"  ".repeat(level) + destination`, apoi apelează `showContent(level+1)` pe copii | Adaugă/elimină din lista internă |
| `Activitate(name)` | Nod intern | Afișează `"  ".repeat(level) + name`, recurse pe copii | Adaugă/elimină din lista internă |
| `Idee(description)` | Frunză | Afișează `"  ".repeat(level) + description` | Aruncă `UnsupportedOperationException` |
| `Cadou(name)` | Frunză | Afișează `"  ".repeat(level) + name` | Aruncă `UnsupportedOperationException` |

**Exemplu de utilizare:**
```kotlin
val calatorie = Calatorie("Conferinta RSA, San Francisco")
val ziua1 = Activitate("Activitati din prima zi")
ziua1.addChild(Idee("Vizita Alcatraz"))
calatorie.addChild(ziua1)
calatorie.showContent(0)
// Conferinta RSA, San Francisco
//   Activitati din prima zi
//     Vizita Alcatraz
```

---

## Tema 3 — Memento: Ceas

Se implementează un ceas cu posibilitatea de a salva și restaura starea.

### `ClockMemento` (data class — gata)

```kotlin
data class ClockMemento(val hours: Int, val minutes: Int, val seconds: Int)
```

### `Clock` (stub)

**Câmpuri:** `hours`, `minutes`, `seconds` (Int, mutabili)

**Metode de implementat:**

| Metodă | Comportament |
|--------|-------------|
| `setTime(h, m, s)` | Setează ora. Aruncă `IllegalArgumentException` dacă `h !in 0..23`, `m !in 0..59` sau `s !in 0..59` |
| `tickOnce()` | Adaugă 1 secundă. Dacă `seconds == 59`: seconds=0, minutes++. Dacă `minutes == 60` după incrementare: minutes=0, hours++ |
| `save(): ClockMemento` | Returnează `ClockMemento(hours, minutes, seconds)` |
| `restore(memento)` | Setează `hours`, `minutes`, `seconds` din `memento` |

### `ClockCaretaker` (stub)

Stivă LIFO care păstrează memento-urile.

| Metodă | Comportament |
|--------|-------------|
| `push(memento)` | Adaugă memento-ul pe stivă |
| `pop(): ClockMemento?` | Scoate și returnează cel mai recent memento, sau `null` dacă stiva e goală |

**Exemplu (undo):**
```kotlin
val clock = Clock(10, 0, 0)
val caretaker = ClockCaretaker()

caretaker.push(clock.save())      // salvăm starea
clock.setTime(20, 30, 0)          // modificăm
clock.restore(caretaker.pop()!!)  // restaurăm
// clock.hours == 10
```

---

## Cum se rulează testele

```bash
gradle test
```

---

## Tabel de evaluare

| Cerință | Punctaj |
|---------|---------|
| `Kitchen.addObserver` / `removeObserver` / `serve` (validare + notificare) | 2p |
| `OrderLog.onMealServed` + `writeToFile` | 1p |
| `Activitate.showContent` + `addChild` / `removeChild` | 2p |
| `Idee.showContent` + excepție la `addChild` / `removeChild` | 1p |
| `Calatorie.showContent` + `addChild` / `removeChild` | 1p |
| `Cadou.showContent` + excepție la `addChild` / `removeChild` | 0.5p |
| `Clock.setTime` (validare) + `tickOnce` (overflow) | 1p |
| `Clock.save` + `Clock.restore` | 0.5p |
| `ClockCaretaker.push` / `pop` (LIFO) | 1p |
| **Total** | **10p** |
