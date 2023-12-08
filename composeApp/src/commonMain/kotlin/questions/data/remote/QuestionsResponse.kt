package questions.data.remote

import kotlinx.serialization.Serializable
import questions.domain.models.Question

@Serializable
data class QuestionsResponse(
    val questions: List<Question>
)
