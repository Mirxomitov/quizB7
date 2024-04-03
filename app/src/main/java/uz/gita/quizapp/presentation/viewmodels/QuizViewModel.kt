package uz.gita.quizapp.presentation.viewmodels

import androidx.lifecycle.LiveData
import uz.gita.quizapp.data.model.QuestionModel

interface QuizViewModel {
    val timerLiveData: LiveData<Int>
    val openFinishScreen: LiveData<Int>
    val questionLoaderLiveData: LiveData<QuestionModel>
    val questionCountLiveData : LiveData<String>
    val progressLiveData : LiveData<Int>
    val clearChecks : LiveData<Unit>
    val openDialog : LiveData<Unit>
    val nextButtonState : LiveData<Boolean>

    fun loadQuestion()
    fun checkAnswerByIndex(index: Int)
    fun onTimeExpired()
    fun finish()
}