package ro.tuiasi.pp.lab8.gates.builder

import ro.tuiasi.pp.lab8.gates.abstraction.FourInputGate
import ro.tuiasi.pp.lab8.gates.bridge.StateMachineAndImpl

// TODO: Implementează builder-ul pentru FourInputGate (similar cu TwoInputGateBuilder).
class FourInputGateBuilder : AndGateBuilder<FourInputGate> {
    private val inputs = mutableListOf<Boolean>()

    override fun addInput(value: Boolean): AndGateBuilder<FourInputGate> = TODO("De implementat")

    override fun build(): FourInputGate = TODO("De implementat")
}
