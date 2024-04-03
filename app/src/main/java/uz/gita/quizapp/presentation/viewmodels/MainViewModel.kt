package uz.gita.quizapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow
import uz.gita.quizapp.data.model.QuizData


interface MainViewModel {
    val showQuizDataFlow: Flow<List<QuizData>>
    val openQuizScreen: LiveData<Unit>

    fun showQuizData()
    fun openQuizScreen(data: QuizData)
}