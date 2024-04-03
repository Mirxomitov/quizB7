package uz.gita.quizapp.presentation.viewmodels.impl

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import uz.gita.quizapp.data.model.QuestionModel
import uz.gita.quizapp.data.model.QuestionResultModel
import uz.gita.quizapp.data.model.QuizData
import uz.gita.quizapp.domain.AppRepository
import uz.gita.quizapp.navigation.AppNavigator
import uz.gita.quizapp.presentation.viewmodels.QuizViewModel
import javax.inject.Inject

@HiltViewModel
class QuizViewModelImpl @Inject constructor(
    private val repository: AppRepository,
    private val navigator: AppNavigator
) : ViewModel(), QuizViewModel {
    override val questionLoaderLiveData = MutableLiveData<QuestionModel>()
    override val questionCountLiveData = MutableLiveData<String>()
    override val progressLiveData = MutableLiveData<Int>()
    override val timerLiveData = MutableLiveData<Int>()
    override val openFinishScreen = MutableLiveData<Int>()
    override val clearChecks = MutableLiveData<Unit>()
    override val openDialog = MutableLiveData<Unit>()
    override val nextButtonState = MutableLiveData<Boolean>()


    private var currentQuestionIndex = 0
    private lateinit var quiz: QuizData
    private var correctAnswers = 0
    private val listResult = ArrayList<QuestionResultModel>()

    override fun loadQuestion() {
        quiz = repository.getQuizData()
        timerLiveData.value = quiz.time.toInt()
        displayQuestion()
    }

    override fun checkAnswerByIndex(index: Int) {
        if (index == -1 || index >= quiz.questionsArr.size) return
        quiz.questionsArr[currentQuestionIndex].apply {
            listResult.add(QuestionResultModel(question, this.options[index], correctAnswer))
            if (correctAnswer == this.options[index]) {
                correctAnswers++
            }
        }

        if (currentQuestionIndex + 1 >= quiz.questionsArr.size) {
            repository.saveCurrentQuizResult(listResult, correctAnswers, quiz.questionsArr.size)
            openFinishScreen.value = correctAnswers
        } else {
            currentQuestionIndex++
            displayQuestion()
        }
    }

    override fun onTimeExpired() {
        while (++currentQuestionIndex < quiz.questionsArr.size) listResult.add(
            QuestionResultModel(
                question = quiz.questionsArr[currentQuestionIndex].question,
                chosen = "not chosen",
                correct = quiz.questionsArr[currentQuestionIndex].correctAnswer,
            )
        )

        repository.saveCurrentQuizResult(
            listResult,
            correctAnswers,
            currentQuestionIndex + 1
        )
        openFinishScreen.value = correctAnswers
    }

    override fun finish() {
        repository.saveCurrentQuizResult(
            listResult,
            correctAnswers,
            currentQuestionIndex + 1
        )

        openFinishScreen.value = correctAnswers
    }

    private fun displayQuestion() {
        questionLoaderLiveData.value = quiz.questionsArr[currentQuestionIndex]
        questionCountLiveData.value = "${currentQuestionIndex + 1} / ${quiz.questionsArr.size}"
        progressLiveData.value = (currentQuestionIndex * 100f / quiz.questionsArr.size).toInt()
        clearChecks.value = Unit
        nextButtonState.value = false
    }
}