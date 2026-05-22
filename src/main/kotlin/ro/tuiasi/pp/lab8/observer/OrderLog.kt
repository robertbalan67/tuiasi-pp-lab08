package ro.tuiasi.pp.lab8.observer

/**
 * Observer care înregistrează în memorie fiecare comandă servită.
 * La final, jurnalul poate fi scris într-un fișier prin [writeToFile].
 *
 * Fiecare înregistrare are forma: "Client: <customer>, Produs: <product>, Angajat: <employee>"
 */
class OrderLog : MealObserver {

    /** Lista de înregistrări (câte una per servire). */
    val entries: MutableList<String> = mutableListOf()

    /**
     * Adaugă o înregistrare în jurnal.
     * Format: "Client: <customer>, Produs: <product>, Angajat: <employee>"
     */
    override fun onMealServed(employee: String, product: String, customer: String) {
        TODO("De implementat")
    }

    /**
     * Scrie toate înregistrările în fișierul [path], câte o linie per înregistrare.
     * Dacă [path] este gol, nu face nimic.
     */
    fun writeToFile(path: String) {
        TODO("De implementat")
    }
}
