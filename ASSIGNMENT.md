# Teme pe acasă - Laborator 8

Două teme independente, fiecare centrată pe câteva design patterns.

---

## Tema 1: Porți logice AND

Să se proiecteze și să se implementeze o aplicație care calculează ieșirile unor porți AND cu **2, 3, 4 și 8 intrări**, utilizând trei design patterns:

- **Bridge** — decuplează abstractizarea porții de implementarea calculului
- **Builder** — construiește fiecare poartă adăugând intrările una câte una
- **State (automat finit)** — calculează ieșirea porții prin tranzițiile automatului

### Structura proiectului

```
gates/
  bridge/
    AndImplementation.kt       ← interfața de calcul (latura implementării din Bridge)
    StateMachineAndImpl.kt     ← implementare concretă care folosește automatul de stări
  state/
    GateState.kt               ← sealed class cu stările automatului
  abstraction/
    AndGate.kt                 ← clasa abstractă a porții (latura abstractizării din Bridge)
    TwoInputGate.kt            ← poartă AND cu 2 intrări
    ThreeInputGate.kt
    FourInputGate.kt
    EightInputGate.kt
  builder/
    AndGateBuilder.kt          ← interfața builder
    TwoInputGateBuilder.kt     ← builder concret pentru TwoInputGate
    ThreeInputGateBuilder.kt
    FourInputGateBuilder.kt
    EightInputGateBuilder.kt
  Main1.kt
```

### Cerințe pas cu pas

#### 1. Automatul de stări — `GateState.kt`

O poartă AND are ieșirea `1` doar dacă **toate** intrările sunt `1`. Modelează aceasta cu două stări:

| Stare | Semnificație | `output()` |
|---|---|---|
| `AllHighState` | Toate intrările văzute până acum sunt `true` | `true` |
| `AnyLowState` | Cel puțin o intrare a fost `false` | `false` |

Tranzițiile automatului:
- `AllHighState.process(true)` → `AllHighState`
- `AllHighState.process(false)` → `AnyLowState`
- `AnyLowState.process(_)` → `AnyLowState` *(stare absorbantă)*

#### 2. Implementarea Bridge — `StateMachineAndImpl.kt`

Implementează `AndImplementation.compute(inputs)`:
1. Pornește din `AllHighState`.
2. Aplică `process()` pentru fiecare input din listă.
3. Returnează `output()` al stării finale.

#### 3. Abstractizarea Bridge — `AndGate.kt`

Clasa abstractă deține:
- o referință la `AndImplementation` (primită prin constructor)
- lista de intrări acumulată
- proprietatea abstractă `expectedInputs: Int`

Metodele:
- `addInput(value: Boolean): AndGate` — adaugă o intrare; aruncă `IllegalStateException` dacă s-a depășit `expectedInputs`
- `output(): Boolean` — delege calculul la `impl.compute(inputs)`; aruncă `IllegalStateException` dacă nu s-au furnizat toate intrările

#### 4. Clasele concrete ale porților

`TwoInputGate`, `ThreeInputGate`, `FourInputGate`, `EightInputGate` — fiecare extinde `AndGate` și setează `expectedInputs` la valoarea corespunzătoare.

#### 5. Builder-ele

Fiecare builder (`TwoInputGateBuilder` etc.) stochează intrările intern și le aplică pe poartă la `build()`.
- `build()` aruncă `IllegalStateException` dacă nu s-au furnizat exact `expectedInputs` intrări.

### Exemplu de utilizare

```kotlin
val gate = TwoInputGateBuilder()
    .addInput(true)
    .addInput(false)
    .build()
println(gate.output()) // false
```

---

## Tema 2: Browser pentru copii

Să se proiecteze și să se implementeze un browser pentru copii utilizând trei design patterns:

- **Prototype** — clonarea cererii HTTP generice
- **Proxy** — control parental care blochează anumite domenii
- **Facade** — interfață simplă de navigare

### Structura proiectului

```
browser/
  HttpRequest.kt              ← cerere HTTP (Prototype)
  HttpClient.kt               ← interfață + implementare reală (stub pentru teste)
  ParentalControlProxy.kt     ← Proxy cu control parental
  KidsBrowserFacade.kt        ← Facade
  Main2.kt
```

### Cerințe pas cu pas

#### 1. Prototype — `HttpRequest.kt`

`HttpRequest` este o `data class` cu câmpurile `url: String` și `headers: Map<String, String>`.
Implementează interfața `Prototype<HttpRequest>`, unde `clone()` returnează o copie a obiectului (folosind `copy()`).

#### 2. Proxy — `ParentalControlProxy.kt`

`ParentalControlProxy` implementează `HttpClient` și primește:
- un `HttpClient` real (delegat)
- o mulțime `blockedDomains: Set<String>`

Logica `get(request)`:
1. Extrage domeniul din URL (ex: `"badsite.com"` din `"https://badsite.com/page"`).
2. Dacă domeniul se află în `blockedDomains` → returnează `HttpResponse(403, "Acces blocat de controlul parental.")` **fără** a apela clientul real.
3. Altfel → delege cererea clientului real.

> **Hint:** `java.net.URI(url).host` extrage domeniul dintr-un URL.

#### 3. Facade — `KidsBrowserFacade.kt`

`KidsBrowserFacade` primește un `HttpClient` (care va fi proxy-ul).

Metoda `browse(url: String): String`:
1. Clonează cererea prototip de bază și setează `url`-ul primit.
2. Trimite cererea prin `client.get()`.
3. Dacă `statusCode == 200` returnează `body`-ul; altfel returnează mesajul de eroare din `body`.

### Exemplu de utilizare

```kotlin
val proxy = ParentalControlProxy(
    client = RealHttpClient(),
    blockedDomains = setOf("badsite.com", "violence.net")
)
val browser = KidsBrowserFacade(proxy)

println(browser.browse("https://wikipedia.org/wiki/Kotlin"))  // conținut pagină
println(browser.browse("https://badsite.com/page"))           // "Acces blocat..."
```

---

## Verificare

```bash
mvn test
```

Testele acoperă:
- **Tema 1:** tranzițiile automatului de stări + toate cele 4 tipuri de porți (cu Builder)
- **Tema 2:** clonarea prototipului, blocarea/permiterea URL-urilor de Proxy, comportamentul Facade

---

## Criterii de evaluare

### Tema 1

| # | Cerință | Punctaj |
|---|---------|---------|
| 1 | Automatul de stări (`AllHighState`, `AnyLowState`, tranzițiile corecte) | 2p |
| 2 | Bridge: `AndImplementation` + `StateMachineAndImpl` | 2p |
| 3 | Bridge: `AndGate` + clasele concrete (2/3/4/8 intrări) | 2p |
| 4 | Builder: toate cele 4 builder-e funcționează corect | 2p |

### Tema 2

| # | Cerință | Punctaj |
|---|---------|---------|
| 1 | Prototype: `HttpRequest.clone()` returnează copie corectă | 1p |
| 2 | Proxy: blochează domeniile din listă fără a apela clientul real | 3p |
| 3 | Facade: `browse()` combină corect Prototype + Proxy | 2p |

### Tema 3 (studiu)

Mediator vs Proxy vs Adapter — pregătește o scurtă comparație (tabel sau câte un paragraf pentru fiecare pattern).
