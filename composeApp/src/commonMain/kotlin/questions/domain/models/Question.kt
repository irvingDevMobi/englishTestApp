package questions.domain.models

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val id: Long = 0,
    val question: String = "",
    val answers: List<Answer> = emptyList()
)
