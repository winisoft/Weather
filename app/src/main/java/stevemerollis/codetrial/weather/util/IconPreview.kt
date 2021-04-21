package stevemerollis.codetrial.weather.util

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import compose.icons.AllIcons
import compose.icons.FontAwesomeIcons
import compose.icons.WeatherIcons
import java.lang.reflect.Modifier
import java.nio.file.Files.size

class ParamProvider: PreviewParameterProvider<List<ImageVector>> {
    override val values: Sequence<List<ImageVector>>
        get() = sequenceOf(FontAwesomeIcons.AllIcons, WeatherIcons.AllIcons)

}

@ExperimentalFoundationApi
@Preview
@Composable
fun IconPreview(@PreviewParameter(ParamProvider::class) pack: List<ImageVector>, from: Int = 0, to: Int = 100) {
    Surface(color = MaterialTheme.colors.background) {

        val icons = remember { pack.subList(from, to) }

        LazyVerticalGrid(cells = GridCells.Adaptive(36.dp)) {
            items(icons.size) {
                Icon(
                    imageVector = icons[it],
                    contentDescription = ""
                )
            }
        }
    }
}