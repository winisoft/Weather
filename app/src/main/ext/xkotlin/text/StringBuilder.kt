package xkotlin.text


fun StringBuilder.appendAll(vararg items: String) = this.apply {
    listOf<String>().apply {
        items.forEach { append(it) }
    }
}

fun StringBuilder.appendAll(block: List<String>.() -> Unit) = this.apply {
    listOf<String>().apply(block).forEach { append(it) }
}
