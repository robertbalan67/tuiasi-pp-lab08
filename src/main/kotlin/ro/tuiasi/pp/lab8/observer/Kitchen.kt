package ro.tuiasi.pp.lab8.observer

/**
 * Bucătăria (Observable): conține angajați și produse disponibile.
 * Gestionează observatorii și îi notifică la fiecare servire.
 *
 * @param employees Lista de angajați disponibili (pre-inițializată)
 * @param products  Lista de produse disponibile (pre-inițializată)
 */
class Kitchen(
    val employees: MutableList<String>,
    val products: MutableList<String>
) {
    private val observers = mutableListOf<MealObserver>()

    /**
     * Adaugă un observator care va fi notificat la fiecare servire.
     */
    fun addObserver(observer: MealObserver) {
        TODO("De implementat")
    }

    /**
     * Elimină un observator din lista de notificări.
     */
    fun removeObserver(observer: MealObserver) {
        TODO("De implementat")
    }

    /**
     * Angajatul [employee] servește produsul [product] clientului [customer].
     *
     * Pre-condiții: [employee] există în [employees], [product] există în [products].
     * Post-condiții: toți observatorii sunt notificați prin onMealServed.
     *
     * Aruncă IllegalArgumentException dacă angajatul sau produsul nu există în liste.
     */
    fun serve(employee: String, product: String, customer: String) {
        TODO("De implementat: validează, notifică observatorii")
    }
}
