package questions.presentation

import androidx.compose.runtime.Composable
import questions.domain.models.Answer
import questions.domain.models.Question

@Composable
fun QuestionUi(
    question: Question
) {

}

@Composable
fun Answer(answer: Answer, isChecked: Boolean, onStateChange: (Boolean) -> Unit) {


}
