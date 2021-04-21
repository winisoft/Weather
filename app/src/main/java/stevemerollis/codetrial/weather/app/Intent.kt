package stevemerollis.codetrial.weather.app

interface Intent<T> {
    fun reduce(previous: T): T
}

data class Okie(val s: String) {

}
val a = object: Intent<Okie> {
    override fun reduce(previous: Okie): Okie =
        previous.copy(s = previous.s + "s2")


}