package ro.tuiasi.pp.lab8.gates.state

// Automat finit de stări folosit de implementarea concretă a porții AND.
//
// Stări posibile:
//   AllHighState — toate intrările văzute până acum sunt 1  → ieșire provizorie: 1
//   AnyLowState  — cel puțin o intrare a fost 0            → ieșire definitivă: 0
//
// Exemplu de utilizare:
//   var state: GateState = AllHighState
//   inputs.forEach { state = state.process(it) }
//   val output = state.output()

sealed class GateState {
    // TODO: Declară funcția care primește o intrare booleană și returnează
    //       starea următoare conform tranzițiilor automatului.
    abstract fun process(input: Boolean): GateState

    // TODO: Declară funcția care returnează ieșirea curentă a stării (true/false).
    abstract fun output(): Boolean
}

// TODO: Implementează AllHighState — starea inițială; trece în AnyLowState dacă input = false.
object AllHighState : GateState() {
    override fun process(input: Boolean): GateState = TODO("De implementat")
    override fun output(): Boolean = TODO("De implementat")
}

// TODO: Implementează AnyLowState — stare absorbantă; rămâne în această stare indiferent de input.
object AnyLowState : GateState() {
    override fun process(input: Boolean): GateState = TODO("De implementat")
    override fun output(): Boolean = TODO("De implementat")
}
