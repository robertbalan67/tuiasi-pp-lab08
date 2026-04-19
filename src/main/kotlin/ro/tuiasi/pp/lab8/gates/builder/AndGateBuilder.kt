package ro.tuiasi.pp.lab8.gates.builder

import ro.tuiasi.pp.lab8.gates.abstraction.AndGate

// Builder — interfața comună pentru construirea porților AND.
// Fiecare intrare se trimite prin addInput(); build() finalizează construcția.
interface AndGateBuilder<T : AndGate> {
    fun addInput(value: Boolean): AndGateBuilder<T>
    fun build(): T
}
