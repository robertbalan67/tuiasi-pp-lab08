# PP Lab 8 - Template GitHub Classroom

Acest repository este un template pentru temele de la Laboratorul 8 (Design Patterns în Kotlin).

## Ce conține template-ul

- proiect Kotlin configurat cu Maven
- schelete de cod pentru două teme independente
- teste unitare pentru ambele teme
- enunțul detaliat în `ASSIGNMENT.md`

## Teme

| Temă | Design Patterns |
|------|----------------|
| **Tema 1** — Porți logice AND | Bridge + Builder + State (automat finit) |
| **Tema 2** — Browser pentru copii | Prototype + Proxy + Facade |
| **Tema 3** — Studiu | Mediator vs Proxy vs Adapter |

## Structura sursă

```
src/main/kotlin/ro/tuiasi/pp/lab8/
  gates/          ← Tema 1
    bridge/       ← interfața și implementarea de calcul
    state/        ← automatul finit de stări
    abstraction/  ← ierarhia porților (Bridge - latura abstractizării)
    builder/      ← builder-e pentru fiecare tip de poartă
  browser/        ← Tema 2
```

## Rulare teste

```bash
mvn test
```

## GitHub Classroom

Repository-ul include workflow-ul `.github/workflows/classroom.yml` care rulează testele la fiecare push/pull request.
