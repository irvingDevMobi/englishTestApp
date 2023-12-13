package questions.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import questions.domain.models.Answer
import questions.domain.models.Question

@Composable
fun QuestionUi(
    question: Question,
    answerSelected: Answer?,
    onQuestionAnswered: (Answer) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(text = question.question)
        Spacer(Modifier.height(8.dp))
        AnswersUi(
            answers = question.answers,
            selectedAnswer = answerSelected,
            onAnswerSelected = onQuestionAnswered
        )
    }
}

@Composable
fun AnswersUi(
    answers: List<Answer>,
    selectedAnswer: Answer?,
    onAnswerSelected: (Answer) -> Unit
) {
    Column {
        answers.forEach {
            val isSelected = it == selectedAnswer
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = isSelected,
                    onClick = { onAnswerSelected(it) }
                )
                Text(text = it.text)
            }
        }
    }
}
