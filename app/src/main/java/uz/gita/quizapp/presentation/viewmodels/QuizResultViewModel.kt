package uz.gita.quizapp.presentation.viewmodels

import kotlinx.coroutines.flow.Flow
import uz.gita.quizapp.data.model.QuestionResultModel


interface QuizResultViewModel {
    val resultDataFlow: Flow<List<QuestionResultModel>>
    val progressIndicatorFlow : Flow<Int>

    fun showResults()
}