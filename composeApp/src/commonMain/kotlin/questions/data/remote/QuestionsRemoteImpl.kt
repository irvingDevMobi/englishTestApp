package questions.data.remote

import com.aallam.openai.api.chat.ChatCompletionRequest
import com.aallam.openai.api.chat.ChatMessage
import com.aallam.openai.api.chat.ChatResponseFormat
import com.aallam.openai.api.chat.ChatRole
import com.aallam.openai.api.model.ModelId
import com.aallam.openai.client.OpenAI
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json
import questions.domain.models.Answer
import questions.domain.models.EnglishLevel
import questions.domain.models.Question
import utils.ResultOf

class QuestionsRemoteImpl(
    private val openAI: OpenAI
) : QuestionsRemote {
    override suspend fun getQuestions(englishLevel: EnglishLevel): ResultOf<List<Question>> {
        return try {
            val request = ChatCompletionRequest(
                model = ModelId("gpt-3.5-turbo-1106"),
                messages = listOf(
                    ChatMessage(
                        role = ChatRole.System,
                        content = "You are an english teacher",
                    ),
                    ChatMessage(
                        role = ChatRole.User,
                        content = """
                        Generate an English Language exam with 10 multiple-choice questions 
                        to assess a student's ${englishLevel.name} level in English.
                        For each question provide four answers, 
                        omit questions related to listening or reading comprehension, 
                        return the answer in a JSON that matches these string class representation:  
                        ${Question()} and ${Answer()}
                    """.trimIndent()
                    )
                ),
                responseFormat = ChatResponseFormat.JsonObject,
                n = 1
            )
            val response = openAI.chatCompletion(request)
            val jsonString = response.choices.first().message.content
                ?: return ResultOf.failure("OpenAi return an empty content")
            println(jsonString)
            val questions = try {
                Json.decodeFromString<QuestionsResponse>(jsonString).questions
            } catch (serializationException: SerializationException) {
                Json.decodeFromString<List<Question>>(jsonString)
            }
            ResultOf.Success(questions)
        } catch (serializationException: SerializationException) {
            ResultOf.failure(
                error = serializationException.message.toString(),
                friendlyMessage = "OpenAi returns an unexpected JSON"
            )
        } catch (exception: Exception) {
            ResultOf.failure(
                error = exception.message.toString(),
            )
        }
    }
}
