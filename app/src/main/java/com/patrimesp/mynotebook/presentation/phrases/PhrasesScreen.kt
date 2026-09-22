package com.patrimesp.mynotebook.presentation.phrases

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.patrimesp.mynotebook.R

@Composable
fun PhrasesScreen(phrasesViewModel: PhrasesViewModel = hiltViewModel()) {
    val uiState by phrasesViewModel.uiState.collectAsStateWithLifecycle()
    PhrasesContent(
        uiState = uiState,
        onScreenTapped = { phrasesViewModel.onScreenTapped((0..19).random()) }
    )
}

@Composable
private fun PhrasesContent(uiState: PhrasesUiState, onScreenTapped: () -> Unit) {
    val white = colorResource(R.color.white)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(colorResource(R.color.purple_500), colorResource(R.color.purple_700))
                )
            )
            .clickable(onClick = onScreenTapped)
            .padding(horizontal = 36.dp, vertical = 48.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "“",
                color = white.copy(alpha = 0.55f),
                fontFamily = FontFamily.Serif,
                fontSize = 88.sp,
                lineHeight = 72.sp
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = uiState.text,
                color = white,
                fontFamily = FontFamily.Serif,
                fontSize = 30.sp,
                lineHeight = 40.sp,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(32.dp))
            Box(
                modifier = Modifier
                    .width(44.dp)
                    .height(2.dp)
                    .background(white.copy(alpha = 0.65f))
            )
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                text = uiState.author,
                color = white.copy(alpha = 0.9f),
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                letterSpacing = 1.sp,
                textAlign = TextAlign.Center
            )
        }

        if (uiState.loading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.BottomCenter),
                color = white,
                strokeWidth = 2.dp
            )
        } else {
            Text(
                text = "Toca para descubrir otra frase",
                modifier = Modifier.align(Alignment.BottomCenter),
                color = white.copy(alpha = 0.7f),
                fontSize = 13.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun PhrasesScreenPreview() {
    PhrasesContent(
        uiState = PhrasesUiState(),
        onScreenTapped = {}
    )
}
