package questions.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Answer(
    val text: String = "",
    val isCorrect: Boolean = false
)
