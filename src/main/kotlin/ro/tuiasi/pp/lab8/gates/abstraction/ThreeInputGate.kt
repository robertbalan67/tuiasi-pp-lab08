package ro.tuiasi.pp.lab8.gates.abstraction

import ro.tuiasi.pp.lab8.gates.bridge.AndImplementation

// TODO: Implementează ThreeInputGate — o poartă AND cu 3 intrări.
class ThreeInputGate(impl: AndImplementation) : AndGate(impl) {
    override val expectedInputs: Int = TODO("De implementat")
}
