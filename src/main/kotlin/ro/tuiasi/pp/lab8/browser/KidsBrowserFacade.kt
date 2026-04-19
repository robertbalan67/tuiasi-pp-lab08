package ro.tuiasi.pp.lab8.browser

// Facade — oferă o interfață simplă pentru browser-ul de copii.
// Ascunde complexitatea: clonarea cererii prototip, apelul prin proxy,
// și extragerea body-ului din răspuns.
class KidsBrowserFacade(private val client: HttpClient) {

    // Cererea prototip de bază — va fi clonată pentru fiecare navigare.
    private val baseRequest = HttpRequest(url = "")

    // TODO: Implementează browse(url):
    //       1. Clonează baseRequest și setează url-ul primit ca parametru.
    //       2. Trimite cererea prin client.get().
    //       3. Returnează body-ul răspunsului dacă statusCode == 200,
    //          altfel returnează mesajul de eroare din body.
    fun browse(url: String): String = TODO("De implementat")
}
