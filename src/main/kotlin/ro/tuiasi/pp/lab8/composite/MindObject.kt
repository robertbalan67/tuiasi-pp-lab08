package ro.tuiasi.pp.lab8.composite

/**
 * Interfața comună pentru toate nodurile din arborele de tip mind-map.
 * Atât frunzele cât și nodurile compuse implementează această interfață.
 */
interface MindObject {
    /**
     * Afișează conținutul nodului, indentat cu [level] niveluri (2 spații per nivel).
     * Nodurile compuse afișează și conținutul copiilor, recursiv, cu [level + 1].
     */
    fun showContent(level: Int)

    /**
     * Adaugă [obj] ca nod copil.
     * Frunzele (Idee, Cadou) aruncă UnsupportedOperationException.
     */
    fun addChild(obj: MindObject)

    /**
     * Elimină [obj] din lista de copii.
     * Frunzele (Idee, Cadou) aruncă UnsupportedOperationException.
     */
    fun removeChild(obj: MindObject)
}
