package ro.tuiasi.pp.lab8

import ro.tuiasi.pp.lab8.gates.builder.EightInputGateBuilder
import ro.tuiasi.pp.lab8.gates.builder.FourInputGateBuilder
import ro.tuiasi.pp.lab8.gates.builder.ThreeInputGateBuilder
import ro.tuiasi.pp.lab8.gates.builder.TwoInputGateBuilder
import ro.tuiasi.pp.lab8.gates.state.AllHighState
import ro.tuiasi.pp.lab8.gates.state.AnyLowState
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class AndGateTest {

    // --- State machine ---

    @Test
    fun allHighStateOutputIsTrue() {
        assertTrue(AllHighState.output())
    }

    @Test
    fun anyLowStateOutputIsFalse() {
        assertFalse(AnyLowState.output())
    }

    @Test
    fun allHighTransitionsToAnyLowOnFalseInput() {
        val next = AllHighState.process(false)
        assertEquals(AnyLowState, next)
    }

    @Test
    fun allHighStaysAllHighOnTrueInput() {
        val next = AllHighState.process(true)
        assertEquals(AllHighState, next)
    }

    @Test
    fun anyLowIsAbsorbent() {
        assertEquals(AnyLowState, AnyLowState.process(true))
        assertEquals(AnyLowState, AnyLowState.process(false))
    }

    // --- TwoInputGate ---

    @Test
    fun twoInputGateTrueAndTrueReturnsTrue() {
        val gate = TwoInputGateBuilder()
            .addInput(true)
            .addInput(true)
            .build()
        assertTrue(gate.output())
    }

    @Test
    fun twoInputGateTrueAndFalseReturnsFalse() {
        val gate = TwoInputGateBuilder()
            .addInput(true)
            .addInput(false)
            .build()
        assertFalse(gate.output())
    }

    // --- ThreeInputGate ---

    @Test
    fun threeInputGateAllTrueReturnsTrue() {
        val gate = ThreeInputGateBuilder()
            .addInput(true).addInput(true).addInput(true)
            .build()
        assertTrue(gate.output())
    }

    @Test
    fun threeInputGateWithOneFalseReturnsFalse() {
        val gate = ThreeInputGateBuilder()
            .addInput(true).addInput(false).addInput(true)
            .build()
        assertFalse(gate.output())
    }

    // --- FourInputGate ---

    @Test
    fun fourInputGateAllTrueReturnsTrue() {
        val gate = FourInputGateBuilder()
            .addInput(true).addInput(true).addInput(true).addInput(true)
            .build()
        assertTrue(gate.output())
    }

    @Test
    fun fourInputGateWithOneFalseReturnsFalse() {
        val gate = FourInputGateBuilder()
            .addInput(true).addInput(true).addInput(false).addInput(true)
            .build()
        assertFalse(gate.output())
    }

    // --- EightInputGate ---

    @Test
    fun eightInputGateAllTrueReturnsTrue() {
        val gate = EightInputGateBuilder()
            .addInput(true).addInput(true).addInput(true).addInput(true)
            .addInput(true).addInput(true).addInput(true).addInput(true)
            .build()
        assertTrue(gate.output())
    }

    @Test
    fun eightInputGateWithLastFalseReturnsFalse() {
        val gate = EightInputGateBuilder()
            .addInput(true).addInput(true).addInput(true).addInput(true)
            .addInput(true).addInput(true).addInput(true).addInput(false)
            .build()
        assertFalse(gate.output())
    }
}
