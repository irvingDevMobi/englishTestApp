import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.aallam.openai.client.OpenAI
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import questions.data.remote.QuestionsRemoteImpl
import questions.presentation.ExamScreen
import questions.presentation.ExamViewModel

@Composable
fun App() {
    MaterialTheme {
        val examViewModel = getViewModel(Unit, viewModelFactory {
            ExamViewModel(
                QuestionsRemoteImpl(
                    OpenAI(
                        token = OpenAiKey,
                    )
                )
            )
        })
        ExamScreen(viewModel = examViewModel)
    }
}

const val OpenAiKey = ""
