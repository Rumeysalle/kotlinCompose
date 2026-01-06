package com.example.view.screens.home


import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.remember


@Composable
fun GenreChip(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Text(
        text = text,
        color = if (isSelected) Color.Green else Color.White,
        fontSize = 14.sp,
        maxLines = 1,
        modifier = Modifier
            .clickable (
                interactionSource = remember { MutableInteractionSource() },
                indication = LocalIndication.current,
            ) {onClick()}
            .padding(horizontal = 12.dp, vertical = 8.dp)
    )
}

