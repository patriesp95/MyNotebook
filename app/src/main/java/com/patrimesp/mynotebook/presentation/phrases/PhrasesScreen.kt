package com.patrimesp.mynotebook.presentation.phrases

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.patrimesp.mynotebook.R

@Composable
fun PhrasesScreen(phrasesViewModel: PhrasesViewModel = hiltViewModel()) {
    val uiState by phrasesViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .clickable { phrasesViewModel.onScreenTapped((0..19).random()) }
            .fillMaxSize()
            .background(colorResource(R.color.purple_700))
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = uiState.text,
            color = colorResource(R.color.white),
            fontSize = 28.sp,
            textAlign = TextAlign.Center
        )
        Text(
            text = uiState.author,
            modifier = Modifier.padding(top = 24.dp),
            color = colorResource(R.color.white),
            fontSize = 22.sp,
            textAlign = TextAlign.Center
        )
    }
}
