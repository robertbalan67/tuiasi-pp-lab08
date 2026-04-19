package ro.tuiasi.pp.lab8.gates.abstraction

import ro.tuiasi.pp.lab8.gates.bridge.AndImplementation

// Bridge — latura abstractizării.
// Deține o referință la AndImplementation și delege calculul către aceasta.
abstract class AndGate(protected val impl: AndImplementation) {

    protected val inputs: MutableList<Boolean> = mutableListOf()

    // Numărul exact de intrări acceptate de această poartă.
    abstract val expectedInputs: Int

    // TODO: Implementează addInput() — adaugă valoarea în lista de intrări.
    //       Aruncă IllegalStateException dacă s-a depășit numărul de intrări așteptat.
    fun addInput(value: Boolean): AndGate = TODO("De implementat")

    // TODO: Implementează output() — delege calculul către impl.
    //       Aruncă IllegalStateException dacă nu s-au furnizat toate intrările așteptate.
    fun output(): Boolean = TODO("De implementat")
}
