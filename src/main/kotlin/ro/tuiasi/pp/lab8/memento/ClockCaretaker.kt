package ro.tuiasi.pp.lab8.memento

/**
 * Caretaker în pattern-ul Memento: gestionează stiva de memento-uri.
 * Permite salvarea stărilor anterioare și restaurarea lor în ordine LIFO.
 */
class ClockCaretaker {
    private val history = mutableListOf<ClockMemento>()

    /**
     * Salvează [memento] în istoricul stărilor.
     */
    fun push(memento: ClockMemento) {
        TODO("De implementat")
    }

    /**
     * Returnează și elimină cel mai recent memento din istoric (LIFO).
     * Returnează null dacă istoricul este gol.
     */
    fun pop(): ClockMemento? {
        TODO("De implementat")
    }

    /** Returnează numărul de memento-uri salvate. */
    val size: Int get() = history.size
}
