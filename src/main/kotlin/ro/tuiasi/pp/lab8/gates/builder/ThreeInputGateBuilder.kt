package ro.tuiasi.pp.lab8.gates.builder

import ro.tuiasi.pp.lab8.gates.abstraction.ThreeInputGate
import ro.tuiasi.pp.lab8.gates.bridge.StateMachineAndImpl

// TODO: Implementează builder-ul pentru ThreeInputGate (similar cu TwoInputGateBuilder).
class ThreeInputGateBuilder : AndGateBuilder<ThreeInputGate> {
    private val inputs = mutableListOf<Boolean>()

    override fun addInput(value: Boolean): AndGateBuilder<ThreeInputGate> = TODO("De implementat")

    override fun build(): ThreeInputGate = TODO("De implementat")
}
