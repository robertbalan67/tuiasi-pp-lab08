package ro.tuiasi.pp.lab8.observer

/**
 * Observable — bucătăria restaurantului.
 *
 * La fiecare apel serve(), validează angajatul și produsul,
 * apoi notifică toți observatorii înregistrați.
 */
class Kitchen(
    val employees: MutableList<String>,
    val products: MutableList<String>
) {
    private val observers: MutableList<MealObserver> = mutableListOf()

    fun addObserver(observer: MealObserver) {
        observers.add(observer)
    }

    fun removeObserver(observer: MealObserver) {
        observers.remove(observer)
    }

    /**
     * Servește o masă și notifică observatorii.
     *
     * @throws IllegalArgumentException dacă employee sau product nu sunt în listele respective
     */
    fun serve(employee: String, product: String, customer: String) {
        require(employee in employees) { "Angajat necunoscut: $employee" }
        require(product in products)   { "Produs necunoscut: $product" }
        observers.forEach { it.onMealServed(employee, product, customer) }
    }
}
