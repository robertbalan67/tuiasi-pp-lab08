package ro.tuiasi.pp.lab8.memento

/**
 * Memento: stochează o instantanee a stării ceasului (ore, minute, secunde).
 * Obiectul este imutabil — starea salvată nu poate fi modificată.
 *
 * @param hours   Orele (0–23)
 * @param minutes Minutele (0–59)
 * @param seconds Secundele (0–59)
 */
data class ClockMemento(val hours: Int, val minutes: Int, val seconds: Int)
