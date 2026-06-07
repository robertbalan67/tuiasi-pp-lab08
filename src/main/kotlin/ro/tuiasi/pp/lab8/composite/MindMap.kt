package ro.tuiasi.pp.lab8.composite

// ─── Interfață (gata în repo) ─────────────────────────────────────────────────

interface MindObject {
    fun showContent(level: Int)
    fun addChild(obj: MindObject)
    fun removeChild(obj: MindObject)
}

// ─── Rădăcină ─────────────────────────────────────────────────────────────────

/**
 * Nodul rădăcină al mind-map-ului (ex: "Conferinta RSA, San Francisco").
 * Poate conține orice tip de MindObject ca copii.
 */
class Calatorie(private val destination: String) : MindObject {

    private val children: MutableList<MindObject> = mutableListOf()

    override fun showContent(level: Int) {
        println("  ".repeat(level) + destination)
        children.forEach { it.showContent(level + 1) }
    }

    override fun addChild(obj: MindObject) {
        children.add(obj)
    }

    override fun removeChild(obj: MindObject) {
        children.remove(obj)
    }
}

// ─── Nod intern ───────────────────────────────────────────────────────────────

/**
 * Nod intern cu copii (ex: "Activitati din prima zi").
 */
class Activitate(private val name: String) : MindObject {

    private val children: MutableList<MindObject> = mutableListOf()

    override fun showContent(level: Int) {
        println("  ".repeat(level) + name)
        children.forEach { it.showContent(level + 1) }
    }

    override fun addChild(obj: MindObject) {
        children.add(obj)
    }

    override fun removeChild(obj: MindObject) {
        children.remove(obj)
    }
}

// ─── Frunze ───────────────────────────────────────────────────────────────────

/**
 * Frunză de tip idee (ex: "Vizita Alcatraz").
 * Nu poate avea copii — addChild/removeChild aruncă UnsupportedOperationException.
 */
class Idee(private val description: String) : MindObject {

    override fun showContent(level: Int) {
        println("  ".repeat(level) + description)
    }

    override fun addChild(obj: MindObject) {
        throw UnsupportedOperationException("Idee este frunză — nu poate avea copii")
    }

    override fun removeChild(obj: MindObject) {
        throw UnsupportedOperationException("Idee este frunză — nu poate avea copii")
    }
}

/**
 * Frunză de tip cadou (ex: "Tricou Google").
 */
class Cadou(private val name: String) : MindObject {

    override fun showContent(level: Int) {
        println("  ".repeat(level) + name)
    }

    override fun addChild(obj: MindObject) {
        throw UnsupportedOperationException("Cadou este frunză — nu poate avea copii")
    }

    override fun removeChild(obj: MindObject) {
        throw UnsupportedOperationException("Cadou este frunză — nu poate avea copii")
    }
}
