package ro.tuiasi.pp.lab8.browser

data class HttpResponse(val statusCode: Int, val body: String)

// Interfața clientului HTTP — permite înlocuirea implementării reale cu un stub în teste.
interface HttpClient {
    fun get(request: HttpRequest): HttpResponse
}

// Implementare reală minimală (pentru demo, nu necesară în teste).
// Poate fi înlocuită cu orice bibliotecă HTTP (OkHttp, Ktor, etc.)
class RealHttpClient : HttpClient {
    override fun get(request: HttpRequest): HttpResponse {
        // TODO (opțional): realizează un GET HTTP real la request.url
        //                  și returnează statusCode + body.
        TODO("De implementat opțional")
    }
}
