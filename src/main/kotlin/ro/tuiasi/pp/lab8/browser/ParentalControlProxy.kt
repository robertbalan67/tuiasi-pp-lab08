package ro.tuiasi.pp.lab8.browser

// Proxy — adaugă control parental peste un HttpClient real.
// Interceptează cererile GET și blochează URL-urile ale căror domenii
// se regăsesc în lista `blockedDomains`.
class ParentalControlProxy(
    private val client: HttpClient,
    private val blockedDomains: Set<String>,
) : HttpClient {

    // TODO: Implementează get():
    //       - Extrage domeniul din request.url (ex: "example.com" din "https://example.com/page").
    //       - Dacă domeniul este blocat, returnează HttpResponse(403, "Acces blocat de controlul parental.")
    //         fără a apela client.get().
    //       - Altfel, delege cererea către client.get(request).
    override fun get(request: HttpRequest): HttpResponse = TODO("De implementat")
}
