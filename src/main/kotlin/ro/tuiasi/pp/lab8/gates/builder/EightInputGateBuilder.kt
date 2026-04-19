package ro.tuiasi.pp.lab8.gates.builder

import ro.tuiasi.pp.lab8.gates.abstraction.EightInputGate
import ro.tuiasi.pp.lab8.gates.bridge.StateMachineAndImpl

// TODO: Implementează builder-ul pentru EightInputGate (similar cu TwoInputGateBuilder).
class EightInputGateBuilder : AndGateBuilder<EightInputGate> {
    private val inputs = mutableListOf<Boolean>()

    override fun addInput(value: Boolean): AndGateBuilder<EightInputGate> = TODO("De implementat")

    override fun build(): EightInputGate = TODO("De implementat")
}
