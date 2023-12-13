import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.aallam.openai.api.http.Timeout
import com.aallam.openai.client.OpenAI
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import questions.data.remote.QuestionsRemoteImpl
import questions.presentation.ExamScreen
import questions.presentation.ExamViewModel
import kotlin.time.Duration.Companion.seconds

@Composable
fun App() {
    MaterialTheme {

        var apiKey by remember { mutableStateOf("") }
        var drawExam by remember { mutableStateOf(false) }
        /*
        val mockQuestion1 = Question(
            question = "How are you?",
            answers = listOf(Answer("Excellent"), Answer("Fine"), Answer("Not to bad"))
        )
        val mockQuestion2 = mockQuestion1.copy(id = 1)
        ExamUi(questions = listOf(mockQuestion1, mockQuestion2))
        */
        // /*
        if (drawExam) {
            val examViewModel = getViewModel(Unit, viewModelFactory {
                ExamViewModel(
                    QuestionsRemoteImpl(
                        OpenAI(
                            token = apiKey,
                            timeout = Timeout(socket = 120.seconds)
                        )
                    )
                )
            })
            ExamScreen(viewModel = examViewModel)
        } else {
            Column {
                TextField(
                    value = apiKey,
                    onValueChange = { apiKey = it.trim() },
                    label = { Text(text = "Api Key") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = PasswordVisualTransformation()
                )
                Button(
                    onClick = { drawExam = true },
                    enabled = apiKey.isNotBlank()
                ) {
                    Text(text = "Start")
                }
            }
        }
        // */
    }
}
