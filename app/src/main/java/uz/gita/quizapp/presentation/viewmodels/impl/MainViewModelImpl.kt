package uz.gita.quizapp.presentation.viewmodels.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita.quizapp.data.model.QuizData
import uz.gita.quizapp.domain.AppRepository
import uz.gita.quizapp.navigation.AppNavigator
import uz.gita.quizapp.presentation.viewmodels.MainViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModelImpl @Inject constructor(
    private val navigation: AppNavigator,
    private val repository: AppRepository
) : ViewModel(), MainViewModel {
    override val showQuizDataFlow = MutableSharedFlow<List<QuizData>>()
    override val openQuizScreen = MutableLiveData<Unit>()

    override fun showQuizData() {
        repository.getAllQuizzes().onEach {
            it.onSuccess {
                showQuizDataFlow.emit(it)
            }
            it.onFailure {}
        }
            .launchIn(viewModelScope)

    }

    override fun openQuizScreen(data: QuizData) {
        repository.saveQuizData(data)
        // todo ishlamadi ustozga ko'rsatish kerak
        /*viewModelScope.launch {
            navigation.navigateTo(MainScreenDirections.actionMainScreenToQuizScreen(data))
        }*/

        openQuizScreen.value = Unit
    }
}