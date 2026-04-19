package ro.tuiasi.pp.lab8.gates.builder

import ro.tuiasi.pp.lab8.gates.abstraction.TwoInputGate
import ro.tuiasi.pp.lab8.gates.bridge.StateMachineAndImpl

// TODO: Implementează builder-ul pentru TwoInputGate.
//       addInput() stochează intrările; build() construiește și returnează gate-ul
//       cu toate intrările adăugate.
//       Aruncă IllegalStateException din build() dacă nu s-au furnizat exact 2 intrări.
class TwoInputGateBuilder : AndGateBuilder<TwoInputGate> {
    private val inputs = mutableListOf<Boolean>()

    override fun addInput(value: Boolean): AndGateBuilder<TwoInputGate> = TODO("De implementat")

    override fun build(): TwoInputGate = TODO("De implementat")
}
