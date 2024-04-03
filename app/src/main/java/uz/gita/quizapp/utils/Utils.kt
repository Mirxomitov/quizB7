package uz.gita.quizapp.utils

import timber.log.Timber

fun logger(msg : String, tag : String = "TTT") {
    Timber.tag(tag).d(message = msg)
}