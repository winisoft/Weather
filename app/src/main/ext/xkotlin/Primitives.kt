package xkotlin

import java.text.DecimalFormat

fun Double.asPrice(): String = DecimalFormat("###,###,###.00").format(this)