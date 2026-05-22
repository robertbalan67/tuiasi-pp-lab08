package ro.tuiasi.pp.lab8.composite

/**
 * Rădăcina arborelui mind-map: reprezintă o călătorie (conferință).
 * Se comportă identic cu Activitate, dar este punctul de intrare al arborelui.
 *
 * @param destination Destinația / titlul conferinței
 */
class Calatorie(val destination: String) : MindObject {
    private val children = mutableListOf<MindObject>()

    /** Afișează "  ".repeat(level) + destination, apoi afișează copiii cu level + 1. */
    override fun showContent(level: Int) {
        TODO("De implementat")
    }

    override fun addChild(obj: MindObject) {
        TODO("De implementat")
    }

    override fun removeChild(obj: MindObject) {
        TODO("De implementat")
    }
}
