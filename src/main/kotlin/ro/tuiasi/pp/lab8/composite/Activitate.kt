package ro.tuiasi.pp.lab8.composite

/**
 * Nod compus în arborele mind-map: reprezintă o activitate cu sub-activități.
 *
 * Exemplu de output pentru showContent(1) cu un copil de tip Idee("o idee"):
 *   "  Activitate"
 *   "    o idee"
 *
 * @param name Numele activității
 */
class Activitate(val name: String) : MindObject {
    private val children = mutableListOf<MindObject>()

    /**
     * Afișează "  ".repeat(level) + name, apoi apelează showContent(level + 1) pe fiecare copil.
     */
    override fun showContent(level: Int) {
        TODO("De implementat")
    }

    /** Adaugă [obj] la lista de copii. */
    override fun addChild(obj: MindObject) {
        TODO("De implementat")
    }

    /** Elimină [obj] din lista de copii. */
    override fun removeChild(obj: MindObject) {
        TODO("De implementat")
    }
}
