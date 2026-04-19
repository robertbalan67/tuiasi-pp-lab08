package ro.tuiasi.pp.lab8.gates.abstraction

import ro.tuiasi.pp.lab8.gates.bridge.AndImplementation

// TODO: Implementează EightInputGate — o poartă AND cu 8 intrări.
class EightInputGate(impl: AndImplementation) : AndGate(impl) {
    override val expectedInputs: Int = TODO("De implementat")
}
