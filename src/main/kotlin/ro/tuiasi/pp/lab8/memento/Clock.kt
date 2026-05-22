package ro.tuiasi.pp.lab8.memento

/**
 * Ceasul (Originator în pattern-ul Memento).
 * Stochează ora curentă și poate crea/restaura memento-uri.
 *
 * @param hours   Ora inițială (0–23)
 * @param minutes Minutele inițiale (0–59)
 * @param seconds Secundele inițiale (0–59)
 */
class Clock(
    var hours: Int = 0,
    var minutes: Int = 0,
    var seconds: Int = 0
) {
    /**
     * Setează ora ceasului la [h]:[m]:[s].
     * Pre-condiții: h în [0..23], m în [0..59], s în [0..59].
     * Aruncă IllegalArgumentException dacă valorile sunt în afara intervalului.
     */
    fun setTime(h: Int, m: Int, s: Int) {
        TODO("De implementat")
    }

    /**
     * Simulează pornirea cronometrului: adaugă 1 secundă la ora curentă,
     * gestionând overflow-ul (59s+1 → 0s, +1min).
     */
    fun tickOnce() {
        TODO("De implementat: incrementează seconds, gestionează overflow la minutes și hours")
    }

    /**
     * Creează și returnează un memento cu starea curentă a ceasului.
     */
    fun save(): ClockMemento {
        TODO("De implementat")
    }

    /**
     * Restaurează starea ceasului din [memento].
     */
    fun restore(memento: ClockMemento) {
        TODO("De implementat")
    }

    override fun toString(): String = "%02d:%02d:%02d".format(hours, minutes, seconds)
}
