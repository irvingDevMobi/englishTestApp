package questions.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import questions.domain.models.EnglishLevel

@Composable
fun ExamScreen(
    viewModel: ExamViewModel
) {
    val uiState by viewModel.uiState.collectAsState()
    LaunchedEffect(Unit) {
        viewModel.fetchQuestions(EnglishLevel.B1)
    }
    ExamScreen(uiState = uiState)
}

@Composable
fun ExamScreen(
    uiState: ExamUiState
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        if (uiState.isLoading) CircularProgressIndicator()
        else {
            Text(text = uiState.questions.toString())
            uiState.error?.let {
                Text(text = it, color = Color.Red)
            }
        }
    }
}
