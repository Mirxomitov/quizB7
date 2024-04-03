package uz.gita.quizapp.navigation

import androidx.annotation.IdRes
import androidx.navigation.NavDirections

interface AppNavigator {

    suspend fun navigateTo(directions: NavDirections)
    suspend fun navigateTo(@IdRes  directions: Int)

    suspend fun popBackStack()
}