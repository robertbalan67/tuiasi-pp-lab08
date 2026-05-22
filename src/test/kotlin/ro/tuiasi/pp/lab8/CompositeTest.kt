package ro.tuiasi.pp.lab8

import ro.tuiasi.pp.lab8.composite.*
import java.io.ByteArrayOutputStream
import java.io.PrintStream
import kotlin.test.*

class CompositeTest {

    /** Capturează stdout în timpul execuției [block]. */
    private fun captureOutput(block: () -> Unit): String {
        val baos = ByteArrayOutputStream()
        val old = System.out
        System.setOut(PrintStream(baos))
        try { block() } finally { System.setOut(old) }
        return baos.toString()
    }

    @Test
    fun `Idee showContent afiseaza descrierea`() {
        val idee = Idee("o idee buna")
        val output = captureOutput { idee.showContent(0) }
        assertTrue(output.contains("o idee buna"), "Output-ul trebuie să conțină descrierea")
    }

    @Test
    fun `Activitate showContent afiseaza numele`() {
        val act = Activitate("Prima zi")
        val output = captureOutput { act.showContent(0) }
        assertTrue(output.contains("Prima zi"))
    }

    @Test
    fun `Activitate cu copil afiseaza si copilul`() {
        val act = Activitate("Ziua 1")
        val idee = Idee("vizita muzeu")
        act.addChild(idee)

        val output = captureOutput { act.showContent(0) }
        assertTrue(output.contains("Ziua 1"))
        assertTrue(output.contains("vizita muzeu"))
    }

    @Test
    fun `Calatorie cu arbore complet afiseaza toata structura`() {
        val calatorie = Calatorie("Conferinta RSA")
        val ziua1 = Activitate("Ziua 1")
        ziua1.addChild(Idee("Vizita Alcatraz"))
        val ziua2 = Activitate("Ziua 2")
        ziua2.addChild(Idee("Prezentare lucrare"))
        ziua2.addChild(Idee("Corelat date"))
        calatorie.addChild(ziua1)
        calatorie.addChild(ziua2)

        val output = captureOutput { calatorie.showContent(0) }
        assertTrue(output.contains("Conferinta RSA"))
        assertTrue(output.contains("Ziua 1"))
        assertTrue(output.contains("Vizita Alcatraz"))
        assertTrue(output.contains("Ziua 2"))
        assertTrue(output.contains("Prezentare lucrare"))
    }

    @Test
    fun `removeChild elimina copilul din lista`() {
        val act = Activitate("Activitate")
        val idee = Idee("de eliminat")
        act.addChild(idee)
        act.removeChild(idee)

        val output = captureOutput { act.showContent(0) }
        assertFalse(output.contains("de eliminat"))
    }

    @Test
    fun `Idee nu accepta copii`() {
        val idee = Idee("frunza")
        assertFailsWith<UnsupportedOperationException> {
            idee.addChild(Idee("alta"))
        }
    }

    @Test
    fun `Cadou nu accepta copii`() {
        val cadou = Cadou("magnet")
        assertFailsWith<UnsupportedOperationException> {
            cadou.addChild(Idee("alta"))
        }
    }

    @Test
    fun `indentare creste cu nivelul`() {
        val calatorie = Calatorie("Root")
        val act = Activitate("Child")
        act.addChild(Idee("GrandChild"))
        calatorie.addChild(act)

        val output = captureOutput { calatorie.showContent(0) }
        val lines = output.lines().filter { it.isNotBlank() }
        // Fiecare nivel următor trebuie să fie mai indentat decât cel anterior
        assertTrue(lines.size >= 3, "Trebuie cel puțin 3 linii (root, child, grandchild)")
        val rootIndent  = lines[0].length - lines[0].trimStart().length
        val childIndent = lines[1].length - lines[1].trimStart().length
        val grandIndent = lines[2].length - lines[2].trimStart().length
        assertTrue(rootIndent < childIndent, "Child trebuie mai indentat decât root")
        assertTrue(childIndent < grandIndent, "GrandChild trebuie mai indentat decât child")
    }
}
