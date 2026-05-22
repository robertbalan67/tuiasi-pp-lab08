# Lab 8 — Design Patterns în Kotlin

Template GitHub Classroom pentru laboratorul 8 — Paradigme de Programare (TUIASI).

## Conținut

Trei teme independente de design patterns:

| Temă | Pattern | Fișiere principale |
|------|---------|-------------------|
| 1 | Observer | `observer/Kitchen.kt`, `observer/OrderLog.kt` |
| 2 | Composite | `composite/Calatorie.kt`, `composite/Activitate.kt`, `composite/Idee.kt`, `composite/Cadou.kt` |
| 3 | Memento | `memento/Clock.kt`, `memento/ClockCaretaker.kt` |

Fișierele sursă conțin **schelete** (`TODO("De implementat")`).
Testele din `src/test/` sunt complete și definesc comportamentul așteptat.

## Rulare teste

```bash
gradle test
```

## GitHub Classroom

Repository-ul include workflow-ul `.github/workflows/classroom.yml` care rulează testele la fiecare push/pull request.
