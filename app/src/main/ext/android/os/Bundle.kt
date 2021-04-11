package android.os



val Bundle.logText: String
    get() = bundleToString(this)

fun bundleToString(bundle: Bundle?): String = StringBuilder("Bundle[").apply {
    bundle?.keySet()?.forEachIndexed { index, it ->
        when (bundle[it]) {
            is IntArray -> (bundle[it] as IntArray).contentToString()
            is ByteArray -> (bundle[it] as ByteArray).contentToString()
            is BooleanArray -> (bundle[it] as BooleanArray).contentToString()
            is ShortArray -> (bundle[it] as ShortArray).contentToString()
            is LongArray -> (bundle[it] as LongArray).contentToString()
            is FloatArray -> (bundle[it] as FloatArray).contentToString()
            is DoubleArray -> (bundle[it] as DoubleArray).contentToString()
            is Array<*> -> (bundle[it] as Array<*>).contentDeepToString()
            is Bundle -> bundleToString(bundle[it] as Bundle)
            else -> it
        }.apply {
            append(if (index == bundle.keySet().size - 1) this else "$this, ")
        }
    }
    append("]")
}.toString()