package ro.tuiasi.pp.lab8.gates.abstraction

import ro.tuiasi.pp.lab8.gates.bridge.AndImplementation

// TODO: Implementează FourInputGate — o poartă AND cu 4 intrări.
class FourInputGate(impl: AndImplementation) : AndGate(impl) {
    override val expectedInputs: Int = TODO("De implementat")
}
