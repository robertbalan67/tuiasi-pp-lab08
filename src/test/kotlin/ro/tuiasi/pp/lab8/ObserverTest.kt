package ro.tuiasi.pp.lab8

import ro.tuiasi.pp.lab8.observer.Kitchen
import ro.tuiasi.pp.lab8.observer.OrderLog
import java.io.File
import kotlin.test.*

class ObserverTest {

    private fun makeKitchen() = Kitchen(
        employees = mutableListOf("Bob", "Alice"),
        products  = mutableListOf("Burger", "Fries", "Cola")
    )

    @Test
    fun `observer primeste notificare la servire`() {
        val kitchen = makeKitchen()
        val log = OrderLog()
        kitchen.addObserver(log)

        kitchen.serve("Bob", "Burger", "Client1")

        assertEquals(1, log.entries.size)
    }

    @Test
    fun `log contine angajatul, produsul si clientul`() {
        val kitchen = makeKitchen()
        val log = OrderLog()
        kitchen.addObserver(log)

        kitchen.serve("Alice", "Fries", "Maria")

        val entry = log.entries.first()
        assertTrue(entry.contains("Alice"), "Intrarea trebuie să conțină angajatul")
        assertTrue(entry.contains("Fries"), "Intrarea trebuie să conțină produsul")
        assertTrue(entry.contains("Maria"), "Intrarea trebuie să conțină clientul")
    }

    @Test
    fun `mai multe serviri produc mai multe intrari`() {
        val kitchen = makeKitchen()
        val log = OrderLog()
        kitchen.addObserver(log)

        kitchen.serve("Bob", "Burger", "Client1")
        kitchen.serve("Alice", "Cola", "Client2")
        kitchen.serve("Bob", "Fries", "Client3")

        assertEquals(3, log.entries.size)
    }

    @Test
    fun `observer eliminat nu mai primeste notificari`() {
        val kitchen = makeKitchen()
        val log = OrderLog()
        kitchen.addObserver(log)
        kitchen.removeObserver(log)

        kitchen.serve("Bob", "Burger", "Client1")

        assertEquals(0, log.entries.size)
    }

    @Test
    fun `servire cu angajat inexistent arunca exceptie`() {
        val kitchen = makeKitchen()
        assertFailsWith<IllegalArgumentException> {
            kitchen.serve("Necunoscut", "Burger", "Client1")
        }
    }

    @Test
    fun `servire cu produs inexistent arunca exceptie`() {
        val kitchen = makeKitchen()
        assertFailsWith<IllegalArgumentException> {
            kitchen.serve("Bob", "Pizza", "Client1")
        }
    }

    @Test
    fun `writeToFile scrie intrarile in fisier`() {
        val kitchen = makeKitchen()
        val log = OrderLog()
        kitchen.addObserver(log)
        kitchen.serve("Bob", "Burger", "Ana")
        kitchen.serve("Alice", "Cola", "Ion")

        val tmp = File.createTempFile("orders_test", ".txt")
        try {
            log.writeToFile(tmp.absolutePath)
            val lines = tmp.readLines()
            assertEquals(2, lines.size)
            assertTrue(lines[0].contains("Bob") || lines[0].contains("Ana"))
        } finally {
            tmp.delete()
        }
    }
}
