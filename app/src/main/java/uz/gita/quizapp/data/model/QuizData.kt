package uz.gita.quizapp.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class QuizData(
    val id: String,
    val imgResID : Int,
    val title: String,
    val description: String,
    val time: String,
    val questionsArr: List<QuestionModel>
) : Parcelable

@Parcelize
data class QuestionModel(
    val question: String,
    val options: List<String>,
    val correctAnswer: String,
) : Parcelable

    data class QuestionResultModel(
    val question: String,
    val chosen: String,
    val correct: String,
)