package questions.data.remote

import questions.domain.models.EnglishLevel
import questions.domain.models.Question
import utils.ResultOf

interface QuestionsRemote {
    suspend fun getQuestions(englishLevel: EnglishLevel): ResultOf<List<Question>>
}
