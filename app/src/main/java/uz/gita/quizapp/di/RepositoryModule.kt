package uz.gita.quizapp.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.quizapp.domain.AppRepository
import uz.gita.quizapp.domain.AppRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds @Singleton
    fun getAppRepository(impl: AppRepositoryImpl): AppRepository
}