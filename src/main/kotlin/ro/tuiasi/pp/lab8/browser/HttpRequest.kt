package ro.tuiasi.pp.lab8.browser

// Prototype — cerere HTTP generică ce poate fi clonată.
// Clonarea permite crearea de copii ale unei cereri de bază fără a cunoaște tipul concret.
interface Prototype<T> {
    fun clone(): T
}

// TODO: Implementează HttpRequest ca data class cu câmpurile `url` (String) și
//       `headers` (Map<String, String>, implicit emptyMap()).
//       Clasa trebuie să implementeze Prototype<HttpRequest>.
//       clone() se poate implementa trivial cu copy().
data class HttpRequest(
    val url: String,
    val headers: Map<String, String> = emptyMap(),
) : Prototype<HttpRequest> {
    override fun clone(): HttpRequest = TODO("De implementat")
}
