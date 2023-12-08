package questions.presentation

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import questions.data.remote.QuestionsRemote
import questions.domain.models.EnglishLevel
import utils.onFailure
import utils.onSuccess

class ExamViewModel(
    private val questionsRemote: QuestionsRemote
) : ViewModel() {

    private val _uiState = MutableStateFlow(ExamUiState())
    val uiState: StateFlow<ExamUiState> = _uiState.asStateFlow()

    fun fetchQuestions(englishLevel: EnglishLevel) = viewModelScope.launch {
        _uiState.update { it.copy(isLoading = true) }
        questionsRemote.getQuestions(englishLevel).onSuccess { questions ->
            _uiState.update {
                it.copy(
                    isLoading = false,
                    questions = questions
                )
            }
        }.onFailure { error ->
            _uiState.update {
                it.copy(
                    isLoading = false,
                    error = error?.stringMessage
                )
            }
        }
    }
}
