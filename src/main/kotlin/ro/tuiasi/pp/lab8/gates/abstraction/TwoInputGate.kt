package ro.tuiasi.pp.lab8.gates.abstraction

import ro.tuiasi.pp.lab8.gates.bridge.AndImplementation

// TODO: Implementează TwoInputGate — o poartă AND cu 2 intrări.
//       expectedInputs trebuie să fie 2.
class TwoInputGate(impl: AndImplementation) : AndGate(impl) {
    override val expectedInputs: Int = TODO("De implementat")
}
