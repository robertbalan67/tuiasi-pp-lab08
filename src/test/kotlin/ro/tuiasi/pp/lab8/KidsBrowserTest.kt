package ro.tuiasi.pp.lab8

import ro.tuiasi.pp.lab8.browser.HttpClient
import ro.tuiasi.pp.lab8.browser.HttpRequest
import ro.tuiasi.pp.lab8.browser.HttpResponse
import ro.tuiasi.pp.lab8.browser.KidsBrowserFacade
import ro.tuiasi.pp.lab8.browser.ParentalControlProxy
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

// Stub HttpClient care returnează răspunsuri fixe, fără apeluri HTTP reale.
private class StubHttpClient : HttpClient {
    override fun get(request: HttpRequest) = HttpResponse(200, "Continut: ${request.url}")
}

class KidsBrowserTest {

    // --- Prototype ---

    @Test
    fun httpRequestCloneProducesEqualButDistinctObject() {
        val original = HttpRequest("https://example.com", mapOf("Accept" to "text/html"))
        val cloned = original.clone()
        assertEquals(original, cloned)
        assertTrue(original !== cloned) // obiecte distincte
    }

    // --- Proxy ---

    @Test
    fun proxyAllowsPermittedUrl() {
        val proxy = ParentalControlProxy(StubHttpClient(), setOf("badsite.com"))
        val response = proxy.get(HttpRequest("https://wikipedia.org/wiki/Kotlin"))
        assertEquals(200, response.statusCode)
    }

    @Test
    fun proxyBlocksBlockedDomain() {
        val proxy = ParentalControlProxy(StubHttpClient(), setOf("badsite.com"))
        val response = proxy.get(HttpRequest("https://badsite.com/page"))
        assertEquals(403, response.statusCode)
    }

    @Test
    fun proxyDoesNotCallRealClientForBlockedDomain() {
        var called = false
        val trackingClient = object : HttpClient {
            override fun get(request: HttpRequest): HttpResponse {
                called = true
                return HttpResponse(200, "")
            }
        }
        val proxy = ParentalControlProxy(trackingClient, setOf("badsite.com"))
        proxy.get(HttpRequest("https://badsite.com/page"))
        assertTrue(!called, "Clientul real nu trebuie apelat pentru domenii blocate")
    }

    // --- Facade ---

    @Test
    fun facadeReturnsBodyForAllowedUrl() {
        val facade = KidsBrowserFacade(
            ParentalControlProxy(StubHttpClient(), setOf("badsite.com"))
        )
        val result = facade.browse("https://wikipedia.org/wiki/Kotlin")
        assertTrue(result.contains("wikipedia.org"))
    }

    @Test
    fun facadeReturnsErrorMessageForBlockedUrl() {
        val facade = KidsBrowserFacade(
            ParentalControlProxy(StubHttpClient(), setOf("badsite.com"))
        )
        val result = facade.browse("https://badsite.com/page")
        assertTrue(result.isNotBlank())
        assertTrue(result.contains("blocat", ignoreCase = true))
    }
}
