package ro.tuiasi.pp.lab8.observer

/**
 * Interfața observer pentru evenimentele de servire a mesei.
 * Implementatorii primesc notificări când un angajat servește un produs unui client.
 */
interface MealObserver {
    /**
     * Apelat când un angajat ([employee]) a servit produsul [product] clientului [customer].
     */
    fun onMealServed(employee: String, product: String, customer: String)
}
