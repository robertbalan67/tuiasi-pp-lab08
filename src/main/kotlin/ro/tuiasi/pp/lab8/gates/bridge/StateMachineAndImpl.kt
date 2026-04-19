package ro.tuiasi.pp.lab8.gates.bridge

import ro.tuiasi.pp.lab8.gates.state.AllHighState

// Implementare concretă a lui AndImplementation care folosește automatul de stări
// din pachetul `state` pentru a calcula ieșirea porții AND.
class StateMachineAndImpl : AndImplementation {
    // TODO: Implementează compute() folosind AllHighState ca stare inițială.
    //       Parcurge lista de intrări, aplică process() pe fiecare și returnează
    //       output() al stării finale.
    override fun compute(inputs: List<Boolean>): Boolean = TODO("De implementat")
}
