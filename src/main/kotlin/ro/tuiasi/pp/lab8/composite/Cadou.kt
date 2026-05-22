package ro.tuiasi.pp.lab8.composite

/**
 * Frunză în arborele mind-map: reprezintă un cadou (fără copii).
 *
 * @param description Descrierea cadoului
 */
class Cadou(val description: String) : MindObject {

    /** Afișează "  ".repeat(level) + description */
    override fun showContent(level: Int) {
        TODO("De implementat")
    }

    override fun addChild(obj: MindObject) {
        throw UnsupportedOperationException("Cadou nu poate avea copii")
    }

    override fun removeChild(obj: MindObject) {
        throw UnsupportedOperationException("Cadou nu poate avea copii")
    }
}
