package ro.tuiasi.pp.lab8.observer

// ─── Interfață (gata în repo — includem pentru completitudine) ────────────────

interface MealObserver {
    fun onMealServed(employee: String, product: String, customer: String)
}

// ─── Observer concret ─────────────────────────────────────────────────────────

/**
 * Înregistrează fiecare servire ca string formatat.
 * Poate persista jurnalul într-un fișier cu writeToFile().
 */
class OrderLog : MealObserver {

    val entries: MutableList<String> = mutableListOf()

    override fun onMealServed(employee: String, product: String, customer: String) {
        entries.add("Client: $customer, Produs: $product, Angajat: $employee")
    }

    /**
     * Scrie fiecare intrare pe câte o linie în fișierul [path].
     */
    fun writeToFile(path: String) {
        java.io.File(path).writeText(entries.joinToString("\n"))
    }
}
