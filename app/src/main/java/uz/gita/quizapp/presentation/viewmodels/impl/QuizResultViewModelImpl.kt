package uz.gita.quizapp.presentation.viewmodels.impl

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import uz.gita.quizapp.data.model.QuestionResultModel
import uz.gita.quizapp.domain.AppRepository
import uz.gita.quizapp.presentation.viewmodels.QuizResultViewModel
import javax.inject.Inject

@HiltViewModel
class QuizResultViewModelImpl @Inject constructor(
    private val repository: AppRepository,
) : ViewModel(), QuizResultViewModel {
    override val resultDataFlow = MutableSharedFlow<List<QuestionResultModel>>()
    override val progressIndicatorFlow = MutableSharedFlow<Int>()

    override fun showResults() {
        viewModelScope.launch {
            resultDataFlow.emit(repository.getResultData())
            progressIndicatorFlow.emit(repository.getResultPercentage())
        }
    }
}