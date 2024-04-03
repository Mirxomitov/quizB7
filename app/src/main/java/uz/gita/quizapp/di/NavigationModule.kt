package uz.gita.quizapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.quizapp.navigation.AppNavigation
import uz.gita.quizapp.navigation.AppNavigationDispatcher
import uz.gita.quizapp.navigation.AppNavigator
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds @Singleton
    fun getNavigation(navigation : AppNavigationDispatcher) : AppNavigator
}