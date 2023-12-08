package questions.presentation

import questions.domain.models.Question

data class ExamUiState(
    val isLoading: Boolean = false,
    val questions: List<Question> = emptyList(),
    val error: String? = null
)
