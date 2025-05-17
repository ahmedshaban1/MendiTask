package com.mendi.task.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.mendi.task.R

val dmFontFamily = FontFamily(
  Font(R.font.dm_sans_regular, FontWeight.Normal),
  Font(R.font.dm_sans_bold, FontWeight.Bold),
  Font(R.font.dm_sans_medium, FontWeight.Medium),
)

// Set of Material typography styles to start with
val Typography = Typography(
  labelMedium = TextStyle(
    fontFamily = dmFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp,
  ),
  labelLarge = TextStyle(
    fontFamily = dmFontFamily,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
  ),
  displayMedium = TextStyle(
    fontFamily = dmFontFamily,
    fontWeight = FontWeight.Bold,
    fontSize = 18.sp,
  ),

)
