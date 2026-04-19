package ro.tuiasi.pp.lab8.gates.bridge

// Bridge — latura implementării.
// Definește contractul de calcul al ieșirii unei porți AND,
// decuplând logica de calcul de abstractizarea porții.
interface AndImplementation {
    // TODO: Declară funcția care primește lista de intrări (Boolean)
    //       și returnează ieșirea porții AND.
    fun compute(inputs: List<Boolean>): Boolean
}
