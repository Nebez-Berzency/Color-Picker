package nebez.berzancy.color_picker

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

sealed class PickerLayout {
    object Horizontal : PickerLayout()
    data class Grid(val column: Int = 4) : PickerLayout()
}


@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
    colors: List<Color>,
    layout: PickerLayout = PickerLayout.Horizontal,
    onColorSelected: (color: Color, index: Int) -> Unit,
    expandedEnabled: Boolean = false,
) {
    when (layout) {
        is PickerLayout.Horizontal -> {
                HorizontalSlider(
                    colors = colors,
                    onColorClick = { color, index ->
                        onColorSelected(color , index)
                    }
                )
        }

        is PickerLayout.Grid -> {
            TODO(reason = "Not yet implemented")
        }
    }
}

@Composable
fun HorizontalSlider(colors : List<Color>, onColorClick: (Color, Int) -> Unit ) {
    if (colors.size <= 10) {

    }
    else {

    }
}


@Composable
private fun Circle(
    isSelected: Boolean,
    circleSize: Dp = 40.dp,
    circleColor: Color,
    tickIcon: ImageVector = Icons.Default.Check,
    onColorClick: (color: Color) -> Unit
) {

    val interactionSource = remember { MutableInteractionSource() }

    Box(
        contentAlignment = Alignment.Center,
        modifier =
            Modifier
                .padding(2.dp)
                .size(circleSize)
                .drawBehind {
                    val r = size.minDimension / 2 - 2
                    drawCircle(
                        color = circleColor
                    )
                    if (isSelected)
                        drawCircle(
                            color = lerp(
                                start = circleColor,
                                stop = Color.Black,
                                fraction = 0.3f
                            ),
                            style = Stroke(width = 6f),
                            radius = r
                        )

                }
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = {
                        onColorClick(circleColor)
                    }
                )

    ) {
        if (isSelected)
            Icon(
                imageVector = tickIcon,
                contentDescription = "Selected"
            )
    }
}
