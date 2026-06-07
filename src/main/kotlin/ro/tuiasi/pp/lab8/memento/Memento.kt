package ro.tuiasi.pp.lab8.memento

// ─── Memento (data class — gata în repo) ─────────────────────────────────────

data class ClockMemento(val hours: Int, val minutes: Int, val seconds: Int)

// ─── Originator ───────────────────────────────────────────────────────────────

/**
 * Ceas cu ore, minute, secunde.
 *
 * Poate salva starea curentă ca ClockMemento și o poate restaura ulterior
 * (pattern Memento — Originator).
 */
class Clock(
    var hours: Int,
    var minutes: Int,
    var seconds: Int
) {
    init {
        validateTime(hours, minutes, seconds)
    }

    /**
     * Setează ora. Aruncă IllegalArgumentException dacă valorile sunt în afara intervalului.
     */
    fun setTime(h: Int, m: Int, s: Int) {
        validateTime(h, m, s)
        hours = h; minutes = m; seconds = s
    }

    /**
     * Avansează cu o secundă, propagând carry-ul în minute și ore.
     *
     * seconds: 0..59 → 60 devine 0 + minutes++
     * minutes: 0..59 → 60 devine 0 + hours++
     */
    fun tickOnce() {
        seconds++
        if (seconds == 60) {
            seconds = 0
            minutes++
        }
        if (minutes == 60) {
            minutes = 0
            hours++
        }
        // Opțional: hours %= 24 pentru wrap la miezul nopții
    }

    /** Salvează starea curentă. */
    fun save(): ClockMemento = ClockMemento(hours, minutes, seconds)

    /** Restaurează o stare salvată anterior. */
    fun restore(memento: ClockMemento) {
        hours   = memento.hours
        minutes = memento.minutes
        seconds = memento.seconds
    }

    // ── Privat ────────────────────────────────────────────────────────────────

    private fun validateTime(h: Int, m: Int, s: Int) {
        require(h in 0..23) { "Ore invalide: $h (trebuie 0..23)" }
        require(m in 0..59) { "Minute invalide: $m (trebuie 0..59)" }
        require(s in 0..59) { "Secunde invalide: $s (trebuie 0..59)" }
    }
}

// ─── Caretaker ────────────────────────────────────────────────────────────────

/**
 * Stivă LIFO de ClockMemento.
 *
 * push() adaugă un snapshot, pop() îl scoate (undo).
 */
class ClockCaretaker {

    private val stack: ArrayDeque<ClockMemento> = ArrayDeque()

    fun push(memento: ClockMemento) {
        stack.addLast(memento)
    }

    /** Returnează și elimină cel mai recent memento, sau null dacă stiva e goală. */
    fun pop(): ClockMemento? =
        if (stack.isEmpty()) null else stack.removeLast()
}
