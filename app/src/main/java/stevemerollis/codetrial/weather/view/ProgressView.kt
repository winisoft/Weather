package stevemerollis.codetrial.weather.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.viewBinding
import androidx.appcompat.widget.AppCompatButton
import androidx.constraintlayout.widget.ConstraintLayout
import stevemerollis.codetrial.weather.databinding.ViewProgressBinding


class ProgressView
@JvmOverloads
constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    val viewBinding: ViewProgressBinding by viewBinding(ViewProgressBinding::bind)

    fun show() {
        viewBinding.root.visibility = View.VISIBLE
    }

    fun hide() {
        viewBinding.root.visibility = View.GONE
    }
}