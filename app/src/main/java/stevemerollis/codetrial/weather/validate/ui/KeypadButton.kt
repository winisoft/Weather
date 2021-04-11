package stevemerollis.codetrial.weather.validate.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import stevemerollis.codetrial.weather.databinding.ViewKeypadButtonBinding

class KeypadButton
@JvmOverloads
constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var binding: ViewKeypadButtonBinding =
            ViewKeypadButtonBinding.inflate(LayoutInflater.from(context), this)

    init {
        addView(binding.root)
    }

    fun setData() = with(binding) {

    }

}