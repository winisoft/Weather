package xkotlin

import java.text.DecimalFormat

fun String.asPrice(): String = DecimalFormat("###,###,###.00").format(this.toDouble())