package uz.gita.quizapp.domain

import kotlinx.coroutines.flow.Flow
import uz.gita.quizapp.data.model.QuestionResultModel
import uz.gita.quizapp.data.model.QuizData

interface AppRepository {
    fun getAllQuizzes(): Flow<Result<List<QuizData>>>
    fun saveCurrentQuizResult(resultList : List<QuestionResultModel>, correctAnswers : Int, finishedQuestions : Int)
    fun getResultData() : List<QuestionResultModel>
    fun getFinishedQuestionsCount() : Int
    fun getResultPercentage() : Int
    fun getCorrectAnswersCount() : Int
    fun saveQuizData(data: QuizData)
    fun getQuizData() : QuizData
}