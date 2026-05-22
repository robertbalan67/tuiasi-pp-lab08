package ro.tuiasi.pp.lab8

import ro.tuiasi.pp.lab8.memento.Clock
import ro.tuiasi.pp.lab8.memento.ClockCaretaker
import kotlin.test.*

class MementoTest {

    @Test
    fun `setTime seteaza ora corecta`() {
        val clock = Clock()
        clock.setTime(10, 30, 45)
        assertEquals(10, clock.hours)
        assertEquals(30, clock.minutes)
        assertEquals(45, clock.seconds)
    }

    @Test
    fun `setTime cu valori invalide arunca exceptie`() {
        val clock = Clock()
        assertFailsWith<IllegalArgumentException> { clock.setTime(25, 0, 0) }
        assertFailsWith<IllegalArgumentException> { clock.setTime(0, 60, 0) }
        assertFailsWith<IllegalArgumentException> { clock.setTime(0, 0, 60) }
        assertFailsWith<IllegalArgumentException> { clock.setTime(-1, 0, 0) }
    }

    @Test
    fun `save creeaza memento cu starea curenta`() {
        val clock = Clock(12, 30, 0)
        val memento = clock.save()
        assertEquals(12, memento.hours)
        assertEquals(30, memento.minutes)
        assertEquals(0, memento.seconds)
    }

    @Test
    fun `restore recupereaza starea salvata`() {
        val clock = Clock(12, 0, 0)
        val memento = clock.save()
        clock.setTime(23, 59, 59)
        clock.restore(memento)
        assertEquals(12, clock.hours)
        assertEquals(0, clock.minutes)
        assertEquals(0, clock.seconds)
    }

    @Test
    fun `tickOnce incrementeaza secundele`() {
        val clock = Clock(0, 0, 0)
        clock.tickOnce()
        assertEquals(1, clock.seconds)
    }

    @Test
    fun `tickOnce gestioneaza overflow la minute`() {
        val clock = Clock(0, 0, 59)
        clock.tickOnce()
        assertEquals(0, clock.seconds)
        assertEquals(1, clock.minutes)
    }

    @Test
    fun `tickOnce gestioneaza overflow la ore`() {
        val clock = Clock(0, 59, 59)
        clock.tickOnce()
        assertEquals(0, clock.seconds)
        assertEquals(0, clock.minutes)
        assertEquals(1, clock.hours)
    }

    @Test
    fun `caretaker push si pop in ordine LIFO`() {
        val clock = Clock(1, 0, 0)
        val caretaker = ClockCaretaker()

        caretaker.push(clock.save())
        clock.setTime(2, 0, 0)
        caretaker.push(clock.save())

        val last = caretaker.pop()!!
        assertEquals(2, last.hours)

        val first = caretaker.pop()!!
        assertEquals(1, first.hours)
    }

    @Test
    fun `caretaker pop pe stiva goala returneaza null`() {
        val caretaker = ClockCaretaker()
        assertNull(caretaker.pop())
    }

    @Test
    fun `undo complet cu caretaker`() {
        val clock = Clock(10, 0, 0)
        val caretaker = ClockCaretaker()

        // Salvăm starea, modificăm, restaurăm
        caretaker.push(clock.save())
        clock.setTime(20, 30, 0)
        assertEquals(20, clock.hours)

        clock.restore(caretaker.pop()!!)
        assertEquals(10, clock.hours)
        assertEquals(0, clock.minutes)
    }
}
