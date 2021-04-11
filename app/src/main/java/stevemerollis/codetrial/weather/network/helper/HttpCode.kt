package stevemerollis.codetrial.weather.network.helper


interface HttpCode: Map.Entry<Int, String>, Comparable<Int> {

    override val key: Int

    override val value: String

    override fun compareTo(other: Int): Int = key.compareTo(other)

    val description: String
        get() = this.value

    val isInformational: Boolean
        get() = key in 100..199
    val isSuccess: Boolean
        get() = key in 200..299
    val isRedirection: Boolean
        get() = key in 300..399
    val isClientError: Boolean
        get() = key in 400..499
    val isServerError: Boolean
        get() = key in 500..599

    companion object {
        val TAG: String = HttpCode::class.simpleName.toString()
        fun from(value: Int): HttpCode = HttpCodes.find { it.key == value }
            ?: throw IllegalArgumentException("Invalid HTTP code: $value")
    }
}