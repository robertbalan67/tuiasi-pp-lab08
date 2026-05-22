package ro.tuiasi.pp.lab8.composite

/**
 * Frunză în arborele mind-map: reprezintă o idee simplă (fără copii).
 *
 * Exemplu de output pentru showContent(2): "    idee"
 *
 * @param description Textul ideii
 */
class Idee(val description: String) : MindObject {

    /**
     * Afișează "  ".repeat(level) + description
     */
    override fun showContent(level: Int) {
        TODO("De implementat")
    }

    /** Frunzele nu acceptă copii. */
    override fun addChild(obj: MindObject) {
        throw UnsupportedOperationException("Idee nu poate avea copii")
    }

    /** Frunzele nu acceptă copii. */
    override fun removeChild(obj: MindObject) {
        throw UnsupportedOperationException("Idee nu poate avea copii")
    }
}
